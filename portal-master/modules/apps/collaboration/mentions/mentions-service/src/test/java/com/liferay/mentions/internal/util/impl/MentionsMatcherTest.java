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

package com.liferay.mentions.internal.util.impl;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Adolfo Pérez
 */
public class MentionsMatcherTest {

	@Test
	public void testMatchBBCodeAtMention() {
		assertEquals("user1", MentionsMatcher.match("[span]@user1[span]"));
	}

	@Test
	public void testMatchBBCodeXMLEntityMention() {
		assertEquals("user1", MentionsMatcher.match("[span]&#64;user1[span]"));
	}

	@Test
	public void testMatchHTMLAtMention() {
		assertEquals("user1", MentionsMatcher.match("<span>@user1</span>"));
	}

	@Test
	public void testMatchHTMLXMLEntityMention() {
		assertEquals("user1", MentionsMatcher.match("<span>&#64;user1</span>"));
	}

	@Test
	public void testMatchMixedContent() {
		String content =
			"Lorem ipsum @user1 dolor sit amet, consectetur adipiscing elit " +
				"Sed non venenatis &#64;user2 justo. Morbi augue mauris, " +
					"suscipit ]@user3 tempus@notthis @@neitherthis et,>@user4";

		assertEquals(
			Arrays.asList("user1", "user2", "user3", "user4"),
			MentionsMatcher.match(content));
	}

	@Test
	public void testMatchSimpleAtMention() {
		assertEquals("user1", MentionsMatcher.match("@user1"));
	}

	@Test
	public void testMatchSimpleAtMentions() {
		assertEquals(
			Arrays.asList("user1", "user2"),
			MentionsMatcher.match("@user1 @user2"));
	}

	@Test
	public void testMatchSimpleXMLEntityMention() {
		assertEquals("user1", MentionsMatcher.match("&#64;user1"));
	}

	@Test
	public void testMatchSimpleXMLEntityMentions() {
		assertEquals(
			Arrays.asList("user1", "user2"),
			MentionsMatcher.match("&#64;user1 &#64;user2"));
	}

	protected <T> void assertEquals(
		Iterable<T> iterable1, Iterable<T> iterable2) {

		Iterator<T> iterator1 = iterable1.iterator();
		Iterator<T> iterator2 = iterable2.iterator();

		int pos = 0;

		while (iterator1.hasNext()) {
			String message = String.format(
				"The second iterator has fewer elements than the first " +
					"one (exhausted at position %d)",
				pos);

			Assert.assertTrue(message, iterator2.hasNext());

			T value1 = iterator1.next();
			T value2 = iterator2.next();

			message = String.format(
				"Elements differ at position %d because '%s' does not equal " +
					"'%s'",
				pos, value1, value2);

			Assert.assertEquals(message, value1, value2);

			pos++;
		}

		String message = String.format(
			"The first iterator has fewer elements than the second one " +
				"(exhausted at position %d)",
			pos);

		Assert.assertTrue(message, !iterator2.hasNext());
	}

	protected <T> void assertEquals(T value, Iterable<T> iterable) {
		assertEquals(Arrays.asList(value), iterable);
	}

}