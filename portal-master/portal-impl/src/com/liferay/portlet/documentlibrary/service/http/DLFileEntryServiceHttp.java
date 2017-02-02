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

package com.liferay.portlet.documentlibrary.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.document.library.kernel.service.DLFileEntryServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link DLFileEntryServiceUtil} service utility. The
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
 * @see DLFileEntryServiceSoap
 * @see HttpPrincipal
 * @see DLFileEntryServiceUtil
 * @generated
 */
@ProviderType
public class DLFileEntryServiceHttp {
	public static com.liferay.document.library.kernel.model.DLFileEntry addFileEntry(
		HttpPrincipal httpPrincipal, long groupId, long repositoryId,
		long folderId, java.lang.String sourceFileName,
		java.lang.String mimeType, java.lang.String title,
		java.lang.String description, java.lang.String changeLog,
		long fileEntryTypeId,
		java.util.Map<java.lang.String, com.liferay.dynamic.data.mapping.kernel.DDMFormValues> ddmFormValuesMap,
		java.io.File file, java.io.InputStream is, long size,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"addFileEntry", _addFileEntryParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					repositoryId, folderId, sourceFileName, mimeType, title,
					description, changeLog, fileEntryTypeId, ddmFormValuesMap,
					file, is, size, serviceContext);

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

