<#setting number_format = "computer">

<#macro insertAssetEntry
	_entry
	_categoryAndTag = false
>
	<#local assetEntryModel = dataFactory.newAssetEntryModel(_entry)>

	insert into AssetEntry values (${assetEntryModel.entryId}, ${assetEntryModel.groupId}, ${assetEntryModel.companyId}, ${assetEntryModel.userId}, '${assetEntryModel.userName}', '${dataFactory.getDateString(assetEntryModel.createDate)}', '${dataFactory.getDateString(assetEntryModel.modifiedDate)}', ${assetEntryModel.classNameId}, ${assetEntryModel.classPK}, '${assetEntryModel.classUuid}', ${assetEntryModel.classTypeId}, ${assetEntryModel.listable?string}, ${assetEntryModel.visible?string}, '${dataFactory.getDateString(assetEntryModel.startDate)}', '${dataFactory.getDateString(assetEntryModel.endDate)}', '${dataFactory.getDateString(assetEntryModel.publishDate)}', '${dataFactory.getDateString(assetEntryModel.expirationDate)}', '${assetEntryModel.mimeType}', '${assetEntryModel.title}', '${assetEntryModel.description}', '${assetEntryModel.summary}', '${assetEntryModel.url}', '${assetEntryModel.layoutUuid}', ${assetEntryModel.height}, ${assetEntryModel.width}, ${assetEntryModel.priority}, ${assetEntryModel.viewCount});

	<#if _categoryAndTag>
		<#local assetCategoryIds = dataFactory.getAssetCategoryIds(assetEntryModel.groupId)>

		<#list assetCategoryIds as assetCategoryId>
			insert into AssetEntries_AssetCategories values (${assetEntryModel.companyId}, ${assetCategoryId}, ${assetEntryModel.entryId});
		</#list>

		<#local assetTagIds = dataFactory.getAssetTagIds(assetEntryModel.groupId)>

		<#list assetTagIds as assetTagId>
			insert into AssetEntries_AssetTags values (${assetEntryModel.companyId}, ${assetEntryModel.entryId}, ${assetTagId});
		</#list>
	</#if>
</#macro>

<#macro insertDDMContent
	_ddmStorageLinkId
	_ddmStructureId
	_entry
	_currentIndex = -1
>
	<#if _currentIndex = -1>
		<#local ddmContentModel = dataFactory.newDDMContentModel(_entry)>
	<#else>
		<#local ddmContentModel = dataFactory.newDDMContentModel(_entry, _currentIndex)>
	</#if>

	insert into DDMContent values ('${ddmContentModel.uuid}', ${ddmContentModel.contentId}, ${ddmContentModel.groupId}, ${ddmContentModel.companyId}, ${ddmContentModel.userId}, '${ddmContentModel.userName}', '${dataFactory.getDateString(ddmContentModel.createDate)}', '${dataFactory.getDateString(ddmContentModel.modifiedDate)}', '${ddmContentModel.name}', '${ddmContentModel.description}', '${ddmContentModel.data}');

	<@insertDDMStorageLink
		_ddmStorageLinkModel = dataFactory.newDDMStorageLinkModel(_ddmStorageLinkId, ddmContentModel, _ddmStructureId)
	/>
</#macro>


<#macro insertDDMStorageLink
	_ddmStorageLinkModel
>
	insert into DDMStorageLink values ('${_ddmStorageLinkModel.uuid}', ${_ddmStorageLinkModel.storageLinkId}, ${_ddmStorageLinkModel.companyId}, ${_ddmStorageLinkModel.classNameId}, ${_ddmStorageLinkModel.classPK}, ${_ddmStorageLinkModel.structureId});
</#macro>

<#macro insertDDMStructure
	_ddmStructureModel
	_ddmStructureLayoutModel
	_ddmStructureVersionModel
