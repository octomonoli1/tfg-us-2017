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

long breadcrumbsFolderId = ParamUtil.getLong(request, "breadcrumbsFolderId");

long repositoryId = ParamUtil.getLong(request, "repositoryId");

if (repositoryId == 0) {
	repositoryId = scopeGroupId;
}

long searchRepositoryId = ParamUtil.getLong(request, "searchRepositoryId");

if (searchRepositoryId == 0) {
	searchRepositoryId = scopeGroupId;
}

long folderId = ParamUtil.getLong(request, "folderId");

long searchFolderId = ParamUtil.getLong(request, "searchFolderId");

Folder folder = null;

if (searchFolderId > 0) {
	folder = DLAppServiceUtil.getFolder(searchFolderId);
}

List<Folder> mountFolders = DLAppServiceUtil.getMountFolders(scopeGroupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

String keywords = ParamUtil.getString(request, "keywords");

boolean showRepositoryTabs = ParamUtil.getBoolean(request, "showRepositoryTabs");
boolean showSearchInfo = ParamUtil.getBoolean(request, "showSearchInfo");

PortletURL portletURL = liferayPortletResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/document_library/search");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("breadcrumbsFolderId", String.valueOf(breadcrumbsFolderId));
portletURL.setParameter("searchFolderId", String.valueOf(searchFolderId));
portletURL.setParameter("keywords", keywords);

SearchContainer searchContainer = new SearchContainer(liferayPortletRequest, portletURL, null, null);
%>

<aui:input name="repositoryId" type="hidden" value="<%= repositoryId %>" />
<aui:input name="searchRepositoryId" type="hidden" value="<%= searchRepositoryId %>" />

<c:if test="<%= showSearchInfo %>">
	<liferay-util:buffer var="searchInfo">
		<div class="search-info">
			<span class="keywords">

				<%
				boolean searchEverywhere = false;

				if ((folder == null) || (folder.getFolderId() == rootFolderId)) {
					searchEverywhere = true;
				}
				%>

				<c:choose>
					<c:when test="<%= !searchEverywhere %>">
						<liferay-ui:message arguments="<%= new Object[] {HtmlUtil.escape(keywords), HtmlUtil.escape(folder.getName())} %>" key="searched-for-x-in-x" translateArguments="<%= false %>" />
					</c:when>
					<c:otherwise>
						<liferay-ui:message arguments="<%= HtmlUtil.escape(keywords) %>" key="searched-for-x-everywhere" translateArguments="<%= false %>" />
					</c:otherwise>
				</c:choose>
			</span>

			<c:if test="<%= folderId != rootFolderId %>">
				<span class="change-search-folder">
					<portlet:renderURL var="changeSearchFolderURL">
						<portlet:param name="mvcRenderCommandName" value="/document_library/search" />
						<portlet:param name="repositoryId" value="<%= String.valueOf(repositoryId) %>" />
						<portlet:param name="searchRepositoryId" value="<%= !searchEverywhere ? String.valueOf(scopeGroupId) : String.valueOf(repositoryId) %>" />
						<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
						<portlet:param name="searchFolderId" value="<%= !searchEverywhere ? String.valueOf(DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) : String.valueOf(folderId) %>" />
						<portlet:param name="keywords" value="<%= keywords %>" />
						<portlet:param name="showRepositoryTabs" value="<% (searchEverywhere) ? Boolean.TRUE.toString() : Boolean.FALSE.toString() %>" />
						<portlet:param name="showSearchInfo" value="<%= Boolean.TRUE.toString() %>" />
					</portlet:renderURL>

					<aui:button href="<%= changeSearchFolderURL %>" value='<%= !searchEverywhere ? "search-everywhere" : "search-in-the-current-folder" %>' />
				</span>
			</c:if>

			<portlet:renderURL var="closeSearchURL">
				<portlet:param name="mvcRenderCommandName" value="/document_library/view" />
			</portlet:renderURL>

			<liferay-ui:icon cssClass="close-search" iconCssClass="icon-remove" id="closeSearch" message="remove" url="<%= closeSearchURL %>" />
		</div>

		<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
			<aui:script>
				Liferay.Util.focusFormField(document.getElementById('<portlet:namespace />keywords'));
			</aui:script>
		</c:if>
	</liferay-util:buffer>

	<div id="<portlet:namespace />searchInfo">
		<%= searchInfo %>
	</div>
</c:if>

<liferay-util:buffer var="searchResults">
	<liferay-portlet:renderURL varImpl="searchURL">
		<portlet:param name="mvcRenderCommandName" value="/document_library/search" />
	</liferay-portlet:renderURL>

	<div class="document-container" id="<portlet:namespace />entriesContainer">
		<aui:form action="<%= searchURL %>" method="get" name="fm">
			<liferay-portlet:renderURLParams varImpl="searchURL" />
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
			<aui:input name="breadcrumbsFolderId" type="hidden" value="<%= breadcrumbsFolderId %>" />
			<aui:input name="searchFolderId" type="hidden" value="<%= searchFolderId %>" />

			<%
			try {
				SearchContext searchContext = SearchContextFactory.getInstance(request);

				searchContext.setAttribute("paginationType", "regular");
				searchContext.setAttribute("searchRepositoryId", searchRepositoryId);
				searchContext.setEnd(searchContainer.getEnd());
				searchContext.setFolderIds(new long[] {searchFolderId});
				searchContext.setIncludeDiscussions(true);
				searchContext.setKeywords(keywords);

				QueryConfig queryConfig = new QueryConfig();

				queryConfig.setSearchSubfolders(true);

				searchContext.setQueryConfig(queryConfig);

				searchContext.setStart(searchContainer.getStart());

				Hits hits = DLAppServiceUtil.search(searchRepositoryId, searchContext);

				searchContainer.setTotal(hits.getLength());

				List<SearchResult> searchResults = SearchResultUtil.getSearchResults(hits, locale);

				for (int i = 0; i < searchResults.size(); i++) {
					SearchResult searchResult = searchResults.get(i);

					Summary summary = searchResult.getSummary();

					FileEntry fileEntry = null;
					Folder curFolder = null;

					String className = searchResult.getClassName();

					if (className.equals(DLFileEntry.class.getName()) || FileEntry.class.isAssignableFrom(Class.forName(className))) {
						fileEntry = DLAppLocalServiceUtil.getFileEntry(searchResult.getClassPK());
					}
					else if (className.equals(DLFolder.class.getName())) {
						curFolder = DLAppLocalServiceUtil.getFolder(searchResult.getClassPK());
					}
			%>

					<c:choose>
						<c:when test="<%= (fileEntry != null) && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.VIEW) %>">

							<%
							PortletURL tempRowURL = liferayPortletResponse.createRenderURL();

							tempRowURL.setParameter("mvcRenderCommandName", "/document_library/view_file_entry");
							tempRowURL.setParameter("redirect", HttpUtil.removeParameter(currentURL, liferayPortletResponse.getNamespace() + "ajax"));
							tempRowURL.setParameter("fileEntryId", String.valueOf(fileEntry.getFileEntryId()));

							FileVersion latestFileVersion = fileEntry.getFileVersion();

							if ((user.getUserId() == fileEntry.getUserId()) || permissionChecker.isContentReviewer(user.getCompanyId(), scopeGroupId) || DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE)) {
								latestFileVersion = fileEntry.getLatestFileVersion();
							}

							request.setAttribute("info_panel.jsp-fileEntry", fileEntry);
							%>

							<liferay-ui:app-view-search-entry
								actionJsp="/document_library/file_entry_action.jsp"
								actionJspServletContext="<%= application %>"
								commentRelatedSearchResults="<%= searchResult.getCommentRelatedSearchResults() %>"
								containerName="<%= DLUtil.getAbsolutePath(liferayPortletRequest, fileEntry.getFolderId()) %>"
								cssClass='<%= MathUtil.isEven(i) ? "alt" : StringPool.BLANK %>'
								description="<%= ((summary != null) && Validator.isNotNull(summary.getContent())) ? summary.getContent() : fileEntry.getDescription() %>"
								locked="<%= fileEntry.isCheckedOut() %>"
								queryTerms="<%= hits.getQueryTerms() %>"
								rowCheckerId="<%= String.valueOf(fileEntry.getFileEntryId()) %>"
								rowCheckerName="<%= FileEntry.class.getSimpleName() %>"
								showCheckbox="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.DELETE) || DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE) %>"
								status="<%= latestFileVersion.getStatus() %>"
								thumbnailSrc="<%= DLUtil.getThumbnailSrc(fileEntry, themeDisplay) %>"
								title="<%= ((summary != null) && Validator.isNotNull(summary.getTitle())) ? summary.getTitle() : fileEntry.getTitle() %>"
								url="<%= tempRowURL.toString() %>"
							/>
						</c:when>
						<c:when test="<%= (curFolder != null) && DLFolderPermission.contains(permissionChecker, curFolder, ActionKeys.VIEW) %>">

							<%
							int status = WorkflowConstants.STATUS_APPROVED;

							if (permissionChecker.isContentReviewer(user.getCompanyId(), curFolder.getGroupId())) {
								status = WorkflowConstants.STATUS_ANY;
							}

							String folderImage = "folder_empty_document";

							if (PropsValues.DL_FOLDER_ICON_CHECK_COUNT && (DLAppServiceUtil.getFoldersAndFileEntriesAndFileShortcutsCount(curFolder.getRepositoryId(), curFolder.getFolderId(), status, true) > 0)) {
								folderImage = "folder_full_document";
							}

							PortletURL tempRowURL = liferayPortletResponse.createRenderURL();

							tempRowURL.setParameter("mvcRenderCommandName", "/document_library/view_folder");
							tempRowURL.setParameter("redirect", HttpUtil.removeParameter(currentURL, liferayPortletResponse.getNamespace() + "ajax"));
							tempRowURL.setParameter("folderId", String.valueOf(curFolder.getFolderId()));

							request.setAttribute("info_panel.jsp-folder", curFolder);

							request.setAttribute("view.jsp-repositoryId", String.valueOf(curFolder.getRepositoryId()));
							%>

							<liferay-ui:app-view-search-entry
								actionJsp="/document_library/folder_action.jsp"
								actionJspServletContext="<%= application %>"
								containerName="<%= DLUtil.getAbsolutePath(liferayPortletRequest, curFolder.getParentFolderId()) %>"
								cssClass='<%= MathUtil.isEven(i) ? "alt" : StringPool.BLANK %>'
								description="<%= ((summary != null) && Validator.isNotNull(summary.getContent())) ? summary.getContent() : curFolder.getDescription() %>"
								queryTerms="<%= hits.getQueryTerms() %>"
								rowCheckerId="<%= String.valueOf(curFolder.getFolderId()) %>"
								rowCheckerName="<%= Folder.class.getSimpleName() %>"
								showCheckbox="<%= DLFolderPermission.contains(permissionChecker, curFolder, ActionKeys.DELETE) || DLFolderPermission.contains(permissionChecker, curFolder, ActionKeys.UPDATE) %>"
								thumbnailSrc='<%= themeDisplay.getPathThemeImages() + "/file_system/large/" + folderImage + ".png" %>'
								title="<%= ((summary != null) && Validator.isNotNull(summary.getTitle())) ? summary.getTitle() : curFolder.getName() %>"
								url="<%= tempRowURL.toString() %>"
							/>
						</c:when>
						<c:otherwise>
							<div style="float: left; margin: 100px 10px 0;">
								<i class="icon-ban-circle"></i>
							</div>
						</c:otherwise>
					</c:choose>

				<%
				}
				%>

				<c:if test="<%= searchResults.isEmpty() %>">
					<div class="alert alert-info">
						<liferay-ui:message arguments='<%= "<strong>" + HtmlUtil.escape(keywords) + "</strong>" %>' key="no-documents-were-found-that-matched-the-keywords-x" translateArguments="<%= false %>" />
					</div>
				</c:if>

			<%
			}
			catch (Exception e) {
				_log.error(e, e);
			}
			%>

		</aui:form>
	</div>

