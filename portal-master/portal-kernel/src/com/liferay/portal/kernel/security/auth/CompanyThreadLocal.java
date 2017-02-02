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

package com.liferay.portal.kernel.security.auth;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.TimeZoneThreadLocal;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class CompanyThreadLocal {

	public static Long getCompanyId() {
		Long companyId = _companyId.get();

		if (_log.isDebugEnabled()) {
			_log.debug("getCompanyId " + companyId);
		}

		return companyId;
	}

	public static boolean isDeleteInProcess() {
		return _deleteInProcess.get();
	}

	public static void setCompanyId(Long companyId) {
		if (_log.isDebugEnabled()) {
			_log.debug("setCompanyId " + companyId);
		}

		if (companyId > 0) {
			_companyId.set(companyId);

			try {
				Company company = CompanyLocalServiceUtil.getCompany(companyId);

				LocaleThreadLocal.setDefaultLocale(company.getLocale());
				TimeZoneThreadLocal.setDefaultTimeZone(company.getTimeZone());
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		else {
			_companyId.set(CompanyConstants.SYSTEM);

			LocaleThreadLocal.setDefaultLocale(null);
			TimeZoneThreadLocal.setDefaultTimeZone(null);
		}
	}

	public static void setDeleteInProcess(boolean deleteInProcess) {
		_deleteInProcess.set(deleteInProcess);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CompanyThreadLocal.class);

	private static final ThreadLocal<Long> _companyId =
		new AutoResetThreadLocal<>(
			CompanyThreadLocal.class + "._companyId", CompanyConstants.SYSTEM);
	private static final ThreadLocal<Boolean> _deleteInProcess =
		new AutoResetThreadLocal<>(
			CompanyThreadLocal.class + "._deleteInProcess", false);

}