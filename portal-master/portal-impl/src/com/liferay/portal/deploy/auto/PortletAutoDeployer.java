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

package com.liferay.portal.deploy.auto;

import com.liferay.portal.deploy.DeployUtil;
import com.liferay.portal.kernel.deploy.auto.AutoDeployer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.tools.deploy.PortletDeployer;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsValues;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivica Cardic
 * @author Brian Wing Shun Chan
 */
public class PortletAutoDeployer
	extends PortletDeployer implements AutoDeployer {

	public PortletAutoDeployer() {
		try {
			baseDir = PrefsPropsUtil.getString(
				PropsKeys.AUTO_DEPLOY_DEPLOY_DIR,
				PropsValues.AUTO_DEPLOY_DEPLOY_DIR);
			destDir = DeployUtil.getAutoDeployDestDir();
			appServerType = ServerDetector.getServerId();
			auiTaglibDTD = DeployUtil.getResourcePath("liferay-aui.tld");
			portletTaglibDTD = DeployUtil.getResourcePath(
				"liferay-portlet.tld");
			portletExtTaglibDTD = DeployUtil.getResourcePath(
				"liferay-portlet-ext.tld");
			securityTaglibDTD = DeployUtil.getResourcePath(
				"liferay-security.tld");
			themeTaglibDTD = DeployUtil.getResourcePath("liferay-theme.tld");
			uiTaglibDTD = DeployUtil.getResourcePath("liferay-ui.tld");
			utilTaglibDTD = DeployUtil.getResourcePath("liferay-util.tld");
			unpackWar = PrefsPropsUtil.getBoolean(
				PropsKeys.AUTO_DEPLOY_UNPACK_WAR,
				PropsValues.AUTO_DEPLOY_UNPACK_WAR);
			filePattern = StringPool.BLANK;
			jbossPrefix = PrefsPropsUtil.getString(
				PropsKeys.AUTO_DEPLOY_JBOSS_PREFIX,
				PropsValues.AUTO_DEPLOY_JBOSS_PREFIX);
			tomcatLibDir = PrefsPropsUtil.getString(
				PropsKeys.AUTO_DEPLOY_TOMCAT_LIB_DIR,
				PropsValues.AUTO_DEPLOY_TOMCAT_LIB_DIR);
			wildflyPrefix = PrefsPropsUtil.getString(
				PropsKeys.AUTO_DEPLOY_WILDFLY_PREFIX,
				PropsValues.AUTO_DEPLOY_WILDFLY_PREFIX);

			List<String> jars = new ArrayList<>();

			addExtJar(jars, "ext-util-bridges.jar");
			addExtJar(jars, "ext-util-java.jar");
			addExtJar(jars, "ext-util-taglib.jar");
			addRequiredJar(jars, "util-bridges.jar");
			addRequiredJar(jars, "util-java.jar");
			addRequiredJar(jars, "util-taglib.jar");

			this.jars = jars;

			checkArguments();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortletAutoDeployer.class);

}