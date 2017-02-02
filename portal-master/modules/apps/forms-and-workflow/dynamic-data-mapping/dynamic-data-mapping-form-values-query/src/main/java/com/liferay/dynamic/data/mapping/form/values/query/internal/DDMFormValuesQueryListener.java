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

package com.liferay.dynamic.data.mapping.form.values.query.internal;

import com.liferay.dynamic.data.mapping.form.values.query.internal.model.DDMFormFieldValueMatcher;
import com.liferay.dynamic.data.mapping.form.values.query.internal.model.DDMFormFieldValueMatchesAllMatcher;
import com.liferay.dynamic.data.mapping.form.values.query.internal.model.DDMFormFieldValueMatchesAnyMatcher;
import com.liferay.dynamic.data.mapping.form.values.query.internal.model.DDMFormFieldValueNameMatcher;
import com.liferay.dynamic.data.mapping.form.values.query.internal.model.DDMFormFieldValueTypeMatcher;
import com.liferay.dynamic.data.mapping.form.values.query.internal.model.DDMFormFieldValueValueMatcher;
import com.liferay.dynamic.data.mapping.form.values.query.internal.model.DDMFormValuesFilter;
import com.liferay.dynamic.data.mapping.form.values.query.internal.model.DDMFormValuesFilterImpl;
import com.liferay.dynamic.data.mapping.form.values.query.internal.parser.DDMFormValuesQueryBaseListener;
import com.liferay.dynamic.data.mapping.form.values.query.internal.parser.DDMFormValuesQueryParser.AttributeTypeContext;
import com.liferay.dynamic.data.mapping.form.values.query.internal.parser.DDMFormValuesQueryParser.AttributeValueContext;
import com.liferay.dynamic.data.mapping.form.values.query.internal.parser.DDMFormValuesQueryParser.FieldSelectorContext;
import com.liferay.dynamic.data.mapping.form.values.query.internal.parser.DDMFormValuesQueryParser.FieldSelectorExpressionContext;
import com.liferay.dynamic.data.mapping.form.values.query.internal.parser.DDMFormValuesQueryParser.LocaleExpressionContext;
import com.liferay.dynamic.data.mapping.form.values.query.internal.parser.DDMFormValuesQueryParser.PredicateAndExpressionContext;
import com.liferay.dynamic.data.mapping.form.values.query.internal.parser.DDMFormValuesQueryParser.PredicateEqualityExpressionContext;
import com.liferay.dynamic.data.mapping.form.values.query.internal.parser.DDMFormValuesQueryParser.PredicateOrExpressionContext;
import com.liferay.dynamic.data.mapping.form.values.query.internal.parser.DDMFormValuesQueryParser.SelectorExpressionContext;
import com.liferay.dynamic.data.mapping.form.values.query.internal.parser.DDMFormValuesQueryParser.StepTypeContext;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @author Marcellus Tavares
 * @author Pablo Carvalho
 */
public class DDMFormValuesQueryListener extends DDMFormValuesQueryBaseListener {

	@Override
	public void enterAttributeType(AttributeTypeContext attributeTypeContext) {
		ddmFormFieldValueMatchers.push(new DDMFormFieldValueTypeMatcher());
	}

	@Override
	public void enterAttributeValue(
		AttributeValueContext attributeValueContext) {

		ddmFormFieldValueMatchers.push(new DDMFormFieldValueValueMatcher());
	}

	@Override
	public void enterFieldSelectorExpression(
		FieldSelectorExpressionContext fieldSelectorExpressionContext) {

		ddmFormFieldValueMatchers.push(
			new DDMFormFieldValueMatchesAllMatcher());
	}

	@Override
	public void enterPredicateAndExpression(
		PredicateAndExpressionContext predicateAndExpressionContext) {

		ddmFormFieldValueMatchers.push(
			new DDMFormFieldValueMatchesAllMatcher());
	}

	@Override
	public void enterPredicateOrExpression(
		PredicateOrExpressionContext predicateOrExpressionContext) {

		ddmFormFieldValueMatchers.push(
			new DDMFormFieldValueMatchesAnyMatcher());
	}

	@Override
	public void enterSelectorExpression(
		SelectorExpressionContext selectorExpressionContext) {

		ddmFormValuesFilters.add(new DDMFormValuesFilterImpl());
	}

	@Override
	public void exitFieldSelector(FieldSelectorContext fieldSelectorContext) {
		String text = fieldSelectorContext.getText();

		if (text.equals(StringPool.STAR)) {
			return;
		}

		DDMFormFieldValueNameMatcher ddmFormFieldValueNameMatcher =
			new DDMFormFieldValueNameMatcher();

		ddmFormFieldValueNameMatcher.setName(text);

		DDMFormFieldValueMatchesAllMatcher
			previousDDMFormFieldValueMatchesAllMatcher =
				(DDMFormFieldValueMatchesAllMatcher)
					ddmFormFieldValueMatchers.peek();

		previousDDMFormFieldValueMatchesAllMatcher.addDDMFormFieldValueMatcher(
			ddmFormFieldValueNameMatcher);
	}

