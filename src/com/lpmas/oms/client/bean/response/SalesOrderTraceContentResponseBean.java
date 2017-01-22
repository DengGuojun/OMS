package com.lpmas.oms.client.bean.response;

import java.sql.Timestamp;

public class SalesOrderTraceContentResponseBean {
	private Timestamp operationTime;
	private String operationContent = "";

	public Timestamp getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Timestamp operationTime) {
		this.operationTime = operationTime;
	}

	public String getOperationContent() {
		return operationContent;
	}

	public void setOperationContent(String operationContent) {
		this.operationContent = operationContent;
	}
}
