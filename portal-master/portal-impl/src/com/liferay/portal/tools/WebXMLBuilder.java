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

package com.liferay.portal.tools;

import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.UnsecureSAXReaderUtil;
import com.liferay.portal.servlet.filters.absoluteredirects.AbsoluteRedirectsFilter;
import com.liferay.portal.xml.DocumentImpl;
import com.liferay.util.xml.XMLMerger;
import com.liferay.util.xml.descriptor.WebXML23Descriptor;
import com.liferay.util.xml.descriptor.WebXML24Descriptor;

import java.io.IOException;

/**
 * @author Brian Wing Shun Chan
 * @author Tang Ying Jian
 * @author Brian Myunghun Kim
 * @author Minhchau Dang
 */
public class WebXMLBuilder {

	public static void main(String[] args) {
		ToolDependencies.wireBasic();

		if (args.length == 3) {
			new WebXMLBuilder(args[0], args[1], args[2]);
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	public static String organizeWebXML(String webXML)
		throws DocumentException, IOException {

		webXML = HtmlUtil.stripComments(webXML);

		Document document = UnsecureSAXReaderUtil.read(webXML);

		Element rootElement = document.getRootElement();

		double version = 2.3;

		version = GetterUtil.getDouble(
			rootElement.attributeValue("version"), version);

		XMLMerger xmlMerger = null;

		if (version == 2.3) {
			xmlMerger = new XMLMerger(new WebXML23Descriptor());
		}
		else {
			xmlMerger = new XMLMerger(new WebXML24Descriptor());
		}

		DocumentImpl documentImpl = (DocumentImpl)document;

		xmlMerger.organizeXML(documentImpl.getWrappedDocument());

		webXML = document.formattedString();

		return webXML;
	}

	public WebXMLBuilder(
		String originalWebXML, String customWebXML, String mergedWebXML) {

		try {
			String customContent = getCustomContent(customWebXML);

			String originalContent = FileUtil.read(originalWebXML);

			String mergedContent = originalContent;

			int x = customContent.indexOf("<filter-mapping>");

			if (x != -1) {
				int y = customContent.lastIndexOf("</filter-mapping>") + 17;

				String filterMappings = customContent.substring(x, y);

				int z = getOriginalContentIndex(originalContent);

				mergedContent =
					mergedContent.substring(0, z) + filterMappings +
						mergedContent.substring(z);

				customContent =
					customContent.substring(0, x) +
						customContent.substring(y + 1);
			}

			int z = getMergedContentIndex(mergedContent);

			mergedContent =
				mergedContent.substring(0, z) + customContent +
					mergedContent.substring(z);

			mergedContent = organizeWebXML(mergedContent);

			FileUtil.write(mergedWebXML, mergedContent, true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String getCustomContent(String customWebXML) throws IOException {
		String customContent = FileUtil.read(customWebXML);

		int x = customContent.indexOf("<web-app");

		x = customContent.indexOf(">", x) + 1;

		int y = customContent.indexOf("</web-app>");

		return customContent.substring(x, y);
	}

	protected int getMergedContentIndex(String content) {
		int x = content.indexOf("<web-app");

		x = content.indexOf(">", x) + 1;

		return x;
	}

	protected int getOriginalContentIndex(String content) {
		int x = content.indexOf(AbsoluteRedirectsFilter.class.getName());

		if (x == -1) {
			x = content.indexOf("<web-app");
			x = content.indexOf(">", x) + 1;

			return x;
		}

		x = content.lastIndexOf("<filter-name", x);
		x = content.indexOf(">", x) + 1;

		int y = content.indexOf("</filter-name>", x);

		String filterName = content.substring(x, y);

		x = content.lastIndexOf(filterName);

		y = content.indexOf("</filter-mapping>", x);
		y = content.indexOf(">", y) + 1;

		return y;
	}

}