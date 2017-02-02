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

package com.liferay.portal.remote.axis.extender.client.test;

import com.liferay.portal.remote.axis.extender.test.service.http.CalcServiceSoap;
import com.liferay.portal.remote.axis.extender.test.service.http.CalcServiceSoapService;
import com.liferay.portal.remote.axis.extender.test.service.http.CalcServiceSoapServiceLocator;

import java.net.URL;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Carlos Sierra Andrés
 */
@RunAsClient
@RunWith(Arquillian.class)
public class AxisExtenderTest {

	@Test
	public void testGreeter() throws Exception {
		URL url = new URL(
			_url,
			"/o/com.liferay.portal.remote.axis.extender.test/api/axis" +
				"/CalcService?wsdl");

		CalcServiceSoapService calcServiceSoapService =
			new CalcServiceSoapServiceLocator();

		CalcServiceSoap calcServiceSoap =
			calcServiceSoapService.getCalcServiceSoapPort(url);

		Assert.assertEquals(5, calcServiceSoap.sum(2, 3));
	}

	@ArquillianResource
	private URL _url;

}