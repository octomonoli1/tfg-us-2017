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

package com.liferay.portal.search.elasticsearch.internal.suggest;

import com.liferay.portal.kernel.search.suggest.TermSuggester;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.elasticsearch.suggest.TermSuggesterTranslator;

import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.term.TermSuggestionBuilder;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = TermSuggesterTranslator.class)
public class TermSuggesterTranslatorImpl
	extends BaseSuggesterTranslatorImpl implements TermSuggesterTranslator {

	@Override
	public SuggestBuilder translate(TermSuggester termSuggester) {
		SuggestBuilder suggestBuilder = new SuggestBuilder(
			termSuggester.getName());

		TermSuggestionBuilder termSuggesterBuilder =
			SuggestBuilders.termSuggestion(termSuggester.getName());

		termSuggesterBuilder.field(termSuggester.getField());

		if (Validator.isNotNull(termSuggester.getAnalyzer())) {
			termSuggesterBuilder.analyzer(termSuggester.getAnalyzer());
		}

		if (termSuggester.getAccuracy() != null) {
			termSuggesterBuilder.setAccuracy(termSuggester.getAccuracy());
		}

		if (termSuggester.getMaxEdits() != null) {
			termSuggesterBuilder.maxEdits(termSuggester.getMaxEdits());
		}

		if (termSuggester.getMaxInspections() != null) {
			termSuggesterBuilder.maxInspections(
				termSuggester.getMaxInspections());
		}

		if (termSuggester.getMaxTermFreq() != null) {
			termSuggesterBuilder.maxTermFreq(termSuggester.getMaxTermFreq());
		}

		if (termSuggester.getMinWordLength() != null) {
			termSuggesterBuilder.minWordLength(
				termSuggester.getMinWordLength());
		}

		if (termSuggester.getMinDocFreq() != null) {
			termSuggesterBuilder.minDocFreq(termSuggester.getMinDocFreq());
		}

		if (termSuggester.getPrefixLength() != null) {
			termSuggesterBuilder.prefixLength(termSuggester.getPrefixLength());
		}

		if (termSuggester.getShardSize() != null) {
			termSuggesterBuilder.shardSize(termSuggester.getShardSize());
		}

		if (termSuggester.getSize() != null) {
			termSuggesterBuilder.size(termSuggester.getSize());
		}

		if (termSuggester.getSort() != null) {
			termSuggesterBuilder.sort(translate(termSuggester.getSort()));
		}

		if (termSuggester.getStringDistance() != null) {
			termSuggesterBuilder.stringDistance(
				translate(termSuggester.getStringDistance()));
		}

		if (termSuggester.getSuggestMode() != null) {
			termSuggesterBuilder.suggestMode(
				translate(termSuggester.getSuggestMode()));
		}

		termSuggesterBuilder.text(termSuggester.getValue());

		suggestBuilder.addSuggestion(termSuggesterBuilder);

		return suggestBuilder;
	}

}