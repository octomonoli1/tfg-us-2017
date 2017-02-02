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

package com.liferay.password.generator.web.internal.upgrade;

import com.liferay.password.generator.web.internal.constants.PasswordGeneratorPortletKeys;
import com.liferay.portal.kernel.upgrade.BaseUpgradePortletId;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Raymond Augé
 * @author Peter Fellwock
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class PasswordGeneratorWebUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.password.generator.web", "0.0.0", "1.0.0",
			new DummyUpgradeStep());

		registry.register(
			"com.liferay.password.generator.web", "0.0.1", "1.0.0",
			new BaseUpgradePortletId() {

				@Override
				protected String[][] getRenamePortletIdsArray() {
					return new String[][] {
						new String[] {
							"70",
							PasswordGeneratorPortletKeys.PASSWORD_GENERATOR
						}
					};
				}

			});
	}

}