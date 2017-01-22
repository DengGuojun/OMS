package com.lpmas.oms.order.dao;

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
import com.lpmas.oms.factory.OmsDBFactory;
import com.lpmas.oms.order.bean.SalesOrderInfoBean;

public class SalesOrderInfoDao {
	private static Logger log = LoggerFactory.getLogger(SalesOrderInfoDao.class);

	public int insertSalesOrderInfo(SalesOrderInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();

			result = insertSalesOrderInfo(bean, db);
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

	public int insertSalesOrderInfo(SalesOrderInfoBean bean, DBObject db) throws SQLException {
		int result = -1;

		String sql = "insert into sales_order_info ( costomer_id, store_id, outer_order_id, country, province, city, region, address, receiver_name, telephone, mobile, zip_code, invoice_number, invoice_type, invoice_title, invoice_amount, delivery_method, transporter_type, transporter_id, transport_number, currency, product_amount, product_discount_amount, product_fact_amount, freight, discount_freight, fact_freight, so_amount, so_discount_amount, so_fact_amount, total_quantity, so_type, pay_time, shop_id, clerk_id, order_from, trade_source, trade_source_id_1, trade_source_id_2, user_comment, so_status, sync_status, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
		PreparedStatement ps = db.getPreparedStatement(sql);
		ps.setInt(1, bean.getCostomerId());
		ps.setInt(2, bean.getStoreId());
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
		ps.setString(13, bean.getInvoiceNumber());
		ps.setString(14, bean.getInvoiceType());
		ps.setString(15, bean.getInvoiceTitle());
		ps.setDouble(16, bean.getInvoiceAmount());
		ps.setInt(17, bean.getDeliveryMethod());
		ps.setInt(18, bean.getTransporterType());
		ps.setInt(19, bean.getTransporterId());
		ps.setString(20, bean.getTransportNumber());
		ps.setString(21, bean.getCurrency());
		ps.setDouble(22, bean.getProductAmount());
		ps.setDouble(23, bean.getProductDiscountAmount());
		ps.setDouble(24, bean.getProductFactAmount());
		ps.setDouble(25, bean.getFreight());
		ps.setDouble(26, bean.getDiscountFreight());
		ps.setDouble(27, bean.getFactFreight());
		ps.setDouble(28, bean.getSoAmount());
		ps.setDouble(29, bean.getSoDiscountAmount());
		ps.setDouble(30, bean.getSoFactAmount());
		ps.setDouble(31, bean.getTotalQuantity());
		ps.setInt(32, bean.getSoType());
		ps.setTimestamp(33, bean.getPayTime());
		ps.setInt(34, bean.getShopId());
		ps.setInt(35, bean.getClerkId());
		ps.setString(36, bean.getOrderFrom());
		ps.setString(37, bean.getTradeSource());
		ps.setString(38, bean.getTradeSourceId1());
		ps.setString(39, bean.getTradeSourceId2());
		ps.setString(40, bean.getUserComment());
		ps.setString(41, bean.getSoStatus());
		ps.setString(42, bean.getSyncStatus());
		ps.setInt(43, bean.getStatus());
		ps.setInt(44, bean.getCreateUser());
		ps.setString(45, bean.getMemo());

		result = db.executePstmtInsert();

		return result;
	}

	public int updateSalesOrderInfo(SalesOrderInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();

			result = updateSalesOrderInfo(bean, db);
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

	public int updateSalesOrderInfo(SalesOrderInfoBean bean, DBObject db) throws SQLException {
		int result = -1;

		String sql = "update sales_order_info set costomer_id = ?, store_id = ?, outer_order_id = ?, country = ?, province = ?, city = ?, region = ?, address = ?, receiver_name = ?, telephone = ?, mobile = ?, zip_code = ?, invoice_number = ?, invoice_type = ?, invoice_title = ?, invoice_amount = ?, delivery_method = ?, transporter_type = ?, transporter_id = ?, transport_number = ?, currency = ?, product_amount = ?, product_discount_amount = ?, product_fact_amount = ?, freight = ?, discount_freight = ?, fact_freight = ?, so_amount = ?, so_discount_amount = ?, so_fact_amount = ?, total_quantity = ?, so_type = ?, pay_time = ?, shop_id = ?, clerk_id = ?, order_from = ?, trade_source = ?, trade_source_id_1 = ?, trade_source_id_2 = ?, user_comment = ?, so_status = ?, sync_status = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where so_id = ?";
		PreparedStatement ps = db.getPreparedStatement(sql);
		ps.setInt(1, bean.getCostomerId());
		ps.setInt(2, bean.getStoreId());
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
		ps.setString(13, bean.getInvoiceNumber());
		ps.setString(14, bean.getInvoiceType());
		ps.setString(15, bean.getInvoiceTitle());
		ps.setDouble(16, bean.getInvoiceAmount());
		ps.setInt(17, bean.getDeliveryMethod());
		ps.setInt(18, bean.getTransporterType());
		ps.setInt(19, bean.getTransporterId());
		ps.setString(20, bean.getTransportNumber());
		ps.setString(21, bean.getCurrency());
		ps.setDouble(22, bean.getProductAmount());
		ps.setDouble(23, bean.getProductDiscountAmount());
		ps.setDouble(24, bean.getProductFactAmount());
		ps.setDouble(25, bean.getFreight());
		ps.setDouble(26, bean.getDiscountFreight());
		ps.setDouble(27, bean.getFactFreight());
		ps.setDouble(28, bean.getSoAmount());
		ps.setDouble(29, bean.getSoDiscountAmount());
		ps.setDouble(30, bean.getSoFactAmount());
		ps.setDouble(31, bean.getTotalQuantity());
		ps.setInt(32, bean.getSoType());
		ps.setTimestamp(33, bean.getPayTime());
		ps.setInt(34, bean.getShopId());
		ps.setInt(35, bean.getClerkId());
		ps.setString(36, bean.getOrderFrom());
		ps.setString(37, bean.getTradeSource());
		ps.setString(38, bean.getTradeSourceId1());
		ps.setString(39, bean.getTradeSourceId2());
		ps.setString(40, bean.getUserComment());
		ps.setString(41, bean.getSoStatus());
		ps.setString(42, bean.getSyncStatus());
		ps.setInt(43, bean.getStatus());
		ps.setInt(44, bean.getModifyUser());
		ps.setString(45, bean.getMemo());

		ps.setInt(46, bean.getSoId());

		result = db.executePstmtUpdate();

		return result;
	}

	public SalesOrderInfoBean getSalesOrderInfoByKey(int soId) {
		SalesOrderInfoBean bean = null;
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from sales_order_info where so_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, soId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new SalesOrderInfoBean();
				bean = BeanKit.resultSet2Bean(rs, SalesOrderInfoBean.class);
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

	public SalesOrderInfoBean getSalesOrderInfoByKey(int storeId, String outerOrderId) {
		SalesOrderInfoBean bean = null;
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from sales_order_info where store_id = ? and outer_order_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, storeId);
			ps.setString(2, outerOrderId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new SalesOrderInfoBean();
				bean = BeanKit.resultSet2Bean(rs, SalesOrderInfoBean.class);
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

	public PageResultBean<SalesOrderInfoBean> getSalesOrderInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<SalesOrderInfoBean> result = new PageResultBean<SalesOrderInfoBean>();
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from sales_order_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
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

			String outerOrderId = condMap.get("outerOrderId");
			if (StringKit.isValid(outerOrderId)) {
				condList.add("outer_order_id = ?");
				paramList.add(outerOrderId);
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

			String soStatus = condMap.get("soStatus");
			if (StringKit.isValid(soStatus)) {
				condList.add("so_status = ?");
				paramList.add(soStatus);
			}

			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}

			String orderQuery = "order by so_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, SalesOrderInfoBean.class, pageBean,
					db);
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

}
