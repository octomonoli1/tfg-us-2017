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

package com.liferay.portal.kernel.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the PluginSetting service. Represents a row in the &quot;PluginSetting&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PluginSettingModel
 * @see com.liferay.portal.model.impl.PluginSettingImpl
 * @see com.liferay.portal.model.impl.PluginSettingModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.portal.model.impl.PluginSettingImpl")
@ProviderType
public interface PluginSetting extends PluginSettingModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.model.impl.PluginSettingImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<PluginSetting, Long> PLUGIN_SETTING_ID_ACCESSOR =
		new Accessor<PluginSetting, Long>() {
			@Override
			public Long get(PluginSetting pluginSetting) {
				return pluginSetting.getPluginSettingId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<PluginSetting> getTypeClass() {
				return PluginSetting.class;
			}
		};

	/**
	* Adds a role to the list of roles.
	*/
	public void addRole(java.lang.String role);

	/**
	* Returns an array of required roles of the plugin.
	*
	* @return an array of required roles of the plugin
	*/
	public java.lang.String[] getRolesArray();

	/**
	* Returns <code>true</code> if the user has permission to use this plugin
	*
	* @param userId the primary key of the user
	* @return <code>true</code> if the user has permission to use this plugin
	*/
	public boolean hasPermission(long userId);

	/**
	* Returns <code>true</code> if the plugin has a role with the specified
	* name.
	*
	* @param roleName the role name
	* @return <code>true</code> if the plugin has a role with the specified
	name
	*/
	public boolean hasRoleWithName(java.lang.String roleName);

	/**
	* Sets an array of required roles of the plugin.
	*/
	public void setRolesArray(java.lang.String[] rolesArray);
}