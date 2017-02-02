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

package com.liferay.portal.security.xml;

import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.NewEnv;
import com.liferay.portal.kernel.test.rule.NewEnv.JVMArgsLine;
import com.liferay.portal.kernel.test.rule.NewEnvTestRule;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.PropsValues;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;

import java.net.ConnectException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLEventReader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Tomas Polesovsky
 */
@JVMArgsLine("-Dattached=true -Xmx5m")
@NewEnv(type = NewEnv.Type.JVM)
public class SecureXMLFactoryProviderImplTest {

	@Before
	public void setUp() throws Exception {
		_secureXMLFactoryProvider = new SecureXMLFactoryProviderImpl();

		_xmlBombBillionLaughsXML = readDependency(
			"xml-bomb-billion-laughs.xml");
		_xmlBombQuadraticBlowupXML = readDependency(
			"xml-bomb-quadratic-blowup.xml");
		_xxeGeneralEntitiesXML1 = readDependency("xxe-general-entities-1.xml");
		_xxeGeneralEntitiesXML2 = readDependency("xxe-general-entities-2.xml");
		_xxeParameterEntitiesXML1 = readDependency(
			"xxe-parameter-entities-1.xml");
		_xxeParameterEntitiesXML2 = readDependency(
			"xxe-parameter-entities-2.xml");
	}

	@Test
	public void testNewDocumentBuilderFactory() throws Throwable {
		XMLSecurityTest documentBuilderTest = new XMLSecurityTest() {

			@Override
			public void run(String xml) throws Exception {
				DocumentBuilderFactory documentBuilderFactory =
					_secureXMLFactoryProvider.newDocumentBuilderFactory();

				DocumentBuilder documentBuilder =
					documentBuilderFactory.newDocumentBuilder();

				documentBuilder.parse(new ByteArrayInputStream(xml.getBytes()));
			}

		};

		runXMLSecurityTest(
			documentBuilderTest, _xmlBombBillionLaughsXML,
			OutOfMemoryError.class, "Billion Laughs XML attack does not work.",
			SAXParseException.class,
			"Vulnerable to Billion Laughs XML attack.");
		runXMLSecurityTest(
			documentBuilderTest, _xmlBombQuadraticBlowupXML,
			OutOfMemoryError.class,
			"Quadratic Blowup XML attack does not work.",
			SAXParseException.class,
			"Vulnerable to Quadratic Blowup XML attack.");
		runXMLSecurityTest(
			documentBuilderTest, _xxeGeneralEntitiesXML1,
			ConnectException.class,
			"General Entities XXE attack using SYSTEM entity does not work.",
			SAXParseException.class,
			"Vulnerable to General Entities XXE attack using SYSTEM entity.");
		runXMLSecurityTest(
			documentBuilderTest, _xxeGeneralEntitiesXML2,
			ConnectException.class,
			"General Entities XXE attack using PUBLIC entity does not work.",
			SAXParseException.class,
			"Vulnerable to General Entities XXE attack using PUBLIC entity.");
		runXMLSecurityTest(
			documentBuilderTest, _xxeParameterEntitiesXML1,
			ConnectException.class,
			"Parameter Entities XXE using SYSTEM entity does not work.",
			SAXParseException.class,
			"Vulnerable to Parameter Entities XXE using SYSTEM entity.");
		runXMLSecurityTest(
			documentBuilderTest, _xxeParameterEntitiesXML2,
			ConnectException.class,
			"Parameter Entities XXE attack using PUBLIC entity does not work.",
			SAXParseException.class,
			"Vulnerable to Parameter Entities XXE attack using PUBLIC entity.");
	}

	@Test
	public void testNewXMLInputFactory() throws Throwable {
		XMLSecurityTest xmlInputFactoryTest = new XMLSecurityTest() {

			@Override
			public void run(String xml) throws Exception {
				XMLEventReader xmlEventReader =
					_secureXMLFactoryProvider.newXMLInputFactory().
						createXMLEventReader(new StringReader(xml));

				while (xmlEventReader.hasNext()) {
					xmlEventReader.next();
				}
			}

		};

		runXMLSecurityTest(
			xmlInputFactoryTest, _xmlBombBillionLaughsXML,
			OutOfMemoryError.class, "Billion Laughs XML attack does not work.",
			null, "Vulnerable to Billion Laughs XML attack.");
		runXMLSecurityTest(
			xmlInputFactoryTest, _xmlBombQuadraticBlowupXML,
			OutOfMemoryError.class,
			"Quadratic Blowup XML attack does not work.", null,
			"Vulnerable to Quadratic Blowup XML attack.");
		runXMLSecurityTest(
			xmlInputFactoryTest, _xxeGeneralEntitiesXML1,
			ConnectException.class,
			"General Entities XXE attack using SYSTEM entity does not work.",
			null,
			"Vulnerable to General Entities XXE attack using SYSTEM entity.");
		runXMLSecurityTest(
			xmlInputFactoryTest, _xxeGeneralEntitiesXML2,
			ConnectException.class,
			"General Entities XXE attack using PUBLIC entity does not work.",
			null,
			"Vulnerable to  General Entities XXE attack using PUBLIC entity.");
		runXMLSecurityTest(
			xmlInputFactoryTest, _xxeParameterEntitiesXML1,
			ConnectException.class,
			"Parameter Entities XXE using SYSTEM entity does not work.", null,
			"Vulnerable to Parameter Entities XXE using SYSTEM entity.");
		runXMLSecurityTest(
			xmlInputFactoryTest, _xxeParameterEntitiesXML2,
			ConnectException.class,
			"Parameter Entities XXE attack using PUBLIC entity does not work.",
			null,
			"Vulnerable to Parameter Entities XXE attack using PUBLIC entity.");
	}

