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

package com.liferay.dynamic.data.mapping.util.impl;

import com.liferay.dynamic.data.mapping.constants.DDMWebKeys;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureService;
import com.liferay.dynamic.data.mapping.util.DDMTemplateHelper;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.template.StringTemplateResource;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateManagerUtil;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.template.TemplateVariableDefinition;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.template.TemplateContextHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Juan Fernández
 * @author Jorge Ferrer
 */
@Component(immediate = true)
public class DDMTemplateHelperImpl implements DDMTemplateHelper {

	@Override
	public DDMStructure fetchStructure(DDMTemplate template) {
		try {
			long classNameId = PortalUtil.getClassNameId(DDMStructure.class);

			if (template.getClassNameId() == classNameId) {
				return _ddmStructureLocalService.fetchDDMStructure(
					template.getClassPK());
			}
		}
		catch (Exception e) {
		}

		return null;
	}

	@Override
	public String getAutocompleteJSON(
			HttpServletRequest request, String language)
		throws Exception {

		JSONObject jsonObject = _jsonFactory.createJSONObject();

		JSONObject typesJSONObject = _jsonFactory.createJSONObject();
		JSONObject variablesJSONObject = _jsonFactory.createJSONObject();

		for (TemplateVariableDefinition templateVariableDefinition :
				getAutocompleteTemplateVariableDefinitions(request, language)) {

			Class<?> clazz = templateVariableDefinition.getClazz();

			if (clazz == null) {
				variablesJSONObject.put(
					templateVariableDefinition.getName(), StringPool.BLANK);
			}
			else {
				if (!typesJSONObject.has(clazz.getName())) {
					typesJSONObject.put(
						clazz.getName(), getAutocompleteClassJSONObject(clazz));
				}

				variablesJSONObject.put(
					templateVariableDefinition.getName(),
					getAutocompleteVariableJSONObject(clazz));
			}
		}

		jsonObject.put("types", typesJSONObject);
		jsonObject.put("variables", variablesJSONObject);

		return jsonObject.toString();
	}

	@Override
	public boolean isAutocompleteEnabled(String language) {
		if (language.equals(TemplateConstants.LANG_TYPE_FTL) ||
			language.equals(TemplateConstants.LANG_TYPE_VM)) {

			return true;
		}

		return false;
	}

	protected JSONObject getAutocompleteClassJSONObject(Class<?> clazz) {
		JSONObject typeJSONObject = _jsonFactory.createJSONObject();

		for (Field field : clazz.getFields()) {
			JSONObject fieldJSONObject = getAutocompleteVariableJSONObject(
				field.getType());

			typeJSONObject.put(field.getName(), fieldJSONObject);
		}

		for (Method method : clazz.getMethods()) {
			JSONObject methodJSONObject = _jsonFactory.createJSONObject();

			JSONArray parametersTypesArray = _jsonFactory.createJSONArray();

			Class<?>[] parameterTypes = method.getParameterTypes();

			for (Class<?> parameterType : parameterTypes) {
				parametersTypesArray.put(parameterType.getCanonicalName());
			}

			methodJSONObject.put("argumentTypes", parametersTypesArray);

			Class<?> returnTypeClass = method.getReturnType();

			methodJSONObject.put("returnType", returnTypeClass.getName());

			methodJSONObject.put("type", "Method");

			typeJSONObject.put(method.getName(), methodJSONObject);
		}

		return typeJSONObject;
	}

	protected List<TemplateVariableDefinition>
			getAutocompleteTemplateVariableDefinitions(
				HttpServletRequest request, String language)
		throws Exception {

		if (!isAutocompleteEnabled(language)) {
			return Collections.emptyList();
		}

		Set<TemplateVariableDefinition> templateVariableDefinitions =
			new LinkedHashSet<>();

		// Declared variables

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		DDMTemplate ddmTemplate = (DDMTemplate)request.getAttribute(
			DDMWebKeys.DYNAMIC_DATA_MAPPING_TEMPLATE);

		long classPK = BeanParamUtil.getLong(ddmTemplate, request, "classPK");
		long classNameId = BeanParamUtil.getLong(
			ddmTemplate, request, "classNameId");

		if (classPK > 0) {
			DDMStructure ddmStructure = _ddmStructureService.getStructure(
				classPK);

			classNameId = ddmStructure.getClassNameId();
		}
		else if (ddmTemplate != null) {
			classNameId = ddmTemplate.getClassNameId();
		}

		Map<String, TemplateVariableGroup> templateVariableGroups =
			TemplateContextHelper.getTemplateVariableGroups(
				classNameId, classPK, language, themeDisplay.getLocale());

		for (TemplateVariableGroup templateVariableGroup :
				templateVariableGroups.values()) {

			if (!templateVariableGroup.isAutocompleteEnabled()) {
				continue;
			}

			templateVariableDefinitions.addAll(
				templateVariableGroup.getTemplateVariableDefinitions());
		}

		// Other variables

		TemplateResource templateResource = new StringTemplateResource(
			_TEMPLATE_ID, _TEMPLATE_CONTENT);

		Template template = TemplateManagerUtil.getTemplate(
			language, templateResource, false);

		for (String key : template.getKeys()) {
			Object value = template.get(key);

			if (value == null) {
				continue;
			}

			TemplateVariableDefinition variableDefinition =
				new TemplateVariableDefinition(
					key, value.getClass(), key, (String)null);

			templateVariableDefinitions.add(variableDefinition);
		}

		return new ArrayList<>(templateVariableDefinitions);
	}

	protected JSONObject getAutocompleteVariableJSONObject(Class<?> clazz) {
		JSONObject jsonObject = _jsonFactory.createJSONObject();

		jsonObject.put("type", clazz.getName());

		return jsonObject;
	}

	@Reference(unbind = "-")
	protected void setDDMStructureLocalService(
		DDMStructureLocalService ddmStructureLocalService) {

		_ddmStructureLocalService = ddmStructureLocalService;
	}

	@Reference(unbind = "-")
	protected void setDDMStructureService(
		DDMStructureService ddmStructureService) {

		_ddmStructureService = ddmStructureService;
	}

	@Reference(unbind = "-")
	protected void setJSONFactory(JSONFactory jsonFactory) {
		_jsonFactory = jsonFactory;
	}

	private static final String _TEMPLATE_CONTENT = "# Placeholder";

	private static final String _TEMPLATE_ID = "0";

	private DDMStructureLocalService _ddmStructureLocalService;
	private DDMStructureService _ddmStructureService;
	private JSONFactory _jsonFactory;

}