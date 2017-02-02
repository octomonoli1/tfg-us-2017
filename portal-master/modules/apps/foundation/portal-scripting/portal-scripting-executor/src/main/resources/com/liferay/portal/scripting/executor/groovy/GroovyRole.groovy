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

package com.liferay.portal.scripting.executor.groovy;

import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.service.ResourceBlockLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;

/**
 * @author Michael C. Han
 */
class GroovyRole {

	static GroovyRole organizationRole(String name, String description) {
		GroovyRole groovyRole = new GroovyRole();

		groovyRole.name = name;
		groovyRole.description = description;
		groovyRole.type = RoleConstants.TYPE_ORGANIZATION;

		return groovyRole;
	}

	static GroovyRole portalRole(String name, String description) {
		GroovyRole groovyRole = new GroovyRole();

		groovyRole.name = name;
		groovyRole.description = description;
		groovyRole.type = RoleConstants.TYPE_REGULAR;

		return groovyRole;
	}

	static GroovyRole siteRole(String name, String description) {
		GroovyRole groovyRole = new GroovyRole();

		groovyRole.name = name;
		groovyRole.description = description;
		groovyRole.type = RoleConstants.TYPE_SITE;

		return groovyRole;
	}

	void create(GroovyScriptingContext groovyScriptingContext) {
		role = RoleLocalServiceUtil.fetchRole(
			groovyScriptingContext.companyId, name);

		if (role != null) {
			return;
		}

		role = RoleLocalServiceUtil.addRole(
			groovyScriptingContext.defaultUserId, null, 0, name,
			GroovyScriptingContext.getLocalizationMap(name),
			GroovyScriptingContext.getLocalizationMap(description), type, null,
			groovyScriptingContext.serviceContext);
	}

	void updatePermissions(
		String resourceName, String[] actionIds, boolean add,
		GroovyScriptingContext groovyScriptingContext) {

		boolean resourceBlockSupported =
			ResourceBlockLocalServiceUtil.isSupported(resourceName);

		int scope = ResourceConstants.SCOPE_COMPANY;

		if ((role.getType() == RoleConstants.TYPE_ORGANIZATION) ||
			(role.getType() == RoleConstants.TYPE_SITE)) {

			scope = ResourceConstants.SCOPE_GROUP_TEMPLATE;
		}

		for (String actionId : actionIds) {
			if (add) {
				if (resourceBlockSupported) {
					ResourceBlockLocalServiceUtil.addCompanyScopePermission(
						groovyScriptingContext.companyId, resourceName,
						role.getRoleId(), actionId);
				}
				else {
					if (scope == ResourceConstants.SCOPE_COMPANY) {
						ResourcePermissionLocalServiceUtil.
							addResourcePermission(
								groovyScriptingContext.companyId, resourceName,
								scope, String.valueOf(role.getCompanyId()),
								role.getRoleId(), actionId);
					}
					else if (scope == ResourceConstants.SCOPE_GROUP_TEMPLATE) {
						ResourcePermissionLocalServiceUtil.
							addResourcePermission(
								groovyScriptingContext.companyId, resourceName,
								scope,
								String.valueOf(
									GroupConstants.DEFAULT_PARENT_GROUP_ID),
								role.getRoleId(), actionId);
					}
				}
			}
			else {
				if (resourceBlockSupported) {
					ResourceBlockLocalServiceUtil.removeCompanyScopePermission(
						groovyScriptingContext.companyId, resourceName,
						role.getRoleId(), actionId);
				}
				else {
					ResourcePermissionLocalServiceUtil.
						removeResourcePermissions(
							groovyScriptingContext.companyId, resourceName,
							scope, role.getRoleId(), actionId);
				}
			}
		}
	}

	String description;
	String name;
	Role role;
	int type;

}