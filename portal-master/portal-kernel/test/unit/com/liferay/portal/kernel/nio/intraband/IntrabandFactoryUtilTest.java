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

package com.liferay.portal.kernel.nio.intraband;

import com.liferay.portal.kernel.nio.intraband.blocking.ExecutorIntraband;
import com.liferay.portal.kernel.nio.intraband.nonblocking.SelectorIntraband;
import com.liferay.portal.kernel.nio.intraband.welder.fifo.FIFOWelder;
import com.liferay.portal.kernel.nio.intraband.welder.socket.SocketWelder;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.kernel.test.rule.NewEnv;
import com.liferay.portal.kernel.test.rule.NewEnvTestRule;
import com.liferay.portal.kernel.util.PropsKeys;

import java.io.IOException;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
@NewEnv(type = NewEnv.Type.CLASSLOADER)
public class IntrabandFactoryUtilTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			CodeCoverageAssertor.INSTANCE, NewEnvTestRule.INSTANCE);

	@Test
	public void testConstructor() {
		new IntrabandFactoryUtil();
	}

	@Test
	public void testCreateIntrabandClassNotFound() throws IOException {
		System.setProperty(PropsKeys.INTRABAND_IMPL, "NoSuchClass");

		try {
			IntrabandFactoryUtil.createIntraband();

			Assert.fail();
		}
		catch (RuntimeException re) {
			Assert.assertEquals(
				"Unable to instantiate NoSuchClass", re.getMessage());

			Throwable throwable = re.getCause();

			Assert.assertSame(
				ClassNotFoundException.class, throwable.getClass());
		}
		finally {
			System.clearProperty(PropsKeys.INTRABAND_IMPL);
		}
	}

	@Test
	public void testCreateIntrabandCustomizedImpl() throws Exception {
		System.setProperty(
			PropsKeys.INTRABAND_IMPL, SelectorIntraband.class.getName());

		Intraband intraband = null;

		try {
			intraband = IntrabandFactoryUtil.createIntraband();

			Assert.assertSame(SelectorIntraband.class, intraband.getClass());
		}
		finally {
			if (intraband != null) {
				intraband.close();
			}

			System.clearProperty(PropsKeys.INTRABAND_IMPL);
		}
	}

	@Test
	public void testCreateIntrabandDefaultToFIFO() throws Exception {
		System.setProperty(
			PropsKeys.INTRABAND_WELDER_IMPL, FIFOWelder.class.getName());

		Intraband intraband = null;

		try {
			intraband = IntrabandFactoryUtil.createIntraband();

			Assert.assertSame(ExecutorIntraband.class, intraband.getClass());
		}
		finally {
			if (intraband != null) {
				intraband.close();
			}

			System.clearProperty(PropsKeys.INTRABAND_WELDER_IMPL);
		}
	}

	@Test
	public void testCreateIntrabandDefaultToSocket() throws Exception {
		System.setProperty(
			PropsKeys.INTRABAND_WELDER_IMPL, SocketWelder.class.getName());

		Intraband intraband = null;

		try {
			intraband = IntrabandFactoryUtil.createIntraband();

			Assert.assertSame(SelectorIntraband.class, intraband.getClass());
		}
		finally {
			if (intraband != null) {
				intraband.close();
			}

			System.clearProperty(PropsKeys.INTRABAND_WELDER_IMPL);
		}
	}

}