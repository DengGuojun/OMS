package com.lpmas.oms.dispatch.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lpmas.constant.sync.SyncStatusConfig;
import com.lpmas.framework.excel.ExcelReadResultBean;
import com.lpmas.framework.tools.common.bean.ImportResultBean;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.NumberKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.oms.dispatch.bean.DispatchOrderInfoBean;
import com.lpmas.oms.dispatch.bean.DispatchOrderItemBean;
import com.lpmas.oms.dispatch.config.DispatchOrderStatusConfig;
import com.lpmas.oms.dispatch.handler.DispatchOrderHandler;
import com.lpmas.oms.order.business.SalesOrderBusiness;
import com.lpmas.tms.client.cache.ExpressCompanyInfoClientCache;
import com.lpmas.tms.config.TmsConfig;
import com.lpmas.tms.express.bean.ExpressCompanyInfoBean;

public class DispatchOrderImportBusiness {

	public ImportResultBean importOrderInfoList(int userId, ExcelReadResultBean excelReadResultBean) throws Exception {
		ImportResultBean resultBean = new ImportResultBean();
		List<String> successMsgList = new ArrayList<String>();
		List<String> errorMsgList = new ArrayList<String>();
		int quantity = 0;
		int successQuantity = 0;

		List<List<String>> contentList = excelReadResultBean.getContentList();
		DispatchOrderInfoBusiness business = new DispatchOrderInfoBusiness();
		DispatchOrderItemBusiness itemBusiness = new DispatchOrderItemBusiness();
		ExpressCompanyInfoClientCache expressCompanyInfoClientCache = new ExpressCompanyInfoClientCache();
		List<ExpressCompanyInfoBean> expressCompanyInfoList = expressCompanyInfoClientCache
				.getExpressCompanyInfoAllList();
		Map<String, ExpressCompanyInfoBean> expressCompanyInfoMap = ListKit.list2Map(expressCompanyInfoList,
				"companyName");
		for (int i = 1; i < contentList.size(); i++) {
			List<String> content = contentList.get(i);
			// 通过判断发运订单是否有数据来确认次行是否读取，否则结束循环。
			if (content.size() < 4 || !StringKit.isValid(content.get(0)) || !StringKit.isValid(content.get(1))
					|| !StringKit.isValid(content.get(2)) || !StringKit.isValid(content.get(3))) {
				break;
			}
			++quantity;
			if (!NumberKit.isAllDigit(content.get(0))) {
				errorMsgList.add("第" + quantity + "条导入失败,订单ID[" + content.get(0) + "]订单ID格式有误，请校验正确的订单ID");
				continue;
			}
			DispatchOrderInfoBean bean = business.getDispatchOrderInfoByKey(Integer.valueOf(content.get(0)));
			if (bean == null) {
				errorMsgList.add("第" + quantity + "条导入失败,订单ID[" + content.get(0) + "]找不到对应的发运订单ID，请校验正确的发运订单ID");
				continue;
			}
			try {
				Timestamp deliveryStartTime = new Timestamp(DateKit.str2Date(content.get(1) + ":00",
						DateKit.DEFAULT_DATE_TIME_FORMAT).getTime());
				bean.setDeliveryStartTime(deliveryStartTime);
			} catch (Exception e) {
				errorMsgList.add("第" + quantity + "条导入失败,订单ID[" + Integer.valueOf(content.get(0))
						+ "]发货时间格式有误，请校验正确的发货时间");
				continue;
			}
			if (expressCompanyInfoMap.containsKey(content.get(2))) {
				bean.setTransporterId(expressCompanyInfoMap.get(content.get(2)).getCompanyId());
				bean.setTransporterType(TmsConfig.TRANSPROTER_TYPE_EXPRESS);
			} else {
				errorMsgList.add("第" + quantity + "条导入失败,订单ID[" + content.get(0) + "]找不到对应的运输公司，请校验正确的运输公司");
				continue;
			}
			bean.setTransportNumber(content.get(3));
			bean.setModifyUser(userId);
			bean.setDoStatus(DispatchOrderStatusConfig.DOS_SENT);
			bean.setSyncStatus(SyncStatusConfig.SYNCS_SENT);

			List<DispatchOrderItemBean> itemList = itemBusiness.getDispatchOrderItemListByKey(bean.getDoId());
			for (DispatchOrderItemBean itemBean : itemList) {
				itemBean.setDoItemStatus(bean.getDoStatus());
				itemBean.setModifyUser(userId);
			}

			DispatchOrderHandler handler = new DispatchOrderHandler();
			handler.updateDispatchOrder(bean, itemList);

			SalesOrderBusiness soBusiness = new SalesOrderBusiness();
			soBusiness.updateDeliveryInfo(bean.getDoId(), userId);
			successQuantity++;

		}

		successMsgList.add("共" + quantity + "条数据,导入成功" + successQuantity + "条,导入失败" + (quantity - successQuantity)
				+ "条。");
		resultBean.setSuccessMsgList(successMsgList);
		resultBean.setErrorMsgList(errorMsgList);
		return resultBean;
	}
}
