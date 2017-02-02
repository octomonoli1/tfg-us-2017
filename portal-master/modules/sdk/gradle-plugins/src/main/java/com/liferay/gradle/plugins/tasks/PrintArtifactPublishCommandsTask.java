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

package com.liferay.gradle.plugins.tasks;

import com.liferay.gradle.plugins.LiferayOSGiDefaultsPlugin;
import com.liferay.gradle.plugins.change.log.builder.BuildChangeLogTask;
import com.liferay.gradle.plugins.change.log.builder.ChangeLogBuilderPlugin;
import com.liferay.gradle.plugins.util.FileUtil;
import com.liferay.gradle.plugins.util.GradleUtil;
import com.liferay.gradle.util.Validator;

import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.file.FileCollection;
import org.gradle.api.plugins.BasePlugin;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.util.GUtil;
import org.gradle.util.VersionNumber;

/**
 * @author Andrea Di Giorgi
 */
public class PrintArtifactPublishCommandsTask extends DefaultTask {

	public static final String IGNORED_MESSAGE_PATTERN = "artifact:ignore";

	public PrintArtifactPublishCommandsTask() {
		String firstOnlyString = GradleUtil.getTaskPrefixedProperty(
			this, "first");

		if (Validator.isNotNull(firstOnlyString)) {
			_firstOnly = Boolean.parseBoolean(firstOnlyString);
		}

		_gradleDaemon = true;

		String gradleDaemonString = GradleUtil.getTaskPrefixedProperty(
			this, "daemon");

		if (Validator.isNotNull(gradleDaemonString)) {
			_gradleDaemon = Boolean.parseBoolean(gradleDaemonString);
		}

		Project project = getProject();

		_gradleDir = GradleUtil.getRootDir(project.getRootProject(), "gradlew");
	}

	@Input
	@Optional
	public File getArtifactPropertiesFile() {
		return GradleUtil.toFile(getProject(), _artifactPropertiesFile);
	}

	@Input
	@Optional
	public String getFirstPublishExcludedTaskName() {
		return GradleUtil.toString(_firstPublishExcludedTaskName);
	}

	@Input
	public File getGradleDir() {
		return GradleUtil.toFile(getProject(), _gradleDir);
	}

	@Input
	public String getLowestPublishedVersion() {
		return GradleUtil.toString(_lowestPublishedVersion);
	}

	@Input
	public FileCollection getPrepNextFiles() {
		Project project = getProject();

		return project.files(_prepNextFiles);
	}

	public boolean isFirstOnly() {
		return _firstOnly;
	}

	@Input
	public boolean isForcedCache() {
		return _forcedCache;
	}

	@Input
	public boolean isGradleDaemon() {
		return _gradleDaemon;
	}

	public PrintArtifactPublishCommandsTask prepNextFiles(
		Iterable<?> prepNextFiles) {

		GUtil.addToCollection(_prepNextFiles, prepNextFiles);

		return this;
	}

	public PrintArtifactPublishCommandsTask prepNextFiles(
		Object... prepNextFiles) {

		return prepNextFiles(Arrays.asList(prepNextFiles));
	}

	@TaskAction
	public void printArtifactPublishCommands() {
		List<String> commands = new ArrayList<>();

		Project project = getProject();

		// Move to the root directory

		commands.add("cd " + FileUtil.getAbsolutePath(project.getRootDir()));

		// Publish if the artifact has never been published

		if (!isPublished()) {
			addPublishCommands(commands, true);
		}

		// Change log

		BuildChangeLogTask buildChangeLogTask = (BuildChangeLogTask)getTask(
			ChangeLogBuilderPlugin.BUILD_CHANGE_LOG_TASK_NAME);

		if (buildChangeLogTask != null) {
			commands.add(getGradleCommand(buildChangeLogTask));

			commands.add(
				"git add " +
					getRelativePath(buildChangeLogTask.getChangeLogFile()));

			commands.add(getGitCommitCommand("change log", false, true, true));
		}

		// Baseline

		Task baselineTask = getTask(
			LiferayOSGiDefaultsPlugin.BASELINE_TASK_NAME);

		if (baselineTask != null) {
			commands.add(getGradleCommand(baselineTask));

			commands.add(
				"git add --all " + getRelativePath(project.getProjectDir()));

			commands.add(
				getGitCommitCommand("packageinfo", false, false, true));
		}

		// Publish the artifact since there will either be change log or
		// baseline changes

		if ((baselineTask != null) || (buildChangeLogTask != null)) {
			addPublishCommands(commands, false);
		}

		System.out.println();

		for (String command : commands) {
			System.out.print(" && ");
			System.out.print(command);
		}

		if (isFirstOnly()) {
			throw new GradleException();
		}
	}

	public void setArtifactPropertiesFile(Object artifactPropertiesFile) {
		_artifactPropertiesFile = artifactPropertiesFile;
	}

	public void setFirstOnly(boolean firstOnly) {
		_firstOnly = firstOnly;
	}

	public void setFirstPublishExcludedTaskName(
		Object firstPublishExcludedTaskName) {

		_firstPublishExcludedTaskName = firstPublishExcludedTaskName;
	}

