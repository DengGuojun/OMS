package com.lpmas.oms.order.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.oms.config.OmsResource;
import com.lpmas.oms.order.business.SalesOrderBusiness;

/**
 * Servlet implementation class SalesOrderApprove
 */
@WebServlet("/order/SalesOrderApprove.do")
public class SalesOrderApprove extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SalesOrderApprove() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		// return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(OmsResource.SALES_ORDER, OperationConfig.UPDATE)) {
			return;
		}

		String soIds = request.getParameter("soIds");

		List<Integer> successList = new ArrayList<Integer>();
		List<Integer> failList = new ArrayList<Integer>();
		for (String id : soIds.split(",")) {
			int soId = Integer.parseInt(id);

			SalesOrderBusiness business = new SalesOrderBusiness();
			boolean result = business.approveSalesOrder(soId, adminHelper.getAdminUserId());
			if (result) {
				successList.add(soId);
			} else {
				failList.add(soId);
			}
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("successList", successList);
		resultMap.put("failList", failList);

		HttpResponseKit.printJson(request, response, JsonKit.toJson(resultMap), null);
	}

}
