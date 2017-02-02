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

package com.liferay.portlet.asset.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.kernel.service.AssetVocabularyServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;

import java.rmi.RemoteException;

import java.util.Locale;
import java.util.Map;

/**
 * Provides the SOAP utility for the
 * {@link AssetVocabularyServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.asset.kernel.model.AssetVocabularySoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.asset.kernel.model.AssetVocabulary}, that is translated to a
 * {@link com.liferay.asset.kernel.model.AssetVocabularySoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetVocabularyServiceHttp
 * @see com.liferay.asset.kernel.model.AssetVocabularySoap
 * @see AssetVocabularyServiceUtil
 * @generated
 */
@ProviderType
public class AssetVocabularyServiceSoap {
	public static com.liferay.asset.kernel.model.AssetVocabularySoap addVocabulary(
		long groupId, java.lang.String title,
		java.lang.String[] titleMapLanguageIds,
		java.lang.String[] titleMapValues,
		java.lang.String[] descriptionMapLanguageIds,
		java.lang.String[] descriptionMapValues, java.lang.String settings,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(titleMapLanguageIds,
					titleMapValues);
			Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(descriptionMapLanguageIds,
					descriptionMapValues);

			com.liferay.asset.kernel.model.AssetVocabulary returnValue = AssetVocabularyServiceUtil.addVocabulary(groupId,
					title, titleMap, descriptionMap, settings, serviceContext);

			return com.liferay.asset.kernel.model.AssetVocabularySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.kernel.model.AssetVocabularySoap addVocabulary(
		long groupId, java.lang.String title,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.asset.kernel.model.AssetVocabulary returnValue = AssetVocabularyServiceUtil.addVocabulary(groupId,
					title, serviceContext);

			return com.liferay.asset.kernel.model.AssetVocabularySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.kernel.model.AssetVocabularySoap[] deleteVocabularies(
		long[] vocabularyIds,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> returnValue =
				AssetVocabularyServiceUtil.deleteVocabularies(vocabularyIds,
					serviceContext);

			return com.liferay.asset.kernel.model.AssetVocabularySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteVocabulary(long vocabularyId)
		throws RemoteException {
		try {
			AssetVocabularyServiceUtil.deleteVocabulary(vocabularyId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.kernel.model.AssetVocabularySoap fetchVocabulary(
		long vocabularyId) throws RemoteException {
		try {
			com.liferay.asset.kernel.model.AssetVocabulary returnValue = AssetVocabularyServiceUtil.fetchVocabulary(vocabularyId);

			return com.liferay.asset.kernel.model.AssetVocabularySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* @deprecated As of 7.0.0, with no direct replacement
	*/
	@Deprecated
	public static com.liferay.asset.kernel.model.AssetVocabularySoap[] getCompanyVocabularies(
		long companyId) throws RemoteException {
		try {
			java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> returnValue =
				AssetVocabularyServiceUtil.getCompanyVocabularies(companyId);

			return com.liferay.asset.kernel.model.AssetVocabularySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.kernel.model.AssetVocabularySoap[] getGroupsVocabularies(
		long[] groupIds) throws RemoteException {
		try {
			java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> returnValue =
				AssetVocabularyServiceUtil.getGroupsVocabularies(groupIds);

			return com.liferay.asset.kernel.model.AssetVocabularySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.kernel.model.AssetVocabularySoap[] getGroupsVocabularies(
		long[] groupIds, java.lang.String className) throws RemoteException {
		try {
			java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> returnValue =
				AssetVocabularyServiceUtil.getGroupsVocabularies(groupIds,
					className);

			return com.liferay.asset.kernel.model.AssetVocabularySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.kernel.model.AssetVocabularySoap[] getGroupsVocabularies(
		long[] groupIds, java.lang.String className, long classTypePK)
		throws RemoteException {
		try {
			java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> returnValue =
				AssetVocabularyServiceUtil.getGroupsVocabularies(groupIds,
					className, classTypePK);

			return com.liferay.asset.kernel.model.AssetVocabularySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.kernel.model.AssetVocabularySoap[] getGroupVocabularies(
		long groupId) throws RemoteException {
		try {
			java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> returnValue =
				AssetVocabularyServiceUtil.getGroupVocabularies(groupId);

			return com.liferay.asset.kernel.model.AssetVocabularySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.kernel.model.AssetVocabularySoap[] getGroupVocabularies(
		long groupId, boolean createDefaultVocabulary)
		throws RemoteException {
		try {
			java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> returnValue =
				AssetVocabularyServiceUtil.getGroupVocabularies(groupId,
					createDefaultVocabulary);

			return com.liferay.asset.kernel.model.AssetVocabularySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.kernel.model.AssetVocabularySoap[] getGroupVocabularies(
		long groupId, boolean createDefaultVocabulary, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetVocabulary> obc)
		throws RemoteException {
		try {
			java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> returnValue =
				AssetVocabularyServiceUtil.getGroupVocabularies(groupId,
					createDefaultVocabulary, start, end, obc);

			return com.liferay.asset.kernel.model.AssetVocabularySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.kernel.model.AssetVocabularySoap[] getGroupVocabularies(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetVocabulary> obc)
		throws RemoteException {
		try {
			java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> returnValue =
				AssetVocabularyServiceUtil.getGroupVocabularies(groupId, start,
					end, obc);

			return com.liferay.asset.kernel.model.AssetVocabularySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.kernel.model.AssetVocabularySoap[] getGroupVocabularies(
		long groupId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetVocabulary> obc)
		throws RemoteException {
		try {
			java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> returnValue =
				AssetVocabularyServiceUtil.getGroupVocabularies(groupId, name,
					start, end, obc);

			return com.liferay.asset.kernel.model.AssetVocabularySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.kernel.model.AssetVocabularySoap[] getGroupVocabularies(
		long[] groupIds) throws RemoteException {
		try {
			java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> returnValue =
				AssetVocabularyServiceUtil.getGroupVocabularies(groupIds);

			return com.liferay.asset.kernel.model.AssetVocabularySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getGroupVocabulariesCount(long groupId)
		throws RemoteException {
		try {
			int returnValue = AssetVocabularyServiceUtil.getGroupVocabulariesCount(groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getGroupVocabulariesCount(long groupId,
		java.lang.String name) throws RemoteException {
		try {
			int returnValue = AssetVocabularyServiceUtil.getGroupVocabulariesCount(groupId,
					name);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getGroupVocabulariesCount(long[] groupIds)
		throws RemoteException {
		try {
			int returnValue = AssetVocabularyServiceUtil.getGroupVocabulariesCount(groupIds);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.kernel.model.AssetVocabularyDisplay getGroupVocabulariesDisplay(
		long groupId, java.lang.String name, int start, int end,
		boolean addDefaultVocabulary,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetVocabulary> obc)
		throws RemoteException {
		try {
			com.liferay.asset.kernel.model.AssetVocabularyDisplay returnValue = AssetVocabularyServiceUtil.getGroupVocabulariesDisplay(groupId,
					name, start, end, addDefaultVocabulary, obc);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.kernel.model.AssetVocabularyDisplay getGroupVocabulariesDisplay(
		long groupId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetVocabulary> obc)
		throws RemoteException {
		try {
			com.liferay.asset.kernel.model.AssetVocabularyDisplay returnValue = AssetVocabularyServiceUtil.getGroupVocabulariesDisplay(groupId,
					name, start, end, obc);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link
	AssetUtil#filterVocabularyIds(PermissionChecker, long[])}
	*/
	@Deprecated
	public static com.liferay.asset.kernel.model.AssetVocabularySoap[] getVocabularies(
		long[] vocabularyIds) throws RemoteException {
		try {
			java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> returnValue =
				AssetVocabularyServiceUtil.getVocabularies(vocabularyIds);

			return com.liferay.asset.kernel.model.AssetVocabularySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.kernel.model.AssetVocabularySoap getVocabulary(
		long vocabularyId) throws RemoteException {
		try {
			com.liferay.asset.kernel.model.AssetVocabulary returnValue = AssetVocabularyServiceUtil.getVocabulary(vocabularyId);

			return com.liferay.asset.kernel.model.AssetVocabularySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.kernel.model.AssetVocabularyDisplay searchVocabulariesDisplay(
		long groupId, java.lang.String title, boolean addDefaultVocabulary,
		int start, int end) throws RemoteException {
		try {
			com.liferay.asset.kernel.model.AssetVocabularyDisplay returnValue = AssetVocabularyServiceUtil.searchVocabulariesDisplay(groupId,
					title, addDefaultVocabulary, start, end);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.kernel.model.AssetVocabularyDisplay searchVocabulariesDisplay(
		long groupId, java.lang.String title, boolean addDefaultVocabulary,
		int start, int end, com.liferay.portal.kernel.search.Sort sort)
		throws RemoteException {
		try {
			com.liferay.asset.kernel.model.AssetVocabularyDisplay returnValue = AssetVocabularyServiceUtil.searchVocabulariesDisplay(groupId,
					title, addDefaultVocabulary, start, end, sort);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.kernel.model.AssetVocabularySoap updateVocabulary(
		long vocabularyId, java.lang.String title,
		java.lang.String[] titleMapLanguageIds,
		java.lang.String[] titleMapValues,
		java.lang.String[] descriptionMapLanguageIds,
		java.lang.String[] descriptionMapValues, java.lang.String settings,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(titleMapLanguageIds,
					titleMapValues);
			Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(descriptionMapLanguageIds,
					descriptionMapValues);

			com.liferay.asset.kernel.model.AssetVocabulary returnValue = AssetVocabularyServiceUtil.updateVocabulary(vocabularyId,
					title, titleMap, descriptionMap, settings, serviceContext);

			return com.liferay.asset.kernel.model.AssetVocabularySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AssetVocabularyServiceSoap.class);
}