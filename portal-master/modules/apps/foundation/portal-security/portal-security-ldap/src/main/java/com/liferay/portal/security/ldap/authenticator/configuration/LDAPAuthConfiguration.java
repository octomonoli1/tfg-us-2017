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

package com.liferay.portal.security.ldap.authenticator.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import com.liferay.portal.security.ldap.configuration.CompanyScopedConfiguration;

/**
 * @author Michael C. Han
 */
@ExtendedObjectClassDefinition(
	category = "foundation", factoryInstanceLabelAttribute = "companyId",
	scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	factory = true,
	id = "com.liferay.portal.security.ldap.authenticator.configuration.LDAPAuthConfiguration",
	localization = "content/Language", name = "ldap.auth.configuration.name"
)
public interface LDAPAuthConfiguration extends CompanyScopedConfiguration {

	@Meta.AD(deflt = "0", required = false)
	@Override
	public long companyId();

	@Meta.AD(deflt = "false", required = false)
	public boolean enabled();

	@Meta.AD(deflt = "false", required = false)
	public boolean required();

	@Meta.AD(
		deflt = "false", description = "password-policy-enabled-help",
		required = false
	)
	public boolean passwordPolicyEnabled();

	@Meta.AD(
		deflt = "bind", description = "method-help",
		optionValues = {"bind", "password-compare"}, required = false
	)
	public String method();

	@Meta.AD(
		deflt = "NONE", description = "password-encryption-algorithm-help",
		optionValues = {
			"BCRYPT", "MD2", "MD5", "NONE", "SHA", "SHA-256", "SHA-384", "SSHA",
			"UFC-CRYPT"
		},
		required = false
	)
	public String passwordEncryptionAlgorithm();

}