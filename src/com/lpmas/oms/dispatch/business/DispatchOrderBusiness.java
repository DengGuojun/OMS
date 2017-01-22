package com.lpmas.oms.dispatch.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.constant.sync.SyncStatusConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.NumeralOperationKit;
import com.lpmas.oms.dispatch.bean.DispatchOrderInfoBean;
import com.lpmas.oms.dispatch.bean.DispatchOrderItemBean;
import com.lpmas.oms.dispatch.config.DispatchOrderStatusConfig;
import com.lpmas.oms.dispatch.handler.DispatchOrderHandler;
import com.lpmas.oms.order.bean.SalesOrderInfoBean;
import com.lpmas.oms.order.bean.SalesOrderItemBean;
import com.lpmas.oms.order.config.DeliveryMethodConfig;
import com.lpmas.pdm.bean.ProductItemBean;
import com.lpmas.pdm.client.PdmServiceClient;
import com.lpmas.wms.client.WmsServiceClient;
import com.lpmas.wms.client.bean.request.DispatchOrderItemRequestBean;
import com.lpmas.wms.client.bean.request.DispatchOrderRequestBean;

public class DispatchOrderBusiness {
	private static Logger log = LoggerFactory.getLogger(DispatchOrderBusiness.class);

	public int addDispatchOrder(SalesOrderInfoBean soBean, List<SalesOrderItemBean> soItemList, int userId) {
		int result = 0;
		int consignerId = 0;

		Map<Integer, DispatchOrderInfoBean> infoMap = new HashMap<Integer, DispatchOrderInfoBean>();
		Map<Integer, List<DispatchOrderItemBean>> itemMap = new HashMap<Integer, List<DispatchOrderItemBean>>();

		List<DispatchOrderItemBean> list = null;
		for (SalesOrderItemBean soItemBean : soItemList) {
			/**if (soItemBean.getProductItemNumber().equals("8L112PN02")
					|| soItemBean.getProductItemNumber().equals("8L111PN02")
					|| soItemBean.getProductItemNumber().equals("8L113PN02")
					|| soItemBean.getProductItemNumber().equals("8L111CEP1")
					|| soItemBean.getProductItemNumber().equals("8L111CEP2")
					|| soItemBean.getProductItemNumber().equals("8L111CEP4")
					|| soItemBean.getProductItemNumber().equals("11620160540248")
					|| soItemBean.getProductItemNumber().equals("8Y114BN02")
					// add at 20160919
					|| soItemBean.getProductItemNumber().equals("8Y113PN02")
					|| soItemBean.getProductItemNumber().equals("8Y114PN02")
					|| soItemBean.getProductItemNumber().equals("8Y113PN01")
					|| soItemBean.getProductItemNumber().equals("8Y114PN01")
			) {
				consignerId = DeliveryMethodConfig.WAREHOUSE_GZ;
			} else {
				consignerId = DeliveryMethodConfig.WAREHOUSE_ZH;
			}**/
			//所有订单都从圆通发货 at 20170104
			consignerId = DeliveryMethodConfig.WAREHOUSE_YT;
			if (itemMap.containsKey(consignerId)) {
				list = itemMap.get(consignerId);
			} else {
				list = new ArrayList<DispatchOrderItemBean>();
			}
			DispatchOrderItemBean itemBean = new DispatchOrderItemBean();
			itemBean.setProductItemId(soItemBean.getProductItemId());
			itemBean.setProductItemNumber(soItemBean.getProductItemNumber());
			itemBean.setProductItemBarcode(getProductItemBarcode(soItemBean.getProductItemId()));
			itemBean.setProductName(soItemBean.getProductName());
			itemBean.setCurrency(soItemBean.getCurrency());
			itemBean.setUnit(soItemBean.getUnit());
			itemBean.setQuantity(soItemBean.getQuantity());
			itemBean.setListPrice(soItemBean.getListPrice());
			itemBean.setOfferPrice(soItemBean.getOfferPrice());
			itemBean.setDiscountPrice(soItemBean.getDiscountPrice());
			itemBean.setFactPrice(soItemBean.getFactPrice());
			itemBean.setFreight(soItemBean.getFreight());
			itemBean.setDiscountFreight(soItemBean.getDiscountFreight());
			itemBean.setFactFreight(soItemBean.getFactFreight());
			itemBean.setItemAmount(soItemBean.getItemAmount());
			itemBean.setItemDiscountAmount(soItemBean.getItemDiscountAmount());
			itemBean.setItemFactAmount(soItemBean.getItemFactAmount());
			itemBean.setDoItemStatus(DispatchOrderStatusConfig.DOS_ALLOCATED);
			itemBean.setStatus(Constants.STATUS_VALID);
			itemBean.setCreateUser(userId);
			list.add(itemBean);
			itemMap.put(consignerId, list);
		}

		for (int key : itemMap.keySet()) {
			List<DispatchOrderItemBean> itemList = itemMap.get(key);
			// 计算分单后的数量和价格信息
			double freight = 0;
			double discountFreight = 0;
			double factFreight = 0;
			double soAmount = 0;
			double soDiscountAmount = 0;
			double soFactAmount = 0;
			double totalQuantity = 0;
			for (DispatchOrderItemBean itemBean : itemList) {
				freight = NumeralOperationKit.add(freight, itemBean.getFreight());
				discountFreight = NumeralOperationKit.add(discountFreight, itemBean.getDiscountFreight());
				factFreight = NumeralOperationKit.add(factFreight, itemBean.getFactFreight());
				soAmount = NumeralOperationKit.add(soAmount, itemBean.getItemAmount());
				soDiscountAmount = NumeralOperationKit.add(soDiscountAmount, itemBean.getItemDiscountAmount());
				soFactAmount = NumeralOperationKit.add(soFactAmount, itemBean.getItemFactAmount());
				totalQuantity = NumeralOperationKit.add(totalQuantity, itemBean.getQuantity());
			}

			DispatchOrderInfoBean bean = new DispatchOrderInfoBean();
			bean.setStoreId(soBean.getStoreId());
			bean.setSoId(soBean.getSoId());
			bean.setOuterOrderId(soBean.getOuterOrderId());
			bean.setCountry(soBean.getCountry());
			bean.setProvince(soBean.getProvince());
			bean.setCity(soBean.getCity());
			bean.setRegion(soBean.getRegion());
			bean.setAddress(soBean.getAddress());
			bean.setReceiverName(soBean.getReceiverName());
			bean.setTelephone(soBean.getTelephone());
			bean.setMobile(soBean.getMobile());
			bean.setZipCode(soBean.getZipCode());
			bean.setDeliveryMethod(soBean.getDeliveryMethod());
			bean.setTransporterType(soBean.getTransporterType());
			bean.setTransporterId(soBean.getTransporterId());
			bean.setCurrency(soBean.getCurrency());

			bean.setFreight(freight);
			bean.setDiscountFreight(discountFreight);
			bean.setFactFreight(factFreight);
			bean.setSoAmount(soAmount);
			bean.setSoDiscountAmount(soDiscountAmount);
			bean.setSoFactAmount(soFactAmount);
			bean.setTotalQuantity(totalQuantity);

			bean.setUserComment(soBean.getUserComment());
			bean.setConsignerType(DeliveryMethodConfig.DM_WAREHOUSE);
			bean.setConsignerId(key);
			bean.setDoStatus(DispatchOrderStatusConfig.DOS_ALLOCATED);
			bean.setSyncStatus(SyncStatusConfig.SYNCS_NONE);
			bean.setStatus(Constants.STATUS_VALID);
			bean.setCreateUser(userId);

			infoMap.put(key, bean);
		}

		DispatchOrderHandler handler = new DispatchOrderHandler();
		result = handler.addDispatchOrder(infoMap, itemMap);

		return result;
	}