	@Override
	public void exitFieldSelectorExpression(
		FieldSelectorExpressionContext fieldSelectorExpressionContext) {

		DDMFormFieldValueMatcher ddmFormFieldValueMatcher =
			ddmFormFieldValueMatchers.pop();

		DDMFormValuesFilter lastDDMFormValuesFilter = ddmFormValuesFilters.get(
			ddmFormValuesFilters.size() - 1);

		lastDDMFormValuesFilter.setDDMFormFieldValueMatcher(
			ddmFormFieldValueMatcher);
	}

	@Override
	public void exitLocaleExpression(
		LocaleExpressionContext localeExpressionContext) {

		ParseTree stringLiteral = localeExpressionContext.STRING_LITERAL();

		String languageId = StringUtil.unquote(stringLiteral.getText());

		DDMFormFieldValueValueMatcher lastDDMFormFieldValueValueMatcher =
			(DDMFormFieldValueValueMatcher)ddmFormFieldValueMatchers.peek();

		lastDDMFormFieldValueValueMatcher.setLocale(
			LocaleUtil.fromLanguageId(languageId));
	}

	@Override
	public void exitPredicateAndExpression(
		PredicateAndExpressionContext predicateAndExpressionContext) {

		DDMFormFieldValueMatcher andDDMFormFieldValueMatcher =
			ddmFormFieldValueMatchers.pop();

		DDMFormFieldValueMatchesAnyMatcher
			orDDMFormFieldValueMatchesAnyMatcher =
				(DDMFormFieldValueMatchesAnyMatcher)
					ddmFormFieldValueMatchers.peek();

		orDDMFormFieldValueMatchesAnyMatcher.addDDMFormFieldValueMatcher(
			andDDMFormFieldValueMatcher);
	}

	@Override
	public void exitPredicateEqualityExpression(
		PredicateEqualityExpressionContext predicateEqualityExpressionContext) {

		DDMFormFieldValueMatcher lastDDMFormFieldValueMatcher =
			ddmFormFieldValueMatchers.pop();

		ParseTree stringLiteral =
			predicateEqualityExpressionContext.STRING_LITERAL();

		String text = StringUtil.unquote(stringLiteral.getText());

		if (lastDDMFormFieldValueMatcher
				instanceof DDMFormFieldValueTypeMatcher) {

			DDMFormFieldValueTypeMatcher ddmFormFieldValueTypeMatcher =
				(DDMFormFieldValueTypeMatcher)lastDDMFormFieldValueMatcher;

			ddmFormFieldValueTypeMatcher.setType(text);
		}
		else {
			DDMFormFieldValueValueMatcher ddmFormFieldValueValueMatcher =
				(DDMFormFieldValueValueMatcher)lastDDMFormFieldValueMatcher;

			ddmFormFieldValueValueMatcher.setValue(text);
		}

		DDMFormFieldValueMatchesAllMatcher
			andDDMFormFieldValueMatchesAllMatcher =
				(DDMFormFieldValueMatchesAllMatcher)
					ddmFormFieldValueMatchers.peek();

		andDDMFormFieldValueMatchesAllMatcher.addDDMFormFieldValueMatcher(
			lastDDMFormFieldValueMatcher);
	}

	@Override
	public void exitPredicateOrExpression(
		PredicateOrExpressionContext predicateOrExpressionContext) {

		DDMFormFieldValueMatcher orDDMFormFieldValueMatcher =
			ddmFormFieldValueMatchers.pop();

		DDMFormFieldValueMatchesAllMatcher
			previousDDMFormFieldValueMatchesAllMatcher =
				(DDMFormFieldValueMatchesAllMatcher)
					ddmFormFieldValueMatchers.peek();

		previousDDMFormFieldValueMatchesAllMatcher.addDDMFormFieldValueMatcher(
			orDDMFormFieldValueMatcher);
	}

	@Override
	public void exitStepType(StepTypeContext stepTypeContext) {
		String text = stepTypeContext.getText();

		DDMFormValuesFilter lastDDMFormValuesFilter = ddmFormValuesFilters.get(
			ddmFormValuesFilters.size() - 1);

		if (text.equals(StringPool.SLASH)) {
			lastDDMFormValuesFilter.setGreedy(false);
		}
		else {
			lastDDMFormValuesFilter.setGreedy(true);
		}
	}

	public List<DDMFormValuesFilter> getDDMFormValuesFilters() {
		return ddmFormValuesFilters;
	}

	public Stack<DDMFormFieldValueMatcher> ddmFormFieldValueMatchers =
		new Stack<>();
	public List<DDMFormValuesFilter> ddmFormValuesFilters = new ArrayList<>();

}