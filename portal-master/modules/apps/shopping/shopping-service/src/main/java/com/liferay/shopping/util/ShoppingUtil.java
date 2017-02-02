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

package com.liferay.shopping.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MathUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.shopping.configuration.ShoppingGroupServiceOverriddenConfiguration;
import com.liferay.shopping.constants.ShoppingConstants;
import com.liferay.shopping.exception.NoSuchCartException;
import com.liferay.shopping.model.ShoppingCart;
import com.liferay.shopping.model.ShoppingCartItem;
import com.liferay.shopping.model.ShoppingCategory;
import com.liferay.shopping.model.ShoppingCoupon;
import com.liferay.shopping.model.ShoppingCouponConstants;
import com.liferay.shopping.model.ShoppingItem;
import com.liferay.shopping.model.ShoppingItemField;
import com.liferay.shopping.model.ShoppingItemPrice;
import com.liferay.shopping.model.ShoppingItemPriceConstants;
import com.liferay.shopping.model.ShoppingOrder;
import com.liferay.shopping.model.ShoppingOrderConstants;
import com.liferay.shopping.model.ShoppingOrderItem;
import com.liferay.shopping.model.impl.ShoppingCartImpl;
import com.liferay.shopping.service.ShoppingCartLocalServiceUtil;
import com.liferay.shopping.service.ShoppingCategoryLocalServiceUtil;
import com.liferay.shopping.service.ShoppingOrderItemLocalServiceUtil;
import com.liferay.shopping.service.persistence.ShoppingItemPriceUtil;
import com.liferay.shopping.util.comparator.ItemMinQuantityComparator;
import com.liferay.shopping.util.comparator.ItemNameComparator;
import com.liferay.shopping.util.comparator.ItemPriceComparator;
import com.liferay.shopping.util.comparator.ItemSKUComparator;

import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Eduardo Garcia
 */
public class ShoppingUtil {

	public static double calculateActualPrice(ShoppingItem item) {
		return item.getPrice() - calculateDiscountPrice(item);
	}

	public static double calculateActualPrice(ShoppingItem item, int count)
		throws PortalException {

		return calculatePrice(item, count) -
			calculateDiscountPrice(item, count);
	}

	public static double calculateActualPrice(ShoppingItemPrice itemPrice) {
		return itemPrice.getPrice() - calculateDiscountPrice(itemPrice);
	}

	public static double calculateActualSubtotal(
		List<ShoppingOrderItem> orderItems) {

		double subtotal = 0.0;

		for (ShoppingOrderItem orderItem : orderItems) {
			subtotal += orderItem.getPrice() * orderItem.getQuantity();
		}

		return subtotal;
	}

	public static double calculateActualSubtotal(
			Map<ShoppingCartItem, Integer> items)
		throws PortalException {

		return calculateSubtotal(items) - calculateDiscountSubtotal(items);
	}

	public static double calculateAlternativeShipping(
			Map<ShoppingCartItem, Integer> items, int altShipping)
		throws PortalException {

		double shipping = calculateShipping(items);
		double alternativeShipping = shipping;

		ShoppingGroupServiceOverriddenConfiguration
			shoppingGroupServiceOverriddenConfiguration = null;

		for (Map.Entry<ShoppingCartItem, Integer> entry : items.entrySet()) {
			ShoppingCartItem cartItem = entry.getKey();

			ShoppingItem item = cartItem.getItem();

			if (shoppingGroupServiceOverriddenConfiguration == null) {
				ShoppingCategory category = item.getCategory();

				shoppingGroupServiceOverriddenConfiguration =
					_getShoppingGroupServiceOverriddenConfiguration(
						category.getGroupId());

				break;
			}
		}

		// Calculate alternative shipping if shopping is configured to use
		// alternative shipping and shipping price is greater than 0

		if ((shoppingGroupServiceOverriddenConfiguration != null) &&
			shoppingGroupServiceOverriddenConfiguration.
				useAlternativeShipping() &&
			(shipping > 0)) {

			double altShippingDelta = 0.0;

			try {
				altShippingDelta = GetterUtil.getDouble(
					shoppingGroupServiceOverriddenConfiguration.
						getAlternativeShipping()[1][altShipping]);
			}
			catch (Exception e) {
				return alternativeShipping;
			}

			if (altShippingDelta > 0) {
				alternativeShipping = shipping * altShippingDelta;
			}
		}

		return alternativeShipping;
	}

