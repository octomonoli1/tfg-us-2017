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

package com.liferay.announcements.web.internal.portlet.action;

import com.liferay.announcements.kernel.exception.EntryContentException;
import com.liferay.announcements.kernel.exception.EntryDisplayDateException;
import com.liferay.announcements.kernel.exception.EntryExpirationDateException;
import com.liferay.announcements.kernel.exception.EntryTitleException;
import com.liferay.announcements.kernel.exception.EntryURLException;
import com.liferay.announcements.kernel.exception.NoSuchEntryException;
import com.liferay.announcements.kernel.service.AnnouncementsEntryService;
import com.liferay.announcements.web.constants.AnnouncementsPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Calendar;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Raymond Augé
 */
@Component(
	property = {
		"javax.portlet.name=" + AnnouncementsPortletKeys.ALERTS,
		"javax.portlet.name=" + AnnouncementsPortletKeys.ANNOUNCEMENTS,
		"mvc.command.name=/announcements/edit_entry"
	},
	service = MVCActionCommand.class
)
public class EditEntryMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteEntry(ActionRequest actionRequest) throws Exception {
		long entryId = ParamUtil.getLong(actionRequest, "entryId");

		_announcementsEntryService.deleteEntry(entryId);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				updateEntry(actionRequest);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteEntry(actionRequest);
			}
		}
		catch (EntryContentException | EntryDisplayDateException |
			   EntryExpirationDateException | EntryTitleException |
			   EntryURLException | NoSuchEntryException |
			   PrincipalException e) {

			SessionErrors.add(actionRequest, e.getClass());
		}
	}

	@Reference(unbind = "-")
	protected void setAnnouncementsEntryService(
		AnnouncementsEntryService announcementsEntryService) {

		_announcementsEntryService = announcementsEntryService;
	}

	protected void updateEntry(ActionRequest actionRequest) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long entryId = ParamUtil.getLong(actionRequest, "entryId");

		String[] distributionScopeParts = StringUtil.split(
			ParamUtil.getString(actionRequest, "distributionScope"));

		long classNameId = 0;
		long classPK = 0;

		if (distributionScopeParts.length == 2) {
			classNameId = GetterUtil.getLong(distributionScopeParts[0]);

			if (classNameId > 0) {
				classPK = GetterUtil.getLong(distributionScopeParts[1]);
			}
		}

		String title = ParamUtil.getString(actionRequest, "title");
		String content = ParamUtil.getString(actionRequest, "content");
		String url = ParamUtil.getString(actionRequest, "url");
		String type = ParamUtil.getString(actionRequest, "type");

		int displayDateMonth = ParamUtil.getInteger(
			actionRequest, "displayDateMonth");
		int displayDateDay = ParamUtil.getInteger(
			actionRequest, "displayDateDay");
		int displayDateYear = ParamUtil.getInteger(
			actionRequest, "displayDateYear");
		int displayDateHour = ParamUtil.getInteger(
			actionRequest, "displayDateHour");
		int displayDateMinute = ParamUtil.getInteger(
			actionRequest, "displayDateMinute");
		int displayDateAmPm = ParamUtil.getInteger(
			actionRequest, "displayDateAmPm");

		if (displayDateAmPm == Calendar.PM) {
			displayDateHour += 12;
		}

		boolean displayImmediately = ParamUtil.getBoolean(
			actionRequest, "displayImmediately");

		int expirationDateMonth = ParamUtil.getInteger(
			actionRequest, "expirationDateMonth");
		int expirationDateDay = ParamUtil.getInteger(
			actionRequest, "expirationDateDay");
		int expirationDateYear = ParamUtil.getInteger(
			actionRequest, "expirationDateYear");
		int expirationDateHour = ParamUtil.getInteger(
			actionRequest, "expirationDateHour");
		int expirationDateMinute = ParamUtil.getInteger(
			actionRequest, "expirationDateMinute");
		int expirationDateAmPm = ParamUtil.getInteger(
			actionRequest, "expirationDateAmPm");

		if (expirationDateAmPm == Calendar.PM) {
			expirationDateHour += 12;
		}

		int priority = ParamUtil.getInteger(actionRequest, "priority");
		boolean alert = ParamUtil.getBoolean(actionRequest, "alert");

		if (entryId <= 0) {

			// Add entry

			_announcementsEntryService.addEntry(
				themeDisplay.getPlid(), classNameId, classPK, title, content,
				url, type, displayDateMonth, displayDateDay, displayDateYear,
				displayDateHour, displayDateMinute, displayImmediately,
				expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, priority, alert);
		}
		else {

			// Update entry

			_announcementsEntryService.updateEntry(
				entryId, title, content, url, type, displayDateMonth,
				displayDateDay, displayDateYear, displayDateHour,
				displayDateMinute, displayImmediately, expirationDateMonth,
				expirationDateDay, expirationDateYear, expirationDateHour,
				expirationDateMinute, priority);
		}
	}

	private AnnouncementsEntryService _announcementsEntryService;

}