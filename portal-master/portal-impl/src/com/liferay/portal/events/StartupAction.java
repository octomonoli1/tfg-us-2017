/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.events;

import com.liferay.portal.fabric.server.FabricServerUtil;
import com.liferay.portal.jericho.CachedLoggerProvider;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.executor.PortalExecutorManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.SystemDataType;
import com.liferay.portal.kernel.nio.intraband.mailbox.MailboxDatagramReceiveHandler;
import com.liferay.portal.kernel.nio.intraband.messaging.MessageDatagramReceiveHandler;
import com.liferay.portal.kernel.nio.intraband.proxy.IntrabandProxyDatagramReceiveHandler;
import com.liferay.portal.kernel.nio.intraband.rpc.RPCDatagramReceiveHandler;
import com.liferay.portal.kernel.patcher.PatcherUtil;
import com.liferay.portal.kernel.resiliency.mpi.MPIHelperUtil;
import com.liferay.portal.kernel.resiliency.spi.agent.annotation.Direction;
import com.liferay.portal.kernel.resiliency.spi.agent.annotation.DistributedRegistry;
import com.liferay.portal.kernel.resiliency.spi.agent.annotation.MatchType;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourceActionLocalServiceUtil;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalLifecycle;
import com.liferay.portal.kernel.util.PortalLifecycleUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.plugin.PluginPackageIndexer;
import com.liferay.portal.tools.DBUpgrader;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.messageboards.util.MBMessageIndexer;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceRegistration;
import com.liferay.registry.dependency.ServiceDependencyListener;
import com.liferay.registry.dependency.ServiceDependencyManager;
import com.liferay.taglib.servlet.JspFactorySwapper;

import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.MimeResponse;
import javax.portlet.PortletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.struts.tiles.taglib.ComponentConstants;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 * @author Raymond Augé
 */
