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
 * The extended model interface for the Address service. Represents a row in the &quot;Address&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AddressModel
 * @see com.liferay.portal.model.impl.AddressImpl
 * @see com.liferay.portal.model.impl.AddressModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.portal.model.impl.AddressImpl")
@ProviderType
public interface Address extends AddressModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.model.impl.AddressImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Address, Long> ADDRESS_ID_ACCESSOR = new Accessor<Address, Long>() {
			@Override
			public Long get(Address address) {
				return address.getAddressId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Address> getTypeClass() {
				return Address.class;
			}
		};

	public Country getCountry();

	public Region getRegion();

	public ListType getType();
}