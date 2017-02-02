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

import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionStatus;

/**
 * @author Shuyang Zhou
 */
public class TransactionStatusAdapter
	extends DefaultTransactionStatus
	implements com.liferay.portal.kernel.transaction.TransactionStatus {

	public TransactionStatusAdapter(TransactionStatus transactionStatus) {
		super(null, false, false, false, false, null);

		_transactionStatus = transactionStatus;
	}

	@Override
	public Object createSavepoint() throws TransactionException {
		return _transactionStatus.createSavepoint();
	}

	@Override
	public void flush() {
		_transactionStatus.flush();
	}

	public TransactionStatus getTransactionStatus() {
		return _transactionStatus;
	}

	@Override
	public boolean hasSavepoint() {
		return _transactionStatus.hasSavepoint();
	}

	@Override
	public boolean isCompleted() {
		return _transactionStatus.isCompleted();
	}

	@Override
	public boolean isNewTransaction() {
		return _transactionStatus.isNewTransaction();
	}

	@Override
	public boolean isRollbackOnly() {
		return _transactionStatus.isRollbackOnly();
	}

	@Override
	public void releaseSavepoint(Object savepoint) throws TransactionException {
		_transactionStatus.releaseSavepoint(savepoint);
	}

	@Override
	public void rollbackToSavepoint(Object savepoint)
		throws TransactionException {

		_transactionStatus.rollbackToSavepoint(savepoint);
	}

	@Override
	public void setRollbackOnly() {
		_transactionStatus.setRollbackOnly();
	}

	private final TransactionStatus _transactionStatus;

}