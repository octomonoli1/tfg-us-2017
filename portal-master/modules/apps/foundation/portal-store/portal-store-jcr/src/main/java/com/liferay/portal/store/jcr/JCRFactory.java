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

package com.liferay.portal.store.jcr;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

/**
 * @author Michael Young
 * @author Manuel de la Peña
 */
public interface JCRFactory {

	public Session createSession(String workspaceName)
		throws RepositoryException;

	public void initialize() throws RepositoryException;

	public void prepare() throws RepositoryException;

	public void shutdown();

}