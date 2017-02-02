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

package com.liferay.gradle.plugins.maven.plugin.builder;

import com.liferay.gradle.util.GradleUtil;

import java.io.File;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;

import org.gradle.api.Action;
import org.gradle.api.JavaVersion;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.DependencySet;
import org.gradle.api.file.FileCollection;
import org.gradle.api.file.SourceDirectorySet;
import org.gradle.api.internal.plugins.osgi.OsgiHelper;
import org.gradle.api.plugins.BasePlugin;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetOutput;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.api.tasks.Upload;
import org.gradle.api.tasks.javadoc.Javadoc;
import org.gradle.external.javadoc.CoreJavadocOptions;

/**
 * @author Andrea Di Giorgi
 */
public class MavenPluginBuilderPlugin implements Plugin<Project> {

	public static final String BUILD_PLUGIN_DESCRIPTOR_TASK_NAME =
		"buildPluginDescriptor";

	public static final String MAVEN_EMBEDDER_CONFIGURATION_NAME =
		"mavenEmbedder";

	@Override
	public void apply(Project project) {
		GradleUtil.applyPlugin(project, JavaPlugin.class);

		Configuration mavenEmbedderConfiguration =
			addConfigurationMavenEmbedder(project);

		BuildPluginDescriptorTask buildPluginDescriptorTask =
			addTaskBuildPluginDescriptor(project, mavenEmbedderConfiguration);

		JavaVersion javaVersion = JavaVersion.current();

		if (javaVersion.isJava8Compatible()) {
			configureTasksJavadocDisableDoclint(project);
		}

		configureTasksUpload(project, buildPluginDescriptorTask);
	}

	protected Configuration addConfigurationMavenEmbedder(
		final Project project) {

		Configuration configuration = GradleUtil.addConfiguration(
			project, MAVEN_EMBEDDER_CONFIGURATION_NAME);

		configuration.defaultDependencies(
			new Action<DependencySet>() {

				@Override
				public void execute(DependencySet dependencySet) {
					addDependenciesMavenEmbedder(project);
				}

			});

		configuration.setDescription(
			"Configures Maven Embedder for this project.");
		configuration.setVisible(false);

		return configuration;
	}

	protected void addDependenciesMavenEmbedder(Project project) {
		GradleUtil.addDependency(
			project, MAVEN_EMBEDDER_CONFIGURATION_NAME, "org.apache.maven",
			"maven-embedder", "3.3.9");
		GradleUtil.addDependency(
			project, MAVEN_EMBEDDER_CONFIGURATION_NAME,
			"org.apache.maven.wagon", "wagon-http", "2.10");
		GradleUtil.addDependency(
			project, MAVEN_EMBEDDER_CONFIGURATION_NAME, "org.eclipse.aether",
			"aether-connector-basic", "1.0.2.v20150114");
		GradleUtil.addDependency(
			project, MAVEN_EMBEDDER_CONFIGURATION_NAME, "org.eclipse.aether",
			"aether-transport-wagon", "1.0.2.v20150114");
		GradleUtil.addDependency(
			project, MAVEN_EMBEDDER_CONFIGURATION_NAME, "org.slf4j",
			"slf4j-simple", "1.7.5");
	}

	protected BuildPluginDescriptorTask addTaskBuildPluginDescriptor(
		final Project project, FileCollection mavenEmbedderClasspath) {

		BuildPluginDescriptorTask buildPluginDescriptorTask =
			GradleUtil.addTask(
				project, BUILD_PLUGIN_DESCRIPTOR_TASK_NAME,
				BuildPluginDescriptorTask.class);

		buildPluginDescriptorTask.dependsOn(JavaPlugin.COMPILE_JAVA_TASK_NAME);

		final SourceSet sourceSet = GradleUtil.getSourceSet(
			project, SourceSet.MAIN_SOURCE_SET_NAME);

		buildPluginDescriptorTask.setClassesDir(
			new Callable<File>() {

				@Override
				public File call() throws Exception {
					SourceSetOutput sourceSetOutput = sourceSet.getOutput();

					return sourceSetOutput.getClassesDir();
				}

			});

		buildPluginDescriptorTask.setDescription(
			"Generates the Maven plugin descriptor for the project.");
		buildPluginDescriptorTask.setGroup(BasePlugin.BUILD_GROUP);
		buildPluginDescriptorTask.setMavenEmbedderClasspath(
			mavenEmbedderClasspath);

		buildPluginDescriptorTask.setOutputDir(
			new Callable<File>() {

				@Override
				public File call() throws Exception {
					File resourcesDir = getSrcDir(sourceSet.getResources());

					return new File(resourcesDir, "META-INF/maven");
				}

			});

		buildPluginDescriptorTask.setPomArtifactId(
			new Callable<String>() {

				@Override
				public String call() throws Exception {
					return _osgiHelper.getBundleSymbolicName(project);
				}

			});

		buildPluginDescriptorTask.setPomGroupId(
			new Callable<Object>() {

				@Override
				public Object call() throws Exception {
					return project.getGroup();
				}

			});

		buildPluginDescriptorTask.setPomVersion(
			new Callable<String>() {

				@Override
				public String call() throws Exception {
					String version = String.valueOf(project.getVersion());

					if (version.endsWith("-SNAPSHOT")) {
						version = version.substring(0, version.length() - 9);
					}

					return version;
				}

			});

		buildPluginDescriptorTask.setSourceDir(
			new Callable<File>() {

				@Override
				public File call() throws Exception {
					return getSrcDir(sourceSet.getJava());
				}

			});

		Task processResourcesTask = GradleUtil.getTask(
			project, JavaPlugin.PROCESS_RESOURCES_TASK_NAME);

		processResourcesTask.mustRunAfter(buildPluginDescriptorTask);

		return buildPluginDescriptorTask;
	}

	protected void configureTaskJavadocDisableDoclint(Javadoc javadoc) {
		CoreJavadocOptions coreJavadocOptions =
			(CoreJavadocOptions)javadoc.getOptions();

		coreJavadocOptions.addStringOption("Xdoclint:none", "-quiet");
	}

	protected void configureTasksJavadocDisableDoclint(Project project) {
		TaskContainer taskContainer = project.getTasks();

		taskContainer.withType(
			Javadoc.class,
			new Action<Javadoc>() {

				@Override
				public void execute(Javadoc javadoc) {
					configureTaskJavadocDisableDoclint(javadoc);
				}

			});
	}

	protected void configureTasksUpload(
		Project project,
		final BuildPluginDescriptorTask buildPluginDescriptorTask) {

		TaskContainer taskContainer = project.getTasks();

		taskContainer.withType(
			Upload.class,
			new Action<Upload>() {

				@Override
				public void execute(Upload upload) {
					configureTaskUpload(upload, buildPluginDescriptorTask);
				}

			});
	}

	protected void configureTaskUpload(
		Upload upload, BuildPluginDescriptorTask buildPluginDescriptorTask) {

		upload.dependsOn(buildPluginDescriptorTask);
	}

	protected File getSrcDir(SourceDirectorySet sourceDirectorySet) {
		Set<File> srcDirs = sourceDirectorySet.getSrcDirs();

		Iterator<File> iterator = srcDirs.iterator();

		return iterator.next();
	}

	private static final OsgiHelper _osgiHelper = new OsgiHelper();

}