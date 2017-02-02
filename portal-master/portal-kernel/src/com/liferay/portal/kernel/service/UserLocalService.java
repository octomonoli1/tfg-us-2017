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

package com.liferay.portal.kernel.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.spring.aop.Skip;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the local service interface for User. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserLocalServiceUtil
 * @see com.liferay.portal.service.base.UserLocalServiceBaseImpl
 * @see com.liferay.portal.service.impl.UserLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface UserLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserLocalServiceUtil} to access the user local service. Add custom service methods to {@link com.liferay.portal.service.impl.UserLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Attempts to authenticate the user using JAAS credentials, without using
	* the AuthPipeline.
	*
	* @param userId the primary key of the user
	* @param encPassword the encrypted password
	* @return <code>true</code> if authentication is successful;
	<code>false</code> otherwise
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean authenticateForJAAS(long userId, java.lang.String encPassword);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasGroupUser(long groupId, long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasGroupUsers(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasOrganizationUser(long organizationId, long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasOrganizationUsers(long organizationId);

	/**
	* Returns <code>true</code> if the password policy has been assigned to the
	* user.
	*
	* @param passwordPolicyId the primary key of the password policy
	* @param userId the primary key of the user
	* @return <code>true</code> if the password policy is assigned to the user;
	<code>false</code> otherwise
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasPasswordPolicyUser(long passwordPolicyId, long userId);

	/**
	* Returns <code>true</code> if the user has the role with the name,
	* optionally through inheritance.
	*
	* @param companyId the primary key of the role's company
	* @param name the name of the role (must be a regular role, not an
	organization, site or provider role)
	* @param userId the primary key of the user
	* @param inherited whether to include roles inherited from organizations,
	sites, etc.
	* @return <code>true</code> if the user has the role; <code>false</code>
	otherwise
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasRoleUser(long companyId, java.lang.String name,
		long userId, boolean inherited) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasRoleUser(long roleId, long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasRoleUsers(long roleId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTeamUser(long teamId, long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTeamUsers(long teamId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasUserGroupUser(long userGroupId, long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasUserGroupUsers(long userGroupId);

	/**
	* Returns <code>true</code> if the user's password is expired.
	*
	* @param user the user
	* @return <code>true</code> if the user's password is expired;
	<code>false</code> otherwise
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isPasswordExpired(User user) throws PortalException;

	/**
	* Returns <code>true</code> if the password policy is configured to warn
	* the user that his password is expiring and the remaining time until
	* expiration is equal or less than the configured warning time.
	*
	* @param user the user
	* @return <code>true</code> if the user's password is expiring soon;
	<code>false</code> otherwise
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isPasswordExpiringSoon(User user) throws PortalException;

	/**
	* Sends the password email to the user with the email address. The content
	* of this email can be specified in <code>portal.properties</code> with the
	* <code>admin.email.password</code> keys.
	*
	* @param companyId the primary key of the user's company
	* @param emailAddress the user's email address
	* @param fromName the name of the individual that the email should be from
	* @param fromAddress the address of the individual that the email should be
	from
	* @param subject the email subject. If <code>null</code>, the subject
	specified in <code>portal.properties</code> will be used.
	* @param body the email body. If <code>null</code>, the body specified in
	<code>portal.properties</code> will be used.
	* @param serviceContext the service context to be applied
	*/
	public boolean sendPassword(long companyId, java.lang.String emailAddress,
		java.lang.String fromName, java.lang.String fromAddress,
		java.lang.String subject, java.lang.String body,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Sends a password notification email to the user matching the email
	* address. The portal's settings determine whether a password is sent
	* explicitly or whether a link for resetting the user's password is sent.
	* The method sends the email asynchronously and returns before the email is
	* sent.
	*
	* <p>
	* The content of the notification email is specified with the
	* <code>admin.email.password</code> portal property keys. They can be
	* overridden via a <code>portal-ext.properties</code> file or modified
	* through the Portal Settings UI.
	* </p>
	*
	* @param companyId the primary key of the user's company
	* @param emailAddress the user's email address
	* @return <code>true</code> if the notification email includes a new
	password; <code>false</code> if the notification email only
	contains a reset link
	*/
	public boolean sendPasswordByEmailAddress(long companyId,
		java.lang.String emailAddress) throws PortalException;

	/**
	* Sends a password notification email to the user matching the screen name.
	* The portal's settings determine whether a password is sent explicitly or
	* whether a link for resetting the user's password is sent. The method
	* sends the email asynchronously and returns before the email is sent.
	*
	* <p>
	* The content of the notification email is specified with the
	* <code>admin.email.password</code> portal property keys. They can be
	* overridden via a <code>portal-ext.properties</code> file or modified
	* through the Portal Settings UI.
	* </p>
	*
	* @param companyId the primary key of the user's company
	* @param screenName the user's screen name
	* @return <code>true</code> if the notification email includes a new
	password; <code>false</code> if the notification email only
	contains a reset link
	*/
	public boolean sendPasswordByScreenName(long companyId,
		java.lang.String screenName) throws PortalException;

	/**
	* Sends a password notification email to the user matching the ID. The
	* portal's settings determine whether a password is sent explicitly or
	* whether a link for resetting the user's password is sent. The method
	* sends the email asynchronously and returns before the email is sent.
	*
	* <p>
	* The content of the notification email is specified with the
	* <code>admin.email.password</code> portal property keys. They can be
	* overridden via a <code>portal-ext.properties</code> file or modified
	* through the Portal Settings UI.
	* </p>
	*
	* @param userId the user's primary key
	* @return <code>true</code> if the notification email includes a new
	password; <code>false</code> if the notification email only
	contains a reset link
	*/
	public boolean sendPasswordByUserId(long userId) throws PortalException;

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
	* Adds a default admin user for the company.
	*
	* @param companyId the primary key of the user's company
	* @param screenName the user's screen name
	* @param emailAddress the user's email address
	* @param locale the user's locale
	* @param firstName the user's first name
	* @param middleName the user's middle name
	* @param lastName the user's last name
	* @return the new default admin user
	*/
	public User addDefaultAdminUser(long companyId,
		java.lang.String screenName, java.lang.String emailAddress,
		Locale locale, java.lang.String firstName, java.lang.String middleName,
		java.lang.String lastName) throws PortalException;

	/**
	* Adds the user to the database. Also notifies the appropriate model listeners.
	*
	* @param user the user
	* @return the user that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public User addUser(User user);

	/**
	* Adds a user.
	*
	* <p>
	* This method handles the creation and bookkeeping of the user including
	* its resources, metadata, and internal data structures. It is not
	* necessary to make subsequent calls to any methods to setup default
	* groups, resources, etc.
	* </p>
	*
	* @param creatorUserId the primary key of the creator
	* @param companyId the primary key of the user's company
	* @param autoPassword whether a password should be automatically generated
	for the user
	* @param password1 the user's password
	* @param password2 the user's password confirmation
	* @param autoScreenName whether a screen name should be automatically
	generated for the user
	* @param screenName the user's screen name
	* @param emailAddress the user's email address
	* @param facebookId the user's facebook ID
	* @param openId the user's OpenID
	* @param locale the user's locale
	* @param firstName the user's first name
	* @param middleName the user's middle name
	* @param lastName the user's last name
	* @param prefixId the user's name prefix ID
	* @param suffixId the user's name suffix ID
	* @param male whether the user is male
	* @param birthdayMonth the user's birthday month (0-based, meaning 0 for
	January)
	* @param birthdayDay the user's birthday day
	* @param birthdayYear the user's birthday year
	* @param jobTitle the user's job title
	* @param groupIds the primary keys of the user's groups
	* @param organizationIds the primary keys of the user's organizations
	* @param roleIds the primary keys of the roles this user possesses
	* @param userGroupIds the primary keys of the user's user groups
	* @param sendEmail whether to send the user an email notification about
	their new account
	* @param serviceContext the service context to be applied (optionally
	<code>null</code>). Can set the UUID (with the <code>uuid</code>
	attribute), asset category IDs, asset tag names, and expando
	bridge attributes for the user.
	* @return the new user
	*/
	public User addUser(long creatorUserId, long companyId,
		boolean autoPassword, java.lang.String password1,
		java.lang.String password2, boolean autoScreenName,
		java.lang.String screenName, java.lang.String emailAddress,
		long facebookId, java.lang.String openId, Locale locale,
		java.lang.String firstName, java.lang.String middleName,
		java.lang.String lastName, long prefixId, long suffixId, boolean male,
		int birthdayMonth, int birthdayDay, int birthdayYear,
		java.lang.String jobTitle, long[] groupIds, long[] organizationIds,
		long[] roleIds, long[] userGroupIds, boolean sendEmail,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Adds a user with workflow.
	*
	* <p>
	* This method handles the creation and bookkeeping of the user including
	* its resources, metadata, and internal data structures. It is not
	* necessary to make subsequent calls to any methods to setup default
	* groups, resources, etc.
	* </p>
	*
	* @param creatorUserId the primary key of the creator
	* @param companyId the primary key of the user's company
	* @param autoPassword whether a password should be automatically generated
	for the user
	* @param password1 the user's password
	* @param password2 the user's password confirmation
	* @param autoScreenName whether a screen name should be automatically
	generated for the user
	* @param screenName the user's screen name
	* @param emailAddress the user's email address
	* @param facebookId the user's facebook ID
	* @param openId the user's OpenID
	* @param locale the user's locale
	* @param firstName the user's first name
	* @param middleName the user's middle name
	* @param lastName the user's last name
	* @param prefixId the user's name prefix ID
	* @param suffixId the user's name suffix ID
	* @param male whether the user is male
	* @param birthdayMonth the user's birthday month (0-based, meaning 0 for
	January)
	* @param birthdayDay the user's birthday day
	* @param birthdayYear the user's birthday year
	* @param jobTitle the user's job title
	* @param groupIds the primary keys of the user's groups
	* @param organizationIds the primary keys of the user's organizations
	* @param roleIds the primary keys of the roles this user possesses
	* @param userGroupIds the primary keys of the user's user groups
	* @param sendEmail whether to send the user an email notification about
	their new account
	* @param serviceContext the service context to be applied (optionally
	<code>null</code>). Can set the UUID (with the <code>uuid</code>
	attribute), asset category IDs, asset tag names, and expando
	bridge attributes for the user.
	* @return the new user
	*/
	public User addUserWithWorkflow(long creatorUserId, long companyId,
		boolean autoPassword, java.lang.String password1,
		java.lang.String password2, boolean autoScreenName,
		java.lang.String screenName, java.lang.String emailAddress,
		long facebookId, java.lang.String openId, Locale locale,
		java.lang.String firstName, java.lang.String middleName,
		java.lang.String lastName, long prefixId, long suffixId, boolean male,
		int birthdayMonth, int birthdayDay, int birthdayYear,
		java.lang.String jobTitle, long[] groupIds, long[] organizationIds,
		long[] roleIds, long[] userGroupIds, boolean sendEmail,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Creates a new user with the primary key. Does not add the user to the database.
	*
	* @param userId the primary key for the new user
	* @return the new user
	*/
	public User createUser(long userId);

	/**
	* Deletes the user from the database. Also notifies the appropriate model listeners.
	*
	* @param user the user
	* @return the user that was removed
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.DELETE)
	public User deleteUser(User user) throws PortalException;

	/**
	* Deletes the user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userId the primary key of the user
	* @return the user that was removed
	* @throws PortalException if a user with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public User deleteUser(long userId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User fetchUser(long userId);

	/**
	* Returns the user with the contact ID.
	*
	* @param contactId the user's contact ID
	* @return the user with the contact ID, or <code>null</code> if a user with
	the contact ID could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User fetchUserByContactId(long contactId);

	/**
	* Returns the user with the email address.
	*
	* @param companyId the primary key of the user's company
	* @param emailAddress the user's email address
	* @return the user with the email address, or <code>null</code> if a user
	with the email address could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User fetchUserByEmailAddress(long companyId,
		java.lang.String emailAddress);

	/**
	* Returns the user with the Facebook ID.
	*
	* @param companyId the primary key of the user's company
	* @param facebookId the user's Facebook ID
	* @return the user with the Facebook ID, or <code>null</code> if a user
	with the Facebook ID could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User fetchUserByFacebookId(long companyId, long facebookId);

	/**
	* Returns the user with the Google user ID.
	*
	* @param companyId the primary key of the user's company
	* @param googleUserId the user's Google user ID
	* @return the user with the Google user ID, or <code>null</code> if a user
	with the Google user ID could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User fetchUserByGoogleUserId(long companyId,
		java.lang.String googleUserId);

	/**
	* Returns the user with the primary key.
	*
	* @param userId the primary key of the user
	* @return the user with the primary key, or <code>null</code> if a user
	with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User fetchUserById(long userId);

	/**
	* Returns the user with the OpenID.
	*
	* @param companyId the primary key of the user's company
	* @param openId the user's OpenID
	* @return the user with the OpenID, or <code>null</code> if a user with the
	OpenID could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User fetchUserByOpenId(long companyId, java.lang.String openId);

	/**
	* Returns the user with the portrait ID.
	*
	* @param portraitId the user's portrait ID
	* @return the user with the portrait ID, or <code>null</code> if a user
	with the portrait ID could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User fetchUserByPortraitId(long portraitId);

	/**
	* Returns the user with the screen name.
	*
	* @param companyId the primary key of the user's company
	* @param screenName the user's screen name
	* @return the user with the screen name, or <code>null</code> if a user
	with the screen name could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User fetchUserByScreenName(long companyId,
		java.lang.String screenName);

	/**
	* Returns the user with the matching UUID and company.
	*
	* @param uuid the user's UUID
	* @param companyId the primary key of the company
	* @return the matching user, or <code>null</code> if a matching user could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User fetchUserByUuidAndCompanyId(java.lang.String uuid,
		long companyId);

	/**
	* Returns the default user for the company.
	*
	* @param companyId the primary key of the company
	* @return the default user for the company
	*/
	@Skip
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User getDefaultUser(long companyId) throws PortalException;

	/**
	* Returns the user with the primary key.
	*
	* @param userId the primary key of the user
	* @return the user
	* @throws PortalException if a user with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User getUser(long userId) throws PortalException;

	/**
	* Returns the user with the contact ID.
	*
	* @param contactId the user's contact ID
	* @return the user with the contact ID
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User getUserByContactId(long contactId) throws PortalException;

	/**
	* Returns the user with the email address.
	*
	* @param companyId the primary key of the user's company
	* @param emailAddress the user's email address
	* @return the user with the email address
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User getUserByEmailAddress(long companyId,
		java.lang.String emailAddress) throws PortalException;

	/**
	* Returns the user with the Facebook ID.
	*
	* @param companyId the primary key of the user's company
	* @param facebookId the user's Facebook ID
	* @return the user with the Facebook ID
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User getUserByFacebookId(long companyId, long facebookId)
		throws PortalException;

	/**
	* Returns the user with the Google user ID.
	*
	* @param companyId the primary key of the user's company
	* @param googleUserId the user's Google user ID
	* @return the user with the Google user ID
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User getUserByGoogleUserId(long companyId,
		java.lang.String googleUserId) throws PortalException;

	/**
	* Returns the user with the primary key from the company.
	*
	* @param companyId the primary key of the user's company
	* @param userId the primary key of the user
	* @return the user with the primary key
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User getUserById(long companyId, long userId)
		throws PortalException;

	/**
	* Returns the user with the primary key.
	*
	* @param userId the primary key of the user
	* @return the user with the primary key
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User getUserById(long userId) throws PortalException;

	/**
	* Returns the user with the OpenID.
	*
	* @param companyId the primary key of the user's company
	* @param openId the user's OpenID
	* @return the user with the OpenID
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User getUserByOpenId(long companyId, java.lang.String openId)
		throws PortalException;

	/**
	* Returns the user with the portrait ID.
	*
	* @param portraitId the user's portrait ID
	* @return the user with the portrait ID
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User getUserByPortraitId(long portraitId) throws PortalException;

	/**
	* Returns the user with the screen name.
	*
	* @param companyId the primary key of the user's company
	* @param screenName the user's screen name
	* @return the user with the screen name
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User getUserByScreenName(long companyId, java.lang.String screenName)
		throws PortalException;

	/**
	* Returns the user with the matching UUID and company.
	*
	* @param uuid the user's UUID
	* @param companyId the primary key of the company
	* @return the matching user
	* @throws PortalException if a matching user could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User getUserByUuidAndCompanyId(java.lang.String uuid, long companyId)
		throws PortalException;

	/**
	* Returns the default user for the company.
	*
	* @param companyId the primary key of the company
	* @return the default user for the company
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public User loadGetDefaultUser(long companyId) throws PortalException;

	/**
	* Updates whether the user has agreed to the terms of use.
	*
	* @param userId the primary key of the user
	* @param agreedToTermsOfUse whether the user has agreet to the terms of
	use
	* @return the user
	*/
	public User updateAgreedToTermsOfUse(long userId, boolean agreedToTermsOfUse)
		throws PortalException;

	/**
	* Updates the user's creation date.
	*
	* @param userId the primary key of the user
	* @param createDate the new creation date
	* @return the user
	*/
	public User updateCreateDate(long userId, Date createDate)
		throws PortalException;

	/**
	* Updates the user's email address.
	*
	* @param userId the primary key of the user
	* @param password the user's password
	* @param emailAddress1 the user's new email address
	* @param emailAddress2 the user's new email address confirmation
	* @return the user
	*/
	public User updateEmailAddress(long userId, java.lang.String password,
		java.lang.String emailAddress1, java.lang.String emailAddress2)
		throws PortalException;

	/**
	* Updates the user's email address or sends verification email.
	*
	* @param userId the primary key of the user
	* @param password the user's password
	* @param emailAddress1 the user's new email address
	* @param emailAddress2 the user's new email address confirmation
	* @param serviceContext the service context to be applied. Must set the
	portal URL, main path, primary key of the layout, remote address,
	remote host, and agent for the user.
	* @return the user
	*/
	public User updateEmailAddress(long userId, java.lang.String password,
		java.lang.String emailAddress1, java.lang.String emailAddress2,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Updates whether the user has verified email address.
	*
	* @param userId the primary key of the user
	* @param emailAddressVerified whether the user has verified email address
	* @return the user
	*/
	public User updateEmailAddressVerified(long userId,
		boolean emailAddressVerified) throws PortalException;

	/**
	* Updates the user's Facebook ID.
	*
	* @param userId the primary key of the user
	* @param facebookId the user's new Facebook ID
	* @return the user
	*/
	public User updateFacebookId(long userId, long facebookId)
		throws PortalException;

	/**
	* Updates the user's Google user ID.
	*
	* @param userId the primary key of the user
	* @param googleUserId the new Google user ID
	* @return the user
	*/
	public User updateGoogleUserId(long userId, java.lang.String googleUserId)
		throws PortalException;

	/**
	* Updates a user account that was automatically created when a guest user
	* participated in an action (e.g. posting a comment) and only provided his
	* name and email address.
	*
	* @param creatorUserId the primary key of the creator
	* @param companyId the primary key of the user's company
	* @param autoPassword whether a password should be automatically generated
	for the user
	* @param password1 the user's password
	* @param password2 the user's password confirmation
	* @param autoScreenName whether a screen name should be automatically
	generated for the user
	* @param screenName the user's screen name
	* @param emailAddress the user's email address
	* @param facebookId the user's facebook ID
	* @param openId the user's OpenID
	* @param locale the user's locale
	* @param firstName the user's first name
	* @param middleName the user's middle name
	* @param lastName the user's last name
	* @param prefixId the user's name prefix ID
	* @param suffixId the user's name suffix ID
	* @param male whether the user is male
	* @param birthdayMonth the user's birthday month (0-based, meaning 0 for
	January)
	* @param birthdayDay the user's birthday day
	* @param birthdayYear the user's birthday year
	* @param jobTitle the user's job title
	* @param updateUserInformation whether to update the user's information
	* @param sendEmail whether to send the user an email notification about
	their new account
	* @param serviceContext the service context to be applied (optionally
	<code>null</code>). Can set expando bridge attributes for the
	user.
	* @return the user
	*/
	public User updateIncompleteUser(long creatorUserId, long companyId,
		boolean autoPassword, java.lang.String password1,
		java.lang.String password2, boolean autoScreenName,
		java.lang.String screenName, java.lang.String emailAddress,
		long facebookId, java.lang.String openId, Locale locale,
		java.lang.String firstName, java.lang.String middleName,
		java.lang.String lastName, long prefixId, long suffixId, boolean male,
		int birthdayMonth, int birthdayDay, int birthdayYear,
		java.lang.String jobTitle, boolean updateUserInformation,
		boolean sendEmail, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Updates the user's job title.
	*
	* @param userId the primary key of the user
	* @param jobTitle the user's job title
	* @return the user
	*/
	public User updateJobTitle(long userId, java.lang.String jobTitle)
		throws PortalException;

	/**
	* Updates the user's last login with the current time and the IP address.
	*
	* @param userId the primary key of the user
	* @param loginIP the IP address the user logged in from
	* @return the user
	*/
	public User updateLastLogin(long userId, java.lang.String loginIP)
		throws PortalException;

	/**
	* Updates whether the user is locked out from logging in.
	*
	* @param user the user
	* @param lockout whether the user is locked out
	* @return the user
	*/
	public User updateLockout(User user, boolean lockout)
		throws PortalException;

	/**
	* Updates whether the user is locked out from logging in.
	*
	* @param companyId the primary key of the user's company
	* @param emailAddress the user's email address
	* @param lockout whether the user is locked out
	* @return the user
	*/
	public User updateLockoutByEmailAddress(long companyId,
		java.lang.String emailAddress, boolean lockout)
		throws PortalException;

	/**
	* Updates whether the user is locked out from logging in.
	*
	* @param userId the primary key of the user
	* @param lockout whether the user is locked out
	* @return the user
	*/
	public User updateLockoutById(long userId, boolean lockout)
		throws PortalException;

	/**
	* Updates whether the user is locked out from logging in.
	*
	* @param companyId the primary key of the user's company
	* @param screenName the user's screen name
	* @param lockout whether the user is locked out
	* @return the user
	*/
	public User updateLockoutByScreenName(long companyId,
		java.lang.String screenName, boolean lockout) throws PortalException;

	/**
	* Updates the user's modified date.
	*
	* @param userId the primary key of the user
	* @param modifiedDate the new modified date
	* @return the user
	*/
	public User updateModifiedDate(long userId, Date modifiedDate)
		throws PortalException;

	/**
	* Updates the user's OpenID.
	*
	* @param userId the primary key of the user
	* @param openId the new OpenID
	* @return the user
	*/
	public User updateOpenId(long userId, java.lang.String openId)
		throws PortalException;

	/**
	* Updates the user's password without tracking or validation of the change.
	*
	* @param userId the primary key of the user
	* @param password1 the user's new password
	* @param password2 the user's new password confirmation
	* @param passwordReset whether the user should be asked to reset their
	password the next time they log in
	* @return the user
	*/
	public User updatePassword(long userId, java.lang.String password1,
		java.lang.String password2, boolean passwordReset)
		throws PortalException;

	/**
	* Updates the user's password, optionally with tracking and validation of
	* the change.
	*
	* @param userId the primary key of the user
	* @param password1 the user's new password
	* @param password2 the user's new password confirmation
	* @param passwordReset whether the user should be asked to reset their
	password the next time they login
	* @param silentUpdate whether the password should be updated without being
	tracked, or validated. Primarily used for password imports.
	* @return the user
	*/
	public User updatePassword(long userId, java.lang.String password1,
		java.lang.String password2, boolean passwordReset, boolean silentUpdate)
		throws PortalException;

	/**
	* Updates the user's password with manually input information. This method
	* should only be used when performing maintenance.
	*
	* @param userId the primary key of the user
	* @param password the user's new password
	* @param passwordEncrypted the user's new encrypted password
	* @param passwordReset whether the user should be asked to reset their
	password the next time they login
	* @param passwordModifiedDate the new password modified date
	* @return the user
	*/
	public User updatePasswordManually(long userId, java.lang.String password,
		boolean passwordEncrypted, boolean passwordReset,
		Date passwordModifiedDate) throws PortalException;

	/**
	* Updates whether the user should be asked to reset their password the next
	* time they login.
	*
	* @param userId the primary key of the user
	* @param passwordReset whether the user should be asked to reset their
	password the next time they login
	* @return the user
	*/
	public User updatePasswordReset(long userId, boolean passwordReset)
		throws PortalException;

	/**
	* Updates the user's portrait image.
	*
	* @param userId the primary key of the user
	* @param bytes the new portrait image data
	* @return the user
	*/
	public User updatePortrait(long userId, byte[] bytes)
		throws PortalException;

	/**
	* Updates the user's password reset question and answer.
	*
	* @param userId the primary key of the user
	* @param question the user's new password reset question
	* @param answer the user's new password reset answer
	* @return the user
	*/
	public User updateReminderQuery(long userId, java.lang.String question,
		java.lang.String answer) throws PortalException;

	/**
	* Updates the user's screen name.
	*
	* @param userId the primary key of the user
	* @param screenName the user's new screen name
	* @return the user
	*/
	public User updateScreenName(long userId, java.lang.String screenName)
		throws PortalException;

	/**
	* Updates the user's workflow status.
	*
	* @param userId the primary key of the user
	* @param status the user's new workflow status
	* @return the user
	* @deprecated As of 7.0.0, replaced by {@link #updateStatus(long, int,
	ServiceContext)}
	*/
	@java.lang.Deprecated
	public User updateStatus(long userId, int status) throws PortalException;

	/**
	* Updates the user's workflow status.
	*
	* @param userId the primary key of the user
	* @param status the user's new workflow status
	* @param serviceContext the service context to be applied. You can specify
	an unencrypted custom password (used by an LDAP listener) for the
	user via attribute <code>passwordUnencrypted</code>.
	* @return the user
	*/
	public User updateStatus(long userId, int status,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Updates the user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param user the user
	* @return the user that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public User updateUser(User user);

	/**
	* Updates the user.
	*
	* @param userId the primary key of the user
	* @param oldPassword the user's old password
	* @param newPassword1 the user's new password (optionally
	<code>null</code>)
	* @param newPassword2 the user's new password confirmation (optionally
	<code>null</code>)
	* @param passwordReset whether the user should be asked to reset their
	password the next time they login
	* @param reminderQueryQuestion the user's new password reset question
	* @param reminderQueryAnswer the user's new password reset answer
	* @param screenName the user's new screen name
	* @param emailAddress the user's new email address
	* @param facebookId the user's new Facebook ID
	* @param openId the user's new OpenID
	* @param portrait whether to update the user's portrait image
	* @param portraitBytes the new portrait image data
	* @param languageId the user's new language ID
	* @param timeZoneId the user's new time zone ID
	* @param greeting the user's new greeting
	* @param comments the user's new comments
	* @param firstName the user's new first name
	* @param middleName the user's new middle name
	* @param lastName the user's new last name
	* @param prefixId the user's new name prefix ID
	* @param suffixId the user's new name suffix ID
	* @param male whether user is male
	* @param birthdayMonth the user's new birthday month (0-based, meaning 0
	for January)
	* @param birthdayDay the user's new birthday day
	* @param birthdayYear the user's birthday year
	* @param smsSn the user's new SMS screen name
	* @param facebookSn the user's new Facebook screen name
	* @param jabberSn the user's new Jabber screen name
	* @param skypeSn the user's new Skype screen name
	* @param twitterSn the user's new Twitter screen name
	* @param jobTitle the user's new job title
	* @param groupIds the primary keys of the user's groups
	* @param organizationIds the primary keys of the user's organizations
	* @param roleIds the primary keys of the user's roles
	* @param userGroupRoles the user user's group roles
	* @param userGroupIds the primary keys of the user's user groups
	* @param serviceContext the service context to be applied (optionally
	<code>null</code>). Can set the UUID (with the <code>uuid</code>
	attribute), asset category IDs, asset tag names, and expando
	bridge attributes for the user.
	* @return the user
	*/
	public User updateUser(long userId, java.lang.String oldPassword,
		java.lang.String newPassword1, java.lang.String newPassword2,
		boolean passwordReset, java.lang.String reminderQueryQuestion,
		java.lang.String reminderQueryAnswer, java.lang.String screenName,
		java.lang.String emailAddress, long facebookId,
		java.lang.String openId, boolean portrait, byte[] portraitBytes,
		java.lang.String languageId, java.lang.String timeZoneId,
		java.lang.String greeting, java.lang.String comments,
		java.lang.String firstName, java.lang.String middleName,
		java.lang.String lastName, long prefixId, long suffixId, boolean male,
		int birthdayMonth, int birthdayDay, int birthdayYear,
		java.lang.String smsSn, java.lang.String facebookSn,
		java.lang.String jabberSn, java.lang.String skypeSn,
		java.lang.String twitterSn, java.lang.String jobTitle, long[] groupIds,
		long[] organizationIds, long[] roleIds,
		List<UserGroupRole> userGroupRoles, long[] userGroupIds,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Updates the user.
	*
	* @param userId the primary key of the user
	* @param oldPassword the user's old password
	* @param newPassword1 the user's new password (optionally
	<code>null</code>)
	* @param newPassword2 the user's new password confirmation (optionally
	<code>null</code>)
	* @param passwordReset whether the user should be asked to reset their
	password the next time they login
	* @param reminderQueryQuestion the user's new password reset question
	* @param reminderQueryAnswer the user's new password reset answer
	* @param screenName the user's new screen name
	* @param emailAddress the user's new email address
	* @param facebookId the user's new Facebook ID
	* @param openId the user's new OpenID
	* @param languageId the user's new language ID
	* @param timeZoneId the user's new time zone ID
	* @param greeting the user's new greeting
	* @param comments the user's new comments
	* @param firstName the user's new first name
	* @param middleName the user's new middle name
	* @param lastName the user's new last name
	* @param prefixId the user's new name prefix ID
	* @param suffixId the user's new name suffix ID
	* @param male whether user is male
	* @param birthdayMonth the user's new birthday month (0-based, meaning
	0 for January)
	* @param birthdayDay the user's new birthday day
	* @param birthdayYear the user's birthday year
	* @param smsSn the user's new SMS screen name
	* @param facebookSn the user's new Facebook screen name
	* @param jabberSn the user's new Jabber screen name
	* @param skypeSn the user's new Skype screen name
	* @param twitterSn the user's new Twitter screen name
	* @param jobTitle the user's new job title
	* @param groupIds the primary keys of the user's groups
	* @param organizationIds the primary keys of the user's organizations
	* @param roleIds the primary keys of the user's roles
	* @param userGroupRoles the user user's group roles
	* @param userGroupIds the primary keys of the user's user groups
	* @param serviceContext the service context to be applied (optionally
	<code>null</code>). Can set the UUID (with the
	<code>uuid</code> attribute), asset category IDs, asset tag
	names, and expando bridge attributes for the user.
	* @return the user
	* @deprecated As of 7.0.0, replaced by {@link #updateUser(long, String,
	String, String, boolean, String, String, String, String,
	long, String, boolean, byte[], String, String, String,
	String, String, String, String, long, long, boolean, int,
	int, int, String, String, String, String, String, String,
	long[], long[], long[], List, long[], ServiceContext)}
	*/
	@java.lang.Deprecated
	public User updateUser(long userId, java.lang.String oldPassword,
		java.lang.String newPassword1, java.lang.String newPassword2,
		boolean passwordReset, java.lang.String reminderQueryQuestion,
		java.lang.String reminderQueryAnswer, java.lang.String screenName,
		java.lang.String emailAddress, long facebookId,
		java.lang.String openId, java.lang.String languageId,
		java.lang.String timeZoneId, java.lang.String greeting,
		java.lang.String comments, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName, long prefixId,
		long suffixId, boolean male, int birthdayMonth, int birthdayDay,
		int birthdayYear, java.lang.String smsSn, java.lang.String facebookSn,
		java.lang.String jabberSn, java.lang.String skypeSn,
		java.lang.String twitterSn, java.lang.String jobTitle, long[] groupIds,
		long[] organizationIds, long[] roleIds,
		List<UserGroupRole> userGroupRoles, long[] userGroupIds,
		ServiceContext serviceContext) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BaseModelSearchResult<User> searchUsers(long companyId,
		java.lang.String firstName, java.lang.String middleName,
		java.lang.String lastName, java.lang.String screenName,
		java.lang.String emailAddress, int status,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end, Sort sort)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BaseModelSearchResult<User> searchUsers(long companyId,
		java.lang.String firstName, java.lang.String middleName,
		java.lang.String lastName, java.lang.String screenName,
		java.lang.String emailAddress, int status,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end, Sort[] sorts)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BaseModelSearchResult<User> searchUsers(long companyId,
		java.lang.String keywords, int status,
		LinkedHashMap<java.lang.String, java.lang.Object> params, int start,
		int end, Sort sort) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BaseModelSearchResult<User> searchUsers(long companyId,
		java.lang.String keywords, int status,
		LinkedHashMap<java.lang.String, java.lang.Object> params, int start,
		int end, Sort[] sorts) throws PortalException;

	/**
	* Returns an ordered range of all the users with the status, and whose
	* first name, middle name, last name, screen name, and email address match
	* the keywords specified for them, using the indexer. It is preferable to
	* use this method instead of the non-indexed version whenever possible for
	* performance reasons.
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
	* @param companyId the primary key of the user's company
	* @param firstName the first name keywords (space separated)
	* @param middleName the middle name keywords
	* @param lastName the last name keywords
	* @param screenName the screen name keywords
	* @param emailAddress the email address keywords
	* @param status the workflow status
	* @param params the indexer parameters (optionally <code>null</code>). For
	more information see {@link
	com.liferay.portlet.usersadmin.util.UserIndexer}.
	* @param andSearch whether every field must match its keywords, or just
	one field. For example, &quot;users with the first name 'bob' and
	last name 'smith'&quot; vs &quot;users with the first name 'bob'
	or the last name 'smith'&quot;.
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param sort the field and direction to sort by (optionally
	<code>null</code>)
	* @return the matching users
	* @see com.liferay.portlet.usersadmin.util.UserIndexer
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(long companyId, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String screenName, java.lang.String emailAddress, int status,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end, Sort sort);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(long companyId, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String screenName, java.lang.String emailAddress, int status,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end, Sort[] sorts);

	/**
	* Returns an ordered range of all the users who match the keywords and
	* status, using the indexer. It is preferable to use this method instead of
	* the non-indexed version whenever possible for performance reasons.
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
	* @param companyId the primary key of the user's company
	* @param keywords the keywords (space separated), which may occur in the
	user's first name, middle name, last name, screen name, or email
	address
	* @param status the workflow status
	* @param params the indexer parameters (optionally <code>null</code>). For
	more information see {@link
	com.liferay.portlet.usersadmin.util.UserIndexer}.
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param sort the field and direction to sort by (optionally
	<code>null</code>)
	* @return the matching users
	* @see com.liferay.portlet.usersadmin.util.UserIndexer
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(long companyId, java.lang.String keywords, int status,
		LinkedHashMap<java.lang.String, java.lang.Object> params, int start,
		int end, Sort sort);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(long companyId, java.lang.String keywords, int status,
		LinkedHashMap<java.lang.String, java.lang.Object> params, int start,
		int end, Sort[] sorts);

	/**
	* Decrypts the user's primary key and password from their encrypted forms.
	* Used for decrypting a user's credentials from the values stored in an
	* automatic login cookie.
	*
	* @param companyId the primary key of the user's company
	* @param name the encrypted primary key of the user
	* @param password the encrypted password of the user
	* @return the user's primary key and password
	*/
	public KeyValuePair decryptUserId(long companyId, java.lang.String name,
		java.lang.String password) throws PortalException;

	/**
	* Attempts to authenticate the user by their email address and password,
	* while using the AuthPipeline.
	*
	* @param companyId the primary key of the user's company
	* @param emailAddress the user's email address
	* @param password the user's password
	* @param headerMap the header map from the authentication request
	* @param parameterMap the parameter map from the authentication request
	* @param resultsMap the map of authentication results (may be nil). After
	a successful authentication the user's primary key will be placed
	under the key <code>userId</code>.
	* @return the authentication status. This can be {@link
	Authenticator#FAILURE} indicating that the user's credentials are
	invalid, {@link Authenticator#SUCCESS} indicating a successful
	login, or {@link Authenticator#DNE} indicating that a user with
	that login does not exist.
	* @see AuthPipeline
	*/
	public int authenticateByEmailAddress(long companyId,
		java.lang.String emailAddress, java.lang.String password,
		Map<java.lang.String, java.lang.String[]> headerMap,
		Map<java.lang.String, java.lang.String[]> parameterMap,
		Map<java.lang.String, java.lang.Object> resultsMap)
		throws PortalException;

	/**
	* Attempts to authenticate the user by their screen name and password,
	* while using the AuthPipeline.
	*
	* @param companyId the primary key of the user's company
	* @param screenName the user's screen name
	* @param password the user's password
	* @param headerMap the header map from the authentication request
	* @param parameterMap the parameter map from the authentication request
	* @param resultsMap the map of authentication results (may be nil). After
	a successful authentication the user's primary key will be placed
	under the key <code>userId</code>.
	* @return the authentication status. This can be {@link
	Authenticator#FAILURE} indicating that the user's credentials are
	invalid, {@link Authenticator#SUCCESS} indicating a successful
	login, or {@link Authenticator#DNE} indicating that a user with
	that login does not exist.
	* @see AuthPipeline
	*/
	public int authenticateByScreenName(long companyId,
		java.lang.String screenName, java.lang.String password,
		Map<java.lang.String, java.lang.String[]> headerMap,
		Map<java.lang.String, java.lang.String[]> parameterMap,
		Map<java.lang.String, java.lang.Object> resultsMap)
		throws PortalException;

	/**
	* Attempts to authenticate the user by their primary key and password,
	* while using the AuthPipeline.
	*
	* @param companyId the primary key of the user's company
	* @param userId the user's primary key
	* @param password the user's password
	* @param headerMap the header map from the authentication request
	* @param parameterMap the parameter map from the authentication request
	* @param resultsMap the map of authentication results (may be nil). After
	a successful authentication the user's primary key will be placed
	under the key <code>userId</code>.
	* @return the authentication status. This can be {@link
	Authenticator#FAILURE} indicating that the user's credentials are
	invalid, {@link Authenticator#SUCCESS} indicating a successful
	login, or {@link Authenticator#DNE} indicating that a user with
	that login does not exist.
	* @see AuthPipeline
	*/
	public int authenticateByUserId(long companyId, long userId,
		java.lang.String password,
		Map<java.lang.String, java.lang.String[]> headerMap,
		Map<java.lang.String, java.lang.String[]> parameterMap,
		Map<java.lang.String, java.lang.Object> resultsMap)
		throws PortalException;

	/**
	* Returns the number of users belonging to the company.
	*
	* @param companyId the primary key of the company
	* @return the number of users belonging to the company
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCompanyUsersCount(long companyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getGroupUsersCount(long groupId);

	/**
	* Returns the number of users with the status belonging to the group.
	*
	* @param groupId the primary key of the group
	* @param status the workflow status
	* @return the number of users with the status belonging to the group
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getGroupUsersCount(long groupId, int status)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOrganizationUsersCount(long organizationId);

	/**
	* Returns the number of users with the status belonging to the
	* organization.
	*
	* @param organizationId the primary key of the organization
	* @param status the workflow status
	* @return the number of users with the status belonging to the organization
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOrganizationUsersCount(long organizationId, int status)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getRoleUsersCount(long roleId);

	/**
	* Returns the number of users with the status belonging to the role.
	*
	* @param roleId the primary key of the role
	* @param status the workflow status
	* @return the number of users with the status belonging to the role
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getRoleUsersCount(long roleId, int status)
		throws PortalException;

	/**
	* Returns the number of users with a social relation with the user.
	*
	* @param userId the primary key of the user
	* @return the number of users with a social relation with the user
	* @deprecated As of 7.0.0, replaced by {@link #getSocialUsersCount(long,
	int, String)}
	*/
	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSocialUsersCount(long userId) throws PortalException;

	/**
	* Returns the number of users with a social relation of the type with the
	* user.
	*
	* @param userId the primary key of the user
	* @param socialRelationType the type of social relation. The possible
	types can be found in {@link SocialRelationConstants}.
	* @return the number of users with a social relation of the type with
	the user
	* @deprecated As of 7.0.0, replaced by {@link #getSocialUsersCount(long,
	int, String)}
	*/
	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSocialUsersCount(long userId, int socialRelationType)
		throws PortalException;

	/**
	* Returns the number of users with a social relation with the user.
	*
	* @param userId the primary key of the user
	* @param socialRelationType the type of social relation. The possible
	types can be found in {@link SocialRelationConstants}.
	* @return the number of users with a social relation with the user
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSocialUsersCount(long userId, int socialRelationType,
		java.lang.String socialRelationTypeComparator)
		throws PortalException;

	/**
	* Returns the number of users with a mutual social relation with both of
	* the given users.
	*
	* @param userId1 the primary key of the first user
	* @param userId2 the primary key of the second user
	* @return the number of users with a mutual social relation with the user
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSocialUsersCount(long userId1, long userId2)
		throws PortalException;

	/**
	* Returns the number of users with a mutual social relation of the type
	* with both of the given users.
	*
	* @param userId1 the primary key of the first user
	* @param userId2 the primary key of the second user
	* @param socialRelationType the type of social relation. The possible
	types can be found in {@link SocialRelationConstants}.
	* @return the number of users with a mutual social relation of the type
	with the user
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSocialUsersCount(long userId1, long userId2,
		int socialRelationType) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTeamUsersCount(long teamId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserGroupUsersCount(long userGroupId);

	/**
	* Returns the number of users with the status belonging to the user group.
	*
	* @param userGroupId the primary key of the user group
	* @param status the workflow status
	* @return the number of users with the status belonging to the user group
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserGroupUsersCount(long userGroupId, int status)
		throws PortalException;

	/**
	* Returns the number of users.
	*
	* @return the number of users
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUsersCount();

	/**
	* Returns the number of users with the status, and whose first name, middle
	* name, last name, screen name, and email address match the keywords
	* specified for them.
	*
	* @param companyId the primary key of the user's company
	* @param firstName the first name keywords (space separated)
	* @param middleName the middle name keywords
	* @param lastName the last name keywords
	* @param screenName the screen name keywords
	* @param emailAddress the email address keywords
	* @param status the workflow status
	* @param params the finder parameters (optionally <code>null</code>). For
	more information see {@link
	com.liferay.portal.kernel.service.persistence.UserFinder}.
	* @param andSearch whether every field must match its keywords, or just
	one field. For example, &quot;users with the first name 'bob' and
	last name 'smith'&quot; vs &quot;users with the first name 'bob'
	or the last name 'smith'&quot;.
	* @return the number of matching users
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long companyId, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String screenName, java.lang.String emailAddress, int status,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch);

	/**
	* Returns the number of users who match the keywords and status.
	*
	* @param companyId the primary key of the user's company
	* @param keywords the keywords (space separated), which may occur in the
	user's first name, middle name, last name, screen name, or email
	address
	* @param status the workflow status
	* @param params the finder parameters (optionally <code>null</code>). For
	more information see {@link
	com.liferay.portal.kernel.service.persistence.UserFinder}.
	* @return the number matching users
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long companyId, java.lang.String keywords,
		int status, LinkedHashMap<java.lang.String, java.lang.Object> params);

	/**
	* Encrypts the primary key of the user. Used when encrypting the user's
	* credentials for storage in an automatic login cookie.
	*
	* @param name the primary key of the user
	* @return the user's encrypted primary key
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String encryptUserId(java.lang.String name)
		throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the users belonging to the company.
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
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @return the range of users belonging to the company
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getCompanyUsers(long companyId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getGroupUsers(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getGroupUsers(long groupId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getGroupUsers(long groupId, int start, int end,
		OrderByComparator<User> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getInheritedRoleUsers(long roleId, int start, int end,
		OrderByComparator<User> obc) throws PortalException;

	/**
	* Returns all the users who have not had any announcements of the type
	* delivered, excluding the default user.
	*
	* @param type the type of announcement
	* @return the users who have not had any annoucements of the type delivered
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getNoAnnouncementsDeliveries(java.lang.String type);

	/**
	* Returns all the users who do not have any contacts.
	*
	* @return the users who do not have any contacts
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getNoContacts();

	/**
	* Returns all the users who do not belong to any groups, excluding the
	* default user.
	*
	* @return the users who do not belong to any groups
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getNoGroups();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getOrganizationUsers(long organizationId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getOrganizationUsers(long organizationId, int start,
		int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getOrganizationUsers(long organizationId, int start,
		int end, OrderByComparator<User> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getRoleUsers(long roleId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getRoleUsers(long roleId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getRoleUsers(long roleId, int start, int end,
		OrderByComparator<User> orderByComparator);

	/**
	* Returns an ordered range of all the users with a social relation of the
	* type with the user.
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
	* @param userId the primary key of the user
	* @param socialRelationType the type of social relation. The possible
	types can be found in {@link SocialRelationConstants}.
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param obc the comparator to order the users by (optionally
	<code>null</code>)
	* @return the ordered range of users with a social relation of the type
	with the user
	* @deprecated As of 7.0.0, replaced by {@link #getSocialUsers(long, int,
	String, int, int, OrderByComparator)}
	*/
	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getSocialUsers(long userId, int socialRelationType,
		int start, int end, OrderByComparator<User> obc)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getSocialUsers(long userId, int socialRelationType,
		java.lang.String socialRelationTypeComparator, int start, int end,
		OrderByComparator<User> obc) throws PortalException;

	/**
	* Returns an ordered range of all the users with a social relation with the
	* user.
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
	* @param userId the primary key of the user
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param obc the comparator to order the users by (optionally
	<code>null</code>)
	* @return the ordered range of users with a social relation with the
	user
	* @deprecated As of 7.0.0, replaced by {@link #getSocialUsers(long, int,
	String, int, int, OrderByComparator)}
	*/
	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getSocialUsers(long userId, int start, int end,
		OrderByComparator<User> obc) throws PortalException;

	/**
	* Returns an ordered range of all the users with a mutual social relation
	* of the type with both of the given users.
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
	* @param userId1 the primary key of the first user
	* @param userId2 the primary key of the second user
	* @param socialRelationType the type of social relation. The possible
	types can be found in {@link SocialRelationConstants}.
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param obc the comparator to order the users by (optionally
	<code>null</code>)
	* @return the ordered range of users with a mutual social relation of the
	type with the user
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getSocialUsers(long userId1, long userId2,
		int socialRelationType, int start, int end, OrderByComparator<User> obc)
		throws PortalException;

	/**
	* Returns an ordered range of all the users with a mutual social relation
	* with both of the given users.
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
	* @param userId1 the primary key of the first user
	* @param userId2 the primary key of the second user
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param obc the comparator to order the users by (optionally
	<code>null</code>)
	* @return the ordered range of users with a mutual social relation with the
	user
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getSocialUsers(long userId1, long userId2, int start,
		int end, OrderByComparator<User> obc) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getTeamUsers(long teamId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getTeamUsers(long teamId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getTeamUsers(long teamId, int start, int end,
		OrderByComparator<User> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getUserGroupUsers(long userGroupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getUserGroupUsers(long userGroupId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getUserGroupUsers(long userGroupId, int start, int end,
		OrderByComparator<User> orderByComparator);

	/**
	* Returns a range of all the users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @return the range of users
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> getUsers(int start, int end);

	/**
	* Returns an ordered range of all the users with the status, and whose
	* first name, middle name, last name, screen name, and email address match
	* the keywords specified for them, without using the indexer. It is
	* preferable to use the indexed version {@link #search(long, String,
	* String, String, String, String, int, LinkedHashMap, boolean, int, int,
	* Sort)} instead of this method wherever possible for performance reasons.
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
	* @param companyId the primary key of the user's company
	* @param firstName the first name keywords (space separated)
	* @param middleName the middle name keywords
	* @param lastName the last name keywords
	* @param screenName the screen name keywords
	* @param emailAddress the email address keywords
	* @param status the workflow status
	* @param params the finder parameters (optionally <code>null</code>). For
	more information see {@link
	com.liferay.portal.kernel.service.persistence.UserFinder}.
	* @param andSearch whether every field must match its keywords, or just
	one field. For example, &quot;users with the first name 'bob' and
	last name 'smith'&quot; vs &quot;users with the first name 'bob'
	or the last name 'smith'&quot;.
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param obc the comparator to order the users by (optionally
	<code>null</code>)
	* @return the matching users
	* @see com.liferay.portal.kernel.service.persistence.UserFinder
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> search(long companyId, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String screenName, java.lang.String emailAddress, int status,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end, OrderByComparator<User> obc);

	/**
	* Returns an ordered range of all the users who match the keywords and
	* status, without using the indexer. It is preferable to use the indexed
	* version {@link #search(long, String, int, LinkedHashMap, int, int, Sort)}
	* instead of this method wherever possible for performance reasons.
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
	* @param companyId the primary key of the user's company
	* @param keywords the keywords (space separated), which may occur in the
	user's first name, middle name, last name, screen name, or email
	address
	* @param status the workflow status
	* @param params the finder parameters (optionally <code>null</code>). For
	more information see {@link
	com.liferay.portal.kernel.service.persistence.UserFinder}.
	* @param start the lower bound of the range of users
	* @param end the upper bound of the range of users (not inclusive)
	* @param obc the comparator to order the users by (optionally
	<code>null</code>)
	* @return the matching users
	* @see com.liferay.portal.kernel.service.persistence.UserFinder
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> search(long companyId, java.lang.String keywords,
		int status, LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, OrderByComparator<User> obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> searchSocial(long companyId, long[] groupIds,
		java.lang.String keywords, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> searchSocial(long userId, int[] socialRelationTypes,
		java.lang.String keywords, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> searchSocial(long[] groupIds, long userId,
		int[] socialRelationTypes, java.lang.String keywords, int start, int end)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Map<java.lang.Long, java.lang.Integer> searchCounts(long companyId,
		int status, long[] groupIds);

	/**
	* Attempts to authenticate the user using HTTP basic access authentication,
	* without using the AuthPipeline. Primarily used for authenticating users
	* of <code>tunnel-web</code>.
	*
	* <p>
	* Authentication type specifies what <code>login</code> contains.The valid
	* values are:
	* </p>
	*
	* <ul>
	* <li>
	* <code>CompanyConstants.AUTH_TYPE_EA</code> - <code>login</code> is the
	* user's email address
	* </li>
	* <li>
	* <code>CompanyConstants.AUTH_TYPE_SN</code> - <code>login</code> is the
	* user's screen name
	* </li>
	* <li>
	* <code>CompanyConstants.AUTH_TYPE_ID</code> - <code>login</code> is the
	* user's primary key
	* </li>
	* </ul>
	*
	* @param companyId the primary key of the user's company
	* @param authType the type of authentication to perform
	* @param login either the user's email address, screen name, or primary
	key depending on the value of <code>authType</code>
	* @param password the user's password
	* @return the user's primary key if authentication is successful;
	<code>0</code> otherwise
	*/
	@Transactional(propagation = Propagation.SUPPORTS)
	public long authenticateForBasic(long companyId, java.lang.String authType,
		java.lang.String login, java.lang.String password)
		throws PortalException;

	/**
	* Attempts to authenticate the user using HTTP digest access
	* authentication, without using the AuthPipeline. Primarily used for
	* authenticating users of <code>tunnel-web</code>.
	*
	* @param companyId the primary key of the user's company
	* @param username either the user's email address, screen name, or primary
	key
	* @param realm unused
	* @param nonce the number used once
	* @param method the request method
	* @param uri the request URI
	* @param response the authentication response hash
	* @return the user's primary key if authentication is successful;
	<code>0</code> otherwise
	*/
	@Transactional(propagation = Propagation.SUPPORTS)
	public long authenticateForDigest(long companyId,
		java.lang.String username, java.lang.String realm,
		java.lang.String nonce, java.lang.String method, java.lang.String uri,
		java.lang.String response) throws PortalException;

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
	* Returns the primary key of the default user for the company.
	*
	* @param companyId the primary key of the company
	* @return the primary key of the default user for the company
	*/
	@Skip
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getDefaultUserId(long companyId) throws PortalException;

	/**
	* Returns the primary key of the user with the email address.
	*
	* @param companyId the primary key of the user's company
	* @param emailAddress the user's email address
	* @return the primary key of the user with the email address
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getUserIdByEmailAddress(long companyId,
		java.lang.String emailAddress) throws PortalException;

	/**
	* Returns the primary key of the user with the screen name.
	*
	* @param companyId the primary key of the user's company
	* @param screenName the user's screen name
	* @return the primary key of the user with the screen name
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getUserIdByScreenName(long companyId,
		java.lang.String screenName) throws PortalException;

	/**
	* Returns the groupIds of the groups associated with the user.
	*
	* @param userId the userId of the user
	* @return long[] the groupIds of groups associated with the user
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getGroupPrimaryKeys(long userId);

	/**
	* Returns the primary keys of all the users belonging to the group.
	*
	* @param groupId the primary key of the group
	* @return the primary keys of the users belonging to the group
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getGroupUserIds(long groupId);

	/**
	* Returns the organizationIds of the organizations associated with the user.
	*
	* @param userId the userId of the user
	* @return long[] the organizationIds of organizations associated with the user
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getOrganizationPrimaryKeys(long userId);

	/**
	* Returns the primary keys of all the users belonging to the organization.
	*
	* @param organizationId the primary key of the organization
	* @return the primary keys of the users belonging to the organization
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getOrganizationUserIds(long organizationId);

	/**
	* Returns the roleIds of the roles associated with the user.
	*
	* @param userId the userId of the user
	* @return long[] the roleIds of roles associated with the user
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getRolePrimaryKeys(long userId);

	/**
	* Returns the primary keys of all the users belonging to the role.
	*
	* @param roleId the primary key of the role
	* @return the primary keys of the users belonging to the role
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getRoleUserIds(long roleId);

	/**
	* Returns the teamIds of the teams associated with the user.
	*
	* @param userId the userId of the user
	* @return long[] the teamIds of teams associated with the user
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getTeamPrimaryKeys(long userId);

	/**
	* Returns the userGroupIds of the user groups associated with the user.
	*
	* @param userId the userId of the user
	* @return long[] the userGroupIds of user groups associated with the user
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getUserGroupPrimaryKeys(long userId);

	/**
	* Adds the user to the default groups, unless the user is already in these
	* groups. The default groups can be specified in
	* <code>portal.properties</code> with the key
	* <code>admin.default.group.names</code>.
	*
	* @param userId the primary key of the user
	*/
	public void addDefaultGroups(long userId) throws PortalException;

	/**
	* Adds the user to the default regular roles, unless the user already has
	* these regular roles. The default regular roles can be specified in
	* <code>portal.properties</code> with the key
	* <code>admin.default.role.names</code>.
	*
	* @param userId the primary key of the user
	*/
	public void addDefaultRoles(long userId) throws PortalException;

	/**
	* Adds the user to the default user groups, unless the user is already in
	* these user groups. The default user groups can be specified in
	* <code>portal.properties</code> with the property
	* <code>admin.default.user.group.names</code>.
	*
	* @param userId the primary key of the user
	*/
	public void addDefaultUserGroups(long userId) throws PortalException;

	public void addGroupUser(long groupId, User user);

	public void addGroupUser(long groupId, long userId);

	/**
	* @throws PortalException
	*/
	public void addGroupUsers(long groupId, List<User> users)
		throws PortalException;

	/**
	* @throws PortalException
	*/
	public void addGroupUsers(long groupId, long[] userIds)
		throws PortalException;

	public void addOrganizationUser(long organizationId, User user);

	public void addOrganizationUser(long organizationId, long userId);

	/**
	* @throws PortalException
	*/
	public void addOrganizationUsers(long organizationId, List<User> users)
		throws PortalException;

	/**
	* @throws PortalException
	*/
	public void addOrganizationUsers(long organizationId, long[] userIds)
		throws PortalException;

	/**
	* Assigns the password policy to the users, removing any other currently
	* assigned password policies.
	*
	* @param passwordPolicyId the primary key of the password policy
	* @param userIds the primary keys of the users
	*/
	public void addPasswordPolicyUsers(long passwordPolicyId, long[] userIds);

	public void addRoleUser(long roleId, User user);

	public void addRoleUser(long roleId, long userId);

	/**
	* @throws PortalException
	*/
	public void addRoleUsers(long roleId, List<User> users)
		throws PortalException;

	/**
	* @throws PortalException
	*/
	public void addRoleUsers(long roleId, long[] userIds)
		throws PortalException;

	public void addTeamUser(long teamId, User user);

	public void addTeamUser(long teamId, long userId);

	/**
	* @throws PortalException
	*/
	public void addTeamUsers(long teamId, List<User> users)
		throws PortalException;

	/**
	* @throws PortalException
	*/
	public void addTeamUsers(long teamId, long[] userIds)
		throws PortalException;

	public void addUserGroupUser(long userGroupId, User user);

	public void addUserGroupUser(long userGroupId, long userId);

	/**
	* @throws PortalException
	*/
	public void addUserGroupUsers(long userGroupId, List<User> users)
		throws PortalException;

	/**
	* @throws PortalException
	*/
	public void addUserGroupUsers(long userGroupId, long[] userIds)
		throws PortalException;

	/**
	* Checks if the user is currently locked out based on the password policy,
	* and performs maintenance on the user's lockout and failed login data.
	*
	* @param user the user
	*/
	public void checkLockout(User user) throws PortalException;

	/**
	* Adds a failed login attempt to the user and updates the user's last
	* failed login date.
	*
	* @param user the user
	*/
	public void checkLoginFailure(User user);

	/**
	* Adds a failed login attempt to the user with the email address and
	* updates the user's last failed login date.
	*
	* @param companyId the primary key of the user's company
	* @param emailAddress the user's email address
	*/
	public void checkLoginFailureByEmailAddress(long companyId,
		java.lang.String emailAddress) throws PortalException;

	/**
	* Adds a failed login attempt to the user and updates the user's last
	* failed login date.
	*
	* @param userId the primary key of the user
	*/
	public void checkLoginFailureById(long userId) throws PortalException;

	/**
	* Adds a failed login attempt to the user with the screen name and updates
	* the user's last failed login date.
	*
	* @param companyId the primary key of the user's company
	* @param screenName the user's screen name
	*/
	public void checkLoginFailureByScreenName(long companyId,
		java.lang.String screenName) throws PortalException;

	/**
	* Checks if the user's password is expired based on the password policy,
	* and performs maintenance on the user's grace login and password reset
	* data.
	*
	* @param user the user
	*/
	public void checkPasswordExpired(User user) throws PortalException;

	public void clearGroupUsers(long groupId);

	public void clearOrganizationUsers(long organizationId);

	public void clearRoleUsers(long roleId);

	public void clearTeamUsers(long teamId);

	public void clearUserGroupUsers(long userGroupId);

	/**
	* Completes the user's registration by generating a password and sending
	* the confirmation email.
	*
	* @param user the user
	* @param serviceContext the service context to be applied. You can specify
	an unencrypted custom password for the user via attribute
	<code>passwordUnencrypted</code>. You automatically generate a
	password for the user by setting attribute
	<code>autoPassword</code> to <code>true</code>. You can send a
	confirmation email to the user by setting attribute
	<code>sendEmail</code> to <code>true</code>.
	*/
	public void completeUserRegistration(User user,
		ServiceContext serviceContext) throws PortalException;

	public void deleteGroupUser(long groupId, User user);

	public void deleteGroupUser(long groupId, long userId);

	public void deleteGroupUsers(long groupId, List<User> users);

	public void deleteGroupUsers(long groupId, long[] userIds);

	public void deleteOrganizationUser(long organizationId, User user);

	public void deleteOrganizationUser(long organizationId, long userId);

	public void deleteOrganizationUsers(long organizationId, List<User> users);

	public void deleteOrganizationUsers(long organizationId, long[] userIds);

	/**
	* Deletes the user's portrait image.
	*
	* @param userId the primary key of the user
	*/
	public void deletePortrait(long userId) throws PortalException;

	/**
	* @throws PortalException
	*/
	public void deleteRoleUser(long roleId, User user)
		throws PortalException;

	/**
	* @throws PortalException
	*/
	public void deleteRoleUser(long roleId, long userId)
		throws PortalException;

	public void deleteRoleUsers(long roleId, List<User> users);

	public void deleteRoleUsers(long roleId, long[] userIds);

	public void deleteTeamUser(long teamId, User user);

	public void deleteTeamUser(long teamId, long userId);

	public void deleteTeamUsers(long teamId, List<User> users);

	public void deleteTeamUsers(long teamId, long[] userIds);

	/**
	* @throws PortalException
	*/
	public void deleteUserGroupUser(long userGroupId, User user)
		throws PortalException;

	/**
	* @throws PortalException
	*/
	public void deleteUserGroupUser(long userGroupId, long userId)
		throws PortalException;

	public void deleteUserGroupUsers(long userGroupId, List<User> users);

	public void deleteUserGroupUsers(long userGroupId, long[] userIds);

	/**
	* Sends an email address verification to the user.
	*
	* @param user the verification email recipient
	* @param emailAddress the recipient's email address
	* @param serviceContext the service context to be applied. Must set the
	portal URL, main path, primary key of the layout, remote address,
	remote host, and agent for the user.
	*/
	public void sendEmailAddressVerification(User user,
		java.lang.String emailAddress, ServiceContext serviceContext)
		throws PortalException;

	public void setGroupUsers(long groupId, long[] userIds);

	public void setOrganizationUsers(long organizationId, long[] userIds);

	/**
	* @throws PortalException
	*/
	public void setRoleUsers(long roleId, long[] userIds)
		throws PortalException;

	public void setTeamUsers(long teamId, long[] userIds);

	/**
	* @throws PortalException
	*/
	public void setUserGroupUsers(long userGroupId, long[] userIds)
		throws PortalException;

	/**
	* Removes the users from the teams of a group.
	*
	* @param groupId the primary key of the group
	* @param userIds the primary keys of the users
	*/
	public void unsetGroupTeamsUsers(long groupId, long[] userIds)
		throws PortalException;

	/**
	* Removes the users from the group.
	*
	* @param groupId the primary key of the group
	* @param userIds the primary keys of the users
	* @param serviceContext the service context to be applied (optionally
	<code>null</code>)
	*/
	public void unsetGroupUsers(long groupId, long[] userIds,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Removes the users from the organization.
	*
	* @param organizationId the primary key of the organization
	* @param userIds the primary keys of the users
	*/
	public void unsetOrganizationUsers(long organizationId, long[] userIds)
		throws PortalException;

	/**
	* Removes the users from the password policy.
	*
	* @param passwordPolicyId the primary key of the password policy
	* @param userIds the primary keys of the users
	*/
	public void unsetPasswordPolicyUsers(long passwordPolicyId, long[] userIds);

	/**
	* Removes the users from the role.
	*
	* @param roleId the primary key of the role
	* @param users the users
	*/
	public void unsetRoleUsers(long roleId, List<User> users)
		throws PortalException;

	/**
	* Removes the users from the role.
	*
	* @param roleId the primary key of the role
	* @param userIds the primary keys of the users
	*/
	public void unsetRoleUsers(long roleId, long[] userIds)
		throws PortalException;

	/**
	* Removes the users from the team.
	*
	* @param teamId the primary key of the team
	* @param userIds the primary keys of the users
	*/
	public void unsetTeamUsers(long teamId, long[] userIds)
		throws PortalException;

	/**
	* Removes the users from the user group.
	*
	* @param userGroupId the primary key of the user group
	* @param userIds the primary keys of the users
	*/
	public void unsetUserGroupUsers(long userGroupId, long[] userIds)
		throws PortalException;

	/**
	* Updates the user's asset with the new asset categories and tag names,
	* removing and adding asset categories and tag names as necessary.
	*
	* @param userId the primary key of the user
	* @param user ID the primary key of the user
	* @param assetCategoryIds the primary key's of the new asset categories
	* @param assetTagNames the new asset tag names
	*/
	public void updateAsset(long userId, User user, long[] assetCategoryIds,
		java.lang.String[] assetTagNames) throws PortalException;

	/**
	* Sets the groups the user is in, removing and adding groups as necessary.
	*
	* @param userId the primary key of the user
	* @param newGroupIds the primary keys of the groups
	* @param serviceContext the service context to be applied (optionally
	<code>null</code>)
	*/
	public void updateGroups(long userId, long[] newGroupIds,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Sets the organizations that the user is in, removing and adding
	* organizations as necessary.
	*
	* @param userId the primary key of the user
	* @param newOrganizationIds the primary keys of the organizations
	* @param serviceContext the service context to be applied. Must set whether
	user indexing is enabled.
	*/
	public void updateOrganizations(long userId, long[] newOrganizationIds,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Verifies the email address of the ticket.
	*
	* @param ticketKey the ticket key
	*/
	public void verifyEmailAddress(java.lang.String ticketKey)
		throws PortalException;
}