public class StartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			doRun(ids);
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun(String[] ids) throws Exception {

		// Print release information

		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		try (InputStream inputStream = classLoader.getResourceAsStream(
				"com/liferay/portal/events/dependencies/startup.txt")) {

			System.out.println(IOUtils.toString(inputStream));
		}

		System.out.println("Starting " + ReleaseInfo.getReleaseInfo() + "\n");

		// Installed patches

		if (_log.isInfoEnabled() && !PatcherUtil.hasInconsistentPatchLevels()) {
			String installedPatches = StringUtil.merge(
				PatcherUtil.getInstalledPatches(), StringPool.COMMA_AND_SPACE);

			if (Validator.isNull(installedPatches)) {
				_log.info("There are no patches installed");
			}
			else {
				_log.info(
					"The following patches are installed: " + installedPatches);
			}
		}

		// Portal resiliency

		ServiceDependencyManager portalResiliencyServiceDependencyManager =
			new ServiceDependencyManager();

		portalResiliencyServiceDependencyManager.addServiceDependencyListener(
			new PortalResiliencyServiceDependencyLister());

		portalResiliencyServiceDependencyManager.registerDependencies(
			MessageBus.class, PortalExecutorManager.class);

		// Shutdown hook

		if (_log.isDebugEnabled()) {
			_log.debug("Add shutdown hook");
		}

		Runtime runtime = Runtime.getRuntime();

		runtime.addShutdownHook(new Thread(new ShutdownHook()));

		// Indexers

		ServiceDependencyManager indexerRegistryServiceDependencyManager =
			new ServiceDependencyManager();

		indexerRegistryServiceDependencyManager.addServiceDependencyListener(
			new ServiceDependencyListener() {

				@Override
				public void dependenciesFulfilled() {
					IndexerRegistryUtil.register(new MBMessageIndexer());
					IndexerRegistryUtil.register(new PluginPackageIndexer());
				}

				@Override
				public void destroy() {
				}

			});

		indexerRegistryServiceDependencyManager.registerDependencies(
			IndexerRegistry.class);

		// MySQL version

		DB db = DBManagerUtil.getDB();

		if ((db.getDBType() == DBType.MYSQL) &&
			GetterUtil.getFloat(db.getVersionString()) < 5.6F) {

			_log.error(
				"Please upgrade to at least MySQL 5.6.4. The portal no " +
					"longer supports older versions of MySQL.");

			System.exit(1);
		}

		// Check required build number

		if (_log.isDebugEnabled()) {
			_log.debug("Check required build number");
		}

		DBUpgrader.checkRequiredBuildNumber(ReleaseInfo.getParentBuildNumber());

		Registry registry = RegistryUtil.getRegistry();

		Map<String, Object> properties = new HashMap<>();

		properties.put("module.service.lifecycle", "database.initialized");
		properties.put("service.vendor", ReleaseInfo.getVendor());
		properties.put("service.version", ReleaseInfo.getVersion());

		final ServiceRegistration<ModuleServiceLifecycle>
			moduleServiceLifecycleServiceRegistration =
				registry.registerService(
					ModuleServiceLifecycle.class,
					new ModuleServiceLifecycle() {}, properties);

		PortalLifecycleUtil.register(
			new BasePortalLifecycle() {

				@Override
				protected void doPortalDestroy() {
					moduleServiceLifecycleServiceRegistration.unregister();
				}

				@Override
				protected void doPortalInit() {
				}

			},
			PortalLifecycle.METHOD_DESTROY);

		// Check class names

		if (_log.isDebugEnabled()) {
			_log.debug("Check class names");
		}

		ClassNameLocalServiceUtil.checkClassNames();

		// Check resource actions

		if (_log.isDebugEnabled()) {
			_log.debug("Check resource actions");
		}

		ResourceActionLocalServiceUtil.checkResourceActions();

		// Verify

		if (_log.isDebugEnabled()) {
			_log.debug("Verify database");
		}

		DBUpgrader.verify();

		// Liferay JspFactory

		JspFactorySwapper.swap();

		// Jericho

		CachedLoggerProvider.install();
	}

	private static final Log _log = LogFactoryUtil.getLog(StartupAction.class);

	private static class PortalResiliencyServiceDependencyLister
		implements ServiceDependencyListener {

		@Override
		public void dependenciesFulfilled() {
			Registry registry = RegistryUtil.getRegistry();

			MessageBus messageBus = registry.getService(MessageBus.class);

			try {
				DistributedRegistry.registerDistributed(
					ComponentConstants.COMPONENT_CONTEXT, Direction.DUPLEX,
					MatchType.POSTFIX);
				DistributedRegistry.registerDistributed(
					MimeResponse.MARKUP_HEAD_ELEMENT, Direction.DUPLEX,
					MatchType.EXACT);
				DistributedRegistry.registerDistributed(
					PortletRequest.LIFECYCLE_PHASE, Direction.DUPLEX,
					MatchType.EXACT);
				DistributedRegistry.registerDistributed(WebKeys.class);

				Intraband intraband = MPIHelperUtil.getIntraband();

				intraband.registerDatagramReceiveHandler(
					SystemDataType.MAILBOX.getValue(),
					new MailboxDatagramReceiveHandler());

				intraband.registerDatagramReceiveHandler(
					SystemDataType.MESSAGE.getValue(),
					new MessageDatagramReceiveHandler(messageBus));

				intraband.registerDatagramReceiveHandler(
					SystemDataType.PROXY.getValue(),
					new IntrabandProxyDatagramReceiveHandler());

				intraband.registerDatagramReceiveHandler(
					SystemDataType.RPC.getValue(),
					new RPCDatagramReceiveHandler());

				if (PropsValues.PORTAL_FABRIC_ENABLED) {
					FabricServerUtil.start();
				}
			}
			catch (Exception e) {
				throw new IllegalStateException(
					"Unable to initialize portal resiliency", e);
			}
		}

		@Override
		public void destroy() {
		}

	}

}