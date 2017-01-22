package com.lpmas.oms.client.bean.response;

import java.util.List;

public class DeliveryInfoSyncResponseBean {
	private String outerOrderId = "";
	private String soStatus = "";
	private int transporterType = 0;
	private int transporterId = 0;
	private String transportNumber = "";

	private List<DeliveryInfoSyncItemResponseBean> deliveryInfoSyncItemList;

	public String getOuterOrderId() {
		return outerOrderId;
	}

	public void setOuterOrderId(String outerOrderId) {
		this.outerOrderId = outerOrderId;
	}

	public String getSoStatus() {
		return soStatus;
	}

	public void setSoStatus(String soStatus) {
		this.soStatus = soStatus;
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

	public List<DeliveryInfoSyncItemResponseBean> getDeliveryInfoSyncItemList() {
		return deliveryInfoSyncItemList;
	}

	public void setDeliveryInfoSyncItemList(List<DeliveryInfoSyncItemResponseBean> deliveryInfoSyncItemList) {
		this.deliveryInfoSyncItemList = deliveryInfoSyncItemList;
	}
}
