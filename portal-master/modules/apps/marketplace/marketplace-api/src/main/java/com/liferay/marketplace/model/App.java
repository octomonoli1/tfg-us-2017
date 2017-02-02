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
 * The extended model interface for the App service. Represents a row in the &quot;Marketplace_App&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ryan Park
 * @see AppModel
 * @see com.liferay.marketplace.model.impl.AppImpl
 * @see com.liferay.marketplace.model.impl.AppModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.marketplace.model.impl.AppImpl")
@ProviderType
public interface App extends AppModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.marketplace.model.impl.AppImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<App, Long> APP_ID_ACCESSOR = new Accessor<App, Long>() {
			@Override
			public Long get(App app) {
				return app.getAppId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<App> getTypeClass() {
				return App.class;
			}
		};

	public java.lang.String[] addContextName(java.lang.String contextName);

	public java.lang.String[] getContextNames();

	public java.lang.String getFileDir();

	public java.lang.String getFileName();

	public java.lang.String getFilePath();

	public boolean isDownloaded()
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean isInstalled();
}