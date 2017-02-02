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

package com.liferay.xsl.content.web.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Juergen Kappler
 */
@ExtendedObjectClassDefinition(
	category = "web-experience",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = "com.liferay.xsl.content.web.configuration.XSLContentPortletInstanceConfiguration",
	localization = "content/Language",
	name = "xsl.content.portlet.instance.configuration.name"
)
public interface XSLContentPortletInstanceConfiguration {

	@Meta.AD(deflt = "@portlet_context_url@/example.xml", required = false)
	public String xmlUrl();

	@Meta.AD(deflt = "@portlet_context_url@/example.xsl", required = false)
	public String xslUrl();

}