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

package com.liferay.portal.security.sso.openid.internal;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.sso.openid.OpenIdProvider;
import com.liferay.portal.security.sso.openid.OpenIdProviderRegistry;
import com.liferay.portal.security.sso.openid.OpenIdServiceException;
import com.liferay.portal.security.sso.openid.OpenIdServiceHandler;
import com.liferay.portal.security.sso.openid.constants.OpenIdWebKeys;

import java.io.IOException;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.openid4java.association.AssociationException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.InMemoryConsumerAssociationStore;
import org.openid4java.consumer.InMemoryNonceVerifier;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.MessageException;
import org.openid4java.message.MessageExtension;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchRequest;
import org.openid4java.message.ax.FetchResponse;
import org.openid4java.message.sreg.SRegMessage;
import org.openid4java.message.sreg.SRegRequest;
import org.openid4java.message.sreg.SRegResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = OpenIdServiceHandler.class)
public class OpenIdServiceHandlerImpl implements OpenIdServiceHandler {

	@Override
	public String readResponse(
			ThemeDisplay themeDisplay, ActionRequest actionRequest)
		throws PortalException {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			actionRequest);

		request = PortalUtil.getOriginalServletRequest(request);

		String receivingURL = ParamUtil.getString(request, "openid.return_to");
		ParameterList parameterList = new ParameterList(
			request.getParameterMap());

		HttpSession session = request.getSession();

		DiscoveryInformation discoveryInformation =
			(DiscoveryInformation)session.getAttribute(
				OpenIdWebKeys.OPEN_ID_DISCO);

		if (discoveryInformation == null) {
			return null;
		}

		AuthSuccess authSuccess = null;
		String firstName = null;
		String lastName = null;
		String emailAddress = null;

