<%--
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
--%>

<%@ include file="/message_boards/init.jsp" %>

<liferay-util:dynamic-include key="com.liferay.message.boards.web#/message_boards/view_thread_message.jsp#pre" />

<%
MBCategory category = (MBCategory)request.getAttribute("edit_message.jsp-category");
Boolean editable = (Boolean)request.getAttribute("edit_message.jsp-editable");
MBMessage message = (MBMessage)request.getAttribute("edit_message.jsp-message");
Boolean showDeletedAttachmentsFileEntries = (Boolean)request.getAttribute("edit-message.jsp-showDeletedAttachmentsFileEntries");
Boolean showPermanentLink = (Boolean)request.getAttribute("edit-message.jsp-showPermanentLink");
Boolean showRecentPosts = (Boolean)request.getAttribute("edit-message.jsp-showRecentPosts");
MBThread thread = (MBThread)request.getAttribute("edit_message.jsp-thread");
%>

<a id="<portlet:namespace />message_<%= message.getMessageId() %>"></a>

<div class="card list-group-card panel">
	<div class="panel-heading">
		<div class="card-row card-row-padded">
			<div class="card-col-field">
				<div class="list-group-card-icon">
					<liferay-ui:user-portrait cssClass="user-icon-lg" userId="<%= !message.isAnonymous() ? message.getUserId() : 0 %>" />
				</div>
			</div>

			<div class="card-col-content card-col-gutters">

				<%
				String messageUserName = "anonymous";

				if (!message.isAnonymous()) {
					messageUserName = message.getUserName();
				}

				Date modifiedDate = message.getModifiedDate();

				String modifiedDateDescription = LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - modifiedDate.getTime(), true);

				String userDisplayText = LanguageUtil.format(request, "x-modified-x-ago", new Object[] {messageUserName, modifiedDateDescription});
				%>

				<h5 class="message-user-display text-default" title="<%= userDisplayText %>">
					<%= userDisplayText %>
				</h5>

				<h4 title="<%= HtmlUtil.escape(message.getSubject()) %>">
					<c:choose>
						<c:when test="<%= showPermanentLink %>">
							<a href="#<portlet:namespace />message_<%= message.getMessageId() %>" title="<liferay-ui:message key="permanent-link-to-this-item" />">
								<%= HtmlUtil.escape(message.getSubject()) %>
							</a>
						</c:when>
						<c:otherwise>
							<%= HtmlUtil.escape(message.getSubject()) %>
						</c:otherwise>
					</c:choose>

					<c:if test="<%= message.isAnswer() %>">
						(<liferay-ui:message key="answer" />)
					</c:if>
				</h4>

				<%
				MBStatsUser statsUser = MBStatsUserLocalServiceUtil.getStatsUser(scopeGroupId, message.getUserId());

				int posts = statsUser.getMessageCount();
				String[] ranks = MBUtil.getUserRank(mbGroupServiceSettings, themeDisplay.getLanguageId(), statsUser);

				User messageUser = UserLocalServiceUtil.fetchUser(message.getUserId());
				%>

				<c:if test="<%= (messageUser != null) && !messageUser.isDefaultUser() %>">
					<c:if test="<%= Validator.isNotNull(ranks[1]) %>">
						<span class="h5 text-default" title="<%= HtmlUtil.escape(ranks[1]) %>">
							<%= HtmlUtil.escape(ranks[1]) %>
						</span>
					</c:if>

					<c:if test="<%= Validator.isNotNull(ranks[0]) %>">
						<span class="h5 text-default" title="<%= HtmlUtil.escape(ranks[0]) %>">
							<%= HtmlUtil.escape(ranks[0]) %>
						</span>
					</c:if>

					<span class="h5 text-default">
						<span><liferay-ui:message key="posts" />:</span> <%= posts %>
					</span>
					<span class="h5 text-default">
						<span><liferay-ui:message key="join-date" />:</span> <%= dateFormatDate.format(messageUser.getCreateDate()) %>
					</span>

					<c:if test="<%= showRecentPosts %>">
						<portlet:renderURL var="recentPostsURL">
							<portlet:param name="mvcRenderCommandName" value="/message_boards/view_recent_posts" />
							<portlet:param name="groupThreadsUserId" value="<%= String.valueOf(messageUser.getUserId()) %>" />
						</portlet:renderURL>

						<span class="h5">
							<liferay-ui:icon
								iconCssClass="icon-search"
								label="<%= true %>"
								message="recent-posts"
								method="get"
								url="<%= recentPostsURL.toString() %>"
							/>
						</span>
					</c:if>

					<c:if test="<%= !message.isApproved() %>">
						<span class="h5 text-default">
							<aui:workflow-status markupView="lexicon" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= message.getStatus() %>" />
						</span>
					</c:if>

					<c:if test="<%= (messageUser != null) && (user.getUserId() != messageUser.getUserId()) && !PortalUtil.isGroupAdmin(messageUser, scopeGroupId) && MBPermission.contains(permissionChecker, scopeGroupId, ActionKeys.BAN_USER) %>">
						<br />

						<c:choose>
							<c:when test="<%= MBBanLocalServiceUtil.hasBan(scopeGroupId, messageUser.getUserId()) %>">
								<portlet:actionURL name="/message_boards/ban_user" var="unbanUserURL">
									<portlet:param name="<%= Constants.CMD %>" value="unban" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="banUserId" value="<%= String.valueOf(messageUser.getUserId()) %>" />
								</portlet:actionURL>

								<liferay-ui:icon
									iconCssClass="icon-ok-sign"
									label="<%= true %>"
									message="unban-this-user"
									url="<%= unbanUserURL.toString() %>"
								/>
							</c:when>
							<c:otherwise>
								<portlet:actionURL name="/message_boards/ban_user" var="banUserURL">
									<portlet:param name="<%= Constants.CMD %>" value="ban" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="banUserId" value="<%= String.valueOf(messageUser.getUserId()) %>" />
								</portlet:actionURL>

								<liferay-ui:icon
									iconCssClass="icon-ban-circle"
									label="<%= true %>"
									message="ban-this-user"
									url="<%= banUserURL.toString() %>"
								/>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:if>

				<c:if test="<%= enableFlags || enableRatings %>">
					<div class="social-interaction">
						<c:if test="<%= enableRatings %>">
							<div>
								<liferay-ui:ratings
									className="<%= MBMessage.class.getName() %>"
									classPK="<%= message.getMessageId() %>"
								/>
							</div>
						</c:if>

						<c:if test="<%= enableFlags %>">
							<liferay-flags:flags
								className="<%= MBMessage.class.getName() %>"
								classPK="<%= message.getMessageId() %>"
								contentTitle="<%= message.getSubject() %>"
								reportedUserId="<%= message.getUserId() %>"
							/>
						</c:if>
					</div>
				</c:if>
			</div>

			<div class="card-col-field">
				<c:if test="<%= editable %>">

					<%
					boolean hasDeletePermission = !thread.isLocked() && (thread.getMessageCount() > 1) && MBMessagePermission.contains(permissionChecker, message, ActionKeys.DELETE);
					boolean hasMoveThreadPermission = (message.getParentMessageId() != MBMessageConstants.DEFAULT_PARENT_MESSAGE_ID) && MBCategoryPermission.contains(permissionChecker, scopeGroupId, category.getCategoryId(), ActionKeys.MOVE_THREAD);
					boolean hasPermissionsPermission = !thread.isLocked() && !message.isRoot() && MBMessagePermission.contains(permissionChecker, message, ActionKeys.PERMISSIONS);
					boolean hasReplyPermission = MBCategoryPermission.contains(permissionChecker, scopeGroupId, message.getCategoryId(), ActionKeys.REPLY_TO_MESSAGE);
					boolean hasUpdatePermission = !thread.isLocked() && MBMessagePermission.contains(permissionChecker, message, ActionKeys.UPDATE);

					boolean showAnswerFlag = false;

					if (!message.isRoot()) {
						MBMessage rootMessage = MBMessageLocalServiceUtil.getMessage(thread.getRootMessageId());

						showAnswerFlag = MBMessagePermission.contains(permissionChecker, rootMessage, ActionKeys.UPDATE) && (thread.isQuestion() || MBThreadLocalServiceUtil.hasAnswerMessage(thread.getThreadId()));
					}
					%>

					<c:if test="<%= showAnswerFlag || hasReplyPermission || hasUpdatePermission || hasPermissionsPermission || hasMoveThreadPermission || hasDeletePermission %>">
						<liferay-ui:icon-menu direction="left-side" icon="<%= StringPool.BLANK %>" markupView="lexicon" message="<%= StringPool.BLANK %>" showWhenSingleIcon="<%= true %>">
							<c:if test="<%= showAnswerFlag %>">
								<c:choose>
									<c:when test="<%= !message.isAnswer() %>">
										<portlet:actionURL name="/message_boards/edit_message" var="addAnswerURL">
											<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD_ANSWER %>" />
											<portlet:param name="redirect" value="<%= currentURL %>" />
											<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
										</portlet:actionURL>

										<liferay-ui:icon
											message="mark-as-an-answer"
											url="<%= addAnswerURL %>"
										/>
									</c:when>
									<c:otherwise>
										<portlet:actionURL name="/message_boards/edit_message" var="deleteAnswerURL">
											<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE_ANSWER %>" />
											<portlet:param name="redirect" value="<%= currentURL %>" />
											<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
										</portlet:actionURL>

										<liferay-ui:icon
											message="unmark-as-an-answer"
											url="<%= deleteAnswerURL %>"
										/>
									</c:otherwise>
								</c:choose>
							</c:if>

							<c:if test="<%= hasReplyPermission && !thread.isLocked() %>">
								<portlet:renderURL var="replyURL">
									<portlet:param name="mvcRenderCommandName" value="/message_boards/edit_message" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="mbCategoryId" value="<%= String.valueOf(message.getCategoryId()) %>" />
									<portlet:param name="threadId" value="<%= String.valueOf(message.getThreadId()) %>" />
									<portlet:param name="parentMessageId" value="<%= String.valueOf(message.getMessageId()) %>" />
									<portlet:param name="priority" value="<%= String.valueOf(message.getPriority()) %>" />
								</portlet:renderURL>

								<liferay-ui:icon
									message="reply"
									url="<%= replyURL %>"
								/>

								<portlet:renderURL var="quoteURL">
									<portlet:param name="mvcRenderCommandName" value="/message_boards/edit_message" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="mbCategoryId" value="<%= String.valueOf(message.getCategoryId()) %>" />
									<portlet:param name="threadId" value="<%= String.valueOf(message.getThreadId()) %>" />
									<portlet:param name="parentMessageId" value="<%= String.valueOf(message.getMessageId()) %>" />
									<portlet:param name="priority" value="<%= String.valueOf(message.getPriority()) %>" />
									<portlet:param name="quote" value="<%= Boolean.TRUE.toString() %>" />
								</portlet:renderURL>

								<liferay-ui:icon
									message="reply-with-quote"
									url="<%= quoteURL %>"
								/>

								<%
								String taglibQuickReplyURL = "javascript:" + liferayPortletResponse.getNamespace() + "addQuickReply('reply', '" + message.getMessageId() + "');";
								%>

								<liferay-ui:icon
									message="quick-reply"
									url="<%= taglibQuickReplyURL %>"
								/>
							</c:if>

							<c:if test="<%= hasUpdatePermission %>">
								<portlet:renderURL var="editURL">
									<portlet:param name="mvcRenderCommandName" value="/message_boards/edit_message" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
								</portlet:renderURL>

								<liferay-ui:icon
									message="edit"
									url="<%= editURL %>"
								/>
							</c:if>

							<c:if test="<%= hasPermissionsPermission %>">
								<liferay-security:permissionsURL
									modelResource="<%= MBMessage.class.getName() %>"
									modelResourceDescription="<%= HtmlUtil.escape(message.getSubject()) %>"
									resourcePrimKey="<%= String.valueOf(message.getMessageId()) %>"
									var="permissionsURL"
									windowState="<%= LiferayWindowState.POP_UP.toString() %>"
								/>

								<liferay-ui:icon
									message="permissions"
									method="get"
									url="<%= permissionsURL %>"
									useDialog="<%= true %>"
								/>
							</c:if>

							<c:if test="<%= hasMoveThreadPermission %>">
								<portlet:renderURL var="splitThreadURL">
									<portlet:param name="mvcRenderCommandName" value="/message_boards/split_thread" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
									<portlet:param name="splitThread" value="<%= Boolean.TRUE.toString() %>" />
								</portlet:renderURL>

								<liferay-ui:icon
									message="split-thread"
									url="<%= splitThreadURL %>"
								/>
							</c:if>

							<c:if test="<%= hasDeletePermission %>">

								<%
								PortletURL categoryURL = liferayPortletResponse.createRenderURL();

								if (message.getCategoryId() == MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {
									categoryURL.setParameter("mvcRenderCommandName", "/message_boards/view");
								}
								else {
									categoryURL.setParameter("mvcRenderCommandName", "/message_boards/view_category");
									categoryURL.setParameter("mbCategoryId", String.valueOf(message.getCategoryId()));
								}
								%>

								<portlet:actionURL name="/message_boards/edit_message" var="deleteURL">
									<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
									<portlet:param name="redirect" value="<%= categoryURL.toString() %>" />
									<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
								</portlet:actionURL>

								<liferay-ui:icon-delete
									label="<%= true %>"
									url="<%= deleteURL %>"
								/>
							</c:if>
						</liferay-ui:icon-menu>
					</c:if>
				</c:if>
			</div>
		</div>
	</div>

	<div class="divider"></div>

	<div class="panel-body">

		<%
		String msgBody = message.getBody();

		if (message.isFormatBBCode()) {
			msgBody = MBUtil.getBBCodeHTML(msgBody, themeDisplay.getPathThemeImages());
		}
		%>

		<div class="card-row card-row-padded message-content">
			<%= msgBody %>
		</div>

		<%
		String assetTagNames = (String)request.getAttribute("edit_message.jsp-assetTagNames");
		%>

		<div class="tags">
			<liferay-ui:asset-tags-summary
				assetTagNames="<%= assetTagNames %>"
				className="<%= MBMessage.class.getName() %>"
				classPK="<%= message.getMessageId() %>"
			/>
		</div>

		<liferay-ui:custom-attributes-available className="<%= MBMessage.class.getName() %>">
			<div class="custom-attributes">
				<liferay-ui:custom-attribute-list
					className="<%= MBMessage.class.getName() %>"
					classPK="<%= (message != null) ? message.getMessageId() : 0 %>"
					editable="<%= false %>"
					label="<%= true %>"
				/>
			</div>
		</liferay-ui:custom-attributes-available>

		<div class="entry-links">
			<liferay-ui:asset-links
				className="<%= MBMessage.class.getName() %>"
				classPK="<%= message.getMessageId() %>"
			/>
		</div>

		<c:if test="<%= message.getMessageId() > 0 %>">

			<%
			int attachmentsFileEntriesCount = message.getAttachmentsFileEntriesCount();
			int deletedAttachmentsFileEntriesCount = message.getDeletedAttachmentsFileEntriesCount();
			%>

			<c:if test="<%= (attachmentsFileEntriesCount > 0) || ((deletedAttachmentsFileEntriesCount > 0) && TrashUtil.isTrashEnabled(scopeGroupId) && MBMessagePermission.contains(permissionChecker, message, ActionKeys.UPDATE)) %>">
				<div class="message-attachments">
					<h3><liferay-ui:message key="attachments" />:</h3>

					<%
					List<FileEntry> attachmentsFileEntries = message.getAttachmentsFileEntries();

					for (FileEntry fileEntry : attachmentsFileEntries) {
						if (MimeTypesUtil.isWebImage(fileEntry.getMimeType())) {
					%>

							<p>
								<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="attachment" />" class="crop-img" src="<%= PortletFileRepositoryUtil.getPortletFileEntryURL(themeDisplay, fileEntry, StringPool.BLANK) %>" />
							</p>

					<%
						}
					}
					%>

					<ul>

						<%
						for (FileEntry fileEntry : attachmentsFileEntries) {
						%>

							<li class="message-attachment">

								<%
								StringBundler sb = new StringBundler(4);

								sb.append(fileEntry.getTitle());
								sb.append(StringPool.OPEN_PARENTHESIS);
								sb.append(TextFormatter.formatStorageSize(fileEntry.getSize(), locale));
								sb.append(StringPool.CLOSE_PARENTHESIS);

								AssetRendererFactory<?> assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(DLFileEntry.class.getName());

								AssetRenderer<?> assetRenderer = assetRendererFactory.getAssetRenderer(fileEntry.getFileEntryId());
								%>

								<liferay-ui:icon
									icon="<%= assetRenderer.getIconCssClass() %>"
									label="<%= true %>"
									markupView="lexicon"
									message="<%= sb.toString() %>"
									url="<%= PortletFileRepositoryUtil.getDownloadPortletFileEntryURL(themeDisplay, fileEntry, StringPool.BLANK) %>"
								/>
							</li>

						<%
						}
						%>

						<c:if test="<%= showDeletedAttachmentsFileEntries && (deletedAttachmentsFileEntriesCount > 0) && TrashUtil.isTrashEnabled(scopeGroupId) && MBMessagePermission.contains(permissionChecker, message, ActionKeys.UPDATE) %>">
							<li class="message-attachment">
								<portlet:renderURL var="viewTrashAttachmentsURL">
									<portlet:param name="mvcRenderCommandName" value="/message_boards/view_deleted_message_attachments" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
								</portlet:renderURL>

								<liferay-ui:icon
									iconCssClass="icon-paperclip"
									label="<%= true %>"
									message='<%= LanguageUtil.format(request, (deletedAttachmentsFileEntriesCount == 1) ? "x-recently-removed-attachment" : "x-recently-removed-attachments", deletedAttachmentsFileEntriesCount, false) %>'
									url="<%= viewTrashAttachmentsURL %>"
								/>
							</li>
						</c:if>
					</ul>
				</div>
			</c:if>
		</c:if>
	</div>
</div>

<liferay-util:dynamic-include key="com.liferay.message.boards.web#/message_boards/view_thread_message.jsp#post" />