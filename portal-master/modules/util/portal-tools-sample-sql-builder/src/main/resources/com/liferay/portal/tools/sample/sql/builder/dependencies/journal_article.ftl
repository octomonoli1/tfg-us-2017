<#assign ddmStructureModel = dataFactory.defaultJournalDDMStructureModel>

<@insertDDMStructure
	_ddmStructureModel = ddmStructureModel
	_ddmStructureLayoutModel = dataFactory.defaultJournalDDMStructureLayoutModel
	_ddmStructureVersionModel = dataFactory.defaultJournalDDMStructureVersionModel
/>

<#assign ddmTemplateModel = dataFactory.defaultJournalDDMTemplateModel>

insert into DDMTemplate values ('${ddmTemplateModel.uuid}', ${ddmTemplateModel.templateId}, ${ddmTemplateModel.groupId}, ${ddmTemplateModel.companyId}, ${ddmTemplateModel.userId}, '${ddmTemplateModel.userName}', ${ddmTemplateModel.versionUserId}, '${ddmTemplateModel.versionUserName}', '${dataFactory.getDateString(ddmTemplateModel.createDate)}', '${dataFactory.getDateString(ddmTemplateModel.modifiedDate)}', ${ddmTemplateModel.classNameId}, ${ddmTemplateModel.classPK}, ${ddmTemplateModel.resourceClassNameId}, '${ddmTemplateModel.templateKey}', '${ddmTemplateModel.version}', '${ddmTemplateModel.name}', '${ddmTemplateModel.description}', '${ddmTemplateModel.type}', '${ddmTemplateModel.mode}', '${ddmTemplateModel.language}', '${ddmTemplateModel.script}', ${ddmTemplateModel.cacheable?string}, ${ddmTemplateModel.smallImage?string}, ${ddmTemplateModel.smallImageId}, '${ddmTemplateModel.smallImageURL}', '${dataFactory.getDateString(ddmTemplateModel.lastPublishDate)}');

<@insertResourcePermissions
	_entry = ddmTemplateModel
/>

<#assign journalArticlePageCounts = dataFactory.getSequence(dataFactory.maxJournalArticlePageCount)>

<#assign resourcePermissionModels = dataFactory.newResourcePermissionModels("com.liferay.journal", groupId)>

<#list resourcePermissionModels as resourcePermissionModel>
	<@insertResourcePermission
		_resourcePermissionModel = resourcePermissionModel
	/>
</#list>

