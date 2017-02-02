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
 * The extended model interface for the PasswordPolicyRel service. Represents a row in the &quot;PasswordPolicyRel&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PasswordPolicyRelModel
 * @see com.liferay.portal.model.impl.PasswordPolicyRelImpl
 * @see com.liferay.portal.model.impl.PasswordPolicyRelModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.portal.model.impl.PasswordPolicyRelImpl")
@ProviderType
public interface PasswordPolicyRel extends PasswordPolicyRelModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.model.impl.PasswordPolicyRelImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<PasswordPolicyRel, Long> PASSWORD_POLICY_REL_ID_ACCESSOR =
		new Accessor<PasswordPolicyRel, Long>() {
			@Override
			public Long get(PasswordPolicyRel passwordPolicyRel) {
				return passwordPolicyRel.getPasswordPolicyRelId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<PasswordPolicyRel> getTypeClass() {
				return PasswordPolicyRel.class;
			}
		};
}