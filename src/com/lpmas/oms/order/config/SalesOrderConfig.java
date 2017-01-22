package com.lpmas.oms.order.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class SalesOrderConfig {
	// 购买方式purchase type
	public final static int PURT_NORMAL = 1;// 正常
	public final static int PURT_GIFT = 2;// 赠品
	public final static int PURT_SCORE = 3;// 积分

	public static List<StatusBean<Integer, String>> PURCHASE_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
	public static Map<Integer, String> PURCHASE_TYPE_MAP = new HashMap<Integer, String>();

	// 订单类型
	public final static int ORDT_NORMAL = 1;// 正常
	public final static int ORDT_PRESELL = 2;// 预售
	public final static int ORDT_PREPAY = 3;// 配送
	public final static int ORDT_GIFT = 4;// 赠品

	public static List<StatusBean<Integer, String>> ORDER_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
	public static Map<Integer, String> ORDER_TYPE_MAP = new HashMap<Integer, String>();

	static {
		new SalesOrderConfig().init();
	}

	private void init() {
		initPurchaseTypeList();
		initPurchaseTypeMap();

		initOrderTypeList();
		initOrderTypeMap();
	}

	private void initPurchaseTypeList() {
		ORDER_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
		ORDER_TYPE_LIST.add(new StatusBean<Integer, String>(PURT_NORMAL, "正常"));
		ORDER_TYPE_LIST.add(new StatusBean<Integer, String>(PURT_GIFT, "赠品"));
		ORDER_TYPE_LIST.add(new StatusBean<Integer, String>(PURT_SCORE, "积分"));
	}

	private void initPurchaseTypeMap() {
		PURCHASE_TYPE_MAP = StatusKit.toMap(PURCHASE_TYPE_LIST);
	}

	private void initOrderTypeList() {
		ORDER_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
		ORDER_TYPE_LIST.add(new StatusBean<Integer, String>(ORDT_NORMAL, "正常"));
		ORDER_TYPE_LIST.add(new StatusBean<Integer, String>(ORDT_PRESELL, "预售"));
		ORDER_TYPE_LIST.add(new StatusBean<Integer, String>(ORDT_PREPAY, "配送"));
		ORDER_TYPE_LIST.add(new StatusBean<Integer, String>(ORDT_GIFT, "赠品"));
	}

	private void initOrderTypeMap() {
		ORDER_TYPE_MAP = StatusKit.toMap(ORDER_TYPE_LIST);
	}
}