>
	insert into DDMStructure values ('${_ddmStructureModel.uuid}', ${_ddmStructureModel.structureId}, ${_ddmStructureModel.groupId}, ${_ddmStructureModel.companyId}, ${_ddmStructureModel.userId}, '${_ddmStructureModel.userName}', ${_ddmStructureModel.versionUserId}, '${_ddmStructureModel.versionUserName}', '${dataFactory.getDateString(_ddmStructureModel.createDate)}', '${dataFactory.getDateString(_ddmStructureModel.modifiedDate)}', ${_ddmStructureModel.parentStructureId}, ${_ddmStructureModel.classNameId}, '${_ddmStructureModel.structureKey}', '${_ddmStructureModel.version}', '${_ddmStructureModel.name}', '${_ddmStructureModel.description}', '${_ddmStructureModel.definition}', '${_ddmStructureModel.storageType}', ${_ddmStructureModel.type}, '${dataFactory.getDateString(_ddmStructureModel.lastPublishDate)}');

	<@insertResourcePermissions
		_entry = _ddmStructureModel
	/>

	insert into DDMStructureLayout values ('${_ddmStructureLayoutModel.uuid}', ${_ddmStructureLayoutModel.structureLayoutId}, ${_ddmStructureLayoutModel.groupId}, ${_ddmStructureLayoutModel.companyId}, ${_ddmStructureLayoutModel.userId}, '${_ddmStructureLayoutModel.userName}', '${dataFactory.getDateString(_ddmStructureLayoutModel.createDate)}', '${dataFactory.getDateString(_ddmStructureLayoutModel.modifiedDate)}', ${_ddmStructureLayoutModel.structureVersionId},'${_ddmStructureLayoutModel.definition}');

	insert into DDMStructureVersion values (${_ddmStructureVersionModel.structureVersionId}, ${_ddmStructureVersionModel.groupId}, ${_ddmStructureVersionModel.companyId}, ${_ddmStructureVersionModel.userId}, '${_ddmStructureVersionModel.userName}', '${dataFactory.getDateString(_ddmStructureVersionModel.createDate)}',  ${_ddmStructureVersionModel.structureId}, '${_ddmStructureVersionModel.version}', ${_ddmStructureVersionModel.parentStructureId}, '${_ddmStructureVersionModel.name}', '${_ddmStructureVersionModel.description}', '${_ddmStructureVersionModel.definition}', '${_ddmStructureVersionModel.storageType}', ${_ddmStructureVersionModel.type}, ${_ddmStructureVersionModel.status}, ${_ddmStructureVersionModel.statusByUserId}, '${_ddmStructureVersionModel.statusByUserName}', '${dataFactory.getDateString(_ddmStructureVersionModel.statusDate)}');
</#macro>

<#macro insertDDMStructureLink
	_entry
>
	<#local ddmStructureLinkModel = dataFactory.newDDMStructureLinkModel(_entry)>

	insert into DDMStructureLink values (${ddmStructureLinkModel.structureLinkId}, ${ddmStructureLinkModel.companyId}, ${ ddmStructureLinkModel.classNameId}, ${ddmStructureLinkModel.classPK}, ${ddmStructureLinkModel.structureId});
</#macro>

<#macro insertDLFolder
	_ddmStructureId
	_dlFolderDepth
	_groupId
	_parentDLFolderId
