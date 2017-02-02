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

package com.liferay.portal.service.impl;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.EmailAddress;
import com.liferay.portal.kernel.model.OrgLabor;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.Website;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.security.membershippolicy.OrganizationMembershipPolicyUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.service.permission.OrganizationPermissionUtil;
import com.liferay.portal.kernel.service.permission.PasswordPolicyPermissionUtil;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.service.permission.UserPermissionUtil;
import com.liferay.portal.service.base.OrganizationServiceBaseImpl;
import com.liferay.users.admin.kernel.util.UsersAdminUtil;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the remote service for accessing, adding, deleting, and updating
 * organizations. Its methods include permission checks.
 *
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 * @author Julio Camarero
 */
public class OrganizationServiceImpl extends OrganizationServiceBaseImpl {

	/**
	 * Adds the organizations to the group.
	 *
	 * @param groupId the primary key of the group
	 * @param organizationIds the primary keys of the organizations
	 */
	@Override
	public void addGroupOrganizations(long groupId, long[] organizationIds)
		throws PortalException {

		GroupPermissionUtil.check(
			getPermissionChecker(), groupId, ActionKeys.ASSIGN_MEMBERS);

		organizationLocalService.addGroupOrganizations(
			groupId, organizationIds);
	}

	/**
	 * Adds an organization with additional parameters.
	 *
	 * <p>
	 * This method handles the creation and bookkeeping of the organization
	 * including its resources, metadata, and internal data structures.
	 * </p>
	 *
	 * @param  parentOrganizationId the primary key of the organization's parent
	 *         organization
	 * @param  name the organization's name
	 * @param  type the organization's type
	 * @param  regionId the primary key of the organization's region
	 * @param  countryId the primary key of the organization's country
	 * @param  statusId the organization's workflow status
	 * @param  comments the comments about the organization
	 * @param  site whether the organization is to be associated with a main
	 *         site
	 * @param  addresses the organization's addresses
	 * @param  emailAddresses the organization's email addresses
	 * @param  orgLabors the organization's hours of operation
	 * @param  phones the organization's phone numbers
	 * @param  websites the organization's websites
	 * @param  serviceContext the service context to be applied (optionally
	 *         <code>null</code>). Can set asset category IDs, asset tag names,
	 *         and expando bridge attributes for the organization.
	 * @return the organization
	 */
	@Override
	public Organization addOrganization(
			long parentOrganizationId, String name, String type, long regionId,
			long countryId, long statusId, String comments, boolean site,
			List<Address> addresses, List<EmailAddress> emailAddresses,
			List<OrgLabor> orgLabors, List<Phone> phones,
			List<Website> websites, ServiceContext serviceContext)
		throws PortalException {

		boolean indexingEnabled = true;

		if (serviceContext != null) {
			indexingEnabled = serviceContext.isIndexingEnabled();

			serviceContext.setIndexingEnabled(false);
		}

		try {
			Organization organization = addOrganization(
				parentOrganizationId, name, type, regionId, countryId, statusId,
				comments, site, serviceContext);

			UsersAdminUtil.updateAddresses(
				Organization.class.getName(), organization.getOrganizationId(),
				addresses);

			UsersAdminUtil.updateEmailAddresses(
				Organization.class.getName(), organization.getOrganizationId(),
				emailAddresses);

			UsersAdminUtil.updateOrgLabors(
				organization.getOrganizationId(), orgLabors);

			UsersAdminUtil.updatePhones(
				Organization.class.getName(), organization.getOrganizationId(),
				phones);

			UsersAdminUtil.updateWebsites(
				Organization.class.getName(), organization.getOrganizationId(),
				websites);

			if (indexingEnabled) {
				Indexer<Organization> indexer =
					IndexerRegistryUtil.nullSafeGetIndexer(Organization.class);

				indexer.reindex(organization);
			}

			return organization;
		}
		finally {
			if (serviceContext != null) {
				serviceContext.setIndexingEnabled(indexingEnabled);
			}
		}
	}

