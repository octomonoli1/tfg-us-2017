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

package com.liferay.petra.xml;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;

import java.io.IOException;

/**
 * @author Leonardo Barros
 * @see com.liferay.util.xml.XMLUtil
 */
public class XMLUtil {

	public static String fixProlog(String xml) {

		// LEP-1921

		if (xml != null) {
			int pos = xml.indexOf(CharPool.LESS_THAN);

			if (pos > 0) {
				xml = xml.substring(pos);
			}
		}

		return xml;
	}

	public static String formatXML(Document document) {
		try {
			return document.formattedString(_XML_INDENT);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
	}

	public static String formatXML(String xml) {

		// This is only supposed to format your xml, however, it will also
		// unwantingly change &#169; and other characters like it into their
		// respective readable versions

		try {
			xml = StringUtil.replace(xml, "&#", "[$SPECIAL_CHARACTER$]");
			xml = Dom4jUtil.toString(xml, _XML_INDENT);
			xml = StringUtil.replace(xml, "[$SPECIAL_CHARACTER$]", "&#");

			return xml;
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
		catch (org.dom4j.DocumentException de) {
			throw new SystemException(de);
		}
	}

	public static String fromCompactSafe(String xml) {
		return StringUtil.replace(xml, "[$NEW_LINE$]", StringPool.NEW_LINE);
	}

	public static String stripInvalidChars(String xml) {
		if (Validator.isNull(xml)) {
			return xml;
		}

		// Strip characters that are not valid in the 1.0 XML spec
		// http://www.w3.org/TR/2000/REC-xml-20001006#NT-Char

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < xml.length(); i++) {
			char c = xml.charAt(i);

			if ((c == 0x9) || (c == 0xA) || (c == 0xD) ||
				((c >= 0x20) && (c <= 0xD7FF)) ||
				((c >= 0xE000) && (c <= 0xFFFD)) ||
				((c >= 0x10000) && (c <= 0x10FFFF))) {

				sb.append(c);
			}
		}

		return sb.toString();
	}

	public static String toCompactSafe(String xml) {
		return StringUtil.replace(
			xml,
			new String[] {
				StringPool.RETURN_NEW_LINE, StringPool.NEW_LINE,
				StringPool.RETURN
			},
			new String[] {"[$NEW_LINE$]", "[$NEW_LINE$]", "[$NEW_LINE$]"});
	}

	private static final String _XML_INDENT = "  ";

}