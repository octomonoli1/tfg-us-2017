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

package com.liferay.portal.service;

import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.spring.aop.ChainableMethodAdvice;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Preston Crary
 */
public class ServiceContextAdvice extends ChainableMethodAdvice {

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		if (!hasServiceContextParameter(methodInvocation.getMethod())) {
			serviceBeanAopCacheManager.removeMethodInterceptor(
				methodInvocation, this);
		}

		boolean pushedServiceContext = pushServiceContext(methodInvocation);

		try {
			return methodInvocation.proceed();
		}
		finally {
			if (pushedServiceContext) {
				ServiceContextThreadLocal.popServiceContext();
			}
		}
	}

	protected boolean hasServiceContextParameter(Method method) {
		Class<?>[] parameterTypes = method.getParameterTypes();

		for (int i = parameterTypes.length - 1; i >= 0; i--) {
			if (ServiceContext.class.isAssignableFrom(parameterTypes[i])) {
				return true;
			}
		}

		return false;
	}

	protected boolean pushServiceContext(MethodInvocation methodInvocation) {
		Object[] arguments = methodInvocation.getArguments();

		if (arguments == null) {
			return false;
		}

		for (int i = arguments.length - 1; i >= 0; i--) {
			if (arguments[i] instanceof ServiceContext) {
				ServiceContext serviceContext = (ServiceContext)arguments[i];

				ServiceContextThreadLocal.pushServiceContext(serviceContext);

				return true;
			}
		}

		return false;
	}

}