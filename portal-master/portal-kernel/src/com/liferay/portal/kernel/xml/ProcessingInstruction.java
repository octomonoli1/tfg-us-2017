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

package com.liferay.portal.kernel.xml;

import aQute.bnd.annotation.ProviderType;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public interface ProcessingInstruction extends Node {

	public String getTarget();

	@Override
	public String getText();

	public String getValue(String name);

	public Map<String, String> getValues();

	public boolean removeValue(String name);

	public void setTarget(String target);

	public void setValue(String name, String value);

	public void setValues(Map<String, String> data);

}