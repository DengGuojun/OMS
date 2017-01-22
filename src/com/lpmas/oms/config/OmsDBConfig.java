package com.lpmas.oms.config;

import com.lpmas.framework.util.PropertiesKit;

public class OmsDBConfig {

	public static String DB_LINK_OMS_W = PropertiesKit.getBundleProperties(OmsConfig.OMS_PROP_FILE_NAME,
			"DB_LINK_OMS_W");

	public static String DB_LINK_OMS_R = PropertiesKit.getBundleProperties(OmsConfig.OMS_PROP_FILE_NAME,
			"DB_LINK_OMS_R");
}
