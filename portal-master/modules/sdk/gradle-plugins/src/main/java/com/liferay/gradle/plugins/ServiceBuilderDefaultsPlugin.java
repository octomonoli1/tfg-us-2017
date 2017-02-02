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

package com.liferay.gradle.plugins;

import com.liferay.gradle.plugins.service.builder.BuildServiceTask;
import com.liferay.gradle.plugins.service.builder.ServiceBuilderPlugin;
import com.liferay.gradle.plugins.tasks.BuildDBTask;
import com.liferay.gradle.plugins.util.GradleUtil;

import java.io.File;

import java.util.concurrent.Callable;

import org.dm.gradle.plugins.bundle.BundlePlugin;

import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.file.FileCollection;
import org.gradle.api.plugins.BasePlugin;
import org.gradle.api.tasks.TaskContainer;

/**
 * @author Andrea Di Giorgi
 */
public class ServiceBuilderDefaultsPlugin
	extends BasePortalToolDefaultsPlugin<ServiceBuilderPlugin> {

	public static final String BUILD_DB_TASK_NAME = "buildDB";

	public static final String PORTAL_TOOL_NAME =
		"com.liferay.portal.tools.service.builder";

	protected BuildDBTask addTaskBuildDB(final Project project) {
		BuildDBTask buildDBTask = GradleUtil.addTask(
			project, BUILD_DB_TASK_NAME, BuildDBTask.class);

		buildDBTask.setDatabaseName("lportal");
		buildDBTask.setDatabaseTypes("hypersonic", "mysql", "postgresql");
		buildDBTask.setDescription(
			"Builds database SQL scripts from the generic SQL scripts.");
		buildDBTask.setGroup(BasePlugin.BUILD_GROUP);

		buildDBTask.setSqlDir(
			new Callable<File>() {

				@Override
				public File call() throws Exception {
					BuildServiceTask buildServiceTask =
						(BuildServiceTask)GradleUtil.getTask(
							project,
							ServiceBuilderPlugin.BUILD_SERVICE_TASK_NAME);

					return buildServiceTask.getSqlDir();
				}

			});

		return buildDBTask;
	}

	@Override
	protected void configureDefaults(
		final Project project, ServiceBuilderPlugin serviceBuilderPlugin) {

		super.configureDefaults(project, serviceBuilderPlugin);

		addTaskBuildDB(project);

		GradleUtil.withPlugin(
			project, LiferayBasePlugin.class,
			new Action<LiferayBasePlugin>() {

				@Override
				public void execute(LiferayBasePlugin liferayBasePlugin) {
					Configuration portalConfiguration =
						GradleUtil.getConfiguration(
							project,
							LiferayBasePlugin.PORTAL_CONFIGURATION_NAME);

					configureTasksBuildDB(project, portalConfiguration);
				}

			});

		GradleUtil.withPlugin(
			project, BundlePlugin.class,
			new Action<BundlePlugin>() {

				@Override
				public void execute(BundlePlugin bundlePlugin) {
					configureTasksBuildServiceForBundlePlugin(project);
				}

			});
	}

	protected void configureTaskBuildDBClasspath(
		BuildDBTask buildDBTask, FileCollection fileCollection) {

		buildDBTask.setClasspath(fileCollection);
	}

	protected void configureTaskBuildServiceForBundlePlugin(
		BuildServiceTask buildServiceTask) {

		buildServiceTask.setOsgiModule(true);
	}

	protected void configureTasksBuildDB(
		Project project, final FileCollection classpath) {

		TaskContainer taskContainer = project.getTasks();

		taskContainer.withType(
			BuildDBTask.class,
			new Action<BuildDBTask>() {

				@Override
				public void execute(BuildDBTask buildDBTask) {
					configureTaskBuildDBClasspath(buildDBTask, classpath);
				}

			});
	}

	protected void configureTasksBuildServiceForBundlePlugin(Project project) {
		TaskContainer taskContainer = project.getTasks();

		taskContainer.withType(
			BuildServiceTask.class,
			new Action<BuildServiceTask>() {

				@Override
				public void execute(BuildServiceTask buildServiceTask) {
					configureTaskBuildServiceForBundlePlugin(buildServiceTask);
				}

			});
	}

	@Override
	protected Class<ServiceBuilderPlugin> getPluginClass() {
		return ServiceBuilderPlugin.class;
	}

	@Override
	protected String getPortalToolConfigurationName() {
		return ServiceBuilderPlugin.CONFIGURATION_NAME;
	}

	@Override
	protected String getPortalToolName() {
		return PORTAL_TOOL_NAME;
	}

}