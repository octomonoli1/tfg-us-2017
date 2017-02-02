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

package com.liferay.portal.kernel.service;

import aQute.bnd.annotation.ProviderType;

/**
 * Provides a wrapper for {@link PhoneLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PhoneLocalService
 * @generated
 */
@ProviderType
public class PhoneLocalServiceWrapper implements PhoneLocalService,
	ServiceWrapper<PhoneLocalService> {
	public PhoneLocalServiceWrapper(PhoneLocalService phoneLocalService) {
		_phoneLocalService = phoneLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _phoneLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _phoneLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _phoneLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _phoneLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phoneLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phoneLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the phone to the database. Also notifies the appropriate model listeners.
	*
	* @param phone the phone
	* @return the phone that was added
	*/
	@Override
	public com.liferay.portal.kernel.model.Phone addPhone(
		com.liferay.portal.kernel.model.Phone phone) {
		return _phoneLocalService.addPhone(phone);
	}

	@Override
	public com.liferay.portal.kernel.model.Phone addPhone(long userId,
		java.lang.String className, long classPK, java.lang.String number,
		java.lang.String extension, long typeId, boolean primary,
		ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phoneLocalService.addPhone(userId, className, classPK, number,
			extension, typeId, primary, serviceContext);
	}

	/**
	* Creates a new phone with the primary key. Does not add the phone to the database.
	*
	* @param phoneId the primary key for the new phone
	* @return the new phone
	*/
	@Override
	public com.liferay.portal.kernel.model.Phone createPhone(long phoneId) {
		return _phoneLocalService.createPhone(phoneId);
	}

	/**
	* Deletes the phone from the database. Also notifies the appropriate model listeners.
	*
	* @param phone the phone
	* @return the phone that was removed
	*/
	@Override
	public com.liferay.portal.kernel.model.Phone deletePhone(
		com.liferay.portal.kernel.model.Phone phone) {
		return _phoneLocalService.deletePhone(phone);
	}

	/**
	* Deletes the phone with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param phoneId the primary key of the phone
	* @return the phone that was removed
	* @throws PortalException if a phone with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.kernel.model.Phone deletePhone(long phoneId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phoneLocalService.deletePhone(phoneId);
	}

	@Override
	public com.liferay.portal.kernel.model.Phone fetchPhone(long phoneId) {
		return _phoneLocalService.fetchPhone(phoneId);
	}

	/**
	* Returns the phone with the matching UUID and company.
	*
	* @param uuid the phone's UUID
	* @param companyId the primary key of the company
	* @return the matching phone, or <code>null</code> if a matching phone could not be found
	*/
	@Override
	public com.liferay.portal.kernel.model.Phone fetchPhoneByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _phoneLocalService.fetchPhoneByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the phone with the primary key.
	*
	* @param phoneId the primary key of the phone
	* @return the phone
	* @throws PortalException if a phone with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.kernel.model.Phone getPhone(long phoneId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phoneLocalService.getPhone(phoneId);
	}

	/**
	* Returns the phone with the matching UUID and company.
	*
	* @param uuid the phone's UUID
	* @param companyId the primary key of the company
	* @return the matching phone
	* @throws PortalException if a matching phone could not be found
	*/
	@Override
	public com.liferay.portal.kernel.model.Phone getPhoneByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phoneLocalService.getPhoneByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Updates the phone in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param phone the phone
	* @return the phone that was updated
	*/
	@Override
	public com.liferay.portal.kernel.model.Phone updatePhone(
		com.liferay.portal.kernel.model.Phone phone) {
		return _phoneLocalService.updatePhone(phone);
	}

	@Override
	public com.liferay.portal.kernel.model.Phone updatePhone(long phoneId,
		java.lang.String number, java.lang.String extension, long typeId,
		boolean primary)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phoneLocalService.updatePhone(phoneId, number, extension,
			typeId, primary);
	}

	/**
	* Returns the number of phones.
	*
	* @return the number of phones
	*/
	@Override
	public int getPhonesCount() {
		return _phoneLocalService.getPhonesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _phoneLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _phoneLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PhoneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _phoneLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PhoneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _phoneLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Phone> getPhones() {
		return _phoneLocalService.getPhones();
	}

	/**
	* Returns a range of all the phones.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PhoneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of phones
	* @param end the upper bound of the range of phones (not inclusive)
	* @return the range of phones
	*/
	@Override
	public java.util.List<com.liferay.portal.kernel.model.Phone> getPhones(
		int start, int end) {
		return _phoneLocalService.getPhones(start, end);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Phone> getPhones(
		long companyId, java.lang.String className, long classPK) {
		return _phoneLocalService.getPhones(companyId, className, classPK);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _phoneLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _phoneLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public void deletePhones(long companyId, java.lang.String className,
		long classPK) {
		_phoneLocalService.deletePhones(companyId, className, classPK);
	}

	@Override
	public PhoneLocalService getWrappedService() {
		return _phoneLocalService;
	}

	@Override
	public void setWrappedService(PhoneLocalService phoneLocalService) {
		_phoneLocalService = phoneLocalService;
	}

	private PhoneLocalService _phoneLocalService;
}