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

package com.liferay.dynamic.data.mapping.service.impl;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.base.DDMTemplateServiceBaseImpl;
import com.liferay.dynamic.data.mapping.service.permission.DDMTemplatePermission;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.File;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the remote service for accessing, adding, copying, deleting, and
 * updating dynamic data mapping (DDM) templates. Its methods include security
 * checks.
 *
 * @author Brian Wing Shun Chan
 * @author Eduardo Lundgren
 * @author Marcellus Tavares
 * @see    DDMTemplateLocalServiceImpl
 */
public class DDMTemplateServiceImpl extends DDMTemplateServiceBaseImpl {

	/**
	 * Adds a template.
	 *
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for template's
	 *         related model
	 * @param  classPK the primary key of the template's related entity
	 * @param  resourceClassNameId the primary key of the class name for
	 *         template's resource model
	 * @param  nameMap the template's locales and localized names
	 * @param  descriptionMap the template's locales and localized descriptions
	 * @param  type the template's type. For more information, see
	 *         DDMTemplateConstants in the dynamic-data-mapping-api module.
	 * @param  mode the template's mode. For more information, see
	 *         DDMTemplateConstants in the dynamic-data-mapping-api module.
	 * @param  language the template's script language. For more information,
	 *         see DDMTemplateConstants in the dynamic-data-mapping-api module.
	 * @param  script the template's script
	 * @param  serviceContext the service context to be applied. Must have the
	 *         <code>ddmResource</code> attribute to check permissions. Can set
	 *         the UUID, creation date, modification date, guest permissions,
	 *         and group permissions for the template.
	 * @return the template
	 */
	@Override
	public DDMTemplate addTemplate(
			long groupId, long classNameId, long classPK,
			long resourceClassNameId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type, String mode,
			String language, String script, ServiceContext serviceContext)
		throws PortalException {

		DDMTemplatePermission.checkAddTemplatePermission(
			getPermissionChecker(), groupId, classNameId, resourceClassNameId);

		return ddmTemplateLocalService.addTemplate(
			getUserId(), groupId, classNameId, classPK, resourceClassNameId,
			null, nameMap, descriptionMap, type, mode, language, script, false,
			false, null, null, serviceContext);
	}

	/**
	 * Adds a template with additional parameters.
	 *
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for template's
	 *         related model
	 * @param  classPK the primary key of the template's related entity
	 * @param  resourceClassNameId the primary key of the class name for
	 *         template's resource model
	 * @param  templateKey the unique string identifying the template
	 *         (optionally <code>null</code>)
	 * @param  nameMap the template's locales and localized names
	 * @param  descriptionMap the template's locales and localized descriptions
	 * @param  type the template's type. For more information, see
	 *         DDMTemplateConstants in the dynamic-data-mapping-api module.
	 * @param  mode the template's mode. For more information, see
	 *         DDMTemplateConstants in the dynamic-data-mapping-api module.
	 * @param  language the template's script language. For more information,
	 *         see DDMTemplateConstants in the dynamic-data-mapping-api module.
	 * @param  script the template's script
	 * @param  cacheable whether the template is cacheable
	 * @param  smallImage whether the template has a small image
	 * @param  smallImageURL the template's small image URL (optionally
	 *         <code>null</code>)
	 * @param  smallImageFile the template's small image file (optionally
	 *         <code>null</code>)
	 * @param  serviceContext the service context to be applied. Must have the
	 *         <code>ddmResource</code> attribute to check permissions. Can set
	 *         the UUID, creation date, modification date, guest permissions,
	 *         and group permissions for the template.
	 * @return the template
	 */
	@Override
	public DDMTemplate addTemplate(
			long groupId, long classNameId, long classPK,
			long resourceClassNameId, String templateKey,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			String type, String mode, String language, String script,
			boolean cacheable, boolean smallImage, String smallImageURL,
			File smallImageFile, ServiceContext serviceContext)
		throws PortalException {

		DDMTemplatePermission.checkAddTemplatePermission(
			getPermissionChecker(), groupId, classNameId, resourceClassNameId);

		return ddmTemplateLocalService.addTemplate(
			getUserId(), groupId, classNameId, classPK, resourceClassNameId,
			templateKey, nameMap, descriptionMap, type, mode, language, script,
			cacheable, smallImage, smallImageURL, smallImageFile,
			serviceContext);
	}

