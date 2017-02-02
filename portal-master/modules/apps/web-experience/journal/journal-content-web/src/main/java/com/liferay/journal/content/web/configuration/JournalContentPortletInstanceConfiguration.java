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

package com.liferay.journal.content.web.configuration;

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
	id = "com.liferay.journal.content.web.configuration.JournalContentPortletInstanceConfiguration",
	localization = "content/Language",
	name = "journal.content.portlet.instance.configuration.name"
)
public interface JournalContentPortletInstanceConfiguration {

	@Meta.AD(required = false)
	public String articleId();

	@Meta.AD(required = false)
	public String contentMetadataAssetAddonEntryKeys();

	@Meta.AD(required = false)
	public String ddmTemplateKey();

	@Meta.AD(required = false)
	public String enableViewCountIncrement();

	@Meta.AD(deflt = "0", required = false)
	public long groupId();

	@Meta.AD(required = false)
	public String userToolAssetAddonEntryKeys();

}