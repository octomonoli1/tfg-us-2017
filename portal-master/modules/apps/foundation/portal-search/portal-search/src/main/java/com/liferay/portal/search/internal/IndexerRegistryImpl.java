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

package com.liferay.portal.search.internal;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.dummy.DummyIndexer;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.search.buffer.IndexerRequestBuffer;
import com.liferay.portal.search.buffer.IndexerRequestBufferOverflowHandler;
import com.liferay.portal.search.configuration.IndexerRegistryConfiguration;
import com.liferay.portal.search.index.IndexStatusManager;
import com.liferay.portal.search.internal.buffer.BufferedIndexerInvocationHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.ClassUtils;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 */
@Component(
	configurationPid = "com.liferay.portal.search.configuration.IndexerRegistryConfiguration",
	immediate = true, service = IndexerRegistry.class
)
public class IndexerRegistryImpl implements IndexerRegistry {

	@Override
	public <T> Indexer<T> getIndexer(Class<T> clazz) {
		return getIndexer(clazz.getName());
	}

	@Override
	public <T> Indexer<T> getIndexer(String className) {
		Indexer<T> indexer = (Indexer<T>)_indexers.get(className);

		return proxyIndexer(indexer);
	}

	@Override
	public Set<Indexer<?>> getIndexers() {
		return new HashSet<>(_indexers.values());
	}

	@Override
	public <T> Indexer<T> nullSafeGetIndexer(Class<T> clazz) {
		return nullSafeGetIndexer(clazz.getName());
	}

	@Override
	public <T> Indexer<T> nullSafeGetIndexer(String className) {
		Indexer<T> indexer = getIndexer(className);

		if (indexer != null) {
			return indexer;
		}

		if (_log.isInfoEnabled()) {
			_log.info("No indexer found for " + className);
		}

		return (Indexer<T>)_dummyIndexer;
	}

	@Override
	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY, unbind = "unregister"
	)
	public void register(Indexer<?> indexer) {
		Class<?> clazz = indexer.getClass();

		_indexers.put(clazz.getName(), indexer);

		_indexers.put(indexer.getClassName(), indexer);
	}

	@Override
	public void unregister(Indexer<?> indexer) {
		Class<?> clazz = indexer.getClass();

		unregister(clazz.getName());
		unregister(indexer.getClassName());
	}

	@Override
	public void unregister(String className) {
		_bufferedInvocationHandlers.remove(className);
		_indexers.remove(className);
		_proxiedIndexers.remove(className);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_indexerRegistryConfiguration = ConfigurableUtil.createConfigurable(
			IndexerRegistryConfiguration.class, properties);

		for (BufferedIndexerInvocationHandler bufferedIndexerInvocationHandler :
				_bufferedInvocationHandlers.values()) {

			bufferedIndexerInvocationHandler.setIndexerRegistryConfiguration(
				_indexerRegistryConfiguration);
		}
	}

	protected <T> Indexer<T> proxyIndexer(Indexer<T> indexer) {
		if (indexer == null) {
			return null;
		}

		IndexerRequestBuffer indexerRequestBuffer = IndexerRequestBuffer.get();

		if ((indexerRequestBuffer == null) ||
			!_indexerRegistryConfiguration.buffered()) {

			return indexer;
		}

		Indexer<?> proxiedIndexer = _proxiedIndexers.get(
			indexer.getClassName());

		if (proxiedIndexer == null) {
			List<?> interfaces = ClassUtils.getAllInterfaces(
				indexer.getClass());

			BufferedIndexerInvocationHandler bufferedIndexerInvocationHandler =
				new BufferedIndexerInvocationHandler(
					indexer, _indexStatusManager,
					_indexerRegistryConfiguration);

			if (_indexerRequestBufferOverflowHandler == null) {
				bufferedIndexerInvocationHandler.
					setIndexerRequestBufferOverflowHandler(
						_defaultIndexerRequestBufferOverflowHandler);
			}
			else {
				bufferedIndexerInvocationHandler.
					setIndexerRequestBufferOverflowHandler(
						_indexerRequestBufferOverflowHandler);
			}

			_bufferedInvocationHandlers.put(
				indexer.getClassName(), bufferedIndexerInvocationHandler);

			proxiedIndexer = (Indexer<?>)ProxyUtil.newProxyInstance(
				PortalClassLoaderUtil.getClassLoader(),
				interfaces.toArray(new Class[interfaces.size()]),
				bufferedIndexerInvocationHandler);

			_proxiedIndexers.put(indexer.getClassName(), proxiedIndexer);
		}

		return (Indexer<T>)proxiedIndexer;
	}

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void setIndexerRequestBufferOverflowHandler(
		IndexerRequestBufferOverflowHandler
			indexerRequestBufferOverflowHandler) {

		_indexerRequestBufferOverflowHandler =
			indexerRequestBufferOverflowHandler;

		for (BufferedIndexerInvocationHandler bufferedIndexerInvocationHandler :
				_bufferedInvocationHandlers.values()) {

			bufferedIndexerInvocationHandler.
				setIndexerRequestBufferOverflowHandler(
					_indexerRequestBufferOverflowHandler);
		}
	}

	protected void unsetIndexerRequestBufferOverflowHandler(
		IndexerRequestBufferOverflowHandler
			indexerRequestBufferOverflowHandler) {

		_indexerRequestBufferOverflowHandler = null;

		for (BufferedIndexerInvocationHandler bufferedIndexerInvocationHandler :
				_bufferedInvocationHandlers.values()) {

			bufferedIndexerInvocationHandler.
				setIndexerRequestBufferOverflowHandler(
					_defaultIndexerRequestBufferOverflowHandler);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		IndexerRegistryImpl.class);

	private final Map<String, BufferedIndexerInvocationHandler>
		_bufferedInvocationHandlers = new ConcurrentHashMap<>();

	@Reference(target = "(mode=DEFAULT)")
	private IndexerRequestBufferOverflowHandler
		_defaultIndexerRequestBufferOverflowHandler;

	private final Indexer<?> _dummyIndexer = new DummyIndexer();
	private volatile IndexerRegistryConfiguration _indexerRegistryConfiguration;
	private volatile IndexerRequestBufferOverflowHandler
		_indexerRequestBufferOverflowHandler;
	private final Map<String, Indexer<? extends Object>> _indexers =
		new ConcurrentHashMap<>();

	@Reference
	private IndexStatusManager _indexStatusManager;

	private final Map<String, Indexer<? extends Object>> _proxiedIndexers =
		new ConcurrentHashMap<>();

}