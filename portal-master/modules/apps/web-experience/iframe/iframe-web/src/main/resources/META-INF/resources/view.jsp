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

<c:choose>
	<c:when test="<%= iFramePortletInstanceConfiguration.auth() && Validator.isNull(iFrameDisplayContext.getUserName()) && !themeDisplay.isSignedIn() %>">
		<div class="alert alert-info">
			<a href="<%= themeDisplay.getURLSignIn() %>" target="_top"><liferay-ui:message key="please-sign-in-to-access-this-application" /></a>
		</div>
	</c:when>
	<c:otherwise>
		<div class="iframe-container">
			<iframe alt="<%= HtmlUtil.escapeAttribute(iFramePortletInstanceConfiguration.alt()) %>" border="<%= HtmlUtil.escapeAttribute(iFramePortletInstanceConfiguration.border()) %>" bordercolor="<%= HtmlUtil.escapeAttribute(iFramePortletInstanceConfiguration.bordercolor()) %>" frameborder="<%= HtmlUtil.escapeAttribute(iFramePortletInstanceConfiguration.frameborder()) %>" height="<%= HtmlUtil.escapeAttribute(iFrameDisplayContext.getHeight()) %>" hspace="<%= HtmlUtil.escapeAttribute(iFramePortletInstanceConfiguration.hspace()) %>" id="<portlet:namespace />iframe" longdesc="<%= HtmlUtil.escapeAttribute(iFramePortletInstanceConfiguration.longdesc()) %>" name="<portlet:namespace />iframe" onload="<%= iFramePortletInstanceConfiguration.dynamicUrlEnabled() ? renderResponse.getNamespace() + "monitorIframe();" : "" %>" scrolling="<%= HtmlUtil.escapeAttribute(iFramePortletInstanceConfiguration.scrolling()) %>" src="<%= HtmlUtil.escapeHREF(iFrameDisplayContext.getIframeSrc()) %>" title="<%= HtmlUtil.escapeAttribute(iFramePortletInstanceConfiguration.title()) %>" vspace="<%= HtmlUtil.escapeAttribute(iFramePortletInstanceConfiguration.vspace()) %>" width="<%= HtmlUtil.escapeAttribute(iFramePortletInstanceConfiguration.width()) %>">
				<liferay-ui:message
					arguments="<%= HtmlUtil.escape(iFrameDisplayContext.getIframeSrc()) %>"
					key="your-browser-does-not-support-inline-frames-or-is-currently-configured-not-to-display-inline-frames.-content-can-be-viewed-at-actual-source-page-x"
					translateArguments="<%= false %>"
				/>
			</iframe>
		</div>
	</c:otherwise>
</c:choose>

<c:if test="<%= iFramePortletInstanceConfiguration.dynamicUrlEnabled() %>">
	<aui:script>
		function <portlet:namespace />monitorIframe() {
			var A = AUI();

			var url = null;

			try {
				var iframe = document.getElementById('<portlet:namespace />iframe');

				url = iframe.contentWindow.document.location.href;
			}
			catch (e) {
				return true;
			}

			iframe.contentWindow.Liferay.on('endNavigate', <portlet:namespace />monitorIframe);

			var baseSrc = '<%= HtmlUtil.escapeJS(iFrameDisplayContext.getIframeBaseSrc()) %>';
			var iframeSrc = '<%= HtmlUtil.escapeJS(iFrameDisplayContext.getIframeSrc()) %>';

			if ((url == iframeSrc) || (url == (iframeSrc + '/'))) {
			}
			else if (A.Lang.String.startsWith(url, baseSrc)) {
				url = url.substring(baseSrc.length);

				<portlet:namespace />updateHash(url);
			}
			else {
				<portlet:namespace />updateHash(url);
			}

			return true;
		}

		Liferay.provide(
			window,
			'<portlet:namespace />init',
			function() {
				var A = AUI();

				var hash = document.location.hash.replace('#', '');

				// LPS-33951

				if (!A.UA.gecko) {
					hash = A.QueryString.unescape(hash);
				}

				var hashObj = A.QueryString.parse(hash);

				hash = hashObj['<portlet:namespace />'];

				if (hash) {
					var src = '';

					var baseSrc = '<%= HtmlUtil.escapeJS(iFrameDisplayContext.getIframeBaseSrc()) %>';

					if (!(/^https?\:\/\//.test(hash)) || !A.Lang.String.startsWith(hash, baseSrc)) {
						src = '<%= HtmlUtil.escapeJS(iFrameDisplayContext.getIframeBaseSrc()) %>';
					}

					src += hash;

					var iframe = A.one('#<portlet:namespace />iframe');

					if (iframe) {
						iframe.attr('src', src);
					}
				}
			},
			['aui-base', 'querystring']
		);

		Liferay.provide(
			window,
			'<portlet:namespace />updateHash',
			function(url) {
				var A = AUI();

				var hash = document.location.hash.replace('#', '');

				var hashObj = A.QueryString.parse(hash);

				hashObj['<portlet:namespace />'] = url;

				hash = A.QueryString.stringify(hashObj);

				var maximize = A.one('#p_p_id<portlet:namespace /> .portlet-maximize-icon a');

				if (maximize) {
					var maximizeUrl = maximize.attr('href');

					maximizeUrl = maximizeUrl.split('#')[0];

					maximize.attr('href', maximizeUrl + '#' + hash);
				}

				var restore = A.one('#p_p_id<portlet:namespace /> a.portlet-icon-back');

				if (restore) {
					var restoreHREF = restore.attr('href');

					restoreHREF = restoreHREF.split('#')[0];

					restore.attr('href', restoreHREF + '#' + hash);
				}

				// LPS-33951

				location.hash = A.QueryString.escape(hash);
			},
			['aui-base', 'querystring']
		);

		<portlet:namespace />init();
	</aui:script>
</c:if>

<aui:script use="aui-autosize-iframe">
	var iframe = A.one('#<portlet:namespace />iframe');

	if (iframe) {
		iframe.plug(
			A.Plugin.AutosizeIframe,
			{
				monitorHeight: <%= iFramePortletInstanceConfiguration.resizeAutomatically() %>
			}
		);

		iframe.on(
			'load',
			function() {
				var height = A.Plugin.AutosizeIframe.getContentHeight(iframe);

				if (height == null) {
					height = '<%= HtmlUtil.escapeJS(iFramePortletInstanceConfiguration.heightNormal()) %>';

					if (themeDisplay.isStateMaximized()) {
						height = '<%= HtmlUtil.escapeJS(iFramePortletInstanceConfiguration.heightMaximized()) %>';
					}

					iframe.setStyle('height', height);

					iframe.autosizeiframe.set('monitorHeight', false);
				}
			}
		);
	}
</aui:script>