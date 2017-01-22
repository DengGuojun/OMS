package com.lpmas.oms.order.config;

public class SalesOrderCacheConfig {
	public static final String DELIVERY_INFO_QUEUE_NAME = "DELIVERY_INFO_QUEUE_";

	public static String getDeliveryInfoQueueName(int storeId) {
		return DELIVERY_INFO_QUEUE_NAME + storeId;
	}
}