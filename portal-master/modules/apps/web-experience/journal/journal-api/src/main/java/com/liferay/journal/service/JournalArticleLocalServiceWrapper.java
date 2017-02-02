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

package com.liferay.journal.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link JournalArticleLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see JournalArticleLocalService
 * @generated
 */
@ProviderType
public class JournalArticleLocalServiceWrapper
	implements JournalArticleLocalService,
		ServiceWrapper<JournalArticleLocalService> {
	public JournalArticleLocalServiceWrapper(
		JournalArticleLocalService journalArticleLocalService) {
		_journalArticleLocalService = journalArticleLocalService;
	}

	/**
	* Returns <code>true</code> if the specified web content article exists.
	*
	* @param groupId the primary key of the group
	* @param articleId the primary key of the web content article
	* @return <code>true</code> if the specified web content article exists;
	<code>false</code> otherwise
	*/
	@Override
	public boolean hasArticle(long groupId, java.lang.String articleId) {
		return _journalArticleLocalService.hasArticle(groupId, articleId);
	}

	/**
	* Returns <code>true</code> if the web content article, specified by group
	* and article ID, is the latest version.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @return <code>true</code> if the specified web content article is the
	latest version; <code>false</code> otherwise
	*/
	@Override
	public boolean isLatestVersion(long groupId, java.lang.String articleId,
		double version)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.isLatestVersion(groupId, articleId,
			version);
	}

	/**
	* Returns <code>true</code> if the web content article, specified by group,
	* article ID, and workflow status, is the latest version.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @return <code>true</code> if the specified web content article is the
	latest version; <code>false</code> otherwise
	*/
	@Override
	public boolean isLatestVersion(long groupId, java.lang.String articleId,
		double version, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.isLatestVersion(groupId, articleId,
			version, status);
	}

	@Override
	public boolean isListable(com.liferay.journal.model.JournalArticle article) {
		return _journalArticleLocalService.isListable(article);
	}

	@Override
	public boolean isRenderable(
		com.liferay.journal.model.JournalArticle article,
		com.liferay.portal.kernel.portlet.PortletRequestModel portletRequestModel,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay) {
		return _journalArticleLocalService.isRenderable(article,
			portletRequestModel, themeDisplay);
	}

	/**
	* Adds a web content article.
	*
	* @param userId the primary key of the web content article's creator/owner
	* @param groupId the primary key of the web content article's group
	* @param folderId the primary key of the web content article folder
	* @param titleMap the web content article's locales and localized titles
	* @param descriptionMap the web content article's locales and localized
	descriptions
	* @param content the HTML content wrapped in XML. For more information,
	see the content example in the {@link #addArticle(long, long,
	long, long, long, String, boolean, double, Map, Map, String,
	String, String, String, int, int, int, int, int, int, int, int,
	int, int, boolean, int, int, int, int, int, boolean, boolean,
	boolean, String, File, Map, String, ServiceContext)} description.
	* @param ddmStructureKey the primary key of the web content article's DDM
	structure, if the article is related to a DDM structure, or
	<code>null</code> otherwise
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @param serviceContext the service context to be applied. Can set the
	UUID, creation date, modification date, expando bridge
	attributes, guest permissions, group permissions, asset category
	IDs, asset tag names, asset link entry IDs, asset priority, URL
	title, and workflow actions for the web content article. Can also
	set whether to add the default guest and group permissions.
	* @return the web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle addArticle(long userId,
		long groupId, long folderId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String content, java.lang.String ddmStructureKey,
		java.lang.String ddmTemplateKey,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.addArticle(userId, groupId,
			folderId, titleMap, descriptionMap, content, ddmStructureKey,
			ddmTemplateKey, serviceContext);
	}

	/**
	* Adds a web content article with additional parameters.
	*
	* <p>
	* The web content articles hold HTML content wrapped in XML. The XML lets
	* you specify the article's default locale and available locales. Here is a
	* content example:
	* </p>
	*
	* <p>
	* <pre>
	* <code>
	* &lt;?xml version='1.0' encoding='UTF-8'?&gt;
	* &lt;root default-locale="en_US" available-locales="en_US"&gt;
	*     &lt;static-content language-id="en_US"&gt;
	*         &lt;![CDATA[&lt;p&gt;&lt;b&gt;&lt;i&gt;test&lt;i&gt; content&lt;b&gt;&lt;/p&gt;]]&gt;
	*     &lt;/static-content&gt;
	* &lt;/root&gt;
	* </code>
	* </pre>
	* </p>
	*
	* @param userId the primary key of the web content article's creator/owner
	* @param groupId the primary key of the web content article's group
	* @param folderId the primary key of the web content article folder
	* @param classNameId the primary key of the DDMStructure class if the web
	content article is related to a DDM structure, the primary key of
	the class name associated with the article, or
	JournalArticleConstants.CLASSNAME_ID_DEFAULT in the journal-api
	module otherwise
	* @param classPK the primary key of the DDM structure, if the primary key
	of the DDMStructure class is given as the
	<code>classNameId</code> parameter, the primary key of the class
	associated with the web content article, or <code>0</code>
	otherwise
	* @param articleId the primary key of the web content article
	* @param autoArticleId whether to auto generate the web content article ID
	* @param version the web content article's version
	* @param titleMap the web content article's locales and localized titles
	* @param descriptionMap the web content article's locales and localized
	descriptions
	* @param content the HTML content wrapped in XML
	* @param ddmStructureKey the primary key of the web content article's DDM
	structure, if the article is related to a DDM structure, or
	<code>null</code> otherwise
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @param layoutUuid the unique string identifying the web content
	article's display page
	* @param displayDateMonth the month the web content article is set to
	display
	* @param displayDateDay the calendar day the web content article is set to
	display
	* @param displayDateYear the year the web content article is set to
	display
	* @param displayDateHour the hour the web content article is set to
	display
	* @param displayDateMinute the minute the web content article is set to
	display
	* @param expirationDateMonth the month the web content article is set to
	expire
	* @param expirationDateDay the calendar day the web content article is set
	to expire
	* @param expirationDateYear the year the web content article is set to
	expire
	* @param expirationDateHour the hour the web content article is set to
	expire
	* @param expirationDateMinute the minute the web content article is set to
	expire
	* @param neverExpire whether the web content article is not set to auto
	expire
	* @param reviewDateMonth the month the web content article is set for
	review
	* @param reviewDateDay the calendar day the web content article is set for
	review
	* @param reviewDateYear the year the web content article is set for review
	* @param reviewDateHour the hour the web content article is set for review
	* @param reviewDateMinute the minute the web content article is set for
	review
	* @param neverReview whether the web content article is not set for review
	* @param indexable whether the web content article is searchable
	* @param smallImage whether the web content article has a small image
	* @param smallImageURL the web content article's small image URL
	* @param smallImageFile the web content article's small image file
	* @param images the web content's images
	* @param articleURL the web content article's accessible URL
	* @param serviceContext the service context to be applied. Can set the
	UUID, creation date, modification date, expando bridge
	attributes, guest permissions, group permissions, asset category
	IDs, asset tag names, asset link entry IDs, URL title, and
	workflow actions for the web content article. Can also set
	whether to add the default guest and group permissions.
	* @return the web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle addArticle(long userId,
		long groupId, long folderId, long classNameId, long classPK,
		java.lang.String articleId, boolean autoArticleId, double version,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String content, java.lang.String ddmStructureKey,
		java.lang.String ddmTemplateKey, java.lang.String layoutUuid,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire, int reviewDateMonth,
		int reviewDateDay, int reviewDateYear, int reviewDateHour,
		int reviewDateMinute, boolean neverReview, boolean indexable,
		boolean smallImage, java.lang.String smallImageURL,
		java.io.File smallImageFile,
		java.util.Map<java.lang.String, byte[]> images,
		java.lang.String articleURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.addArticle(userId, groupId,
			folderId, classNameId, classPK, articleId, autoArticleId, version,
			titleMap, descriptionMap, content, ddmStructureKey, ddmTemplateKey,
			layoutUuid, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, reviewDateMonth, reviewDateDay,
			reviewDateYear, reviewDateHour, reviewDateMinute, neverReview,
			indexable, smallImage, smallImageURL, smallImageFile, images,
			articleURL, serviceContext);
	}

	/**
	* Adds the journal article to the database. Also notifies the appropriate model listeners.
	*
	* @param journalArticle the journal article
	* @return the journal article that was added
	*/
	@Override
	public com.liferay.journal.model.JournalArticle addJournalArticle(
		com.liferay.journal.model.JournalArticle journalArticle) {
		return _journalArticleLocalService.addJournalArticle(journalArticle);
	}

	/**
	* Returns the web content article with the group, article ID, and version.
	* This method checks for the article's resource primary key and, if not
	* found, creates a new one.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @return the matching web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle checkArticleResourcePrimKey(
		long groupId, java.lang.String articleId, double version)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.checkArticleResourcePrimKey(groupId,
			articleId, version);
	}

	/**
	* Copies the web content article matching the group, article ID, and
	* version. This method creates a new article, extracting all the values
	* from the old one and updating its article ID.
	*
	* @param userId the primary key of the web content article's creator/owner
	* @param groupId the primary key of the web content article's group
	* @param oldArticleId the primary key of the old web content article
	* @param newArticleId the primary key of the new web content article
	* @param autoArticleId whether to auto-generate the web content article ID
	* @param version the web content article's version
	* @return the new web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle copyArticle(long userId,
		long groupId, java.lang.String oldArticleId,
		java.lang.String newArticleId, boolean autoArticleId, double version)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.copyArticle(userId, groupId,
			oldArticleId, newArticleId, autoArticleId, version);
	}

	/**
	* Creates a new journal article with the primary key. Does not add the journal article to the database.
	*
	* @param id the primary key for the new journal article
	* @return the new journal article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle createJournalArticle(
		long id) {
		return _journalArticleLocalService.createJournalArticle(id);
	}

	/**
	* Deletes the web content article and its resources.
	*
	* @param article the web content article
	* @return the deleted web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle deleteArticle(
		com.liferay.journal.model.JournalArticle article)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.deleteArticle(article);
	}

	/**
	* Deletes the web content article and its resources, optionally sending
	* email notifying denial of the article if it had not yet been approved.
	*
	* @param article the web content article
	* @param articleURL the web content article's accessible URL to include in
	email notifications (optionally <code>null</code>)
	* @param serviceContext the service context to be applied (optionally
	<code>null</code>). Can set the portlet preferences that include
	email information to notify recipients of the unapproved web
	content's denial.
	* @return the deleted web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle deleteArticle(
		com.liferay.journal.model.JournalArticle article,
		java.lang.String articleURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.deleteArticle(article, articleURL,
			serviceContext);
	}

	/**
	* Deletes the web content article and its resources matching the group,
	* article ID, and version, optionally sending email notifying denial of the
	* web content article if it had not yet been approved.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param articleURL the web content article's accessible URL
	* @param serviceContext the service context to be applied. Can set the
	portlet preferences that include email information to notify
	recipients of the unapproved web content article's denial.
	* @return the deleted web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle deleteArticle(
		long groupId, java.lang.String articleId, double version,
		java.lang.String articleURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.deleteArticle(groupId, articleId,
			version, articleURL, serviceContext);
	}

	/**
	* Deletes the journal article from the database. Also notifies the appropriate model listeners.
	*
	* @param journalArticle the journal article
	* @return the journal article that was removed
	*/
	@Override
	public com.liferay.journal.model.JournalArticle deleteJournalArticle(
		com.liferay.journal.model.JournalArticle journalArticle) {
		return _journalArticleLocalService.deleteJournalArticle(journalArticle);
	}

	/**
	* Deletes the journal article with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the journal article
	* @return the journal article that was removed
	* @throws PortalException if a journal article with the primary key could not be found
	*/
	@Override
	public com.liferay.journal.model.JournalArticle deleteJournalArticle(
		long id) throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.deleteJournalArticle(id);
	}

	/**
	* Expires the web content article matching the group, article ID, and
	* version.
	*
	* @param userId the primary key of the user updating the web content
	article
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param articleURL the web content article's accessible URL
	* @param serviceContext the service context to be applied. Can set the
	modification date, status date, portlet preferences, and can set
	whether to add the default command update for the web content
	article. With respect to social activities, by setting the
	service context's command to {@link Constants#UPDATE}, the
	invocation is considered a web content update activity; otherwise
	it is considered a web content add activity.
	* @return the web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle expireArticle(long userId,
		long groupId, java.lang.String articleId, double version,
		java.lang.String articleURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.expireArticle(userId, groupId,
			articleId, version, articleURL, serviceContext);
	}

	@Override
	public com.liferay.journal.model.JournalArticle fetchArticle(long groupId,
		java.lang.String articleId) {
		return _journalArticleLocalService.fetchArticle(groupId, articleId);
	}

	/**
	* Returns the web content article matching the group, article ID, and
	* version.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @return the web content article matching the group, article ID, and
	version, or <code>null</code> if no web content article could be
	found
	*/
	@Override
	public com.liferay.journal.model.JournalArticle fetchArticle(long groupId,
		java.lang.String articleId, double version) {
		return _journalArticleLocalService.fetchArticle(groupId, articleId,
			version);
	}

	@Override
	public com.liferay.journal.model.JournalArticle fetchArticleByUrlTitle(
		long groupId, java.lang.String urlTitle) {
		return _journalArticleLocalService.fetchArticleByUrlTitle(groupId,
			urlTitle);
	}

	@Override
	public com.liferay.journal.model.JournalArticle fetchDisplayArticle(
		long groupId, java.lang.String articleId) {
		return _journalArticleLocalService.fetchDisplayArticle(groupId,
			articleId);
	}

	@Override
	public com.liferay.journal.model.JournalArticle fetchJournalArticle(long id) {
		return _journalArticleLocalService.fetchJournalArticle(id);
	}

	/**
	* Returns the journal article matching the UUID and group.
	*
	* @param uuid the journal article's UUID
	* @param groupId the primary key of the group
	* @return the matching journal article, or <code>null</code> if a matching journal article could not be found
	*/
	@Override
	public com.liferay.journal.model.JournalArticle fetchJournalArticleByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _journalArticleLocalService.fetchJournalArticleByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the latest web content article matching the group, article ID,
	* and workflow status.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @return the latest matching web content article, or <code>null</code> if
	no matching web content article could be found
	*/
	@Override
	public com.liferay.journal.model.JournalArticle fetchLatestArticle(
		long groupId, java.lang.String articleId, int status) {
		return _journalArticleLocalService.fetchLatestArticle(groupId,
			articleId, status);
	}

	@Override
	public com.liferay.journal.model.JournalArticle fetchLatestArticle(
		long resourcePrimKey) {
		return _journalArticleLocalService.fetchLatestArticle(resourcePrimKey);
	}

	@Override
	public com.liferay.journal.model.JournalArticle fetchLatestArticle(
		long resourcePrimKey, int status) {
		return _journalArticleLocalService.fetchLatestArticle(resourcePrimKey,
			status);
	}

	/**
	* Returns the latest web content article matching the resource primary key
	* and workflow status, optionally preferring articles with approved
	* workflow status.
	*
	* @param resourcePrimKey the primary key of the resource instance
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param preferApproved whether to prefer returning the latest matching
	article that has workflow status {@link
	WorkflowConstants#STATUS_APPROVED} over returning one that has a
	different status
	* @return the latest web content article matching the resource primary key
	and workflow status, optionally preferring articles with an
	approved workflow status, or <code>null</code> if no matching web
	content article could be found
	*/
	@Override
	public com.liferay.journal.model.JournalArticle fetchLatestArticle(
		long resourcePrimKey, int status, boolean preferApproved) {
		return _journalArticleLocalService.fetchLatestArticle(resourcePrimKey,
			status, preferApproved);
	}

	@Override
	public com.liferay.journal.model.JournalArticle fetchLatestArticle(
		long resourcePrimKey, int[] statuses) {
		return _journalArticleLocalService.fetchLatestArticle(resourcePrimKey,
			statuses);
	}

	@Override
	public com.liferay.journal.model.JournalArticle fetchLatestArticleByUrlTitle(
		long groupId, java.lang.String urlTitle, int status) {
		return _journalArticleLocalService.fetchLatestArticleByUrlTitle(groupId,
			urlTitle, status);
	}

	/**
	* Returns the latest indexable web content article matching the resource
	* primary key.
	*
	* @param resourcePrimKey the primary key of the resource instance
	* @return the latest indexable web content article matching the resource
	primary key, or <code>null</code> if no matching web content
	article could be found
	*/
	@Override
	public com.liferay.journal.model.JournalArticle fetchLatestIndexableArticle(
		long resourcePrimKey) {
		return _journalArticleLocalService.fetchLatestIndexableArticle(resourcePrimKey);
	}

	/**
	* Returns the latest approved web content article, or the latest unapproved
	* article if none are approved. Both approved and unapproved articles must
	* match the group and article ID.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @return the matching web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle getArticle(long groupId,
		java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getArticle(groupId, articleId);
	}

	/**
	* Returns the web content article matching the group, article ID, and
	* version.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @return the matching web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle getArticle(long groupId,
		java.lang.String articleId, double version)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getArticle(groupId, articleId,
			version);
	}

	/**
	* Returns the web content article matching the group, class name, and class
	* PK.
	*
	* @param groupId the primary key of the web content article's group
	* @param className the DDMStructure class name if the web content article
	is related to a DDM structure, the primary key of the class name
	associated with the article, or
	JournalArticleConstants.CLASSNAME_ID_DEFAULT in the journal-api
	module otherwise
	* @param classPK the primary key of the DDM structure, if the DDMStructure
	class name is given as the <code>className</code> parameter, the
	primary key of the class associated with the web content article,
	or <code>0</code> otherwise
	* @return the matching web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle getArticle(long groupId,
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getArticle(groupId, className,
			classPK);
	}

	/**
	* Returns the web content article with the ID.
	*
	* @param id the primary key of the web content article
	* @return the web content article with the ID
	*/
	@Override
	public com.liferay.journal.model.JournalArticle getArticle(long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getArticle(id);
	}

	/**
	* Returns the latest web content article that is approved, or the latest
	* unapproved article if none are approved. Both approved and unapproved
	* articles must match the group and URL title.
	*
	* @param groupId the primary key of the web content article's group
	* @param urlTitle the web content article's accessible URL title
	* @return the matching web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle getArticleByUrlTitle(
		long groupId, java.lang.String urlTitle)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getArticleByUrlTitle(groupId,
			urlTitle);
	}

	/**
	* Returns the matching web content article currently displayed or next to
	* be displayed if no article is currently displayed.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @return the matching web content article currently displayed, or the next
	one to be displayed if no version of the article is currently
	displayed
	*/
	@Override
	public com.liferay.journal.model.JournalArticle getDisplayArticle(
		long groupId, java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getDisplayArticle(groupId, articleId);
	}

	/**
	* Returns the web content article matching the URL title that is currently
	* displayed or next to be displayed if no article is currently displayed.
	*
	* @param groupId the primary key of the web content article's group
	* @param urlTitle the web content article's accessible URL title
	* @return the web content article matching the URL title that is currently
	displayed, or next one to be displayed if no version of the
	article is currently displayed
	*/
	@Override
	public com.liferay.journal.model.JournalArticle getDisplayArticleByUrlTitle(
		long groupId, java.lang.String urlTitle)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getDisplayArticleByUrlTitle(groupId,
			urlTitle);
	}

	/**
	* Returns the journal article with the primary key.
	*
	* @param id the primary key of the journal article
	* @return the journal article
	* @throws PortalException if a journal article with the primary key could not be found
	*/
	@Override
	public com.liferay.journal.model.JournalArticle getJournalArticle(long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getJournalArticle(id);
	}

	/**
	* Returns the journal article matching the UUID and group.
	*
	* @param uuid the journal article's UUID
	* @param groupId the primary key of the group
	* @return the matching journal article
	* @throws PortalException if a matching journal article could not be found
	*/
	@Override
	public com.liferay.journal.model.JournalArticle getJournalArticleByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getJournalArticleByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the latest web content article with the group and article ID.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @return the latest matching web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle getLatestArticle(
		long groupId, java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getLatestArticle(groupId, articleId);
	}

	/**
	* Returns the latest web content article matching the group, article ID,
	* and workflow status.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @return the latest matching web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle getLatestArticle(
		long groupId, java.lang.String articleId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getLatestArticle(groupId, articleId,
			status);
	}

	/**
	* Returns the latest web content article matching the group, class name ID,
	* and class PK.
	*
	* @param groupId the primary key of the web content article's group
	* @param className the DDMStructure class name if the web content article
	is related to a DDM structure, the class name associated with the
	article, or JournalArticleConstants.CLASSNAME_ID_DEFAULT in the
	journal-api module otherwise
	* @param classPK the primary key of the DDM structure, if the DDMStructure
	class name is given as the <code>className</code> parameter, the
	primary key of the class associated with the web content article,
	or <code>0</code> otherwise
	* @return the latest matching web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle getLatestArticle(
		long groupId, java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getLatestArticle(groupId, className,
			classPK);
	}

	/**
	* Returns the latest web content article matching the resource primary key,
	* preferring articles with approved workflow status.
	*
	* @param resourcePrimKey the primary key of the resource instance
	* @return the latest web content article matching the resource primary key,
	preferring articles with approved workflow status
	*/
	@Override
	public com.liferay.journal.model.JournalArticle getLatestArticle(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getLatestArticle(resourcePrimKey);
	}

	/**
	* Returns the latest web content article matching the resource primary key
	* and workflow status, preferring articles with approved workflow status.
	*
	* @param resourcePrimKey the primary key of the resource instance
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @return the latest web content article matching the resource primary key
	and workflow status, preferring articles with approved workflow
	status
	*/
	@Override
	public com.liferay.journal.model.JournalArticle getLatestArticle(
		long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getLatestArticle(resourcePrimKey,
			status);
	}

	/**
	* Returns the latest web content article matching the resource primary key
	* and workflow status, optionally preferring articles with approved
	* workflow status.
	*
	* @param resourcePrimKey the primary key of the resource instance
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param preferApproved whether to prefer returning the latest matching
	article that has workflow status {@link
	WorkflowConstants#STATUS_APPROVED} over returning one that has a
	different status
	* @return the latest web content article matching the resource primary key
	and workflow status, optionally preferring articles with approved
	workflow status
	*/
	@Override
	public com.liferay.journal.model.JournalArticle getLatestArticle(
		long resourcePrimKey, int status, boolean preferApproved)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getLatestArticle(resourcePrimKey,
			status, preferApproved);
	}

	/**
	* Returns the latest web content article matching the group, URL title, and
	* workflow status.
	*
	* @param groupId the primary key of the web content article's group
	* @param urlTitle the web content article's accessible URL title
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @return the latest matching web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle getLatestArticleByUrlTitle(
		long groupId, java.lang.String urlTitle, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getLatestArticleByUrlTitle(groupId,
			urlTitle, status);
	}

	/**
	* Returns the oldest web content article with the group and article ID.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @return the oldest matching web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle getOldestArticle(
		long groupId, java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getOldestArticle(groupId, articleId);
	}

	/**
	* Returns the oldest web content article matching the group, article ID,
	* and workflow status.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @return the oldest matching web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle getOldestArticle(
		long groupId, java.lang.String articleId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getOldestArticle(groupId, articleId,
			status);
	}

	/**
	* Returns the previously approved version of the web content article. For
	* more information on the approved workflow status, see {@link
	* WorkflowConstants#STATUS_APPROVED}.
	*
	* @param article the web content article
	* @return the previously approved version of the web content article, or
	the current web content article if there are no previously
	approved web content articles
	*/
	@Override
	public com.liferay.journal.model.JournalArticle getPreviousApprovedArticle(
		com.liferay.journal.model.JournalArticle article) {
		return _journalArticleLocalService.getPreviousApprovedArticle(article);
	}

	/**
	* Moves the web content article matching the group and article ID to a new
	* folder.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param newFolderId the primary key of the web content article's new
	folder
	* @return the updated web content article, which was moved to a new
	folder
	* @deprecated As of 7.0.0, replaced by {@link #moveArticle(long, String,
	long, ServiceContext)}
	*/
	@Deprecated
	@Override
	public com.liferay.journal.model.JournalArticle moveArticle(long groupId,
		java.lang.String articleId, long newFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.moveArticle(groupId, articleId,
			newFolderId);
	}

	/**
	* Moves the web content article matching the group and article ID to a new
	* folder.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param newFolderId the primary key of the web content article's new
	folder
	* @param serviceContext the service context to be applied. Can set the
	user ID, language ID, portlet preferences, portlet request,
	portlet response, theme display, and can set whether to add the
	default command update for the web content article. With respect
	to social activities, by setting the service context's command to
	{@link Constants#UPDATE}, the invocation is considered a web
	content update activity; otherwise it is considered a web content
	add activity.
	* @return the updated web content article, which was moved to a new folder
	*/
	@Override
	public com.liferay.journal.model.JournalArticle moveArticle(long groupId,
		java.lang.String articleId, long newFolderId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.moveArticle(groupId, articleId,
			newFolderId, serviceContext);
	}

	/**
	* Moves the web content article from the Recycle Bin to a new folder.
	*
	* @param userId the primary key of the user updating the web content
	article
	* @param groupId the primary key of the web content article's group
	* @param article the web content article
	* @param newFolderId the primary key of the web content article's new
	folder
	* @param serviceContext the service context to be applied. Can set the
	modification date, portlet preferences, and can set whether to
	add the default command update for the web content article. With
	respect to social activities, by setting the service context's
	command to {@link Constants#UPDATE}, the invocation is considered
	a web content update activity; otherwise it is considered a web
	content add activity.
	* @return the updated web content article, which was moved from the Recycle
	Bin to a new folder
	*/
	@Override
	public com.liferay.journal.model.JournalArticle moveArticleFromTrash(
		long userId, long groupId,
		com.liferay.journal.model.JournalArticle article, long newFolderId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.moveArticleFromTrash(userId,
			groupId, article, newFolderId, serviceContext);
	}

	/**
	* Moves the latest version of the web content article matching the group
	* and article ID to the recycle bin.
	*
	* @param userId the primary key of the user updating the web content
	article
	* @param article the web content article
	* @return the updated web content article, which was moved to the Recycle
	Bin
	*/
	@Override
	public com.liferay.journal.model.JournalArticle moveArticleToTrash(
		long userId, com.liferay.journal.model.JournalArticle article)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.moveArticleToTrash(userId, article);
	}

	/**
	* Moves the latest version of the web content article matching the group
	* and article ID to the recycle bin.
	*
	* @param userId the primary key of the user updating the web content
	article
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @return the moved web content article or <code>null</code> if no matching
	article was found
	*/
	@Override
	public com.liferay.journal.model.JournalArticle moveArticleToTrash(
		long userId, long groupId, java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.moveArticleToTrash(userId, groupId,
			articleId);
	}

	/**
	* Removes the web content of the web content article matching the group,
	* article ID, and version, and language.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param languageId the primary key of the language locale to remove
	* @return the updated web content article with the locale removed
	*/
	@Override
	public com.liferay.journal.model.JournalArticle removeArticleLocale(
		long groupId, java.lang.String articleId, double version,
		java.lang.String languageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.removeArticleLocale(groupId,
			articleId, version, languageId);
	}

	/**
	* Restores the web content article from the Recycle Bin.
	*
	* @param userId the primary key of the user restoring the web content
	article
	* @param article the web content article
	* @return the restored web content article from the Recycle Bin
	*/
	@Override
	public com.liferay.journal.model.JournalArticle restoreArticleFromTrash(
		long userId, com.liferay.journal.model.JournalArticle article)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.restoreArticleFromTrash(userId,
			article);
	}

	/**
	* Updates the web content article matching the version, replacing its
	* folder and content.
	*
	* @param userId the primary key of the user updating the web content
	article
	* @param groupId the primary key of the web content article's group
	* @param folderId the primary key of the web content article folder
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param content the HTML content wrapped in XML. For more information,
	see the content example in the {@link #addArticle(long, long,
	long, long, long, String, boolean, double, Map, Map, String,
	String, String, String, int, int, int, int, int, int, int, int,
	int, int, boolean, int, int, int, int, int, boolean, boolean,
	boolean, String, File, Map, String, ServiceContext)} description.
	* @param serviceContext the service context to be applied. Can set the
	modification date, expando bridge attributes, asset category IDs,
	asset tag names, asset link entry IDs, asset priority, workflow
	actions, URL title, and can set whether to add the default
	command update for the web content article. With respect to
	social activities, by setting the service context's command to
	{@link Constants#UPDATE}, the invocation is considered a web
	content update activity; otherwise it is considered a web content
	add activity.
	* @return the updated web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle updateArticle(long userId,
		long groupId, long folderId, java.lang.String articleId,
		double version, java.lang.String content,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.updateArticle(userId, groupId,
			folderId, articleId, version, content, serviceContext);
	}

	/**
	* Updates the web content article with additional parameters.
	*
	* @param userId the primary key of the user updating the web content
	article
	* @param groupId the primary key of the web content article's group
	* @param folderId the primary key of the web content article folder
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param titleMap the web content article's locales and localized titles
	* @param descriptionMap the web content article's locales and localized
	descriptions
	* @param content the HTML content wrapped in XML. For more information,
	see the content example in the {@link #addArticle(long, long,
	long, long, long, String, boolean, double, Map, Map, String,
	String, String, String, int, int, int, int, int, int, int, int,
	int, int, boolean, int, int, int, int, int, boolean, boolean,
	boolean, String, File, Map, String, ServiceContext)} description.
	* @param ddmStructureKey the primary key of the web content article's DDM
	structure, if the article is related to a DDM structure, or
	<code>null</code> otherwise
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @param layoutUuid the unique string identifying the web content
	article's display page
	* @param displayDateMonth the month the web content article is set to
	display
	* @param displayDateDay the calendar day the web content article is set to
	display
	* @param displayDateYear the year the web content article is set to
	display
	* @param displayDateHour the hour the web content article is set to
	display
	* @param displayDateMinute the minute the web content article is set to
	display
	* @param expirationDateMonth the month the web content article is set to
	expire
	* @param expirationDateDay the calendar day the web content article is set
	to expire
	* @param expirationDateYear the year the web content article is set to
	expire
	* @param expirationDateHour the hour the web content article is set to
	expire
	* @param expirationDateMinute the minute the web content article is set to
	expire
	* @param neverExpire whether the web content article is not set to auto
	expire
	* @param reviewDateMonth the month the web content article is set for
	review
	* @param reviewDateDay the calendar day the web content article is set for
	review
	* @param reviewDateYear the year the web content article is set for review
	* @param reviewDateHour the hour the web content article is set for review
	* @param reviewDateMinute the minute the web content article is set for
	review
	* @param neverReview whether the web content article is not set for review
	* @param indexable whether the web content is searchable
	* @param smallImage whether to update web content article's a small image.
	A file must be passed in as <code>smallImageFile</code> value,
	otherwise the current small image is deleted.
	* @param smallImageURL the web content article's small image URL
	(optionally <code>null</code>)
	* @param smallImageFile the web content article's new small image file
	(optionally <code>null</code>). Must pass in
	<code>smallImage</code> value of <code>true</code> to replace the
	article's small image file.
	* @param images the web content's images (optionally <code>null</code>)
	* @param articleURL the web content article's accessible URL (optionally
	<code>null</code>)
	* @param serviceContext the service context to be applied. Can set the
	modification date, expando bridge attributes, asset category IDs,
	asset tag names, asset link entry IDs, asset priority, workflow
	actions, URL title , and can set whether to add the default
	command update for the web content article. With respect to
	social activities, by setting the service context's command to
	{@link Constants#UPDATE}, the invocation is considered a web
	content update activity; otherwise it is considered a web content
	add activity.
	* @return the updated web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle updateArticle(long userId,
		long groupId, long folderId, java.lang.String articleId,
		double version,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String content, java.lang.String ddmStructureKey,
		java.lang.String ddmTemplateKey, java.lang.String layoutUuid,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire, int reviewDateMonth,
		int reviewDateDay, int reviewDateYear, int reviewDateHour,
		int reviewDateMinute, boolean neverReview, boolean indexable,
		boolean smallImage, java.lang.String smallImageURL,
		java.io.File smallImageFile,
		java.util.Map<java.lang.String, byte[]> images,
		java.lang.String articleURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.updateArticle(userId, groupId,
			folderId, articleId, version, titleMap, descriptionMap, content,
			ddmStructureKey, ddmTemplateKey, layoutUuid, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, reviewDateMonth, reviewDateDay, reviewDateYear,
			reviewDateHour, reviewDateMinute, neverReview, indexable,
			smallImage, smallImageURL, smallImageFile, images, articleURL,
			serviceContext);
	}

	/**
	* Updates the web content article matching the version, replacing its
	* folder, title, description, content, and layout UUID.
	*
	* @param userId the primary key of the user updating the web content
	article
	* @param groupId the primary key of the web content article's group
	* @param folderId the primary key of the web content article folder
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param titleMap the web content article's locales and localized titles
	* @param descriptionMap the web content article's locales and localized
	descriptions
	* @param content the HTML content wrapped in XML. For more information,
	see the content example in the {@link #addArticle(long, long,
	long, long, long, String, boolean, double, Map, Map, String,
	String, String, String, int, int, int, int, int, int, int, int,
	int, int, boolean, int, int, int, int, int, boolean, boolean,
	boolean, String, File, Map, String, ServiceContext)} description.
	* @param layoutUuid the unique string identifying the web content
	article's display page
	* @param serviceContext the service context to be applied. Can set the
	modification date, expando bridge attributes, asset category IDs,
	asset tag names, asset link entry IDs, asset priority, workflow
	actions, URL title, and can set whether to add the default
	command update for the web content article. With respect to
	social activities, by setting the service context's command to
	{@link Constants#UPDATE}, the invocation is considered a web
	content update activity; otherwise it is considered a web content
	add activity.
	* @return the updated web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle updateArticle(long userId,
		long groupId, long folderId, java.lang.String articleId,
		double version,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String content, java.lang.String layoutUuid,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.updateArticle(userId, groupId,
			folderId, articleId, version, titleMap, descriptionMap, content,
			layoutUuid, serviceContext);
	}

	/**
	* Updates the translation of the web content article.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param locale the locale of the web content article's display template
	* @param title the translated web content article title
	* @param description the translated web content article description
	* @param content the HTML content wrapped in XML. For more information,
	see the content example in the {@link #addArticle(long, long,
	long, long, long, String, boolean, double, Map, Map, String,
	String, String, String, int, int, int, int, int, int, int, int,
	int, int, boolean, int, int, int, int, int, boolean, boolean,
	boolean, String, File, Map, String, ServiceContext)} description.
	* @param images the web content's images
	* @param serviceContext the service context to be applied. Can set the
	modification date and URL title for the web content article.
	* @return the updated web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle updateArticleTranslation(
		long groupId, java.lang.String articleId, double version,
		java.util.Locale locale, java.lang.String title,
		java.lang.String description, java.lang.String content,
		java.util.Map<java.lang.String, byte[]> images,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.updateArticleTranslation(groupId,
			articleId, version, locale, title, description, content, images,
			serviceContext);
	}

	/**
	* Updates the web content article matching the group, article ID, and
	* version, replacing its content.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param content the HTML content wrapped in XML. For more information,
	see the content example in the {@link #addArticle(long, long,
	long, long, long, String, boolean, double, Map, Map, String,
	String, String, String, int, int, int, int, int, int, int, int,
	int, int, boolean, int, int, int, int, int, boolean, boolean,
	boolean, String, File, Map, String, ServiceContext)} description.
	* @return the updated web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle updateContent(
		long groupId, java.lang.String articleId, double version,
		java.lang.String content)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.updateContent(groupId, articleId,
			version, content);
	}

	/**
	* Updates the journal article in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param journalArticle the journal article
	* @return the journal article that was updated
	*/
	@Override
	public com.liferay.journal.model.JournalArticle updateJournalArticle(
		com.liferay.journal.model.JournalArticle journalArticle) {
		return _journalArticleLocalService.updateJournalArticle(journalArticle);
	}

	/**
	* Updates the workflow status of the web content article.
	*
	* @param userId the primary key of the user updating the web content
	article's status
	* @param article the web content article
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param articleURL the web content article's accessible URL
	* @param serviceContext the service context to be applied. Can set the
	modification date, status date, and portlet preferences. With
	respect to social activities, by setting the service context's
	command to {@link Constants#UPDATE}, the invocation is considered
	a web content update activity; otherwise it is considered a web
	content add activity.
	* @param workflowContext the web content article's configured workflow
	context
	* @return the updated web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle updateStatus(long userId,
		com.liferay.journal.model.JournalArticle article, int status,
		java.lang.String articleURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.updateStatus(userId, article,
			status, articleURL, serviceContext, workflowContext);
	}

	/**
	* Updates the workflow status of the web content article matching the class
	* PK.
	*
	* @param userId the primary key of the user updating the web content
	article's status
	* @param classPK the primary key of the DDM structure, if the web content
	article is related to a DDM structure, the primary key of the
	class associated with the article, or <code>0</code> otherwise
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param workflowContext the web content article's configured workflow
	* @param serviceContext the service context to be applied. Can set the
	modification date, portlet preferences, and can set whether to
	add the default command update for the web content article.
	* @return the updated web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle updateStatus(long userId,
		long classPK, int status,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.updateStatus(userId, classPK,
			status, workflowContext, serviceContext);
	}

	/**
	* Updates the workflow status of the web content article matching the
	* group, article ID, and version.
	*
	* @param userId the primary key of the user updating the web content
	article's status
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param articleURL the web content article's accessible URL
	* @param workflowContext the web content article's configured workflow
	* @param serviceContext the service context to be applied. Can set the
	modification date, portlet preferences, and can set whether to
	add the default command update for the web content article.
	* @return the updated web content article
	*/
	@Override
	public com.liferay.journal.model.JournalArticle updateStatus(long userId,
		long groupId, java.lang.String articleId, double version, int status,
		java.lang.String articleURL,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.updateStatus(userId, groupId,
			articleId, version, status, articleURL, workflowContext,
			serviceContext);
	}

	/**
	* Returns a web content article display for the specified page of the
	* latest version of the web content article, based on the DDM template. Web
	* content transformation tokens are added using the portlet request model
	* and theme display.
	*
	* @param article the primary key of the web content article
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @param viewMode the mode in which the web content is being viewed
	* @param languageId the primary key of the language translation to get
	* @param page the web content article page to display
	* @param portletRequestModel the portlet request model
	* @param themeDisplay the theme display
	* @return the web content article display, or <code>null</code> if the
	article has expired or if article's display date/time is after
	the current date/time
	*/
	@Override
	public com.liferay.journal.model.JournalArticleDisplay getArticleDisplay(
		com.liferay.journal.model.JournalArticle article,
		java.lang.String ddmTemplateKey, java.lang.String viewMode,
		java.lang.String languageId, int page,
		com.liferay.portal.kernel.portlet.PortletRequestModel portletRequestModel,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getArticleDisplay(article,
			ddmTemplateKey, viewMode, languageId, page, portletRequestModel,
			themeDisplay);
	}

	/**
	* Returns a web content article display for the first page of the specified
	* version of the web content article matching the group, article ID, and
	* DDM template. Web content transformation tokens are added from the theme
	* display (if not <code>null</code>).
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @param viewMode the mode in which the web content is being viewed
	* @param languageId the primary key of the language translation to get
	* @param themeDisplay the theme display
	* @return the web content article display, or <code>null</code> if the
	article has expired or if article's display date/time is after
	the current date/time
	*/
	@Override
	public com.liferay.journal.model.JournalArticleDisplay getArticleDisplay(
		long groupId, java.lang.String articleId, double version,
		java.lang.String ddmTemplateKey, java.lang.String viewMode,
		java.lang.String languageId,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getArticleDisplay(groupId,
			articleId, version, ddmTemplateKey, viewMode, languageId,
			themeDisplay);
	}

	/**
	* Returns a web content article display for the specified page of the
	* specified version of the web content article matching the group, article
	* ID, and DDM template. Web content transformation tokens are added using
	* the portlet request model and theme display.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @param viewMode the mode in which the web content is being viewed
	* @param languageId the primary key of the language translation to get
	* @param page the web content article page to display
	* @param portletRequestModel the portlet request model
	* @param themeDisplay the theme display
	* @return the web content article display, or <code>null</code> if the
	article has expired or if article's display date/time is after
	the current date/time
	*/
	@Override
	public com.liferay.journal.model.JournalArticleDisplay getArticleDisplay(
		long groupId, java.lang.String articleId, double version,
		java.lang.String ddmTemplateKey, java.lang.String viewMode,
		java.lang.String languageId, int page,
		com.liferay.portal.kernel.portlet.PortletRequestModel portletRequestModel,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getArticleDisplay(groupId,
			articleId, version, ddmTemplateKey, viewMode, languageId, page,
			portletRequestModel, themeDisplay);
	}

	/**
	* Returns a web content article display for the first page of the latest
	* version of the web content article matching the group, article ID, and
	* DDM template. Web content transformation tokens are added from the theme
	* display (if not <code>null</code>).
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @param viewMode the mode in which the web content is being viewed
	* @param languageId the primary key of the language translation to get
	* @param themeDisplay the theme display
	* @return the web content article display, or <code>null</code> if the
	article has expired or if article's display date/time is after
	the current date/time
	*/
	@Override
	public com.liferay.journal.model.JournalArticleDisplay getArticleDisplay(
		long groupId, java.lang.String articleId,
		java.lang.String ddmTemplateKey, java.lang.String viewMode,
		java.lang.String languageId,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getArticleDisplay(groupId,
			articleId, ddmTemplateKey, viewMode, languageId, themeDisplay);
	}

	/**
	* Returns a web content article display for the specified page of the
	* latest version of the web content article matching the group, article ID,
	* and DDM template. Web content transformation tokens are added using the
	* portlet request model and theme display.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @param viewMode the mode in which the web content is being viewed
	* @param languageId the primary key of the language translation to get
	* @param page the web content article page to display
	* @param portletRequestModel the portlet request model
	* @param themeDisplay the theme display
	* @return the web content article display, or <code>null</code> if the
	article has expired or if article's display date/time is after
	the current date/time
	*/
	@Override
	public com.liferay.journal.model.JournalArticleDisplay getArticleDisplay(
		long groupId, java.lang.String articleId,
		java.lang.String ddmTemplateKey, java.lang.String viewMode,
		java.lang.String languageId, int page,
		com.liferay.portal.kernel.portlet.PortletRequestModel portletRequestModel,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getArticleDisplay(groupId,
			articleId, ddmTemplateKey, viewMode, languageId, page,
			portletRequestModel, themeDisplay);
	}

	/**
	* Returns a web content article display for the first page of the latest
	* version of the web content article matching the group and article ID. Web
	* content transformation tokens are added from the theme display (if not
	* <code>null</code>).
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param viewMode the mode in which the web content is being viewed
	* @param languageId the primary key of the language translation to get
	* @param themeDisplay the theme display
	* @return the web content article display, or <code>null</code> if the
	article has expired or if article's display date/time is after
	the current date/time
	*/
	@Override
	public com.liferay.journal.model.JournalArticleDisplay getArticleDisplay(
		long groupId, java.lang.String articleId, java.lang.String viewMode,
		java.lang.String languageId,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getArticleDisplay(groupId,
			articleId, viewMode, languageId, themeDisplay);
	}

	/**
	* Returns a web content article display for the specified page of the
	* latest version of the web content article matching the group and article
	* ID. Web content transformation tokens are added from the theme display
	* (if not <code>null</code>).
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param viewMode the mode in which the web content is being viewed
	* @param languageId the primary key of the language translation to get
	* @param page the web content article page to display
	* @param portletRequestModel the portlet request model
	* @param themeDisplay the theme display
	* @return the web content article display, or <code>null</code> if the
	article has expired or if article's display date/time is after
	the current date/time
	*/
	@Override
	public com.liferay.journal.model.JournalArticleDisplay getArticleDisplay(
		long groupId, java.lang.String articleId, java.lang.String viewMode,
		java.lang.String languageId, int page,
		com.liferay.portal.kernel.portlet.PortletRequestModel portletRequestModel,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getArticleDisplay(groupId,
			articleId, viewMode, languageId, page, portletRequestModel,
			themeDisplay);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _journalArticleLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _journalArticleLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _journalArticleLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _journalArticleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a {@link BaseModelSearchResult} containing the total number of
	* hits and an ordered range of all the web content articles matching the
	* parameters using the indexer, including keyword parameters for article
	* ID, title, description, or content, a DDM structure key parameter, a DDM
	* template key parameter, an AND operator switch, and parameters for type,
	* status, and a finder hash map. It is preferable to use this method
	* instead of the non-indexed version whenever possible for performance
	* reasons.
	*
	* <p>
	* The <code>start</code> and <code>end</code> parameters only affect the
	* amount of web content articles returned as results, not the total number
	* of hits.
	* </p>
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the web content article's company
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param folderIds the primary keys of the web content article folders
	(optionally {@link Collections#EMPTY_LIST})
	* @param classNameId the primary key of the DDMStructure class, the
	primary key of the class name associated with the article, or
	JournalArticleConstants.CLASSNAME_ID_DEFAULT in the journal-api
	module otherwise
	* @param articleId the article ID keywords (space separated, optionally
	<code>null</code>)
	* @param title the title keywords (space separated, optionally
	<code>null</code>)
	* @param description the description keywords (space separated, optionally
	<code>null</code>)
	* @param content the content keywords (space separated, optionally
	<code>null</code>)
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param ddmStructureKey the primary key of the web content article's DDM
	structure
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @param params the finder parameters (optionally <code>null</code>). The
	<code>includeDiscussions</code> parameter can be set to
	<code>true</code> to search for the keywords in the web content
	article discussions.
	* @param andSearch whether every field must match its value or keywords,
	or just one field must match
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @param sort the field, type, and direction by which to sort (optionally
	<code>null</code>)
	* @return a {@link BaseModelSearchResult} containing the total number of
	hits and an ordered range of all the matching web content
	articles ordered by <code>sort</code>
	*/
	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.journal.model.JournalArticle> searchJournalArticles(
		long companyId, long groupId, java.util.List<java.lang.Long> folderIds,
		long classNameId, java.lang.String articleId, java.lang.String title,
		java.lang.String description, java.lang.String content, int status,
		java.lang.String ddmStructureKey, java.lang.String ddmTemplateKey,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.searchJournalArticles(companyId,
			groupId, folderIds, classNameId, articleId, title, description,
			content, status, ddmStructureKey, ddmTemplateKey, params,
			andSearch, start, end, sort);
	}

	/**
	* Returns a {@link BaseModelSearchResult} containing the total number of
	* hits and an ordered range of all the web content articles matching the
	* parameters using the indexer, including a keywords parameter for matching
	* an article's ID, title, description, or content, a DDM structure key
	* parameter, a DDM template key parameter, and a finder hash map parameter.
	* It is preferable to use this method instead of the non-indexed version
	* whenever possible for performance reasons.
	*
	* <p>
	* The <code>start</code> and <code>end</code> parameters only affect the
	* amount of web content articles returned as results, not the total number
	* of hits.
	* </p>
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the web content article's company
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param folderIds the primary keys of the web content article folders
	(optionally {@link Collections#EMPTY_LIST})
	* @param classNameId the primary key of the DDMStructure class, the
	primary key of the class name associated with the article, or
	JournalArticleConstants.CLASSNAME_ID_DEFAULT in the journal-api
	module otherwise
	* @param ddmStructureKey the primary key of the web content article's DDM
	structure
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @param keywords the keywords (space separated), which may occur in the
	web content article ID, title, description, or content
	(optionally <code>null</code>). If the keywords value is not
	<code>null</code>, the search uses the OR operator in connecting
	query criteria; otherwise it uses the AND operator.
	* @param params the finder parameters (optionally <code>null</code>)
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @param sort the field, type, and direction by which to sort (optionally
	<code>null</code>)
	* @return a {@link BaseModelSearchResult} containing the total number of
	hits and an ordered range of all the matching web content
	articles ordered by <code>sort</code>
	*/
	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.journal.model.JournalArticle> searchJournalArticles(
		long companyId, long groupId, java.util.List<java.lang.Long> folderIds,
		long classNameId, java.lang.String ddmStructureKey,
		java.lang.String ddmTemplateKey, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.searchJournalArticles(companyId,
			groupId, folderIds, classNameId, ddmStructureKey, ddmTemplateKey,
			keywords, params, start, end, sort);
	}

	/**
	* Returns a {@link BaseModelSearchResult} containing the total number of
	* hits and an ordered range of all the web content articles matching the
	* parameters using the indexer, including the web content article's creator
	* ID and status. It is preferable to use this method instead of the
	* non-indexed version whenever possible for performance reasons.
	*
	* <p>
	* The <code>start</code> and <code>end</code> parameters only affect the
	* amount of web content articles returned as results, not the total number
	* of hits.
	* </p>
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param userId the primary key of the user searching for web content
	articles
	* @param creatorUserId the primary key of the web content article's
	creator
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @return a {@link BaseModelSearchResult} containing the total number of
	hits and an ordered range of all the matching web content
	articles ordered by <code>sort</code>
	*/
	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.journal.model.JournalArticle> searchJournalArticles(
		long groupId, long userId, long creatorUserId, int status, int start,
		int end) throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.searchJournalArticles(groupId,
			userId, creatorUserId, status, start, end);
	}

	/**
	* Returns an ordered range of all the web content articles matching the
	* parameters using the indexer, including a keywords parameter for matching
	* an article's ID, title, description, or content, a DDM structure key
	* parameter, a DDM template key parameter, an AND operator switch, and
	* parameters for type, status, a finder hash map. It is preferable to use
	* this method instead of the non-indexed version whenever possible for
	* performance reasons.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the web content article's company
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param folderIds the primary keys of the web content article folders
	(optionally {@link Collections#EMPTY_LIST})
	* @param classNameId the primary key of the DDMStructure class if the web
	content article is related to a DDM structure, the primary key of
	the class name associated with the article, or
	JournalArticleConstants.CLASSNAME_ID_DEFAULT in the journal-api
	module otherwise
	* @param articleId the article ID keywords (space separated, optionally
	<code>null</code>)
	* @param title the title keywords (space separated, optionally
	<code>null</code>)
	* @param description the description keywords (space separated, optionally
	<code>null</code>)
	* @param content the content keywords (space separated, optionally
	<code>null</code>)
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param ddmStructureKey the primary key of the web content article's DDM
	structure, if the article is related to a DDM structure, or
	<code>null</code> otherwise
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @param params the finder parameters (optionally <code>null</code>). The
	<code>includeDiscussions</code> parameter can be set to
	<code>true</code> to search for the keywords in the web content
	article discussions.
	* @param andSearch whether every field must match its value or keywords,
	or just one field must match
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @param sort the field, type, and direction by which to sort (optionally
	<code>null</code>)
	* @return the matching web content articles ordered by <code>sort</code>
	*/
	@Override
	public com.liferay.portal.kernel.search.Hits search(long companyId,
		long groupId, java.util.List<java.lang.Long> folderIds,
		long classNameId, java.lang.String articleId, java.lang.String title,
		java.lang.String description, java.lang.String content, int status,
		java.lang.String ddmStructureKey, java.lang.String ddmTemplateKey,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.search.Sort sort) {
		return _journalArticleLocalService.search(companyId, groupId,
			folderIds, classNameId, articleId, title, description, content,
			status, ddmStructureKey, ddmTemplateKey, params, andSearch, start,
			end, sort);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #search(long, long, List,
	long, String, String, String, String, int, String, String,
	LinkedHashMap, boolean, int, int, Sort)}
	*/
	@Deprecated
	@Override
	public com.liferay.portal.kernel.search.Hits search(long companyId,
		long groupId, java.util.List<java.lang.Long> folderIds,
		long classNameId, java.lang.String articleId, java.lang.String title,
		java.lang.String description, java.lang.String content,
		java.lang.String type, java.lang.String statusString,
		java.lang.String ddmStructureKey, java.lang.String ddmTemplateKey,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.search.Sort sort) {
		return _journalArticleLocalService.search(companyId, groupId,
			folderIds, classNameId, articleId, title, description, content,
			type, statusString, ddmStructureKey, ddmTemplateKey, params,
			andSearch, start, end, sort);
	}

	/**
	* Returns an ordered range of all the web content articles matching the
	* parameters using the indexer, including a keywords parameter for matching
	* an article's ID, title, description, or content, a DDM structure key
	* parameter, a DDM template key parameter, and a finder hash map parameter.
	* It is preferable to use this method instead of the non-indexed version
	* whenever possible for performance reasons.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the web content article's company
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param folderIds the primary keys of the web content article folders
	(optionally {@link Collections#EMPTY_LIST})
	* @param classNameId the primary key of the DDMStructure class if the web
	content article is related to a DDM structure, the primary key of
	the class name associated with the article, or
	JournalArticleConstants.CLASSNAME_ID_DEFAULT in the journal-api
	module otherwise
	* @param ddmStructureKey the primary key of the web content article's DDM
	structure, if the article is related to a DDM structure, or
	<code>null</code> otherwise
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @param keywords the keywords (space separated), which may occur in the
	web content article ID, title, description, or content
	(optionally <code>null</code>). If the keywords value is not
	<code>null</code>, the search uses the OR operator in connecting
	query criteria; otherwise it uses the AND operator.
	* @param params the finder parameters (optionally <code>null</code>)
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @param sort the field, type, and direction by which to sort (optionally
	<code>null</code>)
	* @return the matching web content articles ordered by <code>sort</code>
	*/
	@Override
	public com.liferay.portal.kernel.search.Hits search(long companyId,
		long groupId, java.util.List<java.lang.Long> folderIds,
		long classNameId, java.lang.String ddmStructureKey,
		java.lang.String ddmTemplateKey, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.search.Sort sort) {
		return _journalArticleLocalService.search(companyId, groupId,
			folderIds, classNameId, ddmStructureKey, ddmTemplateKey, keywords,
			params, start, end, sort);
	}

	/**
	* Returns a range of all the web content articles matching the group,
	* creator, and status using the indexer. It is preferable to use this
	* method instead of the non-indexed version whenever possible for
	* performance reasons.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param userId the primary key of the user searching for web content
	articles
	* @param creatorUserId the primary key of the web content article's
	creator
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @return the matching web content articles
	*/
	@Override
	public com.liferay.portal.kernel.search.Hits search(long groupId,
		long userId, long creatorUserId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.search(groupId, userId,
			creatorUserId, status, start, end);
	}

	/**
	* Returns the latest version number of the web content with the group and
	* article ID.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @return the latest version number of the matching web content
	*/
	@Override
	public double getLatestVersion(long groupId, java.lang.String articleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getLatestVersion(groupId, articleId);
	}

	/**
	* Returns the latest version number of the web content with the group,
	* article ID, and workflow status.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @return the latest version number of the matching web content
	*/
	@Override
	public double getLatestVersion(long groupId, java.lang.String articleId,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getLatestVersion(groupId, articleId,
			status);
	}

	/**
	* Returns the number of web content articles belonging to the group.
	*
	* @param groupId the primary key of the web content article's group
	* @return the number of web content articles belonging to the group
	*/
	@Override
	public int getArticlesCount(long groupId) {
		return _journalArticleLocalService.getArticlesCount(groupId);
	}

	@Override
	public int getArticlesCount(long groupId, java.lang.String articleId) {
		return _journalArticleLocalService.getArticlesCount(groupId, articleId);
	}

	/**
	* Returns the number of web content articles matching the group and folder.
	*
	* @param groupId the primary key of the web content article's group
	* @param folderId the primary key of the web content article's folder
	* @return the number of matching web content articles
	*/
	@Override
	public int getArticlesCount(long groupId, long folderId) {
		return _journalArticleLocalService.getArticlesCount(groupId, folderId);
	}

	/**
	* Returns the number of web content articles matching the group, folder,
	* and status.
	*
	* @param groupId the primary key of the web content article's group
	* @param folderId the primary key of the web content article's folder
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @return the number of matching web content articles
	*/
	@Override
	public int getArticlesCount(long groupId, long folderId, int status) {
		return _journalArticleLocalService.getArticlesCount(groupId, folderId,
			status);
	}

	/**
	* Returns the number of web content articles matching the company, version,
	* and workflow status.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the web content article's company
	* @param version the web content article's version
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @return the number of matching web content articles
	*/
	@Override
	public int getCompanyArticlesCount(long companyId, double version,
		int status, int start, int end) {
		return _journalArticleLocalService.getCompanyArticlesCount(companyId,
			version, status, start, end);
	}

	/**
	* Returns the number of web content articles matching the company and
	* workflow status.
	*
	* @param companyId the primary key of the web content article's company
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @return the number of matching web content articles
	*/
	@Override
	public int getCompanyArticlesCount(long companyId, int status) {
		return _journalArticleLocalService.getCompanyArticlesCount(companyId,
			status);
	}

	/**
	* Returns the number of journal articles.
	*
	* @return the number of journal articles
	*/
	@Override
	public int getJournalArticlesCount() {
		return _journalArticleLocalService.getJournalArticlesCount();
	}

	/**
	* Returns the number of web content articles that are not recycled.
	*
	* @param groupId the primary key of the web content article's group
	* @param folderId the primary key of the web content article folder
	* @return the number of web content articles that are not recycled
	*/
	@Override
	public int getNotInTrashArticlesCount(long groupId, long folderId) {
		return _journalArticleLocalService.getNotInTrashArticlesCount(groupId,
			folderId);
	}

	/**
	* Returns the number of web content articles matching the group and DDM
	* structure key.
	*
	* @param groupId the primary key of the web content article's group
	* @param ddmStructureKey the primary key of the web content article's DDM
	structure
	* @return the number of matching web content articles
	*/
	@Override
	public int getStructureArticlesCount(long groupId,
		java.lang.String ddmStructureKey) {
		return _journalArticleLocalService.getStructureArticlesCount(groupId,
			ddmStructureKey);
	}

	/**
	* Returns the number of web content articles matching the group and DDM
	* template key.
	*
	* @param groupId the primary key of the web content article's group
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @return the number of matching web content articles
	*/
	@Override
	public int getTemplateArticlesCount(long groupId,
		java.lang.String ddmTemplateKey) {
		return _journalArticleLocalService.getTemplateArticlesCount(groupId,
			ddmTemplateKey);
	}

	/**
	* Returns the number of web content articles matching the parameters,
	* including keyword parameters for article ID, title, description, and
	* content, a DDM structure key parameter, a DDM template key parameter, and
	* an AND operator switch.
	*
	* @param companyId the primary key of the web content article's company
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param folderIds the primary keys of the web content article folders
	(optionally {@link Collections#EMPTY_LIST})
	* @param classNameId the primary key of the DDMStructure class if the web
	content article is related to a DDM structure, the primary key of
	the class name associated with the article, or
	JournalArticleConstants.CLASSNAME_ID_DEFAULT in the journal-api
	module otherwise
	* @param articleId the article ID keywords (space separated, optionally
	<code>null</code>)
	* @param version the web content article's version (optionally
	<code>null</code>)
	* @param title the title keywords (space separated, optionally
	<code>null</code>)
	* @param description the description keywords (space separated, optionally
	<code>null</code>)
	* @param content the content keywords (space separated, optionally
	<code>null</code>)
	* @param ddmStructureKey the primary key of the web content article's DDM
	structure, if the article is related to a DDM structure, or
	<code>null</code> otherwise
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @param displayDateGT the date after which a matching web content
	article's display date must be after (optionally
	<code>null</code>)
	* @param displayDateLT the date before which a matching web content
	article's display date must be before (optionally
	<code>null</code>)
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param reviewDate the web content article's scheduled review date
	(optionally <code>null</code>)
	* @param andOperator whether every field must match its value or keywords,
	or just one field must match. Group, folder IDs, class name ID,
	and status must all match their values.
	* @return the number of matching web content articles
	*/
	@Override
	public int searchCount(long companyId, long groupId,
		java.util.List<java.lang.Long> folderIds, long classNameId,
		java.lang.String articleId, java.lang.Double version,
		java.lang.String title, java.lang.String description,
		java.lang.String content, java.lang.String ddmStructureKey,
		java.lang.String ddmTemplateKey, java.util.Date displayDateGT,
		java.util.Date displayDateLT, int status, java.util.Date reviewDate,
		boolean andOperator) {
		return _journalArticleLocalService.searchCount(companyId, groupId,
			folderIds, classNameId, articleId, version, title, description,
			content, ddmStructureKey, ddmTemplateKey, displayDateGT,
			displayDateLT, status, reviewDate, andOperator);
	}

	/**
	* Returns the number of web content articles matching the parameters,
	* including keyword parameters for article ID, title, description, and
	* content, a DDM structure keys (plural) parameter, a DDM template keys
	* (plural) parameter, and an AND operator switch.
	*
	* @param companyId the primary key of the web content article's company
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param folderIds the primary keys of the web content article folders
	(optionally {@link Collections#EMPTY_LIST})
	* @param classNameId the primary key of the DDMStructure class if the web
	content article is related to a DDM structure, the primary key of
	the class name associated with the article, or
	JournalArticleConstants.CLASSNAME_ID_DEFAULT in the journal-api
	module otherwise
	* @param articleId the article ID keywords (space separated, optionally
	<code>null</code>)
	* @param version the web content article's version (optionally
	<code>null</code>)
	* @param title the title keywords (space separated, optionally
	<code>null</code>)
	* @param description the description keywords (space separated, optionally
	<code>null</code>)
	* @param content the content keywords (space separated, optionally
	<code>null</code>)
	* @param ddmStructureKeys the primary keys of the web content article's
	DDM structures, if the article is related to a DDM structure, or
	<code>null</code> otherwise
	* @param ddmTemplateKeys the primary keys of the web content article's DDM
	templates (originally <code>null</code>). If the articles are
	related to a DDM structure, the template's structure must match
	it.
	* @param displayDateGT the date after which a matching web content
	article's display date must be after (optionally
	<code>null</code>)
	* @param displayDateLT the date before which a matching web content
	article's display date must be before (optionally
	<code>null</code>)
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param reviewDate the web content article's scheduled review date
	(optionally <code>null</code>)
	* @param andOperator whether every field must match its value or keywords,
	or just one field must match.  Group, folder IDs, class name ID,
	and status must all match their values.
	* @return the number of matching web content articles
	*/
	@Override
	public int searchCount(long companyId, long groupId,
		java.util.List<java.lang.Long> folderIds, long classNameId,
		java.lang.String articleId, java.lang.Double version,
		java.lang.String title, java.lang.String description,
		java.lang.String content, java.lang.String[] ddmStructureKeys,
		java.lang.String[] ddmTemplateKeys, java.util.Date displayDateGT,
		java.util.Date displayDateLT, int status, java.util.Date reviewDate,
		boolean andOperator) {
		return _journalArticleLocalService.searchCount(companyId, groupId,
			folderIds, classNameId, articleId, version, title, description,
			content, ddmStructureKeys, ddmTemplateKeys, displayDateGT,
			displayDateLT, status, reviewDate, andOperator);
	}

	/**
	* Returns the number of web content articles matching the parameters,
	* including a keywords parameter for matching with the article's ID, title,
	* description, and content, a DDM structure key parameter, and a DDM
	* template key parameter.
	*
	* @param companyId the primary key of the web content article's company
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param folderIds the primary keys of the web content article folders
	(optionally {@link Collections#EMPTY_LIST})
	* @param classNameId the primary key of the DDMStructure class if the web
	content article is related to a DDM structure, the primary key of
	the class name associated with the article, or
	JournalArticleConstants.CLASSNAME_ID_DEFAULT in the journal-api
	module otherwise
	* @param keywords the keywords (space separated), which may occur in the
	web content article ID, title, description, or content
	(optionally <code>null</code>). If the keywords value is not
	<code>null</code>, the search uses the OR operator in connecting
	query criteria; otherwise it uses the AND operator.
	* @param version the web content article's version (optionally
	<code>null</code>)
	* @param ddmStructureKey the primary key of the web content article's DDM
	structure, if the article is related to a DDM structure, or
	<code>null</code> otherwise
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @param displayDateGT the date after which a matching web content
	article's display date must be after (optionally
	<code>null</code>)
	* @param displayDateLT the date before which a matching web content
	article's display date must be before (optionally
	<code>null</code>)
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param reviewDate the web content article's scheduled review date
	(optionally <code>null</code>)
	* @return the number of matching web content articles
	*/
	@Override
	public int searchCount(long companyId, long groupId,
		java.util.List<java.lang.Long> folderIds, long classNameId,
		java.lang.String keywords, java.lang.Double version,
		java.lang.String ddmStructureKey, java.lang.String ddmTemplateKey,
		java.util.Date displayDateGT, java.util.Date displayDateLT, int status,
		java.util.Date reviewDate) {
		return _journalArticleLocalService.searchCount(companyId, groupId,
			folderIds, classNameId, keywords, version, ddmStructureKey,
			ddmTemplateKey, displayDateGT, displayDateLT, status, reviewDate);
	}

	/**
	* Returns the number of web content articles matching the group, folders,
	* and status.
	*
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param folderIds the primary keys of the web content article folders
	(optionally {@link Collections#EMPTY_LIST})
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @return the number of matching web content articles
	*/
	@Override
	public int searchCount(long groupId,
		java.util.List<java.lang.Long> folderIds, int status) {
		return _journalArticleLocalService.searchCount(groupId, folderIds,
			status);
	}

	/**
	* Returns the number of web content articles matching the group, folder,
	* and status.
	*
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param folderId the primary key of the web content article folder
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @return the number of matching web content articles
	*/
	@Override
	public int searchCount(long groupId, long folderId, int status) {
		return _journalArticleLocalService.searchCount(groupId, folderId, status);
	}

	/**
	* Returns the web content from the web content article associated with the
	* portlet request model and the DDM template.
	*
	* @param article the web content article
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @param viewMode the mode in which the web content is being viewed
	* @param languageId the primary key of the language translation to get
	* @param portletRequestModel the portlet request model
	* @param themeDisplay the theme display
	* @return the web content from the web content article associated with the
	portlet request model and the DDM template
	*/
	@Override
	public java.lang.String getArticleContent(
		com.liferay.journal.model.JournalArticle article,
		java.lang.String ddmTemplateKey, java.lang.String viewMode,
		java.lang.String languageId,
		com.liferay.portal.kernel.portlet.PortletRequestModel portletRequestModel,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getArticleContent(article,
			ddmTemplateKey, viewMode, languageId, portletRequestModel,
			themeDisplay);
	}

	/**
	* Returns the web content from the web content article associated with the
	* DDM template.
	*
	* @param article the web content article
	* @param ddmTemplateKey the primary key of the web content article's
	DDM template
	* @param viewMode the mode in which the web content is being viewed
	* @param languageId the primary key of the language translation to get
	* @param themeDisplay the theme display
	* @return the web content from the matching web content article
	* @deprecated As of 7.0.0, replaced by {@link
	#getArticleContent(JournalArticle, String, String, String,
	PortletRequestModel,ThemeDisplay)}
	*/
	@Deprecated
	@Override
	public java.lang.String getArticleContent(
		com.liferay.journal.model.JournalArticle article,
		java.lang.String ddmTemplateKey, java.lang.String viewMode,
		java.lang.String languageId,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getArticleContent(article,
			ddmTemplateKey, viewMode, languageId, themeDisplay);
	}

	/**
	* Returns the web content from the web content article matching the group,
	* article ID, and version, and associated with the portlet request model
	* and the DDM template.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param viewMode the mode in which the web content is being viewed
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @param languageId the primary key of the language translation to get
	* @param portletRequestModel the portlet request model
	* @param themeDisplay the theme display
	* @return the web content from the matching web content article
	*/
	@Override
	public java.lang.String getArticleContent(long groupId,
		java.lang.String articleId, double version, java.lang.String viewMode,
		java.lang.String ddmTemplateKey, java.lang.String languageId,
		com.liferay.portal.kernel.portlet.PortletRequestModel portletRequestModel,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getArticleContent(groupId,
			articleId, version, viewMode, ddmTemplateKey, languageId,
			portletRequestModel, themeDisplay);
	}

	/**
	* Returns the web content from the web content article matching the group,
	* article ID, and version, and associated with the DDM template.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param viewMode the mode in which the web content is being viewed
	* @param ddmTemplateKey the primary key of the web content article's
	DDM template (optionally <code>null</code>). If the article
	is related to a DDM structure, the template's structure must
	match it.
	* @param languageId the primary key of the language translation to get
	* @param themeDisplay the theme display
	* @return the web content from the matching web content article
	* @deprecated As of 7.0.0, replaced by {@link #getArticleContent(long,
	String, double, String, String, String, PortletRequestModel,
	ThemeDisplay)}
	*/
	@Deprecated
	@Override
	public java.lang.String getArticleContent(long groupId,
		java.lang.String articleId, double version, java.lang.String viewMode,
		java.lang.String ddmTemplateKey, java.lang.String languageId,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getArticleContent(groupId,
			articleId, version, viewMode, ddmTemplateKey, languageId,
			themeDisplay);
	}

	/**
	* Returns the web content from the web content article matching the group,
	* article ID, and version.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param viewMode the mode in which the web content is being viewed
	* @param languageId the primary key of the language translation to get
	* @param themeDisplay the theme display
	* @return the web content from the matching web content article
	* @deprecated As of 7.0.0, replaced by {@link #getArticleContent(long,
	String, double, String, String, String, PortletRequestModel,
	ThemeDisplay)}
	*/
	@Deprecated
	@Override
	public java.lang.String getArticleContent(long groupId,
		java.lang.String articleId, double version, java.lang.String viewMode,
		java.lang.String languageId,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getArticleContent(groupId,
			articleId, version, viewMode, languageId, themeDisplay);
	}

	/**
	* Returns the latest web content from the web content article matching the
	* group and article ID, and associated with the portlet request model and
	* the DDM template.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param viewMode the mode in which the web content is being viewed
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @param languageId the primary key of the language translation to get
	* @param portletRequestModel the portlet request model
	* @param themeDisplay the theme display
	* @return the latest web content from the matching web content article
	*/
	@Override
	public java.lang.String getArticleContent(long groupId,
		java.lang.String articleId, java.lang.String viewMode,
		java.lang.String ddmTemplateKey, java.lang.String languageId,
		com.liferay.portal.kernel.portlet.PortletRequestModel portletRequestModel,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getArticleContent(groupId,
			articleId, viewMode, ddmTemplateKey, languageId,
			portletRequestModel, themeDisplay);
	}

	/**
	* Returns the latest web content from the web content article matching the
	* group and article ID, and associated with the DDM template.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param viewMode the mode in which the web content is being viewed
	* @param ddmTemplateKey the primary key of the web content article's
	DDM template
	* @param languageId the primary key of the language translation to get
	* @param themeDisplay the theme display
	* @return the latest web content from the matching web content article
	* @deprecated As of 7.0.0, replaced by {@link #getArticleContent(long,
	String, String, String, String, PortletRequestModel,
	ThemeDisplay)}
	*/
	@Deprecated
	@Override
	public java.lang.String getArticleContent(long groupId,
		java.lang.String articleId, java.lang.String viewMode,
		java.lang.String ddmTemplateKey, java.lang.String languageId,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getArticleContent(groupId,
			articleId, viewMode, ddmTemplateKey, languageId, themeDisplay);
	}

	/**
	* Returns the latest web content from the web content article matching the
	* group and article ID.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param viewMode the mode in which the web content is being viewed
	* @param languageId the primary key of the language translation to get
	* @param themeDisplay the theme display
	* @return the latest web content from the matching web content article
	* @deprecated As of 7.0.0, replaced by {@link #getArticleContent(long,
	String, String, String, String, PortletRequestModel,
	ThemeDisplay)}
	*/
	@Deprecated
	@Override
	public java.lang.String getArticleContent(long groupId,
		java.lang.String articleId, java.lang.String viewMode,
		java.lang.String languageId,
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getArticleContent(groupId,
			articleId, viewMode, languageId, themeDisplay);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _journalArticleLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Returns the web content article's unique URL title.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param urlTitle the web content article's accessible URL title
	* @return the web content article's unique URL title
	*/
	@Override
	public java.lang.String getUniqueUrlTitle(long groupId,
		java.lang.String articleId, java.lang.String urlTitle)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalArticleLocalService.getUniqueUrlTitle(groupId,
			articleId, urlTitle);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _journalArticleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.journal.model.impl.JournalArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _journalArticleLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.journal.model.impl.JournalArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _journalArticleLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns all the web content articles present in the system.
	*
	* @return the web content articles present in the system
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getArticles() {
		return _journalArticleLocalService.getArticles();
	}

	/**
	* Returns all the web content articles belonging to the group.
	*
	* @param groupId the primary key of the web content article's group
	* @return the web content articles belonging to the group
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getArticles(
		long groupId) {
		return _journalArticleLocalService.getArticles(groupId);
	}

	/**
	* Returns a range of all the web content articles belonging to the group.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param groupId the primary key of the web content article's group
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @return the range of matching web content articles
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getArticles(
		long groupId, int start, int end) {
		return _journalArticleLocalService.getArticles(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the web content articles belonging to the
	* group.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param groupId the primary key of the web content article's group
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @param obc the comparator to order the web content articles
	* @return the range of matching web content articles ordered by the
	comparator
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getArticles(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> obc) {
		return _journalArticleLocalService.getArticles(groupId, start, end, obc);
	}

	/**
	* Returns all the web content articles matching the group and article ID.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @return the matching web content articles
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getArticles(
		long groupId, java.lang.String articleId) {
		return _journalArticleLocalService.getArticles(groupId, articleId);
	}

	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getArticles(
		long groupId, java.lang.String articleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> orderByComparator) {
		return _journalArticleLocalService.getArticles(groupId, articleId,
			start, end, orderByComparator);
	}

	/**
	* Returns all the web content articles matching the group and folder.
	*
	* @param groupId the primary key of the web content article's group
	* @param folderId the primary key of the web content article folder
	* @return the matching web content articles
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getArticles(
		long groupId, long folderId) {
		return _journalArticleLocalService.getArticles(groupId, folderId);
	}

	/**
	* Returns a range of all the web content articles matching the group and
	* folder.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param groupId the primary key of the web content article's group
	* @param folderId the primary key of the web content article's folder
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @return the range of matching web content articles
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getArticles(
		long groupId, long folderId, int start, int end) {
		return _journalArticleLocalService.getArticles(groupId, folderId,
			start, end);
	}

	/**
	* Returns an ordered range of all the web content articles matching the
	* group and folder.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param groupId the primary key of the web content article's group
	* @param folderId the primary key of the web content article's folder
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @param orderByComparator the comparator to order the web content
	articles
	* @return the range of matching web content articles ordered by the
	comparator
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getArticles(
		long groupId, long folderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> orderByComparator) {
		return _journalArticleLocalService.getArticles(groupId, folderId,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the web content articles matching the group,
	* folder, and status.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param groupId the primary key of the web content article's group
	* @param folderId the primary key of the web content article's folder
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @return the range of matching web content articles
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getArticles(
		long groupId, long folderId, int status, int start, int end) {
		return _journalArticleLocalService.getArticles(groupId, folderId,
			status, start, end);
	}

	/**
	* Returns all the web content articles matching the resource primary key.
	*
	* @param resourcePrimKey the primary key of the resource instance
	* @return the web content articles matching the resource primary key
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getArticlesByResourcePrimKey(
		long resourcePrimKey) {
		return _journalArticleLocalService.getArticlesByResourcePrimKey(resourcePrimKey);
	}

	/**
	* Returns all the web content articles matching the small image ID.
	*
	* @param smallImageId the primary key of the web content article's small
	image
	* @return the web content articles matching the small image ID
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getArticlesBySmallImageId(
		long smallImageId) {
		return _journalArticleLocalService.getArticlesBySmallImageId(smallImageId);
	}

	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getArticlesByStructureId(
		long groupId, java.lang.String ddmStructureKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> obc) {
		return _journalArticleLocalService.getArticlesByStructureId(groupId,
			ddmStructureKey, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getArticlesByStructureId(
		long groupId, java.lang.String ddmStructureKey, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> obc) {
		return _journalArticleLocalService.getArticlesByStructureId(groupId,
			ddmStructureKey, status, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getArticlesByStructureId(
		long groupId, long classNameId, java.lang.String ddmStructureKey,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> obc) {
		return _journalArticleLocalService.getArticlesByStructureId(groupId,
			classNameId, ddmStructureKey, status, start, end, obc);
	}

	/**
	* Returns an ordered range of all the web content articles matching the
	* company, version, and workflow status.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the web content article's company
	* @param version the web content article's version
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @return the range of matching web content articles ordered by article ID
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getCompanyArticles(
		long companyId, double version, int status, int start, int end) {
		return _journalArticleLocalService.getCompanyArticles(companyId,
			version, status, start, end);
	}

	/**
	* Returns an ordered range of all the web content articles matching the
	* company and workflow status.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the web content article's company
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @return the range of matching web content articles ordered by article ID
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getCompanyArticles(
		long companyId, int status, int start, int end) {
		return _journalArticleLocalService.getCompanyArticles(companyId,
			status, start, end);
	}

	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getIndexableArticlesByDDMStructureKey(
		java.lang.String[] ddmStructureKeys) {
		return _journalArticleLocalService.getIndexableArticlesByDDMStructureKey(ddmStructureKeys);
	}

	/**
	* Returns the indexable web content articles matching the resource primary
	* key.
	*
	* @param resourcePrimKey the primary key of the resource instance
	* @return the indexable web content articles matching the resource primary
	key
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getIndexableArticlesByResourcePrimKey(
		long resourcePrimKey) {
		return _journalArticleLocalService.getIndexableArticlesByResourcePrimKey(resourcePrimKey);
	}

	/**
	* Returns a range of all the journal articles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.journal.model.impl.JournalArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of journal articles
	* @param end the upper bound of the range of journal articles (not inclusive)
	* @return the range of journal articles
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getJournalArticles(
		int start, int end) {
		return _journalArticleLocalService.getJournalArticles(start, end);
	}

	/**
	* Returns all the journal articles matching the UUID and company.
	*
	* @param uuid the UUID of the journal articles
	* @param companyId the primary key of the company
	* @return the matching journal articles, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getJournalArticlesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _journalArticleLocalService.getJournalArticlesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of journal articles matching the UUID and company.
	*
	* @param uuid the UUID of the journal articles
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of journal articles
	* @param end the upper bound of the range of journal articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching journal articles, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getJournalArticlesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> orderByComparator) {
		return _journalArticleLocalService.getJournalArticlesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getNoAssetArticles() {
		return _journalArticleLocalService.getNoAssetArticles();
	}

	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getNoPermissionArticles() {
		return _journalArticleLocalService.getNoPermissionArticles();
	}

	/**
	* Returns the web content articles matching the DDM structure keys.
	*
	* @param ddmStructureKeys the primary keys of the web content article's
	DDM structures
	* @return the web content articles matching the DDM structure keys
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getStructureArticles(
		java.lang.String[] ddmStructureKeys) {
		return _journalArticleLocalService.getStructureArticles(ddmStructureKeys);
	}

	/**
	* Returns the web content articles matching the group and DDM structure
	* key.
	*
	* @param groupId the primary key of the web content article's group
	* @param ddmStructureKey the primary key of the web content article's DDM
	structure
	* @return the matching web content articles
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getStructureArticles(
		long groupId, java.lang.String ddmStructureKey) {
		return _journalArticleLocalService.getStructureArticles(groupId,
			ddmStructureKey);
	}

	/**
	* Returns an ordered range of all the web content articles matching the
	* group and DDM structure key.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param groupId the primary key of the web content article's group
	* @param ddmStructureKey the primary key of the web content article's DDM
	structure
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @param obc the comparator to order the web content articles
	* @return the range of matching web content articles ordered by the
	comparator
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getStructureArticles(
		long groupId, java.lang.String ddmStructureKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> obc) {
		return _journalArticleLocalService.getStructureArticles(groupId,
			ddmStructureKey, start, end, obc);
	}

	/**
	* Returns the web content articles matching the group and DDM template key.
	*
	* @param groupId the primary key of the web content article's group
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @return the matching web content articles
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getTemplateArticles(
		long groupId, java.lang.String ddmTemplateKey) {
		return _journalArticleLocalService.getTemplateArticles(groupId,
			ddmTemplateKey);
	}

	/**
	* Returns an ordered range of all the web content articles matching the
	* group and DDM template key.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param groupId the primary key of the web content article's group
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @param obc the comparator to order the web content articles
	* @return the range of matching web content articles ordered by the
	comparator
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> getTemplateArticles(
		long groupId, java.lang.String ddmTemplateKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> obc) {
		return _journalArticleLocalService.getTemplateArticles(groupId,
			ddmTemplateKey, start, end, obc);
	}

	/**
	* Returns an ordered range of all the web content articles matching the
	* parameters without using the indexer, including keyword parameters for
	* article ID, title, description, and content, a DDM structure key
	* parameter, a DDM template key parameter, and an AND operator switch. It
	* is preferable to use the indexed version {@link #search(long, long, List,
	* long, String, String, String, String, int, String, String, LinkedHashMap,
	* boolean, int, int, Sort)} instead of this method wherever possible for
	* performance reasons.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the web content article's company
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param folderIds the primary keys of the web content article folders
	(optionally {@link Collections#EMPTY_LIST})
	* @param classNameId the primary key of the DDMStructure class if the web
	content article is related to a DDM structure, the primary key of
	the class name associated with the article, or
	JournalArticleConstants.CLASSNAME_ID_DEFAULT in the journal-api
	module otherwise
	* @param articleId the article ID keywords (space separated, optionally
	<code>null</code>)
	* @param version the web content article's version (optionally
	<code>null</code>)
	* @param title the title keywords (space separated, optionally
	<code>null</code>)
	* @param description the description keywords (space separated, optionally
	<code>null</code>)
	* @param content the content keywords (space separated, optionally
	<code>null</code>)
	* @param ddmStructureKey the primary key of the web content article's DDM
	structure, if the article is related to a DDM structure, or
	<code>null</code> otherwise
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @param displayDateGT the date after which a matching web content
	article's display date must be after (optionally
	<code>null</code>)
	* @param displayDateLT the date before which a matching web content
	article's display date must be before (optionally
	<code>null</code>)
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param reviewDate the web content article's scheduled review date
	(optionally <code>null</code>)
	* @param andOperator whether every field must match its value or keywords,
	or just one field must match. Company, group, folder IDs, class
	name ID, and status must all match their values.
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @param obc the comparator to order the web content articles
	* @return the range of matching web content articles ordered by the
	comparator
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> search(
		long companyId, long groupId, java.util.List<java.lang.Long> folderIds,
		long classNameId, java.lang.String articleId, java.lang.Double version,
		java.lang.String title, java.lang.String description,
		java.lang.String content, java.lang.String ddmStructureKey,
		java.lang.String ddmTemplateKey, java.util.Date displayDateGT,
		java.util.Date displayDateLT, int status, java.util.Date reviewDate,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> obc) {
		return _journalArticleLocalService.search(companyId, groupId,
			folderIds, classNameId, articleId, version, title, description,
			content, ddmStructureKey, ddmTemplateKey, displayDateGT,
			displayDateLT, status, reviewDate, andOperator, start, end, obc);
	}

	/**
	* Returns an ordered range of all the web content articles matching the
	* parameters without using the indexer, including keyword parameters for
	* article ID, title, description, and content, a DDM structure keys
	* (plural) parameter, a DDM template keys (plural) parameter, and an AND
	* operator switch.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the web content article's company
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param folderIds the primary keys of the web content article folders
	(optionally {@link Collections#EMPTY_LIST})
	* @param classNameId the primary key of the DDMStructure class if the web
	content article is related to a DDM structure, the primary key of
	the class name associated with the article, or
	JournalArticleConstants.CLASSNAME_ID_DEFAULT in the journal-api
	module otherwise
	* @param articleId the article ID keywords (space separated, optionally
	<code>null</code>)
	* @param version the web content article's version (optionally
	<code>null</code>)
	* @param title the title keywords (space separated, optionally
	<code>null</code>)
	* @param description the description keywords (space separated, optionally
	<code>null</code>)
	* @param content the content keywords (space separated, optionally
	<code>null</code>)
	* @param ddmStructureKeys the primary keys of the web content article's
	DDM structures, if the article is related to a DDM structure, or
	<code>null</code> otherwise
	* @param ddmTemplateKeys the primary keys of the web content article's DDM
	templates (originally <code>null</code>). If the articles are
	related to a DDM structure, the template's structure must match
	it.
	* @param displayDateGT the date after which a matching web content
	article's display date must be after (optionally
	<code>null</code>)
	* @param displayDateLT the date before which a matching web content
	article's display date must be before (optionally
	<code>null</code>)
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param reviewDate the web content article's scheduled review date
	(optionally <code>null</code>)
	* @param andOperator whether every field must match its value or keywords,
	or just one field must match.  Company, group, folder IDs, class
	name ID, and status must all match their values.
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @param obc the comparator to order the web content articles
	* @return the range of matching web content articles ordered by the
	comparator
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> search(
		long companyId, long groupId, java.util.List<java.lang.Long> folderIds,
		long classNameId, java.lang.String articleId, java.lang.Double version,
		java.lang.String title, java.lang.String description,
		java.lang.String content, java.lang.String[] ddmStructureKeys,
		java.lang.String[] ddmTemplateKeys, java.util.Date displayDateGT,
		java.util.Date displayDateLT, int status, java.util.Date reviewDate,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> obc) {
		return _journalArticleLocalService.search(companyId, groupId,
			folderIds, classNameId, articleId, version, title, description,
			content, ddmStructureKeys, ddmTemplateKeys, displayDateGT,
			displayDateLT, status, reviewDate, andOperator, start, end, obc);
	}

	/**
	* Returns an ordered range of all the web content articles matching the
	* parameters without using the indexer, including a keywords parameter for
	* matching with the article's ID, title, description, and content, a DDM
	* structure key parameter, and a DDM template key parameter. It is
	* preferable to use the indexed version {@link #search(long, long, List,
	* long, String, String, String, LinkedHashMap, int, int, Sort)} instead of
	* this method wherever possible for performance reasons.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the web content article's company
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param folderIds the primary keys of the web content article folders
	(optionally {@link Collections#EMPTY_LIST})
	* @param classNameId the primary key of the DDMStructure class if the web
	content article is related to a DDM structure, the primary key of
	the class name associated with the article, or
	JournalArticleConstants.CLASSNAME_ID_DEFAULT in the journal-api
	module otherwise
	* @param keywords the keywords (space separated), which may occur in the
	web content article ID, title, description, or content
	(optionally <code>null</code>). If the keywords value is not
	<code>null</code>, the search uses the OR operator in connecting
	query criteria; otherwise it uses the AND operator.
	* @param version the web content article's version (optionally
	<code>null</code>)
	* @param ddmStructureKey the primary key of the web content article's DDM
	structure, if the article is related to a DDM structure, or
	<code>null</code> otherwise
	* @param ddmTemplateKey the primary key of the web content article's DDM
	template
	* @param displayDateGT the date after which a matching web content
	article's display date must be after (optionally
	<code>null</code>)
	* @param displayDateLT the date before which a matching web content
	article's display date must be before (optionally
	<code>null</code>)
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param reviewDate the web content article's scheduled review date
	(optionally <code>null</code>)
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @param obc the comparator to order the web content articles
	* @return the range of matching web content articles ordered by the
	comparator
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> search(
		long companyId, long groupId, java.util.List<java.lang.Long> folderIds,
		long classNameId, java.lang.String keywords, java.lang.Double version,
		java.lang.String ddmStructureKey, java.lang.String ddmTemplateKey,
		java.util.Date displayDateGT, java.util.Date displayDateLT, int status,
		java.util.Date reviewDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle> obc) {
		return _journalArticleLocalService.search(companyId, groupId,
			folderIds, classNameId, keywords, version, ddmStructureKey,
			ddmTemplateKey, displayDateGT, displayDateLT, status, reviewDate,
			start, end, obc);
	}

	/**
	* Returns a range of all the web content articles matching the parameters
	* without using the indexer. It is preferable to use the indexed version
	* {@link #search(long, long, long, int, int, int)} instead of this method
	* wherever possible for performance reasons.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param folderIds the primary keys of the web content article folders
	(optionally {@link Collections#EMPTY_LIST})
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @return the matching web content articles
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> search(
		long groupId, java.util.List<java.lang.Long> folderIds, int status,
		int start, int end) {
		return _journalArticleLocalService.search(groupId, folderIds, status,
			start, end);
	}

	/**
	* Returns a range of all the web content articles in a single folder
	* matching the parameters without using the indexer. It is preferable to
	* use the indexed version {@link #search(long, long, long, int, int, int)}
	* instead of this method wherever possible for performance reasons.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param folderId the primary key of the web content article folder
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @return the matching web content articles
	*/
	@Override
	public java.util.List<com.liferay.journal.model.JournalArticle> search(
		long groupId, long folderId, int status, int start, int end) {
		return _journalArticleLocalService.search(groupId, folderId, status,
			start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _journalArticleLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _journalArticleLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Adds the resources to the web content article.
	*
	* @param article the web content article
	* @param addGroupPermissions whether to add group permissions
	* @param addGuestPermissions whether to add guest permissions
	*/
	@Override
	public void addArticleResources(
		com.liferay.journal.model.JournalArticle article,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalArticleLocalService.addArticleResources(article,
			addGroupPermissions, addGuestPermissions);
	}

	/**
	* Adds the model resources with the permissions to the web content article.
	*
	* @param article the web content article to add resources to
	* @param groupPermissions the group permissions to be added
	* @param guestPermissions the guest permissions to be added
	*/
	@Override
	public void addArticleResources(
		com.liferay.journal.model.JournalArticle article,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalArticleLocalService.addArticleResources(article,
			groupPermissions, guestPermissions);
	}

	/**
	* Adds the resources to the most recently created web content article.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param addGroupPermissions whether to add group permissions
	* @param addGuestPermissions whether to add guest permissions
	*/
	@Override
	public void addArticleResources(long groupId, java.lang.String articleId,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalArticleLocalService.addArticleResources(groupId, articleId,
			addGroupPermissions, addGuestPermissions);
	}

	/**
	* Adds the resources with the permissions to the most recently created web
	* content article.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param groupPermissions the group permissions to be added
	* @param guestPermissions the guest permissions to be added
	*/
	@Override
	public void addArticleResources(long groupId, java.lang.String articleId,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalArticleLocalService.addArticleResources(groupId, articleId,
			groupPermissions, guestPermissions);
	}

	/**
	* Checks all web content articles by handling their expirations and sending
	* review notifications based on their current workflow.
	*/
	@Override
	public void checkArticles()
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalArticleLocalService.checkArticles();
	}

	/**
	* Checks the web content article matching the group, article ID, and
	* version, replacing escaped newline and return characters with non-escaped
	* newline and return characters.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	*/
	@Override
	public void checkNewLine(long groupId, java.lang.String articleId,
		double version)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalArticleLocalService.checkNewLine(groupId, articleId, version);
	}

	/**
	* Checks the web content article matching the group, article ID, and
	* version for an associated structure. If no structure is associated,
	* return; otherwise check that the article and structure match.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	*/
	@Override
	public void checkStructure(long groupId, java.lang.String articleId,
		double version)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalArticleLocalService.checkStructure(groupId, articleId, version);
	}

	/**
	* Deletes all web content articles and their resources matching the group
	* and article ID, optionally sending email notifying denial of article if
	* it had not yet been approved.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param serviceContext the service context to be applied. Can set the
	portlet preferences that include email information to notify
	recipients of the unapproved web content article's denial.
	*/
	@Override
	public void deleteArticle(long groupId, java.lang.String articleId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalArticleLocalService.deleteArticle(groupId, articleId,
			serviceContext);
	}

	/**
	* Deletes all the group's web content articles and resources.
	*
	* @param groupId the primary key of the web content article's group
	*/
	@Override
	public void deleteArticles(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalArticleLocalService.deleteArticles(groupId);
	}

	/**
	* Deletes all the group's web content articles and resources matching the
	* class name and class primary key.
	*
	* @param groupId the primary key of the web content article's group
	* @param className the DDMStructure class name if the web content article
	is related to a DDM structure, the primary key of the class name
	associated with the article, or
	JournalArticleConstants.CLASSNAME_ID_DEFAULT in the journal-api
	module otherwise
	* @param classPK the primary key of the DDM structure, if the DDMStructure
	class name is given as the <code>className</code> parameter, the
	primary key of the class associated with the web content article,
	or <code>0</code> otherwise
	*/
	@Override
	public void deleteArticles(long groupId, java.lang.String className,
		long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalArticleLocalService.deleteArticles(groupId, className, classPK);
	}

	/**
	* Deletes all the group's web content articles and resources in the folder,
	* including recycled articles.
	*
	* @param groupId the primary key of the web content article's group
	* @param folderId the primary key of the web content article folder
	*/
	@Override
	public void deleteArticles(long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalArticleLocalService.deleteArticles(groupId, folderId);
	}

	/**
	* Deletes all the group's web content articles and resources in the folder,
	* optionally including recycled articles.
	*
	* @param groupId the primary key of the web content article's group
	* @param folderId the primary key of the web content article folder
	* @param includeTrashedEntries whether to include recycled web content
	articles
	*/
	@Override
	public void deleteArticles(long groupId, long folderId,
		boolean includeTrashedEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalArticleLocalService.deleteArticles(groupId, folderId,
			includeTrashedEntries);
	}

	/**
	* Deletes the layout's association with the web content articles for the
	* group.
	*
	* @param groupId the primary key of the web content article's group
	* @param layoutUuid the unique string identifying the web content article's
	display page
	*/
	@Override
	public void deleteLayoutArticleReferences(long groupId,
		java.lang.String layoutUuid) {
		_journalArticleLocalService.deleteLayoutArticleReferences(groupId,
			layoutUuid);
	}

	/**
	* Expires the web content article matching the group and article ID,
	* expiring all of its versions if the
	* <code>journal.article.expire.all.versions</code> portal property is
	* <code>true</code>, otherwise expiring only its latest approved version.
	*
	* @param userId the primary key of the user updating the web content
	article
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param articleURL the web content article's accessible URL
	* @param serviceContext the service context to be applied. Can set the
	modification date, status date, portlet preferences, and can set
	whether to add the default command update for the web content
	article. With respect to social activities, by setting the service
	context's command to {@link Constants#UPDATE}, the invocation is
	considered a web content update activity; otherwise it is
	considered a web content add activity.
	*/
	@Override
	public void expireArticle(long userId, long groupId,
		java.lang.String articleId, java.lang.String articleURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalArticleLocalService.expireArticle(userId, groupId, articleId,
			articleURL, serviceContext);
	}

	/**
	* Rebuilds the web content article's tree path using tree traversal.
	*
	* <p>
	* For example, here is a conceptualization of a web content article tree
	* path:
	* </p>
	*
	* <p>
	* <pre>
	* <code>
	* /(Folder's folderId)/(Subfolder's folderId)/(article's articleId)
	* </code>
	* </pre>
	* </p>
	*
	* @param companyId the primary key of the web content article's company
	*/
	@Override
	public void rebuildTree(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalArticleLocalService.rebuildTree(companyId);
	}

	@Override
	public void setTreePaths(long folderId, java.lang.String treePath,
		boolean reindex)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalArticleLocalService.setTreePaths(folderId, treePath, reindex);
	}

	/**
	* Subscribes the user to changes in elements that belong to the web content
	* article's DDM structure.
	*
	* @param groupId the primary key of the folder's group
	* @param userId the primary key of the user to be subscribed
	* @param ddmStructureId the primary key of the structure to subscribe to
	*/
	@Override
	public void subscribeStructure(long groupId, long userId,
		long ddmStructureId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalArticleLocalService.subscribeStructure(groupId, userId,
			ddmStructureId);
	}

	/**
	* Unsubscribes the user from changes in elements that belong to the web
	* content article's DDM structure.
	*
	* @param groupId the primary key of the folder's group
	* @param userId the primary key of the user to be subscribed
	* @param ddmStructureId the primary key of the structure to subscribe to
	*/
	@Override
	public void unsubscribeStructure(long groupId, long userId,
		long ddmStructureId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalArticleLocalService.unsubscribeStructure(groupId, userId,
			ddmStructureId);
	}

	/**
	* Updates the web content article's asset with the new asset categories,
	* tag names, and link entries, removing and adding them as necessary.
	*
	* @param userId the primary key of the user updating the web content
	article's asset
	* @param article the web content article
	* @param assetCategoryIds the primary keys of the new asset categories
	* @param assetTagNames the new asset tag names
	* @param assetLinkEntryIds the primary keys of the new asset link
	entries
	* @deprecated As of 7.0.0, replaced by {@link #updateAsset(long,
	JournalArticle, long[], String[], long[], Double)}
	*/
	@Deprecated
	@Override
	public void updateAsset(long userId,
		com.liferay.journal.model.JournalArticle article,
		long[] assetCategoryIds, java.lang.String[] assetTagNames,
		long[] assetLinkEntryIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalArticleLocalService.updateAsset(userId, article,
			assetCategoryIds, assetTagNames, assetLinkEntryIds);
	}

	/**
	* Updates the web content article's asset with the new asset categories,
	* tag names, and link entries, removing and adding them as necessary.
	*
	* @param userId the primary key of the user updating the web content
	article's asset
	* @param article the web content article
	* @param assetCategoryIds the primary keys of the new asset categories
	* @param assetTagNames the new asset tag names
	* @param assetLinkEntryIds the primary keys of the new asset link entries
	* @param priority the priority of the asset
	*/
	@Override
	public void updateAsset(long userId,
		com.liferay.journal.model.JournalArticle article,
		long[] assetCategoryIds, java.lang.String[] assetTagNames,
		long[] assetLinkEntryIds, java.lang.Double priority)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalArticleLocalService.updateAsset(userId, article,
			assetCategoryIds, assetTagNames, assetLinkEntryIds, priority);
	}

	/**
	* Updates the web content articles matching the group, class name ID, and
	* DDM template key, replacing the DDM template key with a new one.
	*
	* @param groupId the primary key of the web content article's group
	* @param classNameId the primary key of the DDMStructure class if the web
	content article is related to a DDM structure, the primary key of
	the class name associated with the article, or
	JournalArticleConstants.CLASSNAME_ID_DEFAULT in the journal-api
	module otherwise
	* @param oldDDMTemplateKey the primary key of the web content article's old
	DDM template
	* @param newDDMTemplateKey the primary key of the web content article's new
	DDM template
	*/
	@Override
	public void updateDDMTemplateKey(long groupId, long classNameId,
		java.lang.String oldDDMTemplateKey, java.lang.String newDDMTemplateKey) {
		_journalArticleLocalService.updateDDMTemplateKey(groupId, classNameId,
			oldDDMTemplateKey, newDDMTemplateKey);
	}

	/**
	* Updates the web content articles matching the group, class name ID, and
	* DDM template key, replacing the DDM template key with a new one.
	*
	* @param groupId the primary key of the web content article's group
	* @param classNameId the primary key of the DDMStructure class if the
	web content article is related to a DDM structure, the
	primary key of the class name associated with the article, or
	JournalArticleConstants.CLASSNAME_ID_DEFAULT in the
	journal-api module otherwise
	* @param oldDDMTemplateKey the primary key of the web content
	article's old DDM template
	* @param newDDMTemplateKey the primary key of the web content
	article's new DDM template
	* @deprecated As of 7.0.0, replaced by {@link #updateDDMTemplateKey}
	*/
	@Deprecated
	@Override
	public void updateTemplateId(long groupId, long classNameId,
		java.lang.String oldDDMTemplateKey, java.lang.String newDDMTemplateKey) {
		_journalArticleLocalService.updateTemplateId(groupId, classNameId,
			oldDDMTemplateKey, newDDMTemplateKey);
	}

	@Override
	public JournalArticleLocalService getWrappedService() {
		return _journalArticleLocalService;
	}

	@Override
	public void setWrappedService(
		JournalArticleLocalService journalArticleLocalService) {
		_journalArticleLocalService = journalArticleLocalService;
	}

	private JournalArticleLocalService _journalArticleLocalService;
}