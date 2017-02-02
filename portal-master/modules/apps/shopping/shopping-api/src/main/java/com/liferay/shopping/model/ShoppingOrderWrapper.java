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

package com.liferay.shopping.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ShoppingOrder}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingOrder
 * @generated
 */
@ProviderType
public class ShoppingOrderWrapper implements ShoppingOrder,
	ModelWrapper<ShoppingOrder> {
	public ShoppingOrderWrapper(ShoppingOrder shoppingOrder) {
		_shoppingOrder = shoppingOrder;
	}

	@Override
	public Class<?> getModelClass() {
		return ShoppingOrder.class;
	}

	@Override
	public String getModelClassName() {
		return ShoppingOrder.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("orderId", getOrderId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("number", getNumber());
		attributes.put("tax", getTax());
		attributes.put("shipping", getShipping());
		attributes.put("altShipping", getAltShipping());
		attributes.put("requiresShipping", getRequiresShipping());
		attributes.put("insure", getInsure());
		attributes.put("insurance", getInsurance());
		attributes.put("couponCodes", getCouponCodes());
		attributes.put("couponDiscount", getCouponDiscount());
		attributes.put("billingFirstName", getBillingFirstName());
		attributes.put("billingLastName", getBillingLastName());
		attributes.put("billingEmailAddress", getBillingEmailAddress());
		attributes.put("billingCompany", getBillingCompany());
		attributes.put("billingStreet", getBillingStreet());
		attributes.put("billingCity", getBillingCity());
		attributes.put("billingState", getBillingState());
		attributes.put("billingZip", getBillingZip());
		attributes.put("billingCountry", getBillingCountry());
		attributes.put("billingPhone", getBillingPhone());
		attributes.put("shipToBilling", getShipToBilling());
		attributes.put("shippingFirstName", getShippingFirstName());
		attributes.put("shippingLastName", getShippingLastName());
		attributes.put("shippingEmailAddress", getShippingEmailAddress());
		attributes.put("shippingCompany", getShippingCompany());
		attributes.put("shippingStreet", getShippingStreet());
		attributes.put("shippingCity", getShippingCity());
		attributes.put("shippingState", getShippingState());
		attributes.put("shippingZip", getShippingZip());
		attributes.put("shippingCountry", getShippingCountry());
		attributes.put("shippingPhone", getShippingPhone());
		attributes.put("ccName", getCcName());
		attributes.put("ccType", getCcType());
		attributes.put("ccNumber", getCcNumber());
		attributes.put("ccExpMonth", getCcExpMonth());
		attributes.put("ccExpYear", getCcExpYear());
		attributes.put("ccVerNumber", getCcVerNumber());
		attributes.put("comments", getComments());
		attributes.put("ppTxnId", getPpTxnId());
		attributes.put("ppPaymentStatus", getPpPaymentStatus());
		attributes.put("ppPaymentGross", getPpPaymentGross());
		attributes.put("ppReceiverEmail", getPpReceiverEmail());
		attributes.put("ppPayerEmail", getPpPayerEmail());
		attributes.put("sendOrderEmail", getSendOrderEmail());
		attributes.put("sendShippingEmail", getSendShippingEmail());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long orderId = (Long)attributes.get("orderId");

		if (orderId != null) {
			setOrderId(orderId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String number = (String)attributes.get("number");

		if (number != null) {
			setNumber(number);
		}

		Double tax = (Double)attributes.get("tax");

		if (tax != null) {
			setTax(tax);
		}

		Double shipping = (Double)attributes.get("shipping");

		if (shipping != null) {
			setShipping(shipping);
		}

		String altShipping = (String)attributes.get("altShipping");

		if (altShipping != null) {
			setAltShipping(altShipping);
		}

		Boolean requiresShipping = (Boolean)attributes.get("requiresShipping");

		if (requiresShipping != null) {
			setRequiresShipping(requiresShipping);
		}

		Boolean insure = (Boolean)attributes.get("insure");

		if (insure != null) {
			setInsure(insure);
		}

		Double insurance = (Double)attributes.get("insurance");

		if (insurance != null) {
			setInsurance(insurance);
		}

		String couponCodes = (String)attributes.get("couponCodes");

		if (couponCodes != null) {
			setCouponCodes(couponCodes);
		}

		Double couponDiscount = (Double)attributes.get("couponDiscount");

		if (couponDiscount != null) {
			setCouponDiscount(couponDiscount);
		}

		String billingFirstName = (String)attributes.get("billingFirstName");

		if (billingFirstName != null) {
			setBillingFirstName(billingFirstName);
		}

		String billingLastName = (String)attributes.get("billingLastName");

		if (billingLastName != null) {
			setBillingLastName(billingLastName);
		}

		String billingEmailAddress = (String)attributes.get(
				"billingEmailAddress");

		if (billingEmailAddress != null) {
			setBillingEmailAddress(billingEmailAddress);
		}

		String billingCompany = (String)attributes.get("billingCompany");

		if (billingCompany != null) {
			setBillingCompany(billingCompany);
		}

		String billingStreet = (String)attributes.get("billingStreet");

		if (billingStreet != null) {
			setBillingStreet(billingStreet);
		}

		String billingCity = (String)attributes.get("billingCity");

		if (billingCity != null) {
			setBillingCity(billingCity);
		}

		String billingState = (String)attributes.get("billingState");

		if (billingState != null) {
			setBillingState(billingState);
		}

		String billingZip = (String)attributes.get("billingZip");

		if (billingZip != null) {
			setBillingZip(billingZip);
		}

		String billingCountry = (String)attributes.get("billingCountry");

		if (billingCountry != null) {
			setBillingCountry(billingCountry);
		}

		String billingPhone = (String)attributes.get("billingPhone");

		if (billingPhone != null) {
			setBillingPhone(billingPhone);
		}

		Boolean shipToBilling = (Boolean)attributes.get("shipToBilling");

		if (shipToBilling != null) {
			setShipToBilling(shipToBilling);
		}

		String shippingFirstName = (String)attributes.get("shippingFirstName");

		if (shippingFirstName != null) {
			setShippingFirstName(shippingFirstName);
		}

		String shippingLastName = (String)attributes.get("shippingLastName");

		if (shippingLastName != null) {
			setShippingLastName(shippingLastName);
		}

		String shippingEmailAddress = (String)attributes.get(
				"shippingEmailAddress");

		if (shippingEmailAddress != null) {
			setShippingEmailAddress(shippingEmailAddress);
		}

		String shippingCompany = (String)attributes.get("shippingCompany");

		if (shippingCompany != null) {
			setShippingCompany(shippingCompany);
		}

		String shippingStreet = (String)attributes.get("shippingStreet");

		if (shippingStreet != null) {
			setShippingStreet(shippingStreet);
		}

		String shippingCity = (String)attributes.get("shippingCity");

		if (shippingCity != null) {
			setShippingCity(shippingCity);
		}

		String shippingState = (String)attributes.get("shippingState");

		if (shippingState != null) {
			setShippingState(shippingState);
		}

		String shippingZip = (String)attributes.get("shippingZip");

		if (shippingZip != null) {
			setShippingZip(shippingZip);
		}

		String shippingCountry = (String)attributes.get("shippingCountry");

		if (shippingCountry != null) {
			setShippingCountry(shippingCountry);
		}

		String shippingPhone = (String)attributes.get("shippingPhone");

		if (shippingPhone != null) {
			setShippingPhone(shippingPhone);
		}

		String ccName = (String)attributes.get("ccName");

		if (ccName != null) {
			setCcName(ccName);
		}

		String ccType = (String)attributes.get("ccType");

		if (ccType != null) {
			setCcType(ccType);
		}

		String ccNumber = (String)attributes.get("ccNumber");

		if (ccNumber != null) {
			setCcNumber(ccNumber);
		}

		Integer ccExpMonth = (Integer)attributes.get("ccExpMonth");

		if (ccExpMonth != null) {
			setCcExpMonth(ccExpMonth);
		}

		Integer ccExpYear = (Integer)attributes.get("ccExpYear");

		if (ccExpYear != null) {
			setCcExpYear(ccExpYear);
		}

		String ccVerNumber = (String)attributes.get("ccVerNumber");

		if (ccVerNumber != null) {
			setCcVerNumber(ccVerNumber);
		}

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}

		String ppTxnId = (String)attributes.get("ppTxnId");

		if (ppTxnId != null) {
			setPpTxnId(ppTxnId);
		}

		String ppPaymentStatus = (String)attributes.get("ppPaymentStatus");

		if (ppPaymentStatus != null) {
			setPpPaymentStatus(ppPaymentStatus);
		}

		Double ppPaymentGross = (Double)attributes.get("ppPaymentGross");

		if (ppPaymentGross != null) {
			setPpPaymentGross(ppPaymentGross);
		}

		String ppReceiverEmail = (String)attributes.get("ppReceiverEmail");

		if (ppReceiverEmail != null) {
			setPpReceiverEmail(ppReceiverEmail);
		}

		String ppPayerEmail = (String)attributes.get("ppPayerEmail");

		if (ppPayerEmail != null) {
			setPpPayerEmail(ppPayerEmail);
		}

		Boolean sendOrderEmail = (Boolean)attributes.get("sendOrderEmail");

		if (sendOrderEmail != null) {
			setSendOrderEmail(sendOrderEmail);
		}

		Boolean sendShippingEmail = (Boolean)attributes.get("sendShippingEmail");

		if (sendShippingEmail != null) {
			setSendShippingEmail(sendShippingEmail);
		}
	}

	@Override
	public ShoppingOrder toEscapedModel() {
		return new ShoppingOrderWrapper(_shoppingOrder.toEscapedModel());
	}

	@Override
	public ShoppingOrder toUnescapedModel() {
		return new ShoppingOrderWrapper(_shoppingOrder.toUnescapedModel());
	}

	/**
	* Returns the insure of this shopping order.
	*
	* @return the insure of this shopping order
	*/
	@Override
	public boolean getInsure() {
		return _shoppingOrder.getInsure();
	}

	/**
	* Returns the requires shipping of this shopping order.
	*
	* @return the requires shipping of this shopping order
	*/
	@Override
	public boolean getRequiresShipping() {
		return _shoppingOrder.getRequiresShipping();
	}

	/**
	* Returns the send order email of this shopping order.
	*
	* @return the send order email of this shopping order
	*/
	@Override
	public boolean getSendOrderEmail() {
		return _shoppingOrder.getSendOrderEmail();
	}

	/**
	* Returns the send shipping email of this shopping order.
	*
	* @return the send shipping email of this shopping order
	*/
	@Override
	public boolean getSendShippingEmail() {
		return _shoppingOrder.getSendShippingEmail();
	}

	/**
	* Returns the ship to billing of this shopping order.
	*
	* @return the ship to billing of this shopping order
	*/
	@Override
	public boolean getShipToBilling() {
		return _shoppingOrder.getShipToBilling();
	}

	@Override
	public boolean isCachedModel() {
		return _shoppingOrder.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _shoppingOrder.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this shopping order is insure.
	*
	* @return <code>true</code> if this shopping order is insure; <code>false</code> otherwise
	*/
	@Override
	public boolean isInsure() {
		return _shoppingOrder.isInsure();
	}

	@Override
	public boolean isNew() {
		return _shoppingOrder.isNew();
	}

	/**
	* Returns <code>true</code> if this shopping order is requires shipping.
	*
	* @return <code>true</code> if this shopping order is requires shipping; <code>false</code> otherwise
	*/
	@Override
	public boolean isRequiresShipping() {
		return _shoppingOrder.isRequiresShipping();
	}

	/**
	* Returns <code>true</code> if this shopping order is send order email.
	*
	* @return <code>true</code> if this shopping order is send order email; <code>false</code> otherwise
	*/
	@Override
	public boolean isSendOrderEmail() {
		return _shoppingOrder.isSendOrderEmail();
	}

	/**
	* Returns <code>true</code> if this shopping order is send shipping email.
	*
	* @return <code>true</code> if this shopping order is send shipping email; <code>false</code> otherwise
	*/
	@Override
	public boolean isSendShippingEmail() {
		return _shoppingOrder.isSendShippingEmail();
	}

	/**
	* Returns <code>true</code> if this shopping order is ship to billing.
	*
	* @return <code>true</code> if this shopping order is ship to billing; <code>false</code> otherwise
	*/
	@Override
	public boolean isShipToBilling() {
		return _shoppingOrder.isShipToBilling();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _shoppingOrder.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ShoppingOrder> toCacheModel() {
		return _shoppingOrder.toCacheModel();
	}

	/**
	* Returns the coupon discount of this shopping order.
	*
	* @return the coupon discount of this shopping order
	*/
	@Override
	public double getCouponDiscount() {
		return _shoppingOrder.getCouponDiscount();
	}

	/**
	* Returns the insurance of this shopping order.
	*
	* @return the insurance of this shopping order
	*/
	@Override
	public double getInsurance() {
		return _shoppingOrder.getInsurance();
	}

	/**
	* Returns the pp payment gross of this shopping order.
	*
	* @return the pp payment gross of this shopping order
	*/
	@Override
	public double getPpPaymentGross() {
		return _shoppingOrder.getPpPaymentGross();
	}

	/**
	* Returns the shipping of this shopping order.
	*
	* @return the shipping of this shopping order
	*/
	@Override
	public double getShipping() {
		return _shoppingOrder.getShipping();
	}

	/**
	* Returns the tax of this shopping order.
	*
	* @return the tax of this shopping order
	*/
	@Override
	public double getTax() {
		return _shoppingOrder.getTax();
	}

	@Override
	public int compareTo(ShoppingOrder shoppingOrder) {
		return _shoppingOrder.compareTo(shoppingOrder);
	}

	/**
	* Returns the cc exp month of this shopping order.
	*
	* @return the cc exp month of this shopping order
	*/
	@Override
	public int getCcExpMonth() {
		return _shoppingOrder.getCcExpMonth();
	}

	/**
	* Returns the cc exp year of this shopping order.
	*
	* @return the cc exp year of this shopping order
	*/
	@Override
	public int getCcExpYear() {
		return _shoppingOrder.getCcExpYear();
	}

	@Override
	public int hashCode() {
		return _shoppingOrder.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _shoppingOrder.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ShoppingOrderWrapper((ShoppingOrder)_shoppingOrder.clone());
	}

	/**
	* Returns the alt shipping of this shopping order.
	*
	* @return the alt shipping of this shopping order
	*/
	@Override
	public java.lang.String getAltShipping() {
		return _shoppingOrder.getAltShipping();
	}

	/**
	* Returns the billing city of this shopping order.
	*
	* @return the billing city of this shopping order
	*/
	@Override
	public java.lang.String getBillingCity() {
		return _shoppingOrder.getBillingCity();
	}

	/**
	* Returns the billing company of this shopping order.
	*
	* @return the billing company of this shopping order
	*/
	@Override
	public java.lang.String getBillingCompany() {
		return _shoppingOrder.getBillingCompany();
	}

	/**
	* Returns the billing country of this shopping order.
	*
	* @return the billing country of this shopping order
	*/
	@Override
	public java.lang.String getBillingCountry() {
		return _shoppingOrder.getBillingCountry();
	}

	/**
	* Returns the billing email address of this shopping order.
	*
	* @return the billing email address of this shopping order
	*/
	@Override
	public java.lang.String getBillingEmailAddress() {
		return _shoppingOrder.getBillingEmailAddress();
	}

	/**
	* Returns the billing first name of this shopping order.
	*
	* @return the billing first name of this shopping order
	*/
	@Override
	public java.lang.String getBillingFirstName() {
		return _shoppingOrder.getBillingFirstName();
	}

	/**
	* Returns the billing last name of this shopping order.
	*
	* @return the billing last name of this shopping order
	*/
	@Override
	public java.lang.String getBillingLastName() {
		return _shoppingOrder.getBillingLastName();
	}

	/**
	* Returns the billing phone of this shopping order.
	*
	* @return the billing phone of this shopping order
	*/
	@Override
	public java.lang.String getBillingPhone() {
		return _shoppingOrder.getBillingPhone();
	}

	/**
	* Returns the billing state of this shopping order.
	*
	* @return the billing state of this shopping order
	*/
	@Override
	public java.lang.String getBillingState() {
		return _shoppingOrder.getBillingState();
	}

	/**
	* Returns the billing street of this shopping order.
	*
	* @return the billing street of this shopping order
	*/
	@Override
	public java.lang.String getBillingStreet() {
		return _shoppingOrder.getBillingStreet();
	}

	/**
	* Returns the billing zip of this shopping order.
	*
	* @return the billing zip of this shopping order
	*/
	@Override
	public java.lang.String getBillingZip() {
		return _shoppingOrder.getBillingZip();
	}

	/**
	* Returns the cc name of this shopping order.
	*
	* @return the cc name of this shopping order
	*/
	@Override
	public java.lang.String getCcName() {
		return _shoppingOrder.getCcName();
	}

	/**
	* Returns the cc number of this shopping order.
	*
	* @return the cc number of this shopping order
	*/
	@Override
	public java.lang.String getCcNumber() {
		return _shoppingOrder.getCcNumber();
	}

	/**
	* Returns the cc type of this shopping order.
	*
	* @return the cc type of this shopping order
	*/
	@Override
	public java.lang.String getCcType() {
		return _shoppingOrder.getCcType();
	}

	/**
	* Returns the cc ver number of this shopping order.
	*
	* @return the cc ver number of this shopping order
	*/
	@Override
	public java.lang.String getCcVerNumber() {
		return _shoppingOrder.getCcVerNumber();
	}

	/**
	* Returns the comments of this shopping order.
	*
	* @return the comments of this shopping order
	*/
	@Override
	public java.lang.String getComments() {
		return _shoppingOrder.getComments();
	}

	/**
	* Returns the coupon codes of this shopping order.
	*
	* @return the coupon codes of this shopping order
	*/
	@Override
	public java.lang.String getCouponCodes() {
		return _shoppingOrder.getCouponCodes();
	}

	/**
	* Returns the number of this shopping order.
	*
	* @return the number of this shopping order
	*/
	@Override
	public java.lang.String getNumber() {
		return _shoppingOrder.getNumber();
	}

	/**
	* Returns the pp payer email of this shopping order.
	*
	* @return the pp payer email of this shopping order
	*/
	@Override
	public java.lang.String getPpPayerEmail() {
		return _shoppingOrder.getPpPayerEmail();
	}

	/**
	* Returns the pp payment status of this shopping order.
	*
	* @return the pp payment status of this shopping order
	*/
	@Override
	public java.lang.String getPpPaymentStatus() {
		return _shoppingOrder.getPpPaymentStatus();
	}

	/**
	* Returns the pp receiver email of this shopping order.
	*
	* @return the pp receiver email of this shopping order
	*/
	@Override
	public java.lang.String getPpReceiverEmail() {
		return _shoppingOrder.getPpReceiverEmail();
	}

	/**
	* Returns the pp txn ID of this shopping order.
	*
	* @return the pp txn ID of this shopping order
	*/
	@Override
	public java.lang.String getPpTxnId() {
		return _shoppingOrder.getPpTxnId();
	}

	/**
	* Returns the shipping city of this shopping order.
	*
	* @return the shipping city of this shopping order
	*/
	@Override
	public java.lang.String getShippingCity() {
		return _shoppingOrder.getShippingCity();
	}

	/**
	* Returns the shipping company of this shopping order.
	*
	* @return the shipping company of this shopping order
	*/
	@Override
	public java.lang.String getShippingCompany() {
		return _shoppingOrder.getShippingCompany();
	}

	/**
	* Returns the shipping country of this shopping order.
	*
	* @return the shipping country of this shopping order
	*/
	@Override
	public java.lang.String getShippingCountry() {
		return _shoppingOrder.getShippingCountry();
	}

	/**
	* Returns the shipping email address of this shopping order.
	*
	* @return the shipping email address of this shopping order
	*/
	@Override
	public java.lang.String getShippingEmailAddress() {
		return _shoppingOrder.getShippingEmailAddress();
	}

	/**
	* Returns the shipping first name of this shopping order.
	*
	* @return the shipping first name of this shopping order
	*/
	@Override
	public java.lang.String getShippingFirstName() {
		return _shoppingOrder.getShippingFirstName();
	}

	/**
	* Returns the shipping last name of this shopping order.
	*
	* @return the shipping last name of this shopping order
	*/
	@Override
	public java.lang.String getShippingLastName() {
		return _shoppingOrder.getShippingLastName();
	}

	/**
	* Returns the shipping phone of this shopping order.
	*
	* @return the shipping phone of this shopping order
	*/
	@Override
	public java.lang.String getShippingPhone() {
		return _shoppingOrder.getShippingPhone();
	}

	/**
	* Returns the shipping state of this shopping order.
	*
	* @return the shipping state of this shopping order
	*/
	@Override
	public java.lang.String getShippingState() {
		return _shoppingOrder.getShippingState();
	}

	/**
	* Returns the shipping street of this shopping order.
	*
	* @return the shipping street of this shopping order
	*/
	@Override
	public java.lang.String getShippingStreet() {
		return _shoppingOrder.getShippingStreet();
	}

	/**
	* Returns the shipping zip of this shopping order.
	*
	* @return the shipping zip of this shopping order
	*/
	@Override
	public java.lang.String getShippingZip() {
		return _shoppingOrder.getShippingZip();
	}

	/**
	* Returns the user name of this shopping order.
	*
	* @return the user name of this shopping order
	*/
	@Override
	public java.lang.String getUserName() {
		return _shoppingOrder.getUserName();
	}

	/**
	* Returns the user uuid of this shopping order.
	*
	* @return the user uuid of this shopping order
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _shoppingOrder.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _shoppingOrder.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _shoppingOrder.toXmlString();
	}

	/**
	* Returns the create date of this shopping order.
	*
	* @return the create date of this shopping order
	*/
	@Override
	public Date getCreateDate() {
		return _shoppingOrder.getCreateDate();
	}

	/**
	* Returns the modified date of this shopping order.
	*
	* @return the modified date of this shopping order
	*/
	@Override
	public Date getModifiedDate() {
		return _shoppingOrder.getModifiedDate();
	}

	/**
	* Returns the company ID of this shopping order.
	*
	* @return the company ID of this shopping order
	*/
	@Override
	public long getCompanyId() {
		return _shoppingOrder.getCompanyId();
	}

	/**
	* Returns the group ID of this shopping order.
	*
	* @return the group ID of this shopping order
	*/
	@Override
	public long getGroupId() {
		return _shoppingOrder.getGroupId();
	}

	/**
	* Returns the order ID of this shopping order.
	*
	* @return the order ID of this shopping order
	*/
	@Override
	public long getOrderId() {
		return _shoppingOrder.getOrderId();
	}

	/**
	* Returns the primary key of this shopping order.
	*
	* @return the primary key of this shopping order
	*/
	@Override
	public long getPrimaryKey() {
		return _shoppingOrder.getPrimaryKey();
	}

	/**
	* Returns the user ID of this shopping order.
	*
	* @return the user ID of this shopping order
	*/
	@Override
	public long getUserId() {
		return _shoppingOrder.getUserId();
	}

	@Override
	public void persist() {
		_shoppingOrder.persist();
	}

	/**
	* Sets the alt shipping of this shopping order.
	*
	* @param altShipping the alt shipping of this shopping order
	*/
	@Override
	public void setAltShipping(java.lang.String altShipping) {
		_shoppingOrder.setAltShipping(altShipping);
	}

	/**
	* Sets the billing city of this shopping order.
	*
	* @param billingCity the billing city of this shopping order
	*/
	@Override
	public void setBillingCity(java.lang.String billingCity) {
		_shoppingOrder.setBillingCity(billingCity);
	}

	/**
	* Sets the billing company of this shopping order.
	*
	* @param billingCompany the billing company of this shopping order
	*/
	@Override
	public void setBillingCompany(java.lang.String billingCompany) {
		_shoppingOrder.setBillingCompany(billingCompany);
	}

	/**
	* Sets the billing country of this shopping order.
	*
	* @param billingCountry the billing country of this shopping order
	*/
	@Override
	public void setBillingCountry(java.lang.String billingCountry) {
		_shoppingOrder.setBillingCountry(billingCountry);
	}

	/**
	* Sets the billing email address of this shopping order.
	*
	* @param billingEmailAddress the billing email address of this shopping order
	*/
	@Override
	public void setBillingEmailAddress(java.lang.String billingEmailAddress) {
		_shoppingOrder.setBillingEmailAddress(billingEmailAddress);
	}

	/**
	* Sets the billing first name of this shopping order.
	*
	* @param billingFirstName the billing first name of this shopping order
	*/
	@Override
	public void setBillingFirstName(java.lang.String billingFirstName) {
		_shoppingOrder.setBillingFirstName(billingFirstName);
	}

	/**
	* Sets the billing last name of this shopping order.
	*
	* @param billingLastName the billing last name of this shopping order
	*/
	@Override
	public void setBillingLastName(java.lang.String billingLastName) {
		_shoppingOrder.setBillingLastName(billingLastName);
	}

	/**
	* Sets the billing phone of this shopping order.
	*
	* @param billingPhone the billing phone of this shopping order
	*/
	@Override
	public void setBillingPhone(java.lang.String billingPhone) {
		_shoppingOrder.setBillingPhone(billingPhone);
	}

	/**
	* Sets the billing state of this shopping order.
	*
	* @param billingState the billing state of this shopping order
	*/
	@Override
	public void setBillingState(java.lang.String billingState) {
		_shoppingOrder.setBillingState(billingState);
	}

	/**
	* Sets the billing street of this shopping order.
	*
	* @param billingStreet the billing street of this shopping order
	*/
	@Override
	public void setBillingStreet(java.lang.String billingStreet) {
		_shoppingOrder.setBillingStreet(billingStreet);
	}

	/**
	* Sets the billing zip of this shopping order.
	*
	* @param billingZip the billing zip of this shopping order
	*/
	@Override
	public void setBillingZip(java.lang.String billingZip) {
		_shoppingOrder.setBillingZip(billingZip);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_shoppingOrder.setCachedModel(cachedModel);
	}

	/**
	* Sets the cc exp month of this shopping order.
	*
	* @param ccExpMonth the cc exp month of this shopping order
	*/
	@Override
	public void setCcExpMonth(int ccExpMonth) {
		_shoppingOrder.setCcExpMonth(ccExpMonth);
	}

	/**
	* Sets the cc exp year of this shopping order.
	*
	* @param ccExpYear the cc exp year of this shopping order
	*/
	@Override
	public void setCcExpYear(int ccExpYear) {
		_shoppingOrder.setCcExpYear(ccExpYear);
	}

	/**
	* Sets the cc name of this shopping order.
	*
	* @param ccName the cc name of this shopping order
	*/
	@Override
	public void setCcName(java.lang.String ccName) {
		_shoppingOrder.setCcName(ccName);
	}

	/**
	* Sets the cc number of this shopping order.
	*
	* @param ccNumber the cc number of this shopping order
	*/
	@Override
	public void setCcNumber(java.lang.String ccNumber) {
		_shoppingOrder.setCcNumber(ccNumber);
	}

	/**
	* Sets the cc type of this shopping order.
	*
	* @param ccType the cc type of this shopping order
	*/
	@Override
	public void setCcType(java.lang.String ccType) {
		_shoppingOrder.setCcType(ccType);
	}

	/**
	* Sets the cc ver number of this shopping order.
	*
	* @param ccVerNumber the cc ver number of this shopping order
	*/
	@Override
	public void setCcVerNumber(java.lang.String ccVerNumber) {
		_shoppingOrder.setCcVerNumber(ccVerNumber);
	}

	/**
	* Sets the comments of this shopping order.
	*
	* @param comments the comments of this shopping order
	*/
	@Override
	public void setComments(java.lang.String comments) {
		_shoppingOrder.setComments(comments);
	}

	/**
	* Sets the company ID of this shopping order.
	*
	* @param companyId the company ID of this shopping order
	*/
	@Override
	public void setCompanyId(long companyId) {
		_shoppingOrder.setCompanyId(companyId);
	}

	/**
	* Sets the coupon codes of this shopping order.
	*
	* @param couponCodes the coupon codes of this shopping order
	*/
	@Override
	public void setCouponCodes(java.lang.String couponCodes) {
		_shoppingOrder.setCouponCodes(couponCodes);
	}

	/**
	* Sets the coupon discount of this shopping order.
	*
	* @param couponDiscount the coupon discount of this shopping order
	*/
	@Override
	public void setCouponDiscount(double couponDiscount) {
		_shoppingOrder.setCouponDiscount(couponDiscount);
	}

	/**
	* Sets the create date of this shopping order.
	*
	* @param createDate the create date of this shopping order
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_shoppingOrder.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_shoppingOrder.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_shoppingOrder.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_shoppingOrder.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this shopping order.
	*
	* @param groupId the group ID of this shopping order
	*/
	@Override
	public void setGroupId(long groupId) {
		_shoppingOrder.setGroupId(groupId);
	}

	/**
	* Sets the insurance of this shopping order.
	*
	* @param insurance the insurance of this shopping order
	*/
	@Override
	public void setInsurance(double insurance) {
		_shoppingOrder.setInsurance(insurance);
	}

	/**
	* Sets whether this shopping order is insure.
	*
	* @param insure the insure of this shopping order
	*/
	@Override
	public void setInsure(boolean insure) {
		_shoppingOrder.setInsure(insure);
	}

	/**
	* Sets the modified date of this shopping order.
	*
	* @param modifiedDate the modified date of this shopping order
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_shoppingOrder.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_shoppingOrder.setNew(n);
	}

	/**
	* Sets the number of this shopping order.
	*
	* @param number the number of this shopping order
	*/
	@Override
	public void setNumber(java.lang.String number) {
		_shoppingOrder.setNumber(number);
	}

	/**
	* Sets the order ID of this shopping order.
	*
	* @param orderId the order ID of this shopping order
	*/
	@Override
	public void setOrderId(long orderId) {
		_shoppingOrder.setOrderId(orderId);
	}

	/**
	* Sets the pp payer email of this shopping order.
	*
	* @param ppPayerEmail the pp payer email of this shopping order
	*/
	@Override
	public void setPpPayerEmail(java.lang.String ppPayerEmail) {
		_shoppingOrder.setPpPayerEmail(ppPayerEmail);
	}

	/**
	* Sets the pp payment gross of this shopping order.
	*
	* @param ppPaymentGross the pp payment gross of this shopping order
	*/
	@Override
	public void setPpPaymentGross(double ppPaymentGross) {
		_shoppingOrder.setPpPaymentGross(ppPaymentGross);
	}

	/**
	* Sets the pp payment status of this shopping order.
	*
	* @param ppPaymentStatus the pp payment status of this shopping order
	*/
	@Override
	public void setPpPaymentStatus(java.lang.String ppPaymentStatus) {
		_shoppingOrder.setPpPaymentStatus(ppPaymentStatus);
	}

	/**
	* Sets the pp receiver email of this shopping order.
	*
	* @param ppReceiverEmail the pp receiver email of this shopping order
	*/
	@Override
	public void setPpReceiverEmail(java.lang.String ppReceiverEmail) {
		_shoppingOrder.setPpReceiverEmail(ppReceiverEmail);
	}

	/**
	* Sets the pp txn ID of this shopping order.
	*
	* @param ppTxnId the pp txn ID of this shopping order
	*/
	@Override
	public void setPpTxnId(java.lang.String ppTxnId) {
		_shoppingOrder.setPpTxnId(ppTxnId);
	}

	/**
	* Sets the primary key of this shopping order.
	*
	* @param primaryKey the primary key of this shopping order
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_shoppingOrder.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_shoppingOrder.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this shopping order is requires shipping.
	*
	* @param requiresShipping the requires shipping of this shopping order
	*/
	@Override
	public void setRequiresShipping(boolean requiresShipping) {
		_shoppingOrder.setRequiresShipping(requiresShipping);
	}

	/**
	* Sets whether this shopping order is send order email.
	*
	* @param sendOrderEmail the send order email of this shopping order
	*/
	@Override
	public void setSendOrderEmail(boolean sendOrderEmail) {
		_shoppingOrder.setSendOrderEmail(sendOrderEmail);
	}

	/**
	* Sets whether this shopping order is send shipping email.
	*
	* @param sendShippingEmail the send shipping email of this shopping order
	*/
	@Override
	public void setSendShippingEmail(boolean sendShippingEmail) {
		_shoppingOrder.setSendShippingEmail(sendShippingEmail);
	}

	/**
	* Sets whether this shopping order is ship to billing.
	*
	* @param shipToBilling the ship to billing of this shopping order
	*/
	@Override
	public void setShipToBilling(boolean shipToBilling) {
		_shoppingOrder.setShipToBilling(shipToBilling);
	}

	/**
	* Sets the shipping of this shopping order.
	*
	* @param shipping the shipping of this shopping order
	*/
	@Override
	public void setShipping(double shipping) {
		_shoppingOrder.setShipping(shipping);
	}

	/**
	* Sets the shipping city of this shopping order.
	*
	* @param shippingCity the shipping city of this shopping order
	*/
	@Override
	public void setShippingCity(java.lang.String shippingCity) {
		_shoppingOrder.setShippingCity(shippingCity);
	}

	/**
	* Sets the shipping company of this shopping order.
	*
	* @param shippingCompany the shipping company of this shopping order
	*/
	@Override
	public void setShippingCompany(java.lang.String shippingCompany) {
		_shoppingOrder.setShippingCompany(shippingCompany);
	}

	/**
	* Sets the shipping country of this shopping order.
	*
	* @param shippingCountry the shipping country of this shopping order
	*/
	@Override
	public void setShippingCountry(java.lang.String shippingCountry) {
		_shoppingOrder.setShippingCountry(shippingCountry);
	}

	/**
	* Sets the shipping email address of this shopping order.
	*
	* @param shippingEmailAddress the shipping email address of this shopping order
	*/
	@Override
	public void setShippingEmailAddress(java.lang.String shippingEmailAddress) {
		_shoppingOrder.setShippingEmailAddress(shippingEmailAddress);
	}

	/**
	* Sets the shipping first name of this shopping order.
	*
	* @param shippingFirstName the shipping first name of this shopping order
	*/
	@Override
	public void setShippingFirstName(java.lang.String shippingFirstName) {
		_shoppingOrder.setShippingFirstName(shippingFirstName);
	}

	/**
	* Sets the shipping last name of this shopping order.
	*
	* @param shippingLastName the shipping last name of this shopping order
	*/
	@Override
	public void setShippingLastName(java.lang.String shippingLastName) {
		_shoppingOrder.setShippingLastName(shippingLastName);
	}

	/**
	* Sets the shipping phone of this shopping order.
	*
	* @param shippingPhone the shipping phone of this shopping order
	*/
	@Override
	public void setShippingPhone(java.lang.String shippingPhone) {
		_shoppingOrder.setShippingPhone(shippingPhone);
	}

	/**
	* Sets the shipping state of this shopping order.
	*
	* @param shippingState the shipping state of this shopping order
	*/
	@Override
	public void setShippingState(java.lang.String shippingState) {
		_shoppingOrder.setShippingState(shippingState);
	}

	/**
	* Sets the shipping street of this shopping order.
	*
	* @param shippingStreet the shipping street of this shopping order
	*/
	@Override
	public void setShippingStreet(java.lang.String shippingStreet) {
		_shoppingOrder.setShippingStreet(shippingStreet);
	}

	/**
	* Sets the shipping zip of this shopping order.
	*
	* @param shippingZip the shipping zip of this shopping order
	*/
	@Override
	public void setShippingZip(java.lang.String shippingZip) {
		_shoppingOrder.setShippingZip(shippingZip);
	}

	/**
	* Sets the tax of this shopping order.
	*
	* @param tax the tax of this shopping order
	*/
	@Override
	public void setTax(double tax) {
		_shoppingOrder.setTax(tax);
	}

	/**
	* Sets the user ID of this shopping order.
	*
	* @param userId the user ID of this shopping order
	*/
	@Override
	public void setUserId(long userId) {
		_shoppingOrder.setUserId(userId);
	}

	/**
	* Sets the user name of this shopping order.
	*
	* @param userName the user name of this shopping order
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_shoppingOrder.setUserName(userName);
	}

	/**
	* Sets the user uuid of this shopping order.
	*
	* @param userUuid the user uuid of this shopping order
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_shoppingOrder.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ShoppingOrderWrapper)) {
			return false;
		}

		ShoppingOrderWrapper shoppingOrderWrapper = (ShoppingOrderWrapper)obj;

		if (Objects.equals(_shoppingOrder, shoppingOrderWrapper._shoppingOrder)) {
			return true;
		}

		return false;
	}

	@Override
	public ShoppingOrder getWrappedModel() {
		return _shoppingOrder;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _shoppingOrder.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _shoppingOrder.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_shoppingOrder.resetOriginalValues();
	}

	private final ShoppingOrder _shoppingOrder;
}