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

package com.liferay.dynamic.data.mapping.model;

import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Marcellus Tavares
 */
public class UnlocalizedValue implements Value {

	public UnlocalizedValue(String value) {
		_values.put(LocaleUtil.ROOT, value);
	}

	public UnlocalizedValue(UnlocalizedValue unlocalizedValue) {
		_values.put(
			LocaleUtil.ROOT, unlocalizedValue.getString(LocaleUtil.ROOT));
	}

	@Override
	public void addString(Locale locale, String value) {
		_values.put(LocaleUtil.ROOT, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UnlocalizedValue)) {
			return false;
		}

		UnlocalizedValue unlocalizedValue = (UnlocalizedValue)obj;

		if (Objects.equals(_values, unlocalizedValue._values)) {
			return true;
		}

		return false;
	}

	@Override
	public Set<Locale> getAvailableLocales() {
		return _values.keySet();
	}

	@Override
	public Locale getDefaultLocale() {
		return LocaleUtil.ROOT;
	}

	@Override
	public String getString(Locale locale) {
		return _values.get(LocaleUtil.ROOT);
	}

	@Override
	public Map<Locale, String> getValues() {
		return _values;
	}

	@Override
	public int hashCode() {
		return _values.hashCode();
	}

	@Override
	public boolean isLocalized() {
		return false;
	}

	@Override
	public void setDefaultLocale(Locale defaultLocale) {
		throw new UnsupportedOperationException();
	}

	private final Map<Locale, String> _values = new HashMap<>();

}