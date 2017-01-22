package com.lpmas.oms.order.action;

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
import com.lpmas.framework.web.ParamKit;
import com.lpmas.oms.config.OmsResource;
import com.lpmas.oms.order.bean.SalesOrderInfoBean;
import com.lpmas.oms.order.bean.SalesOrderItemBean;
import com.lpmas.oms.order.business.SalesOrderInfoBusiness;
import com.lpmas.oms.order.business.SalesOrderItemBusiness;
import com.lpmas.oms.order.config.OrderConfig;
import com.lpmas.region.client.RegionServiceClient;
import com.lpmas.system.business.SystemInfoHelper;

/**
 * Servlet implementation class SalesOrderInfoManage
 */
@WebServlet("/order/SalesOrderInfoManage.do")
public class SalesOrderInfoManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SalesOrderInfoManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		SystemInfoHelper infoHelper = new SystemInfoHelper(adminHelper);

		int soId = ParamKit.getIntParameter(request, "soId", 0);
		SalesOrderInfoBean orderInfoBean = new SalesOrderInfoBean();
		List<SalesOrderItemBean> orderItemList = new ArrayList<SalesOrderItemBean>();
		if (soId > 0) {
			if (!adminHelper.checkPermission(OmsResource.SALES_ORDER, OperationConfig.UPDATE)) {
				return;
			}
			SalesOrderInfoBusiness business = new SalesOrderInfoBusiness();
			orderInfoBean = business.getSalesOrderInfoByKey(soId);
			
			SalesOrderItemBusiness itemBusiness = new SalesOrderItemBusiness();
			orderItemList = itemBusiness.getSalesOrderItemListByKey(soId);
		} else {
			if (!adminHelper.checkPermission(OmsResource.SALES_ORDER, OperationConfig.CREATE)) {
				return;
			}
		}

		request.setAttribute("OrderInfo", orderInfoBean);
		request.setAttribute("OrderItemList", orderItemList);

		request.setAttribute("AdminUserHelper", adminHelper);		
		request.setAttribute("StoreList", infoHelper.getUserValidStoreList());
		
		RegionServiceClient regionServiceClient = new RegionServiceClient();
		request.setAttribute("CountryList", regionServiceClient.getCountryInfoAllList());
		request.setAttribute("ProvinceList", regionServiceClient.getProvinceInfoListByCountryName(orderInfoBean.getCountry()));
		request.setAttribute("CityList", regionServiceClient.getCityInfoListByProvinceName(orderInfoBean.getProvince()));
		request.setAttribute("RegionList", regionServiceClient.getRegionInfoListByCityName(orderInfoBean.getCity()));

		String path = OrderConfig.PAGE_PATH + "SalesOrderInfoManage.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