	/**
	 * Adds an organization.
	 *
	 * <p>
	 * This method handles the creation and bookkeeping of the organization
	 * including its resources, metadata, and internal data structures.
	 * </p>
	 *
	 * @param  parentOrganizationId the primary key of the organization's parent
	 *         organization
	 * @param  name the organization's name
	 * @param  type the organization's type
	 * @param  regionId the primary key of the organization's region
	 * @param  countryId the primary key of the organization's country
	 * @param  statusId the organization's workflow status
	 * @param  comments the comments about the organization
	 * @param  site whether the organization is to be associated with a main
	 *         site
	 * @param  serviceContext the service context to be applied (optionally
	 *         <code>null</code>). Can set asset category IDs, asset tag names,
	 *         and expando bridge attributes for the organization.
	 * @return the organization
	 */
	@Override
	public Organization addOrganization(
			long parentOrganizationId, String name, String type, long regionId,
			long countryId, long statusId, String comments, boolean site,
			ServiceContext serviceContext)
		throws PortalException {

		if (parentOrganizationId ==
				OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID) {

			PortalPermissionUtil.check(
				getPermissionChecker(), ActionKeys.ADD_ORGANIZATION);
		}
		else {
			OrganizationPermissionUtil.check(
				getPermissionChecker(), parentOrganizationId,
				ActionKeys.ADD_ORGANIZATION);
		}

		Organization organization = organizationLocalService.addOrganization(
			getUserId(), parentOrganizationId, name, type, regionId, countryId,
			statusId, comments, site, serviceContext);

		OrganizationMembershipPolicyUtil.verifyPolicy(organization);

		return organization;
	}

	/**
	 * Assigns the password policy to the organizations, removing any other
	 * currently assigned password policies.
	 *
	 * @param passwordPolicyId the primary key of the password policy
	 * @param organizationIds the primary keys of the organizations
	 */
	@Override
	public void addPasswordPolicyOrganizations(
			long passwordPolicyId, long[] organizationIds)
		throws PortalException {

		PasswordPolicyPermissionUtil.check(
			getPermissionChecker(), passwordPolicyId, ActionKeys.UPDATE);

		organizationLocalService.addPasswordPolicyOrganizations(
			passwordPolicyId, organizationIds);
	}

	/**
	 * Deletes the organization's logo.
	 *
	 * @param organizationId the primary key of the organization
	 */
	@Override
	public void deleteLogo(long organizationId) throws PortalException {
		OrganizationPermissionUtil.check(
			getPermissionChecker(), organizationId, ActionKeys.UPDATE);

		organizationLocalService.deleteLogo(organizationId);
	}

	/**
	 * Deletes the organization. The organization's associated resources and
	 * assets are also deleted.
	 *
	 * @param organizationId the primary key of the organization
	 */
	@Override
	public void deleteOrganization(long organizationId) throws PortalException {
		OrganizationPermissionUtil.check(
			getPermissionChecker(), organizationId, ActionKeys.DELETE);

		organizationLocalService.deleteOrganization(organizationId);
	}

	/**
	 * Returns the organization with the primary key.
	 *
	 * @param  organizationId the primary key of the organization
	 * @return the organization with the primary key, or <code>null</code> if an
	 *         organization with the primary key could not be found or if the
	 *         user did not have permission to view the organization
	 */
	@Override
	public Organization fetchOrganization(long organizationId)
		throws PortalException {

		Organization organization = organizationLocalService.fetchOrganization(
			organizationId);

		if (organization != null) {
			OrganizationPermissionUtil.check(
				getPermissionChecker(), organization, ActionKeys.VIEW);
		}

		return organization;
	}

	/**
	 * Returns the organization with the primary key.
	 *
	 * @param  organizationId the primary key of the organization
	 * @return the organization with the primary key
	 */
	@Override
	public Organization getOrganization(long organizationId)
		throws PortalException {

		Organization organization = organizationLocalService.getOrganization(
			organizationId);

		OrganizationPermissionUtil.check(
			getPermissionChecker(), organization, ActionKeys.VIEW);

		return organization;
	}

