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

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.util.CookieUtil;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Michael Young
 * @author Shuyang Zhou
 */
public class Header implements Externalizable {

	/**
	 * The empty constructor is required by {@link Externalizable}. Do not use
	 * this for any other purpose.
	 */
	public Header() {
	}

	public Header(Cookie cookie) {
		if (cookie == null) {
			throw new IllegalArgumentException("Cookie is null");
		}

		_type = Type.COOKIE;

		_cookieValue = cookie;
	}

	public Header(int integer) {
		_type = Type.INTEGER;

		_intValue = integer;
	}

	public Header(long date) {
		_type = Type.DATE;

		_dateValue = date;
	}

	public Header(String string) {
		if (string == null) {
			throw new IllegalArgumentException("String is null");
		}

		_type = Type.STRING;

		_stringValue = string;
	}

	public void addToResponse(String key, HttpServletResponse response) {
		if (_type == Type.COOKIE) {
			response.addCookie(_cookieValue);
		}
		else if (_type == Type.DATE) {
			response.addDateHeader(key, _dateValue);
		}
		else if (_type == Type.INTEGER) {
			response.addIntHeader(key, _intValue);
		}
		else if (_type == Type.STRING) {
			response.addHeader(key, _stringValue);
		}
		else {
			throw new IllegalStateException("Invalid type " + _type);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Header)) {
			return false;
		}

		Header header = (Header)obj;

		if (_type != header._type) {
			return false;
		}

		if (_type == Type.COOKIE) {
			return _equals(_cookieValue, header._cookieValue);
		}
		else if (_type == Type.DATE) {
			if (_dateValue == header._dateValue) {
				return true;
			}

			return false;
		}
		else if (_type == Type.INTEGER) {
			if (_intValue == header._intValue) {
				return true;
			}

			return false;
		}
		else if (_type == Type.STRING) {
			return _stringValue.equals(header._stringValue);
		}
		else {
			throw new IllegalStateException("Invalid type " + _type);
		}
	}

	@Override
	public int hashCode() {
		if (_type == Type.COOKIE) {
			return _hashCode(_cookieValue);
		}
		else if (_type == Type.DATE) {
			return (int)(_dateValue ^ (_dateValue >>> 32));
		}
		else if (_type == Type.INTEGER) {
			return _intValue;
		}
		else if (_type == Type.STRING) {
			return _stringValue.hashCode();
		}
		else {
			throw new IllegalStateException("Invalid type " + _type);
		}
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		if (objectInput.readBoolean()) {
			int size = objectInput.readInt();

			byte[] data = new byte[size];

			objectInput.readFully(data);

			_cookieValue = CookieUtil.deserialize(data);
		}

		_dateValue = objectInput.readLong();
		_intValue = objectInput.readInt();

		String stringValue = objectInput.readUTF();

		if (!stringValue.isEmpty()) {
			_stringValue = stringValue;
		}

		_type = Type.values()[objectInput.readInt()];
	}

	public void setToResponse(String key, HttpServletResponse response) {
		if (_type == Type.COOKIE) {
			response.addCookie(_cookieValue);
		}
		else if (_type == Type.DATE) {
			response.setDateHeader(key, _dateValue);
		}
		else if (_type == Type.INTEGER) {
			response.setIntHeader(key, _intValue);
		}
		else if (_type == Type.STRING) {
			response.setHeader(key, _stringValue);
		}
		else {
			throw new IllegalStateException("Invalid type " + _type);
		}
	}

	@Override
	public String toString() {
		if (_type == Type.COOKIE) {
			return CookieUtil.toString(_cookieValue);
		}
		else if (_type == Type.DATE) {
			return String.valueOf(_dateValue);
		}
		else if (_type == Type.INTEGER) {
			return String.valueOf(_intValue);
		}
		else if (_type == Type.STRING) {
			return _stringValue;
		}
		else {
			throw new IllegalStateException("Invalid type " + _type);
		}
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (_cookieValue == null) {
			objectOutput.writeBoolean(false);
		}
		else {
			objectOutput.writeBoolean(true);

			byte[] data = CookieUtil.serialize(_cookieValue);

			objectOutput.writeInt(data.length);
			objectOutput.write(data);
		}

		objectOutput.writeLong(_dateValue);
		objectOutput.writeInt(_intValue);

		if (_stringValue == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(_stringValue);
		}

		objectOutput.writeInt(_type.ordinal());
	}

	private boolean _equals(Cookie cookie1, Cookie cookie2) {
		if (cookie1 == cookie2) {
			return true;
		}

		if (!Objects.equals(cookie1.getComment(), cookie2.getComment()) ||
			!Objects.equals(cookie1.getDomain(), cookie2.getDomain()) ||
			(cookie1.getMaxAge() != cookie2.getMaxAge()) ||
			!Objects.equals(cookie1.getName(), cookie2.getName()) ||
			!Objects.equals(cookie1.getPath(), cookie2.getPath()) ||
			(cookie1.getSecure() != cookie2.getSecure()) ||
			!Objects.equals(cookie1.getValue(), cookie2.getValue()) ||
			(cookie1.getVersion() != cookie2.getVersion())) {

			return false;
		}

		return true;
	}

	private int _hashCode(Cookie cookie) {
		int hashCode = HashUtil.hash(0, cookie.getComment());

		hashCode = HashUtil.hash(hashCode, cookie.getDomain());
		hashCode = HashUtil.hash(hashCode, cookie.getMaxAge());
		hashCode = HashUtil.hash(hashCode, cookie.getName());
		hashCode = HashUtil.hash(hashCode, cookie.getPath());
		hashCode = HashUtil.hash(hashCode, cookie.getSecure());
		hashCode = HashUtil.hash(hashCode, cookie.getValue());
		hashCode = HashUtil.hash(hashCode, cookie.getVersion());

		return hashCode;
	}

	private Cookie _cookieValue;
	private long _dateValue;
	private int _intValue;
	private String _stringValue;
	private Type _type;

	private static enum Type {

		COOKIE, DATE, INTEGER, STRING

	}

}