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

package com.liferay.portlet.blogs.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.blogs.kernel.service.BlogsEntryServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link BlogsEntryServiceUtil} service utility. The
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
 * @see BlogsEntryServiceSoap
 * @see HttpPrincipal
 * @see BlogsEntryServiceUtil
 * @generated
 */
@ProviderType
public class BlogsEntryServiceHttp {
	public static com.liferay.blogs.kernel.model.BlogsEntry addEntry(
		HttpPrincipal httpPrincipal, java.lang.String title,
		java.lang.String description, java.lang.String content,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, boolean allowPingbacks,
		boolean allowTrackbacks, java.lang.String[] trackbacks,
		boolean smallImage, java.lang.String smallImageURL,
		java.lang.String smallImageFileName,
		java.io.InputStream smallImageInputStream,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"addEntry", _addEntryParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, title,
					description, content, displayDateMonth, displayDateDay,
					displayDateYear, displayDateHour, displayDateMinute,
					allowPingbacks, allowTrackbacks, trackbacks, smallImage,
					smallImageURL, smallImageFileName, smallImageInputStream,
					serviceContext);

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

			return (com.liferay.blogs.kernel.model.BlogsEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.blogs.kernel.model.BlogsEntry addEntry(
		HttpPrincipal httpPrincipal, java.lang.String title,
		java.lang.String subtitle, java.lang.String description,
		java.lang.String content, int displayDateMonth, int displayDateDay,
		int displayDateYear, int displayDateHour, int displayDateMinute,
		boolean allowPingbacks, boolean allowTrackbacks,
		java.lang.String[] trackbacks, java.lang.String coverImageCaption,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector coverImageImageSelector,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector smallImageImageSelector,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"addEntry", _addEntryParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, title,
					subtitle, description, content, displayDateMonth,
					displayDateDay, displayDateYear, displayDateHour,
					displayDateMinute, allowPingbacks, allowTrackbacks,
					trackbacks, coverImageCaption, coverImageImageSelector,
					smallImageImageSelector, serviceContext);

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

			return (com.liferay.blogs.kernel.model.BlogsEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteEntry(HttpPrincipal httpPrincipal, long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"deleteEntry", _deleteEntryParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, entryId);

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

	public static java.util.List<com.liferay.blogs.kernel.model.BlogsEntry> getCompanyEntries(
		HttpPrincipal httpPrincipal, long companyId,
		java.util.Date displayDate, int status, int max)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"getCompanyEntries", _getCompanyEntriesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, displayDate, status, max);

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

			return (java.util.List<com.liferay.blogs.kernel.model.BlogsEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.lang.String getCompanyEntriesRSS(
		HttpPrincipal httpPrincipal, long companyId,
		java.util.Date displayDate, int status, int max, java.lang.String type,
		double version, java.lang.String displayStyle,
		java.lang.String feedURL, java.lang.String entryURL,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"getCompanyEntriesRSS", _getCompanyEntriesRSSParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, displayDate, status, max, type, version,
					displayStyle, feedURL, entryURL, themeDisplay);

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

			return (java.lang.String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.blogs.kernel.model.BlogsEntry getEntry(
		HttpPrincipal httpPrincipal, long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"getEntry", _getEntryParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, entryId);

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

			return (com.liferay.blogs.kernel.model.BlogsEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.blogs.kernel.model.BlogsEntry getEntry(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String urlTitle)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"getEntry", _getEntryParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					urlTitle);

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

			return (com.liferay.blogs.kernel.model.BlogsEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.blogs.kernel.model.BlogsEntry> getGroupEntries(
		HttpPrincipal httpPrincipal, long groupId, java.util.Date displayDate,
		int status, int max) {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"getGroupEntries", _getGroupEntriesParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					displayDate, status, max);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.blogs.kernel.model.BlogsEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.blogs.kernel.model.BlogsEntry> getGroupEntries(
		HttpPrincipal httpPrincipal, long groupId, java.util.Date displayDate,
		int status, int start, int end) {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"getGroupEntries", _getGroupEntriesParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					displayDate, status, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.blogs.kernel.model.BlogsEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.blogs.kernel.model.BlogsEntry> getGroupEntries(
		HttpPrincipal httpPrincipal, long groupId, int status, int max) {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"getGroupEntries", _getGroupEntriesParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					status, max);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.blogs.kernel.model.BlogsEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.blogs.kernel.model.BlogsEntry> getGroupEntries(
		HttpPrincipal httpPrincipal, long groupId, int status, int start,
		int end) {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"getGroupEntries", _getGroupEntriesParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					status, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.blogs.kernel.model.BlogsEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.blogs.kernel.model.BlogsEntry> getGroupEntries(
		HttpPrincipal httpPrincipal, long groupId, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.blogs.kernel.model.BlogsEntry> obc) {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"getGroupEntries", _getGroupEntriesParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					status, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.blogs.kernel.model.BlogsEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getGroupEntriesCount(HttpPrincipal httpPrincipal,
		long groupId, java.util.Date displayDate, int status) {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"getGroupEntriesCount",
					_getGroupEntriesCountParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					displayDate, status);

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

	public static int getGroupEntriesCount(HttpPrincipal httpPrincipal,
		long groupId, int status) {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"getGroupEntriesCount",
					_getGroupEntriesCountParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					status);

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

	public static java.lang.String getGroupEntriesRSS(
		HttpPrincipal httpPrincipal, long groupId, java.util.Date displayDate,
		int status, int max, java.lang.String type, double version,
		java.lang.String displayStyle, java.lang.String feedURL,
		java.lang.String entryURL,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"getGroupEntriesRSS", _getGroupEntriesRSSParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					displayDate, status, max, type, version, displayStyle,
					feedURL, entryURL, themeDisplay);

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

			return (java.lang.String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.blogs.kernel.model.BlogsEntry> getGroupsEntries(
		HttpPrincipal httpPrincipal, long companyId, long groupId,
		java.util.Date displayDate, int status, int max)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"getGroupsEntries", _getGroupsEntriesParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, groupId, displayDate, status, max);

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

			return (java.util.List<com.liferay.blogs.kernel.model.BlogsEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.blogs.kernel.model.BlogsEntry> getGroupUserEntries(
		HttpPrincipal httpPrincipal, long groupId, long userId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.blogs.kernel.model.BlogsEntry> obc) {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"getGroupUserEntries", _getGroupUserEntriesParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, status, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.blogs.kernel.model.BlogsEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.blogs.kernel.model.BlogsEntry> getGroupUserEntries(
		HttpPrincipal httpPrincipal, long groupId, long userId, int[] statuses,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.blogs.kernel.model.BlogsEntry> obc) {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"getGroupUserEntries", _getGroupUserEntriesParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, statuses, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.blogs.kernel.model.BlogsEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getGroupUserEntriesCount(HttpPrincipal httpPrincipal,
		long groupId, long userId, int status) {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"getGroupUserEntriesCount",
					_getGroupUserEntriesCountParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, status);

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

	public static int getGroupUserEntriesCount(HttpPrincipal httpPrincipal,
		long groupId, long userId, int[] statuses) {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"getGroupUserEntriesCount",
					_getGroupUserEntriesCountParameterTypes19);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, statuses);

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

	public static java.util.List<com.liferay.blogs.kernel.model.BlogsEntry> getOrganizationEntries(
		HttpPrincipal httpPrincipal, long organizationId,
		java.util.Date displayDate, int status, int max)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"getOrganizationEntries",
					_getOrganizationEntriesParameterTypes20);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId, displayDate, status, max);

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

			return (java.util.List<com.liferay.blogs.kernel.model.BlogsEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.lang.String getOrganizationEntriesRSS(
		HttpPrincipal httpPrincipal, long organizationId,
		java.util.Date displayDate, int status, int max, java.lang.String type,
		double version, java.lang.String displayStyle,
		java.lang.String feedURL, java.lang.String entryURL,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"getOrganizationEntriesRSS",
					_getOrganizationEntriesRSSParameterTypes21);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					organizationId, displayDate, status, max, type, version,
					displayStyle, feedURL, entryURL, themeDisplay);

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

			return (java.lang.String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.blogs.kernel.model.BlogsEntry moveEntryToTrash(
		HttpPrincipal httpPrincipal, long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"moveEntryToTrash", _moveEntryToTrashParameterTypes22);

			MethodHandler methodHandler = new MethodHandler(methodKey, entryId);

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

			return (com.liferay.blogs.kernel.model.BlogsEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void restoreEntryFromTrash(HttpPrincipal httpPrincipal,
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"restoreEntryFromTrash",
					_restoreEntryFromTrashParameterTypes23);

			MethodHandler methodHandler = new MethodHandler(methodKey, entryId);

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

	public static void subscribe(HttpPrincipal httpPrincipal, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"subscribe", _subscribeParameterTypes24);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

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

	public static void unsubscribe(HttpPrincipal httpPrincipal, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"unsubscribe", _unsubscribeParameterTypes25);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

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

	public static com.liferay.blogs.kernel.model.BlogsEntry updateEntry(
		HttpPrincipal httpPrincipal, long entryId, java.lang.String title,
		java.lang.String description, java.lang.String content,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, boolean allowPingbacks,
		boolean allowTrackbacks, java.lang.String[] trackbacks,
		boolean smallImage, java.lang.String smallImageURL,
		java.lang.String smallImageFileName,
		java.io.InputStream smallImageInputStream,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"updateEntry", _updateEntryParameterTypes26);

			MethodHandler methodHandler = new MethodHandler(methodKey, entryId,
					title, description, content, displayDateMonth,
					displayDateDay, displayDateYear, displayDateHour,
					displayDateMinute, allowPingbacks, allowTrackbacks,
					trackbacks, smallImage, smallImageURL, smallImageFileName,
					smallImageInputStream, serviceContext);

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

			return (com.liferay.blogs.kernel.model.BlogsEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.blogs.kernel.model.BlogsEntry updateEntry(
		HttpPrincipal httpPrincipal, long entryId, java.lang.String title,
		java.lang.String subtitle, java.lang.String description,
		java.lang.String content, int displayDateMonth, int displayDateDay,
		int displayDateYear, int displayDateHour, int displayDateMinute,
		boolean allowPingbacks, boolean allowTrackbacks,
		java.lang.String[] trackbacks, java.lang.String coverImageCaption,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector coverImageImageSelector,
		com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector smallImageImageSelector,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(BlogsEntryServiceUtil.class,
					"updateEntry", _updateEntryParameterTypes27);

			MethodHandler methodHandler = new MethodHandler(methodKey, entryId,
					title, subtitle, description, content, displayDateMonth,
					displayDateDay, displayDateYear, displayDateHour,
					displayDateMinute, allowPingbacks, allowTrackbacks,
					trackbacks, coverImageCaption, coverImageImageSelector,
					smallImageImageSelector, serviceContext);

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

			return (com.liferay.blogs.kernel.model.BlogsEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(BlogsEntryServiceHttp.class);
	private static final Class<?>[] _addEntryParameterTypes0 = new Class[] {
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, int.class, int.class, int.class, int.class,
			int.class, boolean.class, boolean.class, java.lang.String[].class,
			boolean.class, java.lang.String.class, java.lang.String.class,
			java.io.InputStream.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addEntryParameterTypes1 = new Class[] {
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class, int.class, int.class,
			int.class, int.class, int.class, boolean.class, boolean.class,
			java.lang.String[].class, java.lang.String.class,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector.class,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteEntryParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCompanyEntriesParameterTypes3 = new Class[] {
			long.class, java.util.Date.class, int.class, int.class
		};
	private static final Class<?>[] _getCompanyEntriesRSSParameterTypes4 = new Class[] {
			long.class, java.util.Date.class, int.class, int.class,
			java.lang.String.class, double.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			com.liferay.portal.kernel.theme.ThemeDisplay.class
		};
	private static final Class<?>[] _getEntryParameterTypes5 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getEntryParameterTypes6 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getGroupEntriesParameterTypes7 = new Class[] {
			long.class, java.util.Date.class, int.class, int.class
		};
	private static final Class<?>[] _getGroupEntriesParameterTypes8 = new Class[] {
			long.class, java.util.Date.class, int.class, int.class, int.class
		};
	private static final Class<?>[] _getGroupEntriesParameterTypes9 = new Class[] {
			long.class, int.class, int.class
		};
	private static final Class<?>[] _getGroupEntriesParameterTypes10 = new Class[] {
			long.class, int.class, int.class, int.class
		};
	private static final Class<?>[] _getGroupEntriesParameterTypes11 = new Class[] {
			long.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getGroupEntriesCountParameterTypes12 = new Class[] {
			long.class, java.util.Date.class, int.class
		};
	private static final Class<?>[] _getGroupEntriesCountParameterTypes13 = new Class[] {
			long.class, int.class
		};
	private static final Class<?>[] _getGroupEntriesRSSParameterTypes14 = new Class[] {
			long.class, java.util.Date.class, int.class, int.class,
			java.lang.String.class, double.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			com.liferay.portal.kernel.theme.ThemeDisplay.class
		};
	private static final Class<?>[] _getGroupsEntriesParameterTypes15 = new Class[] {
			long.class, long.class, java.util.Date.class, int.class, int.class
		};
	private static final Class<?>[] _getGroupUserEntriesParameterTypes16 = new Class[] {
			long.class, long.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getGroupUserEntriesParameterTypes17 = new Class[] {
			long.class, long.class, int[].class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getGroupUserEntriesCountParameterTypes18 = new Class[] {
			long.class, long.class, int.class
		};
	private static final Class<?>[] _getGroupUserEntriesCountParameterTypes19 = new Class[] {
			long.class, long.class, int[].class
		};
	private static final Class<?>[] _getOrganizationEntriesParameterTypes20 = new Class[] {
			long.class, java.util.Date.class, int.class, int.class
		};
	private static final Class<?>[] _getOrganizationEntriesRSSParameterTypes21 = new Class[] {
			long.class, java.util.Date.class, int.class, int.class,
			java.lang.String.class, double.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			com.liferay.portal.kernel.theme.ThemeDisplay.class
		};
	private static final Class<?>[] _moveEntryToTrashParameterTypes22 = new Class[] {
			long.class
		};
	private static final Class<?>[] _restoreEntryFromTrashParameterTypes23 = new Class[] {
			long.class
		};
	private static final Class<?>[] _subscribeParameterTypes24 = new Class[] {
			long.class
		};
	private static final Class<?>[] _unsubscribeParameterTypes25 = new Class[] {
			long.class
		};
	private static final Class<?>[] _updateEntryParameterTypes26 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			java.lang.String.class, int.class, int.class, int.class, int.class,
			int.class, boolean.class, boolean.class, java.lang.String[].class,
			boolean.class, java.lang.String.class, java.lang.String.class,
			java.io.InputStream.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateEntryParameterTypes27 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class, int.class, int.class,
			int.class, int.class, int.class, boolean.class, boolean.class,
			java.lang.String[].class, java.lang.String.class,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector.class,
			com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}