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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ColorScheme;
import com.liferay.portal.kernel.model.Plugin;
import com.liferay.portal.kernel.model.PortletDecorator;
import com.liferay.portal.kernel.model.SpriteImage;
import com.liferay.portal.kernel.model.Theme;
import com.liferay.portal.kernel.model.ThemeSetting;
import com.liferay.portal.kernel.servlet.PortalWebResourceConstants;
import com.liferay.portal.kernel.servlet.PortalWebResourcesUtil;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.theme.ThemeCompanyId;
import com.liferay.portal.kernel.theme.ThemeCompanyLimit;
import com.liferay.portal.kernel.theme.ThemeGroupLimit;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.ThemeHelper;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsValues;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 * @author Julio Camarero
 * @author Raymond Augé
 */
public class ThemeImpl extends PluginBaseImpl implements Theme {

	public ThemeImpl() {
		this(null);
	}

	public ThemeImpl(String themeId) {
		this(themeId, null);
	}

	public ThemeImpl(String themeId, String name) {
		_themeId = themeId;
		_name = name;
	}

	@Override
	public void addSetting(
		String key, String value, boolean configurable, String type,
		String[] options, String script) {

		ThemeSetting themeSetting = new ThemeSettingImpl(
			configurable, options, script, type, value);

		_themeSettingsMap.put(key, themeSetting);
	}

