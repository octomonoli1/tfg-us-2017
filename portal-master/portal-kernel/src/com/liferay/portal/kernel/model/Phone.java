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
 * The extended model interface for the Phone service. Represents a row in the &quot;Phone&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PhoneModel
 * @see com.liferay.portal.model.impl.PhoneImpl
 * @see com.liferay.portal.model.impl.PhoneModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.portal.model.impl.PhoneImpl")
@ProviderType
public interface Phone extends PhoneModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.model.impl.PhoneImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Phone, Long> PHONE_ID_ACCESSOR = new Accessor<Phone, Long>() {
			@Override
			public Long get(Phone phone) {
				return phone.getPhoneId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Phone> getTypeClass() {
				return Phone.class;
			}
		};

	public ListType getType()
		throws com.liferay.portal.kernel.exception.PortalException;
}