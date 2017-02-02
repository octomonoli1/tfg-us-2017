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

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />
	<aui:input name="oldLayoutTemplateId" type="hidden" value="<%= nestedPortletsDisplayContext.getLayoutTemplateId() %>" />

	<div class="portlet-configuration-body-content">
		<div class="container-fluid-1280">
			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset cssClass="display-style-icon">
					<h4><liferay-ui:message key="layout-template" /></h4>

					<%
					String layoutTemplateId = nestedPortletsDisplayContext.getLayoutTemplateId();

					for (LayoutTemplate layoutTemplate : nestedPortletsDisplayContext.getLayoutTemplates()) {
					%>

						<div class="entry-card lfr-asset-item">
							<div class="radio radio-card radio-top-left">
								<label>
									<aui:input checked="<%= layoutTemplateId.equals(layoutTemplate.getLayoutTemplateId()) %>" label="" name="preferences--layoutTemplateId--" type="radio" value="<%= layoutTemplate.getLayoutTemplateId() %>" />

									<div class="card">
										<div class="aspect-ratio aspect-ratio-bg-cover" style="background-image:url('<%= layoutTemplate.getStaticResourcePath() + HtmlUtil.escapeAttribute(layoutTemplate.getThumbnailPath()) %>')">
										</div>

										<div class="card-row card-row-padded">
											<div class="card-col-field">
												<%= layoutTemplate.getName(locale) %>
											</div>
										</div>
									</div>
								</label>
							</div>
						</div>

					<%
					}
					%>

				</aui:fieldset>
			</aui:fieldset-group>
		</div>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>