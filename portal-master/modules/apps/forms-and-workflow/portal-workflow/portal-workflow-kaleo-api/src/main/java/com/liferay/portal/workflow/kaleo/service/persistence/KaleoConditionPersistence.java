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

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.workflow.kaleo.exception.NoSuchConditionException;
import com.liferay.portal.workflow.kaleo.model.KaleoCondition;

/**
 * The persistence interface for the kaleo condition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.workflow.kaleo.service.persistence.impl.KaleoConditionPersistenceImpl
 * @see KaleoConditionUtil
 * @generated
 */
@ProviderType
public interface KaleoConditionPersistence extends BasePersistence<KaleoCondition> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoConditionUtil} to access the kaleo condition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the kaleo conditions where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo conditions
	*/
	public java.util.List<KaleoCondition> findByCompanyId(long companyId);

	/**
	* Returns a range of all the kaleo conditions where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo conditions
	* @param end the upper bound of the range of kaleo conditions (not inclusive)
	* @return the range of matching kaleo conditions
	*/
	public java.util.List<KaleoCondition> findByCompanyId(long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the kaleo conditions where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo conditions
	* @param end the upper bound of the range of kaleo conditions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo conditions
	*/
	public java.util.List<KaleoCondition> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoCondition> orderByComparator);

	/**
	* Returns an ordered range of all the kaleo conditions where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo conditions
	* @param end the upper bound of the range of kaleo conditions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching kaleo conditions
	*/
	public java.util.List<KaleoCondition> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoCondition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first kaleo condition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo condition
	* @throws NoSuchConditionException if a matching kaleo condition could not be found
	*/
	public KaleoCondition findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoCondition> orderByComparator)
		throws NoSuchConditionException;

	/**
	* Returns the first kaleo condition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo condition, or <code>null</code> if a matching kaleo condition could not be found
	*/
	public KaleoCondition fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoCondition> orderByComparator);

	/**
	* Returns the last kaleo condition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo condition
	* @throws NoSuchConditionException if a matching kaleo condition could not be found
	*/
	public KaleoCondition findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoCondition> orderByComparator)
		throws NoSuchConditionException;

	/**
	* Returns the last kaleo condition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo condition, or <code>null</code> if a matching kaleo condition could not be found
	*/
	public KaleoCondition fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoCondition> orderByComparator);

	/**
	* Returns the kaleo conditions before and after the current kaleo condition in the ordered set where companyId = &#63;.
	*
	* @param kaleoConditionId the primary key of the current kaleo condition
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo condition
	* @throws NoSuchConditionException if a kaleo condition with the primary key could not be found
	*/
	public KaleoCondition[] findByCompanyId_PrevAndNext(long kaleoConditionId,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoCondition> orderByComparator)
		throws NoSuchConditionException;

	/**
	* Removes all the kaleo conditions where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of kaleo conditions where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo conditions
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns all the kaleo conditions where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo conditions
	*/
	public java.util.List<KaleoCondition> findByKaleoDefinitionId(
		long kaleoDefinitionId);

	/**
	* Returns a range of all the kaleo conditions where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo conditions
	* @param end the upper bound of the range of kaleo conditions (not inclusive)
	* @return the range of matching kaleo conditions
	*/
	public java.util.List<KaleoCondition> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end);

	/**
	* Returns an ordered range of all the kaleo conditions where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo conditions
	* @param end the upper bound of the range of kaleo conditions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo conditions
	*/
	public java.util.List<KaleoCondition> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoCondition> orderByComparator);

	/**
	* Returns an ordered range of all the kaleo conditions where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo conditions
	* @param end the upper bound of the range of kaleo conditions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching kaleo conditions
	*/
	public java.util.List<KaleoCondition> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoCondition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first kaleo condition in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo condition
	* @throws NoSuchConditionException if a matching kaleo condition could not be found
	*/
	public KaleoCondition findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoCondition> orderByComparator)
		throws NoSuchConditionException;

	/**
	* Returns the first kaleo condition in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo condition, or <code>null</code> if a matching kaleo condition could not be found
	*/
	public KaleoCondition fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoCondition> orderByComparator);

	/**
	* Returns the last kaleo condition in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo condition
	* @throws NoSuchConditionException if a matching kaleo condition could not be found
	*/
	public KaleoCondition findByKaleoDefinitionId_Last(long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoCondition> orderByComparator)
		throws NoSuchConditionException;

	/**
	* Returns the last kaleo condition in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo condition, or <code>null</code> if a matching kaleo condition could not be found
	*/
	public KaleoCondition fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoCondition> orderByComparator);

	/**
	* Returns the kaleo conditions before and after the current kaleo condition in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoConditionId the primary key of the current kaleo condition
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo condition
	* @throws NoSuchConditionException if a kaleo condition with the primary key could not be found
	*/
	public KaleoCondition[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoConditionId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoCondition> orderByComparator)
		throws NoSuchConditionException;

	/**
	* Removes all the kaleo conditions where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	*/
	public void removeByKaleoDefinitionId(long kaleoDefinitionId);

	/**
	* Returns the number of kaleo conditions where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo conditions
	*/
	public int countByKaleoDefinitionId(long kaleoDefinitionId);

	/**
	* Returns the kaleo condition where kaleoNodeId = &#63; or throws a {@link NoSuchConditionException} if it could not be found.
	*
	* @param kaleoNodeId the kaleo node ID
	* @return the matching kaleo condition
	* @throws NoSuchConditionException if a matching kaleo condition could not be found
	*/
	public KaleoCondition findByKaleoNodeId(long kaleoNodeId)
		throws NoSuchConditionException;

	/**
	* Returns the kaleo condition where kaleoNodeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param kaleoNodeId the kaleo node ID
	* @return the matching kaleo condition, or <code>null</code> if a matching kaleo condition could not be found
	*/
	public KaleoCondition fetchByKaleoNodeId(long kaleoNodeId);

	/**
	* Returns the kaleo condition where kaleoNodeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching kaleo condition, or <code>null</code> if a matching kaleo condition could not be found
	*/
	public KaleoCondition fetchByKaleoNodeId(long kaleoNodeId,
		boolean retrieveFromCache);

	/**
	* Removes the kaleo condition where kaleoNodeId = &#63; from the database.
	*
	* @param kaleoNodeId the kaleo node ID
	* @return the kaleo condition that was removed
	*/
	public KaleoCondition removeByKaleoNodeId(long kaleoNodeId)
		throws NoSuchConditionException;

	/**
	* Returns the number of kaleo conditions where kaleoNodeId = &#63;.
	*
	* @param kaleoNodeId the kaleo node ID
	* @return the number of matching kaleo conditions
	*/
	public int countByKaleoNodeId(long kaleoNodeId);

	/**
	* Caches the kaleo condition in the entity cache if it is enabled.
	*
	* @param kaleoCondition the kaleo condition
	*/
	public void cacheResult(KaleoCondition kaleoCondition);

	/**
	* Caches the kaleo conditions in the entity cache if it is enabled.
	*
	* @param kaleoConditions the kaleo conditions
	*/
	public void cacheResult(java.util.List<KaleoCondition> kaleoConditions);

	/**
	* Creates a new kaleo condition with the primary key. Does not add the kaleo condition to the database.
	*
	* @param kaleoConditionId the primary key for the new kaleo condition
	* @return the new kaleo condition
	*/
	public KaleoCondition create(long kaleoConditionId);

	/**
	* Removes the kaleo condition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoConditionId the primary key of the kaleo condition
	* @return the kaleo condition that was removed
	* @throws NoSuchConditionException if a kaleo condition with the primary key could not be found
	*/
	public KaleoCondition remove(long kaleoConditionId)
		throws NoSuchConditionException;

	public KaleoCondition updateImpl(KaleoCondition kaleoCondition);

	/**
	* Returns the kaleo condition with the primary key or throws a {@link NoSuchConditionException} if it could not be found.
	*
	* @param kaleoConditionId the primary key of the kaleo condition
	* @return the kaleo condition
	* @throws NoSuchConditionException if a kaleo condition with the primary key could not be found
	*/
	public KaleoCondition findByPrimaryKey(long kaleoConditionId)
		throws NoSuchConditionException;

	/**
	* Returns the kaleo condition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoConditionId the primary key of the kaleo condition
	* @return the kaleo condition, or <code>null</code> if a kaleo condition with the primary key could not be found
	*/
	public KaleoCondition fetchByPrimaryKey(long kaleoConditionId);

	@Override
	public java.util.Map<java.io.Serializable, KaleoCondition> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the kaleo conditions.
	*
	* @return the kaleo conditions
	*/
	public java.util.List<KaleoCondition> findAll();

	/**
	* Returns a range of all the kaleo conditions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo conditions
	* @param end the upper bound of the range of kaleo conditions (not inclusive)
	* @return the range of kaleo conditions
	*/
	public java.util.List<KaleoCondition> findAll(int start, int end);

	/**
	* Returns an ordered range of all the kaleo conditions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo conditions
	* @param end the upper bound of the range of kaleo conditions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo conditions
	*/
	public java.util.List<KaleoCondition> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoCondition> orderByComparator);

	/**
	* Returns an ordered range of all the kaleo conditions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo conditions
	* @param end the upper bound of the range of kaleo conditions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of kaleo conditions
	*/
	public java.util.List<KaleoCondition> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoCondition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the kaleo conditions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of kaleo conditions.
	*
	* @return the number of kaleo conditions
	*/
	public int countAll();
}