<#list journalArticlePageCounts as journalArticlePageCount>
	<#assign portletIdPrefix = "com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_TEST_" + journalArticlePageCount + "_">

	<#assign layoutModel = dataFactory.newLayoutModel(groupId, groupId + "_journal_article_" + journalArticlePageCount, "", dataFactory.getJournalArticleLayoutColumn(portletIdPrefix))>

	${layoutCSVWriter.write(layoutModel.friendlyURL + "\n")}

	<@insertLayout
		_layoutModel = layoutModel
	/>

	<#assign portletPreferencesModels = dataFactory.newJournalPortletPreferencesModels(layoutModel.plid)>

	<#list portletPreferencesModels as portletPreferencesModel>
		<@insertPortletPreferences
			_portletPreferencesModel = portletPreferencesModel
		/>
	</#list>

	<#assign journalArticleCounts = dataFactory.getSequence(dataFactory.maxJournalArticleCount)>

	<#list journalArticleCounts as journalArticleCount>
		<#assign journalArticleResourceModel = dataFactory.newJournalArticleResourceModel(groupId)>

		insert into JournalArticleResource values ('${journalArticleResourceModel.uuid}', ${journalArticleResourceModel.resourcePrimKey}, ${journalArticleResourceModel.groupId}, ${journalArticleResourceModel.companyId}, '${journalArticleResourceModel.articleId}');

		<#assign versionCounts = dataFactory.getSequence(dataFactory.maxJournalArticleVersionCount)>

		<#list versionCounts as versionCount>
			<#assign journalArticleModel = dataFactory.newJournalArticleModel(journalArticleResourceModel, journalArticleCount, versionCount)>

			insert into JournalArticle values ('${journalArticleModel.uuid}', ${journalArticleModel.id}, ${journalArticleModel.resourcePrimKey}, ${journalArticleModel.groupId}, ${journalArticleModel.companyId}, ${journalArticleModel.userId}, '${journalArticleModel.userName}', '${dataFactory.getDateString(journalArticleModel.createDate)}', '${dataFactory.getDateString(journalArticleModel.modifiedDate)}', ${journalArticleModel.folderId}, ${journalArticleModel.classNameId}, ${journalArticleModel.classPK}, '', '${journalArticleModel.articleId}', ${journalArticleModel.version}, '${journalArticleModel.title}', '${journalArticleModel.urlTitle}', '${journalArticleModel.description}', '${journalArticleModel.content}', '${journalArticleModel.DDMStructureKey}', '${journalArticleModel.DDMTemplateKey}', '${journalArticleModel.layoutUuid}', '${dataFactory.getDateString(journalArticleModel.displayDate)}', '${dataFactory.getDateString(journalArticleModel.expirationDate)}', '${dataFactory.getDateString(journalArticleModel.reviewDate)}', ${journalArticleModel.indexable?string}, ${journalArticleModel.smallImage?string}, ${journalArticleModel.smallImageId}, '${journalArticleModel.smallImageURL}', '${dataFactory.getDateString(journalArticleModel.lastPublishDate)}', ${journalArticleModel.status}, ${journalArticleModel.statusByUserId}, '${journalArticleModel.statusByUserName}', '${dataFactory.getDateString(journalArticleModel.statusDate)}');

			<#assign ddmTemplateLinkModel = dataFactory.newDDMTemplateLinkModel(journalArticleModel, ddmTemplateModel.templateId)>

			insert into DDMTemplateLink values (${ddmTemplateLinkModel.templateLinkId}, ${ddmTemplateLinkModel.companyId}, ${ddmTemplateLinkModel.classNameId}, ${ddmTemplateLinkModel.classPK}, ${ddmTemplateLinkModel.templateId});

			<@insertDDMStorageLink
				_ddmStorageLinkModel = dataFactory.newDDMStorageLinkModel(journalArticleModel, ddmStructureModel.structureId)
			/>

			<@insertSocialActivity
				_entry = journalArticleModel
			/>

			<#if versionCount = dataFactory.maxJournalArticleVersionCount>
				<@insertAssetEntry
					_entry = journalArticleModel
					_categoryAndTag = true
				/>
			</#if>
		</#list>

		<@insertResourcePermissions
			_entry = journalArticleResourceModel
		/>

		<@insertMBDiscussion
			_classNameId = dataFactory.journalArticleClassNameId
			_classPK = journalArticleResourceModel.resourcePrimKey
			_groupId = groupId
			_maxCommentCount = 0
			_mbRootMessageId = dataFactory.getCounterNext()
			_mbThreadId = dataFactory.getCounterNext()
		/>

		<#assign portletPreferencesModel = dataFactory.newPortletPreferencesModel(layoutModel.plid, portletIdPrefix + journalArticleCount, journalArticleResourceModel)>

		<@insertPortletPreferences
			_portletPreferencesModel = portletPreferencesModel
		/>

		<#assign journalContentSearchModel = dataFactory.newJournalContentSearchModel(journalArticleModel, layoutModel.plid)>

		insert into JournalContentSearch values (${journalContentSearchModel.contentSearchId}, ${journalContentSearchModel.groupId}, ${journalContentSearchModel.companyId}, ${journalContentSearchModel.privateLayout?string}, ${journalContentSearchModel.layoutId}, '${journalContentSearchModel.portletId}', '${journalContentSearchModel.articleId}');
	</#list>
</#list>