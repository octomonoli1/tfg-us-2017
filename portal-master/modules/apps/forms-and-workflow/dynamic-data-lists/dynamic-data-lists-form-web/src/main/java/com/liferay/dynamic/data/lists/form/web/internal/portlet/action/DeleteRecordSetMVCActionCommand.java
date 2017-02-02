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

package com.liferay.dynamic.data.lists.form.web.internal.portlet.action;

import com.liferay.dynamic.data.lists.form.web.internal.constants.DDLFormPortletKeys;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetService;
import com.liferay.dynamic.data.mapping.service.DDMStructureService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseTransactionalMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bruno Basto
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + DDLFormPortletKeys.DYNAMIC_DATA_LISTS_FORM_ADMIN,
		"mvc.command.name=deleteRecordSet"
	},
	service = MVCActionCommand.class
)
public class DeleteRecordSetMVCActionCommand
	extends BaseTransactionalMVCActionCommand {

	protected void doDeleteRecordSet(long recordSetId) throws PortalException {
		DDLRecordSet recordSet = _ddlRecordSetService.getRecordSet(recordSetId);

		_ddlRecordSetService.deleteRecordSet(recordSetId);

		_ddmStructureService.deleteStructure(recordSet.getDDMStructureId());
	}

	@Override
	protected void doTransactionalCommand(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] deleteRecordSetIds = null;

		long recordSetId = ParamUtil.getLong(actionRequest, "recordSetId");

		if (recordSetId > 0) {
			deleteRecordSetIds = new long[] {recordSetId};
		}
		else {
			deleteRecordSetIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "deleteRecordSetIds"), 0L);
		}

		for (long deleteRecordSetId : deleteRecordSetIds) {
			doDeleteRecordSet(deleteRecordSetId);
		}
	}

	@Reference(unbind = "-")
	protected void setDDLRecordSetService(
		DDLRecordSetService ddlRecordSetService) {

		_ddlRecordSetService = ddlRecordSetService;
	}

	@Reference(unbind = "-")
	protected void setDDMStructureService(
		DDMStructureService ddmStructureService) {

		_ddmStructureService = ddmStructureService;
	}

	private DDLRecordSetService _ddlRecordSetService;
	private DDMStructureService _ddmStructureService;

}