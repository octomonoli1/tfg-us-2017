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

package com.liferay.portal.security.membershippolicy.bundle.rolemembershippolicyfactoryimpl;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.security.membershippolicy.RoleMembershipPolicy;
import com.liferay.portal.kernel.util.StackTraceUtil;

import java.io.Serializable;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Peter Fellwock
 */
@Component(
	immediate = true,
	property = {"service.ranking:Integer=" + Integer.MAX_VALUE}
)
public class TestRoleMembershipPolicy implements RoleMembershipPolicy {

	@Override
	public void checkRoles(
		long[] userIds, long[] addRoleIds, long[] removeRoleIds) {

		_atomicReference.set(StackTraceUtil.getCallerKey());
	}

	@Override
	public boolean isRoleAllowed(long userId, long roleId) {
		if (userId == 1) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isRoleRequired(long userId, long roleId) {
		if (userId == 1) {
			return true;
		}

		return false;
	}

	@Override
	public void propagateRoles(
		long[] userIds, long[] addRoleIds, long[] removeRoleIds) {

		_atomicReference.set(StackTraceUtil.getCallerKey());
	}

	@Override
	public void verifyPolicy() {
		_atomicReference.set(StackTraceUtil.getCallerKey());
	}

	@Override
	public void verifyPolicy(Role role) {
		_atomicReference.set(StackTraceUtil.getCallerKey());
	}

	@Override
	public void verifyPolicy(
		Role role, Role oldRole,
		Map<String, Serializable> oldExpandoAttributes) {

		_atomicReference.set(StackTraceUtil.getCallerKey());
	}

	@Reference(target = "(test=AtomicState)")
	protected void setAtomicReference(AtomicReference<String> atomicReference) {
		_atomicReference = atomicReference;
	}

	private AtomicReference<String> _atomicReference;

}