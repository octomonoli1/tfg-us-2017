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

package com.liferay.gradle.plugins.wsdd.builder;

import com.liferay.gradle.util.GradleUtil;

import java.io.File;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.file.FileCollection;
import org.gradle.api.file.SourceDirectorySet;
import org.gradle.api.plugins.BasePlugin;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.PluginContainer;
import org.gradle.api.plugins.WarPlugin;
import org.gradle.api.plugins.WarPluginConvention;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.api.tasks.TaskOutputs;

/**
 * @author Andrea Di Giorgi
 */
public class WSDDBuilderPlugin implements Plugin<Project> {

	public static final String BUILD_WSDD_TASK_NAME = "buildWSDD";

	public static final String CONFIGURATION_NAME = "wsddBuilder";

	@Override
	public void apply(Project project) {
		GradleUtil.applyPlugin(project, JavaPlugin.class);

		Configuration wsddBuilderConfiguration = addConfigurationWSDDBuilder(
			project);

		addTaskBuildWSDD(project);

		configureTasksBuildWSDD(project, wsddBuilderConfiguration);
	}

	protected Configuration addConfigurationWSDDBuilder(final Project project) {
		Configuration configuration = GradleUtil.addConfiguration(
			project, CONFIGURATION_NAME);

		configuration.setDescription(
			"Configures Liferay WSDD Builder for this project.");
		configuration.setVisible(false);

		GradleUtil.executeIfEmpty(
			configuration,
			new Action<Configuration>() {

				@Override
				public void execute(Configuration configuration) {
					addDependenciesWSDDBuilder(project);
				}

			});

		return configuration;
	}

	protected void addDependenciesWSDDBuilder(Project project) {
		GradleUtil.addDependency(
			project, CONFIGURATION_NAME, "com.liferay",
			"com.liferay.portal.tools.wsdd.builder", "latest.release");
	}

	protected BuildWSDDTask addTaskBuildWSDD(Project project) {
		final BuildWSDDTask buildWSDDTask = GradleUtil.addTask(
			project, BUILD_WSDD_TASK_NAME, BuildWSDDTask.class);

		buildWSDDTask.dependsOn(JavaPlugin.COMPILE_JAVA_TASK_NAME);

		buildWSDDTask.setBuilderClasspath(
			new Callable<String>() {

				@Override
				public String call() throws Exception {
					Project project = buildWSDDTask.getProject();

					Task compileJavaTask = GradleUtil.getTask(
						project, JavaPlugin.COMPILE_JAVA_TASK_NAME);

					TaskOutputs taskOutputs = compileJavaTask.getOutputs();

					FileCollection fileCollection = taskOutputs.getFiles();

					SourceSet sourceSet = GradleUtil.getSourceSet(
						project, SourceSet.MAIN_SOURCE_SET_NAME);

					fileCollection = fileCollection.plus(
						sourceSet.getCompileClasspath());
					fileCollection = fileCollection.plus(
						sourceSet.getRuntimeClasspath());

					return fileCollection.getAsPath();
				}

			});

		buildWSDDTask.setDescription("Runs Liferay WSDD Builder.");
		buildWSDDTask.setGroup(BasePlugin.BUILD_GROUP);
		buildWSDDTask.setInputFile("service.xml");

		buildWSDDTask.setOutputDir(
			new Callable<File>() {

				@Override
				public File call() throws Exception {
					return getResourcesDir(buildWSDDTask.getProject());
				}

			});

		PluginContainer pluginContainer = project.getPlugins();

		pluginContainer.withType(
			WarPlugin.class,
			new Action<WarPlugin>() {

				@Override
				public void execute(WarPlugin warPlugin) {
					configureTaskBuildWSDDForWarPlugin(buildWSDDTask);
				}

			});

		return buildWSDDTask;
	}

	protected void configureTaskBuildWSDDClasspath(
		BuildWSDDTask buildWSDDTask, FileCollection fileCollection) {

		buildWSDDTask.setClasspath(fileCollection);
	}

	protected void configureTaskBuildWSDDForWarPlugin(
		final BuildWSDDTask buildWSDDTask) {

		buildWSDDTask.setInputFile(
			new Callable<File>() {

				@Override
				public File call() throws Exception {
					return new File(
						getWebAppDir(buildWSDDTask.getProject()),
						"WEB-INF/service.xml");
				}

			});

		buildWSDDTask.setServerConfigFile(
			new Callable<File>() {

				@Override
				public File call() throws Exception {
					return new File(
						getWebAppDir(buildWSDDTask.getProject()),
						"WEB-INF/server-config.wsdd");
				}

			});
	}

	protected void configureTasksBuildWSDD(
		Project project, final Configuration wsddBuilderCOnfiguration) {

		TaskContainer taskContainer = project.getTasks();

		taskContainer.withType(
			BuildWSDDTask.class,
			new Action<BuildWSDDTask>() {

				@Override
				public void execute(BuildWSDDTask buildWSDDTask) {
					configureTaskBuildWSDDClasspath(
						buildWSDDTask, wsddBuilderCOnfiguration);
				}

			});
	}

	protected File getResourcesDir(Project project) {
		SourceSet sourceSet = GradleUtil.getSourceSet(
			project, SourceSet.MAIN_SOURCE_SET_NAME);

		return getSrcDir(sourceSet.getResources());
	}

	protected File getSrcDir(SourceDirectorySet sourceDirectorySet) {
		Set<File> srcDirs = sourceDirectorySet.getSrcDirs();

		Iterator<File> iterator = srcDirs.iterator();

		return iterator.next();
	}

	protected File getWebAppDir(Project project) {
		WarPluginConvention warPluginConvention = GradleUtil.getConvention(
			project, WarPluginConvention.class);

		return warPluginConvention.getWebAppDir();
	}

}