	public static double calculateCouponDiscount(
			Map<ShoppingCartItem, Integer> items, ShoppingCoupon coupon)
		throws PortalException {

		return calculateCouponDiscount(items, null, coupon);
	}

	public static double calculateCouponDiscount(
			Map<ShoppingCartItem, Integer> items, String stateId,
			ShoppingCoupon coupon)
		throws PortalException {

		double discount = 0.0;

		if ((coupon == null) || !coupon.isActive() ||
			!coupon.hasValidDateRange()) {

			return discount;
		}

		String[] categoryNames = StringUtil.split(coupon.getLimitCategories());

		Set<Long> categoryIds = new HashSet<>();

		for (String categoryName : categoryNames) {
			ShoppingCategory category =
				ShoppingCategoryLocalServiceUtil.getCategory(
					coupon.getGroupId(), categoryName);

			List<Long> subcategoryIds = new ArrayList<>();

			ShoppingCategoryLocalServiceUtil.getSubcategoryIds(
				subcategoryIds, category.getGroupId(),
				category.getCategoryId());

			categoryIds.add(category.getCategoryId());
			categoryIds.addAll(subcategoryIds);
		}

		String[] skus = StringUtil.split(coupon.getLimitSkus());

		if ((categoryIds.size() > 0) || (skus.length > 0)) {
			Set<String> skusSet = new HashSet<>();

			for (String sku : skus) {
				skusSet.add(sku);
			}

			Map<ShoppingCartItem, Integer> newItems = new HashMap<>();

			for (Map.Entry<ShoppingCartItem, Integer> entry :
					items.entrySet()) {

				ShoppingCartItem cartItem = entry.getKey();
				Integer count = entry.getValue();

				ShoppingItem item = cartItem.getItem();

				if ((!categoryIds.isEmpty() &&
					 categoryIds.contains(item.getCategoryId())) ||
					(!skusSet.isEmpty() && skusSet.contains(item.getSku()))) {

					newItems.put(cartItem, count);
				}
			}

			items = newItems;
		}

		double actualSubtotal = calculateActualSubtotal(items);

		if ((coupon.getMinOrder() > 0) &&
			(coupon.getMinOrder() > actualSubtotal)) {

			return discount;
		}

		String type = coupon.getDiscountType();

		if (type.equals(ShoppingCouponConstants.DISCOUNT_TYPE_PERCENTAGE)) {
			discount = actualSubtotal * coupon.getDiscount();
		}
		else if (type.equals(ShoppingCouponConstants.DISCOUNT_TYPE_ACTUAL)) {
			discount = coupon.getDiscount();
		}
		else if (type.equals(
					ShoppingCouponConstants.DISCOUNT_TYPE_FREE_SHIPPING)) {

			discount = calculateShipping(items);
		}
		else if (type.equals(ShoppingCouponConstants.DISCOUNT_TYPE_TAX_FREE)) {
			if (stateId != null) {
				discount = calculateTax(items, stateId);
			}
		}

		return discount;
	}

	public static double calculateDiscountPercent(
			Map<ShoppingCartItem, Integer> items)
		throws PortalException {

		double discount = calculateDiscountSubtotal(
			items) / calculateSubtotal(items);

		if (Double.isNaN(discount) || Double.isInfinite(discount)) {
			discount = 0.0;
		}

		return discount;
	}

	public static double calculateDiscountPrice(ShoppingItem item) {
		return item.getPrice() * item.getDiscount();
	}

	public static double calculateDiscountPrice(ShoppingItem item, int count)
		throws PortalException {

		ShoppingItemPrice itemPrice = _getItemPrice(item, count);

		return itemPrice.getPrice() * itemPrice.getDiscount() * count;
	}

	public static double calculateDiscountPrice(ShoppingItemPrice itemPrice) {
		return itemPrice.getPrice() * itemPrice.getDiscount();
	}

