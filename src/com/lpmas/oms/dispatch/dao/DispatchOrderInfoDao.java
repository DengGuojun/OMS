package com.lpmas.oms.dispatch.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.oms.dispatch.bean.DispatchOrderInfoBean;
import com.lpmas.oms.factory.OmsDBFactory;

public class DispatchOrderInfoDao {
	private static Logger log = LoggerFactory.getLogger(DispatchOrderInfoDao.class);

	public int insertDispatchOrderInfo(DispatchOrderInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();

			result = insertDispatchOrderInfo(bean, db);
		} catch (Exception e) {
			log.error("", e);
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

	public int insertDispatchOrderInfo(DispatchOrderInfoBean bean, DBObject db) throws SQLException {
		int result = -1;

		String sql = "insert into dispatch_order_info ( store_id, so_id, outer_order_id, country, province, city, region, address, receiver_name, telephone, mobile, zip_code, delivery_method, delivery_start_time, delivery_end_time, transporter_type, transporter_id, transport_number, freight, discount_freight, fact_freight, so_amount, so_discount_amount, so_fact_amount, currency, total_quantity, user_comment, consigner_type, consigner_id, do_status, sync_status, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
		PreparedStatement ps = db.getPreparedStatement(sql);
		ps.setInt(1, bean.getStoreId());
		ps.setInt(2, bean.getSoId());
		ps.setString(3, bean.getOuterOrderId());
		ps.setString(4, bean.getCountry());
		ps.setString(5, bean.getProvince());
		ps.setString(6, bean.getCity());
		ps.setString(7, bean.getRegion());
		ps.setString(8, bean.getAddress());
		ps.setString(9, bean.getReceiverName());
		ps.setString(10, bean.getTelephone());
		ps.setString(11, bean.getMobile());
		ps.setString(12, bean.getZipCode());
		ps.setInt(13, bean.getDeliveryMethod());
		ps.setTimestamp(14, bean.getDeliveryStartTime());
		ps.setTimestamp(15, bean.getDeliveryEndTime());
		ps.setInt(16, bean.getTransporterType());
		ps.setInt(17, bean.getTransporterId());
		ps.setString(18, bean.getTransportNumber());
		ps.setDouble(19, bean.getFreight());
		ps.setDouble(20, bean.getDiscountFreight());
		ps.setDouble(21, bean.getFactFreight());
		ps.setDouble(22, bean.getSoAmount());
		ps.setDouble(23, bean.getSoDiscountAmount());
		ps.setDouble(24, bean.getSoFactAmount());
		ps.setString(25, bean.getCurrency());
		ps.setDouble(26, bean.getTotalQuantity());
		ps.setString(27, bean.getUserComment());
		ps.setInt(28, bean.getConsignerType());
		ps.setInt(29, bean.getConsignerId());
		ps.setString(30, bean.getDoStatus());
		ps.setString(31, bean.getSyncStatus());
		ps.setInt(32, bean.getStatus());
		ps.setInt(33, bean.getCreateUser());
		ps.setString(34, bean.getMemo());

		result = db.executePstmtInsert();

		return result;
	}

	public int updateDispatchOrderInfo(DispatchOrderInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();

			result = updateDispatchOrderInfo(bean, db);
		} catch (Exception e) {
			log.error("", e);
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

	public int updateDispatchOrderInfo(DispatchOrderInfoBean bean, DBObject db) throws SQLException {
		int result = -1;

		String sql = "update dispatch_order_info set store_id = ?, so_id = ?, outer_order_id = ?, country = ?, province = ?, city = ?, region = ?, address = ?, receiver_name = ?, telephone = ?, mobile = ?, zip_code = ?, delivery_method = ?, delivery_start_time = ?, delivery_end_time = ?, transporter_type = ?, transporter_id = ?, transport_number = ?, freight = ?, discount_freight = ?, fact_freight = ?, so_amount = ?, so_discount_amount = ?, so_fact_amount = ?, currency = ?, total_quantity = ?, user_comment = ?, consigner_type = ?, consigner_id = ?, do_status = ?, sync_status = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where do_id = ?";
		PreparedStatement ps = db.getPreparedStatement(sql);
		ps.setInt(1, bean.getStoreId());
		ps.setInt(2, bean.getSoId());
		ps.setString(3, bean.getOuterOrderId());
		ps.setString(4, bean.getCountry());
		ps.setString(5, bean.getProvince());
		ps.setString(6, bean.getCity());
		ps.setString(7, bean.getRegion());
		ps.setString(8, bean.getAddress());
		ps.setString(9, bean.getReceiverName());
		ps.setString(10, bean.getTelephone());
		ps.setString(11, bean.getMobile());
		ps.setString(12, bean.getZipCode());
		ps.setInt(13, bean.getDeliveryMethod());
		ps.setTimestamp(14, bean.getDeliveryStartTime());
		ps.setTimestamp(15, bean.getDeliveryEndTime());
		ps.setInt(16, bean.getTransporterType());
		ps.setInt(17, bean.getTransporterId());
		ps.setString(18, bean.getTransportNumber());
		ps.setDouble(19, bean.getFreight());
		ps.setDouble(20, bean.getDiscountFreight());
		ps.setDouble(21, bean.getFactFreight());
		ps.setDouble(22, bean.getSoAmount());
		ps.setDouble(23, bean.getSoDiscountAmount());
		ps.setDouble(24, bean.getSoFactAmount());
		ps.setString(25, bean.getCurrency());
		ps.setDouble(26, bean.getTotalQuantity());
		ps.setString(27, bean.getUserComment());
		ps.setInt(28, bean.getConsignerType());
		ps.setInt(29, bean.getConsignerId());
		ps.setString(30, bean.getDoStatus());
		ps.setString(31, bean.getSyncStatus());
		ps.setInt(32, bean.getStatus());
		ps.setInt(33, bean.getModifyUser());
		ps.setString(34, bean.getMemo());

		ps.setInt(35, bean.getDoId());

		result = db.executePstmtUpdate();

		return result;
	}

	public DispatchOrderInfoBean getDispatchOrderInfoByKey(int doId) {
		DispatchOrderInfoBean bean = null;
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from dispatch_order_info where do_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, doId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new DispatchOrderInfoBean();
				bean = BeanKit.resultSet2Bean(rs, DispatchOrderInfoBean.class);
			}
			rs.close();
		} catch (Exception e) {
			log.error("", e);
			bean = null;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return bean;
	}

	public List<DispatchOrderInfoBean> getDispatchOrderInfoListByKey(int storeId, String outerOrderId) {
		List<DispatchOrderInfoBean> list = new ArrayList<DispatchOrderInfoBean>();
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from dispatch_order_info where store_id = ? and outer_order_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, storeId);
			ps.setString(2, outerOrderId);

			ResultSet rs = db.executePstmtQuery();
			while (rs.next()) {
				DispatchOrderInfoBean bean = new DispatchOrderInfoBean();
				bean = BeanKit.resultSet2Bean(rs, DispatchOrderInfoBean.class);

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("", e);
			list = null;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return list;
	}

	public PageResultBean<DispatchOrderInfoBean> getDispatchOrderInfoPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		PageResultBean<DispatchOrderInfoBean> result = new PageResultBean<DispatchOrderInfoBean>();
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from dispatch_order_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			getParseCondition(condMap, condList, paramList);

			String orderQuery = "order by do_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, DispatchOrderInfoBean.class, pageBean, db);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}

	public List<DispatchOrderInfoBean> getDispatchOrderInfoListByMap(HashMap<String, String> condMap) {
		List<DispatchOrderInfoBean> result = new ArrayList<DispatchOrderInfoBean>();
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;

		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from dispatch_order_info";
			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			getParseCondition(condMap, condList, paramList);
			String orderQuery = "order by do_id desc";

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, DispatchOrderInfoBean.class, db);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}

