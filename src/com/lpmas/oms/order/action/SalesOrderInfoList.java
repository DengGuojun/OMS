package com.lpmas.oms.order.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.oms.config.OmsConfig;
import com.lpmas.oms.config.OmsResource;
import com.lpmas.oms.order.bean.SalesOrderInfoBean;
import com.lpmas.oms.order.business.SalesOrderInfoBusiness;
import com.lpmas.oms.order.config.OrderConfig;
import com.lpmas.system.bean.StoreInfoBean;
import com.lpmas.system.bean.TradeSourceInfoBean;
import com.lpmas.system.business.SystemInfoHelper;
import com.lpmas.system.client.cache.TraceSourceInfoClientCache;

/**
 * Servlet implementation class SalesOrderInfoList
 */
@WebServlet("/order/SalesOrderInfoList.do")
public class SalesOrderInfoList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SalesOrderInfoList() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(OmsResource.SALES_ORDER, OperationConfig.SEARCH)) {
			return;
		}

		int pageNum = ParamKit.getIntParameter(request, "pageNum", OmsConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", OmsConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);

		// 处理查询条件
		String condStr = "soId,storeId,outerOrderId,soStatus,gtCreateTime,ltCreateTime";
		HashMap<String, String> condMap = ParamKit.getParameterMap(request, condStr);
		condMap.put("status", Constants.STATUS_VALID + "");

		SystemInfoHelper infoHelper = new SystemInfoHelper(adminHelper);
		List<StoreInfoBean> storeList = infoHelper.getUserValidStoreList();

		SalesOrderInfoBusiness business = new SalesOrderInfoBusiness();
		PageResultBean<SalesOrderInfoBean> result = business.getSalesOrderInfoPageListByMap(condMap, pageBean);
		request.setAttribute("OrderList", result.getRecordList());

		// 查询来源
		HashMap<Integer, String> traceSourceInfoMap = new HashMap<Integer, String>();
		TraceSourceInfoClientCache traceSourceInfoClientCache = new TraceSourceInfoClientCache();
		for (SalesOrderInfoBean salesOrderInfoBean : result.getRecordList()) {
			TradeSourceInfoBean tradeSourceInfoBean = traceSourceInfoClientCache.getTraceSourceInfoByCode(salesOrderInfoBean.getStoreId(),
					salesOrderInfoBean.getTradeSource());
			if (tradeSourceInfoBean != null) {
				traceSourceInfoMap.put(salesOrderInfoBean.getSoId(), tradeSourceInfoBean.getSourceName());
			}
		}
		request.setAttribute("TraceSourceInfoMap", traceSourceInfoMap);
		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);

		request.setAttribute("CondList", MapKit.map2List(condMap));

		request.setAttribute("AdminUserHelper", adminHelper);

		request.setAttribute("StoreList", storeList);
		request.setAttribute("StoreNameMap", ListKit.list2Map(storeList, "storeId", "storeName"));

		String path = OrderConfig.PAGE_PATH + "SalesOrderInfoList.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
