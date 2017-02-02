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

package com.liferay.portal.workflow.kaleo.definition;

import java.util.Collections;
import java.util.Set;

/**
 * @author Marcellus Tavares
 */
public class Timer implements ActionAware, NotificationAware {

	public Timer(String name, String description, boolean blocking) {
		_name = name;
		_description = description;
		_blocking = blocking;
	}

	@Override
	public Set<Action> getActions() {
		if (_actions == null) {
			return Collections.emptySet();
		}

		return _actions;
	}

	public DelayDuration getDelayDuration() {
		return _delayDuration;
	}

	public String getDescription() {
		return _description;
	}

	public String getName() {
		return _name;
	}

	@Override
	public Set<Notification> getNotifications() {
		if (_notifications == null) {
			return Collections.emptySet();
		}

		return _notifications;
	}

	public Set<Assignment> getReassignments() {
		if (_reassignments == null) {
			return Collections.emptySet();
		}

		return _reassignments;
	}

	public DelayDuration getRecurrence() {
		return _recurrence;
	}

	public boolean isBlocking() {
		return _blocking;
	}

	@Override
	public void setActions(Set<Action> actions) {
		_actions = actions;
	}

	public void setDelayDuration(DelayDuration delayDuration) {
		_delayDuration = delayDuration;
	}

	@Override
	public void setNotifications(Set<Notification> notifications) {
		_notifications = notifications;
	}

	public void setReassignments(Set<Assignment> reassignments) {
		_reassignments = reassignments;
	}

	public void setRecurrence(DelayDuration recurrence) {
		_recurrence = recurrence;
	}

	private Set<Action> _actions;
	private final boolean _blocking;
	private DelayDuration _delayDuration;
	private final String _description;
	private final String _name;
	private Set<Notification> _notifications;
	private Set<Assignment> _reassignments;
	private DelayDuration _recurrence;

}