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

package com.liferay.portal.store.cmis.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Manuel de la Peña
 */
@ExtendedObjectClassDefinition(category = "foundation")
@Meta.OCD(
	id = "com.liferay.portal.store.cmis.configuration.CMISStoreConfiguration",
	localization = "content/Language", name = "cmis.store.configuration.name"
)
public interface CMISStoreConfiguration {

	@Meta.AD(
		deflt = "http://localhost:8080/alfresco/service/api/cmis",
		required = true
	)
	public String repositoryUrl();

	@Meta.AD(deflt = "none", required = true)
	public String credentialsUsername();

	@Meta.AD(deflt = "none", required = true)
	public String credentialsPassword();

	@Meta.AD(deflt = "Liferay Home", required = true)
	public String systemRootDir();

}