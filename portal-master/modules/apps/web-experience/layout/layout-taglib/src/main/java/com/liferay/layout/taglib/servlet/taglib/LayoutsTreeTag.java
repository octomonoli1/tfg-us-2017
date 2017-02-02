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

package com.liferay.layout.taglib.servlet.taglib;

import com.liferay.layout.taglib.servlet.ServletContextUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.ui.util.SessionTreeJSClicks;
import com.liferay.taglib.util.IncludeTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Eudaldo Alonso
 */
public class LayoutsTreeTag extends IncludeTag {

	@Override
	public int doStartTag() {
		return EVAL_BODY_INCLUDE;
	}

	public void setCheckContentDisplayPage(boolean checkContentDisplayPage) {
		_checkContentDisplayPage = checkContentDisplayPage;
	}

	public void setDefaultStateChecked(boolean defaultStateChecked) {
		_defaultStateChecked = defaultStateChecked;
	}

	public void setDraggableTree(boolean draggableTree) {
		_draggableTree = draggableTree;
	}

	public void setExpandFirstNode(boolean expandFirstNode) {
		_expandFirstNode = expandFirstNode;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setIncomplete(boolean incomplete) {
		_incomplete = incomplete;
	}

	public void setLinkTemplate(String linkTemplate) {
		_linkTemplate = linkTemplate;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setPortletURL(PortletURL portletURL) {
		_portletURL = portletURL;
	}

	public void setPortletURLs(Map<String, PortletURL> portletURLs) {
		_portletURLs = portletURLs;
	}

	public void setPrivateLayout(boolean privateLayout) {
		_privateLayout = privateLayout;
	}

	public void setRootLinkTemplate(String rootLinkTemplate) {
		_rootLinkTemplate = rootLinkTemplate;
	}

	public void setRootNodeName(String rootNodeName) {
		_rootNodeName = rootNodeName;
	}

	public void setSaveState(boolean saveState) {
		_saveState = saveState;
	}

	public void setSelectableTree(boolean selectableTree) {
		_selectableTree = selectableTree;
	}

	public void setSelectedLayoutIds(String selectedLayoutIds) {
		_selectedLayoutIds = selectedLayoutIds;
	}

	public void setSelPlid(Long selPlid) {
		_selPlid = selPlid;
	}

	public void setTreeId(String treeId) {
		_treeId = treeId;
	}

	@Override
	protected void cleanUp() {
		_checkContentDisplayPage = false;
		_defaultStateChecked = false;
		_draggableTree = true;
		_expandFirstNode = true;
		_groupId = 0;
		_incomplete = true;
		_linkTemplate = null;
		_portletURL = null;
		_portletURLs = null;
		_privateLayout = false;
		_rootLinkTemplate = null;
		_rootNodeName = null;
		_saveState = true;
		_selectableTree = false;
		_selectedLayoutIds = null;
		_selPlid = null;
		_treeId = null;
	}

	protected String getCheckedNodes() {
		JSONArray checkedNodesJSONArray = JSONFactoryUtil.createJSONArray();

		String checkedLayoutIds = GetterUtil.getString(
			_selectedLayoutIds,
			SessionTreeJSClicks.getOpenNodes(
				request, _treeId + "SelectedNode"));

		if (Validator.isNull(checkedLayoutIds)) {
			return checkedNodesJSONArray.toString();
		}

		for (long checkedLayoutId : StringUtil.split(checkedLayoutIds, 0L)) {
			Layout checkedLayout = LayoutLocalServiceUtil.fetchLayout(
				_groupId, _privateLayout, checkedLayoutId);

			if (checkedLayout != null) {
				checkedNodesJSONArray.put(
					String.valueOf(checkedLayout.getPlid()));
			}
		}

		return checkedNodesJSONArray.toString();
	}

	protected String getModules() {
		List<String> modules = new ArrayList<>();

		modules.add("liferay-layouts-tree");

		if (_selectableTree) {
			modules.add("liferay-layouts-tree-selectable");
		}

		if (_checkContentDisplayPage) {
			modules.add("liferay-layouts-tree-check-content-display-page");
		}

		if (_saveState) {
			modules.add("liferay-layouts-tree-state");
		}

		return StringUtil.merge(modules);
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	protected JSONArray getPortletURLsJSONArray(
		Map<String, PortletURL> portletURLs) {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		if (MapUtil.isEmpty(portletURLs)) {
			return jsonArray;
		}

		for (Map.Entry<String, PortletURL> entry : portletURLs.entrySet()) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("name", entry.getKey());

			PortletURL portletURL = entry.getValue();

			portletURL.setParameter("selPlid", "{selPlid}");

			jsonObject.put(
				"value",
				StringUtil.replace(
					portletURL.toString(), HttpUtil.encodePath("{selPlid}"),
					"{selPlid}"));

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return _CLEAN_UP_SET_ATTRIBUTES;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-layout:layouts-tree:checkContentDisplayPage",
			String.valueOf(_checkContentDisplayPage));
		request.setAttribute(
			"liferay-layout:layouts-tree:checkedNodes", getCheckedNodes());
		request.setAttribute(
			"liferay-layout:layouts-tree:defaultStateChecked",
			String.valueOf(_defaultStateChecked));
		request.setAttribute(
			"liferay-layout:layouts-tree:draggableTree",
			String.valueOf(_draggableTree));
		request.setAttribute(
			"liferay-layout:layouts-tree:expandFirstNode",
			String.valueOf(_expandFirstNode));
		request.setAttribute(
			"liferay-layout:layouts-tree:groupId", String.valueOf(_groupId));
		request.setAttribute(
			"liferay-layout:layouts-tree:incomplete",
			String.valueOf(_incomplete));
		request.setAttribute(
			"liferay-layout:layouts-tree:linkTemplate",
			String.valueOf(_linkTemplate));
		request.setAttribute(
			"liferay-layout:layouts-tree:modules", getModules());

		Map<String, PortletURL> portletURLs = _portletURLs;

		if (_portletURL != null) {
			if (portletURLs == null) {
				portletURLs = new HashMap<>();
			}

			portletURLs.put("layoutURL", _portletURL);
		}

		request.setAttribute(
			"liferay-layout:layouts-tree:portletURLs", portletURLs);
		request.setAttribute(
			"liferay-layout:layouts-tree:portletURLsJSONArray",
			getPortletURLsJSONArray(portletURLs));

		request.setAttribute(
			"liferay-layout:layouts-tree:privateLayout",
			String.valueOf(_privateLayout));
		request.setAttribute(
			"liferay-layout:layouts-tree:rootLinkTemplate", _rootLinkTemplate);
		request.setAttribute(
			"liferay-layout:layouts-tree:rootNodeName", _rootNodeName);
		request.setAttribute(
			"liferay-layout:layouts-tree:saveState",
			String.valueOf(_saveState));
		request.setAttribute(
			"liferay-layout:layouts-tree:selectableTree",
			String.valueOf(_selectableTree));
		request.setAttribute(
			"liferay-layout:layouts-tree:selectedLayoutIds",
			_selectedLayoutIds);
		request.setAttribute("liferay-layout:layouts-tree:selPlid", _selPlid);
		request.setAttribute("liferay-layout:layouts-tree:treeId", _treeId);
	}

	private static final boolean _CLEAN_UP_SET_ATTRIBUTES = true;

	private static final String _PAGE = "/layouts_tree/page.jsp";

	private boolean _checkContentDisplayPage;
	private boolean _defaultStateChecked;
	private boolean _draggableTree = true;
	private boolean _expandFirstNode = true;
	private long _groupId;
	private boolean _incomplete = true;
	private String _linkTemplate;
	private PortletURL _portletURL;
	private Map<String, PortletURL> _portletURLs;
	private boolean _privateLayout;
	private String _rootLinkTemplate;
	private String _rootNodeName;
	private boolean _saveState = true;
	private boolean _selectableTree;
	private String _selectedLayoutIds;
	private Long _selPlid;
	private String _treeId;

}