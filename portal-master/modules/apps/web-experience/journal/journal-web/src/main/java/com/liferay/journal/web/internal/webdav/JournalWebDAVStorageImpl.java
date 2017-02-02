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

package com.liferay.journal.web.internal.webdav;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.dynamic.data.mapping.webdav.DDMWebDav;
import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolderConstants;
import com.liferay.journal.service.JournalFolderService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.webdav.BaseWebDAVStorageImpl;
import com.liferay.portal.kernel.webdav.Resource;
import com.liferay.portal.kernel.webdav.WebDAVException;
import com.liferay.portal.kernel.webdav.WebDAVRequest;
import com.liferay.portal.kernel.webdav.WebDAVStorage;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Juan Fernández
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + JournalPortletKeys.JOURNAL,
		"webdav.storage.token=journal"
	},
	service = WebDAVStorage.class
)
public class JournalWebDAVStorageImpl extends BaseWebDAVStorageImpl {

	@Override
	public int deleteResource(WebDAVRequest webDAVRequest)
		throws WebDAVException {

		return _ddmWebDav.deleteResource(
			webDAVRequest, getRootPath(), getToken(),
			PortalUtil.getClassNameId(JournalArticle.class));
	}

	@Override
	public Resource getResource(WebDAVRequest webDAVRequest)
		throws WebDAVException {

		return _ddmWebDav.getResource(
			webDAVRequest, getRootPath(), getToken(),
			PortalUtil.getClassNameId(JournalArticle.class));
	}

	@Override
	public List<Resource> getResources(WebDAVRequest webDAVRequest)
		throws WebDAVException {

		try {
			String[] pathArray = webDAVRequest.getPathArray();

			if (pathArray.length == 2) {
				return getFolders(webDAVRequest);
			}
			else if (pathArray.length == 3) {
				String type = pathArray[2];

				if (type.equals(DDMWebDav.TYPE_STRUCTURES)) {
					return getStructures(webDAVRequest);
				}
				else if (type.equals(DDMWebDav.TYPE_TEMPLATES)) {
					return getTemplates(webDAVRequest);
				}
			}

			return new ArrayList<>();
		}
		catch (Exception e) {
			throw new WebDAVException(e);
		}
	}

	@Override
	public int putResource(WebDAVRequest webDAVRequest) throws WebDAVException {
		return _ddmWebDav.putResource(
			webDAVRequest, getRootPath(), getToken(),
			PortalUtil.getClassNameId(JournalArticle.class));
	}

	protected List<Resource> getFolders(WebDAVRequest webDAVRequest)
		throws Exception {

		List<Resource> resources = new ArrayList<>();

		resources.add(
			_ddmWebDav.toResource(
				webDAVRequest, DDMWebDav.TYPE_STRUCTURES, getRootPath(), true));
		resources.add(
			_ddmWebDav.toResource(
				webDAVRequest, DDMWebDav.TYPE_TEMPLATES, getRootPath(), true));

		return resources;
	}

	protected List<Resource> getStructures(WebDAVRequest webDAVRequest)
		throws Exception {

		List<Resource> resources = new ArrayList<>();

		List<DDMStructure> ddmStructures =
			_journalFolderService.getDDMStructures(
				new long[] {webDAVRequest.getGroupId()},
				JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				JournalFolderConstants.RESTRICTION_TYPE_INHERIT);

		for (DDMStructure ddmStructure : ddmStructures) {
			Resource resource = _ddmWebDav.toResource(
				webDAVRequest, ddmStructure, getRootPath(), true);

			resources.add(resource);
		}

		return resources;
	}

	protected List<Resource> getTemplates(WebDAVRequest webDAVRequest)
		throws Exception {

		List<Resource> resources = new ArrayList<>();

		List<DDMTemplate> ddmTemplates =
			_ddmTemplateLocalService.getTemplatesByStructureClassNameId(
				webDAVRequest.getGroupId(),
				PortalUtil.getClassNameId(JournalArticle.class),
				WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		for (DDMTemplate ddmTemplate : ddmTemplates) {
			Resource resource = _ddmWebDav.toResource(
				webDAVRequest, ddmTemplate, getRootPath(), true);

			resources.add(resource);
		}

		return resources;
	}

	@Reference(unbind = "-")
	protected void setDDMTemplateLocalService(
		DDMTemplateLocalService ddmTemplateLocalService) {

		_ddmTemplateLocalService = ddmTemplateLocalService;
	}

	@Reference(unbind = "-")
	protected void setDDMWebDav(DDMWebDav ddmWebDav) {
		_ddmWebDav = ddmWebDav;
	}

	@Reference(unbind = "-")
	protected void setJournalFolderService(
		JournalFolderService journalFolderService) {

		_journalFolderService = journalFolderService;
	}

	private DDMTemplateLocalService _ddmTemplateLocalService;
	private DDMWebDav _ddmWebDav;
	private JournalFolderService _journalFolderService;

}