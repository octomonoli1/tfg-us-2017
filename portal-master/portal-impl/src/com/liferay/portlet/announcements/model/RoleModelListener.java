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

package com.liferay.portlet.announcements.model;

import com.liferay.announcements.kernel.service.AnnouncementsEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Role;

/**
 * @author Christopher Kian
 */
public class RoleModelListener extends BaseModelListener<Role> {

	@Override
	public void onBeforeRemove(Role role) throws ModelListenerException {
		try {
			AnnouncementsEntryLocalServiceUtil.deleteEntries(
				role.getClassNameId(), role.getRoleId());
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

}