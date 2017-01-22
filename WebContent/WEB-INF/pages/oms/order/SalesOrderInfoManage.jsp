<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.framework.web.*"  %>
<%@ page import="com.lpmas.admin.bean.*"  %>
<%@ page import="com.lpmas.admin.business.*"  %>
<%@ page import="com.lpmas.system.bean.*"  %>
<%@ page import="com.lpmas.region.bean.*"  %>
<%@ page import="com.lpmas.constant.currency.*"  %>
<%@ page import="com.lpmas.oms.order.bean.*"  %>
<%@ page import="com.lpmas.oms.order.config.*"  %>
<% 
SalesOrderInfoBean orderInfoBean = (SalesOrderInfoBean)request.getAttribute("OrderInfo");
List<SalesOrderItemBean> orderItemList = (List<SalesOrderItemBean>)request.getAttribute("OrderItemList");
AdminUserHelper adminHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");

List<StoreInfoBean> storeList = (List<StoreInfoBean>)request.getAttribute("StoreList");

List<CountryInfoBean> countryList = (List<CountryInfoBean>)request.getAttribute("CountryList");
List<ProvinceInfoBean> provinceList = (List<ProvinceInfoBean>)request.getAttribute("ProvinceList");
List<CityInfoBean> cityList = (List<CityInfoBean>)request.getAttribute("CityList");
List<RegionInfoBean> regionList = (List<RegionInfoBean>)request.getAttribute("RegionList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>销售订单管理</title>
<%@ include file="../../include/header.jsp" %>
<script type="text/javascript" src="<%=STATIC_URL%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=REGION_URL%>/region/RegionAjaxList.do"></script>
</head>
<body class="article_bg">
<p class="article_tit">销售订单管理</p>
<form id="formData" name="formData" method="post" action="SalesOrderInfoManage.do" onsubmit="javascript:return checkForm('formData');">
  <input type="hidden" name="soId" id="soId" value="<%=orderInfoBean.getSoId() %>"/>
  <div class="modify_form">
    <p>
      <em class="int_label">客户ID：</em>
      <input type="text" name="costomerId" id="costomerId" size="20" maxlength="20" value="<%=orderInfoBean.getCostomerId() %>" checkStr="客户ID;code;true;;20"/><em><span>*</span></em>
    </p>
    <p>
      <em class="int_label">商店：</em>
      <select name="storeId" id="storeId">
      <%
      	for(StoreInfoBean storeInfoBean : storeList){
      %>
      	<option value="<%=storeInfoBean.getStoreId() %>" <%=(storeInfoBean.getStoreId()==orderInfoBean.getStoreId())?"selected":"" %>><%=storeInfoBean.getStoreName() %></option>
      <%} %>
      </select>
    </p>
    <p>
      <em class="int_label">外部订单号：</em>
      <input type="text" name="outerOrderId" id="outerOrderId" size="30" maxlength="100" value="<%=orderInfoBean.getOuterOrderId() %>" checkStr="外部订单号;txt;false;;100"/>
    </p>
    <p>
      <em class="int_label">地址：</em>    
      <select name="country" id="country" style="width:120px" onchange="javascript:getProvinceNameList('country','province','<%=orderInfoBean.getProvince() %>');">
        <option></option>
      <%
      	for(CountryInfoBean countryInfoBean : countryList){
      %>
      	<option value="<%=countryInfoBean.getCountryName() %>" <%=(countryInfoBean.getCountryName().equals(orderInfoBean.getCountry()))?"selected":"" %>><%=countryInfoBean.getCountryName() %></option>
      <%} %>
      </select>
      <select name="province" id="province" style="width:120px" onchange="javascript:getCityNameList('province','city','<%=orderInfoBean.getCity() %>');">
        <option></option>
      <%
      	for(ProvinceInfoBean provinceInfoBean : provinceList){
      %>
      	<option value="<%=provinceInfoBean.getProvinceName() %>" <%=(provinceInfoBean.getProvinceName().equals(orderInfoBean.getProvince()))?"selected":"" %>><%=provinceInfoBean.getProvinceName() %></option>
      <%} %>
      </select>
      <select name="city" id="city" style="width:120px" onchange="javascript:getRegionNameList('city','region','<%=orderInfoBean.getRegion() %>');">
        <option></option>
      <%
      	for(CityInfoBean cityInfoBean : cityList){
      %>
      	<option value="<%=cityInfoBean.getCityName() %>" <%=(cityInfoBean.getCityName().equals(orderInfoBean.getCity()))?"selected":"" %>><%=cityInfoBean.getCityName() %></option>
      <%} %>
      </select>
      <select name="region" id="region" style="width:120px">
        <option></option>
      <%
      	for(RegionInfoBean regionInfoBean : regionList){
      %>
      	<option value="<%=regionInfoBean.getRegionName() %>" <%=(regionInfoBean.getRegionName().equals(orderInfoBean.getRegion()))?"selected":"" %>><%=regionInfoBean.getRegionName() %></option>
      <%} %>
      </select>
      <input type="text" name="address" id="address" size="50" maxlength="100" value="<%=orderInfoBean.getAddress() %>" checkStr="地址;txt;true;;100"/><em><span>*</span></em>
    </p>
    <p>
      <em class="int_label">收货人姓名：</em>
      <input type="text" name="receiverName" id="receiverName" size="30" maxlength="100" value="<%=orderInfoBean.getReceiverName() %>" checkStr="收货人姓名;txt;true;;100"/><em><span>*</span></em>
    </p>
    <p>
      <em class="int_label">电话：</em>
      <input type="text" name="telephone" id="telephone" size="20" maxlength="30" value="<%=orderInfoBean.getTelephone() %>" checkStr="电话;txt;false;;30"/><em></em>
    </p>
    <p>
      <em class="int_label">手机：</em>
      <input type="text" name="mobile" id="mobile" size="20" maxlength="30" value="<%=orderInfoBean.getMobile() %>" checkStr="手机;txt;true;;30"/><em><span>*</span></em>
    </p>
    <p>
      <em class="int_label">邮编：</em>
      <input type="text" name="zipCode" id="zipCode" size="10" maxlength="10" value="<%=orderInfoBean.getZipCode() %>" checkStr="邮编;txt;false;;10"/>
    </p>    
    <p>
      <em class="int_label">发票号：</em>
      <input type="text" name="invoiceNumber" id="invoiceNumber" size="30" maxlength="50" value="<%=orderInfoBean.getInvoiceNumber() %>" checkStr="发票号;txt;false;;50"/>
    </p>
    <p>
      <em class="int_label">发票类型：</em>
      <input type="text" name="invoiceType" id="invoiceType" size="10" maxlength="10" value="<%=orderInfoBean.getInvoiceType() %>" checkStr="发票类型;txt;false;;50"/>
    </p>
    <p>
      <em class="int_label">发票抬头：</em>
      <input type="text" name="invoiceTitle" id="invoiceTitle" size="30" maxlength="50" value="<%=orderInfoBean.getInvoiceTitle() %>" checkStr="发票抬头;txt;false;;100"/>
    </p>
    <p>
      <em class="int_label">发票金额：</em>
      <input type="text" name="invoiceAmount" id="invoiceAmount" size="20" maxlength="20" value="<%=orderInfoBean.getInvoiceAmount() %>" checkStr="发票金额;num;false;;10"/>
    </p>
    <p>
      <em class="int_label">运输公司：</em>
      <input type="text" name="transporterId" id="transporterId" size="20" maxlength="20" value="<%=orderInfoBean.getTransporterId() %>" checkStr="发票金额;num;false;;10"/>
    </p>
    <p>
      <em class="int_label">运输单号：</em>
      <input type="text" name="transportNumber" id="transportNumber" size="30" maxlength="30" value="<%=orderInfoBean.getTransportNumber() %>" checkStr="运输单号;code;false;;30"/>
    </p>
    <p>
      <em class="int_label">币种：</em>
      <select name="currency" id="currency">
      <%
      	for(StatusBean<String, String> currency:CurrencyConfig.CURRENCY_LIST){
      %>
      	<option value="<%=currency.getStatus() %>" <%=(currency.getStatus().equals(orderInfoBean.getCurrency()))?"selected":"" %>><%=currency.getValue() %></option>
      <%} %>
      </select>
    </p>    
    <p>
      <em class="int_label">货品金额：</em>
      <input type="text" name="productAmount" id="productAmount" size="30" maxlength="30" value="<%=orderInfoBean.getProductAmount() %>" checkStr="货品金额;num;false;;30"/>
    </p>
    <p>
      <em class="int_label">货品折扣金额：</em>
      <input type="text" name="productDiscountAmount" id="productDiscountAmount" size="30" maxlength="30" value="<%=orderInfoBean.getProductDiscountAmount() %>" checkStr="货品折扣金额;num;false;;30"/>
    </p>
    <p>
      <em class="int_label">货品实际金额：</em>
      <input type="text" name="productFactAmount" id="productFactAmount" size="30" maxlength="30" value="<%=orderInfoBean.getProductFactAmount() %>" checkStr="货品实际金额;num;false;;30"/>
    </p>    
    <p>
      <em class="int_label">邮费：</em>
      <input type="text" name="freight" id="freight" size="30" maxlength="30" value="<%=orderInfoBean.getFreight() %>" checkStr="邮费;num;false;;30"/>
    </p>
    <p>
      <em class="int_label">折扣邮费：</em>
      <input type="text" name="discountFreight" id="discountFreight" size="30" maxlength="30" value="<%=orderInfoBean.getDiscountFreight() %>" checkStr="折扣邮费;num;false;;30"/>
    </p>
    <p>
      <em class="int_label">实际邮费：</em>
      <input type="text" name="factFreight" id="factFreight" size="30" maxlength="30" value="<%=orderInfoBean.getFactFreight() %>" checkStr="实际邮费;num;false;;30"/>
    </p>    
    <p>
      <em class="int_label">订单总价：</em>
      <input type="text" name="soAmount" id="soAmount" size="30" maxlength="30" value="<%=orderInfoBean.getSoAmount() %>" checkStr="订单总价;num;false;;30"/>
    </p>
    <p>
      <em class="int_label">订单折扣金额：</em>
      <input type="text" name="soDiscountAmount" id="soDiscountAmount" size="30" maxlength="30" value="<%=orderInfoBean.getSoDiscountAmount() %>" checkStr="订单折扣金额;num;false;;30"/>
    </p>
    <p>
      <em class="int_label">订单实际金额：</em>
      <input type="text" name="soFactAmount" id="soFactAmount" size="30" maxlength="30" value="<%=orderInfoBean.getSoFactAmount() %>" checkStr="订单实际金额;num;false;;30"/>
    </p>    
    <p>
      <em class="int_label">总件数：</em>
      <input type="text" name="totalQuantity" id="totalQuantity" size="30" maxlength="30" value="<%=orderInfoBean.getTotalQuantity() %>" checkStr="总件数;num;false;;30"/>
    </p>
    <p>
      <em class="int_label">订单类型：</em>
      <select name="storeId" id="storeId">
      <%
      	for(StatusBean<Integer, String> statusBean : SalesOrderConfig.ORDER_TYPE_LIST){
      %>
      	<option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus()==orderInfoBean.getSoType())?"selected":"" %>><%=statusBean.getValue() %></option>
      <%} %>
      </select>
    </p>    
    <p>
      <em class="int_label">支付时间：</em>
      <input type="text" name="payTime" id="payTime" size="30" maxlength="30" value="<%=DateKit.formatTimestamp(orderInfoBean.getPayTime(), "yyyy-MM-dd HH:mm:ss")%>" readOnly checkStr="支付时间;num;false;;30" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
    </p>
    <p>
      <em class="int_label">订单来源：</em>
      <input type="text" name="orderFrom" id="orderFrom" size="30" maxlength="30" value="<%=orderInfoBean.getOrderFrom() %>" checkStr="订单来源;txt;false;;30"/>
    </p>
    <p>
      <em class="int_label">交易来源：</em>
      <input type="text" name="tradeSource" id="tradeSource" size="30" maxlength="30" value="<%=orderInfoBean.getTradeSource() %>" checkStr="交易来源;txt;false;;30"/>
      <input type="text" name="tradeSourceId1" id="tradeSourceId1" size="30" maxlength="30" value="<%=orderInfoBean.getTradeSourceId1() %>" checkStr="交易来源1;txt;false;;30"/>
      <input type="text" name="tradeSourceId2" id="tradeSourceId2" size="30" maxlength="30" value="<%=orderInfoBean.getTradeSourceId2() %>" checkStr="交易来源2;txt;false;;30"/>
    </p>
    <p>
      <em class="int_label">订单状态：</em>
      <select id="soStatus" name="soStatus">
	<%		
		for (StatusBean<String,String> statusBean : SalesOrderStatusConfig.ORDER_STATUS_LIST) {
	%>
	<option value="<%=statusBean.getStatus() %>" <%=statusBean.getStatus().equals(orderInfoBean.getSoStatus()) ? "selected" : ""%>><%=statusBean.getValue() %></option>
	<%
		}
	%>
	</select>
    </p>    
    <p class="p_top_border">
      <em class="int_label">顾客留言：</em>
      <textarea name="userComment" id="userComment" cols="60" rows="3" checkStr="顾客留言;txt;false;;1000"><%=orderInfoBean.getUserComment() %></textarea>
    </p>
    <p class="p_top_border">
      <em class="int_label">备注：</em>
      <textarea name="memo" id="memo" cols="60" rows="3" checkStr="备注;txt;false;;1000"><%=orderInfoBean.getMemo() %></textarea>
    </p>
  </div>
  
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_style">
    <tr>
      <th><input type="checkbox" name="selectAllSoId" id="selectAllSoId" onclick="javascript:changeChiefCheckbox('selectAllSoId','selectSoId');"/></th>
      <th>订单项ID</th>
      <th>外部订单项ID</th>      
      <th>商品ID</th>
      <th>商品项ID</th>
      <th>商品项编码</th>
      <th>商品名称</th>
      <th>单价</th>
      <th>数量</th>
      <th>总价</th>
    </tr>
    <%
    for(SalesOrderItemBean itemBean:orderItemList){
    %>
    <tr>
      <td>&nbsp;</td>
      <td><%=itemBean.getSoItemId() %></td>
      <td><%=itemBean.getOuterOrderItemId() %></td>
      <td><%=itemBean.getProductId() %></td>
      <td><%=itemBean.getProductItemId() %></td>
      <td><%=itemBean.getProductItemNumber() %></td>
      <td><%=itemBean.getProductName() %></td>
      <td><%=itemBean.getFactPrice() %></td>
      <td><%=itemBean.getQuantity() %></td>
      <td><%=itemBean.getItemFactAmount() %></td>
    </tr>
    <%} %>
  </table>
  <!-- 
  <div class="div_center"><input type="submit" name="button" id="button" class="modifysubbtn" value="提交" /></div>
   -->
</form>
</body>
</html>