</liferay-util:buffer>

<c:choose>
	<c:when test="<%= showRepositoryTabs %>">

		<%
		PortletURL searchRepositoryURL = liferayPortletResponse.createRenderURL();

		searchRepositoryURL.setParameter("mvcRenderCommandName", "/document_library/search");
		searchRepositoryURL.setParameter("repositoryId", String.valueOf(scopeGroupId));
		searchRepositoryURL.setParameter("searchRepositoryId", String.valueOf(scopeGroupId));
		searchRepositoryURL.setParameter("keywords", keywords);
		searchRepositoryURL.setParameter("showRepositoryTabs", Boolean.TRUE.toString());
		searchRepositoryURL.setParameter("showSearchInfo", Boolean.TRUE.toString());

		String[] tabsUrls = new String[] {searchRepositoryURL.toString()};

		String selectedTab = LanguageUtil.get(request, "local");

		for (Folder mountFolder : mountFolders) {
			if (mountFolder.getRepositoryId() == searchRepositoryId) {
				selectedTab = HtmlUtil.escape(mountFolder.getName());
			}

			searchRepositoryURL.setParameter("repositoryId", String.valueOf(mountFolder.getRepositoryId()));
			searchRepositoryURL.setParameter("searchRepositoryId", String.valueOf(mountFolder.getRepositoryId()));

			tabsUrls = ArrayUtil.append(tabsUrls, searchRepositoryURL.toString());
		}
		%>

		<div class="search-results-container" id="<portlet:namespace />searchResultsContainer">
			<liferay-ui:tabs
				names='<%= LanguageUtil.get(request, "local") + "," + HtmlUtil.escape(ListUtil.toString(mountFolders, "name")) %>'
				refresh="<%= false %>"
				urls="<%= tabsUrls %>"
				value="<%= selectedTab %>"
			>
				<liferay-ui:section>
					<div class="local-search-results" data-repositoryId="<%= scopeGroupId %>" <%= scopeGroupId == searchRepositoryId ? "data-searchProcessed" : "" %> id="<portlet:namespace />searchResultsContainer<%= scopeGroupId %>">
						<c:choose>
							<c:when test="<%= scopeGroupId == searchRepositoryId %>">
								<%= searchResults %>
							</c:when>
							<c:otherwise>
								<div class="alert alert-info">
									<liferay-ui:message key="searching,-please-wait" />
								</div>

								<div class="loading-animation"></div>
							</c:otherwise>
						</c:choose>
					</div>
				</liferay-ui:section>

				<%
				for (Folder mountFolder : mountFolders) {
				%>

					<liferay-ui:section>
						<div data-repositoryId="<%= mountFolder.getRepositoryId() %>" <%= mountFolder.getRepositoryId() == searchRepositoryId ? "data-searchProcessed" : "" %> id="<portlet:namespace />searchResultsContainer<%= mountFolder.getRepositoryId() %>">
							<c:choose>
								<c:when test="<%= mountFolder.getRepositoryId() == searchRepositoryId %>">
									<%= searchResults %>
								</c:when>
								<c:otherwise>
									<div class="alert alert-info">
										<liferay-ui:message key="searching,-please-wait" />
									</div>

									<div class="loading-animation"></div>
								</c:otherwise>
							</c:choose>
						</div>
					</liferay-ui:section>

				<%
				}
				%>

			</liferay-ui:tabs>
		</div>
	</c:when>
	<c:otherwise>
		<div class="repository-search-results" data-repositoryId="<%= searchRepositoryId %>" id="<%= liferayPortletResponse.getNamespace() + "searchResultsContainer" + searchRepositoryId %>">
			<%= searchResults %>
		</div>
	</c:otherwise>
</c:choose>

<%
request.setAttribute("view.jsp-folderId", String.valueOf(folderId));
%>

<div class="document-entries-pagination">
	<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
</div>

<%!
private static Log _log = LogFactoryUtil.getLog("com_liferay_document_library_web.document_library.search_resources_jsp");
%>