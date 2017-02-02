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

package com.liferay.taglib.aui.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

/**
 * @author Eduardo Lundgren
 * @author Bruno Basto
 * @author Nathan Cavanaugh
 * @author Julio Camarero
 * @generated
 */
public abstract class BaseButtonTag extends com.liferay.taglib.util.IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		return super.doStartTag();
	}

	public java.lang.String getCssClass() {
		return _cssClass;
	}

	public java.lang.Object getData() {
		return _data;
	}

	public boolean getDisabled() {
		return _disabled;
	}

	public java.lang.String getHref() {
		return _href;
	}

	public java.lang.String getIcon() {
		return _icon;
	}

	public java.lang.String getIconAlign() {
		return _iconAlign;
	}

	public java.lang.String getId() {
		return _id;
	}

	public java.lang.String getName() {
		return _name;
	}

	public java.lang.String getOnClick() {
		return _onClick;
	}

	public java.lang.Object getPrimary() {
		return _primary;
	}

	public java.lang.String getType() {
		return _type;
	}

	public boolean getUseDialog() {
		return _useDialog;
	}

	public boolean getUseNamespace() {
		return _useNamespace;
	}

	public java.lang.String getValue() {
		return _value;
	}

	public void setCssClass(java.lang.String cssClass) {
		_cssClass = cssClass;

		setScopedAttribute("cssClass", cssClass);
	}

	public void setData(java.lang.Object data) {
		_data = data;

		setScopedAttribute("data", data);
	}

	public void setDisabled(boolean disabled) {
		_disabled = disabled;

		setScopedAttribute("disabled", disabled);
	}

	public void setHref(java.lang.String href) {
		_href = href;

		setScopedAttribute("href", href);
	}

	public void setIcon(java.lang.String icon) {
		_icon = icon;

		setScopedAttribute("icon", icon);
	}

	public void setIconAlign(java.lang.String iconAlign) {
		_iconAlign = iconAlign;

		setScopedAttribute("iconAlign", iconAlign);
	}

	public void setId(java.lang.String id) {
		_id = id;

		setScopedAttribute("id", id);
	}

	public void setName(java.lang.String name) {
		_name = name;

		setScopedAttribute("name", name);
	}

	public void setOnClick(java.lang.String onClick) {
		_onClick = onClick;

		setScopedAttribute("onClick", onClick);
	}

	public void setPrimary(java.lang.Object primary) {
		_primary = primary;

		setScopedAttribute("primary", primary);
	}

	public void setType(java.lang.String type) {
		_type = type;

		setScopedAttribute("type", type);
	}

	public void setUseDialog(boolean useDialog) {
		_useDialog = useDialog;

		setScopedAttribute("useDialog", useDialog);
	}

	public void setUseNamespace(boolean useNamespace) {
		_useNamespace = useNamespace;

		setScopedAttribute("useNamespace", useNamespace);
	}

	public void setValue(java.lang.String value) {
		_value = value;

		setScopedAttribute("value", value);
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_cssClass = null;
		_data = null;
		_disabled = false;
		_href = null;
		_icon = null;
		_iconAlign = "left";
		_id = null;
		_name = null;
		_onClick = null;
		_primary = null;
		_type = "button";
		_useDialog = false;
		_useNamespace = true;
		_value = null;
	}

	@Override
	protected String getEndPage() {
		return _END_PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		setNamespacedAttribute(request, "cssClass", _cssClass);
		setNamespacedAttribute(request, "data", _data);
		setNamespacedAttribute(request, "disabled", _disabled);
		setNamespacedAttribute(request, "href", _href);
		setNamespacedAttribute(request, "icon", _icon);
		setNamespacedAttribute(request, "iconAlign", _iconAlign);
		setNamespacedAttribute(request, "id", _id);
		setNamespacedAttribute(request, "name", _name);
		setNamespacedAttribute(request, "onClick", _onClick);
		setNamespacedAttribute(request, "primary", _primary);
		setNamespacedAttribute(request, "type", _type);
		setNamespacedAttribute(request, "useDialog", _useDialog);
		setNamespacedAttribute(request, "useNamespace", _useNamespace);
		setNamespacedAttribute(request, "value", _value);
	}

	protected static final String _ATTRIBUTE_NAMESPACE = "aui:button:";

	private static final String _END_PAGE =
		"/html/taglib/aui/button/end.jsp";

	private java.lang.String _cssClass = null;
	private java.lang.Object _data = null;
	private boolean _disabled = false;
	private java.lang.String _href = null;
	private java.lang.String _icon = null;
	private java.lang.String _iconAlign = "left";
	private java.lang.String _id = null;
	private java.lang.String _name = null;
	private java.lang.String _onClick = null;
	private java.lang.Object _primary = null;
	private java.lang.String _type = "button";
	private boolean _useDialog = false;
	private boolean _useNamespace = true;
	private java.lang.String _value = null;

}