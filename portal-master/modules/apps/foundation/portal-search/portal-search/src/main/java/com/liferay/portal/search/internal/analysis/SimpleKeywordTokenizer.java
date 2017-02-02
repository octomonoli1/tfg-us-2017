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

package com.liferay.portal.search.internal.analysis;

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.analysis.KeywordTokenizer;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true, property = {"mode=default"},
	service = KeywordTokenizer.class
)
public class SimpleKeywordTokenizer implements KeywordTokenizer {

	@Override
	public boolean requiresTokenization(String keyword) {
		int start = keyword.indexOf(CharPool.QUOTE);

		int end = keyword.indexOf(CharPool.QUOTE, start + 1);

		if (!((keyword.indexOf(CharPool.QUOTE) == 0) &&
			(keyword.lastIndexOf(CharPool.QUOTE) == (keyword.length() -1)))) {

			if (((start > -1) && (end > start)) ||
				((start == -1) && (end == -1) &&
				 (keyword.indexOf(CharPool.SPACE) != -1))) {

				return true;
			}
		}

		return false;
	}

	@Override
	public List<String> tokenize(String keyword) {
		List<String> tokens = new ArrayList<>();

		int start = keyword.indexOf(CharPool.QUOTE);

		int end = keyword.indexOf(CharPool.QUOTE, start + 1);

		tokenize(keyword, tokens, start, end);

		return tokens;
	}

	protected void tokenize(
		String keyword, List<String> tokens, int start, int end) {

		if ((start == -1) || (end == -1)) {
			keyword = keyword.trim();

			if (!keyword.isEmpty()) {
				tokenizeBySpace(keyword, tokens);
			}

			return;
		}

		String token = keyword.substring(0, start);

		token = token.trim();

		if (!token.isEmpty()) {
			tokenizeBySpace(token, tokens);
		}

		token = keyword.substring(start, end + 1);

		token = token.trim();

		if (!token.isEmpty()) {
			tokens.add(token);
		}

		if ((end + 1) > keyword.length()) {
			return;
		}

		keyword = keyword.substring(end + 1, keyword.length());

		keyword = keyword.trim();

		if (keyword.isEmpty()) {
			return;
		}

		start = keyword.indexOf(CharPool.QUOTE, end + 1);

		end = keyword.indexOf(CharPool.QUOTE, start + 1);

		tokenize(keyword, tokens, start, end);
	}

	protected void tokenizeBySpace(String keyword, List<String> tokens) {
		String[] keywordTokens = StringUtil.split(keyword, CharPool.SPACE);

		for (String keywordToken : keywordTokens) {
			keyword = keywordToken.trim();

			if (!keyword.isEmpty()) {
				tokens.add(keyword);
			}
		}
	}

}