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

package com.liferay.portal.background.task.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.background.task.model.BackgroundTask;
import com.liferay.portal.kernel.cluster.Clusterable;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for BackgroundTask. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see BackgroundTaskLocalServiceUtil
 * @see com.liferay.portal.background.task.service.base.BackgroundTaskLocalServiceBaseImpl
 * @see com.liferay.portal.background.task.service.impl.BackgroundTaskLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface BackgroundTaskLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BackgroundTaskLocalServiceUtil} to access the background task local service. Add custom service methods to {@link com.liferay.portal.background.task.service.impl.BackgroundTaskLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the background task to the database. Also notifies the appropriate model listeners.
	*
	* @param backgroundTask the background task
	* @return the background task that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public BackgroundTask addBackgroundTask(BackgroundTask backgroundTask);

	public BackgroundTask addBackgroundTask(long userId, long groupId,
		java.lang.String name, java.lang.String taskExecutorClassName,
		Map<java.lang.String, Serializable> taskContextMap,
		ServiceContext serviceContext) throws PortalException;

	public BackgroundTask addBackgroundTask(long userId, long groupId,
		java.lang.String name, java.lang.String[] servletContextNames,
		java.lang.Class<?> taskExecutorClass,
		Map<java.lang.String, Serializable> taskContextMap,
		ServiceContext serviceContext) throws PortalException;

	public BackgroundTask amendBackgroundTask(long backgroundTaskId,
		Map<java.lang.String, Serializable> taskContextMap, int status,
		ServiceContext serviceContext);

	public BackgroundTask amendBackgroundTask(long backgroundTaskId,
		Map<java.lang.String, Serializable> taskContextMap, int status,
		java.lang.String statusMessage, ServiceContext serviceContext);

	/**
	* Creates a new background task with the primary key. Does not add the background task to the database.
	*
	* @param backgroundTaskId the primary key for the new background task
	* @return the new background task
	*/
	public BackgroundTask createBackgroundTask(long backgroundTaskId);

	/**
	* Deletes the background task from the database. Also notifies the appropriate model listeners.
	*
	* @param backgroundTask the background task
	* @return the background task that was removed
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.DELETE)
	public BackgroundTask deleteBackgroundTask(BackgroundTask backgroundTask)
		throws PortalException;

	/**
	* Deletes the background task with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param backgroundTaskId the primary key of the background task
	* @return the background task that was removed
	* @throws PortalException if a background task with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public BackgroundTask deleteBackgroundTask(long backgroundTaskId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BackgroundTask fetchBackgroundTask(long backgroundTaskId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BackgroundTask fetchFirstBackgroundTask(
		java.lang.String taskExecutorClassName, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BackgroundTask fetchFirstBackgroundTask(
		java.lang.String taskExecutorClassName, int status,
		OrderByComparator<BackgroundTask> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BackgroundTask fetchFirstBackgroundTask(long groupId,
		java.lang.String taskExecutorClassName, boolean completed,
		OrderByComparator<BackgroundTask> orderByComparator);

	/**
	* Returns the background task with the primary key.
	*
	* @param backgroundTaskId the primary key of the background task
	* @return the background task
	* @throws PortalException if a background task with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BackgroundTask getBackgroundTask(long backgroundTaskId)
		throws PortalException;

	/**
	* Updates the background task in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param backgroundTask the background task
	* @return the background task that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public BackgroundTask updateBackgroundTask(BackgroundTask backgroundTask);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the number of background tasks.
	*
	* @return the number of background tasks
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getBackgroundTasksCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getBackgroundTasksCount(long groupId, java.lang.String name,
		java.lang.String taskExecutorClassName);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getBackgroundTasksCount(long groupId, java.lang.String name,
		java.lang.String taskExecutorClassName, boolean completed);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getBackgroundTasksCount(long groupId,
		java.lang.String taskExecutorClassName);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getBackgroundTasksCount(long groupId,
		java.lang.String taskExecutorClassName, boolean completed);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getBackgroundTasksCount(long[] groupIds, java.lang.String name,
		java.lang.String taskExecutorClassName);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getBackgroundTasksCount(long[] groupIds, java.lang.String name,
		java.lang.String taskExecutorClassName, boolean completed);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getBackgroundTasksCount(long[] groupIds,
		java.lang.String[] taskExecutorClassNames);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getBackgroundTasksCount(long[] groupIds,
		java.lang.String[] taskExecutorClassNames, boolean completed);

	@Clusterable(onMaster = true)
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getBackgroundTaskStatusJSON(long backgroundTaskId);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.background.task.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.background.task.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns a range of all the background tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.background.task.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of background tasks
	* @param end the upper bound of the range of background tasks (not inclusive)
	* @return the range of background tasks
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BackgroundTask> getBackgroundTasks(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BackgroundTask> getBackgroundTasks(
		java.lang.String taskExecutorClassName, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BackgroundTask> getBackgroundTasks(
		java.lang.String taskExecutorClassName, int status, int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BackgroundTask> getBackgroundTasks(
		java.lang.String[] taskExecutorClassNames, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BackgroundTask> getBackgroundTasks(
		java.lang.String[] taskExecutorClassNames, int status, int start,
		int end, OrderByComparator<BackgroundTask> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BackgroundTask> getBackgroundTasks(long groupId, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BackgroundTask> getBackgroundTasks(long groupId,
		java.lang.String name, java.lang.String taskExecutorClassName,
		int start, int end, OrderByComparator<BackgroundTask> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BackgroundTask> getBackgroundTasks(long groupId,
		java.lang.String taskExecutorClassName);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BackgroundTask> getBackgroundTasks(long groupId,
		java.lang.String taskExecutorClassName, boolean completed, int start,
		int end, OrderByComparator<BackgroundTask> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BackgroundTask> getBackgroundTasks(long groupId,
		java.lang.String taskExecutorClassName, int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BackgroundTask> getBackgroundTasks(long groupId,
		java.lang.String taskExecutorClassName, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BackgroundTask> getBackgroundTasks(long groupId,
		java.lang.String[] taskExecutorClassNames, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BackgroundTask> getBackgroundTasks(long[] groupIds,
		java.lang.String name, java.lang.String taskExecutorClassName,
		int start, int end, OrderByComparator<BackgroundTask> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BackgroundTask> getBackgroundTasks(long[] groupIds,
		java.lang.String[] taskExecutorClassNames);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BackgroundTask> getBackgroundTasks(long[] groupIds,
		java.lang.String[] taskExecutorClassNames, boolean completed);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BackgroundTask> getBackgroundTasks(long[] groupIds,
		java.lang.String[] taskExecutorClassNames, boolean completed,
		int start, int end, OrderByComparator<BackgroundTask> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BackgroundTask> getBackgroundTasks(long[] groupIds,
		java.lang.String[] taskExecutorClassNames, int start, int end,
		OrderByComparator<BackgroundTask> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	public void addBackgroundTaskAttachment(long userId, long backgroundTaskId,
		java.lang.String fileName, File file) throws PortalException;

	public void addBackgroundTaskAttachment(long userId, long backgroundTaskId,
		java.lang.String fileName, InputStream inputStream)
		throws PortalException;

	@Clusterable(onMaster = true)
	public void cleanUpBackgroundTask(long backgroundTaskId, int status);

	@Clusterable(onMaster = true)
	public void cleanUpBackgroundTasks();

	public void deleteCompanyBackgroundTasks(long companyId)
		throws PortalException;

	public void deleteGroupBackgroundTasks(long groupId)
		throws PortalException;

	public void deleteGroupBackgroundTasks(long groupId, java.lang.String name,
		java.lang.String taskExecutorClassName) throws PortalException;

	@Clusterable(onMaster = true)
	public void resumeBackgroundTask(long backgroundTaskId);

	@Clusterable(onMaster = true)
	public void triggerBackgroundTask(long backgroundTaskId);
}