>
	<#if _dlFolderDepth <= dataFactory.maxDLFolderDepth>
		<#local dlFolderModels = dataFactory.newDLFolderModels(_groupId, _parentDLFolderId)>

		<#list dlFolderModels as dlFolderModel>
			insert into DLFolder values ('${dlFolderModel.uuid}', ${dlFolderModel.folderId}, ${dlFolderModel.groupId}, ${dlFolderModel.companyId}, ${dlFolderModel.userId}, '${dlFolderModel.userName}', '${dataFactory.getDateString(dlFolderModel.createDate)}', '${dataFactory.getDateString(dlFolderModel.modifiedDate)}', ${dlFolderModel.repositoryId}, ${dlFolderModel.mountPoint?string}, ${dlFolderModel.parentFolderId}, '', '${dlFolderModel.name}', '${dlFolderModel.description}', '${dataFactory.getDateString(dlFolderModel.lastPostDate)}', ${dlFolderModel.defaultFileEntryTypeId}, ${dlFolderModel.hidden?string}, ${dlFolderModel.restrictionType}, '${dataFactory.getDateString(dlFolderModel.lastPublishDate)}', ${dlFolderModel.status}, ${dlFolderModel.statusByUserId}, '${dlFolderModel.statusByUserName}', '${dataFactory.getDateString(dlFolderModel.statusDate)}');

			<@insertResourcePermissions
				_entry = dlFolderModel
			/>

			<@insertAssetEntry
				_entry = dlFolderModel
			/>

			<#local dlFileEntryModels = dataFactory.newDlFileEntryModels(dlFolderModel)>

			<#list dlFileEntryModels as dlFileEntryModel>
				insert into DLFileEntry values ('${dlFileEntryModel.uuid}', ${dlFileEntryModel.fileEntryId}, ${dlFileEntryModel.groupId}, ${dlFileEntryModel.companyId}, ${dlFileEntryModel.userId}, '${dlFileEntryModel.userName}', '${dataFactory.getDateString(dlFileEntryModel.createDate)}', '${dataFactory.getDateString(dlFileEntryModel.modifiedDate)}', ${dlFileEntryModel.classNameId}, ${dlFileEntryModel.classPK}, ${dlFileEntryModel.repositoryId}, ${dlFileEntryModel.folderId}, '', '${dlFileEntryModel.name}', '${dlFileEntryModel.fileName}','${dlFileEntryModel.extension}', '${dlFileEntryModel.mimeType}', '${dlFileEntryModel.title}','${dlFileEntryModel.description}', '${dlFileEntryModel.extraSettings}', ${dlFileEntryModel.fileEntryTypeId}, '${dlFileEntryModel.version}', ${dlFileEntryModel.size}, ${dlFileEntryModel.readCount}, ${dlFileEntryModel.smallImageId}, ${dlFileEntryModel.largeImageId}, ${dlFileEntryModel.custom1ImageId}, ${dlFileEntryModel.custom2ImageId}, ${dlFileEntryModel.manualCheckInRequired?string}, '${dataFactory.getDateString(dlFileEntryModel.lastPublishDate)}');

				<#local dlFileVersionModel = dataFactory.newDLFileVersionModel(dlFileEntryModel)>

				insert into DLFileVersion values ('${dlFileVersionModel.uuid}', ${dlFileVersionModel.fileVersionId}, ${dlFileVersionModel.groupId}, ${dlFileVersionModel.companyId}, ${dlFileVersionModel.userId}, '${dlFileVersionModel.userName}', '${dataFactory.getDateString(dlFileVersionModel.createDate)}', '${dataFactory.getDateString(dlFileVersionModel.modifiedDate)}', ${dlFileVersionModel.repositoryId}, ${dlFileVersionModel.folderId}, ${dlFileVersionModel.fileEntryId}, '', '${dlFileVersionModel.fileName}','${dlFileVersionModel.extension}', '${dlFileVersionModel.mimeType}', '${dlFileVersionModel.title}','${dlFileVersionModel.description}', '${dlFileVersionModel.changeLog}', '${dlFileVersionModel.extraSettings}', ${dlFileVersionModel.fileEntryTypeId}, '${dlFileVersionModel.version}', ${dlFileVersionModel.size}, '${dlFileVersionModel.checksum}', '${dataFactory.getDateString(dlFileVersionModel.lastPublishDate)}', ${dlFileVersionModel.status}, ${dlFileVersionModel.statusByUserId}, '${dlFileVersionModel.statusByUserName}', ${dlFileVersionModel.statusDate!'null'});

				<@insertResourcePermissions
					_entry = dlFileEntryModel
				/>

				<@insertAssetEntry
					_entry = dlFileEntryModel
				/>

				<#local ddmStorageLinkId = dataFactory.getCounterNext()>

				<@insertDDMContent
					_ddmStorageLinkId = ddmStorageLinkId
					_ddmStructureId = _ddmStructureId
					_entry = dlFileEntryModel
				/>

				<@insertMBDiscussion
					_classNameId = dataFactory.DLFileEntryClassNameId
					_classPK = dlFileEntryModel.fileEntryId
					_groupId = dlFileEntryModel.groupId
					_maxCommentCount = 0
					_mbRootMessageId = dataFactory.getCounterNext()
					_mbThreadId = dataFactory.getCounterNext()
				/>

				<@insertSocialActivity
					_entry = dlFileEntryModel
				/>

				<#local dlFileEntryMetadataModel = dataFactory.newDLFileEntryMetadataModel(ddmStorageLinkId, _ddmStructureId, dlFileVersionModel)>

				insert into DLFileEntryMetadata values ('${dlFileEntryMetadataModel.uuid}', ${dlFileEntryMetadataModel.fileEntryMetadataId}, ${dlFileEntryMetadataModel.companyId}, ${dlFileEntryMetadataModel.DDMStorageId}, ${dlFileEntryMetadataModel.DDMStructureId}, ${dlFileEntryMetadataModel.fileEntryId}, ${dlFileEntryMetadataModel.fileVersionId});

				<@insertDDMStructureLink
					_entry = dlFileEntryMetadataModel
				/>

				${documentLibraryCSVWriter.write(dlFolderModel.folderId + "," + dlFileEntryModel.name + "," + dlFileEntryModel.fileEntryId + "," + dataFactory.getDateLong(dlFileEntryModel.createDate) + "," + dataFactory.getDateLong(dlFolderModel.createDate) + "\n")}
			</#list>

			<@insertDLFolder
				_ddmStructureId = _ddmStructureId
				_dlFolderDepth = _dlFolderDepth + 1
				_groupId = groupId
				_parentDLFolderId = dlFolderModel.folderId
			/>
		</#list>
	</#if>