		return result;
	}

	private void getParseCondition(HashMap<String, String> condMap, List<String> condList, List<String> paramList) {
		String doId = condMap.get("doId");
		if (StringKit.isValid(doId)) {
			condList.add("do_id = ?");
			paramList.add(doId);
		}
		String soId = condMap.get("soId");
		if (StringKit.isValid(soId)) {
			condList.add("so_id = ?");
			paramList.add(soId);
		}
		String storeId = condMap.get("storeId");
		if (StringKit.isValid(storeId)) {
			condList.add("store_id = ?");
			paramList.add(storeId);
		}
		String consignerId = condMap.get("consignerId");
		if (StringKit.isValid(consignerId)) {
			condList.add("consigner_id = ?");
			paramList.add(consignerId);
		}
		String gtCreateTime = condMap.get("gtCreateTime");
		if (StringKit.isValid(gtCreateTime)) {
			condList.add("create_time > ?");
			paramList.add(gtCreateTime);
		}

		String ltCreateTime = condMap.get("ltCreateTime");
		if (StringKit.isValid(ltCreateTime)) {
			condList.add("create_time < ?");
			paramList.add(ltCreateTime);
		}
		String doStatus = condMap.get("doStatus");
		if (StringKit.isValid(doStatus)) {
			condList.add("do_status = ?");
			paramList.add(doStatus);
		}
		String status = condMap.get("status");
		if (StringKit.isValid(status)) {
			condList.add("status = ?");
			paramList.add(status);
		}
	}

}
