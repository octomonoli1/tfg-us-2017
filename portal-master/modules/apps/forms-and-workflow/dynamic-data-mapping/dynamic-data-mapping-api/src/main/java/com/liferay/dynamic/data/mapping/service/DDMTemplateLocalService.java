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

package com.liferay.dynamic.data.mapping.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.permission.ModelPermissions;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.File;
import java.io.Serializable;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the local service interface for DDMTemplate. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see DDMTemplateLocalServiceUtil
 * @see com.liferay.dynamic.data.mapping.service.base.DDMTemplateLocalServiceBaseImpl
 * @see com.liferay.dynamic.data.mapping.service.impl.DDMTemplateLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DDMTemplateLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DDMTemplateLocalServiceUtil} to access the d d m template local service. Add custom service methods to {@link com.liferay.dynamic.data.mapping.service.impl.DDMTemplateLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the d d m template to the database. Also notifies the appropriate model listeners.
	*
	* @param ddmTemplate the d d m template
	* @return the d d m template that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DDMTemplate addDDMTemplate(DDMTemplate ddmTemplate);

	/**
	* Adds a template with additional parameters.
	*
	* @param userId the primary key of the template's creator/owner
	* @param groupId the primary key of the group
	* @param classNameId the primary key of the class name for the template's
	related model
	* @param classPK the primary key of the template's related entity
	* @param resourceClassNameId the primary key of the class name for
	template's resource model
	* @param templateKey the unique string identifying the template
	(optionally <code>null</code>)
	* @param nameMap the template's locales and localized names
	* @param descriptionMap the template's locales and localized descriptions
	* @param type the template's type. For more information, see
	DDMTemplateConstants in the dynamic-data-mapping-api module.
	* @param mode the template's mode. For more information, see
	DDMTemplateConstants in the dynamic-data-mapping-api module.
	* @param language the template's script language. For more information,
	see DDMTemplateConstants in the dynamic-data-mapping-api module.
	* @param script the template's script
	* @param cacheable whether the template is cacheable
	* @param smallImage whether the template has a small image
	* @param smallImageURL the template's small image URL (optionally
	<code>null</code>)
	* @param smallImageFile the template's small image file (optionally
	<code>null</code>)
	* @param serviceContext the service context to be applied. Can set the
	UUID, creation date, modification date, guest permissions, and
	group permissions for the template.
	* @return the template
	* @throws PortalException if a portal exception occurred
	*/
	public DDMTemplate addTemplate(long userId, long groupId, long classNameId,
		long classPK, long resourceClassNameId, java.lang.String templateKey,
		Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap, java.lang.String type,
		java.lang.String mode, java.lang.String language,
		java.lang.String script, boolean cacheable, boolean smallImage,
		java.lang.String smallImageURL, File smallImageFile,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Adds a template.
	*
	* @param userId the primary key of the template's creator/owner
	* @param groupId the primary key of the group
	* @param classNameId the primary key of the class name for the template's
	related model
	* @param classPK the primary key of the template's related entity
	* @param resourceClassNameId the primary key of the class name for
	template's resource model
	* @param nameMap the template's locales and localized names
	* @param descriptionMap the template's locales and localized descriptions
	* @param type the template's type. For more information, see
	DDMTemplateConstants in the dynamic-data-mapping-api module.
	* @param mode the template's mode. For more information, see
	DDMTemplateConstants in the dynamic-data-mapping-api module.
	* @param language the template's script language. For more information,
	see DDMTemplateConstants in the dynamic-data-mapping-api module.
	* @param script the template's script
	* @param serviceContext the service context to be applied. Can set the
	UUID, creation date, modification date, guest permissions, and
	group permissions for the template.
	* @return the template
	* @throws PortalException if a portal exception occurred
	*/
	public DDMTemplate addTemplate(long userId, long groupId, long classNameId,
		long classPK, long resourceClassNameId,
		Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap, java.lang.String type,
		java.lang.String mode, java.lang.String language,
		java.lang.String script, ServiceContext serviceContext)
		throws PortalException;

	public DDMTemplate copyTemplate(long userId, long templateId,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Copies the template, creating a new template with all the values
	* extracted from the original one. This method supports defining a new name
	* and description.
	*
	* @param userId the primary key of the template's creator/owner
	* @param templateId the primary key of the template to be copied
	* @param nameMap the new template's locales and localized names
	* @param descriptionMap the new template's locales and localized
	descriptions
	* @param serviceContext the service context to be applied. Can set the
	UUID, creation date, modification date, guest permissions, and
	group permissions for the template.
	* @return the new template
	* @throws PortalException if a portal exception occurred
	*/
	public DDMTemplate copyTemplate(long userId, long templateId,
		Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Creates a new d d m template with the primary key. Does not add the d d m template to the database.
	*
	* @param templateId the primary key for the new d d m template
	* @return the new d d m template
	*/
	public DDMTemplate createDDMTemplate(long templateId);

	/**
	* Deletes the d d m template from the database. Also notifies the appropriate model listeners.
	*
	* @param ddmTemplate the d d m template
	* @return the d d m template that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public DDMTemplate deleteDDMTemplate(DDMTemplate ddmTemplate);

	/**
	* Deletes the d d m template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param templateId the primary key of the d d m template
	* @return the d d m template that was removed
	* @throws PortalException if a d d m template with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public DDMTemplate deleteDDMTemplate(long templateId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMTemplate fetchDDMTemplate(long templateId);

	/**
	* Returns the d d m template matching the UUID and group.
	*
	* @param uuid the d d m template's UUID
	* @param groupId the primary key of the group
	* @return the matching d d m template, or <code>null</code> if a matching d d m template could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMTemplate fetchDDMTemplateByUuidAndGroupId(java.lang.String uuid,
		long groupId);

	/**
	* Returns the template matching the group and template key.
	*
	* @param groupId the primary key of the group
	* @param classNameId the primary key of the class name for the template's
	related model
	* @param templateKey the unique string identifying the template
	* @return the matching template, or <code>null</code> if a matching
	template could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMTemplate fetchTemplate(long groupId, long classNameId,
		java.lang.String templateKey);

	/**
	* Returns the template matching the group and template key, optionally
	* searching ancestor sites (that have sharing enabled) and global scoped
	* sites.
	*
	* <p>
	* This method first searches in the given group. If the template is still
	* not found and <code>includeAncestorTemplates</code> is set to
	* <code>true</code>, this method searches the group's ancestor sites (that
	* have sharing enabled) and lastly searches global scoped sites.
	* </p>
	*
	* @param groupId the primary key of the group
	* @param classNameId the primary key of the class name for the template's
	related model
	* @param templateKey the unique string identifying the template
	* @param includeAncestorTemplates whether to include ancestor sites (that
	have sharing enabled) and include global scoped sites in the
	search in the search
	* @return the matching template, or <code>null</code> if a matching
	template could not be found
	* @throws PortalException if a portal exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMTemplate fetchTemplate(long groupId, long classNameId,
		java.lang.String templateKey, boolean includeAncestorTemplates)
		throws PortalException;

	/**
	* Returns the template with the primary key.
	*
	* @param templateId the primary key of the template
	* @return the matching template, or <code>null</code> if a matching
	template could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMTemplate fetchTemplate(long templateId);

	/**
	* Returns the d d m template with the primary key.
	*
	* @param templateId the primary key of the d d m template
	* @return the d d m template
	* @throws PortalException if a d d m template with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMTemplate getDDMTemplate(long templateId)
		throws PortalException;

	/**
	* Returns the d d m template matching the UUID and group.
	*
	* @param uuid the d d m template's UUID
	* @param groupId the primary key of the group
	* @return the matching d d m template
	* @throws PortalException if a matching d d m template could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMTemplate getDDMTemplateByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	/**
	* Returns the template matching the group and template key.
	*
	* @param groupId the primary key of the group
	* @param classNameId the primary key of the class name for the template's
	related model
	* @param templateKey the unique string identifying the template
	* @return the matching template
	* @throws PortalException if a portal exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMTemplate getTemplate(long groupId, long classNameId,
		java.lang.String templateKey) throws PortalException;

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
	* @param groupId the primary key of the group
	* @param classNameId the primary key of the class name for the template's
	related model
	* @param templateKey the unique string identifying the template
	* @param includeAncestorTemplates whether to include ancestor sites (that
	have sharing enabled) and include global scoped sites in the
	search in the search
	* @return the matching template
	* @throws PortalException if a portal exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMTemplate getTemplate(long groupId, long classNameId,
		java.lang.String templateKey, boolean includeAncestorTemplates)
		throws PortalException;

	/**
	* Returns the template with the primary key.
	*
	* @param templateId the primary key of the template
	* @return the template with the primary key
	* @throws PortalException if a portal exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMTemplate getTemplate(long templateId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMTemplate getTemplateBySmallImageId(long smallImageId)
		throws PortalException;

	/**
	* Updates the d d m template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ddmTemplate the d d m template
	* @return the d d m template that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DDMTemplate updateDDMTemplate(DDMTemplate ddmTemplate);

	/**
	* Updates the template matching the ID.
	*
	* @param userId the primary key of the template's creator/owner
	* @param templateId the primary key of the template
	* @param classPK the primary key of the template's related entity
	* @param nameMap the template's new locales and localized names
	* @param descriptionMap the template's new locales and localized
	description
	* @param type the template's type. For more information, see
	DDMTemplateConstants in the dynamic-data-mapping-api module.
	* @param mode the template's mode. For more information, see
	DDMTemplateConstants in the dynamic-data-mapping-api module.
	* @param language the template's script language. For more information,
	see DDMTemplateConstants in the dynamic-data-mapping-api module.
	* @param script the template's script
	* @param cacheable whether the template is cacheable
	* @param smallImage whether the template has a small image
	* @param smallImageURL the template's small image URL (optionally
	<code>null</code>)
	* @param smallImageFile the template's small image file (optionally
	<code>null</code>)
	* @param serviceContext the service context to be applied. Can set the
	modification date.
	* @return the updated template
	* @throws PortalException if a portal exception occurred
	*/
	public DDMTemplate updateTemplate(long userId, long templateId,
		long classPK, Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap, java.lang.String type,
		java.lang.String mode, java.lang.String language,
		java.lang.String script, boolean cacheable, boolean smallImage,
		java.lang.String smallImageURL, File smallImageFile,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Updates the template matching the primary key.
	*
	* @param userId the primary key of the template's creator/owner
	* @param templateId the primary key of the template
	* @param classPK the primary key of the template's related entity
	* @param nameMap the template's new locales and localized names
	* @param descriptionMap the template's new locales and localized
	description
	* @param type the template's type. For more information, see
	DDMTemplateConstants in the dynamic-data-mapping-api module.
	* @param mode the template's mode. For more information, see
	DDMTemplateConstants in the dynamic-data-mapping-api module.
	* @param language the template's script language. For more information,
	see DDMTemplateConstants in the dynamic-data-mapping-api module.
	* @param script the template's script
	* @param cacheable whether the template is cacheable
	* @param serviceContext the service context to be applied. Can set the
	modification date.
	* @return the updated template
	* @throws PortalException if a portal exception occurred
	*/
	public DDMTemplate updateTemplate(long userId, long templateId,
		long classPK, Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap, java.lang.String type,
		java.lang.String mode, java.lang.String language,
		java.lang.String script, boolean cacheable,
		ServiceContext serviceContext) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the number of d d m templates.
	*
	* @return the number of d d m templates
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDDMTemplatesCount();

	/**
	* Returns the number of templates matching the group and class PK.
	*
	* @param groupId the primary key of the group
	* @param classPK the primary key of the template's related entity
	* @return the number of templates belonging to the group and class PK
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTemplatesByClassPKCount(long groupId, long classPK);

	/**
	* Returns the number of templates matching the group, structure class name
	* ID, and status, including Generic Templates.
	*
	* @param groupId the primary key of the group
	* @param structureClassNameId the primary key of the class name for the
	template's related structure
	* @param status the template's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @return the number of matching templates
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTemplatesByStructureClassNameIdCount(long groupId,
		long structureClassNameId, int status);

	/**
	* Returns the number of templates belonging to the group.
	*
	* @param groupId the primary key of the group
	* @return the number of templates belonging to the group
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTemplatesCount(long groupId);

	/**
	* Returns the number of templates matching the group and class name ID.
	*
	* @param groupId the primary key of the group
	* @param classNameId the primary key of the class name for the template's
	related model
	* @return the number of matching templates
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTemplatesCount(long groupId, long classNameId);

	/**
	* Returns the number of templates matching the group, class name ID, and
	* class PK.
	*
	* @param groupId the primary key of the group
	* @param classNameId the primary key of the class name for the template's
	related model
	* @param classPK the primary key of the template's related entity
	* @return the number of matching templates
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTemplatesCount(long groupId, long classNameId, long classPK);

	/**
	* Returns the number of templates matching the group, class name ID, class
	* PK, type, mode, and status, and matching the keywords in the template
	* names and descriptions.
	*
	* @param companyId the primary key of the template's company
	* @param groupId the primary key of the group
	* @param classNameId the primary key of the class name for the template's
	related model
	* @param classPK the primary key of the template's related entity
	* @param resourceClassNameId the primary key of the class name for
	template's resource model
	* @param keywords the keywords (space separated), which may occur in the
	template's name or description (optionally <code>null</code>)
	* @param type the template's type (optionally <code>null</code>). For more
	information, see DDMTemplateConstants in the
	dynamic-data-mapping-api module.
	* @param mode the template's mode (optionally <code>null</code>). For more
	information, see DDMTemplateConstants in the
	dynamic-data-mapping-api module.
	* @param status the template's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @return the number of matching templates
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long companyId, long groupId, long classNameId,
		long classPK, long resourceClassNameId, java.lang.String keywords,
		java.lang.String type, java.lang.String mode, int status);

	/**
	* Returns the number of templates matching the group, class name ID, class
	* PK, name keyword, description keyword, type, mode, language, and status.
	*
	* @param companyId the primary key of the template's company
	* @param groupId the primary key of the group
	* @param classNameId the primary key of the class name for the template's
	related model
	* @param classPK the primary key of the template's related entity
	* @param resourceClassNameId the primary key of the class name for
	template's resource model
	* @param name the name keywords (optionally <code>null</code>)
	* @param description the description keywords (optionally
	<code>null</code>)
	* @param type the template's type (optionally <code>null</code>). For more
	information, see DDMTemplateConstants in the
	dynamic-data-mapping-api module.
	* @param mode the template's mode (optionally <code>null</code>). For more
	information, see DDMTemplateConstants in the
	dynamic-data-mapping-api module.
	* @param language the template's script language (optionally
	<code>null</code>). For more information, see
	DDMTemplateConstants in the dynamic-data-mapping-api module.
	* @param status the template's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @return the number of matching templates
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long companyId, long groupId, long classNameId,
		long classPK, long resourceClassNameId, java.lang.String name,
		java.lang.String description, java.lang.String type,
		java.lang.String mode, java.lang.String language, int status,
		boolean andOperator);

	/**
	* Returns the number of templates matching the group IDs, class name IDs,
	* class PK, type, mode, and status, and matching the keywords in the
	* template names and descriptions.
	*
	* @param companyId the primary key of the template's company
	* @param groupIds the primary keys of the groups
	* @param classNameIds the primary keys of the entity's instance the
	templates are related to
	* @param classPKs the primary keys of the template's related entities
	* @param resourceClassNameId the primary key of the class name for
	template's resource model
	* @param keywords the keywords (space separated), which may occur in the
	template's name or description (optionally <code>null</code>)
	* @param type the template's type (optionally <code>null</code>). For more
	information, see DDMTemplateConstants in the
	dynamic-data-mapping-api module.
	* @param mode the template's mode (optionally <code>null</code>). For more
	information, see DDMTemplateConstants in the
	dynamic-data-mapping-api module.
	* @param status the template's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @return the number of matching templates
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long companyId, long[] groupIds,
		long[] classNameIds, long[] classPKs, long resourceClassNameId,
		java.lang.String keywords, java.lang.String type,
		java.lang.String mode, int status);

	/**
	* Returns the number of templates matching the group IDs, class name IDs,
	* class PKs, name keyword, description keyword, type, mode, language, and
	* status.
	*
	* @param companyId the primary key of the templates company
	* @param groupIds the primary keys of the groups
	* @param classNameIds the primary keys of the entity's instance the
	templates are related to
	* @param classPKs the primary keys of the template's related entities
	* @param resourceClassNameId the primary key of the class name for
	template's resource model
	* @param name the name keywords (optionally <code>null</code>)
	* @param description the description keywords (optionally
	<code>null</code>)
	* @param type the template's type (optionally <code>null</code>). For more
	information, see DDMTemplateConstants in the
	dynamic-data-mapping-api module.
	* @param mode the template's mode (optionally <code>null</code>). For more
	information, see DDMTemplateConstants in the
	dynamic-data-mapping-api module.
	* @param language the template's script language (optionally
	<code>null</code>). For more information, see
	DDMTemplateConstants in the dynamic-data-mapping-api module.
	* @param status the template's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @return the number of matching templates
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long companyId, long[] groupIds,
		long[] classNameIds, long[] classPKs, long resourceClassNameId,
		java.lang.String name, java.lang.String description,
		java.lang.String type, java.lang.String mode,
		java.lang.String language, int status, boolean andOperator);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Copies all the templates matching the class name ID, class PK, and type.
	* This method creates new templates, extracting all the values from the old
	* ones and updating their class PKs.
	*
	* @param userId the primary key of the template's creator/owner
	* @param classNameId the primary key of the class name for the template's
	related model
	* @param oldClassPK the primary key of the old template's related entity
	* @param newClassPK the primary key of the new template's related entity
	* @param type the template's type. For more information, see
	DDMTemplateConstants in the dynamic-data-mapping-api module.
	* @param serviceContext the service context to be applied. Can set the
	creation date, modification date, guest permissions, and group
	permissions for the new templates.
	* @return the new templates
	* @throws PortalException if a portal exception occurred
	*/
	public List<DDMTemplate> copyTemplates(long userId, long classNameId,
		long oldClassPK, long newClassPK, java.lang.String type,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns a range of all the d d m templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of d d m templates
	* @param end the upper bound of the range of d d m templates (not inclusive)
	* @return the range of d d m templates
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMTemplate> getDDMTemplates(int start, int end);

	/**
	* Returns all the d d m templates matching the UUID and company.
	*
	* @param uuid the UUID of the d d m templates
	* @param companyId the primary key of the company
	* @return the matching d d m templates, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMTemplate> getDDMTemplatesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of d d m templates matching the UUID and company.
	*
	* @param uuid the UUID of the d d m templates
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of d d m templates
	* @param end the upper bound of the range of d d m templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching d d m templates, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMTemplate> getDDMTemplatesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<DDMTemplate> orderByComparator);

	/**
	* Returns all the templates with the class PK.
	*
	* @param classPK the primary key of the template's related entity
	* @return the templates with the class PK
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMTemplate> getTemplates(long classPK);

	/**
	* Returns all the templates matching the group and class name ID.
	*
	* @param groupId the primary key of the group
	* @param classNameId the primary key of the class name for the template's
	related model
	* @return the matching templates
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMTemplate> getTemplates(long groupId, long classNameId);

	/**
	* Returns all the templates matching the group, class name ID, and class
	* PK.
	*
	* @param groupId the primary key of the group
	* @param classNameId the primary key of the class name for the template's
	related model
	* @param classPK the primary key of the template's related entity
	* @return the matching templates
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMTemplate> getTemplates(long groupId, long classNameId,
		long classPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMTemplate> getTemplates(long groupId, long classNameId,
		long classPK, boolean includeAncestorTemplates)
		throws PortalException;

	/**
	* Returns all the templates matching the group, class name ID, class PK,
	* and type.
	*
	* @param groupId the primary key of the group
	* @param classNameId the primary key of the class name for the template's
	related model
	* @param classPK the primary key of the template's related entity
	* @param type the template's type. For more information, see
	DDMTemplateConstants in the dynamic-data-mapping-api module.
	* @return the matching templates
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMTemplate> getTemplates(long groupId, long classNameId,
		long classPK, java.lang.String type);

	/**
	* Returns all the templates matching the group, class name ID, class PK,
	* type, and mode.
	*
	* @param groupId the primary key of the group
	* @param classNameId the primary key of the class name for the template's
	related model
	* @param classPK the primary key of the template's related entity
	* @param type the template's type. For more information, see
	DDMTemplateConstants in the dynamic-data-mapping-api module.
	* @param mode the template's mode. For more information, see
	DDMTemplateConstants in the dynamic-data-mapping-api module.
	* @return the matching templates
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMTemplate> getTemplates(long groupId, long classNameId,
		long classPK, java.lang.String type, java.lang.String mode);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMTemplate> getTemplates(long[] groupIds, long classNameId,
		long classPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMTemplate> getTemplatesByClassPK(long groupId, long classPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMTemplate> getTemplatesByClassPK(long groupId, long classPK,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMTemplate> getTemplatesByClassPK(long[] groupIds, long classPK);

	/**
	* Returns an ordered range of all the templates matching the group,
	* structure class name ID, and status.
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
	* @param groupId the primary key of the group
	* @param structureClassNameId the primary key of the class name for the
	template's related structure
	* @param status the template's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @param start the lower bound of the range of templates to return
	* @param end the upper bound of the range of templates to return (not
	inclusive)
	* @param orderByComparator the comparator to order the templates
	(optionally <code>null</code>)
	* @return the range of matching templates ordered by the comparator
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMTemplate> getTemplatesByStructureClassNameId(long groupId,
		long structureClassNameId, int status, int start, int end,
		OrderByComparator<DDMTemplate> orderByComparator);

	/**
	* Returns an ordered range of all the templates matching the group, class
	* name ID, class PK, type, mode, and status, and matching the keywords in
	* the template names and descriptions.
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
	* @param companyId the primary key of the template's company
	* @param groupId the primary key of the group
	* @param classNameId the primary key of the class name for the template's
	related model
	* @param classPK the primary key of the template's related entity
	* @param resourceClassNameId the primary key of the class name for
	template's resource model
	* @param keywords the keywords (space separated), which may occur in the
	template's name or description (optionally <code>null</code>)
	* @param type the template's type (optionally <code>null</code>). For more
	information, see DDMTemplateConstants in the
	dynamic-data-mapping-api module.
	* @param mode the template's mode (optionally <code>null</code>). For more
	information, see DDMTemplateConstants in the
	dynamic-data-mapping-api module.
	* @param status the template's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @param start the lower bound of the range of templates to return
	* @param end the upper bound of the range of templates to return (not
	inclusive)
	* @param orderByComparator the comparator to order the templates
	(optionally <code>null</code>)
	* @return the range of matching templates ordered by the comparator
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMTemplate> search(long companyId, long groupId,
		long classNameId, long classPK, long resourceClassNameId,
		java.lang.String keywords, java.lang.String type,
		java.lang.String mode, int status, int start, int end,
		OrderByComparator<DDMTemplate> orderByComparator);

	/**
	* Returns an ordered range of all the templates matching the group, class
	* name ID, class PK, name keyword, description keyword, type, mode, status,
	* and language.
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
	* @param companyId the primary key of the template's company
	* @param groupId the primary key of the group
	* @param classNameId the primary key of the class name for the template's
	related model
	* @param classPK the primary key of the template's related entity
	* @param resourceClassNameId the primary key of the class name for
	template's resource model
	* @param name the name keywords (optionally <code>null</code>)
	* @param description the description keywords (optionally
	<code>null</code>)
	* @param type the template's type (optionally <code>null</code>). For more
	information, see DDMTemplateConstants in the
	dynamic-data-mapping-api module.
	* @param mode the template's mode (optionally <code>null</code>). For more
	information, see DDMTemplateConstants in the
	dynamic-data-mapping-api module.
	* @param language the template's script language (optionally
	<code>null</code>). For more information, see
	DDMTemplateConstants in the dynamic-data-mapping-api module.
	* @param status the template's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @param andOperator whether every field must match its keywords, or just
	one field
	* @param start the lower bound of the range of templates to return
	* @param end the upper bound of the range of templates to return (not
	inclusive)
	* @param orderByComparator the comparator to order the templates
	(optionally <code>null</code>)
	* @return the range of matching templates ordered by the comparator
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMTemplate> search(long companyId, long groupId,
		long classNameId, long classPK, long resourceClassNameId,
		java.lang.String name, java.lang.String description,
		java.lang.String type, java.lang.String mode,
		java.lang.String language, int status, boolean andOperator, int start,
		int end, OrderByComparator<DDMTemplate> orderByComparator);

	/**
	* Returns an ordered range of all the templates matching the group IDs,
	* class Name IDs, class PK, type, mode, and status, and include the
	* keywords on its names and descriptions.
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
	* @param companyId the primary key of the template's company
	* @param groupIds the primary keys of the groups
	* @param classNameIds the primary keys of the entity's instances the
	templates are related to
	* @param classPKs the primary keys of the template's related entities
	* @param resourceClassNameId the primary key of the class name for
	template's resource model
	* @param keywords the keywords (space separated), which may occur in the
	template's name or description (optionally <code>null</code>)
	* @param type the template's type (optionally <code>null</code>). For more
	information, see DDMTemplateConstants in the
	dynamic-data-mapping-api module.
	* @param mode the template's mode (optionally <code>null</code>). For more
	information, see DDMTemplateConstants in the
	dynamic-data-mapping-api module.
	* @param status the template's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @param start the lower bound of the range of templates to return
	* @param end the upper bound of the range of templates to return (not
	inclusive)
	* @param orderByComparator the comparator to order the templates
	(optionally <code>null</code>)
	* @return the range of matching templates ordered by the comparator
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMTemplate> search(long companyId, long[] groupIds,
		long[] classNameIds, long[] classPKs, long resourceClassNameId,
		java.lang.String keywords, java.lang.String type,
		java.lang.String mode, int status, int start, int end,
		OrderByComparator<DDMTemplate> orderByComparator);

	/**
	* Returns an ordered range of all the templates matching the group IDs,
	* class name IDs, class PK, name keyword, description keyword, type, mode,
	* language, and status.
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
	* @param companyId the primary key of the template's company
	* @param groupIds the primary keys of the groups
	* @param classNameIds the primary keys of the entity's instances the
	templates are related to
	* @param classPKs the primary keys of the template's related entities
	* @param resourceClassNameId the primary key of the class name for
	template's resource model
	* @param name the name keywords (optionally <code>null</code>)
	* @param description the description keywords (optionally
	<code>null</code>)
	* @param type the template's type (optionally <code>null</code>). For more
	information, see DDMTemplateConstants in the
	dynamic-data-mapping-api module.
	* @param mode the template's mode (optionally <code>null</code>). For more
	information, see DDMTemplateConstants in the
	dynamic-data-mapping-api module.
	* @param language the template's script language (optionally
	<code>null</code>). For more information, see
	DDMTemplateConstants in the dynamic-data-mapping-api module.
	* @param status the template's workflow status. For more information see
	{@link WorkflowConstants} for constants starting with the
	"STATUS_" prefix.
	* @param andOperator whether every field must match its keywords, or just
	one field.
	* @param start the lower bound of the range of templates to return
	* @param end the upper bound of the range of templates to return (not
	inclusive)
	* @param orderByComparator the comparator to order the templates
	(optionally <code>null</code>)
	* @return the range of matching templates ordered by the comparator
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMTemplate> search(long companyId, long[] groupIds,
		long[] classNameIds, long[] classPKs, long resourceClassNameId,
		java.lang.String name, java.lang.String description,
		java.lang.String type, java.lang.String mode,
		java.lang.String language, int status, boolean andOperator, int start,
		int end, OrderByComparator<DDMTemplate> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	/**
	* Adds the resources to the template.
	*
	* @param template the template to add resources to
	* @param addGroupPermissions whether to add group permissions
	* @param addGuestPermissions whether to add guest permissions
	* @throws PortalException
	*/
	public void addTemplateResources(DDMTemplate template,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws PortalException;

	/**
	* Adds the model resources with the permissions to the template.
	*
	* @param template the template to add resources to
	* @param modelPermissions the model permissions to be added
	* @throws PortalException if a portal exception occurred
	*/
	public void addTemplateResources(DDMTemplate template,
		ModelPermissions modelPermissions) throws PortalException;

	/**
	* Deletes the template and its resources.
	*
	* @param template the template to be deleted
	* @throws PortalException if a portal exception occurred
	*/
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public void deleteTemplate(DDMTemplate template) throws PortalException;

	/**
	* Deletes the template and its resources.
	*
	* @param templateId the primary key of the template to be deleted
	* @throws PortalException if a portal exception occurred
	*/
	public void deleteTemplate(long templateId) throws PortalException;

	/**
	* Deletes all the templates of the group.
	*
	* @param groupId the primary key of the group
	* @throws PortalException if a portal exception occurred
	*/
	public void deleteTemplates(long groupId) throws PortalException;

	public void deleteTemplates(long groupId, long classNameId)
		throws PortalException;

	public void revertTemplate(long userId, long templateId,
		java.lang.String version, ServiceContext serviceContext)
		throws PortalException;
}