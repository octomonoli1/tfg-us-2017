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

import com.liferay.dynamic.data.mapping.model.DDMStructureLayout;

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
 * The cache model class for representing DDMStructureLayout in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see DDMStructureLayout
 * @generated
 */
@ProviderType
public class DDMStructureLayoutCacheModel implements CacheModel<DDMStructureLayout>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DDMStructureLayoutCacheModel)) {
			return false;
		}

		DDMStructureLayoutCacheModel ddmStructureLayoutCacheModel = (DDMStructureLayoutCacheModel)obj;

		if (structureLayoutId == ddmStructureLayoutCacheModel.structureLayoutId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, structureLayoutId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", structureLayoutId=");
		sb.append(structureLayoutId);
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
		sb.append(", structureVersionId=");
		sb.append(structureVersionId);
		sb.append(", definition=");
		sb.append(definition);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DDMStructureLayout toEntityModel() {
		DDMStructureLayoutImpl ddmStructureLayoutImpl = new DDMStructureLayoutImpl();

		if (uuid == null) {
			ddmStructureLayoutImpl.setUuid(StringPool.BLANK);
		}
		else {
			ddmStructureLayoutImpl.setUuid(uuid);
		}

		ddmStructureLayoutImpl.setStructureLayoutId(structureLayoutId);
		ddmStructureLayoutImpl.setGroupId(groupId);
		ddmStructureLayoutImpl.setCompanyId(companyId);
		ddmStructureLayoutImpl.setUserId(userId);

		if (userName == null) {
			ddmStructureLayoutImpl.setUserName(StringPool.BLANK);
		}
		else {
			ddmStructureLayoutImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			ddmStructureLayoutImpl.setCreateDate(null);
		}
		else {
			ddmStructureLayoutImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ddmStructureLayoutImpl.setModifiedDate(null);
		}
		else {
			ddmStructureLayoutImpl.setModifiedDate(new Date(modifiedDate));
		}

		ddmStructureLayoutImpl.setStructureVersionId(structureVersionId);

		if (definition == null) {
			ddmStructureLayoutImpl.setDefinition(StringPool.BLANK);
		}
		else {
			ddmStructureLayoutImpl.setDefinition(definition);
		}

		ddmStructureLayoutImpl.resetOriginalValues();

		ddmStructureLayoutImpl.setDDMFormLayout(_ddmFormLayout);

		return ddmStructureLayoutImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {
		uuid = objectInput.readUTF();

		structureLayoutId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		structureVersionId = objectInput.readLong();
		definition = objectInput.readUTF();

		_ddmFormLayout = (com.liferay.dynamic.data.mapping.model.DDMFormLayout)objectInput.readObject();
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

		objectOutput.writeLong(structureLayoutId);

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

		objectOutput.writeLong(structureVersionId);

		if (definition == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(definition);
		}

		objectOutput.writeObject(_ddmFormLayout);
	}

	public String uuid;
	public long structureLayoutId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long structureVersionId;
	public String definition;
	public com.liferay.dynamic.data.mapping.model.DDMFormLayout _ddmFormLayout;
}