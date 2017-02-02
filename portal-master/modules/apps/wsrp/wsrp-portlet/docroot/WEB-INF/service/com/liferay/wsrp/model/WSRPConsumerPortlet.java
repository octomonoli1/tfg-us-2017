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

package com.liferay.wsrp.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the WSRPConsumerPortlet service. Represents a row in the &quot;WSRP_WSRPConsumerPortlet&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerPortletModel
 * @see com.liferay.wsrp.model.impl.WSRPConsumerPortletImpl
 * @see com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.wsrp.model.impl.WSRPConsumerPortletImpl")
@ProviderType
public interface WSRPConsumerPortlet extends WSRPConsumerPortletModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.wsrp.model.impl.WSRPConsumerPortletImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WSRPConsumerPortlet, Long> WSRP_CONSUMER_PORTLET_ID_ACCESSOR =
		new Accessor<WSRPConsumerPortlet, Long>() {
			@Override
			public Long get(WSRPConsumerPortlet wsrpConsumerPortlet) {
				return wsrpConsumerPortlet.getWsrpConsumerPortletId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WSRPConsumerPortlet> getTypeClass() {
				return WSRPConsumerPortlet.class;
			}
		};
}