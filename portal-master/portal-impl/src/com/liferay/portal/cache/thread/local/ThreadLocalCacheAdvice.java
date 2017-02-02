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

package com.liferay.portal.cache.thread.local;

import com.liferay.portal.kernel.cache.thread.local.Lifecycle;
import com.liferay.portal.kernel.cache.thread.local.ThreadLocalCachable;
import com.liferay.portal.kernel.cache.thread.local.ThreadLocalCache;
import com.liferay.portal.kernel.cache.thread.local.ThreadLocalCacheManager;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.aop.AnnotationChainableMethodAdvice;
import com.liferay.portal.spring.aop.ServiceBeanMethodInvocation;

import java.io.Serializable;

import java.lang.annotation.Annotation;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class ThreadLocalCacheAdvice
	extends AnnotationChainableMethodAdvice<ThreadLocalCachable> {

	@Override
	public void afterReturning(MethodInvocation methodInvocation, Object result)
		throws Throwable {

		ThreadLocalCachable threadLocalCachable = findAnnotation(
			methodInvocation);

		if (threadLocalCachable == _nullThreadLocalCacheable) {
			return;
		}

		Serializable cacheName = _getCacheName(methodInvocation);

		ThreadLocalCache<Object> threadLocalCache =
			ThreadLocalCacheManager.getThreadLocalCache(
				threadLocalCachable.scope(), cacheName);

		String cacheKey = _getCacheKey(methodInvocation.getArguments());

		if (result == null) {
			threadLocalCache.put(cacheKey, nullResult);
		}
		else {
			threadLocalCache.put(cacheKey, result);
		}
	}

	@Override
	public Object before(MethodInvocation methodInvocation) throws Throwable {
		ThreadLocalCachable threadLocalCachable = findAnnotation(
			methodInvocation);

		if (threadLocalCachable == _nullThreadLocalCacheable) {
			return null;
		}

		Serializable cacheName = _getCacheName(methodInvocation);

		ThreadLocalCache<?> threadLocalCache =
			ThreadLocalCacheManager.getThreadLocalCache(
				threadLocalCachable.scope(), cacheName);

		String cacheKey = _getCacheKey(methodInvocation.getArguments());

		Object value = threadLocalCache.get(cacheKey);

		if (value == nullResult) {
			return null;
		}

		return value;
	}

	@Override
	public ThreadLocalCachable getNullAnnotation() {
		return _nullThreadLocalCacheable;
	}

	private String _getCacheKey(Object[] arguments) {
		StringBundler sb = new StringBundler(arguments.length * 2 - 1);

		for (int i = 0; i < arguments.length; i++) {
			sb.append(StringUtil.toHexString(arguments[i]));

			if ((i + 1) < arguments.length) {
				sb.append(StringPool.POUND);
			}
		}

		return sb.toString();
	}

	private Serializable _getCacheName(MethodInvocation methodInvocation) {
		if (methodInvocation instanceof ServiceBeanMethodInvocation) {
			ServiceBeanMethodInvocation serviceBeanMethodInvocation =
				(ServiceBeanMethodInvocation)methodInvocation;

			return serviceBeanMethodInvocation.toCacheKeyModel();
		}
		else {
			return methodInvocation.toString();
		}
	}

	private static final ThreadLocalCachable _nullThreadLocalCacheable =
		new ThreadLocalCachable() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ThreadLocalCachable.class;
			}

			@Override
			public Lifecycle scope() {
				return null;
			}

		};

}