	/**
	 * Returns the primary key of the organization with the name.
	 *
	 * @param  companyId the primary key of the organization's company
	 * @param  name the organization's name
	 * @return the primary key of the organization with the name, or
	 *         <code>0</code> if the organization could not be found
	 */
	@Override
	public long getOrganizationId(long companyId, String name)
		throws PortalException {

		long organizationId = organizationLocalService.getOrganizationId(
			companyId, name);

		OrganizationPermissionUtil.check(
			getPermissionChecker(), organizationId, ActionKeys.VIEW);

		return organizationId;
	}

	/**
	 * Returns all the organizations belonging to the parent organization.
	 *
	 * @param  companyId the primary key of the organizations' company
	 * @param  parentOrganizationId the primary key of the organizations' parent
	 *         organization
	 * @return the organizations belonging to the parent organization
	 */
	@Override
	public List<Organization> getOrganizations(
		long companyId, long parentOrganizationId) {

		if (parentOrganizationId ==
				OrganizationConstants.ANY_PARENT_ORGANIZATION_ID) {

			return organizationPersistence.filterFindByCompanyId(companyId);
		}

		return organizationPersistence.filterFindByC_P(
			companyId, parentOrganizationId);
	}

	/**
	 * Returns a range of all the organizations belonging to the parent
	 * organization.
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
	 * @param  companyId the primary key of the organizations' company
	 * @param  parentOrganizationId the primary key of the organizations' parent
	 *         organization
	 * @param  start the lower bound of the range of organizations to return
	 * @param  end the upper bound of the range of organizations to return (not
	 *         inclusive)
	 * @return the range of organizations belonging to the parent organization
	 */
	@Override
	public List<Organization> getOrganizations(
		long companyId, long parentOrganizationId, int start, int end) {

		if (parentOrganizationId ==
				OrganizationConstants.ANY_PARENT_ORGANIZATION_ID) {

			return organizationPersistence.filterFindByCompanyId(
				companyId, start, end);
		}

		return organizationPersistence.filterFindByC_P(
			companyId, parentOrganizationId, start, end);
	}

	/**
	 * Returns the number of organizations belonging to the parent organization.
	 *
	 * @param  companyId the primary key of the organizations' company
	 * @param  parentOrganizationId the primary key of the organizations' parent
	 *         organization
	 * @return the number of organizations belonging to the parent organization
	 */
	@Override
	public int getOrganizationsCount(
		long companyId, long parentOrganizationId) {

		if (parentOrganizationId ==
				OrganizationConstants.ANY_PARENT_ORGANIZATION_ID) {

			return organizationPersistence.filterCountByCompanyId(companyId);
		}

		return organizationPersistence.filterCountByC_P(
			companyId, parentOrganizationId);
	}

	/**
	 * Returns all the organizations with which the user is explicitly
	 * associated.
	 *
	 * <p>
	 * A user is considered to be <i>explicitly</i> associated with an
	 * organization if his account is individually created within the
	 * organization or if the user is later added as a member.
	 * </p>
	 *
	 * @param  userId the primary key of the user
	 * @return the organizations with which the user is explicitly associated
	 */
	@Override
	public List<Organization> getUserOrganizations(long userId)
		throws PortalException {

		UserPermissionUtil.check(
			getPermissionChecker(), userId, ActionKeys.VIEW);

		return organizationLocalService.getUserOrganizations(userId);
	}

	/**
	 * Sets the organizations in the group, removing and adding organizations to
	 * the group as necessary.
	 *
	 * @param groupId the primary key of the group
	 * @param organizationIds the primary keys of the organizations
	 */
	@Override
	public void setGroupOrganizations(long groupId, long[] organizationIds)
		throws PortalException {

		GroupPermissionUtil.check(
			getPermissionChecker(), groupId, ActionKeys.ASSIGN_MEMBERS);

		organizationLocalService.setGroupOrganizations(
			groupId, organizationIds);
	}

