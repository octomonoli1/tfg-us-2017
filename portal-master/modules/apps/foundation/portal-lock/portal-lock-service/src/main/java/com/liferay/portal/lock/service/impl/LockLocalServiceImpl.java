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

package com.liferay.portal.lock.service.impl;

import com.liferay.portal.kernel.dao.jdbc.aop.MasterDataSource;
import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.lock.LockListener;
import com.liferay.portal.kernel.lock.LockListenerRegistryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.lock.exception.DuplicateLockException;
import com.liferay.portal.lock.exception.ExpiredLockException;
import com.liferay.portal.lock.exception.NoSuchLockException;
import com.liferay.portal.lock.model.Lock;
import com.liferay.portal.lock.service.base.LockLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.LockAcquisitionException;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class LockLocalServiceImpl extends LockLocalServiceBaseImpl {

	@Override
	public void clear() {
		lockPersistence.removeByLtExpirationDate(new Date());
	}

	@Override
	public Lock getLock(String className, long key) throws PortalException {
		return getLock(className, String.valueOf(key));
	}

	@Override
	public Lock getLock(String className, String key) throws PortalException {
		Lock lock = lockPersistence.findByC_K(className, key);

		if (lock.isExpired()) {
			expireLock(lock);

			throw new ExpiredLockException();
		}

		return lock;
	}

	@Override
	public Lock getLockByUuidAndCompanyId(String uuid, long companyId)
		throws PortalException {

		List<Lock> locks = lockPersistence.findByUuid_C(uuid, companyId);

		if (locks.isEmpty()) {
			StringBundler sb = new StringBundler(5);

			sb.append("{uuid=");
			sb.append(uuid);
			sb.append(", companyId=");
			sb.append(companyId);
			sb.append("}");

			throw new NoSuchLockException(sb.toString());
		}

		return locks.get(0);
	}

	@Override
	public boolean hasLock(long userId, String className, long key) {
		return hasLock(userId, className, String.valueOf(key));
	}

	@Override
	public boolean hasLock(long userId, String className, String key) {
		Lock lock = fetchLock(className, key);

		if ((lock != null) && (lock.getUserId() == userId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isLocked(String className, long key) {
		return isLocked(className, String.valueOf(key));
	}

	@Override
	public boolean isLocked(String className, String key) {
		Lock lock = fetchLock(className, key);

		if (lock == null) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public Lock lock(
			long userId, String className, long key, String owner,
			boolean inheritable, long expirationTime)
		throws PortalException {

		return lock(
			userId, className, String.valueOf(key), owner, inheritable,
			expirationTime);
	}

	@Override
	public Lock lock(
			long userId, String className, String key, String owner,
			boolean inheritable, long expirationTime)
		throws PortalException {

		Date now = new Date();

		Lock lock = lockPersistence.fetchByC_K(className, key);

		if (lock != null) {
			if (lock.isExpired()) {
				expireLock(lock);

				lock = null;
			}
			else if (lock.getUserId() != userId) {
				throw new DuplicateLockException(lock);
			}
		}

		if (lock == null) {
			User user = userPersistence.findByPrimaryKey(userId);

			long lockId = counterLocalService.increment();

			lock = lockPersistence.create(lockId);

			lock.setCompanyId(user.getCompanyId());
			lock.setUserId(user.getUserId());
			lock.setUserName(user.getFullName());
			lock.setClassName(className);
			lock.setKey(key);
			lock.setOwner(owner);
			lock.setInheritable(inheritable);
		}

		lock.setCreateDate(now);

		if (expirationTime == 0) {
			lock.setExpirationDate(null);
		}
		else {
			lock.setExpirationDate(new Date(now.getTime() + expirationTime));
		}

		lockPersistence.update(lock);

		return lock;
	}

	@MasterDataSource
	@Override
	public Lock lock(String className, String key, String owner) {
		return lock(className, key, null, owner);
	}

	@MasterDataSource
	@Override
	public Lock lock(
		final String className, final String key, final String expectedOwner,
		final String updatedOwner) {

		while (true) {
			try {
				return TransactionInvokerUtil.invoke(
					_transactionConfig,
					new Callable<Lock>() {

						@Override
						public Lock call() {
							Lock lock = lockPersistence.fetchByC_K(
								className, key, false);

							if (lock == null) {
								long lockId = counterLocalService.increment();

								lock = lockPersistence.create(lockId);

								lock.setCreateDate(new Date());
								lock.setClassName(className);
								lock.setKey(key);
								lock.setOwner(updatedOwner);

								lockPersistence.update(lock);

								lock.setNew(true);
							}
							else if (Objects.equals(
										lock.getOwner(), expectedOwner)) {

								lock.setCreateDate(new Date());
								lock.setClassName(className);
								lock.setKey(key);
								lock.setOwner(updatedOwner);

								lockPersistence.update(lock);

								lock.setNew(true);
							}

							return lock;
						}

					});
			}
			catch (Throwable t) {
				if (t instanceof ORMException) {
					Throwable cause = t.getCause();

					if ((cause instanceof ConstraintViolationException) ||
						(cause instanceof LockAcquisitionException)) {

						continue;
					}
				}

				ReflectionUtil.throwException(t);
			}
		}
	}

	@Override
	public Lock refresh(String uuid, long companyId, long expirationTime)
		throws PortalException {

		Date now = new Date();

		List<Lock> locks = lockPersistence.findByUuid_C(uuid, companyId);

		if (locks.isEmpty()) {
			StringBundler sb = new StringBundler(5);

			sb.append("{uuid=");
			sb.append(uuid);
			sb.append(", companyId=");
			sb.append(companyId);
			sb.append("}");

			throw new NoSuchLockException(sb.toString());
		}

		Lock lock = locks.get(0);

		LockListener lockListener = LockListenerRegistryUtil.getLockListener(
			lock.getClassName());

		String key = lock.getKey();

		if (lockListener != null) {
			lockListener.onBeforeRefresh(key);
		}

		try {
			lock.setCreateDate(now);

			if (expirationTime == 0) {
				lock.setExpirationDate(null);
			}
			else {
				lock.setExpirationDate(
					new Date(now.getTime() + expirationTime));
			}

			lockPersistence.update(lock);

			return lock;
		}
		finally {
			if (lockListener != null) {
				lockListener.onAfterRefresh(key);
			}
		}
	}

	@Override
	public void unlock(String className, long key) {
		unlock(className, String.valueOf(key));
	}

	@Override
	public void unlock(String className, String key) {
		try {
			lockPersistence.removeByC_K(className, key);
		}
		catch (NoSuchLockException nsle) {
		}
	}

	@MasterDataSource
	@Override
	public void unlock(
		final String className, final String key, final String owner) {

		while (true) {
			try {
				TransactionInvokerUtil.invoke(
					_transactionConfig,
					new Callable<Void>() {

						@Override
						public Void call() {
							Lock lock = lockPersistence.fetchByC_K(
								className, key, false);

							if (lock == null) {
								return null;
							}

							if (Objects.equals(lock.getOwner(), owner)) {
								lockPersistence.remove(lock);
								lockPersistence.flush();
							}

							return null;
						}

					});

				return;
			}
			catch (Throwable t) {
				if (t instanceof ORMException) {
					Throwable cause = t.getCause();

					if ((cause instanceof ConstraintViolationException) ||
						(cause instanceof LockAcquisitionException)) {

						continue;
					}
				}

				ReflectionUtil.throwException(t);
			}
		}
	}

	protected void expireLock(Lock lock) {
		LockListener lockListener = LockListenerRegistryUtil.getLockListener(
			lock.getClassName());

		String key = lock.getKey();

		if (lockListener != null) {
			lockListener.onBeforeExpire(key);
		}

		try {
			lockPersistence.remove(lock);
		}
		finally {
			if (lockListener != null) {
				lockListener.onAfterExpire(key);
			}
		}
	}

	protected Lock fetchLock(String className, String key) {
		Lock lock = lockPersistence.fetchByC_K(className, key);

		if (lock != null) {
			if (lock.isExpired()) {
				expireLock(lock);

				lock = null;
			}
		}

		return lock;
	}

	private final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRES_NEW, new Class<?>[] {Exception.class});

}