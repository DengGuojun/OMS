package com.lpmas.oms.order.handler;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.oms.factory.OmsDBFactory;
import com.lpmas.oms.order.bean.SalesOrderInfoBean;
import com.lpmas.oms.order.bean.SalesOrderItemBean;
import com.lpmas.oms.order.dao.SalesOrderInfoDao;
import com.lpmas.oms.order.dao.SalesOrderItemDao;

public class SalesOrderHandler {
	private static Logger log = LoggerFactory.getLogger(SalesOrderHandler.class);

	private SalesOrderInfoDao salesOrderInfoDao;
	private SalesOrderItemDao salesOrderItemDao;

	private SalesOrderInfoDao getSalesOrderInfoDao() {
		if (null == salesOrderInfoDao) {
			salesOrderInfoDao = new SalesOrderInfoDao();
		}
		return salesOrderInfoDao;
	}

	private SalesOrderItemDao getSalesOrderItemDao() {
		if (null == salesOrderItemDao) {
			salesOrderItemDao = new SalesOrderItemDao();
		}
		return salesOrderItemDao;
	}

	private int addSalesOrderInfo(SalesOrderInfoBean bean, DBObject db) throws SQLException {
		return getSalesOrderInfoDao().insertSalesOrderInfo(bean, db);
	}

	private int updateSalesOrderInfo(SalesOrderInfoBean bean, DBObject db) throws SQLException {
		return getSalesOrderInfoDao().updateSalesOrderInfo(bean, db);
	}

	private int addSalesOrderItem(SalesOrderItemBean bean, DBObject db) throws SQLException {
		return getSalesOrderItemDao().insertSalesOrderItem(bean, db);
	}

	private int updateSalesOrderItem(SalesOrderItemBean bean, DBObject db) throws SQLException {
		return getSalesOrderItemDao().updateSalesOrderItem(bean, db);
	}

	public int addSalesOrder(SalesOrderInfoBean bean, List<SalesOrderItemBean> list) {
		int soId = -1;
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			db.beginTransition();// 事务开始

			// 处理sales order info
			soId = addSalesOrderInfo(bean, db);

			if (soId > 0) {
				for (SalesOrderItemBean itemBean : list) {
					itemBean.setSoId(soId);
					addSalesOrderItem(itemBean, db);
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
			soId = -1;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return soId;
	}

	public int updateSalesOrder(SalesOrderInfoBean bean, List<SalesOrderItemBean> list) {
		int result = 0;
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			db.beginTransition();// 事务开始

			// 处理sales order info
			result = updateSalesOrderInfo(bean, db);

			if (result > 0) {
				for (SalesOrderItemBean itemBean : list) {
					updateSalesOrderItem(itemBean, db);
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
