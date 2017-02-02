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

package com.liferay.portal.dao.jdbc.spring;

import com.liferay.portal.kernel.dao.jdbc.DataSourceFactoryUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.util.PropsUtil;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * @author Brian Wing Shun Chan
 */
public class DataSourceFactoryBean extends AbstractFactoryBean<DataSource> {

	@Override
	public DataSource createInstance() throws Exception {
		Properties properties = _properties;

		if (properties == null) {
			properties = PropsUtil.getProperties(_propertyPrefix, true);
		}
		else {
			properties = PropertiesUtil.getProperties(
				properties, _propertyPrefix, true);
		}

		return DataSourceFactoryUtil.initDataSource(properties);
	}

	@Override
	public void destroyInstance(DataSource dataSource) throws Exception {
		DataSourceFactoryUtil.destroyDataSource(dataSource);
	}

	@Override
	public Class<DataSource> getObjectType() {
		return DataSource.class;
	}

	public void setProperties(Properties properties) {
		_properties = properties;
	}

	public void setPropertyPrefix(String propertyPrefix) {
		_propertyPrefix = propertyPrefix;
	}

	public void setPropertyPrefixLookup(String propertyPrefixLookup) {
		_propertyPrefix = PropsUtil.get(propertyPrefixLookup);
	}

	private Properties _properties;
	private String _propertyPrefix;

}