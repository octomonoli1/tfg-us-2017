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

package com.liferay.portal.search.elasticsearch.internal.connection;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.search.elasticsearch.configuration.ElasticsearchConfiguration;
import com.liferay.portal.search.elasticsearch.connection.ElasticsearchConnection;
import com.liferay.portal.search.elasticsearch.connection.OperationMode;
import com.liferay.portal.search.elasticsearch.index.IndexFactory;
import com.liferay.portal.search.elasticsearch.settings.SettingsContributor;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.plugins.Plugin;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 */
@Component(
	configurationPid = "com.liferay.portal.search.elasticsearch.configuration.ElasticsearchConfiguration",
	immediate = true, property = {"operation.mode=REMOTE"},
	service = ElasticsearchConnection.class
)
public class RemoteElasticsearchConnection extends BaseElasticsearchConnection {

	@Override
	public OperationMode getOperationMode() {
		return OperationMode.REMOTE;
	}

	@Override
	@Reference(unbind = "-")
	public void setIndexFactory(IndexFactory indexFactory) {
		super.setIndexFactory(indexFactory);
	}

	public void setTransportAddresses(Set<String> transportAddresses) {
		_transportAddresses = transportAddresses;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		replaceElasticsearchConfiguration(properties);
	}

	@Override
	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(operation.mode=REMOTE)"
	)
	protected void addSettingsContributor(
		SettingsContributor settingsContributor) {

		super.addSettingsContributor(settingsContributor);
	}

	protected void addTransportAddress(
			TransportClient transportClient, String transportAddress)
		throws UnknownHostException {

		String[] transportAddressParts = StringUtil.split(
			transportAddress, StringPool.COLON);

		String host = transportAddressParts[0];

		int port = GetterUtil.getInteger(transportAddressParts[1]);

		InetAddress inetAddress = InetAddress.getByName(host);

		transportClient.addTransportAddress(
			new InetSocketTransportAddress(inetAddress, port));
	}

	@Override
	protected Client createClient() {
		if (_transportAddresses.isEmpty()) {
			throw new IllegalStateException(
				"There must be at least one transport address");
		}

		TransportClient transportClient = createTransportClient();

		for (String transportAddress : _transportAddresses) {
			try {
				addTransportAddress(transportClient, transportAddress);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to add transport address " + transportAddress,
						e);
				}
			}
		}

		return transportClient;
	}

	protected TransportClient createTransportClient() {
		TransportClient.Builder transportClientBuilder =
			TransportClient.builder();

		transportClientBuilder.settings(settingsBuilder);

		for (String plugin : transportClientPlugins) {
			transportClientBuilder.addPlugin(getPluginClass(plugin));
		}

		return transportClientBuilder.build();
	}

	@Deactivate
	protected void deactivate(Map<String, Object> properties) {
		close();
	}

	@SuppressWarnings("unchecked")
	protected Class<? extends Plugin> getPluginClass(String plugin) {
		try {
			return (Class<? extends Plugin>)Class.forName(plugin);
		}
		catch (ClassNotFoundException cnfe) {
			throw new IllegalArgumentException(
				"Elasticsearch plugin class not found: " + plugin, cnfe);
		}
	}

	@Override
	protected void loadRequiredDefaultConfigurations() {
		settingsBuilder.put(
			"client.transport.ignore_cluster_name",
			elasticsearchConfiguration.clientTransportIgnoreClusterName());
		settingsBuilder.put(
			"client.transport.nodes_sampler_interval",
			elasticsearchConfiguration.clientTransportNodesSamplerInterval());
		settingsBuilder.put(
			"client.transport.sniff",
			elasticsearchConfiguration.clientTransportSniff());
		settingsBuilder.put(
			"cluster.name", elasticsearchConfiguration.clusterName());
		settingsBuilder.put("http.enabled", false);
		settingsBuilder.put("node.client", true);
		settingsBuilder.put("node.data", false);
		settingsBuilder.put(
			"path.logs", props.get(PropsKeys.LIFERAY_HOME) + "/logs");
		settingsBuilder.put(
			"path.work", SystemProperties.get(SystemProperties.TMP_DIR));
	}

	@Modified
	protected synchronized void modified(Map<String, Object> properties) {
		replaceElasticsearchConfiguration(properties);

		if (isConnected()) {
			close();

			connect();
		}
	}

	@Override
	protected void removeSettingsContributor(
		SettingsContributor settingsContributor) {

		super.removeSettingsContributor(settingsContributor);
	}

	protected void replaceElasticsearchConfiguration(
		Map<String, Object> properties) {

		elasticsearchConfiguration = ConfigurableUtil.createConfigurable(
			ElasticsearchConfiguration.class, properties);

		String[] transportAddresses =
			elasticsearchConfiguration.transportAddresses();

		setTransportAddresses(SetUtil.fromArray(transportAddresses));
	}

	@Reference
	protected Props props;

	private static final Log _log = LogFactoryUtil.getLog(
		RemoteElasticsearchConnection.class);

	private Set<String> _transportAddresses = new HashSet<>();

}