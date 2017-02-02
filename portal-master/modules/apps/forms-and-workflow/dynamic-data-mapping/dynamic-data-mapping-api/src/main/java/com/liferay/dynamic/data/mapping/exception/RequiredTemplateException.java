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

package com.liferay.dynamic.data.mapping.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class RequiredTemplateException extends PortalException {

	public RequiredTemplateException() {
	}

	public RequiredTemplateException(String msg) {
		super(msg);
	}

	public RequiredTemplateException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public RequiredTemplateException(Throwable cause) {
		super(cause);
	}

	public static class MustNotDeleteTemplateReferencedByTemplateLinks
		extends RequiredTemplateException {

		public MustNotDeleteTemplateReferencedByTemplateLinks(long templateId) {
			super(
				String.format(
					"Template %s cannot be deleted because it is " +
						"referenced by one or more template links",
					templateId));
		}

	}

}