package com.lpmas.oms.dispatch.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.constant.admin.AdminUserConfig;
import com.lpmas.constant.sync.SyncStatusConfig;
import com.lpmas.framework.mq.activemq.ActiveMQReceiver;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.oms.dispatch.bean.DispatchOrderInfoBean;
import com.lpmas.oms.dispatch.bean.DispatchOrderItemBean;
import com.lpmas.oms.dispatch.config.DispatchOrderStatusConfig;
import com.lpmas.oms.dispatch.handler.DispatchOrderHandler;
import com.lpmas.oms.order.business.SalesOrderBusiness;
import com.lpmas.wms.client.bean.response.DispatchOrderStatusResponseBean;

public class DispatchOrderStatusMQReceiver extends ActiveMQReceiver {
	private static Logger log = LoggerFactory.getLogger(DispatchOrderStatusMQReceiver.class);

	public DispatchOrderStatusMQReceiver(String brokerId) {
		super(brokerId);
	}

	@Override
	public void process(String message) {
		try {
			DispatchOrderStatusResponseBean responseBean = JsonKit.toBean(message,
					DispatchOrderStatusResponseBean.class);

			DispatchOrderInfoBusiness business = new DispatchOrderInfoBusiness();
			DispatchOrderInfoBean bean = business.getDispatchOrderInfoByKey(responseBean.getDoId());

			bean.setModifyUser(AdminUserConfig.SYSTEM_USER);

			DispatchOrderItemBusiness itemBusiness = new DispatchOrderItemBusiness();
			List<DispatchOrderItemBean> itemList = itemBusiness.getDispatchOrderItemListByKey(responseBean.getDoId());

			// 如果是打单状态，则回传快递单号信息，如果是发货状态，则更新发货信息（目前只处理这2种状态）
			if (DispatchOrderStatusConfig.DOS_PRINTED.equals(responseBean.getDoStatus())) {
				bean.setTransporterType(responseBean.getTransporterType());
				bean.setTransporterId(responseBean.getTransporterId());
				bean.setTransportNumber(responseBean.getTransportNumber());
				bean.setSyncStatus(SyncStatusConfig.SYNCS_SENT);
				// 只有当订单为分配状态时，才更新订单状态
				if (DispatchOrderStatusConfig.DOS_ALLOCATED.equals(bean.getDoStatus())) {
					bean.setDoStatus(responseBean.getDoStatus());
					for (DispatchOrderItemBean itemBean : itemList) {
						itemBean.setDoItemStatus(responseBean.getDoStatus());
						itemBean.setModifyUser(AdminUserConfig.SYSTEM_USER);
					}
				}
			} else if (DispatchOrderStatusConfig.DOS_SENT.equals(responseBean.getDoStatus())) {
				bean.setDoStatus(DispatchOrderStatusConfig.DOS_SENT);
				for (DispatchOrderItemBean itemBean : itemList) {
					itemBean.setDoItemStatus(responseBean.getDoStatus());
					itemBean.setModifyUser(AdminUserConfig.SYSTEM_USER);
				}
			}

			DispatchOrderHandler handler = new DispatchOrderHandler();
			handler.updateDispatchOrder(bean, itemList);

			SalesOrderBusiness soBusiness = new SalesOrderBusiness();
			soBusiness.updateDeliveryInfo(responseBean.getDoId(), AdminUserConfig.SYSTEM_USER);
		} catch (Exception e) {
			log.error("DispatchOrderStatusMQReceiver process error:", e);
		}
	}
}
