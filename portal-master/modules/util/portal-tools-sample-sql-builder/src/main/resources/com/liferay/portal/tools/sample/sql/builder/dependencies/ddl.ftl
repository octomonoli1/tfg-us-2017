<#assign ddlRecordSetCounts = dataFactory.getSequence(dataFactory.maxDDLRecordSetCount)>

<#list ddlRecordSetCounts as ddlRecordSetCount>
	<#if ddlRecordSetCount = 1>
		<#assign ddmStructureModel = dataFactory.newDDLDDMStructureModel(groupId)>
		<#assign ddmStructureVersionModel = dataFactory.newDDMStructureVersionModel(ddmStructureModel)>

		<@insertDDMStructure
			_ddmStructureModel = ddmStructureModel
			_ddmStructureLayoutModel = dataFactory.newDDLDDMStructureLayoutModel(groupId, ddmStructureVersionModel)
			_ddmStructureVersionModel = ddmStructureVersionModel
		/>
	</#if>

	<#assign layoutName = "dynamic_data_list_display_" + ddlRecordSetCount>
	<#assign portletId = "com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet_INSTANCE_TEST" + ddlRecordSetCount>

	<#assign layoutModel = dataFactory.newLayoutModel(groupId, layoutName, "", portletId)>

	<@insertLayout
		_layoutModel = layoutModel
	/>

	<#assign ddlRecordSetModel = dataFactory.newDDLRecordSetModel(ddmStructureModel, ddlRecordSetCount)>

	insert into DDLRecordSet values ('${ddlRecordSetModel.uuid}', ${ddlRecordSetModel.recordSetId}, ${ddlRecordSetModel.groupId}, ${ddlRecordSetModel.companyId}, ${ddlRecordSetModel.userId}, '${ddlRecordSetModel.userName}', '${dataFactory.getDateString(ddlRecordSetModel.createDate)}', '${dataFactory.getDateString(ddlRecordSetModel.modifiedDate)}', ${ddlRecordSetModel.DDMStructureId}, '${ddlRecordSetModel.recordSetKey}', '${ddlRecordSetModel.name}', '${ddlRecordSetModel.description}', ${ddlRecordSetModel.minDisplayRows}, ${ddlRecordSetModel.scope}, '${ddlRecordSetModel.settings}', '${dataFactory.getDateString(ddlRecordSetModel.lastPublishDate)}');

	<@insertDDMStructureLink
		_entry = ddlRecordSetModel
	/>

	<@insertResourcePermissions
		_entry = ddlRecordSetModel
	/>

	<#assign ddlRecordCounts = dataFactory.getSequence(dataFactory.maxDDLRecordCount)>

	<#list ddlRecordCounts as ddlRecordCount>
		<#assign ddlRecordModel = dataFactory.newDDLRecordModel(ddlRecordSetModel)>

		insert into DDLRecord values ('${ddlRecordModel.uuid}', ${ddlRecordModel.recordId}, ${ddlRecordModel.groupId}, ${ddlRecordModel.companyId}, ${ddlRecordModel.userId}, '${ddlRecordModel.userName}', ${ddlRecordModel.versionUserId}, '${ddlRecordModel.versionUserName}', '${dataFactory.getDateString(ddlRecordModel.createDate)}', '${dataFactory.getDateString(ddlRecordModel.modifiedDate)}', ${ddlRecordModel.DDMStorageId}, ${ddlRecordModel.recordSetId}, '${ddlRecordModel.version}', ${ddlRecordModel.displayIndex}, '${dataFactory.getDateString(ddlRecordModel.lastPublishDate)}');

		<#assign ddlRecordVersionModel = dataFactory.newDDLRecordVersionModel(ddlRecordModel)>

		insert into DDLRecordVersion values (${ddlRecordVersionModel.recordVersionId}, ${ddlRecordVersionModel.groupId}, ${ddlRecordVersionModel.companyId}, ${ddlRecordVersionModel.userId}, '${ddlRecordVersionModel.userName}', '${dataFactory.getDateString(ddlRecordVersionModel.createDate)}', ${ddlRecordVersionModel.DDMStorageId}, ${ddlRecordVersionModel.recordSetId}, ${ddlRecordVersionModel.recordId}, '${ddlRecordVersionModel.version}', ${ddlRecordVersionModel.displayIndex}, ${ddlRecordVersionModel.status}, ${ddlRecordVersionModel.statusByUserId}, '${ddlRecordVersionModel.statusByUserName}', '${dataFactory.getDateString(ddlRecordVersionModel.statusDate)}');

		<@insertDDMContent
			_currentIndex = ddlRecordCount
			_ddmStorageLinkId = dataFactory.getCounterNext()
			_ddmStructureId = ddmStructureModel.structureId
			_entry = ddlRecordModel
		/>

		${dynamicDataListCSVWriter.write(ddlRecordModel.groupId + "," + layoutName + "," + portletId + "," + ddlRecordSetModel.recordSetId + "," + ddlRecordModel.recordId + "\n")}
	</#list>

	<#assign portletPreferencesModel = dataFactory.newPortletPreferencesModel(layoutModel.plid, portletId, ddlRecordSetModel)>

	<@insertPortletPreferences
		_portletPreferencesModel = portletPreferencesModel
	/>

	<#assign portletPreferencesModels = dataFactory.newDDLPortletPreferencesModels(layoutModel.plid)>

	<#list portletPreferencesModels as portletPreferencesModel>
		<@insertPortletPreferences
			_portletPreferencesModel = portletPreferencesModel
		/>
	</#list>
</#list>