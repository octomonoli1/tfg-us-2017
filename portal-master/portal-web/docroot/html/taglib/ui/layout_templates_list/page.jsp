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

<%@ include file="/html/taglib/init.jsp" %>

<%
String layoutTemplateId = (String)request.getAttribute("liferay-ui:layout-templates-list:layoutTemplateId");
String layoutTemplateIdPrefix = (String)request.getAttribute("liferay-ui:layout-templates-list:layoutTemplateIdPrefix");
List<LayoutTemplate> layoutTemplates = (List<LayoutTemplate>)request.getAttribute("liferay-ui:layout-templates-list:layoutTemplates");
%>

<div class="lfr-page-layouts row">
	<ul class="list-unstyled">

		<%
		layoutTemplates = PluginUtil.restrictPlugins(layoutTemplates, user);

		for (int i = 0; i < layoutTemplates.size(); i++) {
			LayoutTemplate layoutTemplate = layoutTemplates.get(i);
		%>

			<li class="col-lg-3 col-md-4 col-sm-6 lfr-layout-template">
				<div class="checkbox-card">
					<label for="<portlet:namespace /><%= layoutTemplateIdPrefix + "layoutTemplateId" + i %>">
						<aui:input checked="<%= layoutTemplateId.equals(layoutTemplate.getLayoutTemplateId()) %>" cssClass="hide" id='<%= layoutTemplateIdPrefix + "layoutTemplateId" + i %>' label="" name="layoutTemplateId" type="radio" value="<%= layoutTemplate.getLayoutTemplateId() %>" wrappedField="<%= true %>" />

						<div class="card card-horizontal">
							<div class="card-row card-row-padded">
								<div class="card-col-field">
									<img alt="" class="layout-template-entry modify-link <%= layoutTemplateId.equals(layoutTemplate.getLayoutTemplateId()) ? "layout-selected" : StringPool.BLANK %>" height="28" onclick="document.getElementById('<portlet:namespace /><%= layoutTemplateIdPrefix + "layoutTemplateId" + i %>').checked = true;" src="<%= layoutTemplate.getStaticResourcePath() %><%= HtmlUtil.escapeAttribute(layoutTemplate.getThumbnailPath()) %>" width="28" />
								</div>

								<div class="card-col-content card-col-gutters clamp-horizontal">
									<div class="clamp-container">
										<span class="truncate-text" title=""><%= HtmlUtil.escape(layoutTemplate.getName(locale)) %></span>
									</div>
								</div>
							</div>
						</div>
					</label>
				</div>
			</li>

		<%
		}
		%>

	</ul>
</div>