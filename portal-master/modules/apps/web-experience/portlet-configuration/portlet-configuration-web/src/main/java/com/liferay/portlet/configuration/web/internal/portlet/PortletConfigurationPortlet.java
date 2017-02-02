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

package com.liferay.portlet.configuration.web.internal.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletConstants;
import com.liferay.portal.kernel.model.PortletPreferencesIds;
import com.liferay.portal.kernel.model.PublicRenderParameter;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.PortletConfigFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletConfigurationLayoutUtil;
import com.liferay.portal.kernel.portlet.PortletLayoutListener;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionPropagator;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.PermissionService;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.service.ResourceBlockLocalService;
import com.liferay.portal.kernel.service.ResourceBlockService;
import com.liferay.portal.kernel.service.ResourcePermissionService;
import com.liferay.portal.kernel.service.permission.PortletPermission;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.settings.ArchivedSettings;
import com.liferay.portal.kernel.settings.ModifiableSettings;
import com.liferay.portal.kernel.settings.PortletInstanceSettingsLocator;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.AggregateResourceBundle;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.PortletConfigImpl;
import com.liferay.portlet.configuration.kernel.util.PortletConfigurationUtil;
import com.liferay.portlet.configuration.web.internal.constants.PortletConfigurationPortletKeys;
import com.liferay.portlet.portletconfiguration.action.ActionUtil;
import com.liferay.portlet.portletconfiguration.util.PublicRenderParameterConfiguration;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Carlos Sierra Andrés
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=portlet-configuration",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.system=true",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=Portlet Configuration",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/edit_configuration.jsp",
		"javax.portlet.name=" + PortletConfigurationPortletKeys.PORTLET_CONFIGURATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = javax.portlet.Portlet.class
)
public class PortletConfigurationPortlet extends MVCPortlet {

	public void deleteArchivedSetups(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Portlet portlet = ActionUtil.getPortlet(actionRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String[] names = null;

		String name = ParamUtil.getString(actionRequest, "name");

		if (Validator.isNotNull(name)) {
			names = new String[] {name};
		}
		else {
			names = ParamUtil.getStringValues(actionRequest, "rowIds");
		}

		for (String curName : names) {
			ArchivedSettings archivedSettings =
				SettingsFactoryUtil.getPortletInstanceArchivedSettings(
					themeDisplay.getSiteGroupId(), portlet.getRootPortletId(),
					curName);

			archivedSettings.delete();
		}
	}

	public void editConfiguration(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Portlet portlet = ActionUtil.getPortlet(actionRequest);

		PortletConfig portletConfig = (PortletConfig)actionRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String settingsScope = ParamUtil.getString(
			actionRequest, "settingsScope");

		PortletPreferences portletPreferences = getPortletPreferences(
			themeDisplay, portlet.getPortletId(), settingsScope);

		actionRequest = ActionUtil.getWrappedActionRequest(
			actionRequest, portletPreferences);

		ConfigurationAction configurationAction = getConfigurationAction(
			portlet);

		if (configurationAction == null) {
			return;
		}

		configurationAction.processAction(
			portletConfig, actionRequest, actionResponse);

		Layout layout = themeDisplay.getLayout();

		PortletLayoutListener portletLayoutListener =
			portlet.getPortletLayoutListenerInstance();

		if (portletLayoutListener != null) {
			portletLayoutListener.onSetup(
				portlet.getPortletId(), layout.getPlid());
		}
	}

	public void editPublicRenderParameters(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Portlet portlet = ActionUtil.getPortlet(actionRequest);

		PortletPreferences portletPreferences =
			ActionUtil.getLayoutPortletSetup(actionRequest, portlet);

		actionRequest = ActionUtil.getWrappedActionRequest(
			actionRequest, portletPreferences);

		Enumeration<String> enu = portletPreferences.getNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			if (name.startsWith(
					PublicRenderParameterConfiguration.IGNORE_PREFIX) ||
				name.startsWith(
					PublicRenderParameterConfiguration.MAPPING_PREFIX)) {

				portletPreferences.reset(name);
			}
		}

		for (PublicRenderParameter publicRenderParameter :
				portlet.getPublicRenderParameters()) {

			String ignoreKey = PublicRenderParameterConfiguration.getIgnoreKey(
				publicRenderParameter);

			boolean ignoreValue = ParamUtil.getBoolean(
				actionRequest, ignoreKey);

			if (ignoreValue) {
				portletPreferences.setValue(
					ignoreKey, String.valueOf(Boolean.TRUE));
			}
			else {
				String mappingKey =
					PublicRenderParameterConfiguration.getMappingKey(
						publicRenderParameter);

				String mappingValue = ParamUtil.getString(
					actionRequest, mappingKey);

				if (Validator.isNotNull(mappingValue)) {
					portletPreferences.setValue(mappingKey, mappingValue);
				}
			}
		}

		if (SessionErrors.isEmpty(actionRequest)) {
			portletPreferences.store();
		}
	}

