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

package com.liferay.portal.tools.wsdd.builder;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.tools.ArgumentsUtil;
import com.liferay.portal.tools.ToolsUtil;
import com.liferay.portal.xml.SAXReaderFactory;
import com.liferay.util.ant.Java2WsddTask;
import com.liferay.util.xml.XMLSafeReader;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author Brian Wing Shun Chan
 */
public class WSDDBuilder {

	public static void main(String[] args) throws Exception {
		Map<String, String> arguments = ArgumentsUtil.parseArguments(args);

		try {
			WSDDBuilder wsddBuilder = new WSDDBuilder();

			wsddBuilder._classPath = arguments.get("wsdd.class.path");
			wsddBuilder._fileName = GetterUtil.getString(
				arguments.get("wsdd.input.file"), WSDDBuilderArgs.FILE_NAME);
			wsddBuilder._outputPath = GetterUtil.getString(
				arguments.get("wsdd.output.path"), WSDDBuilderArgs.OUTPUT_PATH);
			wsddBuilder._serverConfigFileName = GetterUtil.getString(
				arguments.get("wsdd.server.config.file"),
				WSDDBuilderArgs.SERVER_CONFIG_FILE_NAME);
			wsddBuilder._serviceNamespace = GetterUtil.getString(
				arguments.get("wsdd.service.namespace"),
				WSDDBuilderArgs.SERVICE_NAMESPACE);

			wsddBuilder.build();
		}
		catch (Exception e) {
			ArgumentsUtil.processMainException(arguments, e);
		}
	}

	public void build() throws Exception {
		File serverConfigFile = new File(_serverConfigFileName);

		if (!serverConfigFile.exists()) {
			Class<?> clazz = getClass();

			ClassLoader classLoader = clazz.getClassLoader();

			String serverConfigContent = StringUtil.read(
				classLoader,
				"com/liferay/portal/tools/wsdd/builder/dependencies/" +
					"server-config.wsdd");

			_writeFile(serverConfigFile, serverConfigContent);
		}

		SAXReader saxReader = _getSAXReader();

		String content = ToolsUtil.getContent(_fileName);

		Document document = saxReader.read(new XMLSafeReader(content));

		Element rootElement = document.getRootElement();

		String packagePath = rootElement.attributeValue("package-path");

		Element portletElement = rootElement.element("portlet");
		Element namespaceElement = rootElement.element("namespace");

		if (portletElement != null) {
			_portletShortName = portletElement.attributeValue("short-name");
		}
		else {
			_portletShortName = namespaceElement.getText();
		}

		_outputPath +=
			StringUtil.replace(packagePath, '.', '/') + "/service/http";

		_packagePath = packagePath;

		List<Element> entityElements = rootElement.elements("entity");

		for (Element entityElement : entityElements) {
			String entityName = entityElement.attributeValue("name");

			boolean remoteService = GetterUtil.getBoolean(
				entityElement.attributeValue("remote-service"), true);

			if (remoteService) {
				_createServiceWSDD(entityName);

				WSDDMerger.merge(
					_outputPath + "/" + entityName + "Service_deploy.wsdd",
					_serverConfigFileName);
			}
		}
	}

	public void setClassPath(String classPath) {
		_classPath = classPath;
	}

	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	public void setOutputPath(String outputPath) {
		_outputPath = outputPath;
	}

	public void setServerConfigFileName(String serverConfigFileName) {
		_serverConfigFileName = serverConfigFileName;
	}

	public void setServiceNamespace(String serviceNamespace) {
		_serviceNamespace = serviceNamespace;
	}

	private void _createServiceWSDD(String entityName) throws Exception {
		String className =
			_packagePath + ".service.http." + entityName + "ServiceSoap";

		String serviceName = StringUtil.replace(_portletShortName, ' ', '_');

		if (!_portletShortName.equals("Portal")) {
			serviceName = _serviceNamespace + "_" + serviceName;
		}

		serviceName += ("_" + entityName + "Service");

		String[] wsdds = Java2WsddTask.generateWsdd(
			className, _classPath, serviceName);

		_writeFile(
			new File(_outputPath + "/" + entityName + "Service_deploy.wsdd"),
			wsdds[0]);

		_writeFile(
			new File(_outputPath + "/" + entityName + "Service_undeploy.wsdd"),
			wsdds[1]);
	}

	private SAXReader _getSAXReader() {
		return SAXReaderFactory.getSAXReader(null, false, false);
	}

	private void _writeFile(File file, String content) throws Exception {
		Path path = file.toPath();

		Files.createDirectories(path.getParent());

		String oldContent = null;

		if (file.exists()) {
			oldContent = new String(Files.readAllBytes(path));
		}

		if (!content.equals(oldContent)) {
			Files.write(path, content.getBytes(StringPool.UTF8));
		}
	}

	private String _classPath;
	private String _fileName;
	private String _outputPath;
	private String _packagePath;
	private String _portletShortName;
	private String _serverConfigFileName;
	private String _serviceNamespace;

}