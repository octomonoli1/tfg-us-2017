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

<portlet:defineObjects />

<%
PortletURL portletURL = (PortletURL)request.getAttribute("liferay-ui:group-search:portletURL");
RowChecker rowChecker = (RowChecker)request.getAttribute("liferay-ui:group-search:rowChecker");
LinkedHashMap<String, Object> groupParams = (LinkedHashMap<String, Object>)request.getAttribute("liferay-ui:group-search:groupParams");

GroupSearch searchContainer = new GroupSearch(renderRequest, portletURL);

request.setAttribute(WebKeys.SEARCH_CONTAINER, searchContainer);

searchContainer.setRowChecker(rowChecker);

GroupSearchTerms searchTerms = (GroupSearchTerms)searchContainer.getSearchTerms();

int total = GroupLocalServiceUtil.searchCount(company.getCompanyId(), searchTerms.getKeywords(), groupParams);

searchContainer.setTotal(total);

List<Group> results = GroupLocalServiceUtil.search(company.getCompanyId(), searchTerms.getKeywords(), groupParams, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());

searchContainer.setResults(results);
searchContainer.setTotal(total);
%>

<liferay-ui:input-search />