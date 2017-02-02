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

package com.liferay.roles.admin.web.internal.portlet;

import com.liferay.application.list.PanelAppRegistry;
import com.liferay.application.list.PanelCategoryRegistry;
import com.liferay.application.list.constants.ApplicationListWebKeys;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.application.list.display.context.logic.PanelCategoryHelper;
import com.liferay.portal.kernel.exception.DuplicateRoleException;
import com.liferay.portal.kernel.exception.NoSuchRoleException;
import com.liferay.portal.kernel.exception.RequiredRoleException;
import com.liferay.portal.kernel.exception.RoleAssignmentException;
import com.liferay.portal.kernel.exception.RoleNameException;
import com.liferay.portal.kernel.exception.RolePermissionsException;
import com.liferay.portal.kernel.messaging.proxy.ProxyModeThreadLocal;
import com.liferay.portal.kernel.messaging.proxy.ProxyModeThreadLocalCloseable;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.security.permission.comparator.ActionComparator;
import com.liferay.portal.kernel.service.GroupService;
import com.liferay.portal.kernel.service.ResourceBlockLocalService;
import com.liferay.portal.kernel.service.ResourceBlockService;
import com.liferay.portal.kernel.service.ResourcePermissionService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.RoleService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.roles.admin.constants.RolesAdminPortletKeys;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 * @author Connor McKay
 * @author Drew Brokke
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=portlet-users-admin",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.icon=/icons/roles_admin.png",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=Roles Admin",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + RolesAdminPortletKeys.ROLES_ADMIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = javax.portlet.Portlet.class
)
public class RolesAdminPortlet extends MVCPortlet {

	public void deletePermission(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long roleId = ParamUtil.getLong(actionRequest, "roleId");
		String name = ParamUtil.getString(actionRequest, "name");
		int scope = ParamUtil.getInteger(actionRequest, "scope");
		String primKey = ParamUtil.getString(actionRequest, "primKey");
		String actionId = ParamUtil.getString(actionRequest, "actionId");

		Role role = _roleLocalService.getRole(roleId);

		String roleName = role.getName();

		if (roleName.equals(RoleConstants.ADMINISTRATOR) ||
			roleName.equals(RoleConstants.ORGANIZATION_ADMINISTRATOR) ||
			roleName.equals(RoleConstants.ORGANIZATION_OWNER) ||
			roleName.equals(RoleConstants.OWNER) ||
			roleName.equals(RoleConstants.SITE_ADMINISTRATOR) ||
			roleName.equals(RoleConstants.SITE_OWNER)) {

			throw new RolePermissionsException(roleName);
		}

		if (_resourceBlockLocalService.isSupported(name)) {
			if (scope == ResourceConstants.SCOPE_GROUP) {
				_resourceBlockService.removeGroupScopePermission(
					themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(),
					GetterUtil.getLong(primKey), name, roleId, actionId);
			}
			else {
				_resourceBlockService.removeCompanyScopePermission(
					themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(),
					name, roleId, actionId);
			}
		}
		else {
			_resourcePermissionService.removeResourcePermission(
				themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(),
				name, scope, primKey, roleId, actionId);
		}

		// Send redirect

		SessionMessages.add(actionRequest, "permissionDeleted");

		String redirect = PortalUtil.escapeRedirect(
			ParamUtil.getString(actionRequest, "redirect"));

		if (Validator.isNotNull(redirect)) {
			actionResponse.sendRedirect(redirect);
		}
	}

	public void deleteRole(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long roleId = ParamUtil.getLong(actionRequest, "roleId");

		_roleService.deleteRole(roleId);
	}

