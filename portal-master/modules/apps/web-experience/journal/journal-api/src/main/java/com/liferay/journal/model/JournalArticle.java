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

package com.liferay.journal.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.TreeModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the JournalArticle service. Represents a row in the &quot;JournalArticle&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see JournalArticleModel
 * @see com.liferay.journal.model.impl.JournalArticleImpl
 * @see com.liferay.journal.model.impl.JournalArticleModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.journal.model.impl.JournalArticleImpl")
@ProviderType
public interface JournalArticle extends JournalArticleModel, PersistedModel,
	TreeModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.journal.model.impl.JournalArticleImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<JournalArticle, Long> ID_ACCESSOR = new Accessor<JournalArticle, Long>() {
			@Override
			public Long get(JournalArticle journalArticle) {
				return journalArticle.getId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<JournalArticle> getTypeClass() {
				return JournalArticle.class;
			}
		};

	public static final Accessor<JournalArticle, String> ARTICLE_ID_ACCESSOR = new Accessor<JournalArticle, String>() {
			@Override
			public String get(JournalArticle journalArticle) {
				return journalArticle.getArticleId();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<JournalArticle> getTypeClass() {
				return JournalArticle.class;
			}
		};

	@Override
	public java.lang.String buildTreePath()
		throws com.liferay.portal.kernel.exception.PortalException;

	public long getArticleImageId(java.lang.String elInstanceId,
		java.lang.String elName, java.lang.String languageId);

	public java.lang.String getArticleImageURL(
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay);

	public JournalArticleResource getArticleResource()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.lang.String getArticleResourceUuid()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.lang.String getContentByLocale(java.lang.String languageId);

	public com.liferay.dynamic.data.mapping.model.DDMStructure getDDMStructure()
		throws com.liferay.portal.kernel.exception.PortalException;

	public com.liferay.dynamic.data.mapping.model.DDMTemplate getDDMTemplate()
		throws com.liferay.portal.kernel.exception.PortalException;

	public com.liferay.portal.kernel.xml.Document getDocument();

	public JournalFolder getFolder()
		throws com.liferay.portal.kernel.exception.PortalException;

	public com.liferay.portal.kernel.model.Layout getLayout();

	public java.lang.String getSmallImageType()
		throws com.liferay.portal.kernel.exception.PortalException;

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getDDMStructureKey()}
	*/
	@java.lang.Deprecated()
	public java.lang.String getStructureId();

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getDDMTemplateKey()}
	*/
	@java.lang.Deprecated()
	public java.lang.String getTemplateId();

	public boolean hasApprovedVersion();

	/**
	* @deprecated As of 7.0.0, with no direct replacement
	*/
	@java.lang.Deprecated()
	public boolean isTemplateDriven();

	public void setDefaultLanguageId(java.lang.String defaultLanguageId);

	public void setDocument(com.liferay.portal.kernel.xml.Document document);

	public void setSmallImageType(java.lang.String smallImageType);

	/**
	* @deprecated As of 7.0.0, replaced by {@link #setDDMStructureKey(String)}
	*/
	@java.lang.Deprecated()
	public void setStructureId(java.lang.String ddmStructureKey);

	/**
	* @deprecated As of 7.0.0, replaced by {@link #setDDMTemplateKey(String)}
	*/
	@java.lang.Deprecated()
	public void setTemplateId(java.lang.String ddmTemplateKey);
}