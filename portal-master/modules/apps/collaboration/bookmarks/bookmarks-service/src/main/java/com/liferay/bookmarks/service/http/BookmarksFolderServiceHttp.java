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

package com.liferay.bookmarks.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.bookmarks.service.BookmarksFolderServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link BookmarksFolderServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BookmarksFolderServiceSoap
 * @see HttpPrincipal
 * @see BookmarksFolderServiceUtil
 * @generated
 */
@ProviderType
public class BookmarksFolderServiceHttp {
	public static com.liferay.bookmarks.model.BookmarksFolder addFolder(
		HttpPrincipal httpPrincipal, long parentFolderId,
		java.lang.String name, java.lang.String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"addFolder", _addFolderParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					parentFolderId, name, description, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.bookmarks.model.BookmarksFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteFolder(HttpPrincipal httpPrincipal, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"deleteFolder", _deleteFolderParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, folderId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteFolder(HttpPrincipal httpPrincipal, long folderId,
		boolean includeTrashedEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"deleteFolder", _deleteFolderParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					folderId, includeTrashedEntries);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.bookmarks.model.BookmarksFolder getFolder(
		HttpPrincipal httpPrincipal, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"getFolder", _getFolderParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, folderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.bookmarks.model.BookmarksFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<java.lang.Long> getFolderIds(
		HttpPrincipal httpPrincipal, long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"getFolderIds", _getFolderIdsParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<java.lang.Long>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.bookmarks.model.BookmarksFolder> getFolders(
		HttpPrincipal httpPrincipal, long groupId) {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"getFolders", _getFoldersParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.bookmarks.model.BookmarksFolder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.bookmarks.model.BookmarksFolder> getFolders(
		HttpPrincipal httpPrincipal, long groupId, long parentFolderId) {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"getFolders", _getFoldersParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentFolderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.bookmarks.model.BookmarksFolder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.bookmarks.model.BookmarksFolder> getFolders(
		HttpPrincipal httpPrincipal, long groupId, long parentFolderId,
		int start, int end) {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"getFolders", _getFoldersParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentFolderId, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.bookmarks.model.BookmarksFolder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.bookmarks.model.BookmarksFolder> getFolders(
		HttpPrincipal httpPrincipal, long groupId, long parentFolderId,
		int status, int start, int end) {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"getFolders", _getFoldersParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentFolderId, status, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.bookmarks.model.BookmarksFolder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<java.lang.Object> getFoldersAndEntries(
		HttpPrincipal httpPrincipal, long groupId, long folderId) {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"getFoldersAndEntries", _getFoldersAndEntriesParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<java.lang.Object>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<java.lang.Object> getFoldersAndEntries(
		HttpPrincipal httpPrincipal, long groupId, long folderId, int status) {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"getFoldersAndEntries",
					_getFoldersAndEntriesParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<java.lang.Object>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<java.lang.Object> getFoldersAndEntries(
		HttpPrincipal httpPrincipal, long groupId, long folderId, int status,
		int start, int end) {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"getFoldersAndEntries",
					_getFoldersAndEntriesParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, status, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<java.lang.Object>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getFoldersAndEntriesCount(HttpPrincipal httpPrincipal,
		long groupId, long folderId) {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"getFoldersAndEntriesCount",
					_getFoldersAndEntriesCountParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getFoldersAndEntriesCount(HttpPrincipal httpPrincipal,
		long groupId, long folderId, int status) {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"getFoldersAndEntriesCount",
					_getFoldersAndEntriesCountParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getFoldersCount(HttpPrincipal httpPrincipal,
		long groupId, long parentFolderId) {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"getFoldersCount", _getFoldersCountParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentFolderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getFoldersCount(HttpPrincipal httpPrincipal,
		long groupId, long parentFolderId, int status) {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"getFoldersCount", _getFoldersCountParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentFolderId, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void getSubfolderIds(HttpPrincipal httpPrincipal,
		java.util.List<java.lang.Long> folderIds, long groupId, long folderId) {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"getSubfolderIds", _getSubfolderIdsParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					folderIds, groupId, folderId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void getSubfolderIds(HttpPrincipal httpPrincipal,
		java.util.List<java.lang.Long> folderIds, long groupId, long folderId,
		boolean recurse) {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"getSubfolderIds", _getSubfolderIdsParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					folderIds, groupId, folderId, recurse);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<java.lang.Long> getSubfolderIds(
		HttpPrincipal httpPrincipal, long groupId, long folderId,
		boolean recurse) {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"getSubfolderIds", _getSubfolderIdsParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, recurse);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<java.lang.Long>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void mergeFolders(HttpPrincipal httpPrincipal, long folderId,
		long parentFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"mergeFolders", _mergeFoldersParameterTypes19);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					folderId, parentFolderId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.bookmarks.model.BookmarksFolder moveFolder(
		HttpPrincipal httpPrincipal, long folderId, long parentFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"moveFolder", _moveFolderParameterTypes20);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					folderId, parentFolderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.bookmarks.model.BookmarksFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.bookmarks.model.BookmarksFolder moveFolderFromTrash(
		HttpPrincipal httpPrincipal, long folderId, long parentFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"moveFolderFromTrash", _moveFolderFromTrashParameterTypes21);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					folderId, parentFolderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.bookmarks.model.BookmarksFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.bookmarks.model.BookmarksFolder moveFolderToTrash(
		HttpPrincipal httpPrincipal, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"moveFolderToTrash", _moveFolderToTrashParameterTypes22);

			MethodHandler methodHandler = new MethodHandler(methodKey, folderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.bookmarks.model.BookmarksFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void restoreFolderFromTrash(HttpPrincipal httpPrincipal,
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"restoreFolderFromTrash",
					_restoreFolderFromTrashParameterTypes23);

			MethodHandler methodHandler = new MethodHandler(methodKey, folderId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void subscribeFolder(HttpPrincipal httpPrincipal,
		long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"subscribeFolder", _subscribeFolderParameterTypes24);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void unsubscribeFolder(HttpPrincipal httpPrincipal,
		long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"unsubscribeFolder", _unsubscribeFolderParameterTypes25);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.bookmarks.model.BookmarksFolder updateFolder(
		HttpPrincipal httpPrincipal, long folderId, long parentFolderId,
		java.lang.String name, java.lang.String description,
		boolean mergeWithParentFolder,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"updateFolder", _updateFolderParameterTypes26);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					folderId, parentFolderId, name, description,
					mergeWithParentFolder, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.bookmarks.model.BookmarksFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.bookmarks.model.BookmarksFolder updateFolder(
		HttpPrincipal httpPrincipal, long folderId, long parentFolderId,
		java.lang.String name, java.lang.String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BookmarksFolderServiceUtil.class,
					"updateFolder", _updateFolderParameterTypes27);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					folderId, parentFolderId, name, description, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.bookmarks.model.BookmarksFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(BookmarksFolderServiceHttp.class);
	private static final Class<?>[] _addFolderParameterTypes0 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteFolderParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _deleteFolderParameterTypes2 = new Class[] {
			long.class, boolean.class
		};
	private static final Class<?>[] _getFolderParameterTypes3 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getFolderIdsParameterTypes4 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getFoldersParameterTypes5 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getFoldersParameterTypes6 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getFoldersParameterTypes7 = new Class[] {
			long.class, long.class, int.class, int.class
		};
	private static final Class<?>[] _getFoldersParameterTypes8 = new Class[] {
			long.class, long.class, int.class, int.class, int.class
		};
	private static final Class<?>[] _getFoldersAndEntriesParameterTypes9 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getFoldersAndEntriesParameterTypes10 = new Class[] {
			long.class, long.class, int.class
		};
	private static final Class<?>[] _getFoldersAndEntriesParameterTypes11 = new Class[] {
			long.class, long.class, int.class, int.class, int.class
		};
	private static final Class<?>[] _getFoldersAndEntriesCountParameterTypes12 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getFoldersAndEntriesCountParameterTypes13 = new Class[] {
			long.class, long.class, int.class
		};
	private static final Class<?>[] _getFoldersCountParameterTypes14 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getFoldersCountParameterTypes15 = new Class[] {
			long.class, long.class, int.class
		};
	private static final Class<?>[] _getSubfolderIdsParameterTypes16 = new Class[] {
			java.util.List.class, long.class, long.class
		};
	private static final Class<?>[] _getSubfolderIdsParameterTypes17 = new Class[] {
			java.util.List.class, long.class, long.class, boolean.class
		};
	private static final Class<?>[] _getSubfolderIdsParameterTypes18 = new Class[] {
			long.class, long.class, boolean.class
		};
	private static final Class<?>[] _mergeFoldersParameterTypes19 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _moveFolderParameterTypes20 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _moveFolderFromTrashParameterTypes21 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _moveFolderToTrashParameterTypes22 = new Class[] {
			long.class
		};
	private static final Class<?>[] _restoreFolderFromTrashParameterTypes23 = new Class[] {
			long.class
		};
	private static final Class<?>[] _subscribeFolderParameterTypes24 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _unsubscribeFolderParameterTypes25 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _updateFolderParameterTypes26 = new Class[] {
			long.class, long.class, java.lang.String.class,
			java.lang.String.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateFolderParameterTypes27 = new Class[] {
			long.class, long.class, java.lang.String.class,
			java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}