	public static double calculateDiscountSubtotal(
			Map<ShoppingCartItem, Integer> items)
		throws PortalException {

		double subtotal = 0.0;

		for (Map.Entry<ShoppingCartItem, Integer> entry : items.entrySet()) {
			ShoppingCartItem cartItem = entry.getKey();
			Integer count = entry.getValue();

			ShoppingItem item = cartItem.getItem();

			subtotal += calculateDiscountPrice(item, count.intValue());
		}

		return subtotal;
	}

	public static double calculateInsurance(
			Map<ShoppingCartItem, Integer> items)
		throws PortalException {

		double insurance = 0.0;
		double subtotal = 0.0;

		ShoppingGroupServiceOverriddenConfiguration
			shoppingGroupServiceOverriddenConfiguration = null;

		for (Map.Entry<ShoppingCartItem, Integer> entry : items.entrySet()) {
			ShoppingCartItem cartItem = entry.getKey();
			Integer count = entry.getValue();

			ShoppingItem item = cartItem.getItem();

			if (shoppingGroupServiceOverriddenConfiguration == null) {
				ShoppingCategory category = item.getCategory();

				shoppingGroupServiceOverriddenConfiguration =
					_getShoppingGroupServiceOverriddenConfiguration(
						category.getGroupId());
			}

			ShoppingItemPrice itemPrice = _getItemPrice(item, count.intValue());

			subtotal += calculateActualPrice(itemPrice) * count.intValue();
		}

		if ((shoppingGroupServiceOverriddenConfiguration == null) ||
			(subtotal == 0)) {

			return insurance;
		}

		double insuranceRate = 0.0;

		double[] range =
			ShoppingGroupServiceOverriddenConfiguration.INSURANCE_RANGE;

		for (int i = 0; i < range.length - 1; i++) {
			if ((subtotal > range[i]) && (subtotal <= range[i + 1])) {
				int rangeId = i / 2;

				if (MathUtil.isOdd(i)) {
					rangeId = (i + 1) / 2;
				}

				String[] insurances =
					shoppingGroupServiceOverriddenConfiguration.getInsurance();

				if (insurances.length < rangeId) {
					continue;
				}

				insuranceRate = GetterUtil.getDouble(insurances[rangeId]);
			}
		}

		String formula =
			shoppingGroupServiceOverriddenConfiguration.getInsuranceFormula();

		if (formula.equals("flat")) {
			insurance += insuranceRate;
		}
		else if (formula.equals("percentage")) {
			insurance += subtotal * insuranceRate;
		}

		return insurance;
	}

	public static double calculatePrice(ShoppingItem item, int count)
		throws PortalException {

		ShoppingItemPrice itemPrice = _getItemPrice(item, count);

		return itemPrice.getPrice() * count;
	}

	public static double calculateShipping(Map<ShoppingCartItem, Integer> items)
		throws PortalException {

		double shipping = 0.0;
		double subtotal = 0.0;

		ShoppingGroupServiceOverriddenConfiguration
			shoppingGroupServiceOverriddenConfiguration = null;

		for (Map.Entry<ShoppingCartItem, Integer> entry : items.entrySet()) {
			ShoppingCartItem cartItem = entry.getKey();
			Integer count = entry.getValue();

			ShoppingItem item = cartItem.getItem();

			if (shoppingGroupServiceOverriddenConfiguration == null) {
				ShoppingCategory category = item.getCategory();

				shoppingGroupServiceOverriddenConfiguration =
					_getShoppingGroupServiceOverriddenConfiguration(
						category.getGroupId());
			}

			if (item.isRequiresShipping()) {
				ShoppingItemPrice itemPrice = _getItemPrice(
					item, count.intValue());

				if (itemPrice.isUseShippingFormula()) {
					subtotal +=
						calculateActualPrice(itemPrice) * count.intValue();
				}
				else {
					shipping += itemPrice.getShipping() * count.intValue();
				}
			}
		}

		if ((shoppingGroupServiceOverriddenConfiguration == null) ||
			(subtotal == 0)) {

			return shipping;
		}

		double shippingRate = 0.0;

		double[] range =
			ShoppingGroupServiceOverriddenConfiguration.SHIPPING_RANGE;

		for (int i = 0; i < range.length - 1; i++) {
			if ((subtotal > range[i]) && (subtotal <= range[i + 1])) {
				int rangeId = i / 2;

				if (MathUtil.isOdd(i)) {
					rangeId = (i + 1) / 2;
				}

				shippingRate = GetterUtil.getDouble(
					shoppingGroupServiceOverriddenConfiguration.
						getShipping()[rangeId]);
			}
		}

		String formula =
			shoppingGroupServiceOverriddenConfiguration.getShippingFormula();

		if (formula.equals("flat")) {
			shipping += shippingRate;
		}
		else if (formula.equals("percentage")) {
			shipping += subtotal * shippingRate;
		}

		return shipping;
	}

