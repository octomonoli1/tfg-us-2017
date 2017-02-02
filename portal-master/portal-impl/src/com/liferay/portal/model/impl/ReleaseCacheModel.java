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
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Release in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Release
 * @generated
 */
@ProviderType
public class ReleaseCacheModel implements CacheModel<Release>, Externalizable,
	MVCCModel {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReleaseCacheModel)) {
			return false;
		}

		ReleaseCacheModel releaseCacheModel = (ReleaseCacheModel)obj;

		if ((releaseId == releaseCacheModel.releaseId) &&
				(mvccVersion == releaseCacheModel.mvccVersion)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, releaseId);

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
		sb.append(", releaseId=");
		sb.append(releaseId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", servletContextName=");
		sb.append(servletContextName);
		sb.append(", schemaVersion=");
		sb.append(schemaVersion);
		sb.append(", buildNumber=");
		sb.append(buildNumber);
		sb.append(", buildDate=");
		sb.append(buildDate);
		sb.append(", verified=");
		sb.append(verified);
		sb.append(", state=");
		sb.append(state);
		sb.append(", testString=");
		sb.append(testString);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Release toEntityModel() {
		ReleaseImpl releaseImpl = new ReleaseImpl();

		releaseImpl.setMvccVersion(mvccVersion);
		releaseImpl.setReleaseId(releaseId);

		if (createDate == Long.MIN_VALUE) {
			releaseImpl.setCreateDate(null);
		}
		else {
			releaseImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			releaseImpl.setModifiedDate(null);
		}
		else {
			releaseImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (servletContextName == null) {
			releaseImpl.setServletContextName(StringPool.BLANK);
		}
		else {
			releaseImpl.setServletContextName(servletContextName);
		}

		if (schemaVersion == null) {
			releaseImpl.setSchemaVersion(StringPool.BLANK);
		}
		else {
			releaseImpl.setSchemaVersion(schemaVersion);
		}

		releaseImpl.setBuildNumber(buildNumber);

		if (buildDate == Long.MIN_VALUE) {
			releaseImpl.setBuildDate(null);
		}
		else {
			releaseImpl.setBuildDate(new Date(buildDate));
		}

		releaseImpl.setVerified(verified);
		releaseImpl.setState(state);

		if (testString == null) {
			releaseImpl.setTestString(StringPool.BLANK);
		}
		else {
			releaseImpl.setTestString(testString);
		}

		releaseImpl.resetOriginalValues();

		return releaseImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		releaseId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		servletContextName = objectInput.readUTF();
		schemaVersion = objectInput.readUTF();

		buildNumber = objectInput.readInt();
		buildDate = objectInput.readLong();

		verified = objectInput.readBoolean();

		state = objectInput.readInt();
		testString = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(releaseId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (servletContextName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(servletContextName);
		}

		if (schemaVersion == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(schemaVersion);
		}

		objectOutput.writeInt(buildNumber);
		objectOutput.writeLong(buildDate);

		objectOutput.writeBoolean(verified);

		objectOutput.writeInt(state);

		if (testString == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(testString);
		}
	}

	public long mvccVersion;
	public long releaseId;
	public long createDate;
	public long modifiedDate;
	public String servletContextName;
	public String schemaVersion;
	public int buildNumber;
	public long buildDate;
	public boolean verified;
	public int state;
	public String testString;
}