	public void editScope(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Portlet portlet = ActionUtil.getPortlet(actionRequest);

		PortletPreferences portletPreferences =
			ActionUtil.getLayoutPortletSetup(actionRequest, portlet);

		actionRequest = ActionUtil.getWrappedActionRequest(
			actionRequest, portletPreferences);

		updateScope(actionRequest, portlet);

		if (!SessionErrors.isEmpty(actionRequest)) {
			return;
		}

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		SessionMessages.add(
			actionRequest,
			PortalUtil.getPortletId(actionRequest) +
				SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
			portletResource);

		SessionMessages.add(
			actionRequest,
			PortalUtil.getPortletId(actionRequest) +
				SessionMessages.KEY_SUFFIX_UPDATED_CONFIGURATION);
	}

	public void editSharing(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Portlet portlet = ActionUtil.getPortlet(actionRequest);

		PortletPreferences portletPreferences =
			ActionUtil.getLayoutPortletSetup(actionRequest, portlet);

		actionRequest = ActionUtil.getWrappedActionRequest(
			actionRequest, portletPreferences);

		updateAnyWebsite(actionRequest, portletPreferences);
		updateFacebook(actionRequest, portletPreferences);
		updateFriends(actionRequest, portletPreferences);
		updateGoogleGadget(actionRequest, portletPreferences);
		updateNetvibes(actionRequest, portletPreferences);

		portletPreferences.store();

		if (!SessionErrors.isEmpty(actionRequest)) {
			return;
		}

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		SessionMessages.add(
			actionRequest,
			PortalUtil.getPortletId(actionRequest) +
				SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
			portletResource);

		SessionMessages.add(
			actionRequest,
			PortalUtil.getPortletId(actionRequest) +
				SessionMessages.KEY_SUFFIX_UPDATED_CONFIGURATION);
	}

	public void editSupportedClients(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Portlet portlet = ActionUtil.getPortlet(actionRequest);

		PortletPreferences portletPreferences = actionRequest.getPreferences();

		actionRequest = ActionUtil.getWrappedActionRequest(
			actionRequest, portletPreferences);

		Set<String> allPortletModes = portlet.getAllPortletModes();

		for (String portletMode : allPortletModes) {
			String mobileDevicesParam =
				"portletSetupSupportedClientsMobileDevices_" + portletMode;

			boolean mobileDevices = ParamUtil.getBoolean(
				actionRequest, mobileDevicesParam);

			portletPreferences.setValue(
				mobileDevicesParam, String.valueOf(mobileDevices));
		}

		portletPreferences.store();
	}

	@Override
	public void init(PortletConfig portletConfig) throws PortletException {
		if (portletConfig instanceof PortletConfigImpl) {
			PortletConfigurationPortletPortletConfig
				portletConfigurationPortletPortletConfig =
					new PortletConfigurationPortletPortletConfig(
						(PortletConfigImpl)portletConfig);

			super.init(portletConfigurationPortletPortletConfig);
		}
		else {
			super.init(portletConfig);
		}
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		_portletRequestThreadLocal.set(actionRequest);

		actionRequest.setAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG, getPortletConfig());

