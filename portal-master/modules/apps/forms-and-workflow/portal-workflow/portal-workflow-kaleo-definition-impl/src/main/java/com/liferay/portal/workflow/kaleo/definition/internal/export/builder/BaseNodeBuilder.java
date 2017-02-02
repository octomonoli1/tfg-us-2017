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

package com.liferay.portal.workflow.kaleo.definition.internal.export.builder;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.workflow.kaleo.definition.Action;
import com.liferay.portal.workflow.kaleo.definition.AddressRecipient;
import com.liferay.portal.workflow.kaleo.definition.AssigneesRecipient;
import com.liferay.portal.workflow.kaleo.definition.Assignment;
import com.liferay.portal.workflow.kaleo.definition.AssignmentType;
import com.liferay.portal.workflow.kaleo.definition.DelayDuration;
import com.liferay.portal.workflow.kaleo.definition.DurationScale;
import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.definition.Notification;
import com.liferay.portal.workflow.kaleo.definition.NotificationReceptionType;
import com.liferay.portal.workflow.kaleo.definition.Recipient;
import com.liferay.portal.workflow.kaleo.definition.RecipientType;
import com.liferay.portal.workflow.kaleo.definition.ResourceActionAssignment;
import com.liferay.portal.workflow.kaleo.definition.RoleAssignment;
import com.liferay.portal.workflow.kaleo.definition.RoleRecipient;
import com.liferay.portal.workflow.kaleo.definition.ScriptAssignment;
import com.liferay.portal.workflow.kaleo.definition.ScriptRecipient;
import com.liferay.portal.workflow.kaleo.definition.Timer;
import com.liferay.portal.workflow.kaleo.definition.UserAssignment;
import com.liferay.portal.workflow.kaleo.definition.UserRecipient;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoNotification;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTimer;
import com.liferay.portal.workflow.kaleo.service.KaleoActionLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoNotificationLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoNotificationRecipientLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskAssignmentLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTimerLocalService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
public abstract class BaseNodeBuilder<T extends Node> implements NodeBuilder {

	@Override
	public T buildNode(KaleoNode kaleoNode) throws PortalException {
		T node = createNode(kaleoNode);

		Set<Action> actions = buildActions(
			KaleoNode.class.getName(), kaleoNode.getKaleoNodeId());

		node.setActions(actions);

		node.setMetadata(kaleoNode.getMetadata());

		Set<Notification> notifications = buildNotifications(
			KaleoNode.class.getName(), kaleoNode.getKaleoNodeId());

		node.setNotifications(notifications);

		Set<Timer> timers = buildTimers(
			KaleoNode.class.getName(), kaleoNode.getKaleoNodeId());

		node.setTimers(timers);

		return node;
	}

	protected void addNotificationRecipients(
			KaleoNotification kaleoNotification, Notification notification)
		throws PortalException {

		List<KaleoNotificationRecipient> kaleoNotificationRecipients =
			kaleoNotificationRecipientLocalService.
				getKaleoNotificationRecipients(
					kaleoNotification.getKaleoNotificationId());

		for (KaleoNotificationRecipient kaleoNotificationRecipient :
				kaleoNotificationRecipients) {

			String recipientClassName =
				kaleoNotificationRecipient.getRecipientClassName();

			long recipientClassPK =
				kaleoNotificationRecipient.getRecipientClassPK();

			Recipient recipient = null;

			if (recipientClassName.equals(RecipientType.ADDRESS.getValue())) {
				recipient = new AddressRecipient(
					kaleoNotificationRecipient.getAddress());
			}
			else if (recipientClassName.equals(
						RecipientType.ASSIGNEES.getValue())) {

				recipient = new AssigneesRecipient();
			}
			else if (recipientClassName.equals(RecipientType.ROLE.getValue())) {
				Role role = roleLocalService.fetchRole(recipientClassPK);

				recipient = new RoleRecipient(
					role.getName(), role.getTypeLabel());
			}
			else if (recipientClassName.equals(
						RecipientType.SCRIPT.getValue())) {

				recipient = new ScriptRecipient(
					kaleoNotificationRecipient.getRecipientScript(),
					kaleoNotificationRecipient.getRecipientScriptLanguage(),
					kaleoNotificationRecipient.getRecipientScriptContexts());
			}
			else if (recipientClassName.equals(RecipientType.USER.getValue())) {
				if (recipientClassPK > 0) {
					User user = userLocalService.getUser(recipientClassPK);

					recipient = new UserRecipient(
						user.getUserId(), user.getScreenName(),
						user.getEmailAddress());
				}
				else {
					recipient = new UserRecipient();
				}
			}

			recipient.setNotificationReceptionType(
				NotificationReceptionType.parse(
					kaleoNotificationRecipient.getNotificationReceptionType()));

			notification.addRecipients(recipient);
		}
	}

	protected Set<Action> buildActions(
		String kaleoClassName, long kaleoClassPK) {

		List<KaleoAction> kaleoActions =
			kaleoActionLocalService.getKaleoActions(
				kaleoClassName, kaleoClassPK);

		Set<Action> actions = new HashSet<>(kaleoActions.size());

		for (KaleoAction kaleoAction : kaleoActions) {
			Action action = new Action(
				kaleoAction.getName(), kaleoAction.getDescription(),
				kaleoAction.getExecutionType(), kaleoAction.getScript(),
				kaleoAction.getScriptLanguage(),
				kaleoAction.getScriptRequiredContexts(),
				kaleoAction.getPriority());

			actions.add(action);
		}

		return actions;
	}

