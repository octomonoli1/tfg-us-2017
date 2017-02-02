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

package com.liferay.portal.kernel.theme;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PortletInstance;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portlet.configuration.icon.PortletConfigurationIconMenu;
import com.liferay.portal.kernel.portlet.toolbar.PortletToolbar;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;

import javax.portlet.PortletPreferences;

/**
 * Provides general configuration methods for the portlet, providing access to
 * the portlet's content, instance, theme, URLs, and more. This class contains
 * contextual information about the currently rendered portlet. An object of
 * this class is only available in the context of a single portlet and is not
 * available in the context of any page.
 *
 * @author Brian Wing Shun Chan
 * @author Eduardo Lundgren
 */
public class PortletDisplay implements Cloneable, Serializable {

	public PortletDisplay() {
		if (_log.isDebugEnabled()) {
			_log.debug("Creating new instance " + hashCode());
		}
	}

	@Override
	public Object clone() {
		PortletDisplay portletDisplay = new PortletDisplay();

		portletDisplay.copyFrom(this);

		return portletDisplay;
	}

	public void copyFrom(PortletDisplay master) {
		_active = master.isActive();
		_columnCount = master.getColumnCount();
		_columnId = master.getColumnId();
		_columnPos = master.getColumnPos();
		_content = master.getContent();
		_customCSSClassName = master.getCustomCSSClassName();
		_description = master.getDescription();
		_id = master.getId();
		_instanceId = master.getInstanceId();
		_modeAbout = master.isModeAbout();
		_modeConfig = master.isModeConfig();
		_modeEdit = master.isModeEdit();
		_modeEditDefaults = master.isModeEditDefaults();
		_modeEditGuest = master.isModeEditGuest();
		_modeHelp = master.isModeHelp();
		_modePreview = master.isModePreview();
		_modePrint = master.isModePrint();
		_modeView = master.isModeView();
		_namespace = master.getNamespace();
		_portletConfigurationIconMenu =
			master.getPortletConfigurationIconMenu();
		_portletDecorate = master.isPortletDecorate();
		_portletDecoratorId = master.getPortletDecoratorId();
		_portletDisplayName = master.getPortletDisplayName();
		_portletName = master.getPortletName();
		_portletResource = master.getPortletResource();
		_portletSetup = master.getPortletSetup();
		_portletToolbar = master.getPortletToolbar();
		_resourcePK = master.getResourcePK();
		_restoreCurrentView = master.isRestoreCurrentView();
		_rootPortletId = master.getRootPortletId();
		_showBackIcon = master.isShowBackIcon();
		_showCloseIcon = master.isShowCloseIcon();
		_showConfigurationIcon = master.isShowConfigurationIcon();
		_showEditDefaultsIcon = master.isShowEditDefaultsIcon();
		_showEditGuestIcon = master.isShowEditGuestIcon();
		_showEditIcon = master.isShowEditIcon();
		_showExportImportIcon = master.isShowExportImportIcon();
		_showHelpIcon = master.isShowHelpIcon();
		_showMoveIcon = master.isShowMoveIcon();
		_showPortletCssIcon = master.isShowPortletCssIcon();
		_showPortletIcon = master.isShowPortletIcon();
		_showPrintIcon = master.isShowPrintIcon();
		_showRefreshIcon = master.isShowRefreshIcon();
		_showStagingIcon = master.isShowStagingIcon();
		_stateExclusive = master.isStateExclusive();
		_stateMax = master.isStateMax();
		_stateMin = master.isStateMin();
		_stateNormal = master.isStateNormal();
		_statePopUp = master.isStatePopUp();
		_themeDisplay = master.getThemeDisplay();
		_title = master.getTitle();
		_urlBack = master.getURLBack();
		_urlClose = master.getURLClose();
		_urlConfiguration = master.getURLConfiguration();
		_urlConfigurationJS = master.getURLConfigurationJS();
		_urlEdit = master.getURLEdit();
		_urlEditDefaults = master.getURLEditDefaults();
		_urlEditGuest = master.getURLEditGuest();
		_urlExportImport = master.getURLExportImport();
		_urlHelp = master.getURLHelp();
		_urlMax = master.getURLMax();
		_urlMin = master.getURLMin();
		_urlPortlet = master.getURLPortlet();
		_urlPortletCss = master.getURLPortletCss();
		_urlPrint = master.getURLPrint();
		_urlRefresh = master.getURLRefresh();
		_urlStaging = master.getURLStaging();
		_webDAVEnabled = master.isWebDAVEnabled();
	}

