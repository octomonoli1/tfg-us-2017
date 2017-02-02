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
 * The extended model interface for the ResourcePermission service. Represents a row in the &quot;ResourcePermission&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ResourcePermissionModel
 * @see com.liferay.portal.model.impl.ResourcePermissionImpl
 * @see com.liferay.portal.model.impl.ResourcePermissionModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.portal.model.impl.ResourcePermissionImpl")
@ProviderType
public interface ResourcePermission extends ResourcePermissionModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.model.impl.ResourcePermissionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ResourcePermission, Long> RESOURCE_PERMISSION_ID_ACCESSOR =
		new Accessor<ResourcePermission, Long>() {
			@Override
			public Long get(ResourcePermission resourcePermission) {
				return resourcePermission.getResourcePermissionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ResourcePermission> getTypeClass() {
				return ResourcePermission.class;
			}
		};

	public void addResourceAction(java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean hasAction(ResourceAction resourceAction);

	public boolean hasActionId(java.lang.String actionId);

	public void removeResourceAction(java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException;
}