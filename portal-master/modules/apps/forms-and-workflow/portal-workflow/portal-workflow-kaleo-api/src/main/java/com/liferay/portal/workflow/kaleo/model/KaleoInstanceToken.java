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

package com.liferay.portal.workflow.kaleo.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the KaleoInstanceToken service. Represents a row in the &quot;KaleoInstanceToken&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoInstanceTokenModel
 * @see com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenImpl
 * @see com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenImpl")
@ProviderType
public interface KaleoInstanceToken extends KaleoInstanceTokenModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<KaleoInstanceToken, Long> KALEO_INSTANCE_TOKEN_ID_ACCESSOR =
		new Accessor<KaleoInstanceToken, Long>() {
			@Override
			public Long get(KaleoInstanceToken kaleoInstanceToken) {
				return kaleoInstanceToken.getKaleoInstanceTokenId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<KaleoInstanceToken> getTypeClass() {
				return KaleoInstanceToken.class;
			}
		};

	public java.util.List<KaleoInstanceToken> getChildrenKaleoInstanceTokens();

	public KaleoNode getCurrentKaleoNode()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.util.List<KaleoInstanceToken> getIncompleteChildrenKaleoInstanceTokens();

	public KaleoInstance getKaleoInstance()
		throws com.liferay.portal.kernel.exception.PortalException;

	public KaleoInstanceToken getParentKaleoInstanceToken()
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean hasIncompleteChildrenKaleoInstanceToken();

	public void setCurrentKaleoNode(KaleoNode kaleoNode)
		throws com.liferay.portal.kernel.exception.PortalException;
}