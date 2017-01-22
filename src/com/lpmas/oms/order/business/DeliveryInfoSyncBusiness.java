package com.lpmas.oms.order.business;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.mq.MQSender;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.oms.client.bean.response.DeliveryInfoSyncItemResponseBean;
import com.lpmas.oms.client.bean.response.DeliveryInfoSyncResponseBean;
import com.lpmas.oms.factory.OmsMQFactory;
import com.lpmas.oms.order.bean.SalesOrderInfoBean;
import com.lpmas.oms.order.bean.SalesOrderItemBean;

public class DeliveryInfoSyncBusiness {
	private static Logger log = LoggerFactory.getLogger(DeliveryInfoSyncBusiness.class);

	public boolean sentDeliveryInfo(SalesOrderInfoBean soBean, List<SalesOrderItemBean> soItemList) {
		boolean result = false;

		DeliveryInfoSyncResponseBean bean = new DeliveryInfoSyncResponseBean();
		bean.setOuterOrderId(soBean.getOuterOrderId());
		bean.setSoStatus(soBean.getSoStatus());
		bean.setTransporterType(soBean.getTransporterType());
		bean.setTransporterId(soBean.getTransporterId());
		bean.setTransportNumber(soBean.getTransportNumber());

		List<DeliveryInfoSyncItemResponseBean> itemList = new ArrayList<DeliveryInfoSyncItemResponseBean>();
		for (SalesOrderItemBean soItemBean : soItemList) {
			DeliveryInfoSyncItemResponseBean itemBean = new DeliveryInfoSyncItemResponseBean();
			itemBean.setOuterOrderItemId(soItemBean.getOuterOrderItemId());
			itemBean.setProductItemNumber(soItemBean.getProductItemNumber());
			itemBean.setSoItemStatus(soItemBean.getSoItemStatus());
			itemBean.setTransporterType(soItemBean.getTransporterType());
			itemBean.setTransporterId(soItemBean.getTransporterId());
			itemBean.setTransportNumber(soItemBean.getTransportNumber());

			itemList.add(itemBean);
		}

		bean.setDeliveryInfoSyncItemList(itemList);

		try {
			MQSender sender = new OmsMQFactory().getMQSender();
			SalesOrderMQRouter router = new SalesOrderMQRouter();
			String queueName = router.getDeliveryInfoQueueName(soBean.getStoreId());
			sender.send(queueName, JsonKit.toJson(bean));

			result = true;
		} catch (Exception e) {
			log.error("sentDeliveryInfo error:", e);
		}

		return result;
	}
}
