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

<%@ include file="/init.jsp" %>

<%
String referringPortletResource = ParamUtil.getString(request, "referringPortletResource");

ArticleSearch articleSearchContainer = journalDisplayContext.getSearchContainer();

request.setAttribute("view.jsp-total", String.valueOf(articleSearchContainer.getTotal()));

request.setAttribute("view_entries.jsp-entryEnd", String.valueOf(articleSearchContainer.getEnd()));
request.setAttribute("view_entries.jsp-entryStart", String.valueOf(articleSearchContainer.getStart()));

String displayStyle = journalDisplayContext.getDisplayStyle();

String searchContainerId = ParamUtil.getString(request, "searchContainerId");
%>

<liferay-ui:search-container
	emptyResultsMessage="no-web-content-was-found"
	id="<%= searchContainerId %>"
	searchContainer="<%= articleSearchContainer %>"
>
	<liferay-ui:search-container-row
		className="Object"
		cssClass="entry-display-style"
		modelVar="object"
	>

		<%
		JournalArticle curArticle = null;
		JournalFolder curFolder = null;

		Object result = row.getObject();

		if (result instanceof JournalFolder) {
			curFolder = (JournalFolder)result;
		}
		else {
			curArticle = (JournalArticle)result;
		}
		%>

		<c:choose>
			<c:when test="<%= curArticle != null %>">

				<%
				Map<String, Object> rowData = new HashMap<String, Object>();

				if (journalDisplayContext.isShowEditActions()) {
					rowData.put("draggable", JournalArticlePermission.contains(permissionChecker, curArticle, ActionKeys.DELETE) || JournalArticlePermission.contains(permissionChecker, curArticle, ActionKeys.UPDATE));
				}

				rowData.put("title", HtmlUtil.escape(curArticle.getTitle(locale)));

				row.setData(rowData);

				row.setPrimaryKey(HtmlUtil.escape(curArticle.getArticleId()));

				PortletURL rowURL = null;

				if (journalDisplayContext.isShowEditActions() && JournalArticlePermission.contains(permissionChecker, curArticle, ActionKeys.UPDATE)) {
					rowURL = liferayPortletResponse.createRenderURL();

					rowURL.setParameter("mvcPath", "/edit_article.jsp");
					rowURL.setParameter("redirect", currentURL);
					rowURL.setParameter("referringPortletResource", referringPortletResource);
					rowURL.setParameter("groupId", String.valueOf(curArticle.getGroupId()));
					rowURL.setParameter("folderId", String.valueOf(curArticle.getFolderId()));
					rowURL.setParameter("articleId", curArticle.getArticleId());
					rowURL.setParameter("version", String.valueOf(curArticle.getVersion()));
				}
				%>

				<c:choose>
					<c:when test='<%= displayStyle.equals("descriptive") %>'>
						<liferay-ui:search-container-column-text>
							<liferay-ui:user-portrait
								cssClass="user-icon-lg"
								userId="<%= curArticle.getUserId() %>"
							/>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							colspan="<%= 2 %>"
						>

							<%
							Date createDate = curArticle.getModifiedDate();

							String modifiedDateDescription = LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - createDate.getTime(), true);
							%>

							<h6 class="text-default">
								<liferay-ui:message arguments="<%= new String[] {HtmlUtil.escape(curArticle.getUserName()), modifiedDateDescription} %>" key="x-modified-x-ago" />
							</h6>

							<h5>
								<aui:a href="<%= rowURL != null ? rowURL.toString() : null %>">
									<%= HtmlUtil.escape(curArticle.getTitle(locale)) %>
								</aui:a>
							</h5>

							<h6 class="text-default">
								<aui:workflow-status markupView="lexicon" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= curArticle.getStatus() %>" />
							</h6>
						</liferay-ui:search-container-column-text>

						<c:if test="<%= journalDisplayContext.isShowEditActions() %>">
							<liferay-ui:search-container-column-jsp
								path="/article_action.jsp"
							/>
						</c:if>
					</c:when>
					<c:when test='<%= displayStyle.equals("icon") %>'>

						<%
						row.setCssClass("entry-card lfr-asset-item " + row.getCssClass());
						%>

						<liferay-ui:search-container-column-text>

							<%
							String articleImageURL = curArticle.getArticleImageURL(themeDisplay);
							%>

							<c:choose>
								<c:when test="<%= Validator.isNotNull(articleImageURL) %>">
									<liferay-frontend:vertical-card
										actionJsp='<%= journalDisplayContext.isShowEditActions() ? "/article_action.jsp" : null %>'
										actionJspServletContext="<%= application %>"
										imageUrl="<%= articleImageURL %>"
										resultRow="<%= row %>"
										rowChecker="<%= articleSearchContainer.getRowChecker() %>"
										title="<%= curArticle.getTitle(locale) %>"
										url="<%= rowURL != null ? rowURL.toString() : null %>"
									>
										<%@ include file="/article_vertical_card.jspf" %>
									</liferay-frontend:vertical-card>
								</c:when>
								<c:otherwise>
									<liferay-frontend:icon-vertical-card
										actionJsp='<%= journalDisplayContext.isShowEditActions() ? "/article_action.jsp" : null %>'
										actionJspServletContext="<%= application %>"
										icon="web-content"
										resultRow="<%= row %>"
										rowChecker="<%= articleSearchContainer.getRowChecker() %>"
										title="<%= curArticle.getTitle(locale) %>"
										url="<%= rowURL != null ? rowURL.toString() : null %>"
									>
										<%@ include file="/article_vertical_card.jspf" %>
									</liferay-frontend:icon-vertical-card>
								</c:otherwise>
							</c:choose>
						</liferay-ui:search-container-column-text>
					</c:when>
					<c:otherwise>
						<liferay-ui:search-container-column-text
							name="id"
							value="<%= HtmlUtil.escape(curArticle.getArticleId()) %>"
						/>

						<liferay-ui:search-container-column-jsp
							cssClass="table-cell-content"
							href="<%= rowURL %>"
							name="title"
							path="/article_title.jsp"
						/>

						<liferay-ui:search-container-column-text
							cssClass="table-cell-content"
							name="description"
							value="<%= HtmlUtil.escape(curArticle.getDescription(locale)) %>"
						/>

						<liferay-ui:search-container-column-text
							name="author"
							value="<%= HtmlUtil.escape(PortalUtil.getUserName(curArticle)) %>"
						/>

						<liferay-ui:search-container-column-status
							name="status"
						/>

						<liferay-ui:search-container-column-date
							name="modified-date"
							value="<%= curArticle.getModifiedDate() %>"
						/>

						<liferay-ui:search-container-column-date
							name="display-date"
							value="<%= curArticle.getDisplayDate() %>"
						/>

						<%
						DDMStructure ddmStructure = DDMStructureLocalServiceUtil.getStructure(scopeGroupId, PortalUtil.getClassNameId(JournalArticle.class), curArticle.getDDMStructureKey(), true);
						%>

						<liferay-ui:search-container-column-text
							name="type"
							value="<%= HtmlUtil.escape(ddmStructure.getName(locale)) %>"
						/>

						<c:if test="<%= journalDisplayContext.isShowEditActions() %>">
							<liferay-ui:search-container-column-jsp
								path="/article_action.jsp"
							/>
						</c:if>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="<%= curFolder != null %>">

				<%
				Map<String, Object> rowData = new HashMap<String, Object>();

				rowData.put("draggable", JournalFolderPermission.contains(permissionChecker, curFolder, ActionKeys.DELETE) || JournalFolderPermission.contains(permissionChecker, curFolder, ActionKeys.UPDATE));
				rowData.put("folder", true);
				rowData.put("folder-id", curFolder.getFolderId());
				rowData.put("title", HtmlUtil.escape(curFolder.getName()));

				row.setData(rowData);
				row.setPrimaryKey(String.valueOf(curFolder.getPrimaryKey()));

				PortletURL rowURL = liferayPortletResponse.createRenderURL();

				rowURL.setParameter("redirect", currentURL);
				rowURL.setParameter("groupId", String.valueOf(curFolder.getGroupId()));
				rowURL.setParameter("folderId", String.valueOf(curFolder.getFolderId()));
				rowURL.setParameter("displayStyle", displayStyle);
				rowURL.setParameter("showEditActions", String.valueOf(journalDisplayContext.isShowEditActions()));
				%>

				<c:choose>
					<c:when test='<%= displayStyle.equals("descriptive") %>'>
						<liferay-ui:search-container-column-icon
							icon="folder"
							toggleRowChecker="<%= true %>"
						/>

						<liferay-ui:search-container-column-text
							colspan="<%= 2 %>"
						>

							<%
							Date createDate = curFolder.getCreateDate();

							String createDateDescription = LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - createDate.getTime(), true);
							%>

							<h6 class="text-default">
								<liferay-ui:message arguments="<%= new String[] {HtmlUtil.escape(curFolder.getUserName()), createDateDescription} %>" key="x-modified-x-ago" />
							</h6>

							<h5>
								<aui:a href="<%= rowURL != null ? rowURL.toString() : null %>">
									<%= curFolder.getName() %>
								</aui:a>
							</h5>

							<h6 class="text-default">
								<aui:workflow-status markupView="lexicon" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= curFolder.getStatus() %>" />
							</h6>
						</liferay-ui:search-container-column-text>

						<c:if test="<%= journalDisplayContext.isShowEditActions() %>">
							<liferay-ui:search-container-column-jsp
								path="/folder_action.jsp"
							/>
						</c:if>
					</c:when>
					<c:when test='<%= displayStyle.equals("icon") %>'>

						<%
						row.setCssClass("entry-card lfr-asset-folder " + row.getCssClass());
						%>

						<liferay-ui:search-container-column-text colspan="<%= 2 %>">
							<liferay-frontend:horizontal-card
								actionJsp='<%= journalDisplayContext.isShowEditActions() ? "/folder_action.jsp" : null %>'
								actionJspServletContext="<%= application %>"
								resultRow="<%= row %>"
								rowChecker="<%= articleSearchContainer.getRowChecker() %>"
								text="<%= HtmlUtil.escape(curFolder.getName()) %>"
								url="<%= rowURL.toString() %>"
							>
								<liferay-frontend:horizontal-card-col>
									<liferay-frontend:horizontal-card-icon
										icon="folder"
									/>
								</liferay-frontend:horizontal-card-col>
							</liferay-frontend:horizontal-card>
						</liferay-ui:search-container-column-text>
					</c:when>
					<c:otherwise>
						<liferay-ui:search-container-column-text
							name="id"
							value="<%= String.valueOf(curFolder.getFolderId()) %>"
						/>

						<liferay-ui:search-container-column-text
							cssClass="table-cell-content"
							href="<%= rowURL.toString() %>"
							name="title"
							value="<%= HtmlUtil.escape(curFolder.getName()) %>"
						/>

						<liferay-ui:search-container-column-text
							cssClass="table-cell-content"
							name="description"
							value="<%= HtmlUtil.escape(curFolder.getDescription()) %>"
						/>

						<liferay-ui:search-container-column-text
							name="author"
							value="<%= HtmlUtil.escape(PortalUtil.getUserName(curFolder)) %>"
						/>

						<liferay-ui:search-container-column-text
							name="status"
							value="--"
						/>

						<liferay-ui:search-container-column-date
							name="modified-date"
							value="<%= curFolder.getModifiedDate() %>"
						/>

						<liferay-ui:search-container-column-text
							name="display-date"
							value="--"
						/>

						<liferay-ui:search-container-column-text
							name="type"
							value='<%= LanguageUtil.get(request, "folder") %>'
						/>

						<c:if test="<%= journalDisplayContext.isShowEditActions() %>">
							<liferay-ui:search-container-column-jsp
								path="/folder_action.jsp"
							/>
						</c:if>
					</c:otherwise>
				</c:choose>
			</c:when>
		</c:choose>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator displayStyle="<%= displayStyle %>" markupView="lexicon" resultRowSplitter="<%= journalDisplayContext.isSearch() ? null : new JournalResultRowSplitter() %>" searchContainer="<%= articleSearchContainer %>" />
</liferay-ui:search-container>