	/**
	 * Removes the organizations from the group.
	 *
	 * @param groupId the primary key of the group
	 * @param organizationIds the primary keys of the organizations
	 */
	@Override
	public void unsetGroupOrganizations(long groupId, long[] organizationIds)
		throws PortalException {

		GroupPermissionUtil.check(
			getPermissionChecker(), groupId, ActionKeys.ASSIGN_MEMBERS);

		organizationLocalService.unsetGroupOrganizations(
			groupId, organizationIds);
	}

	/**
	 * Removes the organizations from the password policy.
	 *
	 * @param passwordPolicyId the primary key of the password policy
	 * @param organizationIds the primary keys of the organizations
	 */
	@Override
	public void unsetPasswordPolicyOrganizations(
			long passwordPolicyId, long[] organizationIds)
		throws PortalException {

		PasswordPolicyPermissionUtil.check(
			getPermissionChecker(), passwordPolicyId, ActionKeys.UPDATE);

		organizationLocalService.unsetPasswordPolicyOrganizations(
			passwordPolicyId, organizationIds);
	}

	/**
	 * Updates the organization with additional parameters.
	 *
	 * @param  organizationId the primary key of the organization
	 * @param  parentOrganizationId the primary key of the organization's parent
	 *         organization
	 * @param  name the organization's name
	 * @param  type the organization's type
	 * @param  regionId the primary key of the organization's region
	 * @param  countryId the primary key of the organization's country
	 * @param  statusId the organization's workflow status
	 * @param  comments the comments about the organization
	 * @param  logo whether to update the ogranization's logo
	 * @param  logoBytes the new logo image data
	 * @param  site whether the organization is to be associated with a main
	 *         site
	 * @param  addresses the organization's addresses
	 * @param  emailAddresses the organization's email addresses
	 * @param  orgLabors the organization's hours of operation
	 * @param  phones the organization's phone numbers
	 * @param  websites the organization's websites
	 * @param  serviceContext the service context to be applied (optionally
	 *         <code>null</code>). Can set asset category IDs and asset tag
	 *         names for the organization, and merge expando bridge attributes
	 *         for the organization.
	 * @return the organization
	 */
	@Override
	public Organization updateOrganization(
			long organizationId, long parentOrganizationId, String name,
			String type, long regionId, long countryId, long statusId,
			String comments, boolean logo, byte[] logoBytes, boolean site,
			List<Address> addresses, List<EmailAddress> emailAddresses,
			List<OrgLabor> orgLabors, List<Phone> phones,
			List<Website> websites, ServiceContext serviceContext)
		throws PortalException {

		Organization organization = organizationPersistence.findByPrimaryKey(
			organizationId);

		OrganizationPermissionUtil.check(
			getPermissionChecker(), organization, ActionKeys.UPDATE);

		if (organization.getParentOrganizationId() != parentOrganizationId) {
			if (parentOrganizationId ==
					OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID) {

				PortalPermissionUtil.check(
					getPermissionChecker(), ActionKeys.ADD_ORGANIZATION);
			}
			else {
				OrganizationPermissionUtil.check(
					getPermissionChecker(), parentOrganizationId,
					ActionKeys.ADD_ORGANIZATION);
			}
		}

		if (addresses != null) {
			UsersAdminUtil.updateAddresses(
				Organization.class.getName(), organizationId, addresses);
		}

		if (emailAddresses != null) {
			UsersAdminUtil.updateEmailAddresses(
				Organization.class.getName(), organizationId, emailAddresses);
		}

		if (orgLabors != null) {
			UsersAdminUtil.updateOrgLabors(organizationId, orgLabors);
		}

		if (phones != null) {
			UsersAdminUtil.updatePhones(
				Organization.class.getName(), organizationId, phones);
		}

		if (websites != null) {
			UsersAdminUtil.updateWebsites(
				Organization.class.getName(), organizationId, websites);
		}

		User user = getUser();

		Organization oldOrganization = organization;

		List<AssetCategory> oldAssetCategories =
			assetCategoryLocalService.getCategories(
				Organization.class.getName(), organizationId);

		List<AssetTag> oldAssetTags = assetTagLocalService.getTags(
			Organization.class.getName(), organizationId);

		ExpandoBridge oldExpandoBridge = oldOrganization.getExpandoBridge();

		Map<String, Serializable> oldExpandoAttributes =
			oldExpandoBridge.getAttributes();

		organization = organizationLocalService.updateOrganization(
			user.getCompanyId(), organizationId, parentOrganizationId, name,
			type, regionId, countryId, statusId, comments, logo, logoBytes,
			site, serviceContext);

		OrganizationMembershipPolicyUtil.verifyPolicy(
			organization, oldOrganization, oldAssetCategories, oldAssetTags,
			oldExpandoAttributes);

		return organization;
	}