	public void copyTo(PortletDisplay slave) {
		slave.setActive(_active);
		slave.setColumnCount(_columnCount);
		slave.setColumnId(_columnId);
		slave.setColumnPos(_columnPos);
		slave.setContent(_content);
		slave.setCustomCSSClassName(_customCSSClassName);
		slave.setDescription(_description);
		slave.setId(_id);
		slave.setInstanceId(_instanceId);
		slave.setModeAbout(_modeAbout);
		slave.setModeConfig(_modeConfig);
		slave.setModeEdit(_modeEdit);
		slave.setModeEditDefaults(_modeEditDefaults);
		slave.setModeEditGuest(_modeEditGuest);
		slave.setModeHelp(_modeHelp);
		slave.setModePreview(_modePreview);
		slave.setModePrint(_modePrint);
		slave.setModeView(_modeView);
		slave.setNamespace(_namespace);
		slave.setPortletConfigurationIconMenu(_portletConfigurationIconMenu);
		slave.setPortletDecorate(_portletDecorate);
		slave.setPortletDecoratorId(_portletDecoratorId);
		slave.setPortletDisplayName(_portletDisplayName);
		slave.setPortletName(_portletName);
		slave.setPortletResource(_portletResource);
		slave.setPortletSetup(_portletSetup);
		slave.setPortletToolbar(_portletToolbar);
		slave.setResourcePK(_resourcePK);
		slave.setRestoreCurrentView(_restoreCurrentView);
		slave.setRootPortletId(_rootPortletId);
		slave.setShowBackIcon(_showBackIcon);
		slave.setShowCloseIcon(_showCloseIcon);
		slave.setShowConfigurationIcon(_showConfigurationIcon);
		slave.setShowEditDefaultsIcon(_showEditDefaultsIcon);
		slave.setShowEditGuestIcon(_showEditGuestIcon);
		slave.setShowEditIcon(_showEditIcon);
		slave.setShowExportImportIcon(_showExportImportIcon);
		slave.setShowHelpIcon(_showHelpIcon);
		slave.setShowMoveIcon(_showMoveIcon);
		slave.setShowPortletCssIcon(_showPortletCssIcon);
		slave.setShowPortletIcon(_showPortletIcon);
		slave.setShowPrintIcon(_showPrintIcon);
		slave.setShowRefreshIcon(_showRefreshIcon);
		slave.setShowStagingIcon(_showStagingIcon);
		slave.setStateExclusive(_stateExclusive);
		slave.setStateMax(_stateMax);
		slave.setStateMin(_stateMin);
		slave.setStateNormal(_stateNormal);
		slave.setStatePopUp(_statePopUp);
		slave.setThemeDisplay(_themeDisplay);
		slave.setURLBack(_urlBack);
		slave.setURLClose(_urlClose);
		slave.setURLConfiguration(_urlConfiguration);
		slave.setURLConfigurationJS(_urlConfigurationJS);
		slave.setURLEdit(_urlEdit);
		slave.setURLEditDefaults(_urlEditDefaults);
		slave.setURLEditGuest(_urlEditGuest);
		slave.setURLExportImport(_urlExportImport);
		slave.setURLHelp(_urlHelp);
		slave.setURLMax(_urlMax);
		slave.setURLMin(_urlMin);
		slave.setURLPortlet(_urlPortlet);
		slave.setURLPortletCss(_urlPortletCss);
		slave.setURLPrint(_urlPrint);
		slave.setURLRefresh(_urlRefresh);
		slave.setURLStaging(_urlStaging);
		slave.setWebDAVEnabled(_webDAVEnabled);

		slave._title = _title;
	}

	public int getColumnCount() {
		return _columnCount;
	}

	public String getColumnId() {
		return _columnId;
	}

	public int getColumnPos() {
		return _columnPos;
	}

	public StringBundler getContent() {
		return _content;
	}

	public String getCustomCSSClassName() {
		return _customCSSClassName;
	}

	public String getDescription() {
		return _description;
	}

	public String getId() {
		return _id;
	}

	public String getInstanceId() {
		return _instanceId;
	}

	public String getNamespace() {
		return _namespace;
	}

	public PortletConfigurationIconMenu getPortletConfigurationIconMenu() {
		return _portletConfigurationIconMenu;
	}

	public String getPortletDecoratorId() {
		return _portletDecoratorId;
	}