			return (com.liferay.document.library.kernel.model.DLFileEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.document.library.kernel.model.DLFileVersion cancelCheckOut(
		HttpPrincipal httpPrincipal, long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"cancelCheckOut", _cancelCheckOutParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					fileEntryId);

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

			return (com.liferay.document.library.kernel.model.DLFileVersion)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void checkInFileEntry(HttpPrincipal httpPrincipal,
		long fileEntryId, boolean major, java.lang.String changeLog,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"checkInFileEntry", _checkInFileEntryParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					fileEntryId, major, changeLog, serviceContext);

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

	public static void checkInFileEntry(HttpPrincipal httpPrincipal,
		long fileEntryId, java.lang.String lockUuid,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"checkInFileEntry", _checkInFileEntryParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					fileEntryId, lockUuid, serviceContext);

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

	public static com.liferay.document.library.kernel.model.DLFileEntry checkOutFileEntry(
		HttpPrincipal httpPrincipal, long fileEntryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"checkOutFileEntry", _checkOutFileEntryParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					fileEntryId, serviceContext);

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

			return (com.liferay.document.library.kernel.model.DLFileEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.document.library.kernel.model.DLFileEntry checkOutFileEntry(
		HttpPrincipal httpPrincipal, long fileEntryId, java.lang.String owner,
		long expirationTime,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"checkOutFileEntry", _checkOutFileEntryParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					fileEntryId, owner, expirationTime, serviceContext);

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

			return (com.liferay.document.library.kernel.model.DLFileEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.document.library.kernel.model.DLFileEntry copyFileEntry(
		HttpPrincipal httpPrincipal, long groupId, long repositoryId,
		long fileEntryId, long destFolderId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"copyFileEntry", _copyFileEntryParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					repositoryId, fileEntryId, destFolderId, serviceContext);

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

			return (com.liferay.document.library.kernel.model.DLFileEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteFileEntry(HttpPrincipal httpPrincipal,
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"deleteFileEntry", _deleteFileEntryParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					fileEntryId);

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

	public static void deleteFileEntry(HttpPrincipal httpPrincipal,
		long groupId, long folderId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"deleteFileEntry", _deleteFileEntryParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, title);

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

	public static void deleteFileVersion(HttpPrincipal httpPrincipal,
		long fileEntryId, java.lang.String version)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"deleteFileVersion", _deleteFileVersionParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					fileEntryId, version);

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

	public static com.liferay.document.library.kernel.model.DLFileEntry fetchFileEntryByImageId(
		HttpPrincipal httpPrincipal, long imageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"fetchFileEntryByImageId",
					_fetchFileEntryByImageIdParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey, imageId);

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

			return (com.liferay.document.library.kernel.model.DLFileEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.io.InputStream getFileAsStream(
		HttpPrincipal httpPrincipal, long fileEntryId, java.lang.String version)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"getFileAsStream", _getFileAsStreamParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					fileEntryId, version);

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

			return (java.io.InputStream)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.io.InputStream getFileAsStream(
		HttpPrincipal httpPrincipal, long fileEntryId,
		java.lang.String version, boolean incrementCounter)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"getFileAsStream", _getFileAsStreamParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					fileEntryId, version, incrementCounter);

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

			return (java.io.InputStream)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getFileEntries(
		HttpPrincipal httpPrincipal, long groupId, long folderId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFileEntry> obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"getFileEntries", _getFileEntriesParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, status, start, end, obc);

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

			return (java.util.List<com.liferay.document.library.kernel.model.DLFileEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getFileEntries(
		HttpPrincipal httpPrincipal, long groupId, long folderId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFileEntry> obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"getFileEntries", _getFileEntriesParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, start, end, obc);

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

			return (java.util.List<com.liferay.document.library.kernel.model.DLFileEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getFileEntries(
		HttpPrincipal httpPrincipal, long groupId, long folderId,
		long fileEntryTypeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFileEntry> obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"getFileEntries", _getFileEntriesParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, fileEntryTypeId, start, end, obc);

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

			return (java.util.List<com.liferay.document.library.kernel.model.DLFileEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getFileEntries(
		HttpPrincipal httpPrincipal, long groupId, long folderId,
		java.lang.String[] mimeTypes, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFileEntry> obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"getFileEntries", _getFileEntriesParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, mimeTypes, start, end, obc);

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

			return (java.util.List<com.liferay.document.library.kernel.model.DLFileEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getFileEntriesCount(HttpPrincipal httpPrincipal,
		long groupId, long folderId) {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"getFileEntriesCount", _getFileEntriesCountParameterTypes17);

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

	public static int getFileEntriesCount(HttpPrincipal httpPrincipal,
		long groupId, long folderId, int status) {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"getFileEntriesCount", _getFileEntriesCountParameterTypes18);

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

	public static int getFileEntriesCount(HttpPrincipal httpPrincipal,
		long groupId, long folderId, long fileEntryTypeId) {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"getFileEntriesCount", _getFileEntriesCountParameterTypes19);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, fileEntryTypeId);

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

	public static int getFileEntriesCount(HttpPrincipal httpPrincipal,
		long groupId, long folderId, java.lang.String[] mimeTypes) {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"getFileEntriesCount", _getFileEntriesCountParameterTypes20);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, mimeTypes);

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

	public static com.liferay.document.library.kernel.model.DLFileEntry getFileEntry(
		HttpPrincipal httpPrincipal, long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"getFileEntry", _getFileEntryParameterTypes21);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					fileEntryId);

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

			return (com.liferay.document.library.kernel.model.DLFileEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.document.library.kernel.model.DLFileEntry getFileEntry(
		HttpPrincipal httpPrincipal, long groupId, long folderId,
		java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"getFileEntry", _getFileEntryParameterTypes22);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderId, title);

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

			return (com.liferay.document.library.kernel.model.DLFileEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.document.library.kernel.model.DLFileEntry getFileEntryByUuidAndGroupId(
		HttpPrincipal httpPrincipal, java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"getFileEntryByUuidAndGroupId",
					_getFileEntryByUuidAndGroupIdParameterTypes23);

			MethodHandler methodHandler = new MethodHandler(methodKey, uuid,
					groupId);

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

			return (com.liferay.document.library.kernel.model.DLFileEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.lock.Lock getFileEntryLock(
		HttpPrincipal httpPrincipal, long fileEntryId) {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"getFileEntryLock", _getFileEntryLockParameterTypes24);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					fileEntryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.lock.Lock)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getFoldersFileEntriesCount(HttpPrincipal httpPrincipal,
		long groupId, java.util.List<java.lang.Long> folderIds, int status) {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"getFoldersFileEntriesCount",
					_getFoldersFileEntriesCountParameterTypes25);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					folderIds, status);

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

	public static java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getGroupFileEntries(
		HttpPrincipal httpPrincipal, long groupId, long userId,
		long rootFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFileEntry> obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"getGroupFileEntries", _getGroupFileEntriesParameterTypes26);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, rootFolderId, start, end, obc);

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

			return (java.util.List<com.liferay.document.library.kernel.model.DLFileEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getGroupFileEntries(
		HttpPrincipal httpPrincipal, long groupId, long userId,
		long repositoryId, long rootFolderId, java.lang.String[] mimeTypes,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFileEntry> obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"getGroupFileEntries", _getGroupFileEntriesParameterTypes27);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, repositoryId, rootFolderId, mimeTypes, status,
					start, end, obc);

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

			return (java.util.List<com.liferay.document.library.kernel.model.DLFileEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.document.library.kernel.model.DLFileEntry> getGroupFileEntries(
		HttpPrincipal httpPrincipal, long groupId, long userId,
		long rootFolderId, java.lang.String[] mimeTypes, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFileEntry> obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"getGroupFileEntries", _getGroupFileEntriesParameterTypes28);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, rootFolderId, mimeTypes, status, start, end, obc);

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

			return (java.util.List<com.liferay.document.library.kernel.model.DLFileEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getGroupFileEntriesCount(HttpPrincipal httpPrincipal,
		long groupId, long userId, long rootFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"getGroupFileEntriesCount",
					_getGroupFileEntriesCountParameterTypes29);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, rootFolderId);

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

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getGroupFileEntriesCount(HttpPrincipal httpPrincipal,
		long groupId, long userId, long repositoryId, long rootFolderId,
		java.lang.String[] mimeTypes, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"getGroupFileEntriesCount",
					_getGroupFileEntriesCountParameterTypes30);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, repositoryId, rootFolderId, mimeTypes, status);

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

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getGroupFileEntriesCount(HttpPrincipal httpPrincipal,
		long groupId, long userId, long rootFolderId,
		java.lang.String[] mimeTypes, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"getGroupFileEntriesCount",
					_getGroupFileEntriesCountParameterTypes31);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, rootFolderId, mimeTypes, status);

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

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static boolean hasFileEntryLock(HttpPrincipal httpPrincipal,
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"hasFileEntryLock", _hasFileEntryLockParameterTypes32);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					fileEntryId);

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

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static boolean isFileEntryCheckedOut(HttpPrincipal httpPrincipal,
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"isFileEntryCheckedOut",
					_isFileEntryCheckedOutParameterTypes33);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					fileEntryId);

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

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static boolean isKeepFileVersionLabel(HttpPrincipal httpPrincipal,
		long fileEntryId, boolean majorVersion,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"isKeepFileVersionLabel",
					_isKeepFileVersionLabelParameterTypes34);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					fileEntryId, majorVersion, serviceContext);

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

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static boolean isKeepFileVersionLabel(HttpPrincipal httpPrincipal,
		long fileEntryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"isKeepFileVersionLabel",
					_isKeepFileVersionLabelParameterTypes35);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					fileEntryId, serviceContext);

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

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.document.library.kernel.model.DLFileEntry moveFileEntry(
		HttpPrincipal httpPrincipal, long fileEntryId, long newFolderId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"moveFileEntry", _moveFileEntryParameterTypes36);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					fileEntryId, newFolderId, serviceContext);

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

			return (com.liferay.document.library.kernel.model.DLFileEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.lock.Lock refreshFileEntryLock(
		HttpPrincipal httpPrincipal, java.lang.String lockUuid, long companyId,
		long expirationTime)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"refreshFileEntryLock",
					_refreshFileEntryLockParameterTypes37);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					lockUuid, companyId, expirationTime);

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

			return (com.liferay.portal.kernel.lock.Lock)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void revertFileEntry(HttpPrincipal httpPrincipal,
		long fileEntryId, java.lang.String version,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"revertFileEntry", _revertFileEntryParameterTypes38);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					fileEntryId, version, serviceContext);

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

	public static com.liferay.portal.kernel.search.Hits search(
		HttpPrincipal httpPrincipal, long groupId, long creatorUserId,
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"search", _searchParameterTypes39);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					creatorUserId, status, start, end);

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

			return (com.liferay.portal.kernel.search.Hits)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.search.Hits search(
		HttpPrincipal httpPrincipal, long groupId, long creatorUserId,
		long folderId, java.lang.String[] mimeTypes, int status, int start,
		int end) throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"search", _searchParameterTypes40);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					creatorUserId, folderId, mimeTypes, status, start, end);

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

			return (com.liferay.portal.kernel.search.Hits)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.document.library.kernel.model.DLFileEntry updateFileEntry(
		HttpPrincipal httpPrincipal, long fileEntryId,
		java.lang.String sourceFileName, java.lang.String mimeType,
		java.lang.String title, java.lang.String description,
		java.lang.String changeLog, boolean majorVersion, long fileEntryTypeId,
		java.util.Map<java.lang.String, com.liferay.dynamic.data.mapping.kernel.DDMFormValues> ddmFormValuesMap,
		java.io.File file, java.io.InputStream is, long size,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"updateFileEntry", _updateFileEntryParameterTypes41);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					fileEntryId, sourceFileName, mimeType, title, description,
					changeLog, majorVersion, fileEntryTypeId, ddmFormValuesMap,
					file, is, size, serviceContext);

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

			return (com.liferay.document.library.kernel.model.DLFileEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.document.library.kernel.model.DLFileEntry updateStatus(
		HttpPrincipal httpPrincipal, long userId, long fileVersionId,
		int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"updateStatus", _updateStatusParameterTypes42);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					fileVersionId, status, serviceContext, workflowContext);

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

			return (com.liferay.document.library.kernel.model.DLFileEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static boolean verifyFileEntryCheckOut(HttpPrincipal httpPrincipal,
		long fileEntryId, java.lang.String lockUuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"verifyFileEntryCheckOut",
					_verifyFileEntryCheckOutParameterTypes43);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					fileEntryId, lockUuid);

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

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static boolean verifyFileEntryLock(HttpPrincipal httpPrincipal,
		long fileEntryId, java.lang.String lockUuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DLFileEntryServiceUtil.class,
					"verifyFileEntryLock", _verifyFileEntryLockParameterTypes44);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					fileEntryId, lockUuid);

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

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(DLFileEntryServiceHttp.class);
	private static final Class<?>[] _addFileEntryParameterTypes0 = new Class[] {
			long.class, long.class, long.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class, long.class,
			java.util.Map.class, java.io.File.class, java.io.InputStream.class,
			long.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _cancelCheckOutParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _checkInFileEntryParameterTypes2 = new Class[] {
			long.class, boolean.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _checkInFileEntryParameterTypes3 = new Class[] {
			long.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _checkOutFileEntryParameterTypes4 = new Class[] {
			long.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _checkOutFileEntryParameterTypes5 = new Class[] {
			long.class, java.lang.String.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _copyFileEntryParameterTypes6 = new Class[] {
			long.class, long.class, long.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteFileEntryParameterTypes7 = new Class[] {
			long.class
		};
	private static final Class<?>[] _deleteFileEntryParameterTypes8 = new Class[] {
			long.class, long.class, java.lang.String.class
		};
	private static final Class<?>[] _deleteFileVersionParameterTypes9 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _fetchFileEntryByImageIdParameterTypes10 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getFileAsStreamParameterTypes11 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getFileAsStreamParameterTypes12 = new Class[] {
			long.class, java.lang.String.class, boolean.class
		};
	private static final Class<?>[] _getFileEntriesParameterTypes13 = new Class[] {
			long.class, long.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getFileEntriesParameterTypes14 = new Class[] {
			long.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getFileEntriesParameterTypes15 = new Class[] {
			long.class, long.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getFileEntriesParameterTypes16 = new Class[] {
			long.class, long.class, java.lang.String[].class, int.class,
			int.class, com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getFileEntriesCountParameterTypes17 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _getFileEntriesCountParameterTypes18 = new Class[] {
			long.class, long.class, int.class
		};
	private static final Class<?>[] _getFileEntriesCountParameterTypes19 = new Class[] {
			long.class, long.class, long.class
		};
	private static final Class<?>[] _getFileEntriesCountParameterTypes20 = new Class[] {
			long.class, long.class, java.lang.String[].class
		};
	private static final Class<?>[] _getFileEntryParameterTypes21 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getFileEntryParameterTypes22 = new Class[] {
			long.class, long.class, java.lang.String.class
		};
	private static final Class<?>[] _getFileEntryByUuidAndGroupIdParameterTypes23 =
		new Class[] { java.lang.String.class, long.class };
	private static final Class<?>[] _getFileEntryLockParameterTypes24 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getFoldersFileEntriesCountParameterTypes25 = new Class[] {
			long.class, java.util.List.class, int.class
		};
	private static final Class<?>[] _getGroupFileEntriesParameterTypes26 = new Class[] {
			long.class, long.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getGroupFileEntriesParameterTypes27 = new Class[] {
			long.class, long.class, long.class, long.class,
			java.lang.String[].class, int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getGroupFileEntriesParameterTypes28 = new Class[] {
			long.class, long.class, long.class, java.lang.String[].class,
			int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getGroupFileEntriesCountParameterTypes29 = new Class[] {
			long.class, long.class, long.class
		};
	private static final Class<?>[] _getGroupFileEntriesCountParameterTypes30 = new Class[] {
			long.class, long.class, long.class, long.class,
			java.lang.String[].class, int.class
		};
	private static final Class<?>[] _getGroupFileEntriesCountParameterTypes31 = new Class[] {
			long.class, long.class, long.class, java.lang.String[].class,
			int.class
		};
	private static final Class<?>[] _hasFileEntryLockParameterTypes32 = new Class[] {
			long.class
		};
	private static final Class<?>[] _isFileEntryCheckedOutParameterTypes33 = new Class[] {
			long.class
		};
	private static final Class<?>[] _isKeepFileVersionLabelParameterTypes34 = new Class[] {
			long.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _isKeepFileVersionLabelParameterTypes35 = new Class[] {
			long.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _moveFileEntryParameterTypes36 = new Class[] {
			long.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _refreshFileEntryLockParameterTypes37 = new Class[] {
			java.lang.String.class, long.class, long.class
		};
	private static final Class<?>[] _revertFileEntryParameterTypes38 = new Class[] {
			long.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _searchParameterTypes39 = new Class[] {
			long.class, long.class, int.class, int.class, int.class
		};
	private static final Class<?>[] _searchParameterTypes40 = new Class[] {
			long.class, long.class, long.class, java.lang.String[].class,
			int.class, int.class, int.class
		};
	private static final Class<?>[] _updateFileEntryParameterTypes41 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, boolean.class, long.class,
			java.util.Map.class, java.io.File.class, java.io.InputStream.class,
			long.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateStatusParameterTypes42 = new Class[] {
			long.class, long.class, int.class,
			com.liferay.portal.kernel.service.ServiceContext.class,
			java.util.Map.class
		};
	private static final Class<?>[] _verifyFileEntryCheckOutParameterTypes43 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _verifyFileEntryLockParameterTypes44 = new Class[] {
			long.class, java.lang.String.class
		};
}