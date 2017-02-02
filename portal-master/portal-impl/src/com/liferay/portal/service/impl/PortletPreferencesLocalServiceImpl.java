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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletConstants;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.model.PortletPreferencesIds;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.ExceptionRetryAcceptor;
import com.liferay.portal.kernel.spring.aop.Property;
import com.liferay.portal.kernel.spring.aop.Retry;
import com.liferay.portal.kernel.spring.aop.Skip;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.base.PortletPreferencesLocalServiceBaseImpl;
import com.liferay.portlet.PortletPreferencesImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class PortletPreferencesLocalServiceImpl
	extends PortletPreferencesLocalServiceBaseImpl {

	@Override
	public PortletPreferences addPortletPreferences(
		long companyId, long ownerId, int ownerType, long plid,
		String portletId, Portlet portlet, String defaultPreferences) {

		long portletPreferencesId = counterLocalService.increment();

		PortletPreferences portletPreferences =
			portletPreferencesPersistence.create(portletPreferencesId);

		portletPreferences.setOwnerId(ownerId);
		portletPreferences.setOwnerType(ownerType);
		portletPreferences.setPlid(plid);
		portletPreferences.setPortletId(portletId);

		if (Validator.isNull(defaultPreferences)) {
			if (portlet == null) {
				defaultPreferences = PortletConstants.DEFAULT_PREFERENCES;
			}
			else {
				defaultPreferences = portlet.getDefaultPreferences();
			}
		}

		portletPreferences.setPreferences(defaultPreferences);

		if (_log.isDebugEnabled()) {
			StringBundler sb = new StringBundler(13);

			sb.append("Add {companyId=");
			sb.append(companyId);
			sb.append(", ownerId=");
			sb.append(ownerId);
			sb.append(", ownerType=");
			sb.append(ownerType);
			sb.append(", plid=");
			sb.append(plid);
			sb.append(", portletId=");
			sb.append(portletId);
			sb.append(", defaultPreferences=");
			sb.append(defaultPreferences);
			sb.append("}");

			_log.debug(sb.toString());
		}

		try {
			portletPreferencesPersistence.update(portletPreferences);
		}
		catch (SystemException se) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Add failed, fetch {ownerId=" + ownerId + ", ownerType=" +
						ownerType + ", plid=" + plid + ", portletId=" +
							portletId + "}");
			}

			portletPreferences = portletPreferencesPersistence.fetchByO_O_P_P(
				ownerId, ownerType, plid, portletId, false);

			if (portletPreferences == null) {
				throw se;
			}
		}

		return portletPreferences;
	}

	@Override
	public void deletePortletPreferences(
		long ownerId, int ownerType, long plid) {

		portletPreferencesPersistence.removeByO_O_P(ownerId, ownerType, plid);
	}

	@Override
	public void deletePortletPreferences(
			long ownerId, int ownerType, long plid, String portletId)
		throws PortalException {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Delete {ownerId=" + ownerId + ", ownerType=" + ownerType +
					", plid=" + plid + ", portletId=" + portletId + "}");
		}

		portletPreferencesPersistence.removeByO_O_P_P(
			ownerId, ownerType, plid, portletId);
	}

	@Override
	public void deletePortletPreferencesByPlid(long plid) {
		if (_log.isDebugEnabled()) {
			_log.debug("Delete {plid=" + plid + "}");
		}

		portletPreferencesPersistence.removeByPlid(plid);
	}

	@Override
	public javax.portlet.PortletPreferences fetchPreferences(
		long companyId, long ownerId, int ownerType, long plid,
		String portletId) {

		PortletPreferences portletPreferences =
			portletPreferencesPersistence.fetchByO_O_P_P(
				ownerId, ownerType, plid, portletId);

		if (portletPreferences == null) {
			return null;
		}

		PortletPreferencesImpl portletPreferencesImpl =
			(PortletPreferencesImpl)PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType, plid, portletId,
				portletPreferences.getPreferences());

		return portletPreferencesImpl;
	}

	@Override
	public javax.portlet.PortletPreferences fetchPreferences(
		PortletPreferencesIds portletPreferencesIds) {

		return fetchPreferences(
			portletPreferencesIds.getCompanyId(),
			portletPreferencesIds.getOwnerId(),
			portletPreferencesIds.getOwnerType(),
			portletPreferencesIds.getPlid(),
			portletPreferencesIds.getPortletId());
	}

	@Override
	@Skip
	public javax.portlet.PortletPreferences getDefaultPreferences(
		long companyId, String portletId) {

		Portlet portlet = portletLocalService.getPortletById(
			companyId, portletId);

		return PortletPreferencesFactoryUtil.fromDefaultXML(
			portlet.getDefaultPreferences());
	}

	@Override
	public List<PortletPreferences> getPortletPreferences() {
		return portletPreferencesPersistence.findAll();
	}

	@Override
	public List<PortletPreferences> getPortletPreferences(
		int ownerType, long plid, String portletId) {

		return portletPreferencesPersistence.findByO_P_P(
			ownerType, plid, portletId);
	}

	@Override
	public List<PortletPreferences> getPortletPreferences(
		long ownerId, int ownerType, long plid) {

		return portletPreferencesPersistence.findByO_O_P(
			ownerId, ownerType, plid);
	}

	@Override
	public PortletPreferences getPortletPreferences(
			long ownerId, int ownerType, long plid, String portletId)
		throws PortalException {

		return portletPreferencesPersistence.findByO_O_P_P(
			ownerId, ownerType, plid, portletId);
	}

	@Override
	public List<PortletPreferences> getPortletPreferences(
		long companyId, long groupId, long ownerId, int ownerType,
		String portletId, boolean privateLayout) {

		return portletPreferencesFinder.findByC_G_O_O_P_P(
			companyId, groupId, ownerId, ownerType, portletId, privateLayout);
	}

	@Override
	public List<PortletPreferences> getPortletPreferences(
		long plid, String portletId) {

		return portletPreferencesPersistence.findByP_P(plid, portletId);
	}

	@Override
	public List<PortletPreferences> getPortletPreferencesByPlid(long plid) {
		return portletPreferencesPersistence.findByPlid(plid);
	}

	@Override
	public long getPortletPreferencesCount(
		int ownerType, long plid, String portletId) {

		return portletPreferencesPersistence.countByO_P_P(
			ownerType, plid, portletId);
	}

	@Override
	public long getPortletPreferencesCount(int ownerType, String portletId) {
		return portletPreferencesPersistence.countByO_P(ownerType, portletId);
	}

	@Override
	public long getPortletPreferencesCount(
		long ownerId, int ownerType, long plid, Portlet portlet,
		boolean excludeDefaultPreferences) {

		String portletId = portlet.getPortletId();

		if (plid == -1) {
			portletId = portlet.getRootPortletId();
		}

		return portletPreferencesFinder.countByO_O_P_P_P(
			ownerId, ownerType, plid, portletId, excludeDefaultPreferences);
	}

	@Override
	public long getPortletPreferencesCount(
		long ownerId, int ownerType, String portletId,
		boolean excludeDefaultPreferences) {

		return portletPreferencesFinder.countByO_O_P(
			ownerId, ownerType, portletId, excludeDefaultPreferences);
	}

	@Override
	@Retry(
		acceptor = ExceptionRetryAcceptor.class,
		properties = {
			@Property(
				name = ExceptionRetryAcceptor.EXCEPTION_NAME,
				value = "org.springframework.dao.DataIntegrityViolationException"
			)
		}
	)
	public javax.portlet.PortletPreferences getPreferences(
		long companyId, long ownerId, int ownerType, long plid,
		String portletId) {

		return getPreferences(
			companyId, ownerId, ownerType, plid, portletId, null);
	}

	@Override
	@Retry(
		acceptor = ExceptionRetryAcceptor.class,
		properties = {
			@Property(
				name = ExceptionRetryAcceptor.EXCEPTION_NAME,
				value = "org.springframework.dao.DataIntegrityViolationException"
			)
		}
	)
	public javax.portlet.PortletPreferences getPreferences(
		long companyId, long ownerId, int ownerType, long plid,
		String portletId, String defaultPreferences) {

		return getPreferences(
			companyId, ownerId, ownerType, plid, portletId, defaultPreferences,
			false);
	}

	@Override
	@Retry(
		acceptor = ExceptionRetryAcceptor.class,
		properties = {
			@Property(
				name = ExceptionRetryAcceptor.EXCEPTION_NAME,
				value = "org.springframework.dao.DataIntegrityViolationException"
			)
		}
	)
	public javax.portlet.PortletPreferences getPreferences(
		PortletPreferencesIds portletPreferencesIds) {

		return getPreferences(
			portletPreferencesIds.getCompanyId(),
			portletPreferencesIds.getOwnerId(),
			portletPreferencesIds.getOwnerType(),
			portletPreferencesIds.getPlid(),
			portletPreferencesIds.getPortletId());
	}

	@Override
	@Retry(
		acceptor = ExceptionRetryAcceptor.class,
		properties = {
			@Property(
				name = ExceptionRetryAcceptor.EXCEPTION_NAME,
				value = "org.springframework.dao.DataIntegrityViolationException"
			)
		}
	)
	public javax.portlet.PortletPreferences getStrictPreferences(
		long companyId, long ownerId, int ownerType, long plid,
		String portletId) {

		return getPreferences(
			companyId, ownerId, ownerType, plid, portletId, null, true);
	}

	@Override
	public javax.portlet.PortletPreferences getStrictPreferences(
		PortletPreferencesIds portletPreferencesIds) {

		return getStrictPreferences(
			portletPreferencesIds.getCompanyId(),
			portletPreferencesIds.getOwnerId(),
			portletPreferencesIds.getOwnerType(),
			portletPreferencesIds.getPlid(),
			portletPreferencesIds.getPortletId());
	}

	@Override
	public PortletPreferences updatePreferences(
		long ownerId, int ownerType, long plid, String portletId,
		javax.portlet.PortletPreferences portletPreferences) {

		String xml = PortletPreferencesFactoryUtil.toXML(portletPreferences);

		return updatePreferences(ownerId, ownerType, plid, portletId, xml);
	}

	@Override
	public PortletPreferences updatePreferences(
		long ownerId, int ownerType, long plid, String portletId, String xml) {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Update {ownerId=" + ownerId + ", ownerType=" + ownerType +
					", plid=" + plid + ", portletId=" + portletId + ", xml=" +
						xml + "}");
		}

		PortletPreferences portletPreferences =
			portletPreferencesPersistence.fetchByO_O_P_P(
				ownerId, ownerType, plid, portletId);

		if (portletPreferences == null) {
			long portletPreferencesId = counterLocalService.increment();

			portletPreferences = portletPreferencesPersistence.create(
				portletPreferencesId);

			portletPreferences.setOwnerId(ownerId);
			portletPreferences.setOwnerType(ownerType);
			portletPreferences.setPlid(plid);
			portletPreferences.setPortletId(portletId);
		}

		portletPreferences.setPreferences(xml);

		portletPreferencesPersistence.update(portletPreferences);

		return portletPreferences;
	}

	protected javax.portlet.PortletPreferences getPreferences(
		long companyId, long ownerId, int ownerType, long plid,
		String portletId, String defaultPreferences, boolean strict) {

		PortletPreferences portletPreferences =
			portletPreferencesPersistence.fetchByO_O_P_P(
				ownerId, ownerType, plid, portletId);

		if (portletPreferences == null) {
			Portlet portlet = portletLocalService.getPortletById(
				companyId, portletId);

			if (strict &&
				(Validator.isNull(defaultPreferences) ||
				 ((portlet != null) && portlet.isUndeployedPortlet()))) {

				if (portlet == null) {
					defaultPreferences = PortletConstants.DEFAULT_PREFERENCES;
				}
				else {
					defaultPreferences = portlet.getDefaultPreferences();
				}

				return PortletPreferencesFactoryUtil.strictFromXML(
					companyId, ownerId, ownerType, plid, portletId,
					defaultPreferences);
			}

			portletPreferences =
				portletPreferencesLocalService.addPortletPreferences(
					companyId, ownerId, ownerType, plid, portletId, portlet,
					defaultPreferences);
		}

		PortletPreferencesImpl portletPreferencesImpl =
			(PortletPreferencesImpl)PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType, plid, portletId,
				portletPreferences.getPreferences());

		return portletPreferencesImpl;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortletPreferencesLocalServiceImpl.class);

}