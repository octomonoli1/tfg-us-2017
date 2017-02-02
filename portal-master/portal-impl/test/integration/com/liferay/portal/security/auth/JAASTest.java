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

package com.liferay.portal.security.auth;

import com.liferay.portal.events.EventsProcessorUtil;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.jaas.PortalPrincipal;
import com.liferay.portal.kernel.security.jaas.PortalRole;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.security.jaas.JAASHelper;
import com.liferay.portal.servlet.MainServlet;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.callback.MainServletTestCallback;
import com.liferay.portal.util.PropsValues;

import java.lang.reflect.Field;

import java.security.Principal;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Raymond Augé
 */
public class JAASTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_jaasAuthTypeField = ReflectionUtil.getDeclaredField(
			PropsValues.class, "PORTAL_JAAS_AUTH_TYPE");

		_jaasAuthType = (String)_jaasAuthTypeField.get(null);

		_jaasEnabledField = ReflectionUtil.getDeclaredField(
			PropsValues.class, "PORTAL_JAAS_ENABLE");

		_jaasEnabled = (Boolean)_jaasEnabledField.get(null);

		_jaasEnabledField.set(null, true);

		Configuration.setConfiguration(new JAASConfiguration());

		_user = TestPropsValues.getUser();
	}

	@After
	public void tearDown() throws Exception {
		Configuration.setConfiguration(null);

		_jaasAuthTypeField.set(null, _jaasAuthType);
		_jaasEnabledField.set(null, _jaasEnabled);
	}

	@Test
	public void testGetUser() throws Exception {
		_jaasAuthTypeField.set(null, "screenName");

		final IntegerWrapper counter = new IntegerWrapper();

		JAASHelper jaasHelper = JAASHelper.getInstance();

		JAASHelper.setInstance(
			new JAASHelper() {

				@Override
				protected long doGetJaasUserId(long companyId, String name)
					throws PortalException {

					try {
						return super.doGetJaasUserId(companyId, name);
					}
					finally {
						counter.increment();
					}
				}

			});

		MainServlet mainServlet = MainServletTestCallback.getMainServlet();

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest(
				mainServlet.getServletContext(), HttpMethods.GET,
				StringPool.SLASH);

		mockHttpServletRequest.setRemoteUser(
			String.valueOf(_user.getScreenName()));

		try {
			User user = PortalUtil.getUser(mockHttpServletRequest);

			Assert.assertEquals(1, counter.getValue());
			Assert.assertEquals(_user.getUserId(), user.getUserId());

			user = PortalUtil.getUser(mockHttpServletRequest);

			Assert.assertEquals(1, counter.getValue());
			Assert.assertEquals(_user.getUserId(), user.getUserId());
		}
		finally {
			JAASHelper.setInstance(jaasHelper);
		}
	}

	@Test
	public void testLoginEmailAddressWithEmailAddress() throws Exception {
		_jaasAuthTypeField.set(null, "emailAddress");

		LoginContext loginContext = getLoginContext(
			_user.getEmailAddress(), _user.getPassword());

		loginContext.login();

		validateSubject(loginContext.getSubject(), _user.getEmailAddress());
	}

	@Test
	public void testLoginEmailAddressWithLogin() throws Exception {
		_jaasAuthTypeField.set(null, "login");

		LoginContext loginContext = getLoginContext(
			_user.getEmailAddress(), _user.getPassword());

		loginContext.login();

		validateSubject(loginContext.getSubject(), _user.getEmailAddress());
	}

	@Test
	public void testLoginEmailAddressWithScreenName() throws Exception {
		_jaasAuthTypeField.set(null, "screenName");

		LoginContext loginContext = getLoginContext(
			_user.getEmailAddress(), _user.getPassword());

		try {
			loginContext.login();

			Assert.fail();
		}
		catch (Exception e) {
		}
	}

	@Test
	public void testLoginEmailAddressWithUserId() throws Exception {
		_jaasAuthTypeField.set(null, "userId");

		LoginContext loginContext = getLoginContext(
			_user.getEmailAddress(), _user.getPassword());

		try {
			loginContext.login();

			Assert.fail();
		}
		catch (Exception e) {
		}
	}

	@Test
	public void testLoginScreenNameWithEmailAddress() throws Exception {
		_jaasAuthTypeField.set(null, "emailAddress");

		LoginContext loginContext = getLoginContext(
			_user.getScreenName(), _user.getPassword());

		try {
			loginContext.login();

			Assert.fail();
		}
		catch (Exception e) {
		}
	}

	@Test
	public void testLoginScreenNameWithLogin() throws Exception {
		_jaasAuthTypeField.set(null, "login");

		LoginContext loginContext = getLoginContext(
			_user.getScreenName(), _user.getPassword());

		try {
			loginContext.login();

			Assert.fail();
		}
		catch (Exception e) {
		}
	}

	@Test
	public void testLoginScreenNameWithScreenName() throws Exception {
		_jaasAuthTypeField.set(null, "screenName");

		LoginContext loginContext = getLoginContext(
			_user.getScreenName(), _user.getPassword());

		loginContext.login();

		validateSubject(loginContext.getSubject(), _user.getScreenName());
	}

	@Test
	public void testLoginScreenNameWithUserId() throws Exception {
		_jaasAuthTypeField.set(null, "userId");

		LoginContext loginContext = getLoginContext(
			_user.getScreenName(), _user.getPassword());

		try {
			loginContext.login();

			Assert.fail();
		}
		catch (Exception e) {
		}
	}

	@Test
	public void testLoginUserIdWithEmailAddress() throws Exception {
		_jaasAuthTypeField.set(null, "emailAddress");

		LoginContext loginContext = getLoginContext(
			String.valueOf(_user.getUserId()), _user.getPassword());

		try {
			loginContext.login();

			Assert.fail();
		}
		catch (Exception e) {
		}
	}

	@Test
	public void testLoginUserIdWithLogin() throws Exception {
		_jaasAuthTypeField.set(null, "login");

		LoginContext loginContext = getLoginContext(
			String.valueOf(_user.getUserId()), _user.getPassword());

		try {
			loginContext.login();

			Assert.fail();
		}
		catch (Exception e) {
		}
	}

	@Test
	public void testLoginUserIdWithScreenName() throws Exception {
		_jaasAuthTypeField.set(null, "screenName");

		LoginContext loginContext = getLoginContext(
			String.valueOf(_user.getUserId()), _user.getPassword());

		try {
			loginContext.login();

			Assert.fail();
		}
		catch (Exception e) {
		}
	}

	@Test
	public void testLoginUserIdWithUserId() throws Exception {
		_jaasAuthTypeField.set(null, "userId");

		LoginContext loginContext = getLoginContext(
			String.valueOf(_user.getUserId()), _user.getPassword());

		loginContext.login();

		validateSubject(
			loginContext.getSubject(), String.valueOf(_user.getUserId()));
	}

	@Test
	public void testProcessLoginEvents() throws Exception {
		MainServlet mainServlet = MainServletTestCallback.getMainServlet();

		Date lastLoginDate = _user.getLastLoginDate();

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest(
				mainServlet.getServletContext(), HttpMethods.GET,
				StringPool.SLASH);

		mockHttpServletRequest.setRemoteUser(String.valueOf(_user.getUserId()));

		JAASAction preJAASAction = new JAASAction();
		JAASAction postJAASAction = new JAASAction();

		try {
			EventsProcessorUtil.registerEvent(
				PropsKeys.LOGIN_EVENTS_PRE, preJAASAction);
			EventsProcessorUtil.registerEvent(
				PropsKeys.LOGIN_EVENTS_POST, postJAASAction);

			mainServlet.service(
				mockHttpServletRequest, new MockHttpServletResponse());

			Assert.assertTrue(preJAASAction.isRan());
			Assert.assertTrue(postJAASAction.isRan());

			_user = UserLocalServiceUtil.getUser(_user.getUserId());

			Assert.assertFalse(lastLoginDate.after(_user.getLastLoginDate()));
		}
		finally {
			EventsProcessorUtil.unregisterEvent(
				PropsKeys.LOGIN_EVENTS_PRE, postJAASAction);
			EventsProcessorUtil.unregisterEvent(
				PropsKeys.LOGIN_EVENTS_POST, postJAASAction);
		}
	}

	protected LoginContext getLoginContext(String name, String password)
		throws Exception {

		return new LoginContext(
			"PortalRealm", new JAASCallbackHandler(name, password));
	}

	protected void validateSubject(Subject subject, String userIdString) {
		Assert.assertNotNull(subject);

		Set<Principal> userPrincipals = subject.getPrincipals();

		Assert.assertNotNull(userPrincipals);

		Iterator<Principal> iterator = userPrincipals.iterator();

		Assert.assertTrue(iterator.hasNext());

		while (iterator.hasNext()) {
			Principal principal = iterator.next();

			if (principal instanceof PortalRole) {
				PortalRole portalRole = (PortalRole)principal;

				Assert.assertEquals("users", portalRole.getName());
			}
			else {
				PortalPrincipal portalPrincipal = (PortalPrincipal)principal;

				Assert.assertEquals(userIdString, portalPrincipal.getName());
			}
		}
	}

	private String _jaasAuthType;
	private Field _jaasAuthTypeField;
	private Boolean _jaasEnabled;
	private Field _jaasEnabledField;
	private User _user;

	private static class JAASAction extends Action {

		public boolean isRan() {
			return _ran;
		}

		@Override
		public void run(
			HttpServletRequest request, HttpServletResponse response) {

			_ran = true;
		}

		private boolean _ran;

	}

	private static class JAASCallbackHandler implements CallbackHandler {

		public JAASCallbackHandler(String name, String password) {
			_name = name;
			_password = password;
		}

		@Override
		public void handle(Callback[] callbacks)
			throws UnsupportedCallbackException {

			for (Callback callback : callbacks) {
				if (callback instanceof NameCallback) {
					NameCallback nameCallback = (NameCallback)callback;

					nameCallback.setName(_name);
				}
				else if (callback instanceof PasswordCallback) {
					String password = GetterUtil.getString(_password);

					PasswordCallback passwordCallback =
						(PasswordCallback)callback;

					passwordCallback.setPassword(password.toCharArray());
				}
				else {
					throw new UnsupportedCallbackException(callback);
				}
			}
		}

		private final String _name;
		private final String _password;

	}

	private static class JAASConfiguration extends Configuration {

		@Override
		public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
			AppConfigurationEntry[] appConfigurationEntries =
				new AppConfigurationEntry[1];

			Map<String, Object> options = new HashMap<>();

			options.put("debug", Boolean.TRUE);

			appConfigurationEntries[0] = new AppConfigurationEntry(
				"com.liferay.portal.kernel.security.jaas.PortalLoginModule",
				LoginModuleControlFlag.REQUIRED, options);

			return appConfigurationEntries;
		}

	}

}