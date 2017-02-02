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
 * Provides a wrapper for {@link UserIdMapperLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserIdMapperLocalService
 * @generated
 */
@ProviderType
public class UserIdMapperLocalServiceWrapper implements UserIdMapperLocalService,
	ServiceWrapper<UserIdMapperLocalService> {
	public UserIdMapperLocalServiceWrapper(
		UserIdMapperLocalService userIdMapperLocalService) {
		_userIdMapperLocalService = userIdMapperLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _userIdMapperLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userIdMapperLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _userIdMapperLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userIdMapperLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userIdMapperLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the user ID mapper to the database. Also notifies the appropriate model listeners.
	*
	* @param userIdMapper the user ID mapper
	* @return the user ID mapper that was added
	*/
	@Override
	public com.liferay.portal.kernel.model.UserIdMapper addUserIdMapper(
		com.liferay.portal.kernel.model.UserIdMapper userIdMapper) {
		return _userIdMapperLocalService.addUserIdMapper(userIdMapper);
	}

	/**
	* Creates a new user ID mapper with the primary key. Does not add the user ID mapper to the database.
	*
	* @param userIdMapperId the primary key for the new user ID mapper
	* @return the new user ID mapper
	*/
	@Override
	public com.liferay.portal.kernel.model.UserIdMapper createUserIdMapper(
		long userIdMapperId) {
		return _userIdMapperLocalService.createUserIdMapper(userIdMapperId);
	}

	/**
	* Deletes the user ID mapper from the database. Also notifies the appropriate model listeners.
	*
	* @param userIdMapper the user ID mapper
	* @return the user ID mapper that was removed
	*/
	@Override
	public com.liferay.portal.kernel.model.UserIdMapper deleteUserIdMapper(
		com.liferay.portal.kernel.model.UserIdMapper userIdMapper) {
		return _userIdMapperLocalService.deleteUserIdMapper(userIdMapper);
	}

	/**
	* Deletes the user ID mapper with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userIdMapperId the primary key of the user ID mapper
	* @return the user ID mapper that was removed
	* @throws PortalException if a user ID mapper with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.kernel.model.UserIdMapper deleteUserIdMapper(
		long userIdMapperId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userIdMapperLocalService.deleteUserIdMapper(userIdMapperId);
	}

	@Override
	public com.liferay.portal.kernel.model.UserIdMapper fetchUserIdMapper(
		long userIdMapperId) {
		return _userIdMapperLocalService.fetchUserIdMapper(userIdMapperId);
	}

	@Override
	public com.liferay.portal.kernel.model.UserIdMapper getUserIdMapper(
		long userId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userIdMapperLocalService.getUserIdMapper(userId, type);
	}

	/**
	* Returns the user ID mapper with the primary key.
	*
	* @param userIdMapperId the primary key of the user ID mapper
	* @return the user ID mapper
	* @throws PortalException if a user ID mapper with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.kernel.model.UserIdMapper getUserIdMapper(
		long userIdMapperId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userIdMapperLocalService.getUserIdMapper(userIdMapperId);
	}

	@Override
	public com.liferay.portal.kernel.model.UserIdMapper getUserIdMapperByExternalUserId(
		java.lang.String type, java.lang.String externalUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userIdMapperLocalService.getUserIdMapperByExternalUserId(type,
			externalUserId);
	}

	/**
	* Updates the user ID mapper in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userIdMapper the user ID mapper
	* @return the user ID mapper that was updated
	*/
	@Override
	public com.liferay.portal.kernel.model.UserIdMapper updateUserIdMapper(
		com.liferay.portal.kernel.model.UserIdMapper userIdMapper) {
		return _userIdMapperLocalService.updateUserIdMapper(userIdMapper);
	}

	@Override
	public com.liferay.portal.kernel.model.UserIdMapper updateUserIdMapper(
		long userId, java.lang.String type, java.lang.String description,
		java.lang.String externalUserId) {
		return _userIdMapperLocalService.updateUserIdMapper(userId, type,
			description, externalUserId);
	}

	/**
	* Returns the number of user ID mappers.
	*
	* @return the number of user ID mappers
	*/
	@Override
	public int getUserIdMappersCount() {
		return _userIdMapperLocalService.getUserIdMappersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _userIdMapperLocalService.getOSGiServiceIdentifier();
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
		return _userIdMapperLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _userIdMapperLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _userIdMapperLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the user ID mappers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user ID mappers
	* @param end the upper bound of the range of user ID mappers (not inclusive)
	* @return the range of user ID mappers
	*/
	@Override
	public java.util.List<com.liferay.portal.kernel.model.UserIdMapper> getUserIdMappers(
		int start, int end) {
		return _userIdMapperLocalService.getUserIdMappers(start, end);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.UserIdMapper> getUserIdMappers(
		long userId) {
		return _userIdMapperLocalService.getUserIdMappers(userId);
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
		return _userIdMapperLocalService.dynamicQueryCount(dynamicQuery);
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
		return _userIdMapperLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void deleteUserIdMappers(long userId) {
		_userIdMapperLocalService.deleteUserIdMappers(userId);
	}

	@Override
	public UserIdMapperLocalService getWrappedService() {
		return _userIdMapperLocalService;
	}

	@Override
	public void setWrappedService(
		UserIdMapperLocalService userIdMapperLocalService) {
		_userIdMapperLocalService = userIdMapperLocalService;
	}

	private UserIdMapperLocalService _userIdMapperLocalService;
}