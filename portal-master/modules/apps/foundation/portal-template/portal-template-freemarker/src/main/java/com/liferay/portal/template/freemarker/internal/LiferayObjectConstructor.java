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

package com.liferay.portal.template.freemarker.internal;

import com.liferay.portal.kernel.util.ClassLoaderUtil;

import freemarker.ext.beans.BeansWrapper;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

/**
 * @author Raymond Augé
 */
public class LiferayObjectConstructor implements TemplateMethodModelEx {

	@Override
	public Object exec(@SuppressWarnings("rawtypes") List arguments)
		throws TemplateModelException {

		if (arguments.isEmpty()) {
			throw new TemplateModelException(
				"This method must have at least one argument as the name of " +
					"the class to instantiate");
		}

		Class<?> clazz = null;

		try {
			String className = String.valueOf(arguments.get(0));

			clazz = Class.forName(
				className, true, ClassLoaderUtil.getContextClassLoader());
		}
		catch (Exception e) {
			throw new TemplateModelException(e.getMessage());
		}

		BeansWrapper beansWrapper = FreeMarkerManager.getBeansWrapper();

		Object object = beansWrapper.newInstance(
			clazz, arguments.subList(1, arguments.size()));

		return beansWrapper.wrap(object);
	}

}