	/**
	 * Updates the organization with additional parameters.
	 *
	 * @param      organizationId the primary key of the organization
	 * @param      parentOrganizationId the primary key of the organization's
	 *             parent organization
	 * @param      name the organization's name
	 * @param      type the organization's type
	 * @param      regionId the primary key of the organization's region
	 * @param      countryId the primary key of the organization's country
	 * @param      statusId the organization's workflow status
	 * @param      comments the comments about the organization
	 * @param      site whether the organization is to be associated with a main
	 *             site
	 * @param      addresses the organization's addresses
	 * @param      emailAddresses the organization's email addresses
	 * @param      orgLabors the organization's hours of operation
	 * @param      phones the organization's phone numbers
	 * @param      websites the organization's websites
	 * @param      serviceContext the service context to be applied (optionally
	 *             <code>null</code>). Can set asset category IDs and asset tag
	 *             names for the organization, and merge expando bridge
	 *             attributes for the organization.
	 * @return     the organization
	 * @deprecated As of 7.0.0, replaced by {@link #updateOrganization(long,
	 *             long, String, String, long, long, long, String, boolean,
	 *             byte[], boolean, List, List, List, List, List,
	 *             ServiceContext)}
	 */
	@Deprecated
	@Override
	public Organization updateOrganization(
			long organizationId, long parentOrganizationId, String name,
			String type, long regionId, long countryId, long statusId,
			String comments, boolean site, List<Address> addresses,
			List<EmailAddress> emailAddresses, List<OrgLabor> orgLabors,
			List<Phone> phones, List<Website> websites,
			ServiceContext serviceContext)
		throws PortalException {

		return updateOrganization(
			organizationId, parentOrganizationId, name, type, regionId,
			countryId, statusId, comments, true, null, site, addresses,
			emailAddresses, orgLabors, phones, websites, serviceContext);
	}

	/**
	 * Updates the organization.
	 *
	 * @param  organizationId the primary key of the organization
	 * @param  parentOrganizationId the primary key of the organization's parent
	 *         organization
	 * @param  name the organization's name
	 * @param  type the organization's type
	 * @param  regionId the primary key of the organization's region
	 * @param  countryId the primary key of the organization's country
	 * @param  statusId the organization's workflow status
	 * @param  comments the comments about the organization
	 * @param  site whether the organization is to be associated with a main
	 *         site
	 * @param  serviceContext the service context to be applied (optionally
	 *         <code>null</code>). Can set asset category IDs and asset tag
	 *         names for the organization, and merge expando bridge attributes
	 *         for the organization.
	 * @return the organization
	 */
	@Override
	public Organization updateOrganization(
			long organizationId, long parentOrganizationId, String name,
			String type, long regionId, long countryId, long statusId,
			String comments, boolean site, ServiceContext serviceContext)
		throws PortalException {

		return updateOrganization(
			organizationId, parentOrganizationId, name, type, regionId,
			countryId, statusId, comments, true, null, site, null, null, null,
			null, null, serviceContext);
	}

}