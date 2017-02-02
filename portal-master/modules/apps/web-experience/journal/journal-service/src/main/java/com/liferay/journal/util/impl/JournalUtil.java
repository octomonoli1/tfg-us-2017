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

package com.liferay.journal.util.impl;

import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.journal.configuration.JournalGroupServiceConfiguration;
import com.liferay.journal.configuration.JournalServiceConfigurationKeys;
import com.liferay.journal.configuration.JournalServiceConfigurationValues;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleDisplay;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.model.JournalFolderConstants;
import com.liferay.journal.model.JournalStructureConstants;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalArticleServiceUtil;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.journal.transformer.JournalTransformer;
import com.liferay.journal.util.comparator.ArticleVersionComparator;
import com.liferay.petra.collection.stack.FiniteUniqueStack;
import com.liferay.petra.xml.XMLUtil;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.diff.CompareVersionsException;
import com.liferay.portal.kernel.diff.DiffHtmlUtil;
import com.liferay.portal.kernel.diff.DiffVersion;
import com.liferay.portal.kernel.diff.DiffVersionsInfo;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.PortletRequestModel;
import com.liferay.portal.kernel.portlet.ThemeDisplayModel;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ImageLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateHandlerRegistryUtil;
import com.liferay.portal.kernel.template.TemplateManager;
import com.liferay.portal.kernel.template.TemplateManagerUtil;
import com.liferay.portal.kernel.templateparser.TransformerListener;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.xml.XPath;
import com.liferay.portal.util.PropsUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Wesley Gong
 * @author Angelo Jefferson
 * @author Hugo Huijser
 */
public class JournalUtil {

	public static final int MAX_STACK_SIZE = 20;

	public static final String[] SELECTED_FIELD_NAMES =
		{Field.ARTICLE_ID, Field.COMPANY_ID, Field.GROUP_ID, Field.UID};

	public static void addAllReservedEls(
		Element rootElement, Map<String, String> tokens, JournalArticle article,
		String languageId, ThemeDisplay themeDisplay) {

		addReservedEl(
			rootElement, tokens, JournalStructureConstants.RESERVED_ARTICLE_ID,
			article.getArticleId());

		addReservedEl(
			rootElement, tokens,
			JournalStructureConstants.RESERVED_ARTICLE_VERSION,
			article.getVersion());

		addReservedEl(
			rootElement, tokens,
			JournalStructureConstants.RESERVED_ARTICLE_TITLE,
			article.getTitle(languageId));

		addReservedEl(
			rootElement, tokens,
			JournalStructureConstants.RESERVED_ARTICLE_URL_TITLE,
			article.getUrlTitle());

		addReservedEl(
			rootElement, tokens,
			JournalStructureConstants.RESERVED_ARTICLE_DESCRIPTION,
			article.getDescription(languageId));

		addReservedEl(
			rootElement, tokens,
			JournalStructureConstants.RESERVED_ARTICLE_CREATE_DATE,
			article.getCreateDate());

		addReservedEl(
			rootElement, tokens,
			JournalStructureConstants.RESERVED_ARTICLE_MODIFIED_DATE,
			article.getModifiedDate());

		if (article.getDisplayDate() != null) {
			addReservedEl(
				rootElement, tokens,
				JournalStructureConstants.RESERVED_ARTICLE_DISPLAY_DATE,
				article.getDisplayDate());
		}

		String smallImageURL = StringPool.BLANK;

		if (Validator.isNotNull(article.getSmallImageURL())) {
			smallImageURL = article.getSmallImageURL();
		}
		else if ((themeDisplay != null) && article.isSmallImage()) {
			StringBundler sb = new StringBundler(5);

			sb.append(themeDisplay.getPathImage());
			sb.append("/journal/article?img_id=");
			sb.append(article.getSmallImageId());
			sb.append("&t=");
			sb.append(
				WebServerServletTokenUtil.getToken(article.getSmallImageId()));

			smallImageURL = sb.toString();
		}

		addReservedEl(
			rootElement, tokens,
			JournalStructureConstants.RESERVED_ARTICLE_SMALL_IMAGE_URL,
			smallImageURL);

		String[] assetTagNames = AssetTagLocalServiceUtil.getTagNames(
			JournalArticle.class.getName(), article.getResourcePrimKey());

		addReservedEl(
			rootElement, tokens,
			JournalStructureConstants.RESERVED_ARTICLE_ASSET_TAG_NAMES,
			StringUtil.merge(assetTagNames));

		addReservedEl(
			rootElement, tokens,
			JournalStructureConstants.RESERVED_ARTICLE_AUTHOR_ID,
			String.valueOf(article.getUserId()));

		String userName = StringPool.BLANK;
		String userEmailAddress = StringPool.BLANK;
		String userComments = StringPool.BLANK;
		String userJobTitle = StringPool.BLANK;

		User user = UserLocalServiceUtil.fetchUserById(article.getUserId());

		if (user != null) {
			userName = user.getFullName();
			userEmailAddress = user.getEmailAddress();
			userComments = user.getComments();
			userJobTitle = user.getJobTitle();
		}

		addReservedEl(
			rootElement, tokens,
			JournalStructureConstants.RESERVED_ARTICLE_AUTHOR_NAME, userName);

		addReservedEl(
			rootElement, tokens,
			JournalStructureConstants.RESERVED_ARTICLE_AUTHOR_EMAIL_ADDRESS,
			userEmailAddress);

		addReservedEl(
			rootElement, tokens,
			JournalStructureConstants.RESERVED_ARTICLE_AUTHOR_COMMENTS,
			userComments);

		addReservedEl(
			rootElement, tokens,
			JournalStructureConstants.RESERVED_ARTICLE_AUTHOR_JOB_TITLE,
			userJobTitle);
	}

	public static void addRecentArticle(
		PortletRequest portletRequest, JournalArticle article) {

		if (article != null) {
			Stack<JournalArticle> stack = getRecentArticles(portletRequest);

			stack.push(article);
		}
	}

