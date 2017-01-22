package com.lpmas.oms.dispatch.dao;

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
import com.lpmas.oms.dispatch.bean.DispatchOrderItemBean;
import com.lpmas.oms.factory.OmsDBFactory;

public class DispatchOrderItemDao {
	private static Logger log = LoggerFactory.getLogger(DispatchOrderItemDao.class);

	public int insertDispatchOrderItem(DispatchOrderItemBean bean) {
		int result = -1;
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();

			result = insertDispatchOrderItem(bean, db);
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

	public int insertDispatchOrderItem(DispatchOrderItemBean bean, DBObject db) throws SQLException {
		int result = -1;

		String sql = "insert into dispatch_order_item ( do_id, product_item_id, product_item_number, product_item_barcode, product_name, unit, quantity, list_price, offer_price, discount_price, fact_price, freight, discount_freight, fact_freight, item_amount, item_discount_amount, item_fact_amount, currency, do_item_status, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
		PreparedStatement ps = db.getPreparedStatement(sql);
		ps.setInt(1, bean.getDoId());
		ps.setInt(2, bean.getProductItemId());
		ps.setString(3, bean.getProductItemNumber());
		ps.setString(4, bean.getProductItemBarcode());
		ps.setString(5, bean.getProductName());
		ps.setString(6, bean.getUnit());
		ps.setDouble(7, bean.getQuantity());
		ps.setDouble(8, bean.getListPrice());
		ps.setDouble(9, bean.getOfferPrice());
		ps.setDouble(10, bean.getDiscountPrice());
		ps.setDouble(11, bean.getFactPrice());
		ps.setDouble(12, bean.getFreight());
		ps.setDouble(13, bean.getDiscountFreight());
		ps.setDouble(14, bean.getFactFreight());
		ps.setDouble(15, bean.getItemAmount());
		ps.setDouble(16, bean.getItemDiscountAmount());
		ps.setDouble(17, bean.getItemFactAmount());
		ps.setString(18, bean.getCurrency());
		ps.setString(19, bean.getDoItemStatus());
		ps.setInt(20, bean.getStatus());
		ps.setInt(21, bean.getCreateUser());
		ps.setString(22, bean.getMemo());

		result = db.executePstmtInsert();

		return result;
	}

	public int updateDispatchOrderItem(DispatchOrderItemBean bean) {
		int result = -1;
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();

			result = updateDispatchOrderItem(bean, db);
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

	public int updateDispatchOrderItem(DispatchOrderItemBean bean, DBObject db) throws SQLException {
		int result = -1;

		String sql = "update dispatch_order_item set do_id = ?, product_item_id = ?, product_item_number = ?, product_item_barcode = ?, product_name = ?, unit = ?, quantity = ?, list_price = ?, offer_price = ?, discount_price = ?, fact_price = ?, freight = ?, discount_freight = ?, fact_freight = ?, item_amount = ?, item_discount_amount = ?, item_fact_amount = ?, currency = ?, do_item_status = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where do_item_id = ?";
		PreparedStatement ps = db.getPreparedStatement(sql);
		ps.setInt(1, bean.getDoId());
		ps.setInt(2, bean.getProductItemId());
		ps.setString(3, bean.getProductItemNumber());
		ps.setString(4, bean.getProductItemBarcode());
		ps.setString(5, bean.getProductName());
		ps.setString(6, bean.getUnit());
		ps.setDouble(7, bean.getQuantity());
		ps.setDouble(8, bean.getListPrice());
		ps.setDouble(9, bean.getOfferPrice());
		ps.setDouble(10, bean.getDiscountPrice());
		ps.setDouble(11, bean.getFactPrice());
		ps.setDouble(12, bean.getFreight());
		ps.setDouble(13, bean.getDiscountFreight());
		ps.setDouble(14, bean.getFactFreight());
		ps.setDouble(15, bean.getItemAmount());
		ps.setDouble(16, bean.getItemDiscountAmount());
		ps.setDouble(17, bean.getItemFactAmount());
		ps.setString(18, bean.getCurrency());
		ps.setString(19, bean.getDoItemStatus());
		ps.setInt(20, bean.getStatus());
		ps.setInt(21, bean.getModifyUser());
		ps.setString(22, bean.getMemo());

		ps.setInt(23, bean.getDoItemId());

		result = db.executePstmtUpdate();

		return result;
	}

	public DispatchOrderItemBean getDispatchOrderItemByKey(int doItemId) {
		DispatchOrderItemBean bean = null;
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from dispatch_order_item where do_item_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, doItemId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new DispatchOrderItemBean();
				bean = BeanKit.resultSet2Bean(rs, DispatchOrderItemBean.class);
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

	public List<DispatchOrderItemBean> getDispatchOrderItemListByKey(int doId) {
		List<DispatchOrderItemBean> list = new ArrayList<DispatchOrderItemBean>();
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from dispatch_order_item where do_id = ? and status = " + Constants.STATUS_VALID;
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, doId);

			ResultSet rs = db.executePstmtQuery();
			while (rs.next()) {
				DispatchOrderItemBean bean = new DispatchOrderItemBean();
				bean = BeanKit.resultSet2Bean(rs, DispatchOrderItemBean.class);

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

	public PageResultBean<DispatchOrderItemBean> getDispatchOrderItemPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<DispatchOrderItemBean> result = new PageResultBean<DispatchOrderItemBean>();
		DBFactory dbFactory = new OmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from dispatch_order_item";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String orderQuery = "order by do_item_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, DispatchOrderItemBean.class,
					pageBean, db);
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
