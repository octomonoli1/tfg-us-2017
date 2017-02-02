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

package com.liferay.util.aspectj;

import com.liferay.portal.kernel.util.ServerDetector;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author Brian Wing Shun Chan
 */
public class AspectJUtil {

	public static Method getMethod(MethodSignature methodSignature)
		throws NoSuchMethodException {

		Method method = null;

		if (ServerDetector.isWebSphere()) {
			Class<?> declaringType = methodSignature.getDeclaringType();
			String name = methodSignature.getName();
			Class<?>[] parameterTypes = methodSignature.getParameterTypes();

			method = declaringType.getMethod(name, parameterTypes);
		}
		else {
			method = methodSignature.getMethod();
		}

		return method;
	}

	public static Method getMethod(ProceedingJoinPoint proceedingJoinPoint)
		throws NoSuchMethodException {

		MethodSignature methodSignature =
			(MethodSignature)proceedingJoinPoint.getSignature();

		return getMethod(methodSignature);
	}

}