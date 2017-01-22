/*
 * JSP generated by Resin Professional 4.0.48 (built Wed, 17 Feb 2016 11:03:24 PST)
 */

package _jsp._web_22dinf._pages._oms._order;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import java.util.*;
import com.lpmas.framework.bean.*;
import com.lpmas.framework.page.*;
import com.lpmas.framework.util.*;
import com.lpmas.framework.web.*;
import com.lpmas.oms.order.bean.*;
import com.lpmas.oms.order.config.*;
import com.lpmas.oms.config.*;
import com.lpmas.admin.business.*;
import com.lpmas.admin.config.*;
import com.lpmas.system.bean.*;

public class _salesorderinfolist__jsp extends com.caucho.jsp.JavaPage
{
  private static final java.util.HashMap<String,java.lang.reflect.Method> _jsp_functionMap = new java.util.HashMap<String,java.lang.reflect.Method>();
  private boolean _caucho_isDead;
  private boolean _caucho_isNotModified;
  private com.caucho.jsp.PageManager _jsp_pageManager;
  
  public void
  _jspService(javax.servlet.http.HttpServletRequest request,
              javax.servlet.http.HttpServletResponse response)
    throws java.io.IOException, javax.servlet.ServletException
  {
    javax.servlet.http.HttpSession session = request.getSession(true);
    com.caucho.server.webapp.WebApp _jsp_application = _caucho_getApplication();
    com.caucho.jsp.PageContextImpl pageContext = _jsp_pageManager.allocatePageContext(this, _jsp_application, request, response, null, session, 8192, true, false);

    TagState _jsp_state = null;

    try {
      _jspService(request, response, pageContext, _jsp_application, session, _jsp_state);
    } catch (java.lang.Throwable _jsp_e) {
      pageContext.handlePageException(_jsp_e);
    } finally {
      _jsp_pageManager.freePageContext(pageContext);
    }
  }
  
  private void
  _jspService(javax.servlet.http.HttpServletRequest request,
              javax.servlet.http.HttpServletResponse response,
              com.caucho.jsp.PageContextImpl pageContext,
              javax.servlet.ServletContext application,
              javax.servlet.http.HttpSession session,
              TagState _jsp_state)
    throws Throwable
  {
    javax.servlet.jsp.JspWriter out = pageContext.getOut();
    final javax.el.ELContext _jsp_env = pageContext.getELContext();
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.Servlet page = this;
    javax.servlet.jsp.tagext.JspTag _jsp_parent_tag = null;
    com.caucho.jsp.PageContextImpl _jsp_parentContext = pageContext;
    response.setContentType("text/html; charset=UTF-8");

    out.write(_jsp_string0, 0, _jsp_string0.length);
    
	AdminUserHelper adminHelper = (AdminUserHelper) request.getAttribute("AdminUserHelper");
	List<SalesOrderInfoBean> list = (List<SalesOrderInfoBean>) request.getAttribute("OrderList");

	PageBean PAGE_BEAN = (PageBean) request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>) request.getAttribute("CondList");

	List<StoreInfoBean> storeList = (List<StoreInfoBean>) request.getAttribute("StoreList");
	Map<Integer, String> storeNameMap = ListKit.list2Map(storeList, "storeId", "storeName");
	
	HashMap<Integer,String> traceSourceInfoMap = (HashMap<Integer,String>) request.getAttribute("TraceSourceInfoMap");

    out.write(_jsp_string1, 0, _jsp_string1.length);
    
String DOMAIN = "lpmas-admin.net";
String STATIC_URL = "http://test.portal.lpmas-admin.net:9010/"; 
String REGION_URL = "http://test.region.lpmas-admin.net:9030";
String TMS_URL = "http://test.tms.lpmas-admin.net:9070";

    out.write(_jsp_string2, 0, _jsp_string2.length);
    out.print((STATIC_URL ));
    out.write(_jsp_string3, 0, _jsp_string3.length);
    out.print((STATIC_URL ));
    out.write(_jsp_string4, 0, _jsp_string4.length);
    out.print((STATIC_URL ));
    out.write(_jsp_string5, 0, _jsp_string5.length);
    out.print((STATIC_URL ));
    out.write(_jsp_string6, 0, _jsp_string6.length);
    out.print((STATIC_URL));
    out.write(_jsp_string7, 0, _jsp_string7.length);
    out.print((STATIC_URL));
    out.write(_jsp_string8, 0, _jsp_string8.length);
    out.print((MapKit.getValueFromMap(SalesOrderStatusConfig.ORDS_APPROVED, SalesOrderStatusConfig.ORDER_STATUS_MAP)));
    out.write(_jsp_string9, 0, _jsp_string9.length);
    out.print((ParamKit.getParameter(request, "soId", "")));
    out.write(_jsp_string10, 0, _jsp_string10.length);
    