	public static double calculateSubtotal(Map<ShoppingCartItem, Integer> items)
		throws PortalException {

		double subtotal = 0.0;

		for (Map.Entry<ShoppingCartItem, Integer> entry : items.entrySet()) {
			ShoppingCartItem cartItem = entry.getKey();
			Integer count = entry.getValue();

			ShoppingItem item = cartItem.getItem();

			subtotal += calculatePrice(item, count.intValue());
		}

		return subtotal;
	}

	public static double calculateTax(
			Map<ShoppingCartItem, Integer> items, String stateId)
		throws PortalException {

		double tax = 0.0;

		ShoppingGroupServiceOverriddenConfiguration
			shoppingGroupServiceOverriddenConfiguration = null;

		for (Map.Entry<ShoppingCartItem, Integer> entry : items.entrySet()) {
			ShoppingCartItem cartItem = entry.getKey();

			ShoppingItem item = cartItem.getItem();

			if (shoppingGroupServiceOverriddenConfiguration == null) {
				ShoppingCategory category = item.getCategory();

				shoppingGroupServiceOverriddenConfiguration =
					_getShoppingGroupServiceOverriddenConfiguration(
						category.getGroupId());

				break;
			}
		}

		if (shoppingGroupServiceOverriddenConfiguration == null) {
			return tax;
		}

		String taxState =
			shoppingGroupServiceOverriddenConfiguration.getTaxState();

		if (!taxState.equals(stateId)) {
			return tax;
		}

		double subtotal = 0.0;

		for (Map.Entry<ShoppingCartItem, Integer> entry : items.entrySet()) {
			ShoppingCartItem cartItem = entry.getKey();
			Integer count = entry.getValue();

			ShoppingItem item = cartItem.getItem();

			if (item.isTaxable()) {
				subtotal += calculatePrice(item, count.intValue());
			}
		}

		return shoppingGroupServiceOverriddenConfiguration.getTaxRate() *
			subtotal;
	}

	public static double calculateTotal(
			Map<ShoppingCartItem, Integer> items, String stateId,
			ShoppingCoupon coupon, int altShipping, boolean insure)
		throws PortalException {

		double actualSubtotal = calculateActualSubtotal(items);
		double tax = calculateTax(items, stateId);
		double shipping = calculateAlternativeShipping(items, altShipping);

		double insurance = 0.0;

		if (insure) {
			insurance = calculateInsurance(items);
		}

		double couponDiscount = calculateCouponDiscount(items, stateId, coupon);

		double total =
			actualSubtotal + tax + shipping + insurance - couponDiscount;

		if (total < 0) {
			total = 0.0;
		}

		return total;
	}

	public static double calculateTotal(ShoppingOrder order) {
		List<ShoppingOrderItem> orderItems =
			ShoppingOrderItemLocalServiceUtil.getOrderItems(order.getOrderId());

		double total =
			calculateActualSubtotal(orderItems) + order.getTax() +
				order.getShipping() + order.getInsurance() -
					order.getCouponDiscount();

		if (total < 0) {
			total = 0.0;
		}

		return total;
	}

	public static String getBreadcrumbs(
			long categoryId, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		ShoppingCategory category = null;

		try {
			category = ShoppingCategoryLocalServiceUtil.getCategory(categoryId);
		}
		catch (Exception e) {
		}

		return getBreadcrumbs(category, renderRequest, renderResponse);
	}

