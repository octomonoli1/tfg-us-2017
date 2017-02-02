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

package com.liferay.portal.kernel.portlet;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

import javax.portlet.PortletURL;
import javax.portlet.ResourceURL;

/**
 * Represents a URL pointing to a portlet.
 *
 * @author Brian Wing Shun Chan
 * @see    com.liferay.portlet.PortletURLImpl
 */
public interface LiferayPortletURL
	extends PortletURL, ResourceURL, Serializable {

	/**
	 * Adds a parameter that is included in the friendly URL path and does not
	 * need to appear in the query string.
	 *
	 * @param name the name of the parameter
	 */
	public void addParameterIncludedInPath(String name);

	/**
	 * Returns the portlet lifecycle of this URL's target portlet.
	 *
	 * @return the portlet lifecycle of this URL's target portlet
	 * @see    #setLifecycle(String)
	 */
	public String getLifecycle();

	/**
	 * Returns the first value of the URL parameter.
	 *
	 * @param  name the name of the URL parameter
	 * @return the first value of the URL parameter
	 */
	public String getParameter(String name);

	/**
	 * Returns the parameters that are included in the friendly URL path and do
	 * not need to appear in the query string.
	 *
	 * @return the names of the parameters that are included in the friendly URL
	 *         path and do not need to appear in the query string
	 */
	public Set<String> getParametersIncludedInPath();

	public long getPlid();

	/**
	 * Returns the ID of this URL's target portlet.
	 *
	 * @return the ID of this URL's target portlet
	 */
	public String getPortletId();

	public Set<String> getRemovedParameterNames();

	/**
	 * Returns the map of reserved parameters for this URL.
	 *
	 * <p>
	 * This method is only used internally. Reserved parameters contain special,
	 * Liferay specific information, such as <code>p_p_id</code> and
	 * <code>p_p_lifecycle</code>.
	 * </p>
	 *
	 * @return the reserved parameter names and values in a read-only map
	 */
	public Map<String, String> getReservedParameterMap();

	/**
	 * Returns the ID of this URL's target resource.
	 *
	 * @return the ID of this URL's target resource
	 */
	public String getResourceID();

	/**
	 * Returns <code>true</code> if this URL is an anchor pointing to the
	 * specified portlet on the page.
	 *
	 * @return whether this URL is an anchor pointing to the specified portlet
	 *         on the page
	 * @see    #setAnchor(boolean)
	 */
	public boolean isAnchor();

	/**
	 * Returns <code>true</code> if the render parameters in the current request
	 * should be copied to this URL.
	 *
	 * @return whether the render parameters in the current request should be
	 *         copied to this URL
	 * @see    #setCopyCurrentRenderParameters(boolean)
	 */
	public boolean isCopyCurrentRenderParameters();

	/**
	 * Returns <code>true</code> if this URL should be encrypted.
	 *
	 * @return <code>true</code> if this URL should be encrypted;
	 *         <code>false</code> otherwise
	 * @see    #setEncrypt(boolean)
	 */
	public boolean isEncrypt();

	/**
	 * Returns <code>true</code> if this URL should be XML escaped.
	 *
	 * @return <code>true</code> if this URL should be XML escaped;
	 *         <code>false</code> otherwise
	 * @see    #setEscapeXml(boolean)
	 */
	public boolean isEscapeXml();

	/**
	 * Returns <code>true</code> if the parameter is included in the friendly
	 * URL path.
	 *
	 * @param  name the name of the parameter to check for inclusion in the path
	 * @return whether the parameter is included in the friendly URL path
	 * @see    #addParameterIncludedInPath(String)
	 */
	public boolean isParameterIncludedInPath(String name);

	/**
	 * Returns <code>true</code> if this URL is secure (https).
	 *
	 * @return <code>true</code> if this URL is secure; <code>false</code>
	 *         otherwise
	 */
	public boolean isSecure();

	/**
	 * Sets whether this URL is an anchor pointing to the specified portlet on
	 * the page.
	 *
	 * <p>
	 * An anchor URL will cause the user's browser to automatically jump down to
	 * the specified portlet after the page loads, avoiding the need to scroll.
	 * </p>
	 *
	 * @param anchor whether this URL is an anchor pointing to the specified
	 *        portlet on the page
	 */
	public void setAnchor(boolean anchor);

	/**
	 * Sets whether the render parameters in the current request should be
	 * copied to this URL.
	 *
	 * <p>
	 * New parameters set on this URL will appear before the copied render
	 * parameters.
	 * </p>
	 *
	 * @param copyCurrentRenderParameters whether the render parameters in the
	 *        current request should be copied to this URL
	 */
	public void setCopyCurrentRenderParameters(
		boolean copyCurrentRenderParameters);

	public void setDoAsGroupId(long doAsGroupId);

	/**
	 * Sets the ID of the user to impersonate.
	 *
	 * <p>
	 * When a page is accessed while impersonating a user, it will appear
	 * exactly as it would to that user.
	 * </p>
	 *
	 * @param doAsUserId the ID of the user to impersonate in the portlet this
	 *        URL points to
	 */
	public void setDoAsUserId(long doAsUserId);

	/**
	 * Sets the language ID of the user to impersonate. This will only have an
	 * effect when a user is being impersonated via {@link
	 * #setDoAsUserId(long)}.
	 *
	 * <p>
	 * The language set here will override the impersonated user's default
	 * language.
	 * </p>
	 *
	 * @param doAsUserLanguageId the language ID of the user to impersonate
	 */
	public void setDoAsUserLanguageId(String doAsUserLanguageId);

	/**
	 * Sets whether this URL should be encrypted.
	 *
	 * <p>
	 * In an encrypted URL, the value of every parameter will be encrypted using
	 * the company's key. This allows sensitive information to be placed in the
	 * URL without being vulnerable to snooping.
	 * </p>
	 *
	 * <p>
	 * Note that this is not the same as making a URL {@link #setSecure(boolean)
	 * secure}.
	 * </p>
	 */
	public void setEncrypt(boolean encrypt);

	/**
	 * Sets whether this URL should be XML escaped.
	 *
	 * <p>
	 * If a URL is XML escaped, it will automatically have special characters
	 * escaped when it is converted to a string or written to a {@link
	 * java.io.Writer}.
	 * </p>
	 *
	 * @param escapeXml whether this URL should be XML escaped
	 */
	public void setEscapeXml(boolean escapeXml);

	/**
	 * Sets the portlet lifecycle of this URL's target portlet.
	 *
	 * <p>
	 * Valid lifecycles are:
	 * </p>
	 *
	 * <ul>
	 * <li>
	 * {@link javax.portlet.PortletRequest#ACTION_PHASE}
	 * </li>
	 * <li>
	 * {@link javax.portlet.PortletRequest#RENDER_PHASE}
	 * </li>
	 * <li>
	 * {@link javax.portlet.PortletRequest#RESOURCE_PHASE}
	 * </li>
	 * </ul>
	 *
	 * @param lifecycle the portlet lifecycle
	 */
	public void setLifecycle(String lifecycle);

	/**
	 * Sets the URL parameter to the value.
	 *
	 * @param name the name of the URL parameter
	 * @param value the value of the URL parameter
	 * @param append whether the new value should be appended to any existing
	 *        values for the parameter. If <code>append</code> is
	 *        <code>false</code> any existing values will be overwritten with
	 *        the new value.
	 */
	public void setParameter(String name, String value, boolean append);

	/**
	 * Sets the URL parameter the values.
	 *
	 * @param name the name of the URL parameter
	 * @param values the values of the URL parameter
	 * @param append whether the new values should be appended to any existing
	 *        values for the parameter. If <code>append</code> is
	 *        <code>false</code> any existing values will be overwritten with
	 *        the new values.
	 */
	public void setParameter(String name, String[] values, boolean append);

	/**
	 * Sets the portlet layout ID.
	 *
	 * @param plid the portlet layout ID
	 */
	public void setPlid(long plid);

	/**
	 * Sets the ID of the target portlet.
	 */
	public void setPortletId(String portletId);

	public void setRefererGroupId(long refererGroupId);

	/**
	 * Sets the referer layout ID.
	 *
	 * @param refererPlid the referer layout ID
	 */
	public void setRefererPlid(long refererPlid);

	public void setRemovedParameterNames(Set<String> removedParamNames);

}