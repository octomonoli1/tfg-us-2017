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

package com.liferay.wiki.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.liferay.wiki.model.WikiPage;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WikiPage in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see WikiPage
 * @generated
 */
@ProviderType
public class WikiPageCacheModel implements CacheModel<WikiPage>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WikiPageCacheModel)) {
			return false;
		}

		WikiPageCacheModel wikiPageCacheModel = (WikiPageCacheModel)obj;

		if (pageId == wikiPageCacheModel.pageId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, pageId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(49);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", pageId=");
		sb.append(pageId);
		sb.append(", resourcePrimKey=");
		sb.append(resourcePrimKey);
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
		sb.append(", nodeId=");
		sb.append(nodeId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", version=");
		sb.append(version);
		sb.append(", minorEdit=");
		sb.append(minorEdit);
		sb.append(", content=");
		sb.append(content);
		sb.append(", summary=");
		sb.append(summary);
		sb.append(", format=");
		sb.append(format);
		sb.append(", head=");
		sb.append(head);
		sb.append(", parentTitle=");
		sb.append(parentTitle);
		sb.append(", redirectTitle=");
		sb.append(redirectTitle);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WikiPage toEntityModel() {
		WikiPageImpl wikiPageImpl = new WikiPageImpl();

		if (uuid == null) {
			wikiPageImpl.setUuid(StringPool.BLANK);
		}
		else {
			wikiPageImpl.setUuid(uuid);
		}

		wikiPageImpl.setPageId(pageId);
		wikiPageImpl.setResourcePrimKey(resourcePrimKey);
		wikiPageImpl.setGroupId(groupId);
		wikiPageImpl.setCompanyId(companyId);
		wikiPageImpl.setUserId(userId);

		if (userName == null) {
			wikiPageImpl.setUserName(StringPool.BLANK);
		}
		else {
			wikiPageImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			wikiPageImpl.setCreateDate(null);
		}
		else {
			wikiPageImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			wikiPageImpl.setModifiedDate(null);
		}
		else {
			wikiPageImpl.setModifiedDate(new Date(modifiedDate));
		}

		wikiPageImpl.setNodeId(nodeId);

		if (title == null) {
			wikiPageImpl.setTitle(StringPool.BLANK);
		}
		else {
			wikiPageImpl.setTitle(title);
		}

		wikiPageImpl.setVersion(version);
		wikiPageImpl.setMinorEdit(minorEdit);

		if (content == null) {
			wikiPageImpl.setContent(StringPool.BLANK);
		}
		else {
			wikiPageImpl.setContent(content);
		}

		if (summary == null) {
			wikiPageImpl.setSummary(StringPool.BLANK);
		}
		else {
			wikiPageImpl.setSummary(summary);
		}

		if (format == null) {
			wikiPageImpl.setFormat(StringPool.BLANK);
		}
		else {
			wikiPageImpl.setFormat(format);
		}

		wikiPageImpl.setHead(head);

		if (parentTitle == null) {
			wikiPageImpl.setParentTitle(StringPool.BLANK);
		}
		else {
			wikiPageImpl.setParentTitle(parentTitle);
		}

		if (redirectTitle == null) {
			wikiPageImpl.setRedirectTitle(StringPool.BLANK);
		}
		else {
			wikiPageImpl.setRedirectTitle(redirectTitle);
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			wikiPageImpl.setLastPublishDate(null);
		}
		else {
			wikiPageImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		wikiPageImpl.setStatus(status);
		wikiPageImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			wikiPageImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			wikiPageImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			wikiPageImpl.setStatusDate(null);
		}
		else {
			wikiPageImpl.setStatusDate(new Date(statusDate));
		}

		wikiPageImpl.resetOriginalValues();

		return wikiPageImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		pageId = objectInput.readLong();

		resourcePrimKey = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		nodeId = objectInput.readLong();
		title = objectInput.readUTF();

		version = objectInput.readDouble();

		minorEdit = objectInput.readBoolean();
		content = objectInput.readUTF();
		summary = objectInput.readUTF();
		format = objectInput.readUTF();

		head = objectInput.readBoolean();
		parentTitle = objectInput.readUTF();
		redirectTitle = objectInput.readUTF();
		lastPublishDate = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
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

		objectOutput.writeLong(pageId);

		objectOutput.writeLong(resourcePrimKey);

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

		objectOutput.writeLong(nodeId);

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		objectOutput.writeDouble(version);

		objectOutput.writeBoolean(minorEdit);

		if (content == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(content);
		}

		if (summary == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(summary);
		}

		if (format == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(format);
		}

		objectOutput.writeBoolean(head);

		if (parentTitle == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(parentTitle);
		}

		if (redirectTitle == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(redirectTitle);
		}

		objectOutput.writeLong(lastPublishDate);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);
	}

	public String uuid;
	public long pageId;
	public long resourcePrimKey;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long nodeId;
	public String title;
	public double version;
	public boolean minorEdit;
	public String content;
	public String summary;
	public String format;
	public boolean head;
	public String parentTitle;
	public String redirectTitle;
	public long lastPublishDate;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
}