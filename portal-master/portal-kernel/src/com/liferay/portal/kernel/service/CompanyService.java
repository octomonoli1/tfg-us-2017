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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.EmailAddress;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.Website;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.io.InputStream;

import java.util.List;

/**
 * Provides the remote service interface for Company. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see CompanyServiceUtil
 * @see com.liferay.portal.service.base.CompanyServiceBaseImpl
 * @see com.liferay.portal.service.impl.CompanyServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CompanyService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CompanyServiceUtil} to access the company remote service. Add custom service methods to {@link com.liferay.portal.service.impl.CompanyServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds a company.
	*
	* @param webId the company's web domain
	* @param virtualHost the company's virtual host name
	* @param mx the company's mail domain
	* @param system whether the company is the very first company (i.e., the
	* @param maxUsers the max number of company users (optionally
	<code>0</code>)
	* @param active whether the company is active
	* @return the company
	*/
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	public Company addCompany(java.lang.String webId,
		java.lang.String virtualHost, java.lang.String mx, boolean system,
		int maxUsers, boolean active) throws PortalException;

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	public Company deleteCompany(long companyId) throws PortalException;

	/**
	* Returns the company with the primary key.
	*
	* @param companyId the primary key of the company
	* @return Returns the company with the primary key
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Company getCompanyById(long companyId) throws PortalException;

	/**
	* Returns the company with the logo.
	*
	* @param logoId the ID of the company's logo
	* @return Returns the company with the logo
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Company getCompanyByLogoId(long logoId) throws PortalException;

	/**
	* Returns the company with the mail domian.
	*
	* @param mx the company's mail domain
	* @return Returns the company with the mail domain
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Company getCompanyByMx(java.lang.String mx)
		throws PortalException;

	/**
	* Returns the company with the virtual host name.
	*
	* @param virtualHost the company's virtual host name
	* @return Returns the company with the virtual host name
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Company getCompanyByVirtualHost(java.lang.String virtualHost)
		throws PortalException;

	/**
	* Returns the company with the web domain.
	*
	* @param webId the company's web domain
	* @return Returns the company with the web domain
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Company getCompanyByWebId(java.lang.String webId)
		throws PortalException;

	/**
	* Updates the company
	*
	* @param companyId the primary key of the company
	* @param virtualHost the company's virtual host name
	* @param mx the company's mail domain
	* @param maxUsers the max number of company users (optionally
	<code>0</code>)
	* @param active whether the company is active
	* @return the company with the primary key
	*/
	public Company updateCompany(long companyId, java.lang.String virtualHost,
		java.lang.String mx, int maxUsers, boolean active)
		throws PortalException;

	/**
	* Updates the company with additional account information.
	*
	* @param companyId the primary key of the company
	* @param virtualHost the company's virtual host name
	* @param mx the company's mail domain
	* @param homeURL the company's home URL (optionally <code>null</code>)
	* @param logo whether to update the company's logo
	* @param logoBytes the new logo image data
	* @param name the company's account name (optionally <code>null</code>)
	* @param legalName the company's account legal name (optionally
	<code>null</code>)
	* @param legalId the company's account legal ID (optionally
	<code>null</code>)
	* @param legalType the company's account legal type (optionally
	<code>null</code>)
	* @param sicCode the company's account SIC code (optionally
	<code>null</code>)
	* @param tickerSymbol the company's account ticker symbol (optionally
	<code>null</code>)
	* @param industry the the company's account industry (optionally
	<code>null</code>)
	* @param type the company's account type (optionally <code>null</code>)
	* @param size the company's account size (optionally <code>null</code>)
	* @return the the company with the primary key
	*/
	public Company updateCompany(long companyId, java.lang.String virtualHost,
		java.lang.String mx, java.lang.String homeURL, boolean logo,
		byte[] logoBytes, java.lang.String name, java.lang.String legalName,
		java.lang.String legalId, java.lang.String legalType,
		java.lang.String sicCode, java.lang.String tickerSymbol,
		java.lang.String industry, java.lang.String type, java.lang.String size)
		throws PortalException;

	/**
	* Updates the company with addition information.
	*
	* @param companyId the primary key of the company
	* @param virtualHost the company's virtual host name
	* @param mx the company's mail domain
	* @param homeURL the company's home URL (optionally <code>null</code>)
	* @param logo if the company has a custom logo
	* @param logoBytes the new logo image data
	* @param name the company's account name (optionally <code>null</code>)
	* @param legalName the company's account legal name (optionally
	<code>null</code>)
	* @param legalId the company's accout legal ID (optionally
	<code>null</code>)
	* @param legalType the company's account legal type (optionally
	<code>null</code>)
	* @param sicCode the company's account SIC code (optionally
	<code>null</code>)
	* @param tickerSymbol the company's account ticker symbol (optionally
	<code>null</code>)
	* @param industry the the company's account industry (optionally
	<code>null</code>)
	* @param type the company's account type (optionally <code>null</code>)
	* @param size the company's account size (optionally <code>null</code>)
	* @param languageId the ID of the company's default user's language
	* @param timeZoneId the ID of the company's default user's time zone
	* @param addresses the company's addresses
	* @param emailAddresses the company's email addresses
	* @param phones the company's phone numbers
	* @param websites the company's websites
	* @param properties the company's properties
	* @return the company with the primary key
	*/
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	public Company updateCompany(long companyId, java.lang.String virtualHost,
		java.lang.String mx, java.lang.String homeURL, boolean logo,
		byte[] logoBytes, java.lang.String name, java.lang.String legalName,
		java.lang.String legalId, java.lang.String legalType,
		java.lang.String sicCode, java.lang.String tickerSymbol,
		java.lang.String industry, java.lang.String type,
		java.lang.String size, java.lang.String languageId,
		java.lang.String timeZoneId, List<Address> addresses,
		List<EmailAddress> emailAddresses, List<Phone> phones,
		List<Website> websites, UnicodeProperties properties)
		throws PortalException;

	/**
	* Updates the company with additional account information.
	*
	* @param companyId the primary key of the company
	* @param virtualHost the company's virtual host name
	* @param mx the company's mail domain
	* @param homeURL the company's home URL (optionally <code>null</code>)
	* @param name the company's account name (optionally
	<code>null</code>)
	* @param legalName the company's account legal name (optionally
	<code>null</code>)
	* @param legalId the company's account legal ID (optionally
	<code>null</code>)
	* @param legalType the company's account legal type (optionally
	<code>null</code>)
	* @param sicCode the company's account SIC code (optionally
	<code>null</code>)
	* @param tickerSymbol the company's account ticker symbol (optionally
	<code>null</code>)
	* @param industry the the company's account industry (optionally
	<code>null</code>)
	* @param type the company's account type (optionally
	<code>null</code>)
	* @param size the company's account size (optionally
	<code>null</code>)
	* @return the the company with the primary key
	* @deprecated As of 7.0.0, replaced by {@link #updateCompany(long, String,
	String, String, boolean, byte[], String, String, String,
	String, String, String, String, String, String)}
	*/
	@java.lang.Deprecated
	public Company updateCompany(long companyId, java.lang.String virtualHost,
		java.lang.String mx, java.lang.String homeURL, java.lang.String name,
		java.lang.String legalName, java.lang.String legalId,
		java.lang.String legalType, java.lang.String sicCode,
		java.lang.String tickerSymbol, java.lang.String industry,
		java.lang.String type, java.lang.String size) throws PortalException;

	/**
	* Updates the company with addition information.
	*
	* @param companyId the primary key of the company
	* @param virtualHost the company's virtual host name
	* @param mx the company's mail domain
	* @param homeURL the company's home URL (optionally <code>null</code>)
	* @param name the company's account name (optionally
	<code>null</code>)
	* @param legalName the company's account legal name (optionally
	<code>null</code>)
	* @param legalId the company's accout legal ID (optionally
	<code>null</code>)
	* @param legalType the company's account legal type (optionally
	<code>null</code>)
	* @param sicCode the company's account SIC code (optionally
	<code>null</code>)
	* @param tickerSymbol the company's account ticker symbol (optionally
	<code>null</code>)
	* @param industry the the company's account industry (optionally
	<code>null</code>)
	* @param type the company's account type (optionally
	<code>null</code>)
	* @param size the company's account size (optionally
	<code>null</code>)
	* @param languageId the ID of the company's default user's language
	* @param timeZoneId the ID of the company's default user's time zone
	* @param addresses the company's addresses
	* @param emailAddresses the company's email addresses
	* @param phones the company's phone numbers
	* @param websites the company's websites
	* @param properties the company's properties
	* @return the company with the primary key
	* @deprecated As of 7.0.0, replaced by {@link #updateCompany(long, String,
	String, String, boolean, byte[], String, String, String,
	String, String, String, String, String, String, String,
	String, List, List, List, List, UnicodeProperties)}
	*/
	@java.lang.Deprecated
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	public Company updateCompany(long companyId, java.lang.String virtualHost,
		java.lang.String mx, java.lang.String homeURL, java.lang.String name,
		java.lang.String legalName, java.lang.String legalId,
		java.lang.String legalType, java.lang.String sicCode,
		java.lang.String tickerSymbol, java.lang.String industry,
		java.lang.String type, java.lang.String size,
		java.lang.String languageId, java.lang.String timeZoneId,
		List<Address> addresses, List<EmailAddress> emailAddresses,
		List<Phone> phones, List<Website> websites, UnicodeProperties properties)
		throws PortalException;

	/**
	* Updates the company's logo.
	*
	* @param companyId the primary key of the company
	* @param bytes the bytes of the company's logo image
	* @return the company with the primary key
	*/
	public Company updateLogo(long companyId, byte[] bytes)
		throws PortalException;

	/**
	* Updates the company's logo.
	*
	* @param companyId the primary key of the company
	* @param inputStream the input stream of the company's logo image
	* @return the company with the primary key
	*/
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	public Company updateLogo(long companyId, InputStream inputStream)
		throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Deletes the company's logo.
	*
	* @param companyId the primary key of the company
	*/
	public void deleteLogo(long companyId) throws PortalException;

	/**
	* Removes the values that match the keys of the company's preferences.
	*
	* This method is called by {@link
	* com.liferay.portlet.portalsettings.action.EditLDAPServerAction} remotely
	* through {@link CompanyService}.
	*
	* @param companyId the primary key of the company
	* @param keys the company's preferences keys to be remove
	*/
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	public void removePreferences(long companyId, java.lang.String[] keys)
		throws PortalException;

	/**
	* Update the company's display.
	*
	* @param companyId the primary key of the company
	* @param languageId the ID of the company's default user's language
	* @param timeZoneId the ID of the company's default user's time zone
	*/
	public void updateDisplay(long companyId, java.lang.String languageId,
		java.lang.String timeZoneId) throws PortalException;

	/**
	* Updates the company's preferences. The company's default properties are
	* found in portal.properties.
	*
	* @param companyId the primary key of the company
	* @param properties the company's properties. See {@link UnicodeProperties}
	*/
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	public void updatePreferences(long companyId, UnicodeProperties properties)
		throws PortalException;

	/**
	* Updates the company's security properties.
	*
	* @param companyId the primary key of the company
	* @param authType the company's method of authenticating users
	* @param autoLogin whether to allow users to select the "remember me"
	feature
	* @param sendPassword whether to allow users to ask the company to send
	their passwords
	* @param strangers whether to allow strangers to create accounts to
	register themselves in the company
	* @param strangersWithMx whether to allow strangers to create accounts with
	email addresses that match the company mail suffix
	* @param strangersVerify whether to require strangers who create accounts
	to be verified via email
	* @param siteLogo whether to to allow site administrators to use their own
	logo instead of the enterprise logo
	*/
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	public void updateSecurity(long companyId, java.lang.String authType,
		boolean autoLogin, boolean sendPassword, boolean strangers,
		boolean strangersWithMx, boolean strangersVerify, boolean siteLogo)
		throws PortalException;
}