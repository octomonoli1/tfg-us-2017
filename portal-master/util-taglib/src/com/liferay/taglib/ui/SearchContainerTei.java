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

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

/**
 * @author Raymond Augé
 */
public class SearchContainerTei extends TagExtraInfo {

	@Override
	public VariableInfo[] getVariableInfo(TagData tagData) {
		String totalVar = tagData.getAttributeString("totalVar");

		if (Validator.isNull(totalVar)) {
			totalVar = SearchContainer.DEFAULT_TOTAL_VAR;
		}

		String var = tagData.getAttributeString("var");

		if (Validator.isNull(var)) {
			var = SearchContainer.DEFAULT_VAR;
		}

		return new VariableInfo[] {
			new VariableInfo(
				totalVar, Integer.class.getName(), true, VariableInfo.NESTED),
			new VariableInfo(
				var, SearchContainer.class.getName(), true, VariableInfo.NESTED)
		};
	}

}