	public static void addRecentDDMStructure(
		PortletRequest portletRequest, DDMStructure ddmStructure) {

		if (ddmStructure != null) {
			Stack<DDMStructure> stack = getRecentDDMStructures(portletRequest);

			stack.push(ddmStructure);
		}
	}

	public static void addRecentDDMTemplate(
		PortletRequest portletRequest, DDMTemplate ddmTemplate) {

		if (ddmTemplate != null) {
			Stack<DDMTemplate> stack = getRecentDDMTemplates(portletRequest);

			stack.push(ddmTemplate);
		}
	}

	public static void addReservedEl(
		Element rootElement, Map<String, String> tokens, String name,
		Date value) {

		addReservedEl(rootElement, tokens, name, Time.getRFC822(value));
	}

	public static void addReservedEl(
		Element rootElement, Map<String, String> tokens, String name,
		double value) {

		addReservedEl(rootElement, tokens, name, String.valueOf(value));
	}

	public static void addReservedEl(
		Element rootElement, Map<String, String> tokens, String name,
		String value) {

		// XML

		if (rootElement != null) {
			Element dynamicElementElement = rootElement.addElement(
				"dynamic-element");

			dynamicElementElement.addAttribute("name", name);

			dynamicElementElement.addAttribute("type", "text");

			Element dynamicContentElement = dynamicElementElement.addElement(
				"dynamic-content");

			//dynamicContentElement.setText("<![CDATA[" + value + "]]>");
			dynamicContentElement.setText(value);
		}

		// Tokens

		tokens.put(
			StringUtil.replace(name, CharPool.DASH, CharPool.UNDERLINE), value);
	}

	public static String diffHtml(
			long groupId, String articleId, double sourceVersion,
			double targetVersion, String languageId,
			PortletRequestModel portletRequestModel, ThemeDisplay themeDisplay)
		throws Exception {

		JournalArticle sourceArticle =
			JournalArticleLocalServiceUtil.getArticle(
				groupId, articleId, sourceVersion);

		if (!JournalArticleLocalServiceUtil.isRenderable(
				sourceArticle, portletRequestModel, themeDisplay)) {

			throw new CompareVersionsException(sourceVersion);
		}

		JournalArticleDisplay sourceArticleDisplay =
			JournalArticleLocalServiceUtil.getArticleDisplay(
				sourceArticle, null, Constants.VIEW, languageId, 1,
				portletRequestModel, themeDisplay);

		JournalArticle targetArticle =
			JournalArticleLocalServiceUtil.getArticle(
				groupId, articleId, targetVersion);

		if (!JournalArticleLocalServiceUtil.isRenderable(
				targetArticle, portletRequestModel, themeDisplay)) {

			throw new CompareVersionsException(targetVersion);
		}

		JournalArticleDisplay targetArticleDisplay =
			JournalArticleLocalServiceUtil.getArticleDisplay(
				targetArticle, null, Constants.VIEW, languageId, 1,
				portletRequestModel, themeDisplay);

		return DiffHtmlUtil.diff(
			new UnsyncStringReader(sourceArticleDisplay.getContent()),
			new UnsyncStringReader(targetArticleDisplay.getContent()));
	}

	public static String formatVM(String vm) {
		return vm;
	}

	public static String getAbsolutePath(
			PortletRequest portletRequest, long folderId)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (folderId == JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return themeDisplay.translate("home");
		}

		JournalFolder folder = JournalFolderLocalServiceUtil.getFolder(
			folderId);

		List<JournalFolder> folders = folder.getAncestors();

		Collections.reverse(folders);

		StringBundler sb = new StringBundler((folders.size() * 3) + 5);

		sb.append(themeDisplay.translate("home"));
		sb.append(StringPool.SPACE);

		for (JournalFolder curFolder : folders) {
			sb.append(StringPool.RAQUO_CHAR);
			sb.append(StringPool.SPACE);
			sb.append(curFolder.getName());
		}

		sb.append(StringPool.RAQUO_CHAR);
		sb.append(StringPool.SPACE);
		sb.append(folder.getName());

