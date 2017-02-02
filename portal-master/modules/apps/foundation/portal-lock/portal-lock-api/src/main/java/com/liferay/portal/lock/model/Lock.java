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

package com.liferay.portal.lock.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Lock service. Represents a row in the &quot;Lock_&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see LockModel
 * @see com.liferay.portal.lock.model.impl.LockImpl
 * @see com.liferay.portal.lock.model.impl.LockModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.portal.lock.model.impl.LockImpl")
@ProviderType
public interface Lock extends LockModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.lock.model.impl.LockImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Lock, Long> LOCK_ID_ACCESSOR = new Accessor<Lock, Long>() {
			@Override
			public Long get(Lock lock) {
				return lock.getLockId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Lock> getTypeClass() {
				return Lock.class;
			}
		};

	public long getExpirationTime();

	public boolean isExpired();

	public boolean isNeverExpires();
}