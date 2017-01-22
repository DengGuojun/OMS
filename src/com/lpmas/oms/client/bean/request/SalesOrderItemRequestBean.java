package com.lpmas.oms.client.bean.request;

public class SalesOrderItemRequestBean {
	private String outerOrderItemId = "";
	private int productId = 0;
	private int productItemId = 0;
	private String productItemNumber = "";
	private String productName = "";
	private int purchaseType = 0;
	private int deliveryMethod = 0;
	private int transporterType = 0;
	private int transporterId = 0;
	private String currency = "";
	private String unit = "";
	private double quantity = 0;
	private double listPrice = 0;
	private double offerPrice = 0;
	private double discountPrice = 0;
	private double factPrice = 0;
	private double freight = 0;
	private double discountFreight = 0;
	private double factFreight = 0;
	private double itemAmount = 0;
	private double itemDiscountAmount = 0;
	private double itemFactAmount = 0;
	private int clerkId = 0;

	public String getOuterOrderItemId() {
		return outerOrderItemId;
	}

	public void setOuterOrderItemId(String outerOrderItemId) {
		this.outerOrderItemId = outerOrderItemId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductItemId() {
		return productItemId;
	}

	public void setProductItemId(int productItemId) {
		this.productItemId = productItemId;
	}

	public String getProductItemNumber() {
		return productItemNumber;
	}

	public void setProductItemNumber(String productItemNumber) {
		this.productItemNumber = productItemNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(int purchaseType) {
		this.purchaseType = purchaseType;
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getListPrice() {
		return listPrice;
	}

	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}

	public double getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(double offerPrice) {
		this.offerPrice = offerPrice;
	}

	public double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public double getFactPrice() {
		return factPrice;
	}

	public void setFactPrice(double factPrice) {
		this.factPrice = factPrice;
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

	public double getItemAmount() {
		return itemAmount;
	}

	public void setItemAmount(double itemAmount) {
		this.itemAmount = itemAmount;
	}

	public double getItemDiscountAmount() {
		return itemDiscountAmount;
	}

	public void setItemDiscountAmount(double itemDiscountAmount) {
		this.itemDiscountAmount = itemDiscountAmount;
	}

	public double getItemFactAmount() {
		return itemFactAmount;
	}

	public void setItemFactAmount(double itemFactAmount) {
		this.itemFactAmount = itemFactAmount;
	}

	public int getClerkId() {
		return clerkId;
	}

	public void setClerkId(int clerkId) {
		this.clerkId = clerkId;
	}
}
