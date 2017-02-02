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

package com.liferay.portal.kernel.servlet.taglib.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Mergeable;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Shuyang Zhou
 */
public class OutputData implements Mergeable<OutputData>, Serializable {

	public void addData(String outputKey, String webKey, StringBundler sb) {
		DataKey dataKey = new DataKey(outputKey, webKey);

		StringBundler mergedSB = _dataMap.get(dataKey);

		if (mergedSB == null) {
			_dataMap.put(dataKey, sb);
		}
		else {
			mergedSB.append(sb);
		}
	}

	public boolean addOutputKey(String outputKey) {
		return _outputKeys.add(outputKey);
	}

	public StringBundler getData(String outputKey, String webKey) {
		DataKey dataKey = new DataKey(outputKey, webKey);

		return _dataMap.get(dataKey);
	}

	public StringBundler getMergedData(String webKey) {
		StringBundler mergedSB = null;

		for (Map.Entry<DataKey, StringBundler> entry : _dataMap.entrySet()) {
			DataKey dataKey = entry.getKey();

			if (dataKey._webKey.equals(webKey)) {
				if (mergedSB == null) {
					mergedSB = entry.getValue();
				}
				else {
					mergedSB.append(entry.getValue());
				}
			}
		}

		return mergedSB;
	}

	public Set<String> getOutputKeys() {
		return _outputKeys;
	}

	@Override
	public OutputData merge(OutputData outputData) {
		if ((outputData == null) || (outputData == this)) {
			return this;
		}

		for (Map.Entry<DataKey, StringBundler> entry :
				outputData._dataMap.entrySet()) {

			DataKey dataKey = entry.getKey();

			String outputKey = dataKey._outputKey;

			StringBundler sb = entry.getValue();

			if (!_outputKeys.contains(outputKey)) {
				StringBundler mergedSB = _dataMap.get(dataKey);

				if (mergedSB == null) {
					_dataMap.put(dataKey, sb);
				}
				else {
					mergedSB.append(sb);
				}

				if (outputData._outputKeys.contains(outputKey)) {
					_outputKeys.add(outputKey);
				}
			}
		}

		return this;
	}

	public void setData(String outputKey, String webKey, StringBundler sb) {
		DataKey dataKey = new DataKey(outputKey, webKey);

		_dataMap.put(dataKey, sb);
	}

	@Override
	public OutputData split() {
		return new OutputData();
	}

	private static final long serialVersionUID = 1L;

	private final Map<DataKey, StringBundler> _dataMap = new HashMap<>();
	private final Set<String> _outputKeys = new HashSet<>();

	private static class DataKey implements Serializable {

		public DataKey(String outputKey, String webKey) {
			_outputKey = GetterUtil.getString(outputKey);
			_webKey = webKey;
		}

		@Override
		public boolean equals(Object obj) {
			DataKey dataKey = (DataKey)obj;

			if (_outputKey.equals(dataKey._outputKey) &&
				_webKey.equals(dataKey._webKey)) {

				return true;
			}

			return false;
		}

		@Override
		public int hashCode() {
			return _outputKey.hashCode() * 11 + _webKey.hashCode();
		}

		private static final long serialVersionUID = 1L;

		private final String _outputKey;
		private final String _webKey;

	}

}