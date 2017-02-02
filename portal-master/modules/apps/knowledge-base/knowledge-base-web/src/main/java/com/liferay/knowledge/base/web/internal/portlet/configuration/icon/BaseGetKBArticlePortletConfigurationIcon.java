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

package com.liferay.knowledge.base.web.internal.portlet.configuration.icon;

import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.knowledge.base.web.internal.constants.KBWebKeys;
import com.liferay.portal.kernel.portlet.configuration.icon.BasePortletConfigurationIcon;

import javax.portlet.PortletRequest;

/**
 * @author Sergio González
 */
public abstract class BaseGetKBArticlePortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	protected KBArticle getKBArticle(PortletRequest portletRequest) {
		KBArticle kbArticle = (KBArticle)portletRequest.getAttribute(
			KBWebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

		if (kbArticle != null) {
			return kbArticle;
		}

		return (KBArticle)portletRequest.getAttribute(
			KBWebKeys.KNOWLEDGE_BASE_PARENT_KB_ARTICLE);
	}

}