	/**
	 * Copies the template, creating a new template with all the values
	 * extracted from the original one. This method supports defining a new name
	 * and description.
	 *
	 * @param  templateId the primary key of the template to be copied
	 * @param  nameMap the new template's locales and localized names
	 * @param  descriptionMap the new template's locales and localized
	 *         descriptions
	 * @param  serviceContext the service context to be applied. Must have the
	 *         <code>ddmResource</code> attribute to check permissions. Can set
	 *         the UUID, creation date, modification date, guest permissions,
	 *         and group permissions for the template.
	 * @return the new template
	 */
	@Override
	public DDMTemplate copyTemplate(
			long templateId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, ServiceContext serviceContext)
		throws PortalException {

		DDMTemplate template = ddmTemplatePersistence.findByPrimaryKey(
			templateId);

		DDMTemplatePermission.checkAddTemplatePermission(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			template.getClassNameId(), template.getResourceClassNameId());

		return ddmTemplateLocalService.copyTemplate(
			getUserId(), templateId, nameMap, descriptionMap, serviceContext);
	}

	@Override
	public DDMTemplate copyTemplate(
			long templateId, ServiceContext serviceContext)
		throws PortalException {

		DDMTemplate template = ddmTemplatePersistence.findByPrimaryKey(
			templateId);

		DDMTemplatePermission.checkAddTemplatePermission(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			template.getClassNameId(), template.getResourceClassNameId());

		return ddmTemplateLocalService.copyTemplate(
			getUserId(), templateId, serviceContext);
	}

	/**
	 * Copies all the templates matching the class name ID, class PK, and type.
	 * This method creates new templates, extracting all the values from the old
	 * ones and updating their class PKs.
	 *
	 * @param  classNameId the primary key of the class name for template's
	 *         related model
	 * @param  oldClassPK the primary key of the old template's related entity
	 * @param  resourceClassNameId the primary key of the class name for
	 *         template's resource model
	 * @param  newClassPK the primary key of the new template's related entity
	 * @param  type the template's type. For more information, see
	 *         DDMTemplateConstants in the dynamic-data-mapping-api module.
	 * @param  serviceContext the service context to be applied. Must have the
	 *         <code>ddmResource</code> attribute to check permissions. Can set
	 *         the UUID, creation date, modification date, guest permissions,
	 *         and group permissions for the template.
	 * @return the new template
	 */
	@Override
	public List<DDMTemplate> copyTemplates(
			long classNameId, long oldClassPK, long resourceClassNameId,
			long newClassPK, String type, ServiceContext serviceContext)
		throws PortalException {

		DDMTemplatePermission.checkAddTemplatePermission(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			classNameId, resourceClassNameId);

		return ddmTemplateLocalService.copyTemplates(
			getUserId(), classNameId, oldClassPK, newClassPK, type,
			serviceContext);
	}

	/**
	 * Deletes the template and its resources.
	 *
	 * @param templateId the primary key of the template to be deleted
	 */
	@Override
	public void deleteTemplate(long templateId) throws PortalException {
		DDMTemplatePermission.check(
			getPermissionChecker(), templateId, ActionKeys.DELETE);

		ddmTemplateLocalService.deleteTemplate(templateId);
	}

	/**
	 * Returns the template matching the group and template key.
	 *
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for template's
	 *         related model
	 * @param  templateKey the unique string identifying the template
	 * @return the matching template, or <code>null</code> if a matching
	 *         template could not be found
	 */
	@Override
	public DDMTemplate fetchTemplate(
			long groupId, long classNameId, String templateKey)
		throws PortalException {

		DDMTemplate ddmTemplate = ddmTemplateLocalService.fetchTemplate(
			groupId, classNameId, templateKey);

		if (ddmTemplate != null) {
			DDMTemplatePermission.check(
				getPermissionChecker(), ddmTemplate, ActionKeys.VIEW);
		}

		return ddmTemplate;
	}