	public String getPortletDisplayName() {
		if (Validator.isNull(_portletDisplayName)) {
			return _title;
		}

		return _portletDisplayName;
	}

	public <T> T getPortletInstanceConfiguration(Class<T> clazz)
		throws ConfigurationException {

		String portletId = Validator.isNull(
			_portletResource) ? _id : _portletResource;

		PortletInstance portletInstance =
			PortletInstance.fromPortletInstanceKey(portletId);

		return ConfigurationProviderUtil.getPortletInstanceConfiguration(
			clazz, _themeDisplay.getLayout(), portletInstance);
	}

	public String getPortletName() {
		return _portletName;
	}

	public String getPortletResource() {
		return _portletResource;
	}

	public PortletPreferences getPortletSetup() {
		return _portletSetup;
	}

	public PortletToolbar getPortletToolbar() {
		return _portletToolbar;
	}

	public String getResourcePK() {
		return _resourcePK;
	}

	public String getRootPortletId() {
		return _rootPortletId;
	}

	public ThemeDisplay getThemeDisplay() {
		return _themeDisplay;
	}

	public String getTitle() {
		return _title;
	}

	public String getURLBack() {
		return _urlBack;
	}

	public String getURLClose() {
		return _urlClose;
	}

	public String getURLConfiguration() {
		return _urlConfiguration;
	}

	public String getURLConfigurationJS() {
		return _urlConfigurationJS;
	}

	public String getURLEdit() {
		return _urlEdit;
	}

	public String getURLEditDefaults() {
		return _urlEditDefaults;
	}

	public String getURLEditGuest() {
		return _urlEditGuest;
	}

	public String getURLExportImport() {
		return _urlExportImport;
	}

	public String getURLHelp() {
		return _urlHelp;
	}

	public String getURLMax() {
		return _urlMax;
	}

	public String getURLMin() {
		return _urlMin;
	}

	public String getURLPortlet() {
		return _urlPortlet;
	}

	public String getURLPortletCss() {
		return _urlPortletCss;
	}

	public String getURLPrint() {
		return _urlPrint;
	}

	public String getURLRefresh() {
		return _urlRefresh;
	}

	public String getURLStaging() {
		return _urlStaging;
	}

	/**
	 * @deprecated As of 6.2.0, with no direct replacement
	 */
	@Deprecated
	public boolean isAccess() {
		return true;
	}

	public boolean isActive() {
		return _active;
	}

	public boolean isFocused() {
		return _id.equals(_themeDisplay.getPpid());
	}

	public boolean isModeAbout() {
		return _modeAbout;
	}

	public boolean isModeConfig() {
		return _modeConfig;
	}

	public boolean isModeEdit() {
		return _modeEdit;
	}

	public boolean isModeEditDefaults() {
		return _modeEditDefaults;
	}

	public boolean isModeEditGuest() {
		return _modeEditGuest;
	}

	public boolean isModeHelp() {
		return _modeHelp;
	}

	public boolean isModePreview() {
		return _modePreview;
	}

	public boolean isModePrint() {
		return _modePrint;
	}

	public boolean isModeView() {
		return _modeView;
	}

	public boolean isPortletDecorate() {
		return _portletDecorate;
	}

	public boolean isRestoreCurrentView() {
		return _restoreCurrentView;
	}

	public boolean isShowBackIcon() {
		return _showBackIcon;
	}

	public boolean isShowCloseIcon() {
		return _showCloseIcon;
	}

	public boolean isShowConfigurationIcon() {
		return _showConfigurationIcon;
	}

	public boolean isShowEditDefaultsIcon() {
		return _showEditDefaultsIcon;
	}

	public boolean isShowEditGuestIcon() {
		return _showEditGuestIcon;
	}

	public boolean isShowEditIcon() {
		return _showEditIcon;
	}

	public boolean isShowExportImportIcon() {
		return _showExportImportIcon;
	}

	public boolean isShowHelpIcon() {
		return _showHelpIcon;
	}

	public boolean isShowMoveIcon() {
		return _showMoveIcon;
	}

	public boolean isShowPortletCssIcon() {
		return _showPortletCssIcon;
	}

	public boolean isShowPortletIcon() {
		return _showPortletIcon;
	}

	public boolean isShowPrintIcon() {
		return _showPrintIcon;
	}

	public boolean isShowRefreshIcon() {
		return _showRefreshIcon;
	}

	public boolean isShowStagingIcon() {
		return _showStagingIcon;
	}

	public boolean isStateExclusive() {
		return _stateExclusive;
	}

