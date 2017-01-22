package com.lpmas.oms.dispatch.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.oms.dispatch.bean.DispatchOrderItemBean;
import com.lpmas.oms.dispatch.dao.DispatchOrderItemDao;

public class DispatchOrderItemBusiness {
	public int addDispatchOrderItem(DispatchOrderItemBean bean) {
		DispatchOrderItemDao dao = new DispatchOrderItemDao();
		return dao.insertDispatchOrderItem(bean);
	}

	public int updateDispatchOrderItem(DispatchOrderItemBean bean) {
		DispatchOrderItemDao dao = new DispatchOrderItemDao();
		return dao.updateDispatchOrderItem(bean);
	}

	public DispatchOrderItemBean getDispatchOrderItemByKey(int doItemId) {
		DispatchOrderItemDao dao = new DispatchOrderItemDao();
		return dao.getDispatchOrderItemByKey(doItemId);
	}

	public List<DispatchOrderItemBean> getDispatchOrderItemListByKey(int doId) {
		DispatchOrderItemDao dao = new DispatchOrderItemDao();
		return dao.getDispatchOrderItemListByKey(doId);
	}

	public PageResultBean<DispatchOrderItemBean> getDispatchOrderItemPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		DispatchOrderItemDao dao = new DispatchOrderItemDao();
		return dao.getDispatchOrderItemPageListByMap(condMap, pageBean);
	}

}