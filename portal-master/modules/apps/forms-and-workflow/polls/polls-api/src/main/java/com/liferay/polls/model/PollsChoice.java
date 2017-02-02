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

package com.liferay.polls.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the PollsChoice service. Represents a row in the &quot;PollsChoice&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PollsChoiceModel
 * @see com.liferay.polls.model.impl.PollsChoiceImpl
 * @see com.liferay.polls.model.impl.PollsChoiceModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.polls.model.impl.PollsChoiceImpl")
@ProviderType
public interface PollsChoice extends PollsChoiceModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.polls.model.impl.PollsChoiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<PollsChoice, Long> CHOICE_ID_ACCESSOR = new Accessor<PollsChoice, Long>() {
			@Override
			public Long get(PollsChoice pollsChoice) {
				return pollsChoice.getChoiceId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<PollsChoice> getTypeClass() {
				return PollsChoice.class;
			}
		};

	public int getVotesCount();
}