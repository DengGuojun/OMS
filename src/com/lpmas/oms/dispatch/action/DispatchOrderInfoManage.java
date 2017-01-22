package com.lpmas.oms.dispatch.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.constant.sync.SyncStatusConfig;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.oms.config.OmsResource;
import com.lpmas.oms.dispatch.bean.DispatchOrderInfoBean;
import com.lpmas.oms.dispatch.bean.DispatchOrderItemBean;
import com.lpmas.oms.dispatch.business.DispatchOrderInfoBusiness;
import com.lpmas.oms.dispatch.business.DispatchOrderItemBusiness;
import com.lpmas.oms.dispatch.config.DispatchConfig;
import com.lpmas.oms.dispatch.config.DispatchOrderStatusConfig;
import com.lpmas.oms.dispatch.handler.DispatchOrderHandler;
import com.lpmas.oms.order.bean.SalesOrderInfoBean;
import com.lpmas.oms.order.business.SalesOrderBusiness;
import com.lpmas.oms.order.business.SalesOrderInfoBusiness;
import com.lpmas.region.client.RegionServiceClient;
import com.lpmas.system.business.SystemInfoHelper;
import com.lpmas.tms.transporter.business.TransporterInfoMediator;

/**
 * Servlet implementation class SalesOrderInfoManage
 */
@WebServlet("/dispatch/DispatchOrderInfoManage.do")
public class DispatchOrderInfoManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DispatchOrderInfoManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		SystemInfoHelper infoHelper = new SystemInfoHelper(adminHelper);

		int doId = ParamKit.getIntParameter(request, "doId", 0);
		DispatchOrderInfoBean orderInfoBean = new DispatchOrderInfoBean();
		SalesOrderInfoBean soInfoBean =  new SalesOrderInfoBean();
		List<DispatchOrderItemBean> orderItemList = new ArrayList<DispatchOrderItemBean>();
		if (doId > 0) {
			if (!adminHelper.checkPermission(OmsResource.DISPATH_ORDER, OperationConfig.UPDATE)) {
				return;
			}
			DispatchOrderInfoBusiness business = new DispatchOrderInfoBusiness();
			orderInfoBean = business.getDispatchOrderInfoByKey(doId);
			
			DispatchOrderItemBusiness itemBusiness = new DispatchOrderItemBusiness();
			orderItemList = itemBusiness.getDispatchOrderItemListByKey(doId);
			
			SalesOrderInfoBusiness salesOrderInfoBusiness = new SalesOrderInfoBusiness();
			soInfoBean = salesOrderInfoBusiness.getSalesOrderInfoByKey(orderInfoBean.getSoId());
		} else {
			if (!adminHelper.checkPermission(OmsResource.DISPATH_ORDER, OperationConfig.CREATE)) {
				return;
			}
		}

		request.setAttribute("OrderInfo", orderInfoBean);
		request.setAttribute("OrderItemList", orderItemList);
		request.setAttribute("SalesOrderInfo", soInfoBean);

		request.setAttribute("AdminUserHelper", adminHelper);
		request.setAttribute("StoreList", infoHelper.getUserValidStoreList());

		RegionServiceClient regionServiceClient = new RegionServiceClient();
		request.setAttribute("CountryList", regionServiceClient.getCountryInfoAllList());
		request.setAttribute("ProvinceList",
				regionServiceClient.getProvinceInfoListByCountryName(orderInfoBean.getCountry()));
		request.setAttribute("CityList", regionServiceClient.getCityInfoListByProvinceName(orderInfoBean.getProvince()));
		request.setAttribute("RegionList", regionServiceClient.getRegionInfoListByCityName(orderInfoBean.getCity()));

		TransporterInfoMediator transporterInfoMediator = new TransporterInfoMediator();
		request.setAttribute("TransporterInfoMediator", transporterInfoMediator);

		String path = DispatchConfig.PAGE_PATH + "DispatchOrderInfoManage.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		int doId = ParamKit.getIntParameter(request, "doId", 0);
		int transporterType = ParamKit.getIntParameter(request, "transporterType", 0);
		int transporterId = ParamKit.getIntParameter(request, "transporterId", 0);
		String transportNumber = ParamKit.getParameter(request, "transportNumber", "");
		String doStatus = ParamKit.getParameter(request, "doStatus", "");

		if (!adminUserHelper.checkPermission(OmsResource.DISPATH_ORDER, OperationConfig.UPDATE)) {
			return;
		}

		DispatchOrderInfoBusiness business = new DispatchOrderInfoBusiness();
		DispatchOrderInfoBean bean = business.getDispatchOrderInfoByKey(doId);
		if (bean == null) {
			HttpResponseKit.alertMessage(response, "参数错误", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		int result = 0;
		// 只有自发货的订单能进行修改
		/*if (bean.getConsignerId() != DeliveryMethodConfig.WAREHOUSE_GZ) {
			HttpResponseKit.alertMessage(response, "只有自发货的订单能修改订单内容", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}*/
		// 运输信息不能为空
		if (transporterType == 0 || transporterId == 0 || !StringKit.isValid(transportNumber)) {
			HttpResponseKit.alertMessage(response, "请填写运输信息", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		// 只更新运输信息和订单状态
		if (bean.getDoStatus() != DispatchOrderStatusConfig.DOS_ALLOCATED
				&& !DispatchOrderStatusConfig.DOS_SENT.equals(doStatus)) {
			HttpResponseKit.alertMessage(response, "订单状态只能从分配状态改为发货状态", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		bean.setTransporterType(transporterType);
		bean.setTransporterId(transporterId);
		bean.setDoStatus(doStatus);
		bean.setTransportNumber(transportNumber);
		bean.setSyncStatus(SyncStatusConfig.SYNCS_SENT);
		bean.setModifyUser(adminUserHelper.getAdminUserId());

		DispatchOrderItemBusiness itemBusiness = new DispatchOrderItemBusiness();
		List<DispatchOrderItemBean> itemList = itemBusiness.getDispatchOrderItemListByKey(doId);
		for (DispatchOrderItemBean itemBean : itemList) {
			itemBean.setDoItemStatus(doStatus);
		}
		DispatchOrderHandler handler = new DispatchOrderHandler();
		result = handler.updateDispatchOrder(bean, itemList);

		// 更新销售订单
		SalesOrderBusiness soBusiness = new SalesOrderBusiness();
		soBusiness.updateDeliveryInfo(doId, adminUserHelper.getAdminUserId());

		if (result > 0) {
			HttpResponseKit.alertMessage(response, "处理成功", "/dispatch/DispatchOrderInfoList.do");
		} else {
			HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
		}
	}

}
