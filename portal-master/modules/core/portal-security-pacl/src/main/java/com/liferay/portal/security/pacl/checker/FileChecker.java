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

package com.liferay.portal.security.pacl.checker;

import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.deploy.DeployManagerUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.url.URLContainer;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.PathUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.pacl.PACLPolicy;
import com.liferay.portal.spring.context.PortalContextLoaderListener;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;

import java.io.File;
import java.io.FilePermission;
import java.io.IOException;

import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;

import java.security.Permission;
import java.security.Permissions;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class FileChecker extends BaseChecker {

	@Override
	public void afterPropertiesSet() {
		PACLPolicy paclPolicy = getPACLPolicy();

		URLContainer urlContainer = paclPolicy.getURLContainer();

		try {
			URL url = urlContainer.getResource(StringPool.SLASH);

			_rootDir = url.getPath();
		}
		catch (Exception e) {

			// This means the WAR is probably not exploded

		}

		if (_log.isDebugEnabled()) {
			_log.debug("Root directory " + _rootDir);
		}

		Properties properties = paclPolicy.getProperties();

		if (properties.containsKey(
				JavaConstants.JAVAX_SERVLET_CONTEXT_TEMPDIR)) {

			_workDir = paclPolicy.getProperty(
				JavaConstants.JAVAX_SERVLET_CONTEXT_TEMPDIR);

			if (_log.isDebugEnabled()) {
				_log.debug("Work directory " + _workDir);
			}
		}

		_defaultReadPathsFromArray = new String[] {
			"${/}", "${auto.deploy.installed.dir}", "${catalina.base}",
			"${com.sun.aas.instanceRoot}", "${com.sun.aas.installRoot}",
			"${file.separator}", "${java.home}", "${java.io.tmpdir}",
			"${jboss.home.dir}", "${jetty.home}", "${jonas.base}",
			"${liferay.web.portal.dir}", "${liferay.home}", "${line.separator}",
			"${path.separator}", "${plugin.servlet.context.name}",
			"${release.info.version}", "${resin.home}", "${user.dir}",
			"${user.home}", "${user.name}", "${weblogic.domain.dir}",
			"${websphere.cell}", "${websphere.profile.dir}",
			StringPool.DOUBLE_SLASH
		};

		String installedDir = StringPool.BLANK;

		try {
			if (DeployManagerUtil.getDeployManager() != null) {
				installedDir = DeployManagerUtil.getInstalledDir();
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		_defaultReadPathsToArray = new String[] {
			System.getProperty("file.separator"), installedDir,
			System.getProperty("catalina.base"),
			System.getProperty("com.sun.aas.instanceRoot"),
			System.getProperty("com.sun.aas.installRoot"),
			System.getProperty("file.separator"),
			System.getProperty("java.home"),
			System.getProperty("java.io.tmpdir"),
			System.getProperty("jboss.home.dir"),
			System.getProperty("jetty.home"), System.getProperty("jonas.base"),
			_PORTAL_DIR, PropsValues.LIFERAY_HOME,
			System.getProperty("line.separator"),
			System.getProperty("path.separator"), getContextName(),
			ReleaseInfo.getVersion(), System.getProperty("resin.home"),
			System.getProperty("user.dir"), System.getProperty("user.home"),
			System.getProperty("user.name"), System.getenv("DOMAIN_HOME"),
			System.getenv("WAS_CELL"), System.getProperty("server.root"),
			StringPool.SLASH
		};

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Default read paths replace with " +
					StringUtil.merge(_defaultReadPathsToArray));
		}

		initPermissions();
	}

	@Override
	public AuthorizationProperty generateAuthorizationProperty(
		Object... arguments) {

		if ((arguments == null) || (arguments.length != 1) ||
			!(arguments[0] instanceof Permission)) {

			return null;
		}

		Permission permission = (Permission)arguments[0];

		String actions = permission.getActions();

		String key = null;

		if (actions.equals(FILE_PERMISSION_ACTION_DELETE)) {
			key = "security-manager-files-delete";
		}
		else if (actions.equals(FILE_PERMISSION_ACTION_EXECUTE)) {
			key = "security-manager-files-execute";
		}
		else if (actions.equals(FILE_PERMISSION_ACTION_READ)) {
			key = "security-manager-files-read";
		}
		else if (actions.equals(FILE_PERMISSION_ACTION_WRITE)) {
			key = "security-manager-files-write";
		}
		else {
			return null;
		}

		AuthorizationProperty authorizationProperty =
			new AuthorizationProperty();

		authorizationProperty.setKey(key);
		authorizationProperty.setValue(permission.getName());

		return authorizationProperty;
	}

	public String getRootDir() {
		return _rootDir;
	}

	@Override
	public boolean implies(Permission permission) {
		if (_permissions.implies(permission)) {
			return true;
		}

		logSecurityException(
			_log,
			"Attempted to " + permission.getActions() + " on file " +
				permission.getName());

		return false;
	}

	protected void addCanonicalPath(Set<String> paths, String path) {
		Iterator<String> itr = paths.iterator();

		while (itr.hasNext()) {
			String curPath = itr.next();

			if (curPath.startsWith(path) &&
				(curPath.length() > path.length())) {

				itr.remove();
			}
			else if (path.startsWith(curPath)) {
				return;
			}
		}

		path = StringUtil.replace(path, CharPool.BACK_SLASH, CharPool.SLASH);

		if (path.endsWith(StringPool.SLASH)) {
			path = path + "-";
		}

		paths.add(path);
	}

	protected void addCanonicalPaths(Set<String> paths, File directory)
		throws IOException {

		addCanonicalPath(
			paths, directory.getCanonicalPath() + StringPool.SLASH);

		File[] files = directory.listFiles();

		if (ArrayUtil.isEmpty(files)) {
			return;
		}

		for (File file : files) {
			if (file.isDirectory()) {
				addCanonicalPaths(paths, file);
			}
			else {
				File canonicalFile = new File(file.getCanonicalPath());

				File parentFile = canonicalFile.getParentFile();

				addCanonicalPath(
					paths, parentFile.getPath() + StringPool.SLASH);
			}
		}
	}

	protected void addDefaultReadPaths(Set<String> paths, String selector) {
		String[] pathsArray = PropsUtil.getArray(
			PropsKeys.PORTAL_SECURITY_MANAGER_FILE_CHECKER_DEFAULT_READ_PATHS,
			new Filter(selector));

		for (String path : pathsArray) {
			path = StringUtil.replace(
				path, _defaultReadPathsFromArray, _defaultReadPathsToArray);

			paths.add(path);
		}
	}

	protected void addPermission(String path, String actions) {
		if (_log.isDebugEnabled()) {
			_log.debug("Allowing " + actions + " on " + path);
		}

		String unixPath = PathUtil.toUnixPath(path);

		Permission unixPermission = new FilePermission(unixPath, actions);

		_permissions.add(unixPermission);

		String windowsPath = PathUtil.toWindowsPath(path);

		Permission windowsPermission = new FilePermission(windowsPath, actions);

		_permissions.add(windowsPermission);
	}

	protected void getPermissions(String key, String actions) {
		String value = getProperty(key);

		if (value != null) {
			int x = value.indexOf(_ENV_PREFIX);

			while (x >= 0) {
				int y = value.indexOf(StringPool.CLOSE_CURLY_BRACE, x);

				String propertyName = value.substring(x + 6, y);

				String propertyValue = GetterUtil.getString(
					System.getenv(propertyName));

				String fullPropertyName =
					_ENV_PREFIX + propertyName + StringPool.CLOSE_CURLY_BRACE;

				if (!ArrayUtil.contains(
						_defaultReadPathsFromArray, fullPropertyName)) {

					_defaultReadPathsFromArray = ArrayUtil.append(
						_defaultReadPathsFromArray, fullPropertyName);
					_defaultReadPathsToArray = ArrayUtil.append(
						_defaultReadPathsToArray, propertyValue);
				}

				x = value.indexOf(_ENV_PREFIX, y + 1);
			}

			value = StringUtil.replace(
				value, _defaultReadPathsFromArray, _defaultReadPathsToArray);

			String[] paths = StringUtil.split(value);

			if (value.contains("${comma}")) {
				for (int i = 0; i < paths.length; i++) {
					paths[i] = StringUtil.replace(
						paths[i], "${comma}", StringPool.COMMA);
				}
			}

			for (String path : paths) {
				addPermission(path, actions);
			}
		}

		// Plugin can do anything, except execute, in its own work folder

		ServletContext servletContext = ServletContextPool.get(
			PortalContextLoaderListener.getPortalServletContextName());

		if (!actions.equals(FILE_PERMISSION_ACTION_EXECUTE) &&
			(_workDir != null)) {

			addPermission(_workDir, actions);
			addPermission(_workDir + "/-", actions);

			if (ServerDetector.isWebLogic()) {
				addPermission(_workDir + "/../-", actions);
			}

			if (servletContext != null) {
				File tempDir = (File)servletContext.getAttribute(
					JavaConstants.JAVAX_SERVLET_CONTEXT_TEMPDIR);

				String tempDirAbsolutePath = tempDir.getAbsolutePath();

				if (_log.isDebugEnabled()) {
					_log.debug("Temp directory " + tempDirAbsolutePath);
				}

				if (actions.equals(FILE_PERMISSION_ACTION_READ)) {
					addPermission(tempDirAbsolutePath, actions);
				}

				addPermission(tempDirAbsolutePath + "/-", actions);
			}
		}

		if (!actions.equals(FILE_PERMISSION_ACTION_READ)) {
			return;
		}

		Set<String> paths = new LinkedHashSet<>();

		// JDK

		// There may be JARs in the system library that are symlinked. We must
		// include their canonical paths or they will fail permission checks.

		try {
			File file = new File(System.getProperty("java.home") + "/lib");

			addCanonicalPaths(paths, file);

			ClassLoader classLoader = ClassLoader.getSystemClassLoader();

			Enumeration<URL> enumeration = classLoader.getResources(
				"META-INF/MANIFEST.MF");

			while (enumeration.hasMoreElements()) {
				URL url = enumeration.nextElement();

				URLConnection urlConnection = url.openConnection();

				if (urlConnection instanceof JarURLConnection) {
					JarURLConnection jarURLConnection =
						(JarURLConnection)url.openConnection();

					URL jarFileURL = jarURLConnection.getJarFileURL();

					String fileName = jarFileURL.getFile();

					int pos = fileName.lastIndexOf(File.separatorChar);

					if (pos != -1) {
						fileName = fileName.substring(0, pos + 1);
					}

					if (ServerDetector.isJetty()) {
						String jettyHome = System.getProperty("jetty.home");

						if (fileName.startsWith(jettyHome)) {
							continue;
						}
					}

					if (ServerDetector.isResin()) {
						String resinHome = System.getProperty("resin.home");

						if (fileName.startsWith(resinHome)) {
							continue;
						}
					}

					addCanonicalPath(paths, fileName);
				}
			}
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}

		// Shared libs

		if (Validator.isNotNull(_GLOBAL_SHARED_LIB_DIR)) {
			paths.add(_GLOBAL_SHARED_LIB_DIR + "-");
		}

		// Plugin

		if (_rootDir != null) {
			paths.add(_rootDir);
			paths.add(_rootDir + "-");
		}

		// Portal

		addDefaultReadPaths(paths, ServerDetector.getServerId());

		for (String path : paths) {
			addPermission(path, actions);
		}
	}

	protected void initPermissions() {
		getPermissions(
			"security-manager-files-delete", FILE_PERMISSION_ACTION_DELETE);
		getPermissions(
			"security-manager-files-execute", FILE_PERMISSION_ACTION_EXECUTE);
		getPermissions(
			"security-manager-files-read", FILE_PERMISSION_ACTION_READ);
		getPermissions(
			"security-manager-files-write", FILE_PERMISSION_ACTION_WRITE);
	}

	private static final String _ENV_PREFIX = "${env:";

	private static final String _GLOBAL_SHARED_LIB_DIR =
		PropsValues.LIFERAY_LIB_GLOBAL_SHARED_DIR;

	private static final String _PORTAL_DIR =
		PropsValues.LIFERAY_WEB_PORTAL_DIR;

	private static final Log _log = LogFactoryUtil.getLog(FileChecker.class);

	private String[] _defaultReadPathsFromArray;
	private String[] _defaultReadPathsToArray;
	private final Permissions _permissions = new Permissions();
	private String _rootDir;
	private String _workDir;

}