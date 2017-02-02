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

package com.liferay.portal.model.impl;

import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.expando.kernel.model.CustomAttributesDisplay;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.portal.kernel.application.type.ApplicationType;
import com.liferay.portal.kernel.atom.AtomCollectionAdapter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Plugin;
import com.liferay.portal.kernel.model.PluginSetting;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletApp;
import com.liferay.portal.kernel.model.PortletConstants;
import com.liferay.portal.kernel.model.PortletFilter;
import com.liferay.portal.kernel.model.PortletInfo;
import com.liferay.portal.kernel.model.PublicRenderParameter;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.notifications.UserNotificationHandler;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.poller.PollerProcessor;
import com.liferay.portal.kernel.pop.MessageListener;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.ControlPanelEntry;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;
import com.liferay.portal.kernel.portlet.FriendlyURLMapperTracker;
import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.portlet.PortletLayoutListener;
import com.liferay.portal.kernel.portlet.PortletQNameUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.OpenSearch;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionPropagator;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.PortletPermissionUtil;
import com.liferay.portal.kernel.servlet.ServletContextUtil;
import com.liferay.portal.kernel.servlet.URLEncoder;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.webdav.WebDAVStorage;
import com.liferay.portal.kernel.workflow.WorkflowHandler;
import com.liferay.portal.kernel.xml.QName;
import com.liferay.portal.kernel.xmlrpc.Method;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.DefaultControlPanelEntryFactory;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceRegistrar;
import com.liferay.social.kernel.model.SocialActivityInterpreter;
import com.liferay.social.kernel.model.SocialRequestInterpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.portlet.PortletMode;
import javax.portlet.WindowState;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletImpl extends PortletBaseImpl {

	/**
	 * Constructs a portlet with no parameters.
	 */
	public PortletImpl() {
		this(0, null);
	}

	/**
	 * Constructs a portlet with the specified parameters.
	 */
	public PortletImpl(long companyId, String portletId) {
		setCompanyId(companyId);
		setPortletId(portletId);

		setActive(true);
		setStrutsPath(portletId);

		_assetRendererFactoryClasses = new ArrayList<>();
		_atomCollectionAdapterClasses = new ArrayList<>();
		_autopropagatedParameters = new LinkedHashSet<>();
		_customAttributesDisplayClasses = new ArrayList<>();
		_footerPortalCss = new ArrayList<>();
		_footerPortalJavaScript = new ArrayList<>();
		_footerPortletCss = new ArrayList<>();
		_footerPortletJavaScript = new ArrayList<>();
		_headerPortalCss = new ArrayList<>();
		_headerPortalJavaScript = new ArrayList<>();
		_headerPortletCss = new ArrayList<>();
		_headerPortletJavaScript = new ArrayList<>();
		_indexerClasses = new ArrayList<>();
		_initParams = new HashMap<>();
		_portletFilters = new LinkedHashMap<>();
		_portletModes = new HashMap<>();
		_roleMappers = new LinkedHashMap<>();
		_rootPortlet = this;
		_schedulerEntries = new ArrayList<>();
		_socialActivityInterpreterClasses = new ArrayList<>();
		_stagedModelDataHandlerClasses = new ArrayList<>();
		_supportedLocales = new HashSet<>();
		_trashHandlerClasses = new ArrayList<>();
		_unlinkedRoles = new HashSet<>();
		_userNotificationHandlerClasses = new ArrayList<>();
		_windowStates = new HashMap<>();
		_workflowHandlerClasses = new ArrayList<>();
	}

	/**
	 * Constructs a portlet with the specified parameters.
	 */
	public PortletImpl(
		String portletId, Portlet rootPortlet, PluginPackage pluginPackage,
		PluginSetting pluginSetting, long companyId, String icon,
		String virtualPath, String strutsPath, String parentStrutsPath,
		String portletName, String displayName, String portletClass,
		String configurationActionClass, List<String> indexerClasses,
		String openSearchClass, List<SchedulerEntry> schedulerEntries,
		String portletURLClass, String friendlyURLMapperClass,
		String friendlyURLMapping, String friendlyURLRoutes,
		String urlEncoderClass, String portletDataHandlerClass,
		List<String> stagedModelDataHandlerClasses, String templateHandlerClass,
		String portletLayoutListenerClass, String pollerProcessorClass,
		String popMessageListenerClass,
		List<String> socialActivityInterpreterClasses,
		String socialRequestInterpreterClass,
		String userNotificationDefinitions,
		List<String> userNotificationHandlerClasses, String webDAVStorageToken,
		String webDAVStorageClass, String xmlRpcMethodClass,
		String controlPanelEntryCategory, double controlPanelEntryWeight,
		String controlPanelClass, List<String> assetRendererFactoryClasses,
		List<String> atomCollectionAdapterClasses,
		List<String> customAttributesDisplayClasses,
		String permissionPropagatorClass, List<String> trashHandlerClasses,
		List<String> workflowHandlerClasses, String defaultPreferences,
		String preferencesValidator, boolean preferencesCompanyWide,
		boolean preferencesUniquePerLayout, boolean preferencesOwnedByGroup,
		boolean useDefaultTemplate, boolean showPortletAccessDenied,
		boolean showPortletInactive, boolean actionURLRedirect,
		boolean restoreCurrentView, boolean maximizeEdit, boolean maximizeHelp,
		boolean popUpPrint, boolean layoutCacheable, boolean instanceable,
		boolean remoteable, boolean scopeable, boolean singlePageApplication,
		String userPrincipalStrategy, boolean privateRequestAttributes,
		boolean privateSessionAttributes, Set<String> autopropagatedParameters,
		boolean requiresNamespacedParameters, int actionTimeout,
		int renderTimeout, int renderWeight, boolean ajaxable,
		List<String> headerPortalCss, List<String> headerPortletCss,
		List<String> headerPortalJavaScript,
		List<String> headerPortletJavaScript, List<String> footerPortalCss,
		List<String> footerPortletCss, List<String> footerPortalJavaScript,
		List<String> footerPortletJavaScript, String cssClassWrapper,
		String facebookIntegration, boolean addDefaultResource, String roles,
		Set<String> unlinkedRoles, Map<String, String> roleMappers,
		boolean system, boolean active, boolean include,
		Map<String, String> initParams, Integer expCache,
		Map<String, Set<String>> portletModes,
		Map<String, Set<String>> windowStates, Set<String> supportedLocales,
		String resourceBundle, PortletInfo portletInfo,
		Map<String, PortletFilter> portletFilters, Set<QName> processingEvents,
		Set<QName> publishingEvents,
		Set<PublicRenderParameter> publicRenderParameters,
		PortletApp portletApp) {

		setPortletId(portletId);
		setCompanyId(companyId);
		setRoles(roles);
		setActive(active);
		setProcessingEvents(processingEvents);
		setPublishingEvents(publishingEvents);
		setPublicRenderParameters(publicRenderParameters);

		_rootPortlet = rootPortlet;
		_pluginPackage = pluginPackage;
		_defaultPluginSetting = pluginSetting;
		_icon = icon;
		_virtualPath = virtualPath;
		_strutsPath = strutsPath;
		_parentStrutsPath = parentStrutsPath;
		_portletName = portletName;
		_displayName = displayName;
		_portletClass = portletClass;
		_configurationActionClass = configurationActionClass;
		_indexerClasses = indexerClasses;
		_openSearchClass = openSearchClass;
		_schedulerEntries = schedulerEntries;
		_portletURLClass = portletURLClass;
		_friendlyURLMapperClass = friendlyURLMapperClass;
		_friendlyURLMapping = friendlyURLMapping;
		_friendlyURLRoutes = friendlyURLRoutes;
		_urlEncoderClass = urlEncoderClass;
		_portletDataHandlerClass = portletDataHandlerClass;
		_stagedModelDataHandlerClasses = stagedModelDataHandlerClasses;
		_templateHandlerClass = templateHandlerClass;
		_portletLayoutListenerClass = portletLayoutListenerClass;
		_pollerProcessorClass = pollerProcessorClass;
		_popMessageListenerClass = popMessageListenerClass;
		_socialActivityInterpreterClasses = socialActivityInterpreterClasses;
		_socialRequestInterpreterClass = socialRequestInterpreterClass;
		_userNotificationDefinitions = userNotificationDefinitions;
		_userNotificationHandlerClasses = userNotificationHandlerClasses;
		_webDAVStorageToken = webDAVStorageToken;
		_webDAVStorageClass = webDAVStorageClass;
		_xmlRpcMethodClass = xmlRpcMethodClass;
		_controlPanelEntryCategory = controlPanelEntryCategory;
		_controlPanelEntryWeight = controlPanelEntryWeight;
		_controlPanelEntryClass = controlPanelClass;
		_assetRendererFactoryClasses = assetRendererFactoryClasses;
		_atomCollectionAdapterClasses = atomCollectionAdapterClasses;
		_customAttributesDisplayClasses = customAttributesDisplayClasses;
		_permissionPropagatorClass = permissionPropagatorClass;
		_trashHandlerClasses = trashHandlerClasses;
		_workflowHandlerClasses = workflowHandlerClasses;
		_defaultPreferences = defaultPreferences;
		_preferencesValidator = preferencesValidator;
		_preferencesCompanyWide = preferencesCompanyWide;
		_preferencesUniquePerLayout = preferencesUniquePerLayout;
		_preferencesOwnedByGroup = preferencesOwnedByGroup;
		_useDefaultTemplate = useDefaultTemplate;
		_showPortletAccessDenied = showPortletAccessDenied;
		_showPortletInactive = showPortletInactive;
		_actionURLRedirect = actionURLRedirect;
		_restoreCurrentView = restoreCurrentView;
		_maximizeEdit = maximizeEdit;
		_maximizeHelp = maximizeHelp;
		_popUpPrint = popUpPrint;
		_layoutCacheable = layoutCacheable;
		_instanceable = instanceable;
		_remoteable = remoteable;
		_scopeable = scopeable;
		_singlePageApplication = singlePageApplication;
		_userPrincipalStrategy = userPrincipalStrategy;
		_privateRequestAttributes = privateRequestAttributes;
		_privateSessionAttributes = privateSessionAttributes;
		_autopropagatedParameters = autopropagatedParameters;
		_requiresNamespacedParameters = requiresNamespacedParameters;
		_actionTimeout = actionTimeout;
		_renderTimeout = renderTimeout;
		_renderWeight = renderWeight;
		_ajaxable = ajaxable;
		_headerPortalCss = headerPortalCss;
		_headerPortletCss = headerPortletCss;
		_headerPortalJavaScript = headerPortalJavaScript;
		_headerPortletJavaScript = headerPortletJavaScript;
		_footerPortalCss = footerPortalCss;
		_footerPortletCss = footerPortletCss;
		_footerPortalJavaScript = footerPortalJavaScript;
		_footerPortletJavaScript = footerPortletJavaScript;
		_cssClassWrapper = cssClassWrapper;
		_facebookIntegration = facebookIntegration;
		_addDefaultResource = addDefaultResource;
		_unlinkedRoles = unlinkedRoles;
		_roleMappers = roleMappers;
		_system = system;
		_include = include;
		_initParams = initParams;
		_expCache = expCache;
		_portletModes = portletModes;
		_windowStates = windowStates;
		_supportedLocales = supportedLocales;
		_resourceBundle = resourceBundle;
		_portletInfo = portletInfo;
		_portletFilters = portletFilters;
		_portletApp = portletApp;
	}

	/**
	 * Adds an application type.
	 *
	 * @param applicationType an application type
	 */
	@Override
	public void addApplicationType(ApplicationType applicationType) {
		_applicationTypes.add(applicationType);
	}

	/**
	 * Adds a processing event.
	 */
	@Override
	public void addProcessingEvent(QName processingEvent) {
		_processingEvents.add(processingEvent);
		_processingEventsByQName.put(
			PortletQNameUtil.getKey(processingEvent), processingEvent);
	}

	/**
	 * Adds a public render parameter.
	 *
	 * @param publicRenderParameter a public render parameter
	 */
	@Override
	public void addPublicRenderParameter(
		PublicRenderParameter publicRenderParameter) {

		_publicRenderParameters.add(publicRenderParameter);

		String identifier = publicRenderParameter.getIdentifier();

		_publicRenderParametersByIdentifier.put(
			identifier, publicRenderParameter);

		QName qName = publicRenderParameter.getQName();

		_publicRenderParametersByQName.put(
			PortletQNameUtil.getKey(qName), publicRenderParameter);

		String publicRenderParameterName =
			PortletQNameUtil.getPublicRenderParameterName(qName);

		PortletQNameUtil.setPublicRenderParameterIdentifier(
			publicRenderParameterName, identifier);
	}

	/**
	 * Adds a publishing event.
	 */
	@Override
	public void addPublishingEvent(QName publishingEvent) {
		_publishingEvents.add(publishingEvent);
	}

	/**
	 * Adds a scheduler entry.
	 */
	@Override
	public void addSchedulerEntry(SchedulerEntry schedulerEntry) {
		_schedulerEntries.add(schedulerEntry);
	}

	/**
	 * Creates and returns a copy of this object.
	 *
	 * @return a copy of this object
	 */
	@Override
	public Object clone() {
		Portlet portlet = new PortletImpl(
			getPortletId(), getRootPortlet(), getPluginPackage(),
			getDefaultPluginSetting(), getCompanyId(), getIcon(),
			getVirtualPath(), getStrutsPath(), getParentStrutsPath(),
			getPortletName(), getDisplayName(), getPortletClass(),
			getConfigurationActionClass(), getIndexerClasses(),
			getOpenSearchClass(), getSchedulerEntries(), getPortletURLClass(),
			getFriendlyURLMapperClass(), getFriendlyURLMapping(),
			getFriendlyURLRoutes(), getURLEncoderClass(),
			getPortletDataHandlerClass(), getStagedModelDataHandlerClasses(),
			getTemplateHandlerClass(), getPortletLayoutListenerClass(),
			getPollerProcessorClass(), getPopMessageListenerClass(),
			getSocialActivityInterpreterClasses(),
			getSocialRequestInterpreterClass(),
			getUserNotificationDefinitions(),
			getUserNotificationHandlerClasses(), getWebDAVStorageToken(),
			getWebDAVStorageClass(), getXmlRpcMethodClass(),
			getControlPanelEntryCategory(), getControlPanelEntryWeight(),
			getControlPanelEntryClass(), getAssetRendererFactoryClasses(),
			getAtomCollectionAdapterClasses(),
			getCustomAttributesDisplayClasses(), getPermissionPropagatorClass(),
			getTrashHandlerClasses(), getWorkflowHandlerClasses(),
			getDefaultPreferences(), getPreferencesValidator(),
			isPreferencesCompanyWide(), isPreferencesUniquePerLayout(),
			isPreferencesOwnedByGroup(), isUseDefaultTemplate(),
			isShowPortletAccessDenied(), isShowPortletInactive(),
			isActionURLRedirect(), isRestoreCurrentView(), isMaximizeEdit(),
			isMaximizeHelp(), isPopUpPrint(), isLayoutCacheable(),
			isInstanceable(), isRemoteable(), isScopeable(),
			isSinglePageApplication(), getUserPrincipalStrategy(),
			isPrivateRequestAttributes(), isPrivateSessionAttributes(),
			getAutopropagatedParameters(), isRequiresNamespacedParameters(),
			getActionTimeout(), getRenderTimeout(), getRenderWeight(),
			isAjaxable(), getHeaderPortalCss(), getHeaderPortletCss(),
			getHeaderPortalJavaScript(), getHeaderPortletJavaScript(),
			getFooterPortalCss(), getFooterPortletCss(),
			getFooterPortalJavaScript(), getFooterPortletJavaScript(),
			getCssClassWrapper(), getFacebookIntegration(),
			isAddDefaultResource(), getRoles(), getUnlinkedRoles(),
			getRoleMappers(), isSystem(), isActive(), isInclude(),
			getInitParams(), getExpCache(), getPortletModes(),
			getWindowStates(), getSupportedLocales(), getResourceBundle(),
			getPortletInfo(), getPortletFilters(), getProcessingEvents(),
			getPublishingEvents(), getPublicRenderParameters(),
			getPortletApp());

		portlet.setApplicationTypes(getApplicationTypes());
		portlet.setId(getId());
		portlet.setUndeployedPortlet(isUndeployedPortlet());

		return portlet;
	}

	/**
	 * Compares this portlet to the specified object.
	 *
	 * @param  portlet the portlet to compare this portlet against
	 * @return the value 0 if the argument portlet is equal to this portlet; a
	 *         value less than -1 if this portlet is less than the portlet
	 *         argument; and 1 if this portlet is greater than the portlet
	 *         argument
	 */
	@Override
	public int compareTo(Portlet portlet) {
		String portletId = getPortletId();

		return portletId.compareTo(portlet.getPortletId());
	}

	/**
	 * Checks whether this portlet is equal to the specified object.
	 *
	 * @param  obj the object to compare this portlet against
	 * @return <code>true</code> if the portlet is equal to the specified object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Portlet)) {
			return false;
		}

		Portlet portlet = (Portlet)obj;

		String portletId = getPortletId();

		return portletId.equals(portlet.getPortletId());
	}

	/**
	 * Returns the action timeout of the portlet.
	 *
	 * @return the action timeout of the portlet
	 */
	@Override
	public int getActionTimeout() {
		return _actionTimeout;
	}

	/**
	 * Returns <code>true</code> if an action URL for this portlet should cause
	 * an auto redirect.
	 *
	 * @return <code>true</code> if an action URL for this portlet should cause
	 *         an auto redirect
	 */
	@Override
	public boolean getActionURLRedirect() {
		return _actionURLRedirect;
	}

	/**
	 * Returns <code>true</code> if default resources for the portlet are added
	 * to a page.
	 *
	 * @return <code>true</code> if default resources for the portlet are added
	 *         to a page
	 */
	@Override
	public boolean getAddDefaultResource() {
		return _addDefaultResource;
	}

	/**
	 * Returns <code>true</code> if the portlet can be displayed via Ajax.
	 *
	 * @return <code>true</code> if the portlet can be displayed via Ajax
	 */
	@Override
	public boolean getAjaxable() {
		return _ajaxable;
	}

	/**
	 * Returns the portlet modes of the portlet.
	 *
	 * @return the portlet modes of the portlet
	 */
	@Override
	public Set<String> getAllPortletModes() {
		Set<String> allPortletModes = new TreeSet<>();

		for (Map.Entry<String, Set<String>> entry : _portletModes.entrySet()) {
			Set<String> mimeTypePortletModes = entry.getValue();

			for (String portletMode : mimeTypePortletModes) {
				allPortletModes.add(portletMode);
			}
		}

		return allPortletModes;
	}

	/**
	 * Returns the window states of the portlet.
	 *
	 * @return the window states of the portlet
	 */
	@Override
	public Set<String> getAllWindowStates() {
		Set<String> allWindowStates = new TreeSet<>();

		for (Map.Entry<String, Set<String>> entry : _windowStates.entrySet()) {
			Set<String> mimeTypeWindowStates = entry.getValue();

			for (String windowState : mimeTypeWindowStates) {
				allWindowStates.add(windowState);
			}
		}

		return allWindowStates;
	}

	/**
	 * Returns the application types of the portlet.
	 *
	 * @return the application types of the portlet
	 */
	@Override
	public Set<ApplicationType> getApplicationTypes() {
		return _applicationTypes;
	}

	/**
	 * Returns the names of the classes that represent asset types associated
	 * with the portlet.
	 *
	 * @return the names of the classes that represent asset types associated
	 *         with the portlet
	 */
	@Override
	public List<String> getAssetRendererFactoryClasses() {
		return _assetRendererFactoryClasses;
	}

	/**
	 * Returns the asset type instances of the portlet.
	 *
	 * @return the asset type instances of the portlet
	 */
	@Override
	public List<AssetRendererFactory<?>> getAssetRendererFactoryInstances() {
		if (_assetRendererFactoryClasses.isEmpty()) {
			return null;
		}

		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		return portletBag.getAssetRendererFactoryInstances();
	}

	/**
	 * Returns the names of the classes that represent atom collection adapters
	 * associated with the portlet.
	 *
	 * @return the names of the classes that represent atom collection adapters
	 *         associated with the portlet
	 */
	@Override
	public List<String> getAtomCollectionAdapterClasses() {
		return _atomCollectionAdapterClasses;
	}

	/**
	 * Returns the atom collection adapter instances of the portlet.
	 *
	 * @return the atom collection adapter instances of the portlet
	 */
	@Override
	public List<AtomCollectionAdapter<?>> getAtomCollectionAdapterInstances() {
		if (_atomCollectionAdapterClasses.isEmpty()) {
			return null;
		}

		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		return portletBag.getAtomCollectionAdapterInstances();
	}

	/**
	 * Returns the names of the parameters that will be automatically propagated
	 * through the portlet.
	 *
	 * @return the names of the parameters that will be automatically propagated
	 *         through the portlet
	 */
	@Override
	public Set<String> getAutopropagatedParameters() {
		return _autopropagatedParameters;
	}

	/**
	 * Returns <code>true</code> if the portlet is found in a WAR file.
	 *
	 * @param  portletId the cloned instance portlet ID
	 * @return a cloned instance of the portlet
	 */
	@Override
	public Portlet getClonedInstance(String portletId) {
		Portlet portlet = (Portlet)clone();

		portlet.setPortletId(portletId);

		return portlet;
	}

	/**
	 * Returns the configuration action class of the portlet.
	 *
	 * @return the configuration action class of the portlet
	 */
	@Override
	public String getConfigurationActionClass() {
		return _configurationActionClass;
	}

	/**
	 * Returns the configuration action instance of the portlet.
	 *
	 * @return the configuration action instance of the portlet
	 */
	@Override
	public ConfigurationAction getConfigurationActionInstance() {
		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		List<ConfigurationAction> configurationActionInstances =
			portletBag.getConfigurationActionInstances();

		if (configurationActionInstances.isEmpty()) {
			return null;
		}

		return configurationActionInstances.get(0);
	}

	/**
	 * Returns the servlet context name of the portlet.
	 *
	 * @return the servlet context name of the portlet
	 */
	@Override
	public String getContextName() {
		if (!_portletApp.isWARFile()) {
			return PortalUtil.getServletContextName();
		}

		return _portletApp.getServletContextName();
	}

	/**
	 * Returns the servlet context path of the portlet.
	 *
	 * @return the servlet context path of the portlet
	 */
	@Override
	public String getContextPath() {
		return _portletApp.getContextPath();
	}

	/**
	 * Returns the name of the category of the Control Panel where the portlet
	 * will be shown.
	 *
	 * @return the name of the category of the Control Panel where the portlet
	 *         will be shown
	 */
	@Override
	public String getControlPanelEntryCategory() {
		return _controlPanelEntryCategory;
	}

	/**
	 * Returns the name of the class that will control when the portlet will be
	 * shown in the Control Panel.
	 *
	 * @return the name of the class that will control when the portlet will be
	 *         shown in the Control Panel
	 */
	@Override
	public String getControlPanelEntryClass() {
		return _controlPanelEntryClass;
	}

	/**
	 * Returns an instance of the class that will control when the portlet will
	 * be shown in the Control Panel.
	 *
	 * @return the instance of the class that will control when the portlet will
	 *         be shown in the Control Panel
	 */
	@Override
	public ControlPanelEntry getControlPanelEntryInstance() {
		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		List<ControlPanelEntry> controlPanelEntryInstances =
			portletBag.getControlPanelEntryInstances();

		if (controlPanelEntryInstances.isEmpty()) {
			return DefaultControlPanelEntryFactory.getInstance();
		}

		return controlPanelEntryInstances.get(0);
	}

	/**
	 * Returns the relative weight of the portlet with respect to the other
	 * portlets in the same category of the Control Panel.
	 *
	 * @return the relative weight of the portlet with respect to the other
	 *         portlets in the same category of the Control Panel
	 */
	@Override
	public double getControlPanelEntryWeight() {
		return _controlPanelEntryWeight;
	}

	/**
	 * Returns the name of the CSS class that will be injected in the DIV that
	 * wraps this portlet.
	 *
	 * @return the name of the CSS class that will be injected in the DIV that
	 *         wraps this portlet
	 */
	@Override
	public String getCssClassWrapper() {
		return _cssClassWrapper;
	}

	/**
	 * Returns the names of the classes that represent custom attribute displays
	 * associated with the portlet.
	 *
	 * @return the names of the classes that represent asset types associated
	 *         with the portlet
	 */
	@Override
	public List<String> getCustomAttributesDisplayClasses() {
		return _customAttributesDisplayClasses;
	}

	/**
	 * Returns the custom attribute display instances of the portlet.
	 *
	 * @return the custom attribute display instances of the portlet
	 */
	@Override
	public List<CustomAttributesDisplay> getCustomAttributesDisplayInstances() {
		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		return portletBag.getCustomAttributesDisplayInstances();
	}

	/**
	 * Get the default plugin settings of the portlet.
	 *
	 * @return the plugin settings
	 */
	@Override
	public PluginSetting getDefaultPluginSetting() {
		return _defaultPluginSetting;
	}

	/**
	 * Returns the default preferences of the portlet.
	 *
	 * @return the default preferences of the portlet
	 */
	@Override
	public String getDefaultPreferences() {
		if (Validator.isNull(_defaultPreferences)) {
			return PortletConstants.DEFAULT_PREFERENCES;
		}
		else {
			return _defaultPreferences;
		}
	}

	/**
	 * Returns the display name of the portlet.
	 *
	 * @return the display name of the portlet
	 */
	@Override
	public String getDisplayName() {
		return _displayName;
	}

	/**
	 * Returns expiration cache of the portlet.
	 *
	 * @return expiration cache of the portlet
	 */
	@Override
	public Integer getExpCache() {
		return _expCache;
	}

	/**
	 * Returns the Facebook integration method of the portlet.
	 *
	 * @return the Facebook integration method of the portlet
	 */
	@Override
	public String getFacebookIntegration() {
		return _facebookIntegration;
	}

	/**
	 * Returns a list of CSS files that will be referenced from the page's
	 * footer relative to the portal's context path.
	 *
	 * @return a list of CSS files that will be referenced from the page's
	 *         footer relative to the portal's context path
	 */
	@Override
	public List<String> getFooterPortalCss() {
		return _footerPortalCss;
	}

	/**
	 * Returns a list of JavaScript files that will be referenced from the
	 * page's footer relative to the portal's context path.
	 *
	 * @return a list of JavaScript files that will be referenced from the
	 *         page's footer relative to the portal's context path
	 */
	@Override
	public List<String> getFooterPortalJavaScript() {
		return _footerPortalJavaScript;
	}

	/**
	 * Returns a list of CSS files that will be referenced from the page's
	 * footer relative to the portlet's context path.
	 *
	 * @return a list of CSS files that will be referenced from the page's
	 *         footer relative to the portlet's context path
	 */
	@Override
	public List<String> getFooterPortletCss() {
		return _footerPortletCss;
	}

	/**
	 * Returns a list of JavaScript files that will be referenced from the
	 * page's footer relative to the portlet's context path.
	 *
	 * @return a list of JavaScript files that will be referenced from the
	 *         page's footer relative to the portlet's context path
	 */
	@Override
	public List<String> getFooterPortletJavaScript() {
		return _footerPortletJavaScript;
	}

	/**
	 * Returns the name of the friendly URL mapper class of the portlet.
	 *
	 * @return the name of the friendly URL mapper class of the portlet
	 */
	@Override
	public String getFriendlyURLMapperClass() {
		return _friendlyURLMapperClass;
	}

	/**
	 * Returns the friendly URL mapper instance of the portlet.
	 *
	 * @return the friendly URL mapper instance of the portlet
	 */
	@Override
	public FriendlyURLMapper getFriendlyURLMapperInstance() {
		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		if (portletBag == null) {
			return null;
		}

		FriendlyURLMapperTracker friendlyURLMapperTracker =
			portletBag.getFriendlyURLMapperTracker();

		return friendlyURLMapperTracker.getFriendlyURLMapper();
	}

	/**
	 * Returns the name of the friendly URL mapping of the portlet.
	 *
	 * @return the name of the friendly URL mapping of the portlet
	 */
	@Override
	public String getFriendlyURLMapping() {
		if (Validator.isNotNull(_friendlyURLMapping)) {
			return _friendlyURLMapping;
		}

		FriendlyURLMapper friendlyURLMapperInstance =
			getFriendlyURLMapperInstance();

		if (friendlyURLMapperInstance == null) {
			return null;
		}

		return friendlyURLMapperInstance.getMapping();
	}

	/**
	 * Returns the class loader resource path to the friendly URL routes of the
	 * portlet.
	 *
	 * @return the class loader resource path to the friendly URL routes of the
	 *         portlet
	 */
	@Override
	public String getFriendlyURLRoutes() {
		return _friendlyURLRoutes;
	}

	/**
	 * Returns a list of CSS files that will be referenced from the page's
	 * header relative to the portal's context path.
	 *
	 * @return a list of CSS files that will be referenced from the page's
	 *         header relative to the portal's context path
	 */
	@Override
	public List<String> getHeaderPortalCss() {
		return _headerPortalCss;
	}

	/**
	 * Returns a list of JavaScript files that will be referenced from the
	 * page's header relative to the portal's context path.
	 *
	 * @return a list of JavaScript files that will be referenced from the
	 *         page's header relative to the portal's context path
	 */
	@Override
	public List<String> getHeaderPortalJavaScript() {
		return _headerPortalJavaScript;
	}

	/**
	 * Returns a list of CSS files that will be referenced from the page's
	 * header relative to the portlet's context path.
	 *
	 * @return a list of CSS files that will be referenced from the page's
	 *         header relative to the portlet's context path
	 */
	@Override
	public List<String> getHeaderPortletCss() {
		return _headerPortletCss;
	}

	/**
	 * Returns a list of JavaScript files that will be referenced from the
	 * page's header relative to the portlet's context path.
	 *
	 * @return a list of JavaScript files that will be referenced from the
	 *         page's header relative to the portlet's context path
	 */
	@Override
	public List<String> getHeaderPortletJavaScript() {
		return _headerPortletJavaScript;
	}

	/**
	 * Returns the icon of the portlet.
	 *
	 * @return the icon of the portlet
	 */
	@Override
	public String getIcon() {
		return _icon;
	}

	/**
	 * Returns <code>true</code> to include the portlet and make it available to
	 * be made active.
	 *
	 * @return <code>true</code> to include the portlet and make it available to
	 *         be made active
	 */
	@Override
	public boolean getInclude() {
		return _include;
	}

	/**
	 * Returns the names of the classes that represent indexers associated with
	 * the portlet.
	 *
	 * @return the names of the classes that represent indexers associated with
	 *         the portlet
	 */
	@Override
	public List<String> getIndexerClasses() {
		return _indexerClasses;
	}

	/**
	 * Returns the indexer instances of the portlet.
	 *
	 * @return the indexer instances of the portlet
	 */
	@Override
	public List<Indexer<?>> getIndexerInstances() {
		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		return portletBag.getIndexerInstances();
	}

	/**
	 * Returns the init parameters of the portlet.
	 *
	 * @return init parameters of the portlet
	 */
	@Override
	public Map<String, String> getInitParams() {
		return _initParams;
	}

	/**
	 * Returns <code>true</code> if the portlet can be added multiple times to a
	 * layout.
	 *
	 * @return <code>true</code> if the portlet can be added multiple times to a
	 *         layout
	 */
	@Override
	public boolean getInstanceable() {
		return _instanceable;
	}

	/**
	 * Returns the instance ID of the portlet.
	 *
	 * @return the instance ID of the portlet
	 */
	@Override
	public String getInstanceId() {
		return PortletConstants.getInstanceId(getPortletId());
	}

	/**
	 * Returns <code>true</code> to allow the portlet to be cached within the
	 * layout.
	 *
	 * @return <code>true</code> if the portlet can be cached within the layout
	 */
	@Override
	public boolean getLayoutCacheable() {
		return _layoutCacheable;
	}

	/**
	 * Returns <code>true</code> if the portlet goes into the maximized state
	 * when the user goes into the edit mode.
	 *
	 * @return <code>true</code> if the portlet goes into the maximized state
	 *         when the user goes into the edit mode
	 */
	@Override
	public boolean getMaximizeEdit() {
		return _maximizeEdit;
	}

	/**
	 * Returns <code>true</code> if the portlet goes into the maximized state
	 * when the user goes into the help mode.
	 *
	 * @return <code>true</code> if the portlet goes into the maximized state
	 *         when the user goes into the help mode
	 */
	@Override
	public boolean getMaximizeHelp() {
		return _maximizeHelp;
	}

	/**
	 * Returns the name of the open search class of the portlet.
	 *
	 * @return the name of the open search class of the portlet
	 */
	@Override
	public String getOpenSearchClass() {
		return _openSearchClass;
	}

	/**
	 * Returns the indexer instance of the portlet.
	 *
	 * @return the indexer instance of the portlet
	 */
	@Override
	public OpenSearch getOpenSearchInstance() {
		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		List<OpenSearch> openSearchInstances =
			portletBag.getOpenSearchInstances();

		if (openSearchInstances.isEmpty()) {
			return null;
		}

		return openSearchInstances.get(0);
	}

	/**
	 * Returns the parent struts path of the portlet.
	 *
	 * @return the parent struts path of the portlet.
	 */
	@Override
	public String getParentStrutsPath() {
		return _parentStrutsPath;
	}

	/**
	 * Returns the name of the permission propagator class of the portlet.
	 *
	 * @return the name of the permission propagator class of the portlet
	 */
	@Override
	public String getPermissionPropagatorClass() {
		return _permissionPropagatorClass;
	}

	/**
	 * Returns the permission propagator instance of the portlet.
	 *
	 * @return the permission propagator instance of the portlet
	 */
	@Override
	public PermissionPropagator getPermissionPropagatorInstance() {
		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		List<PermissionPropagator> permissionPropagatorInstances =
			portletBag.getPermissionPropagatorInstances();

		if (permissionPropagatorInstances.isEmpty()) {
			return null;
		}

		return permissionPropagatorInstances.get(0);
	}

	/**
	 * Returns the plugin ID of the portlet.
	 *
	 * @return the plugin ID of the portlet
	 */
	@Override
	public String getPluginId() {
		return getRootPortletId();
	}

	/**
	 * Returns this portlet's plugin package.
	 *
	 * @return this portlet's plugin package
	 */
	@Override
	public PluginPackage getPluginPackage() {
		return _pluginPackage;
	}

	/**
	 * Returns the plugin type of the portlet.
	 *
	 * @return the plugin type of the portlet
	 */
	@Override
	public String getPluginType() {
		return Plugin.TYPE_PORTLET;
	}

	/**
	 * Returns the name of the poller processor class of the portlet.
	 *
	 * @return the name of the poller processor class of the portlet
	 */
	@Override
	public String getPollerProcessorClass() {
		return _pollerProcessorClass;
	}

	/**
	 * Returns the poller processor instance of the portlet.
	 *
	 * @return the poller processor instance of the portlet
	 */
	@Override
	public PollerProcessor getPollerProcessorInstance() {
		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		List<PollerProcessor> pollerProcessorInstances =
			portletBag.getPollerProcessorInstances();

		if (pollerProcessorInstances.isEmpty()) {
			return null;
		}

		return pollerProcessorInstances.get(0);
	}

	/**
	 * Returns the name of the POP message listener class of the portlet.
	 *
	 * @return the name of the POP message listener class of the portlet
	 */
	@Override
	public String getPopMessageListenerClass() {
		return _popMessageListenerClass;
	}

	/**
	 * Returns the POP message listener instance of the portlet.
	 *
	 * @return the POP message listener instance of the portlet
	 */
	@Override
	public MessageListener getPopMessageListenerInstance() {
		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		List<MessageListener> popMessageListenerInstances =
			portletBag.getPopMessageListenerInstances();

		if (popMessageListenerInstances.isEmpty()) {
			return null;
		}

		return popMessageListenerInstances.get(0);
	}

	/**
	 * Returns <code>true</code> if the portlet goes into the pop up state when
	 * the user goes into the print mode.
	 *
	 * @return <code>true</code> if the portlet goes into the pop up state when
	 *         the user goes into the print mode
	 */
	@Override
	public boolean getPopUpPrint() {
		return _popUpPrint;
	}

	/**
	 * Returns this portlet's application.
	 *
	 * @return this portlet's application
	 */
	@Override
	public PortletApp getPortletApp() {
		return _portletApp;
	}

	/**
	 * Returns the name of the portlet class of the portlet.
	 *
	 * @return the name of the portlet class of the portlet
	 */
	@Override
	public String getPortletClass() {
		return _portletClass;
	}

	/**
	 * Returns the name of the portlet data handler class of the portlet.
	 *
	 * @return the name of the portlet data handler class of the portlet
	 */
	@Override
	public String getPortletDataHandlerClass() {
		return _portletDataHandlerClass;
	}

	/**
	 * Returns the portlet data handler instance of the portlet.
	 *
	 * @return the portlet data handler instance of the portlet
	 */
	@Override
	public PortletDataHandler getPortletDataHandlerInstance() {
		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		if (portletBag == null) {
			_log.error("No portlet bag for " + toString());

			throw new IllegalStateException("No portlet bag for " + toString());
		}

		List<PortletDataHandler> portletDataHandlerInstances =
			portletBag.getPortletDataHandlerInstances();

		if (portletDataHandlerInstances.isEmpty()) {
			return null;
		}

		return portletDataHandlerInstances.get(0);
	}

	/**
	 * Returns the filters of the portlet.
	 *
	 * @return filters of the portlet
	 */
	@Override
	public Map<String, PortletFilter> getPortletFilters() {
		return _portletFilters;
	}

	/**
	 * Returns the portlet info of the portlet.
	 *
	 * @return portlet info of the portlet
	 */
	@Override
	public PortletInfo getPortletInfo() {
		return _portletInfo;
	}

	/**
	 * Returns the name of the portlet layout listener class of the portlet.
	 *
	 * @return the name of the portlet layout listener class of the portlet
	 */
	@Override
	public String getPortletLayoutListenerClass() {
		return _portletLayoutListenerClass;
	}

	/**
	 * Returns the portlet layout listener instance of the portlet.
	 *
	 * @return the portlet layout listener instance of the portlet
	 */
	@Override
	public PortletLayoutListener getPortletLayoutListenerInstance() {
		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		if (portletBag == null) {
			return null;
		}

		List<PortletLayoutListener> portletLayoutListenerInstances =
			portletBag.getPortletLayoutListenerInstances();

		if (portletLayoutListenerInstances.isEmpty()) {
			return null;
		}

		return portletLayoutListenerInstances.get(0);
	}

	/**
	 * Returns the portlet modes of the portlet.
	 *
	 * @return portlet modes of the portlet
	 */
	@Override
	public Map<String, Set<String>> getPortletModes() {
		return _portletModes;
	}

	/**
	 * Returns the name of the portlet.
	 *
	 * @return the display name of the portlet
	 */
	@Override
	public String getPortletName() {
		return _portletName;
	}

	/**
	 * Returns the name of the portlet URL class of the portlet.
	 *
	 * @return the name of the portlet URL class of the portlet
	 */
	@Override
	public String getPortletURLClass() {
		return _portletURLClass;
	}

	/**
	 * Returns <code>true</code> if preferences are shared across the entire
	 * company.
	 *
	 * @return <code>true</code> if preferences are shared across the entire
	 *         company
	 */
	@Override
	public boolean getPreferencesCompanyWide() {
		return _preferencesCompanyWide;
	}

	/**
	 * Returns <code>true</code> if preferences are owned by the group when the
	 * portlet is shown in a group layout. Returns <code>false</code> if
	 * preferences are owned by the user at all times.
	 *
	 * @return <code>true</code> if preferences are owned by the group when the
	 *         portlet is shown in a group layout; <code>false</code> if
	 *         preferences are owned by the user at all times.
	 */
	@Override
	public boolean getPreferencesOwnedByGroup() {
		return _preferencesOwnedByGroup;
	}

	/**
	 * Returns <code>true</code> if preferences are unique per layout.
	 *
	 * @return <code>true</code> if preferences are unique per layout
	 */
	@Override
	public boolean getPreferencesUniquePerLayout() {
		return _preferencesUniquePerLayout;
	}

	/**
	 * Returns the name of the preferences validator class of the portlet.
	 *
	 * @return the name of the preferences validator class of the portlet
	 */
	@Override
	public String getPreferencesValidator() {
		return _preferencesValidator;
	}

	/**
	 * Returns <code>true</code> if the portlet does not share request
	 * attributes with the portal or portlets from another WAR.
	 *
	 * @return <code>true</code> if the portlet does not share request
	 *         attributes with the portal or portlets from another WAR
	 */
	@Override
	public boolean getPrivateRequestAttributes() {
		return _privateRequestAttributes;
	}

	/**
	 * Returns <code>true</code> if the portlet does not share session
	 * attributes with the portal.
	 *
	 * @return <code>true</code> if the portlet does not share session
	 *         attributes with the portal
	 */
	@Override
	public boolean getPrivateSessionAttributes() {
		return _privateSessionAttributes;
	}

	/**
	 * Returns the processing event from a namespace URI and a local part.
	 *
	 * @param  uri the namespace URI
	 * @param  localPart the local part
	 * @return the processing event from a namespace URI and a local part
	 */
	@Override
	public QName getProcessingEvent(String uri, String localPart) {
		return _processingEventsByQName.get(
			PortletQNameUtil.getKey(uri, localPart));
	}

	/**
	 * Returns the processing events of the portlet.
	 *
	 * @return the processing events of the portlet
	 */
	@Override
	public Set<QName> getProcessingEvents() {
		return _processingEvents;
	}

	/**
	 * Returns the public render parameter from an identifier.
	 *
	 * @param  identifier the identifier
	 * @return the public render parameter from an identifier
	 */
	@Override
	public PublicRenderParameter getPublicRenderParameter(String identifier) {
		return _publicRenderParametersByIdentifier.get(identifier);
	}

	/**
	 * Returns the spublic render parameter from a namespace URI and a local
	 * part.
	 *
	 * @param  uri the namespace URI
	 * @param  localPart the local part
	 * @return the spublic render parameter from a namespace URI and a local
	 *         part
	 */
	@Override
	public PublicRenderParameter getPublicRenderParameter(
		String uri, String localPart) {

		return _publicRenderParametersByQName.get(
			PortletQNameUtil.getKey(uri, localPart));
	}

	/**
	 * Returns the public render parameters of the portlet.
	 *
	 * @return the public render parameters of the portlet
	 */
	@Override
	public Set<PublicRenderParameter> getPublicRenderParameters() {
		return _publicRenderParameters;
	}

	/**
	 * Returns the publishing events of the portlet.
	 *
	 * @return the publishing events of the portlet
	 */
	@Override
	public Set<QName> getPublishingEvents() {
		return _publishingEvents;
	}

	/**
	 * Returns <code>true</code> if the portlet is ready to be used.
	 *
	 * @return <code>true</code> if the portlet is ready to be used
	 */
	@Override
	public boolean getReady() {
		return isReady();
	}

	/**
	 * Returns <code>true</code> if the portlet supports remoting.
	 *
	 * @return <code>true</code> if the portlet supports remoting
	 */
	@Override
	public boolean getRemoteable() {
		return _remoteable;
	}

	/**
	 * Returns the render timeout of the portlet.
	 *
	 * @return the render timeout of the portlet
	 */
	@Override
	public int getRenderTimeout() {
		return _renderTimeout;
	}

	/**
	 * Returns the render weight of the portlet.
	 *
	 * @return the render weight of the portlet
	 */
	@Override
	public int getRenderWeight() {
		return _renderWeight;
	}

	/**
	 * Returns the resource bundle of the portlet.
	 *
	 * @return resource bundle of the portlet
	 */
	@Override
	public String getResourceBundle() {
		return _resourceBundle;
	}

	/**
	 * Returns <code>true</code> if the portlet restores to the current view
	 * from the maximized state.
	 *
	 * @return <code>true</code> if the portlet restores to the current view
	 *         from the maximized state
	 */
	@Override
	public boolean getRestoreCurrentView() {
		return _restoreCurrentView;
	}

	/**
	 * Returns the role mappers of the portlet.
	 *
	 * @return role mappers of the portlet
	 */
	@Override
	public Map<String, String> getRoleMappers() {
		return _roleMappers;
	}

	/**
	 * Returns an array of required roles of the portlet.
	 *
	 * @return an array of required roles of the portlet
	 */
	@Override
	public String[] getRolesArray() {
		return _rolesArray;
	}

	/**
	 * Returns the root portlet of this portlet instance.
	 *
	 * @return the root portlet of this portlet instance
	 */
	@Override
	public Portlet getRootPortlet() {
		return _rootPortlet;
	}

	/**
	 * Returns the root portlet ID of the portlet.
	 *
	 * @return the root portlet ID of the portlet
	 */
	@Override
	public String getRootPortletId() {
		return PortletConstants.getRootPortletId(getPortletId());
	}

	/**
	 * Returns the scheduler entries of the portlet.
	 *
	 * @return the scheduler entries of the portlet
	 */
	@Override
	public List<SchedulerEntry> getSchedulerEntries() {
		return _schedulerEntries;
	}

	/**
	 * Returns <code>true</code> if the portlet supports scoping of data.
	 *
	 * @return <code>true</code> if the portlet supports scoping of data
	 */
	@Override
	public boolean getScopeable() {
		return _scopeable;
	}

	/**
	 * Returns <code>true</code> if users are shown that they do not have access
	 * to the portlet.
	 *
	 * @return <code>true</code> if users are shown that they do not have access
	 *         to the portlet
	 */
	@Override
	public boolean getShowPortletAccessDenied() {
		return _showPortletAccessDenied;
	}

	/**
	 * Returns <code>true</code> if users are shown that the portlet is
	 * inactive.
	 *
	 * @return <code>true</code> if users are shown that the portlet is inactive
	 */
	@Override
	public boolean getShowPortletInactive() {
		return _showPortletInactive;
	}

	/**
	 * Returns <code>true</code> if the portlet uses Single Page Application.
	 *
	 * @return <code>true</code> if the portlet uses Single Page Application
	 */
	@Override
	public boolean getSinglePageApplication() {
		return _singlePageApplication;
	}

	/**
	 * Returns the names of the classes that represent social activity
	 * interpreters associated with the portlet.
	 *
	 * @return the names of the classes that represent social activity
	 *         interpreters associated with the portlet
	 */
	@Override
	public List<String> getSocialActivityInterpreterClasses() {
		return _socialActivityInterpreterClasses;
	}

	/**
	 * Returns the social activity interpreter instances of the portlet.
	 *
	 * @return the social activity interpreter instances of the portlet
	 */
	@Override
	public List<SocialActivityInterpreter>
		getSocialActivityInterpreterInstances() {

		if (_socialActivityInterpreterClasses.isEmpty()) {
			return null;
		}

		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		return portletBag.getSocialActivityInterpreterInstances();
	}

	/**
	 * Returns the name of the social request interpreter class of the portlet.
	 *
	 * @return the name of the social request interpreter class of the portlet
	 */
	@Override
	public String getSocialRequestInterpreterClass() {
		return _socialRequestInterpreterClass;
	}

	/**
	 * Returns the name of the social request interpreter instance of the
	 * portlet.
	 *
	 * @return the name of the social request interpreter instance of the
	 *         portlet
	 */
	@Override
	public SocialRequestInterpreter getSocialRequestInterpreterInstance() {
		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		List<SocialRequestInterpreter> socialRequestInterpreterInstances =
			portletBag.getSocialRequestInterpreterInstances();

		if (socialRequestInterpreterInstances.isEmpty()) {
			return null;
		}

		return socialRequestInterpreterInstances.get(0);
	}

	/**
	 * Returns the names of the classes that represent staged model data
	 * handlers associated with the portlet.
	 *
	 * @return the names of the classes that represent staged model data
	 *         handlers associated with the portlet
	 */
	@Override
	public List<String> getStagedModelDataHandlerClasses() {
		return _stagedModelDataHandlerClasses;
	}

	/**
	 * Returns the staged model data handler instances of the portlet.
	 *
	 * @return the staged model data handler instances of the portlet
	 */
	@Override
	public List<StagedModelDataHandler<?>>
		getStagedModelDataHandlerInstances() {

		if (_stagedModelDataHandlerClasses.isEmpty()) {
			return null;
		}

		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		return portletBag.getStagedModelDataHandlerInstances();
	}

	/**
	 * Returns <code>true</code> if the portlet is a static portlet that is
	 * cannot be moved.
	 *
	 * @return <code>true</code> if the portlet is a static portlet that is
	 *         cannot be moved
	 */
	@Override
	public boolean getStatic() {
		return _staticPortlet;
	}

	/**
	 * Returns <code>true</code> if the portlet is a static portlet at the end
	 * of a list of portlets.
	 *
	 * @return <code>true</code> if the portlet is a static portlet at the end
	 *         of a list of portlets
	 */
	@Override
	public boolean getStaticEnd() {
		return !_staticPortletStart;
	}

	/**
	 * Returns the path for static resources served by this portlet.
	 *
	 * @return the path for static resources served by this portlet
	 */
	@Override
	public String getStaticResourcePath() {
		String proxyPath = PortalUtil.getPathProxy();

		String virtualPath = getVirtualPath();

		if (Validator.isNotNull(virtualPath)) {
			return proxyPath.concat(virtualPath);
		}

		String contextPath = getContextPath();

		if (!_portletApp.isWARFile()) {
			return contextPath;
		}

		return proxyPath.concat(contextPath);
	}

	/**
	 * Returns <code>true</code> if the portlet is a static portlet at the start
	 * of a list of portlets.
	 *
	 * @return <code>true</code> if the portlet is a static portlet at the start
	 *         of a list of portlets
	 */
	@Override
	public boolean getStaticStart() {
		return _staticPortletStart;
	}

	/**
	 * Returns the struts path of the portlet.
	 *
	 * @return the struts path of the portlet
	 */
	@Override
	public String getStrutsPath() {
		return _strutsPath;
	}

	/**
	 * Returns the supported locales of the portlet.
	 *
	 * @return the supported locales of the portlet
	 */
	@Override
	public Set<String> getSupportedLocales() {
		return _supportedLocales;
	}

	/**
	 * Returns <code>true</code> if the portlet is a system portlet that a user
	 * cannot manually add to their page.
	 *
	 * @return <code>true</code> if the portlet is a system portlet that a user
	 *         cannot manually add to their page
	 */
	@Override
	public boolean getSystem() {
		return _system;
	}

	/**
	 * Returns the name of the template handler class of the portlet.
	 *
	 * @return the name of the template handler class of the portlet
	 */
	@Override
	public String getTemplateHandlerClass() {
		return _templateHandlerClass;
	}

	/**
	 * Returns the template handler instance of the portlet.
	 *
	 * @return the template handler instance of the portlet
	 */
	@Override
	public TemplateHandler getTemplateHandlerInstance() {
		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		List<TemplateHandler> templateHandlerInstances =
			portletBag.getTemplateHandlerInstances();

		if (templateHandlerInstances.isEmpty()) {
			return null;
		}

		return templateHandlerInstances.get(0);
	}

	/**
	 * Returns the timestamp of the portlet.
	 *
	 * @return the timestamp of the portlet
	 */
	@Override
	public long getTimestamp() {
		if (_timestamp == null) {
			PortletApp portletApp = getPortletApp();

			ServletContext servletContext = portletApp.getServletContext();

			_timestamp = ServletContextUtil.getLastModified(
				servletContext, StringPool.SLASH, true);
		}

		return _timestamp;
	}

	/**
	 * Returns the names of the classes that represent trash handlers associated
	 * with the portlet.
	 *
	 * @return the names of the classes that represent trash handlers associated
	 *         with the portlet
	 */
	@Override
	public List<String> getTrashHandlerClasses() {
		return _trashHandlerClasses;
	}

	/**
	 * Returns the trash handler instances of the portlet.
	 *
	 * @return the trash handler instances of the portlet
	 */
	@Override
	public List<TrashHandler> getTrashHandlerInstances() {
		if (_trashHandlerClasses.isEmpty()) {
			return null;
		}

		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		return portletBag.getTrashHandlerInstances();
	}

	/**
	 * Returns <code>true</code> if the portlet is an undeployed portlet.
	 *
	 * @return <code>true</code> if the portlet is a placeholder of an
	 *         undeployed portlet
	 */
	@Override
	public boolean getUndeployedPortlet() {
		return _undeployedPortlet;
	}

	/**
	 * Returns the unlinked roles of the portlet.
	 *
	 * @return unlinked roles of the portlet
	 */
	@Override
	public Set<String> getUnlinkedRoles() {
		return _unlinkedRoles;
	}

	/**
	 * Returns the name of the URL encoder class of the portlet.
	 *
	 * @return the name of the URL encoder class of the portlet
	 */
	@Override
	public String getURLEncoderClass() {
		return _urlEncoderClass;
	}

	/**
	 * Returns the URL encoder instance of the portlet.
	 *
	 * @return the URL encoder instance of the portlet
	 */
	@Override
	public URLEncoder getURLEncoderInstance() {
		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		List<URLEncoder> urlEncoderInstances =
			portletBag.getURLEncoderInstances();

		if (urlEncoderInstances.isEmpty()) {
			return null;
		}

		return urlEncoderInstances.get(0);
	}

	/**
	 * Returns <code>true</code> if the portlet uses the default template.
	 *
	 * @return <code>true</code> if the portlet uses the default template
	 */
	@Override
	public boolean getUseDefaultTemplate() {
		return _useDefaultTemplate;
	}

	/**
	 * Returns the user ID of the portlet. This only applies when the portlet is
	 * added by a user in a customizable layout.
	 *
	 * @return the user ID of the portlet
	 */
	@Override
	public long getUserId() {
		return PortletConstants.getUserId(getPortletId());
	}

	/**
	 * Returns the class loader resource path to the use notification
	 * definitions of the portlet.
	 *
	 * @return the class loader resource path to the use notification
	 *         definitions of the portlet
	 */
	@Override
	public String getUserNotificationDefinitions() {
		return _userNotificationDefinitions;
	}

	/**
	 * Returns the names of the classes that represent user notification
	 * handlers associated with the portlet.
	 *
	 * @return the names of the classes that represent user notification
	 *         handlers associated with the portlet
	 */
	@Override
	public List<String> getUserNotificationHandlerClasses() {
		return _userNotificationHandlerClasses;
	}

	/**
	 * Returns the user notification handler instances of the portlet.
	 *
	 * @return the user notification handler instances of the portlet
	 */
	@Override
	public List<UserNotificationHandler> getUserNotificationHandlerInstances() {
		if (_userNotificationHandlerClasses.isEmpty()) {
			return null;
		}

		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		return portletBag.getUserNotificationHandlerInstances();
	}

	/**
	 * Returns the user principal strategy of the portlet.
	 *
	 * @return the user principal strategy of the portlet
	 */
	@Override
	public String getUserPrincipalStrategy() {
		return _userPrincipalStrategy;
	}

	/**
	 * Returns the virtual path of the portlet.
	 *
	 * @return the virtual path of the portlet
	 */
	@Override
	public String getVirtualPath() {
		return _virtualPath;
	}

	/**
	 * Returns the name of the WebDAV storage class of the portlet.
	 *
	 * @return the name of the WebDAV storage class of the portlet
	 */
	@Override
	public String getWebDAVStorageClass() {
		return _webDAVStorageClass;
	}

	/**
	 * Returns the name of the WebDAV storage instance of the portlet.
	 *
	 * @return the name of the WebDAV storage instance of the portlet
	 */
	@Override
	public WebDAVStorage getWebDAVStorageInstance() {
		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		List<WebDAVStorage> webDAVStorageInstances =
			portletBag.getWebDAVStorageInstances();

		if (webDAVStorageInstances.isEmpty()) {
			return null;
		}

		return webDAVStorageInstances.get(0);
	}

	/**
	 * Returns the name of the WebDAV storage token of the portlet.
	 *
	 * @return the name of the WebDAV storage token of the portlet
	 */
	@Override
	public String getWebDAVStorageToken() {
		return _webDAVStorageToken;
	}

	/**
	 * Returns the window states of the portlet.
	 *
	 * @return window states of the portlet
	 */
	@Override
	public Map<String, Set<String>> getWindowStates() {
		return _windowStates;
	}

	/**
	 * Returns the names of the classes that represent workflow handlers
	 * associated with the portlet.
	 *
	 * @return the names of the classes that represent workflow handlers
	 *         associated with the portlet
	 */
	@Override
	public List<String> getWorkflowHandlerClasses() {
		return _workflowHandlerClasses;
	}

	/**
	 * Returns the workflow handler instances of the portlet.
	 *
	 * @return the workflow handler instances of the portlet
	 */
	@Override
	public List<WorkflowHandler<?>> getWorkflowHandlerInstances() {
		if (_workflowHandlerClasses.isEmpty()) {
			return null;
		}

		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		return portletBag.getWorkflowHandlerInstances();
	}

	/**
	 * Returns the name of the XML-RPC method class of the portlet.
	 *
	 * @return the name of the XML-RPC method class of the portlet
	 */
	@Override
	public String getXmlRpcMethodClass() {
		return _xmlRpcMethodClass;
	}

	/**
	 * Returns the name of the XML-RPC method instance of the portlet.
	 *
	 * @return the name of the XML-RPC method instance of the portlet
	 */
	@Override
	public Method getXmlRpcMethodInstance() {
		PortletBag portletBag = PortletBagPool.get(getRootPortletId());

		List<Method> xmlRpcMethodInstances =
			portletBag.getXmlRpcMethodInstances();

		if (xmlRpcMethodInstances.isEmpty()) {
			return null;
		}

		return xmlRpcMethodInstances.get(0);
	}

	/**
	 * Returns <code>true</code> if the user has the permission to add the
	 * portlet to a layout.
	 *
	 * @param  userId the primary key of the user
	 * @return <code>true</code> if the user has the permission to add the
	 *         portlet to a layout
	 */
	@Override
	public boolean hasAddPortletPermission(long userId) {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		try {
			if ((permissionChecker == null) ||
				(permissionChecker.getUserId() != userId)) {

				User user = UserLocalServiceUtil.getUser(userId);

				permissionChecker = PermissionCheckerFactoryUtil.create(user);
			}

			if (PortletPermissionUtil.contains(
					permissionChecker, getRootPortletId(),
					ActionKeys.ADD_TO_PAGE)) {

				return true;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return false;
	}

	@Override
	public boolean hasFooterPortalCss() {
		return !_footerPortalCss.isEmpty();
	}

	@Override
	public boolean hasFooterPortalJavaScript() {
		return !_footerPortalJavaScript.isEmpty();
	}

	@Override
	public boolean hasFooterPortletCss() {
		return !_footerPortletCss.isEmpty();
	}

	@Override
	public boolean hasFooterPortletJavaScript() {
		return !_footerPortletJavaScript.isEmpty();
	}

	@Override
	public int hashCode() {
		String portletId = getPortletId();

		return portletId.hashCode();
	}

	@Override
	public boolean hasHeaderPortalCss() {
		return !_headerPortalCss.isEmpty();
	}

	@Override
	public boolean hasHeaderPortalJavaScript() {
		return !_headerPortalJavaScript.isEmpty();
	}

	@Override
	public boolean hasHeaderPortletCss() {
		return !_headerPortletCss.isEmpty();
	}

	@Override
	public boolean hasHeaderPortletJavaScript() {
		return !_headerPortletJavaScript.isEmpty();
	}

	/**
	 * Returns <code>true</code> if the portlet supports more than one mime
	 * type.
	 *
	 * @return <code>true</code> if the portlet supports more than one mime type
	 */
	@Override
	public boolean hasMultipleMimeTypes() {
		if (_portletModes.size() > 1) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Returns <code>true</code> if the portlet supports the specified mime type
	 * and portlet mode.
	 *
	 * @param  mimeType the mime type
	 * @param  portletMode the portlet mode
	 * @return <code>true</code> if the portlet supports the specified mime type
	 *         and portlet mode
	 */
	@Override
	public boolean hasPortletMode(String mimeType, PortletMode portletMode) {
		if (mimeType == null) {
			mimeType = ContentTypes.TEXT_HTML;
		}

		Set<String> mimeTypePortletModes = _portletModes.get(mimeType);

		if (mimeTypePortletModes == null) {
			return false;
		}

		if (mimeTypePortletModes.contains(portletMode.toString())) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Returns <code>true</code> if the portlet has a role with the specified
	 * name.
	 *
	 * @param  roleName the role name
	 * @return <code>true</code> if the portlet has a role with the specified
	 *         name
	 */
	@Override
	public boolean hasRoleWithName(String roleName) {
		if (ArrayUtil.isEmpty(_rolesArray)) {
			return false;
		}

		for (String curRoleName : _rolesArray) {
			if (StringUtil.equalsIgnoreCase(curRoleName, roleName)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns <code>true</code> if the portlet supports the specified mime type
	 * and window state.
	 *
	 * @param  mimeType the mime type
	 * @param  windowState the window state
	 * @return <code>true</code> if the portlet supports the specified mime type
	 *         and window state
	 */
	@Override
	public boolean hasWindowState(String mimeType, WindowState windowState) {
		if (mimeType == null) {
			mimeType = ContentTypes.TEXT_HTML;
		}

		Set<String> mimeTypeWindowStates = _windowStates.get(mimeType);

		if (mimeTypeWindowStates == null) {
			return false;
		}

		if (mimeTypeWindowStates.contains(windowState.toString())) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Returns <code>true</code> if an action URL for this portlet should cause
	 * an auto redirect.
	 *
	 * @return <code>true</code> if an action URL for this portlet should cause
	 *         an auto redirect
	 */
	@Override
	public boolean isActionURLRedirect() {
		return _actionURLRedirect;
	}

	/**
	 * Returns <code>true</code> if default resources for the portlet are added
	 * to a page.
	 *
	 * @return <code>true</code> if default resources for the portlet are added
	 *         to a page
	 */
	@Override
	public boolean isAddDefaultResource() {
		return _addDefaultResource;
	}

	/**
	 * Returns <code>true</code> if the portlet can be displayed via Ajax.
	 *
	 * @return <code>true</code> if the portlet can be displayed via Ajax
	 */
	@Override
	public boolean isAjaxable() {
		return _ajaxable;
	}

	@Override
	public boolean isFullPageDisplayable() {
		return _applicationTypes.contains(
			ApplicationType.FULL_PAGE_APPLICATION);
	}

	/**
	 * Returns <code>true</code> to include the portlet and make it available to
	 * be made active.
	 *
	 * @return <code>true</code> to include the portlet and make it available to
	 *         be made active
	 */
	@Override
	public boolean isInclude() {
		return _include;
	}

	/**
	 * Returns <code>true</code> if the portlet can be added multiple times to a
	 * layout.
	 *
	 * @return <code>true</code> if the portlet can be added multiple times to a
	 *         layout
	 */
	@Override
	public boolean isInstanceable() {
		return _instanceable;
	}

	/**
	 * Returns <code>true</code> to allow the portlet to be cached within the
	 * layout.
	 *
	 * @return <code>true</code> if the portlet can be cached within the layout
	 */
	@Override
	public boolean isLayoutCacheable() {
		return _layoutCacheable;
	}

	/**
	 * Returns <code>true</code> if the portlet goes into the maximized state
	 * when the user goes into the edit mode.
	 *
	 * @return <code>true</code> if the portlet goes into the maximized state
	 *         when the user goes into the edit mode
	 */
	@Override
	public boolean isMaximizeEdit() {
		return _maximizeEdit;
	}

	/**
	 * Returns <code>true</code> if the portlet goes into the maximized state
	 * when the user goes into the help mode.
	 *
	 * @return <code>true</code> if the portlet goes into the maximized state
	 *         when the user goes into the help mode
	 */
	@Override
	public boolean isMaximizeHelp() {
		return _maximizeHelp;
	}

	/**
	 * Returns <code>true</code> if the portlet goes into the pop up state when
	 * the user goes into the print mode.
	 *
	 * @return <code>true</code> if the portlet goes into the pop up state when
	 *         the user goes into the print mode
	 */
	@Override
	public boolean isPopUpPrint() {
		return _popUpPrint;
	}

	/**
	 * Returns <code>true</code> if preferences are shared across the entire
	 * company.
	 *
	 * @return <code>true</code> if preferences are shared across the entire
	 *         company
	 */
	@Override
	public boolean isPreferencesCompanyWide() {
		return _preferencesCompanyWide;
	}

	/**
	 * Returns <code>true</code> if preferences are owned by the group when the
	 * portlet is shown in a group layout. Returns <code>false</code> if
	 * preferences are owned by the user at all times.
	 *
	 * @return <code>true</code> if preferences are owned by the group when the
	 *         portlet is shown in a group layout; <code>false</code> if
	 *         preferences are owned by the user at all times.
	 */
	@Override
	public boolean isPreferencesOwnedByGroup() {
		return _preferencesOwnedByGroup;
	}

	/**
	 * Returns <code>true</code> if preferences are unique per layout.
	 *
	 * @return <code>true</code> if preferences are unique per layout
	 */
	@Override
	public boolean isPreferencesUniquePerLayout() {
		return _preferencesUniquePerLayout;
	}

	/**
	 * Returns <code>true</code> if the portlet does not share request
	 * attributes with the portal or portlets from another WAR.
	 *
	 * @return <code>true</code> if the portlet does not share request
	 *         attributes with the portal or portlets from another WAR
	 */
	@Override
	public boolean isPrivateRequestAttributes() {
		return _privateRequestAttributes;
	}

	/**
	 * Returns <code>true</code> if the portlet does not share session
	 * attributes with the portal.
	 *
	 * @return <code>true</code> if the portlet does not share session
	 *         attributes with the portal
	 */
	@Override
	public boolean isPrivateSessionAttributes() {
		return _privateSessionAttributes;
	}

	/**
	 * Returns <code>true</code> if the portlet is ready to be used.
	 *
	 * @return <code>true</code> if the portlet is ready to be used
	 */
	@Override
	public boolean isReady() {
		Readiness readiness = _readinessMap.get(getRootPortletId());

		if (readiness == null) {
			return true;
		}
		else {
			return readiness._ready;
		}
	}

	/**
	 * Returns <code>true</code> if the portlet supports remoting.
	 *
	 * @return <code>true</code> if the portlet supports remoting
	 */
	@Override
	public boolean isRemoteable() {
		return _remoteable;
	}

	/**
	 * Returns <code>true</code> if the portlet will only process namespaced
	 * parameters.
	 *
	 * @return <code>true</code> if the portlet will only process namespaced
	 *         parameters
	 */
	@Override
	public boolean isRequiresNamespacedParameters() {
		return _requiresNamespacedParameters;
	}

	/**
	 * Returns <code>true</code> if the portlet restores to the current view
	 * from the maximized state.
	 *
	 * @return <code>true</code> if the portlet restores to the current view
	 *         from the maximized state
	 */
	@Override
	public boolean isRestoreCurrentView() {
		return _restoreCurrentView;
	}

	/**
	 * Returns <code>true</code> if the portlet supports scoping of data.
	 *
	 * @return <code>true</code> if the portlet supports scoping of data
	 */
	@Override
	public boolean isScopeable() {
		return _scopeable;
	}

	/**
	 * Returns <code>true</code> if users are shown that they do not have access
	 * to the portlet.
	 *
	 * @return <code>true</code> if users are shown that they do not have access
	 *         to the portlet
	 */
	@Override
	public boolean isShowPortletAccessDenied() {
		return _showPortletAccessDenied;
	}

	/**
	 * Returns <code>true</code> if users are shown that the portlet is
	 * inactive.
	 *
	 * @return <code>true</code> if users are shown that the portlet is inactive
	 */
	@Override
	public boolean isShowPortletInactive() {
		return _showPortletInactive;
	}

	/**
	 * Returns <code>true</code> if the portlet uses Single Page Application.
	 *
	 * @return <code>true</code> if the portlet uses Single Page Application
	 */
	@Override
	public boolean isSinglePageApplication() {
		return _singlePageApplication;
	}

	/**
	 * Returns <code>true</code> if the portlet is a static portlet that is
	 * cannot be moved.
	 *
	 * @return <code>true</code> if the portlet is a static portlet that is
	 *         cannot be moved
	 */
	@Override
	public boolean isStatic() {
		return _staticPortlet;
	}

	/**
	 * Returns <code>true</code> if the portlet is a static portlet at the end
	 * of a list of portlets.
	 *
	 * @return <code>true</code> if the portlet is a static portlet at the end
	 *         of a list of portlets
	 */
	@Override
	public boolean isStaticEnd() {
		return !_staticPortletStart;
	}

	/**
	 * Returns <code>true</code> if the portlet is a static portlet at the start
	 * of a list of portlets.
	 *
	 * @return <code>true</code> if the portlet is a static portlet at the start
	 *         of a list of portlets
	 */
	@Override
	public boolean isStaticStart() {
		return _staticPortletStart;
	}

	/**
	 * Returns <code>true</code> if the portlet is a system portlet that a user
	 * cannot manually add to their page.
	 *
	 * @return <code>true</code> if the portlet is a system portlet that a user
	 *         cannot manually add to their page
	 */
	@Override
	public boolean isSystem() {
		return _system;
	}

	/**
	 * Returns <code>true</code> if the portlet is an undeployed portlet.
	 *
	 * @return <code>true</code> if the portlet is a placeholder of an
	 *         undeployed portlet
	 */
	@Override
	public boolean isUndeployedPortlet() {
		return _undeployedPortlet;
	}

	/**
	 * Returns <code>true</code> if the portlet uses the default template.
	 *
	 * @return <code>true</code> if the portlet uses the default template
	 */
	@Override
	public boolean isUseDefaultTemplate() {
		return _useDefaultTemplate;
	}

	/**
	 * Link the role names set in portlet.xml with the Liferay roles set in
	 * liferay-portlet.xml.
	 */
	@Override
	public void linkRoles() {
		List<String> linkedRoles = new ArrayList<>();

		for (String unlinkedRole : _unlinkedRoles) {
			String roleLink = _roleMappers.get(unlinkedRole);

			if (Validator.isNotNull(roleLink)) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Linking role for portlet [" + getPortletId() +
							"] with role-name [" + unlinkedRole +
								"] to role-link [" + roleLink + "]");
				}

				linkedRoles.add(roleLink);
			}
			else {
				_log.error(
					"Unable to link role for portlet [" + getPortletId() +
						"] with role-name [" + unlinkedRole +
							"] because role-link is null");
			}
		}

		String[] array = linkedRoles.toArray(new String[linkedRoles.size()]);

		Arrays.sort(array);

		setRolesArray(array);
	}

	/**
	 * Sets the action timeout of the portlet.
	 *
	 * @param actionTimeout the action timeout of the portlet
	 */
	@Override
	public void setActionTimeout(int actionTimeout) {
		_actionTimeout = actionTimeout;
	}

	/**
	 * Set to <code>true</code> if an action URL for this portlet should cause
	 * an auto redirect.
	 *
	 * @param actionURLRedirect boolean value for whether an action URL for this
	 *        portlet should cause an auto redirect
	 */
	@Override
	public void setActionURLRedirect(boolean actionURLRedirect) {
		_actionURLRedirect = actionURLRedirect;
	}

	/**
	 * Set to <code>true</code> if default resources for the portlet are added
	 * to a page.
	 *
	 * @param addDefaultResource boolean value for whether or not default
	 *        resources for the portlet are added to a page
	 */
	@Override
	public void setAddDefaultResource(boolean addDefaultResource) {
		_addDefaultResource = addDefaultResource;
	}

	/**
	 * Set to <code>true</code> if the portlet can be displayed via Ajax.
	 *
	 * @param ajaxable boolean value for whether the portlet can be displayed
	 *        via Ajax
	 */
	@Override
	public void setAjaxable(boolean ajaxable) {
		_ajaxable = ajaxable;
	}

	/**
	 * Sets the application types of the portlet.
	 *
	 * @param applicationTypes the application types of the portlet
	 */
	@Override
	public void setApplicationTypes(Set<ApplicationType> applicationTypes) {
		for (ApplicationType applicationType : applicationTypes) {
			addApplicationType(applicationType);
		}
	}

	/**
	 * Sets the names of the classes that represent asset types associated with
	 * the portlet.
	 *
	 * @param assetRendererFactoryClasses the names of the classes that
	 *        represent asset types associated with the portlet
	 */
	@Override
	public void setAssetRendererFactoryClasses(
		List<String> assetRendererFactoryClasses) {

		_assetRendererFactoryClasses = assetRendererFactoryClasses;
	}

	/**
	 * Sets the names of the classes that represent atom collection adapters
	 * associated with the portlet.
	 *
	 * @param atomCollectionAdapterClasses the names of the classes that
	 *        represent atom collection adapters associated with the portlet
	 */
	@Override
	public void setAtomCollectionAdapterClasses(
		List<String> atomCollectionAdapterClasses) {

		_atomCollectionAdapterClasses = atomCollectionAdapterClasses;
	}

	/**
	 * Sets the names of the parameters that will be automatically propagated
	 * through the portlet.
	 *
	 * @param autopropagatedParameters the names of the parameters that will be
	 *        automatically propagated through the portlet
	 */
	@Override
	public void setAutopropagatedParameters(
		Set<String> autopropagatedParameters) {

		_autopropagatedParameters = autopropagatedParameters;
	}

	/**
	 * Sets the configuration action class of the portlet.
	 *
	 * @param configurationActionClass the configuration action class of the
	 *        portlet
	 */
	@Override
	public void setConfigurationActionClass(String configurationActionClass) {
		_configurationActionClass = configurationActionClass;
	}

	/**
	 * Set the name of the category of the Control Panel where the portlet will
	 * be shown.
	 *
	 * @param controlPanelEntryCategory the name of the category of the Control
	 *        Panel where the portlet will be shown
	 */
	@Override
	public void setControlPanelEntryCategory(String controlPanelEntryCategory) {
		_controlPanelEntryCategory = controlPanelEntryCategory;
	}

	/**
	 * Sets the name of the class that will control when the portlet will be
	 * shown in the Control Panel.
	 *
	 * @param controlPanelEntryClass the name of the class that will control
	 *        when the portlet will be shown in the Control Panel
	 */
	@Override
	public void setControlPanelEntryClass(String controlPanelEntryClass) {
		_controlPanelEntryClass = controlPanelEntryClass;
	}

	/**
	 * Sets the relative weight of the portlet with respect to the other
	 * portlets in the same category of the Control Panel.
	 *
	 * @param controlPanelEntryWeight the relative weight of the portlet with
	 *        respect to the other portlets in the same category of the Control
	 *        Panel
	 */
	@Override
	public void setControlPanelEntryWeight(double controlPanelEntryWeight) {
		_controlPanelEntryWeight = controlPanelEntryWeight;
	}

	/**
	 * Sets the name of the CSS class that will be injected in the DIV that
	 * wraps this portlet.
	 *
	 * @param cssClassWrapper the name of the CSS class that will be injected in
	 *        the DIV that wraps this portlet
	 */
	@Override
	public void setCssClassWrapper(String cssClassWrapper) {
		_cssClassWrapper = cssClassWrapper;
	}

	/**
	 * Sets the names of the classes that represent custom attribute displays
	 * associated with the portlet.
	 *
	 * @param customAttributesDisplayClasses the names of the classes that
	 *        represent custom attribute displays associated with the portlet
	 */
	@Override
	public void setCustomAttributesDisplayClasses(
		List<String> customAttributesDisplayClasses) {

		_customAttributesDisplayClasses = customAttributesDisplayClasses;
	}

	/**
	 * Sets the default plugin settings of the portlet.
	 *
	 * @param pluginSetting the plugin setting
	 */
	@Override
	public void setDefaultPluginSetting(PluginSetting pluginSetting) {
		_defaultPluginSetting = pluginSetting;
	}

	/**
	 * Sets the default preferences of the portlet.
	 *
	 * @param defaultPreferences the default preferences of the portlet
	 */
	@Override
	public void setDefaultPreferences(String defaultPreferences) {
		_defaultPreferences = defaultPreferences;
	}

	/**
	 * Sets the display name of the portlet.
	 *
	 * @param displayName the display name of the portlet
	 */
	@Override
	public void setDisplayName(String displayName) {
		_displayName = displayName;
	}

	/**
	 * Sets expiration cache of the portlet.
	 *
	 * @param expCache expiration cache of the portlet
	 */
	@Override
	public void setExpCache(Integer expCache) {
		_expCache = expCache;
	}

	/**
	 * Sets the Facebook integration method of the portlet.
	 *
	 * @param facebookIntegration the Facebook integration method of the portlet
	 */
	@Override
	public void setFacebookIntegration(String facebookIntegration) {
		if (Validator.isNotNull(facebookIntegration)) {
			_facebookIntegration = facebookIntegration;
		}
	}

	/**
	 * Sets a list of CSS files that will be referenced from the page's footer
	 * relative to the portal's context path.
	 *
	 * @param footerPortalCss a list of CSS files that will be referenced from
	 *        the page's footer relative to the portal's context path
	 */
	@Override
	public void setFooterPortalCss(List<String> footerPortalCss) {
		_footerPortalCss = footerPortalCss;
	}

	/**
	 * Sets a list of JavaScript files that will be referenced from the page's
	 * footer relative to the portal's context path.
	 *
	 * @param footerPortalJavaScript a list of JavaScript files that will be
	 *        referenced from the page's footer relative to the portal's context
	 *        path
	 */
	@Override
	public void setFooterPortalJavaScript(List<String> footerPortalJavaScript) {
		_footerPortalJavaScript = footerPortalJavaScript;
	}

	/**
	 * Sets a list of CSS files that will be referenced from the page's footer
	 * relative to the portlet's context path.
	 *
	 * @param footerPortletCss a list of CSS files that will be referenced from
	 *        the page's footer relative to the portlet's context path
	 */
	@Override
	public void setFooterPortletCss(List<String> footerPortletCss) {
		_footerPortletCss = footerPortletCss;
	}

	/**
	 * Sets a list of JavaScript files that will be referenced from the page's
	 * footer relative to the portlet's context path.
	 *
	 * @param footerPortletJavaScript a list of JavaScript files that will be
	 *        referenced from the page's footer relative to the portlet's
	 *        context path
	 */
	@Override
	public void setFooterPortletJavaScript(
		List<String> footerPortletJavaScript) {

		_footerPortletJavaScript = footerPortletJavaScript;
	}

	/**
	 * Sets the name of the friendly URL mapper class of the portlet.
	 *
	 * @param friendlyURLMapperClass the name of the friendly URL mapper class
	 *        of the portlet
	 */
	@Override
	public void setFriendlyURLMapperClass(String friendlyURLMapperClass) {
		_friendlyURLMapperClass = friendlyURLMapperClass;
	}

	/**
	 * Sets the name of the friendly URL mapping of the portlet.
	 *
	 * @param friendlyURLMapping the name of the friendly URL mapping of the
	 *        portlet
	 */
	@Override
	public void setFriendlyURLMapping(String friendlyURLMapping) {
		_friendlyURLMapping = friendlyURLMapping;
	}

	/**
	 * Sets the class loader resource path to the friendly URL routes of the
	 * portlet.
	 *
	 * @param friendlyURLRoutes the class loader resource path to the friendly
	 *        URL routes of the portlet
	 */
	@Override
	public void setFriendlyURLRoutes(String friendlyURLRoutes) {
		_friendlyURLRoutes = friendlyURLRoutes;
	}

	/**
	 * Sets a list of CSS files that will be referenced from the page's header
	 * relative to the portal's context path.
	 *
	 * @param headerPortalCss a list of CSS files that will be referenced from
	 *        the page's header relative to the portal's context path
	 */
	@Override
	public void setHeaderPortalCss(List<String> headerPortalCss) {
		_headerPortalCss = headerPortalCss;
	}

	/**
	 * Sets a list of JavaScript files that will be referenced from the page's
	 * header relative to the portal's context path.
	 *
	 * @param headerPortalJavaScript a list of JavaScript files that will be
	 *        referenced from the page's header relative to the portal's context
	 *        path
	 */
	@Override
	public void setHeaderPortalJavaScript(List<String> headerPortalJavaScript) {
		_headerPortalJavaScript = headerPortalJavaScript;
	}

	/**
	 * Sets a list of CSS files that will be referenced from the page's header
	 * relative to the portlet's context path.
	 *
	 * @param headerPortletCss a list of CSS files that will be referenced from
	 *        the page's header relative to the portlet's context path
	 */
	@Override
	public void setHeaderPortletCss(List<String> headerPortletCss) {
		_headerPortletCss = headerPortletCss;
	}

	/**
	 * Sets a list of JavaScript files that will be referenced from the page's
	 * header relative to the portlet's context path.
	 *
	 * @param headerPortletJavaScript a list of JavaScript files that will be
	 *        referenced from the page's header relative to the portlet's
	 *        context path
	 */
	@Override
	public void setHeaderPortletJavaScript(
		List<String> headerPortletJavaScript) {

		_headerPortletJavaScript = headerPortletJavaScript;
	}

	/**
	 * Sets the icon of the portlet.
	 *
	 * @param icon the icon of the portlet
	 */
	@Override
	public void setIcon(String icon) {
		_icon = icon;
	}

	/**
	 * Set to <code>true</code> to include the portlet and make it available to
	 * be made active.
	 *
	 * @param include boolean value for whether to include the portlet and make
	 *        it available to be made active
	 */
	@Override
	public void setInclude(boolean include) {
		_include = include;
	}

	/**
	 * Sets the names of the classes that represent indexers associated with the
	 * portlet.
	 *
	 * @param indexerClasses the names of the classes that represent indexers
	 *        associated with the portlet
	 */
	@Override
	public void setIndexerClasses(List<String> indexerClasses) {
		_indexerClasses = indexerClasses;
	}

	/**
	 * Sets the init parameters of the portlet.
	 *
	 * @param initParams the init parameters of the portlet
	 */
	@Override
	public void setInitParams(Map<String, String> initParams) {
		_initParams = initParams;
	}

	/**
	 * Set to <code>true</code> if the portlet can be added multiple times to a
	 * layout.
	 *
	 * @param instanceable boolean value for whether the portlet can be added
	 *        multiple times to a layout
	 */
	@Override
	public void setInstanceable(boolean instanceable) {
		_instanceable = instanceable;
	}

	/**
	 * Set to <code>true</code> to allow the portlet to be cached within the
	 * layout.
	 *
	 * @param layoutCacheable boolean value for whether the portlet can be
	 *        cached within the layout
	 */
	@Override
	public void setLayoutCacheable(boolean layoutCacheable) {
		_layoutCacheable = layoutCacheable;
	}

	/**
	 * Set to <code>true</code> if the portlet goes into the maximized state
	 * when the user goes into the edit mode.
	 *
	 * @param maximizeEdit boolean value for whether the portlet goes into the
	 *        maximized state when the user goes into the edit mode
	 */
	@Override
	public void setMaximizeEdit(boolean maximizeEdit) {
		_maximizeEdit = maximizeEdit;
	}

	/**
	 * Set to <code>true</code> if the portlet goes into the maximized state
	 * when the user goes into the help mode.
	 *
	 * @param maximizeHelp boolean value for whether the portlet goes into the
	 *        maximized state when the user goes into the help mode
	 */
	@Override
	public void setMaximizeHelp(boolean maximizeHelp) {
		_maximizeHelp = maximizeHelp;
	}

	/**
	 * Sets the name of the open search class of the portlet.
	 *
	 * @param openSearchClass the name of the open search class of the portlet
	 */
	@Override
	public void setOpenSearchClass(String openSearchClass) {
		_openSearchClass = openSearchClass;
	}

	/**
	 * Sets the parent struts path of the portlet.
	 *
	 * @param parentStrutsPath the parent struts path of the portlet
	 */
	@Override
	public void setParentStrutsPath(String parentStrutsPath) {
		_parentStrutsPath = parentStrutsPath;
	}

	/**
	 * Sets the name of the permission propagator class of the portlet.
	 */
	@Override
	public void setPermissionPropagatorClass(String permissionPropagatorClass) {
		_permissionPropagatorClass = permissionPropagatorClass;
	}

	/**
	 * Sets this portlet's plugin package.
	 *
	 * @param pluginPackage this portlet's plugin package
	 */
	@Override
	public void setPluginPackage(PluginPackage pluginPackage) {
		_pluginPackage = pluginPackage;
	}

	/**
	 * Sets the name of the poller processor class of the portlet.
	 *
	 * @param pollerProcessorClass the name of the poller processor class of the
	 *        portlet
	 */
	@Override
	public void setPollerProcessorClass(String pollerProcessorClass) {
		_pollerProcessorClass = pollerProcessorClass;
	}

	/**
	 * Sets the name of the POP message listener class of the portlet.
	 *
	 * @param popMessageListenerClass the name of the POP message listener class
	 *        of the portlet
	 */
	@Override
	public void setPopMessageListenerClass(String popMessageListenerClass) {
		_popMessageListenerClass = popMessageListenerClass;
	}

	/**
	 * Set to <code>true</code> if the portlet goes into the pop up state when
	 * the user goes into the print mode.
	 *
	 * @param popUpPrint boolean value for whether the portlet goes into the pop
	 *        up state when the user goes into the print mode
	 */
	@Override
	public void setPopUpPrint(boolean popUpPrint) {
		_popUpPrint = popUpPrint;
	}

	/**
	 * Sets this portlet's application.
	 *
	 * @param portletApp this portlet's application
	 */
	@Override
	public void setPortletApp(PortletApp portletApp) {
		_portletApp = portletApp;

		_portletApp.addPortlet(this);
	}

	/**
	 * Sets the name of the portlet class of the portlet.
	 *
	 * @param portletClass the name of the portlet class of the portlet
	 */
	@Override
	public void setPortletClass(String portletClass) {
		_portletClass = portletClass;
	}

	/**
	 * Sets the name of the portlet data handler class of the portlet.
	 *
	 * @param portletDataHandlerClass the name of portlet data handler class of
	 *        the portlet
	 */
	@Override
	public void setPortletDataHandlerClass(String portletDataHandlerClass) {
		_portletDataHandlerClass = portletDataHandlerClass;
	}

	/**
	 * Sets the filters of the portlet.
	 *
	 * @param portletFilters the filters of the portlet
	 */
	@Override
	public void setPortletFilters(Map<String, PortletFilter> portletFilters) {
		_portletFilters = portletFilters;
	}

	/**
	 * Sets the portlet info of the portlet.
	 *
	 * @param portletInfo the portlet info of the portlet
	 */
	@Override
	public void setPortletInfo(PortletInfo portletInfo) {
		_portletInfo = portletInfo;
	}

	/**
	 * Sets the name of the portlet layout listener class of the portlet.
	 *
	 * @param portletLayoutListenerClass the name of the portlet layout listener
	 *        class of the portlet
	 */
	@Override
	public void setPortletLayoutListenerClass(
		String portletLayoutListenerClass) {

		_portletLayoutListenerClass = portletLayoutListenerClass;
	}

	/**
	 * Sets the portlet modes of the portlet.
	 *
	 * @param portletModes the portlet modes of the portlet
	 */
	@Override
	public void setPortletModes(Map<String, Set<String>> portletModes) {
		_portletModes = portletModes;
	}

	/**
	 * Sets the name of the portlet.
	 *
	 * @param portletName the name of the portlet
	 */
	@Override
	public void setPortletName(String portletName) {
		_portletName = portletName;
	}

	/**
	 * Sets the name of the portlet URL class of the portlet.
	 *
	 * @param portletURLClass the name of the portlet URL class of the portlet
	 */
	@Override
	public void setPortletURLClass(String portletURLClass) {
		_portletURLClass = portletURLClass;
	}

	/**
	 * Set to <code>true</code> if preferences are shared across the entire
	 * company.
	 *
	 * @param preferencesCompanyWide boolean value for whether preferences are
	 *        shared across the entire company
	 */
	@Override
	public void setPreferencesCompanyWide(boolean preferencesCompanyWide) {
		_preferencesCompanyWide = preferencesCompanyWide;
	}

	/**
	 * Set to <code>true</code> if preferences are owned by the group when the
	 * portlet is shown in a group layout. Set to <code>false</code> if
	 * preferences are owned by the user at all times.
	 *
	 * @param preferencesOwnedByGroup boolean value for whether preferences are
	 *        owned by the group when the portlet is shown in a group layout or
	 *        preferences are owned by the user at all times
	 */
	@Override
	public void setPreferencesOwnedByGroup(boolean preferencesOwnedByGroup) {
		_preferencesOwnedByGroup = preferencesOwnedByGroup;
	}

	/**
	 * Set to <code>true</code> if preferences are unique per layout.
	 *
	 * @param preferencesUniquePerLayout boolean value for whether preferences
	 *        are unique per layout
	 */
	@Override
	public void setPreferencesUniquePerLayout(
		boolean preferencesUniquePerLayout) {

		_preferencesUniquePerLayout = preferencesUniquePerLayout;
	}

	/**
	 * Sets the name of the preferences validator class of the portlet.
	 *
	 * @param preferencesValidator the name of the preferences validator class
	 *        of the portlet
	 */
	@Override
	public void setPreferencesValidator(String preferencesValidator) {
		if (preferencesValidator != null) {

			// Trim this because XDoclet generates preferences validators with
			// extra white spaces

			_preferencesValidator = preferencesValidator.trim();
		}
		else {
			_preferencesValidator = null;
		}
	}

	/**
	 * Set to <code>true</code> if the portlet does not share request attributes
	 * with the portal or portlets from another WAR.
	 *
	 * @param privateRequestAttributes boolean value for whether the portlet
	 *        shares request attributes with the portal or portlets from another
	 *        WAR
	 */
	@Override
	public void setPrivateRequestAttributes(boolean privateRequestAttributes) {
		_privateRequestAttributes = privateRequestAttributes;
	}

	/**
	 * Set to <code>true</code> if the portlet does not share session attributes
	 * with the portal.
	 *
	 * @param privateSessionAttributes boolean value for whether the portlet
	 *        shares session attributes with the portal
	 */
	@Override
	public void setPrivateSessionAttributes(boolean privateSessionAttributes) {
		_privateSessionAttributes = privateSessionAttributes;
	}

	/**
	 * Sets the processing events of the portlet.
	 *
	 * @param processingEvents the processing events of the portlet
	 */
	@Override
	public void setProcessingEvents(Set<QName> processingEvents) {
		for (QName processingEvent : processingEvents) {
			addProcessingEvent(processingEvent);
		}
	}

	/**
	 * Sets the public render parameters of the portlet.
	 *
	 * @param publicRenderParameters the public render parameters of the portlet
	 */
	@Override
	public void setPublicRenderParameters(
		Set<PublicRenderParameter> publicRenderParameters) {

		for (PublicRenderParameter publicRenderParameter :
				publicRenderParameters) {

			addPublicRenderParameter(publicRenderParameter);
		}
	}

	/**
	 * Sets the publishing events of the portlet.
	 *
	 * @param publishingEvents the publishing events of the portlet
	 */
	@Override
	public void setPublishingEvents(Set<QName> publishingEvents) {
		for (QName publishingEvent : publishingEvents) {
			addPublishingEvent(publishingEvent);
		}
	}

	/**
	 * Set to <code>true</code> if the portlet is ready to be used.
	 *
	 * @param ready whether the portlet is ready to be used
	 */
	@Override
	public void setReady(boolean ready) {
		Registry registry = RegistryUtil.getRegistry();

		Readiness readiness = new Readiness(
			ready, registry.getServiceRegistrar(Portlet.class));

		String rootPortletId = getRootPortletId();

		Readiness previousReadiness = _readinessMap.putIfAbsent(
			rootPortletId, readiness);

		if (previousReadiness != null) {
			readiness = previousReadiness;
		}

		synchronized (readiness) {
			if (readiness != _readinessMap.get(rootPortletId)) {
				return;
			}

			readiness._ready = ready;

			ServiceRegistrar<Portlet> serviceRegistrar =
				readiness._serviceRegistrar;

			if (ready) {
				if (serviceRegistrar.isDestroyed()) {
					serviceRegistrar = registry.getServiceRegistrar(
						Portlet.class);

					readiness._serviceRegistrar = serviceRegistrar;
				}

				Map<String, Object> properties = new HashMap<>();

				properties.put("javax.portlet.name", getPortletName());

				serviceRegistrar.registerService(
					Portlet.class, this, properties);
			}
			else {
				serviceRegistrar.destroy();
			}
		}
	}

	/**
	 * Set to <code>true</code> if the portlet supports remoting
	 *
	 * @param remoteable boolean value for whether or not the the portlet
	 *        supports remoting
	 */
	@Override
	public void setRemoteable(boolean remoteable) {
		_remoteable = remoteable;
	}

	/**
	 * Sets the render timeout of the portlet.
	 *
	 * @param renderTimeout the render timeout of the portlet
	 */
	@Override
	public void setRenderTimeout(int renderTimeout) {
		_renderTimeout = renderTimeout;
	}

	/**
	 * Sets the render weight of the portlet.
	 *
	 * @param renderWeight int value for the render weight of the portlet
	 */
	@Override
	public void setRenderWeight(int renderWeight) {
		_renderWeight = renderWeight;
	}

	/**
	 * Set to <code>true</code> if the portlet will only process namespaced
	 * parameters.
	 *
	 * @param requiresNamespacedParameters boolean value for whether the portlet
	 *        will only process namespaced parameters
	 */
	@Override
	public void setRequiresNamespacedParameters(
		boolean requiresNamespacedParameters) {

		_requiresNamespacedParameters = requiresNamespacedParameters;
	}

	/**
	 * Sets the resource bundle of the portlet.
	 *
	 * @param resourceBundle the resource bundle of the portlet
	 */
	@Override
	public void setResourceBundle(String resourceBundle) {
		_resourceBundle = resourceBundle;
	}

	/**
	 * Set to <code>true</code> if the portlet restores to the current view from
	 * the maximized state.
	 *
	 * @param restoreCurrentView boolean value for whether the portlet restores
	 *        to the current view from the maximized state
	 */
	@Override
	public void setRestoreCurrentView(boolean restoreCurrentView) {
		_restoreCurrentView = restoreCurrentView;
	}

	/**
	 * Sets the role mappers of the portlet.
	 *
	 * @param roleMappers the role mappers of the portlet
	 */
	@Override
	public void setRoleMappers(Map<String, String> roleMappers) {
		_roleMappers = roleMappers;
	}

	/**
	 * Sets a string of ordered comma delimited portlet IDs.
	 *
	 * @param roles a string of ordered comma delimited portlet IDs
	 */
	@Override
	public void setRoles(String roles) {
		_rolesArray = StringUtil.split(roles);

		super.setRoles(roles);
	}

	/**
	 * Sets an array of required roles of the portlet.
	 *
	 * @param rolesArray an array of required roles of the portlet
	 */
	@Override
	public void setRolesArray(String[] rolesArray) {
		_rolesArray = rolesArray;

		super.setRoles(StringUtil.merge(rolesArray));
	}

	/**
	 * Sets the scheduler entries of the portlet.
	 *
	 * @param schedulerEntries the scheduler entries of the portlet
	 */
	@Override
	public void setSchedulerEntries(List<SchedulerEntry> schedulerEntries) {
		for (SchedulerEntry schedulerEntry : schedulerEntries) {
			addSchedulerEntry(schedulerEntry);
		}
	}

	/**
	 * Set to <code>true</code> if the portlet supports scoping of data.
	 *
	 * @param scopeable boolean value for whether or not the the portlet
	 *        supports scoping of data
	 */
	@Override
	public void setScopeable(boolean scopeable) {
		_scopeable = scopeable;
	}

	/**
	 * Set to <code>true</code> if users are shown that they do not have access
	 * to the portlet.
	 *
	 * @param showPortletAccessDenied boolean value for whether users are shown
	 *        that they do not have access to the portlet
	 */
	@Override
	public void setShowPortletAccessDenied(boolean showPortletAccessDenied) {
		_showPortletAccessDenied = showPortletAccessDenied;
	}

	/**
	 * Set to <code>true</code> if users are shown that the portlet is inactive.
	 *
	 * @param showPortletInactive boolean value for whether users are shown that
	 *        the portlet is inactive
	 */
	@Override
	public void setShowPortletInactive(boolean showPortletInactive) {
		_showPortletInactive = showPortletInactive;
	}

	/**
	 * Set to <code>true</code> if the portlet uses Single Page Application.
	 *
	 * @param singlePageApplication boolean value for whether or not the the
	 *        portlet uses Single Page Application
	 */
	@Override
	public void setSinglePageApplication(boolean singlePageApplication) {
		_singlePageApplication = singlePageApplication;
	}

	/**
	 * Sets the names of the classes that represent social activity interpreters
	 * associated with the portlet.
	 *
	 * @param socialActivityInterpreterClasses the names of the classes that
	 *        represent social activity interpreters associated with the portlet
	 */
	@Override
	public void setSocialActivityInterpreterClasses(
		List<String> socialActivityInterpreterClasses) {

		_socialActivityInterpreterClasses = socialActivityInterpreterClasses;
	}

	/**
	 * Sets the name of the social request interpreter class of the portlet.
	 *
	 * @param socialRequestInterpreterClass the name of the request interpreter
	 *        class of the portlet
	 */
	@Override
	public void setSocialRequestInterpreterClass(
		String socialRequestInterpreterClass) {

		_socialRequestInterpreterClass = socialRequestInterpreterClass;
	}

	/**
	 * Returns the names of the classes that represent staged model data handlers associated with the portlet.
	 *
	 * @return the names of the classes that represent staged model data handlers associated with the portlet
	 */

	/**
	 * Sets the names of the classes that represent staged model data handlers
	 * associated with the portlet.
	 *
	 * @param stagedModelDataHandlerClasses the names of the classes that
	 *        represent staged model data handlers associated with the portlet
	 */
	@Override
	public void setStagedModelDataHandlerClasses(
		List<String> stagedModelDataHandlerClasses) {

		_stagedModelDataHandlerClasses = stagedModelDataHandlerClasses;
	}

	/**
	 * Set to <code>true</code> if the portlet is a static portlet that is
	 * cannot be moved.
	 *
	 * @param staticPortlet boolean value for whether the portlet is a static
	 *        portlet that cannot be moved
	 */
	@Override
	public void setStatic(boolean staticPortlet) {
		_staticPortlet = staticPortlet;
	}

	/**
	 * Set to <code>true</code> if the portlet is a static portlet at the start
	 * of a list of portlets.
	 *
	 * @param staticPortletStart boolean value for whether the portlet is a
	 *        static portlet at the start of a list of portlets
	 */
	@Override
	public void setStaticStart(boolean staticPortletStart) {
		_staticPortletStart = staticPortletStart;
	}

	/**
	 * Sets the struts path of the portlet.
	 *
	 * @param strutsPath the struts path of the portlet
	 */
	@Override
	public void setStrutsPath(String strutsPath) {
		_strutsPath = strutsPath;
	}

	/**
	 * Sets the supported locales of the portlet.
	 *
	 * @param supportedLocales the supported locales of the portlet
	 */
	@Override
	public void setSupportedLocales(Set<String> supportedLocales) {
		_supportedLocales = supportedLocales;
	}

	/**
	 * Set to <code>true</code> if the portlet is a system portlet that a user
	 * cannot manually add to their page.
	 *
	 * @param system boolean value for whether the portlet is a system portlet
	 *        that a user cannot manually add to their page
	 */
	@Override
	public void setSystem(boolean system) {
		_system = system;
	}

	/**
	 * Sets the name of the template handler class of the portlet.
	 *
	 * @param templateHandlerClass the name of template handler class of the
	 *        portlet
	 */
	@Override
	public void setTemplateHandlerClass(String templateHandlerClass) {
		_templateHandlerClass = templateHandlerClass;
	}

	/**
	 * Sets the names of the classes that represent trash handlers associated to
	 * the portlet.
	 *
	 * @param trashHandlerClasses the names of the classes that represent trash
	 *        handlers associated with the portlet
	 */
	@Override
	public void setTrashHandlerClasses(List<String> trashHandlerClasses) {
		_trashHandlerClasses = trashHandlerClasses;
	}

	/**
	 * Set to <code>true</code> if the portlet is an undeployed portlet.
	 *
	 * @param undeployedPortlet boolean value for whether the portlet is an
	 *        undeployed portlet
	 */
	@Override
	public void setUndeployedPortlet(boolean undeployedPortlet) {
		_undeployedPortlet = undeployedPortlet;
	}

	/**
	 * Sets the unlinked roles of the portlet.
	 *
	 * @param unlinkedRoles the unlinked roles of the portlet
	 */
	@Override
	public void setUnlinkedRoles(Set<String> unlinkedRoles) {
		_unlinkedRoles = unlinkedRoles;
	}

	/**
	 * Sets the name of the URL encoder class of the portlet.
	 *
	 * @param urlEncoderClass the name of the URL encoder class of the portlet
	 */
	@Override
	public void setURLEncoderClass(String urlEncoderClass) {
		_urlEncoderClass = urlEncoderClass;
	}

	/**
	 * Set to <code>true</code> if the portlet uses the default template.
	 *
	 * @param useDefaultTemplate boolean value for whether the portlet uses the
	 *        default template
	 */
	@Override
	public void setUseDefaultTemplate(boolean useDefaultTemplate) {
		_useDefaultTemplate = useDefaultTemplate;
	}

	/**
	 * Sets the class loader resource path to the user notification definitions
	 * of the portlet.
	 *
	 * @param userNotificationDefinitions the class loader resource path to the
	 *        user notification definitions of the portlet
	 */
	@Override
	public void setUserNotificationDefinitions(
		String userNotificationDefinitions) {

		_userNotificationDefinitions = userNotificationDefinitions;
	}

	/**
	 * Sets the names of the classes that represent user notification handlers
	 * associated with the portlet.
	 *
	 * @param userNotificationHandlerClasses the names of the classes that
	 *        represent user notification handlers associated with the portlet
	 */
	@Override
	public void setUserNotificationHandlerClasses(
		List<String> userNotificationHandlerClasses) {

		_userNotificationHandlerClasses = userNotificationHandlerClasses;
	}

	/**
	 * Sets the user principal strategy of the portlet.
	 *
	 * @param userPrincipalStrategy the user principal strategy of the portlet
	 */
	@Override
	public void setUserPrincipalStrategy(String userPrincipalStrategy) {
		if (Validator.isNotNull(userPrincipalStrategy)) {
			_userPrincipalStrategy = userPrincipalStrategy;
		}
	}

	/**
	 * Sets the virtual path of the portlet.
	 *
	 * @param virtualPath the virtual path of the portlet
	 */
	@Override
	public void setVirtualPath(String virtualPath) {
		if (_portletApp.isWARFile() && Validator.isNull(virtualPath)) {
			virtualPath = PropsValues.PORTLET_VIRTUAL_PATH;
		}

		_virtualPath = virtualPath;
	}

	/**
	 * Sets the name of the WebDAV storage class of the portlet.
	 *
	 * @param webDAVStorageClass the name of the WebDAV storage class of the
	 *        portlet
	 */
	@Override
	public void setWebDAVStorageClass(String webDAVStorageClass) {
		_webDAVStorageClass = webDAVStorageClass;
	}

	/**
	 * Sets the name of the WebDAV storage token of the portlet.
	 *
	 * @param webDAVStorageToken the name of the WebDAV storage token of the
	 *        portlet
	 */
	@Override
	public void setWebDAVStorageToken(String webDAVStorageToken) {
		_webDAVStorageToken = webDAVStorageToken;
	}

	/**
	 * Sets the window states of the portlet.
	 *
	 * @param windowStates the window states of the portlet
	 */
	@Override
	public void setWindowStates(Map<String, Set<String>> windowStates) {
		_windowStates = windowStates;
	}

	/**
	 * Sets the names of the classes that represent workflow handlers associated
	 * to the portlet.
	 *
	 * @param workflowHandlerClasses the names of the classes that represent
	 *        workflow handlers associated with the portlet
	 */
	@Override
	public void setWorkflowHandlerClasses(List<String> workflowHandlerClasses) {
		_workflowHandlerClasses = workflowHandlerClasses;
	}

	/**
	 * Sets the name of the XML-RPC method class of the portlet.
	 *
	 * @param xmlRpcMethodClass the name of the XML-RPC method class of the
	 *        portlet
	 */
	@Override
	public void setXmlRpcMethodClass(String xmlRpcMethodClass) {
		_xmlRpcMethodClass = xmlRpcMethodClass;
	}

	@Override
	public void unsetReady() {
		Readiness readiness = _readinessMap.remove(getRootPortletId());

		if (readiness != null) {
			synchronized (readiness) {
				ServiceRegistrar<Portlet> serviceRegistrar =
					readiness._serviceRegistrar;

				serviceRegistrar.destroy();
			}
		}
	}

	/**
	 * Log instance for this class.
	 */
	private static final Log _log = LogFactoryUtil.getLog(PortletImpl.class);

	/**
	 * Map of the ready states of all portlets keyed by their root portlet ID.
	 */
	private static final ConcurrentMap<String, Readiness> _readinessMap =
		new ConcurrentHashMap<>();

	/**
	 * The action timeout of the portlet.
	 */
	private int _actionTimeout;

	/**
	 * <code>True</code> if an action URL for this portlet should cause an auto
	 * redirect.
	 */
	private boolean _actionURLRedirect;

	/**
	 * <code>True</code> if default resources for the portlet are added to a
	 * page.
	 */
	private boolean _addDefaultResource;

	/**
	 * <code>True</code> if the portlet can be displayed via Ajax.
	 */
	private boolean _ajaxable = true;

	/**
	 * The application types of the portlet.
	 */
	private final Set<ApplicationType> _applicationTypes = new HashSet<>();

	/**
	 * The names of the classes that represents asset types associated with the
	 * portlet.
	 */
	private List<String> _assetRendererFactoryClasses;

	/**
	 * The names of the classes that represents atom collection adapters
	 * associated with the portlet.
	 */
	private List<String> _atomCollectionAdapterClasses;

	/**
	 * The names of the parameters that will be automatically propagated through
	 * the portlet.
	 */
	private Set<String> _autopropagatedParameters;

	/**
	 * The configuration action class of the portlet.
	 */
	private String _configurationActionClass;

	/**
	 * The name of the category of the Control Panel where this portlet will be
	 * shown.
	 */
	private String _controlPanelEntryCategory = StringPool.BLANK;

	/**
	 * The name of the class that will control when this portlet will be shown
	 * in the Control Panel.
	 */
	private String _controlPanelEntryClass;

	/**
	 * The relative weight of this portlet with respect to the other portlets in
	 * the same category of the Control Panel.
	 */
	private double _controlPanelEntryWeight = 100;

	/**
	 * The name of the CSS class that will be injected in the DIV that wraps
	 * this portlet.
	 */
	private String _cssClassWrapper = StringPool.BLANK;

	/**
	 * The names of the classes that represents custom attribute displays
	 * associated with the portlet.
	 */
	private List<String> _customAttributesDisplayClasses;

	/**
	 * Plugin settings associated with the portlet.
	 */
	private PluginSetting _defaultPluginSetting;

	/**
	 * The default preferences of the portlet.
	 */
	private String _defaultPreferences;

	/**
	 * The display name of the portlet.
	 */
	private String _displayName;

	/**
	 * The expiration cache of the portlet.
	 */
	private Integer _expCache;

	/**
	 * The Facebook integration method of the portlet.
	 */
	private String _facebookIntegration =
		PortletConstants.FACEBOOK_INTEGRATION_IFRAME;

	/**
	 * A list of CSS files that will be referenced from the page's footer
	 * relative to the portal's context path.
	 */
	private List<String> _footerPortalCss;

	/**
	 * A list of JavaScript files that will be referenced from the page's footer
	 * relative to the portal's context path.
	 */
	private List<String> _footerPortalJavaScript;

	/**
	 * A list of CSS files that will be referenced from the page's footer
	 * relative to the portlet's context path.
	 */
	private List<String> _footerPortletCss;

	/**
	 * A list of JavaScript files that will be referenced from the page's footer
	 * relative to the portlet's context path.
	 */
	private List<String> _footerPortletJavaScript;

	/**
	 * The name of the friendly URL mapper class of the portlet.
	 */
	private String _friendlyURLMapperClass;

	/**
	 * The name of the friendly URL mapping of the portlet.
	 */
	private String _friendlyURLMapping;

	/**
	 * The the class loader resource path to the friendly URL routes of the
	 * portlet.
	 */
	private String _friendlyURLRoutes;

	/**
	 * A list of CSS files that will be referenced from the page's header
	 * relative to the portal's context path.
	 */
	private List<String> _headerPortalCss;

	/**
	 * A list of JavaScript files that will be referenced from the page's header
	 * relative to the portal's context path.
	 */
	private List<String> _headerPortalJavaScript;

	/**
	 * A list of CSS files that will be referenced from the page's header
	 * relative to the portlet's context path.
	 */
	private List<String> _headerPortletCss;

	/**
	 * A list of JavaScript files that will be referenced from the page's header
	 * relative to the portlet's context path.
	 */
	private List<String> _headerPortletJavaScript;

	/**
	 * The icon of the portlet.
	 */
	private String _icon;

	/**
	 * <code>True</code> to include the portlet and make it available to be made
	 * active.
	 */
	private boolean _include = true;

	/**
	 * The names of the classes that represent indexers associated with the
	 * portlet.
	 */
	private List<String> _indexerClasses;

	/**
	 * The init parameters of the portlet.
	 */
	private Map<String, String> _initParams;

	/**
	 * <code>True</code> if the portlet can be added multiple times to a layout.
	 */
	private boolean _instanceable;

	/**
	 * <code>True</code> if the portlet can be cached within the layout.
	 */
	private boolean _layoutCacheable;

	/**
	 * <code>True</code> if the portlet goes into the maximized state when the
	 * user goes into the edit mode.
	 */
	private boolean _maximizeEdit;

	/**
	 * <code>True</code> if the portlet goes into the maximized state when the
	 * user goes into the help mode.
	 */
	private boolean _maximizeHelp;

	/**
	 * The name of the open search class of the portlet.
	 */
	private String _openSearchClass;

	/**
	 * The parent struts path of the portlet.
	 */
	private String _parentStrutsPath;

	/**
	 * The name of the permission propagator class of the portlet.
	 */
	private String _permissionPropagatorClass;

	/**
	 * Package to which this plugin belongs.
	 */
	private PluginPackage _pluginPackage;

	/**
	 * The name of the poller processor class of the portlet.
	 */
	private String _pollerProcessorClass;

	/**
	 * The name of the POP message listener class of the portlet.
	 */
	private String _popMessageListenerClass;

	/**
	 * <code>True</code> if the portlet goes into the pop up state when the user
	 * goes into the print mode.
	 */
	private boolean _popUpPrint = true;

	/**
	 * The application to which this portlet belongs.
	 */
	private PortletApp _portletApp;

	/**
	 * The name of the portlet class of the portlet.
	 */
	private String _portletClass;

	/**
	 * The name of the portlet data handler class of the portlet.
	 */
	private String _portletDataHandlerClass;

	/**
	 * The filters of the portlet.
	 */
	private Map<String, PortletFilter> _portletFilters;

	/**
	 * The portlet info of the portlet.
	 */
	private PortletInfo _portletInfo;

	/**
	 * The name of the portlet data layout listener class of the portlet.
	 */
	private String _portletLayoutListenerClass;

	/**
	 * The portlet modes of the portlet.
	 */
	private Map<String, Set<String>> _portletModes;

	/**
	 * The name of the portlet.
	 */
	private String _portletName;

	/**
	 * The name of the portlet URL class of the portlet.
	 */
	private String _portletURLClass;

	/**
	 * <code>True</code> if preferences are shared across the entire company.
	 */
	private boolean _preferencesCompanyWide;

	/**
	 * <code>True</code> if preferences are owned by the group when the portlet
	 * is shown in a group layout. <code>False</code> if preferences are owned
	 * by the user at all times.
	 */
	private boolean _preferencesOwnedByGroup = true;

	/**
	 * <code>True</code> if preferences are unique per layout.
	 */
	private boolean _preferencesUniquePerLayout = true;

	/**
	 * The name of the preferences validator class of the portlet.
	 */
	private String _preferencesValidator;

	/**
	 * <code>True</code> if the portlet does not share request attributes with
	 * the portal or portlets from another WAR.
	 */
	private boolean _privateRequestAttributes = true;

	/**
	 * <code>True</code> if the portlet does not share session attributes with
	 * the portal.
	 */
	private boolean _privateSessionAttributes = true;

	/**
	 * The processing events of the portlet.
	 */
	private final Set<QName> _processingEvents = new HashSet<>();

	/**
	 * Map of the processing events of the portlet keyed by the QName.
	 */
	private final Map<String, QName> _processingEventsByQName = new HashMap<>();

	/**
	 * The public render parameters of the portlet.
	 */
	private final Set<PublicRenderParameter> _publicRenderParameters =
		new HashSet<>();

	/**
	 * Map of the public render parameters of the portlet keyed by the
	 * identifier.
	 */
	private final Map<String, PublicRenderParameter>
		_publicRenderParametersByIdentifier = new HashMap<>();

	/**
	 * Map of the public render parameters of the portlet keyed by the QName.
	 */
	private final Map<String, PublicRenderParameter>
		_publicRenderParametersByQName = new HashMap<>();

	/**
	 * The publishing events of the portlet.
	 */
	private final Set<QName> _publishingEvents = new HashSet<>();

	/**
	 * <code>True</code> if the portlet supports remoting.
	 */
	private boolean _remoteable;

	/**
	 * The render timeout of the portlet.
	 */
	private int _renderTimeout;

	/**
	 * Render weight of the portlet.
	 */
	private int _renderWeight = 1;

	/**
	 * <code>True</code> if the portlet will only process namespaced parameters.
	 */
	private boolean _requiresNamespacedParameters = true;

	/**
	 * The resource bundle of the portlet.
	 */
	private String _resourceBundle;

	/**
	 * <code>True</code> if the portlet restores to the current view from the
	 * maximized state.
	 */
	private boolean _restoreCurrentView = true;

	/**
	 * The role mappers of the portlet.
	 */
	private Map<String, String> _roleMappers;

	/**
	 * An array of required roles of the portlet.
	 */
	private String[] _rolesArray = new String[0];

	/**
	 * The root portlet of this portlet instance.
	 */
	private final Portlet _rootPortlet;

	/**
	 * The scheduler entries of the portlet.
	 */
	private final List<SchedulerEntry> _schedulerEntries;

	/**
	 * <code>True</code> if the portlet supports scoping of data.
	 */
	private boolean _scopeable;

	/**
	 * <code>True</code> if users are shown that they do not have access to the
	 * portlet.
	 */
	private boolean _showPortletAccessDenied =
		PropsValues.LAYOUT_SHOW_PORTLET_ACCESS_DENIED;

	/**
	 * <code>True</code> if users are shown that the portlet is inactive.
	 */
	private boolean _showPortletInactive =
		PropsValues.LAYOUT_SHOW_PORTLET_INACTIVE;

	/**
	 * <code>True</code> if the portlet uses Single Page Application.
	 */
	private boolean _singlePageApplication = true;

	/**
	 * The names of the classes that represents social activity interpreters
	 * associated with the portlet.
	 */
	private List<String> _socialActivityInterpreterClasses;

	/**
	 * The name of the social request interpreter class of the portlet.
	 */
	private String _socialRequestInterpreterClass;

	/**
	 * The names of the classes that represent staged model data handlers
	 * associated with the portlet.
	 */
	private List<String> _stagedModelDataHandlerClasses;

	/**
	 * <code>True</code> if the portlet is a static portlet that is cannot be
	 * moved.
	 */
	private boolean _staticPortlet;

	/**
	 * <code>True</code> if the portlet is a static portlet at the start of a
	 * list of portlets.
	 */
	private boolean _staticPortletStart;

	/**
	 * The struts path of the portlet.
	 */
	private String _strutsPath;

	/**
	 * The supported locales of the portlet.
	 */
	private Set<String> _supportedLocales;

	/**
	 * <code>True</code> if the portlet is a system portlet that a user cannot
	 * manually add to their page.
	 */
	private boolean _system;

	/**
	 * The name of the display style handler class of the portlet.
	 */
	private String _templateHandlerClass;

	/**
	 * The timestamp of the portlet.
	 */
	private Long _timestamp;

	/**
	 * The names of the classes that represents trash handlers associated with
	 * the portlet.
	 */
	private List<String> _trashHandlerClasses;

	/**
	 * <code>True</code> if the portlet is an undeployed portlet.
	 */
	private boolean _undeployedPortlet;

	/**
	 * The unlinked roles of the portlet.
	 */
	private Set<String> _unlinkedRoles;

	/**
	 * The name of the URL encoder class of the portlet.
	 */
	private String _urlEncoderClass;

	/**
	 * <code>True</code> if the portlet uses the default template.
	 */
	private boolean _useDefaultTemplate = true;

	/**
	 * The the class loader resource path to the user notification definitions
	 * of the portlet.
	 */
	private String _userNotificationDefinitions;

	/**
	 * The names of the classes that represents user notification handlers
	 * associated with the portlet.
	 */
	private List<String> _userNotificationHandlerClasses;

	/**
	 * The user principal strategy of the portlet.
	 */
	private String _userPrincipalStrategy =
		PortletConstants.USER_PRINCIPAL_STRATEGY_USER_ID;

	/**
	 * The virtual path of the portlet.
	 */
	private String _virtualPath;

	/**
	 * The name of the WebDAV storage class of the portlet.
	 */
	private String _webDAVStorageClass;

	/**
	 * The name of the WebDAV storage token of the portlet.
	 */
	private String _webDAVStorageToken;

	/**
	 * The window states of the portlet.
	 */
	private Map<String, Set<String>> _windowStates;

	/**
	 * The names of the classes that represents workflow handlers associated
	 * with the portlet.
	 */
	private List<String> _workflowHandlerClasses;

	/**
	 * The name of the XML-RPC method class of the portlet.
	 */
	private String _xmlRpcMethodClass;

	private static class Readiness {

		private Readiness(
			boolean ready, ServiceRegistrar<Portlet> serviceRegistrar) {

			_ready = ready;
			_serviceRegistrar = serviceRegistrar;
		}

		private volatile boolean _ready;
		private ServiceRegistrar<Portlet> _serviceRegistrar;

	}

}