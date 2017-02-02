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

package com.liferay.opensocial.service.impl;

import com.liferay.opensocial.model.OAuthToken;
import com.liferay.opensocial.service.base.OAuthTokenLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author Dennis Ju
 */
public class OAuthTokenLocalServiceImpl extends OAuthTokenLocalServiceBaseImpl {

	public OAuthToken addOAuthToken(
			long userId, String gadgetKey, String serviceName, long moduleId,
			String accessToken, String tokenName, String tokenSecret,
			String sessionHandle, long expiration)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		long oAuthTokenId = counterLocalService.increment();

		OAuthToken oAuthToken = oAuthTokenPersistence.create(oAuthTokenId);

		oAuthToken.setCompanyId(user.getCompanyId());
		oAuthToken.setUserId(user.getUserId());
		oAuthToken.setUserName(user.getFullName());
		oAuthToken.setCreateDate(now);
		oAuthToken.setModifiedDate(now);
		oAuthToken.setGadgetKey(gadgetKey);
		oAuthToken.setServiceName(serviceName);
		oAuthToken.setModuleId(moduleId);
		oAuthToken.setAccessToken(accessToken);
		oAuthToken.setTokenName(tokenName);
		oAuthToken.setTokenSecret(tokenSecret);
		oAuthToken.setSessionHandle(sessionHandle);
		oAuthToken.setExpiration(expiration);

		oAuthTokenPersistence.update(oAuthToken);

		return oAuthToken;
	}

	public void deleteOAuthToken(
			long userId, String gadgetKey, String serviceName, long moduleId,
			String tokenName)
		throws PortalException {

		oAuthTokenPersistence.removeByU_G_S_M_T(
			userId, gadgetKey, serviceName, moduleId, tokenName);
	}

	public void deleteOAuthTokens(String gadgetKey, String serviceName) {
		oAuthTokenPersistence.removeByG_S(gadgetKey, serviceName);
	}

	public OAuthToken fetchOAuthToken(
		long userId, String gadgetKey, String serviceName, long moduleId,
		String tokenName) {

		return oAuthTokenPersistence.fetchByU_G_S_M_T(
			userId, gadgetKey, serviceName, moduleId, tokenName);
	}

	public OAuthToken getOAuthToken(
			long userId, String gadgetKey, String serviceName, long moduleId,
			String tokenName)
		throws PortalException {

		return oAuthTokenPersistence.findByU_G_S_M_T(
			userId, gadgetKey, serviceName, moduleId, tokenName);
	}

	public List<OAuthToken> getOAuthTokens(
		String gadgetKey, String serviceName) {

		return oAuthTokenPersistence.findByG_S(gadgetKey, serviceName);
	}

}