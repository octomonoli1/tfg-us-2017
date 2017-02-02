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

package com.liferay.portal.security.auth.bundle.emailaddressgeneratorfactory;

import com.liferay.portal.kernel.security.auth.EmailAddressGenerator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Raymond Augé
 */
@Component(
	immediate = true,
	property = {"service.ranking:Integer=" + Integer.MAX_VALUE}
)
public class TestEmailAddressGenerator implements EmailAddressGenerator {

	@Override
	public String generate(long companyId, long userId) {
		return userId + "@generated.com";
	}

	@Override
	public boolean isFake(String emailAddress) {
		if (emailAddress.endsWith("@generated.com")) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isGenerated(String emailAddress) {
		return emailAddress.endsWith("@generated.com");
	}

}