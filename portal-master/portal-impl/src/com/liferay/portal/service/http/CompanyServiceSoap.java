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

package com.liferay.portal.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.CompanyServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link CompanyServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.portal.kernel.model.CompanySoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.portal.kernel.model.Company}, that is translated to a
 * {@link com.liferay.portal.kernel.model.CompanySoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CompanyServiceHttp
 * @see com.liferay.portal.kernel.model.CompanySoap
 * @see CompanyServiceUtil
 * @generated
 */
@ProviderType
public class CompanyServiceSoap {
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
	public static com.liferay.portal.kernel.model.CompanySoap addCompany(
		java.lang.String webId, java.lang.String virtualHost,
		java.lang.String mx, boolean system, int maxUsers, boolean active)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.model.Company returnValue = CompanyServiceUtil.addCompany(webId,
					virtualHost, mx, system, maxUsers, active);

			return com.liferay.portal.kernel.model.CompanySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.CompanySoap deleteCompany(
		long companyId) throws RemoteException {
		try {
			com.liferay.portal.kernel.model.Company returnValue = CompanyServiceUtil.deleteCompany(companyId);

			return com.liferay.portal.kernel.model.CompanySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Deletes the company's logo.
	*
	* @param companyId the primary key of the company
	*/
	public static void deleteLogo(long companyId) throws RemoteException {
		try {
			CompanyServiceUtil.deleteLogo(companyId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns the company with the primary key.
	*
	* @param companyId the primary key of the company
	* @return Returns the company with the primary key
	*/
	public static com.liferay.portal.kernel.model.CompanySoap getCompanyById(
		long companyId) throws RemoteException {
		try {
			com.liferay.portal.kernel.model.Company returnValue = CompanyServiceUtil.getCompanyById(companyId);

			return com.liferay.portal.kernel.model.CompanySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns the company with the logo.
	*
	* @param logoId the ID of the company's logo
	* @return Returns the company with the logo
	*/
	public static com.liferay.portal.kernel.model.CompanySoap getCompanyByLogoId(
		long logoId) throws RemoteException {
		try {
			com.liferay.portal.kernel.model.Company returnValue = CompanyServiceUtil.getCompanyByLogoId(logoId);

			return com.liferay.portal.kernel.model.CompanySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns the company with the mail domian.
	*
	* @param mx the company's mail domain
	* @return Returns the company with the mail domain
	*/
	public static com.liferay.portal.kernel.model.CompanySoap getCompanyByMx(
		java.lang.String mx) throws RemoteException {
		try {
			com.liferay.portal.kernel.model.Company returnValue = CompanyServiceUtil.getCompanyByMx(mx);

			return com.liferay.portal.kernel.model.CompanySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns the company with the virtual host name.
	*
	* @param virtualHost the company's virtual host name
	* @return Returns the company with the virtual host name
	*/
	public static com.liferay.portal.kernel.model.CompanySoap getCompanyByVirtualHost(
		java.lang.String virtualHost) throws RemoteException {
		try {
			com.liferay.portal.kernel.model.Company returnValue = CompanyServiceUtil.getCompanyByVirtualHost(virtualHost);

			return com.liferay.portal.kernel.model.CompanySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Returns the company with the web domain.
	*
	* @param webId the company's web domain
	* @return Returns the company with the web domain
	*/
	public static com.liferay.portal.kernel.model.CompanySoap getCompanyByWebId(
		java.lang.String webId) throws RemoteException {
		try {
			com.liferay.portal.kernel.model.Company returnValue = CompanyServiceUtil.getCompanyByWebId(webId);

			return com.liferay.portal.kernel.model.CompanySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Removes the values that match the keys of the company's preferences.
	*
	* This method is called by {@link
	* com.liferay.portlet.portalsettings.action.EditLDAPServerAction} remotely
	* through {@link com.liferay.portal.kernel.service.CompanyService}.
	*
	* @param companyId the primary key of the company
	* @param keys the company's preferences keys to be remove
	*/
	public static void removePreferences(long companyId, java.lang.String[] keys)
		throws RemoteException {
		try {
			CompanyServiceUtil.removePreferences(companyId, keys);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

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
	public static com.liferay.portal.kernel.model.CompanySoap updateCompany(
		long companyId, java.lang.String virtualHost, java.lang.String mx,
		int maxUsers, boolean active) throws RemoteException {
		try {
			com.liferay.portal.kernel.model.Company returnValue = CompanyServiceUtil.updateCompany(companyId,
					virtualHost, mx, maxUsers, active);

			return com.liferay.portal.kernel.model.CompanySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

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
	public static com.liferay.portal.kernel.model.CompanySoap updateCompany(
		long companyId, java.lang.String virtualHost, java.lang.String mx,
		java.lang.String homeURL, boolean logo, byte[] logoBytes,
		java.lang.String name, java.lang.String legalName,
		java.lang.String legalId, java.lang.String legalType,
		java.lang.String sicCode, java.lang.String tickerSymbol,
		java.lang.String industry, java.lang.String type, java.lang.String size)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.model.Company returnValue = CompanyServiceUtil.updateCompany(companyId,
					virtualHost, mx, homeURL, logo, logoBytes, name, legalName,
					legalId, legalType, sicCode, tickerSymbol, industry, type,
					size);

			return com.liferay.portal.kernel.model.CompanySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

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
	@Deprecated
	public static com.liferay.portal.kernel.model.CompanySoap updateCompany(
		long companyId, java.lang.String virtualHost, java.lang.String mx,
		java.lang.String homeURL, java.lang.String name,
		java.lang.String legalName, java.lang.String legalId,
		java.lang.String legalType, java.lang.String sicCode,
		java.lang.String tickerSymbol, java.lang.String industry,
		java.lang.String type, java.lang.String size) throws RemoteException {
		try {
			com.liferay.portal.kernel.model.Company returnValue = CompanyServiceUtil.updateCompany(companyId,
					virtualHost, mx, homeURL, name, legalName, legalId,
					legalType, sicCode, tickerSymbol, industry, type, size);

			return com.liferay.portal.kernel.model.CompanySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Update the company's display.
	*
	* @param companyId the primary key of the company
	* @param languageId the ID of the company's default user's language
	* @param timeZoneId the ID of the company's default user's time zone
	*/
	public static void updateDisplay(long companyId,
		java.lang.String languageId, java.lang.String timeZoneId)
		throws RemoteException {
		try {
			CompanyServiceUtil.updateDisplay(companyId, languageId, timeZoneId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Updates the company's logo.
	*
	* @param companyId the primary key of the company
	* @param bytes the bytes of the company's logo image
	* @return the company with the primary key
	*/
	public static com.liferay.portal.kernel.model.CompanySoap updateLogo(
		long companyId, byte[] bytes) throws RemoteException {
		try {
			com.liferay.portal.kernel.model.Company returnValue = CompanyServiceUtil.updateLogo(companyId,
					bytes);

			return com.liferay.portal.kernel.model.CompanySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

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
	public static void updateSecurity(long companyId,
		java.lang.String authType, boolean autoLogin, boolean sendPassword,
		boolean strangers, boolean strangersWithMx, boolean strangersVerify,
		boolean siteLogo) throws RemoteException {
		try {
			CompanyServiceUtil.updateSecurity(companyId, authType, autoLogin,
				sendPassword, strangers, strangersWithMx, strangersVerify,
				siteLogo);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CompanyServiceSoap.class);
}