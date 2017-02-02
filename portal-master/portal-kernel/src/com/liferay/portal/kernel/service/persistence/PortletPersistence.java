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

import com.liferay.portal.kernel.exception.NoSuchPortletException;
import com.liferay.portal.kernel.model.Portlet;

/**
 * The persistence interface for the portlet service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.persistence.impl.PortletPersistenceImpl
 * @see PortletUtil
 * @generated
 */
@ProviderType
public interface PortletPersistence extends BasePersistence<Portlet> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PortletUtil} to access the portlet persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the portlets where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching portlets
	*/
	public java.util.List<Portlet> findByCompanyId(long companyId);

	/**
	* Returns a range of all the portlets where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of portlets
	* @param end the upper bound of the range of portlets (not inclusive)
	* @return the range of matching portlets
	*/
	public java.util.List<Portlet> findByCompanyId(long companyId, int start,
		int end);

	/**
	* Returns an ordered range of all the portlets where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of portlets
	* @param end the upper bound of the range of portlets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching portlets
	*/
	public java.util.List<Portlet> findByCompanyId(long companyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Portlet> orderByComparator);

	/**
	* Returns an ordered range of all the portlets where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of portlets
	* @param end the upper bound of the range of portlets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching portlets
	*/
	public java.util.List<Portlet> findByCompanyId(long companyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Portlet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first portlet in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portlet
	* @throws NoSuchPortletException if a matching portlet could not be found
	*/
	public Portlet findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Portlet> orderByComparator)
		throws NoSuchPortletException;

	/**
	* Returns the first portlet in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portlet, or <code>null</code> if a matching portlet could not be found
	*/
	public Portlet fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Portlet> orderByComparator);

	/**
	* Returns the last portlet in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portlet
	* @throws NoSuchPortletException if a matching portlet could not be found
	*/
	public Portlet findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Portlet> orderByComparator)
		throws NoSuchPortletException;

	/**
	* Returns the last portlet in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portlet, or <code>null</code> if a matching portlet could not be found
	*/
	public Portlet fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Portlet> orderByComparator);

	/**
	* Returns the portlets before and after the current portlet in the ordered set where companyId = &#63;.
	*
	* @param id the primary key of the current portlet
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next portlet
	* @throws NoSuchPortletException if a portlet with the primary key could not be found
	*/
	public Portlet[] findByCompanyId_PrevAndNext(long id, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Portlet> orderByComparator)
		throws NoSuchPortletException;

	/**
	* Removes all the portlets where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of portlets where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching portlets
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns the portlet where companyId = &#63; and portletId = &#63; or throws a {@link NoSuchPortletException} if it could not be found.
	*
	* @param companyId the company ID
	* @param portletId the portlet ID
	* @return the matching portlet
	* @throws NoSuchPortletException if a matching portlet could not be found
	*/
	public Portlet findByC_P(long companyId, java.lang.String portletId)
		throws NoSuchPortletException;

	/**
	* Returns the portlet where companyId = &#63; and portletId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param portletId the portlet ID
	* @return the matching portlet, or <code>null</code> if a matching portlet could not be found
	*/
	public Portlet fetchByC_P(long companyId, java.lang.String portletId);

	/**
	* Returns the portlet where companyId = &#63; and portletId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param portletId the portlet ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching portlet, or <code>null</code> if a matching portlet could not be found
	*/
	public Portlet fetchByC_P(long companyId, java.lang.String portletId,
		boolean retrieveFromCache);

	/**
	* Removes the portlet where companyId = &#63; and portletId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param portletId the portlet ID
	* @return the portlet that was removed
	*/
	public Portlet removeByC_P(long companyId, java.lang.String portletId)
		throws NoSuchPortletException;

	/**
	* Returns the number of portlets where companyId = &#63; and portletId = &#63;.
	*
	* @param companyId the company ID
	* @param portletId the portlet ID
	* @return the number of matching portlets
	*/
	public int countByC_P(long companyId, java.lang.String portletId);

	/**
	* Caches the portlet in the entity cache if it is enabled.
	*
	* @param portlet the portlet
	*/
	public void cacheResult(Portlet portlet);

	/**
	* Caches the portlets in the entity cache if it is enabled.
	*
	* @param portlets the portlets
	*/
	public void cacheResult(java.util.List<Portlet> portlets);

	/**
	* Creates a new portlet with the primary key. Does not add the portlet to the database.
	*
	* @param id the primary key for the new portlet
	* @return the new portlet
	*/
	public Portlet create(long id);

	/**
	* Removes the portlet with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the portlet
	* @return the portlet that was removed
	* @throws NoSuchPortletException if a portlet with the primary key could not be found
	*/
	public Portlet remove(long id) throws NoSuchPortletException;

	public Portlet updateImpl(Portlet portlet);

	/**
	* Returns the portlet with the primary key or throws a {@link NoSuchPortletException} if it could not be found.
	*
	* @param id the primary key of the portlet
	* @return the portlet
	* @throws NoSuchPortletException if a portlet with the primary key could not be found
	*/
	public Portlet findByPrimaryKey(long id) throws NoSuchPortletException;

	/**
	* Returns the portlet with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the portlet
	* @return the portlet, or <code>null</code> if a portlet with the primary key could not be found
	*/
	public Portlet fetchByPrimaryKey(long id);

	@Override
	public java.util.Map<java.io.Serializable, Portlet> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the portlets.
	*
	* @return the portlets
	*/
	public java.util.List<Portlet> findAll();

	/**
	* Returns a range of all the portlets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of portlets
	* @param end the upper bound of the range of portlets (not inclusive)
	* @return the range of portlets
	*/
	public java.util.List<Portlet> findAll(int start, int end);

	/**
	* Returns an ordered range of all the portlets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of portlets
	* @param end the upper bound of the range of portlets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of portlets
	*/
	public java.util.List<Portlet> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Portlet> orderByComparator);

	/**
	* Returns an ordered range of all the portlets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of portlets
	* @param end the upper bound of the range of portlets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of portlets
	*/
	public java.util.List<Portlet> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Portlet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the portlets from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of portlets.
	*
	* @return the number of portlets
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}