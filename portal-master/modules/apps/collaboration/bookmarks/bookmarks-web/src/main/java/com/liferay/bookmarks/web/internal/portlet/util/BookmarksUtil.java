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

package com.liferay.bookmarks.web.internal.portlet.util;

import com.liferay.bookmarks.model.BookmarksEntry;
import com.liferay.bookmarks.model.BookmarksFolder;
import com.liferay.bookmarks.model.BookmarksFolderConstants;
import com.liferay.bookmarks.service.BookmarksEntryLocalServiceUtil;
import com.liferay.bookmarks.service.BookmarksFolderLocalServiceUtil;
import com.liferay.bookmarks.util.comparator.EntryCreateDateComparator;
import com.liferay.bookmarks.util.comparator.EntryModifiedDateComparator;
import com.liferay.bookmarks.util.comparator.EntryNameComparator;
import com.liferay.bookmarks.util.comparator.EntryPriorityComparator;
import com.liferay.bookmarks.util.comparator.EntryURLComparator;
import com.liferay.bookmarks.util.comparator.EntryVisitsComparator;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class BookmarksUtil {

	public static void addPortletBreadcrumbEntries(
			BookmarksEntry entry, HttpServletRequest request,
			RenderResponse renderResponse)
		throws Exception {

		BookmarksFolder folder = entry.getFolder();

		if (folder.getFolderId() !=
				BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			addPortletBreadcrumbEntries(folder, request, renderResponse);
		}
	}

	public static void addPortletBreadcrumbEntries(
			BookmarksFolder folder, HttpServletRequest request,
			RenderResponse renderResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String mvcRenderCommandName = ParamUtil.getString(
			request, "mvcRenderCommandName");

		PortletURL portletURL = renderResponse.createRenderURL();

		if (mvcRenderCommandName.equals("/bookmarks/select_folder")) {
			portletURL.setParameter(
				"mvcRenderCommandName", "/bookmarks/select_folder");
			portletURL.setWindowState(LiferayWindowState.POP_UP);
		}
		else {
			portletURL.setParameter("mvcRenderCommandName", "/bookmarks/view");
		}

		PortalUtil.addPortletBreadcrumbEntry(
			request, themeDisplay.translate("home"), portletURL.toString());

		if (folder == null) {
			return;
		}

		if (!mvcRenderCommandName.equals("/bookmarks/select_folder")) {
			portletURL.setParameter(
				"mvcRenderCommandName", "/bookmarks/view_folder");
		}

		List<BookmarksFolder> ancestorFolders = folder.getAncestors();

		Collections.reverse(ancestorFolders);

		for (BookmarksFolder ancestorFolder : ancestorFolders) {
			portletURL.setParameter(
				"folderId", String.valueOf(ancestorFolder.getFolderId()));

			PortalUtil.addPortletBreadcrumbEntry(
				request, ancestorFolder.getName(), portletURL.toString());
		}

		portletURL.setParameter(
			"folderId", String.valueOf(folder.getFolderId()));

		if (folder.getFolderId() !=
				BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			BookmarksFolder unescapedFolder = folder.toUnescapedModel();

			PortalUtil.addPortletBreadcrumbEntry(
				request, unescapedFolder.getName(), portletURL.toString());
		}
	}

	public static void addPortletBreadcrumbEntries(
			long folderId, HttpServletRequest request,
			RenderResponse renderResponse)
		throws Exception {

		if (folderId == BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return;
		}

		BookmarksFolder folder = BookmarksFolderLocalServiceUtil.getFolder(
			folderId);

		addPortletBreadcrumbEntries(folder, request, renderResponse);
	}

	public static Map<String, String> getEmailDefinitionTerms(
		PortletRequest portletRequest, String emailFromAddress,
		String emailFromName) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Map<String, String> definitionTerms = new LinkedHashMap<>();

		definitionTerms.put(
			"[$BOOKMARKS_ENTRY_USER_NAME$]",
			LanguageUtil.get(
				themeDisplay.getLocale(),
				"the-user-who-added-the-bookmark-entry"));
		definitionTerms.put(
			"[$BOOKMARKS_ENTRY_STATUS_BY_USER_NAME$]",
			LanguageUtil.get(
				themeDisplay.getLocale(),
				"the-user-who-updated-the-bookmark-entry"));
		definitionTerms.put(
			"[$BOOKMARKS_ENTRY_URL$]",
			LanguageUtil.get(
				themeDisplay.getLocale(), "the-bookmark-entry-url"));
		definitionTerms.put(
			"[$FROM_ADDRESS$]", HtmlUtil.escape(emailFromAddress));
		definitionTerms.put("[$FROM_NAME$]", HtmlUtil.escape(emailFromName));

		Company company = themeDisplay.getCompany();

		definitionTerms.put("[$PORTAL_URL$]", company.getVirtualHostname());

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		definitionTerms.put(
			"[$PORTLET_NAME$]", HtmlUtil.escape(portletDisplay.getTitle()));

		definitionTerms.put(
			"[$TO_ADDRESS$]",
			LanguageUtil.get(
				themeDisplay.getLocale(),
				"the-address-of-the-email-recipient"));
		definitionTerms.put(
			"[$TO_NAME$]",
			LanguageUtil.get(
				themeDisplay.getLocale(), "the-name-of-the-email-recipient"));

		return definitionTerms;
	}

	public static List<Object> getEntries(Hits hits) {
		List<Object> entries = new ArrayList<>();

		for (Document document : hits.getDocs()) {
			String entryClassName = document.get(Field.ENTRY_CLASS_NAME);
			long entryClassPK = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			try {
				Object obj = null;

				if (entryClassName.equals(BookmarksEntry.class.getName())) {
					obj = BookmarksEntryLocalServiceUtil.getEntry(entryClassPK);
				}
				else if (entryClassName.equals(
							BookmarksFolder.class.getName())) {

					obj = BookmarksFolderLocalServiceUtil.getFolder(
						entryClassPK);
				}

				entries.add(obj);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Bookmarks search index is stale and contains entry " +
							entryClassPK);
				}

				continue;
			}
		}

		return entries;
	}

	public static OrderByComparator<BookmarksEntry> getEntryOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<BookmarksEntry> orderByComparator = null;

		if (orderByCol.equals("create-date")) {
			orderByComparator = new EntryCreateDateComparator(orderByAsc);
		}
		else if (orderByCol.equals("modified-date")) {
			orderByComparator = new EntryModifiedDateComparator(orderByAsc);
		}
		else if (orderByCol.equals("name")) {
			orderByComparator = new EntryNameComparator(orderByAsc);
		}
		else if (orderByCol.equals("priority")) {
			orderByComparator = new EntryPriorityComparator(orderByAsc);
		}
		else if (orderByCol.equals("url")) {
			orderByComparator = new EntryURLComparator(orderByAsc);
		}
		else if (orderByCol.equals("visits")) {
			orderByComparator = new EntryVisitsComparator(orderByAsc);
		}

		return orderByComparator;
	}

	private static final Log _log = LogFactoryUtil.getLog(BookmarksUtil.class);

}