	public boolean isStateMax() {
		return _stateMax;
	}

	public boolean isStateMin() {
		return _stateMin;
	}

	public boolean isStateNormal() {
		return _stateNormal;
	}

	public boolean isStatePopUp() {
		return _statePopUp;
	}

	public boolean isWebDAVEnabled() {
		return _webDAVEnabled;
	}

	public void recycle() {
		if (_log.isDebugEnabled()) {
			_log.debug("Recycling instance " + hashCode());
		}

		_active = false;
		_columnCount = 0;
		_columnId = StringPool.BLANK;
		_columnPos = 0;
		_content.setIndex(0);
		_customCSSClassName = StringPool.BLANK;
		_description = StringPool.BLANK;
		_id = StringPool.BLANK;
		_instanceId = StringPool.BLANK;
		_modeAbout = false;
		_modeConfig = false;
		_modeEdit = false;
		_modeEditDefaults = false;
		_modeEditGuest = false;
		_modeHelp = false;
		_modePreview = false;
		_modePrint = false;
		_modeView = false;
		_namespace = StringPool.BLANK;
		_portletDisplayName = StringPool.BLANK;
		_portletName = StringPool.BLANK;
		_portletSetup = null;
		_resourcePK = StringPool.BLANK;
		_restoreCurrentView = false;
		_rootPortletId = StringPool.BLANK;
		_showBackIcon = false;
		_showCloseIcon = false;
		_showConfigurationIcon = false;
		_showEditDefaultsIcon = false;
		_showEditGuestIcon = false;
		_showEditIcon = false;
		_showExportImportIcon = false;
		_showHelpIcon = false;
		_showMoveIcon = false;
		_showPortletCssIcon = false;
		_showPortletIcon = false;
		_showPrintIcon = false;
		_showRefreshIcon = false;
		_showStagingIcon = false;
		_stateExclusive = false;
		_stateMax = false;
		_stateMin = false;
		_stateNormal = false;
		_statePopUp = false;
		_title = StringPool.BLANK;
		_urlBack = StringPool.BLANK;
		_urlClose = StringPool.BLANK;
		_urlConfiguration = StringPool.BLANK;
		_urlEdit = StringPool.BLANK;
		_urlEditDefaults = StringPool.BLANK;
		_urlEditGuest = StringPool.BLANK;
		_urlExportImport = StringPool.BLANK;
		_urlHelp = StringPool.BLANK;
		_urlMax = StringPool.BLANK;
		_urlMin = StringPool.BLANK;
		_urlPortlet = StringPool.BLANK;
		_urlPortletCss = StringPool.BLANK;
		_urlPrint = StringPool.BLANK;
		_urlRefresh = StringPool.BLANK;
		_webDAVEnabled = false;
	}

