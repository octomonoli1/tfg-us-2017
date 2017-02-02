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

import com.liferay.portal.kernel.model.Resource;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Brian Wing Shun Chan
 */
public class ResourceImpl implements Resource {

	public ResourceImpl() {
	}

	@Override
	public long getCodeId() {
		return _codeId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getPrimKey() {
		return _primKey;
	}

	@Override
	public long getResourceId() {
		return _resourceId;
	}

	@Override
	public int getScope() {
		return _scope;
	}

	@Override
	public void setCodeId(long codeId) {
		_codeId = codeId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setPrimKey(String primKey) {
		_primKey = primKey;
	}

	@Override
	public void setResourceId(long resourceId) {
		_resourceId = resourceId;
	}

	@Override
	public void setScope(int scope) {
		_scope = scope;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{companyid=");
		sb.append(_companyId);
		sb.append(", name=");
		sb.append(_name);
		sb.append(", primKey=");
		sb.append(_primKey);
		sb.append(", scope=");
		sb.append(_scope);
		sb.append("}");

		return sb.toString();
	}

	private long _codeId;
	private long _companyId;
	private String _name;
	private String _primKey;
	private long _resourceId;
	private int _scope;

}