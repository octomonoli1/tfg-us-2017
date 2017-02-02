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

package com.liferay.marketplace.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Module service. Represents a row in the &quot;Marketplace_Module&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ryan Park
 * @see ModuleModel
 * @see com.liferay.marketplace.model.impl.ModuleImpl
 * @see com.liferay.marketplace.model.impl.ModuleModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.marketplace.model.impl.ModuleImpl")
@ProviderType
public interface Module extends ModuleModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.marketplace.model.impl.ModuleImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Module, Long> MODULE_ID_ACCESSOR = new Accessor<Module, Long>() {
			@Override
			public Long get(Module module) {
				return module.getModuleId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Module> getTypeClass() {
				return Module.class;
			}
		};

	public boolean isBundle();
}