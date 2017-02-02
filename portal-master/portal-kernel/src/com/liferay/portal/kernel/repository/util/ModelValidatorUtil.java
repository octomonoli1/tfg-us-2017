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

package com.liferay.portal.kernel.repository.util;

import com.liferay.document.library.kernel.util.DLValidatorUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileContentReference;
import com.liferay.portal.kernel.repository.model.ModelValidator;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Adolfo Pérez
 */
public class ModelValidatorUtil {

	public static final <T> ModelValidator<T> compose(
		ModelValidator<T>... modelValidators) {

		return new CompositeModelValidator<>(modelValidators);
	}

	public static final ModelValidator<FileContentReference>
		getDefaultDLFileEntryModelValidator() {

		return compose(
			getDefaultFileNameModelValidator(),
			getDefaultFileExtensionModelValidator(),
			getDefaultFileSizeModelValidator());
	}

	public static final ModelValidator<FileContentReference>
		getDefaultFileExtensionModelValidator() {

		return _defaultFileExtensionModelValidator;
	}

	public static final ModelValidator<FileContentReference>
		getDefaultFileNameModelValidator() {

		return _defaultFileNameModelValidator;
	}

	public static final ModelValidator<FileContentReference>
		getDefaultFileSizeModelValidator() {

		return _defaultFileSizeModelValidator;
	}

	private static final ModelValidator<FileContentReference>
		_defaultFileExtensionModelValidator =
			new ModelValidator<FileContentReference>() {

				@Override
				public void validate(FileContentReference fileContentReference)
					throws PortalException {

					DLValidatorUtil.validateFileExtension(
						fileContentReference.getSourceFileName());

					DLValidatorUtil.validateSourceFileExtension(
						fileContentReference.getExtension(),
						fileContentReference.getSourceFileName());
				}

			};

	private static final ModelValidator<FileContentReference>
		_defaultFileNameModelValidator =
			new ModelValidator<FileContentReference>() {

				@Override
				public void validate(FileContentReference fileContentReference)
					throws PortalException {

					if (Validator.isNotNull(
							fileContentReference.getSourceFileName())) {

						DLValidatorUtil.validateFileName(
							fileContentReference.getSourceFileName());
					}
				}

			};

	private static final ModelValidator<FileContentReference>
		_defaultFileSizeModelValidator =
			new ModelValidator<FileContentReference>() {

				@Override
				public void validate(FileContentReference fileContentReference)
					throws PortalException {

					DLValidatorUtil.validateFileSize(
						fileContentReference.getSourceFileName(),
						fileContentReference.getSize());
				}

			};

	private static class CompositeModelValidator<T>
		implements ModelValidator<T> {

		public CompositeModelValidator(ModelValidator<T>... modelValidators) {
			_modelValidators = modelValidators;
		}

		@Override
		public void validate(T t) throws PortalException {
			for (ModelValidator<T> modelValidator : _modelValidators) {
				modelValidator.validate(t);
			}
		}

		private final ModelValidator<T>[] _modelValidators;

	}

}