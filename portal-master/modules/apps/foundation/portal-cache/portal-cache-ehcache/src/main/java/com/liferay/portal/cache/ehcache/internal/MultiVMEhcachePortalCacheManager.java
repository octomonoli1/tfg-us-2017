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

package com.liferay.portal.cache.ehcache.internal;

import com.liferay.portal.cache.PortalCacheBootstrapLoaderFactory;
import com.liferay.portal.cache.PortalCacheListenerFactory;
import com.liferay.portal.cache.PortalCacheManagerListenerFactory;
import com.liferay.portal.cache.ehcache.internal.configurator.MultiVMEhcachePortalCacheManagerConfigurator;
import com.liferay.portal.kernel.cache.PortalCacheManager;
import com.liferay.portal.kernel.cache.PortalCacheManagerNames;
import com.liferay.portal.kernel.cache.configurator.PortalCacheConfiguratorSettings;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.AggregateClassLoader;
import com.liferay.portal.kernel.util.ClassLoaderUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsKeys;

import java.io.Serializable;

import java.util.Map;

import javax.management.MBeanServer;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Tina Tian
 */
@Component(
	immediate = true,
	property = {
		PortalCacheManager.PORTAL_CACHE_MANAGER_NAME + "=" + PortalCacheManagerNames.MULTI_VM
	},
	service = PortalCacheManager.class
)
public class MultiVMEhcachePortalCacheManager
	<K extends Serializable, V extends Serializable>
		extends EhcachePortalCacheManager<K, V> {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		setClusterAware(true);
		setConfigFile(props.get(PropsKeys.EHCACHE_MULTI_VM_CONFIG_LOCATION));
		setDefaultConfigFile(_DEFAULT_CONFIG_FILE_NAME);
		setMpiOnly(true);
		setPortalCacheManagerName(PortalCacheManagerNames.MULTI_VM);

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		Class<?> clazz = getClass();

		ClassLoaderUtil.setContextClassLoader(
			AggregateClassLoader.getAggregateClassLoader(
				contextClassLoader, clazz.getClassLoader()));

		try {
			initialize();
		}
		finally {
			ClassLoaderUtil.setContextClassLoader(contextClassLoader);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Activated " + PortalCacheManagerNames.MULTI_VM);
		}
	}

	@Deactivate
	protected void deactivate() {
		destroy();
	}

	@Reference(unbind = "-")
	protected void setMBeanServer(MBeanServer mBeanServer) {
		this.mBeanServer = mBeanServer;
	}

	@Reference(unbind = "-")
	protected void setMultiVMEhcachePortalCacheManagerConfigurator(
		MultiVMEhcachePortalCacheManagerConfigurator
			multiVMEhcachePortalCacheManagerConfigurator) {

		this.baseEhcachePortalCacheManagerConfigurator =
			multiVMEhcachePortalCacheManagerConfigurator;
	}

	@Reference(unbind = "-")
	protected void setPortalCacheBootstrapLoaderFactory(
		PortalCacheBootstrapLoaderFactory portalCacheBootstrapLoaderFactory) {

		this.portalCacheBootstrapLoaderFactory =
			portalCacheBootstrapLoaderFactory;
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(" + PortalCacheManager.PORTAL_CACHE_MANAGER_NAME + "=" + PortalCacheManagerNames.MULTI_VM + ")"
	)
	protected void setPortalCacheConfiguratorSettings(
		PortalCacheConfiguratorSettings portalCacheConfiguratorSettings) {

		reconfigure(portalCacheConfiguratorSettings);
	}

	@Reference(unbind = "-")
	protected void setPortalCacheListenerFactory(
		PortalCacheListenerFactory portalCacheListenerFactory) {

		this.portalCacheListenerFactory = portalCacheListenerFactory;
	}

	@Reference(unbind = "-")
	protected void setPortalCacheManagerListenerFactory(
		PortalCacheManagerListenerFactory<PortalCacheManager<K, V>>
			portalCacheManagerListenerFactory) {

		this.portalCacheManagerListenerFactory =
			portalCacheManagerListenerFactory;
	}

	@Reference(unbind = "-")
	protected void setProps(Props props) {
		this.props = props;
	}

	protected void unsetPortalCacheConfiguratorSettings(
		PortalCacheConfiguratorSettings portalCacheConfiguratorSettings) {
	}

	private static final String _DEFAULT_CONFIG_FILE_NAME =
		"/ehcache/liferay-multi-vm-clustered.xml";

	private static final Log _log = LogFactoryUtil.getLog(
		MultiVMEhcachePortalCacheManager.class);

}