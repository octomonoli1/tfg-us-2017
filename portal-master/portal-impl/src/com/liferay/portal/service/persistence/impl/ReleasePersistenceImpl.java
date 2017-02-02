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
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.NoSuchReleaseException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.ReleasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.impl.ReleaseImpl;
import com.liferay.portal.model.impl.ReleaseModelImpl;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the release service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReleasePersistence
 * @see com.liferay.portal.kernel.service.persistence.ReleaseUtil
 * @generated
 */
@ProviderType
public class ReleasePersistenceImpl extends BasePersistenceImpl<Release>
	implements ReleasePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ReleaseUtil} to access the release persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ReleaseImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseModelImpl.FINDER_CACHE_ENABLED, ReleaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseModelImpl.FINDER_CACHE_ENABLED, ReleaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_SERVLETCONTEXTNAME = new FinderPath(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseModelImpl.FINDER_CACHE_ENABLED, ReleaseImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByServletContextName",
			new String[] { String.class.getName() },
			ReleaseModelImpl.SERVLETCONTEXTNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SERVLETCONTEXTNAME = new FinderPath(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByServletContextName", new String[] { String.class.getName() });

	/**
	 * Returns the release where servletContextName = &#63; or throws a {@link NoSuchReleaseException} if it could not be found.
	 *
	 * @param servletContextName the servlet context name
	 * @return the matching release
	 * @throws NoSuchReleaseException if a matching release could not be found
	 */
	@Override
	public Release findByServletContextName(String servletContextName)
		throws NoSuchReleaseException {
		Release release = fetchByServletContextName(servletContextName);

		if (release == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("servletContextName=");
			msg.append(servletContextName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchReleaseException(msg.toString());
		}

		return release;
	}

	/**
	 * Returns the release where servletContextName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param servletContextName the servlet context name
	 * @return the matching release, or <code>null</code> if a matching release could not be found
	 */
	@Override
	public Release fetchByServletContextName(String servletContextName) {
		return fetchByServletContextName(servletContextName, true);
	}

	/**
	 * Returns the release where servletContextName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param servletContextName the servlet context name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching release, or <code>null</code> if a matching release could not be found
	 */
	@Override
	public Release fetchByServletContextName(String servletContextName,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { servletContextName };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_SERVLETCONTEXTNAME,
					finderArgs, this);
		}

		if (result instanceof Release) {
			Release release = (Release)result;

			if (!Objects.equals(servletContextName,
						release.getServletContextName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_RELEASE_WHERE);

			boolean bindServletContextName = false;

			if (servletContextName == null) {
				query.append(_FINDER_COLUMN_SERVLETCONTEXTNAME_SERVLETCONTEXTNAME_1);
			}
			else if (servletContextName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SERVLETCONTEXTNAME_SERVLETCONTEXTNAME_3);
			}
			else {
				bindServletContextName = true;

				query.append(_FINDER_COLUMN_SERVLETCONTEXTNAME_SERVLETCONTEXTNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindServletContextName) {
					qPos.add(StringUtil.toLowerCase(servletContextName));
				}

				List<Release> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_SERVLETCONTEXTNAME,
						finderArgs, list);
				}
				else {
					Release release = list.get(0);

					result = release;

					cacheResult(release);

					if ((release.getServletContextName() == null) ||
							!release.getServletContextName()
										.equals(servletContextName)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_SERVLETCONTEXTNAME,
							finderArgs, release);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_SERVLETCONTEXTNAME,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Release)result;
		}
	}

	/**
	 * Removes the release where servletContextName = &#63; from the database.
	 *
	 * @param servletContextName the servlet context name
	 * @return the release that was removed
	 */
	@Override
	public Release removeByServletContextName(String servletContextName)
		throws NoSuchReleaseException {
		Release release = findByServletContextName(servletContextName);

		return remove(release);
	}

	/**
	 * Returns the number of releases where servletContextName = &#63;.
	 *
	 * @param servletContextName the servlet context name
	 * @return the number of matching releases
	 */
	@Override
	public int countByServletContextName(String servletContextName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SERVLETCONTEXTNAME;

		Object[] finderArgs = new Object[] { servletContextName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RELEASE_WHERE);

			boolean bindServletContextName = false;

			if (servletContextName == null) {
				query.append(_FINDER_COLUMN_SERVLETCONTEXTNAME_SERVLETCONTEXTNAME_1);
			}
			else if (servletContextName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SERVLETCONTEXTNAME_SERVLETCONTEXTNAME_3);
			}
			else {
				bindServletContextName = true;

				query.append(_FINDER_COLUMN_SERVLETCONTEXTNAME_SERVLETCONTEXTNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindServletContextName) {
					qPos.add(StringUtil.toLowerCase(servletContextName));
				}

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

	private static final String _FINDER_COLUMN_SERVLETCONTEXTNAME_SERVLETCONTEXTNAME_1 =
		"release.servletContextName IS NULL";
	private static final String _FINDER_COLUMN_SERVLETCONTEXTNAME_SERVLETCONTEXTNAME_2 =
		"lower(release.servletContextName) = ?";
	private static final String _FINDER_COLUMN_SERVLETCONTEXTNAME_SERVLETCONTEXTNAME_3 =
		"(release.servletContextName IS NULL OR release.servletContextName = '')";

	public ReleasePersistenceImpl() {
		setModelClass(Release.class);
	}

	/**
	 * Caches the release in the entity cache if it is enabled.
	 *
	 * @param release the release
	 */
	@Override
	public void cacheResult(Release release) {
		entityCache.putResult(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseImpl.class, release.getPrimaryKey(), release);

		finderCache.putResult(FINDER_PATH_FETCH_BY_SERVLETCONTEXTNAME,
			new Object[] { release.getServletContextName() }, release);

		release.resetOriginalValues();
	}

	/**
	 * Caches the releases in the entity cache if it is enabled.
	 *
	 * @param releases the releases
	 */
	@Override
	public void cacheResult(List<Release> releases) {
		for (Release release : releases) {
			if (entityCache.getResult(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
						ReleaseImpl.class, release.getPrimaryKey()) == null) {
				cacheResult(release);
			}
			else {
				release.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all releases.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ReleaseImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the release.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Release release) {
		entityCache.removeResult(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseImpl.class, release.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ReleaseModelImpl)release);
	}

	@Override
	public void clearCache(List<Release> releases) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Release release : releases) {
			entityCache.removeResult(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
				ReleaseImpl.class, release.getPrimaryKey());

			clearUniqueFindersCache((ReleaseModelImpl)release);
		}
	}

	protected void cacheUniqueFindersCache(ReleaseModelImpl releaseModelImpl,
		boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					releaseModelImpl.getServletContextName()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_SERVLETCONTEXTNAME,
				args, Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_SERVLETCONTEXTNAME,
				args, releaseModelImpl);
		}
		else {
			if ((releaseModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_SERVLETCONTEXTNAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						releaseModelImpl.getServletContextName()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_SERVLETCONTEXTNAME,
					args, Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_SERVLETCONTEXTNAME,
					args, releaseModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(ReleaseModelImpl releaseModelImpl) {
		Object[] args = new Object[] { releaseModelImpl.getServletContextName() };

		finderCache.removeResult(FINDER_PATH_COUNT_BY_SERVLETCONTEXTNAME, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_SERVLETCONTEXTNAME, args);

		if ((releaseModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SERVLETCONTEXTNAME.getColumnBitmask()) != 0) {
			args = new Object[] { releaseModelImpl.getOriginalServletContextName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SERVLETCONTEXTNAME,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SERVLETCONTEXTNAME,
				args);
		}
	}

	/**
	 * Creates a new release with the primary key. Does not add the release to the database.
	 *
	 * @param releaseId the primary key for the new release
	 * @return the new release
	 */
	@Override
	public Release create(long releaseId) {
		Release release = new ReleaseImpl();

		release.setNew(true);
		release.setPrimaryKey(releaseId);

		return release;
	}

	/**
	 * Removes the release with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param releaseId the primary key of the release
	 * @return the release that was removed
	 * @throws NoSuchReleaseException if a release with the primary key could not be found
	 */
	@Override
	public Release remove(long releaseId) throws NoSuchReleaseException {
		return remove((Serializable)releaseId);
	}

	/**
	 * Removes the release with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the release
	 * @return the release that was removed
	 * @throws NoSuchReleaseException if a release with the primary key could not be found
	 */
	@Override
	public Release remove(Serializable primaryKey)
		throws NoSuchReleaseException {
		Session session = null;

		try {
			session = openSession();

			Release release = (Release)session.get(ReleaseImpl.class, primaryKey);

			if (release == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchReleaseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(release);
		}
		catch (NoSuchReleaseException nsee) {
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
	protected Release removeImpl(Release release) {
		release = toUnwrappedModel(release);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(release)) {
				release = (Release)session.get(ReleaseImpl.class,
						release.getPrimaryKeyObj());
			}

			if (release != null) {
				session.delete(release);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (release != null) {
			clearCache(release);
		}

		return release;
	}

	@Override
	public Release updateImpl(Release release) {
		release = toUnwrappedModel(release);

		boolean isNew = release.isNew();

		ReleaseModelImpl releaseModelImpl = (ReleaseModelImpl)release;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (release.getCreateDate() == null)) {
			if (serviceContext == null) {
				release.setCreateDate(now);
			}
			else {
				release.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!releaseModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				release.setModifiedDate(now);
			}
			else {
				release.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (release.isNew()) {
				session.save(release);

				release.setNew(false);
			}
			else {
				release = (Release)session.merge(release);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ReleaseModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		entityCache.putResult(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
			ReleaseImpl.class, release.getPrimaryKey(), release, false);

		clearUniqueFindersCache(releaseModelImpl);
		cacheUniqueFindersCache(releaseModelImpl, isNew);

		release.resetOriginalValues();

		return release;
	}

	protected Release toUnwrappedModel(Release release) {
		if (release instanceof ReleaseImpl) {
			return release;
		}

		ReleaseImpl releaseImpl = new ReleaseImpl();

		releaseImpl.setNew(release.isNew());
		releaseImpl.setPrimaryKey(release.getPrimaryKey());

		releaseImpl.setMvccVersion(release.getMvccVersion());
		releaseImpl.setReleaseId(release.getReleaseId());
		releaseImpl.setCreateDate(release.getCreateDate());
		releaseImpl.setModifiedDate(release.getModifiedDate());
		releaseImpl.setServletContextName(release.getServletContextName());
		releaseImpl.setSchemaVersion(release.getSchemaVersion());
		releaseImpl.setBuildNumber(release.getBuildNumber());
		releaseImpl.setBuildDate(release.getBuildDate());
		releaseImpl.setVerified(release.isVerified());
		releaseImpl.setState(release.getState());
		releaseImpl.setTestString(release.getTestString());

		return releaseImpl;
	}

	/**
	 * Returns the release with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the release
	 * @return the release
	 * @throws NoSuchReleaseException if a release with the primary key could not be found
	 */
	@Override
	public Release findByPrimaryKey(Serializable primaryKey)
		throws NoSuchReleaseException {
		Release release = fetchByPrimaryKey(primaryKey);

		if (release == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchReleaseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return release;
	}

	/**
	 * Returns the release with the primary key or throws a {@link NoSuchReleaseException} if it could not be found.
	 *
	 * @param releaseId the primary key of the release
	 * @return the release
	 * @throws NoSuchReleaseException if a release with the primary key could not be found
	 */
	@Override
	public Release findByPrimaryKey(long releaseId)
		throws NoSuchReleaseException {
		return findByPrimaryKey((Serializable)releaseId);
	}

	/**
	 * Returns the release with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the release
	 * @return the release, or <code>null</code> if a release with the primary key could not be found
	 */
	@Override
	public Release fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
				ReleaseImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Release release = (Release)serializable;

		if (release == null) {
			Session session = null;

			try {
				session = openSession();

				release = (Release)session.get(ReleaseImpl.class, primaryKey);

				if (release != null) {
					cacheResult(release);
				}
				else {
					entityCache.putResult(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
						ReleaseImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
					ReleaseImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return release;
	}

	/**
	 * Returns the release with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param releaseId the primary key of the release
	 * @return the release, or <code>null</code> if a release with the primary key could not be found
	 */
	@Override
	public Release fetchByPrimaryKey(long releaseId) {
		return fetchByPrimaryKey((Serializable)releaseId);
	}

	@Override
	public Map<Serializable, Release> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Release> map = new HashMap<Serializable, Release>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Release release = fetchByPrimaryKey(primaryKey);

			if (release != null) {
				map.put(primaryKey, release);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
					ReleaseImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Release)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_RELEASE_WHERE_PKS_IN);

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

			for (Release release : (List<Release>)q.list()) {
				map.put(release.getPrimaryKeyObj(), release);

				cacheResult(release);

				uncachedPrimaryKeys.remove(release.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ReleaseModelImpl.ENTITY_CACHE_ENABLED,
					ReleaseImpl.class, primaryKey, nullModel);
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
	 * Returns all the releases.
	 *
	 * @return the releases
	 */
	@Override
	public List<Release> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the releases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of releases
	 * @param end the upper bound of the range of releases (not inclusive)
	 * @return the range of releases
	 */
	@Override
	public List<Release> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the releases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of releases
	 * @param end the upper bound of the range of releases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of releases
	 */
	@Override
	public List<Release> findAll(int start, int end,
		OrderByComparator<Release> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the releases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of releases
	 * @param end the upper bound of the range of releases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of releases
	 */
	@Override
	public List<Release> findAll(int start, int end,
		OrderByComparator<Release> orderByComparator, boolean retrieveFromCache) {
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

		List<Release> list = null;

		if (retrieveFromCache) {
			list = (List<Release>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_RELEASE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RELEASE;

				if (pagination) {
					sql = sql.concat(ReleaseModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Release>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Release>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the releases from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Release release : findAll()) {
			remove(release);
		}
	}

	/**
	 * Returns the number of releases.
	 *
	 * @return the number of releases
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_RELEASE);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ReleaseModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the release persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ReleaseImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_RELEASE = "SELECT release FROM Release release";
	private static final String _SQL_SELECT_RELEASE_WHERE_PKS_IN = "SELECT release FROM Release release WHERE releaseId IN (";
	private static final String _SQL_SELECT_RELEASE_WHERE = "SELECT release FROM Release release WHERE ";
	private static final String _SQL_COUNT_RELEASE = "SELECT COUNT(release) FROM Release release";
	private static final String _SQL_COUNT_RELEASE_WHERE = "SELECT COUNT(release) FROM Release release WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "release.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Release exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Release exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ReleasePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"state"
			});
}