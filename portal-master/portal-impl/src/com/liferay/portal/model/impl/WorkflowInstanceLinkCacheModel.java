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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.WorkflowInstanceLink;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WorkflowInstanceLink in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see WorkflowInstanceLink
 * @generated
 */
@ProviderType
public class WorkflowInstanceLinkCacheModel implements CacheModel<WorkflowInstanceLink>,
	Externalizable, MVCCModel {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkflowInstanceLinkCacheModel)) {
			return false;
		}

		WorkflowInstanceLinkCacheModel workflowInstanceLinkCacheModel = (WorkflowInstanceLinkCacheModel)obj;

		if ((workflowInstanceLinkId == workflowInstanceLinkCacheModel.workflowInstanceLinkId) &&
				(mvccVersion == workflowInstanceLinkCacheModel.mvccVersion)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, workflowInstanceLinkId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", workflowInstanceLinkId=");
		sb.append(workflowInstanceLinkId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", workflowInstanceId=");
		sb.append(workflowInstanceId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WorkflowInstanceLink toEntityModel() {
		WorkflowInstanceLinkImpl workflowInstanceLinkImpl = new WorkflowInstanceLinkImpl();

		workflowInstanceLinkImpl.setMvccVersion(mvccVersion);
		workflowInstanceLinkImpl.setWorkflowInstanceLinkId(workflowInstanceLinkId);
		workflowInstanceLinkImpl.setGroupId(groupId);
		workflowInstanceLinkImpl.setCompanyId(companyId);
		workflowInstanceLinkImpl.setUserId(userId);

		if (userName == null) {
			workflowInstanceLinkImpl.setUserName(StringPool.BLANK);
		}
		else {
			workflowInstanceLinkImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			workflowInstanceLinkImpl.setCreateDate(null);
		}
		else {
			workflowInstanceLinkImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			workflowInstanceLinkImpl.setModifiedDate(null);
		}
		else {
			workflowInstanceLinkImpl.setModifiedDate(new Date(modifiedDate));
		}

		workflowInstanceLinkImpl.setClassNameId(classNameId);
		workflowInstanceLinkImpl.setClassPK(classPK);
		workflowInstanceLinkImpl.setWorkflowInstanceId(workflowInstanceId);

		workflowInstanceLinkImpl.resetOriginalValues();

		return workflowInstanceLinkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		workflowInstanceLinkId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		workflowInstanceId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(workflowInstanceLinkId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeLong(workflowInstanceId);
	}

	public long mvccVersion;
	public long workflowInstanceLinkId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public long workflowInstanceId;
}