	public static String getBreadcrumbs(
			ShoppingCategory category, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		PortletURL categoriesURL = renderResponse.createRenderURL();

		WindowState windowState = renderRequest.getWindowState();

		if (windowState.equals(LiferayWindowState.POP_UP)) {
			categoriesURL.setParameter(
				"struts_action", "/shopping/select_category");
			categoriesURL.setWindowState(LiferayWindowState.POP_UP);
		}
		else {
			categoriesURL.setParameter("struts_action", "/shopping/view");
			categoriesURL.setParameter("tabs1", "categories");
			//categoriesURL.setWindowState(WindowState.MAXIMIZED);
		}

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			renderRequest);

		String categoriesLink =
			"<a href=\"" + categoriesURL.toString() + "\">" +
				LanguageUtil.get(request, "categories") + "</a>";

		if (category == null) {
			return "<span class=\"first last\">" + categoriesLink + "</span>";
		}

		String breadcrumbs = StringPool.BLANK;

		if (category != null) {
			for (int i = 0;; i++) {
				category = category.toEscapedModel();

				PortletURL portletURL = renderResponse.createRenderURL();

				if (windowState.equals(LiferayWindowState.POP_UP)) {
					portletURL.setParameter(
						"struts_action", "/shopping/select_category");
					portletURL.setParameter(
						"categoryId", String.valueOf(category.getCategoryId()));
					portletURL.setWindowState(LiferayWindowState.POP_UP);
				}
				else {
					portletURL.setParameter("struts_action", "/shopping/view");
					portletURL.setParameter("tabs1", "categories");
					portletURL.setParameter(
						"categoryId", String.valueOf(category.getCategoryId()));
					//portletURL.setWindowState(WindowState.MAXIMIZED);
				}

				String categoryLink =
					"<a href=\"" + portletURL.toString() + "\">" +
						category.getName() + "</a>";

				if (i == 0) {
					breadcrumbs =
						"<span class=\"last\">" + categoryLink + "</span>";
				}
				else {
					breadcrumbs = categoryLink + " &raquo; " + breadcrumbs;
				}

				if (category.isRoot()) {
					break;
				}

				category = ShoppingCategoryLocalServiceUtil.getCategory(
					category.getParentCategoryId());
			}
		}

		breadcrumbs =
			"<span class=\"first\">" + categoriesLink + " &raquo; </span>" +
				breadcrumbs;

