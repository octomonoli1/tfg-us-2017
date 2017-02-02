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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DDMTemplateLinkLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DDMTemplateLinkLocalService
 * @generated
 */
@ProviderType
public class DDMTemplateLinkLocalServiceWrapper
	implements DDMTemplateLinkLocalService,
		ServiceWrapper<DDMTemplateLinkLocalService> {
	public DDMTemplateLinkLocalServiceWrapper(
		DDMTemplateLinkLocalService ddmTemplateLinkLocalService) {
		_ddmTemplateLinkLocalService = ddmTemplateLinkLocalService;
	}

	/**
	* Adds the d d m template link to the database. Also notifies the appropriate model listeners.
	*
	* @param ddmTemplateLink the d d m template link
	* @return the d d m template link that was added
	*/
	@Override
	public com.liferay.dynamic.data.mapping.model.DDMTemplateLink addDDMTemplateLink(
		com.liferay.dynamic.data.mapping.model.DDMTemplateLink ddmTemplateLink) {
		return _ddmTemplateLinkLocalService.addDDMTemplateLink(ddmTemplateLink);
	}

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMTemplateLink addTemplateLink(
		long classNameId, long classPK, long templateId) {
		return _ddmTemplateLinkLocalService.addTemplateLink(classNameId,
			classPK, templateId);
	}

	/**
	* Creates a new d d m template link with the primary key. Does not add the d d m template link to the database.
	*
	* @param templateLinkId the primary key for the new d d m template link
	* @return the new d d m template link
	*/
	@Override
	public com.liferay.dynamic.data.mapping.model.DDMTemplateLink createDDMTemplateLink(
		long templateLinkId) {
		return _ddmTemplateLinkLocalService.createDDMTemplateLink(templateLinkId);
	}

	/**
	* Deletes the d d m template link from the database. Also notifies the appropriate model listeners.
	*
	* @param ddmTemplateLink the d d m template link
	* @return the d d m template link that was removed
	*/
	@Override
	public com.liferay.dynamic.data.mapping.model.DDMTemplateLink deleteDDMTemplateLink(
		com.liferay.dynamic.data.mapping.model.DDMTemplateLink ddmTemplateLink) {
		return _ddmTemplateLinkLocalService.deleteDDMTemplateLink(ddmTemplateLink);
	}

	/**
	* Deletes the d d m template link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param templateLinkId the primary key of the d d m template link
	* @return the d d m template link that was removed
	* @throws PortalException if a d d m template link with the primary key could not be found
	*/
	@Override
	public com.liferay.dynamic.data.mapping.model.DDMTemplateLink deleteDDMTemplateLink(
		long templateLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ddmTemplateLinkLocalService.deleteDDMTemplateLink(templateLinkId);
	}

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMTemplateLink deleteTemplateLink(
		com.liferay.dynamic.data.mapping.model.DDMTemplateLink templateLink) {
		return _ddmTemplateLinkLocalService.deleteTemplateLink(templateLink);
	}

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMTemplateLink deleteTemplateLink(
		long classNameId, long classPK) {
		return _ddmTemplateLinkLocalService.deleteTemplateLink(classNameId,
			classPK);
	}

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMTemplateLink deleteTemplateLink(
		long templateLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ddmTemplateLinkLocalService.deleteTemplateLink(templateLinkId);
	}

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMTemplateLink fetchDDMTemplateLink(
		long templateLinkId) {
		return _ddmTemplateLinkLocalService.fetchDDMTemplateLink(templateLinkId);
	}

	/**
	* Returns the d d m template link with the primary key.
	*
	* @param templateLinkId the primary key of the d d m template link
	* @return the d d m template link
	* @throws PortalException if a d d m template link with the primary key could not be found
	*/
	@Override
	public com.liferay.dynamic.data.mapping.model.DDMTemplateLink getDDMTemplateLink(
		long templateLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ddmTemplateLinkLocalService.getDDMTemplateLink(templateLinkId);
	}

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMTemplateLink getTemplateLink(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ddmTemplateLinkLocalService.getTemplateLink(classNameId, classPK);
	}

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMTemplateLink getTemplateLink(
		long templateLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ddmTemplateLinkLocalService.getTemplateLink(templateLinkId);
	}

	/**
	* Updates the d d m template link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ddmTemplateLink the d d m template link
	* @return the d d m template link that was updated
	*/
	@Override
	public com.liferay.dynamic.data.mapping.model.DDMTemplateLink updateDDMTemplateLink(
		com.liferay.dynamic.data.mapping.model.DDMTemplateLink ddmTemplateLink) {
		return _ddmTemplateLinkLocalService.updateDDMTemplateLink(ddmTemplateLink);
	}

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMTemplateLink updateTemplateLink(
		long classNameId, long classPK, long templateId) {
		return _ddmTemplateLinkLocalService.updateTemplateLink(classNameId,
			classPK, templateId);
	}

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMTemplateLink updateTemplateLink(
		long templateLinkId, long templateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ddmTemplateLinkLocalService.updateTemplateLink(templateLinkId,
			templateId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ddmTemplateLinkLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ddmTemplateLinkLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ddmTemplateLinkLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ddmTemplateLinkLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ddmTemplateLinkLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of d d m template links.
	*
	* @return the number of d d m template links
	*/
	@Override
	public int getDDMTemplateLinksCount() {
		return _ddmTemplateLinkLocalService.getDDMTemplateLinksCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ddmTemplateLinkLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _ddmTemplateLinkLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _ddmTemplateLinkLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _ddmTemplateLinkLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the d d m template links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMTemplateLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of d d m template links
	* @param end the upper bound of the range of d d m template links (not inclusive)
	* @return the range of d d m template links
	*/
	@Override
	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMTemplateLink> getDDMTemplateLinks(
		int start, int end) {
		return _ddmTemplateLinkLocalService.getDDMTemplateLinks(start, end);
	}

	@Override
	public java.util.List<com.liferay.dynamic.data.mapping.model.DDMTemplateLink> getTemplateLinks(
		long classNameId) {
		return _ddmTemplateLinkLocalService.getTemplateLinks(classNameId);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _ddmTemplateLinkLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _ddmTemplateLinkLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public DDMTemplateLinkLocalService getWrappedService() {
		return _ddmTemplateLinkLocalService;
	}

	@Override
	public void setWrappedService(
		DDMTemplateLinkLocalService ddmTemplateLinkLocalService) {
		_ddmTemplateLinkLocalService = ddmTemplateLinkLocalService;
	}

	private DDMTemplateLinkLocalService _ddmTemplateLinkLocalService;
}