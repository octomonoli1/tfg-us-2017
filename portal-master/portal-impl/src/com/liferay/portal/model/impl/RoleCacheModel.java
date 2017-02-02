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
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Role in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Role
 * @generated
 */
@ProviderType
public class RoleCacheModel implements CacheModel<Role>, Externalizable,
	MVCCModel {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RoleCacheModel)) {
			return false;
		}

		RoleCacheModel roleCacheModel = (RoleCacheModel)obj;

		if ((roleId == roleCacheModel.roleId) &&
				(mvccVersion == roleCacheModel.mvccVersion)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, roleId);

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
		StringBundler sb = new StringBundler(31);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", roleId=");
		sb.append(roleId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", type=");
		sb.append(type);
		sb.append(", subtype=");
		sb.append(subtype);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Role toEntityModel() {
		RoleImpl roleImpl = new RoleImpl();

		roleImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			roleImpl.setUuid(StringPool.BLANK);
		}
		else {
			roleImpl.setUuid(uuid);
		}

		roleImpl.setRoleId(roleId);
		roleImpl.setCompanyId(companyId);
		roleImpl.setUserId(userId);

		if (userName == null) {
			roleImpl.setUserName(StringPool.BLANK);
		}
		else {
			roleImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			roleImpl.setCreateDate(null);
		}
		else {
			roleImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			roleImpl.setModifiedDate(null);
		}
		else {
			roleImpl.setModifiedDate(new Date(modifiedDate));
		}

		roleImpl.setClassNameId(classNameId);
		roleImpl.setClassPK(classPK);

		if (name == null) {
			roleImpl.setName(StringPool.BLANK);
		}
		else {
			roleImpl.setName(name);
		}

		if (title == null) {
			roleImpl.setTitle(StringPool.BLANK);
		}
		else {
			roleImpl.setTitle(title);
		}

		if (description == null) {
			roleImpl.setDescription(StringPool.BLANK);
		}
		else {
			roleImpl.setDescription(description);
		}

		roleImpl.setType(type);

		if (subtype == null) {
			roleImpl.setSubtype(StringPool.BLANK);
		}
		else {
			roleImpl.setSubtype(subtype);
		}

		roleImpl.resetOriginalValues();

		return roleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		roleId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();
		name = objectInput.readUTF();
		title = objectInput.readUTF();
		description = objectInput.readUTF();

		type = objectInput.readInt();
		subtype = objectInput.readUTF();
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

		objectOutput.writeLong(roleId);

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

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeInt(type);

		if (subtype == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(subtype);
		}
	}

	public long mvccVersion;
	public String uuid;
	public long roleId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public String name;
	public String title;
	public String description;
	public int type;
	public String subtype;
}