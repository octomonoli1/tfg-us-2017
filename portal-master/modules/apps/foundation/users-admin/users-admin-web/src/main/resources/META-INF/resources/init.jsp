<%--
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
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.admin.kernel.util.PortalMyAccountApplicationType" %><%@
page import="com.liferay.announcements.kernel.model.AnnouncementsDelivery" %><%@
page import="com.liferay.announcements.kernel.model.AnnouncementsEntryConstants" %><%@
page import="com.liferay.announcements.kernel.service.AnnouncementsDeliveryLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.bean.BeanPropertiesUtil" %><%@
page import="com.liferay.portal.kernel.configuration.Filter" %><%@
page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker" %><%@
page import="com.liferay.portal.kernel.dao.search.RowChecker" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.exception.AddressCityException" %><%@
page import="com.liferay.portal.kernel.exception.AddressStreetException" %><%@
page import="com.liferay.portal.kernel.exception.AddressZipException" %><%@
page import="com.liferay.portal.kernel.exception.CompanyMaxUsersException" %><%@
page import="com.liferay.portal.kernel.exception.ContactBirthdayException" %><%@
page import="com.liferay.portal.kernel.exception.DuplicateOpenIdException" %><%@
page import="com.liferay.portal.kernel.exception.DuplicateOrganizationException" %><%@
page import="com.liferay.portal.kernel.exception.EmailAddressException" %><%@
page import="com.liferay.portal.kernel.exception.GroupFriendlyURLException" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchCountryException" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchListTypeException" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchOrganizationException" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchRegionException" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchRoleException" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchUserException" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchUserGroupException" %><%@
page import="com.liferay.portal.kernel.exception.OrganizationNameException" %><%@
page import="com.liferay.portal.kernel.exception.OrganizationParentException" %><%@
page import="com.liferay.portal.kernel.exception.PhoneNumberException" %><%@
page import="com.liferay.portal.kernel.exception.PhoneNumberExtensionException" %><%@
page import="com.liferay.portal.kernel.exception.RequiredOrganizationException" %><%@
page import="com.liferay.portal.kernel.exception.RequiredUserException" %><%@
page import="com.liferay.portal.kernel.exception.UserEmailAddressException" %><%@
page import="com.liferay.portal.kernel.exception.UserFieldException" %><%@
page import="com.liferay.portal.kernel.exception.UserIdException" %><%@
page import="com.liferay.portal.kernel.exception.UserLockoutException" %><%@
page import="com.liferay.portal.kernel.exception.UserPasswordException" %><%@
page import="com.liferay.portal.kernel.exception.UserScreenNameException" %><%@
page import="com.liferay.portal.kernel.exception.UserSmsException" %><%@
page import="com.liferay.portal.kernel.exception.WebsiteURLException" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.Address" %><%@
page import="com.liferay.portal.kernel.model.Contact" %><%@
page import="com.liferay.portal.kernel.model.EmailAddress" %><%@
page import="com.liferay.portal.kernel.model.Group" %><%@
page import="com.liferay.portal.kernel.model.Layout" %><%@
page import="com.liferay.portal.kernel.model.LayoutConstants" %><%@
page import="com.liferay.portal.kernel.model.LayoutSet" %><%@
page import="com.liferay.portal.kernel.model.LayoutSetPrototype" %><%@
page import="com.liferay.portal.kernel.model.ListTypeConstants" %><%@
page import="com.liferay.portal.kernel.model.ModelHintsConstants" %><%@
page import="com.liferay.portal.kernel.model.OrgLabor" %><%@
page import="com.liferay.portal.kernel.model.Organization" %><%@
page import="com.liferay.portal.kernel.model.OrganizationConstants" %><%@
page import="com.liferay.portal.kernel.model.PasswordPolicy" %><%@
page import="com.liferay.portal.kernel.model.Phone" %><%@
page import="com.liferay.portal.kernel.model.ResourceConstants" %><%@
page import="com.liferay.portal.kernel.model.Role" %><%@
page import="com.liferay.portal.kernel.model.RoleConstants" %><%@
page import="com.liferay.portal.kernel.model.User" %><%@
page import="com.liferay.portal.kernel.model.UserConstants" %><%@
page import="com.liferay.portal.kernel.model.UserGroup" %><%@
page import="com.liferay.portal.kernel.model.UserGroupGroupRole" %><%@
page import="com.liferay.portal.kernel.model.UserGroupRole" %><%@
page import="com.liferay.portal.kernel.model.Website" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.portlet.PortletProvider" %><%@
page import="com.liferay.portal.kernel.portlet.PortletProviderUtil" %><%@
page import="com.liferay.portal.kernel.portlet.PortletURLUtil" %><%@
page import="com.liferay.portal.kernel.security.auth.ScreenNameValidator" %><%@
page import="com.liferay.portal.kernel.security.membershippolicy.OrganizationMembershipPolicyUtil" %><%@
page import="com.liferay.portal.kernel.security.membershippolicy.RoleMembershipPolicyUtil" %><%@
page import="com.liferay.portal.kernel.security.membershippolicy.SiteMembershipPolicyUtil" %><%@
page import="com.liferay.portal.kernel.security.membershippolicy.UserGroupMembershipPolicyUtil" %><%@
page import="com.liferay.portal.kernel.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.kernel.service.AddressServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.EmailAddressServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.LayoutLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.LayoutSetLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.LayoutSetPrototypeLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.LayoutSetPrototypeServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.OrgLaborServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.OrganizationLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.OrganizationServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.PasswordPolicyLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.PhoneServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.UserGroupGroupRoleLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.UserGroupLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.WebsiteServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.permission.GroupPermissionUtil" %><%@
page import="com.liferay.portal.kernel.service.permission.OrganizationPermissionUtil" %><%@
page import="com.liferay.portal.kernel.service.permission.PortalPermissionUtil" %><%@
page import="com.liferay.portal.kernel.service.permission.UserPermissionUtil" %><%@
page import="com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorConstants" %><%@
page import="com.liferay.portal.kernel.util.ArrayUtil" %><%@
page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.CalendarUtil" %><%@
page import="com.liferay.portal.kernel.util.CharPool" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ListUtil" %><%@
page import="com.liferay.portal.kernel.util.LocaleUtil" %><%@
page import="com.liferay.portal.kernel.util.LocalizationUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.PropsKeys" %><%@
page import="com.liferay.portal.kernel.util.StringBundler" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.TextFormatter" %><%@
page import="com.liferay.portal.kernel.util.UnicodeFormatter" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.kernel.webserver.WebServerServletTokenUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %><%@
page import="com.liferay.portal.model.impl.AddressImpl" %><%@
page import="com.liferay.portal.model.impl.EmailAddressImpl" %><%@
page import="com.liferay.portal.model.impl.OrgLaborImpl" %><%@
page import="com.liferay.portal.model.impl.PhoneImpl" %><%@
page import="com.liferay.portal.model.impl.WebsiteImpl" %><%@
page import="com.liferay.portal.security.auth.ScreenNameValidatorFactory" %><%@
page import="com.liferay.portal.util.PrefsPropsUtil" %><%@
page import="com.liferay.portal.util.PropsUtil" %><%@
page import="com.liferay.portal.util.PropsValues" %><%@
page import="com.liferay.portlet.announcements.model.impl.AnnouncementsDeliveryImpl" %><%@
page import="com.liferay.portlet.usergroupsadmin.search.UserGroupDisplayTerms" %><%@
page import="com.liferay.portlet.usergroupsadmin.search.UserGroupSearch" %><%@
page import="com.liferay.portlet.usersadmin.search.OrganizationSearch" %><%@
page import="com.liferay.portlet.usersadmin.search.OrganizationSearchTerms" %><%@
page import="com.liferay.portlet.usersadmin.search.UserDisplayTerms" %><%@
page import="com.liferay.portlet.usersadmin.search.UserOrganizationChecker" %><%@
page import="com.liferay.portlet.usersadmin.search.UserSearch" %><%@
page import="com.liferay.portlet.usersadmin.search.UserSearchTerms" %><%@
page import="com.liferay.roles.admin.kernel.util.RolesAdminUtil" %><%@
page import="com.liferay.taglib.search.ResultRow" %><%@
page import="com.liferay.taglib.search.SearchEntry" %><%@
page import="com.liferay.users.admin.constants.UsersAdminPortletKeys" %><%@
page import="com.liferay.users.admin.kernel.util.UsersAdmin" %><%@
page import="com.liferay.users.admin.kernel.util.UsersAdminUtil" %><%@
page import="com.liferay.users.admin.web.search.OrganizationChecker" %>