	public void setForcedCache(boolean forcedCache) {
		_forcedCache = forcedCache;
	}

	public void setGradleDaemon(boolean gradleDaemon) {
		_gradleDaemon = gradleDaemon;
	}

	public void setGradleDir(Object gradleDir) {
		_gradleDir = gradleDir;
	}

	public void setLowestPublishedVersion(Object lowestPublishedVersion) {
		_lowestPublishedVersion = lowestPublishedVersion;
	}

	public void setPrepNextFiles(Iterable<?> prepNextFiles) {
		_prepNextFiles.clear();

		prepNextFiles(prepNextFiles);
	}

	public void setPrepNextFiles(Object... prepNextFiles) {
		setPrepNextFiles(Arrays.asList(prepNextFiles));
	}

	protected void addPublishCommands(
		List<String> commands, boolean firstPublish) {

		// Publish snapshot

		commands.add(
			getGradleCommand(
				BasePlugin.UPLOAD_ARCHIVES_TASK_NAME,
				"-P" + GradleUtil.SNAPSHOT_PROPERTY_NAME));

		// Publish release

		String[] arguments = new String[0];

		if (firstPublish) {
			Task task = getTask(getFirstPublishExcludedTaskName());

			if (task != null) {
				arguments = new String[] {"-x", task.getPath()};
			}
		}

		commands.add(
			getGradleCommand(BasePlugin.UPLOAD_ARCHIVES_TASK_NAME, arguments));

		// Commit "prep next"

		boolean prepNext = false;

		for (File file : getPrepNextFiles()) {
			if (!file.exists()) {
				continue;
			}

			prepNext = true;

			commands.add("git add " + getRelativePath(file));
		}

		if (prepNext) {
			commands.add(getGitCommitCommand("prep next", false, true, false));
		}

		// Commit "artifact properties"

		File artifactPropertiesFile = getArtifactPropertiesFile();

		if (artifactPropertiesFile != null) {
			commands.add("git add " + getRelativePath(artifactPropertiesFile));

			commands.add(
				getGitCommitCommand("artifact properties", false, true, false));
		}

		// Commit other changed files

		commands.add(getGitCommitCommand("apply", true, false, true));
	}

	protected String getGitCommitCommand(
		String message, boolean all, boolean ignored, boolean quiet) {

		StringBuilder sb = new StringBuilder();

		Project project = getProject();

		if (all || quiet) {
			sb.append("(git diff-index --quiet HEAD || ");
		}

		sb.append("git commit ");

		if (all) {
			sb.append("--all ");
		}

		sb.append("--message=\"");

		if (ignored) {
			sb.append(IGNORED_MESSAGE_PATTERN);
			sb.append(' ');
		}

		sb.append(project.getName());
		sb.append(' ');
		sb.append(project.getVersion());
		sb.append(' ');
		sb.append(message);

		sb.append('"');

		if (all || quiet) {
			sb.append(')');
		}

		return sb.toString();
	}

	protected String getGradleCommand(String taskName, String... arguments) {
		Task task = GradleUtil.getTask(getProject(), taskName);

		return getGradleCommand(task, arguments);
	}

	protected String getGradleCommand(Task task, String... arguments) {
		StringBuilder sb = new StringBuilder();

		sb.append(getGradleRelativePath());
		sb.append(' ');
		sb.append(task.getPath());

		if (isGradleDaemon()) {
			sb.append(" --daemon");
		}

		if (isForcedCache() &&
			!LiferayOSGiDefaultsPlugin.BASELINE_TASK_NAME.equals(
				task.getName())) {

			sb.append(" -Dforced.cache.enabled=true");
		}

		for (String argument : arguments) {
			sb.append(' ');
			sb.append(argument);
		}

		return sb.toString();
	}

	protected File getGradleFile() {
		return new File(getGradleDir(), "gradlew");
	}

	protected String getGradleRelativePath() {
		return getRelativePath(getGradleFile());
	}

	protected String getRelativePath(Object object) {
		Project project = getProject();

		File file = project.file(object);

		Project rootProject = project.getRootProject();

		return rootProject.relativePath(file);
	}

	protected Task getTask(String name) {
		if (Validator.isNull(name)) {
			return null;
		}

		Project project = getProject();

		TaskContainer taskContainer = project.getTasks();

		return taskContainer.findByName(name);
	}

	protected boolean isPublished() {
		Project project = getProject();

		String version = String.valueOf(project.getVersion());

		VersionNumber versionNumber = VersionNumber.parse(version);
		VersionNumber lowestPublishedVersionNumber = VersionNumber.parse(
			getLowestPublishedVersion());

		if (versionNumber.compareTo(lowestPublishedVersionNumber) > 0) {
			return true;
		}

		return false;
	}

	private Object _artifactPropertiesFile;
	private boolean _firstOnly;
	private Object _firstPublishExcludedTaskName;
	private boolean _forcedCache = true;
	private boolean _gradleDaemon;
	private Object _gradleDir;
	private Object _lowestPublishedVersion = "1.0.0";
	private final Set<Object> _prepNextFiles = new LinkedHashSet<>();

}