</#macro>

<#macro insertGroup
	_groupModel
	_publicPageCount
>
	insert into Group_ values (${_groupModel.mvccVersion}, '${_groupModel.uuid}', ${_groupModel.groupId}, ${_groupModel.companyId}, ${_groupModel.creatorUserId}, ${_groupModel.classNameId}, ${_groupModel.classPK}, ${_groupModel.parentGroupId}, ${_groupModel.liveGroupId}, '${_groupModel.treePath}', '${_groupModel.groupKey}', '${_groupModel.name}', '${_groupModel.description}', ${_groupModel.type}, '${_groupModel.typeSettings}', ${_groupModel.manualMembership?string}, ${_groupModel.membershipRestriction}, '${_groupModel.friendlyURL}', ${_groupModel.site?string}, ${_groupModel.remoteStagingGroupCount}, ${_groupModel.inheritContent?string}, ${_groupModel.active?string});

	<@insertResourcePermissions
		_entry = _groupModel
	/>

	<#local layoutSetModels = dataFactory.newLayoutSetModels(_groupModel.groupId, _publicPageCount)>

	<#list layoutSetModels as layoutSetModel>
		insert into LayoutSet values (${layoutSetModel.mvccVersion}, ${layoutSetModel.layoutSetId}, ${layoutSetModel.groupId}, ${layoutSetModel.companyId}, '${dataFactory.getDateString(layoutSetModel.createDate)}', '${dataFactory.getDateString(layoutSetModel.modifiedDate)}', ${layoutSetModel.privateLayout?string}, ${layoutSetModel.logoId}, '${layoutSetModel.themeId}', '${layoutSetModel.colorSchemeId}', '${layoutSetModel.css}', ${layoutSetModel.pageCount}, '${layoutSetModel.settings}', '${layoutSetModel.layoutSetPrototypeUuid}', ${layoutSetModel.layoutSetPrototypeLinkEnabled?string});
	</#list>
</#macro>

<#macro insertLayout
	_layoutModel
