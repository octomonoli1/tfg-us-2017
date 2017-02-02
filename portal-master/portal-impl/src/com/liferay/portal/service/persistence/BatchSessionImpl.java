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

package com.liferay.portal.service.persistence;

import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.service.persistence.BatchSession;
import com.liferay.portal.kernel.util.InitialThreadLocal;

/**
 * @author     Raymond Augé
 * @author     Brian Wing Shun Chan
 * @deprecated As of 6.2.0, see LPS-30598.
 */
@Deprecated
public class BatchSessionImpl implements BatchSession {

	@Override
	public void delete(Session session, BaseModel<?> model)
		throws ORMException {

		if (!session.contains(model)) {
			model = (BaseModel<?>)session.get(
				model.getClass(), model.getPrimaryKeyObj());
		}

		if (model != null) {
			session.delete(model);
		}
	}

	@Override
	public boolean isEnabled() {
		return _enabled.get();
	}

	@Override
	public void setEnabled(boolean enabled) {
		_enabled.set(enabled);
	}

	@Override
	public void update(Session session, BaseModel<?> model, boolean merge)
		throws ORMException {

		if (model.isNew()) {
			session.save(model);

			model.setNew(false);
		}
		else {
			session.merge(model);
		}
	}

	private static final ThreadLocal<Boolean> _enabled =
		new InitialThreadLocal<>(BatchSessionImpl.class + "._enabled", false);

}