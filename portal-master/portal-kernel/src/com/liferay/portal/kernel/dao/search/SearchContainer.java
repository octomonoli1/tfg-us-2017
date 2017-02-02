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

package com.liferay.portal.kernel.dao.search;

import com.liferay.portal.kernel.util.DeterminateKeyGenerator;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SearchContainerReference;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class SearchContainer<R> {

	public static final int DEFAULT_CUR = 1;

	public static final String DEFAULT_CUR_PARAM = "cur";

	public static final int DEFAULT_DELTA = GetterUtil.getInteger(
		PropsUtil.get(PropsKeys.SEARCH_CONTAINER_PAGE_DEFAULT_DELTA));

	public static final boolean DEFAULT_DELTA_CONFIGURABLE = true;

	public static final String DEFAULT_DELTA_PARAM = "delta";

	public static final String DEFAULT_DEPRECATED_TOTAL_VAR = "deprecatedTotal";

	public static final boolean DEFAULT_FORCE_POST = false;

	public static final String DEFAULT_ORDER_BY_COL_PARAM = "orderByCol";

	public static final String DEFAULT_ORDER_BY_TYPE_PARAM = "orderByType";

	public static final String DEFAULT_RESULTS_VAR = "results";

	public static final String DEFAULT_TOTAL_VAR = "total";

	public static final String DEFAULT_VAR = "searchContainer";

	public static final int MAX_DELTA = 200;

	public SearchContainer() {
		_curParam = DEFAULT_CUR_PARAM;
		_displayTerms = null;
		_portletRequest = null;
		_searchTerms = null;
	}

	public SearchContainer(
		PortletRequest portletRequest, DisplayTerms displayTerms,
		DisplayTerms searchTerms, String curParam, int cur, int delta,
		PortletURL iteratorURL, List<String> headerNames,
		String emptyResultsMessage) {

		this (
			portletRequest, displayTerms, searchTerms, curParam, cur, delta,
			iteratorURL, headerNames, emptyResultsMessage, StringPool.BLANK);
	}

	public SearchContainer(
		PortletRequest portletRequest, DisplayTerms displayTerms,
		DisplayTerms searchTerms, String curParam, int cur, int delta,
		PortletURL iteratorURL, List<String> headerNames,
		String emptyResultsMessage, String cssClass) {

		_portletRequest = portletRequest;
		_displayTerms = displayTerms;
		_searchTerms = searchTerms;

		_curParam = curParam;

		boolean resetCur = ParamUtil.getBoolean(portletRequest, "resetCur");

		if (resetCur) {
			_cur = DEFAULT_CUR;
		}
		else {
			if (cur < 1) {
				_cur = ParamUtil.getInteger(
					portletRequest, _curParam, DEFAULT_CUR);

				if (_cur < 1) {
					_cur = DEFAULT_CUR;
				}
			}
			else {
				_cur = cur;
			}
		}

		if (!_curParam.equals(DEFAULT_CUR_PARAM)) {
			_deltaParam =
				DEFAULT_DELTA_PARAM +
					StringUtil.replace(
						_curParam, DEFAULT_CUR_PARAM, StringPool.BLANK);
		}

		setDelta(ParamUtil.getInteger(portletRequest, _deltaParam, delta));

		_iteratorURL = iteratorURL;

		_iteratorURL.setParameter(_curParam, String.valueOf(_cur));
		_iteratorURL.setParameter(_deltaParam, String.valueOf(_delta));

		_setParameter(DisplayTerms.KEYWORDS);
		_setParameter(DisplayTerms.ADVANCED_SEARCH);
		_setParameter(DisplayTerms.AND_OPERATOR);

		if (headerNames != null) {
			_headerNames = new ArrayList<>(headerNames.size());

			_headerNames.addAll(headerNames);

			_buildNormalizedHeaderNames(_headerNames);
		}

		_emptyResultsMessage = emptyResultsMessage;

		SearchContainerReference searchContainerReference =
			(SearchContainerReference)portletRequest.getAttribute(
				WebKeys.SEARCH_CONTAINER_REFERENCE);

		if (searchContainerReference != null) {
			searchContainerReference.register(this);
		}

		if (Validator.isNotNull(cssClass)) {
			_cssClass = cssClass;
		}
	}

	public SearchContainer(
		PortletRequest portletRequest, DisplayTerms displayTerms,
		DisplayTerms searchTerms, String curParam, int delta,
		PortletURL iteratorURL, List<String> headerNames,
		String emptyResultsMessage) {

		this (
			portletRequest, displayTerms, searchTerms, curParam, 0, delta,
			iteratorURL, headerNames, emptyResultsMessage);
	}

	public SearchContainer(
		PortletRequest portletRequest, PortletURL iteratorURL,
		List<String> headerNames, String emptyResultsMessage) {

		this(
			portletRequest, null, null, DEFAULT_CUR_PARAM, DEFAULT_DELTA,
			iteratorURL, headerNames, emptyResultsMessage);
	}

	public String getClassName() {
		return _className;
	}

	public String getCssClass() {
		return _cssClass;
	}

	public int getCur() {
		return _cur;
	}

	public String getCurParam() {
		return _curParam;
	}

	public int getDelta() {
		return _delta;
	}

	public String getDeltaParam() {
		return _deltaParam;
	}

	public DisplayTerms getDisplayTerms() {
		return _displayTerms;
	}

	public String getEmptyResultsMessage() {
		return _emptyResultsMessage;
	}

	public String getEmptyResultsMessageCssClass() {
		return _emptyResultsMessageCssClass;
	}

	public int getEnd() {
		return _end;
	}

	public List<String> getHeaderNames() {
		return _headerNames;
	}

	public String getId(HttpServletRequest request, String namespace) {
		if (_uniqueId) {
			return _id;
		}

		if (Validator.isNotNull(_id)) {
			_id = PortalUtil.getUniqueElementId(request, namespace, _id);
			_uniqueId = true;

			return _id;
		}

		String id = null;

		if (Validator.isNotNull(_className)) {
			String simpleClassName = _className;

			int pos = simpleClassName.lastIndexOf(StringPool.PERIOD);

			if (pos != -1) {
				simpleClassName = simpleClassName.substring(pos + 1);
			}

			String variableCasingSimpleClassName = TextFormatter.format(
				simpleClassName, TextFormatter.I);

			id = TextFormatter.formatPlural(variableCasingSimpleClassName);

			id = id.concat("SearchContainer");

			_id = PortalUtil.getUniqueElementId(request, namespace, id);
			_uniqueId = true;

			return _id;
		}

		id = DeterminateKeyGenerator.generate("taglib_search_container");

		_id = id.concat("SearchContainer");
		_uniqueId = true;

		return _id;
	}

	public PortletURL getIteratorURL() {
		return _iteratorURL;
	}

	public List<String> getNormalizedHeaderNames() {
		return _normalizedHeaderNames;
	}

	public Map<String, String> getOrderableHeaders() {
		return _orderableHeaders;
	}

	public String getOrderByCol() {
		return _orderByCol;
	}

	public String getOrderByColParam() {
		return _orderByColParam;
	}

	public OrderByComparator<R> getOrderByComparator() {
		return _orderByComparator;
	}

	public String getOrderByJS() {
		return _orderByJS;
	}

	public String getOrderByType() {
		return _orderByType;
	}

	public String getOrderByTypeParam() {
		return _orderByTypeParam;
	}

	public PortletRequest getPortletRequest() {
		return _portletRequest;
	}

	public int getResultEnd() {
		return _resultEnd;
	}

	public List<ResultRow> getResultRows() {
		return _resultRows;
	}

	public List<R> getResults() {
		return _results;
	}

	public RowChecker getRowChecker() {
		return _rowChecker;
	}

	public RowMover getRowMover() {
		return _rowMover;
	}

	public DisplayTerms getSearchTerms() {
		return _searchTerms;
	}

	public int getStart() {
		return _start;
	}

	public int getTotal() {
		return _total;
	}

	public String getTotalVar() {
		return _totalVar;
	}

	public boolean hasResults() {
		return !_results.isEmpty();
	}

	public boolean isDeltaConfigurable() {
		return _deltaConfigurable;
	}

	public boolean isForcePost() {
		return _forcePost;
	}

	public boolean isHover() {
		return _hover;
	}

	public boolean isRecalculateCur() {
		if ((_total == 0) && (_cur == DEFAULT_CUR)) {
			return false;
		}

		if (((_cur - 1) * _delta) >= _total) {
			return true;
		}

		return false;
	}

	public boolean isSearch() {
		if (_searchTerms != null) {
			return _searchTerms.isSearch();
		}

		return _search;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	public void setDelta(int delta) {
		if (delta <= 0) {
			_delta = DEFAULT_DELTA;
		}
		else if (delta > MAX_DELTA) {
			_delta = MAX_DELTA;
		}
		else {
			_delta = delta;
		}

		_calculateStartAndEnd();
	}

	public void setDeltaConfigurable(boolean deltaConfigurable) {
		_deltaConfigurable = deltaConfigurable;
	}

	public void setDeltaParam(String deltaParam) {
		_deltaParam = deltaParam;
	}

	public void setEmptyResultsMessage(String emptyResultsMessage) {
		_emptyResultsMessage = emptyResultsMessage;
	}

	public void setEmptyResultsMessageCssClass(
		String emptyResultsMessageCssClass) {

		_emptyResultsMessageCssClass = emptyResultsMessageCssClass;
	}

	public void setForcePost(boolean forcePost) {
		_forcePost = forcePost;
	}

	public void setHeaderNames(List<String> headerNames) {
		_headerNames = headerNames;

		_buildNormalizedHeaderNames(headerNames);
	}

	public void setHover(boolean hover) {
		_hover = hover;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setIteratorURL(PortletURL iteratorURL) {
		_iteratorURL = iteratorURL;
	}

	public void setOrderableHeaders(Map<String, String> orderableHeaders) {
		_orderableHeaders = orderableHeaders;
	}

	public void setOrderByCol(String orderByCol) {
		_orderByCol = orderByCol;

		_iteratorURL.setParameter(_orderByColParam, _orderByCol);
	}

	public void setOrderByColParam(String orderByColParam) {
		_orderByColParam = orderByColParam;
	}

	public void setOrderByComparator(OrderByComparator<R> orderByComparator) {
		_orderByComparator = orderByComparator;
	}

	public void setOrderByJS(String orderByJS) {
		_orderByJS = orderByJS;
	}

	public void setOrderByType(String orderByType) {
		_orderByType = orderByType;

		_iteratorURL.setParameter(_orderByTypeParam, _orderByType);
	}

	public void setOrderByTypeParam(String orderByTypeParam) {
		_orderByTypeParam = orderByTypeParam;
	}

	public void setResults(List<R> results) {
		_results = results;
	}

	public void setRowChecker(RowChecker rowChecker) {
		_rowChecker = rowChecker;
	}

	public void setRowMover(RowMover rowMover) {
		_rowMover = rowMover;
	}

	public void setSearch(boolean search) {
		_search = search;
	}

	public void setTotal(int total) {
		_total = total;

		_calculateCur();
		_calculateStartAndEnd();
	}

	public void setTotalVar(String totalVar) {
		_totalVar = totalVar;
	}

	private void _buildNormalizedHeaderNames(List<String> headerNames) {
		if (headerNames == null) {
			return;
		}

		_normalizedHeaderNames = new ArrayList<>(headerNames.size());

		for (String headerName : headerNames) {
			_normalizedHeaderNames.add(
				FriendlyURLNormalizerUtil.normalize(headerName));
		}
	}

	private void _calculateCur() {
		if (_total == 0) {
			_cur = DEFAULT_CUR;

			return;
		}

		if (isRecalculateCur()) {
			if ((_total % _delta) == 0) {
				_cur = (_total / _delta);
			}
			else {
				_cur = (_total / _delta) + 1;
			}
		}
	}

	private void _calculateStartAndEnd() {
		int[] startAndEnd = SearchPaginationUtil.calculateStartAndEnd(
			_cur, _delta);

		_start = startAndEnd[0];
		_end = startAndEnd[1];

		_resultEnd = _end;

		if (_resultEnd > _total) {
			_resultEnd = _total;
		}
	}

	private void _setParameter(String name) {
		String value = _portletRequest.getParameter(name);

		if (value != null) {
			_iteratorURL.setParameter(name, value);
		}
	}

	private String _className;
	private String _cssClass = StringPool.BLANK;
	private int _cur;
	private final String _curParam;
	private int _delta = DEFAULT_DELTA;
	private boolean _deltaConfigurable = DEFAULT_DELTA_CONFIGURABLE;
	private String _deltaParam = DEFAULT_DELTA_PARAM;
	private final DisplayTerms _displayTerms;
	private String _emptyResultsMessage;
	private String _emptyResultsMessageCssClass;
	private int _end;
	private boolean _forcePost = DEFAULT_FORCE_POST;
	private List<String> _headerNames;
	private boolean _hover = true;
	private String _id;
	private PortletURL _iteratorURL;
	private List<String> _normalizedHeaderNames;
	private Map<String, String> _orderableHeaders;
	private String _orderByCol;
	private String _orderByColParam = DEFAULT_ORDER_BY_COL_PARAM;
	private OrderByComparator<R> _orderByComparator;
	private String _orderByJS;
	private String _orderByType;
	private String _orderByTypeParam = DEFAULT_ORDER_BY_TYPE_PARAM;
	private final PortletRequest _portletRequest;
	private int _resultEnd;
	private final List<ResultRow> _resultRows = new ArrayList<>();
	private List<R> _results = new ArrayList<>();
	private RowChecker _rowChecker;
	private RowMover _rowMover;
	private boolean _search;
	private final DisplayTerms _searchTerms;
	private int _start;
	private int _total;
	private String _totalVar;
	private boolean _uniqueId;

}