package com.lpmas.oms.order.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.oms.order.bean.SalesOrderItemBean;
import com.lpmas.oms.order.dao.SalesOrderItemDao;

public class SalesOrderItemBusiness {
	public int addSalesOrderItem(SalesOrderItemBean bean) {
		SalesOrderItemDao dao = new SalesOrderItemDao();
		return dao.insertSalesOrderItem(bean);
	}

	public int updateSalesOrderItem(SalesOrderItemBean bean) {
		SalesOrderItemDao dao = new SalesOrderItemDao();
		return dao.updateSalesOrderItem(bean);
	}

	public SalesOrderItemBean getSalesOrderItemByKey(int soItemId) {
		SalesOrderItemDao dao = new SalesOrderItemDao();
		return dao.getSalesOrderItemByKey(soItemId);
	}
	
	public List<SalesOrderItemBean> getSalesOrderItemListByKey(int soId) {
		SalesOrderItemDao dao = new SalesOrderItemDao();
		return dao.getSalesOrderItemListByKey(soId);
	}

	public PageResultBean<SalesOrderItemBean> getSalesOrderItemPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		SalesOrderItemDao dao = new SalesOrderItemDao();
		return dao.getSalesOrderItemPageListByMap(condMap, pageBean);
	}

}