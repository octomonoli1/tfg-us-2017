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

package com.liferay.portal.upgrade.v7_0_0;

import com.liferay.portal.kernel.util.PortletKeys;

/**
 * @author Cristina González
 */
@SuppressWarnings("deprecation")
public class UpgradePortletId
	extends com.liferay.portal.upgrade.util.UpgradePortletId {

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(UpgradeUserNotificationEvent.class);

		super.doUpgrade();
	}

	@Override
	protected String[][] getRenamePortletIdsArray() {
		return new String[][] {
			new String[] {"115", PortletKeys.BLOGS_AGGREGATOR},
			new String[] {"125", PortletKeys.USERS_ADMIN},
			new String[] {"127", PortletKeys.USER_GROUPS_ADMIN},
			new String[] {"128", PortletKeys.ROLES_ADMIN},
			new String[] {"129", PortletKeys.PASSWORD_POLICIES_ADMIN},
			new String[] {"134", PortletKeys.SITE_ADMIN},
			new String[] {"139", PortletKeys.EXPANDO},
			new String[] {"140", PortletKeys.MY_PAGES},
			new String[] {"146", _LAYOUT_PROTOTYPE},
			new String[] {"147", _ASSET_CATEGORIES_ADMIN},
			new String[] {"149", _LAYOUT_SET_PROTOTYPE},
			new String[] {"156", PortletKeys.GROUP_PAGES},
			new String[] {"161", PortletKeys.BLOGS_ADMIN},
			new String[] {"162", PortletKeys.MESSAGE_BOARDS_ADMIN},
			new String[] {"165", _SITE_SETTINGS},
			new String[] {"174", _SITE_MEMBERSHIPS_ADMIN},
			new String[] {"19", PortletKeys.MESSAGE_BOARDS},
			new String[] {"191", _SITE_TEAMS},
			new String[] {"192", _SITE_TEMPLATE_SETTINGS},
			new String[] {"199", PortletKeys.DOCUMENT_LIBRARY_ADMIN},
			new String[] {"20", PortletKeys.DOCUMENT_LIBRARY},
			new String[] {"31", PortletKeys.MEDIA_GALLERY_DISPLAY},
			new String[] {"33", PortletKeys.BLOGS},
			new String[] {"83", PortletKeys.ALERTS},
			new String[] {"88", _LAYOUTS_ADMIN},
			new String[] {"99", _ASSET_TAGS_ADMIN}
		};
	}

	private static final String _ASSET_CATEGORIES_ADMIN =
		"com_liferay_asset_categories_admin_web_portlet_" +
			"AssetCategoriesAdminPortlet";

	private static final String _ASSET_TAGS_ADMIN =
		"com_liferay_asset_tags_admin_web_portlet_AssetTagsAdminPortlet";

	private static final String _LAYOUT_PROTOTYPE =
		"com_liferay_layout_prototype_web_portlet_LayoutPrototypePortlet";

	private static final String _LAYOUT_SET_PROTOTYPE =
		"com_liferay_layout_set_prototype_web_portlet_" +
			"LayoutSetPrototypePortlet";

	private static final String _LAYOUTS_ADMIN =
		"com_liferay_layout_admin_web_portlet_LayoutAdminPortlet";

	private static final String _SITE_MEMBERSHIPS_ADMIN =
		"com_liferay_site_memberships_web_portlet_SiteMembershipsPortlet";

	private static final String _SITE_SETTINGS =
		"com_liferay_site_admin_web_portlet_SiteSettingsPortlet";

	private static final String _SITE_TEAMS =
		"com_liferay_site_teams_web_portlet_SiteTeamsPortlet";

	private static final String _SITE_TEMPLATE_SETTINGS =
		"com_liferay_layout_set_prototype_web_portlet_" +
			"SiteTemplateSettingsPortlet";

}