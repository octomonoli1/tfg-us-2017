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

package com.liferay.dynamic.data.lists.internal.exportimport.data.handler;

import com.liferay.dynamic.data.lists.exportimport.staged.model.repository.DDLRecordStagedModelRepository;
import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordLocalService;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService;
import com.liferay.dynamic.data.mapping.exportimport.content.processor.DDMFormValuesExportImportContentProcessor;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesJSONDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesJSONSerializer;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.StorageEngine;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.lar.BaseStagedModelDataHandler;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Element;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Daniel Kocsis
 */
@Component(immediate = true, service = StagedModelDataHandler.class)
public class DDLRecordStagedModelDataHandler
	extends BaseStagedModelDataHandler<DDLRecord> {

	public static final String[] CLASS_NAMES = {DDLRecord.class.getName()};

	@Deprecated
	@Override
	public void deleteStagedModel(DDLRecord stagedModel)
		throws PortalException {

		super.deleteStagedModel(stagedModel);
	}

	@Deprecated
	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		super.deleteStagedModel(uuid, groupId, className, extraData);
	}

	@Deprecated
	@Override
	public DDLRecord fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return super.fetchStagedModelByUuidAndGroupId(uuid, groupId);
	}

	@Deprecated
	@Override
	public List<DDLRecord> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return super.fetchStagedModelsByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(DDLRecord record) {
		return record.getUuid();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, DDLRecord record)
		throws Exception {

		StagedModelDataHandlerUtil.exportReferenceStagedModel(
			portletDataContext, record, record.getRecordSet(),
			PortletDataContext.REFERENCE_TYPE_STRONG);

		Element recordElement = portletDataContext.getExportDataElement(record);

		exportDDMFormValues(portletDataContext, record, recordElement);

		portletDataContext.addClassedModel(
			recordElement, ExportImportPathUtil.getModelPath(record), record);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, DDLRecord record)
		throws Exception {

		Map<Long, Long> recordSetIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				DDLRecordSet.class);

		long recordSetId = MapUtil.getLong(
			recordSetIds, record.getRecordSetId(), record.getRecordSetId());

		Element recordElement = portletDataContext.getImportDataElement(record);

		DDMFormValues ddmFormValues = getImportDDMFormValues(
			portletDataContext, recordElement, recordSetId);

		DDLRecord importedRecord = (DDLRecord)record.clone();

		importedRecord.setGroupId(portletDataContext.getScopeGroupId());
		importedRecord.setRecordSetId(recordSetId);

		DDLRecord existingRecord =
			_ddlRecordStagedModelRepository.fetchStagedModelByUuidAndGroupId(
				record.getUuid(), portletDataContext.getScopeGroupId());

		if (existingRecord == null) {
			importedRecord = _ddlRecordStagedModelRepository.addStagedModel(
				portletDataContext, importedRecord, ddmFormValues);
		}
		else {
			importedRecord.setRecordId(existingRecord.getRecordId());

			importedRecord = _ddlRecordStagedModelRepository.updateStagedModel(
				portletDataContext, importedRecord, ddmFormValues);
		}

		portletDataContext.importClassedModel(record, importedRecord);
	}

	protected void exportDDMFormValues(
			PortletDataContext portletDataContext, DDLRecord record,
			Element recordElement)
		throws Exception {

		String ddmFormValuesPath = ExportImportPathUtil.getModelPath(
			record, "ddm-form-values.json");

		recordElement.addAttribute("ddm-form-values-path", ddmFormValuesPath);

		DDMFormValues ddmFormValues = _storageEngine.getDDMFormValues(
			record.getDDMStorageId());

		ddmFormValues =
			_ddmFormValuesExportImportContentProcessor.
				replaceExportContentReferences(
					portletDataContext, record, ddmFormValues, true, false);

		portletDataContext.addZipEntry(
			ddmFormValuesPath,
			_ddmFormValuesJSONSerializer.serialize(ddmFormValues));
	}

	protected DDMFormValues getImportDDMFormValues(
			PortletDataContext portletDataContext, Element recordElement,
			long recordSetId)
		throws Exception {

		DDLRecordSet recordSet = _ddlRecordSetLocalService.getRecordSet(
			recordSetId);

		DDMStructure ddmStructure = recordSet.getDDMStructure();

		String ddmFormValuesPath = recordElement.attributeValue(
			"ddm-form-values-path");

		String serializedDDMFormValues = portletDataContext.getZipEntryAsString(
			ddmFormValuesPath);

		DDMFormValues ddmFormValues =
			_ddmFormValuesJSONDeserializer.deserialize(
				ddmStructure.getDDMForm(), serializedDDMFormValues);

		return _ddmFormValuesExportImportContentProcessor.
			replaceImportContentReferences(
				portletDataContext, ddmStructure, ddmFormValues);
	}

	@Override
	protected StagedModelRepository<DDLRecord> getStagedModelRepository() {
		return _ddlRecordStagedModelRepository;
	}

	@Deprecated
	protected void setDDLRecordLocalService(
		DDLRecordLocalService ddlRecordLocalService) {
	}

	@Reference(unbind = "-")
	protected void setDDLRecordSetLocalService(
		DDLRecordSetLocalService ddlRecordSetLocalService) {

		_ddlRecordSetLocalService = ddlRecordSetLocalService;
	}

	@Reference(
		target = "(model.class.name=com.liferay.dynamic.data.lists.model.DDLRecord)",
		unbind = "-"
	)
	protected void setDDLRecordStagedModelRepository(
		DDLRecordStagedModelRepository ddlRecordStagedModelRepository) {

		_ddlRecordStagedModelRepository = ddlRecordStagedModelRepository;
	}

	@Reference(unbind = "-")
	protected void setDDMFormValuesExportImportContentProcessor(
		DDMFormValuesExportImportContentProcessor
			ddmFormValuesExportImportContentProcessor) {

		_ddmFormValuesExportImportContentProcessor =
			ddmFormValuesExportImportContentProcessor;
	}

	@Reference(unbind = "-")
	protected void setDDMFormValuesJSONDeserializer(
		DDMFormValuesJSONDeserializer ddmFormValuesJSONDeserializer) {

		_ddmFormValuesJSONDeserializer = ddmFormValuesJSONDeserializer;
	}

	@Reference(unbind = "-")
	protected void setDDMFormValuesJSONSerializer(
		DDMFormValuesJSONSerializer ddmFormValuesJSONSerializer) {

		_ddmFormValuesJSONSerializer = ddmFormValuesJSONSerializer;
	}

	@Reference(unbind = "-")
	protected void setStorageEngine(StorageEngine storageEngine) {
		_storageEngine = storageEngine;
	}

	@Override
	protected void validateExport(
			PortletDataContext portletDataContext, DDLRecord record)
		throws PortletDataException {

		int status = WorkflowConstants.STATUS_ANY;

		try {
			status = record.getStatus();
		}
		catch (Exception e) {
			throw new PortletDataException(e);
		}

		if (!portletDataContext.isInitialPublication() &&
			!ArrayUtil.contains(getExportableStatuses(), status)) {

			PortletDataException pde = new PortletDataException(
				PortletDataException.STATUS_UNAVAILABLE);

			pde.setStagedModel(record);

			throw pde;
		}
	}

	private DDLRecordSetLocalService _ddlRecordSetLocalService;
	private DDLRecordStagedModelRepository _ddlRecordStagedModelRepository;
	private DDMFormValuesExportImportContentProcessor
		_ddmFormValuesExportImportContentProcessor;
	private DDMFormValuesJSONDeserializer _ddmFormValuesJSONDeserializer;
	private DDMFormValuesJSONSerializer _ddmFormValuesJSONSerializer;
	private StorageEngine _storageEngine;

}