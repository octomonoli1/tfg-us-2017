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

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.taglib.aui.ScriptData;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.BaseBodyTagSupport;
import com.liferay.taglib.FileAvailabilityUtil;
import com.liferay.taglib.aui.ScriptTag;
import com.liferay.taglib.util.PortalIncludeUtil;
import com.liferay.taglib.util.TagResourceBundleUtil;

import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTag;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class IconMenuTag extends BaseBodyTagSupport implements BodyTag {

	@Override
	public int doAfterBody() {
		HttpServletRequest request =
			(HttpServletRequest)pageContext.getRequest();

		IntegerWrapper iconCount = (IntegerWrapper)request.getAttribute(
			"liferay-ui:icon-menu:icon-count");

		Boolean singleIcon = (Boolean)request.getAttribute(
			"liferay-ui:icon-menu:single-icon");

		if ((iconCount != null) && (iconCount.getValue() == 1) &&
			(singleIcon == null)) {

			bodyContent.clearBody();

			ScriptData scriptData = (ScriptData)request.getAttribute(
				WebKeys.AUI_SCRIPT_DATA);

			if (scriptData != null) {
				scriptData.reset();
			}

			request.setAttribute(
				"liferay-ui:icon-menu:single-icon", Boolean.TRUE);

			return EVAL_BODY_AGAIN;
		}
		else {
			return SKIP_BODY;
		}
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			return processEndTag();
		}
		catch (Exception e) {
			throw new JspException(e);
		}
		finally {
			if (!ServerDetector.isResin()) {
				_cssClass = null;
				_data = null;
				_direction = "left";
				_endPage = null;
				_extended = true;
				_icon = null;
				_id = null;
				_localizeMessage = true;
				_maxDisplayItems = _DEFAULT_MAX_DISPLAY_ITEMS;
				_message = "actions";
				_scroll = false;
				_select = false;
				_showArrow = true;
				_showExpanded = false;
				_showWhenSingleIcon = false;
				_startPage = null;
				_triggerCssClass = null;
			}
		}
	}

	@Override
	public int doStartTag() {
		HttpServletRequest request =
			(HttpServletRequest)pageContext.getRequest();

		ScriptData scriptData = (ScriptData)request.getAttribute(
			WebKeys.AUI_SCRIPT_DATA);

		if (scriptData != null) {
			scriptData.mark();
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (_direction == null) {
			_direction = "left";
		}

		if (Validator.isNull(_id)) {
			_id = (String)request.getAttribute(
				"liferay-ui:search-container-row:rowId");

			if (Validator.isNull(_id)) {
				_id = PortalUtil.generateRandomKey(
					request, IconMenuTag.class.getName());
			}

			_id = _id.concat("_menu");
		}

		request.setAttribute("liferay-ui:icon-menu:id", _id);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String namespace = portletDisplay.getNamespace();

		_id = namespace.concat(_id);

		request.setAttribute(
			"liferay-ui:icon-menu:icon-count", new IntegerWrapper());
		request.setAttribute(
			"liferay-ui:icon-menu:showWhenSingleIcon",
			String.valueOf(_showWhenSingleIcon));

		return EVAL_BODY_BUFFERED;
	}

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	public void setData(Map<String, Object> data) {
		_data = data;
	}

	public void setDirection(String direction) {
		_direction = direction;
	}

	public void setDisabled(boolean disabled) {
		_disabled = disabled;
	}

	public void setEndPage(String endPage) {
		_endPage = endPage;
	}

	public void setExtended(boolean extended) {
		_extended = extended;
	}

	public void setIcon(String icon) {
		_icon = icon;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setLocalizeMessage(boolean localizeMessage) {
		_localizeMessage = localizeMessage;
	}

	public void setMarkupView(String markupView) {
		_markupView = markupView;
	}

	public void setMaxDisplayItems(int maxDisplayItems) {
		if (maxDisplayItems <= 0) {
			maxDisplayItems = _DEFAULT_MAX_DISPLAY_ITEMS;
		}

		_maxDisplayItems = maxDisplayItems;
	}

	public void setMessage(String message) {
		if (message != null) {
			_message = message;
		}
	}

	public void setScroll(boolean scroll) {
		_scroll = scroll;
	}

	public void setSelect(boolean select) {
		_select = select;
	}

	public void setShowArrow(boolean showArrow) {
		_showArrow = showArrow;
	}

	public void setShowExpanded(boolean showExpanded) {
		_showExpanded = showExpanded;
	}

	public void setShowWhenSingleIcon(boolean showWhenSingleIcon) {
		_showWhenSingleIcon = showWhenSingleIcon;
	}

	public void setStartPage(String startPage) {
		_startPage = startPage;
	}

	public void setTriggerCssClass(String triggerCssClass) {
		_triggerCssClass = triggerCssClass;
	}

	public void setUseIconCaret(boolean useIconCaret) {
		_useIconCaret = useIconCaret;
	}

	protected String getEndPage() {
		if (Validator.isNotNull(_endPage)) {
			return _endPage;
		}

		if (Validator.isNotNull(_markupView)) {
			return "/html/taglib/ui/icon_menu/" + _markupView + "/end.jsp";
		}

		return "/html/taglib/ui/icon_menu/end.jsp";
	}

	protected String getStartPage() {
		if (Validator.isNotNull(_startPage)) {
			return _startPage;
		}

		if (Validator.isNotNull(_markupView)) {
			return "/html/taglib/ui/icon_menu/" + _markupView + "/start.jsp";
		}

		return "/html/taglib/ui/icon_menu/start.jsp";
	}

	protected int processEndTag() throws Exception {
		HttpServletRequest request =
			(HttpServletRequest)pageContext.getRequest();

		IntegerWrapper iconCount = (IntegerWrapper)request.getAttribute(
			"liferay-ui:icon-menu:icon-count");

		request.removeAttribute("liferay-ui:icon-menu:icon-count");
		request.removeAttribute("liferay-ui:icon-menu:id");

		Boolean singleIcon = (Boolean)request.getAttribute(
			"liferay-ui:icon-menu:single-icon");

		request.removeAttribute("liferay-ui:icon-menu:single-icon");

		JspWriter jspWriter = pageContext.getOut();

		if ((iconCount != null) && (iconCount.getValue() >= 1) &&
			((singleIcon == null) || _showWhenSingleIcon)) {

			if (!FileAvailabilityUtil.isAvailable(
					(ServletContext)request.getAttribute(WebKeys.CTX),
					getStartPage())) {

				if (_showExpanded) {
					jspWriter.write("<ul class=\"lfr-menu-expanded ");
					jspWriter.write("lfr-menu-list");

					if (Validator.isNotNull(_cssClass)) {
						jspWriter.write(StringPool.SPACE);
						jspWriter.write(_cssClass);
					}

					jspWriter.write("\" id=\"");
					jspWriter.write(_id);
					jspWriter.write("\">");
				}
				else {
					jspWriter.write("<div class=\"btn-group lfr-icon-menu");

					if (Validator.isNotNull(_cssClass)) {
						jspWriter.write(StringPool.SPACE);
						jspWriter.write(_cssClass);
					}

					if (_direction.equals("up")) {
						jspWriter.write(" dropup");
					}

					jspWriter.write("\"><a class=\"dropdown-toggle direction-");
					jspWriter.write(_direction);
					jspWriter.write(" max-display-items-");
					jspWriter.write(String.valueOf(_maxDisplayItems));

					if (_disabled) {
						jspWriter.write(" disabled");
					}

					if (_extended) {
						jspWriter.write(" btn btn-default");
					}

					if (_select) {
						jspWriter.write(" select");
					}

					if (Validator.isNotNull(_triggerCssClass)) {
						jspWriter.write(StringPool.SPACE + _triggerCssClass);
					}

					String message = _message;

					ResourceBundle resourceBundle =
						TagResourceBundleUtil.getResourceBundle(pageContext);

					if (_localizeMessage) {
						message = LanguageUtil.get(resourceBundle, _message);
					}

					jspWriter.write("\" href=\"javascript:;\" id=\"");
					jspWriter.write(_id);
					jspWriter.write("\" title=\"");
					jspWriter.write(message);
					jspWriter.write("\">");

					if (_showArrow && _direction.equals("left")) {
						String caret = "caret";

						if (_useIconCaret) {
							caret = "icon-caret-left";
						}

						jspWriter.write("<i class=\"lfr-icon-menu-arrow ");
						jspWriter.write(caret);
						jspWriter.write("\"></i> ");
					}

					boolean auiImage = false;

					if (Validator.isNotNull(_icon)) {
						auiImage = _icon.startsWith(_AUI_PATH);

						if (auiImage) {
							jspWriter.write(" <i class=\"icon-");
							jspWriter.write(
								_icon.substring(_AUI_PATH.length()));
							jspWriter.write(" lfr-icon-menu-icon");
							jspWriter.write("\"></i> ");
						}
						else {
							jspWriter.write(
								"<img alt=\"\" class=\"lfr-icon-menu-icon\" ");
							jspWriter.write("src=\"");
							jspWriter.write(_icon);
							jspWriter.write("\" /> ");
						}
					}

					if (Validator.isNotNull(message)) {
						jspWriter.write("<span class=\"lfr-icon-menu-text\">");
						jspWriter.write(message);
						jspWriter.write("</span>");
					}

					if (_showArrow && !_direction.equals("left")) {
						String caret = "caret";

						if (_useIconCaret) {
							caret = "icon-caret-" + _direction;
						}

						jspWriter.write(" <i class=\"lfr-icon-menu-arrow ");
						jspWriter.write(caret);
						jspWriter.write("\"></i> ");
					}

					jspWriter.write("</a>");

					ScriptTag.doTag(
						null, null, "liferay-menu",
						"Liferay.Menu.register('" + _id + "');", bodyContent,
						pageContext);

					jspWriter.write("<ul class=\"dropdown-menu lfr-menu-list");
					jspWriter.write(" direction-");
					jspWriter.write(_direction);
					jspWriter.write("\">");
				}
			}
			else {
				setAttributes();

				PortalIncludeUtil.include(pageContext, getStartPage());
			}
		}

		writeBodyContent(jspWriter);

		if ((iconCount != null) && (iconCount.getValue() >= 1) &&
			((singleIcon == null) || _showWhenSingleIcon)) {

			if (!FileAvailabilityUtil.isAvailable(
					(ServletContext)request.getAttribute(WebKeys.CTX),
					getEndPage())) {

				jspWriter.write("</ul>");

				if (_showExpanded) {
					ScriptTag.doTag(
						null, null, "liferay-menu",
						"Liferay.Menu.handleFocus('#" + _id + "menu');",
						bodyContent, pageContext);
				}
				else {
					jspWriter.write("</div>");
				}
			}
			else {
				PortalIncludeUtil.include(pageContext, getEndPage());
			}
		}

		request.removeAttribute("liferay-ui:icon-menu:showWhenSingleIcon");

		return EVAL_PAGE;
	}

	protected void setAttributes() {
		HttpServletRequest request =
			(HttpServletRequest)pageContext.getRequest();

		request.setAttribute("liferay-ui:icon-menu:cssClass", _cssClass);
		request.setAttribute("liferay-ui:icon-menu:data", _data);
		request.setAttribute("liferay-ui:icon-menu:direction", _direction);
		request.setAttribute("liferay-ui:icon-menu:icon", _icon);
		request.setAttribute("liferay-ui:icon-menu:id", _id);

		String message = _message;

		ResourceBundle resourceBundle = TagResourceBundleUtil.getResourceBundle(
			pageContext);

		if (_localizeMessage) {
			message = LanguageUtil.get(resourceBundle, _message);
		}

		request.setAttribute("liferay-ui:icon-menu:message", message);

		request.setAttribute("liferay-ui:icon-menu:scroll", _scroll);
		request.setAttribute(
			"liferay-ui:icon-menu:triggerCssClass", _triggerCssClass);
	}

	private static final String _AUI_PATH = "../aui/";

	private static final int _DEFAULT_MAX_DISPLAY_ITEMS = GetterUtil.getInteger(
		PropsUtil.get(PropsKeys.MENU_MAX_DISPLAY_ITEMS));

	private String _cssClass;
	private Map<String, Object> _data;
	private String _direction = "left";
	private boolean _disabled;
	private String _endPage;
	private boolean _extended = true;
	private String _icon;
	private String _id;
	private boolean _localizeMessage = true;
	private String _markupView;
	private int _maxDisplayItems = _DEFAULT_MAX_DISPLAY_ITEMS;
	private String _message = "actions";
	private boolean _scroll;
	private boolean _select;
	private boolean _showArrow = true;
	private boolean _showExpanded;
	private boolean _showWhenSingleIcon;
	private String _startPage;
	private String _triggerCssClass;
	private boolean _useIconCaret;

}