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

package com.liferay.portal.kernel.notifications;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.UserNotificationDelivery;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserNotificationDeliveryLocalServiceUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Jonathan Lee
 */
public abstract class BaseUserNotificationHandler
	implements UserNotificationHandler {

	@Override
	public String getPortletId() {
		return _portletId;
	}

	@Override
	public String getSelector() {
		return _selector;
	}

	@Override
	@SuppressWarnings("unused")
	public UserNotificationFeedEntry interpret(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws PortalException {

		try {
			UserNotificationFeedEntry userNotificationFeedEntry = doInterpret(
				userNotificationEvent, serviceContext);

			if (userNotificationFeedEntry != null) {
				userNotificationFeedEntry.setOpenDialog(isOpenDialog());
				userNotificationFeedEntry.setPortletId(getPortletId());
			}
			else {
				Portlet portlet = PortletLocalServiceUtil.getPortletById(
					getPortletId());

				String body = StringUtil.replace(
					_BODY_TEMPLATE_DEFAULT,
					new String[] {"[$BODY$]", "[$TITLE$]"},
					new String[] {
						serviceContext.translate(
							"notification-for-x-was-deleted",
							portlet.getDisplayName()),
						serviceContext.translate(
							"notification-no-longer-applies")
					});

				userNotificationFeedEntry = new UserNotificationFeedEntry(
					false, body, StringPool.BLANK);
			}

			return userNotificationFeedEntry;
		}
		catch (Exception e) {
			_log.error("Unable to interpret notification", e);
		}

		return null;
	}

	@Override
	public boolean isDeliver(
			long userId, long classNameId, int notificationType,
			int deliveryType, ServiceContext serviceContext)
		throws PortalException {

		UserNotificationDefinition userNotificationDefinition =
			UserNotificationManagerUtil.fetchUserNotificationDefinition(
				_portletId, classNameId, notificationType);

		if (userNotificationDefinition == null) {
			if (deliveryType == UserNotificationDeliveryConstants.TYPE_EMAIL) {
				return true;
			}

			return false;
		}

		UserNotificationDeliveryType userNotificationDeliveryType =
			userNotificationDefinition.getUserNotificationDeliveryType(
				deliveryType);

		if (userNotificationDeliveryType == null) {
			return false;
		}

		UserNotificationDelivery userNotificationDelivery =
			UserNotificationDeliveryLocalServiceUtil.
				getUserNotificationDelivery(
					userId, _portletId, classNameId, notificationType,
					deliveryType, userNotificationDeliveryType.isDefault());

		return userNotificationDelivery.isDeliver();
	}

	@Override
	public boolean isOpenDialog() {
		return _openDialog;
	}

	protected UserNotificationFeedEntry doInterpret(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		String body = getBody(userNotificationEvent, serviceContext);

		if (Validator.isNull(body)) {
			return null;
		}

		String link = getLink(userNotificationEvent, serviceContext);

		return new UserNotificationFeedEntry(isActionable(), body, link);
	}

	protected String getBody(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		return StringPool.BLANK;
	}

	protected String getBodyTemplate() throws Exception {
		if (isActionable()) {
			StringBundler sb = new StringBundler(7);

			sb.append("<div class=\"title\">[$TITLE$]</div><div ");
			sb.append("class=\"body\"><div class=\"button-holder\"><a ");
			sb.append("class=\"btn btn-lg btn-primary ");
			sb.append("user-notification-action\" href=\"[$CONFIRM_URL$]\">");
			sb.append("[$CONFIRM$]</a><a class=\"btn btn-lg btn-default ");
			sb.append("user-notification-action\" href=\"[$IGNORE_URL$]\">");
			sb.append("[$IGNORE$]</a></div></div>");

			return sb.toString();
		}
		else {
			return _BODY_TEMPLATE_DEFAULT;
		}
	}

	protected String getLink(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		return StringPool.BLANK;
	}

	protected boolean isActionable() {
		return _actionable;
	}

	protected void setActionable(boolean actionable) {
		_actionable = actionable;
	}

	protected void setOpenDialog(boolean openDialog) {
		_openDialog = openDialog;
	}

	protected void setPortletId(String portletId) {
		_portletId = portletId;
	}

	protected void setSelector(String selector) {
		_selector = selector;
	}

	private static final String _BODY_TEMPLATE_DEFAULT =
		"<div class=\"title\">[$TITLE$]</div><div class=\"body\">[$BODY$]" +
			"</div>";

	private static final Log _log = LogFactoryUtil.getLog(
		BaseUserNotificationHandler.class);

	private boolean _actionable;
	private boolean _openDialog;
	private String _portletId;
	private String _selector = StringPool.BLANK;

}