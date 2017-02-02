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

import com.liferay.portal.kernel.exception.NoSuchRepositoryException;
import com.liferay.portal.kernel.model.Repository;

/**
 * The persistence interface for the repository service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.persistence.impl.RepositoryPersistenceImpl
 * @see RepositoryUtil
 * @generated
 */
@ProviderType
public interface RepositoryPersistence extends BasePersistence<Repository> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RepositoryUtil} to access the repository persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the repositories where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching repositories
	*/
	public java.util.List<Repository> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the repositories where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of repositories
	* @param end the upper bound of the range of repositories (not inclusive)
	* @return the range of matching repositories
	*/
	public java.util.List<Repository> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the repositories where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of repositories
	* @param end the upper bound of the range of repositories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching repositories
	*/
	public java.util.List<Repository> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator);

	/**
	* Returns an ordered range of all the repositories where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of repositories
	* @param end the upper bound of the range of repositories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching repositories
	*/
	public java.util.List<Repository> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first repository in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching repository
	* @throws NoSuchRepositoryException if a matching repository could not be found
	*/
	public Repository findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator)
		throws NoSuchRepositoryException;

	/**
	* Returns the first repository in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching repository, or <code>null</code> if a matching repository could not be found
	*/
	public Repository fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator);

	/**
	* Returns the last repository in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching repository
	* @throws NoSuchRepositoryException if a matching repository could not be found
	*/
	public Repository findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator)
		throws NoSuchRepositoryException;

	/**
	* Returns the last repository in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching repository, or <code>null</code> if a matching repository could not be found
	*/
	public Repository fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator);

	/**
	* Returns the repositories before and after the current repository in the ordered set where uuid = &#63;.
	*
	* @param repositoryId the primary key of the current repository
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next repository
	* @throws NoSuchRepositoryException if a repository with the primary key could not be found
	*/
	public Repository[] findByUuid_PrevAndNext(long repositoryId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator)
		throws NoSuchRepositoryException;

	/**
	* Removes all the repositories where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of repositories where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching repositories
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the repository where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRepositoryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching repository
	* @throws NoSuchRepositoryException if a matching repository could not be found
	*/
	public Repository findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchRepositoryException;

	/**
	* Returns the repository where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching repository, or <code>null</code> if a matching repository could not be found
	*/
	public Repository fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the repository where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching repository, or <code>null</code> if a matching repository could not be found
	*/
	public Repository fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the repository where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the repository that was removed
	*/
	public Repository removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchRepositoryException;

	/**
	* Returns the number of repositories where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching repositories
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the repositories where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching repositories
	*/
	public java.util.List<Repository> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the repositories where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of repositories
	* @param end the upper bound of the range of repositories (not inclusive)
	* @return the range of matching repositories
	*/
	public java.util.List<Repository> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the repositories where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of repositories
	* @param end the upper bound of the range of repositories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching repositories
	*/
	public java.util.List<Repository> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator);

	/**
	* Returns an ordered range of all the repositories where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of repositories
	* @param end the upper bound of the range of repositories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching repositories
	*/
	public java.util.List<Repository> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first repository in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching repository
	* @throws NoSuchRepositoryException if a matching repository could not be found
	*/
	public Repository findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator)
		throws NoSuchRepositoryException;

	/**
	* Returns the first repository in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching repository, or <code>null</code> if a matching repository could not be found
	*/
	public Repository fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator);

	/**
	* Returns the last repository in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching repository
	* @throws NoSuchRepositoryException if a matching repository could not be found
	*/
	public Repository findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator)
		throws NoSuchRepositoryException;

	/**
	* Returns the last repository in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching repository, or <code>null</code> if a matching repository could not be found
	*/
	public Repository fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator);

	/**
	* Returns the repositories before and after the current repository in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param repositoryId the primary key of the current repository
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next repository
	* @throws NoSuchRepositoryException if a repository with the primary key could not be found
	*/
	public Repository[] findByUuid_C_PrevAndNext(long repositoryId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator)
		throws NoSuchRepositoryException;

	/**
	* Removes all the repositories where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of repositories where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching repositories
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the repositories where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching repositories
	*/
	public java.util.List<Repository> findByGroupId(long groupId);

	/**
	* Returns a range of all the repositories where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of repositories
	* @param end the upper bound of the range of repositories (not inclusive)
	* @return the range of matching repositories
	*/
	public java.util.List<Repository> findByGroupId(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the repositories where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of repositories
	* @param end the upper bound of the range of repositories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching repositories
	*/
	public java.util.List<Repository> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator);

	/**
	* Returns an ordered range of all the repositories where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of repositories
	* @param end the upper bound of the range of repositories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching repositories
	*/
	public java.util.List<Repository> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first repository in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching repository
	* @throws NoSuchRepositoryException if a matching repository could not be found
	*/
	public Repository findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator)
		throws NoSuchRepositoryException;

	/**
	* Returns the first repository in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching repository, or <code>null</code> if a matching repository could not be found
	*/
	public Repository fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator);

	/**
	* Returns the last repository in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching repository
	* @throws NoSuchRepositoryException if a matching repository could not be found
	*/
	public Repository findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator)
		throws NoSuchRepositoryException;

	/**
	* Returns the last repository in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching repository, or <code>null</code> if a matching repository could not be found
	*/
	public Repository fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator);

	/**
	* Returns the repositories before and after the current repository in the ordered set where groupId = &#63;.
	*
	* @param repositoryId the primary key of the current repository
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next repository
	* @throws NoSuchRepositoryException if a repository with the primary key could not be found
	*/
	public Repository[] findByGroupId_PrevAndNext(long repositoryId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator)
		throws NoSuchRepositoryException;

	/**
	* Removes all the repositories where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of repositories where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching repositories
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the repository where groupId = &#63; and name = &#63; and portletId = &#63; or throws a {@link NoSuchRepositoryException} if it could not be found.
	*
	* @param groupId the group ID
	* @param name the name
	* @param portletId the portlet ID
	* @return the matching repository
	* @throws NoSuchRepositoryException if a matching repository could not be found
	*/
	public Repository findByG_N_P(long groupId, java.lang.String name,
		java.lang.String portletId) throws NoSuchRepositoryException;

	/**
	* Returns the repository where groupId = &#63; and name = &#63; and portletId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param name the name
	* @param portletId the portlet ID
	* @return the matching repository, or <code>null</code> if a matching repository could not be found
	*/
	public Repository fetchByG_N_P(long groupId, java.lang.String name,
		java.lang.String portletId);

	/**
	* Returns the repository where groupId = &#63; and name = &#63; and portletId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param name the name
	* @param portletId the portlet ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching repository, or <code>null</code> if a matching repository could not be found
	*/
	public Repository fetchByG_N_P(long groupId, java.lang.String name,
		java.lang.String portletId, boolean retrieveFromCache);

	/**
	* Removes the repository where groupId = &#63; and name = &#63; and portletId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param name the name
	* @param portletId the portlet ID
	* @return the repository that was removed
	*/
	public Repository removeByG_N_P(long groupId, java.lang.String name,
		java.lang.String portletId) throws NoSuchRepositoryException;

	/**
	* Returns the number of repositories where groupId = &#63; and name = &#63; and portletId = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @param portletId the portlet ID
	* @return the number of matching repositories
	*/
	public int countByG_N_P(long groupId, java.lang.String name,
		java.lang.String portletId);

	/**
	* Caches the repository in the entity cache if it is enabled.
	*
	* @param repository the repository
	*/
	public void cacheResult(Repository repository);

	/**
	* Caches the repositories in the entity cache if it is enabled.
	*
	* @param repositories the repositories
	*/
	public void cacheResult(java.util.List<Repository> repositories);

	/**
	* Creates a new repository with the primary key. Does not add the repository to the database.
	*
	* @param repositoryId the primary key for the new repository
	* @return the new repository
	*/
	public Repository create(long repositoryId);

	/**
	* Removes the repository with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param repositoryId the primary key of the repository
	* @return the repository that was removed
	* @throws NoSuchRepositoryException if a repository with the primary key could not be found
	*/
	public Repository remove(long repositoryId)
		throws NoSuchRepositoryException;

	public Repository updateImpl(Repository repository);

	/**
	* Returns the repository with the primary key or throws a {@link NoSuchRepositoryException} if it could not be found.
	*
	* @param repositoryId the primary key of the repository
	* @return the repository
	* @throws NoSuchRepositoryException if a repository with the primary key could not be found
	*/
	public Repository findByPrimaryKey(long repositoryId)
		throws NoSuchRepositoryException;

	/**
	* Returns the repository with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param repositoryId the primary key of the repository
	* @return the repository, or <code>null</code> if a repository with the primary key could not be found
	*/
	public Repository fetchByPrimaryKey(long repositoryId);

	@Override
	public java.util.Map<java.io.Serializable, Repository> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the repositories.
	*
	* @return the repositories
	*/
	public java.util.List<Repository> findAll();

	/**
	* Returns a range of all the repositories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of repositories
	* @param end the upper bound of the range of repositories (not inclusive)
	* @return the range of repositories
	*/
	public java.util.List<Repository> findAll(int start, int end);

	/**
	* Returns an ordered range of all the repositories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of repositories
	* @param end the upper bound of the range of repositories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of repositories
	*/
	public java.util.List<Repository> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator);

	/**
	* Returns an ordered range of all the repositories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of repositories
	* @param end the upper bound of the range of repositories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of repositories
	*/
	public java.util.List<Repository> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Repository> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the repositories from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of repositories.
	*
	* @return the number of repositories
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}