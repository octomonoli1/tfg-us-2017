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

import com.liferay.portal.kernel.exception.NoSuchRegionException;
import com.liferay.portal.kernel.model.Region;

/**
 * The persistence interface for the region service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.persistence.impl.RegionPersistenceImpl
 * @see RegionUtil
 * @generated
 */
@ProviderType
public interface RegionPersistence extends BasePersistence<Region> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RegionUtil} to access the region persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the regions where countryId = &#63;.
	*
	* @param countryId the country ID
	* @return the matching regions
	*/
	public java.util.List<Region> findByCountryId(long countryId);

	/**
	* Returns a range of all the regions where countryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param countryId the country ID
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @return the range of matching regions
	*/
	public java.util.List<Region> findByCountryId(long countryId, int start,
		int end);

	/**
	* Returns an ordered range of all the regions where countryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param countryId the country ID
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching regions
	*/
	public java.util.List<Region> findByCountryId(long countryId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator);

	/**
	* Returns an ordered range of all the regions where countryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param countryId the country ID
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching regions
	*/
	public java.util.List<Region> findByCountryId(long countryId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first region in the ordered set where countryId = &#63;.
	*
	* @param countryId the country ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching region
	* @throws NoSuchRegionException if a matching region could not be found
	*/
	public Region findByCountryId_First(long countryId,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator)
		throws NoSuchRegionException;

	/**
	* Returns the first region in the ordered set where countryId = &#63;.
	*
	* @param countryId the country ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching region, or <code>null</code> if a matching region could not be found
	*/
	public Region fetchByCountryId_First(long countryId,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator);

	/**
	* Returns the last region in the ordered set where countryId = &#63;.
	*
	* @param countryId the country ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching region
	* @throws NoSuchRegionException if a matching region could not be found
	*/
	public Region findByCountryId_Last(long countryId,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator)
		throws NoSuchRegionException;

	/**
	* Returns the last region in the ordered set where countryId = &#63;.
	*
	* @param countryId the country ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching region, or <code>null</code> if a matching region could not be found
	*/
	public Region fetchByCountryId_Last(long countryId,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator);

	/**
	* Returns the regions before and after the current region in the ordered set where countryId = &#63;.
	*
	* @param regionId the primary key of the current region
	* @param countryId the country ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next region
	* @throws NoSuchRegionException if a region with the primary key could not be found
	*/
	public Region[] findByCountryId_PrevAndNext(long regionId, long countryId,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator)
		throws NoSuchRegionException;

	/**
	* Removes all the regions where countryId = &#63; from the database.
	*
	* @param countryId the country ID
	*/
	public void removeByCountryId(long countryId);

	/**
	* Returns the number of regions where countryId = &#63;.
	*
	* @param countryId the country ID
	* @return the number of matching regions
	*/
	public int countByCountryId(long countryId);

	/**
	* Returns all the regions where active = &#63;.
	*
	* @param active the active
	* @return the matching regions
	*/
	public java.util.List<Region> findByActive(boolean active);

	/**
	* Returns a range of all the regions where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @return the range of matching regions
	*/
	public java.util.List<Region> findByActive(boolean active, int start,
		int end);

	/**
	* Returns an ordered range of all the regions where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching regions
	*/
	public java.util.List<Region> findByActive(boolean active, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator);

	/**
	* Returns an ordered range of all the regions where active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching regions
	*/
	public java.util.List<Region> findByActive(boolean active, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first region in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching region
	* @throws NoSuchRegionException if a matching region could not be found
	*/
	public Region findByActive_First(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator)
		throws NoSuchRegionException;

	/**
	* Returns the first region in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching region, or <code>null</code> if a matching region could not be found
	*/
	public Region fetchByActive_First(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator);

	/**
	* Returns the last region in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching region
	* @throws NoSuchRegionException if a matching region could not be found
	*/
	public Region findByActive_Last(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator)
		throws NoSuchRegionException;

	/**
	* Returns the last region in the ordered set where active = &#63;.
	*
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching region, or <code>null</code> if a matching region could not be found
	*/
	public Region fetchByActive_Last(boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator);

	/**
	* Returns the regions before and after the current region in the ordered set where active = &#63;.
	*
	* @param regionId the primary key of the current region
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next region
	* @throws NoSuchRegionException if a region with the primary key could not be found
	*/
	public Region[] findByActive_PrevAndNext(long regionId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator)
		throws NoSuchRegionException;

	/**
	* Removes all the regions where active = &#63; from the database.
	*
	* @param active the active
	*/
	public void removeByActive(boolean active);

	/**
	* Returns the number of regions where active = &#63;.
	*
	* @param active the active
	* @return the number of matching regions
	*/
	public int countByActive(boolean active);

	/**
	* Returns the region where countryId = &#63; and regionCode = &#63; or throws a {@link NoSuchRegionException} if it could not be found.
	*
	* @param countryId the country ID
	* @param regionCode the region code
	* @return the matching region
	* @throws NoSuchRegionException if a matching region could not be found
	*/
	public Region findByC_R(long countryId, java.lang.String regionCode)
		throws NoSuchRegionException;

	/**
	* Returns the region where countryId = &#63; and regionCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param countryId the country ID
	* @param regionCode the region code
	* @return the matching region, or <code>null</code> if a matching region could not be found
	*/
	public Region fetchByC_R(long countryId, java.lang.String regionCode);

	/**
	* Returns the region where countryId = &#63; and regionCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param countryId the country ID
	* @param regionCode the region code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching region, or <code>null</code> if a matching region could not be found
	*/
	public Region fetchByC_R(long countryId, java.lang.String regionCode,
		boolean retrieveFromCache);

	/**
	* Removes the region where countryId = &#63; and regionCode = &#63; from the database.
	*
	* @param countryId the country ID
	* @param regionCode the region code
	* @return the region that was removed
	*/
	public Region removeByC_R(long countryId, java.lang.String regionCode)
		throws NoSuchRegionException;

	/**
	* Returns the number of regions where countryId = &#63; and regionCode = &#63;.
	*
	* @param countryId the country ID
	* @param regionCode the region code
	* @return the number of matching regions
	*/
	public int countByC_R(long countryId, java.lang.String regionCode);

	/**
	* Returns all the regions where countryId = &#63; and active = &#63;.
	*
	* @param countryId the country ID
	* @param active the active
	* @return the matching regions
	*/
	public java.util.List<Region> findByC_A(long countryId, boolean active);

	/**
	* Returns a range of all the regions where countryId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param countryId the country ID
	* @param active the active
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @return the range of matching regions
	*/
	public java.util.List<Region> findByC_A(long countryId, boolean active,
		int start, int end);

	/**
	* Returns an ordered range of all the regions where countryId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param countryId the country ID
	* @param active the active
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching regions
	*/
	public java.util.List<Region> findByC_A(long countryId, boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator);

	/**
	* Returns an ordered range of all the regions where countryId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param countryId the country ID
	* @param active the active
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching regions
	*/
	public java.util.List<Region> findByC_A(long countryId, boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first region in the ordered set where countryId = &#63; and active = &#63;.
	*
	* @param countryId the country ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching region
	* @throws NoSuchRegionException if a matching region could not be found
	*/
	public Region findByC_A_First(long countryId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator)
		throws NoSuchRegionException;

	/**
	* Returns the first region in the ordered set where countryId = &#63; and active = &#63;.
	*
	* @param countryId the country ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching region, or <code>null</code> if a matching region could not be found
	*/
	public Region fetchByC_A_First(long countryId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator);

	/**
	* Returns the last region in the ordered set where countryId = &#63; and active = &#63;.
	*
	* @param countryId the country ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching region
	* @throws NoSuchRegionException if a matching region could not be found
	*/
	public Region findByC_A_Last(long countryId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator)
		throws NoSuchRegionException;

	/**
	* Returns the last region in the ordered set where countryId = &#63; and active = &#63;.
	*
	* @param countryId the country ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching region, or <code>null</code> if a matching region could not be found
	*/
	public Region fetchByC_A_Last(long countryId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator);

	/**
	* Returns the regions before and after the current region in the ordered set where countryId = &#63; and active = &#63;.
	*
	* @param regionId the primary key of the current region
	* @param countryId the country ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next region
	* @throws NoSuchRegionException if a region with the primary key could not be found
	*/
	public Region[] findByC_A_PrevAndNext(long regionId, long countryId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator)
		throws NoSuchRegionException;

	/**
	* Removes all the regions where countryId = &#63; and active = &#63; from the database.
	*
	* @param countryId the country ID
	* @param active the active
	*/
	public void removeByC_A(long countryId, boolean active);

	/**
	* Returns the number of regions where countryId = &#63; and active = &#63;.
	*
	* @param countryId the country ID
	* @param active the active
	* @return the number of matching regions
	*/
	public int countByC_A(long countryId, boolean active);

	/**
	* Caches the region in the entity cache if it is enabled.
	*
	* @param region the region
	*/
	public void cacheResult(Region region);

	/**
	* Caches the regions in the entity cache if it is enabled.
	*
	* @param regions the regions
	*/
	public void cacheResult(java.util.List<Region> regions);

	/**
	* Creates a new region with the primary key. Does not add the region to the database.
	*
	* @param regionId the primary key for the new region
	* @return the new region
	*/
	public Region create(long regionId);

	/**
	* Removes the region with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param regionId the primary key of the region
	* @return the region that was removed
	* @throws NoSuchRegionException if a region with the primary key could not be found
	*/
	public Region remove(long regionId) throws NoSuchRegionException;

	public Region updateImpl(Region region);

	/**
	* Returns the region with the primary key or throws a {@link NoSuchRegionException} if it could not be found.
	*
	* @param regionId the primary key of the region
	* @return the region
	* @throws NoSuchRegionException if a region with the primary key could not be found
	*/
	public Region findByPrimaryKey(long regionId) throws NoSuchRegionException;

	/**
	* Returns the region with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param regionId the primary key of the region
	* @return the region, or <code>null</code> if a region with the primary key could not be found
	*/
	public Region fetchByPrimaryKey(long regionId);

	@Override
	public java.util.Map<java.io.Serializable, Region> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the regions.
	*
	* @return the regions
	*/
	public java.util.List<Region> findAll();

	/**
	* Returns a range of all the regions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @return the range of regions
	*/
	public java.util.List<Region> findAll(int start, int end);

	/**
	* Returns an ordered range of all the regions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of regions
	*/
	public java.util.List<Region> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator);

	/**
	* Returns an ordered range of all the regions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of regions
	*/
	public java.util.List<Region> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the regions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of regions.
	*
	* @return the number of regions
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}