package com.lpmas.oms.dispatch.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.framework.excel.ExcelReadKit;
import com.lpmas.framework.excel.ExcelReadResultBean;
import com.lpmas.framework.tools.common.bean.ImportResultBean;
import com.lpmas.framework.transfer.FileUploadKit;
import com.lpmas.framework.transfer.FileUploadResultBean;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.oms.config.OmsResource;
import com.lpmas.oms.dispatch.business.DispatchOrderImportBusiness;
import com.lpmas.oms.dispatch.config.DispatchConfig;
import com.lpmas.tms.client.cache.ExpressCompanyInfoClientCache;
import com.lpmas.tms.express.bean.ExpressCompanyInfoBean;

@WebServlet("/dispatch/DispatchOrderInfoImport.do")
public class DispatchOrderInfoImport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DispatchOrderInfoImport() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		if (!adminUserHelper.checkPermission(OmsResource.DISPATH_ORDER, OperationConfig.UPDATE)) {
			return;
		}

		// 获取快递公司列表
		ExpressCompanyInfoClientCache expressCompanyInfoClientCache = new ExpressCompanyInfoClientCache();
		List<ExpressCompanyInfoBean> expressCompanyInfoList = expressCompanyInfoClientCache.getExpressCompanyInfoAllList();
		request.setAttribute("ExpressCompanyInfoList", expressCompanyInfoList);
		RequestDispatcher rd = request.getRequestDispatcher(DispatchConfig.PAGE_PATH + "DispatchOrderInfoImport.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		if (!adminUserHelper.checkPermission(OmsResource.DISPATH_ORDER, OperationConfig.UPDATE)) {
			return;
		}

		int userId = adminUserHelper.getAdminUserId();
		// 上传Excel到服务器
		FileUploadKit fileUploadKit = new FileUploadKit();
		String fileName = DateKit.getCurrentDateTime("yyyyMMddHHmmss");
		String uploadPath = DispatchConfig.FILE_PATH;
		fileUploadKit.setAllowedFileType(DispatchConfig.ALLOWED_FILE_TYPE);// 设置允许上传的文件类型
		fileUploadKit.setMaxSize(DispatchConfig.MAX_SIZE);
		FileUploadResultBean resultBean = fileUploadKit.fileUpload(request, "file", uploadPath, fileName);
		if (!resultBean.getResult()) {
			HttpResponseKit.alertMessage(response, resultBean.getResultContent(), HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		if (resultBean.getFileItemList() == null || resultBean.getFileItemList().size() == 0) {
			HttpResponseKit.alertMessage(response, "文件上传至服务器失败，详情请查看日志。", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		// 获取上传结果
		String filePath = resultBean.getFileItemList().get(0).getFilePath();
		ExcelReadKit excelReadKit = new ExcelReadKit();
		ExcelReadResultBean excelReadResultBean = excelReadKit.readExcel(filePath, 0);
		if (excelReadResultBean != null && excelReadResultBean.getResult()) {
			// 解析Excel数据,从第二行开始读起
			DispatchOrderImportBusiness dispatchOrderImportBusiness = new DispatchOrderImportBusiness();
			try {
				ImportResultBean result = dispatchOrderImportBusiness.importOrderInfoList(userId, excelReadResultBean);
				request.setAttribute("ImportResult", result);
				// 获取运输公司列表
				ExpressCompanyInfoClientCache expressCompanyInfoClientCache = new ExpressCompanyInfoClientCache();
				List<ExpressCompanyInfoBean> expressCompanyInfoList = expressCompanyInfoClientCache.getExpressCompanyInfoAllList();
				request.setAttribute("ExpressCompanyInfoList", expressCompanyInfoList);
				RequestDispatcher rd = request.getRequestDispatcher(DispatchConfig.PAGE_PATH + "DispatchOrderInfoImport.jsp");
				rd.forward(request, response);

			} catch (Exception e) {
				HttpResponseKit.alertMessage(response, "导入失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
			return;
		} else {
			HttpResponseKit.alertMessage(response, excelReadResultBean.getErrMsg(), "/dispatch/DispatchOrderInfoList.do");
			return;
		}
	}

}
