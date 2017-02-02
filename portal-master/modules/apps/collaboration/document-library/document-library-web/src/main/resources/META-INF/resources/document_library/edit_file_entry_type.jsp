<%--
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
--%>

<%@ include file="/document_library/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

DLFileEntryType fileEntryType = (DLFileEntryType)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_ENTRY_TYPE);

long fileEntryTypeId = BeanParamUtil.getLong(fileEntryType, request, "fileEntryTypeId");

DDMStructure ddmStructure = (DDMStructure)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_DYNAMIC_DATA_MAPPING_STRUCTURE);

long ddmStructureId = BeanParamUtil.getLong(ddmStructure, request, "structureId");

String script = BeanParamUtil.getString(ddmStructure, request, "definition");

JSONArray fieldsJSONArray = DDMUtil.getDDMFormFieldsJSONArray(DDMStructureLocalServiceUtil.fetchDDMStructure(ddmStructureId), script);

List<DDMStructure> ddmStructures = null;

if (fileEntryType != null) {
	ddmStructures = fileEntryType.getDDMStructures();

	if (ddmStructure != null) {
		ddmStructures = new ArrayList<DDMStructure>(ddmStructures);

		ddmStructures.remove(ddmStructure);
	}
}

DDMDisplay ddmDisplay = new DLDDMDisplay();

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle((fileEntryType == null) ? LanguageUtil.get(request, "new-document-type") : fileEntryType.getName(locale));
%>

<div class="container-fluid-1280">
	<liferay-util:buffer var="removeStructureIcon">
		<liferay-ui:icon
			iconCssClass="icon-remove"
			label="<%= true %>"
			message="remove"
		/>
	</liferay-util:buffer>

	<portlet:actionURL name="/document_library/edit_file_entry_type" var="editFileEntryTypeURL">
		<portlet:param name="mvcRenderCommandName" value="/document_library/edit_file_entry_type" />
	</portlet:actionURL>

	<aui:form action="<%= editFileEntryTypeURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (fileEntryType == null) ? Constants.ADD : Constants.UPDATE %>" />
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="fileEntryTypeId" type="hidden" value="<%= fileEntryTypeId %>" />
		<aui:input name="ddmStructureId" type="hidden" value="<%= ddmStructureId %>" />
		<aui:input name="definition" type="hidden" />

		<liferay-ui:error exception="<%= DuplicateFileEntryTypeException.class %>" message="please-enter-a-unique-document-type-name" />
		<liferay-ui:error exception="<%= NoSuchMetadataSetException.class %>" message="please-enter-a-valid-metadata-set-or-enter-a-metadata-field" />
		<liferay-ui:error exception="<%= StorageFieldRequiredException.class %>" message="please-fill-out-all-required-fields" />
		<liferay-ui:error exception="<%= StructureDefinitionException.class %>" message="please-enter-a-valid-definition" />
		<liferay-ui:error exception="<%= StructureDuplicateElementException.class %>" message="please-enter-unique-metadata-field-names-(including-field-names-inherited-from-the-parent)" />
		<liferay-ui:error exception="<%= StructureNameException.class %>" message="please-enter-a-valid-name" />

		<aui:model-context bean="<%= fileEntryType %>" model="<%= DLFileEntryType.class %>" />

		<aui:fieldset-group cssClass="edit-file-entry-type" markupView="lexicon">
			<aui:fieldset collapsible="<%= true %>" extended="<%= false %>" id="detailsMetadataFields" persistState="<%= true %>" title="details">
				<aui:input name="name" />

				<aui:input name="description" />
			</aui:fieldset>

			<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" id="mainMetadataFields" label="main-metadata-fields">
				<liferay-util:include page="/form_builder.jsp" portletId="<%= PortletProviderUtil.getPortletId(com.liferay.dynamic.data.mapping.model.DDMStructure.class.getName(), PortletProvider.Action.VIEW) %>">
					<portlet:param name="refererPortletName" value="<%= DLPortletKeys.DOCUMENT_LIBRARY %>" />
					<portlet:param name="portletResourceNamespace" value="<%= renderResponse.getNamespace() %>" />
					<portlet:param name="script" value="<%= script %>" />
					<portlet:param name="fieldsJSONArrayString" value="<%= (fieldsJSONArray != null) ? fieldsJSONArray.toString() : StringPool.BLANK %>" />
					<portlet:param name="className" value="<%= ddmDisplay.getStructureType() %>" />
					<portlet:param name="storageType" value="<%= ddmDisplay.getStorageType() %>" />
					<portlet:param name="scopeAvailableFields" value="<%= ddmDisplay.getAvailableFields() %>" />
				</liferay-util:include>
			</aui:fieldset>

			<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" id="additionalMetadataFields" label="additional-metadata-fields">
				<liferay-ui:search-container
					headerNames="name,null"
					total="<%= (ddmStructures != null) ? ddmStructures.size() : 0 %>"
				>
					<liferay-ui:search-container-results
						results="<%= ddmStructures %>"
					/>

					<liferay-ui:search-container-row
						className="com.liferay.dynamic.data.mapping.kernel.DDMStructure"
						escapedModel="<%= true %>"
						keyProperty="structureId"
						modelVar="curDDMStructure"
					>
						<liferay-ui:search-container-column-text
							name="name"
							value="<%= HtmlUtil.escape(curDDMStructure.getName(locale)) %>"
						/>

						<liferay-ui:search-container-column-text>
							<a class="modify-link" data-rowId="<%= curDDMStructure.getStructureId() %>" href="javascript:;"><%= removeStructureIcon %></a>
						</liferay-ui:search-container-column-text>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator markupView="lexicon" paginate="<%= false %>" />
				</liferay-ui:search-container>

				<liferay-ui:icon
					cssClass="modify-link select-metadata"
					iconCssClass="icon-search"
					label="<%= true %>"
					linkCssClass="btn btn-default"
					message="select-metadata-set"
					url='<%= "javascript:" + renderResponse.getNamespace() + "openDDMStructureSelector();" %>'
				/>
			</aui:fieldset>

			<c:if test="<%= fileEntryType == null %>">
				<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="permissions" markupView="lexicon">
					<liferay-ui:input-permissions
						modelName="<%= DLFileEntryType.class.getName() %>"
					/>
				</aui:fieldset>
			</c:if>
		</aui:fieldset-group>
	</aui:form>

	<aui:button-row>
		<aui:button cssClass="btn-lg" onClick='<%= renderResponse.getNamespace() + "saveStructure();" %>' type="submit" />

		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</div>