	public synchronized boolean sendDispatchOrderToWms(DispatchOrderInfoBean doInfoBean) {
		boolean result = false;

		DispatchOrderItemBusiness doItemBusiness = new DispatchOrderItemBusiness();
		List<DispatchOrderItemBean> doItemList = doItemBusiness.getDispatchOrderItemListByKey(doInfoBean.getDoId());

		DispatchOrderRequestBean requestBean = new DispatchOrderRequestBean();
		requestBean.setDoId(doInfoBean.getDoId());
		requestBean.setStoreId(doInfoBean.getStoreId());
		requestBean.setSoId(doInfoBean.getSoId());
		requestBean.setOuterOrderId(doInfoBean.getOuterOrderId());
		requestBean.setCountry(doInfoBean.getCountry());
		requestBean.setProvince(doInfoBean.getProvince());
		requestBean.setCity(doInfoBean.getCity());
		requestBean.setRegion(doInfoBean.getRegion());
		requestBean.setAddress(doInfoBean.getAddress());
		requestBean.setReceiverName(doInfoBean.getReceiverName());
		requestBean.setTelephone(doInfoBean.getTelephone());
		requestBean.setMobile(doInfoBean.getMobile());
		requestBean.setZipCode(doInfoBean.getZipCode());
		requestBean.setDeliveryMethod(doInfoBean.getDeliveryMethod());
		requestBean.setDeliveryStartTime(doInfoBean.getDeliveryStartTime());
		requestBean.setDeliveryEndTime(doInfoBean.getDeliveryEndTime());
		requestBean.setTransporterType(doInfoBean.getTransporterType());
		requestBean.setTransporterId(doInfoBean.getTransporterId());
		requestBean.setTransportNumber(doInfoBean.getTransportNumber());
		requestBean.setFreight(doInfoBean.getFreight());
		requestBean.setDiscountFreight(doInfoBean.getDiscountFreight());
		requestBean.setFactFreight(doInfoBean.getFactFreight());
		requestBean.setSoAmount(doInfoBean.getSoAmount());
		requestBean.setSoDiscountAmount(doInfoBean.getSoDiscountAmount());
		requestBean.setSoFactAmount(doInfoBean.getSoFactAmount());
		requestBean.setCurrency(doInfoBean.getCurrency());
		requestBean.setTotalQuantity(doInfoBean.getTotalQuantity());
		requestBean.setUserComment(doInfoBean.getUserComment());
		requestBean.setConsignerType(doInfoBean.getConsignerType());
		requestBean.setConsignerId(doInfoBean.getConsignerId());

		List<DispatchOrderItemRequestBean> doItemRequestList = new ArrayList<DispatchOrderItemRequestBean>();
		for (DispatchOrderItemBean doItemBean : doItemList) {
			DispatchOrderItemRequestBean doItemRequestBean = new DispatchOrderItemRequestBean();
			doItemRequestBean.setDoItemId(doItemBean.getDoItemId());
			doItemRequestBean.setDoId(doItemBean.getDoId());
			doItemRequestBean.setProductItemId(doItemBean.getProductItemId());
			doItemRequestBean.setProductItemNumber(doItemBean.getProductItemNumber());
			doItemRequestBean.setProductItemBarcode(doItemBean.getProductItemBarcode());
			doItemRequestBean.setProductName(doItemBean.getProductName());
			doItemRequestBean.setUnit(doItemBean.getUnit());
			doItemRequestBean.setQuantity(doItemBean.getQuantity());
			doItemRequestBean.setItemAmount(doItemBean.getItemAmount());
			doItemRequestBean.setItemDiscountAmount(doItemBean.getItemDiscountAmount());
			doItemRequestBean.setItemFactAmount(doItemBean.getItemFactAmount());

			doItemRequestList.add(doItemRequestBean);
		}
		requestBean.setDispatchOrderItemList(doItemRequestList);

		// 发送到MQ
		try {
			WmsServiceClient client = new WmsServiceClient();
			client.sendDispatchOrder(requestBean);
			result = true;
		} catch (Exception e) {
			log.error("sendDispatchOrderToWms error:", e);
		}

		return result;
	}

	private String getProductItemBarcode(int productItemId) {
		String barcode = "";
		if (productItemId > 0) {
			PdmServiceClient client = new PdmServiceClient();
			ProductItemBean bean = client.getProductItemByKey(productItemId);
			barcode = bean.getBarcode();
		}
		return barcode;
	}
}