		return breadcrumbs;
	}

	public static ShoppingCart getCart(PortletRequest portletRequest)
		throws PortalException {

		PortletSession portletSession = portletRequest.getPortletSession();

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String sessionCartId =
			ShoppingCart.class.getName() + themeDisplay.getScopeGroupId();

		if (themeDisplay.isSignedIn()) {
			ShoppingCart cart = (ShoppingCart)portletSession.getAttribute(
				sessionCartId);

			if (cart != null) {
				portletSession.removeAttribute(sessionCartId);
			}

			if ((cart != null) && (cart.getItemsSize() > 0)) {
				cart = ShoppingCartLocalServiceUtil.updateCart(
					themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
					cart.getItemIds(), cart.getCouponCodes(),
					cart.getAltShipping(), cart.isInsure());
			}
			else {
				try {
					cart = ShoppingCartLocalServiceUtil.getCart(
						themeDisplay.getUserId(),
						themeDisplay.getScopeGroupId());
				}
				catch (NoSuchCartException nsce) {
					cart = getCart(themeDisplay);

					cart = ShoppingCartLocalServiceUtil.updateCart(
						themeDisplay.getUserId(),
						themeDisplay.getScopeGroupId(), cart.getItemIds(),
						cart.getCouponCodes(), cart.getAltShipping(),
						cart.isInsure());
				}
			}

			return cart;
		}

		ShoppingCart cart = (ShoppingCart)portletSession.getAttribute(
			sessionCartId);

		if (cart == null) {
			cart = getCart(themeDisplay);

			portletSession.setAttribute(sessionCartId, cart);
		}

		return cart;
	}

	public static ShoppingCart getCart(ThemeDisplay themeDisplay) {
		ShoppingCart cart = new ShoppingCartImpl();

		cart.setGroupId(themeDisplay.getScopeGroupId());
		cart.setCompanyId(themeDisplay.getCompanyId());
		cart.setUserId(themeDisplay.getUserId());
		cart.setItemIds(StringPool.BLANK);
		cart.setCouponCodes(StringPool.BLANK);
		cart.setAltShipping(0);
		cart.setInsure(false);

		return cart;
	}

	public static Map<String, String> getEmailDefinitionTerms(
		PortletRequest portletRequest, String emailFromAddress,
		String emailFromName) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Map<String, String> definitionTerms = new LinkedHashMap<>();

		definitionTerms.put(
			"[$FROM_ADDRESS$]", HtmlUtil.escape(emailFromAddress));
		definitionTerms.put("[$FROM_NAME$]", HtmlUtil.escape(emailFromName));
		definitionTerms.put(
			"[$ORDER_BILLING_ADDRESS$]",
			LanguageUtil.get(
				themeDisplay.getLocale(), "the-order-billing-address"));
		definitionTerms.put(
			"[$ORDER_CURRENCY$]",
			LanguageUtil.get(themeDisplay.getLocale(), "the-order-currency"));
		definitionTerms.put(
			"[$ORDER_NUMBER$]",
			LanguageUtil.get(themeDisplay.getLocale(), "the-order-id"));
		definitionTerms.put(
			"[$ORDER_SHIPPING_ADDRESS$]",
			LanguageUtil.get(
				themeDisplay.getLocale(), "the-order-shipping-address"));
		definitionTerms.put(
			"[$ORDER_TOTAL$]",
			LanguageUtil.get(themeDisplay.getLocale(), "the-order-total"));

		Company company = themeDisplay.getCompany();

		definitionTerms.put("[$PORTAL_URL$]", company.getVirtualHostname());

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		definitionTerms.put(
			"[$PORTLET_NAME$]", HtmlUtil.escape(portletDisplay.getTitle()));

		definitionTerms.put(
			"[$TO_ADDRESS$]",
			LanguageUtil.get(
				themeDisplay.getLocale(),
				"the-address-of-the-email-recipient"));
		definitionTerms.put(
			"[$TO_NAME$]",
			LanguageUtil.get(
				themeDisplay.getLocale(), "the-name-of-the-email-recipient"));

		return definitionTerms;
	}

	public static int getFieldsQuantitiesPos(
		ShoppingItem item, ShoppingItemField[] itemFields,
		String[] fieldsArray) {

		Set<String> fieldsValues = new HashSet<>();

		for (String fields : fieldsArray) {
			int pos = fields.indexOf("=");

			String fieldValue = fields.substring(pos + 1, fields.length());

			fieldsValues.add(fieldValue.trim());
		}

		List<String> names = new ArrayList<>();
		List<String[]> values = new ArrayList<>();

		for (int i = 0; i < itemFields.length; i++) {
			names.add(itemFields[i].getName());
			values.add(StringUtil.split(itemFields[i].getValues()));
		}

		int numOfRows = 1;

		for (String[] vArray : values) {
			numOfRows = numOfRows * vArray.length;
		}

		int rowPos = 0;

		for (int i = 0; i < numOfRows; i++) {
			boolean match = true;

			for (int j = 0; j < names.size(); j++) {
				int numOfRepeats = 1;

				for (int k = j + 1; k < values.size(); k++) {
					String[] vArray = values.get(k);

					numOfRepeats = numOfRepeats * vArray.length;
				}

				String[] vArray = values.get(j);

				int arrayPos = 0;

				for (arrayPos = i / numOfRepeats; arrayPos >= vArray.length;
					arrayPos = arrayPos - vArray.length) {
				}

				if (!fieldsValues.contains(vArray[arrayPos].trim())) {
					match = false;

					break;
				}
			}

			if (match) {
				rowPos = i;

				break;
			}
		}

		return rowPos;
	}

	public static String getItemFields(String itemId) {
		int pos = itemId.indexOf(CharPool.PIPE);

		if (pos == -1) {
			return StringPool.BLANK;
		}
		else {
			return itemId.substring(pos + 1);
		}
	}

	public static long getItemId(String itemId) {
		int pos = itemId.indexOf(CharPool.PIPE);

		if (pos != -1) {
			itemId = itemId.substring(0, pos);
		}

		return GetterUtil.getLong(itemId);
	}

	public static OrderByComparator<ShoppingItem> getItemOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<ShoppingItem> orderByComparator = null;

		if (orderByCol.equals("min-qty")) {
			orderByComparator = new ItemMinQuantityComparator(orderByAsc);
		}
		else if (orderByCol.equals("name")) {
			orderByComparator = new ItemNameComparator(orderByAsc);
		}
		else if (orderByCol.equals("price")) {
			orderByComparator = new ItemPriceComparator(orderByAsc);
		}
		else if (orderByCol.equals("sku")) {
			orderByComparator = new ItemSKUComparator(orderByAsc);
		}

		return orderByComparator;
	}

	public static int getMinQuantity(ShoppingItem item) throws PortalException {
		int minQuantity = item.getMinQuantity();

		List<ShoppingItemPrice> itemPrices = item.getItemPrices();

		for (ShoppingItemPrice itemPrice : itemPrices) {
			if (minQuantity > itemPrice.getMinQuantity()) {
				minQuantity = itemPrice.getMinQuantity();
			}
		}

		return minQuantity;
	}

	public static String getPayPalNotifyURL(ThemeDisplay themeDisplay) {
		return themeDisplay.getPortalURL() + themeDisplay.getPathMain() +
			"/shopping/notify";
	}

	public static String getPayPalRedirectURL(
		ShoppingGroupServiceOverriddenConfiguration
			shoppingGroupServiceOverriddenConfiguration,
		ShoppingOrder order, double total, String returnURL, String notifyURL) {

		String payPalEmailAddress = HttpUtil.encodeURL(
			shoppingGroupServiceOverriddenConfiguration.
				getPayPalEmailAddress());

		NumberFormat doubleFormat = NumberFormat.getNumberInstance(
			LocaleUtil.ENGLISH);

		doubleFormat.setMaximumFractionDigits(2);
		doubleFormat.setMinimumFractionDigits(2);

		String amount = doubleFormat.format(total);

		returnURL = HttpUtil.encodeURL(returnURL);
		notifyURL = HttpUtil.encodeURL(notifyURL);

		String firstName = HttpUtil.encodeURL(order.getBillingFirstName());
		String lastName = HttpUtil.encodeURL(order.getBillingLastName());
		String address1 = HttpUtil.encodeURL(order.getBillingStreet());
		String city = HttpUtil.encodeURL(order.getBillingCity());
		String state = HttpUtil.encodeURL(order.getBillingState());
		String zip = HttpUtil.encodeURL(order.getBillingZip());

		String currencyCode =
			shoppingGroupServiceOverriddenConfiguration.getCurrencyId();

		StringBundler sb = new StringBundler(29);

		sb.append("https://www.paypal.com/cgi-bin/webscr?cmd=_xclick&");
		sb.append("business=");
		sb.append(payPalEmailAddress);
		sb.append("&item_name=");
		sb.append(order.getNumber());
		sb.append("&item_number=");
		sb.append(order.getNumber());
		sb.append("&invoice=");
		sb.append(order.getNumber());
		sb.append("&amount=");
		sb.append(amount);
		sb.append("&return=");
		sb.append(returnURL);
		sb.append("&notify_url=");
		sb.append(notifyURL);
		sb.append("&first_name=");
		sb.append(firstName);
		sb.append("&last_name=");
		sb.append(lastName);
		sb.append("&address1=");
		sb.append(address1);
		sb.append("&city=");
		sb.append(city);
		sb.append("&state=");
		sb.append(state);
		sb.append("&zip=");
		sb.append(zip);
		sb.append("&no_note=1&currency_code=");
		sb.append(currencyCode);

		return sb.toString();
	}

	public static String getPayPalReturnURL(
		PortletURL portletURL, ShoppingOrder order) {

		portletURL.setParameter("struts_action", "/shopping/checkout");
		portletURL.setParameter(Constants.CMD, Constants.VIEW);
		portletURL.setParameter("orderId", String.valueOf(order.getOrderId()));

		return portletURL.toString();
	}

	public static String getPpPaymentStatus(
		ShoppingOrder order, HttpServletRequest request) {

		String ppPaymentStatus = order.getPpPaymentStatus();

		if (ppPaymentStatus.equals(ShoppingOrderConstants.STATUS_CHECKOUT)) {
			ppPaymentStatus = "checkout";
		}
		else {
			ppPaymentStatus = StringUtil.toLowerCase(ppPaymentStatus);
		}

		return LanguageUtil.get(request, HtmlUtil.escape(ppPaymentStatus));
	}

	public static String getPpPaymentStatus(String ppPaymentStatus) {
		if ((ppPaymentStatus == null) || (ppPaymentStatus.length() < 2) ||
			ppPaymentStatus.equals("checkout")) {

			return ShoppingOrderConstants.STATUS_CHECKOUT;
		}
		else {
			return Character.toUpperCase(ppPaymentStatus.charAt(0)) +
				ppPaymentStatus.substring(1);
		}
	}

	public static boolean isInStock(ShoppingItem item) {
		if (item.isInfiniteStock()) {
			return true;
		}

		if (!item.isFields()) {
			if (item.getStockQuantity() > 0) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			String[] fieldsQuantities = item.getFieldsQuantitiesArray();

			for (int i = 0; i < fieldsQuantities.length; i++) {
				if (GetterUtil.getInteger(fieldsQuantities[i]) > 0) {
					return true;
				}
			}

			return false;
		}
	}

	public static boolean isInStock(
		ShoppingItem item, ShoppingItemField[] itemFields, String[] fieldsArray,
		Integer orderedQuantity) {

		if (item.isInfiniteStock()) {
			return true;
		}

		if (!item.isFields()) {
			int stockQuantity = item.getStockQuantity();

			if ((stockQuantity > 0) &&
				(stockQuantity >= orderedQuantity.intValue())) {

				return true;
			}
			else {
				return false;
			}
		}
		else {
			String[] fieldsQuantities = item.getFieldsQuantitiesArray();

			int stockQuantity = 0;

			if (fieldsQuantities.length > 0) {
				int rowPos = getFieldsQuantitiesPos(
					item, itemFields, fieldsArray);

				stockQuantity = GetterUtil.getInteger(fieldsQuantities[rowPos]);
			}

			try {
				if ((stockQuantity > 0) &&
					(stockQuantity >= orderedQuantity.intValue())) {

					return true;
				}
			}
			catch (Exception e) {
			}

			return false;
		}
	}

	public static boolean meetsMinOrder(
			ShoppingGroupServiceOverriddenConfiguration
				shoppingGroupServiceOverriddenConfiguration,
			Map<ShoppingCartItem, Integer> items)
		throws PortalException {

		if ((shoppingGroupServiceOverriddenConfiguration.getMinOrder() > 0) &&
			(calculateSubtotal(items) <
				shoppingGroupServiceOverriddenConfiguration.getMinOrder())) {

			return false;
		}
		else {
			return true;
		}
	}

	private static ShoppingItemPrice _getItemPrice(ShoppingItem item, int count)
		throws PortalException {

		ShoppingItemPrice itemPrice = null;

		List<ShoppingItemPrice> itemPrices = item.getItemPrices();

		for (ShoppingItemPrice temp : itemPrices) {
			int minQty = temp.getMinQuantity();
			int maxQty = temp.getMaxQuantity();

			if (temp.getStatus() !=
					ShoppingItemPriceConstants.STATUS_INACTIVE) {

				if ((count >= minQty) && ((count <= maxQty) || (maxQty == 0))) {
					return temp;
				}

				if ((count > maxQty) &&
					((itemPrice == null) ||
					 (itemPrice.getMaxQuantity() < maxQty))) {

					itemPrice = temp;
				}
			}
		}

		if (itemPrice == null) {
			return ShoppingItemPriceUtil.create(0);
		}

		return itemPrice;
	}

	private static ShoppingGroupServiceOverriddenConfiguration
			_getShoppingGroupServiceOverriddenConfiguration(long groupId)
		throws ConfigurationException {

		return ConfigurationProviderUtil.getConfiguration(
			ShoppingGroupServiceOverriddenConfiguration.class,
			new GroupServiceSettingsLocator(
				groupId, ShoppingConstants.SERVICE_NAME));
	}

}