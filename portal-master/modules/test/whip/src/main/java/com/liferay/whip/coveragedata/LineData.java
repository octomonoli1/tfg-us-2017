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

package com.liferay.whip.coveragedata;

import java.io.Serializable;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Shuyang Zhou
 */
public class LineData implements CoverageData<LineData>, Serializable {

	public LineData(String className, int lineNumber) {
		_className = className;
		_lineNumber = lineNumber;
	}

	public JumpData addJump(JumpData jumpData) {
		JumpData previousJumpData = _jumpDatas.putIfAbsent(
			jumpData.getJumpNumber(), jumpData);

		if (previousJumpData != null) {
			return previousJumpData;
		}

		return jumpData;
	}

	public SwitchData addSwitch(SwitchData switchData) {
		SwitchData previousSwitchData = _switchDatas.putIfAbsent(
			switchData.getSwitchNumber(), switchData);

		if (previousSwitchData != null) {
			return previousSwitchData;
		}

		return switchData;
	}

	@Override
	public double getBranchCoverageRate() {
		int numberOfValidBranches = getNumberOfValidBranches();

		if (numberOfValidBranches == 0) {
			return 1;
		}

		return (double)getNumberOfCoveredBranches() / numberOfValidBranches;
	}

	@Override
	public double getLineCoverageRate() {
		return getNumberOfCoveredLines();
	}

	public int getLineNumber() {
		return _lineNumber;
	}

	@Override
	public int getNumberOfCoveredBranches() {
		int numberOfCoveredBranches = 0;

		for (JumpData jumpData : _jumpDatas.values()) {
			numberOfCoveredBranches += jumpData.getNumberOfCoveredBranches();
		}

		for (SwitchData switchData : _switchDatas.values()) {
			numberOfCoveredBranches += switchData.getNumberOfCoveredBranches();
		}

		return numberOfCoveredBranches;
	}

	@Override
	public int getNumberOfCoveredLines() {
		if (_hitCounter.get() > 0) {
			return 1;
		}

		return 0;
	}

	@Override
	public int getNumberOfValidBranches() {
		int numberOfValidBranches = 0;

		for (JumpData jumpData : _jumpDatas.values()) {
			numberOfValidBranches += jumpData.getNumberOfValidBranches();
		}

		for (SwitchData switchData : _switchDatas.values()) {
			numberOfValidBranches += switchData.getNumberOfValidBranches();
		}

		return numberOfValidBranches;
	}

	@Override
	public int getNumberOfValidLines() {
		return 1;
	}

	public boolean isCovered() {
		if ((_hitCounter.get() > 0) &&
			(getNumberOfCoveredBranches() == getNumberOfValidBranches())) {

			return true;
		}

		return false;
	}

	@Override
	public void merge(LineData lineData) {
		if (!_className.equals(lineData._className) ||
			(_lineNumber != lineData._lineNumber)) {

			throw new IllegalArgumentException(
				"Line data mismatch, left : " + toString() + ", right : " +
					lineData);
		}

		AtomicLong hitCounter = lineData._hitCounter;

		_hitCounter.addAndGet(hitCounter.get());

		ConcurrentMap<Integer, JumpData> otherJumpDatas = lineData._jumpDatas;

		for (JumpData jumpData : otherJumpDatas.values()) {
			JumpData previousJumpData = _jumpDatas.putIfAbsent(
				jumpData.getJumpNumber(), jumpData);

			if (previousJumpData != null) {
				previousJumpData.merge(jumpData);
			}
		}

		ConcurrentMap<Integer, SwitchData> otherSwitchDatas =
			lineData._switchDatas;

		for (SwitchData switchData : otherSwitchDatas.values()) {
			SwitchData previousSwitchData = _switchDatas.putIfAbsent(
				switchData.getSwitchNumber(), switchData);

			if (previousSwitchData != null) {
				previousSwitchData.merge(switchData);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("{className=");
		sb.append(_className);
		sb.append(", lineNumber=");
		sb.append(_lineNumber);
		sb.append("}");

		return sb.toString();
	}

	public void touch() {
		_hitCounter.incrementAndGet();
	}

	public void touchJump(int jumpNumber, boolean branch) {
		JumpData jumpData = _jumpDatas.get(jumpNumber);

		if (jumpData == null) {
			throw new IllegalStateException(
				"No instrument data for class " + _className + " line " +
					_lineNumber + " jump " + jumpNumber);
		}

		jumpData.touchBranch(branch);
	}

	public void touchSwitch(int switchNumber, int branch) {
		SwitchData switchData = _switchDatas.get(switchNumber);

		if (switchData == null) {
			throw new IllegalStateException(
				"No instrument data for class " + _className + " line " +
					_lineNumber + " switch " + switchNumber);
		}

		switchData.touchBranch(branch);
	}

	private static final long serialVersionUID = 1;

	private final String _className;
	private final AtomicLong _hitCounter = new AtomicLong();
	private final ConcurrentMap<Integer, JumpData> _jumpDatas =
		new ConcurrentHashMap<>();
	private final int _lineNumber;
	private final ConcurrentMap<Integer, SwitchData> _switchDatas =
		new ConcurrentHashMap<>();

}