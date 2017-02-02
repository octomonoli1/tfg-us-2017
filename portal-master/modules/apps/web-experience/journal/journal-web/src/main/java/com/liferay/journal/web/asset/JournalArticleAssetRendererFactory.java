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

package com.liferay.journal.web.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.asset.kernel.model.BaseDDMStructureClassTypeReader;
import com.liferay.asset.kernel.model.ClassTypeReader;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.permission.DDMStructurePermission;
import com.liferay.dynamic.data.mapping.util.FieldsToDDMFormValuesConverter;
import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleResource;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalArticleResourceLocalService;
import com.liferay.journal.service.JournalArticleService;
import com.liferay.journal.service.permission.JournalArticlePermission;
import com.liferay.journal.service.permission.JournalPermission;
import com.liferay.journal.util.JournalContent;
import com.liferay.journal.util.JournalConverter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Julio Camarero
 * @author Juan Fernández
 * @author Raymond Augé
 * @author Sergio González
 */
@Component(
	immediate = true,
	property = {"javax.portlet.name=" + JournalPortletKeys.JOURNAL},
	service = AssetRendererFactory.class
)
public class JournalArticleAssetRendererFactory
	extends BaseAssetRendererFactory<JournalArticle> {

	public static final String TYPE = "content";

	public JournalArticleAssetRendererFactory() {
		setClassName(JournalArticle.class.getName());
		setLinkable(true);
		setPortletId(JournalPortletKeys.JOURNAL);
		setSearchable(true);
		setSupportsClassTypes(true);
	}

	@Override
	public AssetRenderer<JournalArticle> getAssetRenderer(
			long classPK, int type)
		throws PortalException {

		JournalArticle article =
			_journalArticleLocalService.fetchJournalArticle(classPK);

		if (article == null) {
			JournalArticleResource articleResource =
				_journalArticleResourceLocalService.getArticleResource(classPK);

			if (type == TYPE_LATEST_APPROVED) {
				article = _journalArticleLocalService.fetchDisplayArticle(
					articleResource.getGroupId(),
					articleResource.getArticleId());
			}

			if (article == null) {
				article = _journalArticleLocalService.fetchLatestArticle(
					articleResource.getGroupId(),
					articleResource.getArticleId(),
					WorkflowConstants.STATUS_ANY);
			}

			if ((article == null) && (type == TYPE_LATEST)) {
				article = _journalArticleLocalService.fetchLatestArticle(
					classPK, WorkflowConstants.STATUS_ANY);
			}
		}

		JournalArticleAssetRenderer journalArticleAssetRenderer =
			getJournalArticleAssetRenderer(article);

		journalArticleAssetRenderer.setAssetRendererType(type);

		return journalArticleAssetRenderer;
	}

	@Override
	public AssetRenderer<JournalArticle> getAssetRenderer(
			long groupId, String urlTitle)
		throws PortalException {

		JournalArticle article =
			_journalArticleService.getDisplayArticleByUrlTitle(
				groupId, urlTitle);

		return getJournalArticleAssetRenderer(article);
	}

	@Override
	public String getClassName() {
		return JournalArticle.class.getName();
	}

	@Override
	public ClassTypeReader getClassTypeReader() {
		return new BaseDDMStructureClassTypeReader(getClassName());
	}

	@Override
	public String getIconCssClass() {
		return "web-content";
	}

	@Override
	public String getSubtypeTitle(Locale locale) {
		return LanguageUtil.get(locale, "structures");
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public String getTypeName(Locale locale, long subtypeId) {
		try {
			DDMStructure ddmStructure = _ddmStructureLocalService.getStructure(
				subtypeId);

			return ddmStructure.getName(locale);
		}
		catch (Exception e) {
			return super.getTypeName(locale, subtypeId);
		}
	}

	@Override
	public PortletURL getURLAdd(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse, long classTypeId) {

		PortletURL portletURL = PortalUtil.getControlPanelPortletURL(
			liferayPortletRequest, getGroup(liferayPortletRequest),
			JournalPortletKeys.JOURNAL, 0, 0, PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcPath", "/edit_article.jsp");

		if (classTypeId > 0) {
			DDMStructure ddmStructure =
				_ddmStructureLocalService.fetchDDMStructure(classTypeId);

			if (ddmStructure != null) {
				portletURL.setParameter(
					"ddmStructureKey", ddmStructure.getStructureKey());
			}
		}

		return portletURL;
	}

	@Override
	public PortletURL getURLView(
		LiferayPortletResponse liferayPortletResponse,
		WindowState windowState) {

		LiferayPortletURL liferayPortletURL =
			liferayPortletResponse.createLiferayPortletURL(
				JournalPortletKeys.JOURNAL, PortletRequest.RENDER_PHASE);

		try {
			liferayPortletURL.setWindowState(windowState);
		}
		catch (WindowStateException wse) {
		}

		return liferayPortletURL;
	}

	@Override
	public boolean hasAddPermission(
			PermissionChecker permissionChecker, long groupId, long classTypeId)
		throws Exception {

		if (classTypeId == 0) {
			return false;
		}

		if (!DDMStructurePermission.contains(
				permissionChecker, classTypeId, ActionKeys.VIEW)) {

			return false;
		}

		return JournalPermission.contains(
			permissionChecker, groupId, ActionKeys.ADD_ARTICLE);
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws Exception {

		return JournalArticlePermission.contains(
			permissionChecker, classPK, actionId);
	}

	@Reference(unbind = "-")
	public void setFieldsToDDMFormValuesConverter(
		FieldsToDDMFormValuesConverter fieldsToDDMFormValuesConverter) {

		_fieldsToDDMFormValuesConverter = fieldsToDDMFormValuesConverter;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.journal.web)", unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	protected JournalArticleAssetRenderer getJournalArticleAssetRenderer(
		JournalArticle article) {

		JournalArticleAssetRenderer journalArticleAssetRenderer =
			new JournalArticleAssetRenderer(article);

		journalArticleAssetRenderer.setFieldsToDDMFormValuesConverter(
			_fieldsToDDMFormValuesConverter);
		journalArticleAssetRenderer.setJournalContent(_journalContent);
		journalArticleAssetRenderer.setJournalConverter(_journalConverter);
		journalArticleAssetRenderer.setServletContext(_servletContext);

		return journalArticleAssetRenderer;
	}

	@Reference(unbind = "-")
	protected void setDDMStructureLocalService(
		DDMStructureLocalService ddmStructureLocalService) {

		_ddmStructureLocalService = ddmStructureLocalService;
	}

	@Reference(unbind = "-")
	protected void setJournalArticleLocalService(
		JournalArticleLocalService journalArticleLocalService) {

		_journalArticleLocalService = journalArticleLocalService;
	}

	@Reference(unbind = "-")
	protected void setJournalArticleResourceLocalService(
		JournalArticleResourceLocalService journalArticleResourceLocalService) {

		_journalArticleResourceLocalService =
			journalArticleResourceLocalService;
	}

	@Reference(unbind = "-")
	protected void setJournalArticleService(
		JournalArticleService journalArticleService) {

		_journalArticleService = journalArticleService;
	}

	@Reference(unbind = "-")
	protected void setJournalContent(JournalContent journalContent) {
		_journalContent = journalContent;
	}

	@Reference(unbind = "-")
	protected void setJournalConverter(JournalConverter journalConverter) {
		_journalConverter = journalConverter;
	}

	private DDMStructureLocalService _ddmStructureLocalService;
	private FieldsToDDMFormValuesConverter _fieldsToDDMFormValuesConverter;
	private JournalArticleLocalService _journalArticleLocalService;
	private JournalArticleResourceLocalService
		_journalArticleResourceLocalService;
	private JournalArticleService _journalArticleService;
	private JournalContent _journalContent;
	private JournalConverter _journalConverter;
	private ServletContext _servletContext;

}