	@Test
	public void testNewXMLReader() throws Throwable {
		XMLSecurityTest xmlReaderTest = new XMLSecurityTest() {

			@Override
			public void run(String xml) throws Exception {
				XMLReader xmlReader = _secureXMLFactoryProvider.newXMLReader();

				if (xmlReader instanceof StripDoctypeXMLReader) {
					xmlReader =
						((StripDoctypeXMLReader)xmlReader).getXmlReader();
				}

				xmlReader.setContentHandler(
					new DefaultHandler() {

						@Override
						public void characters(
							char[] ch, int start, int length) {

							_contentLenght += length;

							if (_contentLenght > (1024 * 1024 * 10)) {
								throw new OutOfMemoryError();
							}
						}

						private int _contentLenght;

					});

				xmlReader.parse(new InputSource(new StringReader(xml)));
			}

		};

		runXMLSecurityTest(
			xmlReaderTest, _xmlBombBillionLaughsXML, OutOfMemoryError.class,
			"Billion Laughs XML attack does not work.", SAXParseException.class,
			"Vulnerable to Billion Laughs XML attack.");
		runXMLSecurityTest(
			xmlReaderTest, _xmlBombQuadraticBlowupXML, OutOfMemoryError.class,
			"Quadratic Blowup XML attack does not work.",
			SAXParseException.class,
			"Vulnerable to Quadratic Blowup XML attack.");
		runXMLSecurityTest(
			xmlReaderTest, _xxeGeneralEntitiesXML1, ConnectException.class,
			"General Entities XXE attack using SYSTEM entity does not work.",
			SAXParseException.class,
			"Vulnerable to General Entities XXE attack using SYSTEM entity.");
		runXMLSecurityTest(
			xmlReaderTest, _xxeGeneralEntitiesXML2, ConnectException.class,
			"General Entities XXE attack using PUBLIC entity does not work.",
			SAXParseException.class,
			"Vulnerable to General Entities XXE attack using PUBLIC entity.");
		runXMLSecurityTest(
			xmlReaderTest, _xxeParameterEntitiesXML1, ConnectException.class,
			"Parameter Entities XXE using SYSTEM entity does not work.",
			SAXParseException.class,
			"Vulnerable to Parameter Entities XXE using SYSTEM entity.");
		runXMLSecurityTest(
			xmlReaderTest, _xxeParameterEntitiesXML2, ConnectException.class,
			"Parameter Entities XXE attack using PUBLIC entity does not work.",
			SAXParseException.class,
			"Vulnerable to Parameter Entities XXE attack using PUBLIC entity.");
	}

	@Rule
	public final NewEnvTestRule newEnvTestRule = NewEnvTestRule.INSTANCE;

	protected static String readDependency(String name) throws IOException {
		return StringUtil.read(
			SecureXMLFactoryProviderImplTest.class.getResourceAsStream(
				"dependencies/" + name));
	}

	protected void runXMLSecurityTest(
			XMLSecurityTest xmlSecurityTest, String xml,
			Class<? extends Throwable> expectedException, String failMessage)
		throws Throwable {

		try {
			xmlSecurityTest.run(xml);

			if (expectedException != null) {
				Assert.fail(failMessage);
			}
		}
		catch (Throwable t) {
			if (expectedException == null) {
				throw t;
			}

			Throwable cause = t;

			while (cause.getCause() != null) {
				cause = cause.getCause();
			}

			Class<?> causeClass = cause.getClass();

			if (!causeClass.isAssignableFrom(expectedException)) {
				throw t;
			}
		}
	}

	protected void runXMLSecurityTest(
			XMLSecurityTest xmlSecurityTest, String xml,
			Class<? extends Throwable> disableXMLSecurityExpectedException,
			String disableXMLSecurityFailMessage,
			Class<? extends Throwable> enableXMLSecurityExpectedException,
			String enableXMLSecurityFailMessage)
		throws Throwable {

		boolean xmlSecurityEnabled = ReflectionTestUtil.getFieldValue(
			PropsValues.class, "XML_SECURITY_ENABLED");

		try {
			ReflectionTestUtil.setFieldValue(
				PropsValues.class, "XML_SECURITY_ENABLED", false);

			runXMLSecurityTest(
				xmlSecurityTest, xml, disableXMLSecurityExpectedException,
				disableXMLSecurityFailMessage);

			ReflectionTestUtil.setFieldValue(
				PropsValues.class, "XML_SECURITY_ENABLED", true);

			runXMLSecurityTest(
				xmlSecurityTest, xml, enableXMLSecurityExpectedException,
				enableXMLSecurityFailMessage);
		}
		finally {
			ReflectionTestUtil.setFieldValue(
				PropsValues.class, "XML_SECURITY_ENABLED", xmlSecurityEnabled);
		}
	}

	private static String _xmlBombBillionLaughsXML;
	private static String _xmlBombQuadraticBlowupXML;
	private static String _xxeGeneralEntitiesXML1;
	private static String _xxeGeneralEntitiesXML2;
	private static String _xxeParameterEntitiesXML1;
	private static String _xxeParameterEntitiesXML2;

	private SecureXMLFactoryProviderImpl _secureXMLFactoryProvider;

	private abstract class XMLSecurityTest {

		public abstract void run(String xml) throws Exception;

	}

}