	@Override
	public int compareTo(Theme theme) {
		return getName().compareTo(theme.getName());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Theme)) {
			return false;
		}

		Theme theme = (Theme)obj;

		String themeId = theme.getThemeId();

		if (getThemeId().equals(themeId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public List<ColorScheme> getColorSchemes() {
		List<ColorScheme> colorSchemes = ListUtil.fromMapValues(
			_colorSchemesMap);

		return ListUtil.sort(colorSchemes);
	}

	@Override
	public Map<String, ColorScheme> getColorSchemesMap() {
		return _colorSchemesMap;
	}

	@Override
	public Map<String, ThemeSetting> getConfigurableSettings() {
		Map<String, ThemeSetting> configurableSettings = new LinkedHashMap<>();

		for (Map.Entry<String, ThemeSetting> entry :
				_themeSettingsMap.entrySet()) {

			ThemeSetting themeSetting = entry.getValue();

			if (themeSetting.isConfigurable()) {
				configurableSettings.put(entry.getKey(), entry.getValue());
			}
		}

		return configurableSettings;
	}

	@Override
	public String getContextPath() {
		if (!isWARFile()) {
			return PortalUtil.getPathContext();
		}

		String servletContextName = getServletContextName();

		if (ServletContextPool.containsKey(servletContextName)) {
			ServletContext servletContext = ServletContextPool.get(
				servletContextName);

			String proxyPath = PortalUtil.getPathProxy();

			return proxyPath.concat(servletContext.getContextPath());
		}

		String portalPathContext = PortalUtil.getPathContext();

		return portalPathContext.concat(
			StringPool.SLASH.concat(servletContextName));
	}

	@Override
	public String getCssPath() {
		return _cssPath;
	}

	@Override
	public String getDevice() {
		return "regular";
	}

	@Override
	public String getFreeMarkerTemplateLoader() {
		if (_loadFromServletContext) {
			return TemplateConstants.SERVLET_SEPARATOR;
		}
		else {
			return TemplateConstants.THEME_LOADER_SEPARATOR;
		}
	}

	@Override
	public String getImagesPath() {
		return _imagesPath;
	}

	@Override
	public String getJavaScriptPath() {
		return _javaScriptPath;
	}

	@Override
	public boolean getLoadFromServletContext() {
		return _loadFromServletContext;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getPluginId() {
		return getThemeId();
	}

	@Override
	public String getPluginType() {
		return Plugin.TYPE_THEME;
	}

	@Override
	public List<PortletDecorator> getPortletDecorators() {
		List<PortletDecorator> portletDecorators = ListUtil.fromMapValues(
			_portletDecoratorsMap);

		return ListUtil.sort(portletDecorators);
	}

	@Override
	public Map<String, PortletDecorator> getPortletDecoratorsMap() {
		return _portletDecoratorsMap;
	}

	@Override
	public String getResourcePath(
		ServletContext servletContext, String portletId, String path) {

		if (!PropsValues.LAYOUT_TEMPLATE_CACHE_ENABLED) {
			return ThemeHelper.getResourcePath(
				servletContext, this, portletId, path);
		}

		String key = path;

		if (Validator.isNotNull(portletId)) {
			key = path.concat(StringPool.POUND).concat(portletId);
		}

		String resourcePath = _resourcePathsMap.get(key);

		if (resourcePath != null) {
			return resourcePath;
		}

		resourcePath = ThemeHelper.getResourcePath(
			servletContext, this, portletId, path);

		_resourcePathsMap.put(key, resourcePath);

		return resourcePath;
	}

	@Override
	public String getRootPath() {
		return _rootPath;
	}

	@Override
	public String getServletContextName() {
		return _servletContextName;
	}

	@Override
	public String getSetting(String key) {
		String value = null;

		ThemeSetting themeSetting = _themeSettingsMap.get(key);

		if (themeSetting != null) {
			value = themeSetting.getValue();
		}

		return value;
	}

	@Override
	public String[] getSettingOptions(String key) {
		String[] options = null;

		ThemeSetting themeSetting = _themeSettingsMap.get(key);

		if (themeSetting != null) {
			options = themeSetting.getOptions();
		}

		return options;
	}

	@Override
	public Map<String, ThemeSetting> getSettings() {
		return _themeSettingsMap;
	}

	@Override
	public Properties getSettingsProperties() {
		Properties properties = new Properties();

		for (Map.Entry<String, ThemeSetting> entry :
				_themeSettingsMap.entrySet()) {

			ThemeSetting setting = entry.getValue();

			if (setting != null) {
				properties.setProperty(entry.getKey(), setting.getValue());
			}
		}

		return properties;
	}

	@Override
	public SpriteImage getSpriteImage(String fileName) {
		return _spriteImagesMap.get(fileName);
	}

	@Override
	public String getStaticResourcePath() {
		String proxyPath = PortalUtil.getPathProxy();

		String virtualPath = getVirtualPath();

		if (Validator.isNotNull(virtualPath)) {
			return proxyPath.concat(virtualPath);
		}

		if (isWARFile()) {
			return getContextPath();
		}

		String contextPath = null;

		if (_themeId.equals("admin")) {
			contextPath = PortalWebResourcesUtil.getModuleContextPath(
				PortalWebResourceConstants.RESOURCE_TYPE_THEME_ADMIN);
		}
		else if (_themeId.equals("classic")) {
			contextPath = PortalWebResourcesUtil.getModuleContextPath(
				PortalWebResourceConstants.RESOURCE_TYPE_THEME_CLASSIC);
		}

		if (Validator.isNull(contextPath)) {
			return proxyPath;
		}

		return proxyPath.concat(contextPath);
	}

	@Override
	public String getTemplateExtension() {
		return _templateExtension;
	}

	@Override
	public String getTemplatesPath() {
		return _templatesPath;
	}

	@Override
	public ThemeCompanyLimit getThemeCompanyLimit() {
		return _themeCompanyLimit;
	}

	@Override
	public ThemeGroupLimit getThemeGroupLimit() {
		return _themeGroupLimit;
	}

	@Override
	public String getThemeId() {
		return _themeId;
	}

	@Override
	public long getTimestamp() {
		return _timestamp;
	}

	@Override
	public String getVelocityResourceListener() {
		if (_loadFromServletContext) {
			return TemplateConstants.SERVLET_SEPARATOR;
		}
		else {
			return TemplateConstants.THEME_LOADER_SEPARATOR;
		}
	}

	@Override
	public String getVirtualPath() {
		return _virtualPath;
	}

	@Override
	public boolean getWARFile() {
		return _warFile;
	}

	@Override
	public boolean hasColorSchemes() {
		if (!_colorSchemesMap.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return _themeId.hashCode();
	}

	@Override
	public boolean isCompanyAvailable(long companyId) {
		return isAvailable(getThemeCompanyLimit(), companyId);
	}

	@Override
	public boolean isControlPanelTheme() {
		return _controlPanelTheme;
	}

	@Override
	public boolean isGroupAvailable(long groupId) {
		return isAvailable(getThemeGroupLimit(), groupId);
	}

	@Override
	public boolean isLoadFromServletContext() {
		return _loadFromServletContext;
	}

	@Override
	public boolean isPageTheme() {
		return _pageTheme;
	}

	@Override
	public boolean isWARFile() {
		return _warFile;
	}

	@Override
	public boolean resourceExists(
			ServletContext servletContext, String portletId, String path)
		throws Exception {

		if (!PropsValues.LAYOUT_TEMPLATE_CACHE_ENABLED) {
			return ThemeHelper.resourceExists(
				servletContext, this, portletId, path);
		}

		if (Validator.isNull(path)) {
			return false;
		}

		String key = path;

		if (Validator.isNotNull(portletId)) {
			key = path.concat(StringPool.POUND).concat(portletId);
		}

		Boolean resourceExists = _resourceExistsMap.get(key);

		if (resourceExists != null) {
			return resourceExists;
		}

		resourceExists = ThemeHelper.resourceExists(
			servletContext, this, portletId, path);

		_resourceExistsMap.put(key, resourceExists);

		return resourceExists;
	}

	@Override
	public void setControlPanelTheme(boolean controlPanelTheme) {
		_controlPanelTheme = controlPanelTheme;
	}

	@Override
	public void setCssPath(String cssPath) {
		_cssPath = cssPath;
	}

	@Override
	public void setImagesPath(String imagesPath) {
		_imagesPath = imagesPath;
	}

	@Override
	public void setJavaScriptPath(String javaScriptPath) {
		_javaScriptPath = javaScriptPath;
	}

	@Override
	public void setLoadFromServletContext(boolean loadFromServletContext) {
		_loadFromServletContext = loadFromServletContext;
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setPageTheme(boolean pageTheme) {
		_pageTheme = pageTheme;
	}

	@Override
	public void setRootPath(String rootPath) {
		_rootPath = rootPath;
	}

	@Override
	public void setServletContextName(String servletContextName) {
		_servletContextName = servletContextName;

		if (Validator.isNotNull(_servletContextName)) {
			_warFile = true;
		}
		else {
			_warFile = false;
		}
	}

	@Override
	public void setSetting(String key, String value) {
		ThemeSetting themeSetting = _themeSettingsMap.get(key);

		if (themeSetting != null) {
			themeSetting.setValue(value);
		}
		else {
			addSetting(key, value, false, null, null, null);
		}
	}

	@Override
	public void setSpriteImages(
		String spriteFileName, Properties spriteProperties) {

		for (Map.Entry<Object, Object> entry : spriteProperties.entrySet()) {
			String key = (String)entry.getKey();
			String value = (String)entry.getValue();

			int[] values = StringUtil.split(value, 0);

			int offset = values[0];
			int height = values[1];
			int width = values[2];

			SpriteImage spriteImage = new SpriteImage(
				spriteFileName, key, offset, height, width);

			_spriteImagesMap.put(key, spriteImage);
		}
	}

	@Override
	public void setTemplateExtension(String templateExtension) {
		_templateExtension = templateExtension;
	}

	@Override
	public void setTemplatesPath(String templatesPath) {
		_templatesPath = templatesPath;
	}

	@Override
	public void setThemeCompanyLimit(ThemeCompanyLimit themeCompanyLimit) {
		_themeCompanyLimit = themeCompanyLimit;
	}

	@Override
	public void setThemeGroupLimit(ThemeGroupLimit themeGroupLimit) {
		_themeGroupLimit = themeGroupLimit;
	}

	@Override
	public void setTimestamp(long timestamp) {
		_timestamp = timestamp;
	}

	@Override
	public void setVirtualPath(String virtualPath) {
		if (_warFile && Validator.isNull(virtualPath)) {
			virtualPath = PropsValues.THEME_VIRTUAL_PATH;
		}

		_virtualPath = virtualPath;
	}

	protected boolean isAvailable(ThemeCompanyLimit limit, long id) {
		boolean available = true;

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Check if theme " + getThemeId() + " is available for " + id);
		}

		if (limit != null) {
			List<ThemeCompanyId> includes = limit.getIncludes();
			List<ThemeCompanyId> excludes = limit.getExcludes();

			if (!includes.isEmpty() && !excludes.isEmpty()) {

				// Since includes and excludes are specified, check to make sure
				// the current company id is included and also not excluded

				if (_log.isDebugEnabled()) {
					_log.debug("Check includes and excludes");
				}

				available = limit.isIncluded(id);

				if (available) {
					available = !limit.isExcluded(id);
				}
			}
			else if (includes.isEmpty() && !excludes.isEmpty()) {

				// Since no includes are specified, check to make sure the
				// current company id is not excluded

				if (_log.isDebugEnabled()) {
					_log.debug("Check excludes");
				}

				available = !limit.isExcluded(id);
			}
			else if (!includes.isEmpty() && excludes.isEmpty()) {

				// Since no excludes are specified, check to make sure the
				// current company id is included

				if (_log.isDebugEnabled()) {
					_log.debug("Check includes");
				}

				available = limit.isIncluded(id);
			}
			else {

				// Since no includes or excludes are specified, this theme is
				// available for every company

				if (_log.isDebugEnabled()) {
					_log.debug("No includes or excludes set");
				}

				available = true;
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Theme " + getThemeId() + " is " + (!available ? "NOT " : "") +
					"available for " + id);
		}

		return available;
	}

	private static final Log _log = LogFactoryUtil.getLog(ThemeImpl.class);

	private final Map<String, ColorScheme> _colorSchemesMap = new HashMap<>();
	private boolean _controlPanelTheme;
	private String _cssPath = "${root-path}/css";
	private String _imagesPath = "${root-path}/images";
	private String _javaScriptPath = "${root-path}/js";
	private boolean _loadFromServletContext;
	private String _name;
	private boolean _pageTheme;
	private final Map<String, PortletDecorator> _portletDecoratorsMap =
		new HashMap<>();
	private final Map<String, Boolean> _resourceExistsMap =
		new ConcurrentHashMap<>();
	private final Map<String, String> _resourcePathsMap =
		new ConcurrentHashMap<>();
	private String _rootPath = "/";
	private String _servletContextName = StringPool.BLANK;
	private final Map<String, SpriteImage> _spriteImagesMap = new HashMap<>();
	private String _templateExtension = "ftl";
	private String _templatesPath = "${root-path}/templates";
	private ThemeCompanyLimit _themeCompanyLimit;
	private ThemeGroupLimit _themeGroupLimit;
	private final String _themeId;
	private final Map<String, ThemeSetting> _themeSettingsMap =
		new LinkedHashMap<>();
	private long _timestamp;
	private String _virtualPath = StringPool.BLANK;
	private boolean _warFile;

}