		try {
			VerificationResult verificationResult = _consumerManager.verify(
				receivingURL, parameterList, discoveryInformation);

			Identifier identifier = verificationResult.getVerifiedId();

			if (identifier == null) {
				return null;
			}

			authSuccess = (AuthSuccess)verificationResult.getAuthResponse();

			firstName = null;
			lastName = null;
			emailAddress = null;

			if (authSuccess.hasExtension(SRegMessage.OPENID_NS_SREG)) {
				MessageExtension messageExtension = authSuccess.getExtension(
					SRegMessage.OPENID_NS_SREG);

				if (messageExtension instanceof SRegResponse) {
					SRegResponse sregResp = (SRegResponse)messageExtension;

					String fullName = GetterUtil.getString(
						sregResp.getAttributeValue(
							_OPEN_ID_SREG_ATTR_FULLNAME));

					String[] names = splitFullName(fullName);

					if (names != null) {
						firstName = names[0];
						lastName = names[1];
					}

					emailAddress = sregResp.getAttributeValue(
						_OPEN_ID_SREG_ATTR_EMAIL);
				}
			}

			if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX)) {
				MessageExtension messageExtension = authSuccess.getExtension(
					AxMessage.OPENID_NS_AX);

				if (messageExtension instanceof FetchResponse) {
					FetchResponse fetchResponse =
						(FetchResponse)messageExtension;

					OpenIdProvider openIdProvider =
						_openIdProviderRegistry.getOpenIdProvider(
							discoveryInformation.getOPEndpoint());

					String[] openIdAXTypes = openIdProvider.getAxSchema();

					for (String openIdAXType : openIdAXTypes) {
						if (openIdAXType.equals(_OPEN_ID_AX_ATTR_EMAIL)) {
							if (Validator.isNull(emailAddress)) {
								emailAddress = getFirstValue(
									fetchResponse.getAttributeValues(
										_OPEN_ID_AX_ATTR_EMAIL));
							}
						}
						else if (openIdAXType.equals(
									_OPEN_ID_AX_ATTR_FIRST_NAME)) {

							if (Validator.isNull(firstName)) {
								firstName = getFirstValue(
									fetchResponse.getAttributeValues(
										_OPEN_ID_AX_ATTR_FIRST_NAME));
							}
						}
						else if (openIdAXType.equals(
									_OPEN_ID_AX_ATTR_FULL_NAME)) {

							String fullName = fetchResponse.getAttributeValue(
								_OPEN_ID_AX_ATTR_FULL_NAME);

							String[] names = splitFullName(fullName);

							if (names != null) {
								if (Validator.isNull(firstName)) {
									firstName = names[0];
								}

								if (Validator.isNull(lastName)) {
									lastName = names[1];
								}
							}
						}
						else if (openIdAXType.equals(
									_OPEN_ID_AX_ATTR_LAST_NAME)) {

							if (Validator.isNull(lastName)) {
								lastName = getFirstValue(
									fetchResponse.getAttributeValues(
										_OPEN_ID_AX_ATTR_LAST_NAME));
							}
						}
					}
				}
			}
		}
		catch (AssociationException ae) {
			throw new OpenIdServiceException.AssociationException(
				ae.getMessage(), ae);
		}
		catch (DiscoveryException de) {
			throw new OpenIdServiceException.DiscoveryException(
				de.getMessage(), de);
		}
		catch (MessageException me) {
			throw new OpenIdServiceException.MessageException(
				me.getMessage(), me);
		}

		String openId = normalize(authSuccess.getIdentity());

		User user = _userLocalService.fetchUserByOpenId(
			themeDisplay.getCompanyId(), openId);

		if (user != null) {
			session.setAttribute(WebKeys.OPEN_ID_LOGIN, user.getUserId());

			return null;
		}

		try {
			if (Validator.isNull(firstName) || Validator.isNull(lastName) ||
				Validator.isNull(emailAddress)) {

				SessionMessages.add(request, "openIdUserInformationMissing");

				if (_log.isInfoEnabled()) {
					_log.info(
						"The OpenID provider did not send the required " +
							"attributes to create an account");
				}

				String createAccountURL = PortalUtil.getCreateAccountURL(
					request, themeDisplay);

				String portletId = HttpUtil.getParameter(
					createAccountURL, "p_p_id", false);

				String portletNamespace = PortalUtil.getPortletNamespace(
					portletId);

				createAccountURL = HttpUtil.setParameter(
					createAccountURL, portletNamespace + "openId", openId);

				session.setAttribute(
					WebKeys.OPEN_ID_LOGIN_PENDING, Boolean.TRUE);

				return createAccountURL;
			}
		}
		catch (Exception e) {
			throw new PortalException(e);
		}

		long creatorUserId = 0;
		long companyId = themeDisplay.getCompanyId();
		boolean autoPassword = false;
		String password1 = PwdGenerator.getPassword();
		String password2 = password1;
		boolean autoScreenName = true;
		String screenName = StringPool.BLANK;
		long facebookId = 0;
		Locale locale = themeDisplay.getLocale();
		String middleName = StringPool.BLANK;
		long prefixId = 0;
		long suffixId = 0;
		boolean male = true;
		int birthdayMonth = Calendar.JANUARY;
		int birthdayDay = 1;
		int birthdayYear = 1970;
		String jobTitle = StringPool.BLANK;
		long[] groupIds = null;
		long[] organizationIds = null;
		long[] roleIds = null;
		long[] userGroupIds = null;
		boolean sendEmail = false;

		ServiceContext serviceContext = new ServiceContext();

		user = _userLocalService.addUser(
			creatorUserId, companyId, autoPassword, password1, password2,
			autoScreenName, screenName, emailAddress, facebookId, openId,
			locale, firstName, middleName, lastName, prefixId, suffixId, male,
			birthdayMonth, birthdayDay, birthdayYear, jobTitle, groupIds,
			organizationIds, roleIds, userGroupIds, sendEmail, serviceContext);

		session.setAttribute(WebKeys.OPEN_ID_LOGIN, user.getUserId());

		return null;
	}

	@Override
	public void sendRequest(
			ThemeDisplay themeDisplay, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws PortalException {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			actionRequest);

		request = PortalUtil.getOriginalServletRequest(request);

		HttpServletResponse response = PortalUtil.getHttpServletResponse(
			actionResponse);

		HttpSession session = request.getSession();

		LiferayPortletResponse liferayPortletResponse =
			PortalUtil.getLiferayPortletResponse(actionResponse);

		String openId = ParamUtil.getString(actionRequest, "openId");

		PortletURL portletURL = liferayPortletResponse.createActionURL();

		portletURL.setParameter(ActionRequest.ACTION_NAME, "/login/openid");
		portletURL.setParameter("saveLastPath", Boolean.FALSE.toString());
		portletURL.setParameter("mvcRenderCommandName", "/login/openid");
		portletURL.setParameter(Constants.CMD, Constants.READ);

		try {
			List<DiscoveryInformation> discoveryInformationList =
				_consumerManager.discover(openId);

			DiscoveryInformation discoveryInformation =
				_consumerManager.associate(discoveryInformationList);

			session.setAttribute(
				OpenIdWebKeys.OPEN_ID_DISCO, discoveryInformation);

			AuthRequest authRequest = _consumerManager.authenticate(
				discoveryInformation, portletURL.toString(),
				themeDisplay.getPortalURL());

			if (_userLocalService.fetchUserByOpenId(
					themeDisplay.getCompanyId(), openId) != null) {

				response.sendRedirect(authRequest.getDestinationUrl(true));

				return;
			}

			String screenName = getScreenName(openId);

			User user = _userLocalService.fetchUserByScreenName(
				themeDisplay.getCompanyId(), screenName);

			if (user != null) {
				_userLocalService.updateOpenId(user.getUserId(), openId);

				response.sendRedirect(authRequest.getDestinationUrl(true));

				return;
			}

			FetchRequest fetchRequest = FetchRequest.createFetchRequest();

			OpenIdProvider openIdProvider =
				_openIdProviderRegistry.getOpenIdProvider(
					discoveryInformation.getOPEndpoint());

			Map<String, String> openIdAXTypes = openIdProvider.getAxTypes();

			for (Map.Entry<String, String> entry : openIdAXTypes.entrySet()) {
				fetchRequest.addAttribute(
					entry.getKey(), entry.getValue(), true);
			}

			authRequest.addExtension(fetchRequest);

			SRegRequest sRegRequest = SRegRequest.createFetchRequest();

			sRegRequest.addAttribute(_OPEN_ID_SREG_ATTR_EMAIL, true);
			sRegRequest.addAttribute(_OPEN_ID_SREG_ATTR_FULLNAME, true);

			authRequest.addExtension(sRegRequest);

			response.sendRedirect(authRequest.getDestinationUrl(true));
		}
		catch (ConsumerException ce) {
			throw new OpenIdServiceException.ConsumerException(
				ce.getMessage(), ce);
		}
		catch (DiscoveryException de) {
			throw new OpenIdServiceException.DiscoveryException(
				de.getMessage(), de);
		}
		catch (MessageException me) {
			throw new OpenIdServiceException.MessageException(
				me.getMessage(), me);
		}
		catch (IOException ioe) {
			throw new SystemException(
				"Unable to communicate with OpenId provider", ioe);
		}
	}

	@Activate
	@Modified
	protected void activate() {
		try {
			_consumerManager = new ConsumerManager();

			_consumerManager.setAssociations(
				new InMemoryConsumerAssociationStore());
			_consumerManager.setNonceVerifier(new InMemoryNonceVerifier(5000));
		}
		catch (Exception e) {
			throw new IllegalStateException(
				"Unable to start consumer manager", e);
		}
	}

	protected String getFirstValue(List<String> values) {
		if ((values == null) || (values.size() < 1)) {
			return null;
		}

		return values.get(0);
	}

	protected String getScreenName(String openId) {
		String screenName = normalize(openId);

		if (screenName.startsWith(Http.HTTP_WITH_SLASH)) {
			screenName = screenName.substring(Http.HTTP_WITH_SLASH.length());
		}

		if (screenName.startsWith(Http.HTTPS_WITH_SLASH)) {
			screenName = screenName.substring(Http.HTTPS_WITH_SLASH.length());
		}

		screenName = StringUtil.replace(
			screenName, new char[] {CharPool.SLASH, CharPool.UNDERLINE},
			new char[] {CharPool.PERIOD, CharPool.PERIOD});

		return screenName;
	}

	protected String normalize(String identity) {
		if (identity.endsWith(StringPool.SLASH)) {
			return identity.substring(0, identity.length() - 1);
		}

		return identity;
	}

	@Reference(unbind = "-")
	protected void setOpenIdProviderRegistry(
		OpenIdProviderRegistry openIdProviderRegistry) {

		_openIdProviderRegistry = openIdProviderRegistry;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	protected String[] splitFullName(String fullName) {
		if (Validator.isNull(fullName)) {
			return null;
		}

		int pos = fullName.indexOf(CharPool.SPACE);

		if ((pos != -1) && ((pos + 1) < fullName.length())) {
			String[] names = new String[2];

			names[0] = fullName.substring(0, pos);
			names[1] = fullName.substring(pos + 1);

			return names;
		}

		return null;
	}

	private static final String _OPEN_ID_AX_ATTR_EMAIL = "email";

	private static final String _OPEN_ID_AX_ATTR_FIRST_NAME = "firstname";

	private static final String _OPEN_ID_AX_ATTR_FULL_NAME = "fullname";

	private static final String _OPEN_ID_AX_ATTR_LAST_NAME = "lastname";

	private static final String _OPEN_ID_SREG_ATTR_EMAIL = "email";

	private static final String _OPEN_ID_SREG_ATTR_FULLNAME = "fullname";

	private static final Log _log = LogFactoryUtil.getLog(
		OpenIdServiceHandlerImpl.class);

	private ConsumerManager _consumerManager;
	private OpenIdProviderRegistry _openIdProviderRegistry;
	private UserLocalService _userLocalService;

}