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
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LayoutPrototype in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPrototype
 * @generated
 */
@ProviderType
public class LayoutPrototypeCacheModel implements CacheModel<LayoutPrototype>,
	Externalizable, MVCCModel {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LayoutPrototypeCacheModel)) {
			return false;
		}

		LayoutPrototypeCacheModel layoutPrototypeCacheModel = (LayoutPrototypeCacheModel)obj;

		if ((layoutPrototypeId == layoutPrototypeCacheModel.layoutPrototypeId) &&
				(mvccVersion == layoutPrototypeCacheModel.mvccVersion)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, layoutPrototypeId);

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
		StringBundler sb = new StringBundler(25);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", layoutPrototypeId=");
		sb.append(layoutPrototypeId);
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
		sb.append(", settings=");
		sb.append(settings);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LayoutPrototype toEntityModel() {
		LayoutPrototypeImpl layoutPrototypeImpl = new LayoutPrototypeImpl();

		layoutPrototypeImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			layoutPrototypeImpl.setUuid(StringPool.BLANK);
		}
		else {
			layoutPrototypeImpl.setUuid(uuid);
		}

		layoutPrototypeImpl.setLayoutPrototypeId(layoutPrototypeId);
		layoutPrototypeImpl.setCompanyId(companyId);
		layoutPrototypeImpl.setUserId(userId);

		if (userName == null) {
			layoutPrototypeImpl.setUserName(StringPool.BLANK);
		}
		else {
			layoutPrototypeImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			layoutPrototypeImpl.setCreateDate(null);
		}
		else {
			layoutPrototypeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			layoutPrototypeImpl.setModifiedDate(null);
		}
		else {
			layoutPrototypeImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			layoutPrototypeImpl.setName(StringPool.BLANK);
		}
		else {
			layoutPrototypeImpl.setName(name);
		}

		if (description == null) {
			layoutPrototypeImpl.setDescription(StringPool.BLANK);
		}
		else {
			layoutPrototypeImpl.setDescription(description);
		}

		if (settings == null) {
			layoutPrototypeImpl.setSettings(StringPool.BLANK);
		}
		else {
			layoutPrototypeImpl.setSettings(settings);
		}

		layoutPrototypeImpl.setActive(active);

		layoutPrototypeImpl.resetOriginalValues();

		return layoutPrototypeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		layoutPrototypeId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		settings = objectInput.readUTF();

		active = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(mvccVersion);

		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(layoutPrototypeId);

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

		if (settings == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(settings);
		}

		objectOutput.writeBoolean(active);
	}

	public long mvccVersion;
	public String uuid;
	public long layoutPrototypeId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public String settings;
	public boolean active;
}