	protected Set<Assignment> buildAssigments(
			String kaleoClassName, long kaleoClassPK)
		throws PortalException {

		List<KaleoTaskAssignment> kaleoTaskAssignments =
			kaleoTaskAssignmentLocalService.getKaleoTaskAssignments(
				kaleoClassName, kaleoClassPK);

		Set<Assignment> assignments = new HashSet<>(
			kaleoTaskAssignments.size());

		for (KaleoTaskAssignment kaleoTaskAssignment : kaleoTaskAssignments) {
			String assigneeClassName =
				kaleoTaskAssignment.getAssigneeClassName();

			long assigneeClassPK = kaleoTaskAssignment.getAssigneeClassPK();

			Assignment assignment = null;

			if (assigneeClassName.equals(AssignmentType.SCRIPT.name())) {
				assignment = new ScriptAssignment(
					kaleoTaskAssignment.getAssigneeScript(),
					kaleoTaskAssignment.getAssigneeScriptLanguage(),
					kaleoTaskAssignment.getAssigneeScriptRequiredContexts());
			}
			else if (assigneeClassName.equals(ResourceAction.class.getName())) {
				assignment = new ResourceActionAssignment(
					kaleoTaskAssignment.getAssigneeActionId());
			}
			else if (assigneeClassName.equals(Role.class.getName())) {
				Role role = roleLocalService.fetchRole(assigneeClassPK);

				assignment = new RoleAssignment(
					role.getName(), role.getTypeLabel());
			}
			else if (assigneeClassName.equals(User.class.getName())) {
				if (assigneeClassPK == 0) {
					assignment = new UserAssignment();
				}
				else {
					User user = userLocalService.getUser(assigneeClassPK);

					assignment = new UserAssignment(
						user.getUserId(), user.getScreenName(),
						user.getEmailAddress());
				}
			}

			assignments.add(assignment);
		}

		return assignments;
	}

	protected Set<Notification> buildNotifications(
			String kaleoClassName, long kaleoClassPK)
		throws PortalException {

		List<KaleoNotification> kaleoNotifications =
			kaleoNotificationLocalService.getKaleoNotifications(
				kaleoClassName, kaleoClassPK);

		Set<Notification> notifications = new HashSet<>(
			kaleoNotifications.size());

		for (KaleoNotification kaleoNotification : kaleoNotifications) {
			Notification notification = new Notification(
				kaleoNotification.getName(), kaleoNotification.getDescription(),
				kaleoNotification.getExecutionType(),
				kaleoNotification.getTemplate(),
				kaleoNotification.getTemplateLanguage());

			notifications.add(notification);

			String notificationTypes = kaleoNotification.getNotificationTypes();

			String[] notificationTypeValues = StringUtil.split(
				notificationTypes, StringPool.COMMA);

			for (String notificationTypeValue : notificationTypeValues) {
				notification.addNotificationType(notificationTypeValue);
			}

			addNotificationRecipients(kaleoNotification, notification);
		}

		return notifications;
	}

	protected Set<Timer> buildTimers(String kaleoClassName, long kaleoClassPK)
		throws PortalException {

		List<KaleoTimer> kaleoTimers = kaleoTimerLocalService.getKaleoTimers(
			kaleoClassName, kaleoClassPK);

		Set<Timer> timers = new HashSet<>(kaleoTimers.size());

		for (KaleoTimer kaleoTimer : kaleoTimers) {
			Timer timer = new Timer(
				kaleoTimer.getName(), kaleoTimer.getDescription(),
				kaleoTimer.isBlocking());

			timers.add(timer);

			DelayDuration delayDuration = new DelayDuration(
				kaleoTimer.getDuration(),
				DurationScale.valueOf(
					StringUtil.toUpperCase(kaleoTimer.getScale())));

			timer.setDelayDuration(delayDuration);

			String recurrenceScale = kaleoTimer.getRecurrenceScale();

			if (Validator.isNotNull(recurrenceScale)) {
				DelayDuration recurrenceDelayDuration = new DelayDuration(
					kaleoTimer.getRecurrenceDuration(),
					DurationScale.valueOf(
						StringUtil.toUpperCase(recurrenceScale)));

				timer.setRecurrence(recurrenceDelayDuration);
			}

			Set<Action> timerActions = buildActions(
				KaleoTimer.class.getName(), kaleoTimer.getKaleoTimerId());

			timer.setActions(timerActions);

			Set<Assignment> reassignments = buildAssigments(
				KaleoTimer.class.getName(), kaleoTimer.getKaleoTimerId());

			timer.setReassignments(reassignments);

			Set<Notification> timerNotifications = buildNotifications(
				KaleoTimer.class.getName(), kaleoTimer.getKaleoTimerId());

			timer.setNotifications(timerNotifications);
		}

		return timers;
	}

	protected abstract T createNode(KaleoNode kaleoNode) throws PortalException;

	@Reference
	protected KaleoActionLocalService kaleoActionLocalService;

	@Reference
	protected KaleoNotificationLocalService kaleoNotificationLocalService;

	@Reference
	protected KaleoNotificationRecipientLocalService
		kaleoNotificationRecipientLocalService;

	@Reference
	protected KaleoTaskAssignmentLocalService kaleoTaskAssignmentLocalService;

	@Reference
	protected KaleoTimerLocalService kaleoTimerLocalService;

	@Reference
	protected RoleLocalService roleLocalService;

	@Reference
	protected UserLocalService userLocalService;

}