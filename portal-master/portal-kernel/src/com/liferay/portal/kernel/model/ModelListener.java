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

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.exception.ModelListenerException;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public interface ModelListener<T> {

	public void onAfterAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException;

	public void onAfterCreate(T model) throws ModelListenerException;

	public void onAfterRemove(T model) throws ModelListenerException;

	public void onAfterRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException;

	public void onAfterUpdate(T model) throws ModelListenerException;

	public void onBeforeAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException;

	public void onBeforeCreate(T model) throws ModelListenerException;

	public void onBeforeRemove(T model) throws ModelListenerException;

	public void onBeforeRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException;

	public void onBeforeUpdate(T model) throws ModelListenerException;

}