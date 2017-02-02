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

<%@ include file="/admin/common/init.jsp" %>

<%
KBArticle kbArticle = (KBArticle)request.getAttribute(KBWebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

List<FileEntry> attachmentsFileEntries = new ArrayList<FileEntry>();

if (kbArticle != null) {
	attachmentsFileEntries = kbArticle.getAttachmentsFileEntries();
}
%>

<c:if test="<%= !attachmentsFileEntries.isEmpty() %>">
	<div class="kb-attachments">
		<h5><liferay-ui:message key="attachments" /></h5>

		<div class="row">

			<%
			DLMimeTypeDisplayContext dlMimeTypeDisplayContext = (DLMimeTypeDisplayContext)request.getAttribute(KBWebKeys.DL_MIME_TYPE_DISPLAY_CONTEXT);

			for (FileEntry fileEntry : attachmentsFileEntries) {
				String clipURL = DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), themeDisplay, StringPool.BLANK);
			%>

				<div class="col-md-4">
					<liferay-frontend:horizontal-card
						text="<%= HtmlUtil.escape(fileEntry.getTitle()) %>"
						url="<%= clipURL %>"
					>
						<liferay-frontend:horizontal-card-col>
							<span class="icon-monospaced sticker-lg <%= (dlMimeTypeDisplayContext != null) ? dlMimeTypeDisplayContext.getCssClassFileMimeType(fileEntry.getMimeType()) : "file-icon-color-0" %>"><%= StringUtil.shorten(StringUtil.upperCase(fileEntry.getExtension()), 3, StringPool.BLANK) %></span>
						</liferay-frontend:horizontal-card-col>
					</liferay-frontend:horizontal-card>
				</div>

			<%
			}
			%>

		</div>
	</div>
</c:if>