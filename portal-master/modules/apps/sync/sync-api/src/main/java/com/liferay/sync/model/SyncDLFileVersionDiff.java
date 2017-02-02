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

package com.liferay.sync.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the SyncDLFileVersionDiff service. Represents a row in the &quot;SyncDLFileVersionDiff&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLFileVersionDiffModel
 * @see com.liferay.sync.model.impl.SyncDLFileVersionDiffImpl
 * @see com.liferay.sync.model.impl.SyncDLFileVersionDiffModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.sync.model.impl.SyncDLFileVersionDiffImpl")
@ProviderType
public interface SyncDLFileVersionDiff extends SyncDLFileVersionDiffModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.sync.model.impl.SyncDLFileVersionDiffImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SyncDLFileVersionDiff, Long> SYNC_D_L_FILE_VERSION_DIFF_ID_ACCESSOR =
		new Accessor<SyncDLFileVersionDiff, Long>() {
			@Override
			public Long get(SyncDLFileVersionDiff syncDLFileVersionDiff) {
				return syncDLFileVersionDiff.getSyncDLFileVersionDiffId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<SyncDLFileVersionDiff> getTypeClass() {
				return SyncDLFileVersionDiff.class;
			}
		};
}