					int storeId = ParamKit.getIntParameter(request, "storeId", -1);
					for (StoreInfoBean store : storeList) {
				
    out.write(_jsp_string11, 0, _jsp_string11.length);
    out.print((store.getStoreId()));
    out.write(_jsp_string12, 0, _jsp_string12.length);
    out.print((storeId == store.getStoreId() ? "selected" : ""));
    out.write('>');
    out.print((store.getStoreName()));
    out.write(_jsp_string13, 0, _jsp_string13.length);
    
					}
				
    out.write(_jsp_string14, 0, _jsp_string14.length);
    out.print((ParamKit.getParameter(request, "outerOrderId", "")));
    out.write(_jsp_string15, 0, _jsp_string15.length);
    out.print((ParamKit.getParameter(request, "gtCreateTime", "")));
    out.write(_jsp_string16, 0, _jsp_string16.length);
    out.print((ParamKit.getParameter(request, "ltCreateTime", "")));
    out.write(_jsp_string17, 0, _jsp_string17.length);
    
					String soStatus = ParamKit.getParameter(request, "soStatus", "");
					for (StatusBean<String, String> statusBean : SalesOrderStatusConfig.ORDER_STATUS_LIST) {
				
    out.write(_jsp_string11, 0, _jsp_string11.length);
    out.print((statusBean.getStatus()));
    out.write(_jsp_string12, 0, _jsp_string12.length);
    out.print((statusBean.getStatus().equals(soStatus) ? "selected" : ""));
    out.write('>');
    out.print((statusBean.getValue()));
    out.write(_jsp_string13, 0, _jsp_string13.length);
    
					}
				
    out.write(_jsp_string18, 0, _jsp_string18.length);
    
				for (SalesOrderInfoBean bean : list) {
			
    out.write(_jsp_string19, 0, _jsp_string19.length);
    out.print((bean.getSoId()));
    out.write(_jsp_string20, 0, _jsp_string20.length);
    out.print((bean.getSoId()));
    out.write(_jsp_string21, 0, _jsp_string21.length);
    out.print((MapKit.getValueFromMap(bean.getStoreId(), storeNameMap)));
    out.write(_jsp_string21, 0, _jsp_string21.length);
    out.print((MapKit.getValueFromMap(bean.getSoId(), traceSourceInfoMap)));
    out.write(_jsp_string21, 0, _jsp_string21.length);
    out.print((bean.getOuterOrderId()));
    out.write(_jsp_string21, 0, _jsp_string21.length);
    out.print((bean.getReceiverName()));
    out.write(_jsp_string21, 0, _jsp_string21.length);
    out.print((bean.getProvince() + bean.getCity() + bean.getRegion() + bean.getAddress()));
    out.write(_jsp_string21, 0, _jsp_string21.length);
    out.print((DateKit.formatTimestamp(bean.getCreateTime(), DateKit.DEFAULT_DATE_TIME_FORMAT)));
    out.write(_jsp_string22, 0, _jsp_string22.length);
    out.print((bean.getSoId()));
    out.write(_jsp_string23, 0, _jsp_string23.length);
    out.print((MapKit.getValueFromMap(bean.getSoStatus(), SalesOrderStatusConfig.ORDER_STATUS_MAP)));
    out.write(_jsp_string24, 0, _jsp_string24.length);
    
						if (adminHelper.hasPermission(OmsResource.SALES_ORDER, OperationConfig.UPDATE)) {
					
    out.write(_jsp_string25, 0, _jsp_string25.length);
    out.print((bean.getSoId()));
    out.write(_jsp_string26, 0, _jsp_string26.length);
    
 	}
 
    out.write(_jsp_string27, 0, _jsp_string27.length);
    
				}
			
    out.write(_jsp_string28, 0, _jsp_string28.length);
    
