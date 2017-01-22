package com.lpmas.oms.client.bean.response;

public class DeliveryInfoSyncItemResponseBean {
	private String outerOrderItemId = "";
	private String productItemNumber = "";
	private String soItemStatus = "";
	private int transporterType = 0;
	private int transporterId = 0;
	private String transportNumber = "";

	public String getOuterOrderItemId() {
		return outerOrderItemId;
	}

	public void setOuterOrderItemId(String outerOrderItemId) {
		this.outerOrderItemId = outerOrderItemId;
	}

	public String getProductItemNumber() {
		return productItemNumber;
	}

	public void setProductItemNumber(String productItemNumber) {
		this.productItemNumber = productItemNumber;
	}

	public String getSoItemStatus() {
		return soItemStatus;
	}

	public void setSoItemStatus(String soItemStatus) {
		this.soItemStatus = soItemStatus;
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
}
