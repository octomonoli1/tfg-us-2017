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

import com.liferay.portal.kernel.exception.NoSuchWebDAVPropsException;
import com.liferay.portal.kernel.model.WebDAVProps;

/**
 * The persistence interface for the web d a v props service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.persistence.impl.WebDAVPropsPersistenceImpl
 * @see WebDAVPropsUtil
 * @generated
 */
@ProviderType
public interface WebDAVPropsPersistence extends BasePersistence<WebDAVProps> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WebDAVPropsUtil} to access the web d a v props persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the web d a v props where classNameId = &#63; and classPK = &#63; or throws a {@link NoSuchWebDAVPropsException} if it could not be found.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching web d a v props
	* @throws NoSuchWebDAVPropsException if a matching web d a v props could not be found
	*/
	public WebDAVProps findByC_C(long classNameId, long classPK)
		throws NoSuchWebDAVPropsException;

	/**
	* Returns the web d a v props where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching web d a v props, or <code>null</code> if a matching web d a v props could not be found
	*/
	public WebDAVProps fetchByC_C(long classNameId, long classPK);

	/**
	* Returns the web d a v props where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching web d a v props, or <code>null</code> if a matching web d a v props could not be found
	*/
	public WebDAVProps fetchByC_C(long classNameId, long classPK,
		boolean retrieveFromCache);

	/**
	* Removes the web d a v props where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the web d a v props that was removed
	*/
	public WebDAVProps removeByC_C(long classNameId, long classPK)
		throws NoSuchWebDAVPropsException;

	/**
	* Returns the number of web d a v propses where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching web d a v propses
	*/
	public int countByC_C(long classNameId, long classPK);

	/**
	* Caches the web d a v props in the entity cache if it is enabled.
	*
	* @param webDAVProps the web d a v props
	*/
	public void cacheResult(WebDAVProps webDAVProps);

	/**
	* Caches the web d a v propses in the entity cache if it is enabled.
	*
	* @param webDAVPropses the web d a v propses
	*/
	public void cacheResult(java.util.List<WebDAVProps> webDAVPropses);

	/**
	* Creates a new web d a v props with the primary key. Does not add the web d a v props to the database.
	*
	* @param webDavPropsId the primary key for the new web d a v props
	* @return the new web d a v props
	*/
	public WebDAVProps create(long webDavPropsId);

	/**
	* Removes the web d a v props with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param webDavPropsId the primary key of the web d a v props
	* @return the web d a v props that was removed
	* @throws NoSuchWebDAVPropsException if a web d a v props with the primary key could not be found
	*/
	public WebDAVProps remove(long webDavPropsId)
		throws NoSuchWebDAVPropsException;

	public WebDAVProps updateImpl(WebDAVProps webDAVProps);

	/**
	* Returns the web d a v props with the primary key or throws a {@link NoSuchWebDAVPropsException} if it could not be found.
	*
	* @param webDavPropsId the primary key of the web d a v props
	* @return the web d a v props
	* @throws NoSuchWebDAVPropsException if a web d a v props with the primary key could not be found
	*/
	public WebDAVProps findByPrimaryKey(long webDavPropsId)
		throws NoSuchWebDAVPropsException;

	/**
	* Returns the web d a v props with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param webDavPropsId the primary key of the web d a v props
	* @return the web d a v props, or <code>null</code> if a web d a v props with the primary key could not be found
	*/
	public WebDAVProps fetchByPrimaryKey(long webDavPropsId);

	@Override
	public java.util.Map<java.io.Serializable, WebDAVProps> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the web d a v propses.
	*
	* @return the web d a v propses
	*/
	public java.util.List<WebDAVProps> findAll();

	/**
	* Returns a range of all the web d a v propses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebDAVPropsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of web d a v propses
	* @param end the upper bound of the range of web d a v propses (not inclusive)
	* @return the range of web d a v propses
	*/
	public java.util.List<WebDAVProps> findAll(int start, int end);

	/**
	* Returns an ordered range of all the web d a v propses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebDAVPropsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of web d a v propses
	* @param end the upper bound of the range of web d a v propses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of web d a v propses
	*/
	public java.util.List<WebDAVProps> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WebDAVProps> orderByComparator);

	/**
	* Returns an ordered range of all the web d a v propses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebDAVPropsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of web d a v propses
	* @param end the upper bound of the range of web d a v propses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of web d a v propses
	*/
	public java.util.List<WebDAVProps> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WebDAVProps> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the web d a v propses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of web d a v propses.
	*
	* @return the number of web d a v propses
	*/
	public int countAll();
}