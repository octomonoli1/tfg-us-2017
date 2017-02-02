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

package com.liferay.portal.workflow.kaleo.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the kaleo instance service. This utility wraps {@link com.liferay.portal.workflow.kaleo.service.persistence.impl.KaleoInstancePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoInstancePersistence
 * @see com.liferay.portal.workflow.kaleo.service.persistence.impl.KaleoInstancePersistenceImpl
 * @generated
 */
@ProviderType
public class KaleoInstanceUtil {
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
	public static void clearCache(KaleoInstance kaleoInstance) {
		getPersistence().clearCache(kaleoInstance);
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
	public static List<KaleoInstance> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoInstance> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoInstance> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<KaleoInstance> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static KaleoInstance update(KaleoInstance kaleoInstance) {
		return getPersistence().update(kaleoInstance);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static KaleoInstance update(KaleoInstance kaleoInstance,
		ServiceContext serviceContext) {
		return getPersistence().update(kaleoInstance, serviceContext);
	}

	/**
	* Returns all the kaleo instances where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo instances
	*/
	public static List<KaleoInstance> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the kaleo instances where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @return the range of matching kaleo instances
	*/
	public static List<KaleoInstance> findByCompanyId(long companyId,
		int start, int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo instances where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instances
	*/
	public static List<KaleoInstance> findByCompanyId(long companyId,
		int start, int end, OrderByComparator<KaleoInstance> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the kaleo instances where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching kaleo instances
	*/
	public static List<KaleoInstance> findByCompanyId(long companyId,
		int start, int end, OrderByComparator<KaleoInstance> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first kaleo instance in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance
	* @throws NoSuchInstanceException if a matching kaleo instance could not be found
	*/
	public static KaleoInstance findByCompanyId_First(long companyId,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first kaleo instance in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	*/
	public static KaleoInstance fetchByCompanyId_First(long companyId,
		OrderByComparator<KaleoInstance> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance
	* @throws NoSuchInstanceException if a matching kaleo instance could not be found
	*/
	public static KaleoInstance findByCompanyId_Last(long companyId,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	*/
	public static KaleoInstance fetchByCompanyId_Last(long companyId,
		OrderByComparator<KaleoInstance> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the kaleo instances before and after the current kaleo instance in the ordered set where companyId = &#63;.
	*
	* @param kaleoInstanceId the primary key of the current kaleo instance
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance
	* @throws NoSuchInstanceException if a kaleo instance with the primary key could not be found
	*/
	public static KaleoInstance[] findByCompanyId_PrevAndNext(
		long kaleoInstanceId, long companyId,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(kaleoInstanceId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the kaleo instances where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of kaleo instances where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo instances
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns all the kaleo instances where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo instances
	*/
	public static List<KaleoInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId) {
		return getPersistence().findByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns a range of all the kaleo instances where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @return the range of matching kaleo instances
	*/
	public static List<KaleoInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo instances where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instances
	*/
	public static List<KaleoInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator<KaleoInstance> orderByComparator) {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the kaleo instances where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching kaleo instances
	*/
	public static List<KaleoInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator<KaleoInstance> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first kaleo instance in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance
	* @throws NoSuchInstanceException if a matching kaleo instance could not be found
	*/
	public static KaleoInstance findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException {
		return getPersistence()
				   .findByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the first kaleo instance in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	*/
	public static KaleoInstance fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		OrderByComparator<KaleoInstance> orderByComparator) {
		return getPersistence()
				   .fetchByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance
	* @throws NoSuchInstanceException if a matching kaleo instance could not be found
	*/
	public static KaleoInstance findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException {
		return getPersistence()
				   .findByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	*/
	public static KaleoInstance fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		OrderByComparator<KaleoInstance> orderByComparator) {
		return getPersistence()
				   .fetchByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the kaleo instances before and after the current kaleo instance in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoInstanceId the primary key of the current kaleo instance
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance
	* @throws NoSuchInstanceException if a kaleo instance with the primary key could not be found
	*/
	public static KaleoInstance[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoInstanceId, long kaleoDefinitionId,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException {
		return getPersistence()
				   .findByKaleoDefinitionId_PrevAndNext(kaleoInstanceId,
			kaleoDefinitionId, orderByComparator);
	}

	/**
	* Removes all the kaleo instances where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	*/
	public static void removeByKaleoDefinitionId(long kaleoDefinitionId) {
		getPersistence().removeByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the number of kaleo instances where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo instances
	*/
	public static int countByKaleoDefinitionId(long kaleoDefinitionId) {
		return getPersistence().countByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns all the kaleo instances where companyId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @return the matching kaleo instances
	*/
	public static List<KaleoInstance> findByC_U(long companyId, long userId) {
		return getPersistence().findByC_U(companyId, userId);
	}

	/**
	* Returns a range of all the kaleo instances where companyId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @return the range of matching kaleo instances
	*/
	public static List<KaleoInstance> findByC_U(long companyId, long userId,
		int start, int end) {
		return getPersistence().findByC_U(companyId, userId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo instances where companyId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instances
	*/
	public static List<KaleoInstance> findByC_U(long companyId, long userId,
		int start, int end, OrderByComparator<KaleoInstance> orderByComparator) {
		return getPersistence()
				   .findByC_U(companyId, userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the kaleo instances where companyId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching kaleo instances
	*/
	public static List<KaleoInstance> findByC_U(long companyId, long userId,
		int start, int end, OrderByComparator<KaleoInstance> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_U(companyId, userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first kaleo instance in the ordered set where companyId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance
	* @throws NoSuchInstanceException if a matching kaleo instance could not be found
	*/
	public static KaleoInstance findByC_U_First(long companyId, long userId,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException {
		return getPersistence()
				   .findByC_U_First(companyId, userId, orderByComparator);
	}

	/**
	* Returns the first kaleo instance in the ordered set where companyId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	*/
	public static KaleoInstance fetchByC_U_First(long companyId, long userId,
		OrderByComparator<KaleoInstance> orderByComparator) {
		return getPersistence()
				   .fetchByC_U_First(companyId, userId, orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where companyId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance
	* @throws NoSuchInstanceException if a matching kaleo instance could not be found
	*/
	public static KaleoInstance findByC_U_Last(long companyId, long userId,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException {
		return getPersistence()
				   .findByC_U_Last(companyId, userId, orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where companyId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	*/
	public static KaleoInstance fetchByC_U_Last(long companyId, long userId,
		OrderByComparator<KaleoInstance> orderByComparator) {
		return getPersistence()
				   .fetchByC_U_Last(companyId, userId, orderByComparator);
	}

	/**
	* Returns the kaleo instances before and after the current kaleo instance in the ordered set where companyId = &#63; and userId = &#63;.
	*
	* @param kaleoInstanceId the primary key of the current kaleo instance
	* @param companyId the company ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance
	* @throws NoSuchInstanceException if a kaleo instance with the primary key could not be found
	*/
	public static KaleoInstance[] findByC_U_PrevAndNext(long kaleoInstanceId,
		long companyId, long userId,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException {
		return getPersistence()
				   .findByC_U_PrevAndNext(kaleoInstanceId, companyId, userId,
			orderByComparator);
	}

	/**
	* Removes all the kaleo instances where companyId = &#63; and userId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param userId the user ID
	*/
	public static void removeByC_U(long companyId, long userId) {
		getPersistence().removeByC_U(companyId, userId);
	}

	/**
	* Returns the number of kaleo instances where companyId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @return the number of matching kaleo instances
	*/
	public static int countByC_U(long companyId, long userId) {
		return getPersistence().countByC_U(companyId, userId);
	}

	/**
	* Returns all the kaleo instances where kaleoDefinitionId = &#63; and completed = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param completed the completed
	* @return the matching kaleo instances
	*/
	public static List<KaleoInstance> findByKDI_C(long kaleoDefinitionId,
		boolean completed) {
		return getPersistence().findByKDI_C(kaleoDefinitionId, completed);
	}

	/**
	* Returns a range of all the kaleo instances where kaleoDefinitionId = &#63; and completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param completed the completed
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @return the range of matching kaleo instances
	*/
	public static List<KaleoInstance> findByKDI_C(long kaleoDefinitionId,
		boolean completed, int start, int end) {
		return getPersistence()
				   .findByKDI_C(kaleoDefinitionId, completed, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo instances where kaleoDefinitionId = &#63; and completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param completed the completed
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instances
	*/
	public static List<KaleoInstance> findByKDI_C(long kaleoDefinitionId,
		boolean completed, int start, int end,
		OrderByComparator<KaleoInstance> orderByComparator) {
		return getPersistence()
				   .findByKDI_C(kaleoDefinitionId, completed, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the kaleo instances where kaleoDefinitionId = &#63; and completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param completed the completed
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching kaleo instances
	*/
	public static List<KaleoInstance> findByKDI_C(long kaleoDefinitionId,
		boolean completed, int start, int end,
		OrderByComparator<KaleoInstance> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByKDI_C(kaleoDefinitionId, completed, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first kaleo instance in the ordered set where kaleoDefinitionId = &#63; and completed = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance
	* @throws NoSuchInstanceException if a matching kaleo instance could not be found
	*/
	public static KaleoInstance findByKDI_C_First(long kaleoDefinitionId,
		boolean completed, OrderByComparator<KaleoInstance> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException {
		return getPersistence()
				   .findByKDI_C_First(kaleoDefinitionId, completed,
			orderByComparator);
	}

	/**
	* Returns the first kaleo instance in the ordered set where kaleoDefinitionId = &#63; and completed = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	*/
	public static KaleoInstance fetchByKDI_C_First(long kaleoDefinitionId,
		boolean completed, OrderByComparator<KaleoInstance> orderByComparator) {
		return getPersistence()
				   .fetchByKDI_C_First(kaleoDefinitionId, completed,
			orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where kaleoDefinitionId = &#63; and completed = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance
	* @throws NoSuchInstanceException if a matching kaleo instance could not be found
	*/
	public static KaleoInstance findByKDI_C_Last(long kaleoDefinitionId,
		boolean completed, OrderByComparator<KaleoInstance> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException {
		return getPersistence()
				   .findByKDI_C_Last(kaleoDefinitionId, completed,
			orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where kaleoDefinitionId = &#63; and completed = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	*/
	public static KaleoInstance fetchByKDI_C_Last(long kaleoDefinitionId,
		boolean completed, OrderByComparator<KaleoInstance> orderByComparator) {
		return getPersistence()
				   .fetchByKDI_C_Last(kaleoDefinitionId, completed,
			orderByComparator);
	}

	/**
	* Returns the kaleo instances before and after the current kaleo instance in the ordered set where kaleoDefinitionId = &#63; and completed = &#63;.
	*
	* @param kaleoInstanceId the primary key of the current kaleo instance
	* @param kaleoDefinitionId the kaleo definition ID
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance
	* @throws NoSuchInstanceException if a kaleo instance with the primary key could not be found
	*/
	public static KaleoInstance[] findByKDI_C_PrevAndNext(
		long kaleoInstanceId, long kaleoDefinitionId, boolean completed,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException {
		return getPersistence()
				   .findByKDI_C_PrevAndNext(kaleoInstanceId, kaleoDefinitionId,
			completed, orderByComparator);
	}

	/**
	* Removes all the kaleo instances where kaleoDefinitionId = &#63; and completed = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param completed the completed
	*/
	public static void removeByKDI_C(long kaleoDefinitionId, boolean completed) {
		getPersistence().removeByKDI_C(kaleoDefinitionId, completed);
	}

	/**
	* Returns the number of kaleo instances where kaleoDefinitionId = &#63; and completed = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param completed the completed
	* @return the number of matching kaleo instances
	*/
	public static int countByKDI_C(long kaleoDefinitionId, boolean completed) {
		return getPersistence().countByKDI_C(kaleoDefinitionId, completed);
	}

	/**
	* Returns all the kaleo instances where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class p k
	* @return the matching kaleo instances
	*/
	public static List<KaleoInstance> findByCN_CPK(java.lang.String className,
		long classPK) {
		return getPersistence().findByCN_CPK(className, classPK);
	}

	/**
	* Returns a range of all the kaleo instances where className = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param className the class name
	* @param classPK the class p k
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @return the range of matching kaleo instances
	*/
	public static List<KaleoInstance> findByCN_CPK(java.lang.String className,
		long classPK, int start, int end) {
		return getPersistence().findByCN_CPK(className, classPK, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo instances where className = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param className the class name
	* @param classPK the class p k
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instances
	*/
	public static List<KaleoInstance> findByCN_CPK(java.lang.String className,
		long classPK, int start, int end,
		OrderByComparator<KaleoInstance> orderByComparator) {
		return getPersistence()
				   .findByCN_CPK(className, classPK, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the kaleo instances where className = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param className the class name
	* @param classPK the class p k
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching kaleo instances
	*/
	public static List<KaleoInstance> findByCN_CPK(java.lang.String className,
		long classPK, int start, int end,
		OrderByComparator<KaleoInstance> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCN_CPK(className, classPK, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first kaleo instance in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance
	* @throws NoSuchInstanceException if a matching kaleo instance could not be found
	*/
	public static KaleoInstance findByCN_CPK_First(java.lang.String className,
		long classPK, OrderByComparator<KaleoInstance> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException {
		return getPersistence()
				   .findByCN_CPK_First(className, classPK, orderByComparator);
	}

	/**
	* Returns the first kaleo instance in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	*/
	public static KaleoInstance fetchByCN_CPK_First(
		java.lang.String className, long classPK,
		OrderByComparator<KaleoInstance> orderByComparator) {
		return getPersistence()
				   .fetchByCN_CPK_First(className, classPK, orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance
	* @throws NoSuchInstanceException if a matching kaleo instance could not be found
	*/
	public static KaleoInstance findByCN_CPK_Last(java.lang.String className,
		long classPK, OrderByComparator<KaleoInstance> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException {
		return getPersistence()
				   .findByCN_CPK_Last(className, classPK, orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	*/
	public static KaleoInstance fetchByCN_CPK_Last(java.lang.String className,
		long classPK, OrderByComparator<KaleoInstance> orderByComparator) {
		return getPersistence()
				   .fetchByCN_CPK_Last(className, classPK, orderByComparator);
	}

	/**
	* Returns the kaleo instances before and after the current kaleo instance in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param kaleoInstanceId the primary key of the current kaleo instance
	* @param className the class name
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance
	* @throws NoSuchInstanceException if a kaleo instance with the primary key could not be found
	*/
	public static KaleoInstance[] findByCN_CPK_PrevAndNext(
		long kaleoInstanceId, java.lang.String className, long classPK,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException {
		return getPersistence()
				   .findByCN_CPK_PrevAndNext(kaleoInstanceId, className,
			classPK, orderByComparator);
	}

	/**
	* Removes all the kaleo instances where className = &#63; and classPK = &#63; from the database.
	*
	* @param className the class name
	* @param classPK the class p k
	*/
	public static void removeByCN_CPK(java.lang.String className, long classPK) {
		getPersistence().removeByCN_CPK(className, classPK);
	}

	/**
	* Returns the number of kaleo instances where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class p k
	* @return the number of matching kaleo instances
	*/
	public static int countByCN_CPK(java.lang.String className, long classPK) {
		return getPersistence().countByCN_CPK(className, classPK);
	}

	/**
	* Returns all the kaleo instances where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionName the kaleo definition name
	* @param kaleoDefinitionVersion the kaleo definition version
	* @param completionDate the completion date
	* @return the matching kaleo instances
	*/
	public static List<KaleoInstance> findByC_KDN_KDV_CD(long companyId,
		java.lang.String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate) {
		return getPersistence()
				   .findByC_KDN_KDV_CD(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate);
	}

	/**
	* Returns a range of all the kaleo instances where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param kaleoDefinitionName the kaleo definition name
	* @param kaleoDefinitionVersion the kaleo definition version
	* @param completionDate the completion date
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @return the range of matching kaleo instances
	*/
	public static List<KaleoInstance> findByC_KDN_KDV_CD(long companyId,
		java.lang.String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate, int start, int end) {
		return getPersistence()
				   .findByC_KDN_KDV_CD(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo instances where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param kaleoDefinitionName the kaleo definition name
	* @param kaleoDefinitionVersion the kaleo definition version
	* @param completionDate the completion date
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instances
	*/
	public static List<KaleoInstance> findByC_KDN_KDV_CD(long companyId,
		java.lang.String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate, int start, int end,
		OrderByComparator<KaleoInstance> orderByComparator) {
		return getPersistence()
				   .findByC_KDN_KDV_CD(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the kaleo instances where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param kaleoDefinitionName the kaleo definition name
	* @param kaleoDefinitionVersion the kaleo definition version
	* @param completionDate the completion date
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching kaleo instances
	*/
	public static List<KaleoInstance> findByC_KDN_KDV_CD(long companyId,
		java.lang.String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate, int start, int end,
		OrderByComparator<KaleoInstance> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_KDN_KDV_CD(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first kaleo instance in the ordered set where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionName the kaleo definition name
	* @param kaleoDefinitionVersion the kaleo definition version
	* @param completionDate the completion date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance
	* @throws NoSuchInstanceException if a matching kaleo instance could not be found
	*/
	public static KaleoInstance findByC_KDN_KDV_CD_First(long companyId,
		java.lang.String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate, OrderByComparator<KaleoInstance> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException {
		return getPersistence()
				   .findByC_KDN_KDV_CD_First(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate, orderByComparator);
	}

	/**
	* Returns the first kaleo instance in the ordered set where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionName the kaleo definition name
	* @param kaleoDefinitionVersion the kaleo definition version
	* @param completionDate the completion date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	*/
	public static KaleoInstance fetchByC_KDN_KDV_CD_First(long companyId,
		java.lang.String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate, OrderByComparator<KaleoInstance> orderByComparator) {
		return getPersistence()
				   .fetchByC_KDN_KDV_CD_First(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate, orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionName the kaleo definition name
	* @param kaleoDefinitionVersion the kaleo definition version
	* @param completionDate the completion date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance
	* @throws NoSuchInstanceException if a matching kaleo instance could not be found
	*/
	public static KaleoInstance findByC_KDN_KDV_CD_Last(long companyId,
		java.lang.String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate, OrderByComparator<KaleoInstance> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException {
		return getPersistence()
				   .findByC_KDN_KDV_CD_Last(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate, orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionName the kaleo definition name
	* @param kaleoDefinitionVersion the kaleo definition version
	* @param completionDate the completion date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	*/
	public static KaleoInstance fetchByC_KDN_KDV_CD_Last(long companyId,
		java.lang.String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate, OrderByComparator<KaleoInstance> orderByComparator) {
		return getPersistence()
				   .fetchByC_KDN_KDV_CD_Last(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate, orderByComparator);
	}

	/**
	* Returns the kaleo instances before and after the current kaleo instance in the ordered set where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	*
	* @param kaleoInstanceId the primary key of the current kaleo instance
	* @param companyId the company ID
	* @param kaleoDefinitionName the kaleo definition name
	* @param kaleoDefinitionVersion the kaleo definition version
	* @param completionDate the completion date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance
	* @throws NoSuchInstanceException if a kaleo instance with the primary key could not be found
	*/
	public static KaleoInstance[] findByC_KDN_KDV_CD_PrevAndNext(
		long kaleoInstanceId, long companyId,
		java.lang.String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate, OrderByComparator<KaleoInstance> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException {
		return getPersistence()
				   .findByC_KDN_KDV_CD_PrevAndNext(kaleoInstanceId, companyId,
			kaleoDefinitionName, kaleoDefinitionVersion, completionDate,
			orderByComparator);
	}

	/**
	* Removes all the kaleo instances where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63; from the database.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionName the kaleo definition name
	* @param kaleoDefinitionVersion the kaleo definition version
	* @param completionDate the completion date
	*/
	public static void removeByC_KDN_KDV_CD(long companyId,
		java.lang.String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate) {
		getPersistence()
			.removeByC_KDN_KDV_CD(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate);
	}

	/**
	* Returns the number of kaleo instances where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionName the kaleo definition name
	* @param kaleoDefinitionVersion the kaleo definition version
	* @param completionDate the completion date
	* @return the number of matching kaleo instances
	*/
	public static int countByC_KDN_KDV_CD(long companyId,
		java.lang.String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate) {
		return getPersistence()
				   .countByC_KDN_KDV_CD(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate);
	}

	/**
	* Caches the kaleo instance in the entity cache if it is enabled.
	*
	* @param kaleoInstance the kaleo instance
	*/
	public static void cacheResult(KaleoInstance kaleoInstance) {
		getPersistence().cacheResult(kaleoInstance);
	}

	/**
	* Caches the kaleo instances in the entity cache if it is enabled.
	*
	* @param kaleoInstances the kaleo instances
	*/
	public static void cacheResult(List<KaleoInstance> kaleoInstances) {
		getPersistence().cacheResult(kaleoInstances);
	}

	/**
	* Creates a new kaleo instance with the primary key. Does not add the kaleo instance to the database.
	*
	* @param kaleoInstanceId the primary key for the new kaleo instance
	* @return the new kaleo instance
	*/
	public static KaleoInstance create(long kaleoInstanceId) {
		return getPersistence().create(kaleoInstanceId);
	}

	/**
	* Removes the kaleo instance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoInstanceId the primary key of the kaleo instance
	* @return the kaleo instance that was removed
	* @throws NoSuchInstanceException if a kaleo instance with the primary key could not be found
	*/
	public static KaleoInstance remove(long kaleoInstanceId)
		throws com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException {
		return getPersistence().remove(kaleoInstanceId);
	}

	public static KaleoInstance updateImpl(KaleoInstance kaleoInstance) {
		return getPersistence().updateImpl(kaleoInstance);
	}

	/**
	* Returns the kaleo instance with the primary key or throws a {@link NoSuchInstanceException} if it could not be found.
	*
	* @param kaleoInstanceId the primary key of the kaleo instance
	* @return the kaleo instance
	* @throws NoSuchInstanceException if a kaleo instance with the primary key could not be found
	*/
	public static KaleoInstance findByPrimaryKey(long kaleoInstanceId)
		throws com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException {
		return getPersistence().findByPrimaryKey(kaleoInstanceId);
	}

	/**
	* Returns the kaleo instance with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoInstanceId the primary key of the kaleo instance
	* @return the kaleo instance, or <code>null</code> if a kaleo instance with the primary key could not be found
	*/
	public static KaleoInstance fetchByPrimaryKey(long kaleoInstanceId) {
		return getPersistence().fetchByPrimaryKey(kaleoInstanceId);
	}

	public static java.util.Map<java.io.Serializable, KaleoInstance> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the kaleo instances.
	*
	* @return the kaleo instances
	*/
	public static List<KaleoInstance> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the kaleo instances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @return the range of kaleo instances
	*/
	public static List<KaleoInstance> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the kaleo instances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo instances
	*/
	public static List<KaleoInstance> findAll(int start, int end,
		OrderByComparator<KaleoInstance> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the kaleo instances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of kaleo instances
	*/
	public static List<KaleoInstance> findAll(int start, int end,
		OrderByComparator<KaleoInstance> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the kaleo instances from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of kaleo instances.
	*
	* @return the number of kaleo instances
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static KaleoInstancePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<KaleoInstancePersistence, KaleoInstancePersistence> _serviceTracker =
		ServiceTrackerFactory.open(KaleoInstancePersistence.class);
}