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
String redirect = ParamUtil.getString(request, "redirect");
String returnToFullPageURL = ParamUtil.getString(request, "returnToFullPageURL");

List<PublicRenderParameterConfiguration> publicRenderParameterConfigurations = (List<PublicRenderParameterConfiguration>)request.getAttribute(WebKeys.PUBLIC_RENDER_PARAMETER_CONFIGURATIONS);
Set<PublicRenderParameter> publicRenderParameters = (Set<PublicRenderParameter>)request.getAttribute(WebKeys.PUBLIC_RENDER_PARAMETERS);

PortletURL editPublicRenderParameterURL = renderResponse.createRenderURL();

editPublicRenderParameterURL.setParameter("mvcPath", "/edit_public_render_parameters.jsp");
editPublicRenderParameterURL.setParameter("redirect", redirect);
editPublicRenderParameterURL.setParameter("returnToFullPageURL", returnToFullPageURL);
editPublicRenderParameterURL.setParameter("portletResource", portletResource);
%>

<portlet:actionURL name="editPublicRenderParameters" var="editPublicRenderParametersURL">
	<portlet:param name="mvcPath" value="/edit_public_render_parameters.jsp" />
	<portlet:param name="portletConfiguration" value="<%= Boolean.TRUE.toString() %>" />
</portlet:actionURL>

<div class="portlet-configuration-edit-communications">
	<aui:form action="<%= editPublicRenderParametersURL %>" cssClass="form" method="post" name="fm">
		<aui:input name="redirect" type="hidden" value="<%= editPublicRenderParameterURL.toString() %>" />
		<aui:input name="returnToFullPageURL" type="hidden" value="<%= returnToFullPageURL %>" />
		<aui:input name="portletResource" type="hidden" value="<%= portletResource %>" />

		<div class="portlet-configuration-body-content">
			<liferay-util:include page="/tabs1.jsp" servletContext="<%= application %>">
				<liferay-util:param name="tabs1" value="communication" />
			</liferay-util:include>

			<div class="container-fluid-1280">
				<liferay-ui:error key="duplicateMapping" message="several-shared-parameters-are-mapped-to-the-same-parameter" />

				<div class="alert alert-info">
					<liferay-ui:message arguments='<%= "http://www.liferay.com/community/wiki/-/wiki/Main/Portlet+Communication+Configuration" %>' key="set-up-the-communication-among-the-portlets-that-use-public-render-parameters" translateArguments="<%= false %>" />
				</div>

				<liferay-ui:search-container
					total="<%= publicRenderParameterConfigurations.size() %>"
				>
					<liferay-ui:search-container-results
						results="<%= ListUtil.subList(publicRenderParameterConfigurations, searchContainer.getStart(), searchContainer.getEnd()) %>"
					/>

					<liferay-ui:search-container-row
						className="PublicRenderParameterConfiguration"
						modelVar="publicRenderParameterConfiguration"
					>
						<liferay-ui:search-container-column-text
							name="shared-parameter"
							value="<%= HtmlUtil.escape(publicRenderParameterConfiguration.getPublicRenderParameter().getIdentifier()) %>"
						/>

						<liferay-ui:search-container-column-text
							name="ignore"
						>
							<aui:input label="" name="<%= publicRenderParameterConfiguration.getIgnoreKey() %>" type="checkbox" value="<%= publicRenderParameterConfiguration.getIgnoreValue() %>" />
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							name="read-value-from-parameter"
						>
							<aui:select label="" name="<%= publicRenderParameterConfiguration.getMappingKey() %>">
								<aui:option label="<%= HtmlUtil.escape(publicRenderParameterConfiguration.getPublicRenderParameter().getIdentifier()) %>" value="" />

								<%
								for (PublicRenderParameter publicRenderParameter : publicRenderParameters) {
									String publicRenderParameterName = PortletQNameUtil.getPublicRenderParameterName(publicRenderParameter.getQName());

									if (publicRenderParameterName.equals(publicRenderParameterConfiguration.getPublicRenderParameterName())) {
										continue;
									}
								%>

									<aui:option label="<%= HtmlUtil.escape(publicRenderParameter.getIdentifier()) %>" selected="<%= publicRenderParameterName.equals(publicRenderParameterConfiguration.getMappingValue()) %>" value="<%= publicRenderParameterName %>" />

								<%
								}
								%>

							</aui:select>
						</liferay-ui:search-container-column-text>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator markupView="lexicon" paginate="<%= false %>" />
				</liferay-ui:search-container>
			</div>
		</div>

		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" />
		</aui:button-row>
	</aui:form>
</div>

<aui:script>

	<%
	for (PublicRenderParameterConfiguration publicRenderParameterConfiguration : publicRenderParameterConfigurations) {
	%>

		Liferay.Util.disableToggleBoxes('<portlet:namespace /><%= PublicRenderParameterConfiguration.IGNORE_PREFIX + HtmlUtil.escapeJS(publicRenderParameterConfiguration.getPublicRenderParameterName()) %>', '<portlet:namespace /><%= PublicRenderParameterConfiguration.MAPPING_PREFIX + HtmlUtil.escapeJS(publicRenderParameterConfiguration.getPublicRenderParameterName()) %>', true);

	<%
	}
	%>

</aui:script>