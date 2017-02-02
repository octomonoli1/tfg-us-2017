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

import aQute.service.reporter.Reporter;

import com.liferay.ant.bnd.Baseline;
import com.liferay.gradle.plugins.util.GradleUtil;
import com.liferay.gradle.util.Validator;

import java.io.File;

import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;
import org.gradle.api.reporting.ReportingExtension;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.VerificationTask;

/**
 * @author Andrea Di Giorgi
 */
public class BaselineTask extends DefaultTask implements VerificationTask {

	public BaselineTask() {
		_logFileName = "baseline/" + getName() + ".log";
	}

	@TaskAction
	public void baseline() throws Exception {
		Baseline baseline = new Baseline() {

			@Override
			protected void log(Reporter reporter) {
				if (_logger.isErrorEnabled()) {
					for (String message : reporter.getErrors()) {
						_logger.error(message);
					}
				}

				if (_logger.isWarnEnabled()) {
					for (String message : reporter.getWarnings()) {
						_logger.warn(message);
					}
				}
			}

			@Override
			protected void log(String output) {
				if (_logger.isLifecycleEnabled()) {
					_logger.lifecycle(output);
				}
			}

		};

		baseline.setBndFile(getBndFile());
		baseline.setForcePackageInfo(true);
		baseline.setLogFile(getLogFile());
		baseline.setNewJarFile(getNewJarFile());
		baseline.setOldJarFile(getOldJarFile());
		baseline.setReportDiff(isReportDiff());
		baseline.setReportOnlyDirtyPackages(isReportOnlyDirtyPackages());
		baseline.setSourceDir(getSourceDir());

		boolean match = baseline.execute();

		if (!match) {
			String message = "Semantic versioning is incorrect";

			if (getIgnoreFailures()) {
				if (_logger.isWarnEnabled()) {
					_logger.warn(message);
				}
			}
			else {
				throw new GradleException(message);
			}
		}
	}

	@Input
	@Optional
	public File getBndFile() {
		return GradleUtil.toFile(getProject(), _bndFile);
	}

	@Override
	public boolean getIgnoreFailures() {
		return _ignoreFailures;
	}

	@Input
	@Optional
	public File getLogFile() {
		if (Validator.isNull(_logFileName)) {
			return null;
		}

		ReportingExtension reportingExtension = GradleUtil.getExtension(
			getProject(), ReportingExtension.class);

		return reportingExtension.file(_logFileName);
	}

	@InputFile
	public File getNewJarFile() {
		return GradleUtil.toFile(getProject(), _newJarFile);
	}

	@InputFile
	public File getOldJarFile() {
		return GradleUtil.toFile(getProject(), _oldJarFile);
	}

	@Input
	@Optional
	public File getSourceDir() {
		return GradleUtil.toFile(getProject(), _sourceDir);
	}

	@Input
	public boolean isReportDiff() {
		return _reportDiff;
	}

	@Input
	public boolean isReportOnlyDirtyPackages() {
		return _reportOnlyDirtyPackages;
	}

	public void setBndFile(Object bndFile) {
		_bndFile = bndFile;
	}

	@Override
	public void setIgnoreFailures(boolean ignoreFailures) {
		_ignoreFailures = ignoreFailures;
	}

	public void setLogFileName(String logFileName) {
		_logFileName = logFileName;
	}

	public void setNewJarFile(Object newJarFile) {
		_newJarFile = newJarFile;
	}

	public void setOldJarFile(Object oldJarFile) {
		_oldJarFile = oldJarFile;
	}

	public void setReportDiff(boolean reportDiff) {
		_reportDiff = reportDiff;
	}

	public void setReportOnlyDirtyPackages(boolean reportOnlyDirtyPackages) {
		_reportOnlyDirtyPackages = reportOnlyDirtyPackages;
	}

	public void setSourceDir(Object sourceDir) {
		_sourceDir = sourceDir;
	}

	private static final Logger _logger = Logging.getLogger(BaselineTask.class);

	private Object _bndFile;
	private boolean _ignoreFailures;
	private String _logFileName;
	private Object _newJarFile;
	private Object _oldJarFile;
	private boolean _reportDiff;
	private boolean _reportOnlyDirtyPackages;
	private Object _sourceDir;

}