>
	insert into Layout values (${_layoutModel.mvccVersion}, '${_layoutModel.uuid}', ${_layoutModel.plid}, ${_layoutModel.groupId}, ${_layoutModel.companyId}, ${_layoutModel.userId}, '${_layoutModel.userName}', '${dataFactory.getDateString(_layoutModel.createDate)}', '${dataFactory.getDateString(_layoutModel.modifiedDate)}', ${_layoutModel.privateLayout?string}, ${_layoutModel.layoutId}, ${_layoutModel.parentLayoutId}, '${_layoutModel.name}', '${_layoutModel.title}', '${_layoutModel.description}', '${_layoutModel.keywords}', '${_layoutModel.robots}', '${_layoutModel.type}', '${_layoutModel.typeSettings}', ${_layoutModel.hidden?string}, '${_layoutModel.friendlyURL}', ${_layoutModel.iconImageId}, '${_layoutModel.themeId}', '${_layoutModel.colorSchemeId}', '${_layoutModel.css}', ${_layoutModel.priority}, '${_layoutModel.layoutPrototypeUuid}', ${_layoutModel.layoutPrototypeLinkEnabled?string}, '${_layoutModel.sourcePrototypeLayoutUuid}', '${dataFactory.getDateString(_layoutModel.lastPublishDate)}');

	<@insertResourcePermissions
		_entry = _layoutModel
	/>

	<#local layoutFriendlyURLModel = dataFactory.newLayoutFriendlyURLModel(_layoutModel)>

	insert into LayoutFriendlyURL values (${layoutFriendlyURLModel.mvccVersion}, '${layoutFriendlyURLModel.uuid}', ${layoutFriendlyURLModel.layoutFriendlyURLId}, ${layoutFriendlyURLModel.groupId}, ${layoutFriendlyURLModel.companyId}, ${layoutFriendlyURLModel.userId}, '${layoutFriendlyURLModel.userName}', '${dataFactory.getDateString(layoutFriendlyURLModel.createDate)}', '${dataFactory.getDateString(layoutFriendlyURLModel.modifiedDate)}', ${layoutFriendlyURLModel.plid}, ${layoutFriendlyURLModel.privateLayout?string}, '${layoutFriendlyURLModel.friendlyURL}', '${layoutFriendlyURLModel.languageId}', '${dataFactory.getDateString(layoutFriendlyURLModel.lastPublishDate)}');
</#macro>

<#macro insertMBDiscussion
	_classNameId
	_classPK
	_groupId
	_maxCommentCount
	_mbRootMessageId
	_mbThreadId
>
	<#local mbThreadModel = dataFactory.newMBThreadModel(_mbThreadId, _groupId, _mbRootMessageId, _maxCommentCount)>

	insert into MBThread values ('${mbThreadModel.uuid}', ${mbThreadModel.threadId}, ${mbThreadModel.groupId}, ${mbThreadModel.companyId}, ${mbThreadModel.userId}, '${mbThreadModel.userName}', '${dataFactory.getDateString(mbThreadModel.createDate)}', '${dataFactory.getDateString(mbThreadModel.modifiedDate)}', ${mbThreadModel.categoryId}, ${mbThreadModel.rootMessageId}, ${mbThreadModel.rootMessageUserId}, ${mbThreadModel.messageCount}, ${mbThreadModel.viewCount}, ${mbThreadModel.lastPostByUserId}, '${dataFactory.getDateString(mbThreadModel.lastPostDate)}', ${mbThreadModel.priority}, ${mbThreadModel.question?string}, '${dataFactory.getDateString(mbThreadModel.lastPublishDate)}', ${mbThreadModel.status}, ${mbThreadModel.statusByUserId}, '${mbThreadModel.statusByUserName}', '${dataFactory.getDateString(mbThreadModel.statusDate)}');

	<#local mbRootMessageModel = dataFactory.newMBMessageModel(mbThreadModel, _classNameId, _classPK, 0)>

	<@insertMBMessage
		_mbMessageModel = mbRootMessageModel
	/>

	<#local mbMessageModels = dataFactory.newMBMessageModels(mbThreadModel, _classNameId, _classPK, _maxCommentCount)>

	<#list mbMessageModels as mbMessageModel>
		<@insertMBMessage
			_mbMessageModel = mbMessageModel
		/>

		<@insertSocialActivity
			_entry = mbMessageModel
		/>
	</#list>

	<#local mbDiscussionModel = dataFactory.newMBDiscussionModel(_groupId, _classNameId, _classPK, _mbThreadId)>

	insert into MBDiscussion values ('${mbDiscussionModel.uuid}', ${mbDiscussionModel.discussionId}, ${mbDiscussionModel.groupId}, ${mbDiscussionModel.companyId}, ${mbDiscussionModel.userId}, '${mbDiscussionModel.userName}', '${dataFactory.getDateString(mbDiscussionModel.createDate)}', '${dataFactory.getDateString(mbDiscussionModel.modifiedDate)}', ${mbDiscussionModel.classNameId}, ${mbDiscussionModel.classPK}, ${mbDiscussionModel.threadId}, '${dataFactory.getDateString(mbDiscussionModel.lastPublishDate)}');
