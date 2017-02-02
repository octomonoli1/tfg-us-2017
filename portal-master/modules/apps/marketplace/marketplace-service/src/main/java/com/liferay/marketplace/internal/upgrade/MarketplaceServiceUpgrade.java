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

package com.liferay.marketplace.internal.upgrade;

import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.service.ExpandoTableLocalService;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.marketplace.internal.upgrade.v1_0_0.UpgradeCompanyId;
import com.liferay.marketplace.internal.upgrade.v1_0_0.UpgradeExpando;
import com.liferay.marketplace.internal.upgrade.v1_0_0.UpgradeModule;
import com.liferay.marketplace.internal.upgrade.v2_0_0.UpgradeApp;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Joan Kim
 * @author Ryan Park
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class MarketplaceServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.marketplace.service", "0.0.1", "2.0.0",
			new UpgradeExpando(
				_expandoColumnLocalService, _expandoTableLocalService,
				_expandoValueLocalService));

		registry.register(
			"com.liferay.marketplace.service", "1.0.0", "1.0.1",
			new UpgradeModule());

		registry.register(
			"com.liferay.marketplace.service", "1.0.1", "1.0.2",
			new UpgradeCompanyId());

		registry.register(
			"com.liferay.marketplace.service", "1.0.2", "2.0.0",
			new UpgradeApp());
	}

	@Reference(unbind = "-")
	protected void setExpandoColumnLocalService(
		ExpandoColumnLocalService expandoColumnLocalService) {

		_expandoColumnLocalService = expandoColumnLocalService;
	}

	@Reference(unbind = "-")
	protected void setExpandoTableLocalService(
		ExpandoTableLocalService expandoTableLocalService) {

		_expandoTableLocalService = expandoTableLocalService;
	}

	@Reference(unbind = "-")
	protected void setExpandoValueLocalService(
		ExpandoValueLocalService expandoValueLocalService) {

		_expandoValueLocalService = expandoValueLocalService;
	}

	private ExpandoColumnLocalService _expandoColumnLocalService;
	private ExpandoTableLocalService _expandoTableLocalService;
	private ExpandoValueLocalService _expandoValueLocalService;

}