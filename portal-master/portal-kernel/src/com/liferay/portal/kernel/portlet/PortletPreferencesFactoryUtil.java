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

package com.liferay.portal.kernel.portlet;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletPreferencesIds;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PreferencesValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class PortletPreferencesFactoryUtil {

	public static void checkControlPanelPortletPreferences(
			ThemeDisplay themeDisplay, Portlet portlet)
		throws PortalException {

		getPortletPreferencesFactory().checkControlPanelPortletPreferences(
			themeDisplay, portlet);
	}

	public static PortletPreferences fromDefaultXML(String xml) {
		return getPortletPreferencesFactory().fromDefaultXML(xml);
	}

	public static PortalPreferences fromXML(
		long ownerId, int ownerType, String xml) {

		return getPortletPreferencesFactory().fromXML(ownerId, ownerType, xml);
	}

	public static PortletPreferences fromXML(
		long companyId, long ownerId, int ownerType, long plid,
		String portletId, String xml) {

		return getPortletPreferencesFactory().fromXML(
			companyId, ownerId, ownerType, plid, portletId, xml);
	}

	public static PortletPreferences getExistingPortletSetup(
			Layout layout, String portletId)
		throws PortalException {

		return getPortletPreferencesFactory().getExistingPortletSetup(
			layout, portletId);
	}

	public static PortletPreferences getExistingPortletSetup(
			PortletRequest portletRequest)
		throws PortalException {

		return getPortletPreferencesFactory().getExistingPortletSetup(
			portletRequest);
	}

	public static PortletPreferences getLayoutPortletSetup(
		Layout layout, String portletId) {

		return getPortletPreferencesFactory().getLayoutPortletSetup(
			layout, portletId);
	}

	public static PortletPreferences getLayoutPortletSetup(
		Layout layout, String portletId, String defaultPreferences) {

		return getPortletPreferencesFactory().getLayoutPortletSetup(
			layout, portletId, defaultPreferences);
	}

	public static PortletPreferences getLayoutPortletSetup(
		long companyId, long ownerId, int ownerType, long plid,
		String portletId, String defaultPreferences) {

		return getPortletPreferencesFactory().getLayoutPortletSetup(
			companyId, ownerId, ownerType, plid, portletId, defaultPreferences);
	}

	public static PortalPreferences getPortalPreferences(
		HttpServletRequest request) {

		return getPortletPreferencesFactory().getPortalPreferences(request);
	}

	public static PortalPreferences getPortalPreferences(
		HttpSession session, long userId, boolean signedIn) {

		return getPortletPreferencesFactory().getPortalPreferences(
			session, userId, signedIn);
	}

	public static PortalPreferences getPortalPreferences(
		long userId, boolean signedIn) {

		return getPortletPreferencesFactory().getPortalPreferences(
			userId, signedIn);
	}

	public static PortalPreferences getPortalPreferences(
		PortletRequest portletRequest) {

		return getPortletPreferencesFactory().getPortalPreferences(
			portletRequest);
	}

	public static PortletPreferences getPortletPreferences(
			HttpServletRequest request, String portletId)
		throws PortalException {

		return getPortletPreferencesFactory().getPortletPreferences(
			request, portletId);
	}

	public static PortletPreferencesFactory getPortletPreferencesFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			PortletPreferencesFactoryUtil.class);

		return _portletPreferencesFactory;
	}

	public static PortletPreferencesIds getPortletPreferencesIds(
			HttpServletRequest request, Layout selLayout, String portletId)
		throws PortalException {

		return getPortletPreferencesFactory().getPortletPreferencesIds(
			request, selLayout, portletId);
	}

	public static PortletPreferencesIds getPortletPreferencesIds(
			HttpServletRequest request, String portletId)
		throws PortalException {

		return getPortletPreferencesFactory().getPortletPreferencesIds(
			request, portletId);
	}

	public static PortletPreferencesIds getPortletPreferencesIds(
			long siteGroupId, long userId, Layout layout, String portletId,
			boolean modeEditGuest)
		throws PortalException {

		return getPortletPreferencesFactory().getPortletPreferencesIds(
			siteGroupId, userId, layout, portletId, modeEditGuest);
	}

	public static PortletPreferencesIds getPortletPreferencesIds(
		long companyId, long siteGroupId, long plid, String portletId,
		String settingsScope) {

		return getPortletPreferencesFactory().getPortletPreferencesIds(
			companyId, siteGroupId, plid, portletId, settingsScope);
	}

	public static PortletPreferences getPortletSetup(
			HttpServletRequest request, String portletId)
		throws PortalException {

		return getPortletPreferencesFactory().getPortletSetup(
			request, portletId);
	}

	public static PortletPreferences getPortletSetup(
			HttpServletRequest request, String portletId,
			String defaultPreferences)
		throws PortalException {

		return getPortletPreferencesFactory().getPortletSetup(
			request, portletId, defaultPreferences);
	}

	public static PortletPreferences getPortletSetup(
		Layout layout, String portletId, String defaultPreferences) {

		return getPortletPreferencesFactory().getPortletSetup(
			layout, portletId, defaultPreferences);
	}

	public static PortletPreferences getPortletSetup(
		long siteGroupId, Layout layout, String portletId,
		String defaultPreferences) {

		return getPortletPreferencesFactory().getPortletSetup(
			siteGroupId, layout, portletId, defaultPreferences);
	}

	public static PortletPreferences getPortletSetup(
			PortletRequest portletRequest)
		throws PortalException {

		return getPortletPreferencesFactory().getPortletSetup(portletRequest);
	}

	public static PortletPreferences getPortletSetup(
			PortletRequest portletRequest, String portletId)
		throws PortalException {

		return getPortletPreferencesFactory().getPortletSetup(
			portletRequest, portletId);
	}

	public static Map<Long, PortletPreferences> getPortletSetupMap(
		long companyId, long groupId, long ownerId, int ownerType,
		String portletId, boolean privateLayout) {

		return getPortletPreferencesFactory().getPortletSetupMap(
			companyId, groupId, ownerId, ownerType, portletId, privateLayout);
	}

	public static PortletPreferences getPreferences(
		HttpServletRequest request) {

		return getPortletPreferencesFactory().getPreferences(request);
	}

	public static PreferencesValidator getPreferencesValidator(
		Portlet portlet) {

		return getPortletPreferencesFactory().getPreferencesValidator(portlet);
	}

	public static PortletPreferences getStrictLayoutPortletSetup(
		Layout layout, String portletId) {

		return getPortletPreferencesFactory().getStrictLayoutPortletSetup(
			layout, portletId);
	}

	public static PortletPreferences getStrictPortletSetup(
		Layout layout, String portletId) {

		return getPortletPreferencesFactory().getStrictPortletSetup(
			layout, portletId);
	}

	public static PortletPreferences getStrictPortletSetup(
		long companyId, long groupId, String portletId) {

		return getPortletPreferencesFactory().getStrictPortletSetup(
			companyId, groupId, portletId);
	}

	public static PortletPreferences strictFromXML(
		long companyId, long ownerId, int ownerType, long plid,
		String portletId, String xml) {

		return getPortletPreferencesFactory().strictFromXML(
			companyId, ownerId, ownerType, plid, portletId, xml);
	}

	public static String toXML(PortalPreferences portalPreferences) {
		return getPortletPreferencesFactory().toXML(portalPreferences);
	}

	public static String toXML(PortletPreferences portletPreferences) {
		return getPortletPreferencesFactory().toXML(portletPreferences);
	}

	public void setPortletPreferencesFactory(
		PortletPreferencesFactory portletPreferencesFactory) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_portletPreferencesFactory = portletPreferencesFactory;
	}

	private static PortletPreferencesFactory _portletPreferencesFactory;

}