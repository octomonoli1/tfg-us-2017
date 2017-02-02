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

package com.liferay.gradle.plugins.test.integration.tasks;

import com.liferay.gradle.plugins.test.integration.util.GradleUtil;
import com.liferay.gradle.plugins.test.integration.util.StringUtil;
import com.liferay.gradle.util.FileUtil;
import com.liferay.gradle.util.copy.ExcludeExistingFileAction;
import com.liferay.gradle.util.copy.StripPathSegmentsAction;

import groovy.xml.DOMBuilder;
import groovy.xml.XmlUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import org.gradle.api.Action;
import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.file.CopySpec;
import org.gradle.api.logging.Logger;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;
import org.gradle.util.VersionNumber;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Andrea Di Giorgi
 */
public class SetUpTestableTomcatTask
	extends DefaultTask
	implements JmxRemotePortSpec, ManagerSpec, ModuleFrameworkBaseDirSpec {

	public SetUpTestableTomcatTask() {
		_zipUrl = new Callable<String>() {

			@Override
			public String call() throws Exception {
				File dir = getDir();

				String dirName = dir.getName();

				int start = StringUtil.indexOfDigit(dirName);

				if (start < 0) {
					return null;
				}

				VersionNumber versionNumber = VersionNumber.parse(
					dirName.substring(start));

				if (versionNumber == VersionNumber.UNKNOWN) {
					return null;
				}

				StringBuilder sb = new StringBuilder();

				sb.append("http://archive.apache.org/dist/tomcat/tomcat-");
				sb.append(versionNumber.getMajor());
				sb.append("/v");
				sb.append(versionNumber);
				sb.append("/bin/apache-tomcat-");
				sb.append(versionNumber);
				sb.append(".zip");

				return sb.toString();
			}

		};
	}

	public SetUpTestableTomcatTask catalinaOptsReplacement(
		String oldSub, Object newSub) {

		_catalinaOptsReplacements.put(oldSub, newSub);

		return this;
	}

	public SetUpTestableTomcatTask catalinaOptsReplacements(
		Map<String, ?> catalinaOptsReplacements) {

		_catalinaOptsReplacements.putAll(catalinaOptsReplacements);

		return this;
	}

	public File getBinDir() {
		return new File(getDir(), "bin");
	}

	@Input
	public Map<String, Object> getCatalinaOptsReplacements() {
		return _catalinaOptsReplacements;
	}

	@Input
	public File getDir() {
		return GradleUtil.toFile(getProject(), _dir);
	}

	@Input
	@Override
	public int getJmxRemotePort() {
		return GradleUtil.toInteger(_jmxRemotePort);
	}

	@Input
	@Override
	public String getManagerPassword() {
		return GradleUtil.toString(_managerPassword);
	}

	@Input
	@Override
	public String getManagerUserName() {
		return GradleUtil.toString(_managerUserName);
	}

	@Input
	@Override
	public File getModuleFrameworkBaseDir() {
		return GradleUtil.toFile(getProject(), _moduleFrameworkBaseDir);
	}

	@Input
	public String getZipUrl() {
		return GradleUtil.toString(_zipUrl);
	}

	@Input
	public boolean isDebugLogging() {
		return _debugLogging;
	}

	@Input
	public boolean isJmxRemoteAuthenticate() {
		return _jmxRemoteAuthenticate;
	}

	@Input
	public boolean isJmxRemoteSsl() {
		return _jmxRemoteSsl;
	}

	@Input
	public boolean isOverwriteTestModules() {
		return _overwriteTestModules;
	}

	public void setCatalinaOptsReplacements(
		Map<String, ?> catalinaOptsReplacements) {

		_catalinaOptsReplacements.clear();

		catalinaOptsReplacements(catalinaOptsReplacements);
	}

	public void setDebugLogging(boolean debugLogging) {
		_debugLogging = debugLogging;
	}

	public void setDir(Object dir) {
		_dir = dir;
	}

	public void setJmxRemoteAuthenticate(boolean jmxRemoteAuthenticate) {
		_jmxRemoteAuthenticate = jmxRemoteAuthenticate;
	}

	@Override
	public void setJmxRemotePort(Object jmxRemotePort) {
		_jmxRemotePort = jmxRemotePort;
	}

	public void setJmxRemoteSsl(boolean jmxRemoteSsl) {
		_jmxRemoteSsl = jmxRemoteSsl;
	}

	@Override
	public void setManagerPassword(Object managerPassword) {
		_managerPassword = managerPassword;
	}

	@Override
	public void setManagerUserName(Object managerUserName) {
		_managerUserName = managerUserName;
	}

	@Override
	public void setModuleFrameworkBaseDir(Object moduleFrameworkBaseDir) {
		_moduleFrameworkBaseDir = moduleFrameworkBaseDir;
	}

	public void setOverwriteTestModules(boolean overwriteTestModules) {
		_overwriteTestModules = overwriteTestModules;
	}

	@TaskAction
	public void setUpTestableTomcat() throws Exception {
		setUpCatalinaOpts();
		setUpJmx();
		setUpLogging();
		setUpManager();
		setUpOsgiModules();
	}

	public void setZipUrl(Object zipUrl) {
		_zipUrl = zipUrl;
	}

	protected boolean contains(String fileName, String s) throws IOException {
		File file = new File(getDir(), fileName);

		String fileContent = new String(Files.readAllBytes(file.toPath()));

		if (fileContent.contains(s)) {
			return true;
		}

		return false;
	}

	protected PrintWriter getAppendPrintWriter(String fileName)
		throws IOException {

		File file = new File(getDir(), fileName);

		return new PrintWriter(
			Files.newBufferedWriter(
				file.toPath(), StandardCharsets.UTF_8,
				StandardOpenOption.APPEND, StandardOpenOption.WRITE));
	}

	protected String getJmxOptions() {
		StringBuilder sb = new StringBuilder();

		sb.append("-Dcom.sun.management.jmxremote ");
		sb.append("-Dcom.sun.management.jmxremote.authenticate=");
		sb.append(isJmxRemoteAuthenticate());
		sb.append(" -Dcom.sun.management.jmxremote.port=");
		sb.append(getJmxRemotePort());
		sb.append(" -Dcom.sun.management.jmxremote.ssl=");
		sb.append(isJmxRemoteSsl());

		return sb.toString();
	}

	protected void replace(String fileName, Map<String, Object> replacements)
		throws IOException {

		Logger logger = getLogger();

		File dir = getDir();

		Path dirPath = dir.toPath();

		Path path = dirPath.resolve(fileName);

		String content = new String(Files.readAllBytes(path));

		for (Map.Entry<String, Object> entry : replacements.entrySet()) {
			String oldSub = entry.getKey();
			String newSub = GradleUtil.toString(entry.getValue());

			if (logger.isWarnEnabled() && !content.contains(oldSub)) {
				logger.warn("Unable to find \"" + oldSub + "\" in " + path);
			}

			content = content.replace(oldSub, newSub);
		}

		Files.write(path, content.getBytes());
	}

	protected void setUpCatalinaOpts() throws IOException {
		Map<String, Object> replacements = getCatalinaOptsReplacements();

		if (replacements.isEmpty()) {
			return;
		}

		replace("bin/setenv.bat", replacements);
		replace("bin/setenv.sh", replacements);
	}

	protected void setUpJmx() throws IOException {
		String jmxOptions = getJmxOptions();

		if (!contains("bin/setenv.bat", jmxOptions)) {
			try (PrintWriter printWriter = getAppendPrintWriter(
					"bin/setenv.bat")) {

				printWriter.println();

				printWriter.print("set \"JMX_OPTS=");
				printWriter.print(jmxOptions);
				printWriter.println('\"');

				printWriter.println();

				printWriter.println(
					"set \"CATALINA_OPTS=%CATALINA_OPTS% %JMX_OPTS%\"");
			}
		}

		if (!contains("bin/setenv.sh", jmxOptions)) {
			try (PrintWriter printWriter = getAppendPrintWriter(
					"bin/setenv.sh")) {

				printWriter.println();

				printWriter.print("JMX_OPTS=\"");
				printWriter.print(jmxOptions);
				printWriter.println('\"');

				printWriter.println();

				printWriter.println(
					"CATALINA_OPTS=\"${CATALINA_OPTS} ${JMX_OPTS}\"");
			}
		}
	}

	protected void setUpLogging() throws IOException {
		if (!isDebugLogging() ||
			contains("conf/Logging.properties", "org.apache.catalina.level")) {

			return;
		}

		try (PrintWriter printWriter = getAppendPrintWriter(
				"conf/Logging.properties")) {

			printWriter.println("org.apache.catalina.level=ALL");

			printWriter.println();

			printWriter.println(
				"org.apache.catalina.loader.WebappClassLoader.level=INFO");
			printWriter.println(
				"org.apache.catalina.loader.WebappLoader.level=INFO");
			printWriter.println(
				"org.apache.catalina.startup.ClassLoaderFactory.level=INFO");
		}
	}

	protected void setUpManager() throws Exception {
		final File managerDir = new File(getDir(), "webapps/manager");

		if (!managerDir.exists()) {
			final Project project = getProject();

			final File zipFile = FileUtil.get(project, getZipUrl());

			project.copy(
				new Action<CopySpec>() {

					@Override
					public void execute(CopySpec copySpec) {
						copySpec.eachFile(new StripPathSegmentsAction(2));
						copySpec.from(project.zipTree(zipFile));
						copySpec.include(
							"apache-tomcat-*/webapps/manager/**/*");
						copySpec.into(managerDir.getParentFile());
						copySpec.setIncludeEmptyDirs(false);
					}

				});
		}

		Document document = null;

		final File tomcatUsersXmlFile = new File(
			getDir(), "conf/tomcat-users.xml");

		try (InputStreamReader inputStreamReader = new InputStreamReader(
				new FileInputStream(tomcatUsersXmlFile))) {

			document = DOMBuilder.parse(inputStreamReader);
		}

		Element tomcatUsersElement = document.getDocumentElement();

		Set<String> existentRoleNames = new HashSet<>();
		boolean tomcatManagerUserExists = false;

		NodeList nodeList = tomcatUsersElement.getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			if (node.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}

			Element element = (Element)node;

			String elementName = element.getNodeName();

			if (elementName.equals("role")) {
				String roleName = element.getAttribute("rolename");

				existentRoleNames.add(roleName);
			}
			else if (elementName.equals("user")) {
				String userName = element.getAttribute("username");

				if (userName.equals(getManagerUserName())) {
					tomcatManagerUserExists = true;
				}
			}
		}

		boolean tomcatUsersXmlFileModified = false;

		for (String roleName : _TOMCAT_USERS_ROLE_NAMES) {
			if (!existentRoleNames.contains(roleName)) {
				Element element = document.createElement("role");

				element.setAttribute("rolename", roleName);

				tomcatUsersElement.appendChild(element);

				tomcatUsersXmlFileModified = true;
			}
		}

		if (!tomcatManagerUserExists) {
			Element element = document.createElement("user");

			element.setAttribute("password", getManagerPassword());
			element.setAttribute(
				"roles",
				"tomcat,manager-gui,manager-script,manager-jmx,manager-status");
			element.setAttribute("username", getManagerUserName());

			tomcatUsersElement.appendChild(element);

			tomcatUsersXmlFileModified = true;
		}

		if (tomcatUsersXmlFileModified) {
			Path timestampTomcatUserXmlFilePath = Paths.get(
				tomcatUsersXmlFile.toString() + "." +
					_timestampDateFormat.format(new Date()));

			Files.copy(
				tomcatUsersXmlFile.toPath(), timestampTomcatUserXmlFilePath);

			try (FileOutputStream fileOutputStream = new FileOutputStream(
					tomcatUsersXmlFile)) {

				XmlUtil.serialize(tomcatUsersElement, fileOutputStream);
			}
		}
	}

	protected void setUpOsgiModules() {
		Project project = getProject();

		project.copy(
			new Action<CopySpec>() {

				@Override
				public void execute(CopySpec copySpec) {
					File moduleFrameworkBaseDir = getModuleFrameworkBaseDir();

					File modulesDir = new File(
						moduleFrameworkBaseDir, "modules");

					if (!isOverwriteTestModules()) {
						copySpec.eachFile(
							new ExcludeExistingFileAction(modulesDir));
					}

					copySpec.from(new File(moduleFrameworkBaseDir, "test"));
					copySpec.into(modulesDir);
				}

			});
	}

	private static final String[] _TOMCAT_USERS_ROLE_NAMES = {
		"manager-gui", "manager-jmx", "manager-script", "manager-status",
		"tomcat"
	};

	private final Map<String, Object> _catalinaOptsReplacements =
		new LinkedHashMap<>();
	private boolean _debugLogging;
	private Object _dir;
	private boolean _jmxRemoteAuthenticate;
	private Object _jmxRemotePort;
	private boolean _jmxRemoteSsl;
	private Object _managerPassword;
	private Object _managerUserName;
	private Object _moduleFrameworkBaseDir;
	private boolean _overwriteTestModules;
	private final DateFormat _timestampDateFormat = new SimpleDateFormat(
		"yyyyMMddkkmmssSSS");
	private Object _zipUrl;

}