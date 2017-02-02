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

package com.liferay.portal.store.jcr;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.memory.FinalizeAction;

import java.lang.ref.Reference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.jcr.Binary;
import javax.jcr.Session;

/**
 * @author Raymond Augé
 * @author Shuyang Zhou
 */
public class JCRSessionInvocationHandler
	implements FinalizeAction, InvocationHandler {

	public JCRSessionInvocationHandler(Session session) {
		_session = session;

		if (_log.isDebugEnabled()) {
			_log.debug("Starting session " + _session);
		}
	}

	@Override
	public void doFinalize(Reference<?> reference) {
		for (Entry<String, Binary> entry : _binaries.entrySet()) {
			Binary binary = entry.getValue();

			binary.dispose();
		}

		_session.logout();
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments)
		throws Throwable {

		String methodName = method.getName();

		if (methodName.equals("logout")) {
			if (_log.isDebugEnabled()) {
				_log.debug("Skipping logout for session " + _session);
			}

			return null;
		}
		else if (methodName.equals("put")) {
			String key = (String)arguments[0];
			Binary binary = (Binary)arguments[1];

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Tracking binary " + key + " for session " + _session);
			}

			_binaries.put(key, binary);

			return null;
		}

		try {
			return method.invoke(_session, arguments);
		}
		catch (InvocationTargetException ite) {
			throw ite.getCause();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JCRSessionInvocationHandler.class);

	private final Map<String, Binary> _binaries = new HashMap<>();
	private final Session _session;

}