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

package com.liferay.portal.security.pacl.jndi;

import java.util.Hashtable;
import java.util.Objects;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.spi.NamingManager;

/**
 * @author Raymond Augé
 */
public class SchemeAwareContextWrapper implements Context {

	public SchemeAwareContextWrapper(Context context) {
		_context = context;
	}

	@Override
	public Object addToEnvironment(String propName, Object propVal)
		throws NamingException {

		return _context.addToEnvironment(propName, propVal);
	}

	@Override
	public void bind(Name name, Object obj) throws NamingException {
		Context context = getContext(name);

		context.bind(name, obj);
	}

	@Override
	public void bind(String name, Object obj) throws NamingException {
		Context context = getContext(name);

		context.bind(name, obj);
	}

	@Override
	public void close() throws NamingException {
		_context.close();
	}

	@Override
	public Name composeName(Name name, Name prefix) throws NamingException {
		return _context.composeName(name, prefix);
	}

	@Override
	public String composeName(String name, String prefix)
		throws NamingException {

		return _context.composeName(name, prefix);
	}

	@Override
	public Context createSubcontext(Name name) throws NamingException {
		Context context = getContext(name);

		return context.createSubcontext(name);
	}

	@Override
	public Context createSubcontext(String name) throws NamingException {
		Context context = getContext(name);

		return context.createSubcontext(name);
	}

	@Override
	public void destroySubcontext(Name name) throws NamingException {
		Context context = getContext(name);

		context.destroySubcontext(name);
	}

	@Override
	public void destroySubcontext(String name) throws NamingException {
		Context context = getContext(name);

		context.destroySubcontext(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Context)) {
			return false;
		}

		Context context = (Context)obj;

		if (Objects.equals(_context, context)) {
			return true;
		}

		return false;
	}

	@Override
	public Hashtable<?, ?> getEnvironment() throws NamingException {
		return _context.getEnvironment();
	}

	@Override
	public String getNameInNamespace() throws NamingException {
		return _context.getNameInNamespace();
	}

	@Override
	public NameParser getNameParser(Name name) throws NamingException {
		Context context = getContext(name);

		return context.getNameParser(name);
	}

	@Override
	public NameParser getNameParser(String name) throws NamingException {
		Context context = getContext(name);

		return context.getNameParser(name);
	}

	@Override
	public int hashCode() {
		return _context.hashCode();
	}

	@Override
	public NamingEnumeration<NameClassPair> list(Name name)
		throws NamingException {

		Context context = getContext(name);

		return context.list(name);
	}

	@Override
	public NamingEnumeration<NameClassPair> list(String name)
		throws NamingException {

		Context context = getContext(name);

		return context.list(name);
	}

	@Override
	public NamingEnumeration<Binding> listBindings(Name name)
		throws NamingException {

		Context context = getContext(name);

		return context.listBindings(name);
	}

	@Override
	public NamingEnumeration<Binding> listBindings(String name)
		throws NamingException {

		Context context = getContext(name);

		return context.listBindings(name);
	}

	@Override
	public Object lookup(Name name) throws NamingException {
		Context context = getContext(name);

		return context.lookup(name);
	}

	@Override
	public Object lookup(String name) throws NamingException {
		Context context = getContext(name);

		return context.lookup(name);
	}

	@Override
	public Object lookupLink(Name name) throws NamingException {
		Context context = getContext(name);

		return context.lookupLink(name);
	}

	@Override
	public Object lookupLink(String name) throws NamingException {
		Context context = getContext(name);

		return context.lookupLink(name);
	}

	@Override
	public void rebind(Name name, Object obj) throws NamingException {
		Context context = getContext(name);

		context.rebind(name, obj);
	}

	@Override
	public void rebind(String name, Object obj) throws NamingException {
		Context context = getContext(name);

		context.rebind(name, obj);
	}

	@Override
	public Object removeFromEnvironment(String propName)
		throws NamingException {

		return _context.removeFromEnvironment(propName);
	}

	@Override
	public void rename(Name oldName, Name newName) throws NamingException {
		Context context = getContext(oldName);

		context.rename(oldName, newName);
	}

	@Override
	public void rename(String oldName, String newName) throws NamingException {
		Context context = getContext(oldName);

		context.rename(oldName, newName);
	}

	@Override
	public void unbind(Name name) throws NamingException {
		Context context = getContext(name);

		context.unbind(name);
	}

	@Override
	public void unbind(String name) throws NamingException {
		Context context = getContext(name);

		context.unbind(name);
	}

	protected Context getContext(Name name) throws NamingException {
		return getContext(name.toString());
	}

	protected Context getContext(String name) throws NamingException {
		String scheme = null;

		int x = name.indexOf(':');
		int y = name.indexOf('/');

		if ((x > 0) && ((y == -1) || (x < y))) {
			scheme = name.substring(0, x);
		}

		if (scheme != null) {
			Context context = NamingManager.getURLContext(
				scheme, _context.getEnvironment());

			if (context != null) {
				return context;
			}
		}

		return _context;
	}

	private final Context _context;

}