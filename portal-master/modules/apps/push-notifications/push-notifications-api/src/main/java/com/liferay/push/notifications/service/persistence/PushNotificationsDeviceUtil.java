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

package com.liferay.push.notifications.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferay.push.notifications.model.PushNotificationsDevice;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the push notifications device service. This utility wraps {@link com.liferay.push.notifications.service.persistence.impl.PushNotificationsDevicePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Bruno Farache
 * @see PushNotificationsDevicePersistence
 * @see com.liferay.push.notifications.service.persistence.impl.PushNotificationsDevicePersistenceImpl
 * @generated
 */
@ProviderType
public class PushNotificationsDeviceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(
		PushNotificationsDevice pushNotificationsDevice) {
		getPersistence().clearCache(pushNotificationsDevice);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<PushNotificationsDevice> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PushNotificationsDevice> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PushNotificationsDevice> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PushNotificationsDevice> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PushNotificationsDevice update(
		PushNotificationsDevice pushNotificationsDevice) {
		return getPersistence().update(pushNotificationsDevice);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PushNotificationsDevice update(
		PushNotificationsDevice pushNotificationsDevice,
		ServiceContext serviceContext) {
		return getPersistence().update(pushNotificationsDevice, serviceContext);
	}

	/**
	* Returns the push notifications device where token = &#63; or throws a {@link NoSuchDeviceException} if it could not be found.
	*
	* @param token the token
	* @return the matching push notifications device
	* @throws NoSuchDeviceException if a matching push notifications device could not be found
	*/
	public static PushNotificationsDevice findByToken(java.lang.String token)
		throws com.liferay.push.notifications.exception.NoSuchDeviceException {
		return getPersistence().findByToken(token);
	}

	/**
	* Returns the push notifications device where token = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param token the token
	* @return the matching push notifications device, or <code>null</code> if a matching push notifications device could not be found
	*/
	public static PushNotificationsDevice fetchByToken(java.lang.String token) {
		return getPersistence().fetchByToken(token);
	}

	/**
	* Returns the push notifications device where token = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param token the token
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching push notifications device, or <code>null</code> if a matching push notifications device could not be found
	*/
	public static PushNotificationsDevice fetchByToken(java.lang.String token,
		boolean retrieveFromCache) {
		return getPersistence().fetchByToken(token, retrieveFromCache);
	}

	/**
	* Removes the push notifications device where token = &#63; from the database.
	*
	* @param token the token
	* @return the push notifications device that was removed
	*/
	public static PushNotificationsDevice removeByToken(java.lang.String token)
		throws com.liferay.push.notifications.exception.NoSuchDeviceException {
		return getPersistence().removeByToken(token);
	}

	/**
	* Returns the number of push notifications devices where token = &#63;.
	*
	* @param token the token
	* @return the number of matching push notifications devices
	*/
	public static int countByToken(java.lang.String token) {
		return getPersistence().countByToken(token);
	}

	/**
	* Returns all the push notifications devices where userId = &#63; and platform = &#63;.
	*
	* @param userId the user ID
	* @param platform the platform
	* @return the matching push notifications devices
	*/
	public static List<PushNotificationsDevice> findByU_P(long userId,
		java.lang.String platform) {
		return getPersistence().findByU_P(userId, platform);
	}

	/**
	* Returns a range of all the push notifications devices where userId = &#63; and platform = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param platform the platform
	* @param start the lower bound of the range of push notifications devices
	* @param end the upper bound of the range of push notifications devices (not inclusive)
	* @return the range of matching push notifications devices
	*/
	public static List<PushNotificationsDevice> findByU_P(long userId,
		java.lang.String platform, int start, int end) {
		return getPersistence().findByU_P(userId, platform, start, end);
	}

	/**
	* Returns an ordered range of all the push notifications devices where userId = &#63; and platform = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param platform the platform
	* @param start the lower bound of the range of push notifications devices
	* @param end the upper bound of the range of push notifications devices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching push notifications devices
	*/
	public static List<PushNotificationsDevice> findByU_P(long userId,
		java.lang.String platform, int start, int end,
		OrderByComparator<PushNotificationsDevice> orderByComparator) {
		return getPersistence()
				   .findByU_P(userId, platform, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the push notifications devices where userId = &#63; and platform = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param platform the platform
	* @param start the lower bound of the range of push notifications devices
	* @param end the upper bound of the range of push notifications devices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching push notifications devices
	*/
	public static List<PushNotificationsDevice> findByU_P(long userId,
		java.lang.String platform, int start, int end,
		OrderByComparator<PushNotificationsDevice> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_P(userId, platform, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first push notifications device in the ordered set where userId = &#63; and platform = &#63;.
	*
	* @param userId the user ID
	* @param platform the platform
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push notifications device
	* @throws NoSuchDeviceException if a matching push notifications device could not be found
	*/
	public static PushNotificationsDevice findByU_P_First(long userId,
		java.lang.String platform,
		OrderByComparator<PushNotificationsDevice> orderByComparator)
		throws com.liferay.push.notifications.exception.NoSuchDeviceException {
		return getPersistence()
				   .findByU_P_First(userId, platform, orderByComparator);
	}

	/**
	* Returns the first push notifications device in the ordered set where userId = &#63; and platform = &#63;.
	*
	* @param userId the user ID
	* @param platform the platform
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push notifications device, or <code>null</code> if a matching push notifications device could not be found
	*/
	public static PushNotificationsDevice fetchByU_P_First(long userId,
		java.lang.String platform,
		OrderByComparator<PushNotificationsDevice> orderByComparator) {
		return getPersistence()
				   .fetchByU_P_First(userId, platform, orderByComparator);
	}

	/**
	* Returns the last push notifications device in the ordered set where userId = &#63; and platform = &#63;.
	*
	* @param userId the user ID
	* @param platform the platform
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push notifications device
	* @throws NoSuchDeviceException if a matching push notifications device could not be found
	*/
	public static PushNotificationsDevice findByU_P_Last(long userId,
		java.lang.String platform,
		OrderByComparator<PushNotificationsDevice> orderByComparator)
		throws com.liferay.push.notifications.exception.NoSuchDeviceException {
		return getPersistence()
				   .findByU_P_Last(userId, platform, orderByComparator);
	}

	/**
	* Returns the last push notifications device in the ordered set where userId = &#63; and platform = &#63;.
	*
	* @param userId the user ID
	* @param platform the platform
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push notifications device, or <code>null</code> if a matching push notifications device could not be found
	*/
	public static PushNotificationsDevice fetchByU_P_Last(long userId,
		java.lang.String platform,
		OrderByComparator<PushNotificationsDevice> orderByComparator) {
		return getPersistence()
				   .fetchByU_P_Last(userId, platform, orderByComparator);
	}

	/**
	* Returns the push notifications devices before and after the current push notifications device in the ordered set where userId = &#63; and platform = &#63;.
	*
	* @param pushNotificationsDeviceId the primary key of the current push notifications device
	* @param userId the user ID
	* @param platform the platform
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next push notifications device
	* @throws NoSuchDeviceException if a push notifications device with the primary key could not be found
	*/
	public static PushNotificationsDevice[] findByU_P_PrevAndNext(
		long pushNotificationsDeviceId, long userId, java.lang.String platform,
		OrderByComparator<PushNotificationsDevice> orderByComparator)
		throws com.liferay.push.notifications.exception.NoSuchDeviceException {
		return getPersistence()
				   .findByU_P_PrevAndNext(pushNotificationsDeviceId, userId,
			platform, orderByComparator);
	}

	/**
	* Returns all the push notifications devices where userId = any &#63; and platform = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userIds the user IDs
	* @param platform the platform
	* @return the matching push notifications devices
	*/
	public static List<PushNotificationsDevice> findByU_P(long[] userIds,
		java.lang.String platform) {
		return getPersistence().findByU_P(userIds, platform);
	}

	/**
	* Returns a range of all the push notifications devices where userId = any &#63; and platform = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userIds the user IDs
	* @param platform the platform
	* @param start the lower bound of the range of push notifications devices
	* @param end the upper bound of the range of push notifications devices (not inclusive)
	* @return the range of matching push notifications devices
	*/
	public static List<PushNotificationsDevice> findByU_P(long[] userIds,
		java.lang.String platform, int start, int end) {
		return getPersistence().findByU_P(userIds, platform, start, end);
	}

	/**
	* Returns an ordered range of all the push notifications devices where userId = any &#63; and platform = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userIds the user IDs
	* @param platform the platform
	* @param start the lower bound of the range of push notifications devices
	* @param end the upper bound of the range of push notifications devices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching push notifications devices
	*/
	public static List<PushNotificationsDevice> findByU_P(long[] userIds,
		java.lang.String platform, int start, int end,
		OrderByComparator<PushNotificationsDevice> orderByComparator) {
		return getPersistence()
				   .findByU_P(userIds, platform, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the push notifications devices where userId = &#63; and platform = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param platform the platform
	* @param start the lower bound of the range of push notifications devices
	* @param end the upper bound of the range of push notifications devices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching push notifications devices
	*/
	public static List<PushNotificationsDevice> findByU_P(long[] userIds,
		java.lang.String platform, int start, int end,
		OrderByComparator<PushNotificationsDevice> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_P(userIds, platform, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Removes all the push notifications devices where userId = &#63; and platform = &#63; from the database.
	*
	* @param userId the user ID
	* @param platform the platform
	*/
	public static void removeByU_P(long userId, java.lang.String platform) {
		getPersistence().removeByU_P(userId, platform);
	}

	/**
	* Returns the number of push notifications devices where userId = &#63; and platform = &#63;.
	*
	* @param userId the user ID
	* @param platform the platform
	* @return the number of matching push notifications devices
	*/
	public static int countByU_P(long userId, java.lang.String platform) {
		return getPersistence().countByU_P(userId, platform);
	}

	/**
	* Returns the number of push notifications devices where userId = any &#63; and platform = &#63;.
	*
	* @param userIds the user IDs
	* @param platform the platform
	* @return the number of matching push notifications devices
	*/
	public static int countByU_P(long[] userIds, java.lang.String platform) {
		return getPersistence().countByU_P(userIds, platform);
	}

	/**
	* Caches the push notifications device in the entity cache if it is enabled.
	*
	* @param pushNotificationsDevice the push notifications device
	*/
	public static void cacheResult(
		PushNotificationsDevice pushNotificationsDevice) {
		getPersistence().cacheResult(pushNotificationsDevice);
	}

	/**
	* Caches the push notifications devices in the entity cache if it is enabled.
	*
	* @param pushNotificationsDevices the push notifications devices
	*/
	public static void cacheResult(
		List<PushNotificationsDevice> pushNotificationsDevices) {
		getPersistence().cacheResult(pushNotificationsDevices);
	}

	/**
	* Creates a new push notifications device with the primary key. Does not add the push notifications device to the database.
	*
	* @param pushNotificationsDeviceId the primary key for the new push notifications device
	* @return the new push notifications device
	*/
	public static PushNotificationsDevice create(long pushNotificationsDeviceId) {
		return getPersistence().create(pushNotificationsDeviceId);
	}

	/**
	* Removes the push notifications device with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pushNotificationsDeviceId the primary key of the push notifications device
	* @return the push notifications device that was removed
	* @throws NoSuchDeviceException if a push notifications device with the primary key could not be found
	*/
	public static PushNotificationsDevice remove(long pushNotificationsDeviceId)
		throws com.liferay.push.notifications.exception.NoSuchDeviceException {
		return getPersistence().remove(pushNotificationsDeviceId);
	}

	public static PushNotificationsDevice updateImpl(
		PushNotificationsDevice pushNotificationsDevice) {
		return getPersistence().updateImpl(pushNotificationsDevice);
	}

	/**
	* Returns the push notifications device with the primary key or throws a {@link NoSuchDeviceException} if it could not be found.
	*
	* @param pushNotificationsDeviceId the primary key of the push notifications device
	* @return the push notifications device
	* @throws NoSuchDeviceException if a push notifications device with the primary key could not be found
	*/
	public static PushNotificationsDevice findByPrimaryKey(
		long pushNotificationsDeviceId)
		throws com.liferay.push.notifications.exception.NoSuchDeviceException {
		return getPersistence().findByPrimaryKey(pushNotificationsDeviceId);
	}

	/**
	* Returns the push notifications device with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param pushNotificationsDeviceId the primary key of the push notifications device
	* @return the push notifications device, or <code>null</code> if a push notifications device with the primary key could not be found
	*/
	public static PushNotificationsDevice fetchByPrimaryKey(
		long pushNotificationsDeviceId) {
		return getPersistence().fetchByPrimaryKey(pushNotificationsDeviceId);
	}

	public static java.util.Map<java.io.Serializable, PushNotificationsDevice> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the push notifications devices.
	*
	* @return the push notifications devices
	*/
	public static List<PushNotificationsDevice> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the push notifications devices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push notifications devices
	* @param end the upper bound of the range of push notifications devices (not inclusive)
	* @return the range of push notifications devices
	*/
	public static List<PushNotificationsDevice> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the push notifications devices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push notifications devices
	* @param end the upper bound of the range of push notifications devices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of push notifications devices
	*/
	public static List<PushNotificationsDevice> findAll(int start, int end,
		OrderByComparator<PushNotificationsDevice> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the push notifications devices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push notifications devices
	* @param end the upper bound of the range of push notifications devices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of push notifications devices
	*/
	public static List<PushNotificationsDevice> findAll(int start, int end,
		OrderByComparator<PushNotificationsDevice> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the push notifications devices from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of push notifications devices.
	*
	* @return the number of push notifications devices
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static PushNotificationsDevicePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PushNotificationsDevicePersistence, PushNotificationsDevicePersistence> _serviceTracker =
		ServiceTrackerFactory.open(PushNotificationsDevicePersistence.class);
}