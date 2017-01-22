package com.lpmas.oms.client;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.component.ComponentClient;
import com.lpmas.framework.mq.MQSender;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.oms.client.bean.request.SalesOrderRequestBean;
import com.lpmas.oms.client.bean.response.SalesOrderTraceContentResponseBean;
import com.lpmas.oms.component.OmsServicePrx;
import com.lpmas.oms.config.OmsClientConfig;
import com.lpmas.oms.config.OmsConfig;
import com.lpmas.oms.config.OmsMQConfig;
import com.lpmas.oms.factory.OmsMQFactory;

public class OmsServiceClient {
	private static Logger log = LoggerFactory.getLogger(OmsServiceClient.class);
	
	public void sendSalesOrder(SalesOrderRequestBean bean) throws Exception {
		MQSender sender = new OmsMQFactory().getMQSender();
		String message = JsonKit.toJson(bean);
		sender.send(OmsMQConfig.SALES_ORDER_QUEUE, message);
	}

	public List<SalesOrderTraceContentResponseBean> getDispatchOrderTrace(int storeId, String outerOrderId) {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("storeId", String.valueOf(storeId));
		param.put("outerOrderId", outerOrderId);
		return JsonKit.toList(rpc(OmsClientConfig.GET_SALES_ORDER_TRACE, JsonKit.toJson(param)),
				SalesOrderTraceContentResponseBean.class);
	}

	private String rpc(String method, String params) {
		ComponentClient client = new ComponentClient();
		OmsServicePrx omsService = (OmsServicePrx) client.getProxy(OmsConfig.APP_ID, OmsServicePrx.class);
		String result = omsService.rpc(method, params);
		log.info("result : {}", result);
		return result;
	}
}