<aui:script>
	function <portlet:namespace />openDDMStructureSelector() {
		Liferay.Util.openDDMPortlet(
			{
				basePortletURL: '<%= PortletURLFactoryUtil.create(request, PortletProviderUtil.getPortletId(com.liferay.dynamic.data.mapping.model.DDMStructure.class.getName(), PortletProvider.Action.VIEW), PortletRequest.RENDER_PHASE) %>',
				classPK: '<%= ddmStructureId %>',
				dialog: {
					destroyOnHide: true
				},
				eventName: '<portlet:namespace />selectDDMStructure',
				mvcPath: '/select_structure.jsp',
				navigationStartsOn: '<%= DDMNavigationHelper.SELECT_STRUCTURE %>',
				refererPortletName: '<%= DLPortletKeys.DOCUMENT_LIBRARY %>',
				showAncestorScopes: true,
				showManageTemplates: false,
				title: '<%= UnicodeLanguageUtil.get(request, "metadata-sets") %>'
			},
			function(event) {
				var A = AUI();

				var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />ddmStructuresSearchContainer');

				var ddmStructureLink = '<a class="modify-link" data-rowId="' + event.ddmstructureid + '" href="javascript:;"><%= UnicodeFormatter.toString(removeStructureIcon) %></a>';

				searchContainer.addRow([event.name, ddmStructureLink], event.ddmstructureid);

				searchContainer.updateDataStore();
			}
		);
	}

	Liferay.provide(
		window,
		'<portlet:namespace />saveStructure',
		function() {
			document.<portlet:namespace />fm.<portlet:namespace />definition.value = window.<portlet:namespace />formBuilder.getContentValue();

			submitForm(document.<portlet:namespace />fm);
		},
		['liferay-portlet-dynamic-data-mapping']
	);
</aui:script>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />ddmStructuresSearchContainer');

	searchContainer.get('contentBox').delegate(
		'click',
		function(event) {
			var link = event.currentTarget;

			var tr = link.ancestor('tr');

			searchContainer.deleteRow(tr, link.getAttribute('data-rowId'));
		},
		'.modify-link'
	);
</aui:script>

<%
if (fileEntryType == null) {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "add-document-type"), currentURL);
}
else {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "edit-document-type"), currentURL);
}
%>