</#macro>

<#macro insertMBMessage
	_mbMessageModel
>
	insert into MBMessage values ('${_mbMessageModel.uuid}', ${_mbMessageModel.messageId}, ${_mbMessageModel.groupId}, ${_mbMessageModel.companyId}, ${_mbMessageModel.userId}, '${_mbMessageModel.userName}', '${dataFactory.getDateString(_mbMessageModel.createDate)}', '${dataFactory.getDateString(_mbMessageModel.modifiedDate)}', ${_mbMessageModel.classNameId}, ${_mbMessageModel.classPK}, ${_mbMessageModel.categoryId}, ${_mbMessageModel.threadId}, ${_mbMessageModel.rootMessageId}, ${_mbMessageModel.parentMessageId}, '${_mbMessageModel.subject}', '${_mbMessageModel.body}', '${_mbMessageModel.format}', ${_mbMessageModel.anonymous?string}, ${_mbMessageModel.priority}, ${_mbMessageModel.allowPingbacks?string}, ${_mbMessageModel.answer?string}, '${dataFactory.getDateString(_mbMessageModel.lastPublishDate)}', ${_mbMessageModel.status}, ${_mbMessageModel.statusByUserId}, '${_mbMessageModel.statusByUserName}', '${dataFactory.getDateString(_mbMessageModel.statusDate)}');

	<@insertResourcePermissions
		_entry = _mbMessageModel
	/>

	<@insertAssetEntry
		_entry = _mbMessageModel
	/>
</#macro>

<#macro insertPortletPreferences
	_portletPreferencesModel
>
	insert into PortletPreferences values (${_portletPreferencesModel.mvccVersion}, ${_portletPreferencesModel.portletPreferencesId}, ${_portletPreferencesModel.companyId}, ${_portletPreferencesModel.ownerId}, ${_portletPreferencesModel.ownerType}, ${_portletPreferencesModel.plid}, '${_portletPreferencesModel.portletId}', '${_portletPreferencesModel.preferences}');

	<@insertResourcePermissions
		_entry = _portletPreferencesModel
	/>
</#macro>

<#macro insertResourcePermission
	_resourcePermissionModel
>
	insert into ResourcePermission values (${_resourcePermissionModel.mvccVersion}, ${_resourcePermissionModel.resourcePermissionId}, ${_resourcePermissionModel.companyId}, '${_resourcePermissionModel.name}', ${_resourcePermissionModel.scope}, '${_resourcePermissionModel.primKey}', ${_resourcePermissionModel.primKeyId}, ${_resourcePermissionModel.roleId}, ${_resourcePermissionModel.ownerId}, ${_resourcePermissionModel.actionIds}, ${_resourcePermissionModel.viewActionId?string("true", "false")});
</#macro>

<#macro insertResourcePermissions
	_entry
>
	<#local resourcePermissionModels = dataFactory.newResourcePermissionModels(_entry)>

	<#list resourcePermissionModels as resourcePermissionModel>
		<@insertResourcePermission
			_resourcePermissionModel = resourcePermissionModel
		/>
	</#list>
</#macro>

<#macro insertSocialActivity
	_entry
>
	<#local socialActivityModel = dataFactory.newSocialActivityModel(_entry)>

	insert into SocialActivity values (${socialActivityModel.activityId}, ${socialActivityModel.groupId}, ${socialActivityModel.companyId}, ${socialActivityModel.userId}, ${socialActivityModel.createDate}, ${socialActivityModel.activitySetId}, ${socialActivityModel.mirrorActivityId}, ${socialActivityModel.classNameId}, ${socialActivityModel.classPK}, ${socialActivityModel.parentClassNameId}, ${socialActivityModel.parentClassPK}, ${socialActivityModel.type}, '${socialActivityModel.extraData}', ${socialActivityModel.receiverUserId});
