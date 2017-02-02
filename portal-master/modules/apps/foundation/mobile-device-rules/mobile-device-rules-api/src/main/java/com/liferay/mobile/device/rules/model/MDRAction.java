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

package com.liferay.mobile.device.rules.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;
import com.liferay.portal.kernel.util.LocaleThreadLocal;

/**
 * The extended model interface for the MDRAction service. Represents a row in the &quot;MDRAction&quot; database table, with each column mapped to a property of this class.
 *
 * @author Edward C. Han
 * @see MDRActionModel
 * @see com.liferay.mobile.device.rules.model.impl.MDRActionImpl
 * @see com.liferay.mobile.device.rules.model.impl.MDRActionModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.mobile.device.rules.model.impl.MDRActionImpl")
@ProviderType
public interface MDRAction extends MDRActionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.mobile.device.rules.model.impl.MDRActionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MDRAction, Long> ACTION_ID_ACCESSOR = new Accessor<MDRAction, Long>() {
			@Override
			public Long get(MDRAction mdrAction) {
				return mdrAction.getActionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<MDRAction> getTypeClass() {
				return MDRAction.class;
			}
		};

	public static final Accessor<MDRAction, String> NAME_ACCESSOR = new Accessor<MDRAction, String>() {
			@Override
			public String get(MDRAction mdrAction) {
				return mdrAction.getName(LocaleThreadLocal.getThemeDisplayLocale());
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<MDRAction> getTypeClass() {
				return MDRAction.class;
			}
		};

	public com.liferay.portal.kernel.util.UnicodeProperties getTypeSettingsProperties();

	public void setTypeSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties typeSettingsProperties);
}