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

<%@ include file="/panel_category/init.jsp" %>

<c:if test="<%= showBody %>">
	<liferay-application-list:panel-category-body panelApps="<%= panelApps %>" panelCategory="<%= panelCategory %>" />
</c:if>

<c:if test="<%= !panelApps.isEmpty() && showHeader %>">
		</div>
	</div>

	<c:if test="<%= persistState %>">
		<aui:script position="auto" use="liferay-store,io-request,parse-content">
			var collapse = $('#<%= id %>');

			collapse.on(
				'hidden.bs.collapse',
				function(event) {
					Liferay.Store('<%= PanelCategory.class.getName() %><%= id %>', 'closed');
				}
			);

			collapse.on(
				'shown.bs.collapse',
				function(event) {
					Liferay.Store('<%= PanelCategory.class.getName() %><%= id %>', 'open');
				}
			);
		</aui:script>
	</c:if>
</c:if>