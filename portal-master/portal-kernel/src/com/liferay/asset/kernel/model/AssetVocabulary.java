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

package com.liferay.asset.kernel.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the AssetVocabulary service. Represents a row in the &quot;AssetVocabulary&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AssetVocabularyModel
 * @see com.liferay.portlet.asset.model.impl.AssetVocabularyImpl
 * @see com.liferay.portlet.asset.model.impl.AssetVocabularyModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.portlet.asset.model.impl.AssetVocabularyImpl")
@ProviderType
public interface AssetVocabulary extends AssetVocabularyModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portlet.asset.model.impl.AssetVocabularyImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AssetVocabulary, Long> VOCABULARY_ID_ACCESSOR = new Accessor<AssetVocabulary, Long>() {
			@Override
			public Long get(AssetVocabulary assetVocabulary) {
				return assetVocabulary.getVocabularyId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AssetVocabulary> getTypeClass() {
				return AssetVocabulary.class;
			}
		};

	public java.util.List<AssetCategory> getCategories();

	public int getCategoriesCount();

	public long[] getRequiredClassNameIds();

	public long[] getSelectedClassNameIds();

	public long[] getSelectedClassTypePKs();

	/**
	* @deprecated As of 7.0.0, with no direct replacement
	*/
	@java.lang.Deprecated()
	public com.liferay.portal.kernel.util.UnicodeProperties getSettingsProperties();

	public java.lang.String getUnambiguousTitle(
		java.util.List<AssetVocabulary> vocabularies, long groupId,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean hasMoreThanOneCategorySelected(long[] categoryIds);

	public boolean isAssociatedToClassNameId(long classNameId);

	public boolean isAssociatedToClassNameIdAndClassTypePK(long classNameId,
		long classTypePK);

	public boolean isMissingRequiredCategory(long classNameId,
		long classTypePK, long[] categoryIds);

	public boolean isMultiValued();

	/**
	* @deprecated As of 7.0.0, replaced by {@link #isRequired(long, long)}
	*/
	@java.lang.Deprecated()
	public boolean isRequired(long classNameId);

	public boolean isRequired(long classNameId, long classTypePK);

	/**
	* @deprecated As of 7.0.0, with no direct replacement
	*/
	@java.lang.Deprecated()
	public void setSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties settingsProperties);
}