</#macro>

<#macro insertSubscription
	_entry
>
	<#local subscriptionModel = dataFactory.newSubscriptionModel(_entry)>

	insert into Subscription values (${subscriptionModel.mvccVersion}, ${subscriptionModel.subscriptionId}, ${subscriptionModel.groupId}, ${subscriptionModel.companyId}, ${subscriptionModel.userId}, '${subscriptionModel.userName}', '${dataFactory.getDateString(subscriptionModel.createDate)}', '${dataFactory.getDateString(subscriptionModel.modifiedDate)}', '${subscriptionModel.classNameId}', ${subscriptionModel.classPK}, '${subscriptionModel.frequency}');
</#macro>

<#macro insertUser
	_userModel
	_groupIds = []
	_roleIds = []
>
	insert into User_ values (${_userModel.mvccVersion}, '${_userModel.uuid}', ${_userModel.userId}, ${_userModel.companyId}, '${dataFactory.getDateString(_userModel.createDate)}', '${dataFactory.getDateString(_userModel.modifiedDate)}', ${_userModel.defaultUser?string}, ${_userModel.contactId}, '${_userModel.password}', ${_userModel.passwordEncrypted?string}, ${_userModel.passwordReset?string}, '${dataFactory.getDateString(_userModel.passwordModifiedDate)}', '${_userModel.digest}', '${_userModel.reminderQueryQuestion}', '${_userModel.reminderQueryAnswer}', ${_userModel.graceLoginCount}, '${_userModel.screenName}', '${_userModel.emailAddress}', ${_userModel.facebookId}, '${_userModel.googleUserId}', ${_userModel.ldapServerId}, '${_userModel.openId}', ${_userModel.portraitId}, '${_userModel.languageId}', '${_userModel.timeZoneId}', '${_userModel.greeting}', '${_userModel.comments}', '${_userModel.firstName}', '${_userModel.middleName}', '${_userModel.lastName}', '${_userModel.jobTitle}', '${dataFactory.getDateString(_userModel.loginDate)}', '${_userModel.loginIP}', '${dataFactory.getDateString(_userModel.lastLoginDate)}', '${_userModel.lastLoginIP}', '${dataFactory.getDateString(_userModel.lastFailedLoginDate)}', ${_userModel.failedLoginAttempts}, ${_userModel.lockout?string}, '${dataFactory.getDateString(_userModel.lockoutDate)}', ${_userModel.agreedToTermsOfUse?string}, ${_userModel.emailAddressVerified?string}, '${_userModel.status}');

	<#local contactModel = dataFactory.newContactModel(_userModel)>

	insert into Contact_ values (${contactModel.mvccVersion}, ${contactModel.contactId}, ${contactModel.companyId}, ${contactModel.userId}, '${contactModel.userName}', '${dataFactory.getDateString(contactModel.createDate)}', '${dataFactory.getDateString(contactModel.modifiedDate)}', ${contactModel.classNameId}, ${contactModel.classPK}, ${contactModel.accountId}, ${contactModel.parentContactId}, '${contactModel.emailAddress}', '${contactModel.firstName}', '${contactModel.middleName}', '${contactModel.lastName}', ${contactModel.prefixId}, ${contactModel.suffixId}, ${contactModel.male?string}, '${dataFactory.getDateString(contactModel.birthday)}', '${contactModel.smsSn}', '${contactModel.facebookSn}', '${contactModel.jabberSn}', '${contactModel.skypeSn}', '${contactModel.twitterSn}', '${contactModel.employeeStatusId}', '${contactModel.employeeNumber}', '${contactModel.jobTitle}', '${contactModel.jobClass}', '${contactModel.hoursOfOperation}');

	<#list _roleIds as roleId>
		insert into Users_Roles values (0, ${roleId}, ${_userModel.userId});
	</#list>

	<#list _groupIds as groupId>
		insert into Users_Groups values (0, ${groupId}, ${_userModel.userId});
	</#list>
</#macro>