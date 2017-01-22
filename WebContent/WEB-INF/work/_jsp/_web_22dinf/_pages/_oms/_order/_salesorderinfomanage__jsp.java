/*
 * JSP generated by Resin Professional 4.0.48 (built Wed, 17 Feb 2016 11:03:24 PST)
 */

package _jsp._web_22dinf._pages._oms._order;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import java.util.*;
import com.lpmas.framework.bean.*;
import com.lpmas.framework.config.*;
import com.lpmas.framework.util.*;
import com.lpmas.framework.web.*;
import com.lpmas.admin.bean.*;
import com.lpmas.admin.business.*;
import com.lpmas.system.bean.*;
import com.lpmas.region.bean.*;
import com.lpmas.constant.currency.*;
import com.lpmas.oms.order.bean.*;
import com.lpmas.oms.order.config.*;

public class _salesorderinfomanage__jsp extends com.caucho.jsp.JavaPage
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
     
SalesOrderInfoBean orderInfoBean = (SalesOrderInfoBean)request.getAttribute("OrderInfo");
List<SalesOrderItemBean> orderItemList = (List<SalesOrderItemBean>)request.getAttribute("OrderItemList");
AdminUserHelper adminHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");

List<StoreInfoBean> storeList = (List<StoreInfoBean>)request.getAttribute("StoreList");

