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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.io.Deserializer;
import com.liferay.portal.kernel.io.Serializer;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.lang.reflect.Method;

import java.nio.ByteBuffer;

import java.util.Arrays;
import java.util.Objects;

/**
 * Provides a serializable loose representation for {@link Method}, considering
 * the declaring class, name, and parameter types of the {@link Method}, while
 * ignoring its return type and exceptions. This means the compiler generated
 * bridging method is considered logically the same as it source counterpart. On
 * deserialization for a generic {@link Method}, the {@link Method} that is
 * resolved (bridge method or source method) is runtime environment dependent.
 * Whether it is resolved to a bridge method or source method is of no
 * consequence, as a force cast is performed on the method's return value,
 * assuring the same result.
 *
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class MethodKey implements Externalizable {

	/**
	 * The empty constructor is required by {@link Externalizable}. Do not use
	 * this for any other purpose.
	 */
	public MethodKey() {
	}

	public MethodKey(
		Class<?> declaringClass, String methodName,
		Class<?>... parameterTypes) {

		_declaringClass = declaringClass;
		_methodName = methodName;
		_parameterTypes = parameterTypes;
	}

	public MethodKey(Method method) {
		this(
			method.getDeclaringClass(), method.getName(),
			method.getParameterTypes());
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #MethodKey(Class, String,
	 *             Class...)}
	 */
	@Deprecated
	public MethodKey(
		String declaringClassName, String methodName,
		Class<?>... parameterTypes) {

		Thread currentThread = Thread.currentThread();

		ClassLoader classLoader = currentThread.getContextClassLoader();

		try {
			_declaringClass = classLoader.loadClass(declaringClassName);
		}
		catch (ClassNotFoundException cnfe) {
			throw new RuntimeException(cnfe);
		}

		_methodName = methodName;
		_parameterTypes = parameterTypes;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MethodKey)) {
			return false;
		}

		MethodKey methodKey = (MethodKey)obj;

		if ((_declaringClass == methodKey._declaringClass) &&
			Objects.equals(_methodName, methodKey._methodName) &&
			Arrays.equals(_parameterTypes, methodKey._parameterTypes)) {

			return true;
		}

		return false;
	}

	public Class<?> getDeclaringClass() {
		return _declaringClass;
	}

	public Method getMethod() throws NoSuchMethodException {
		return MethodCache.get(this);
	}

	public String getMethodName() {
		return _methodName;
	}

	public Class<?>[] getParameterTypes() {
		return _parameterTypes;
	}

	@Override
	public int hashCode() {

		// Using the same hash algorithm as java.lang.reflect.Method

		return _declaringClass.getName().hashCode() ^ _methodName.hashCode();
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		int size = objectInput.readInt();

		byte[] data = new byte[size];

		objectInput.readFully(data);

		Deserializer deserializer = new Deserializer(ByteBuffer.wrap(data));

		_declaringClass = deserializer.readObject();
		_methodName = deserializer.readString();

		int parameterTypesLength = deserializer.readInt();

		_parameterTypes = new Class<?>[parameterTypesLength];

		for (int i = 0; i < parameterTypesLength; i++) {
			_parameterTypes[i] = deserializer.readObject();
		}
	}

	@Override
	public String toString() {
		if (_toString != null) {
			return _toString;
		}

		StringBundler sb = new StringBundler(4 + _parameterTypes.length * 2);

		sb.append(_declaringClass.getName());
		sb.append(StringPool.PERIOD);
		sb.append(_methodName);
		sb.append(StringPool.OPEN_PARENTHESIS);

		for (Class<?> parameterType : _parameterTypes) {
			sb.append(parameterType.getName());
			sb.append(StringPool.COMMA);
		}

		sb.setIndex(sb.index() - 1);

		sb.append(StringPool.CLOSE_PARENTHESIS);

		_toString = sb.toString();

		return _toString;
	}

	public MethodKey transform(ClassLoader classLoader)
		throws ClassNotFoundException {

		Class<?> declaringClass = classLoader.loadClass(
			_declaringClass.getName());

		Class<?>[] parameterTypes = new Class<?>[_parameterTypes.length];

		for (int i = 0; i < _parameterTypes.length; i++) {
			parameterTypes[i] = classLoader.loadClass(
				_parameterTypes[i].getName());
		}

		return new MethodKey(declaringClass, _methodName, parameterTypes);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		Serializer serializer = new Serializer();

		serializer.writeObject(_declaringClass);
		serializer.writeString(_methodName);
		serializer.writeInt(_parameterTypes.length);

		for (Class<?> parameterType : _parameterTypes) {
			serializer.writeObject(parameterType);
		}

		ByteBuffer byteBuffer = serializer.toByteBuffer();

		objectOutput.writeInt(byteBuffer.remaining());
		objectOutput.write(
			byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining());
	}

	private static final long serialVersionUID = 1L;

	private Class<?> _declaringClass;
	private String _methodName;
	private Class<?>[] _parameterTypes;

	// Transient cache

	private String _toString;

}