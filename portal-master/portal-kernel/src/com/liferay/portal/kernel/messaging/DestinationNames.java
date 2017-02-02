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

package com.liferay.portal.kernel.messaging;

/**
 * @author Brian Wing Shun Chan
 */
public interface DestinationNames {

	public static final String ASYNC_SERVICE = "liferay/async_service";

	public static final String AUDIT = "liferay/audit";

	public static final String BACKGROUND_TASK = "liferay/background_task";

	public static final String BACKGROUND_TASK_STATUS =
		"liferay/background_task_status";

	public static final String CONVERT_PROCESS = "liferay/convert_process";

	public static final String DEVICE_RECOGNITION_PROVIDER =
		"liferay/device_recognition_provider";

	public static final String DOCUMENT_LIBRARY_AUDIO_PROCESSOR =
		"liferay/document_library_audio_processor";

	public static final String DOCUMENT_LIBRARY_HOOK =
		"liferay/document_library_hook";

	public static final String DOCUMENT_LIBRARY_IMAGE_PROCESSOR =
		"liferay/document_library_image_processor";

	public static final String DOCUMENT_LIBRARY_PDF_PROCESSOR =
		"liferay/document_library_pdf_processor";

	public static final String DOCUMENT_LIBRARY_RAW_METADATA_PROCESSOR =
		"liferay/document_library_raw_metadata_processor";

	public static final String DOCUMENT_LIBRARY_SYNC_EVENT_PROCESSOR =
		"liferay/document_library_sync_event_processor";

	public static final String DOCUMENT_LIBRARY_VIDEO_PROCESSOR =
		"liferay/document_library_video_processor";

	public static final String EXPORT_IMPORT_LIFECYCLE_EVENT_ASYNC =
		"liferay/export_import_lifecycle_event_async";

	public static final String EXPORT_IMPORT_LIFECYCLE_EVENT_SYNC =
		"liferay/export_import_lifecycle_event_sync";

	public static final String FLAGS = "liferay/flags";

	public static final String HOT_DEPLOY = "liferay/hot_deploy";

	public static final String IP_GEOCODER = "liferay/ip_geocoder";

	public static final String IP_GEOCODER_RESPONSE =
		"liferay/ip_geocoder/response";

	public static final String LAYOUTS_LOCAL_PUBLISHER =
		"liferay/layouts_local_publisher";

	public static final String LAYOUTS_REMOTE_PUBLISHER =
		"liferay/layouts_remote_publisher";

	public static final String LIVE_USERS = "liferay/live_users";

	public static final String MAIL = "liferay/mail";

	public static final String MAIL_SYNCHRONIZER = "liferay/mail_synchronizer";

	public static final String MARKETPLACE = "liferay/marketplace";

	public static final String MESSAGE_BOARDS_MAILING_LIST =
		"liferay/message_boards_mailing_list";

	public static final String MESSAGE_BUS_DEFAULT_RESPONSE =
		"liferay/message_bus/default_response";

	public static final String MESSAGE_BUS_MESSAGE_STATUS =
		"liferay/message_bus/message_status";

	public static final String MONITORING = "liferay/monitoring";

	public static final String POLLER = "liferay/poller";

	public static final String POLLER_COMET_RESPONSE =
		"liferay/poller_comet_response";

	public static final String POLLER_RESPONSE = "liferay/poller_response";

	/**
	 * @deprecated As of 7.0.0 replaced by {@link com.liferay.push.notifications.messaging.DestinationNames#PUSH_NOTIFICATION}
	 */
	@Deprecated
	public static final String PUSH_NOTIFICATION = "liferay/push_notification";

	public static final String SCHEDULER_DISPATCH =
		"liferay/scheduler_dispatch";

	public static final String SCHEDULER_ENGINE = "liferay/scheduler_engine";

	public static final String SCHEDULER_SCRIPTING =
		"liferay/scheduler_scripting";

	public static final String SCRIPTING = "liferay/scripting";

	public static final String SEARCH_READER = "liferay/search_reader";

	public static final String SEARCH_WRITER = "liferay/search_writer";

	public static final String SUBSCRIPTION_CLEAN_UP =
		"liferay/subscription_clean_up";

	public static final String SUBSCRIPTION_SENDER =
		"liferay/subscription_sender";

	public static final String TEST_TRANSACTION = "liferay/test_transaction";

	public static final String WORKFLOW_COMPARATOR =
		"liferay/workflow_comparator";

	public static final String WORKFLOW_DEFINITION =
		"liferay/workflow_definition";

	public static final String WORKFLOW_ENGINE = "liferay/workflow_engine";

	public static final String WORKFLOW_INSTANCE = "liferay/workflow_instance";

	public static final String WORKFLOW_LOG = "liferay/workflow_log";

	public static final String WORKFLOW_TASK = "liferay/workflow_task";

	public static final String WSRP = "liferay/wsrp";

}