package com.lpmas.oms.order.config;

import java.util.HashMap;
import java.util.Map;

import com.lpmas.oms.config.OmsConfig;

public class OrderConfig {
	public final static String PAGE_PATH = OmsConfig.PAGE_PATH + "order/";
	
	//临时sku转换
	public static Map<String,String> skuMap = new HashMap<String,String>();
	
	static{
		new OrderConfig().initSkuMap();
	}
	
	private void initSkuMap(){
		skuMap.put("11607131800501", "8111PN10");
	}
}
