<#assign layoutModel = dataFactory.newLayoutModel(dataFactory.guestGroupModel.groupId, "welcome", "com_liferay_login_web_portlet_LoginPortlet,", "com_liferay_hello_world_web_portlet_HelloWorldPortlet,")>

<@insertLayout
	_layoutModel = layoutModel
/>

<@insertGroup
	_groupModel = dataFactory.globalGroupModel
	_publicPageCount = 1
/>

<@insertGroup
	_groupModel = dataFactory.guestGroupModel
	_publicPageCount = 1
/>

<#list dataFactory.groupModels as groupModel>
	<#assign groupId = groupModel.groupId>

	<#include "asset_publisher.ftl">

	<#include "blogs.ftl">

	<#include "ddl.ftl">

	<#include "journal_article.ftl">

	<#include "mb.ftl">

	<#include "users.ftl">

	<#include "wiki.ftl">

	<@insertDLFolder
		_ddmStructureId = dataFactory.defaultDLDDMStructureId
		_dlFolderDepth = 1
		_groupId = groupId
		_parentDLFolderId = 0
	/>

	<#assign publicLayoutModels = dataFactory.newPublicLayoutModels(groupId)>

	<#list publicLayoutModels as publicLayoutModel>
		<@insertLayout
			_layoutModel = publicLayoutModel
		/>
	</#list>

	<#assign publicPageCount = publicLayoutModels?size + dataFactory.maxDDLRecordSetCount + dataFactory.maxJournalArticleCount>

	<@insertGroup
		_groupModel = groupModel
		_publicPageCount = publicPageCount
	/>

	${repositoryCSVWriter.write(groupId + ", " + groupModel.name + "\n")}
</#list>