	/**
	 * Returns the template with the ID.
	 *
	 * @param  templateId the primary key of the template
	 * @return the template with the ID
	 */
	@Override
	public DDMTemplate getTemplate(long templateId) throws PortalException {
		DDMTemplatePermission.check(
			getPermissionChecker(), templateId, ActionKeys.VIEW);

		return ddmTemplatePersistence.findByPrimaryKey(templateId);
	}

	/**
	 * Returns the template matching the group and template key.
	 *
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for template's
	 *         related model
	 * @param  templateKey the unique string identifying the template
	 * @return the matching template
	 */
	@Override
	public DDMTemplate getTemplate(
			long groupId, long classNameId, String templateKey)
		throws PortalException {

		DDMTemplate ddmTemplate = ddmTemplateLocalService.getTemplate(
			groupId, classNameId, templateKey);

		DDMTemplatePermission.check(
			getPermissionChecker(), ddmTemplate, ActionKeys.VIEW);

		return ddmTemplate;
	}

	/**
	 * Returns the template matching the group and template key, optionally
	 * searching ancestor sites (that have sharing enabled) and global scoped
	 * sites.
	 *
	 * <p>
	 * This method first searches in the group. If the template is still not
	 * found and <code>includeAncestorTemplates</code> is set to
	 * <code>true</code>, this method searches the group's ancestor sites (that
	 * have sharing enabled) and lastly searches global scoped sites.
	 * </p>
	 *
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for template's
	 *         related model
	 * @param  templateKey the unique string identifying the template
	 * @param  includeAncestorTemplates whether to include ancestor sites (that
	 *         have sharing enabled) and include global scoped sites in the
	 *         search
	 * @return the matching template
	 */
	@Override
	public DDMTemplate getTemplate(
			long groupId, long classNameId, String templateKey,
			boolean includeAncestorTemplates)
		throws PortalException {

		DDMTemplate ddmTemplate = ddmTemplateLocalService.getTemplate(
			groupId, classNameId, templateKey, includeAncestorTemplates);

		DDMTemplatePermission.check(
			getPermissionChecker(), ddmTemplate, ActionKeys.VIEW);

		return ddmTemplate;
	}

	@Override
	public List<DDMTemplate> getTemplates(
		long companyId, long groupId, long classNameId,
		long resourceClassNameId, int status) {

		return getTemplates(
			companyId, new long[] {groupId}, classNameId, 0,
			resourceClassNameId, null, null, status);
	}

	@Override
	public List<DDMTemplate> getTemplates(
			long companyId, long groupId, long classNameId, long classPK,
			long resourceClassNameId, boolean includeAncestorTemplates,
			int status)
		throws PortalException {

		List<DDMTemplate> ddmTemplates = new ArrayList<>();

		ddmTemplates.addAll(
			getTemplates(
				companyId, new long[] {groupId}, classNameId, classPK,
				resourceClassNameId, null, null, status));

		if (!includeAncestorTemplates) {
			return ddmTemplates;
		}

		ddmTemplates.addAll(
			getTemplates(
				companyId, PortalUtil.getAncestorSiteGroupIds(groupId),
				classNameId, classPK, resourceClassNameId, null, null, status));

		return ddmTemplates;
	}

	@Override
	public List<DDMTemplate> getTemplates(
		long companyId, long groupId, long classNameId, long classPK,
		long resourceClassNameId, int status) {

		return getTemplates(
			companyId, new long[] {groupId}, classNameId, classPK,
			resourceClassNameId, null, null, status);
	}

