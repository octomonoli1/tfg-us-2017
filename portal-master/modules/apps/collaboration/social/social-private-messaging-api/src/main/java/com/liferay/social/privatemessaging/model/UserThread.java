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

package com.liferay.social.privatemessaging.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the UserThread service. Represents a row in the &quot;PM_UserThread&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see UserThreadModel
 * @see com.liferay.social.privatemessaging.model.impl.UserThreadImpl
 * @see com.liferay.social.privatemessaging.model.impl.UserThreadModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.social.privatemessaging.model.impl.UserThreadImpl")
@ProviderType
public interface UserThread extends UserThreadModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.social.privatemessaging.model.impl.UserThreadImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<UserThread, Long> USER_THREAD_ID_ACCESSOR = new Accessor<UserThread, Long>() {
			@Override
			public Long get(UserThread userThread) {
				return userThread.getUserThreadId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<UserThread> getTypeClass() {
				return UserThread.class;
			}
		};
}