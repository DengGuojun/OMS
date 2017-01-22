package com.lpmas.oms.dispatch.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.oms.dispatch.bean.DispatchOrderInfoBean;
import com.lpmas.oms.dispatch.dao.DispatchOrderInfoDao;

public class DispatchOrderInfoBusiness {
	public int addDispatchOrderInfo(DispatchOrderInfoBean bean) {
		DispatchOrderInfoDao dao = new DispatchOrderInfoDao();
		return dao.insertDispatchOrderInfo(bean);
	}

	public int updateDispatchOrderInfo(DispatchOrderInfoBean bean) {
		DispatchOrderInfoDao dao = new DispatchOrderInfoDao();
		return dao.updateDispatchOrderInfo(bean);
	}

	public DispatchOrderInfoBean getDispatchOrderInfoByKey(int doId) {
		DispatchOrderInfoDao dao = new DispatchOrderInfoDao();
		return dao.getDispatchOrderInfoByKey(doId);
	}

	public List<DispatchOrderInfoBean> getDispatchOrderInfoListByKey(int storeId, String outerOrderId) {
		DispatchOrderInfoDao dao = new DispatchOrderInfoDao();
		return dao.getDispatchOrderInfoListByKey(storeId, outerOrderId);
	}

	public PageResultBean<DispatchOrderInfoBean> getDispatchOrderInfoPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		DispatchOrderInfoDao dao = new DispatchOrderInfoDao();
		return dao.getDispatchOrderInfoPageListByMap(condMap, pageBean);
	}

	public List<DispatchOrderInfoBean> getDispatchOrderInfoListByMap(HashMap<String, String> condMap) {
		DispatchOrderInfoDao dao = new DispatchOrderInfoDao();
		return dao.getDispatchOrderInfoListByMap(condMap);
	}

}