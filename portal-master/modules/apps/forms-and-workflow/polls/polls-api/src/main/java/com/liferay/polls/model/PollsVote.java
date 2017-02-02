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
 * The extended model interface for the PollsVote service. Represents a row in the &quot;PollsVote&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PollsVoteModel
 * @see com.liferay.polls.model.impl.PollsVoteImpl
 * @see com.liferay.polls.model.impl.PollsVoteModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.polls.model.impl.PollsVoteImpl")
@ProviderType
public interface PollsVote extends PollsVoteModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.polls.model.impl.PollsVoteImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<PollsVote, Long> VOTE_ID_ACCESSOR = new Accessor<PollsVote, Long>() {
			@Override
			public Long get(PollsVote pollsVote) {
				return pollsVote.getVoteId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<PollsVote> getTypeClass() {
				return PollsVote.class;
			}
		};

	public PollsChoice getChoice()
		throws com.liferay.portal.kernel.exception.PortalException;
}