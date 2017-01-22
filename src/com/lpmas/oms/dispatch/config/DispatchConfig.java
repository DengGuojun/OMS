package com.lpmas.oms.dispatch.config;

import com.lpmas.framework.util.PropertiesKit;
import com.lpmas.oms.config.OmsConfig;

public class DispatchConfig {
	public final static String PAGE_PATH = OmsConfig.PAGE_PATH + "dispatch/";

	public static final String ALLOWED_FILE_TYPE = "xls,xlsx";// 设置允许上传的文件类型
	public static final int MAX_SIZE = 5 * 1024 * 1024; // 设置上传文件最大为5M
	// 设置文件保存路径
	public static final String FILE_PATH = PropertiesKit.getBundleProperties(OmsConfig.OMS_PROP_FILE_NAME, "FILE_PATH");
}
