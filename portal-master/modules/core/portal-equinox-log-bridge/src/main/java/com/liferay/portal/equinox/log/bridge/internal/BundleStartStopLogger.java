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

package com.liferay.portal.equinox.log.bridge.internal;

import org.osgi.framework.BundleEvent;
import org.osgi.framework.SynchronousBundleListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Raymond Augé
 */
public class BundleStartStopLogger implements SynchronousBundleListener {

	@Override
	public void bundleChanged(BundleEvent bundleEvent) {
		if (!_log.isInfoEnabled()) {
			return;
		}

		if (bundleEvent.getType() == BundleEvent.STARTED) {
			_log.info("STARTED {}", bundleEvent.getBundle());
		}
		else if (bundleEvent.getType() == BundleEvent.STOPPED) {
			_log.info("STOPPED {}", bundleEvent.getBundle());
		}
	}

	private static final Logger _log = LoggerFactory.getLogger(
		BundleStartStopLogger.class);

}