	/**
	 * @deprecated As of 6.2.0, with no direct replacement
	 */
	@Deprecated
	public void setAccess(boolean access) {
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public void setColumnCount(int columnCount) {
		_columnCount = columnCount;
	}

	public void setColumnId(String columnId) {
		_columnId = columnId;
	}

	public void setColumnPos(int columnPos) {
		_columnPos = columnPos;
	}

	public void setContent(StringBundler content) {
		if (content == null) {
			_content = _blankStringBundler;
		}
		else {
			_content = content;
		}
	}

	public void setCustomCSSClassName(String customCSSClassName) {
		_customCSSClassName = customCSSClassName;
	}

	public void setDescription(String description) {
		description = HtmlUtil.escape(description);

		_description = description;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setInstanceId(String instanceId) {
		_instanceId = instanceId;
	}

	public void setModeAbout(boolean modeAbout) {
		_modeAbout = modeAbout;
	}

	public void setModeConfig(boolean modeConfig) {
		_modeConfig = modeConfig;
	}

	public void setModeEdit(boolean modeEdit) {
		_modeEdit = modeEdit;
	}

	public void setModeEditDefaults(boolean modeEditDefaults) {
		_modeEditDefaults = modeEditDefaults;
	}

	public void setModeEditGuest(boolean modeEditGuest) {
		_modeEditGuest = modeEditGuest;
	}

	public void setModeHelp(boolean modeHelp) {
		_modeHelp = modeHelp;
	}

	public void setModePreview(boolean modePreview) {
		_modePreview = modePreview;
	}

	public void setModePrint(boolean modePrint) {
		_modePrint = modePrint;
	}

	public void setModeView(boolean modeView) {
		_modeView = modeView;
	}

	public void setNamespace(String namespace) {
		_namespace = namespace;
	}

	public void setPortletConfigurationIconMenu(
		PortletConfigurationIconMenu portletConfigurationIconMenu) {

		_portletConfigurationIconMenu = portletConfigurationIconMenu;
	}

	public void setPortletDecorate(boolean portletDecorate) {
		_portletDecorate = portletDecorate;
	}

	public void setPortletDecoratorId(String portletDecoratorId) {
		_portletDecoratorId = portletDecoratorId;
	}

	public void setPortletDisplayName(String portletDisplayName) {
		_portletDisplayName = portletDisplayName;
	}

	public void setPortletName(String portletName) {
		_portletName = portletName;
	}

	public void setPortletResource(String portletResource) {
		_portletResource = portletResource;
	}

	public void setPortletSetup(PortletPreferences portletSetup) {
		_portletSetup = portletSetup;
	}

	public void setPortletToolbar(PortletToolbar portletToolbar) {
		_portletToolbar = portletToolbar;
	}

	public void setResourcePK(String resourcePK) {
		_resourcePK = resourcePK;
	}

	public void setRestoreCurrentView(boolean restoreCurrentView) {
		_restoreCurrentView = restoreCurrentView;
	}

	public void setRootPortletId(String rootPortletId) {
		_rootPortletId = rootPortletId;
	}

	public void setShowBackIcon(boolean showBackIcon) {
		_showBackIcon = showBackIcon;
	}

	public void setShowCloseIcon(boolean showCloseIcon) {
		_showCloseIcon = showCloseIcon;
	}

	public void setShowConfigurationIcon(boolean showConfigurationIcon) {
		_showConfigurationIcon = showConfigurationIcon;
	}

	public void setShowEditDefaultsIcon(boolean showEditDefaultsIcon) {
		_showEditDefaultsIcon = showEditDefaultsIcon;
	}

	public void setShowEditGuestIcon(boolean showEditGuestIcon) {
		_showEditGuestIcon = showEditGuestIcon;
	}

	public void setShowEditIcon(boolean showEditIcon) {
		_showEditIcon = showEditIcon;
	}

	public void setShowExportImportIcon(boolean showExportImportIcon) {
		_showExportImportIcon = showExportImportIcon;
	}

	public void setShowHelpIcon(boolean showHelpIcon) {
		_showHelpIcon = showHelpIcon;
	}

	public void setShowMoveIcon(boolean showMoveIcon) {
		_showMoveIcon = showMoveIcon;
	}

	public void setShowPortletCssIcon(boolean showPortletCssIcon) {
		_showPortletCssIcon = showPortletCssIcon;
	}

	public void setShowPortletIcon(boolean showPortletIcon) {
		_showPortletIcon = showPortletIcon;
	}

	public void setShowPrintIcon(boolean showPrintIcon) {
		_showPrintIcon = showPrintIcon;
	}

	public void setShowRefreshIcon(boolean showRefreshIcon) {
		_showRefreshIcon = showRefreshIcon;
	}

	public void setShowStagingIcon(boolean showStagingIcon) {
		_showStagingIcon = showStagingIcon;
	}

	public void setStateExclusive(boolean stateExclusive) {
		_stateExclusive = stateExclusive;
	}

	public void setStateMax(boolean stateMax) {
		_stateMax = stateMax;
	}

	public void setStateMin(boolean stateMin) {
		_stateMin = stateMin;
	}

	public void setStateNormal(boolean stateNormal) {
		_stateNormal = stateNormal;
	}

	public void setStatePopUp(boolean statePopUp) {
		_statePopUp = statePopUp;
	}

	public void setThemeDisplay(ThemeDisplay themeDisplay) {
		_themeDisplay = themeDisplay;
	}

	public void setTitle(String title) {
		_title = title;

		// LEP-5317

		if (Validator.isNull(_id)) {
			setId(_themeDisplay.getTilesTitle());
		}
	}

	public void setURLBack(String urlBack) {
		_urlBack = urlBack;
	}

	public void setURLClose(String urlClose) {
		_urlClose = urlClose;
	}

	public void setURLConfiguration(String urlConfiguration) {
		_urlConfiguration = urlConfiguration;
	}

	public void setURLConfigurationJS(String urlConfigurationJS) {
		_urlConfigurationJS = urlConfigurationJS;
	}

	public void setURLEdit(String urlEdit) {
		_urlEdit = urlEdit;
	}

	public void setURLEditDefaults(String urlEditDefaults) {
		_urlEditDefaults = urlEditDefaults;
	}

	public void setURLEditGuest(String urlEditGuest) {
		_urlEditGuest = urlEditGuest;
	}

	public void setURLExportImport(String urlExportImport) {
		_urlExportImport = urlExportImport;
	}

	public void setURLHelp(String urlHelp) {
		_urlHelp = urlHelp;
	}

	public void setURLMax(String urlMax) {
		_urlMax = urlMax;
	}

	public void setURLMin(String urlMin) {
		_urlMin = urlMin;
	}

	public void setURLPortlet(String urlPortlet) {
		_urlPortlet = urlPortlet;
	}

	public void setURLPortletCss(String urlPortletCss) {
		_urlPortletCss = urlPortletCss;
	}

	public void setURLPrint(String urlPrint) {
		_urlPrint = urlPrint;
	}

	public void setURLRefresh(String urlRefresh) {
		_urlRefresh = urlRefresh;
	}

	public void setURLStaging(String urlStaging) {
		_urlStaging = urlStaging;
	}

	public void setWebDAVEnabled(boolean webDAVEnabled) {
		_webDAVEnabled = webDAVEnabled;
	}

	public void writeContent(Writer writer) throws IOException {
		_content.writeTo(writer);
	}

	private static final Log _log = LogFactoryUtil.getLog(PortletDisplay.class);

	private static final StringBundler _blankStringBundler = new StringBundler(
		StringPool.BLANK);

	private boolean _active;
	private int _columnCount;
	private String _columnId = StringPool.BLANK;
	private int _columnPos;
	private StringBundler _content = _blankStringBundler;
	private String _customCSSClassName = StringPool.BLANK;
	private String _description = StringPool.BLANK;
	private String _id = StringPool.BLANK;
	private String _instanceId = StringPool.BLANK;
	private boolean _modeAbout;
	private boolean _modeConfig;
	private boolean _modeEdit;
	private boolean _modeEditDefaults;
	private boolean _modeEditGuest;
	private boolean _modeHelp;
	private boolean _modePreview;
	private boolean _modePrint;
	private boolean _modeView;
	private String _namespace = StringPool.BLANK;
	private PortletConfigurationIconMenu _portletConfigurationIconMenu;
	private boolean _portletDecorate;
	private String _portletDecoratorId = StringPool.BLANK;
	private String _portletDisplayName = StringPool.BLANK;
	private String _portletName = StringPool.BLANK;
	private String _portletResource = StringPool.BLANK;
	private PortletPreferences _portletSetup;
	private PortletToolbar _portletToolbar;
	private String _resourcePK = StringPool.BLANK;
	private boolean _restoreCurrentView;
	private String _rootPortletId = StringPool.BLANK;
	private boolean _showBackIcon;
	private boolean _showCloseIcon;
	private boolean _showConfigurationIcon;
	private boolean _showEditDefaultsIcon;
	private boolean _showEditGuestIcon;
	private boolean _showEditIcon;
	private boolean _showExportImportIcon;
	private boolean _showHelpIcon;
	private boolean _showMoveIcon;
	private boolean _showPortletCssIcon;
	private boolean _showPortletIcon;
	private boolean _showPrintIcon;
	private boolean _showRefreshIcon;
	private boolean _showStagingIcon;
	private boolean _stateExclusive;
	private boolean _stateMax;
	private boolean _stateMin;
	private boolean _stateNormal;
	private boolean _statePopUp;
	private ThemeDisplay _themeDisplay;
	private String _title = StringPool.BLANK;
	private String _urlBack = StringPool.BLANK;
	private String _urlClose = StringPool.BLANK;
	private String _urlConfiguration = StringPool.BLANK;
	private String _urlConfigurationJS = StringPool.BLANK;
	private String _urlEdit = StringPool.BLANK;
	private String _urlEditDefaults = StringPool.BLANK;
	private String _urlEditGuest = StringPool.BLANK;
	private String _urlExportImport = StringPool.BLANK;
	private String _urlHelp = StringPool.BLANK;
	private String _urlMax = StringPool.BLANK;
	private String _urlMin = StringPool.BLANK;
	private String _urlPortlet = StringPool.BLANK;
	private String _urlPortletCss = StringPool.BLANK;
	private String _urlPrint = StringPool.BLANK;
	private String _urlRefresh = StringPool.BLANK;
	private String _urlStaging = StringPool.BLANK;
	private boolean _webDAVEnabled;

}