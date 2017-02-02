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

package com.liferay.mobile.device.rules.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.mobile.device.rules.model.MDRRuleGroupInstance;
import com.liferay.mobile.device.rules.service.base.MDRRuleGroupInstanceServiceBaseImpl;
import com.liferay.mobile.device.rules.service.permission.MDRPermission;
import com.liferay.mobile.device.rules.service.permission.MDRRuleGroupInstancePermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Edward C. Han
 */
@ProviderType
public class MDRRuleGroupInstanceServiceImpl
	extends MDRRuleGroupInstanceServiceBaseImpl {

	@Override
	public MDRRuleGroupInstance addRuleGroupInstance(
			long groupId, String className, long classPK, long ruleGroupId,
			int priority, ServiceContext serviceContext)
		throws PortalException {

		MDRPermission.check(
			getPermissionChecker(), groupId,
			ActionKeys.ADD_RULE_GROUP_INSTANCE);

		return mdrRuleGroupInstanceLocalService.addRuleGroupInstance(
			groupId, className, classPK, ruleGroupId, priority, serviceContext);
	}

	@Override
	public MDRRuleGroupInstance addRuleGroupInstance(
			long groupId, String className, long classPK, long ruleGroupId,
			ServiceContext serviceContext)
		throws PortalException {

		MDRPermission.check(
			getPermissionChecker(), groupId,
			ActionKeys.ADD_RULE_GROUP_INSTANCE);

		return mdrRuleGroupInstanceLocalService.addRuleGroupInstance(
			groupId, className, classPK, ruleGroupId, serviceContext);
	}

	@Override
	public void deleteRuleGroupInstance(long ruleGroupInstanceId)
		throws PortalException {

		MDRRuleGroupInstance ruleGroupInstance =
			mdrRuleGroupInstancePersistence.findByPrimaryKey(
				ruleGroupInstanceId);

		MDRRuleGroupInstancePermission.check(
			getPermissionChecker(), ruleGroupInstance, ActionKeys.DELETE);

		mdrRuleGroupInstanceLocalService.deleteRuleGroupInstance(
			ruleGroupInstance);
	}

	@Override
	public List<MDRRuleGroupInstance> getRuleGroupInstances(
		String className, long classPK, int start, int end,
		OrderByComparator<MDRRuleGroupInstance> orderByComparator) {

		long groupId = getGroupId(className, classPK);
		long classNameId = classNameLocalService.getClassNameId(className);

		return mdrRuleGroupInstancePersistence.filterFindByG_C_C(
			groupId, classNameId, classPK, start, end, orderByComparator);
	}

	@Override
	public int getRuleGroupInstancesCount(String className, long classPK) {
		long groupId = getGroupId(className, classPK);
		long classNameId = classNameLocalService.getClassNameId(className);

		return mdrRuleGroupInstancePersistence.filterCountByG_C_C(
			groupId, classNameId, classPK);
	}

	@Override
	public MDRRuleGroupInstance updateRuleGroupInstance(
			long ruleGroupInstanceId, int priority)
		throws PortalException {

		MDRRuleGroupInstance ruleGroupInstance =
			mdrRuleGroupInstancePersistence.findByPrimaryKey(
				ruleGroupInstanceId);

		MDRRuleGroupInstancePermission.check(
			getPermissionChecker(), ruleGroupInstance.getRuleGroupInstanceId(),
			ActionKeys.UPDATE);

		return mdrRuleGroupInstanceLocalService.updateRuleGroupInstance(
			ruleGroupInstanceId, priority);
	}

	protected long getGroupId(String className, long classPK) {
		long groupId = 0;

		if (className.equals(Layout.class.getName())) {
			Layout layout = layoutPersistence.fetchByPrimaryKey(classPK);

			if (layout != null) {
				groupId = layout.getGroupId();
			}
		}
		else if (className.equals(LayoutSet.class.getName())) {
			LayoutSet layoutSet = layoutSetPersistence.fetchByPrimaryKey(
				classPK);

			if (layoutSet != null) {
				groupId = layoutSet.getGroupId();
			}
		}

		return groupId;
	}

}