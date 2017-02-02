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

package com.liferay.portal.security.sso.google.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Stian Sigvartsen
 */
@ExtendedObjectClassDefinition(category = "foundation")
@Meta.OCD(
	id = "com.liferay.portal.security.sso.google.configuration.GoogleAuthorizationConfiguration",
	localization = "content/Language",
	name = "google.authorization.configuration.name"
)
public interface GoogleAuthorizationConfiguration {

	@Meta.AD(deflt = "false", description = "enabled-help", required = false)
	public boolean enabled();

	@Meta.AD(deflt = "", description = "client-id-help", required = false)
	public String clientId();

	@Meta.AD(deflt = "", description = "client-secret-help", required = false)
	public String clientSecret();

}