package com.lpmas.oms.order.business;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.constant.admin.AdminUserConfig;
import com.lpmas.constant.sync.SyncStatusConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.mq.activemq.ActiveMQReceiver;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.oms.client.bean.request.SalesOrderItemRequestBean;
import com.lpmas.oms.client.bean.request.SalesOrderRequestBean;
import com.lpmas.oms.order.bean.SalesOrderInfoBean;
import com.lpmas.oms.order.bean.SalesOrderItemBean;
import com.lpmas.oms.order.config.OrderConfig;
import com.lpmas.oms.order.config.SalesOrderStatusConfig;
import com.lpmas.oms.order.handler.SalesOrderHandler;
import com.lpmas.pdm.bean.ProductItemBean;
import com.lpmas.pdm.client.PdmServiceClient;

public class SalesOrderMQReceiver extends ActiveMQReceiver {
	private static Logger log = LoggerFactory.getLogger(SalesOrderMQReceiver.class);

	public SalesOrderMQReceiver(String brokerId) {
		super(brokerId);
	}

	@Override
	public void process(String message) {
		try {
			SalesOrderRequestBean requestBean = JsonKit.toBean(message, SalesOrderRequestBean.class);

			SalesOrderInfoBusiness business = new SalesOrderInfoBusiness();
			if (business.isDuplicateOuterOrderId(requestBean.getStoreId(), requestBean.getOuterOrderId())) {
				log.warn("Duplicate outer order id:storeId[{}],outerOrderId[{}]", requestBean.getStoreId(),
						requestBean.getOuterOrderId());
				return;
			}

			SalesOrderInfoBean bean = new SalesOrderInfoBean();
			getSalesOrderInfoBean(bean, requestBean);

			List<SalesOrderItemBean> itemList = new ArrayList<SalesOrderItemBean>();
			for (SalesOrderItemRequestBean itemRquestBean : requestBean.getSalesOrderItemList()) {
				SalesOrderItemBean itemBean = new SalesOrderItemBean();
				getSalesOrderItemBean(itemBean, itemRquestBean);

				itemList.add(itemBean);
			}

			SalesOrderHandler handler = new SalesOrderHandler();
			int result = handler.addSalesOrder(bean, itemList);
			// 同步解锁状态
			if (result > 0) {

			}
		} catch (Exception e) {
			log.error("SalesOrderMQReceiver process error:", e);
		}
	}

	private void getSalesOrderInfoBean(SalesOrderInfoBean bean, SalesOrderRequestBean requestBean) {
		bean.setStoreId(requestBean.getStoreId());
		bean.setOuterOrderId(requestBean.getOuterOrderId());
		bean.setCountry(requestBean.getCountry());
		bean.setProvince(requestBean.getProvince());
		bean.setCity(requestBean.getCity());
		bean.setRegion(requestBean.getRegion());
		bean.setAddress(requestBean.getAddress());
		bean.setReceiverName(requestBean.getReceiverName());
		bean.setTelephone(requestBean.getTelephone());
		bean.setMobile(requestBean.getMobile());
		bean.setZipCode(requestBean.getZipCode());
		bean.setInvoiceType(requestBean.getInvoiceType());
		bean.setInvoiceTitle(requestBean.getInvoiceTitle());
		bean.setInvoiceAmount(requestBean.getInvoiceAmount());
		bean.setDeliveryMethod(requestBean.getDeliveryMethod());
		bean.setTransporterType(requestBean.getTransporterType());
		bean.setTransporterId(requestBean.getTransporterId());
		bean.setCurrency(requestBean.getCurrency());
		bean.setProductAmount(requestBean.getProductAmount());
		bean.setProductDiscountAmount(requestBean.getProductDiscountAmount());
		bean.setProductFactAmount(requestBean.getProductFactAmount());
		bean.setFreight(requestBean.getFreight());
		bean.setDiscountFreight(requestBean.getDiscountFreight());
		bean.setFactFreight(requestBean.getFactFreight());
		bean.setSoAmount(requestBean.getSoAmount());
		bean.setSoDiscountAmount(requestBean.getSoDiscountAmount());
		bean.setSoFactAmount(requestBean.getSoFactAmount());
		bean.setTotalQuantity(requestBean.getTotalQuantity());
		bean.setSoType(requestBean.getSoType());
		bean.setPayTime(requestBean.getPayTime());
		bean.setShopId(requestBean.getShopId());
		bean.setClerkId(requestBean.getClerkId());
		bean.setOrderFrom(requestBean.getOrderFrom());
		bean.setTradeSource(requestBean.getTradeSource());
		bean.setTradeSourceId1(requestBean.getTradeSourceId1());
		bean.setTradeSourceId2(requestBean.getTradeSourceId2());
		bean.setUserComment(requestBean.getUserComment());
		bean.setSoStatus(SalesOrderStatusConfig.ORDS_PAYED);
		bean.setSyncStatus(SyncStatusConfig.SYNCS_NONE);
		bean.setStatus(Constants.STATUS_VALID);
		bean.setCreateUser(AdminUserConfig.SYSTEM_USER);
	}

	private void getSalesOrderItemBean(SalesOrderItemBean bean, SalesOrderItemRequestBean requestBean) {
		bean.setOuterOrderItemId(requestBean.getOuterOrderItemId());
		
		// 临时转换
		if (OrderConfig.skuMap.containsKey(requestBean.getProductItemNumber())) {
			String sku = OrderConfig.skuMap.get(requestBean.getProductItemNumber());
			requestBean.setProductItemNumber(sku);
		}

		if (requestBean.getProductId() <= 0 || requestBean.getProductItemId() <= 0) {// 从商品库获取商品项信息
			PdmServiceClient pdmServiceClient = new PdmServiceClient();
			ProductItemBean productItemBean = pdmServiceClient
					.getProductItemByNumber(requestBean.getProductItemNumber());

			bean.setProductId(productItemBean.getProductId());
			bean.setProductItemId(productItemBean.getItemId());
		} else {
			bean.setProductId(requestBean.getProductId());
			bean.setProductItemId(requestBean.getProductItemId());
		}
		bean.setProductItemNumber(requestBean.getProductItemNumber());

		bean.setProductName(requestBean.getProductName());
		bean.setPurchaseType(requestBean.getPurchaseType());
		bean.setDeliveryMethod(requestBean.getDeliveryMethod());
		bean.setTransporterType(requestBean.getTransporterType());
		bean.setTransporterId(requestBean.getTransporterId());
		bean.setCurrency(requestBean.getCurrency());
		bean.setUnit(requestBean.getUnit());
		bean.setQuantity(requestBean.getQuantity());
		bean.setListPrice(requestBean.getListPrice());
		bean.setOfferPrice(requestBean.getOfferPrice());
		bean.setDiscountPrice(requestBean.getDiscountPrice());
		bean.setFactPrice(requestBean.getFactPrice());
		bean.setFreight(requestBean.getFreight());
		bean.setDiscountFreight(requestBean.getDiscountFreight());
		bean.setFactFreight(requestBean.getFactFreight());
		bean.setItemAmount(requestBean.getItemAmount());
		bean.setItemDiscountAmount(requestBean.getItemDiscountAmount());
		bean.setItemFactAmount(requestBean.getItemFactAmount());
		bean.setClerkId(requestBean.getClerkId());
		bean.setSoItemStatus(SalesOrderStatusConfig.ORDS_PAYED);
		bean.setStatus(Constants.STATUS_VALID);
		bean.setCreateUser(AdminUserConfig.SYSTEM_USER);
	}

}
