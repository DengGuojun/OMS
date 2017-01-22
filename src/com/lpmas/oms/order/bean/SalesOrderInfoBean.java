package com.lpmas.oms.order.bean;

import java.sql.Timestamp;

public class SalesOrderInfoBean {
	private int soId = 0;
	private int costomerId = 0;
	private int storeId = 0;
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
	private String invoiceNumber = "";
	private String invoiceType = "";
	private String invoiceTitle = "";
	private double invoiceAmount = 0;
	private int deliveryMethod = 0;
	private int transporterType = 0;
	private int transporterId = 0;
	private String transportNumber = "";
	private String currency = "";
	private double productAmount = 0;
	private double productDiscountAmount = 0;
	private double productFactAmount = 0;
	private double freight = 0;
	private double discountFreight = 0;
	private double factFreight = 0;
	private double soAmount = 0;
	private double soDiscountAmount = 0;
	private double soFactAmount = 0;
	private double totalQuantity = 0;
	private int soType = 0;
	private Timestamp payTime = null;
	private int shopId = 0;
	private int clerkId = 0;
	private String orderFrom = "";
	private String tradeSource = "";
	private String tradeSourceId1 = "";
	private String tradeSourceId2 = "";
	private String userComment = "";
	private String soStatus = "";
	private String syncStatus = "";
	private int status = 0;
	private Timestamp createTime = null;
	private int createUser = 0;
	private Timestamp modifyTime = null;
	private int modifyUser = 0;
	private String memo = "";

	public int getSoId() {
		return soId;
	}

	public void setSoId(int soId) {
		this.soId = soId;
	}

	public int getCostomerId() {
		return costomerId;
	}

	public void setCostomerId(int costomerId) {
		this.costomerId = costomerId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
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

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public double getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public int getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(int deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(double productAmount) {
		this.productAmount = productAmount;
	}

	public double getProductDiscountAmount() {
		return productDiscountAmount;
	}

	public void setProductDiscountAmount(double productDiscountAmount) {
		this.productDiscountAmount = productDiscountAmount;
	}

	public double getProductFactAmount() {
		return productFactAmount;
	}

	public void setProductFactAmount(double productFactAmount) {
		this.productFactAmount = productFactAmount;
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

	public double getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public int getSoType() {
		return soType;
	}

	public void setSoType(int soType) {
		this.soType = soType;
	}

	public Timestamp getPayTime() {
		return payTime;
	}

	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public int getClerkId() {
		return clerkId;
	}

	public void setClerkId(int clerkId) {
		this.clerkId = clerkId;
	}

	public String getOrderFrom() {
		return orderFrom;
	}

	public void setOrderFrom(String orderFrom) {
		this.orderFrom = orderFrom;
	}

	public String getTradeSource() {
		return tradeSource;
	}

	public void setTradeSource(String tradeSource) {
		this.tradeSource = tradeSource;
	}

	public String getTradeSourceId1() {
		return tradeSourceId1;
	}

	public void setTradeSourceId1(String tradeSourceId1) {
		this.tradeSourceId1 = tradeSourceId1;
	}

	public String getTradeSourceId2() {
		return tradeSourceId2;
	}

	public void setTradeSourceId2(String tradeSourceId2) {
		this.tradeSourceId2 = tradeSourceId2;
	}

	public String getUserComment() {
		return userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

	public String getSoStatus() {
		return soStatus;
	}

	public void setSoStatus(String soStatus) {
		this.soStatus = soStatus;
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