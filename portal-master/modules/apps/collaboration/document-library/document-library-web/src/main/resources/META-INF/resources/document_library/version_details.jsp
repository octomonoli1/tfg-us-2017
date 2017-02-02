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
boolean checkedOut = GetterUtil.getBoolean(request.getAttribute("edit_file_entry.jsp-checkedOut"));
%>

<div id="<portlet:namespace />versionDetails" style="display: none">
	<aui:fieldset>
		<h5 class="control-label"><liferay-ui:message key="select-whether-this-is-a-major-or-minor-version" /></h5>

		<aui:input checked="<%= checkedOut %>" label="major-version" name="versionDetailsMajorVersion" type="radio" value="<%= true %>" />

		<aui:input checked="<%= !checkedOut %>" label="minor-version" name="versionDetailsMajorVersion" type="radio" value="<%= false %>" />

		<aui:input label="change-log" name="versionDetailsChangeLog" type="textarea" />

		<aui:button name="versionDetailsSave" primary="true" value="save" />

		<aui:button name="versionDetailsCancel" type="cancel" value="cancel" />
	</aui:fieldset>
</div>