	/**
	 * Returns all the templates matching the group, class name ID, class PK,
	 * resource class name ID, and type.
	 *
	 * @param  companyId the primary key of the template's company
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for the template's
	 *         related model
	 * @param  classPK the primary key of the template's related entity
	 * @param  resourceClassNameId the primary key of the class name for the
	 *         template's resource model
	 * @param  type the template's type. For more information, see
	 *         DDMTemplateConstants in the dynamic-data-mapping-api module.
	 * @return the matching templates
	 */
	@Override
	public List<DDMTemplate> getTemplates(
		long companyId, long groupId, long classNameId, long classPK,
		long resourceClassNameId, String type, int status) {

		return getTemplates(
			companyId, new long[] {groupId}, classNameId, classPK,
			resourceClassNameId, type, null, status);
	}

	@Override
	public List<DDMTemplate> getTemplates(
		long companyId, long groupId, long classNameId, long classPK,
		long resourceClassNameId, String type, String mode, int status) {

		return getTemplates(
			companyId, new long[] {groupId}, classNameId, classPK,
			resourceClassNameId, type, mode, status);
	}

	/**
	 * Returns all the templates matching the group, class PK, and resource
	 * class name ID.
	 *
	 * @param  companyId the primary key of the template's company
	 * @param  groupId the primary key of the group
	 * @param  classPK the primary key of the template's related entity
	 * @param  resourceClassNameId the primary key of the class name for the
	 *         template's resource model
	 * @return the matching templates
	 */
	@Override
	public List<DDMTemplate> getTemplatesByClassPK(
		long companyId, long groupId, long classPK, long resourceClassNameId,
		int status) {

		return getTemplates(
			companyId, new long[] {groupId}, 0, classPK, resourceClassNameId,
			null, null, status);
	}

	/**
	 * Returns an ordered range of all the templates matching the group and
	 * structure class name ID and all the generic templates matching the group.
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
	 * @param  groupId the primary key of the group
	 * @param  structureClassNameId the primary key of the class name for the
	 *         template's related structure (optionally <code>0</code>). Specify
	 *         <code>0</code> to return generic templates only.
	 * @param  start the lower bound of the range of templates to return
	 * @param  end the upper bound of the range of templates to return (not
	 *         inclusive)
	 * @param  orderByComparator the comparator to order the templates
	 *         (optionally <code>null</code>)
	 * @return the range of matching templates ordered by the comparator
	 */
	@Override
	public List<DDMTemplate> getTemplatesByStructureClassNameId(
		long groupId, long structureClassNameId, int status, int start, int end,
		OrderByComparator<DDMTemplate> orderByComparator) {

		return ddmTemplateFinder.filterFindByG_SC_S(
			groupId, structureClassNameId, status, start, end,
			orderByComparator);
	}

	/**
	 * Returns the number of templates matching the group and structure class
	 * name ID plus the number of generic templates matching the group.
	 *
	 * @param  groupId the primary key of the group
	 * @param  structureClassNameId the primary key of the class name for the
	 *         template's related structure (optionally <code>0</code>). Specify
	 *         <code>0</code> to count generic templates only.
	 * @return the number of matching templates plus the number of matching
	 *         generic templates
	 */
	@Override
	public int getTemplatesByStructureClassNameIdCount(
		long groupId, long structureClassNameId, int status) {

		return ddmTemplateFinder.filterCountByG_SC_S(
			groupId, structureClassNameId, status);
	}

	@Override
	public void revertTemplate(
			long templateId, String version, ServiceContext serviceContext)
		throws PortalException {

		DDMTemplatePermission.check(
			getPermissionChecker(), templateId, ActionKeys.UPDATE);

		ddmTemplateLocalService.revertTemplate(
			getUserId(), templateId, version, serviceContext);
	}