	public void deleteRoles(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] deleteRoleIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "deleteRoleIds"), 0L);

		for (long roleId : deleteRoleIds) {
			_roleService.deleteRole(roleId);
		}
	}

	public Role editRole(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long roleId = ParamUtil.getLong(actionRequest, "roleId");

		String name = ParamUtil.getString(actionRequest, "name");
		Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "title");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(actionRequest, "description");
		int type = ParamUtil.getInteger(
			actionRequest, "type", RoleConstants.TYPE_REGULAR);
		String subtype = ParamUtil.getString(actionRequest, "subtype");
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Role.class.getName(), actionRequest);

		if (roleId <= 0) {

			// Add role

			return _roleService.addRole(
				null, 0, name, titleMap, descriptionMap, type, subtype,
				serviceContext);
		}
		else {

			// Update role

			return _roleService.updateRole(
				roleId, name, titleMap, descriptionMap, subtype,
				serviceContext);
		}
	}

	public void editRoleAssignments(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long roleId = ParamUtil.getLong(actionRequest, "roleId");
		Role role = _roleLocalService.getRole(roleId);

		if (role.getName().equals(RoleConstants.OWNER)) {
			throw new RoleAssignmentException(role.getName());
		}

		long[] addUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "addUserIds"), 0L);
		long[] removeUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "removeUserIds"), 0L);

		if (!ArrayUtil.isEmpty(addUserIds) ||
			!ArrayUtil.isEmpty(removeUserIds)) {

			try (ProxyModeThreadLocalCloseable proxyModeThreadLocalCloseable =
					new ProxyModeThreadLocalCloseable()) {

				ProxyModeThreadLocal.setForceSync(true);

				_userService.addRoleUsers(roleId, addUserIds);
				_userService.unsetRoleUsers(roleId, removeUserIds);
			}
		}

		long[] addGroupIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "addGroupIds"), 0L);
		long[] removeGroupIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "removeGroupIds"), 0L);

		if (!ArrayUtil.isEmpty(addGroupIds) ||
			!ArrayUtil.isEmpty(removeGroupIds)) {

			_groupService.addRoleGroups(roleId, addGroupIds);
			_groupService.unsetRoleGroups(roleId, removeGroupIds);
		}
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		setAttributes(resourceRequest);

		super.serveResource(resourceRequest, resourceResponse);
	}

	public void updateActions(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long roleId = ParamUtil.getLong(actionRequest, "roleId");

		Role role = _roleLocalService.getRole(roleId);

		String roleName = role.getName();

		if (roleName.equals(RoleConstants.ADMINISTRATOR) ||
			roleName.equals(RoleConstants.ORGANIZATION_ADMINISTRATOR) ||
			roleName.equals(RoleConstants.ORGANIZATION_OWNER) ||
			roleName.equals(RoleConstants.OWNER) ||
			roleName.equals(RoleConstants.SITE_ADMINISTRATOR) ||
			roleName.equals(RoleConstants.SITE_OWNER)) {

			throw new RolePermissionsException(roleName);
		}

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");
		String[] relatedPortletResources = StringUtil.split(
			ParamUtil.getString(actionRequest, "relatedPortletResources"));
		String[] modelResources = StringUtil.split(
			ParamUtil.getString(actionRequest, "modelResources"));

		Map<String, List<String>> resourceActionsMap = new HashMap<>();

		if (Validator.isNotNull(portletResource)) {
			resourceActionsMap.put(
				portletResource,
				ResourceActionsUtil.getResourceActions(portletResource, null));
		}

		for (String relatedPortletResource : relatedPortletResources) {
			resourceActionsMap.put(
				relatedPortletResource,
				ResourceActionsUtil.getResourceActions(
					relatedPortletResource, null));
		}

		for (String modelResource : modelResources) {
			resourceActionsMap.put(
				modelResource,
				ResourceActionsUtil.getResourceActions(null, modelResource));
		}

		int rootResourceScope = ResourceConstants.SCOPE_COMPANY;
		String[] rootResourceGroupIds = null;

		String[] selectedTargets = StringUtil.split(
			ParamUtil.getString(actionRequest, "selectedTargets"));
		String[] unselectedTargets = StringUtil.split(
			ParamUtil.getString(actionRequest, "unselectedTargets"));

		for (Map.Entry<String, List<String>> entry :
				resourceActionsMap.entrySet()) {

			String selResource = entry.getKey();
			List<String> actions = entry.getValue();

			actions = ListUtil.sort(
				actions, new ActionComparator(themeDisplay.getLocale()));

			for (String actionId : actions) {
				String target = selResource + actionId;

				boolean selected = ArrayUtil.contains(selectedTargets, target);

				if (!selected &&
					!ArrayUtil.contains(unselectedTargets, target)) {

					continue;
				}

				String[] groupIds = StringUtil.split(
					ParamUtil.getString(actionRequest, "groupIds" + target));

				groupIds = ArrayUtil.distinct(groupIds);

				int scope = ResourceConstants.SCOPE_COMPANY;

				if ((role.getType() == RoleConstants.TYPE_ORGANIZATION) ||
					(role.getType() == RoleConstants.TYPE_PROVIDER) ||
					(role.getType() == RoleConstants.TYPE_SITE)) {

					scope = ResourceConstants.SCOPE_GROUP_TEMPLATE;
				}
				else {
					if (groupIds.length > 0) {
						scope = ResourceConstants.SCOPE_GROUP;
					}
				}

				if (_resourceBlockLocalService.isSupported(selResource)) {
					updateActions_Blocks(
						role, themeDisplay.getScopeGroupId(), selResource,
						actionId, selected, scope, groupIds);
				}
				else {
					updateAction(
						role, themeDisplay.getScopeGroupId(), selResource,
						actionId, selected, scope, groupIds);
				}

				if (selected &&
					actionId.equals(ActionKeys.ACCESS_IN_CONTROL_PANEL)) {

					updateViewControlPanelPermission(
						role, themeDisplay.getScopeGroupId(), selResource,
						scope, groupIds);

					rootResourceScope = scope;
					rootResourceGroupIds = groupIds;
				}
			}
		}

		// LPS-38031

		if (rootResourceGroupIds != null) {
			updateViewRootResourcePermission(
				role, themeDisplay.getScopeGroupId(), portletResource,
				rootResourceScope, rootResourceGroupIds);
		}

		// Send redirect

		SessionMessages.add(actionRequest, "permissionsUpdated");

		String redirect = PortalUtil.escapeRedirect(
			ParamUtil.getString(actionRequest, "redirect"));

		if (Validator.isNotNull(redirect)) {
			actionResponse.sendRedirect(redirect);
		}
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		setAttributes(renderRequest);

		long roleId = ParamUtil.getLong(renderRequest, "roleId");

		if (SessionErrors.contains(
				renderRequest, RequiredRoleException.class.getName()) &&
			(roleId < 1)) {

			include("/view.jsp", renderRequest, renderResponse);
		}
		else if (SessionErrors.contains(
					renderRequest, DuplicateRoleException.class.getName()) ||
				 SessionErrors.contains(
					 renderRequest, RequiredRoleException.class.getName()) ||
				 SessionErrors.contains(
					 renderRequest, RoleNameException.class.getName())) {

			include("/edit_role.jsp", renderRequest, renderResponse);
		}
		else if (SessionErrors.contains(
					renderRequest, NoSuchRoleException.class.getName()) ||
				 SessionErrors.contains(
					 renderRequest, PrincipalException.getNestedClasses()) ||
				 SessionErrors.contains(
					 renderRequest, RoleAssignmentException.class.getName()) ||
				 SessionErrors.contains(
					 renderRequest, RolePermissionsException.class.getName())) {

			include("/error.jsp", renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof DuplicateRoleException ||
			cause instanceof NoSuchRoleException ||
			cause instanceof PrincipalException ||
			cause instanceof RequiredRoleException ||
			cause instanceof RoleAssignmentException ||
			cause instanceof RoleNameException ||
			cause instanceof RolePermissionsException) {

			return true;
		}

		return false;
	}

	protected void setAttributes(PortletRequest portletRequest) {
		portletRequest.setAttribute(
			ApplicationListWebKeys.PANEL_APP_REGISTRY, _panelAppRegistry);

		PanelCategoryHelper panelCategoryHelper = new PanelCategoryHelper(
			_panelAppRegistry, _panelCategoryRegistry);

		portletRequest.setAttribute(
			ApplicationListWebKeys.PANEL_CATEGORY_HELPER, panelCategoryHelper);

		portletRequest.setAttribute(
			ApplicationListWebKeys.PANEL_CATEGORY_REGISTRY,
			_panelCategoryRegistry);
	}

	@Reference(unbind = "-")
	protected void setGroupService(GroupService groupService) {
		_groupService = groupService;
	}

	@Reference(unbind = "-")
	protected void setPanelAppRegistry(PanelAppRegistry panelAppRegistry) {
		_panelAppRegistry = panelAppRegistry;
	}

	@Reference(unbind = "-")
	protected void setPanelCategoryRegistry(
		PanelCategoryRegistry panelCategoryRegistry) {

		_panelCategoryRegistry = panelCategoryRegistry;
	}

	@Reference(unbind = "-")
	protected void setResourceBlockLocalService(
		ResourceBlockLocalService resourceBlockLocalService) {

		_resourceBlockLocalService = resourceBlockLocalService;
	}

	@Reference(unbind = "-")
	protected void setResourceBlockService(
		ResourceBlockService resourceBlockService) {

		_resourceBlockService = resourceBlockService;
	}

	@Reference(unbind = "-")
	protected void setResourcePermissionService(
		ResourcePermissionService resourcePermissionService) {

		_resourcePermissionService = resourcePermissionService;
	}

	@Reference(unbind = "-")
	protected void setRoleLocalService(RoleLocalService roleLocalService) {
		_roleLocalService = roleLocalService;
	}

	@Reference(unbind = "-")
	protected void setRoleService(RoleService roleService) {
		_roleService = roleService;
	}

	@Reference(unbind = "-")
	protected void setUserService(UserService userService) {
		_userService = userService;
	}

	protected void updateAction(
			Role role, long groupId, String selResource, String actionId,
			boolean selected, int scope, String[] groupIds)
		throws Exception {

		long companyId = role.getCompanyId();
		long roleId = role.getRoleId();

		if (selected) {
			if (scope == ResourceConstants.SCOPE_COMPANY) {
				_resourcePermissionService.addResourcePermission(
					groupId, companyId, selResource, scope,
					String.valueOf(role.getCompanyId()), roleId, actionId);
			}
			else if (scope == ResourceConstants.SCOPE_GROUP_TEMPLATE) {
				_resourcePermissionService.addResourcePermission(
					groupId, companyId, selResource,
					ResourceConstants.SCOPE_GROUP_TEMPLATE,
					String.valueOf(GroupConstants.DEFAULT_PARENT_GROUP_ID),
					roleId, actionId);
			}
			else if (scope == ResourceConstants.SCOPE_GROUP) {
				_resourcePermissionService.removeResourcePermissions(
					groupId, companyId, selResource,
					ResourceConstants.SCOPE_GROUP, roleId, actionId);

				for (String curGroupId : groupIds) {
					_resourcePermissionService.addResourcePermission(
						groupId, companyId, selResource,
						ResourceConstants.SCOPE_GROUP, curGroupId, roleId,
						actionId);
				}
			}
		}
		else {

			// Remove company, group template, and group permissions

			_resourcePermissionService.removeResourcePermissions(
				groupId, companyId, selResource,
				ResourceConstants.SCOPE_COMPANY, roleId, actionId);

			_resourcePermissionService.removeResourcePermissions(
				groupId, companyId, selResource,
				ResourceConstants.SCOPE_GROUP_TEMPLATE, roleId, actionId);

			_resourcePermissionService.removeResourcePermissions(
				groupId, companyId, selResource, ResourceConstants.SCOPE_GROUP,
				roleId, actionId);
		}
	}

	protected void updateActions_Blocks(
			Role role, long scopeGroupId, String selResource, String actionId,
			boolean selected, int scope, String[] groupIds)
		throws Exception {

		long companyId = role.getCompanyId();
		long roleId = role.getRoleId();

		if (selected) {
			if (scope == ResourceConstants.SCOPE_GROUP) {
				_resourceBlockService.removeAllGroupScopePermissions(
					scopeGroupId, companyId, selResource, roleId, actionId);
				_resourceBlockService.removeCompanyScopePermission(
					scopeGroupId, companyId, selResource, roleId, actionId);

				for (String groupId : groupIds) {
					_resourceBlockService.addGroupScopePermission(
						scopeGroupId, companyId, GetterUtil.getLong(groupId),
						selResource, roleId, actionId);
				}
			}
			else {
				_resourceBlockService.removeAllGroupScopePermissions(
					scopeGroupId, companyId, selResource, roleId, actionId);
				_resourceBlockService.addCompanyScopePermission(
					scopeGroupId, companyId, selResource, roleId, actionId);
			}
		}
		else {
			_resourceBlockService.removeAllGroupScopePermissions(
				scopeGroupId, companyId, selResource, roleId, actionId);
			_resourceBlockService.removeCompanyScopePermission(
				scopeGroupId, companyId, selResource, roleId, actionId);
		}
	}

	protected void updateViewControlPanelPermission(
			Role role, long scopeGroupId, String portletId, int scope,
			String[] groupIds)
		throws Exception {

		PanelCategoryHelper panelCategoryHelper = new PanelCategoryHelper(
			_panelAppRegistry, _panelCategoryRegistry);

		String selResource = null;
		String actionId = null;

		if (panelCategoryHelper.containsPortlet(
				portletId, PanelCategoryKeys.CONTROL_PANEL) &&
			(role.getType() == RoleConstants.TYPE_REGULAR)) {

			selResource = PortletKeys.PORTAL;
			actionId = ActionKeys.VIEW_CONTROL_PANEL;
		}
		else if (panelCategoryHelper.containsPortlet(
					portletId, PanelCategoryKeys.SITE_ADMINISTRATION)) {

			selResource = Group.class.getName();
			actionId = ActionKeys.VIEW_SITE_ADMINISTRATION;
		}

		if (selResource != null) {
			updateAction(
				role, scopeGroupId, selResource, actionId, true, scope,
				groupIds);
		}
	}

	protected void updateViewRootResourcePermission(
			Role role, long scopeGroupId, String portletId, int scope,
			String[] groupIds)
		throws Exception {

		String modelResource = ResourceActionsUtil.getPortletRootModelResource(
			portletId);

		if (modelResource != null) {
			List<String> actions = ResourceActionsUtil.getModelResourceActions(
				modelResource);

			if (actions.contains(ActionKeys.VIEW)) {
				updateAction(
					role, scopeGroupId, modelResource, ActionKeys.VIEW, true,
					scope, groupIds);
			}
		}
	}

	private GroupService _groupService;
	private PanelAppRegistry _panelAppRegistry;
	private PanelCategoryRegistry _panelCategoryRegistry;
	private ResourceBlockLocalService _resourceBlockLocalService;
	private ResourceBlockService _resourceBlockService;
	private ResourcePermissionService _resourcePermissionService;
	private RoleLocalService _roleLocalService;
	private RoleService _roleService;
	private UserService _userService;

}