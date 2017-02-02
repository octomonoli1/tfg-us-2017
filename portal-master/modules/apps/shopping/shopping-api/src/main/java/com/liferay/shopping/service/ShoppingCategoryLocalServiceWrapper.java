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

package com.liferay.shopping.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ShoppingCategoryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingCategoryLocalService
 * @generated
 */
@ProviderType
public class ShoppingCategoryLocalServiceWrapper
	implements ShoppingCategoryLocalService,
		ServiceWrapper<ShoppingCategoryLocalService> {
	public ShoppingCategoryLocalServiceWrapper(
		ShoppingCategoryLocalService shoppingCategoryLocalService) {
		_shoppingCategoryLocalService = shoppingCategoryLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _shoppingCategoryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _shoppingCategoryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _shoppingCategoryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _shoppingCategoryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _shoppingCategoryLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.shopping.model.ShoppingCategory addCategory(
		long userId, long parentCategoryId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _shoppingCategoryLocalService.addCategory(userId,
			parentCategoryId, name, description, serviceContext);
	}

	/**
	* Adds the shopping category to the database. Also notifies the appropriate model listeners.
	*
	* @param shoppingCategory the shopping category
	* @return the shopping category that was added
	*/
	@Override
	public com.liferay.shopping.model.ShoppingCategory addShoppingCategory(
		com.liferay.shopping.model.ShoppingCategory shoppingCategory) {
		return _shoppingCategoryLocalService.addShoppingCategory(shoppingCategory);
	}

	/**
	* Creates a new shopping category with the primary key. Does not add the shopping category to the database.
	*
	* @param categoryId the primary key for the new shopping category
	* @return the new shopping category
	*/
	@Override
	public com.liferay.shopping.model.ShoppingCategory createShoppingCategory(
		long categoryId) {
		return _shoppingCategoryLocalService.createShoppingCategory(categoryId);
	}

	/**
	* Deletes the shopping category from the database. Also notifies the appropriate model listeners.
	*
	* @param shoppingCategory the shopping category
	* @return the shopping category that was removed
	*/
	@Override
	public com.liferay.shopping.model.ShoppingCategory deleteShoppingCategory(
		com.liferay.shopping.model.ShoppingCategory shoppingCategory) {
		return _shoppingCategoryLocalService.deleteShoppingCategory(shoppingCategory);
	}

	/**
	* Deletes the shopping category with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param categoryId the primary key of the shopping category
	* @return the shopping category that was removed
	* @throws PortalException if a shopping category with the primary key could not be found
	*/
	@Override
	public com.liferay.shopping.model.ShoppingCategory deleteShoppingCategory(
		long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _shoppingCategoryLocalService.deleteShoppingCategory(categoryId);
	}

	@Override
	public com.liferay.shopping.model.ShoppingCategory fetchShoppingCategory(
		long categoryId) {
		return _shoppingCategoryLocalService.fetchShoppingCategory(categoryId);
	}

	@Override
	public com.liferay.shopping.model.ShoppingCategory getCategory(
		long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _shoppingCategoryLocalService.getCategory(categoryId);
	}

	@Override
	public com.liferay.shopping.model.ShoppingCategory getCategory(
		long groupId, java.lang.String categoryName) {
		return _shoppingCategoryLocalService.getCategory(groupId, categoryName);
	}

	@Override
	public com.liferay.shopping.model.ShoppingCategory getParentCategory(
		com.liferay.shopping.model.ShoppingCategory category)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _shoppingCategoryLocalService.getParentCategory(category);
	}

	/**
	* Returns the shopping category with the primary key.
	*
	* @param categoryId the primary key of the shopping category
	* @return the shopping category
	* @throws PortalException if a shopping category with the primary key could not be found
	*/
	@Override
	public com.liferay.shopping.model.ShoppingCategory getShoppingCategory(
		long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _shoppingCategoryLocalService.getShoppingCategory(categoryId);
	}

	@Override
	public com.liferay.shopping.model.ShoppingCategory updateCategory(
		long categoryId, long parentCategoryId, java.lang.String name,
		java.lang.String description, boolean mergeWithParentCategory,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _shoppingCategoryLocalService.updateCategory(categoryId,
			parentCategoryId, name, description, mergeWithParentCategory,
			serviceContext);
	}

	/**
	* Updates the shopping category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param shoppingCategory the shopping category
	* @return the shopping category that was updated
	*/
	@Override
	public com.liferay.shopping.model.ShoppingCategory updateShoppingCategory(
		com.liferay.shopping.model.ShoppingCategory shoppingCategory) {
		return _shoppingCategoryLocalService.updateShoppingCategory(shoppingCategory);
	}

	@Override
	public int getCategoriesCount(long groupId, long parentCategoryId) {
		return _shoppingCategoryLocalService.getCategoriesCount(groupId,
			parentCategoryId);
	}

	/**
	* Returns the number of shopping categories.
	*
	* @return the number of shopping categories
	*/
	@Override
	public int getShoppingCategoriesCount() {
		return _shoppingCategoryLocalService.getShoppingCategoriesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _shoppingCategoryLocalService.getOSGiServiceIdentifier();
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
		return _shoppingCategoryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shopping.model.impl.ShoppingCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _shoppingCategoryLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shopping.model.impl.ShoppingCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _shoppingCategoryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.shopping.model.ShoppingCategory> getCategories(
		long groupId) {
		return _shoppingCategoryLocalService.getCategories(groupId);
	}

	@Override
	public java.util.List<com.liferay.shopping.model.ShoppingCategory> getCategories(
		long groupId, long parentCategoryId, int start, int end) {
		return _shoppingCategoryLocalService.getCategories(groupId,
			parentCategoryId, start, end);
	}

	@Override
	public java.util.List<com.liferay.shopping.model.ShoppingCategory> getParentCategories(
		com.liferay.shopping.model.ShoppingCategory category)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _shoppingCategoryLocalService.getParentCategories(category);
	}

	@Override
	public java.util.List<com.liferay.shopping.model.ShoppingCategory> getParentCategories(
		long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _shoppingCategoryLocalService.getParentCategories(categoryId);
	}

	/**
	* Returns a range of all the shopping categories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shopping.model.impl.ShoppingCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of shopping categories
	* @param end the upper bound of the range of shopping categories (not inclusive)
	* @return the range of shopping categories
	*/
	@Override
	public java.util.List<com.liferay.shopping.model.ShoppingCategory> getShoppingCategories(
		int start, int end) {
		return _shoppingCategoryLocalService.getShoppingCategories(start, end);
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
		return _shoppingCategoryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _shoppingCategoryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void addCategoryResources(
		com.liferay.shopping.model.ShoppingCategory category,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_shoppingCategoryLocalService.addCategoryResources(category,
			addGroupPermissions, addGuestPermissions);
	}

	@Override
	public void addCategoryResources(
		com.liferay.shopping.model.ShoppingCategory category,
		com.liferay.portal.kernel.service.permission.ModelPermissions modelPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_shoppingCategoryLocalService.addCategoryResources(category,
			modelPermissions);
	}

	@Override
	public void addCategoryResources(long categoryId,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_shoppingCategoryLocalService.addCategoryResources(categoryId,
			addGroupPermissions, addGuestPermissions);
	}

	@Override
	public void addCategoryResources(long categoryId,
		com.liferay.portal.kernel.service.permission.ModelPermissions modelPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_shoppingCategoryLocalService.addCategoryResources(categoryId,
			modelPermissions);
	}

	@Override
	public void deleteCategories(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_shoppingCategoryLocalService.deleteCategories(groupId);
	}

	@Override
	public void deleteCategory(
		com.liferay.shopping.model.ShoppingCategory category)
		throws com.liferay.portal.kernel.exception.PortalException {
		_shoppingCategoryLocalService.deleteCategory(category);
	}

	@Override
	public void deleteCategory(long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_shoppingCategoryLocalService.deleteCategory(categoryId);
	}

	@Override
	public void getSubcategoryIds(java.util.List<java.lang.Long> categoryIds,
		long groupId, long categoryId) {
		_shoppingCategoryLocalService.getSubcategoryIds(categoryIds, groupId,
			categoryId);
	}

	@Override
	public ShoppingCategoryLocalService getWrappedService() {
		return _shoppingCategoryLocalService;
	}

	@Override
	public void setWrappedService(
		ShoppingCategoryLocalService shoppingCategoryLocalService) {
		_shoppingCategoryLocalService = shoppingCategoryLocalService;
	}

	private ShoppingCategoryLocalService _shoppingCategoryLocalService;
}