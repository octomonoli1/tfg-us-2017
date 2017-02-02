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
 * The extended model interface for the KaleoNode service. Represents a row in the &quot;KaleoNode&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNodeModel
 * @see com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeImpl
 * @see com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeImpl")
@ProviderType
public interface KaleoNode extends KaleoNodeModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<KaleoNode, Long> KALEO_NODE_ID_ACCESSOR = new Accessor<KaleoNode, Long>() {
			@Override
			public Long get(KaleoNode kaleoNode) {
				return kaleoNode.getKaleoNodeId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<KaleoNode> getTypeClass() {
				return KaleoNode.class;
			}
		};

	public KaleoTransition getDefaultKaleoTransition()
		throws com.liferay.portal.kernel.exception.PortalException;

	public KaleoTransition getKaleoTransition(java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.util.List<KaleoTransition> getKaleoTransitions();

	public boolean hasKaleoTransition();
}