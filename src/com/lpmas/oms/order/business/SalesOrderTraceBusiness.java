package com.lpmas.oms.order.business;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.oms.client.bean.response.SalesOrderTraceContentResponseBean;
import com.lpmas.oms.dispatch.bean.DispatchOrderInfoBean;
import com.lpmas.oms.dispatch.business.DispatchOrderInfoBusiness;
import com.lpmas.wms.client.WmsServiceClient;
import com.lpmas.wms.client.bean.request.DispatchOrderTraceRequestBean;
import com.lpmas.wms.client.bean.response.DispatchOrderTraceContentResponseBean;
import com.lpmas.wms.client.bean.response.DispatchOrderTraceResponseBean;

public class SalesOrderTraceBusiness {
	private static Logger log = LoggerFactory.getLogger(SalesOrderTraceBusiness.class);

	public List<SalesOrderTraceContentResponseBean> getSalesOrderTrace(int storeId, String outerOrderId) {
		List<SalesOrderTraceContentResponseBean> list = new ArrayList<SalesOrderTraceContentResponseBean>();

		DispatchOrderInfoBusiness doInfoBusiness = new DispatchOrderInfoBusiness();
		List<DispatchOrderInfoBean> doInfoList = doInfoBusiness.getDispatchOrderInfoListByKey(storeId, outerOrderId);

		if (doInfoList.size() > 0) {
			for (DispatchOrderInfoBean doInfoBean : doInfoList) {
				DispatchOrderTraceRequestBean requestBean = new DispatchOrderTraceRequestBean();
				requestBean.setDoId(doInfoBean.getDoId());
				requestBean.setTransporterType(doInfoBean.getTransporterType());
				requestBean.setTransporterId(doInfoBean.getTransporterId());
				requestBean.setTransportNumber(doInfoBean.getTransportNumber());

				WmsServiceClient wmsClient = new WmsServiceClient();
				DispatchOrderTraceResponseBean responseBean = wmsClient.getDispatchOrderTrace(requestBean);
				for (DispatchOrderTraceContentResponseBean contentResponseBean : responseBean.getTraceContentList()) {
					SalesOrderTraceContentResponseBean bean = new SalesOrderTraceContentResponseBean();
					bean.setOperationTime(contentResponseBean.getOperationTime());
					bean.setOperationContent(contentResponseBean.getOperationContent());
					list.add(bean);
				}
			}
		}

		return list;
	}
}
