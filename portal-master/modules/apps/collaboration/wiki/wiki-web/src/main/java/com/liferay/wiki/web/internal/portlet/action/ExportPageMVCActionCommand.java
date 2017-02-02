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

package com.liferay.wiki.web.internal.portlet.action;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portlet.documentlibrary.util.DocumentConversionUtil;
import com.liferay.wiki.constants.WikiPortletKeys;
import com.liferay.wiki.engine.impl.WikiEngineRenderer;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.service.WikiPageService;
import com.liferay.wiki.util.WikiUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bruno Farache
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + WikiPortletKeys.WIKI,
		"javax.portlet.name=" + WikiPortletKeys.WIKI_ADMIN,
		"javax.portlet.name=" + WikiPortletKeys.WIKI_DISPLAY,
		"mvc.command.name=/wiki/export_page"
	},
	service = MVCActionCommand.class
)
public class ExportPageMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		PortletConfig portletConfig = getPortletConfig(actionRequest);

		try {
			long nodeId = ParamUtil.getLong(actionRequest, "nodeId");
			String nodeName = ParamUtil.getString(actionRequest, "nodeName");
			String title = ParamUtil.getString(actionRequest, "title");
			double version = ParamUtil.getDouble(actionRequest, "version");

			String targetExtension = ParamUtil.getString(
				actionRequest, "targetExtension");

			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			PortletURL viewPageURL = PortletURLFactoryUtil.create(
				actionRequest, portletConfig.getPortletName(),
				PortletRequest.RENDER_PHASE);

			viewPageURL.setParameter("mvcRenderCommandName", "/wiki/view");
			viewPageURL.setParameter("nodeName", nodeName);
			viewPageURL.setParameter("title", title);
			viewPageURL.setPortletMode(PortletMode.VIEW);
			viewPageURL.setWindowState(WindowState.MAXIMIZED);

			PortletURL editPageURL = PortletURLFactoryUtil.create(
				actionRequest, portletConfig.getPortletName(),
				PortletRequest.RENDER_PHASE);

			editPageURL.setParameter("mvcRenderCommandName", "/wiki/edit_page");
			editPageURL.setParameter("nodeId", String.valueOf(nodeId));
			editPageURL.setParameter("title", title);
			editPageURL.setPortletMode(PortletMode.VIEW);
			editPageURL.setWindowState(WindowState.MAXIMIZED);

			HttpServletRequest request = PortalUtil.getHttpServletRequest(
				actionRequest);
			HttpServletResponse response = PortalUtil.getHttpServletResponse(
				actionResponse);

			getFile(
				nodeId, title, version, targetExtension, viewPageURL,
				editPageURL, themeDisplay, request, response);

			actionResponse.setRenderParameter("mvcPath", "/null.jsp");
		}
		catch (Exception e) {
			String host = PrefsPropsUtil.getString(
				PropsKeys.OPENOFFICE_SERVER_HOST);

			if (Validator.isNotNull(host) && !host.equals(_LOCALHOST_IP) &&
				!host.startsWith(_LOCALHOST)) {

				StringBundler sb = new StringBundler(3);

				sb.append("Conversion using a remote OpenOffice instance is ");
				sb.append("not fully supported. Please use a local instance ");
				sb.append("to prevent any limitations and problems.");

				_log.error(sb.toString());
			}

			PortalUtil.sendError(e, actionRequest, actionResponse);
		}
	}

	protected void getFile(
			long nodeId, String title, double version, String targetExtension,
			PortletURL viewPageURL, PortletURL editPageURL,
			ThemeDisplay themeDisplay, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		WikiPage page = _wikiPageService.getPage(nodeId, title, version);

		String content = page.getContent();

		String attachmentURLPrefix = WikiUtil.getAttachmentURLPrefix(
			themeDisplay.getPathMain(), themeDisplay.getPlid(), nodeId, title);

		try {
			content = _wikiEngineRenderer.convert(
				page, viewPageURL, editPageURL, attachmentURLPrefix);
		}
		catch (Exception e) {
			_log.error(
				"Error formatting the wiki page " + page.getPageId() +
					" with the format " + page.getFormat(),
				e);
		}

		StringBundler sb = new StringBundler(17);

		sb.append("<!DOCTYPE html>");

		sb.append("<html>");

		sb.append("<head>");
		sb.append("<meta content=\"");
		sb.append(ContentTypes.TEXT_HTML_UTF8);
		sb.append("\" http-equiv=\"content-type\" />");
		sb.append("<base href=\"");
		sb.append(themeDisplay.getPortalURL());
		sb.append("\" />");
		sb.append("</head>");

		sb.append("<body>");

		sb.append("<h1>");
		sb.append(title);
		sb.append("</h1>");
		sb.append(content);

		sb.append("</body>");
		sb.append("</html>");

		InputStream is = new UnsyncByteArrayInputStream(
			sb.toString().getBytes(StringPool.UTF8));

		String sourceExtension = "html";

		String fileName = title.concat(StringPool.PERIOD).concat(
			sourceExtension);

		if (Validator.isNotNull(targetExtension)) {
			String id = page.getUuid();

			File convertedFile = DocumentConversionUtil.convert(
				id, is, sourceExtension, targetExtension);

			if (convertedFile != null) {
				fileName = title.concat(StringPool.PERIOD).concat(
					targetExtension);

				is = new FileInputStream(convertedFile);
			}
		}

		String contentType = MimeTypesUtil.getContentType(fileName);

		ServletResponseUtil.sendFile(
			request, response, fileName, is, contentType);
	}

	@Reference(unbind = "-")
	protected void setWikiEngineRenderer(
		WikiEngineRenderer wikiEngineRenderer) {

		_wikiEngineRenderer = wikiEngineRenderer;
	}

	@Reference(unbind = "-")
	protected void setWikiPageService(WikiPageService wikiPageService) {
		_wikiPageService = wikiPageService;
	}

	private static final String _LOCALHOST = "localhost";

	private static final String _LOCALHOST_IP = "127.0.0.1";

	private static final Log _log = LogFactoryUtil.getLog(
		ExportPageMVCActionCommand.class);

	private WikiEngineRenderer _wikiEngineRenderer;
	private WikiPageService _wikiPageService;

}