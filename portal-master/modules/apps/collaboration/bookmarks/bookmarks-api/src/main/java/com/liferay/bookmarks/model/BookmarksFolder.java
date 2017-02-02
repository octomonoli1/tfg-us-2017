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

package com.liferay.bookmarks.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PermissionedModel;
import com.liferay.portal.kernel.model.TreeModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the BookmarksFolder service. Represents a row in the &quot;BookmarksFolder&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see BookmarksFolderModel
 * @see com.liferay.bookmarks.model.impl.BookmarksFolderImpl
 * @see com.liferay.bookmarks.model.impl.BookmarksFolderModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.bookmarks.model.impl.BookmarksFolderImpl")
@ProviderType
public interface BookmarksFolder extends BookmarksFolderModel, PermissionedModel,
	TreeModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.bookmarks.model.impl.BookmarksFolderImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<BookmarksFolder, Long> FOLDER_ID_ACCESSOR = new Accessor<BookmarksFolder, Long>() {
			@Override
			public Long get(BookmarksFolder bookmarksFolder) {
				return bookmarksFolder.getFolderId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<BookmarksFolder> getTypeClass() {
				return BookmarksFolder.class;
			}
		};

	public java.util.List<java.lang.Long> getAncestorFolderIds()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.util.List<BookmarksFolder> getAncestors()
		throws com.liferay.portal.kernel.exception.PortalException;

	public BookmarksFolder getParentFolder()
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean isRoot();
}