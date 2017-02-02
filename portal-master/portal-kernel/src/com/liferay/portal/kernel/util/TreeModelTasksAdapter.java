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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.TreeModel;

import java.util.List;

/**
 * @deprecated As of 7.0.0, moved to {@link
 *             com.liferay.portal.kernel.tree.TreeModelTasksAdapter}
 * @author Shinn Lok
 */
@Deprecated
public class TreeModelTasksAdapter<T extends TreeModel>
	implements TreeModelTasks<T> {

	@Override
	public List<T> findTreeModels(
		long previousId, long companyId, long parentPrimaryKey, int size) {

		return null;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public void rebuildDependentModelsTreePaths(
			long parentPrimaryKey, String treePath)
		throws PortalException {
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public void reindexTreeModels(List<TreeModel> treeModels)
		throws PortalException {
	}

}