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

package com.liferay.portal.kernel.dao.orm;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.TransactionalTestRule;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Sampsa Sohlman
 */
public class QueryUtilTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), TransactionalTestRule.INSTANCE);

	@BeforeClass
	public static void setUpClass() throws Exception {
		_db = DBManagerUtil.getDB();

		_db.runSQL(_SQL_CREATE_TABLE);
		_db.runSQL(createInserts(_SIZE));
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		_db.runSQL(_SQL_DROP_TABLE);
	}

	@Test
	public void testListModifiableAllPos() throws Exception {
		testList(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, false, _SIZE, 0, _SIZE - 1);
	}

	@Test
	public void testListModifiableFaultyParameters1() throws Exception {
		testList(-1, 0, false, 0, 0, 0);
	}

	@Test
	public void testListModifiableFaultyParameters2() throws Exception {
		testList(0, -1, false, 0, 0, 0);
	}

	@Test
	public void testListModifiableFaultyParameters3() throws Exception {
		testList(-2, -2, false, 0, 0, 0);
	}

	@Test
	public void testListModifiableFaultyParameters4() throws Exception {
		testList(-1, 10, false, 10, 0, 9);
	}

	@Test
	public void testListModifiableFaultyParameters5() throws Exception {
		testList(10, 8, false, 0, 0, 0);
	}

	@Test
	public void testListModifiableFirstTen() throws Exception {
		testList(0, 10, false, 10, 0, 9);
	}

	@Test
	public void testListModifiableFiveAfterFive() throws Exception {
		testList(5, 10, false, 5, 5, 9);
	}

	@Test
	public void testListModifiableTooBigRange() throws Exception {
		testList(_SIZE + 1, _SIZE + 21, false, 0, 0, 0);
	}

	@Test
	public void testListUnmodifiableAllPos() throws Exception {
		testList(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, true, _SIZE, 0, _SIZE - 1);
	}

	@Test
	public void testListUnmodifiableFaultyParameters1() throws Exception {
		testList(-1, 0, true, 0, 0, 0);
	}

	@Test
	public void testListUnmodifiableFaultyParameters2() throws Exception {
		testList(0, -1, true, 0, 0, 0);
	}

	@Test
	public void testListUnmodifiableFaultyParameters3() throws Exception {
		testList(-2, -2, true, 0, 0, 0);
	}

	@Test
	public void testListUnmodifiableFaultyParameters4() throws Exception {
		testList(-1, 10, true, 10, 0, 9);
	}

	@Test
	public void testListUnmodifiableFaultyParameters5() throws Exception {
		testList(10, 8, true, 0, 0, 0);
	}

	@Test
	public void testListUnmodifiableFirstTen() throws Exception {
		testList(0, 10, true, 10, 0, 9);
	}

	@Test
	public void testListUnmodifiableFiveAfterFive() throws Exception {
		testList(5, 10, true, 5, 5, 9);
	}

	@Test
	public void testListUnmodifiableTooBigRange() throws Exception {
		testList(_SIZE + 1, _SIZE + 21, true, 0, 0, 0);
	}

	@Test
	public void testSQLWithLimitWithOuterOrder1Ascending() throws Exception {
		testSQLWithLimitWithOuterOrder("ASC", 0, 10, 0, 9);
	}

	@Test
	public void testSQLWithLimitWithOuterOrder1Descending() throws Exception {
		testSQLWithLimitWithOuterOrder("DESC", 0, 10, 19, 10);
	}

	@Test
	public void testSQLWithLimitWithOuterOrder2Ascending() throws Exception {
		testSQLWithLimitWithOuterOrder("ASC", 5, 15, 5, 14);
	}

	@Test
	public void testSQLWithLimitWithOuterOrder2Descending() throws Exception {
		testSQLWithLimitWithOuterOrder("DESC", 5, 15, 14, 5);
	}

	@Test
	public void testSQLWithLimitWithOuterOrder3Ascending() throws Exception {
		testSQLWithLimitWithOuterOrder("ASC", 10, 20, 10, 19);
	}

	@Test
	public void testSQLWithLimitWithOuterOrder3Descending() throws Exception {
		testSQLWithLimitWithOuterOrder("DESC", 10, 20, 9, 0);
	}

	@Test
	public void testUnionSQL1() throws Exception {
		testUnionSQL(
			"ASC", _SIZE / 2, _SIZE + (_SIZE / 2), _SIZE, "id", "value");
	}

	@Test
	public void testUnionSQL2() throws Exception {
		testUnionSQL(
			"DESC", _SIZE / 2, _SIZE + (_SIZE / 2), _SIZE, "value", "id");
	}

	@Test
	public void testUnionSQL3() throws Exception {
		testUnionSQL("ASC", 0, _SIZE, _SIZE, "id", "id");
	}

	@Test
	public void testUnionSQL4() throws Exception {
		testUnionSQL("DESC", 0, _SIZE, _SIZE, "value", "value");
	}

	@Test
	public void testUnionSQL5() throws Exception {
		testUnionSQL("ASC", _SIZE, _SIZE * 2, _SIZE, "value", "value");
	}

	@Test
	public void testUnionSQL6() throws Exception {
		testUnionSQL("DESC", _SIZE, _SIZE * 2, _SIZE, "id", "id");
	}

	protected static String[] createInserts(int amount) {
		String[] sqls = new String[amount];

		for (int i = 0; i < amount; i++) {
			sqls[i] = StringUtil.replace(
				_SQL_INSERT, new String[] {"[$ID$]", "[$VALUE$]"},
				new String[] {String.valueOf(i), String.valueOf(i)});
		}

		return sqls;
	}

	protected void testList(
			int start, int end, boolean unmodifiable, int expectedSize,
			int expectedFirstValue, int expectedLastValue)
		throws Exception {

		Session session = null;

		try {
			session = _sessionFactory.openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(_SQL_SELECT);

			List<Object[]> result = (List<Object[]>)QueryUtil.list(
				q, _sessionFactory.getDialect(), start, end, unmodifiable);

			Assert.assertNotNull(result);
			Assert.assertEquals(expectedSize, result.size());

			if (expectedSize > 0) {
				Object[] firstRow = result.get(0);
				Object[] lastRow = result.get(result.size() - 1);

				Number firstId = (Number)firstRow[0];
				Number lastId = (Number)lastRow[0];

				Assert.assertEquals(expectedFirstValue, firstId.intValue());
				Assert.assertEquals(expectedLastValue, lastId.intValue());
			}

			try {
				result.add(new Object[0]);

				expectedSize++;

				Assert.assertFalse(unmodifiable);
			}
			catch (UnsupportedOperationException uoe) {
				Assert.assertTrue(unmodifiable);
			}

			Assert.assertEquals(expectedSize, result.size());
		}
		finally {
			_sessionFactory.closeSession(session);
		}
	}

	protected void testSQLWithLimitWithOuterOrder(
			String order, int start, int end, int expectedFirstValue,
			int expectedLastValue)
		throws Exception {

		Session session = null;

		try {
			session = _sessionFactory.openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(
				"SELECT id FROM QueryUtilTest ORDER BY value ".concat(order));

			sqlQuery.addScalar("id", Type.INTEGER);

			List<Integer> result = (List<Integer>)QueryUtil.list(
				sqlQuery, _sessionFactory.getDialect(), start, end, true);

			Assert.assertEquals(end - start, result.size());

			Number firstId = result.get(0);
			Number lastId = result.get(result.size() - 1);

			Assert.assertEquals(expectedFirstValue, firstId.intValue());
			Assert.assertEquals(expectedLastValue, lastId.intValue());
		}
		finally {
			_sessionFactory.closeSession(session);
		}
	}

	protected void testUnionSQL(
			String order, int start, int end, int size, String firstType,
			String lastType)
		throws Exception {

		Session session = null;

		try {
			session = _sessionFactory.openSession();

			String sql = _SQL_UNION_SELECT;

			if (order != null) {
				sql += " ORDER BY type ".concat(order);
			}

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			List<Object[]> result = (List<Object[]>)QueryUtil.list(
				q, _sessionFactory.getDialect(), start, end, true);

			Assert.assertEquals(size, result.size());

			Object[] firstRow = result.get(0);
			Object[] lastRow = result.get(result.size() - 1);

			Assert.assertEquals(
				firstType, StringUtil.trim((String)firstRow[0]));
			Assert.assertEquals(lastType, StringUtil.trim((String)lastRow[0]));
		}
		finally {
			_sessionFactory.closeSession(session);
		}
	}

	private static final int _SIZE = 20;

	private static final String _SQL_CREATE_TABLE =
		"CREATE TABLE QueryUtilTest (id INTEGER NOT NULL PRIMARY KEY, " +
			"value INTEGER)";

	private static final String _SQL_DROP_TABLE = "DROP TABLE QueryUtilTest";

	private static final String _SQL_INSERT =
		"INSERT INTO QueryUtilTest VALUES ([$ID$], [$VALUE$])";

	private static final String _SQL_SELECT =
		"SELECT id, value FROM QueryUtilTest ORDER BY id ASC";

	private static final String _SQL_UNION_SELECT =
		"( SELECT 'value' AS type, id as value from QueryUtilTest ) " +
			"UNION ALL ( SELECT 'id' AS type, id as value from QueryUtilTest )";

	private static DB _db;

	private final SessionFactory _sessionFactory =
		(SessionFactory)PortalBeanLocatorUtil.locate("liferaySessionFactory");

}