	/**
	 * Returns an ordered range of all the templates matching the group, class
	 * name ID, class PK, type, and mode, and matching the keywords in the
	 * template names and descriptions.
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
	 * @param  companyId the primary key of the template's company
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for template's
	 *         related model
	 * @param  classPK the primary key of the template's related entity
	 * @param  resourceClassNameId the primary key of the class name for
	 *         template's resource model
	 * @param  keywords the keywords (space separated), which may occur in the
	 *         template's name or description (optionally <code>null</code>)
	 * @param  type the template's type (optionally <code>null</code>). For more
	 *         information, see DDMTemplateConstants in the
	 *         dynamic-data-mapping-api module.
	 * @param  mode the template's mode (optionally <code>null</code>). For more
	 *         information, see DDMTemplateConstants in the
	 *         dynamic-data-mapping-api module.
	 * @param  start the lower bound of the range of templates to return
	 * @param  end the upper bound of the range of templates to return (not
	 *         inclusive)
	 * @param  orderByComparator the comparator to order the templates
	 *         (optionally <code>null</code>)
	 * @return the matching templates ordered by the comparator
	 */
	@Override
	public List<DDMTemplate> search(
		long companyId, long groupId, long classNameId, long classPK,
		long resourceClassNameId, String keywords, String type, String mode,
		int status, int start, int end,
		OrderByComparator<DDMTemplate> orderByComparator) {

		return ddmTemplateFinder.filterFindByKeywords(
			companyId, groupId, classNameId, classPK, resourceClassNameId,
			keywords, type, mode, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the templates matching the group, class
	 * name ID, class PK, name keyword, description keyword, type, mode, and
	 * language.
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
	 * @param  companyId the primary key of the template's company
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for template's
	 *         related model
	 * @param  classPK the primary key of the template's related entity
	 * @param  resourceClassNameId the primary key of the class name for
	 *         template's resource model
	 * @param  name the name keywords (optionally <code>null</code>)
	 * @param  description the description keywords (optionally
	 *         <code>null</code>)
	 * @param  type the template's type (optionally <code>null</code>). For more
	 *         information, see DDMTemplateConstants in the
	 *         dynamic-data-mapping-api module.
	 * @param  mode the template's mode (optionally <code>null</code>). For more
	 *         information, see DDMTemplateConstants in the
	 *         dynamic-data-mapping-api module.
	 * @param  language the template's script language (optionally
	 *         <code>null</code>). For more information, see
	 *         DDMTemplateConstants in the dynamic-data-mapping-api module.
	 * @param  andOperator whether every field must match its keywords, or just
	 *         one field.
	 * @param  start the lower bound of the range of templates to return
	 * @param  end the upper bound of the range of templates to return (not
	 *         inclusive)
	 * @param  orderByComparator the comparator to order the templates
	 *         (optionally <code>null</code>)
	 * @return the matching templates ordered by the comparator
	 */
	@Override
	public List<DDMTemplate> search(
		long companyId, long groupId, long classNameId, long classPK,
		long resourceClassNameId, String name, String description, String type,
		String mode, String language, int status, boolean andOperator,
		int start, int end, OrderByComparator<DDMTemplate> orderByComparator) {

		return ddmTemplateFinder.filterFindByC_G_C_C_R_N_D_T_M_L_S(
			companyId, groupId, classNameId, classPK, resourceClassNameId, name,
			description, type, mode, language, status, andOperator, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the templates matching the group IDs,
	 * class name IDs, class PK, type, and mode, and matching the keywords in
	 * the template names and descriptions.
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
	 * @param  companyId the primary key of the template's company
	 * @param  groupIds the primary keys of the groups
	 * @param  classNameIds the primary keys of the entity's instances the
	 *         templates are related to
	 * @param  classPKs the primary keys of the template's related entities
	 * @param  resourceClassNameId the primary key of the class name for
	 *         template's resource model
	 * @param  keywords the keywords (space separated), which may occur in the
	 *         template's name or description (optionally <code>null</code>)
	 * @param  type the template's type (optionally <code>null</code>). For more
	 *         information, see DDMTemplateConstants in the
	 *         dynamic-data-mapping-api module.
	 * @param  mode the template's mode (optionally <code>null</code>). For more
	 *         information, see DDMTemplateConstants in the
	 *         dynamic-data-mapping-api module.
	 * @param  start the lower bound of the range of templates to return
	 * @param  end the upper bound of the range of templates to return (not
	 *         inclusive)
	 * @param  orderByComparator the comparator to order the templates
	 *         (optionally <code>null</code>)
	 * @return the matching templates ordered by the comparator
	 */
	@Override
	public List<DDMTemplate> search(
		long companyId, long[] groupIds, long[] classNameIds, long[] classPKs,
		long resourceClassNameId, String keywords, String type, String mode,
		int status, int start, int end,
		OrderByComparator<DDMTemplate> orderByComparator) {

		return ddmTemplateFinder.filterFindByKeywords(
			companyId, groupIds, classNameIds, classPKs, resourceClassNameId,
			keywords, type, mode, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the templates matching the group IDs,
	 * class name IDs, class PK, name keyword, description keyword, type, mode,
	 * and language.
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
	 * @param  companyId the primary key of the template's company
	 * @param  groupIds the primary keys of the groups
	 * @param  classNameIds the primary keys of the entity's instances the
	 *         templates are related to
	 * @param  classPKs the primary keys of the template's related entities
	 * @param  resourceClassNameId the primary key of the class name for
	 *         template's resource model
	 * @param  name the name keywords (optionally <code>null</code>)
	 * @param  description the description keywords (optionally
	 *         <code>null</code>)
	 * @param  type the template's type (optionally <code>null</code>). For more
	 *         information, see DDMTemplateConstants in the
	 *         dynamic-data-mapping-api module.
	 * @param  mode the template's mode (optionally <code>null</code>). For more
	 *         information, see DDMTemplateConstants in the
	 *         dynamic-data-mapping-api module.
	 * @param  language the template's script language (optionally
	 *         <code>null</code>). For more information, see
	 *         DDMTemplateConstants in the dynamic-data-mapping-api module.
	 * @param  andOperator whether every field must match its keywords, or just
	 *         one field.
	 * @param  start the lower bound of the range of templates to return
	 * @param  end the upper bound of the range of templates to return (not
	 *         inclusive)
	 * @param  orderByComparator the comparator to order the templates
	 *         (optionally <code>null</code>)
	 * @return the matching templates ordered by the comparator
	 */
	@Override
	public List<DDMTemplate> search(
		long companyId, long[] groupIds, long[] classNameIds, long[] classPKs,
		long resourceClassNameId, String name, String description, String type,
		String mode, String language, int status, boolean andOperator,
		int start, int end, OrderByComparator<DDMTemplate> orderByComparator) {

		return ddmTemplateFinder.filterFindByC_G_C_C_R_N_D_T_M_L_S(
			companyId, groupIds, classNameIds, classPKs, resourceClassNameId,
			name, description, type, mode, language, status, andOperator, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of templates matching the group, class name ID, class
	 * PK, type, and mode, and matching the keywords in the template names and
	 * descriptions.
	 *
	 * @param  companyId the primary key of the template's company
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for template's
	 *         related model
	 * @param  classPK the primary key of the template's related entity
	 * @param  resourceClassNameId the primary key of the class name for
	 *         template's resource model
	 * @param  keywords the keywords (space separated), which may occur in the
	 *         template's name or description (optionally <code>null</code>)
	 * @param  type the template's type (optionally <code>null</code>). For more
	 *         information, see DDMTemplateConstants in the
	 *         dynamic-data-mapping-api module.
	 * @param  mode the template's mode (optionally <code>null</code>). For more
	 *         information, see DDMTemplateConstants in the
	 *         dynamic-data-mapping-api module.
	 * @return the number of matching templates
	 */
	@Override
	public int searchCount(
		long companyId, long groupId, long classNameId, long classPK,
		long resourceClassNameId, String keywords, String type, String mode,
		int status) {

		return ddmTemplateFinder.filterCountByKeywords(
			companyId, groupId, classNameId, classPK, resourceClassNameId,
			keywords, type, mode, status);
	}

	/**
	 * Returns the number of templates matching the group, class name ID, class
	 * PK, name keyword, description keyword, type, mode, and language.
	 *
	 * @param  companyId the primary key of the template's company
	 * @param  groupId the primary key of the group
	 * @param  classNameId the primary key of the class name for template's
	 *         related model
	 * @param  classPK the primary key of the template's related entity
	 * @param  resourceClassNameId the primary key of the class name for
	 *         template's resource model
	 * @param  name the name keywords (optionally <code>null</code>)
	 * @param  description the description keywords (optionally
	 *         <code>null</code>)
	 * @param  type the template's type (optionally <code>null</code>). For more
	 *         information, see DDMTemplateConstants in the
	 *         dynamic-data-mapping-api module.
	 * @param  mode the template's mode (optionally <code>null</code>). For more
	 *         information, see DDMTemplateConstants in the
	 *         dynamic-data-mapping-api module.
	 * @param  language the template's script language (optionally
	 *         <code>null</code>). For more information, see
	 *         DDMTemplateConstants in the dynamic-data-mapping-api module.
	 * @param  andOperator whether every field must match its keywords, or just
	 *         one field.
	 * @return the number of matching templates
	 */
	@Override
	public int searchCount(
		long companyId, long groupId, long classNameId, long classPK,
		long resourceClassNameId, String name, String description, String type,
		String mode, String language, int status, boolean andOperator) {

		return ddmTemplateFinder.filterCountByC_G_C_C_R_N_D_T_M_L_S(
			companyId, groupId, classNameId, classPK, resourceClassNameId, name,
			description, type, mode, language, status, andOperator);
	}

	/**
	 * Returns the number of templates matching the group IDs, class name IDs,
	 * class PK, type, and mode, and matching the keywords in the template names
	 * and descriptions.
	 *
	 * @param  companyId the primary key of the template's company
	 * @param  groupIds the primary keys of the groups
	 * @param  classNameIds the primary keys of the entity's instances the
	 *         templates are related to
	 * @param  classPKs the primary keys of the template's related entities
	 * @param  resourceClassNameId the primary key of the class name for
	 *         template's resource model
	 * @param  keywords the keywords (space separated), which may occur in the
	 *         template's name or description (optionally <code>null</code>)
	 * @param  type the template's type (optionally <code>null</code>). For more
	 *         information, see DDMTemplateConstants in the
	 *         dynamic-data-mapping-api module.
	 * @param  mode the template's mode (optionally <code>null</code>). For more
	 *         information, see DDMTemplateConstants in the
	 *         dynamic-data-mapping-api module.
	 * @return the number of matching templates
	 */
	@Override
	public int searchCount(
		long companyId, long[] groupIds, long[] classNameIds, long[] classPKs,
		long resourceClassNameId, String keywords, String type, String mode,
		int status) {

		return ddmTemplateFinder.filterCountByKeywords(
			companyId, groupIds, classNameIds, classPKs, resourceClassNameId,
			keywords, type, mode, status);
	}

	/**
	 * Returns the number of templates matching the group IDs, class name IDs,
	 * class PK, name keyword, description keyword, type, mode, and language.
	 *
	 * @param  companyId the primary key of the template's company
	 * @param  groupIds the primary keys of the groups
	 * @param  classNameIds the primary keys of the entity's instances the
	 *         templates are related to
	 * @param  classPKs the primary keys of the template's related entities
	 * @param  resourceClassNameId the primary key of the class name for
	 *         template's resource model
	 * @param  name the name keywords (optionally <code>null</code>)
	 * @param  description the description keywords (optionally
	 *         <code>null</code>)
	 * @param  type the template's type (optionally <code>null</code>). For more
	 *         information, see DDMTemplateConstants in the
	 *         dynamic-data-mapping-api module.
	 * @param  mode the template's mode (optionally <code>null</code>). For more
	 *         information, see DDMTemplateConstants in the
	 *         dynamic-data-mapping-api module.
	 * @param  language the template's script language (optionally
	 *         <code>null</code>). For more information, see
	 *         DDMTemplateConstants in the dynamic-data-mapping-api module.
	 * @param  andOperator whether every field must match its keywords, or just
	 *         one field.
	 * @return the number of matching templates
	 */
	@Override
	public int searchCount(
		long companyId, long[] groupIds, long[] classNameIds, long[] classPKs,
		long resourceClassNameId, String name, String description, String type,
		String mode, String language, int status, boolean andOperator) {

		return ddmTemplateFinder.filterCountByC_G_C_C_R_N_D_T_M_L_S(
			companyId, groupIds, classNameIds, classPKs, resourceClassNameId,
			name, description, type, mode, language, status, andOperator);
	}

	/**
	 * Updates the template matching the ID.
	 *
	 * @param  templateId the primary key of the template
	 * @param  classPK the primary key of the template's related entity
	 * @param  nameMap the template's new locales and localized names
	 * @param  descriptionMap the template's new locales and localized
	 *         description
	 * @param  type the template's type. For more information, see
	 *         DDMTemplateConstants in the dynamic-data-mapping-api module.
	 * @param  mode the template's mode. For more information, see
	 *         DDMTemplateConstants in the dynamic-data-mapping-api module.
	 * @param  language the template's script language. For more information,
	 *         see DDMTemplateConstants in the dynamic-data-mapping-api module.
	 * @param  script the template's script
	 * @param  cacheable whether the template is cacheable
	 * @param  smallImage whether the template has a small image
	 * @param  smallImageURL the template's small image URL (optionally
	 *         <code>null</code>)
	 * @param  smallImageFile the template's small image file (optionally
	 *         <code>null</code>)
	 * @param  serviceContext the service context to be applied. Can set the
	 *         modification date.
	 * @return the updated template
	 */
	@Override
	public DDMTemplate updateTemplate(
			long templateId, long classPK, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type, String mode,
			String language, String script, boolean cacheable,
			boolean smallImage, String smallImageURL, File smallImageFile,
			ServiceContext serviceContext)
		throws PortalException {

		DDMTemplatePermission.check(
			getPermissionChecker(), templateId, ActionKeys.UPDATE);

		return ddmTemplateLocalService.updateTemplate(
			getUserId(), templateId, classPK, nameMap, descriptionMap, type,
			mode, language, script, cacheable, smallImage, smallImageURL,
			smallImageFile, serviceContext);
	}

	/**
	 * Updates the template matching the ID.
	 *
	 * @param  templateId the primary key of the template
	 * @param  classPK the primary key of the template's related entity
	 * @param  nameMap the template's new locales and localized names
	 * @param  descriptionMap the template's new locales and localized
	 *         description
	 * @param  type the template's type. For more information, see
	 *         DDMTemplateConstants in the dynamic-data-mapping-api module.
	 * @param  mode the template's mode. For more information, see
	 *         DDMTemplateConstants in the dynamic-data-mapping-api module.
	 * @param  language the template's script language. For more information,
	 *         see DDMTemplateConstants in the dynamic-data-mapping-api module.
	 * @param  script the template's script
	 * @param  cacheable whether the template is cacheable
	 * @param  serviceContext the service context to be applied. Can set the
	 *         modification date.
	 * @return the updated template
	 */
	@Override
	public DDMTemplate updateTemplate(
			long templateId, long classPK, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type, String mode,
			String language, String script, boolean cacheable,
			ServiceContext serviceContext)
		throws PortalException {

		DDMTemplatePermission.check(
			getPermissionChecker(), templateId, ActionKeys.UPDATE);

		return ddmTemplateLocalService.updateTemplate(
			getUserId(), templateId, classPK, nameMap, descriptionMap, type,
			mode, language, script, cacheable, serviceContext);
	}

	protected List<DDMTemplate> getTemplates(
		long companyId, long[] groupIds, long classNameId, long classPK,
		long resourceClassNameId, String type, String mode, int status) {

		return ddmTemplateFinder.filterFindByC_G_C_C_R_T_M_S(
			companyId, groupIds, classNameId, classPK, resourceClassNameId,
			type, mode, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

}