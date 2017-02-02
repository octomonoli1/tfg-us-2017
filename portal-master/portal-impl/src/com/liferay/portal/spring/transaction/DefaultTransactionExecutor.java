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

package com.liferay.portal.spring.transaction;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.transaction.TransactionLifecycleManager;

import org.aopalliance.intercept.MethodInvocation;

import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author Michael C. Han
 * @author Shuyang Zhou
 */
public class DefaultTransactionExecutor
	implements TransactionExecutor, TransactionHandler {

	@Override
	public void commit(
		PlatformTransactionManager platformTransactionManager,
		TransactionAttributeAdapter transactionAttributeAdapter,
		TransactionStatusAdapter transactionStatusAdapter) {

		Throwable throwable = null;

		try {
			platformTransactionManager.commit(
				transactionStatusAdapter.getTransactionStatus());
		}
		catch (RuntimeException re) {
			_log.error(
				"Application exception overridden by commit exception", re);

			throwable = re;

			throw re;
		}
		catch (Error e) {
			_log.error("Application exception overridden by commit error", e);

			throwable = e;

			throw e;
		}
		finally {
			if (throwable != null) {
				TransactionLifecycleManager.fireTransactionRollbackedEvent(
					transactionAttributeAdapter, transactionStatusAdapter,
					throwable);
			}
			else {
				TransactionLifecycleManager.fireTransactionCommittedEvent(
					transactionAttributeAdapter, transactionStatusAdapter);
			}
		}
	}

	@Override
	public Object execute(
			PlatformTransactionManager platformTransactionManager,
			TransactionAttributeAdapter transactionAttributeAdapter,
			MethodInvocation methodInvocation)
		throws Throwable {

		TransactionStatusAdapter transactionStatusAdapter = start(
			platformTransactionManager, transactionAttributeAdapter);

		Object returnValue = null;

		try {
			returnValue = methodInvocation.proceed();
		}
		catch (Throwable throwable) {
			rollback(
				platformTransactionManager, throwable,
				transactionAttributeAdapter, transactionStatusAdapter);
		}

		commit(
			platformTransactionManager, transactionAttributeAdapter,
			transactionStatusAdapter);

		return returnValue;
	}

	@Override
	public void rollback(
			PlatformTransactionManager platformTransactionManager,
			Throwable throwable,
			TransactionAttributeAdapter transactionAttributeAdapter,
			TransactionStatusAdapter transactionStatusAdapter)
		throws Throwable {

		if (transactionAttributeAdapter.rollbackOn(throwable)) {
			try {
				platformTransactionManager.rollback(
					transactionStatusAdapter.getTransactionStatus());
			}
			catch (RuntimeException re) {
				re.addSuppressed(throwable);

				_log.error(
					"Application exception overridden by rollback exception",
					re);

				throw re;
			}
			catch (Error e) {
				e.addSuppressed(throwable);

				_log.error(
					"Application exception overridden by rollback error", e);

				throw e;
			}
			finally {
				TransactionLifecycleManager.fireTransactionRollbackedEvent(
					transactionAttributeAdapter, transactionStatusAdapter,
					throwable);
			}
		}
		else {
			commit(
				platformTransactionManager, transactionAttributeAdapter,
				transactionStatusAdapter);
		}

		throw throwable;
	}

	@Override
	public TransactionStatusAdapter start(
		PlatformTransactionManager platformTransactionManager,
		TransactionAttributeAdapter transactionAttributeAdapter) {

		TransactionStatusAdapter transactionStatusAdapter =
			new TransactionStatusAdapter(
				platformTransactionManager.getTransaction(
					transactionAttributeAdapter));

		TransactionLifecycleManager.fireTransactionCreatedEvent(
			transactionAttributeAdapter, transactionStatusAdapter);

		return transactionStatusAdapter;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DefaultTransactionExecutor.class);

}