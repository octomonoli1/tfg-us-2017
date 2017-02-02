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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.concurrent.ThrowableAwareRunnable;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ContactLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourceLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.verify.model.VerifiableResourcedModel;
import com.liferay.portal.util.PortalInstances;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Raymond Augé
 * @author James Lefeu
 */
public class VerifyResourcePermissions extends VerifyProcess {

	public void verify(VerifiableResourcedModel... verifiableResourcedModels)
		throws Exception {

		long[] companyIds = PortalInstances.getCompanyIdsBySQL();

		for (long companyId : companyIds) {
			Role role = RoleLocalServiceUtil.getRole(
				companyId, RoleConstants.OWNER);

			List<VerifyResourcedModelRunnable> verifyResourcedModelRunnables =
				new ArrayList<>(verifiableResourcedModels.length);

			for (VerifiableResourcedModel verifiableResourcedModel :
					verifiableResourcedModels) {

				VerifyResourcedModelRunnable verifyResourcedModelRunnable =
					new VerifyResourcedModelRunnable(
						role, verifiableResourcedModel);

				verifyResourcedModelRunnables.add(verifyResourcedModelRunnable);
			}

			doVerify(verifyResourcedModelRunnables);

			verifyLayout(role);
		}
	}

	@Override
	protected void doVerify() throws Exception {
		Map<String, VerifiableResourcedModel> verifiableResourcedModelsMap =
			PortalBeanLocatorUtil.locate(VerifiableResourcedModel.class);

		Collection<VerifiableResourcedModel> verifiableResourcedModels =
			verifiableResourcedModelsMap.values();

		verify(
			verifiableResourcedModels.toArray(
				new VerifiableResourcedModel[
					verifiableResourcedModels.size()]));
	}

	protected void verifyLayout(Role role) throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			List<Layout> layouts =
				LayoutLocalServiceUtil.getNoPermissionLayouts(role.getRoleId());

			int total = layouts.size();

			for (int i = 0; i < total; i++) {
				Layout layout = layouts.get(i);

				verifyResourcedModel(
					role.getCompanyId(), Layout.class.getName(),
					layout.getPlid(), role, 0, i, total);
			}
		}
	}

	protected void verifyResourcedModel(
			long companyId, String modelName, long primKey, Role role,
			long ownerId, int cur, int total)
		throws Exception {

		if (_log.isInfoEnabled() && ((cur % 100) == 0)) {
			_log.info(
				"Processed " + cur + " of " + total + " resource permissions " +
					"for company = " + companyId + " and model " + modelName);
		}

		ResourcePermission resourcePermission =
			ResourcePermissionLocalServiceUtil.fetchResourcePermission(
				companyId, modelName, ResourceConstants.SCOPE_INDIVIDUAL,
				String.valueOf(primKey), role.getRoleId());

		if (resourcePermission == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"No resource found for {" + companyId + ", " + modelName +
						", " + ResourceConstants.SCOPE_INDIVIDUAL + ", " +
							primKey + ", " + role.getRoleId() + "}");
			}

			ResourceLocalServiceUtil.addResources(
				companyId, 0, ownerId, modelName, String.valueOf(primKey),
				false, false, false);
		}

		if (resourcePermission == null) {
			resourcePermission =
				ResourcePermissionLocalServiceUtil.fetchResourcePermission(
					companyId, modelName, ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(primKey), role.getRoleId());

			if (resourcePermission == null) {
				return;
			}
		}

		if (modelName.equals(User.class.getName())) {
			User user = UserLocalServiceUtil.fetchUserById(ownerId);

			if (user != null) {
				Contact contact = ContactLocalServiceUtil.fetchContact(
					user.getContactId());

				if (contact != null) {
					ownerId = contact.getUserId();
				}
			}
		}

		if (ownerId != resourcePermission.getOwnerId()) {
			resourcePermission.setOwnerId(ownerId);

			ResourcePermissionLocalServiceUtil.updateResourcePermission(
				resourcePermission);
		}
	}

	protected void verifyResourcedModel(
			Role role, VerifiableResourcedModel verifiableResourcedModel)
		throws Exception {

		int total = 0;

		try (LoggingTimer loggingTimer = new LoggingTimer(
				verifiableResourcedModel.getTableName());
			Connection con = DataAccess.getUpgradeOptimizedConnection();
			PreparedStatement ps1 = con.prepareStatement(
				"select count(*) from " +
					verifiableResourcedModel.getTableName() +
						" where companyId = " + role.getCompanyId());
			ResultSet rs1 = ps1.executeQuery()) {

			if (rs1.next()) {
				total = rs1.getInt(1);
			}

			StringBundler sb = new StringBundler(8);

			sb.append("select ");
			sb.append(verifiableResourcedModel.getPrimaryKeyColumnName());
			sb.append(", ");
			sb.append(verifiableResourcedModel.getUserIdColumnName());
			sb.append(" from ");
			sb.append(verifiableResourcedModel.getTableName());
			sb.append(" where companyId = ");
			sb.append(role.getCompanyId());

			try (PreparedStatement ps2 = con.prepareStatement(sb.toString());
				ResultSet rs2 = ps2.executeQuery()) {

				for (int i = 0; rs2.next(); i++) {
					long primKey = rs2.getLong(
						verifiableResourcedModel.getPrimaryKeyColumnName());
					long userId = rs2.getLong(
						verifiableResourcedModel.getUserIdColumnName());

					verifyResourcedModel(
						role.getCompanyId(),
						verifiableResourcedModel.getModelName(), primKey, role,
						userId, i, total);
				}
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		VerifyResourcePermissions.class);

	private class VerifyResourcedModelRunnable extends ThrowableAwareRunnable {

		public VerifyResourcedModelRunnable(
			Role role, VerifiableResourcedModel verifiableResourcedModel) {

			_role = role;
			_verifiableResourcedModel = verifiableResourcedModel;
		}

		@Override
		protected void doRun() throws Exception {
			verifyResourcedModel(_role, _verifiableResourcedModel);
		}

		private final Role _role;
		private final VerifiableResourcedModel _verifiableResourcedModel;

	}

}