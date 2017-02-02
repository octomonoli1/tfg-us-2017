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

package com.liferay.knowledge.base.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the KBFolder service. Represents a row in the &quot;KBFolder&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see KBFolderModel
 * @see com.liferay.knowledge.base.model.impl.KBFolderImpl
 * @see com.liferay.knowledge.base.model.impl.KBFolderModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.knowledge.base.model.impl.KBFolderImpl")
@ProviderType
public interface KBFolder extends KBFolderModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.knowledge.base.model.impl.KBFolderImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<KBFolder, Long> KB_FOLDER_ID_ACCESSOR = new Accessor<KBFolder, Long>() {
			@Override
			public Long get(KBFolder kbFolder) {
				return kbFolder.getKbFolderId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<KBFolder> getTypeClass() {
				return KBFolder.class;
			}
		};

	public java.util.List<java.lang.Long> getAncestorKBFolderIds()
		throws com.liferay.portal.kernel.exception.PortalException;

	public long getClassNameId();

	public KBFolder getParentKBFolder()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.lang.String getParentTitle(java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean isEmpty()
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean isRoot();
}