		super.processAction(actionRequest, actionResponse);
	}

	@Override
	public void processEvent(
			EventRequest eventRequest, EventResponse eventResponse)
		throws IOException, PortletException {

		_portletRequestThreadLocal.set(eventRequest);

		eventRequest.setAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG, getPortletConfig());

		super.processEvent(eventRequest, eventResponse);
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		_portletRequestThreadLocal.set(renderRequest);

		renderRequest.setAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG, getPortletConfig());

		super.render(renderRequest, renderResponse);
	}

	public void restoreArchivedSetup(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Portlet portlet = ActionUtil.getPortlet(actionRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Settings portletInstanceSettings = SettingsFactoryUtil.getSettings(
			new PortletInstanceSettingsLocator(
				themeDisplay.getLayout(), portlet.getPortletId()));

		ModifiableSettings portletInstanceModifiableSettings =
			portletInstanceSettings.getModifiableSettings();

		String name = ParamUtil.getString(actionRequest, "name");

		ArchivedSettings archivedSettings =
			SettingsFactoryUtil.getPortletInstanceArchivedSettings(
				themeDisplay.getSiteGroupId(), portlet.getRootPortletId(),
				name);

		portletInstanceModifiableSettings.reset();

		portletInstanceModifiableSettings.setValues(archivedSettings);

		portletInstanceModifiableSettings.store();

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		SessionMessages.add(
			actionRequest,
			PortalUtil.getPortletId(actionRequest) +
				SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
			portletResource);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		_portletRequestThreadLocal.set(resourceRequest);

		resourceRequest.setAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG, getPortletConfig());

		super.serveResource(resourceRequest, resourceResponse);
	}

	public void updateAnyWebsite(
			ActionRequest actionRequest, PortletPreferences portletPreferences)
		throws Exception {

		boolean widgetShowAddAppLink = ParamUtil.getBoolean(
			actionRequest, "widgetShowAddAppLink");

		portletPreferences.setValue(
			"lfrWidgetShowAddAppLink", String.valueOf(widgetShowAddAppLink));
	}

	public void updateArchivedSetup(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Portlet portlet = ActionUtil.getPortlet(actionRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String name = ParamUtil.getString(actionRequest, "name");

		ArchivedSettings archivedSettings =
			SettingsFactoryUtil.getPortletInstanceArchivedSettings(
				themeDisplay.getSiteGroupId(), portlet.getRootPortletId(),
				name);

		Settings portletInstanceSettings = SettingsFactoryUtil.getSettings(
			new PortletInstanceSettingsLocator(
				themeDisplay.getLayout(), portlet.getPortletId()));

		ModifiableSettings portletInstanceModifiableSettings =
			portletInstanceSettings.getModifiableSettings();

		archivedSettings.setValues(portletInstanceModifiableSettings);

		archivedSettings.store();
	}

	public void updateRolePermissions(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");
		String modelResource = ParamUtil.getString(
			actionRequest, "modelResource");
		long[] roleIds = StringUtil.split(
			ParamUtil.getString(
				actionRequest, "rolesSearchContainerPrimaryKeys"),
			0L);

		String selResource = PortletConstants.getRootPortletId(portletResource);

		if (Validator.isNotNull(modelResource)) {
			selResource = modelResource;
		}

		long resourceGroupId = ParamUtil.getLong(
			actionRequest, "resourceGroupId", themeDisplay.getScopeGroupId());
		String resourcePrimKey = ParamUtil.getString(
			actionRequest, "resourcePrimKey");

		Map<Long, String[]> roleIdsToActionIds = new HashMap<>();

		if (_resourceBlockLocalService.isSupported(selResource)) {
			for (long roleId : roleIds) {
				List<String> actionIds = getActionIdsList(
					actionRequest, roleId, true);

				roleIdsToActionIds.put(
					roleId, actionIds.toArray(new String[actionIds.size()]));
			}

			_resourceBlockService.setIndividualScopePermissions(
				themeDisplay.getCompanyId(), resourceGroupId, selResource,
				GetterUtil.getLong(resourcePrimKey), roleIdsToActionIds);
		}
		else {
			for (long roleId : roleIds) {
				String[] actionIds = getActionIds(actionRequest, roleId, false);

				roleIdsToActionIds.put(roleId, actionIds);
			}

			_resourcePermissionService.setIndividualResourcePermissions(
				resourceGroupId, themeDisplay.getCompanyId(), selResource,
				resourcePrimKey, roleIdsToActionIds);
		}

		if (PropsValues.PERMISSIONS_PROPAGATION_ENABLED) {
			Portlet portlet = _portletLocalService.getPortletById(
				themeDisplay.getCompanyId(), portletResource);

			PermissionPropagator permissionPropagator =
				portlet.getPermissionPropagatorInstance();

			if (permissionPropagator != null) {
				permissionPropagator.propagateRolePermissions(
					actionRequest, modelResource, resourcePrimKey, roleIds);
			}
		}

		if (Validator.isNull(modelResource)) {

			// Force update of layout modified date. See LPS-59246.

			Portlet portlet = ActionUtil.getPortlet(actionRequest);

			PortletPreferences portletPreferences =
				ActionUtil.getLayoutPortletSetup(actionRequest, portlet);

			portletPreferences.store();
		}
	}

	protected void checkEditPermissionsJSP(PortletRequest request)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String modelResource = ParamUtil.getString(request, "modelResource");

		long resourceGroupId = ParamUtil.getLong(
			request, "resourceGroupId", themeDisplay.getScopeGroupId());

		if (Validator.isNotNull(modelResource)) {
			String resourcePrimKey = ParamUtil.getString(
				request, "resourcePrimKey");

			_permissionService.checkPermission(
				resourceGroupId, modelResource, resourcePrimKey);

			return;
		}

		String portletResource = ParamUtil.getString(
			request, "portletResource");

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		Layout layout = PortletConfigurationLayoutUtil.getLayout(themeDisplay);

		_portletPermission.check(
			permissionChecker, resourceGroupId, layout, portletResource,
			ActionKeys.PERMISSIONS);
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			String mvcPath = renderRequest.getParameter("mvcPath");

			if (mvcPath.equals("/edit_permissions.jsp")) {
				checkEditPermissionsJSP(renderRequest);

				super.doDispatch(renderRequest, renderResponse);

				return;
			}

			Portlet portlet = ActionUtil.getPortlet(renderRequest);

			if (mvcPath.endsWith("edit_configuration.jsp")) {
				ThemeDisplay themeDisplay =
					(ThemeDisplay)renderRequest.getAttribute(
						WebKeys.THEME_DISPLAY);

				String settingsScope = renderRequest.getParameter(
					"settingsScope");

				PortletPreferences portletPreferences = getPortletPreferences(
					themeDisplay, portlet.getPortletId(), settingsScope);

				renderRequest = ActionUtil.getWrappedRenderRequest(
					renderRequest, portletPreferences);

				renderEditConfiguration(renderRequest, portlet);
			}
			else {
				PortletPreferences portletPreferences =
					ActionUtil.getLayoutPortletSetup(renderRequest, portlet);

				renderRequest = ActionUtil.getWrappedRenderRequest(
					renderRequest, portletPreferences);

				if (mvcPath.endsWith("edit_public_render_parameters.jsp")) {
					renderEditPublicParameters(renderRequest, portlet);
				}
			}

			renderResponse.setTitle(
				ActionUtil.getTitle(portlet, renderRequest));

			super.doDispatch(renderRequest, renderResponse);
		}
		catch (Exception e) {
			_log.error(e.getMessage());

			SessionErrors.add(renderRequest, e.getClass());

			include("/error.jsp", renderRequest, renderResponse);
		}
	}

	protected String[] getActionIds(
		ActionRequest actionRequest, long roleId, boolean includePreselected) {

		List<String> actionIds = getActionIdsList(
			actionRequest, roleId, includePreselected);

		return actionIds.toArray(new String[actionIds.size()]);
	}

	protected List<String> getActionIdsList(
		ActionRequest actionRequest, long roleId, boolean includePreselected) {

		List<String> actionIds = new ArrayList<>();

		Enumeration<String> enu = actionRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			if (name.startsWith(roleId + ActionUtil.ACTION)) {
				int pos = name.indexOf(ActionUtil.ACTION);

				String actionId = name.substring(
					pos + ActionUtil.ACTION.length());

				actionIds.add(actionId);
			}
			else if (includePreselected &&
					 name.startsWith(roleId + ActionUtil.PRESELECTED)) {

				int pos = name.indexOf(ActionUtil.PRESELECTED);

				String actionId = name.substring(
					pos + ActionUtil.PRESELECTED.length());

				actionIds.add(actionId);
			}
		}

		return actionIds;
	}

	protected ConfigurationAction getConfigurationAction(Portlet portlet)
		throws Exception {

		if (portlet == null) {
			return null;
		}

		ConfigurationAction configurationAction =
			portlet.getConfigurationActionInstance();

		if (configurationAction == null) {
			_log.error(
				"Configuration action for portlet " + portlet.getPortletId() +
					" is null");
		}

		return configurationAction;
	}

	protected Tuple getNewScope(ActionRequest actionRequest) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		String[] scopes = StringUtil.split(
			ParamUtil.getString(actionRequest, "scope"));

		String scopeType = scopes[0];

		long scopeGroupId = 0;
		String scopeName = null;

		if (Validator.isNull(scopeType)) {
			scopeGroupId = layout.getGroupId();
		}
		else if (scopeType.equals("company")) {
			scopeGroupId = themeDisplay.getCompanyGroupId();
			scopeName = themeDisplay.translate("global");
		}
		else if (scopeType.equals("layout")) {
			String scopeLayoutUuid = scopes[1];

			Layout scopeLayout = _layoutLocalService.getLayoutByUuidAndGroupId(
				scopeLayoutUuid, layout.getGroupId(), layout.isPrivateLayout());

			if (!scopeLayout.hasScopeGroup()) {
				Map<Locale, String> nameMap = new HashMap<>();

				String name = String.valueOf(scopeLayout.getPlid());

				nameMap.put(LocaleUtil.getDefault(), name);

				_groupLocalService.addGroup(
					themeDisplay.getUserId(),
					GroupConstants.DEFAULT_PARENT_GROUP_ID,
					Layout.class.getName(), scopeLayout.getPlid(),
					GroupConstants.DEFAULT_LIVE_GROUP_ID, nameMap, null, 0,
					true, GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION, null,
					false, true, null);
			}

			scopeGroupId = scopeLayout.getGroupId();
			scopeName = scopeLayout.getName(themeDisplay.getLocale());
		}
		else {
			throw new IllegalArgumentException(
				"Scope type " + scopeType + " is invalid");
		}

		return new Tuple(scopeGroupId, scopeName);
	}

	protected String getOldScopeName(
			ActionRequest actionRequest, Portlet portlet)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		PortletPreferences portletPreferences = actionRequest.getPreferences();

		String scopeType = GetterUtil.getString(
			portletPreferences.getValue("lfrScopeType", null));

		if (Validator.isNull(scopeType)) {
			return null;
		}

		String scopeName = null;

		if (scopeType.equals("company")) {
			scopeName = themeDisplay.translate("global");
		}
		else if (scopeType.equals("layout")) {
			String scopeLayoutUuid = GetterUtil.getString(
				portletPreferences.getValue("lfrScopeLayoutUuid", null));

			Layout scopeLayout =
				_layoutLocalService.fetchLayoutByUuidAndGroupId(
					scopeLayoutUuid, layout.getGroupId(),
					layout.isPrivateLayout());

			if (scopeLayout != null) {
				scopeName = scopeLayout.getName(themeDisplay.getLocale());
			}
		}
		else {
			throw new IllegalArgumentException(
				"Scope type " + scopeType + " is invalid");
		}

		return scopeName;
	}

	protected PortletPreferences getPortletPreferences(
		ThemeDisplay themeDisplay, String portletId, String settingsScope) {

		Layout layout = themeDisplay.getLayout();

		if (!layout.isSupportsEmbeddedPortlets()) {
			return null;
		}

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		if (!layoutTypePortlet.isPortletEmbedded(portletId)) {
			return null;
		}

		PortletPreferencesIds portletPreferencesIds = new PortletPreferencesIds(
			themeDisplay.getCompanyId(), layout.getGroupId(),
			PortletKeys.PREFS_OWNER_TYPE_LAYOUT, PortletKeys.PREFS_PLID_SHARED,
			portletId);

		return _portletPreferencesLocalService.getPreferences(
			portletPreferencesIds);
	}

	protected String getPortletTitle(
		PortletRequest portletRequest, Portlet portlet,
		PortletPreferences portletPreferences) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String portletTitle = PortletConfigurationUtil.getPortletTitle(
			portletPreferences, themeDisplay.getLanguageId());

		if (Validator.isNull(portletTitle)) {
			ServletContext servletContext =
				(ServletContext)portletRequest.getAttribute(WebKeys.CTX);

			portletTitle = PortalUtil.getPortletTitle(
				portlet, servletContext, themeDisplay.getLocale());
		}

		return portletTitle;
	}

	protected void renderEditConfiguration(
			RenderRequest renderRequest, Portlet portlet)
		throws Exception {

		ConfigurationAction configurationAction = getConfigurationAction(
			portlet);

		if (configurationAction != null) {
			renderRequest.setAttribute(
				WebKeys.CONFIGURATION_ACTION, configurationAction);
		}
		else if (_log.isDebugEnabled()) {
			_log.debug("Configuration action is null");
		}
	}

	protected void renderEditPublicParameters(
			RenderRequest renderRequest, Portlet portlet)
		throws Exception {

		ActionUtil.getLayoutPublicRenderParameters(renderRequest);

		ActionUtil.getPublicRenderParameterConfigurationList(
			renderRequest, portlet);
	}

	@Reference(unbind = "-")
	protected void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Reference(unbind = "-")
	protected void setLayoutLocalService(
		LayoutLocalService layoutLocalService) {

		_layoutLocalService = layoutLocalService;
	}

	@Reference(unbind = "-")
	protected void setPermissionService(PermissionService permissionService) {
		_permissionService = permissionService;
	}

	@Reference(unbind = "-")
	protected void setPortletLocalService(
		PortletLocalService portletLocalService) {

		_portletLocalService = portletLocalService;
	}

	@Reference(unbind = "-")
	protected void setPortletPermission(PortletPermission portletPermission) {
		_portletPermission = portletPermission;
	}

	@Reference(unbind = "-")
	protected void setPortletPreferencesLocalService(
		PortletPreferencesLocalService portletPreferencesLocalService) {

		_portletPreferencesLocalService = portletPreferencesLocalService;
	}

	@Reference(
		target = "(&(release.bundle.symbolic.name=com.liferay.portlet.configuration.web)(release.schema.version=1.0.0))",
		unbind = "-"
	)
	protected void setRelease(Release release) {
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

	protected void updateFacebook(
			ActionRequest actionRequest, PortletPreferences portletPreferences)
		throws Exception {

		String facebookAPIKey = ParamUtil.getString(
			actionRequest, "facebookAPIKey");
		String facebookCanvasPageURL = ParamUtil.getString(
			actionRequest, "facebookCanvasPageURL");
		boolean facebookShowAddAppLink = ParamUtil.getBoolean(
			actionRequest, "facebookShowAddAppLink");

		portletPreferences.setValue("lfrFacebookApiKey", facebookAPIKey);
		portletPreferences.setValue(
			"lfrFacebookCanvasPageUrl", facebookCanvasPageURL);
		portletPreferences.setValue(
			"lfrFacebookShowAddAppLink",
			String.valueOf(facebookShowAddAppLink));
	}

	protected void updateFriends(
			ActionRequest actionRequest, PortletPreferences portletPreferences)
		throws Exception {

		boolean appShowShareWithFriendsLink = ParamUtil.getBoolean(
			actionRequest, "appShowShareWithFriendsLink");

		portletPreferences.setValue(
			"lfrAppShowShareWithFriendsLink",
			String.valueOf(appShowShareWithFriendsLink));
	}

	protected void updateGoogleGadget(
			ActionRequest actionRequest, PortletPreferences portletPreferences)
		throws Exception {

		boolean iGoogleShowAddAppLink = ParamUtil.getBoolean(
			actionRequest, "iGoogleShowAddAppLink");

		portletPreferences.setValue(
			"lfrIgoogleShowAddAppLink", String.valueOf(iGoogleShowAddAppLink));
	}

	protected void updateNetvibes(
			ActionRequest actionRequest, PortletPreferences portletPreferences)
		throws Exception {

		boolean netvibesShowAddAppLink = ParamUtil.getBoolean(
			actionRequest, "netvibesShowAddAppLink");

		portletPreferences.setValue(
			"lfrNetvibesShowAddAppLink",
			String.valueOf(netvibesShowAddAppLink));
	}

	protected void updateScope(ActionRequest actionRequest, Portlet portlet)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String oldScopeName = getOldScopeName(actionRequest, portlet);

		PortletPreferences portletPreferences = actionRequest.getPreferences();

		String[] scopes = StringUtil.split(
			ParamUtil.getString(actionRequest, "scope"));

		String scopeType = scopes[0];

		portletPreferences.setValue("lfrScopeType", scopeType);

		String scopeLayoutUuid = StringPool.BLANK;

		if ((scopes.length > 1) && scopeType.equals("layout")) {
			scopeLayoutUuid = scopes[1];
		}

		portletPreferences.setValue("lfrScopeLayoutUuid", scopeLayoutUuid);

		String portletTitle = getPortletTitle(
			actionRequest, portlet, portletPreferences);

		Tuple newScopeTuple = getNewScope(actionRequest);

		String newScopeName = (String)newScopeTuple.getObject(1);

		String newPortletTitle = PortalUtil.getNewPortletTitle(
			portletTitle, oldScopeName, newScopeName);

		if (!newPortletTitle.equals(portletTitle)) {
			portletPreferences.setValue(
				"portletSetupTitle_" + themeDisplay.getLanguageId(),
				newPortletTitle);
			portletPreferences.setValue(
				"portletSetupUseCustomTitle", Boolean.TRUE.toString());
		}

		portletPreferences.store();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortletConfigurationPortlet.class);

	private GroupLocalService _groupLocalService;
	private LayoutLocalService _layoutLocalService;
	private PermissionService _permissionService;
	private PortletLocalService _portletLocalService;
	private PortletPermission _portletPermission;
	private PortletPreferencesLocalService _portletPreferencesLocalService;
	private final ThreadLocal<PortletRequest> _portletRequestThreadLocal =
		new AutoResetThreadLocal<>("_portletRequestThreadLocal");
	private ResourceBlockLocalService _resourceBlockLocalService;
	private ResourceBlockService _resourceBlockService;
	private ResourcePermissionService _resourcePermissionService;

	private class PortletConfigurationPortletPortletConfig
		extends PortletConfigImpl {

		@Override
		public ResourceBundle getResourceBundle(Locale locale) {
			try {
				PortletRequest portletRequest =
					_portletRequestThreadLocal.get();

				long companyId = PortalUtil.getCompanyId(portletRequest);

				String portletResource = ParamUtil.getString(
					portletRequest, "portletResource");

				Portlet portlet = _portletLocalService.getPortletById(
					companyId, portletResource);

				HttpServletRequest httpServletRequest =
					PortalUtil.getHttpServletRequest(portletRequest);

				PortletConfig portletConfig = PortletConfigFactoryUtil.create(
					portlet, httpServletRequest.getServletContext());

				return new AggregateResourceBundle(
					super.getResourceBundle(locale),
					portletConfig.getResourceBundle(locale));
			}
			catch (Exception e) {
				_log.error(e, e);
			}

			return super.getResourceBundle(locale);
		}

		private PortletConfigurationPortletPortletConfig(
			PortletConfigImpl portletConfigImpl) {

			super(
				portletConfigImpl.getPortlet(),
				portletConfigImpl.getPortletContext());
		}

	}

}