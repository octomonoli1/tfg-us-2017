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

package com.liferay.portal.kernel.lock;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Date;

/**
 * @author Zsolt Berentey
 */
public class LockProtectedAction<T> {

	public LockProtectedAction(
		Class<?> clazz, String lockKey, long timeout, long retryDelay) {

		_className = clazz.getName();
		_lockKey = lockKey;
		_timeout = timeout;
		_retryDelay = retryDelay;
	}

	public T getReturnValue() {
		return _returnValue;
	}

	public void performAction() throws PortalException {
		Lock lock = LockManagerUtil.lock(_className, _lockKey, _lockKey);

		if (lock.isNew()) {
			try {
				_returnValue = performProtectedAction();
			}
			finally {
				LockManagerUtil.unlock(_className, _lockKey, _lockKey);
			}

			return;
		}

		Date createDate = lock.getCreateDate();

		if ((System.currentTimeMillis() - createDate.getTime()) >= _timeout) {
			LockManagerUtil.unlock(_className, _lockKey, lock.getOwner());

			if (_log.isWarnEnabled()) {
				_log.warn("Removed lock " + lock + " due to timeout");
			}
		}
		else {
			try {
				Thread.sleep(_retryDelay);
			}
			catch (InterruptedException ie) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Interrupted while waiting to reacquire lock", ie);
				}
			}
		}
	}

	@SuppressWarnings("unused")
	protected T performProtectedAction() throws PortalException {
		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LockProtectedAction.class);

	private final String _className;
	private final String _lockKey;
	private final long _retryDelay;
	private T _returnValue;
	private final long _timeout;

}