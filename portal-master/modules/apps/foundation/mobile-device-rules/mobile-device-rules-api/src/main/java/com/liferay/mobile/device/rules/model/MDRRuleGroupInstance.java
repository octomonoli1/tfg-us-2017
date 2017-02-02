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

package com.liferay.mobile.device.rules.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the MDRRuleGroupInstance service. Represents a row in the &quot;MDRRuleGroupInstance&quot; database table, with each column mapped to a property of this class.
 *
 * @author Edward C. Han
 * @see MDRRuleGroupInstanceModel
 * @see com.liferay.mobile.device.rules.model.impl.MDRRuleGroupInstanceImpl
 * @see com.liferay.mobile.device.rules.model.impl.MDRRuleGroupInstanceModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.mobile.device.rules.model.impl.MDRRuleGroupInstanceImpl")
@ProviderType
public interface MDRRuleGroupInstance extends MDRRuleGroupInstanceModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.mobile.device.rules.model.impl.MDRRuleGroupInstanceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MDRRuleGroupInstance, Long> RULE_GROUP_INSTANCE_ID_ACCESSOR =
		new Accessor<MDRRuleGroupInstance, Long>() {
			@Override
			public Long get(MDRRuleGroupInstance mdrRuleGroupInstance) {
				return mdrRuleGroupInstance.getRuleGroupInstanceId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<MDRRuleGroupInstance> getTypeClass() {
				return MDRRuleGroupInstance.class;
			}
		};

	public java.util.List<MDRAction> getActions();

	public MDRRuleGroup getRuleGroup()
		throws com.liferay.portal.kernel.exception.PortalException;
}