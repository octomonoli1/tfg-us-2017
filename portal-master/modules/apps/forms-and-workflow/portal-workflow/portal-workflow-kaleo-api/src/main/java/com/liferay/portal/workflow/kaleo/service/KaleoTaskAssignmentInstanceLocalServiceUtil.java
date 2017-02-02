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

package com.liferay.portal.workflow.kaleo.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for KaleoTaskAssignmentInstance. This utility wraps
 * {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoTaskAssignmentInstanceLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskAssignmentInstanceLocalService
 * @see com.liferay.portal.workflow.kaleo.service.base.KaleoTaskAssignmentInstanceLocalServiceBaseImpl
 * @see com.liferay.portal.workflow.kaleo.service.impl.KaleoTaskAssignmentInstanceLocalServiceImpl
 * @generated
 */
@ProviderType
public class KaleoTaskAssignmentInstanceLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoTaskAssignmentInstanceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the kaleo task assignment instance to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignmentInstance the kaleo task assignment instance
	* @return the kaleo task assignment instance that was added
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance addKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {
		return getService()
				   .addKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstance);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance addKaleoTaskAssignmentInstance(
		long groupId,
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.lang.String assigneeClassName, long assigneeClassPK,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addKaleoTaskAssignmentInstance(groupId,
			kaleoTaskInstanceToken, assigneeClassName, assigneeClassPK,
			serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance assignKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.lang.String assigneeClassName, long assigneeClassPK,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .assignKaleoTaskAssignmentInstance(kaleoTaskInstanceToken,
			assigneeClassName, assigneeClassPK, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance completeKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .completeKaleoTaskInstanceToken(kaleoTaskInstanceTokenId,
			serviceContext);
	}

	/**
	* Creates a new kaleo task assignment instance with the primary key. Does not add the kaleo task assignment instance to the database.
	*
	* @param kaleoTaskAssignmentInstanceId the primary key for the new kaleo task assignment instance
	* @return the new kaleo task assignment instance
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance createKaleoTaskAssignmentInstance(
		long kaleoTaskAssignmentInstanceId) {
		return getService()
				   .createKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstanceId);
	}

	/**
	* Deletes the kaleo task assignment instance from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignmentInstance the kaleo task assignment instance
	* @return the kaleo task assignment instance that was removed
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance deleteKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {
		return getService()
				   .deleteKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstance);
	}

	/**
	* Deletes the kaleo task assignment instance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignmentInstanceId the primary key of the kaleo task assignment instance
	* @return the kaleo task assignment instance that was removed
	* @throws PortalException if a kaleo task assignment instance with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance deleteKaleoTaskAssignmentInstance(
		long kaleoTaskAssignmentInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstanceId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchKaleoTaskAssignmentInstance(
		long kaleoTaskAssignmentInstanceId) {
		return getService()
				   .fetchKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstanceId);
	}

	/**
	* Returns the kaleo task assignment instance with the primary key.
	*
	* @param kaleoTaskAssignmentInstanceId the primary key of the kaleo task assignment instance
	* @return the kaleo task assignment instance
	* @throws PortalException if a kaleo task assignment instance with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance getKaleoTaskAssignmentInstance(
		long kaleoTaskAssignmentInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstanceId);
	}

	/**
	* Updates the kaleo task assignment instance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignmentInstance the kaleo task assignment instance
	* @return the kaleo task assignment instance that was updated
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance updateKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {
		return getService()
				   .updateKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstance);
	}

	/**
	* Returns the number of kaleo task assignment instances.
	*
	* @return the number of kaleo task assignment instances
	*/
	public static int getKaleoTaskAssignmentInstancesCount() {
		return getService().getKaleoTaskAssignmentInstancesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> addTaskAssignmentInstances(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.util.Collection<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> kaleoTaskAssignments,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addTaskAssignmentInstances(kaleoTaskInstanceToken,
			kaleoTaskAssignments, workflowContext, serviceContext);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns a range of all the kaleo task assignment instances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @return the range of kaleo task assignment instances
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances(
		int start, int end) {
		return getService().getKaleoTaskAssignmentInstances(start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances(
		long kaleoTaskInstanceTokenId) {
		return getService()
				   .getKaleoTaskAssignmentInstances(kaleoTaskInstanceTokenId);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static void deleteCompanyKaleoTaskAssignmentInstances(long companyId) {
		getService().deleteCompanyKaleoTaskAssignmentInstances(companyId);
	}

	public static void deleteKaleoDefinitionKaleoTaskAssignmentInstances(
		long kaleoDefintionId) {
		getService()
			.deleteKaleoDefinitionKaleoTaskAssignmentInstances(kaleoDefintionId);
	}

	public static void deleteKaleoInstanceKaleoTaskAssignmentInstances(
		long kaleoInstanceId) {
		getService()
			.deleteKaleoInstanceKaleoTaskAssignmentInstances(kaleoInstanceId);
	}

	public static void deleteKaleoTaskAssignmentInstances(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		getService().deleteKaleoTaskAssignmentInstances(kaleoTaskInstanceToken);
	}

	public static KaleoTaskAssignmentInstanceLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<KaleoTaskAssignmentInstanceLocalService, KaleoTaskAssignmentInstanceLocalService> _serviceTracker =
		ServiceTrackerFactory.open(KaleoTaskAssignmentInstanceLocalService.class);
}