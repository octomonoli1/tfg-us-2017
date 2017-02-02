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

package com.liferay.portal.kernel.service;

import aQute.bnd.annotation.ProviderType;

/**
 * Provides a wrapper for {@link WorkflowDefinitionLinkLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see WorkflowDefinitionLinkLocalService
 * @generated
 */
@ProviderType
public class WorkflowDefinitionLinkLocalServiceWrapper
	implements WorkflowDefinitionLinkLocalService,
		ServiceWrapper<WorkflowDefinitionLinkLocalService> {
	public WorkflowDefinitionLinkLocalServiceWrapper(
		WorkflowDefinitionLinkLocalService workflowDefinitionLinkLocalService) {
		_workflowDefinitionLinkLocalService = workflowDefinitionLinkLocalService;
	}

	@Override
	public boolean hasWorkflowDefinitionLink(long companyId, long groupId,
		java.lang.String className) {
		return _workflowDefinitionLinkLocalService.hasWorkflowDefinitionLink(companyId,
			groupId, className);
	}

	@Override
	public boolean hasWorkflowDefinitionLink(long companyId, long groupId,
		java.lang.String className, long classPK) {
		return _workflowDefinitionLinkLocalService.hasWorkflowDefinitionLink(companyId,
			groupId, className, classPK);
	}

	@Override
	public boolean hasWorkflowDefinitionLink(long companyId, long groupId,
		java.lang.String className, long classPK, long typePK) {
		return _workflowDefinitionLinkLocalService.hasWorkflowDefinitionLink(companyId,
			groupId, className, classPK, typePK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _workflowDefinitionLinkLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _workflowDefinitionLinkLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _workflowDefinitionLinkLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workflowDefinitionLinkLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workflowDefinitionLinkLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the workflow definition link to the database. Also notifies the appropriate model listeners.
	*
	* @param workflowDefinitionLink the workflow definition link
	* @return the workflow definition link that was added
	*/
	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink addWorkflowDefinitionLink(
		com.liferay.portal.kernel.model.WorkflowDefinitionLink workflowDefinitionLink) {
		return _workflowDefinitionLinkLocalService.addWorkflowDefinitionLink(workflowDefinitionLink);
	}

	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink addWorkflowDefinitionLink(
		long userId, long companyId, long groupId, java.lang.String className,
		long classPK, long typePK, java.lang.String workflowDefinitionName,
		int workflowDefinitionVersion)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workflowDefinitionLinkLocalService.addWorkflowDefinitionLink(userId,
			companyId, groupId, className, classPK, typePK,
			workflowDefinitionName, workflowDefinitionVersion);
	}

	/**
	* Creates a new workflow definition link with the primary key. Does not add the workflow definition link to the database.
	*
	* @param workflowDefinitionLinkId the primary key for the new workflow definition link
	* @return the new workflow definition link
	*/
	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink createWorkflowDefinitionLink(
		long workflowDefinitionLinkId) {
		return _workflowDefinitionLinkLocalService.createWorkflowDefinitionLink(workflowDefinitionLinkId);
	}

	/**
	* Deletes the workflow definition link from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowDefinitionLink the workflow definition link
	* @return the workflow definition link that was removed
	*/
	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink deleteWorkflowDefinitionLink(
		com.liferay.portal.kernel.model.WorkflowDefinitionLink workflowDefinitionLink) {
		return _workflowDefinitionLinkLocalService.deleteWorkflowDefinitionLink(workflowDefinitionLink);
	}

	/**
	* Deletes the workflow definition link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowDefinitionLinkId the primary key of the workflow definition link
	* @return the workflow definition link that was removed
	* @throws PortalException if a workflow definition link with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink deleteWorkflowDefinitionLink(
		long workflowDefinitionLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workflowDefinitionLinkLocalService.deleteWorkflowDefinitionLink(workflowDefinitionLinkId);
	}

	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink fetchDefaultWorkflowDefinitionLink(
		long companyId, java.lang.String className, long classPK, long typePK) {
		return _workflowDefinitionLinkLocalService.fetchDefaultWorkflowDefinitionLink(companyId,
			className, classPK, typePK);
	}

	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink fetchWorkflowDefinitionLink(
		long companyId, long groupId, java.lang.String className, long classPK,
		long typePK) {
		return _workflowDefinitionLinkLocalService.fetchWorkflowDefinitionLink(companyId,
			groupId, className, classPK, typePK);
	}

	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink fetchWorkflowDefinitionLink(
		long companyId, long groupId, java.lang.String className, long classPK,
		long typePK, boolean strict) {
		return _workflowDefinitionLinkLocalService.fetchWorkflowDefinitionLink(companyId,
			groupId, className, classPK, typePK, strict);
	}

	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink fetchWorkflowDefinitionLink(
		long workflowDefinitionLinkId) {
		return _workflowDefinitionLinkLocalService.fetchWorkflowDefinitionLink(workflowDefinitionLinkId);
	}

	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink getDefaultWorkflowDefinitionLink(
		long companyId, java.lang.String className, long classPK, long typePK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workflowDefinitionLinkLocalService.getDefaultWorkflowDefinitionLink(companyId,
			className, classPK, typePK);
	}

	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink getWorkflowDefinitionLink(
		long companyId, long groupId, java.lang.String className, long classPK,
		long typePK) throws com.liferay.portal.kernel.exception.PortalException {
		return _workflowDefinitionLinkLocalService.getWorkflowDefinitionLink(companyId,
			groupId, className, classPK, typePK);
	}

	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink getWorkflowDefinitionLink(
		long companyId, long groupId, java.lang.String className, long classPK,
		long typePK, boolean strict)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workflowDefinitionLinkLocalService.getWorkflowDefinitionLink(companyId,
			groupId, className, classPK, typePK, strict);
	}

	/**
	* Returns the workflow definition link with the primary key.
	*
	* @param workflowDefinitionLinkId the primary key of the workflow definition link
	* @return the workflow definition link
	* @throws PortalException if a workflow definition link with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink getWorkflowDefinitionLink(
		long workflowDefinitionLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workflowDefinitionLinkLocalService.getWorkflowDefinitionLink(workflowDefinitionLinkId);
	}

	/**
	* Updates the workflow definition link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param workflowDefinitionLink the workflow definition link
	* @return the workflow definition link that was updated
	*/
	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink updateWorkflowDefinitionLink(
		com.liferay.portal.kernel.model.WorkflowDefinitionLink workflowDefinitionLink) {
		return _workflowDefinitionLinkLocalService.updateWorkflowDefinitionLink(workflowDefinitionLink);
	}

	@Override
	public com.liferay.portal.kernel.model.WorkflowDefinitionLink updateWorkflowDefinitionLink(
		long userId, long companyId, long groupId, java.lang.String className,
		long classPK, long typePK, java.lang.String workflowDefinitionName,
		int workflowDefinitionVersion)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workflowDefinitionLinkLocalService.updateWorkflowDefinitionLink(userId,
			companyId, groupId, className, classPK, typePK,
			workflowDefinitionName, workflowDefinitionVersion);
	}

	/**
	* Returns the number of workflow definition links.
	*
	* @return the number of workflow definition links
	*/
	@Override
	public int getWorkflowDefinitionLinksCount() {
		return _workflowDefinitionLinkLocalService.getWorkflowDefinitionLinksCount();
	}

	@Override
	public int getWorkflowDefinitionLinksCount(long companyId,
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion) {
		return _workflowDefinitionLinkLocalService.getWorkflowDefinitionLinksCount(companyId,
			workflowDefinitionName, workflowDefinitionVersion);
	}

	@Override
	public int getWorkflowDefinitionLinksCount(long companyId, long groupId,
		java.lang.String className) {
		return _workflowDefinitionLinkLocalService.getWorkflowDefinitionLinksCount(companyId,
			groupId, className);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _workflowDefinitionLinkLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _workflowDefinitionLinkLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.WorkflowDefinitionLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _workflowDefinitionLinkLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.WorkflowDefinitionLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _workflowDefinitionLinkLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the workflow definition links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.WorkflowDefinitionLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workflow definition links
	* @param end the upper bound of the range of workflow definition links (not inclusive)
	* @return the range of workflow definition links
	*/
	@Override
	public java.util.List<com.liferay.portal.kernel.model.WorkflowDefinitionLink> getWorkflowDefinitionLinks(
		int start, int end) {
		return _workflowDefinitionLinkLocalService.getWorkflowDefinitionLinks(start,
			end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _workflowDefinitionLinkLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _workflowDefinitionLinkLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void deleteWorkflowDefinitionLink(long companyId, long groupId,
		java.lang.String className, long classPK, long typePK) {
		_workflowDefinitionLinkLocalService.deleteWorkflowDefinitionLink(companyId,
			groupId, className, classPK, typePK);
	}

	@Override
	public void updateWorkflowDefinitionLink(long userId, long companyId,
		long groupId, java.lang.String className, long classPK, long typePK,
		java.lang.String workflowDefinition)
		throws com.liferay.portal.kernel.exception.PortalException {
		_workflowDefinitionLinkLocalService.updateWorkflowDefinitionLink(userId,
			companyId, groupId, className, classPK, typePK, workflowDefinition);
	}

	@Override
	public void updateWorkflowDefinitionLinks(long userId, long companyId,
		long groupId, java.lang.String className, long classPK,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.Long, java.lang.String>> workflowDefinitionOVPs)
		throws com.liferay.portal.kernel.exception.PortalException {
		_workflowDefinitionLinkLocalService.updateWorkflowDefinitionLinks(userId,
			companyId, groupId, className, classPK, workflowDefinitionOVPs);
	}

	@Override
	public WorkflowDefinitionLinkLocalService getWrappedService() {
		return _workflowDefinitionLinkLocalService;
	}

	@Override
	public void setWrappedService(
		WorkflowDefinitionLinkLocalService workflowDefinitionLinkLocalService) {
		_workflowDefinitionLinkLocalService = workflowDefinitionLinkLocalService;
	}

	private WorkflowDefinitionLinkLocalService _workflowDefinitionLinkLocalService;
}