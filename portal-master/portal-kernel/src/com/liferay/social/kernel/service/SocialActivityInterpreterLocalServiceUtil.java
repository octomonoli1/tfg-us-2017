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

package com.liferay.social.kernel.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for SocialActivityInterpreter. This utility wraps
 * {@link com.liferay.portlet.social.service.impl.SocialActivityInterpreterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityInterpreterLocalService
 * @see com.liferay.portlet.social.service.base.SocialActivityInterpreterLocalServiceBaseImpl
 * @see com.liferay.portlet.social.service.impl.SocialActivityInterpreterLocalServiceImpl
 * @generated
 */
@ProviderType
public class SocialActivityInterpreterLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.social.service.impl.SocialActivityInterpreterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Creates a human readable activity feed entry for the activity using an
	* available compatible activity interpreter.
	*
	* <p>
	* This method finds the appropriate interpreter for the activity by going
	* through the available interpreters and asking them if they can handle the
	* asset type of the activity.
	* </p>
	*
	* @param selector the context in which the activity interpreter is used
	* @param activity the activity to be translated to human readable form
	* @param serviceContext the service context to be applied
	* @return the activity feed that is a human readable form of the activity
	record or <code>null</code> if a compatible interpreter is not
	found
	*/
	public static com.liferay.social.kernel.model.SocialActivityFeedEntry interpret(
		java.lang.String selector,
		com.liferay.social.kernel.model.SocialActivity activity,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return getService().interpret(selector, activity, serviceContext);
	}

	public static com.liferay.social.kernel.model.SocialActivityFeedEntry interpret(
		java.lang.String selector,
		com.liferay.social.kernel.model.SocialActivitySet activitySet,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return getService().interpret(selector, activitySet, serviceContext);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.social.kernel.model.SocialActivityInterpreter> getActivityInterpreters(
		java.lang.String selector) {
		return getService().getActivityInterpreters(selector);
	}

	public static java.util.Map<java.lang.String, java.util.List<com.liferay.social.kernel.model.SocialActivityInterpreter>> getActivityInterpreters() {
		return getService().getActivityInterpreters();
	}

	/**
	* Adds the activity interpreter to the list of available interpreters.
	*
	* @param activityInterpreter the activity interpreter
	*/
	public static void addActivityInterpreter(
		com.liferay.social.kernel.model.SocialActivityInterpreter activityInterpreter) {
		getService().addActivityInterpreter(activityInterpreter);
	}

	/**
	* Removes the activity interpreter from the list of available interpreters.
	*
	* @param activityInterpreter the activity interpreter
	*/
	public static void deleteActivityInterpreter(
		com.liferay.social.kernel.model.SocialActivityInterpreter activityInterpreter) {
		getService().deleteActivityInterpreter(activityInterpreter);
	}

	public static void updateActivitySet(long activityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateActivitySet(activityId);
	}

	public static SocialActivityInterpreterLocalService getService() {
		if (_service == null) {
			_service = (SocialActivityInterpreterLocalService)PortalBeanLocatorUtil.locate(SocialActivityInterpreterLocalService.class.getName());

			ReferenceRegistry.registerReference(SocialActivityInterpreterLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static SocialActivityInterpreterLocalService _service;
}