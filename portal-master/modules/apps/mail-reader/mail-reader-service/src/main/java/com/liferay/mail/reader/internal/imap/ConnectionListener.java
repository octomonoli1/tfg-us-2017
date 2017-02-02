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

package com.liferay.mail.reader.internal.imap;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.mail.event.ConnectionEvent;

/**
 * @author Alexander Chow
 */
public class ConnectionListener implements javax.mail.event.ConnectionListener {

	public ConnectionListener(String service) {
		_service = service;
	}

	@Override
	public void closed(ConnectionEvent connectionEvent) {
		if (_log.isDebugEnabled()) {
			long uptime = (System.currentTimeMillis() - _startTime) / 1000;

			_log.debug("Closed " + _service + " after " + uptime + "seconds");
		}
	}

	@Override
	public void disconnected(ConnectionEvent connectionEvent) {
		if (_log.isDebugEnabled()) {
			long uptime = (System.currentTimeMillis() - _startTime) / 1000;

			_log.debug(
				"Disconnected " + _service + " after " + uptime + "seconds");
		}
	}

	@Override
	public void opened(ConnectionEvent connectionEvent) {
		_startTime = System.currentTimeMillis();

		if (_log.isDebugEnabled()) {
			_log.debug("Opened " + _service);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ConnectionListener.class);

	private final String _service;
	private long _startTime;

}