		return sb.toString();
	}

	public static Layout getArticleLayout(String layoutUuid, long groupId) {
		if (Validator.isNull(layoutUuid)) {
			return null;
		}

		// The target page and the article must belong to the same group

		Layout layout = LayoutLocalServiceUtil.fetchLayoutByUuidAndGroupId(
			layoutUuid, groupId, false);

		if (layout == null) {
			layout = LayoutLocalServiceUtil.fetchLayoutByUuidAndGroupId(
				layoutUuid, groupId, true);
		}

		return layout;
	}

	public static List<JournalArticle> getArticles(Hits hits)
		throws PortalException {

		List<com.liferay.portal.kernel.search.Document> documents =
			hits.toList();

		List<JournalArticle> articles = new ArrayList<>(documents.size());

		for (com.liferay.portal.kernel.search.Document document : documents) {
			String articleId = document.get(Field.ARTICLE_ID);
			long groupId = GetterUtil.getLong(document.get(Field.GROUP_ID));

			JournalArticle article =
				JournalArticleLocalServiceUtil.fetchLatestArticle(
					groupId, articleId, WorkflowConstants.STATUS_APPROVED);

			if (article == null) {
				articles = null;

				Indexer<JournalArticle> indexer =
					IndexerRegistryUtil.getIndexer(JournalArticle.class);

				long companyId = GetterUtil.getLong(
					document.get(Field.COMPANY_ID));

				indexer.delete(companyId, document.getUID());
			}
			else if (articles != null) {
				articles.add(article);
			}
		}

		return articles;
	}

	public static DiffVersionsInfo getDiffVersionsInfo(
		long groupId, String articleId, double sourceVersion,
		double targetVersion) {

		double previousVersion = 0;
		double nextVersion = 0;

		List<JournalArticle> articles =
			JournalArticleServiceUtil.getArticlesByArticleId(
				groupId, articleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new ArticleVersionComparator(true));

		for (JournalArticle article : articles) {
			if ((article.getVersion() < sourceVersion) &&
				(article.getVersion() > previousVersion)) {

				previousVersion = article.getVersion();
			}

			if ((article.getVersion() > targetVersion) &&
				((article.getVersion() < nextVersion) || (nextVersion == 0))) {

				nextVersion = article.getVersion();
			}
		}

		List<DiffVersion> diffVersions = new ArrayList<>();

		for (JournalArticle article : articles) {
			DiffVersion diffVersion = new DiffVersion(
				article.getUserId(), article.getVersion(),
				article.getModifiedDate());

			diffVersions.add(diffVersion);
		}

		return new DiffVersionsInfo(diffVersions, nextVersion, previousVersion);
	}

	public static boolean getEmailArticleAnyEventEnabled(
		JournalGroupServiceConfiguration journalGroupServiceConfiguration) {

		if (journalGroupServiceConfiguration.emailArticleAddedEnabled() ||
			journalGroupServiceConfiguration.
				emailArticleApprovalDeniedEnabled() ||
			journalGroupServiceConfiguration.
				emailArticleApprovalGrantedEnabled() ||
			journalGroupServiceConfiguration.
				emailArticleApprovalRequestedEnabled() ||
			journalGroupServiceConfiguration.emailArticleReviewEnabled() ||
			journalGroupServiceConfiguration.emailArticleUpdatedEnabled()) {

			return true;
		}

		return false;
	}

	public static Map<String, String> getEmailDefinitionTerms(
		PortletRequest portletRequest, String emailFromAddress,
		String emailFromName) {

		return getEmailDefinitionTerms(
			portletRequest, emailFromAddress, emailFromName, StringPool.BLANK);
	}

	public static Map<String, String> getEmailDefinitionTerms(
		PortletRequest portletRequest, String emailFromAddress,
		String emailFromName, String emailType) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String fromAddress = HtmlUtil.escape(emailFromAddress);
		String fromName = HtmlUtil.escape(emailFromName);
		String toAddress = LanguageUtil.get(
			themeDisplay.getLocale(), "the-address-of-the-email-recipient");
		String toName = LanguageUtil.get(
			themeDisplay.getLocale(), "the-name-of-the-email-recipient");

		if (emailType.equals("requested")) {
			toName = fromName;
			toAddress = fromAddress;

			fromName = LanguageUtil.get(
				themeDisplay.getLocale(), "the-name-of-the-email-sender");
			fromAddress = LanguageUtil.get(
				themeDisplay.getLocale(), "the-address-of-the-email-sender");
		}

		Map<String, String> definitionTerms = new LinkedHashMap<>();

		definitionTerms.put(
			"[$ARTICLE_CONTENT]",
			LanguageUtil.get(themeDisplay.getLocale(), "the-web-content"));
		definitionTerms.put(
			"[$ARTICLE_DIFFS$]",
			LanguageUtil.get(
				themeDisplay.getLocale(),
				"the-web-content-compared-with-the-previous-version-web-" +
					"content"));
		definitionTerms.put(
			"[$ARTICLE_ID$]",
			LanguageUtil.get(themeDisplay.getLocale(), "the-web-content-id"));
		definitionTerms.put(
			"[$ARTICLE_TITLE$]",
			LanguageUtil.get(
				themeDisplay.getLocale(), "the-web-content-title"));
		definitionTerms.put(
			"[$ARTICLE_URL$]",
			LanguageUtil.get(themeDisplay.getLocale(), "the-web-content-url"));
		definitionTerms.put(
			"[$ARTICLE_VERSION$]",
			LanguageUtil.get(
				themeDisplay.getLocale(), "the-web-content-version"));
		definitionTerms.put("[$FROM_ADDRESS$]", fromAddress);
		definitionTerms.put("[$FROM_NAME$]", fromName);

		Company company = themeDisplay.getCompany();

		definitionTerms.put("[$PORTAL_URL$]", company.getVirtualHostname());

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		definitionTerms.put(
			"[$PORTLET_NAME$]", HtmlUtil.escape(portletDisplay.getTitle()));

		definitionTerms.put("[$TO_ADDRESS$]", toAddress);
		definitionTerms.put("[$TO_NAME$]", toName);

		return definitionTerms;
	}

	public static String getJournalControlPanelLink(
			PortletRequest portletRequest, long folderId)
		throws PortalException {

		PortletURL portletURL = PortletProviderUtil.getPortletURL(
			portletRequest, JournalArticle.class.getName(),
			PortletProvider.Action.EDIT);

		portletURL.setParameter("folderId", String.valueOf(folderId));

		return portletURL.toString();
	}

	public static long getPreviewPlid(
			JournalArticle article, ThemeDisplay themeDisplay)
		throws Exception {

		if (article != null) {
			Layout layout = article.getLayout();

			if (layout != null) {
				return layout.getPlid();
			}
		}

		Layout layout = LayoutLocalServiceUtil.fetchFirstLayout(
			themeDisplay.getScopeGroupId(), false,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

		if (layout == null) {
			layout = LayoutLocalServiceUtil.fetchFirstLayout(
				themeDisplay.getScopeGroupId(), true,
				LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);
		}

		if (layout != null) {
			return layout.getPlid();
		}

		return themeDisplay.getPlid();
	}

	public static Stack<JournalArticle> getRecentArticles(
		PortletRequest portletRequest) {

		PortletSession portletSession = portletRequest.getPortletSession();

		Stack<JournalArticle> recentArticles =
			(Stack<JournalArticle>)portletSession.getAttribute(
				WebKeys.JOURNAL_RECENT_ARTICLES);

		if (recentArticles == null) {
			recentArticles = new FiniteUniqueStack<>(MAX_STACK_SIZE);

			portletSession.setAttribute(
				WebKeys.JOURNAL_RECENT_ARTICLES, recentArticles);
		}

		return recentArticles;
	}

	public static Stack<DDMStructure> getRecentDDMStructures(
		PortletRequest portletRequest) {

		PortletSession portletSession = portletRequest.getPortletSession();

		Stack<DDMStructure> recentDDMStructures =
			(Stack<DDMStructure>)portletSession.getAttribute(
				WebKeys.JOURNAL_RECENT_DYNAMIC_DATA_MAPPING_STRUCTURES);

		if (recentDDMStructures == null) {
			recentDDMStructures = new FiniteUniqueStack<>(MAX_STACK_SIZE);

			portletSession.setAttribute(
				WebKeys.JOURNAL_RECENT_DYNAMIC_DATA_MAPPING_STRUCTURES,
				recentDDMStructures);
		}

		return recentDDMStructures;
	}

	public static Stack<DDMTemplate> getRecentDDMTemplates(
		PortletRequest portletRequest) {

		PortletSession portletSession = portletRequest.getPortletSession();

		Stack<DDMTemplate> recentDDMTemplates =
			(Stack<DDMTemplate>)portletSession.getAttribute(
				WebKeys.JOURNAL_RECENT_DYNAMIC_DATA_MAPPING_TEMPLATES);

		if (recentDDMTemplates == null) {
			recentDDMTemplates = new FiniteUniqueStack<>(MAX_STACK_SIZE);

			portletSession.setAttribute(
				WebKeys.JOURNAL_RECENT_DYNAMIC_DATA_MAPPING_TEMPLATES,
				recentDDMTemplates);
		}

		return recentDDMTemplates;
	}

	public static int getRestrictionType(long folderId) {
		int restrictionType = JournalFolderConstants.RESTRICTION_TYPE_INHERIT;

		JournalFolder folder = JournalFolderLocalServiceUtil.fetchFolder(
			folderId);

		if (folder != null) {
			restrictionType = folder.getRestrictionType();
		}

		return restrictionType;
	}

	public static String getTemplateScript(
		DDMTemplate ddmTemplate, Map<String, String> tokens, String languageId,
		boolean transform) {

		String script = ddmTemplate.getScript();

		if (!transform) {
			return script;
		}

		String[] transformerListenerClassNames = PropsUtil.getArray(
			JournalServiceConfigurationKeys.TRANSFORMER_LISTENER);

		for (String transformerListenerClassName :
				transformerListenerClassNames) {

			TransformerListener transformerListener = null;

			try {
				transformerListener =
					(TransformerListener)InstanceFactory.newInstance(
						transformerListenerClassName);

				continue;
			}
			catch (Exception e) {
				_log.error(e, e);
			}

			script = transformerListener.onScript(
				script, (Document)null, languageId, tokens);
		}

		return script;
	}

	public static String getTemplateScript(
			long groupId, String ddmTemplateKey, Map<String, String> tokens,
			String languageId)
		throws PortalException {

		return getTemplateScript(
			groupId, ddmTemplateKey, tokens, languageId, true);
	}

	public static String getTemplateScript(
			long groupId, String ddmTemplateKey, Map<String, String> tokens,
			String languageId, boolean transform)
		throws PortalException {

		DDMTemplate ddmTemplate = DDMTemplateLocalServiceUtil.getTemplate(
			groupId, PortalUtil.getClassNameId(DDMStructure.class),
			ddmTemplateKey, true);

		return getTemplateScript(ddmTemplate, tokens, languageId, transform);
	}

	public static Map<String, String> getTokens(
			long articleGroupId, PortletRequestModel portletRequestModel,
			ThemeDisplay themeDisplay)
		throws PortalException {

		Map<String, String> tokens = new HashMap<>();

		if (themeDisplay != null) {
			_populateTokens(tokens, articleGroupId, themeDisplay);
		}
		else if (portletRequestModel != null) {
			ThemeDisplayModel themeDisplayModel =
				portletRequestModel.getThemeDisplayModel();

			if (themeDisplayModel != null) {
				try {
					_populateTokens(tokens, articleGroupId, themeDisplayModel);
				}
				catch (Exception e) {
					if (_log.isWarnEnabled()) {
						_log.warn(e, e);
					}
				}
			}
		}

		return tokens;
	}

	public static Map<String, String> getTokens(
			long articleGroupId, ThemeDisplay themeDisplay)
		throws PortalException {

		return getTokens(
			articleGroupId, (PortletRequestModel)null, themeDisplay);
	}

	public static String getUrlTitle(long id, String title) {
		if (title == null) {
			return String.valueOf(id);
		}

		title = StringUtil.toLowerCase(title.trim());

		if (Validator.isNull(title) || Validator.isNumber(title) ||
			title.equals("rss")) {

			title = String.valueOf(id);
		}
		else {
			title = FriendlyURLNormalizerUtil.normalizeWithPeriodsAndSlashes(
				title);
		}

		return ModelHintsUtil.trimString(
			JournalArticle.class.getName(), "urlTitle", title);
	}

	public static boolean isHead(JournalArticle article) {
		JournalArticle latestArticle =
			JournalArticleLocalServiceUtil.fetchLatestArticle(
				article.getResourcePrimKey(),
				new int[] {
					WorkflowConstants.STATUS_APPROVED,
					WorkflowConstants.STATUS_IN_TRASH
				});

		if ((latestArticle != null) && !latestArticle.isIndexable()) {
			return false;
		}
		else if ((latestArticle != null) &&
				 (article.getId() == latestArticle.getId())) {

			return true;
		}

		return false;
	}

	public static boolean isHeadListable(JournalArticle article) {
		JournalArticle latestArticle =
			JournalArticleLocalServiceUtil.fetchLatestArticle(
				article.getResourcePrimKey(),
				new int[] {
					WorkflowConstants.STATUS_APPROVED,
					WorkflowConstants.STATUS_IN_TRASH,
					WorkflowConstants.STATUS_SCHEDULED
				});

		if ((latestArticle != null) &&
			(article.getId() == latestArticle.getId())) {

			return true;
		}

		return false;
	}

	public static boolean isSubscribedToFolder(
			long companyId, long groupId, long userId, long folderId)
		throws PortalException {

		return isSubscribedToFolder(companyId, groupId, userId, folderId, true);
	}

	public static boolean isSubscribedToFolder(
			long companyId, long groupId, long userId, long folderId,
			boolean recursive)
		throws PortalException {

		List<Long> ancestorFolderIds = new ArrayList<>();

		if (folderId != JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			JournalFolder folder = JournalFolderLocalServiceUtil.getFolder(
				folderId);

			ancestorFolderIds.add(folderId);

			if (recursive) {
				ancestorFolderIds.addAll(folder.getAncestorFolderIds());

				ancestorFolderIds.add(groupId);
			}
		}
		else {
			ancestorFolderIds.add(groupId);
		}

		return SubscriptionLocalServiceUtil.isSubscribed(
			companyId, userId, JournalFolder.class.getName(),
			ArrayUtil.toLongArray(ancestorFolderIds));
	}

	public static boolean isSubscribedToStructure(
		long companyId, long groupId, long userId, long ddmStructureId) {

		return SubscriptionLocalServiceUtil.isSubscribed(
			companyId, userId, DDMStructure.class.getName(), ddmStructureId);
	}

	public static String mergeArticleContent(
		String curContent, String newContent, boolean removeNullElements) {

		try {
			Document curDocument = SAXReaderUtil.read(curContent);
			Document newDocument = SAXReaderUtil.read(newContent);

			Element curRootElement = curDocument.getRootElement();
			Element newRootElement = newDocument.getRootElement();

			curRootElement.addAttribute(
				"default-locale",
				newRootElement.attributeValue("default-locale"));
			curRootElement.addAttribute(
				"available-locales",
				newRootElement.attributeValue("available-locales"));

			_mergeArticleContentUpdate(
				curDocument, newRootElement,
				LocaleUtil.toLanguageId(LocaleUtil.getSiteDefault()));

			if (removeNullElements) {
				_mergeArticleContentDelete(curRootElement, newDocument);
			}

			curContent = XMLUtil.formatXML(curDocument);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return curContent;
	}

	public static String prepareLocalizedContentForImport(
			String content, Locale defaultImportLocale)
		throws LocaleException {

		try {
			Document oldDocument = SAXReaderUtil.read(content);

			Document newDocument = SAXReaderUtil.read(content);

			Element newRootElement = newDocument.getRootElement();

			Attribute availableLocalesAttribute = newRootElement.attribute(
				"available-locales");

			if (availableLocalesAttribute == null) {
				newRootElement = newRootElement.addAttribute(
					"available-locales", StringPool.BLANK);

				availableLocalesAttribute = newRootElement.attribute(
					"available-locales");
			}

			String defaultImportLanguageId = LocaleUtil.toLanguageId(
				defaultImportLocale);

			if (!StringUtil.contains(
					availableLocalesAttribute.getValue(),
					defaultImportLanguageId)) {

				if (Validator.isNull(availableLocalesAttribute.getValue())) {
					availableLocalesAttribute.setValue(defaultImportLanguageId);
				}
				else {
					availableLocalesAttribute.setValue(
						availableLocalesAttribute.getValue() +
							StringPool.COMMA + defaultImportLanguageId);
				}

				_mergeArticleContentUpdate(
					oldDocument, newRootElement,
					LocaleUtil.toLanguageId(defaultImportLocale));

				content = XMLUtil.formatXML(newDocument);
			}

			Attribute defaultLocaleAttribute = newRootElement.attribute(
				"default-locale");

			if (defaultLocaleAttribute == null) {
				newRootElement = newRootElement.addAttribute(
					"default-locale", StringPool.BLANK);

				defaultLocaleAttribute = newRootElement.attribute(
					"default-locale");
			}

			Locale defaultContentLocale = LocaleUtil.fromLanguageId(
				defaultLocaleAttribute.getValue());

			if (!LocaleUtil.equals(defaultContentLocale, defaultImportLocale)) {
				defaultLocaleAttribute.setValue(defaultImportLanguageId);

				content = XMLUtil.formatXML(newDocument);
			}
		}
		catch (Exception e) {
			throw new LocaleException(
				LocaleException.TYPE_CONTENT,
				"The locale " + defaultImportLocale + " is not available");
		}

		return content;
	}

	public static String removeArticleLocale(
		Document document, String content, String languageId) {

		try {
			Element rootElement = document.getRootElement();

			String availableLocales = rootElement.attributeValue(
				"available-locales");

			if (availableLocales == null) {
				return content;
			}

			availableLocales = StringUtil.removeFromList(
				availableLocales, languageId);

			if (availableLocales.endsWith(",")) {
				availableLocales = availableLocales.substring(
					0, availableLocales.length() - 1);
			}

			rootElement.addAttribute("available-locales", availableLocales);

			removeArticleLocale(rootElement, languageId);

			content = XMLUtil.formatXML(document);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return content;
	}

	public static void removeArticleLocale(Element element, String languageId)
		throws PortalException {

		for (Element dynamicElementElement :
				element.elements("dynamic-element")) {

			for (Element dynamicContentElement :
					dynamicElementElement.elements("dynamic-content")) {

				String curLanguageId = GetterUtil.getString(
					dynamicContentElement.attributeValue("language-id"));

				if (curLanguageId.equals(languageId)) {
					long id = GetterUtil.getLong(
						dynamicContentElement.attributeValue("id"));

					if (id > 0) {
						ImageLocalServiceUtil.deleteImage(id);
					}

					dynamicContentElement.detach();
				}
			}

			removeArticleLocale(dynamicElementElement, languageId);
		}
	}

	public static String removeOldContent(String content, String xsd) {
		try {
			Document contentDoc = SAXReaderUtil.read(content);
			Document xsdDoc = SAXReaderUtil.read(xsd);

			Element contentRoot = contentDoc.getRootElement();

			Stack<String> path = new Stack<>();

			path.push(contentRoot.getName());

			_removeOldContent(path, contentRoot, xsdDoc);

			content = XMLUtil.formatXML(contentDoc);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return content;
	}

	public static void removeRecentArticle(
		PortletRequest portletRequest, String articleId) {

		removeRecentArticle(portletRequest, articleId, 0);
	}

	public static void removeRecentArticle(
		PortletRequest portletRequest, String articleId, double version) {

		Stack<JournalArticle> stack = getRecentArticles(portletRequest);

		Iterator<JournalArticle> itr = stack.iterator();

		while (itr.hasNext()) {
			JournalArticle journalArticle = itr.next();

			if (journalArticle.getArticleId().equals(articleId) &&
				((journalArticle.getVersion() == version) || (version == 0))) {

				itr.remove();
			}
		}
	}

	public static void removeRecentDDMStructure(
		PortletRequest portletRequest, String ddmStructureKey) {

		Stack<DDMStructure> stack = getRecentDDMStructures(portletRequest);

		Iterator<DDMStructure> itr = stack.iterator();

		while (itr.hasNext()) {
			DDMStructure ddmStructure = itr.next();

			if (ddmStructureKey.equals(ddmStructure.getStructureKey())) {
				itr.remove();

				break;
			}
		}
	}

	public static void removeRecentDDMTemplate(
		PortletRequest portletRequest, String ddmTemplateKey) {

		Stack<DDMTemplate> stack = getRecentDDMTemplates(portletRequest);

		Iterator<DDMTemplate> itr = stack.iterator();

		while (itr.hasNext()) {
			DDMTemplate ddmTemplate = itr.next();

			if (ddmTemplateKey.equals(ddmTemplate.getTemplateKey())) {
				itr.remove();

				break;
			}
		}
	}

	public static String transform(
			ThemeDisplay themeDisplay, Map<String, String> tokens,
			String viewMode, String languageId, Document document,
			PortletRequestModel portletRequestModel, String script,
			String langType)
		throws Exception {

		return transform(
			themeDisplay, tokens, viewMode, languageId, document,
			portletRequestModel, script, langType, false);
	}

	public static String transform(
			ThemeDisplay themeDisplay, Map<String, String> tokens,
			String viewMode, String languageId, Document document,
			PortletRequestModel portletRequestModel, String script,
			String langType, boolean propagateException)
		throws Exception {

		TemplateManager templateManager =
			TemplateManagerUtil.getTemplateManager(langType);

		TemplateHandler templateHandler =
			TemplateHandlerRegistryUtil.getTemplateHandler(
				JournalArticle.class.getName());

		Map<String, Object> contextObjects = new HashMap<>();

		templateManager.addContextObjects(
			contextObjects, templateHandler.getCustomContextObjects());

		return _journalTransformer.transform(
			themeDisplay, contextObjects, tokens, viewMode, languageId,
			document, portletRequestModel, script, langType,
			propagateException);
	}

	private static void _addElementOptions(
		Element curContentElement, Element newContentElement) {

		List<Element> newElementOptions = newContentElement.elements("option");

		for (Element newElementOption : newElementOptions) {
			Element curElementOption = SAXReaderUtil.createElement("option");

			curElementOption.addCDATA(newElementOption.getText());

			curContentElement.add(curElementOption);
		}
	}

	private static Element _getElementByInstanceId(
		Document document, String instanceId) {

		if (Validator.isNull(instanceId)) {
			return null;
		}

		XPath xPathSelector = SAXReaderUtil.createXPath(
			"//dynamic-element[@instance-id=" +
				HtmlUtil.escapeXPathAttribute(instanceId) + "]");

		List<Node> nodes = xPathSelector.selectNodes(document);

		if (nodes.size() == 1) {
			return (Element)nodes.get(0);
		}
		else {
			return null;
		}
	}

	private static void _mergeArticleContentDelete(
			Element curParentElement, Document newDocument)
		throws Exception {

		List<Element> curElements = curParentElement.elements(
			"dynamic-element");

		for (int i = 0; i < curElements.size(); i++) {
			Element curElement = curElements.get(i);

			_mergeArticleContentDelete(curElement, newDocument);

			String instanceId = curElement.attributeValue("instance-id");

			Element newElement = _getElementByInstanceId(
				newDocument, instanceId);

			if (newElement == null) {
				curElement.detach();
			}
		}
	}

	private static void _mergeArticleContentUpdate(
			Document curDocument, Element newParentElement, Element newElement,
			int pos, String defaultLocale)
		throws Exception {

		_mergeArticleContentUpdate(curDocument, newElement, defaultLocale);

		String instanceId = newElement.attributeValue("instance-id");

		Element curElement = _getElementByInstanceId(curDocument, instanceId);

		if (curElement != null) {
			_mergeArticleContentUpdate(curElement, newElement, defaultLocale);
		}
		else {
			String parentInstanceId = newParentElement.attributeValue(
				"instance-id");

			if (Validator.isNull(parentInstanceId)) {
				Element curRoot = curDocument.getRootElement();

				List<Element> curRootElements = curRoot.elements();

				curRootElements.add(pos, newElement.createCopy());
			}
			else {
				Element curParentElement = _getElementByInstanceId(
					curDocument, parentInstanceId);

				if (curParentElement != null) {
					List<Element> curParentElements =
						curParentElement.elements();

					curParentElements.add(pos, newElement.createCopy());
				}
			}
		}
	}

	private static void _mergeArticleContentUpdate(
			Document curDocument, Element newParentElement,
			String defaultLocale)
		throws Exception {

		List<Element> newElements = newParentElement.elements(
			"dynamic-element");

		for (int i = 0; i < newElements.size(); i++) {
			Element newElement = newElements.get(i);

			_mergeArticleContentUpdate(
				curDocument, newParentElement, newElement, i, defaultLocale);
		}
	}

	private static void _mergeArticleContentUpdate(
		Element curElement, Element newElement, String defaultLocale) {

		Attribute curTypeAttribute = curElement.attribute("type");
		Attribute newTypeAttribute = newElement.attribute("type");

		curTypeAttribute.setValue(newTypeAttribute.getValue());

		Attribute curIndexTypeAttribute = curElement.attribute("index-type");
		Attribute newIndexTypeAttribute = newElement.attribute("index-type");

		if (newIndexTypeAttribute != null) {
			if (curIndexTypeAttribute == null) {
				curElement.addAttribute(
					"index-type", newIndexTypeAttribute.getValue());
			}
			else {
				curIndexTypeAttribute.setValue(
					newIndexTypeAttribute.getValue());
			}
		}

		List<Element> elements = newElement.elements("dynamic-content");

		Element newContentElement = elements.get(0);

		String newLanguageId = newContentElement.attributeValue("language-id");
		String newValue = newContentElement.getText();

		String indexType = newElement.attributeValue("index-type");

		if (Validator.isNotNull(indexType)) {
			curElement.addAttribute("index-type", indexType);
		}

		List<Element> curContentElements = curElement.elements(
			"dynamic-content");

		if (Validator.isNull(newLanguageId)) {
			for (Element curContentElement : curContentElements) {
				curContentElement.detach();
			}

			Element curContentElement = SAXReaderUtil.createElement(
				"dynamic-content");

			if (newContentElement.element("option") != null) {
				_addElementOptions(curContentElement, newContentElement);
			}
			else {
				curContentElement.addCDATA(newValue);
			}

			curElement.add(curContentElement);
		}
		else {
			boolean alreadyExists = false;

			for (Element curContentElement : curContentElements) {
				String curLanguageId = curContentElement.attributeValue(
					"language-id");

				if (newLanguageId.equals(curLanguageId)) {
					alreadyExists = true;

					curContentElement.clearContent();

					if (newContentElement.element("option") != null) {
						_addElementOptions(
							curContentElement, newContentElement);
					}
					else {
						curContentElement.addCDATA(newValue);
					}

					break;
				}
			}

			if (!alreadyExists) {
				Element curContentElement = curContentElements.get(0);

				String curLanguageId = curContentElement.attributeValue(
					"language-id");

				if (Validator.isNull(curLanguageId)) {
					if (newLanguageId.equals(defaultLocale)) {
						curContentElement.clearContent();

						if (newContentElement.element("option") != null) {
							_addElementOptions(
								curContentElement, newContentElement);
						}
						else {
							curContentElement.addCDATA(newValue);
						}
					}
					else {
						curElement.add(newContentElement.createCopy());
					}

					curContentElement.addAttribute(
						"language-id", defaultLocale);
				}
				else {
					curElement.add(newContentElement.createCopy());
				}
			}
		}
	}

	private static void _populateCustomTokens(Map<String, String> tokens) {
		if (_customTokens == null) {
			synchronized (JournalUtil.class) {
				_customTokens = new HashMap<>();

				for (String customToken : JournalServiceConfigurationValues.
						JOURNAL_ARTICLE_CUSTOM_TOKENS) {

					String value = PropsUtil.get(
						JournalServiceConfigurationKeys.
							JOURNAL_ARTICLE_CUSTOM_TOKEN_VALUE,
						new Filter(customToken));

					_customTokens.put(customToken, value);
				}
			}
		}

		if (!_customTokens.isEmpty()) {
			tokens.putAll(_customTokens);
		}
	}

	private static void _populateTokens(
			Map<String, String> tokens, long articleGroupId,
			ThemeDisplay themeDisplay)
		throws PortalException {

		Layout layout = themeDisplay.getLayout();

		Group group = layout.getGroup();

		LayoutSet layoutSet = layout.getLayoutSet();

		String friendlyUrlCurrent = null;

		if (layout.isPublicLayout()) {
			friendlyUrlCurrent = themeDisplay.getPathFriendlyURLPublic();
		}
		else if (group.isUserGroup()) {
			friendlyUrlCurrent = themeDisplay.getPathFriendlyURLPrivateUser();
		}
		else {
			friendlyUrlCurrent = themeDisplay.getPathFriendlyURLPrivateGroup();
		}

		String layoutSetFriendlyUrl = themeDisplay.getI18nPath();

		String virtualHostname = layoutSet.getVirtualHostname();

		if (Validator.isNull(virtualHostname) ||
			!virtualHostname.equals(themeDisplay.getServerName())) {

			layoutSetFriendlyUrl = friendlyUrlCurrent + group.getFriendlyURL();
		}

		tokens.put("article_group_id", String.valueOf(articleGroupId));
		tokens.put("cdn_host", themeDisplay.getCDNHost());
		tokens.put("company_id", String.valueOf(themeDisplay.getCompanyId()));
		tokens.put("friendly_url_current", friendlyUrlCurrent);
		tokens.put(
			"friendly_url_private_group",
			themeDisplay.getPathFriendlyURLPrivateGroup());
		tokens.put(
			"friendly_url_private_user",
			themeDisplay.getPathFriendlyURLPrivateUser());
		tokens.put(
			"friendly_url_public", themeDisplay.getPathFriendlyURLPublic());
		tokens.put("group_friendly_url", group.getFriendlyURL());
		tokens.put("image_path", themeDisplay.getPathImage());
		tokens.put("layout_set_friendly_url", layoutSetFriendlyUrl);
		tokens.put("main_path", themeDisplay.getPathMain());
		tokens.put("portal_ctx", themeDisplay.getPathContext());
		tokens.put(
			"portal_url", HttpUtil.removeProtocol(themeDisplay.getURLPortal()));
		tokens.put(
			"protocol", HttpUtil.getProtocol(themeDisplay.getURLPortal()));
		tokens.put("root_path", themeDisplay.getPathContext());
		tokens.put(
			"site_group_id", String.valueOf(themeDisplay.getSiteGroupId()));
		tokens.put(
			"scope_group_id", String.valueOf(themeDisplay.getScopeGroupId()));
		tokens.put("theme_image_path", themeDisplay.getPathThemeImages());

		_populateCustomTokens(tokens);

		// Deprecated tokens

		tokens.put("friendly_url", themeDisplay.getPathFriendlyURLPublic());
		tokens.put(
			"friendly_url_private",
			themeDisplay.getPathFriendlyURLPrivateGroup());
		tokens.put("group_id", String.valueOf(articleGroupId));
		tokens.put("page_url", themeDisplay.getPathFriendlyURLPublic());
	}

	private static void _populateTokens(
			Map<String, String> tokens, long articleGroupId,
			ThemeDisplayModel themeDisplayModel)
		throws Exception {

		Layout layout = LayoutLocalServiceUtil.getLayout(
			themeDisplayModel.getPlid());

		Group group = layout.getGroup();

		LayoutSet layoutSet = layout.getLayoutSet();

		String friendlyUrlCurrent = null;

		if (layout.isPublicLayout()) {
			friendlyUrlCurrent = themeDisplayModel.getPathFriendlyURLPublic();
		}
		else if (group.isUserGroup()) {
			friendlyUrlCurrent =
				themeDisplayModel.getPathFriendlyURLPrivateUser();
		}
		else {
			friendlyUrlCurrent =
				themeDisplayModel.getPathFriendlyURLPrivateGroup();
		}

		String layoutSetFriendlyUrl = themeDisplayModel.getI18nPath();

		String virtualHostname = layoutSet.getVirtualHostname();

		if (Validator.isNull(virtualHostname) ||
			!virtualHostname.equals(themeDisplayModel.getServerName())) {

			layoutSetFriendlyUrl = friendlyUrlCurrent + group.getFriendlyURL();
		}

		tokens.put("article_group_id", String.valueOf(articleGroupId));
		tokens.put("cdn_host", themeDisplayModel.getCdnHost());
		tokens.put(
			"company_id", String.valueOf(themeDisplayModel.getCompanyId()));
		tokens.put("friendly_url_current", friendlyUrlCurrent);
		tokens.put(
			"friendly_url_private_group",
			themeDisplayModel.getPathFriendlyURLPrivateGroup());
		tokens.put(
			"friendly_url_private_user",
			themeDisplayModel.getPathFriendlyURLPrivateUser());
		tokens.put(
			"friendly_url_public",
			themeDisplayModel.getPathFriendlyURLPublic());
		tokens.put("group_friendly_url", group.getFriendlyURL());
		tokens.put("image_path", themeDisplayModel.getPathImage());
		tokens.put("layout_set_friendly_url", layoutSetFriendlyUrl);
		tokens.put("main_path", themeDisplayModel.getPathMain());
		tokens.put("portal_ctx", themeDisplayModel.getPathContext());
		tokens.put(
			"portal_url",
			HttpUtil.removeProtocol(themeDisplayModel.getURLPortal()));
		tokens.put(
			"protocol", HttpUtil.getProtocol(themeDisplayModel.getURLPortal()));
		tokens.put("root_path", themeDisplayModel.getPathContext());
		tokens.put(
			"scope_group_id",
			String.valueOf(themeDisplayModel.getScopeGroupId()));
		tokens.put("theme_image_path", themeDisplayModel.getPathThemeImages());

		_populateCustomTokens(tokens);

		// Deprecated tokens

		tokens.put(
			"friendly_url", themeDisplayModel.getPathFriendlyURLPublic());
		tokens.put(
			"friendly_url_private",
			themeDisplayModel.getPathFriendlyURLPrivateGroup());
		tokens.put("group_id", String.valueOf(articleGroupId));
		tokens.put("page_url", themeDisplayModel.getPathFriendlyURLPublic());
	}

	private static void _removeOldContent(
		Stack<String> path, Element contentElement, Document xsdDocument) {

		String elementPath = "";

		for (int i = 0; i < path.size(); i++) {
			elementPath += "/" + path.elementAt(i);
		}

		for (int i = 0; i < contentElement.nodeCount(); i++) {
			Node contentNode = contentElement.node(i);

			if (contentNode instanceof Element) {
				_removeOldContent(
					path, (Element)contentNode, xsdDocument, elementPath);
			}
		}
	}

	private static void _removeOldContent(
		Stack<String> path, Element contentElement, Document xsdDocument,
		String elementPath) {

		String name = contentElement.attributeValue("name");

		if (Validator.isNull(name)) {
			return;
		}

		String localPath =
			"dynamic-element[@name=" + HtmlUtil.escapeXPathAttribute(name) +
				"]";

		String fullPath = elementPath + "/" + localPath;

		XPath xPathSelector = SAXReaderUtil.createXPath(fullPath);

		List<Node> curNodes = xPathSelector.selectNodes(xsdDocument);

		if (curNodes.isEmpty()) {
			contentElement.detach();
		}

		path.push(localPath);

		_removeOldContent(path, contentElement, xsdDocument);

		path.pop();
	}

	private static final Log _log = LogFactoryUtil.getLog(JournalUtil.class);

	private static Map<String, String> _customTokens;
	private static final JournalTransformer _journalTransformer =
		new JournalTransformer(
			JournalServiceConfigurationKeys.TRANSFORMER_LISTENER,
			JournalServiceConfigurationKeys.ERROR_TEMPLATE, true);

}