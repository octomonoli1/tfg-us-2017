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

import com.liferay.journal.model.JournalArticle;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.portlet.PortletRequestModel;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.File;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the remote service interface for JournalArticle. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see JournalArticleServiceUtil
 * @see com.liferay.journal.service.base.JournalArticleServiceBaseImpl
 * @see com.liferay.journal.service.impl.JournalArticleServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=journal", "json.web.service.context.path=JournalArticle"}, service = JournalArticleService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface JournalArticleService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JournalArticleServiceUtil} to access the journal article remote service. Add custom service methods to {@link com.liferay.journal.service.impl.JournalArticleServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds a web content article with additional parameters.
	*
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
	* @param titleMap the web content article's locales and localized titles
	* @param descriptionMap the web content article's locales and localized
	descriptions
	* @param content the HTML content wrapped in XML. For more information,
	see the content example in the {@link #updateArticle(long, long,
	String, double, String, ServiceContext)} description.
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
	* @param smallFile the web content article's small image file
	* @param images the web content's images
	* @param articleURL the web content article's accessible URL
	* @param serviceContext the service context to be applied. Can set the
	UUID, creation date, modification date, expando bridge
	attributes, guest permissions, group permissions, asset category
	IDs, asset tag names, asset link entry IDs, asset priority, URL
	title, and workflow actions for the web content article. Can also
	set whether to add the default guest and group permissions.
	* @return the web content article
	*/
	public JournalArticle addArticle(long groupId, long folderId,
		long classNameId, long classPK, java.lang.String articleId,
		boolean autoArticleId, Map<Locale, java.lang.String> titleMap,
		Map<Locale, java.lang.String> descriptionMap, java.lang.String content,
		java.lang.String ddmStructureKey, java.lang.String ddmTemplateKey,
		java.lang.String layoutUuid, int displayDateMonth, int displayDateDay,
		int displayDateYear, int displayDateHour, int displayDateMinute,
		int expirationDateMonth, int expirationDateDay, int expirationDateYear,
		int expirationDateHour, int expirationDateMinute, boolean neverExpire,
		int reviewDateMonth, int reviewDateDay, int reviewDateYear,
		int reviewDateHour, int reviewDateMinute, boolean neverReview,
		boolean indexable, boolean smallImage, java.lang.String smallImageURL,
		File smallFile, Map<java.lang.String, byte[]> images,
		java.lang.String articleURL, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Adds a web content article without any images.
	*
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
	* @param articleURL the web content article's accessible URL
	* @param serviceContext the service context to be applied. Can set the
	UUID, creation date, modification date, expando bridge
	attributes, guest permissions, group permissions, asset category
	IDs, asset tag names, asset link entry IDs, asset priority, URL
	title, and workflow actions for the web content article. Can also
	set whether to add the default guest and group permissions.
	* @return the web content article
	*/
	public JournalArticle addArticle(long groupId, long folderId,
		long classNameId, long classPK, java.lang.String articleId,
		boolean autoArticleId, Map<Locale, java.lang.String> titleMap,
		Map<Locale, java.lang.String> descriptionMap, java.lang.String content,
		java.lang.String ddmStructureKey, java.lang.String ddmTemplateKey,
		java.lang.String layoutUuid, int displayDateMonth, int displayDateDay,
		int displayDateYear, int displayDateHour, int displayDateMinute,
		int expirationDateMonth, int expirationDateDay, int expirationDateYear,
		int expirationDateHour, int expirationDateMinute, boolean neverExpire,
		int reviewDateMonth, int reviewDateDay, int reviewDateYear,
		int reviewDateHour, int reviewDateMinute, boolean neverReview,
		boolean indexable, java.lang.String articleURL,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Copies the web content article matching the group, article ID, and
	* version. This method creates a new article, extracting all the values
	* from the old one and updating its article ID.
	*
	* @param groupId the primary key of the web content article's group
	* @param oldArticleId the primary key of the old web content article
	* @param newArticleId the primary key of the new web content article
	* @param autoArticleId whether to auto-generate the web content article ID
	* @param version the web content article's version
	* @return the new web content article
	*/
	public JournalArticle copyArticle(long groupId,
		java.lang.String oldArticleId, java.lang.String newArticleId,
		boolean autoArticleId, double version) throws PortalException;

	/**
	* Expires the web content article matching the group, article ID, and
	* version.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param articleURL the web content article's accessible URL
	* @param serviceContext the service context to be applied. Can set the
	modification date, status date, portlet preferences, and can set
	whether to add the default command update for the web content
	article. With respect to social activities, by setting the
	service context's command to {@link
	com.liferay.portal.kernel.util.Constants#UPDATE}, the invocation
	is considered a web content update activity; otherwise it is
	considered a web content add activity.
	* @return the web content article
	*/
	public JournalArticle expireArticle(long groupId,
		java.lang.String articleId, double version,
		java.lang.String articleURL, ServiceContext serviceContext)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JournalArticle fetchArticle(long groupId, java.lang.String articleId)
		throws PortalException;

	/**
	* Returns the latest approved web content article, or the latest unapproved
	* article if none are approved. Both approved and unapproved articles must
	* match the group and article ID.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @return the matching web content article
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JournalArticle getArticle(long groupId, java.lang.String articleId)
		throws PortalException;

	/**
	* Returns the web content article matching the group, article ID, and
	* version.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @return the matching web content article
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JournalArticle getArticle(long groupId, java.lang.String articleId,
		double version) throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JournalArticle getArticle(long groupId, java.lang.String className,
		long classPK) throws PortalException;

	/**
	* Returns the web content article with the ID.
	*
	* @param id the primary key of the web content article
	* @return the web content article with the ID
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JournalArticle getArticle(long id) throws PortalException;

	/**
	* Returns the latest web content article that is approved, or the latest
	* unapproved article if none are approved. Both approved and unapproved
	* articles must match the group and URL title.
	*
	* @param groupId the primary key of the web content article's group
	* @param urlTitle the web content article's accessible URL title
	* @return the matching web content article
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JournalArticle getArticleByUrlTitle(long groupId,
		java.lang.String urlTitle) throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JournalArticle getDisplayArticleByUrlTitle(long groupId,
		java.lang.String urlTitle) throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JournalArticle getLatestArticle(long groupId,
		java.lang.String articleId, int status) throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JournalArticle getLatestArticle(long groupId,
		java.lang.String className, long classPK) throws PortalException;

	/**
	* Returns the latest web content article matching the resource primary key,
	* preferring articles with approved workflow status.
	*
	* @param resourcePrimKey the primary key of the resource instance
	* @return the latest web content article matching the resource primary key,
	preferring articles with approved workflow status
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JournalArticle getLatestArticle(long resourcePrimKey)
		throws PortalException;

	/**
	* Moves the web content article from the Recycle Bin to the folder.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param newFolderId the primary key of the web content article's new
	folder
	* @param serviceContext the service context to be applied. Can set the
	modification date, portlet preferences, and can set whether to
	add the default command update for the web content article. With
	respect to social activities, by setting the service context's
	command to {@link
	com.liferay.portal.kernel.util.Constants#UPDATE}, the invocation
	is considered a web content update activity; otherwise it is
	considered a web content add activity.
	* @return the updated web content article, which was moved from the Recycle
	Bin to the folder
	*/
	public JournalArticle moveArticleFromTrash(long groupId,
		java.lang.String articleId, long newFolderId,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Moves the web content article from the Recycle Bin to the folder.
	*
	* @param groupId the primary key of the web content article's group
	* @param resourcePrimKey the primary key of the resource instance
	* @param newFolderId the primary key of the web content article's new
	folder
	* @param serviceContext the service context to be applied. Can set the
	modification date, portlet preferences, and can set whether to
	add the default command update for the web content article. With
	respect to social activities, by setting the service context's
	command to {@link
	com.liferay.portal.kernel.util.Constants#UPDATE}, the invocation
	is considered a web content update activity; otherwise it is
	considered a web content add activity.
	* @return the updated web content article, which was moved from the Recycle
	Bin to the folder
	*/
	public JournalArticle moveArticleFromTrash(long groupId,
		long resourcePrimKey, long newFolderId, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Moves the latest version of the web content article matching the group
	* and article ID to the recycle bin.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @return the moved web content article or <code>null</code> if no matching
	article was found
	*/
	public JournalArticle moveArticleToTrash(long groupId,
		java.lang.String articleId) throws PortalException;

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
	public JournalArticle removeArticleLocale(long groupId,
		java.lang.String articleId, double version, java.lang.String languageId)
		throws PortalException;

	/**
	* Updates the web content article matching the version, replacing its
	* folder and content.
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
	* @param groupId the primary key of the web content article's group
	* @param folderId the primary key of the web content article folder
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param content the HTML content wrapped in XML.
	* @param serviceContext the service context to be applied. Can set the
	modification date, expando bridge attributes, asset category IDs,
	asset tag names, asset link entry IDs, asset priority, workflow
	actions, URL title, and can set whether to add the default
	command update for the web content article. With respect to
	social activities, by setting the service context's command to
	{@link com.liferay.portal.kernel.util.Constants#UPDATE}, the
	invocation is considered a web content update activity; otherwise
	it is considered a web content add activity.
	* @return the updated web content article
	*/
	public JournalArticle updateArticle(long groupId, long folderId,
		java.lang.String articleId, double version, java.lang.String content,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Updates the web content article with additional parameters.
	*
	* @param groupId the primary key of the web content article's group
	* @param folderId the primary key of the web content article folder
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param titleMap the web content article's locales and localized titles
	* @param descriptionMap the web content article's locales and localized
	descriptions
	* @param content the HTML content wrapped in XML. For more information,
	see the content example in the {@link #updateArticle(long, long,
	String, double, String, ServiceContext)} description.
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
	* @param smallFile the web content article's new small image file
	(optionally <code>null</code>). Must pass in
	<code>smallImage</code> value of <code>true</code> to replace the
	article's small image file.
	* @param images the web content's images (optionally <code>null</code>)
	* @param articleURL the web content article's accessible URL (optionally
	<code>null</code>)
	* @param serviceContext the service context to be applied. Can set the
	modification date, expando bridge attributes, asset category IDs,
	asset tag names, asset link entry IDs, asset priority, workflow
	actions, URL title, and can set whether to add the default
	command update for the web content article. With respect to
	social activities, by setting the service context's command to
	{@link com.liferay.portal.kernel.util.Constants#UPDATE}, the
	invocation is considered a web content update activity; otherwise
	it is considered a web content add activity.
	* @return the updated web content article
	*/
	public JournalArticle updateArticle(long groupId, long folderId,
		java.lang.String articleId, double version,
		Map<Locale, java.lang.String> titleMap,
		Map<Locale, java.lang.String> descriptionMap, java.lang.String content,
		java.lang.String ddmStructureKey, java.lang.String ddmTemplateKey,
		java.lang.String layoutUuid, int displayDateMonth, int displayDateDay,
		int displayDateYear, int displayDateHour, int displayDateMinute,
		int expirationDateMonth, int expirationDateDay, int expirationDateYear,
		int expirationDateHour, int expirationDateMinute, boolean neverExpire,
		int reviewDateMonth, int reviewDateDay, int reviewDateYear,
		int reviewDateHour, int reviewDateMinute, boolean neverReview,
		boolean indexable, boolean smallImage, java.lang.String smallImageURL,
		File smallFile, Map<java.lang.String, byte[]> images,
		java.lang.String articleURL, ServiceContext serviceContext)
		throws PortalException;

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
	see the content example in the {@link #updateArticle(long, long,
	String, double, String, ServiceContext)} description.
	* @param layoutUuid the unique string identifying the web content
	article's display page
	* @param serviceContext the service context to be applied. Can set the
	modification date, expando bridge attributes, asset category IDs,
	asset tag names, asset link entry IDs, asset priority, workflow
	actions, URL title, and can set whether to add the default
	command update for the web content article. With respect to
	social activities, by setting the service context's command to
	{@link com.liferay.portal.kernel.util.Constants#UPDATE}, the
	invocation is considered a web content update activity; otherwise
	it is considered a web content add activity.
	* @return the updated web content article
	*/
	public JournalArticle updateArticle(long userId, long groupId,
		long folderId, java.lang.String articleId, double version,
		Map<Locale, java.lang.String> titleMap,
		Map<Locale, java.lang.String> descriptionMap, java.lang.String content,
		java.lang.String layoutUuid, ServiceContext serviceContext)
		throws PortalException;

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
	see the content example in the {@link #updateArticle(long, long,
	String, double, String, ServiceContext)} description.
	* @param images the web content's images
	* @param serviceContext the service context to be applied. Can set the
	modification date and URL title for the web content article.
	* @return the updated web content article
	*/
	public JournalArticle updateArticleTranslation(long groupId,
		java.lang.String articleId, double version, Locale locale,
		java.lang.String title, java.lang.String description,
		java.lang.String content, Map<java.lang.String, byte[]> images,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Updates the web content article matching the group, article ID, and
	* version, replacing its content.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param content the HTML content wrapped in XML. For more information,
	see the content example in the {@link #updateArticle(long, long,
	String, double, String, ServiceContext)} description.
	* @return the updated web content article
	*/
	public JournalArticle updateContent(long groupId,
		java.lang.String articleId, double version, java.lang.String content)
		throws PortalException;

	/**
	* Updates the workflow status of the web content article matching the
	* group, article ID, and version.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param articleURL the web content article's accessible URL
	* @param serviceContext the service context to be applied. Can set the
	modification date, portlet preferences, and can set whether to
	add the default command update for the web content article.
	* @return the updated web content article
	*/
	public JournalArticle updateStatus(long groupId,
		java.lang.String articleId, double version, int status,
		java.lang.String articleURL, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Returns a range of all the web content articles matching the group,
	* creator, creator, and workflow status using the indexer. It is preferable
	* to use this method instead of the non-indexed version whenever possible
	* for performance reasons.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param groupId the primary key of the web content article's group
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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(long groupId, long creatorUserId, int status, int start,
		int end) throws PortalException;

	/**
	* Returns the number of web content articles matching the group and folder.
	*
	* @param groupId the primary key of the web content article's group
	* @param folderId the primary key of the web content article folder
	* @return the number of matching web content articles
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getArticlesCount(long groupId, long folderId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getArticlesCount(long groupId, long folderId, int status);

	/**
	* Returns the number of web content articles matching the group and article
	* ID.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @return the number of matching web content articles
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getArticlesCountByArticleId(long groupId,
		java.lang.String articleId);

	/**
	* Returns the number of web content articles matching the group, default
	* class name ID, and DDM structure key.
	*
	* @param groupId the primary key of the web content article's group
	* @param ddmStructureKey the primary key of the web content article's DDM
	structure
	* @return the number of matching web content articles
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getArticlesCountByStructureId(long groupId,
		java.lang.String ddmStructureKey);

	/**
	* Returns the number of web content articles matching the group, default
	* class name ID, and DDM structure key.
	*
	* @param groupId the primary key of the web content article's group
	* @param ddmStructureKey the primary key of the web content article's DDM
	structure
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @return the number of matching web content articles
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getArticlesCountByStructureId(long groupId,
		java.lang.String ddmStructureKey, int status);

	/**
	* Returns the number of web content articles matching the group, class name
	* ID, DDM structure key, and workflow status.
	*
	* @param groupId the primary key of the web content article's group
	* @param classNameId the primary key of the DDMStructure class if the web
	content article is related to a DDM structure, the primary key of
	the class name associated with the article, or
	JournalArticleConstants.CLASSNAME_ID_DEFAULT in the journal-api
	module otherwise
	* @param ddmStructureKey the primary key of the web content article's DDM
	structure
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @return the number of matching web content articles
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getArticlesCountByStructureId(long groupId, long classNameId,
		java.lang.String ddmStructureKey, int status);

	/**
	* Returns the number of folders containing web content articles belonging
	* to the group.
	*
	* @param groupId the primary key of the web content article's group
	* @param folderIds the primary keys of the web content article folders
	(optionally {@link java.util.Collections#EMPTY_LIST})
	* @return the number of matching folders containing web content articles
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFoldersAndArticlesCount(long groupId,
		List<java.lang.Long> folderIds);

	/**
	* Returns the number of web content articles matching the group, user, and
	* the root folder or any of its subfolders.
	*
	* @param groupId the primary key of the web content article's group
	* @param userId the primary key of the user (optionally <code>0</code>)
	* @param rootFolderId the primary key of the root folder to begin the
	search
	* @return the number of matching web content articles
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getGroupArticlesCount(long groupId, long userId,
		long rootFolderId) throws PortalException;

	/**
	* Returns the number of web content articles matching the group, user, and
	* the root folder or any of its subfolders.
	*
	* @param groupId the primary key of the web content article's group
	* @param userId the primary key of the user (optionally <code>0</code>)
	* @param rootFolderId the primary key of the root folder to begin the
	search
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @return the number of matching web content articles
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getGroupArticlesCount(long groupId, long userId,
		long rootFolderId, int status) throws PortalException;

	/**
	* Returns the number of web content articles matching the group, user, the
	* root folder or any of its subfolders.
	*
	* @param groupId the primary key of the web content article's group
	* @param userId the primary key of the user (optionally <code>0</code>)
	* @param rootFolderId the primary key of the root folder to begin the
	search
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @return the range of matching web content articles ordered by the
	comparator
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getGroupArticlesCount(long groupId, long userId,
		long rootFolderId, int status, boolean includeOwner)
		throws PortalException;

	/**
	* Returns the number of web content articles matching the parameters,
	* including keyword parameters for article ID, title, description, and
	* content, a DDM structure key parameter, a DDM template key parameter, and
	* an AND operator switch.
	*
	* @param companyId the primary key of the web content article's company
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param folderIds the primary keys of the web content article folders
	(optionally {@link java.util.Collections#EMPTY_LIST})
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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long companyId, long groupId,
		List<java.lang.Long> folderIds, long classNameId,
		java.lang.String articleId, java.lang.Double version,
		java.lang.String title, java.lang.String description,
		java.lang.String content, java.lang.String ddmStructureKey,
		java.lang.String ddmTemplateKey, Date displayDateGT,
		Date displayDateLT, int status, Date reviewDate, boolean andOperator);

	/**
	* Returns the number of web content articles matching the parameters,
	* including keyword parameters for article ID, title, description, and
	* content, a DDM structure keys (plural) parameter, a DDM template keys
	* (plural) parameter, and an AND operator switch.
	*
	* @param companyId the primary key of the web content article's company
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param folderIds the primary keys of the web content article folders
	(optionally {@link java.util.Collections#EMPTY_LIST})
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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long companyId, long groupId,
		List<java.lang.Long> folderIds, long classNameId,
		java.lang.String articleId, java.lang.Double version,
		java.lang.String title, java.lang.String description,
		java.lang.String content, java.lang.String[] ddmStructureKeys,
		java.lang.String[] ddmTemplateKeys, Date displayDateGT,
		Date displayDateLT, int status, Date reviewDate, boolean andOperator);

	/**
	* Returns the number of web content articles matching the parameters,
	* including a keywords parameter for matching with the article's ID, title,
	* description, and content, a DDM structure key parameter, and a DDM
	* template key parameter.
	*
	* @param companyId the primary key of the web content article's company
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param folderIds the primary keys of the web content article folders
	(optionally {@link java.util.Collections#EMPTY_LIST})
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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long companyId, long groupId,
		List<java.lang.Long> folderIds, long classNameId,
		java.lang.String keywords, java.lang.Double version,
		java.lang.String ddmStructureKey, java.lang.String ddmTemplateKey,
		Date displayDateGT, Date displayDateLT, int status, Date reviewDate);

	/**
	* Returns the web content from the web content article matching the group,
	* article ID, and version.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param languageId the primary key of the language translation to get
	* @param portletRequestModel the portlet request model
	* @param themeDisplay the theme display
	* @return the matching web content
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getArticleContent(long groupId,
		java.lang.String articleId, double version,
		java.lang.String languageId, PortletRequestModel portletRequestModel,
		ThemeDisplay themeDisplay) throws PortalException;

	/**
	* Returns the web content from the web content article matching the group,
	* article ID, and version.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param version the web content article's version
	* @param languageId the primary key of the language translation to get
	* @param themeDisplay the theme display
	* @return the matching web content
	* @deprecated As of 7.0.0, replaced by {@link #getArticleContent(long,
	String, double, String, PortletRequestModel, ThemeDisplay)}
	*/
	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getArticleContent(long groupId,
		java.lang.String articleId, double version,
		java.lang.String languageId, ThemeDisplay themeDisplay)
		throws PortalException;

	/**
	* Returns the latest web content from the web content article matching the
	* group and article ID.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param languageId the primary key of the language translation to get
	* @param portletRequestModel the portlet request model
	* @param themeDisplay the theme display
	* @return the matching web content
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getArticleContent(long groupId,
		java.lang.String articleId, java.lang.String languageId,
		PortletRequestModel portletRequestModel, ThemeDisplay themeDisplay)
		throws PortalException;

	/**
	* Returns the latest web content from the web content article matching the
	* group and article ID.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param languageId the primary key of the language translation to get
	* @param themeDisplay the theme display
	* @return the matching web content
	* @deprecated As of 7.0.0, replaced by {@link #getArticleContent(long,
	String, String, PortletRequestModel, ThemeDisplay)}
	*/
	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getArticleContent(long groupId,
		java.lang.String articleId, java.lang.String languageId,
		ThemeDisplay themeDisplay) throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Returns all the web content articles matching the group and folder.
	*
	* @param groupId the primary key of the web content article's group
	* @param folderId the primary key of the web content article folder
	* @return the matching web content articles
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticle> getArticles(long groupId, long folderId);

	/**
	* Returns an ordered range of all the web content articles matching the
	* group and folder.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param groupId the primary key of the web content article's group
	* @param folderId the primary key of the web content article folder
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @param obc the comparator to order the web content articles
	* @return the matching web content articles
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticle> getArticles(long groupId, long folderId,
		int start, int end, OrderByComparator<JournalArticle> obc);

	/**
	* Returns an ordered range of all the web content articles matching the
	* group and article ID.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @param obc the comparator to order the web content articles
	* @return the range of matching web content articles ordered by the
	comparator
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticle> getArticlesByArticleId(long groupId,
		java.lang.String articleId, int start, int end,
		OrderByComparator<JournalArticle> obc);

	/**
	* Returns all the web content articles matching the group and layout UUID.
	*
	* @param groupId the primary key of the web content article's group
	* @param layoutUuid the unique string identifying the web content
	article's display page
	* @return the matching web content articles
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticle> getArticlesByLayoutUuid(long groupId,
		java.lang.String layoutUuid);

	/**
	* Returns an ordered range of all the web content articles matching the
	* group, default class name ID, and DDM structure key.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticle> getArticlesByStructureId(long groupId,
		java.lang.String ddmStructureKey, int start, int end,
		OrderByComparator<JournalArticle> obc);

	/**
	* Returns an ordered range of all the web content articles matching the
	* group, default class name ID, and DDM structure key.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticle> getArticlesByStructureId(long groupId,
		java.lang.String ddmStructureKey, int status, int start, int end,
		OrderByComparator<JournalArticle> obc);

	/**
	* Returns an ordered range of all the web content articles matching the
	* group, class name ID, DDM structure key, and workflow status.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param groupId the primary key of the web content article's group
	* @param classNameId the primary key of the DDMStructure class if the web
	content article is related to a DDM structure, the primary key of
	the class name associated with the article, or
	JournalArticleConstants.CLASSNAME_ID_DEFAULT in the journal-api
	module otherwise
	* @param ddmStructureKey the primary key of the web content article's DDM
	structure
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @param obc the comparator to order the web content articles
	* @return the range of matching web content articles ordered by the
	comparator
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticle> getArticlesByStructureId(long groupId,
		long classNameId, java.lang.String ddmStructureKey, int status,
		int start, int end, OrderByComparator<JournalArticle> obc);

	/**
	* Returns an ordered range of all the web content articles matching the
	* group, user, the root folder or any of its subfolders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param groupId the primary key of the web content article's group
	* @param userId the primary key of the user (optionally <code>0</code>)
	* @param rootFolderId the primary key of the root folder to begin the
	search
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @param orderByComparator the comparator to order the web content
	articles
	* @return the range of matching web content articles ordered by the
	comparator
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticle> getGroupArticles(long groupId, long userId,
		long rootFolderId, int start, int end,
		OrderByComparator<JournalArticle> orderByComparator)
		throws PortalException;

	/**
	* Returns an ordered range of all the web content articles matching the
	* group, user, the root folder or any of its subfolders.
	*
	* @param groupId the primary key of the web content article's group
	* @param userId the primary key of the user (optionally <code>0</code>)
	* @param rootFolderId the primary key of the root folder to begin the
	search
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @param orderByComparator the comparator to order the web content
	articles
	* @return the range of matching web content articles ordered by the
	comparator
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticle> getGroupArticles(long groupId, long userId,
		long rootFolderId, int status, boolean includeOwner, int start,
		int end, OrderByComparator<JournalArticle> orderByComparator)
		throws PortalException;

	/**
	* Returns an ordered range of all the web content articles matching the
	* group, user, the root folder or any of its subfolders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param groupId the primary key of the web content article's group
	* @param userId the primary key of the user (optionally <code>0</code>)
	* @param rootFolderId the primary key of the root folder to begin the
	search
	* @param status the web content article's workflow status. For more
	information see {@link WorkflowConstants} for constants starting
	with the "STATUS_" prefix.
	* @param start the lower bound of the range of web content articles to
	return
	* @param end the upper bound of the range of web content articles to
	return (not inclusive)
	* @param orderByComparator the comparator to order the web content
	articles
	* @return the range of matching web content articles ordered by the
	comparator
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticle> getGroupArticles(long groupId, long userId,
		long rootFolderId, int status, int start, int end,
		OrderByComparator<JournalArticle> orderByComparator)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticle> getLayoutArticles(long groupId);

	/**
	* Returns an ordered range of all the web content articles matching the
	* parameters, including keyword parameters for article ID, title,
	* description, and content, a DDM structure key parameter, a DDM template
	* key parameter, and an AND operator switch.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the web content article's company
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param folderIds the primary keys of the web content article folders
	(optionally {@link java.util.Collections#EMPTY_LIST})
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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticle> search(long companyId, long groupId,
		List<java.lang.Long> folderIds, long classNameId,
		java.lang.String articleId, java.lang.Double version,
		java.lang.String title, java.lang.String description,
		java.lang.String content, java.lang.String ddmStructureKey,
		java.lang.String ddmTemplateKey, Date displayDateGT,
		Date displayDateLT, int status, Date reviewDate, boolean andOperator,
		int start, int end, OrderByComparator<JournalArticle> obc);

	/**
	* Returns an ordered range of all the web content articles matching the
	* parameters, including keyword parameters for article ID, title,
	* description, and content, a DDM structure keys (plural) parameter, a DDM
	* template keys (plural) parameter, and an AND operator switch.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the web content article's company
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param folderIds the primary keys of the web content article folders
	(optionally {@link java.util.Collections#EMPTY_LIST})
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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticle> search(long companyId, long groupId,
		List<java.lang.Long> folderIds, long classNameId,
		java.lang.String articleId, java.lang.Double version,
		java.lang.String title, java.lang.String description,
		java.lang.String content, java.lang.String[] ddmStructureKeys,
		java.lang.String[] ddmTemplateKeys, Date displayDateGT,
		Date displayDateLT, int status, Date reviewDate, boolean andOperator,
		int start, int end, OrderByComparator<JournalArticle> obc);

	/**
	* Returns an ordered range of all the web content articles matching the
	* parameters, including a keywords parameter for matching with the
	* article's ID, title, description, and content, a DDM structure key
	* parameter, and a DDM template key parameter.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	* primary keys, they are indexes in the result set. Thus, <code>0</code>
	* refers to the first result in the set. Setting both <code>start</code>
	* and <code>end</code> to {@link
	* com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	* result set.
	* </p>
	*
	* @param companyId the primary key of the web content article's company
	* @param groupId the primary key of the group (optionally <code>0</code>)
	* @param folderIds the primary keys of the web content article folders
	(optionally {@link java.util.Collections#EMPTY_LIST})
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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<JournalArticle> search(long companyId, long groupId,
		List<java.lang.Long> folderIds, long classNameId,
		java.lang.String keywords, java.lang.Double version,
		java.lang.String ddmStructureKey, java.lang.String ddmTemplateKey,
		Date displayDateGT, Date displayDateLT, int status, Date reviewDate,
		int start, int end, OrderByComparator<JournalArticle> obc);

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
	*/
	public void deleteArticle(long groupId, java.lang.String articleId,
		double version, java.lang.String articleURL,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Deletes all web content articles and their resources matching the group
	* and article ID, optionally sending email notifying denial of article if
	* it had not yet been approved.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param articleURL the web content article's accessible URL
	* @param serviceContext the service context to be applied. Can set the
	portlet preferences that include email information to notify
	recipients of the unapproved web content article's denial.
	*/
	public void deleteArticle(long groupId, java.lang.String articleId,
		java.lang.String articleURL, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Expires the web content article matching the group and article ID,
	* expiring all of its versions if the
	* <code>journal.article.expire.all.versions</code> portal property is
	* <code>true</code>, otherwise expiring only its latest approved version.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param articleURL the web content article's accessible URL
	* @param serviceContext the service context to be applied. Can set the
	modification date, status date, portlet preferences, and can set
	whether to add the default command update for the web content
	article. With respect to social activities, by setting the service
	context's command to {@link
	com.liferay.portal.kernel.util.Constants#UPDATE}, the invocation
	is considered a web content update activity; otherwise it is
	considered a web content add activity.
	*/
	public void expireArticle(long groupId, java.lang.String articleId,
		java.lang.String articleURL, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Moves all versions of the web content article matching the group and
	* article ID to the folder.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param newFolderId the primary key of the web content article's new
	folder
	* @deprecated As of 7.0.0, replaced by {@link #moveArticle(long, String,
	long, ServiceContext)}
	*/
	@java.lang.Deprecated
	public void moveArticle(long groupId, java.lang.String articleId,
		long newFolderId) throws PortalException;

	/**
	* Moves all versions of the web content article matching the group and
	* article ID to the folder.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	* @param newFolderId the primary key of the web content article's new
	folder
	* @param serviceContext the service context to be applied. Can set the user
	ID, language ID, portlet preferences, portlet request, portlet
	response, theme display, and can set whether to add the default
	command update for the web content article. With respect to social
	activities, by setting the service context's command to {@link
	com.liferay.portal.kernel.util.Constants#UPDATE}, the invocation
	is considered a web content update activity; otherwise it is
	considered a web content add activity.
	*/
	public void moveArticle(long groupId, java.lang.String articleId,
		long newFolderId, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Removes the web content of all the company's web content articles
	* matching the language.
	*
	* @param companyId the primary key of the web content article's company
	* @param languageId the primary key of the language locale to remove
	*/
	public void removeArticleLocale(long companyId, java.lang.String languageId)
		throws PortalException;

	/**
	* Restores the web content article from the Recycle Bin.
	*
	* @param groupId the primary key of the web content article's group
	* @param articleId the primary key of the web content article
	*/
	public void restoreArticleFromTrash(long groupId, java.lang.String articleId)
		throws PortalException;

	/**
	* Restores the web content article associated with the resource primary key
	* from the Recycle Bin.
	*
	* @param resourcePrimKey the primary key of the resource instance
	*/
	public void restoreArticleFromTrash(long resourcePrimKey)
		throws PortalException;

	/**
	* Subscribes the user to changes in elements that belong to the web content
	* article's DDM structure.
	*
	* @param groupId the primary key of the folder's group
	* @param userId the primary key of the user to be subscribed
	* @param ddmStructureId the primary key of the structure to subscribe to
	*/
	public void subscribeStructure(long groupId, long userId,
		long ddmStructureId) throws PortalException;

	/**
	* Unsubscribes the user from changes in elements that belong to the web
	* content article's DDM structure.
	*
	* @param groupId the primary key of the folder's group
	* @param userId the primary key of the user to be subscribed
	* @param ddmStructureId the primary key of the structure to subscribe to
	*/
	public void unsubscribeStructure(long groupId, long userId,
		long ddmStructureId) throws PortalException;
}