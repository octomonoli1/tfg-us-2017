<#assign finderColsList = finder.getColumns()>

/**
 * Returns the number of ${entity.humanNames} where ${finder.getHumanConditions(false)}.
 *
<#list finderColsList as finderCol>
 * @param ${finderCol.name} the ${finderCol.humanName}
</#list>
 * @return the number of matching ${entity.humanNames}
 */
@Override
public int countBy${finder.name}(

<#list finderColsList as finderCol>
	${finderCol.type} ${finderCol.name}

	<#if finderCol_has_next>
		,
	</#if>
</#list>

) {
	FinderPath finderPath =
		<#if !finder.hasCustomComparator()>
			FINDER_PATH_COUNT_BY_${finder.name?upper_case};
		<#else>
			FINDER_PATH_WITH_PAGINATION_COUNT_BY_${finder.name?upper_case};
		</#if>

	Object[] finderArgs = new Object[] {
		<#list finderColsList as finderCol>
			${finderCol.name}

			<#if finderCol_has_next>
				,
			</#if>
		</#list>
	};

	Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

	if (count == null) {
		<#include "persistence_impl_count_by_query.ftl">

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			<@finderQPos />

			count = (Long)q.uniqueResult();

			finderCache.putResult(finderPath, finderArgs, count);
		}
		catch (Exception e) {
			finderCache.removeResult(finderPath, finderArgs);

			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	return count.intValue();
}

<#if finder.hasArrayableOperator()>
	/**
	 * Returns the number of ${entity.humanNames} where ${finder.getHumanConditions(true)}.
	 *
	<#list finderColsList as finderCol>
		<#if finderCol.hasArrayableOperator()>
	 * @param ${finderCol.names} the ${finderCol.humanNames}
		<#else>
	 * @param ${finderCol.name} the ${finderCol.humanName}
		</#if>
	</#list>
	 * @return the number of matching ${entity.humanNames}
	 */
	@Override
	public int countBy${finder.name}(

	<#list finderColsList as finderCol>
		<#if finderCol.hasArrayableOperator()>
			${finderCol.type}[] ${finderCol.names}
		<#else>
			${finderCol.type} ${finderCol.name}
		</#if>

		<#if finderCol_has_next>
			,
		</#if>
	</#list>

	) {
		<#list finderColsList as finderCol>
			<#if finderCol.hasArrayableOperator()>
				if (${finderCol.names} == null) {
					${finderCol.names} = new ${finderCol.type}[0];
				}
				else if (${finderCol.names}.length > 1) {
					${finderCol.names} =
						<#if finderCol.type == "String">
							ArrayUtil.distinct(${finderCol.names}, NULL_SAFE_STRING_COMPARATOR);
						<#else>
							ArrayUtil.unique(${finderCol.names});
						</#if>

					<#if finderCol.type == "String">
						Arrays.sort(${finderCol.names}, NULL_SAFE_STRING_COMPARATOR);
					<#else>
						Arrays.sort(${finderCol.names});
					</#if>
				}
			</#if>
		</#list>

		Object[] finderArgs = new Object[] {
			<#list finderColsList as finderCol>
				<#if finderCol.hasArrayableOperator()>
					StringUtil.merge(${finderCol.names})
				<#else>
					${finderCol.name}
				</#if>

				<#if finderCol_has_next>
					,
				</#if>
			</#list>
		};

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_${finder.name?upper_case}, finderArgs, this);

		if (count == null) {
			<#include "persistence_impl_count_by_arrayable_query.ftl">

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				<#if bindParameter(finderColsList)>
					QueryPos qPos = QueryPos.getInstance(q);
				</#if>

				<@finderQPos
					_arrayable=true
				/>

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_${finder.name?upper_case}, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_${finder.name?upper_case}, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}
</#if>

<#if entity.isPermissionCheckEnabled(finder)>
	/**
	 * Returns the number of ${entity.humanNames} that the user has permission to view where ${finder.getHumanConditions(false)}.
	 *
	<#list finderColsList as finderCol>
	 * @param ${finderCol.name} the ${finderCol.humanName}
	</#list>
	 * @return the number of matching ${entity.humanNames} that the user has permission to view
	 */
	@Override
	public int filterCountBy${finder.name}(

	<#list finderColsList as finderCol>
		${finderCol.type} ${finderCol.name}

		<#if finderCol_has_next>
			,
		</#if>
	</#list>

	) {
		<#if finder.hasColumn("groupId")>
			if (!InlineSQLHelperUtil.isEnabled(groupId)) {
		<#elseif finder.hasColumn("companyId")>
			if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
		<#else>
			if (!InlineSQLHelperUtil.isEnabled()) {
		</#if>

			return countBy${finder.name}(

			<#list finderColsList as finderCol>
				${finderCol.name}

				<#if finderCol_has_next>
					,
				</#if>
			</#list>

			);
		}

		<#if entity.isPermissionedModel()>
			<#include "persistence_impl_count_by_query.ftl">

			String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(), ${entity.name}.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, _FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN<#if finder.hasColumn("groupId")>, groupId</#if>);

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				<@finderQPos />

				Long count = (Long)q.uniqueResult();

				return count.intValue();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		<#else>
			StringBundler query = new StringBundler(${finderColsList?size + 1});

			query.append(_FILTER_SQL_COUNT_${entity.alias?upper_case}_WHERE);

			<#assign sqlQuery = true>

			<#include "persistence_impl_finder_cols.ftl">

			<#assign sqlQuery = false>

			String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(), ${entity.name}.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN<#if finder.hasColumn("groupId")>, groupId</#if>);

			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSynchronizedSQLQuery(sql);

				q.addScalar(COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				<@finderQPos />

				Long count = (Long)q.uniqueResult();

				return count.intValue();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		</#if>
	}

	<#if finder.hasArrayableOperator()>
		/**
		 * Returns the number of ${entity.humanNames} that the user has permission to view where ${finder.getHumanConditions(true)}.
		 *
		<#list finderColsList as finderCol>
			<#if finderCol.hasArrayableOperator()>
		 * @param ${finderCol.names} the ${finderCol.humanNames}
			<#else>
		 * @param ${finderCol.name} the ${finderCol.humanName}
			</#if>
		</#list>
		 * @return the number of matching ${entity.humanNames} that the user has permission to view
		 */
		@Override
		public int filterCountBy${finder.name}(

		<#list finderColsList as finderCol>
			<#if finderCol.hasArrayableOperator()>
				${finderCol.type}[] ${finderCol.names}
			<#else>
				${finderCol.type} ${finderCol.name}
			</#if>

			<#if finderCol_has_next>
				,
			</#if>
		</#list>

		) {
			<#if finder.hasColumn("groupId")>
				if (!InlineSQLHelperUtil.isEnabled(
					<#if finder.getColumn("groupId").hasArrayableOperator()>
						groupIds
					<#else>
						groupId
					</#if>
				)) {
			<#elseif finder.hasColumn("companyId")>
				if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			<#else>
				if (!InlineSQLHelperUtil.isEnabled()) {
			</#if>

				return countBy${finder.name}(

				<#list finderColsList as finderCol>
					<#if finderCol.hasArrayableOperator()>
						${finderCol.names}
					<#else>
						${finderCol.name}
					</#if>

					<#if finderCol_has_next>
						,
					</#if>
				</#list>

				);
			}

			<#list finderColsList as finderCol>
				<#if finderCol.hasArrayableOperator()>
					if (${finderCol.names} == null) {
						${finderCol.names} = new ${finderCol.type}[0];
					}
					else if (${finderCol.names}.length > 1) {
						${finderCol.names} =
							<#if finderCol.type == "String">
								ArrayUtil.distinct(${finderCol.names}, NULL_SAFE_STRING_COMPARATOR);
							<#else>
								ArrayUtil.unique(${finderCol.names});
							</#if>

						<#if finderCol.type == "String">
							Arrays.sort(${finderCol.names}, NULL_SAFE_STRING_COMPARATOR);
						<#else>
							Arrays.sort(${finderCol.names});
						</#if>
					}
				</#if>
			</#list>

			<#if entity.isPermissionedModel()>
				<#include "persistence_impl_count_by_arrayable_query.ftl">

				String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(), ${entity.name}.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, _FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN

				<#if finder.hasColumn("groupId")>,
					<#if finder.getColumn("groupId").hasArrayableOperator()>
						groupIds
					<#else>
						groupId
					</#if>
				</#if>);

				Session session = null;

				try {
					session = openSession();

					Query q = session.createQuery(sql);

					<#if bindParameter(finderColsList)>
						QueryPos qPos = QueryPos.getInstance(q);
					</#if>

					<@finderQPos
						_arrayable=true
					/>

					Long count = (Long)q.uniqueResult();

					return count.intValue();
				}
				catch (Exception e) {
					throw processException(e);
				}
				finally {
					closeSession(session);
				}
			<#else>
				StringBundler query = new StringBundler();

				query.append(_FILTER_SQL_COUNT_${entity.alias?upper_case}_WHERE);

				<#assign sqlQuery = true>

				<#include "persistence_impl_finder_arrayable_cols.ftl">

				<#assign sqlQuery = false>

				String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(), ${entity.name}.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN

				<#if finder.hasColumn("groupId")>,
					<#if finder.getColumn("groupId").hasArrayableOperator()>
						groupIds
					<#else>
						groupId
					</#if>
				</#if>);

				Session session = null;

				try {
					session = openSession();

					SQLQuery q = session.createSynchronizedSQLQuery(sql);

					q.addScalar(COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

					<#if bindParameter(finderColsList)>
						QueryPos qPos = QueryPos.getInstance(q);
					</#if>

					<@finderQPos
						_arrayable=true
					/>

					Long count = (Long)q.uniqueResult();

					return count.intValue();
				}
				catch (Exception e) {
					throw processException(e);
				}
				finally {
					closeSession(session);
				}
			</#if>
		}
	</#if>
</#if>