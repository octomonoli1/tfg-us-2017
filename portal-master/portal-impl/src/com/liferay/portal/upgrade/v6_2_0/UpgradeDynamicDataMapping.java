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

package com.liferay.portal.upgrade.v6_2_0;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.metadata.RawMetadataProcessor;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.upgrade.v6_2_0.util.DDMTemplateTable;
import com.liferay.util.xml.XMLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

/**
 * @author Juan Fernández
 * @author Marcellus Tavares
 */
public class UpgradeDynamicDataMapping extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateSchema();

		updateStructures();
		updateStructuresClassNameId();

		updateTemplates();
	}

	protected void updateMetadataElement(
		Element metadataElement, String[] relocatedMetadadaEntryNames,
		String[] removedMetadataEntryNames) {

		Element parentElement = metadataElement.getParent();

		List<Element> entryElements = metadataElement.elements("entry");

		for (Element entryElement : entryElements) {
			String name = entryElement.attributeValue("name");

			if (ArrayUtil.contains(removedMetadataEntryNames, name)) {
				metadataElement.remove(entryElement);
			}
			else if (ArrayUtil.contains(relocatedMetadadaEntryNames, name)) {
				parentElement.addAttribute(name, entryElement.getText());

				metadataElement.remove(entryElement);
			}
		}
	}

	protected void updateSchema() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			alter(
				DDMTemplateTable.class,
				new AlterTableAddColumn("classNameId LONG"),
				new AlterTableAddColumn("templateKey STRING"),
				new AlterColumnName("structureId", "classPK LONG"));

			long classNameId = PortalUtil.getClassNameId(
				"com.liferay.portlet.dynamicdatamapping.model.DDMStructure");

			try {
				runSQL("update DDMTemplate set classNameId = " + classNameId);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e, e);
				}
			}
		}
	}

	protected void updateStructure(
			long structureId, String structureKey, String xsd)
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				"update DDMStructure set structureKey = ?, xsd = ? where " +
					"structureId = ?")) {

			ps.setString(1, structureKey);
			ps.setString(2, xsd);
			ps.setLong(3, structureId);

			ps.executeUpdate();
		}
		catch (SQLException sqle) {
			if (_log.isWarnEnabled()) {
				_log.warn(sqle, sqle);
			}
		}
	}

	protected void updateStructures() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps = connection.prepareStatement(
				"select structureId, structureKey, xsd from DDMStructure");
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long structureId = rs.getLong("structureId");
				String structureKey = rs.getString("structureKey");
				String xsd = rs.getString("xsd");

				if (Validator.isNull(structureKey)) {
					structureKey = String.valueOf(System.currentTimeMillis());
				}
				else {
					structureKey = StringUtil.toUpperCase(structureKey.trim());
				}

				updateStructure(
					structureId, structureKey, updateXSD(xsd, structureKey));
			}
		}
	}

	protected void updateStructuresClassNameId() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps = connection.prepareStatement(
				"update DDMStructure set classNameId = ? where " +
					"classNameId = ?")) {

			ps.setLong(
				1,
				PortalUtil.getClassNameId(
					"com.liferay.portlet.documentlibrary.model." +
						"DLFileEntryMetadata"));
			ps.setLong(
				2,
				PortalUtil.getClassNameId(
					"com.liferay.portlet.documentlibrary.model.DLFileEntry"));

			ps.executeUpdate();
		}
		catch (SQLException sqle) {
			if (_log.isWarnEnabled()) {
				_log.warn(sqle, sqle);
			}
		}
	}

	protected void updateTemplate(
			long templateId, String templateKey, String script)
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				"update DDMTemplate set templateKey = ?, script = ? where " +
					"templateId = ?")) {

			ps.setString(1, templateKey);
			ps.setString(2, script);
			ps.setLong(3, templateId);

			ps.executeUpdate();
		}
	}

	protected void updateTemplates() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps = connection.prepareStatement(
				"select templateId, templateKey, script from DDMTemplate " +
					"where language = 'xsd'");
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long templateId = rs.getLong("templateId");
				String templateKey = rs.getString("templateKey");
				String script = rs.getString("script");

				if (Validator.isNull(templateKey)) {
					templateKey = String.valueOf(System.currentTimeMillis());
				}
				else {
					templateKey = StringUtil.toUpperCase(templateKey.trim());
				}

				updateTemplate(
					templateId, templateKey,
					updateXSD(script, StringPool.BLANK));
			}
		}
	}

	protected String updateXSD(String xsd, String structureKey)
		throws Exception {

		Document document = SAXReaderUtil.read(xsd);

		Element rootElement = document.getRootElement();

		List<Element> dynamicElementElements = rootElement.elements(
			"dynamic-element");

		for (Element dynamicElementElement : dynamicElementElements) {
			updateXSDDynamicElement(dynamicElementElement, structureKey);
		}

		return XMLUtil.formatXML(document);
	}

	protected void updateXSDDynamicElement(
		Element element, String structureKey) {

		Element metadataElement = element.element("meta-data");

		updateMetadataElement(
			metadataElement,
			new String[] {
				"multiple", "name", "readOnly", "repeatable", "required",
				"showLabel", "type", "width"
			},
			new String[] {
				"acceptFiles", "displayChildLabelAsValue", "fieldCssClass",
				"folder"
			});

		if (StringUtil.equalsIgnoreCase(
				structureKey, RawMetadataProcessor.TIKA_RAW_METADATA)) {

			element.addAttribute("indexType", "text");
		}

		List<Element> dynamicElementElements = element.elements(
			"dynamic-element");

		for (Element dynamicElementElement : dynamicElementElements) {
			updateXSDDynamicElement(dynamicElementElement, structureKey);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeDynamicDataMapping.class);

}