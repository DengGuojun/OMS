package com.lpmas.oms.dispatch.bean;

import java.sql.Timestamp;

public class DispatchOrderInfoBean {
	private int doId = 0;
	private int storeId = 0;
	private int soId = 0;
	private String outerOrderId = "";
	private String country = "";
	private String province = "";
	private String city = "";
	private String region = "";
	private String address = "";
	private String receiverName = "";
	private String telephone = "";
	private String mobile = "";
	private String zipCode = "";
	private int deliveryMethod = 0;
	private Timestamp deliveryStartTime = null;
	private Timestamp deliveryEndTime = null;
	private int transporterType = 0;
	private int transporterId = 0;
	private String transportNumber = "";
	private double freight = 0;
	private double discountFreight = 0;
	private double factFreight = 0;
	private double soAmount = 0;
	private double soDiscountAmount = 0;
	private double soFactAmount = 0;
	private String currency = "";
	private double totalQuantity = 0;
	private String userComment = "";
	private int consignerType = 0;
	private int consignerId = 0;
	private String doStatus = "";
	private String syncStatus = "";
	private int status = 0;
	private Timestamp createTime = null;
	private int createUser = 0;
	private Timestamp modifyTime = null;
	private int modifyUser = 0;
	private String memo = "";

	public int getDoId() {
		return doId;
	}

	public void setDoId(int doId) {
		this.doId = doId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getSoId() {
		return soId;
	}

	public void setSoId(int soId) {
		this.soId = soId;
	}

	public String getOuterOrderId() {
		return outerOrderId;
	}

	public void setOuterOrderId(String outerOrderId) {
		this.outerOrderId = outerOrderId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public int getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(int deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public Timestamp getDeliveryStartTime() {
		return deliveryStartTime;
	}

	public void setDeliveryStartTime(Timestamp deliveryStartTime) {
		this.deliveryStartTime = deliveryStartTime;
	}

	public Timestamp getDeliveryEndTime() {
		return deliveryEndTime;
	}

	public void setDeliveryEndTime(Timestamp deliveryEndTime) {
		this.deliveryEndTime = deliveryEndTime;
	}

	public int getTransporterType() {
		return transporterType;
	}

	public void setTransporterType(int transporterType) {
		this.transporterType = transporterType;
	}

	public int getTransporterId() {
		return transporterId;
	}

	public void setTransporterId(int transporterId) {
		this.transporterId = transporterId;
	}

	public String getTransportNumber() {
		return transportNumber;
	}

	public void setTransportNumber(String transportNumber) {
		this.transportNumber = transportNumber;
	}

	public double getFreight() {
		return freight;
	}

	public void setFreight(double freight) {
		this.freight = freight;
	}

	public double getDiscountFreight() {
		return discountFreight;
	}

	public void setDiscountFreight(double discountFreight) {
		this.discountFreight = discountFreight;
	}

	public double getFactFreight() {
		return factFreight;
	}

	public void setFactFreight(double factFreight) {
		this.factFreight = factFreight;
	}

	public double getSoAmount() {
		return soAmount;
	}

	public void setSoAmount(double soAmount) {
		this.soAmount = soAmount;
	}

	public double getSoDiscountAmount() {
		return soDiscountAmount;
	}

	public void setSoDiscountAmount(double soDiscountAmount) {
		this.soDiscountAmount = soDiscountAmount;
	}

	public double getSoFactAmount() {
		return soFactAmount;
	}

	public void setSoFactAmount(double soFactAmount) {
		this.soFactAmount = soFactAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public String getUserComment() {
		return userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

	public int getConsignerType() {
		return consignerType;
	}

	public void setConsignerType(int consignerType) {
		this.consignerType = consignerType;
	}

	public int getConsignerId() {
		return consignerId;
	}

	public void setConsignerId(int consignerId) {
		this.consignerId = consignerId;
	}

	public String getDoStatus() {
		return doStatus;
	}

	public void setDoStatus(String doStatus) {
		this.doStatus = doStatus;
	}

	public String getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(String syncStatus) {
		this.syncStatus = syncStatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getCreateUser() {
		return createUser;
	}

	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public int getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(int modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}