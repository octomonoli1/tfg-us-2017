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

package com.liferay.polls.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.polls.model.PollsChoice;
import com.liferay.polls.model.PollsQuestion;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the remote service interface for PollsQuestion. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see PollsQuestionServiceUtil
 * @see com.liferay.polls.service.base.PollsQuestionServiceBaseImpl
 * @see com.liferay.polls.service.impl.PollsQuestionServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=polls", "json.web.service.context.path=PollsQuestion"}, service = PollsQuestionService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface PollsQuestionService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PollsQuestionServiceUtil} to access the polls question remote service. Add custom service methods to {@link com.liferay.polls.service.impl.PollsQuestionServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public PollsQuestion addQuestion(Map<Locale, java.lang.String> titleMap,
		Map<Locale, java.lang.String> descriptionMap, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire,
		List<PollsChoice> choices, ServiceContext serviceContext)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PollsQuestion getQuestion(long questionId) throws PortalException;

	public PollsQuestion updateQuestion(long questionId,
		Map<Locale, java.lang.String> titleMap,
		Map<Locale, java.lang.String> descriptionMap, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire,
		List<PollsChoice> choices, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	public void deleteQuestion(long questionId) throws PortalException;
}