List<CountryInfoBean> countryList = (List<CountryInfoBean>)request.getAttribute("CountryList");
List<ProvinceInfoBean> provinceList = (List<ProvinceInfoBean>)request.getAttribute("ProvinceList");
List<CityInfoBean> cityList = (List<CityInfoBean>)request.getAttribute("CityList");
List<RegionInfoBean> regionList = (List<RegionInfoBean>)request.getAttribute("RegionList");

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
    out.print((REGION_URL));
    out.write(_jsp_string8, 0, _jsp_string8.length);
    out.print((orderInfoBean.getSoId() ));
    out.write(_jsp_string9, 0, _jsp_string9.length);
    out.print((orderInfoBean.getCostomerId() ));
    out.write(_jsp_string10, 0, _jsp_string10.length);
    
      	for(StoreInfoBean storeInfoBean : storeList){
      
    out.write(_jsp_string11, 0, _jsp_string11.length);
    out.print((storeInfoBean.getStoreId() ));
    out.write(_jsp_string12, 0, _jsp_string12.length);
    out.print(((storeInfoBean.getStoreId()==orderInfoBean.getStoreId())?"selected":"" ));
    out.write('>');
    out.print((storeInfoBean.getStoreName() ));
    out.write(_jsp_string13, 0, _jsp_string13.length);
    } 
    out.write(_jsp_string14, 0, _jsp_string14.length);
    out.print((orderInfoBean.getOuterOrderId() ));
    out.write(_jsp_string15, 0, _jsp_string15.length);
    out.print((orderInfoBean.getProvince() ));
    out.write(_jsp_string16, 0, _jsp_string16.length);
    
      	for(CountryInfoBean countryInfoBean : countryList){
      
    out.write(_jsp_string11, 0, _jsp_string11.length);
    out.print((countryInfoBean.getCountryName() ));
    out.write(_jsp_string12, 0, _jsp_string12.length);
    out.print(((countryInfoBean.getCountryName().equals(orderInfoBean.getCountry()))?"selected":"" ));
    out.write('>');
    out.print((countryInfoBean.getCountryName() ));
    out.write(_jsp_string13, 0, _jsp_string13.length);
    } 
    out.write(_jsp_string17, 0, _jsp_string17.length);
    out.print((orderInfoBean.getCity() ));
    out.write(_jsp_string16, 0, _jsp_string16.length);
    
      	for(ProvinceInfoBean provinceInfoBean : provinceList){
      
    out.write(_jsp_string11, 0, _jsp_string11.length);
    out.print((provinceInfoBean.getProvinceName() ));
    out.write(_jsp_string12, 0, _jsp_string12.length);
    out.print(((provinceInfoBean.getProvinceName().equals(orderInfoBean.getProvince()))?"selected":"" ));
    out.write('>');
    out.print((provinceInfoBean.getProvinceName() ));
    out.write(_jsp_string13, 0, _jsp_string13.length);
    } 
    out.write(_jsp_string18, 0, _jsp_string18.length);
    out.print((orderInfoBean.getRegion() ));
    out.write(_jsp_string16, 0, _jsp_string16.length);
    
      	for(CityInfoBean cityInfoBean : cityList){
      
    out.write(_jsp_string11, 0, _jsp_string11.length);
    out.print((cityInfoBean.getCityName() ));
    out.write(_jsp_string12, 0, _jsp_string12.length);
    out.print(((cityInfoBean.getCityName().equals(orderInfoBean.getCity()))?"selected":"" ));
    out.write('>');
    out.print((cityInfoBean.getCityName() ));
    out.write(_jsp_string13, 0, _jsp_string13.length);
    } 
    out.write(_jsp_string19, 0, _jsp_string19.length);
    
      	for(RegionInfoBean regionInfoBean : regionList){
      
    out.write(_jsp_string11, 0, _jsp_string11.length);
    out.print((regionInfoBean.getRegionName() ));
    out.write(_jsp_string12, 0, _jsp_string12.length);
    out.print(((regionInfoBean.getRegionName().equals(orderInfoBean.getRegion()))?"selected":"" ));
    out.write('>');
    out.print((regionInfoBean.getRegionName() ));
    out.write(_jsp_string13, 0, _jsp_string13.length);
    } 
    out.write(_jsp_string20, 0, _jsp_string20.length);
    out.print((orderInfoBean.getAddress() ));
    out.write(_jsp_string21, 0, _jsp_string21.length);
    out.print((orderInfoBean.getReceiverName() ));
    out.write(_jsp_string22, 0, _jsp_string22.length);
    out.print((orderInfoBean.getTelephone() ));
    out.write(_jsp_string23, 0, _jsp_string23.length);
    out.print((orderInfoBean.getMobile() ));
    out.write(_jsp_string24, 0, _jsp_string24.length);
    out.print((orderInfoBean.getZipCode() ));
    out.write(_jsp_string25, 0, _jsp_string25.length);
    out.print((orderInfoBean.getInvoiceNumber() ));
    out.write(_jsp_string26, 0, _jsp_string26.length);
    out.print((orderInfoBean.getInvoiceType() ));
    out.write(_jsp_string27, 0, _jsp_string27.length);
    out.print((orderInfoBean.getInvoiceTitle() ));
    out.write(_jsp_string28, 0, _jsp_string28.length);
    out.print((orderInfoBean.getInvoiceAmount() ));
    out.write(_jsp_string29, 0, _jsp_string29.length);
    out.print((orderInfoBean.getTransporterId() ));
    out.write(_jsp_string30, 0, _jsp_string30.length);
    out.print((orderInfoBean.getTransportNumber() ));
    out.write(_jsp_string31, 0, _jsp_string31.length);
    
      	for(StatusBean<String, String> currency:CurrencyConfig.CURRENCY_LIST){
      
    out.write(_jsp_string11, 0, _jsp_string11.length);
    out.print((currency.getStatus() ));
    out.write(_jsp_string12, 0, _jsp_string12.length);
    out.print(((currency.getStatus().equals(orderInfoBean.getCurrency()))?"selected":"" ));
    out.write('>');
    out.print((currency.getValue() ));
    out.write(_jsp_string13, 0, _jsp_string13.length);
    } 
    out.write(_jsp_string32, 0, _jsp_string32.length);
    out.print((orderInfoBean.getProductAmount() ));
    out.write(_jsp_string33, 0, _jsp_string33.length);
    out.print((orderInfoBean.getProductDiscountAmount() ));
    out.write(_jsp_string34, 0, _jsp_string34.length);
    out.print((orderInfoBean.getProductFactAmount() ));
    out.write(_jsp_string35, 0, _jsp_string35.length);
    out.print((orderInfoBean.getFreight() ));
    out.write(_jsp_string36, 0, _jsp_string36.length);
    out.print((orderInfoBean.getDiscountFreight() ));
    out.write(_jsp_string37, 0, _jsp_string37.length);
    out.print((orderInfoBean.getFactFreight() ));
    out.write(_jsp_string38, 0, _jsp_string38.length);
    out.print((orderInfoBean.getSoAmount() ));
    out.write(_jsp_string39, 0, _jsp_string39.length);
    out.print((orderInfoBean.getSoDiscountAmount() ));
    out.write(_jsp_string40, 0, _jsp_string40.length);
    out.print((orderInfoBean.getSoFactAmount() ));
    out.write(_jsp_string41, 0, _jsp_string41.length);
    out.print((orderInfoBean.getTotalQuantity() ));
    out.write(_jsp_string42, 0, _jsp_string42.length);
    
      	for(StatusBean<Integer, String> statusBean : SalesOrderConfig.ORDER_TYPE_LIST){
      
    out.write(_jsp_string11, 0, _jsp_string11.length);
    out.print((statusBean.getStatus() ));
    out.write(_jsp_string12, 0, _jsp_string12.length);
    out.print(((statusBean.getStatus()==orderInfoBean.getSoType())?"selected":"" ));
    out.write('>');
    out.print((statusBean.getValue() ));
    out.write(_jsp_string13, 0, _jsp_string13.length);
    } 
    out.write(_jsp_string43, 0, _jsp_string43.length);
    out.print((DateKit.formatTimestamp(orderInfoBean.getPayTime(), "yyyy-MM-dd HH:mm:ss")));
    out.write(_jsp_string44, 0, _jsp_string44.length);
    out.print((orderInfoBean.getOrderFrom() ));
    out.write(_jsp_string45, 0, _jsp_string45.length);
    out.print((orderInfoBean.getTradeSource() ));
    out.write(_jsp_string46, 0, _jsp_string46.length);
    out.print((orderInfoBean.getTradeSourceId1() ));
    out.write(_jsp_string47, 0, _jsp_string47.length);
    out.print((orderInfoBean.getTradeSourceId2() ));
    out.write(_jsp_string48, 0, _jsp_string48.length);
    		
		for (StatusBean<String,String> statusBean : SalesOrderStatusConfig.ORDER_STATUS_LIST) {
	
    out.write(_jsp_string49, 0, _jsp_string49.length);
    out.print((statusBean.getStatus() ));
    out.write(_jsp_string12, 0, _jsp_string12.length);
    out.print((statusBean.getStatus().equals(orderInfoBean.getSoStatus()) ? "selected" : ""));
    out.write('>');
    out.print((statusBean.getValue() ));
    out.write(_jsp_string50, 0, _jsp_string50.length);
    
		}
	
    out.write(_jsp_string51, 0, _jsp_string51.length);
    out.print((orderInfoBean.getUserComment() ));
    out.write(_jsp_string52, 0, _jsp_string52.length);
    out.print((orderInfoBean.getMemo() ));
    out.write(_jsp_string53, 0, _jsp_string53.length);
    
    for(SalesOrderItemBean itemBean:orderItemList){
    
    out.write(_jsp_string54, 0, _jsp_string54.length);
    out.print((itemBean.getSoItemId() ));
    out.write(_jsp_string55, 0, _jsp_string55.length);
    out.print((itemBean.getOuterOrderItemId() ));
    out.write(_jsp_string55, 0, _jsp_string55.length);
    out.print((itemBean.getProductId() ));
    out.write(_jsp_string55, 0, _jsp_string55.length);
    out.print((itemBean.getProductItemId() ));
    out.write(_jsp_string55, 0, _jsp_string55.length);
    out.print((itemBean.getProductItemNumber() ));
    out.write(_jsp_string55, 0, _jsp_string55.length);
    out.print((itemBean.getProductName() ));
    out.write(_jsp_string55, 0, _jsp_string55.length);
    out.print((itemBean.getFactPrice() ));
    out.write(_jsp_string55, 0, _jsp_string55.length);
    out.print((itemBean.getQuantity() ));
    out.write(_jsp_string55, 0, _jsp_string55.length);
    out.print((itemBean.getItemFactAmount() ));
    out.write(_jsp_string56, 0, _jsp_string56.length);
    } 
    out.write(_jsp_string57, 0, _jsp_string57.length);
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
    depend = new com.caucho.vfs.Depend(appDir.lookup("WEB-INF/pages/oms/order/SalesOrderInfoManage.jsp"), -1250313393935906355L, false);
    _caucho_depends.add(depend);
    loader.addDependency(depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("WEB-INF/pages/include/header.jsp"), -8805852070666955107L, false);
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
  private final static char []_jsp_string40;
  private final static char []_jsp_string14;
  private final static char []_jsp_string12;
  private final static char []_jsp_string34;
  private final static char []_jsp_string26;
  private final static char []_jsp_string28;
  private final static char []_jsp_string15;
  private final static char []_jsp_string8;
  private final static char []_jsp_string21;
  private final static char []_jsp_string52;
  private final static char []_jsp_string27;
  private final static char []_jsp_string29;
  private final static char []_jsp_string55;
  private final static char []_jsp_string37;
  private final static char []_jsp_string22;
  private final static char []_jsp_string17;
  private final static char []_jsp_string16;
  private final static char []_jsp_string38;
  private final static char []_jsp_string39;
  private final static char []_jsp_string50;
  private final static char []_jsp_string20;
  private final static char []_jsp_string9;
  private final static char []_jsp_string19;
  private final static char []_jsp_string25;
  private final static char []_jsp_string42;
  private final static char []_jsp_string41;
  private final static char []_jsp_string56;
  private final static char []_jsp_string30;
  private final static char []_jsp_string3;
  private final static char []_jsp_string44;
  private final static char []_jsp_string2;
  private final static char []_jsp_string6;
  private final static char []_jsp_string48;
  private final static char []_jsp_string4;
  private final static char []_jsp_string53;
  private final static char []_jsp_string31;
  private final static char []_jsp_string23;
  private final static char []_jsp_string43;
  private final static char []_jsp_string13;
  private final static char []_jsp_string32;
  private final static char []_jsp_string0;
  private final static char []_jsp_string45;
  private final static char []_jsp_string7;
  private final static char []_jsp_string10;
  private final static char []_jsp_string54;
  private final static char []_jsp_string49;
  private final static char []_jsp_string18;
  private final static char []_jsp_string57;
  private final static char []_jsp_string1;
  private final static char []_jsp_string36;
  private final static char []_jsp_string51;
  private final static char []_jsp_string24;
  private final static char []_jsp_string35;
  private final static char []_jsp_string33;
  private final static char []_jsp_string46;
  private final static char []_jsp_string11;
  static {
    _jsp_string5 = "js/common.js\"></script>\r\n<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string47 = "\" checkStr=\"\u4ea4\u6613\u6765\u6e901;txt;false;;30\"/>\r\n      <input type=\"text\" name=\"tradeSourceId2\" id=\"tradeSourceId2\" size=\"30\" maxlength=\"30\" value=\"".toCharArray();
    _jsp_string40 = "\" checkStr=\"\u8ba2\u5355\u6298\u6263\u91d1\u989d;num;false;;30\"/>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u8ba2\u5355\u5b9e\u9645\u91d1\u989d\uff1a</em>\r\n      <input type=\"text\" name=\"soFactAmount\" id=\"soFactAmount\" size=\"30\" maxlength=\"30\" value=\"".toCharArray();
    _jsp_string14 = "\r\n      </select>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u5916\u90e8\u8ba2\u5355\u53f7\uff1a</em>\r\n      <input type=\"text\" name=\"outerOrderId\" id=\"outerOrderId\" size=\"30\" maxlength=\"100\" value=\"".toCharArray();
    _jsp_string12 = "\" ".toCharArray();
    _jsp_string34 = "\" checkStr=\"\u8d27\u54c1\u6298\u6263\u91d1\u989d;num;false;;30\"/>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u8d27\u54c1\u5b9e\u9645\u91d1\u989d\uff1a</em>\r\n      <input type=\"text\" name=\"productFactAmount\" id=\"productFactAmount\" size=\"30\" maxlength=\"30\" value=\"".toCharArray();
    _jsp_string26 = "\" checkStr=\"\u53d1\u7968\u53f7;txt;false;;50\"/>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u53d1\u7968\u7c7b\u578b\uff1a</em>\r\n      <input type=\"text\" name=\"invoiceType\" id=\"invoiceType\" size=\"10\" maxlength=\"10\" value=\"".toCharArray();
    _jsp_string28 = "\" checkStr=\"\u53d1\u7968\u62ac\u5934;txt;false;;100\"/>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u53d1\u7968\u91d1\u989d\uff1a</em>\r\n      <input type=\"text\" name=\"invoiceAmount\" id=\"invoiceAmount\" size=\"20\" maxlength=\"20\" value=\"".toCharArray();
    _jsp_string15 = "\" checkStr=\"\u5916\u90e8\u8ba2\u5355\u53f7;txt;false;;100\"/>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u5730\u5740\uff1a</em>    \r\n      <select name=\"country\" id=\"country\" style=\"width:120px\" onchange=\"javascript:getProvinceNameList('country','province','".toCharArray();
    _jsp_string8 = "/region/RegionAjaxList.do\"></script>\r\n</head>\r\n<body class=\"article_bg\">\r\n<p class=\"article_tit\">\u9500\u552e\u8ba2\u5355\u7ba1\u7406</p>\r\n<form id=\"formData\" name=\"formData\" method=\"post\" action=\"SalesOrderInfoManage.do\" onsubmit=\"javascript:return checkForm('formData');\">\r\n  <input type=\"hidden\" name=\"soId\" id=\"soId\" value=\"".toCharArray();
    _jsp_string21 = "\" checkStr=\"\u5730\u5740;txt;true;;100\"/><em><span>*</span></em>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u6536\u8d27\u4eba\u59d3\u540d\uff1a</em>\r\n      <input type=\"text\" name=\"receiverName\" id=\"receiverName\" size=\"30\" maxlength=\"100\" value=\"".toCharArray();
    _jsp_string52 = "</textarea>\r\n    </p>\r\n    <p class=\"p_top_border\">\r\n      <em class=\"int_label\">\u5907\u6ce8\uff1a</em>\r\n      <textarea name=\"memo\" id=\"memo\" cols=\"60\" rows=\"3\" checkStr=\"\u5907\u6ce8;txt;false;;1000\">".toCharArray();
    _jsp_string27 = "\" checkStr=\"\u53d1\u7968\u7c7b\u578b;txt;false;;50\"/>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u53d1\u7968\u62ac\u5934\uff1a</em>\r\n      <input type=\"text\" name=\"invoiceTitle\" id=\"invoiceTitle\" size=\"30\" maxlength=\"50\" value=\"".toCharArray();
    _jsp_string29 = "\" checkStr=\"\u53d1\u7968\u91d1\u989d;num;false;;10\"/>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u8fd0\u8f93\u516c\u53f8\uff1a</em>\r\n      <input type=\"text\" name=\"transporterId\" id=\"transporterId\" size=\"20\" maxlength=\"20\" value=\"".toCharArray();
    _jsp_string55 = "</td>\r\n      <td>".toCharArray();
    _jsp_string37 = "\" checkStr=\"\u6298\u6263\u90ae\u8d39;num;false;;30\"/>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u5b9e\u9645\u90ae\u8d39\uff1a</em>\r\n      <input type=\"text\" name=\"factFreight\" id=\"factFreight\" size=\"30\" maxlength=\"30\" value=\"".toCharArray();
    _jsp_string22 = "\" checkStr=\"\u6536\u8d27\u4eba\u59d3\u540d;txt;true;;100\"/><em><span>*</span></em>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u7535\u8bdd\uff1a</em>\r\n      <input type=\"text\" name=\"telephone\" id=\"telephone\" size=\"20\" maxlength=\"30\" value=\"".toCharArray();
    _jsp_string17 = "\r\n      </select>\r\n      <select name=\"province\" id=\"province\" style=\"width:120px\" onchange=\"javascript:getCityNameList('province','city','".toCharArray();
    _jsp_string16 = "');\">\r\n        <option></option>\r\n      ".toCharArray();
    _jsp_string38 = "\" checkStr=\"\u5b9e\u9645\u90ae\u8d39;num;false;;30\"/>\r\n    </p>    \r\n    <p>\r\n      <em class=\"int_label\">\u8ba2\u5355\u603b\u4ef7\uff1a</em>\r\n      <input type=\"text\" name=\"soAmount\" id=\"soAmount\" size=\"30\" maxlength=\"30\" value=\"".toCharArray();
    _jsp_string39 = "\" checkStr=\"\u8ba2\u5355\u603b\u4ef7;num;false;;30\"/>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u8ba2\u5355\u6298\u6263\u91d1\u989d\uff1a</em>\r\n      <input type=\"text\" name=\"soDiscountAmount\" id=\"soDiscountAmount\" size=\"30\" maxlength=\"30\" value=\"".toCharArray();
    _jsp_string50 = "</option>\r\n	".toCharArray();
    _jsp_string20 = "\r\n      </select>\r\n      <input type=\"text\" name=\"address\" id=\"address\" size=\"50\" maxlength=\"100\" value=\"".toCharArray();
    _jsp_string9 = "\"/>\r\n  <div class=\"modify_form\">\r\n    <p>\r\n      <em class=\"int_label\">\u5ba2\u6237ID\uff1a</em>\r\n      <input type=\"text\" name=\"costomerId\" id=\"costomerId\" size=\"20\" maxlength=\"20\" value=\"".toCharArray();
    _jsp_string19 = "\r\n      </select>\r\n      <select name=\"region\" id=\"region\" style=\"width:120px\">\r\n        <option></option>\r\n      ".toCharArray();
    _jsp_string25 = "\" checkStr=\"\u90ae\u7f16;txt;false;;10\"/>\r\n    </p>    \r\n    <p>\r\n      <em class=\"int_label\">\u53d1\u7968\u53f7\uff1a</em>\r\n      <input type=\"text\" name=\"invoiceNumber\" id=\"invoiceNumber\" size=\"30\" maxlength=\"50\" value=\"".toCharArray();
    _jsp_string42 = "\" checkStr=\"\u603b\u4ef6\u6570;num;false;;30\"/>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u8ba2\u5355\u7c7b\u578b\uff1a</em>\r\n      <select name=\"storeId\" id=\"storeId\">\r\n      ".toCharArray();
    _jsp_string41 = "\" checkStr=\"\u8ba2\u5355\u5b9e\u9645\u91d1\u989d;num;false;;30\"/>\r\n    </p>    \r\n    <p>\r\n      <em class=\"int_label\">\u603b\u4ef6\u6570\uff1a</em>\r\n      <input type=\"text\" name=\"totalQuantity\" id=\"totalQuantity\" size=\"30\" maxlength=\"30\" value=\"".toCharArray();
    _jsp_string56 = "</td>\r\n    </tr>\r\n    ".toCharArray();
    _jsp_string30 = "\" checkStr=\"\u53d1\u7968\u91d1\u989d;num;false;;10\"/>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u8fd0\u8f93\u5355\u53f7\uff1a</em>\r\n      <input type=\"text\" name=\"transportNumber\" id=\"transportNumber\" size=\"30\" maxlength=\"30\" value=\"".toCharArray();
    _jsp_string3 = "css/main.css\" type=\"text/css\" rel=\"stylesheet\" />\r\n<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string44 = "\" readOnly checkStr=\"\u652f\u4ed8\u65f6\u95f4;num;false;;30\" onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\"/>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u8ba2\u5355\u6765\u6e90\uff1a</em>\r\n      <input type=\"text\" name=\"orderFrom\" id=\"orderFrom\" size=\"30\" maxlength=\"30\" value=\"".toCharArray();
    _jsp_string2 = "\r\n<link href=\"".toCharArray();
    _jsp_string6 = "js/ui.js\"></script>\r\n<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string48 = "\" checkStr=\"\u4ea4\u6613\u6765\u6e902;txt;false;;30\"/>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u8ba2\u5355\u72b6\u6001\uff1a</em>\r\n      <select id=\"soStatus\" name=\"soStatus\">\r\n	".toCharArray();
    _jsp_string4 = "js/jquery.js\"></script>\r\n<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string53 = "</textarea>\r\n    </p>\r\n  </div>\r\n  \r\n  <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table_style\">\r\n    <tr>\r\n      <th><input type=\"checkbox\" name=\"selectAllSoId\" id=\"selectAllSoId\" onclick=\"javascript:changeChiefCheckbox('selectAllSoId','selectSoId');\"/></th>\r\n      <th>\u8ba2\u5355\u9879ID</th>\r\n      <th>\u5916\u90e8\u8ba2\u5355\u9879ID</th>      \r\n      <th>\u5546\u54c1ID</th>\r\n      <th>\u5546\u54c1\u9879ID</th>\r\n      <th>\u5546\u54c1\u9879\u7f16\u7801</th>\r\n      <th>\u5546\u54c1\u540d\u79f0</th>\r\n      <th>\u5355\u4ef7</th>\r\n      <th>\u6570\u91cf</th>\r\n      <th>\u603b\u4ef7</th>\r\n    </tr>\r\n    ".toCharArray();
    _jsp_string31 = "\" checkStr=\"\u8fd0\u8f93\u5355\u53f7;code;false;;30\"/>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u5e01\u79cd\uff1a</em>\r\n      <select name=\"currency\" id=\"currency\">\r\n      ".toCharArray();
    _jsp_string23 = "\" checkStr=\"\u7535\u8bdd;txt;false;;30\"/><em></em>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u624b\u673a\uff1a</em>\r\n      <input type=\"text\" name=\"mobile\" id=\"mobile\" size=\"20\" maxlength=\"30\" value=\"".toCharArray();
    _jsp_string43 = "\r\n      </select>\r\n    </p>    \r\n    <p>\r\n      <em class=\"int_label\">\u652f\u4ed8\u65f6\u95f4\uff1a</em>\r\n      <input type=\"text\" name=\"payTime\" id=\"payTime\" size=\"30\" maxlength=\"30\" value=\"".toCharArray();
    _jsp_string13 = "</option>\r\n      ".toCharArray();
    _jsp_string32 = "\r\n      </select>\r\n    </p>    \r\n    <p>\r\n      <em class=\"int_label\">\u8d27\u54c1\u91d1\u989d\uff1a</em>\r\n      <input type=\"text\" name=\"productAmount\" id=\"productAmount\" size=\"30\" maxlength=\"30\" value=\"".toCharArray();
    _jsp_string0 = "\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n".toCharArray();
    _jsp_string45 = "\" checkStr=\"\u8ba2\u5355\u6765\u6e90;txt;false;;30\"/>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u4ea4\u6613\u6765\u6e90\uff1a</em>\r\n      <input type=\"text\" name=\"tradeSource\" id=\"tradeSource\" size=\"30\" maxlength=\"30\" value=\"".toCharArray();
    _jsp_string7 = "/js/My97DatePicker/WdatePicker.js\"></script>\r\n<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string10 = "\" checkStr=\"\u5ba2\u6237ID;code;true;;20\"/><em><span>*</span></em>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u5546\u5e97\uff1a</em>\r\n      <select name=\"storeId\" id=\"storeId\">\r\n      ".toCharArray();
    _jsp_string54 = "\r\n    <tr>\r\n      <td>&nbsp;</td>\r\n      <td>".toCharArray();
    _jsp_string49 = "\r\n	<option value=\"".toCharArray();
    _jsp_string18 = "\r\n      </select>\r\n      <select name=\"city\" id=\"city\" style=\"width:120px\" onchange=\"javascript:getRegionNameList('city','region','".toCharArray();
    _jsp_string57 = "\r\n  </table>\r\n  <!-- \r\n  <div class=\"div_center\"><input type=\"submit\" name=\"button\" id=\"button\" class=\"modifysubbtn\" value=\"\u63d0\u4ea4\" /></div>\r\n   -->\r\n</form>\r\n</body>\r\n</html>".toCharArray();
    _jsp_string1 = "\r\n<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n<html>\r\n<head>\r\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n<title>\u9500\u552e\u8ba2\u5355\u7ba1\u7406</title>\r\n\r\n".toCharArray();
    _jsp_string36 = "\" checkStr=\"\u90ae\u8d39;num;false;;30\"/>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u6298\u6263\u90ae\u8d39\uff1a</em>\r\n      <input type=\"text\" name=\"discountFreight\" id=\"discountFreight\" size=\"30\" maxlength=\"30\" value=\"".toCharArray();
    _jsp_string51 = "\r\n	</select>\r\n    </p>    \r\n    <p class=\"p_top_border\">\r\n      <em class=\"int_label\">\u987e\u5ba2\u7559\u8a00\uff1a</em>\r\n      <textarea name=\"userComment\" id=\"userComment\" cols=\"60\" rows=\"3\" checkStr=\"\u987e\u5ba2\u7559\u8a00;txt;false;;1000\">".toCharArray();
    _jsp_string24 = "\" checkStr=\"\u624b\u673a;txt;true;;30\"/><em><span>*</span></em>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u90ae\u7f16\uff1a</em>\r\n      <input type=\"text\" name=\"zipCode\" id=\"zipCode\" size=\"10\" maxlength=\"10\" value=\"".toCharArray();
    _jsp_string35 = "\" checkStr=\"\u8d27\u54c1\u5b9e\u9645\u91d1\u989d;num;false;;30\"/>\r\n    </p>    \r\n    <p>\r\n      <em class=\"int_label\">\u90ae\u8d39\uff1a</em>\r\n      <input type=\"text\" name=\"freight\" id=\"freight\" size=\"30\" maxlength=\"30\" value=\"".toCharArray();
    _jsp_string33 = "\" checkStr=\"\u8d27\u54c1\u91d1\u989d;num;false;;30\"/>\r\n    </p>\r\n    <p>\r\n      <em class=\"int_label\">\u8d27\u54c1\u6298\u6263\u91d1\u989d\uff1a</em>\r\n      <input type=\"text\" name=\"productDiscountAmount\" id=\"productDiscountAmount\" size=\"30\" maxlength=\"30\" value=\"".toCharArray();
    _jsp_string46 = "\" checkStr=\"\u4ea4\u6613\u6765\u6e90;txt;false;;30\"/>\r\n      <input type=\"text\" name=\"tradeSourceId1\" id=\"tradeSourceId1\" size=\"30\" maxlength=\"30\" value=\"".toCharArray();
    _jsp_string11 = "\r\n      	<option value=\"".toCharArray();
  }
}