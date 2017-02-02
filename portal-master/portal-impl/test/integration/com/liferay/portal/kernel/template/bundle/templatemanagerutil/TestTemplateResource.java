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

package com.liferay.portal.kernel.template.bundle.templatemanagerutil;

import com.liferay.portal.kernel.template.TemplateResource;

import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Reader;

/**
 * @author Philip Jones
 */
public class TestTemplateResource implements TemplateResource {

	public static final String TEST_TEMPLATE_RESOURCE_TEMPLATE_ID =
		"TEST_TEMPLATE_RESOURCE_TEMPLATE_ID";

	@Override
	public long getLastModified() {
		return 0;
	}

	@Override
	public Reader getReader() {
		return null;
	}

	@Override
	public String getTemplateId() {
		return TEST_TEMPLATE_RESOURCE_TEMPLATE_ID;
	}

	@Override
	public void readExternal(ObjectInput objectInput) {
		return;
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) {
		return;
	}

}