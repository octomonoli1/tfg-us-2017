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

package com.liferay.message.boards.kernel.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the MBDiscussion service. Represents a row in the &quot;MBDiscussion&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see MBDiscussionModel
 * @see com.liferay.portlet.messageboards.model.impl.MBDiscussionImpl
 * @see com.liferay.portlet.messageboards.model.impl.MBDiscussionModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.portlet.messageboards.model.impl.MBDiscussionImpl")
@ProviderType
public interface MBDiscussion extends MBDiscussionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portlet.messageboards.model.impl.MBDiscussionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MBDiscussion, Long> DISCUSSION_ID_ACCESSOR = new Accessor<MBDiscussion, Long>() {
			@Override
			public Long get(MBDiscussion mbDiscussion) {
				return mbDiscussion.getDiscussionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<MBDiscussion> getTypeClass() {
				return MBDiscussion.class;
			}
		};
}