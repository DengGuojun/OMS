package com.lpmas.oms.component.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.oms.client.bean.response.SalesOrderTraceContentResponseBean;
import com.lpmas.oms.component._OmsServiceDisp;
import com.lpmas.oms.config.OmsClientConfig;
import com.lpmas.oms.order.business.SalesOrderTraceBusiness;

import Ice.Current;

public class OmsServiceImpl extends _OmsServiceDisp {
	private static Logger log = LoggerFactory.getLogger(OmsServiceImpl.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -2137263434877424904L;

	@Override
	public String rpc(String method, String params, Current __current) {
		String result = "";
		if (method.equals(OmsClientConfig.GET_SALES_ORDER_TRACE)) {
			result = getSalesOrderTrace(params);
		}
		return result;
	}

	private String getSalesOrderTrace(String params) {
		log.info("OmsServiceImpl.getSalesOrderTrace [{}]", params);
		HashMap<String, String> paramMap = JsonKit.toBean(params, HashMap.class);
		int storeId = Integer.parseInt(paramMap.get("storeId"));
		String outerOrderId = paramMap.get("outerOrderId");
		SalesOrderTraceBusiness business = new SalesOrderTraceBusiness();
		List<SalesOrderTraceContentResponseBean> list = business.getSalesOrderTrace(storeId, outerOrderId);
		if (list == null) {
			list = new ArrayList<SalesOrderTraceContentResponseBean>();
		}
		return JsonKit.toJson(list);
	}
}
