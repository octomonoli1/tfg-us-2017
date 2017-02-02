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

package com.liferay.dynamic.data.mapping.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.dynamic.data.mapping.model.DDMDataProviderInstance;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DDMDataProviderInstance in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see DDMDataProviderInstance
 * @generated
 */
@ProviderType
public class DDMDataProviderInstanceCacheModel implements CacheModel<DDMDataProviderInstance>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DDMDataProviderInstanceCacheModel)) {
			return false;
		}

		DDMDataProviderInstanceCacheModel ddmDataProviderInstanceCacheModel = (DDMDataProviderInstanceCacheModel)obj;

		if (dataProviderInstanceId == ddmDataProviderInstanceCacheModel.dataProviderInstanceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dataProviderInstanceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", dataProviderInstanceId=");
		sb.append(dataProviderInstanceId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", definition=");
		sb.append(definition);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DDMDataProviderInstance toEntityModel() {
		DDMDataProviderInstanceImpl ddmDataProviderInstanceImpl = new DDMDataProviderInstanceImpl();

		if (uuid == null) {
			ddmDataProviderInstanceImpl.setUuid(StringPool.BLANK);
		}
		else {
			ddmDataProviderInstanceImpl.setUuid(uuid);
		}

		ddmDataProviderInstanceImpl.setDataProviderInstanceId(dataProviderInstanceId);
		ddmDataProviderInstanceImpl.setGroupId(groupId);
		ddmDataProviderInstanceImpl.setCompanyId(companyId);
		ddmDataProviderInstanceImpl.setUserId(userId);

		if (userName == null) {
			ddmDataProviderInstanceImpl.setUserName(StringPool.BLANK);
		}
		else {
			ddmDataProviderInstanceImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			ddmDataProviderInstanceImpl.setCreateDate(null);
		}
		else {
			ddmDataProviderInstanceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ddmDataProviderInstanceImpl.setModifiedDate(null);
		}
		else {
			ddmDataProviderInstanceImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			ddmDataProviderInstanceImpl.setName(StringPool.BLANK);
		}
		else {
			ddmDataProviderInstanceImpl.setName(name);
		}

		if (description == null) {
			ddmDataProviderInstanceImpl.setDescription(StringPool.BLANK);
		}
		else {
			ddmDataProviderInstanceImpl.setDescription(description);
		}

		if (definition == null) {
			ddmDataProviderInstanceImpl.setDefinition(StringPool.BLANK);
		}
		else {
			ddmDataProviderInstanceImpl.setDefinition(definition);
		}

		if (type == null) {
			ddmDataProviderInstanceImpl.setType(StringPool.BLANK);
		}
		else {
			ddmDataProviderInstanceImpl.setType(type);
		}

		ddmDataProviderInstanceImpl.resetOriginalValues();

		return ddmDataProviderInstanceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		dataProviderInstanceId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		definition = objectInput.readUTF();
		type = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(dataProviderInstanceId);

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

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (definition == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(definition);
		}

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}
	}

	public String uuid;
	public long dataProviderInstanceId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public String definition;
	public String type;
}