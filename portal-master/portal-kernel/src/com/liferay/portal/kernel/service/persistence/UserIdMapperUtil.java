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

package com.liferay.portal.kernel.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.model.UserIdMapper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the user ID mapper service. This utility wraps {@link com.liferay.portal.service.persistence.impl.UserIdMapperPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserIdMapperPersistence
 * @see com.liferay.portal.service.persistence.impl.UserIdMapperPersistenceImpl
 * @generated
 */
@ProviderType
public class UserIdMapperUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(UserIdMapper userIdMapper) {
		getPersistence().clearCache(userIdMapper);
	}

	/**
	 * @see BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<UserIdMapper> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserIdMapper> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserIdMapper> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserIdMapper> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UserIdMapper update(UserIdMapper userIdMapper) {
		return getPersistence().update(userIdMapper);
	}

	/**
	 * @see BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UserIdMapper update(UserIdMapper userIdMapper,
		ServiceContext serviceContext) {
		return getPersistence().update(userIdMapper, serviceContext);
	}

	/**
	* Returns all the user ID mappers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching user ID mappers
	*/
	public static List<UserIdMapper> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the user ID mappers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of user ID mappers
	* @param end the upper bound of the range of user ID mappers (not inclusive)
	* @return the range of matching user ID mappers
	*/
	public static List<UserIdMapper> findByUserId(long userId, int start,
		int end) {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the user ID mappers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of user ID mappers
	* @param end the upper bound of the range of user ID mappers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user ID mappers
	*/
	public static List<UserIdMapper> findByUserId(long userId, int start,
		int end, OrderByComparator<UserIdMapper> orderByComparator) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user ID mappers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of user ID mappers
	* @param end the upper bound of the range of user ID mappers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user ID mappers
	*/
	public static List<UserIdMapper> findByUserId(long userId, int start,
		int end, OrderByComparator<UserIdMapper> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first user ID mapper in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user ID mapper
	* @throws NoSuchUserIdMapperException if a matching user ID mapper could not be found
	*/
	public static UserIdMapper findByUserId_First(long userId,
		OrderByComparator<UserIdMapper> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserIdMapperException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first user ID mapper in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user ID mapper, or <code>null</code> if a matching user ID mapper could not be found
	*/
	public static UserIdMapper fetchByUserId_First(long userId,
		OrderByComparator<UserIdMapper> orderByComparator) {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last user ID mapper in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user ID mapper
	* @throws NoSuchUserIdMapperException if a matching user ID mapper could not be found
	*/
	public static UserIdMapper findByUserId_Last(long userId,
		OrderByComparator<UserIdMapper> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserIdMapperException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last user ID mapper in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user ID mapper, or <code>null</code> if a matching user ID mapper could not be found
	*/
	public static UserIdMapper fetchByUserId_Last(long userId,
		OrderByComparator<UserIdMapper> orderByComparator) {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the user ID mappers before and after the current user ID mapper in the ordered set where userId = &#63;.
	*
	* @param userIdMapperId the primary key of the current user ID mapper
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user ID mapper
	* @throws NoSuchUserIdMapperException if a user ID mapper with the primary key could not be found
	*/
	public static UserIdMapper[] findByUserId_PrevAndNext(long userIdMapperId,
		long userId, OrderByComparator<UserIdMapper> orderByComparator)
		throws com.liferay.portal.kernel.exception.NoSuchUserIdMapperException {
		return getPersistence()
				   .findByUserId_PrevAndNext(userIdMapperId, userId,
			orderByComparator);
	}

	/**
	* Removes all the user ID mappers where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of user ID mappers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching user ID mappers
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the user ID mapper where userId = &#63; and type = &#63; or throws a {@link NoSuchUserIdMapperException} if it could not be found.
	*
	* @param userId the user ID
	* @param type the type
	* @return the matching user ID mapper
	* @throws NoSuchUserIdMapperException if a matching user ID mapper could not be found
	*/
	public static UserIdMapper findByU_T(long userId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.NoSuchUserIdMapperException {
		return getPersistence().findByU_T(userId, type);
	}

	/**
	* Returns the user ID mapper where userId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param type the type
	* @return the matching user ID mapper, or <code>null</code> if a matching user ID mapper could not be found
	*/
	public static UserIdMapper fetchByU_T(long userId, java.lang.String type) {
		return getPersistence().fetchByU_T(userId, type);
	}

	/**
	* Returns the user ID mapper where userId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param type the type
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching user ID mapper, or <code>null</code> if a matching user ID mapper could not be found
	*/
	public static UserIdMapper fetchByU_T(long userId, java.lang.String type,
		boolean retrieveFromCache) {
		return getPersistence().fetchByU_T(userId, type, retrieveFromCache);
	}

	/**
	* Removes the user ID mapper where userId = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param type the type
	* @return the user ID mapper that was removed
	*/
	public static UserIdMapper removeByU_T(long userId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.NoSuchUserIdMapperException {
		return getPersistence().removeByU_T(userId, type);
	}

	/**
	* Returns the number of user ID mappers where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the number of matching user ID mappers
	*/
	public static int countByU_T(long userId, java.lang.String type) {
		return getPersistence().countByU_T(userId, type);
	}

	/**
	* Returns the user ID mapper where type = &#63; and externalUserId = &#63; or throws a {@link NoSuchUserIdMapperException} if it could not be found.
	*
	* @param type the type
	* @param externalUserId the external user ID
	* @return the matching user ID mapper
	* @throws NoSuchUserIdMapperException if a matching user ID mapper could not be found
	*/
	public static UserIdMapper findByT_E(java.lang.String type,
		java.lang.String externalUserId)
		throws com.liferay.portal.kernel.exception.NoSuchUserIdMapperException {
		return getPersistence().findByT_E(type, externalUserId);
	}

	/**
	* Returns the user ID mapper where type = &#63; and externalUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param type the type
	* @param externalUserId the external user ID
	* @return the matching user ID mapper, or <code>null</code> if a matching user ID mapper could not be found
	*/
	public static UserIdMapper fetchByT_E(java.lang.String type,
		java.lang.String externalUserId) {
		return getPersistence().fetchByT_E(type, externalUserId);
	}

	/**
	* Returns the user ID mapper where type = &#63; and externalUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param type the type
	* @param externalUserId the external user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching user ID mapper, or <code>null</code> if a matching user ID mapper could not be found
	*/
	public static UserIdMapper fetchByT_E(java.lang.String type,
		java.lang.String externalUserId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByT_E(type, externalUserId, retrieveFromCache);
	}

	/**
	* Removes the user ID mapper where type = &#63; and externalUserId = &#63; from the database.
	*
	* @param type the type
	* @param externalUserId the external user ID
	* @return the user ID mapper that was removed
	*/
	public static UserIdMapper removeByT_E(java.lang.String type,
		java.lang.String externalUserId)
		throws com.liferay.portal.kernel.exception.NoSuchUserIdMapperException {
		return getPersistence().removeByT_E(type, externalUserId);
	}

	/**
	* Returns the number of user ID mappers where type = &#63; and externalUserId = &#63;.
	*
	* @param type the type
	* @param externalUserId the external user ID
	* @return the number of matching user ID mappers
	*/
	public static int countByT_E(java.lang.String type,
		java.lang.String externalUserId) {
		return getPersistence().countByT_E(type, externalUserId);
	}

	/**
	* Caches the user ID mapper in the entity cache if it is enabled.
	*
	* @param userIdMapper the user ID mapper
	*/
	public static void cacheResult(UserIdMapper userIdMapper) {
		getPersistence().cacheResult(userIdMapper);
	}

	/**
	* Caches the user ID mappers in the entity cache if it is enabled.
	*
	* @param userIdMappers the user ID mappers
	*/
	public static void cacheResult(List<UserIdMapper> userIdMappers) {
		getPersistence().cacheResult(userIdMappers);
	}

	/**
	* Creates a new user ID mapper with the primary key. Does not add the user ID mapper to the database.
	*
	* @param userIdMapperId the primary key for the new user ID mapper
	* @return the new user ID mapper
	*/
	public static UserIdMapper create(long userIdMapperId) {
		return getPersistence().create(userIdMapperId);
	}

	/**
	* Removes the user ID mapper with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userIdMapperId the primary key of the user ID mapper
	* @return the user ID mapper that was removed
	* @throws NoSuchUserIdMapperException if a user ID mapper with the primary key could not be found
	*/
	public static UserIdMapper remove(long userIdMapperId)
		throws com.liferay.portal.kernel.exception.NoSuchUserIdMapperException {
		return getPersistence().remove(userIdMapperId);
	}

	public static UserIdMapper updateImpl(UserIdMapper userIdMapper) {
		return getPersistence().updateImpl(userIdMapper);
	}

	/**
	* Returns the user ID mapper with the primary key or throws a {@link NoSuchUserIdMapperException} if it could not be found.
	*
	* @param userIdMapperId the primary key of the user ID mapper
	* @return the user ID mapper
	* @throws NoSuchUserIdMapperException if a user ID mapper with the primary key could not be found
	*/
	public static UserIdMapper findByPrimaryKey(long userIdMapperId)
		throws com.liferay.portal.kernel.exception.NoSuchUserIdMapperException {
		return getPersistence().findByPrimaryKey(userIdMapperId);
	}

	/**
	* Returns the user ID mapper with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userIdMapperId the primary key of the user ID mapper
	* @return the user ID mapper, or <code>null</code> if a user ID mapper with the primary key could not be found
	*/
	public static UserIdMapper fetchByPrimaryKey(long userIdMapperId) {
		return getPersistence().fetchByPrimaryKey(userIdMapperId);
	}

	public static java.util.Map<java.io.Serializable, UserIdMapper> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the user ID mappers.
	*
	* @return the user ID mappers
	*/
	public static List<UserIdMapper> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the user ID mappers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user ID mappers
	* @param end the upper bound of the range of user ID mappers (not inclusive)
	* @return the range of user ID mappers
	*/
	public static List<UserIdMapper> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the user ID mappers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user ID mappers
	* @param end the upper bound of the range of user ID mappers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of user ID mappers
	*/
	public static List<UserIdMapper> findAll(int start, int end,
		OrderByComparator<UserIdMapper> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user ID mappers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user ID mappers
	* @param end the upper bound of the range of user ID mappers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of user ID mappers
	*/
	public static List<UserIdMapper> findAll(int start, int end,
		OrderByComparator<UserIdMapper> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the user ID mappers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of user ID mappers.
	*
	* @return the number of user ID mappers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static UserIdMapperPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (UserIdMapperPersistence)PortalBeanLocatorUtil.locate(UserIdMapperPersistence.class.getName());

			ReferenceRegistry.registerReference(UserIdMapperUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static UserIdMapperPersistence _persistence;
}