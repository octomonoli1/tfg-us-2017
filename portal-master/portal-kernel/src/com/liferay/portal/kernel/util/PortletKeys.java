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

package com.liferay.portal.kernel.util;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletKeys {

	public static final String ADMIN_PLUGINS = "136";

	public static final String ALERTS =
		"com_liferay_announcements_web_portlet_AlertsPortlet";

	public static final String ANNOUNCEMENTS =
		"com_liferay_announcements_web_portlet_AnnouncementsPortlet";

	public static final String BACKGROUND_TASK = "189";

	public static final String BLOGS =
		"com_liferay_blogs_web_portlet_BlogsPortlet";

	public static final String BLOGS_ADMIN =
		"com_liferay_blogs_web_portlet_BlogsAdminPortlet";

	public static final String BLOGS_AGGREGATOR =
		"com_liferay_blogs_web_portlet_BlogsAgreggatorPortlet";

	public static final String CALENDAR = "8";

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             com.liferay.chat.constants.ChatPortletKeys#CHAT}
	 */
	@Deprecated
	public static final String CHAT = "1_WAR_chatportlet";

	public static final String DIRECTORY =
		"com_liferay_directory_web_portlet_DirectoryPortlet";

	public static final String DOCUMENT_LIBRARY =
		"com_liferay_document_library_web_portlet_DLPortlet";

	public static final String DOCUMENT_LIBRARY_ADMIN =
		"com_liferay_document_library_web_portlet_DLAdminPortlet";

	public static final String EXPANDO =
		"com_liferay_expando_web_portlet_ExpandoPortlet";

	public static final String EXPORT_IMPORT =
		"com_liferay_exportimport_web_portlet_ExportImportPortlet";

	public static final String FAST_LOGIN =
		"com_liferay_login_web_portlet_FastLoginPortlet";

	public static final String FLAGS =
		"com_liferay_flags_web_portlet_FlagsPortlet";

	public static final String FRIENDS_DIRECTORY =
		"com_liferay_directory_web_portlet_FriendsDirectoryPortlet";

	public static final String GROUP_PAGES =
		"com_liferay_layout_admin_web_portlet_GroupPagesPortlet";

	public static final String IMAGE_UPLOADER =
		"com_liferay_image_uploader_web_portlet_ImageUploaderPortlet";

	public static final String ITEM_SELECTOR =
		"com_liferay_item_selector_web_portlet_ItemSelectorPortlet";

	public static final String LICENSE_MANAGER =
		"com_liferay_license_manager_web_portlet_LicenseManagerPortlet";

	public static final String LIFERAY_PORTAL = "LIFERAY_PORTAL";

	public static final String LOGIN =
		"com_liferay_login_web_portlet_LoginPortlet";

	public static final String MAIL =
		"com_liferay_mail_web_portlet_MailPortlet";

	public static final String MARKETPLACE_APP_MANAGER =
		"3_WAR_marketplaceportlet";

	public static final String MARKETPLACE_STORE = "1_WAR_marketplaceportlet";

	public static final String MEDIA_GALLERY_DISPLAY =
		"com_liferay_document_library_web_portlet_IGDisplayPortlet";

	public static final String MESSAGE_BOARDS =
		"com_liferay_message_boards_web_portlet_MBPortlet";

	public static final String MESSAGE_BOARDS_ADMIN =
		"com_liferay_message_boards_web_portlet_MBAdminPortlet";

	public static final String MONITORING_INVOKER =
		"com_liferay_monitoring_web_portlet_" +
			"MonitoringInvokerPortletFactoryImpl";

	public static final String MY_ACCOUNT =
		"com_liferay_my_account_web_portlet_MyAccountPortlet";

	public static final String MY_PAGES =
		"com_liferay_layout_admin_web_portlet_MyPagesPortlet";

	public static final String MY_SITES_DIRECTORY =
		"com_liferay_directory_web_portlet_MySitesDirectoryPortlet";

	public static final String MY_WORKFLOW_INSTANCE =
		"com_liferay_portal_workflow_instance_web_portlet_" +
			"MyWorkflowInstancePortlet";

	public static final String MY_WORKFLOW_TASK =
		"com_liferay_portal_workflow_task_web_portlet_MyWorkflowTaskPortlet";

	public static final String NESTED_PORTLETS =
		"com_liferay_nested_portlets_web_portlet_NestedPortletsPortlet";

	public static final String PASSWORD_POLICIES_ADMIN =
		"com_liferay_password_policies_admin_web_portlet_" +
			"PasswordPoliciesAdminPortlet";

	public static final String PLUGINS_ADMIN =
		"com_liferay_plugins_admin_web_portlet_PluginsAdminPortlet";

	public static final String PORTAL = "90";

	public static final String PORTAL_SETTINGS =
		"com_liferay_portal_settings_web_portlet_PortalSettingsPortlet";

	public static final String PORTLET_DISPLAY_TEMPLATE =
		"com_liferay_dynamic_data_mapping_web_portlet_" +
			"PortletDisplayTemplatePortlet";

	public static final long PREFS_OWNER_ID_DEFAULT = 0;

	public static final int PREFS_OWNER_TYPE_ARCHIVED = 5;

	public static final int PREFS_OWNER_TYPE_COMPANY = 1;

	public static final int PREFS_OWNER_TYPE_GROUP = 2;

	public static final int PREFS_OWNER_TYPE_LAYOUT = 3;

	public static final int PREFS_OWNER_TYPE_ORGANIZATION = 6;

	public static final int PREFS_OWNER_TYPE_USER = 4;

	public static final long PREFS_PLID_SHARED = 0;

	public static final String RECENT_DOCUMENTS =
		"com_liferay_recent_documents_web_portlet_RecentDocumentsPortlet";

	public static final String REQUESTS = "121";

	public static final String ROLES_ADMIN =
		"com_liferay_roles_admin_web_portlet_RolesAdminPortlet";

	public static final String SERVER_ADMIN =
		"com_liferay_server_admin_web_portlet_ServerAdminPortlet";

	public static final String SHOPPING = "34";

	public static final String SITE_ADMIN =
		"com_liferay_site_admin_web_portlet_SiteAdminPortlet";

	public static final String SITE_MEMBERS_DIRECTORY =
		"com_liferay_directory_web_portlet_SiteMembersDirectoryPortlet";

	public static final String STOCKS = "12";

	public static final String TRANSLATOR = "26";

	public static final String UNIT_CONVERTER = "27";

	public static final String USER_GROUPS_ADMIN =
		"com_liferay_user_groups_admin_web_portlet_UserGroupsAdminPortlet";

	public static final String USERS_ADMIN =
		"com_liferay_users_admin_web_portlet_UsersAdminPortlet";

	public static final String WORKFLOW_DEFINITION =
		"com_liferay_portal_workflow_definition_web_portlet_" +
			"WorkflowDefinitionPortlet";

}