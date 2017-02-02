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

package com.liferay.mail.reader.mailbox;

import com.liferay.mail.reader.model.Account;
import com.liferay.mail.reader.service.AccountLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.StringPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Michael C. Han
 * @author Scott Lee
 */
public class PasswordRetriever {

	public PasswordRetriever(HttpServletRequest request) {
		if (request == null) {
			throw new IllegalStateException("Request is null");
		}

		_request = request;
	}

	public String getPassword(long accountId) throws PortalException {
		Account account = AccountLocalServiceUtil.getAccount(accountId);

		if (account.isSavePassword()) {
			return account.getPasswordDecrypted();
		}

		HttpSession session = _request.getSession();

		return (String)session.getAttribute(encodeKey(accountId));
	}

	public void removePassword(long accountId) {
		HttpSession session = _request.getSession();

		session.removeAttribute(encodeKey(accountId));
	}

	public void setPassword(long accountId, String password) {
		HttpSession session = _request.getSession();

		session.setAttribute(encodeKey(accountId), password);
	}

	protected String encodeKey(long accountId) {
		return PasswordRetriever.class.getName().concat(
			StringPool.POUND).concat(String.valueOf(accountId));
	}

	private final HttpServletRequest _request;

}