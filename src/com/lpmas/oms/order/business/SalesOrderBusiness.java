package com.lpmas.oms.order.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.constant.sync.SyncStatusConfig;
import com.lpmas.framework.util.NumeralOperationKit;
import com.lpmas.oms.dispatch.bean.DispatchOrderInfoBean;
import com.lpmas.oms.dispatch.bean.DispatchOrderItemBean;
import com.lpmas.oms.dispatch.business.DispatchOrderBusiness;
import com.lpmas.oms.dispatch.business.DispatchOrderInfoBusiness;
import com.lpmas.oms.dispatch.business.DispatchOrderItemBusiness;
import com.lpmas.oms.dispatch.config.DispatchOrderStatusConfig;
import com.lpmas.oms.order.bean.SalesOrderInfoBean;
import com.lpmas.oms.order.bean.SalesOrderItemBean;
import com.lpmas.oms.order.config.DeliveryMethodConfig;
import com.lpmas.oms.order.config.SalesOrderStatusConfig;
import com.lpmas.oms.order.handler.SalesOrderHandler;

public class SalesOrderBusiness {
	/**
	 * 审核订单，并生成发运订单
	 * 
	 * @param soId
	 * @param userId
	 * @return
	 */
	public boolean approveSalesOrder(int soId, int userId) {
		boolean result = false;

		SalesOrderInfoBusiness business = new SalesOrderInfoBusiness();
		SalesOrderInfoBean bean = business.getSalesOrderInfoByKey(soId);

		if (!bean.getSoStatus().equals(SalesOrderStatusConfig.ORDS_PAYED)) {
			return false;
		}

		SalesOrderItemBusiness itemBusiness = new SalesOrderItemBusiness();
		List<SalesOrderItemBean> list = itemBusiness.getSalesOrderItemListByKey(soId);

		// 新建dispatch order
		DispatchOrderBusiness doBusiness = new DispatchOrderBusiness();

		if (doBusiness.addDispatchOrder(bean, list, userId) > 0) {
			// 更新数据库状态
			bean.setSoStatus(SalesOrderStatusConfig.ORDS_APPROVED);
			bean.setSyncStatus(SyncStatusConfig.SYNCS_SENT);
			bean.setModifyUser(userId);

			for (SalesOrderItemBean itemBean : list) {
				itemBean.setSoItemStatus(SalesOrderStatusConfig.ORDS_APPROVED);
				itemBean.setModifyUser(userId);
			}

			SalesOrderHandler handler = new SalesOrderHandler();
			result = handler.updateSalesOrder(bean, list) > 0;
			if (result) {
				// 发送信息到WMS的MQ
				DispatchOrderInfoBusiness doInfoBusiness = new DispatchOrderInfoBusiness();
				List<DispatchOrderInfoBean> dispatchOrderList = doInfoBusiness.getDispatchOrderInfoListByKey(bean.getStoreId(), bean.getOuterOrderId());
				for(DispatchOrderInfoBean doInfoBean : dispatchOrderList){
					if(doInfoBean.getConsignerId() == DeliveryMethodConfig.WAREHOUSE_ZH){
						result = doBusiness.sendDispatchOrderToWms(doInfoBean);
					}
				}
			}
		}

		return result;
	}

	public boolean updateDeliveryInfo(int doId, int userId) {
		boolean result = false;

		DispatchOrderInfoBusiness doBusiness = new DispatchOrderInfoBusiness();
		DispatchOrderInfoBean doBean = doBusiness.getDispatchOrderInfoByKey(doId);

		DispatchOrderItemBusiness doItemBusiness = new DispatchOrderItemBusiness();
		List<DispatchOrderItemBean> doItemList = doItemBusiness.getDispatchOrderItemListByKey(doBean.getDoId());

		SalesOrderInfoBusiness soBusiness = new SalesOrderInfoBusiness();
		SalesOrderInfoBean soBean = soBusiness.getSalesOrderInfoByKey(doBean.getSoId());

		SalesOrderItemBusiness soItemBusiness = new SalesOrderItemBusiness();
		List<SalesOrderItemBean> soItemList = soItemBusiness.getSalesOrderItemListByKey(doBean.getSoId());

		Map<Integer, DispatchOrderItemBean> map = new HashMap<Integer, DispatchOrderItemBean>();
		for (DispatchOrderItemBean doItemBean : doItemList) {
			map.put(doItemBean.getProductItemId(), doItemBean);
		}

		double totalQuantity = 0;
		List<SalesOrderItemBean> updateSoItemList = new ArrayList<SalesOrderItemBean>();
		for (SalesOrderItemBean soItemBean : soItemList) {
			if (map.containsKey(soItemBean.getProductItemId())) {
				DispatchOrderItemBean doItemBean = map.get(soItemBean.getProductItemId());

				soItemBean.setTransporterType(doBean.getTransporterType());
				soItemBean.setTransporterId(doBean.getTransporterId());
				soItemBean.setTransportNumber(doBean.getTransportNumber());
				soItemBean.setSoItemStatus(doItemBean.getDoItemStatus());
				soItemBean.setModifyUser(userId);
				updateSoItemList.add(soItemBean);
			}
			// 计算已发货数量
			if(soItemBean.getSoItemStatus().equals(SalesOrderStatusConfig.ORDS_SENT)){
				totalQuantity = NumeralOperationKit.add(totalQuantity, soItemBean.getQuantity());
			}
		}

		soBean.setModifyUser(userId);

		//当发运单状态修改为打单状态时，销售订单从审核状态变更为打单状态
		if (DispatchOrderStatusConfig.DOS_PRINTED.equals(doBean.getDoStatus()) &&
				soBean.getSoStatus().equals(SalesOrderStatusConfig.ORDS_APPROVED)) {
			soBean.setSoStatus(SalesOrderStatusConfig.ORDS_PRINTED);
		}
		// 当发运单状态修改为发货状态时，需要根据是否发货完全来修改销售订单的订单状态
		if (DispatchOrderStatusConfig.DOS_SENT.equals(doBean.getDoStatus())) {
			if (totalQuantity < soBean.getTotalQuantity()) {// 部分发货
				soBean.setSoStatus(SalesOrderStatusConfig.ORDS_SENT_PART);
			} else {// 全部发货
				soBean.setSoStatus(SalesOrderStatusConfig.ORDS_SENT);
			}
		}

		SalesOrderHandler handler = new SalesOrderHandler();
		if (handler.updateSalesOrder(soBean, updateSoItemList) > 0) {
			// 只有在修改发货状态时，才通知前台
			if (DispatchOrderStatusConfig.DOS_SENT.equals(doBean.getDoStatus())) {
				DeliveryInfoSyncBusiness deliveryInfoSyncBusiness = new DeliveryInfoSyncBusiness();
				result = deliveryInfoSyncBusiness.sentDeliveryInfo(soBean, updateSoItemList);
			}
		}

		return result;
	}
}
