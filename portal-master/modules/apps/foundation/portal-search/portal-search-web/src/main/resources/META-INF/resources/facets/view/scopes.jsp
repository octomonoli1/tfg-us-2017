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

<%@ include file="/facets/init.jsp" %>

<%
if (Validator.isNull(fieldParam)) {
	fieldParam = String.valueOf(searchDisplayContext.getSearchScopeGroupId());
}

ScopeSearchFacetDisplayContext scopeSearchFacetDisplayContext = new ScopeSearchFacetDisplayContext(facet, fieldParam, locale, dataJSONObject.getInt("frequencyThreshold"), dataJSONObject.getInt("maxTerms"), dataJSONObject.getBoolean("showAssetCount", true), GroupLocalServiceUtil.getService());
%>

<c:choose>
	<c:when test="<%= scopeSearchFacetDisplayContext.isRenderNothing() %>">
		<aui:input autocomplete="off" name="<%= HtmlUtil.escapeAttribute(scopeSearchFacetDisplayContext.getFieldParamInputName()) %>" type="hidden" value="<%= scopeSearchFacetDisplayContext.getFieldParamInputValue() %>" />
	</c:when>
	<c:otherwise>
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">
					<liferay-ui:message key="sites" />
				</div>
			</div>

			<div class="panel-body">
				<div class="<%= cssClass %>" data-facetFieldName="<%= HtmlUtil.escapeAttribute(facet.getFieldId()) %>" id="<%= randomNamespace %>facet">
					<aui:input autocomplete="off" name="<%= HtmlUtil.escapeAttribute(scopeSearchFacetDisplayContext.getFieldParamInputName()) %>" type="hidden" value="<%= scopeSearchFacetDisplayContext.getFieldParamInputValue() %>" />

					<ul class="list-unstyled scopes">
						<li class="default facet-value">
							<a class="<%= scopeSearchFacetDisplayContext.isNothingSelected() ? "text-primary" : "text-default" %>" data-value="0" href="javascript:;"><liferay-ui:message key="<%= HtmlUtil.escape(facetConfiguration.getLabel()) %>" /></a>
						</li>

						<%
						List<ScopeSearchFacetTermDisplayContext> scopeSearchFacetTermDisplayContexts = scopeSearchFacetDisplayContext.getTermDisplayContexts();

						for (ScopeSearchFacetTermDisplayContext scopeSearchFacetTermDisplayContext : scopeSearchFacetTermDisplayContexts) {
						%>

							<li class="facet-value">
								<a class="<%= scopeSearchFacetTermDisplayContext.isSelected() ? "text-primary" : "text-default" %>" data-value="<%= scopeSearchFacetTermDisplayContext.getGroupId() %>" href="javascript:;">
									<%= HtmlUtil.escape(scopeSearchFacetTermDisplayContext.getDescriptiveName()) %>

									<c:if test="<%= scopeSearchFacetTermDisplayContext.isShowCount() %>">
										<span class="frequency">(<%= scopeSearchFacetTermDisplayContext.getCount() %>)</span>
									</c:if>
								</a>
							</li>

						<%
						}
						%>

					</ul>
				</div>
			</div>
		</div>
	</c:otherwise>
</c:choose>