<%@ page import="java.text.Format" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Calendar" %><%@
page import="java.util.Collections" %><%@
page import="java.util.HashMap" %><%@
page import="java.util.LinkedHashMap" %><%@
page import="java.util.List" %><%@
page import="java.util.Locale" %><%@
page import="java.util.Map" %><%@
page import="java.util.Set" %>

<%@ page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.WindowState" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
boolean filterManageableGroups = true;

boolean filterManageableOrganizations = true;

if (permissionChecker.hasPermission(0, Organization.class.getName(), Organization.class.getName(), ActionKeys.VIEW)) {
	filterManageableOrganizations = false;
}

boolean filterManageableRoles = true;
boolean filterManageableUserGroupRoles = true;
boolean filterManageableUserGroups = true;

String myAccountPortletId = PortletProviderUtil.getPortletId(PortalMyAccountApplicationType.MyAccount.CLASS_NAME, PortletProvider.Action.VIEW);

if (portletName.equals(myAccountPortletId)) {
	filterManageableGroups = false;
	filterManageableOrganizations = false;
	filterManageableRoles = false;
	filterManageableUserGroupRoles = false;
	filterManageableUserGroups = false;
}
else if (permissionChecker.isCompanyAdmin()) {
	filterManageableGroups = false;
	filterManageableOrganizations = false;
	filterManageableUserGroups = false;
}
%>

<%@ include file="/init-ext.jsp" %>