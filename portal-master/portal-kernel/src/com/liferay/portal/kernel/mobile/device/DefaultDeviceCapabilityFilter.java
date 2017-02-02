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

package com.liferay.portal.kernel.mobile.device;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Michael C. Han
 */
@ProviderType
public class DefaultDeviceCapabilityFilter implements DeviceCapabilityFilter {

	@Override
	public boolean accept(String capabilityName) {
		if (_acceptableCapabilityNames.isEmpty() ||
			_acceptableCapabilityNames.contains(capabilityName)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean accept(String capabilityName, String capabilityValue) {
		if (Validator.isNull(capabilityValue)) {
			return false;
		}

		capabilityValue = StringUtil.toLowerCase(capabilityValue);

		if (capabilityValue.equals("false")) {
			return false;
		}

		if (!accept(capabilityName)) {
			return false;
		}

		return true;
	}

	public void setAcceptableCapabilityNames(
		Set<String> acceptableCapabilityNames) {

		_acceptableCapabilityNames.addAll(acceptableCapabilityNames);
	}

	private final Set<String> _acceptableCapabilityNames = new HashSet<>();

}