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

package com.liferay.portal.workflow.kaleo.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the KaleoTimer service. Represents a row in the &quot;KaleoTimer&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTimerModel
 * @see com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerImpl
 * @see com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerImpl")
@ProviderType
public interface KaleoTimer extends KaleoTimerModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<KaleoTimer, Long> KALEO_TIMER_ID_ACCESSOR = new Accessor<KaleoTimer, Long>() {
			@Override
			public Long get(KaleoTimer kaleoTimer) {
				return kaleoTimer.getKaleoTimerId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<KaleoTimer> getTypeClass() {
				return KaleoTimer.class;
			}
		};

	public java.util.List<KaleoTaskAssignment> getKaleoTaskReassignments();

	public boolean isRecurring();
}