package com.lpmas.oms.order.business;

import java.util.HashMap;

import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.oms.order.bean.SalesOrderInfoBean;
import com.lpmas.oms.order.dao.SalesOrderInfoDao;

public class SalesOrderInfoBusiness {
	public int addSalesOrderInfo(SalesOrderInfoBean bean) {
		SalesOrderInfoDao dao = new SalesOrderInfoDao();
		return dao.insertSalesOrderInfo(bean);
	}

	public int updateSalesOrderInfo(SalesOrderInfoBean bean) {
		SalesOrderInfoDao dao = new SalesOrderInfoDao();
		return dao.updateSalesOrderInfo(bean);
	}

	public SalesOrderInfoBean getSalesOrderInfoByKey(int soId) {
		SalesOrderInfoDao dao = new SalesOrderInfoDao();
		return dao.getSalesOrderInfoByKey(soId);
	}

	public SalesOrderInfoBean getSalesOrderInfoByKey(int storeId, String outerOrderId) {
		SalesOrderInfoDao dao = new SalesOrderInfoDao();
		return dao.getSalesOrderInfoByKey(storeId, outerOrderId);
	}

	public PageResultBean<SalesOrderInfoBean> getSalesOrderInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		SalesOrderInfoDao dao = new SalesOrderInfoDao();
		return dao.getSalesOrderInfoPageListByMap(condMap, pageBean);
	}

	public boolean isDuplicateOuterOrderId(int storeId, String outerOrderId) {
		boolean result = false;
		if (null != getSalesOrderInfoByKey(storeId, outerOrderId)) {
			result = true;
		}
		return result;
	}
}