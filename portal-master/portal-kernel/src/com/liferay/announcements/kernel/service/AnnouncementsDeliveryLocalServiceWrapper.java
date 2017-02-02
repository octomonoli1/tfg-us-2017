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

package com.liferay.announcements.kernel.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AnnouncementsDeliveryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AnnouncementsDeliveryLocalService
 * @generated
 */
@ProviderType
public class AnnouncementsDeliveryLocalServiceWrapper
	implements AnnouncementsDeliveryLocalService,
		ServiceWrapper<AnnouncementsDeliveryLocalService> {
	public AnnouncementsDeliveryLocalServiceWrapper(
		AnnouncementsDeliveryLocalService announcementsDeliveryLocalService) {
		_announcementsDeliveryLocalService = announcementsDeliveryLocalService;
	}

	/**
	* Adds the announcements delivery to the database. Also notifies the appropriate model listeners.
	*
	* @param announcementsDelivery the announcements delivery
	* @return the announcements delivery that was added
	*/
	@Override
	public com.liferay.announcements.kernel.model.AnnouncementsDelivery addAnnouncementsDelivery(
		com.liferay.announcements.kernel.model.AnnouncementsDelivery announcementsDelivery) {
		return _announcementsDeliveryLocalService.addAnnouncementsDelivery(announcementsDelivery);
	}

	@Override
	public com.liferay.announcements.kernel.model.AnnouncementsDelivery addUserDelivery(
		long userId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _announcementsDeliveryLocalService.addUserDelivery(userId, type);
	}

	/**
	* Creates a new announcements delivery with the primary key. Does not add the announcements delivery to the database.
	*
	* @param deliveryId the primary key for the new announcements delivery
	* @return the new announcements delivery
	*/
	@Override
	public com.liferay.announcements.kernel.model.AnnouncementsDelivery createAnnouncementsDelivery(
		long deliveryId) {
		return _announcementsDeliveryLocalService.createAnnouncementsDelivery(deliveryId);
	}

	/**
	* Deletes the announcements delivery from the database. Also notifies the appropriate model listeners.
	*
	* @param announcementsDelivery the announcements delivery
	* @return the announcements delivery that was removed
	*/
	@Override
	public com.liferay.announcements.kernel.model.AnnouncementsDelivery deleteAnnouncementsDelivery(
		com.liferay.announcements.kernel.model.AnnouncementsDelivery announcementsDelivery) {
		return _announcementsDeliveryLocalService.deleteAnnouncementsDelivery(announcementsDelivery);
	}

	/**
	* Deletes the announcements delivery with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliveryId the primary key of the announcements delivery
	* @return the announcements delivery that was removed
	* @throws PortalException if a announcements delivery with the primary key could not be found
	*/
	@Override
	public com.liferay.announcements.kernel.model.AnnouncementsDelivery deleteAnnouncementsDelivery(
		long deliveryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _announcementsDeliveryLocalService.deleteAnnouncementsDelivery(deliveryId);
	}

	@Override
	public com.liferay.announcements.kernel.model.AnnouncementsDelivery fetchAnnouncementsDelivery(
		long deliveryId) {
		return _announcementsDeliveryLocalService.fetchAnnouncementsDelivery(deliveryId);
	}

	/**
	* Returns the announcements delivery with the primary key.
	*
	* @param deliveryId the primary key of the announcements delivery
	* @return the announcements delivery
	* @throws PortalException if a announcements delivery with the primary key could not be found
	*/
	@Override
	public com.liferay.announcements.kernel.model.AnnouncementsDelivery getAnnouncementsDelivery(
		long deliveryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _announcementsDeliveryLocalService.getAnnouncementsDelivery(deliveryId);
	}

	@Override
	public com.liferay.announcements.kernel.model.AnnouncementsDelivery getDelivery(
		long deliveryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _announcementsDeliveryLocalService.getDelivery(deliveryId);
	}

	@Override
	public com.liferay.announcements.kernel.model.AnnouncementsDelivery getUserDelivery(
		long userId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _announcementsDeliveryLocalService.getUserDelivery(userId, type);
	}

	/**
	* Updates the announcements delivery in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param announcementsDelivery the announcements delivery
	* @return the announcements delivery that was updated
	*/
	@Override
	public com.liferay.announcements.kernel.model.AnnouncementsDelivery updateAnnouncementsDelivery(
		com.liferay.announcements.kernel.model.AnnouncementsDelivery announcementsDelivery) {
		return _announcementsDeliveryLocalService.updateAnnouncementsDelivery(announcementsDelivery);
	}

	@Override
	public com.liferay.announcements.kernel.model.AnnouncementsDelivery updateDelivery(
		long userId, java.lang.String type, boolean email, boolean sms,
		boolean website)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _announcementsDeliveryLocalService.updateDelivery(userId, type,
			email, sms, website);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _announcementsDeliveryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _announcementsDeliveryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _announcementsDeliveryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _announcementsDeliveryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _announcementsDeliveryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of announcements deliveries.
	*
	* @return the number of announcements deliveries
	*/
	@Override
	public int getAnnouncementsDeliveriesCount() {
		return _announcementsDeliveryLocalService.getAnnouncementsDeliveriesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _announcementsDeliveryLocalService.getOSGiServiceIdentifier();
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
		return _announcementsDeliveryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.announcements.model.impl.AnnouncementsDeliveryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _announcementsDeliveryLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.announcements.model.impl.AnnouncementsDeliveryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _announcementsDeliveryLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the announcements deliveries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.announcements.model.impl.AnnouncementsDeliveryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of announcements deliveries
	* @param end the upper bound of the range of announcements deliveries (not inclusive)
	* @return the range of announcements deliveries
	*/
	@Override
	public java.util.List<com.liferay.announcements.kernel.model.AnnouncementsDelivery> getAnnouncementsDeliveries(
		int start, int end) {
		return _announcementsDeliveryLocalService.getAnnouncementsDeliveries(start,
			end);
	}

	@Override
	public java.util.List<com.liferay.announcements.kernel.model.AnnouncementsDelivery> getUserDeliveries(
		long userId) throws com.liferay.portal.kernel.exception.PortalException {
		return _announcementsDeliveryLocalService.getUserDeliveries(userId);
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
		return _announcementsDeliveryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _announcementsDeliveryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void deleteDeliveries(long userId) {
		_announcementsDeliveryLocalService.deleteDeliveries(userId);
	}

	@Override
	public void deleteDelivery(
		com.liferay.announcements.kernel.model.AnnouncementsDelivery delivery) {
		_announcementsDeliveryLocalService.deleteDelivery(delivery);
	}

	@Override
	public void deleteDelivery(long deliveryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_announcementsDeliveryLocalService.deleteDelivery(deliveryId);
	}

	@Override
	public void deleteDelivery(long userId, java.lang.String type) {
		_announcementsDeliveryLocalService.deleteDelivery(userId, type);
	}

	@Override
	public AnnouncementsDeliveryLocalService getWrappedService() {
		return _announcementsDeliveryLocalService;
	}

	@Override
	public void setWrappedService(
		AnnouncementsDeliveryLocalService announcementsDeliveryLocalService) {
		_announcementsDeliveryLocalService = announcementsDeliveryLocalService;
	}

	private AnnouncementsDeliveryLocalService _announcementsDeliveryLocalService;
}