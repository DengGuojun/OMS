package com.lpmas.oms.order.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.oms.factory.OmsDBFactory;
import com.lpmas.oms.order.bean.SalesOrderItemBean;

public class SalesOrderItemDao {
	private static Logger log = LoggerFactory.getLogger(SalesOrderItemDao.class);

	public int insertSalesOrderItem(SalesOrderItemBean bean) {
		int result = -1;
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();

			result = insertSalesOrderItem(bean, db);
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

	public int insertSalesOrderItem(SalesOrderItemBean bean, DBObject db) throws SQLException {
		int result = -1;

		String sql = "insert into sales_order_item ( so_id, outer_order_item_id, product_id, product_item_id, product_item_number, product_name, purchase_type, delivery_method, transporter_type, transporter_id, transport_number, currency, unit, quantity, list_price, offer_price, discount_price, fact_price, freight, discount_freight, fact_freight, item_amount, item_discount_amount, item_fact_amount, clerk_id, so_item_status, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
		PreparedStatement ps = db.getPreparedStatement(sql);
		ps.setInt(1, bean.getSoId());
		ps.setString(2, bean.getOuterOrderItemId());
		ps.setInt(3, bean.getProductId());
		ps.setInt(4, bean.getProductItemId());
		ps.setString(5, bean.getProductItemNumber());
		ps.setString(6, bean.getProductName());
		ps.setInt(7, bean.getPurchaseType());
		ps.setInt(8, bean.getDeliveryMethod());
		ps.setInt(9, bean.getTransporterType());
		ps.setInt(10, bean.getTransporterId());
		ps.setString(11, bean.getTransportNumber());
		ps.setString(12, bean.getCurrency());
		ps.setString(13, bean.getUnit());
		ps.setDouble(14, bean.getQuantity());
		ps.setDouble(15, bean.getListPrice());
		ps.setDouble(16, bean.getOfferPrice());
		ps.setDouble(17, bean.getDiscountPrice());
		ps.setDouble(18, bean.getFactPrice());
		ps.setDouble(19, bean.getFreight());
		ps.setDouble(20, bean.getDiscountFreight());
		ps.setDouble(21, bean.getFactFreight());
		ps.setDouble(22, bean.getItemAmount());
		ps.setDouble(23, bean.getItemDiscountAmount());
		ps.setDouble(24, bean.getItemFactAmount());
		ps.setInt(25, bean.getClerkId());
		ps.setString(26, bean.getSoItemStatus());
		ps.setInt(27, bean.getStatus());
		ps.setInt(28, bean.getCreateUser());
		ps.setString(29, bean.getMemo());

		result = db.executePstmtInsert();

		return result;
	}

	public int updateSalesOrderItem(SalesOrderItemBean bean) {
		int result = -1;
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();

			result = updateSalesOrderItem(bean, db);
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

	public int updateSalesOrderItem(SalesOrderItemBean bean, DBObject db) throws SQLException {
		int result = -1;

		String sql = "update sales_order_item set so_id = ?, outer_order_item_id = ?, product_id = ?, product_item_id = ?, product_item_number = ?, product_name = ?, purchase_type = ?, delivery_method = ?, transporter_type = ?, transporter_id = ?, transport_number = ?, currency = ?, unit = ?, quantity = ?, list_price = ?, offer_price = ?, discount_price = ?, fact_price = ?, freight = ?, discount_freight = ?, fact_freight = ?, item_amount = ?, item_discount_amount = ?, item_fact_amount = ?, clerk_id = ?, so_item_status = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where so_item_id = ?";
		PreparedStatement ps = db.getPreparedStatement(sql);
		ps.setInt(1, bean.getSoId());
		ps.setString(2, bean.getOuterOrderItemId());
		ps.setInt(3, bean.getProductId());
		ps.setInt(4, bean.getProductItemId());
		ps.setString(5, bean.getProductItemNumber());
		ps.setString(6, bean.getProductName());
		ps.setInt(7, bean.getPurchaseType());
		ps.setInt(8, bean.getDeliveryMethod());
		ps.setInt(9, bean.getTransporterType());
		ps.setInt(10, bean.getTransporterId());
		ps.setString(11, bean.getTransportNumber());
		ps.setString(12, bean.getCurrency());
		ps.setString(13, bean.getUnit());
		ps.setDouble(14, bean.getQuantity());
		ps.setDouble(15, bean.getListPrice());
		ps.setDouble(16, bean.getOfferPrice());
		ps.setDouble(17, bean.getDiscountPrice());
		ps.setDouble(18, bean.getFactPrice());
		ps.setDouble(19, bean.getFreight());
		ps.setDouble(20, bean.getDiscountFreight());
		ps.setDouble(21, bean.getFactFreight());
		ps.setDouble(22, bean.getItemAmount());
		ps.setDouble(23, bean.getItemDiscountAmount());
		ps.setDouble(24, bean.getItemFactAmount());
		ps.setInt(25, bean.getClerkId());
		ps.setString(26, bean.getSoItemStatus());
		ps.setInt(27, bean.getStatus());
		ps.setInt(28, bean.getModifyUser());
		ps.setString(29, bean.getMemo());

		ps.setInt(30, bean.getSoItemId());

		result = db.executePstmtUpdate();

		return result;
	}

	public SalesOrderItemBean getSalesOrderItemByKey(int soItemId) {
		SalesOrderItemBean bean = null;
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from sales_order_item where so_item_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, soItemId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new SalesOrderItemBean();
				bean = BeanKit.resultSet2Bean(rs, SalesOrderItemBean.class);
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

	public List<SalesOrderItemBean> getSalesOrderItemListByKey(int soId) {
		List<SalesOrderItemBean> list = new ArrayList<SalesOrderItemBean>();
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from sales_order_item where so_id = ? and status = " + Constants.STATUS_VALID;
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, soId);

			ResultSet rs = db.executePstmtQuery();
			while (rs.next()) {
				SalesOrderItemBean bean = new SalesOrderItemBean();
				bean = BeanKit.resultSet2Bean(rs, SalesOrderItemBean.class);

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return list;
	}

	public PageResultBean<SalesOrderItemBean> getSalesOrderItemPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<SalesOrderItemBean> result = new PageResultBean<SalesOrderItemBean>();
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from sales_order_item";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String orderQuery = "order by so_item_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, SalesOrderItemBean.class, pageBean,
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
