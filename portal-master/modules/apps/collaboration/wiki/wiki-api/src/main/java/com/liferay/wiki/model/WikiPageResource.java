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

package com.liferay.wiki.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the WikiPageResource service. Represents a row in the &quot;WikiPageResource&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see WikiPageResourceModel
 * @see com.liferay.wiki.model.impl.WikiPageResourceImpl
 * @see com.liferay.wiki.model.impl.WikiPageResourceModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.wiki.model.impl.WikiPageResourceImpl")
@ProviderType
public interface WikiPageResource extends WikiPageResourceModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.wiki.model.impl.WikiPageResourceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WikiPageResource, Long> RESOURCE_PRIM_KEY_ACCESSOR =
		new Accessor<WikiPageResource, Long>() {
			@Override
			public Long get(WikiPageResource wikiPageResource) {
				return wikiPageResource.getResourcePrimKey();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WikiPageResource> getTypeClass() {
				return WikiPageResource.class;
			}
		};
}