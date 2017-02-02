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

package com.liferay.sync.engine;

import com.liferay.sync.engine.listener.SyncEngineListener;
import com.liferay.sync.engine.util.SyncEngineUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Shinn Lok
 */
public class SyncEngineTest {

	@Test
	public void testStop() throws Exception {
		final CountDownLatch countDownLatch = new CountDownLatch(1);

		SyncEngineListener syncEngineListener = new SyncEngineListener() {

			@Override
			public void syncEngineStateChanged(
				long syncAccountId, int syncEngineState) {

				if (syncEngineState ==
						SyncEngineUtil.SYNC_ENGINE_STATE_STOPPED) {

					_stopped = true;

					countDownLatch.countDown();
				}
			}

		};

		SyncEngineUtil.registerSyncEngineListener(syncEngineListener);

		SyncEngine.start();

		SyncEngine.stop();

		countDownLatch.await(5, TimeUnit.SECONDS);

		Assert.assertTrue(_stopped);
	}

	private boolean _stopped;

}