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

package com.liferay.portal.comment.display.context;

import com.liferay.portal.kernel.comment.display.context.CommentDisplayContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.security.sso.SSOUtil;

/**
 * @author Adolfo Pérez
 */
public abstract class BaseCommentDisplayContext
	implements CommentDisplayContext {

	@Override
	public boolean isReplyButtonVisible() {
		ThemeDisplay themeDisplay = getThemeDisplay();

		if (themeDisplay.isSignedIn() ||
			!SSOUtil.isLoginRedirectRequired(themeDisplay.getCompanyId())) {

			return true;
		}

		return false;
	}

	protected abstract ThemeDisplay getThemeDisplay();

}