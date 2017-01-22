package com.lpmas.oms.order.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class DeliveryMethodConfig {
	public static final int DM_WAREHOUSE = 1;// 仓库发货

	// 临时hardcode3个仓库
	public static int WAREHOUSE_ZH = 1;// 中浩发货
	public static int WAREHOUSE_GZ = 2;// 广州仓库自发货
	public static int WAREHOUSE_YT = 3;// 圆通发货

	// 二进制位置
	protected static final int DM_POSE_WAREHOUSE = 0;// 仓库发货

	public static List<StatusBean<Integer, String>> DELIVERY_METHOD_LIST = new ArrayList<StatusBean<Integer, String>>();
	public static HashMap<Integer, String> DELIVERY_METHOD_MAP = new HashMap<Integer, String>();
	
	//TODO 临时harcode3个仓库对应的中文名，实际上应该从ERP的Client读取WarehouseInfo信息（需要ERP的Client更新新版本）
	public static List<StatusBean<Integer, String>> DELIVERY_WAREHOUSE_LIST = new ArrayList<StatusBean<Integer, String>>();
	public static HashMap<Integer, String> DELIVERY_WAREHOUSE_MAP = new HashMap<Integer, String>();

	static {
		new DeliveryMethodConfig().init();
	}

	private void init() {
		initDeliveryMethodList();
		initDeliveryMethodMap();
		
		initDeliveryWarehouseList();
		initDeliveryWarehouseMap();
	}

	private void initDeliveryMethodList() {
		DELIVERY_METHOD_LIST = new ArrayList<StatusBean<Integer, String>>();
		DELIVERY_METHOD_LIST.add(new StatusBean<Integer, String>(DM_WAREHOUSE, "仓库发货"));
	}

	private void initDeliveryMethodMap() {
		DELIVERY_METHOD_MAP = StatusKit.toMap(DELIVERY_METHOD_LIST);
	}
	
	private void initDeliveryWarehouseList() {
		DELIVERY_WAREHOUSE_LIST = new ArrayList<StatusBean<Integer, String>>();
		DELIVERY_WAREHOUSE_LIST.add(new StatusBean<Integer, String>(WAREHOUSE_ZH, "北京中浩"));
		DELIVERY_WAREHOUSE_LIST.add(new StatusBean<Integer, String>(WAREHOUSE_GZ, "广州公司"));
		DELIVERY_WAREHOUSE_LIST.add(new StatusBean<Integer, String>(WAREHOUSE_YT, "圆通仓-广州仓"));
	}

	private void initDeliveryWarehouseMap() {
		DELIVERY_WAREHOUSE_MAP = StatusKit.toMap(DELIVERY_WAREHOUSE_LIST);
	}
}
