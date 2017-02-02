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

package com.liferay.document.library.web.internal.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.Repository;
import com.liferay.portal.kernel.repository.RepositoryProviderUtil;
import com.liferay.portal.kernel.repository.capabilities.TrashCapability;
import com.liferay.trash.kernel.util.TrashUtil;

/**
 * @author Adolfo Pérez
 */
public class DLTrashUtil {

	public static boolean isTrashEnabled(long groupId, long repositoryId)
		throws PortalException {

		if (!TrashUtil.isTrashEnabled(groupId)) {
			return false;
		}

		if (repositoryId == groupId) {
			return true;
		}

		Repository repository = RepositoryProviderUtil.getRepository(
			repositoryId);

		return repository.isCapabilityProvided(TrashCapability.class);
	}

}