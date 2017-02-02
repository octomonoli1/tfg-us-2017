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

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.IncludeTag;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTag;

/**
 * @author Brian Wing Shun Chan
 */
public class ErrorTag extends IncludeTag implements BodyTag {

	@Override
	public int doStartTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		PortletRequest portletRequest = (PortletRequest)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST);

		if (!SessionErrors.contains(portletRequest, _key)) {
			return SKIP_BODY;
		}

		Object value = getException(portletRequest);

		if (value == null) {
			return SKIP_BODY;
		}

		pageContext.setAttribute("errorException", value);

		return super.doStartTag();
	}

	public void setException(Class<?> exception) {
		_exception = exception;

		if (_exception != null) {
			_key = _exception.getName();
		}
	}

	public void setFocusField(String focusField) {
		_focusField = focusField;
	}

	public void setKey(String key) {
		_key = key;
	}

	public void setMessage(String message) {
		_message = message;
	}

	public void setRowBreak(String rowBreak) {
		_rowBreak = HtmlUtil.unescape(rowBreak);
	}

	public void setTargetNode(String targetNode) {
		_targetNode = targetNode;
	}

	public void setTranslateMessage(boolean translateMessage) {
		_translateMessage = translateMessage;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_exception = null;
		_focusField = null;
		_key = null;
		_message = null;
		_rowBreak = StringPool.BLANK;
		_targetNode = null;
		_translateMessage = true;
	}

	protected Object getException(PortletRequest portletRequest) {
		Object value = null;

		if (_exception != null) {
			value = SessionErrors.get(portletRequest, _exception.getName());
		}
		else {
			value = SessionErrors.get(portletRequest, _key);
		}

		return value;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected int processStartTag() throws Exception {
		return EVAL_BODY_BUFFERED;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		PortletRequest portletRequest = (PortletRequest)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST);

		request.setAttribute("liferay-ui:error:key", _key);
		request.setAttribute("liferay-ui:error:message", _message);
		request.setAttribute("liferay-ui:error:rowBreak", _rowBreak);
		request.setAttribute("liferay-ui:error:targetNode", _targetNode);
		request.setAttribute(
			"liferay-ui:error:translateMessage",
			String.valueOf(_translateMessage));

		if (SessionErrors.contains(portletRequest, _key)) {
			String errorMarkerKey = (String)request.getAttribute(
				"liferay-ui:error-marker:key");
			String errorMarkerValue = (String)request.getAttribute(
				"liferay-ui:error-marker:value");

			if (Validator.isNotNull(errorMarkerKey) &&
				Validator.isNotNull(errorMarkerValue)) {

				request.setAttribute(errorMarkerKey, errorMarkerValue);

				Object exception = getException(portletRequest);

				if (exception instanceof Exception) {
					request.setAttribute(
						"liferay-ui:error:exception", exception);
				}

				request.setAttribute(
					"liferay-ui:error:focusField", _focusField);
			}
		}
	}

	private static final String _ATTRIBUTE_NAMESPACE = "liferay-ui:error:";

	private static final String _PAGE = "/html/taglib/ui/error/page.jsp";

	private Class<?> _exception;
	private String _focusField;
	private String _key;
	private String _message;
	private String _rowBreak = StringPool.BLANK;
	private String _targetNode;
	private boolean _translateMessage = true;

}