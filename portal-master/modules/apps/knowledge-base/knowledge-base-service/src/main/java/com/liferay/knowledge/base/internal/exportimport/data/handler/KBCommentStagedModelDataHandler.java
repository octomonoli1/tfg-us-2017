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

package com.liferay.knowledge.base.internal.exportimport.data.handler;

import com.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.knowledge.base.model.KBComment;
import com.liferay.knowledge.base.service.KBCommentLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Daniel Kocsis
 */
@Component(immediate = true, service = StagedModelDataHandler.class)
public class KBCommentStagedModelDataHandler
	extends BaseStagedModelDataHandler<KBComment> {

	public static final String[] CLASS_NAMES = {KBComment.class.getName()};

	@Override
	public void deleteStagedModel(KBComment kbComment) throws PortalException {
		_kbCommentLocalService.deleteKBComment(kbComment);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		KBComment kbComment = fetchStagedModelByUuidAndGroupId(uuid, groupId);

		if (kbComment != null) {
			deleteStagedModel(kbComment);
		}
	}

	@Override
	public KBComment fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return _kbCommentLocalService.fetchKBCommentByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public List<KBComment> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return _kbCommentLocalService.getKBCommentsByUuidAndCompanyId(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new StagedModelModifiedDateComparator<KBComment>());
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(KBComment kbComment) {
		return kbComment.getUuid();
	}

	@Reference(unbind = "-")
	public void setKBCommentLocalService(
		KBCommentLocalService kbCommentLocalService) {

		_kbCommentLocalService = kbCommentLocalService;
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, KBComment kbComment)
		throws Exception {

		Element kbCommentElement = portletDataContext.getExportDataElement(
			kbComment);

		portletDataContext.addClassedModel(
			kbCommentElement, ExportImportPathUtil.getModelPath(kbComment),
			kbComment);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, KBComment kbComment)
		throws Exception {

		long userId = portletDataContext.getUserId(kbComment.getUserUuid());

		Map<Long, Long> relatedClassPKs =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				kbComment.getClassName());

		long newClassPK = MapUtil.getLong(
			relatedClassPKs, kbComment.getClassPK(), kbComment.getClassPK());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			kbComment);

		KBComment importedKBComment = null;

		if (portletDataContext.isDataStrategyMirror()) {
			KBComment existingKBComment = fetchStagedModelByUuidAndGroupId(
				kbComment.getUuid(), portletDataContext.getScopeGroupId());

			if (existingKBComment == null) {
				serviceContext.setUuid(kbComment.getUuid());

				importedKBComment = _kbCommentLocalService.addKBComment(
					userId, kbComment.getClassNameId(), newClassPK,
					kbComment.getContent(), kbComment.getUserRating(),
					serviceContext);
			}
			else {
				importedKBComment = _kbCommentLocalService.updateKBComment(
					existingKBComment.getKbCommentId(),
					kbComment.getClassNameId(), newClassPK,
					kbComment.getContent(), kbComment.getUserRating(),
					kbComment.getStatus(), serviceContext);
			}
		}
		else {
			importedKBComment = _kbCommentLocalService.addKBComment(
				userId, kbComment.getClassNameId(), newClassPK,
				kbComment.getContent(), kbComment.getUserRating(),
				serviceContext);
		}

		portletDataContext.importClassedModel(kbComment, importedKBComment);
	}

	private KBCommentLocalService _kbCommentLocalService;

}