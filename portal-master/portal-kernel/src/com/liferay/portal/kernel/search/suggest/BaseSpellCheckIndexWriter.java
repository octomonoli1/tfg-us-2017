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

package com.liferay.portal.kernel.search.suggest;

import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.nio.charset.CharsetEncoderUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.InputStream;

import java.net.URL;

import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;

import java.util.List;

/**
 * @author Michael C. Han
 */
public abstract class BaseSpellCheckIndexWriter
	implements SpellCheckIndexWriter {

	@Override
	public void indexKeyword(
			SearchContext searchContext, float weight, String keywordType)
		throws SearchException {

		if (!keywordType.equals(SuggestionConstants.TYPE_QUERY_SUGGESTION) &&
			!keywordType.equals(SuggestionConstants.TYPE_SPELL_CHECKER)) {

			throw new IllegalArgumentException(
				"Invalid keyword type " + keywordType);
		}

		long groupId = 0;

		long[] groupIds = searchContext.getGroupIds();

		if ((groupIds != null) && (groupIds.length > 0)) {
			groupId = groupIds[1];
		}

		String keywordFieldName = Field.KEYWORD_SEARCH;
		String typeFieldValue = SuggestionConstants.TYPE_QUERY_SUGGESTION;
		int maxNGramLength = _querySuggestionMaxNGramLength;

		if (keywordType.equals(SuggestionConstants.TYPE_SPELL_CHECKER)) {
			keywordFieldName = Field.SPELL_CHECK_WORD;
			typeFieldValue = SuggestionConstants.TYPE_SPELL_CHECKER;
			maxNGramLength = 0;
		}

		try {
			indexKeyword(
				searchContext, groupId, searchContext.getLanguageId(),
				searchContext.getKeywords(), weight, keywordFieldName,
				typeFieldValue, maxNGramLength);
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	@Override
	public void indexQuerySuggestionDictionaries(SearchContext searchContext)
		throws SearchException {

		try {
			for (String languageId : _SUPPORTED_LOCALES) {
				indexKeywords(
					searchContext, languageId,
					PropsKeys.INDEX_SEARCH_QUERY_SUGGESTION_DICTIONARY,
					Field.KEYWORD_SEARCH,
					SuggestionConstants.TYPE_QUERY_SUGGESTION,
					_querySuggestionMaxNGramLength);
			}
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	@Override
	public void indexQuerySuggestionDictionary(SearchContext searchContext)
		throws SearchException {

		try {
			indexKeywords(
				searchContext, searchContext.getLanguageId(),
				PropsKeys.INDEX_SEARCH_QUERY_SUGGESTION_DICTIONARY,
				Field.KEYWORD_SEARCH, SuggestionConstants.TYPE_QUERY_SUGGESTION,
				_querySuggestionMaxNGramLength);
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	@Override
	public void indexSpellCheckerDictionaries(SearchContext searchContext)
		throws SearchException {

		try {
			for (String languageId : _SUPPORTED_LOCALES) {
				indexKeywords(
					searchContext, languageId,
					PropsKeys.INDEX_SEARCH_SPELL_CHECKER_DICTIONARY,
					Field.SPELL_CHECK_WORD,
					SuggestionConstants.TYPE_SPELL_CHECKER, 0);
			}
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	@Override
	public void indexSpellCheckerDictionary(SearchContext searchContext)
		throws SearchException {

		try {
			indexKeywords(
				searchContext, searchContext.getLanguageId(),
				PropsKeys.INDEX_SEARCH_SPELL_CHECKER_DICTIONARY,
				Field.SPELL_CHECK_WORD, SuggestionConstants.TYPE_SPELL_CHECKER,
				0);
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	public void setQuerySuggestionMaxNGramLength(
		int querySuggestionMaxNGramLength) {

		_querySuggestionMaxNGramLength = querySuggestionMaxNGramLength;
	}

	protected URL getResource(String name) {
		ClassLoader contextClassLoader =
			Thread.currentThread().getContextClassLoader();

		URL url = contextClassLoader.getResource(name);

		if (url == null) {
			ClassLoader portalClassLoader =
				PortalClassLoaderUtil.getClassLoader();

			url = portalClassLoader.getResource(name);
		}

		return url;
	}

	protected String getUID(
		long companyId, String languageId, String word, String... parameters) {

		StringBundler uidSB = new StringBundler(5);

		uidSB.append(String.valueOf(companyId));
		uidSB.append(StringPool.UNDERLINE);
		uidSB.append(Field.SPELL_CHECK_WORD);
		uidSB.append(StringPool.UNDERLINE);

		int length = 5;

		if (parameters != null) {
			length += 2 * parameters.length;
		}

		try {
			CharsetEncoder charsetEncoder =
				CharsetEncoderUtil.getCharsetEncoder(StringPool.UTF8);

			StringBundler keySB = new StringBundler(length);

			keySB.append(languageId);
			keySB.append(StringPool.UNDERLINE);
			keySB.append(word);
			keySB.append(StringPool.UNDERLINE);

			keySB.append(StringUtil.toLowerCase(word));

			if (parameters != null) {
				for (String parameter : parameters) {
					keySB.append(parameter);
					keySB.append(StringPool.UNDERLINE);
				}
			}

			String key = keySB.toString();

			byte[] bytes = DigesterUtil.digestRaw(
				Digester.MD5, charsetEncoder.encode(CharBuffer.wrap(key)));

			uidSB.append(Base64.encode(bytes));
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}

		return uidSB.toString();
	}

	protected abstract void indexKeyword(
			SearchContext searchContext, long groupId, String languageId,
			String keyword, float weight, String keywordFieldName,
			String typeFieldValue, int maxNGramLength)
		throws Exception;

	protected abstract void indexKeywords(
			SearchContext searchContext, long groupId, String languageId,
			InputStream inputStream, String keywordFieldName,
			String typeFieldValue, int maxNGramLength)
		throws Exception;

	protected void indexKeywords(
			SearchContext searchContext, long groupId, String languageId,
			String[] dictionaryFileNames, String keywordFieldName,
			String typeFieldValue, int maxNGramLength)
		throws Exception {

		for (String dictionaryFileName : dictionaryFileNames) {
			InputStream inputStream = null;

			if (_log.isInfoEnabled()) {
				_log.info(
					"Start indexing dictionary for " + dictionaryFileName);
			}

			try {
				URL url = getResource(dictionaryFileName);

				if (url == null) {
					if (_log.isWarnEnabled()) {
						_log.warn("Unable to read " + dictionaryFileName);
					}

					continue;
				}

				inputStream = url.openStream();

				if (inputStream == null) {
					if (_log.isWarnEnabled()) {
						_log.warn("Unable to read " + dictionaryFileName);
					}

					continue;
				}

				indexKeywords(
					searchContext, groupId, languageId, inputStream,
					keywordFieldName, typeFieldValue, maxNGramLength);
			}
			finally {
				StreamUtil.cleanUp(inputStream);
			}

			if (_log.isInfoEnabled()) {
				_log.info(
					"Finished indexing dictionary for " + dictionaryFileName);
			}
		}
	}

	protected void indexKeywords(
			SearchContext searchContext, String languageId, String propsKey,
			String keywordFieldName, String typeFieldValue, int maxNGramLength)
		throws Exception {

		String[] dictionaryFileNames = PropsUtil.getArray(
			propsKey, new Filter(languageId));

		indexKeywords(
			searchContext, 0, languageId, dictionaryFileNames, keywordFieldName,
			typeFieldValue, maxNGramLength);

		List<Group> groups = GroupLocalServiceUtil.getLiveGroups();

		for (Group group : groups) {
			String[] groupDictionaryFileNames = PropsUtil.getArray(
				propsKey,
				new Filter(languageId, String.valueOf(group.getGroupId())));

			if (ArrayUtil.isEmpty(groupDictionaryFileNames)) {
				continue;
			}

			indexKeywords(
				searchContext, group.getGroupId(), languageId,
				groupDictionaryFileNames, keywordFieldName, typeFieldValue,
				maxNGramLength);
		}
	}

	private static final String[] _SUPPORTED_LOCALES = StringUtil.split(
		PropsUtil.get(PropsKeys.INDEX_SEARCH_SPELL_CHECKER_SUPPORTED_LOCALES));

	private static final Log _log = LogFactoryUtil.getLog(
		BaseSpellCheckIndexWriter.class);

	private int _querySuggestionMaxNGramLength = 50;

}