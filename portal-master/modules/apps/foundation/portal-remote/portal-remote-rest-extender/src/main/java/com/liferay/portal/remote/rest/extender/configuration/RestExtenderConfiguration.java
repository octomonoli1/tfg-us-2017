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

package com.liferay.portal.remote.rest.extender.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Carlos Sierra Andrés
 */
@ExtendedObjectClassDefinition(
	category = "foundation", scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
	factory = true,
	id = "com.liferay.portal.remote.rest.extender.configuration.RestExtenderConfiguration",
	localization = "content/Language", name = "rest.extender.configuration.name"
)
public interface RestExtenderConfiguration {

	@Meta.AD(required = false)
	public String[] contextPaths();

	@Meta.AD(name = "jaxrs.applications.filters", required = false)
	public String[] jaxRsApplicationFilterStrings();

	@Meta.AD(name = "jax.rs.provider.filters", required = false)
	public String[] jaxRsProviderFilterStrings();

	@Meta.AD(name = "jax.rs.service.filters", required = false)
	public String[] jaxRsServiceFilterStrings();

}