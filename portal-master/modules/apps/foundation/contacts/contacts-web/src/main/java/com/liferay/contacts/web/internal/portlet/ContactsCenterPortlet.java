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

package com.liferay.contacts.web.internal.portlet;

import com.liferay.announcements.kernel.model.AnnouncementsDelivery;
import com.liferay.announcements.kernel.service.AnnouncementsDeliveryLocalService;
import com.liferay.contacts.constants.ContactsConstants;
import com.liferay.contacts.constants.SocialRelationConstants;
import com.liferay.contacts.exception.DuplicateEntryEmailAddressException;
import com.liferay.contacts.exception.EntryEmailAddressException;
import com.liferay.contacts.model.Entry;
import com.liferay.contacts.service.EntryLocalService;
import com.liferay.contacts.util.ContactsUtil;
import com.liferay.contacts.web.internal.constants.ContactsPortletKeys;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.AddressCityException;
import com.liferay.portal.kernel.exception.AddressStreetException;
import com.liferay.portal.kernel.exception.AddressZipException;
import com.liferay.portal.kernel.exception.ContactFirstNameException;
import com.liferay.portal.kernel.exception.ContactFullNameException;
import com.liferay.portal.kernel.exception.ContactLastNameException;
import com.liferay.portal.kernel.exception.DuplicateUserEmailAddressException;
import com.liferay.portal.kernel.exception.EmailAddressException;
import com.liferay.portal.kernel.exception.NoSuchCountryException;
import com.liferay.portal.kernel.exception.NoSuchListTypeException;
import com.liferay.portal.kernel.exception.NoSuchRegionException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PhoneNumberException;
import com.liferay.portal.kernel.exception.PhoneNumberExtensionException;
import com.liferay.portal.kernel.exception.ReservedUserEmailAddressException;
import com.liferay.portal.kernel.exception.ReservedUserScreenNameException;
import com.liferay.portal.kernel.exception.UserEmailAddressException;
import com.liferay.portal.kernel.exception.UserScreenNameException;
import com.liferay.portal.kernel.exception.UserSmsException;
import com.liferay.portal.kernel.exception.WebsiteURLException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.EmailAddress;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.PortletConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.model.Website;
import com.liferay.portal.kernel.notifications.UserNotificationManagerUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserNotificationEventLocalService;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.util.comparator.UserLastNameComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.social.kernel.exception.NoSuchRelationException;
import com.liferay.social.kernel.model.SocialRelation;
import com.liferay.social.kernel.model.SocialRequest;
import com.liferay.social.kernel.model.SocialRequestConstants;
import com.liferay.social.kernel.service.SocialRelationLocalService;
import com.liferay.social.kernel.service.SocialRequestLocalService;
import com.liferay.users.admin.kernel.util.UsersAdminUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ryan Park
 * @author Jonathan Lee
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=contacts-portlet",
		"com.liferay.portlet.display-category=category.social",
		"com.liferay.portlet.friendly-url-mapping=contacts",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.header-portlet-javascript=/js/main.js",
		"com.liferay.portlet.icon=/icons/contacts_center.png",
		"javax.portlet.display-name=Contacts Center",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.info.keywords=Contacts Center",
		"javax.portlet.info.short-title=Contacts Center",
		"javax.portlet.info.title=Contacts Center",
		"javax.portlet.init-param.config-template=/configuration.jsp",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ContactsPortletKeys.CONTACTS_CENTER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class ContactsCenterPortlet extends MVCPortlet {

	public void addSocialRelation(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] userIds = getUserIds(actionRequest);

		int type = ParamUtil.getInteger(actionRequest, "type");

		if (type == SocialRelationConstants.TYPE_BI_CONNECTION) {
			return;
		}

		for (long userId : userIds) {
			if (userId == themeDisplay.getUserId()) {
				continue;
			}

			boolean blocked = socialRelationLocalService.hasRelation(
				userId, themeDisplay.getUserId(),
				SocialRelationConstants.TYPE_UNI_ENEMY);

			if (type == SocialRelationConstants.TYPE_UNI_ENEMY) {
				socialRelationLocalService.deleteRelations(
					themeDisplay.getUserId(), userId);

				socialRelationLocalService.deleteRelations(
					userId, themeDisplay.getUserId());
			}
			else if (blocked) {
				continue;
			}

			socialRelationLocalService.addRelation(
				themeDisplay.getUserId(), userId, type);

			if (blocked) {
				socialRelationLocalService.addRelation(
					userId, themeDisplay.getUserId(), type);
			}
		}
	}

	public void deleteSocialRelation(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] userIds = getUserIds(actionRequest);

		int type = ParamUtil.getInteger(actionRequest, "type");

		for (long userId : userIds) {
			if (userId == themeDisplay.getUserId()) {
				continue;
			}

			try {
				socialRelationLocalService.deleteRelation(
					themeDisplay.getUserId(), userId, type);
			}
			catch (NoSuchRelationException nsre) {
			}
		}
	}

	public void exportVCard(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long userId = ParamUtil.getLong(resourceRequest, "userId");

		User user = userService.getUserById(userId);

		String vCard = ContactsUtil.getVCard(user);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, user.getFullName() + ".vcf",
			vCard.getBytes(StringPool.UTF8), "text/x-vcard; charset=UTF-8");
	}

	public void exportVCards(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long[] userIds = StringUtil.split(
			ParamUtil.getString(resourceRequest, "userIds"), 0L);

		List<User> users = new ArrayList<>(userIds.length);

		for (long userId : userIds) {
			User user = userService.getUserById(userId);

			users.add(user);
		}

		String vCards = ContactsUtil.getVCards(users);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, "vcards.vcf",
			vCards.getBytes(StringPool.UTF8), "text/x-vcard; charset=UTF-8");
	}

	public void getContact(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long userId = ParamUtil.getLong(resourceRequest, "userId");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("success", Boolean.TRUE);

		JSONObject userJSONObject = getUserJSONObject(
			resourceResponse, themeDisplay, userId);

		jsonObject.put("user", userJSONObject);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	public void getContacts(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		JSONObject contactListJSONObject = getContactsJSONObject(
			resourceRequest, resourceResponse);

		writeJSON(resourceRequest, resourceResponse, contactListJSONObject);
	}

	public void getSelectedContacts(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] userIds = StringUtil.split(
			ParamUtil.getString(resourceRequest, "userIds"), 0L);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (long userId : userIds) {
			try {
				JSONObject userJSONObject = JSONFactoryUtil.createJSONObject();

				userJSONObject.put("success", Boolean.TRUE);
				userJSONObject.put(
					"user",
					getUserJSONObject(resourceResponse, themeDisplay, userId));

				jsonArray.put(userJSONObject);
			}
			catch (NoSuchUserException nsue) {
			}
		}

		jsonObject.put("contacts", jsonArray);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		try {
			String actionName = ParamUtil.getString(
				actionRequest, ActionRequest.ACTION_NAME);

			boolean jsonFormat = ParamUtil.getBoolean(
				actionRequest, "jsonFormat");

			if (jsonFormat) {
				if (actionName.equals("addSocialRelation")) {
					addSocialRelation(actionRequest, actionResponse);
				}
				else if (actionName.equals("deleteSocialRelation")) {
					deleteSocialRelation(actionRequest, actionResponse);
				}
				else if (actionName.equals("requestSocialRelation")) {
					requestSocialRelation(actionRequest, actionResponse);
				}

				JSONObject jsonObject = getContactsDisplayJSONObject(
					actionRequest, actionResponse);

				writeJSON(actionRequest, actionResponse, jsonObject);
			}
			else if (actionName.equals("deleteEntry")) {
				deleteEntry(actionRequest, actionResponse);
			}
			else if (actionName.equals("updateEntry")) {
				updateEntry(actionRequest, actionResponse);
			}
			else if (actionName.equals("updateFieldGroup")) {
				updateFieldGroup(actionRequest, actionResponse);
			}
			else if (actionName.equals("updateSocialRequest")) {
				updateSocialRequest(actionRequest, actionResponse);
			}
			else {
				super.processAction(actionRequest, actionResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void requestSocialRelation(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] userIds = getUserIds(actionRequest);

		int type = ParamUtil.getInteger(actionRequest, "type");

		for (long userId : userIds) {
			if (userId == themeDisplay.getUserId()) {
				continue;
			}

			if (socialRelationLocalService.hasRelation(
					userId, themeDisplay.getUserId(),
					SocialRelationConstants.TYPE_UNI_ENEMY) ||
				socialRequestLocalService.hasRequest(
					themeDisplay.getUserId(), User.class.getName(),
					themeDisplay.getUserId(), type, userId,
					SocialRequestConstants.STATUS_PENDING)) {

				continue;
			}

			JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

			String portletId = PortalUtil.getPortletId(actionRequest);

			extraDataJSONObject.put(
				"portletId", PortletConstants.getRootPortletId(portletId));

			SocialRequest socialRequest = socialRequestLocalService.addRequest(
				themeDisplay.getUserId(), 0, User.class.getName(),
				themeDisplay.getUserId(), type, extraDataJSONObject.toString(),
				userId);

			sendNotificationEvent(socialRequest);
		}
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("exportVCard")) {
				exportVCard(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("exportVCards")) {
				exportVCards(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("getContact")) {
				getContact(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("getContacts")) {
				getContacts(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("getSelectedContacts")) {
				getSelectedContacts(resourceRequest, resourceResponse);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void updateEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		long entryId = ParamUtil.getLong(actionRequest, "entryId");

		String fullName = ParamUtil.getString(actionRequest, "fullName");
		String emailAddress = ParamUtil.getString(
			actionRequest, "emailAddress");
		String comments = ParamUtil.getString(actionRequest, "comments");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		String message = null;

		try {
			Entry entry = null;

			if (entryId > 0) {
				entry = entryLocalService.getEntry(entryId);

				if (entry.getUserId() == themeDisplay.getUserId()) {
					entry = entryLocalService.updateEntry(
						entryId, fullName, emailAddress, comments);

					message = "you-have-successfully-updated-the-contact";
				}
			}
			else {
				entry = entryLocalService.addEntry(
					themeDisplay.getUserId(), fullName, emailAddress, comments);

				message = "you-have-successfully-added-a-new-contact";
			}

			jsonObject.put(
				"contact",
				getEntryJSONObject(
					actionResponse, themeDisplay, entry, redirect));

			JSONObject contactsJSONObject = getContactsJSONObject(
				actionRequest, actionResponse);

			jsonObject.put("contactList", contactsJSONObject);

			jsonObject.put("success", Boolean.TRUE);
		}
		catch (Exception e) {
			if (e instanceof ContactFullNameException) {
				message = "full-name-cannot-be-empty";
			}
			else if (e instanceof DuplicateEntryEmailAddressException) {
				message = "there-is-already-a-contact-with-this-email-address";
			}
			else if (e instanceof EntryEmailAddressException) {
				message = "please-enter-a-valid-email-address";
			}
			else {
				message =
					"an-error-occurred-while-processing-the-requested-resource";
			}

			jsonObject.put("success", Boolean.FALSE);
		}

		jsonObject.put("message", translate(actionRequest, message));

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void updateFieldGroup(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			String fieldGroup = ParamUtil.getString(
				actionRequest, "fieldGroup");

			if (fieldGroup.equals("additionalEmailAddresses")) {
				updateAdditionalEmailAddresses(actionRequest);
			}
			else if (fieldGroup.equals("addresses")) {
				updateAddresses(actionRequest);
			}
			else if (fieldGroup.equals("categorization")) {
				updateAsset(actionRequest);
			}
			else if (fieldGroup.equals("comments") ||
					 fieldGroup.equals("details") ||
					 fieldGroup.equals("instantMessenger") ||
					 fieldGroup.equals("sms") ||
					 fieldGroup.equals("socialNetwork")) {

				updateProfile(actionRequest);
			}
			else if (fieldGroup.equals("phoneNumbers")) {
				updatePhoneNumbers(actionRequest);
			}
			else if (fieldGroup.equals("websites")) {
				updateWebsites(actionRequest);
			}

			String redirect = ParamUtil.getString(actionRequest, "redirect");

			jsonObject.put("redirect", redirect);

			jsonObject.put("success", Boolean.TRUE);
		}
		catch (Exception e) {
			String message = "your-request-failed-to-complete";

			if (e instanceof AddressCityException) {
				message = "please-enter-a-valid-city";
			}
			else if (e instanceof AddressStreetException) {
				message = "please-enter-a-valid-street";
			}
			else if (e instanceof AddressZipException) {
				message = "please-enter-a-valid-postal-code";
			}
			else if (e instanceof ContactFirstNameException) {
				message = "please-enter-a-valid-first-name";
			}
			else if (e instanceof ContactFullNameException) {
				message = "please-enter-a-valid-first-middle-and-last-name";
			}
			else if (e instanceof ContactLastNameException) {
				message = "please-enter-a-valid-last-name";
			}
			else if (e instanceof DuplicateUserEmailAddressException) {
				message = "the-email-address-you-requested-is-already-taken";
			}
			else if (e instanceof EmailAddressException) {
				message = "please-enter-a-valid-email-address";
			}
			else if (e instanceof NoSuchCountryException) {
				message ="please-select-a-country";
			}
			else if (e instanceof NoSuchListTypeException) {
				message = "please-select-a-type";
			}
			else if (e instanceof NoSuchRegionException) {
				message = "please-select-a-region";
			}
			else if (e instanceof PhoneNumberException) {
				message = "please-enter-a-valid-phone-number";
			}
			else if (e instanceof PhoneNumberExtensionException) {
				message = "please-enter-a-valid-phone-number-extension";
			}
			else if (e instanceof ReservedUserEmailAddressException) {
				message = "the-email-address-you-requested-is-reserveds";
			}
			else if (e instanceof ReservedUserScreenNameException) {
				message = "the-screen-name-you-requested-is-reserved";
			}
			else if (e instanceof UserEmailAddressException) {
				message = "please-enter-a-valid-email-address";
			}
			else if (e instanceof UserScreenNameException) {
				message = "please-enter-a-valid-screen-name";
			}
			else if (e instanceof UserSmsException) {
				message = "please-enter-a-sms-id-that-is-a-valid-email-address";
			}
			else if (e instanceof WebsiteURLException) {
				message = "please-enter-a-valid-url";
			}

			jsonObject.put("message", translate(actionRequest, message));

			jsonObject.put("success", Boolean.FALSE);
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void updateSocialRequest(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long socialRequestId = ParamUtil.getLong(
			actionRequest, "socialRequestId");
		int status = ParamUtil.getInteger(actionRequest, "status");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			SocialRequest socialRequest =
				socialRequestLocalService.getSocialRequest(socialRequestId);

			if (socialRelationLocalService.hasRelation(
					socialRequest.getReceiverUserId(),
					socialRequest.getUserId(),
					SocialRelationConstants.TYPE_UNI_ENEMY)) {

				status = SocialRequestConstants.STATUS_IGNORE;
			}

			socialRequestLocalService.updateRequest(
				socialRequestId, status, themeDisplay);

			if (status == SocialRequestConstants.STATUS_CONFIRM) {
				socialRelationLocalService.addRelation(
					socialRequest.getUserId(),
					socialRequest.getReceiverUserId(), socialRequest.getType());
			}

			jsonObject.put("success", Boolean.TRUE);
		}
		catch (Exception e) {
			jsonObject.put("success", Boolean.FALSE);
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	protected void deleteEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long entryId = ParamUtil.getLong(actionRequest, "entryId");

		if (entryId > 0) {
			Entry entry = entryLocalService.getEntry(entryId);

			if (entry.getUserId() == themeDisplay.getUserId()) {
				entryLocalService.deleteEntry(entryId);
			}
		}
	}

	protected JSONObject getContactsDisplayJSONObject(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] userIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "userIds"), 0L);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject contactListJSONObject = getContactsJSONObject(
			actionRequest, actionResponse);

		jsonObject.put("contactList", contactListJSONObject);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (long userId : userIds) {
			JSONObject userJSONObject = JSONFactoryUtil.createJSONObject();

			userJSONObject.put("success", Boolean.TRUE);
			userJSONObject.put(
				"user",
				getUserJSONObject(actionResponse, themeDisplay, userId));

			jsonArray.put(userJSONObject);
		}

		jsonObject.put("contacts", jsonArray);

		String message = getRelationMessage(actionRequest);

		jsonObject.put("message", translate(actionRequest, message));

		return jsonObject;
	}

	protected JSONObject getContactsJSONObject(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String redirect = ParamUtil.getString(portletRequest, "redirect");

		String filterBy = ParamUtil.getString(portletRequest, "filterBy");
		String keywords = ParamUtil.getString(portletRequest, "keywords");
		int start = ParamUtil.getInteger(portletRequest, "start");
		int end = ParamUtil.getInteger(portletRequest, "end");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject optionsJSONObject = JSONFactoryUtil.createJSONObject();

		optionsJSONObject.put("end", end);
		optionsJSONObject.put("filterBy", filterBy);
		optionsJSONObject.put("keywords", keywords);
		optionsJSONObject.put("start", start);

		jsonObject.put("options", optionsJSONObject);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String portletId = portletDisplay.getId();

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		if (filterBy.equals(ContactsConstants.FILTER_BY_DEFAULT) &&
			!portletId.equals(ContactsPortletKeys.MEMBERS)) {

			List<BaseModel<?>> contacts =
				entryLocalService.searchUsersAndContacts(
					themeDisplay.getCompanyId(), themeDisplay.getUserId(),
					keywords, start, end);

			int contactsCount = entryLocalService.searchUsersAndContactsCount(
				themeDisplay.getCompanyId(), themeDisplay.getUserId(),
				keywords);

			jsonObject.put("count", contactsCount);

			for (BaseModel<?> contact : contacts) {
				JSONObject contactJSONObject = null;

				if (contact instanceof User) {
					contactJSONObject = getUserJSONObject(
						portletResponse, themeDisplay, (User)contact);
				}
				else {
					contactJSONObject = getEntryJSONObject(
						portletResponse, themeDisplay, (Entry)contact,
						redirect);
				}

				jsonArray.put(contactJSONObject);
			}
		}
		else if (filterBy.equals(ContactsConstants.FILTER_BY_FOLLOWERS) &&
				 !portletId.equals(ContactsPortletKeys.MEMBERS)) {

			List<SocialRelation> socialRelations =
				socialRelationLocalService.getInverseRelations(
					themeDisplay.getUserId(),
					SocialRelationConstants.TYPE_UNI_FOLLOWER, start, end);

			for (SocialRelation socialRelation : socialRelations) {
				jsonArray.put(
					getUserJSONObject(
						portletResponse, themeDisplay,
						socialRelation.getUserId1()));
			}
		}
		else if (filterBy.equals(
					ContactsConstants.FILTER_BY_TYPE_MY_CONTACTS) &&
				 !portletId.equals(ContactsPortletKeys.MEMBERS)) {

			List<Entry> entries = entryLocalService.search(
				themeDisplay.getUserId(), keywords, start, end);

			int entriesCount = entryLocalService.searchCount(
				themeDisplay.getUserId(), keywords);

			jsonObject.put("count", entriesCount);

			for (Entry entry : entries) {
				JSONObject contactJSONObject = getEntryJSONObject(
					portletResponse, themeDisplay, entry, redirect);

				jsonArray.put(contactJSONObject);
			}
		}
		else {
			LinkedHashMap<String, Object> params = new LinkedHashMap<>();

			params.put("inherit", Boolean.TRUE);

			Group group = themeDisplay.getScopeGroup();
			Layout layout = themeDisplay.getLayout();

			if (group.isUser() && layout.isPublicLayout()) {
				params.put(
					"socialRelationType",
					new Long[] {
						group.getClassPK(),
						(long)SocialRelationConstants.TYPE_BI_CONNECTION
					});
			}
			else if (filterBy.startsWith(ContactsConstants.FILTER_BY_TYPE)) {
				params.put(
					"socialRelationType",
					new Long[] {
						themeDisplay.getUserId(),
						ContactsUtil.getSocialRelationType(filterBy)
					});
			}

			if (portletId.equals(ContactsPortletKeys.MEMBERS)) {
				params.put("usersGroups", group.getGroupId());
			}
			else if (filterBy.startsWith(ContactsConstants.FILTER_BY_GROUP)) {
				params.put("usersGroups", ContactsUtil.getGroupId(filterBy));
			}

			List<User> usersList = null;

			if (filterBy.equals(ContactsConstants.FILTER_BY_ADMINS)) {
				Role siteAdministratorRole = roleLocalService.getRole(
					group.getCompanyId(), RoleConstants.SITE_ADMINISTRATOR);

				params.put(
					"userGroupRole",
					new Long[] {
						group.getGroupId(), siteAdministratorRole.getRoleId()
					});

				Set<User> users = new HashSet<>();

				users.addAll(
					userLocalService.search(
						themeDisplay.getCompanyId(), keywords,
						WorkflowConstants.STATUS_APPROVED, params,
						QueryUtil.ALL_POS, QueryUtil.ALL_POS,
						(OrderByComparator)null));

				Role siteOwnerRole = roleLocalService.getRole(
					group.getCompanyId(), RoleConstants.SITE_OWNER);

				params.put(
					"userGroupRole",
					new Long[] {group.getGroupId(), siteOwnerRole.getRoleId()});

				users.addAll(
					userLocalService.search(
						themeDisplay.getCompanyId(), keywords,
						WorkflowConstants.STATUS_APPROVED, params,
						QueryUtil.ALL_POS, QueryUtil.ALL_POS,
						(OrderByComparator)null));

				usersList = new ArrayList<>(users);

				ListUtil.sort(usersList, new UserLastNameComparator(true));
			}
			else {
				int usersCount = userLocalService.searchCount(
					themeDisplay.getCompanyId(), keywords,
					WorkflowConstants.STATUS_APPROVED, params);

				jsonObject.put("count", usersCount);

				usersList = userLocalService.search(
					themeDisplay.getCompanyId(), keywords,
					WorkflowConstants.STATUS_APPROVED, params, start, end,
					new UserLastNameComparator(true));
			}

			for (User user : usersList) {
				JSONObject userJSONObject = getUserJSONObject(
					portletResponse, themeDisplay, user);

				jsonArray.put(userJSONObject);
			}
		}

		jsonObject.put("users", jsonArray);

		return jsonObject;
	}

	protected JSONObject getEntryJSONObject(
			PortletResponse portletResponse, ThemeDisplay themeDisplay,
			Entry entry, String redirect)
		throws Exception {

		entry = entry.toEscapedModel();

		JSONObject jsonObject = ContactsUtil.getEntryJSONObject(entry);

		jsonObject.put(
			"portraitURL",
			themeDisplay.getPathImage() + "/user_male_portrait?img_id=0");
		jsonObject.put("redirect", redirect);

		LiferayPortletResponse liferayPortletResponse =
			(LiferayPortletResponse)portletResponse;

		PortletURL viewSummaryURL = liferayPortletResponse.createRenderURL();

		viewSummaryURL.setParameter(
			"mvcPath", "/contacts_center/view_resources.jsp");
		viewSummaryURL.setParameter("redirect", redirect);
		viewSummaryURL.setParameter(
			"entryId", String.valueOf(entry.getEntryId()));
		viewSummaryURL.setParameter("portalUser", Boolean.FALSE.toString());
		viewSummaryURL.setWindowState(LiferayWindowState.EXCLUSIVE);

		jsonObject.put("viewSummaryURL", viewSummaryURL.toString());

		return jsonObject;
	}

	protected String getRelationMessage(ActionRequest actionRequest) {
		int type = ParamUtil.getInteger(actionRequest, "type");

		String actionName = ParamUtil.getString(
			actionRequest, ActionRequest.ACTION_NAME);

		String message = "your-request-completed-successfully";

		if (actionName.equals("addSocialRelation")) {
			if (type == SocialRelationConstants.TYPE_BI_CONNECTION) {
				message = "you-are-now-connected-to-this-user";
			}
			else if (type == SocialRelationConstants.TYPE_UNI_FOLLOWER) {
				message = "you-are-now-following-this-user";
			}
			else if (type == SocialRelationConstants.TYPE_UNI_ENEMY) {
				message = "you-have-blocked-this-user";
			}
		}
		else if (actionName.equals("deleteSocialRelation")) {
			if (type == SocialRelationConstants.TYPE_BI_CONNECTION) {
				message = "you-are-not-connected-to-this-user-anymore";
			}
			else if (type == SocialRelationConstants.TYPE_UNI_FOLLOWER) {
				message = "you-are-not-following-this-user-anymore";
			}
			else if (type == SocialRelationConstants.TYPE_UNI_ENEMY) {
				message = "you-have-unblocked-this-user";
			}
		}
		else if (actionName.equals("requestSocialRelation")) {
			if (type == SocialRelationConstants.TYPE_BI_CONNECTION) {
				message =
					"this-user-has-received-a-connection-request-from-you";
			}
		}

		return message;
	}

	protected long[] getUserIds(ActionRequest actionRequest) {
		long[] userIds;

		long userId = ParamUtil.getLong(actionRequest, "userId", 0);

		if (userId > 0) {
			userIds = new long[] {userId};
		}
		else {
			userIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "userIds"), 0L);
		}

		return userIds;
	}

	protected JSONObject getUserJSONObject(
			PortletResponse portletResponse, ThemeDisplay themeDisplay,
			long userId)
		throws Exception {

		User user = userLocalService.getUser(userId);

		return getUserJSONObject(portletResponse, themeDisplay, user);
	}

	protected JSONObject getUserJSONObject(
			PortletResponse portletResponse, ThemeDisplay themeDisplay,
			User user)
		throws Exception {

		user = user.toEscapedModel();

		JSONObject jsonObject = ContactsUtil.getUserJSONObject(
			themeDisplay.getUserId(), user);

		jsonObject.put("portraitURL", user.getPortraitURL(themeDisplay));

		LiferayPortletResponse liferayPortletResponse =
			(LiferayPortletResponse)portletResponse;

		PortletURL viewSummaryURL = liferayPortletResponse.createRenderURL();

		viewSummaryURL.setParameter(
			"mvcPath", "/contacts_center/view_resources.jsp");
		viewSummaryURL.setParameter("userId", String.valueOf(user.getUserId()));
		viewSummaryURL.setParameter("portalUser", Boolean.TRUE.toString());
		viewSummaryURL.setWindowState(LiferayWindowState.EXCLUSIVE);

		jsonObject.put("viewSummaryURL", viewSummaryURL.toString());

		return jsonObject;
	}

	protected void sendNotificationEvent(SocialRequest socialRequest)
		throws Exception {

		if (UserNotificationManagerUtil.isDeliver(
				socialRequest.getReceiverUserId(),
				ContactsPortletKeys.CONTACTS_CENTER, 0,
				SocialRelationConstants.SOCIAL_RELATION_REQUEST,
				UserNotificationDeliveryConstants.TYPE_WEBSITE)) {

			JSONObject notificationEventJSONObject =
				JSONFactoryUtil.createJSONObject();

			notificationEventJSONObject.put(
				"classPK", socialRequest.getRequestId());
			notificationEventJSONObject.put(
				"userId", socialRequest.getUserId());

			userNotificationEventLocalService.sendUserNotificationEvents(
				socialRequest.getReceiverUserId(),
				ContactsPortletKeys.CONTACTS_CENTER,
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true,
				notificationEventJSONObject);
		}
	}

	protected void updateAdditionalEmailAddresses(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		List<EmailAddress> emailAddresses = UsersAdminUtil.getEmailAddresses(
			actionRequest);

		UsersAdminUtil.updateEmailAddresses(
			Contact.class.getName(), user.getContactId(), emailAddresses);
	}

	protected void updateAddresses(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		List<Address> addresses = UsersAdminUtil.getAddresses(actionRequest);

		UsersAdminUtil.updateAddresses(
			Contact.class.getName(), user.getContactId(), addresses);
	}

	protected void updateAsset(ActionRequest actionRequest) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			User.class.getName(), actionRequest);

		userLocalService.updateAsset(
			user.getUserId(), user, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());
	}

	protected void updatePhoneNumbers(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		List<Phone> phones = UsersAdminUtil.getPhones(actionRequest);

		UsersAdminUtil.updatePhones(
			Contact.class.getName(), user.getContactId(), phones);
	}

	protected void updateProfile(ActionRequest actionRequest) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		boolean deleteLogo = ParamUtil.getBoolean(actionRequest, "deleteLogo");

		if (deleteLogo) {
			userService.deletePortrait(user.getUserId());
		}

		String comments = BeanParamUtil.getString(
			user, actionRequest, "comments");
		String emailAddress = BeanParamUtil.getString(
			user, actionRequest, "emailAddress");
		String firstName = BeanParamUtil.getString(
			user, actionRequest, "firstName");
		String jobTitle = BeanParamUtil.getString(
			user, actionRequest, "jobTitle");
		String lastName = BeanParamUtil.getString(
			user, actionRequest, "lastName");
		String middleName = BeanParamUtil.getString(
			user, actionRequest, "middleName");
		String screenName = BeanParamUtil.getString(
			user, actionRequest, "screenName");

		Contact contact = user.getContact();

		String facebookSn = BeanParamUtil.getString(
			contact, actionRequest, "facebookSn");
		String jabberSn = BeanParamUtil.getString(
			contact, actionRequest, "jabberSn");
		String skypeSn = BeanParamUtil.getString(
			contact, actionRequest, "skypeSn");
		String smsSn = BeanParamUtil.getString(contact, actionRequest, "smsSn");
		String twitterSn = BeanParamUtil.getString(
			contact, actionRequest, "twitterSn");

		Calendar cal = CalendarFactoryUtil.getCalendar();

		cal.setTime(user.getBirthday());

		int birthdayDay = cal.get(Calendar.DATE);
		int birthdayMonth = cal.get(Calendar.MONTH);
		int birthdayYear = cal.get(Calendar.YEAR);

		List<AnnouncementsDelivery> announcementsDeliveries =
			announcementsDeliveryLocalService.getUserDeliveries(
				user.getUserId());

		userService.updateUser(
			user.getUserId(), user.getPasswordUnencrypted(),
			user.getPasswordUnencrypted(), user.getPasswordUnencrypted(),
			user.getPasswordReset(), user.getReminderQueryQuestion(),
			user.getReminderQueryAnswer(), screenName, emailAddress,
			user.getFacebookId(), user.getOpenId(), true, null,
			user.getLanguageId(), user.getTimeZoneId(), user.getGreeting(),
			comments, firstName, middleName, lastName, contact.getPrefixId(),
			contact.getSuffixId(), user.isMale(), birthdayMonth, birthdayDay,
			birthdayYear, smsSn, facebookSn, jabberSn, skypeSn, twitterSn,
			jobTitle, user.getGroupIds(), user.getOrganizationIds(),
			user.getRoleIds(), null, user.getUserGroupIds(),
			user.getAddresses(), null, user.getPhones(), user.getWebsites(),
			announcementsDeliveries, new ServiceContext());
	}

	protected void updateWebsites(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		List<Website> websites = UsersAdminUtil.getWebsites(actionRequest);

		UsersAdminUtil.updateWebsites(
			Contact.class.getName(), user.getContactId(), websites);
	}

	@Reference
	protected AnnouncementsDeliveryLocalService
		announcementsDeliveryLocalService;

	@Reference
	protected EntryLocalService entryLocalService;

	@Reference
	protected RoleLocalService roleLocalService;

	@Reference
	protected SocialRelationLocalService socialRelationLocalService;

	@Reference
	protected SocialRequestLocalService socialRequestLocalService;

	@Reference
	protected UserLocalService userLocalService;

	@Reference
	protected UserNotificationEventLocalService
		userNotificationEventLocalService;

	@Reference
	protected UserService userService;

}