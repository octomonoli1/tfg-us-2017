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

import com.liferay.portal.kernel.comment.Discussion;
import com.liferay.portal.kernel.comment.DiscussionComment;
import com.liferay.portal.kernel.comment.DiscussionPermission;
import com.liferay.portal.kernel.comment.display.context.CommentSectionDisplayContext;
import com.liferay.portal.kernel.comment.display.context.CommentTreeDisplayContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Adolfo Pérez
 */
public class CommentDisplayContextProviderUtil {

	public static CommentSectionDisplayContext getCommentSectionDisplayContext(
		HttpServletRequest request, HttpServletResponse response,
		DiscussionPermission discussionPermission, Discussion discussion) {

		return _commentDisplayContextProvider.getCommentSectionDisplayContext(
			request, response, discussionPermission, discussion);
	}

	public static CommentTreeDisplayContext getCommentTreeDisplayContext(
		HttpServletRequest request, HttpServletResponse response,
		DiscussionPermission discussionPermission,
		DiscussionComment discussionComment) {

		return _commentDisplayContextProvider.getCommentTreeDisplayContext(
			request, response, discussionPermission, discussionComment);
	}

	private static final CommentDisplayContextProvider
		_commentDisplayContextProvider =
			new CommentDisplayContextProviderImpl();

}