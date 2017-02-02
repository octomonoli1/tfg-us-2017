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

package com.liferay.dynamic.data.lists.web.internal.portlet.action;

import com.liferay.dynamic.data.lists.constants.DDLPortletKeys;
import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordConstants;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordService;
import com.liferay.dynamic.data.lists.service.DDLRecordSetService;
import com.liferay.dynamic.data.mapping.io.DDMFormJSONDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesJSONDeserializer;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateService;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + DDLPortletKeys.DYNAMIC_DATA_LISTS,
		"javax.portlet.name=" + DDLPortletKeys.DYNAMIC_DATA_LISTS_DISPLAY,
		"mvc.command.name=addRecord"
	},
	service = MVCActionCommand.class
)
public class AddRecordMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long groupId = ParamUtil.getLong(actionRequest, "groupId");
		long recordSetId = ParamUtil.getLong(actionRequest, "recordSetId");
		DDMFormValues ddmFormValues = getDDMFormValues(actionRequest);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DDLRecord.class.getName(), actionRequest);

		ddlRecordService.addRecord(
			groupId, recordSetId, DDLRecordConstants.DISPLAY_INDEX_DEFAULT,
			ddmFormValues, serviceContext);
	}

	protected DDMForm getDDMForm(ActionRequest actionRequest)
		throws PortalException {

		long recordSetId = ParamUtil.getLong(actionRequest, "recordSetId");
		long formDDMTemplateId = ParamUtil.getLong(
			actionRequest, "formDDMTemplateId");

		if (formDDMTemplateId > 0) {
			return getDDMFormTemplate(formDDMTemplateId);
		}

		DDLRecordSet recordSet = ddlRecordSetService.getRecordSet(recordSetId);

		DDMStructure ddmStructure = recordSet.getDDMStructure();

		return ddmStructure.getFullHierarchyDDMForm();
	}

	protected DDMForm getDDMFormTemplate(long formDDMTemplateId)
		throws PortalException {

		DDMTemplate ddmTemplate = ddmTemplateService.getTemplate(
			formDDMTemplateId);

		return ddmFormJSONDeserializer.deserialize(ddmTemplate.getScript());
	}

	protected DDMFormValues getDDMFormValues(ActionRequest actionRequest)
		throws PortalException {

		DDMForm ddmForm = getDDMForm(actionRequest);

		String serializedDDMFormValues = ParamUtil.getString(
			actionRequest, "ddmFormValues");

		return ddmFormValuesJSONDeserializer.deserialize(
			ddmForm, serializedDDMFormValues);
	}

	@Reference(unbind = "-")
	protected void setDDLRecordService(DDLRecordService ddlRecordService) {
		this.ddlRecordService = ddlRecordService;
	}

	@Reference(unbind = "-")
	protected void setDDLRecordSetService(
		DDLRecordSetService ddlRecordSetService) {

		this.ddlRecordSetService = ddlRecordSetService;
	}

	@Reference(unbind = "-")
	protected void setDDMFormJSONDeserializer(
		DDMFormJSONDeserializer ddmFormJSONDeserializer) {

		this.ddmFormJSONDeserializer = ddmFormJSONDeserializer;
	}

	@Reference(unbind = "-")
	protected void setDDMFormValuesJSONDeserializer(
		DDMFormValuesJSONDeserializer ddmFormValuesJSONDeserializer) {

		this.ddmFormValuesJSONDeserializer = ddmFormValuesJSONDeserializer;
	}

	@Reference(unbind = "-")
	protected void setDDMTemplateService(
		DDMTemplateService ddmTemplateService) {

		this.ddmTemplateService = ddmTemplateService;
	}

	protected DDLRecordService ddlRecordService;
	protected DDLRecordSetService ddlRecordSetService;
	protected DDMFormJSONDeserializer ddmFormJSONDeserializer;
	protected DDMFormValuesJSONDeserializer ddmFormValuesJSONDeserializer;
	protected DDMTemplateService ddmTemplateService;

}