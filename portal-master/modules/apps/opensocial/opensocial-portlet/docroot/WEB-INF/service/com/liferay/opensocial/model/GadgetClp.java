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

package com.liferay.opensocial.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.opensocial.service.ClpSerializer;
import com.liferay.opensocial.service.GadgetLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class GadgetClp extends BaseModelImpl<Gadget> implements Gadget {
	public GadgetClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Gadget.class;
	}

	@Override
	public String getModelClassName() {
		return Gadget.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _gadgetId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setGadgetId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _gadgetId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("gadgetId", getGadgetId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("url", getUrl());
		attributes.put("portletCategoryNames", getPortletCategoryNames());
		attributes.put("lastPublishDate", getLastPublishDate());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long gadgetId = (Long)attributes.get("gadgetId");

		if (gadgetId != null) {
			setGadgetId(gadgetId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		String portletCategoryNames = (String)attributes.get(
				"portletCategoryNames");

		if (portletCategoryNames != null) {
			setPortletCategoryNames(portletCategoryNames);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public String getUuid() {
		return _uuid;
	}

	@Override
	public void setUuid(String uuid) {
		_uuid = uuid;

		if (_gadgetRemoteModel != null) {
			try {
				Class<?> clazz = _gadgetRemoteModel.getClass();

				Method method = clazz.getMethod("setUuid", String.class);

				method.invoke(_gadgetRemoteModel, uuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getGadgetId() {
		return _gadgetId;
	}

	@Override
	public void setGadgetId(long gadgetId) {
		_gadgetId = gadgetId;

		if (_gadgetRemoteModel != null) {
			try {
				Class<?> clazz = _gadgetRemoteModel.getClass();

				Method method = clazz.getMethod("setGadgetId", long.class);

				method.invoke(_gadgetRemoteModel, gadgetId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_gadgetRemoteModel != null) {
			try {
				Class<?> clazz = _gadgetRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_gadgetRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_gadgetRemoteModel != null) {
			try {
				Class<?> clazz = _gadgetRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_gadgetRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_gadgetRemoteModel != null) {
			try {
				Class<?> clazz = _gadgetRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_gadgetRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setName(String name) {
		_name = name;

		if (_gadgetRemoteModel != null) {
			try {
				Class<?> clazz = _gadgetRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_gadgetRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUrl() {
		return _url;
	}

	@Override
	public void setUrl(String url) {
		_url = url;

		if (_gadgetRemoteModel != null) {
			try {
				Class<?> clazz = _gadgetRemoteModel.getClass();

				Method method = clazz.getMethod("setUrl", String.class);

				method.invoke(_gadgetRemoteModel, url);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPortletCategoryNames() {
		return _portletCategoryNames;
	}

	@Override
	public void setPortletCategoryNames(String portletCategoryNames) {
		_portletCategoryNames = portletCategoryNames;

		if (_gadgetRemoteModel != null) {
			try {
				Class<?> clazz = _gadgetRemoteModel.getClass();

				Method method = clazz.getMethod("setPortletCategoryNames",
						String.class);

				method.invoke(_gadgetRemoteModel, portletCategoryNames);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;

		if (_gadgetRemoteModel != null) {
			try {
				Class<?> clazz = _gadgetRemoteModel.getClass();

				Method method = clazz.getMethod("setLastPublishDate", Date.class);

				method.invoke(_gadgetRemoteModel, lastPublishDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				Gadget.class.getName()));
	}

	public BaseModel<?> getGadgetRemoteModel() {
		return _gadgetRemoteModel;
	}

	public void setGadgetRemoteModel(BaseModel<?> gadgetRemoteModel) {
		_gadgetRemoteModel = gadgetRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _gadgetRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_gadgetRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			GadgetLocalServiceUtil.addGadget(this);
		}
		else {
			GadgetLocalServiceUtil.updateGadget(this);
		}
	}

	@Override
	public Gadget toEscapedModel() {
		return (Gadget)ProxyUtil.newProxyInstance(Gadget.class.getClassLoader(),
			new Class[] { Gadget.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		GadgetClp clone = new GadgetClp();

		clone.setUuid(getUuid());
		clone.setGadgetId(getGadgetId());
		clone.setCompanyId(getCompanyId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setName(getName());
		clone.setUrl(getUrl());
		clone.setPortletCategoryNames(getPortletCategoryNames());
		clone.setLastPublishDate(getLastPublishDate());

		return clone;
	}

	@Override
	public int compareTo(Gadget gadget) {
		int value = 0;

		value = getName().compareTo(gadget.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GadgetClp)) {
			return false;
		}

		GadgetClp gadget = (GadgetClp)obj;

		long primaryKey = gadget.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", gadgetId=");
		sb.append(getGadgetId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", url=");
		sb.append(getUrl());
		sb.append(", portletCategoryNames=");
		sb.append(getPortletCategoryNames());
		sb.append(", lastPublishDate=");
		sb.append(getLastPublishDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.opensocial.model.Gadget");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>gadgetId</column-name><column-value><![CDATA[");
		sb.append(getGadgetId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>url</column-name><column-value><![CDATA[");
		sb.append(getUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portletCategoryNames</column-name><column-value><![CDATA[");
		sb.append(getPortletCategoryNames());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lastPublishDate</column-name><column-value><![CDATA[");
		sb.append(getLastPublishDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _gadgetId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _url;
	private String _portletCategoryNames;
	private Date _lastPublishDate;
	private BaseModel<?> _gadgetRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}