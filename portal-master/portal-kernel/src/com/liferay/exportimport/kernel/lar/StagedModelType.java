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

package com.liferay.exportimport.kernel.lar;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashCode;
import com.liferay.portal.kernel.util.HashCodeFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Zsolt Berentey
 */
@ProviderType
public class StagedModelType {

	public static final String REFERRER_CLASS_NAME_ALL =
		"referrer-class-name-all";

	public static final String REFERRER_CLASS_NAME_ANY =
		"referrer-class-name-any";

	public static final int REFERRER_CLASS_NAME_ID_ALL = -1;

	public static final int REFERRER_CLASS_NAME_ID_ANY = -2;

	public StagedModelType(Class<?> clazz) {
		setClassName(clazz.getName());
	}

	public StagedModelType(Class<?> clazz, Class<?> referrerClass) {
		setClassName(clazz.getName());
		setReferrerClassName(referrerClass.getName());
	}

	public StagedModelType(long classNameId) {
		setClassNameId(classNameId);
	}

	public StagedModelType(long classNameId, long referrerClassNameId) {
		setClassNameId(classNameId);
		setReferrerClassNameId(referrerClassNameId);
	}

	public StagedModelType(String className) {
		setClassName(className);
	}

	public StagedModelType(String className, String referrerClassName) {
		setClassName(className);
		setReferrerClassName(referrerClassName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || !(obj instanceof StagedModelType)) {
			return false;
		}

		StagedModelType stagedModelType = (StagedModelType)obj;

		if ((stagedModelType._classNameId != _classNameId) ||
			(stagedModelType._referrerClassNameId != _referrerClassNameId)) {

			return false;
		}

		return true;
	}

	public String getClassName() {
		return _className;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public String getClassSimpleName() {
		return _classSimpleName;
	}

	public String getReferrerClassName() {
		return _referrerClassName;
	}

	public long getReferrerClassNameId() {
		return _referrerClassNameId;
	}

	@Override
	public int hashCode() {
		HashCode hashCode = HashCodeFactoryUtil.getHashCode();

		hashCode.append(_classNameId);
		hashCode.append(_referrerClassNameId);

		return hashCode.toHashCode();
	}

	@Override
	public String toString() {
		if (_referrerClassNameId <= 0) {
			return _className;
		}

		return _className.concat(StringPool.POUND).concat(_referrerClassName);
	}

	protected String getSimpleName(String className) {
		if (Validator.isNull(className)) {
			return StringPool.BLANK;
		}

		int pos = className.lastIndexOf(StringPool.PERIOD) + 1;

		if (pos <= 0) {
			return className;
		}

		return className.substring(pos);
	}

	protected void setClassName(String className) {
		_className = className;
		_classSimpleName = getSimpleName(_className);

		if (Validator.isNotNull(className)) {
			_classNameId = PortalUtil.getClassNameId(className);
		}
		else {
			_classNameId = 0;
		}
	}

	protected void setClassNameId(long classNameId) {
		if (classNameId > 0) {
			_className = PortalUtil.getClassName(classNameId);
			_classSimpleName = getSimpleName(_className);
		}
		else {
			_className = null;
			_classSimpleName = null;
		}

		_classNameId = classNameId;
	}

	protected void setClassSimpleName(String classSimpleName) {
		_classSimpleName = classSimpleName;
	}

	protected void setReferrerClassName(String referrerClassName) {
		_referrerClassName = referrerClassName;

		if (Validator.isNull(referrerClassName)) {
			_referrerClassNameId = 0;
		}
		else if (referrerClassName.equals(REFERRER_CLASS_NAME_ALL)) {
			_referrerClassNameId = REFERRER_CLASS_NAME_ID_ALL;
		}
		else if (referrerClassName.equals(REFERRER_CLASS_NAME_ANY)) {
			_referrerClassNameId = REFERRER_CLASS_NAME_ID_ANY;
		}
		else {
			_referrerClassNameId = PortalUtil.getClassNameId(referrerClassName);
		}
	}

	protected void setReferrerClassNameId(long referrerClassNameId) {
		_referrerClassNameId = referrerClassNameId;

		if (referrerClassNameId == 0) {
			_referrerClassName = null;
		}
		else if (referrerClassNameId == REFERRER_CLASS_NAME_ID_ALL) {
			_referrerClassName = REFERRER_CLASS_NAME_ALL;
		}
		else if (referrerClassNameId == REFERRER_CLASS_NAME_ID_ANY) {
			_referrerClassName = REFERRER_CLASS_NAME_ANY;
		}
		else if (referrerClassNameId > 0) {
			_referrerClassName = PortalUtil.getClassName(referrerClassNameId);
		}
	}

	private String _className;
	private long _classNameId;
	private String _classSimpleName;
	private String _referrerClassName;
	private long _referrerClassNameId;

}