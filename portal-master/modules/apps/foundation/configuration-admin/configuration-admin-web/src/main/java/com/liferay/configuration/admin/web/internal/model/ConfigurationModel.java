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

package com.liferay.configuration.admin.web.internal.model;

import static com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition.XML_NAMESPACE;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition.Scope;
import com.liferay.portal.configuration.metatype.definitions.ExtendedAttributeDefinition;
import com.liferay.portal.configuration.metatype.definitions.ExtendedObjectClassDefinition;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.osgi.service.cm.Configuration;
import org.osgi.service.metatype.ObjectClassDefinition;

/**
 * @author Raymond Augé
 */
public class ConfigurationModel implements ExtendedObjectClassDefinition {

	public static final String PROPERTY_KEY_COMPANY_ID = "companyId";

	public static final String PROPERTY_VALUE_COMPANY_ID_DEFAULT = "0";

	public ConfigurationModel(
		ExtendedObjectClassDefinition extendedObjectClassDefinition,
		Configuration configuration, String bundleSymbolicName,
		String bundleLocation, boolean factory) {

		_extendedObjectClassDefinition = extendedObjectClassDefinition;
		_configuration = configuration;
		_bundleSymbolicName = bundleSymbolicName;
		_bundleLocation = bundleLocation;
		_factory = factory;
	}

	@Override
	public ExtendedAttributeDefinition[] getAttributeDefinitions(int filter) {
		ExtendedAttributeDefinition[] extendedAttributeDefinitions =
			_extendedObjectClassDefinition.getAttributeDefinitions(filter);

		return removeFactoryInstanceLabelAttribute(
			extendedAttributeDefinitions);
	}

	public String getBundleLocation() {
		return _bundleLocation;
	}

	public String getBundleSymbolicName() {
		return _bundleSymbolicName;
	}

	public String getCategory() {
		Map<String, String> extensionAttributes =
			_extendedObjectClassDefinition.getExtensionAttributes(
				XML_NAMESPACE);

		return GetterUtil.get(extensionAttributes.get("category"), "other");
	}

	public Configuration getConfiguration() {
		return _configuration;
	}

	@Override
	public String getDescription() {
		return _extendedObjectClassDefinition.getDescription();
	}

	public ExtendedAttributeDefinition getExtendedAttributeDefinition(
		String id) {

		ExtendedAttributeDefinition[] extendedAttributeDefinitions =
			_extendedObjectClassDefinition.getAttributeDefinitions(
				ObjectClassDefinition.ALL);

		for (ExtendedAttributeDefinition extendedAttributeDefinition :
				extendedAttributeDefinitions) {

			if (id.equals(extendedAttributeDefinition.getID())) {
				return extendedAttributeDefinition;
			}
		}

		return null;
	}

	public ExtendedObjectClassDefinition getExtendedObjectClassDefinition() {
		return _extendedObjectClassDefinition;
	}

	@Override
	public Map<String, String> getExtensionAttributes(String uri) {
		return _extendedObjectClassDefinition.getExtensionAttributes(uri);
	}

	@Override
	public Set<String> getExtensionUris() {
		return _extendedObjectClassDefinition.getExtensionUris();
	}

	public String getFactoryPid() {
		return _extendedObjectClassDefinition.getID();
	}

	public Map<String, String> getHintAttributes() {
		return _extendedObjectClassDefinition.getExtensionAttributes(
			"http://www.liferay.com/xsd/meta-type-hints_7_0_0");
	}

	@Override
	public InputStream getIcon(int size) throws IOException {
		return _extendedObjectClassDefinition.getIcon(size);
	}

	@Override
	public String getID() {
		if (_configuration != null) {
			return _configuration.getPid();
		}

		return _extendedObjectClassDefinition.getID();
	}

	public String getLabel() {
		String value = getLabelAttributeValue();

		if (value == null) {
			return getName();
		}

		return value;
	}

	public String getLabelAttribute() {
		Map<String, String> extensionAttributes =
			_extendedObjectClassDefinition.getExtensionAttributes(
				XML_NAMESPACE);

		return GetterUtil.get(
			extensionAttributes.get("factoryInstanceLabelAttribute"),
			StringPool.BLANK);
	}

	@Override
	public String getName() {
		return _extendedObjectClassDefinition.getName();
	}

	public String getScope() {
		Map<String, String> extensionAttributes =
			_extendedObjectClassDefinition.getExtensionAttributes(
				XML_NAMESPACE);

		return extensionAttributes.get("scope");
	}

	public boolean hasConfiguration() {
		if (getConfiguration() == null) {
			return false;
		}

		return true;
	}

	public boolean isCompanyFactory() {
		if (!isFactory()) {
			return false;
		}

		if (Objects.equals(getScope(), Scope.COMPANY.toString()) &&
			Objects.equals(getLabelAttribute(), PROPERTY_KEY_COMPANY_ID)) {

			return true;
		}

		return false;
	}

	public boolean isFactory() {
		return _factory;
	}

	protected String getLabelAttributeValue() {
		String factoryInstanceLabelAttribute = getLabelAttribute();

		String value = null;

		if (Validator.isNotNull(factoryInstanceLabelAttribute)) {
			Dictionary<String, Object> properties =
				_configuration.getProperties();

			Object valueObj = properties.get(factoryInstanceLabelAttribute);

			if (valueObj instanceof Object[]) {
				value = StringUtil.merge(
					(Object[])valueObj, StringPool.COMMA_AND_SPACE);
			}
			else {
				value = String.valueOf(valueObj);
			}
		}

		return value;
	}

	protected ExtendedAttributeDefinition[] removeFactoryInstanceLabelAttribute(
		ExtendedAttributeDefinition[] extendedAttributeDefinitions) {

		if (!isCompanyFactory()) {
			return extendedAttributeDefinitions;
		}

		List<ExtendedAttributeDefinition>
			filteredExtendedAttributeDefinitionsList = new ArrayList<>();

		for (ExtendedAttributeDefinition extendedAttributeDefinition :
				extendedAttributeDefinitions) {

			String attributeId = extendedAttributeDefinition.getID();

			if (!attributeId.equals(getLabelAttribute())) {
				filteredExtendedAttributeDefinitionsList.add(
					extendedAttributeDefinition);
			}
		}

		return filteredExtendedAttributeDefinitionsList.toArray(
			new ExtendedAttributeDefinition[
				filteredExtendedAttributeDefinitionsList.size()]);
	}

	private final String _bundleLocation;
	private final String _bundleSymbolicName;
	private final Configuration _configuration;
	private final ExtendedObjectClassDefinition _extendedObjectClassDefinition;
	private final boolean _factory;

}