				if (adminHelper.hasPermission(OmsResource.SALES_ORDER, OperationConfig.CREATE)) {
			
    out.write(_jsp_string29, 0, _jsp_string29.length);
    
 			}
 			
    out.write(_jsp_string30, 0, _jsp_string30.length);
    
			 	if (adminHelper.hasPermission(OmsResource.SALES_ORDER, OperationConfig.UPDATE)) {
			 
    out.write(_jsp_string31, 0, _jsp_string31.length);
    
			 	}
			 
    out.write(_jsp_string32, 0, _jsp_string32.length);
    for(String[] condArray :COND_LIST){ 
    out.write(_jsp_string33, 0, _jsp_string33.length);
    out.print((condArray[0] ));
    out.write(_jsp_string34, 0, _jsp_string34.length);
    out.print((condArray[0] ));
    out.write(_jsp_string35, 0, _jsp_string35.length);
    out.print((condArray[1] ));
    out.write(_jsp_string36, 0, _jsp_string36.length);
    } 
    out.write(_jsp_string37, 0, _jsp_string37.length);
    if(PAGE_BEAN.getCurrentPageNumber()>1){ 
    out.write(_jsp_string38, 0, _jsp_string38.length);
    out.print((PAGE_BEAN.getPrePageNumber() ));
    out.write(_jsp_string39, 0, _jsp_string39.length);
    } 
    out.write(_jsp_string40, 0, _jsp_string40.length);
    out.print((PAGE_BEAN.getTotalRecordNumber() ));
    out.write(_jsp_string41, 0, _jsp_string41.length);
    out.print((PAGE_BEAN.getPageSize() ));
    out.write(_jsp_string42, 0, _jsp_string42.length);
    out.print((PAGE_BEAN.getCurrentPageNumber() ));
    out.write(_jsp_string43, 0, _jsp_string43.length);
    out.print((PAGE_BEAN.getTotalPageNumber() ));
    out.write(_jsp_string44, 0, _jsp_string44.length);
    if(PAGE_BEAN.getCurrentPageNumber() < PAGE_BEAN.getTotalPageNumber()){ 
    out.write(_jsp_string45, 0, _jsp_string45.length);
    out.print((PAGE_BEAN.getNextPageNumber() ));
    out.write(_jsp_string46, 0, _jsp_string46.length);
    out.print((PAGE_BEAN.getTotalPageNumber() ));
    out.write(_jsp_string47, 0, _jsp_string47.length);
    } 
    out.write(_jsp_string48, 0, _jsp_string48.length);
    out.print((PAGE_BEAN.getTotalPageNumber() ));
    out.write(_jsp_string49, 0, _jsp_string49.length);
  }

  private com.caucho.make.DependencyContainer _caucho_depends
    = new com.caucho.make.DependencyContainer();

  public java.util.ArrayList<com.caucho.vfs.Dependency> _caucho_getDependList()
  {
    return _caucho_depends.getDependencies();
  }

  public void _caucho_addDepend(com.caucho.vfs.PersistentDependency depend)
  {
    super._caucho_addDepend(depend);
    _caucho_depends.add(depend);
  }

  protected void _caucho_setNeverModified(boolean isNotModified)
  {
    _caucho_isNotModified = true;
  }

  public boolean _caucho_isModified()
  {
    if (_caucho_isDead)
      return true;

    if (_caucho_isNotModified)
      return false;

    if (com.caucho.server.util.CauchoSystem.getVersionId() != -8002497470487589159L)
      return true;

    return _caucho_depends.isModified();
  }

  public long _caucho_lastModified()
  {
    return 0;
  }

  public void destroy()
  {
      _caucho_isDead = true;
      super.destroy();
    TagState tagState;
  }

  public void init(com.caucho.vfs.Path appDir)
    throws javax.servlet.ServletException
  {
    com.caucho.vfs.Path resinHome = com.caucho.server.util.CauchoSystem.getResinHome();
    com.caucho.vfs.MergePath mergePath = new com.caucho.vfs.MergePath();
    mergePath.addMergePath(appDir);
    mergePath.addMergePath(resinHome);
    com.caucho.loader.DynamicClassLoader loader;
    loader = (com.caucho.loader.DynamicClassLoader) getClass().getClassLoader();
    String resourcePath = loader.getResourcePathSpecificFirst();
    mergePath.addClassPath(resourcePath);
    com.caucho.vfs.Depend depend;
    depend = new com.caucho.vfs.Depend(appDir.lookup("WEB-INF/pages/oms/order/SalesOrderInfoList.jsp"), 7699865841478638845L, false);
    _caucho_depends.add(depend);
    loader.addDependency(depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("WEB-INF/pages/include/header.jsp"), -8805852070666955107L, false);
    _caucho_depends.add(depend);
    loader.addDependency(depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("WEB-INF/pages/include/page.jsp"), -7400274417948715423L, false);
    _caucho_depends.add(depend);
    loader.addDependency(depend);
  }

  final static class TagState {

    void release()
    {
    }
  }

  public java.util.HashMap<String,java.lang.reflect.Method> _caucho_getFunctionMap()
  {
    return _jsp_functionMap;
  }

  public void caucho_init(ServletConfig config)
  {
    try {
      com.caucho.server.webapp.WebApp webApp
        = (com.caucho.server.webapp.WebApp) config.getServletContext();
      init(config);
      if (com.caucho.jsp.JspManager.getCheckInterval() >= 0)
        _caucho_depends.setCheckInterval(com.caucho.jsp.JspManager.getCheckInterval());
      _jsp_pageManager = webApp.getJspApplicationContext().getPageManager();
      com.caucho.jsp.TaglibManager manager = webApp.getJspApplicationContext().getTaglibManager();
      com.caucho.jsp.PageContextImpl pageContext = new com.caucho.jsp.InitPageContextImpl(webApp, this);
    } catch (Exception e) {
      throw com.caucho.config.ConfigException.create(e);
    }
  }

  private final static char []_jsp_string5;
  private final static char []_jsp_string47;
  private final static char []_jsp_string41;
  private final static char []_jsp_string12;
  private final static char []_jsp_string38;
  private final static char []_jsp_string11;
  private final static char []_jsp_string27;
  private final static char []_jsp_string14;
  private final static char []_jsp_string7;
  private final static char []_jsp_string23;
  private final static char []_jsp_string20;
  private final static char []_jsp_string30;
  private final static char []_jsp_string19;
  private final static char []_jsp_string13;
  private final static char []_jsp_string46;
  private final static char []_jsp_string42;
  private final static char []_jsp_string17;
  private final static char []_jsp_string1;
  private final static char []_jsp_string18;
  private final static char []_jsp_string21;
  private final static char []_jsp_string35;
  private final static char []_jsp_string24;
  private final static char []_jsp_string37;
  private final static char []_jsp_string33;
  private final static char []_jsp_string49;
  private final static char []_jsp_string8;
  private final static char []_jsp_string0;
  private final static char []_jsp_string32;
  private final static char []_jsp_string28;
  private final static char []_jsp_string31;
  private final static char []_jsp_string48;
  private final static char []_jsp_string34;
  private final static char []_jsp_string44;
  private final static char []_jsp_string3;
  private final static char []_jsp_string2;
  private final static char []_jsp_string15;
  private final static char []_jsp_string43;
  private final static char []_jsp_string4;
  private final static char []_jsp_string10;
  private final static char []_jsp_string6;
  private final static char []_jsp_string29;
  private final static char []_jsp_string26;
  private final static char []_jsp_string45;
  private final static char []_jsp_string36;
  private final static char []_jsp_string16;
  private final static char []_jsp_string25;
  private final static char []_jsp_string9;
  private final static char []_jsp_string22;
  private final static char []_jsp_string39;
  private final static char []_jsp_string40;
  static {
    _jsp_string5 = "js/common.js\"></script>\r\n<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string47 = ");\">&nbsp;</em>".toCharArray();
    _jsp_string41 = "</b>\u6761  \u6bcf\u9875\u663e\u793a<b>".toCharArray();
    _jsp_string12 = "\" ".toCharArray();
    _jsp_string38 = "<em class=\"first\" onclick=\"javascript:goPage('formPage',1);\">&nbsp;</em>\r\n  <em class=\"pre\" onclick=\"javascript:goPage('formPage',".toCharArray();
    _jsp_string11 = "\r\n				<option value=\"".toCharArray();
    _jsp_string27 = "\r\n				</td>\r\n			</tr>\r\n			".toCharArray();
    _jsp_string14 = "\r\n			</select>\r\n			<em class=\"em1\">\u5916\u90e8\u8ba2\u5355\u53f7\uff1a</em>\r\n			<input type=\"text\" name=\"outerOrderId\" id=\"outerOrderId\" value=\"".toCharArray();
    _jsp_string7 = "/js/My97DatePicker/skin/WdatePicker.css\" rel=\"stylesheet\" type=\"text/css\">\r\n<script type='text/javascript'src=\"".toCharArray();
    _jsp_string23 = "\">".toCharArray();
    _jsp_string20 = "\" /></td>\r\n				<td>".toCharArray();
    _jsp_string30 = " \r\n 			 -->\r\n 			".toCharArray();
    _jsp_string19 = "\r\n			<tr>\r\n				<td><input type=\"checkbox\" name=\"selectSoId\" id=\"selectSoId\" value=\"".toCharArray();
    _jsp_string13 = "</option>\r\n				".toCharArray();
    _jsp_string46 = ");\">&nbsp;</em>\r\n  <em class=\"last\" onclick=\"javascript:goPage('formPage',".toCharArray();
    _jsp_string42 = "</b>\u6761 \u5f53\u524d\u7b2c<b>".toCharArray();
    _jsp_string17 = "\" />\r\n			<em class=\"em1\">\u8ba2\u5355\u72b6\u6001\uff1a</em>\r\n			<select id=\"soStatus\" name=\"soStatus\">\r\n				<option value=\"\"></option>\r\n				".toCharArray();
    _jsp_string1 = "\r\n<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n<html>\r\n<head>\r\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n<title>\u9500\u552e\u8ba2\u5355\u5217\u8868</title>\r\n\r\n".toCharArray();
    _jsp_string18 = "\r\n			</select>\r\n			<input name=\"\" type=\"submit\" class=\"search_btn_sub\" value=\"\u67e5\u8be2\" />\r\n		</div>\r\n		<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table_style\">\r\n			<tr>\r\n				<th><input type=\"checkbox\" name=\"selectAllSoId\" id=\"selectAllSoId\" onclick=\"javascript:changeChiefCheckbox('selectAllSoId','selectSoId');\" /></th>\r\n				<th>\u8ba2\u5355ID</th>\r\n				<th>\u5546\u5e97</th>\r\n				<th>\u6765\u6e90</th>\r\n				<th>\u5916\u90e8\u8ba2\u5355\u53f7</th>\r\n				<th>\u6536\u8d27\u4eba</th>\r\n				<th>\u6536\u8d27\u5730\u5740</th>\r\n				<th>\u521b\u5efa\u65f6\u95f4</th>\r\n				<th>\u8ba2\u5355\u72b6\u6001</th>\r\n				<th>\u64cd\u4f5c</th>\r\n			</tr>\r\n			".toCharArray();
    _jsp_string21 = "</td>\r\n				<td>".toCharArray();
    _jsp_string35 = "\" value=\"".toCharArray();
    _jsp_string24 = "</td>\r\n				<td align=\"center\">\r\n					".toCharArray();
    _jsp_string37 = "\r\n</form>\r\n<li class=\"page_num\">\r\n  ".toCharArray();
    _jsp_string33 = "\r\n<input type=\"hidden\" name=\"".toCharArray();
    _jsp_string49 = ");\"/> \r\n</li>\r\n	</ul>\r\n</body>\r\n</html>".toCharArray();
    _jsp_string8 = "/js/My97DatePicker/WdatePicker.js\"></script>\r\n<script language=\"javascript\">\r\n	function approveSaleOrder() {\r\n		var soIds = getCheckboxValue('selectSoId', ',');\r\n		if (strLength(soIds) == 0) {\r\n			alert(\"\u8bf7\u9009\u62e9\u9700\u8981\u5ba1\u6279\u7684\u8ba2\u5355\");\r\n			return;\r\n		}\r\n		jQuery.getJSON(\"SalesOrderApprove.do\", {\r\n			soIds : soIds\r\n		}, function(data) {\r\n			var obj = jQuery.parseJSON(data);\r\n			var failResult = \"\";\r\n			for(var j=0;j<obj.failList.length;j++){\r\n				var soId = obj.failList[j];\r\n				failResult += \"\\n [\"+soId+\"]\u5ba1\u6838\u5931\u8d25\"\r\n			}\r\n			if(obj.failList.length >0){\r\n				alert(\"\u8ba2\u5355\u5ba1\u6279\u5b8c\u6210,\u4ee5\u4e0b\u8ba2\u5355\u5ba1\u6838\u5931\u8d25\"+failResult);\r\n			}else{\r\n				alert(\"\u8ba2\u5355\u5ba1\u6279\u5b8c\u6210\")\r\n			}\r\n			\r\n			for(var i=0;i < obj.successList.length;i++){\r\n				var soId = obj.successList[i];\r\n				jQuery(\"#soStatus_\"+soId).html(\"".toCharArray();
    _jsp_string0 = "\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n".toCharArray();
    _jsp_string32 = "\r\n		</li>\r\n		\r\n<form name=\"formPage\" method=\"post\" action=\"\">\r\n<input type=\"hidden\" name=\"pageNum\" id=\"pageNum\" value=\"\"/>\r\n".toCharArray();
    _jsp_string28 = "\r\n		</table>\r\n	</form>\r\n	<ul class=\"page_info\">\r\n		<li class=\"page_left_btn\">\r\n			<!-- \r\n			".toCharArray();
    _jsp_string31 = "<input type=\"button\" name=\"btnApprove\" id=\"btnApprove\" value=\"\u5ba1\u6279\" onclick=\"javascript:approveSaleOrder();\"> ".toCharArray();
    _jsp_string48 = "\r\n  <input name=\"goPageNum\" id=\"goPageNum\" type=\"text\" class=\"page_int_txt\"/><input name=\"\" type=\"button\" class=\"page_btn_go\" value=\"GO\" onclick=\"javascript:goInputPage('formPage',".toCharArray();
    _jsp_string34 = "\" id=\"".toCharArray();
    _jsp_string44 = "\u9875 \r\n  ".toCharArray();
    _jsp_string3 = "css/main.css\" type=\"text/css\" rel=\"stylesheet\" />\r\n<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string2 = "\r\n<link href=\"".toCharArray();
    _jsp_string15 = "\" size=\"20\" />\r\n			<em class=\"em1\">\u521b\u5efa\u65f6\u95f4\uff1a</em>\r\n			<input type=\"text\" name=\"gtCreateTime\" id=\"gtCreateTime\" onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\" size=\"20\" value=\"".toCharArray();
    _jsp_string43 = "</b>/".toCharArray();
    _jsp_string4 = "js/jquery.js\"></script>\r\n<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string10 = "\" size=\"20\" />\r\n			<em class=\"em1\">\u5546\u5e97\uff1a</em>\r\n			<select id=\"storeId\" name=\"storeId\">\r\n				<option value=\"\"></option>\r\n				".toCharArray();
    _jsp_string6 = "js/ui.js\"></script>\r\n<link href=\"".toCharArray();
    _jsp_string29 = "<input type=\"button\" name=\"button\" id=\"button\" value=\"\u65b0\u5efa\" onclick=\"javascript:location.href='SalesOrderInfoManage.do'\"> ".toCharArray();
    _jsp_string26 = "\">\u4fee\u6539</a> ".toCharArray();
    _jsp_string45 = "<em class=\"next\" onclick=\"javascript:goPage('formPage',".toCharArray();
    _jsp_string36 = "\"/>".toCharArray();
    _jsp_string16 = "\" /> - \r\n			<input type=\"text\" name=\"ltCreateTime\" id=\"ltCreateTime\" onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\" size=\"20\" value=\"".toCharArray();
    _jsp_string25 = "<a href=\"SalesOrderInfoManage.do?soId=".toCharArray();
    _jsp_string9 = "\");\r\n			}\r\n			\r\n			\r\n		});\r\n	}\r\n</script>\r\n</head>\r\n<body class=\"article_bg\">\r\n	<p class=\"article_tit\">\u9500\u552e\u8ba2\u5355\u5217\u8868</p>\r\n	<form name=\"formSearch\" method=\"post\" action=\"SalesOrderInfoList.do\">\r\n		<div class=\"search_form\">\r\n			<em class=\"em1\">\u8ba2\u5355ID\uff1a</em>\r\n			<input type=\"text\" name=\"soId\" id=\"soId\" value=\"".toCharArray();
    _jsp_string22 = "</td>\r\n				<td id=\"soStatus_".toCharArray();
    _jsp_string39 = ");\"> &nbsp;</em>".toCharArray();
    _jsp_string40 = "\r\n       \u603b\u8bb0\u5f55<b>".toCharArray();
  }
}
