package com.lpmas.oms.dispatch.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class DispatchOrderStatusConfig {
	// 发货方类型
	public static final String DOS_CREATED = "CREATED";// 创建
	public static final String DOS_ALLOCATED = "ALLOCATED";// 分配
	public static final String DOS_PRINTED = "PRINTED";// 已打单
	public static final String DOS_SENT = "SENT";// 发货
	public static final String DOS_REJECTED = "REJECTED";// 拒绝
	public static final String DOS_WAIT_TIMEOUT = "WAIT_TIMEOUT";// 等待超时
	public static final String DOS_FAILED = "FAILED";// 失败
	public static final String DOS_CANCELLED = "CANCELLED";// 取消
	public static final String DOS_FINISHED = "FINISHED";// 完成
	public static final String DOS_EDITING = "EDITING";// 正在处理

	public static List<StatusBean<String, String>> DISPATCH_ORDER_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
	public static HashMap<String, String> DISPATCH_ORDER_STATUS_MAP = new HashMap<String, String>();

	static {
		new DispatchOrderStatusConfig().init();
	}

	private void init() {
		initDeliveryStatusList();
		initDeliveryStatusMap();
	}

	private void initDeliveryStatusList() {
		DISPATCH_ORDER_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
		DISPATCH_ORDER_STATUS_LIST.add(new StatusBean<String, String>(DOS_CREATED, "创建"));
		DISPATCH_ORDER_STATUS_LIST.add(new StatusBean<String, String>(DOS_ALLOCATED, "分配"));
		DISPATCH_ORDER_STATUS_LIST.add(new StatusBean<String, String>(DOS_PRINTED, "打单"));
		DISPATCH_ORDER_STATUS_LIST.add(new StatusBean<String, String>(DOS_SENT, "发货"));
		DISPATCH_ORDER_STATUS_LIST.add(new StatusBean<String, String>(DOS_REJECTED, "拒绝"));
		DISPATCH_ORDER_STATUS_LIST.add(new StatusBean<String, String>(DOS_WAIT_TIMEOUT, "等待超时"));
		DISPATCH_ORDER_STATUS_LIST.add(new StatusBean<String, String>(DOS_FAILED, "失败"));
		DISPATCH_ORDER_STATUS_LIST.add(new StatusBean<String, String>(DOS_CANCELLED, "取消"));
		DISPATCH_ORDER_STATUS_LIST.add(new StatusBean<String, String>(DOS_FINISHED, "完成"));
	}

	private void initDeliveryStatusMap() {
		DISPATCH_ORDER_STATUS_MAP = StatusKit.toMap(DISPATCH_ORDER_STATUS_LIST);
	}
}
