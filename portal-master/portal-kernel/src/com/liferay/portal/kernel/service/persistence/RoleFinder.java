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

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface RoleFinder {
	public int countByKeywords(long companyId, java.lang.String keywords,
		java.lang.Integer[] types);

	public int countByKeywords(long companyId, java.lang.String keywords,
		java.lang.Integer[] types,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params);

	public int countByUserGroupGroupRole(long userId, long groupId);

	public int countByR_U(long roleId, long userId);

	public int countByU_G_R(long userId, long groupId, long roleId);

	public int countByC_N_D_T(long companyId, java.lang.String name,
		java.lang.String description, java.lang.Integer[] types,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator);

	public int countByC_N_D_T(long companyId, java.lang.String[] names,
		java.lang.String[] descriptions, java.lang.Integer[] types,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator);

	public int filterCountByKeywords(long companyId, java.lang.String keywords,
		java.lang.Integer[] types,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params);

	public int filterCountByC_N_D_T(long companyId, java.lang.String name,
		java.lang.String description, java.lang.Integer[] types,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator);

	public int filterCountByC_N_D_T(long companyId, java.lang.String[] names,
		java.lang.String[] descriptions, java.lang.Integer[] types,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator);

	public java.util.List<com.liferay.portal.kernel.model.Role> filterFindByKeywords(
		long companyId, java.lang.String keywords, java.lang.Integer[] types,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Role> obc);

	public java.util.List<com.liferay.portal.kernel.model.Role> filterFindByC_N_D_T(
		long companyId, java.lang.String name, java.lang.String description,
		java.lang.Integer[] types,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Role> obc);

	public java.util.List<com.liferay.portal.kernel.model.Role> filterFindByC_N_D_T(
		long companyId, java.lang.String[] names,
		java.lang.String[] descriptions, java.lang.Integer[] types,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Role> obc);

	public java.util.List<com.liferay.portal.kernel.model.Role> findByKeywords(
		long companyId, java.lang.String keywords, java.lang.Integer[] types,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Role> obc);

	public java.util.List<com.liferay.portal.kernel.model.Role> findByKeywords(
		long companyId, java.lang.String keywords, java.lang.Integer[] types,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Role> obc);

	public java.util.List<com.liferay.portal.kernel.model.Role> findBySystem(
		long companyId);

	public java.util.List<com.liferay.portal.kernel.model.Role> findByUserGroupGroupRole(
		long userId, long groupId);

	public java.util.List<com.liferay.portal.kernel.model.Role> findByUserGroupGroupRole(
		long userId, long groupId, int start, int end);

	public java.util.List<com.liferay.portal.kernel.model.Role> findByUserGroupRole(
		long userId, long groupId);

	public com.liferay.portal.kernel.model.Role findByC_N(long companyId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.NoSuchRoleException;

	public java.util.List<com.liferay.portal.kernel.model.Role> findByU_G(
		long userId,
		java.util.List<com.liferay.portal.kernel.model.Group> groups);

	public java.util.List<com.liferay.portal.kernel.model.Role> findByU_G(
		long userId, long groupId);

	public java.util.List<com.liferay.portal.kernel.model.Role> findByU_G(
		long userId, long[] groupIds);

	public java.util.List<com.liferay.portal.kernel.model.Role> findByR_N_A(
		long resourceBlockId, java.lang.String className,
		java.lang.String actionId);

	public java.util.List<com.liferay.portal.kernel.model.Role> findByC_N_D_T(
		long companyId, java.lang.String name, java.lang.String description,
		java.lang.Integer[] types,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Role> obc);

	public java.util.List<com.liferay.portal.kernel.model.Role> findByC_N_D_T(
		long companyId, java.lang.String[] names,
		java.lang.String[] descriptions, java.lang.Integer[] types,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Role> obc);

	public java.util.Map<java.lang.String, java.util.List<java.lang.String>> findByC_N_S_P(
		long companyId, java.lang.String name, int scope,
		java.lang.String primKey);

	public java.util.List<com.liferay.portal.kernel.model.Role> findByC_N_S_P_A(
		long companyId, java.lang.String name, int scope,
		java.lang.String primKey, java.lang.String actionId);
}