package com.lpmas.oms.dispatch.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.excel.ExcelWriteBean;
import com.lpmas.framework.excel.WebExcelWriteKit;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.oms.config.OmsConfig;
import com.lpmas.oms.config.OmsResource;
import com.lpmas.oms.dispatch.bean.DispatchOrderInfoBean;
import com.lpmas.oms.dispatch.bean.DispatchOrderItemBean;
import com.lpmas.oms.dispatch.business.DispatchOrderInfoBusiness;
import com.lpmas.oms.dispatch.business.DispatchOrderItemBusiness;
import com.lpmas.oms.dispatch.config.DispatchConfig;
import com.lpmas.oms.dispatch.config.DispatchOrderStatusConfig;
import com.lpmas.oms.order.bean.SalesOrderInfoBean;
import com.lpmas.oms.order.business.SalesOrderInfoBusiness;
import com.lpmas.system.bean.StoreInfoBean;
import com.lpmas.system.client.cache.StoreInfoClientCache;
import com.lpmas.tms.transporter.business.TransporterInfoMediator;

/**
 * Servlet implementation class SalesOrderInfoList
 */
@WebServlet("/dispatch/DispatchOrderInfoList.do")
public class DispatchOrderInfoList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DispatchOrderInfoList() {
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
		// 判断查询or导出
		String goAction = ParamKit.getParameter(request, "goAction", "");
		if (goAction.equals("export")) {
			export(request, response);
		} else {
			display(request, response);
		}
	}

	protected void display(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(OmsResource.DISPATH_ORDER, OperationConfig.SEARCH)) {
			return;
		}

		int pageNum = ParamKit.getIntParameter(request, "pageNum", OmsConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", OmsConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);

		// 处理查询条件
		String condStr = "doId,soId,storeId,doStatus,consignerId,gtCreateTime,ltCreateTime";
		HashMap<String, String> condMap = ParamKit.getParameterMap(request, condStr);
		condMap.put("status", Constants.STATUS_VALID + "");

		DispatchOrderInfoBusiness business = new DispatchOrderInfoBusiness();
		StoreInfoClientCache storeInfoClientCache = new StoreInfoClientCache();
		List<StoreInfoBean> storeList = storeInfoClientCache.getStoreInfoAllList();

		PageResultBean<DispatchOrderInfoBean> result = business.getDispatchOrderInfoPageListByMap(condMap, pageBean);
		request.setAttribute("OrderList", result.getRecordList());

		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("CondList", MapKit.map2List(condMap));

		request.setAttribute("AdminUserHelper", adminHelper);
		request.setAttribute("StoreList", storeList);
		request.setAttribute("StoreNameMap", ListKit.list2Map(storeList, "storeId", "storeName"));

		TransporterInfoMediator transporterInfoMediator = new TransporterInfoMediator();
		request.setAttribute("TransporterInfoMediator", transporterInfoMediator);

		String path = DispatchConfig.PAGE_PATH + "DispatchOrderInfoList.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	protected void export(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(OmsResource.DISPATH_ORDER, OperationConfig.EXPORT)) {
			return;
		}
		// 处理查询条件
		String condStr = "doId,soId,storeId,doStatus,consignerId,gtCreateTime,ltCreateTime";
		HashMap<String, String> condMap = ParamKit.getParameterMap(request, condStr);
		condMap.put("status", Constants.STATUS_VALID + "");

		DispatchOrderInfoBusiness business = new DispatchOrderInfoBusiness();
		List<DispatchOrderInfoBean> result = business.getDispatchOrderInfoListByMap(condMap);

		DispatchOrderItemBusiness itemBusiness = new DispatchOrderItemBusiness();
		List<DispatchOrderItemBean> orderItemList = new ArrayList<DispatchOrderItemBean>();
		TransporterInfoMediator transporterInfoMediator = new TransporterInfoMediator();
		SalesOrderInfoBusiness salesOrderInfoBusiness = new SalesOrderInfoBusiness();
		// 查商店
		StoreInfoClientCache storeInfoClientCache = new StoreInfoClientCache();
		List<StoreInfoBean> storeList = storeInfoClientCache.getStoreInfoAllList();
		Map<Integer, String> storeNameMap = ListKit.list2Map(storeList, "storeId", "storeName");

		List<List<Object>> contentList = new ArrayList<List<Object>>();
		List<Object> tempList = null;
		for (DispatchOrderInfoBean bean : result) {
			tempList = new ArrayList<Object>();
			tempList.add(bean.getDoId());
			tempList.add(MapKit.getValueFromMap(bean.getStoreId(), storeNameMap));
			tempList.add(bean.getOuterOrderId());
			tempList.add(DateKit.formatTimestamp(bean.getCreateTime(), DateKit.DEFAULT_DATE_TIME_FORMAT));
			tempList.add(MapKit.getValueFromMap(bean.getDoStatus(), DispatchOrderStatusConfig.DISPATCH_ORDER_STATUS_MAP));
			tempList.add(bean.getSoFactAmount());
			tempList.add(bean.getTotalQuantity());
			SalesOrderInfoBean salesOrderInfoBean = salesOrderInfoBusiness.getSalesOrderInfoByKey(bean.getSoId());
			if (salesOrderInfoBean != null) {
				tempList.add(salesOrderInfoBean.getInvoiceTitle());
				tempList.add(salesOrderInfoBean.getInvoiceAmount());
			} else {
				putEmptyCell(tempList, 2);
			}
			orderItemList = itemBusiness.getDispatchOrderItemListByKey(bean.getDoId());
			if (orderItemList.size() > 0) {
				tempList.add(orderItemList.get(0).getProductItemNumber());
				tempList.add(orderItemList.get(0).getProductItemBarcode());
				tempList.add(orderItemList.get(0).getProductName());
				tempList.add(orderItemList.get(0).getQuantity());
			} else {
				putEmptyCell(tempList, 4);
			}
			tempList.add(bean.getReceiverName());
			tempList.add(bean.getMobile());
			tempList.add(bean.getProvince());
			tempList.add(bean.getCity());
			tempList.add(bean.getRegion());
			tempList.add(bean.getProvince() + bean.getCity() + bean.getRegion() + bean.getAddress());
			tempList.add(transporterInfoMediator.getTransporterNameByKey(bean.getTransporterType(), bean.getTransporterId()));
			tempList.add(bean.getTransportNumber());

			contentList.add(tempList);
			for (int i = 1; i < orderItemList.size(); ++i) {
				List<Object> rowContentList = new ArrayList<Object>();
				putEmptyCell(rowContentList, 9);
				rowContentList.add(orderItemList.get(i).getProductItemNumber());
				rowContentList.add(orderItemList.get(i).getProductItemBarcode());
				rowContentList.add(orderItemList.get(i).getProductName());
				rowContentList.add(orderItemList.get(i).getQuantity());
				putEmptyCell(rowContentList, 8);
				contentList.add(rowContentList);
			}
		}
		// 导出表头
		List<String> headerList = new ArrayList<String>();
		headerList.add("发运订单ID");
		headerList.add("商店");
		headerList.add("外部订单号");
		headerList.add("创建时间");
		headerList.add("订单状态");
		headerList.add("订单金额");
		headerList.add("件数");
		headerList.add("发票抬头");
		headerList.add("发票金额");
		headerList.add("商品SKU");
		headerList.add("商品条形码");
		headerList.add("商品项名称");
		headerList.add("商品项件数");
		headerList.add("收货人");
		headerList.add("手机");
		headerList.add("省份");
		headerList.add("城市");
		headerList.add("区");
		headerList.add("收货地址");
		headerList.add("运输公司");
		headerList.add("运输单号");

		// 设列宽
		HashMap<Integer, Integer> columnWidthMap = new HashMap<Integer, Integer>();
		columnWidthMap.put(20, 18);

		ExcelWriteBean excelWriteBean = new ExcelWriteBean();
		excelWriteBean.setFileName("发运订单导出");
		excelWriteBean.setFileType("xlsx");
		excelWriteBean.setHeaderList(headerList);
		excelWriteBean.setContentList(contentList);
		excelWriteBean.setColumnWidthMap(columnWidthMap);

		WebExcelWriteKit webExcelWriteKit = new WebExcelWriteKit();
		webExcelWriteKit.outputExcel(excelWriteBean, request, response);
		return;
	}

	private void putEmptyCell(List<Object> rowContentList, int count) {
		for (int i = 0; i < count; i++) {
			rowContentList.add("");
		}
	}
}
