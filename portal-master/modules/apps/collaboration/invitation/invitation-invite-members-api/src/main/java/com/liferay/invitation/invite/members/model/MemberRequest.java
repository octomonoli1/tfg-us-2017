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

package com.liferay.invitation.invite.members.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the MemberRequest service. Represents a row in the &quot;SO_MemberRequest&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see MemberRequestModel
 * @see com.liferay.invitation.invite.members.model.impl.MemberRequestImpl
 * @see com.liferay.invitation.invite.members.model.impl.MemberRequestModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.invitation.invite.members.model.impl.MemberRequestImpl")
@ProviderType
public interface MemberRequest extends MemberRequestModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.invitation.invite.members.model.impl.MemberRequestImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MemberRequest, Long> MEMBER_REQUEST_ID_ACCESSOR =
		new Accessor<MemberRequest, Long>() {
			@Override
			public Long get(MemberRequest memberRequest) {
				return memberRequest.getMemberRequestId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<MemberRequest> getTypeClass() {
				return MemberRequest.class;
			}
		};
}