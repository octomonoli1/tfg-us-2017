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

package com.liferay.document.library.kernel.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the DLFileEntryType service. Represents a row in the &quot;DLFileEntryType&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see DLFileEntryTypeModel
 * @see com.liferay.portlet.documentlibrary.model.impl.DLFileEntryTypeImpl
 * @see com.liferay.portlet.documentlibrary.model.impl.DLFileEntryTypeModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.portlet.documentlibrary.model.impl.DLFileEntryTypeImpl")
@ProviderType
public interface DLFileEntryType extends DLFileEntryTypeModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portlet.documentlibrary.model.impl.DLFileEntryTypeImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DLFileEntryType, Long> FILE_ENTRY_TYPE_ID_ACCESSOR =
		new Accessor<DLFileEntryType, Long>() {
			@Override
			public Long get(DLFileEntryType dlFileEntryType) {
				return dlFileEntryType.getFileEntryTypeId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<DLFileEntryType> getTypeClass() {
				return DLFileEntryType.class;
			}
		};

	public java.util.List<com.liferay.dynamic.data.mapping.kernel.DDMStructure> getDDMStructures();

	public java.lang.String getUnambiguousName(
		java.util.List<DLFileEntryType> dlFileEntryTypes, long groupId,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean isExportable();
}