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

package com.liferay.portal.kernel.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the UserGroupGroupRole service. Represents a row in the &quot;UserGroupGroupRole&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupGroupRoleModel
 * @see com.liferay.portal.model.impl.UserGroupGroupRoleImpl
 * @see com.liferay.portal.model.impl.UserGroupGroupRoleModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.portal.model.impl.UserGroupGroupRoleImpl")
@ProviderType
public interface UserGroupGroupRole extends UserGroupGroupRoleModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.model.impl.UserGroupGroupRoleImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<UserGroupGroupRole, Long> USER_GROUP_ID_ACCESSOR =
		new Accessor<UserGroupGroupRole, Long>() {
			@Override
			public Long get(UserGroupGroupRole userGroupGroupRole) {
				return userGroupGroupRole.getUserGroupId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<UserGroupGroupRole> getTypeClass() {
				return UserGroupGroupRole.class;
			}
		};

	public static final Accessor<UserGroupGroupRole, Long> GROUP_ID_ACCESSOR = new Accessor<UserGroupGroupRole, Long>() {
			@Override
			public Long get(UserGroupGroupRole userGroupGroupRole) {
				return userGroupGroupRole.getGroupId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<UserGroupGroupRole> getTypeClass() {
				return UserGroupGroupRole.class;
			}
		};

	public static final Accessor<UserGroupGroupRole, Long> ROLE_ID_ACCESSOR = new Accessor<UserGroupGroupRole, Long>() {
			@Override
			public Long get(UserGroupGroupRole userGroupGroupRole) {
				return userGroupGroupRole.getRoleId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<UserGroupGroupRole> getTypeClass() {
				return UserGroupGroupRole.class;
			}
		};

	public Group getGroup()
		throws com.liferay.portal.kernel.exception.PortalException;

	public Role getRole()
		throws com.liferay.portal.kernel.exception.PortalException;

	public UserGroup getUserGroup()
		throws com.liferay.portal.kernel.exception.PortalException;
}