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

package com.liferay.portal.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.NoSuchClusterGroupException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ClusterGroup;
import com.liferay.portal.kernel.service.persistence.ClusterGroupPersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.ClusterGroupImpl;
import com.liferay.portal.model.impl.ClusterGroupModelImpl;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the cluster group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ClusterGroupPersistence
 * @see com.liferay.portal.kernel.service.persistence.ClusterGroupUtil
 * @generated
 */
@ProviderType
public class ClusterGroupPersistenceImpl extends BasePersistenceImpl<ClusterGroup>
	implements ClusterGroupPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ClusterGroupUtil} to access the cluster group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ClusterGroupImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ClusterGroupModelImpl.ENTITY_CACHE_ENABLED,
			ClusterGroupModelImpl.FINDER_CACHE_ENABLED, ClusterGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ClusterGroupModelImpl.ENTITY_CACHE_ENABLED,
			ClusterGroupModelImpl.FINDER_CACHE_ENABLED, ClusterGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ClusterGroupModelImpl.ENTITY_CACHE_ENABLED,
			ClusterGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ClusterGroupPersistenceImpl() {
		setModelClass(ClusterGroup.class);
	}

	/**
	 * Caches the cluster group in the entity cache if it is enabled.
	 *
	 * @param clusterGroup the cluster group
	 */
	@Override
	public void cacheResult(ClusterGroup clusterGroup) {
		entityCache.putResult(ClusterGroupModelImpl.ENTITY_CACHE_ENABLED,
			ClusterGroupImpl.class, clusterGroup.getPrimaryKey(), clusterGroup);

		clusterGroup.resetOriginalValues();
	}

	/**
	 * Caches the cluster groups in the entity cache if it is enabled.
	 *
	 * @param clusterGroups the cluster groups
	 */
	@Override
	public void cacheResult(List<ClusterGroup> clusterGroups) {
		for (ClusterGroup clusterGroup : clusterGroups) {
			if (entityCache.getResult(
						ClusterGroupModelImpl.ENTITY_CACHE_ENABLED,
						ClusterGroupImpl.class, clusterGroup.getPrimaryKey()) == null) {
				cacheResult(clusterGroup);
			}
			else {
				clusterGroup.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cluster groups.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ClusterGroupImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cluster group.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ClusterGroup clusterGroup) {
		entityCache.removeResult(ClusterGroupModelImpl.ENTITY_CACHE_ENABLED,
			ClusterGroupImpl.class, clusterGroup.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ClusterGroup> clusterGroups) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ClusterGroup clusterGroup : clusterGroups) {
			entityCache.removeResult(ClusterGroupModelImpl.ENTITY_CACHE_ENABLED,
				ClusterGroupImpl.class, clusterGroup.getPrimaryKey());
		}
	}

	/**
	 * Creates a new cluster group with the primary key. Does not add the cluster group to the database.
	 *
	 * @param clusterGroupId the primary key for the new cluster group
	 * @return the new cluster group
	 */
	@Override
	public ClusterGroup create(long clusterGroupId) {
		ClusterGroup clusterGroup = new ClusterGroupImpl();

		clusterGroup.setNew(true);
		clusterGroup.setPrimaryKey(clusterGroupId);

		return clusterGroup;
	}

	/**
	 * Removes the cluster group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param clusterGroupId the primary key of the cluster group
	 * @return the cluster group that was removed
	 * @throws NoSuchClusterGroupException if a cluster group with the primary key could not be found
	 */
	@Override
	public ClusterGroup remove(long clusterGroupId)
		throws NoSuchClusterGroupException {
		return remove((Serializable)clusterGroupId);
	}

	/**
	 * Removes the cluster group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cluster group
	 * @return the cluster group that was removed
	 * @throws NoSuchClusterGroupException if a cluster group with the primary key could not be found
	 */
	@Override
	public ClusterGroup remove(Serializable primaryKey)
		throws NoSuchClusterGroupException {
		Session session = null;

		try {
			session = openSession();

			ClusterGroup clusterGroup = (ClusterGroup)session.get(ClusterGroupImpl.class,
					primaryKey);

			if (clusterGroup == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchClusterGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(clusterGroup);
		}
		catch (NoSuchClusterGroupException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected ClusterGroup removeImpl(ClusterGroup clusterGroup) {
		clusterGroup = toUnwrappedModel(clusterGroup);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(clusterGroup)) {
				clusterGroup = (ClusterGroup)session.get(ClusterGroupImpl.class,
						clusterGroup.getPrimaryKeyObj());
			}

			if (clusterGroup != null) {
				session.delete(clusterGroup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (clusterGroup != null) {
			clearCache(clusterGroup);
		}

		return clusterGroup;
	}

	@Override
	public ClusterGroup updateImpl(ClusterGroup clusterGroup) {
		clusterGroup = toUnwrappedModel(clusterGroup);

		boolean isNew = clusterGroup.isNew();

		Session session = null;

		try {
			session = openSession();

			if (clusterGroup.isNew()) {
				session.save(clusterGroup);

				clusterGroup.setNew(false);
			}
			else {
				clusterGroup = (ClusterGroup)session.merge(clusterGroup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		entityCache.putResult(ClusterGroupModelImpl.ENTITY_CACHE_ENABLED,
			ClusterGroupImpl.class, clusterGroup.getPrimaryKey(), clusterGroup,
			false);

		clusterGroup.resetOriginalValues();

		return clusterGroup;
	}

	protected ClusterGroup toUnwrappedModel(ClusterGroup clusterGroup) {
		if (clusterGroup instanceof ClusterGroupImpl) {
			return clusterGroup;
		}

		ClusterGroupImpl clusterGroupImpl = new ClusterGroupImpl();

		clusterGroupImpl.setNew(clusterGroup.isNew());
		clusterGroupImpl.setPrimaryKey(clusterGroup.getPrimaryKey());

		clusterGroupImpl.setMvccVersion(clusterGroup.getMvccVersion());
		clusterGroupImpl.setClusterGroupId(clusterGroup.getClusterGroupId());
		clusterGroupImpl.setName(clusterGroup.getName());
		clusterGroupImpl.setClusterNodeIds(clusterGroup.getClusterNodeIds());
		clusterGroupImpl.setWholeCluster(clusterGroup.isWholeCluster());

		return clusterGroupImpl;
	}

	/**
	 * Returns the cluster group with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cluster group
	 * @return the cluster group
	 * @throws NoSuchClusterGroupException if a cluster group with the primary key could not be found
	 */
	@Override
	public ClusterGroup findByPrimaryKey(Serializable primaryKey)
		throws NoSuchClusterGroupException {
		ClusterGroup clusterGroup = fetchByPrimaryKey(primaryKey);

		if (clusterGroup == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchClusterGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return clusterGroup;
	}

	/**
	 * Returns the cluster group with the primary key or throws a {@link NoSuchClusterGroupException} if it could not be found.
	 *
	 * @param clusterGroupId the primary key of the cluster group
	 * @return the cluster group
	 * @throws NoSuchClusterGroupException if a cluster group with the primary key could not be found
	 */
	@Override
	public ClusterGroup findByPrimaryKey(long clusterGroupId)
		throws NoSuchClusterGroupException {
		return findByPrimaryKey((Serializable)clusterGroupId);
	}

	/**
	 * Returns the cluster group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cluster group
	 * @return the cluster group, or <code>null</code> if a cluster group with the primary key could not be found
	 */
	@Override
	public ClusterGroup fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ClusterGroupModelImpl.ENTITY_CACHE_ENABLED,
				ClusterGroupImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ClusterGroup clusterGroup = (ClusterGroup)serializable;

		if (clusterGroup == null) {
			Session session = null;

			try {
				session = openSession();

				clusterGroup = (ClusterGroup)session.get(ClusterGroupImpl.class,
						primaryKey);

				if (clusterGroup != null) {
					cacheResult(clusterGroup);
				}
				else {
					entityCache.putResult(ClusterGroupModelImpl.ENTITY_CACHE_ENABLED,
						ClusterGroupImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ClusterGroupModelImpl.ENTITY_CACHE_ENABLED,
					ClusterGroupImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return clusterGroup;
	}

	/**
	 * Returns the cluster group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param clusterGroupId the primary key of the cluster group
	 * @return the cluster group, or <code>null</code> if a cluster group with the primary key could not be found
	 */
	@Override
	public ClusterGroup fetchByPrimaryKey(long clusterGroupId) {
		return fetchByPrimaryKey((Serializable)clusterGroupId);
	}

	@Override
	public Map<Serializable, ClusterGroup> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ClusterGroup> map = new HashMap<Serializable, ClusterGroup>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ClusterGroup clusterGroup = fetchByPrimaryKey(primaryKey);

			if (clusterGroup != null) {
				map.put(primaryKey, clusterGroup);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ClusterGroupModelImpl.ENTITY_CACHE_ENABLED,
					ClusterGroupImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ClusterGroup)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CLUSTERGROUP_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (ClusterGroup clusterGroup : (List<ClusterGroup>)q.list()) {
				map.put(clusterGroup.getPrimaryKeyObj(), clusterGroup);

				cacheResult(clusterGroup);

				uncachedPrimaryKeys.remove(clusterGroup.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ClusterGroupModelImpl.ENTITY_CACHE_ENABLED,
					ClusterGroupImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the cluster groups.
	 *
	 * @return the cluster groups
	 */
	@Override
	public List<ClusterGroup> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cluster groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ClusterGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cluster groups
	 * @param end the upper bound of the range of cluster groups (not inclusive)
	 * @return the range of cluster groups
	 */
	@Override
	public List<ClusterGroup> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cluster groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ClusterGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cluster groups
	 * @param end the upper bound of the range of cluster groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cluster groups
	 */
	@Override
	public List<ClusterGroup> findAll(int start, int end,
		OrderByComparator<ClusterGroup> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cluster groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ClusterGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cluster groups
	 * @param end the upper bound of the range of cluster groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cluster groups
	 */
	@Override
	public List<ClusterGroup> findAll(int start, int end,
		OrderByComparator<ClusterGroup> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<ClusterGroup> list = null;

		if (retrieveFromCache) {
			list = (List<ClusterGroup>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CLUSTERGROUP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CLUSTERGROUP;

				if (pagination) {
					sql = sql.concat(ClusterGroupModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ClusterGroup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ClusterGroup>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the cluster groups from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ClusterGroup clusterGroup : findAll()) {
			remove(clusterGroup);
		}
	}

	/**
	 * Returns the number of cluster groups.
	 *
	 * @return the number of cluster groups
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CLUSTERGROUP);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ClusterGroupModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cluster group persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ClusterGroupImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_CLUSTERGROUP = "SELECT clusterGroup FROM ClusterGroup clusterGroup";
	private static final String _SQL_SELECT_CLUSTERGROUP_WHERE_PKS_IN = "SELECT clusterGroup FROM ClusterGroup clusterGroup WHERE clusterGroupId IN (";
	private static final String _SQL_COUNT_CLUSTERGROUP = "SELECT COUNT(clusterGroup) FROM ClusterGroup clusterGroup";
	private static final String _ORDER_BY_ENTITY_ALIAS = "clusterGroup.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ClusterGroup exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ClusterGroupPersistenceImpl.class);
}