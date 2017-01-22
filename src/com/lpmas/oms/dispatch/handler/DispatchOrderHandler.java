package com.lpmas.oms.dispatch.handler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.oms.dispatch.bean.DispatchOrderInfoBean;
import com.lpmas.oms.dispatch.bean.DispatchOrderItemBean;
import com.lpmas.oms.dispatch.dao.DispatchOrderInfoDao;
import com.lpmas.oms.dispatch.dao.DispatchOrderItemDao;
import com.lpmas.oms.factory.OmsDBFactory;

public class DispatchOrderHandler {
	private static Logger log = LoggerFactory.getLogger(DispatchOrderHandler.class);

	private DispatchOrderInfoDao dispatchOrderInfoDao;
	private DispatchOrderItemDao dispatchOrderItemDao;

	private DispatchOrderInfoDao getDispatchOrderInfoDao() {
		if (null == dispatchOrderInfoDao) {
			dispatchOrderInfoDao = new DispatchOrderInfoDao();
		}
		return dispatchOrderInfoDao;
	}

	private DispatchOrderItemDao getDispatchOrderItemDao() {
		if (null == dispatchOrderItemDao) {
			dispatchOrderItemDao = new DispatchOrderItemDao();
		}
		return dispatchOrderItemDao;
	}

	private int addDispatchOrderInfo(DispatchOrderInfoBean bean, DBObject db) throws SQLException {
		return getDispatchOrderInfoDao().insertDispatchOrderInfo(bean, db);
	}

	private int updateDispatchOrderInfo(DispatchOrderInfoBean bean, DBObject db) throws SQLException {
		return getDispatchOrderInfoDao().updateDispatchOrderInfo(bean, db);
	}

	private int addDispatchOrderItem(DispatchOrderItemBean bean, DBObject db) throws SQLException {
		return getDispatchOrderItemDao().insertDispatchOrderItem(bean, db);
	}

	private int updateDispatchOrderItem(DispatchOrderItemBean bean, DBObject db) throws SQLException {
		return getDispatchOrderItemDao().updateDispatchOrderItem(bean, db);
	}

	public int addDispatchOrder(Map<Integer,DispatchOrderInfoBean> dispatchOrderInfoMap, Map<Integer,List<DispatchOrderItemBean>> dispatchOrderItemMap) {
		int doId = -1;
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			db.beginTransition();// 事务开始
			
			for(int key: dispatchOrderInfoMap.keySet()){
				// 处理dispatch order info
				DispatchOrderInfoBean bean = dispatchOrderInfoMap.get(key);
				doId = addDispatchOrderInfo(bean, db);
				
				// 处理dispatch order item并扣减库存
				if (doId > 0) {
					List<DispatchOrderItemBean> list = dispatchOrderItemMap.get(key);
					for (DispatchOrderItemBean itemBean : list) {
						itemBean.setDoId(doId);
						addDispatchOrderItem(itemBean, db);
					}
				}
			}
			

			

			db.commit();// 事务提交
		} catch (Exception e) {
			log.error("", e);
			try {
				db.rollback();
			} catch (SQLException sqle) {
				log.error("", sqle);
			} // 事务回滚
			doId = -1;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return doId;
	}

	public int updateDispatchOrder(DispatchOrderInfoBean bean, List<DispatchOrderItemBean> list) {
		int result = -1;
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			db.beginTransition();// 事务开始

			// 处理dispatch order info
			result = updateDispatchOrderInfo(bean, db);

			// 处理dispatch order item并扣减库存
			if (result > 0) {
				for (DispatchOrderItemBean itemBean : list) {
					updateDispatchOrderItem(itemBean, db);
				}
			}

			db.commit();// 事务提交
		} catch (Exception e) {
			log.error("", e);
			try {
				db.rollback();
			} catch (SQLException sqle) {
				log.error("", sqle);
			} // 事务回滚
			result = -1;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}
}
