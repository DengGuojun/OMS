<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lpmas.framework.bean.*"%>
<%@ page import="com.lpmas.framework.config.*"%>
<%@ page import="com.lpmas.framework.util.*"%>
<%@ page import="com.lpmas.admin.bean.*"%>
<%@ page import="com.lpmas.admin.business.*"%>
<%@ page import="com.lpmas.system.bean.*"%>
<%@ page import="com.lpmas.constant.currency.*"%>
<%@ page import="com.lpmas.constant.sync.*"%>
<%@ page import="com.lpmas.region.bean.*"%>
<%@ page import="com.lpmas.oms.order.config.*"%>
<%@ page import="com.lpmas.oms.order.bean.*"%>
<%@ page import="com.lpmas.oms.dispatch.bean.*"%>
<%@ page import="com.lpmas.oms.dispatch.config.*"%>
<%@ page import="com.lpmas.tms.transporter.bean.*"%>
<%@ page import="com.lpmas.tms.transporter.business.*"%>
<%@ page import="com.lpmas.tms.config.*"%>
<%
	DispatchOrderInfoBean orderInfoBean = (DispatchOrderInfoBean) request.getAttribute("OrderInfo");
	List<DispatchOrderItemBean> orderItemList = (List<DispatchOrderItemBean>)request.getAttribute("OrderItemList");
	List<StoreInfoBean> storeList = (List<StoreInfoBean>) request.getAttribute("StoreList");

	List<CountryInfoBean> countryList = (List<CountryInfoBean>) request.getAttribute("CountryList");
	List<ProvinceInfoBean> provinceList = (List<ProvinceInfoBean>) request.getAttribute("ProvinceList");
	List<CityInfoBean> cityList = (List<CityInfoBean>) request.getAttribute("CityList");
	List<RegionInfoBean> regionList = (List<RegionInfoBean>) request.getAttribute("RegionList");

	TransporterInfoMediator transporterInfoMediator = (TransporterInfoMediator) request
			.getAttribute("TransporterInfoMediator");
	List<TransporterInfoBean> transporterInfoList = transporterInfoMediator
			.getTransporterInfoListByType(orderInfoBean.getTransporterType());
	SalesOrderInfoBean soInfoBean = (SalesOrderInfoBean) request.getAttribute("SalesOrderInfo");
	AdminUserHelper adminHelper = (AdminUserHelper) request.getAttribute("AdminUserHelper");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发运订单管理</title>
<%@ include file="../../include/header.jsp"%>
<script type="text/javascript" src="<%=REGION_URL%>/region/RegionAjaxList.do"></script>
<script type="text/javascript" src="<%=TMS_URL%>/tms/TransporterAjaxList.do"></script>
</head>
<body class="article_bg">
	<p class="article_tit">发运订单管理</p>
	<form id="formData" name="formData" method="post" action="DispatchOrderInfoManage.do" onsubmit="javascript:return checkForm('formData');">
		<input type="hidden" name="doId" id="doId" value="<%=orderInfoBean.getDoId()%>" />
		<div class="modify_form">
			<p>
				<em class="int_label">商店：</em>
				<select name="storeId" id="storeId" disabled="disabled">
					<%
						for (StoreInfoBean storeInfoBean : storeList) {
					%>
					<option value="<%=storeInfoBean.getStoreId()%>" <%=(storeInfoBean.getStoreId() == orderInfoBean.getStoreId()) ? "selected" : ""%>><%=storeInfoBean.getStoreName()%></option>
					<%
						}
					%>
				</select>
			</p>
			<p>
				<em class="int_label">销售订单ID：</em>
				<input type="text" name="soId" id="soId" size="20" maxlength="20" value="<%=orderInfoBean.getSoId()%>" checkStr="销售订单ID;code;true;;20" readOnly/>
				<em><span>*</span></em>
			</p>
			<p>
				<em class="int_label">外部订单号：</em>
				<input type="text" name="outerOrderId" id="outerOrderId" size="30" maxlength="100" value="<%=orderInfoBean.getOuterOrderId()%>" checkStr="外部订单号;txt;true;;100" readOnly />
				<em><span>*</span></em>
			</p>
			<p>
				<em class="int_label">地址：</em>
				<select name="country" id="country" style="width: 120px" onchange="javascript:getProvinceNameList('country','province','<%=orderInfoBean.getProvince()%>');" disabled="disabled">
					<option></option>
					<%
						for (CountryInfoBean countryInfoBean : countryList) {
					%>
					<option value="<%=countryInfoBean.getCountryName()%>" <%=(countryInfoBean.getCountryName().equals(orderInfoBean.getCountry())) ? "selected" : ""%>><%=countryInfoBean.getCountryName()%></option>
					<%
						}
					%>
				</select>
				<select name="province" id="province" style="width: 120px" onchange="javascript:getCityNameList('province','city','<%=orderInfoBean.getCity()%>');" disabled="disabled">
					<option></option>
					<%
						for (ProvinceInfoBean provinceInfoBean : provinceList) {
					%>
					<option value="<%=provinceInfoBean.getProvinceName()%>" <%=(provinceInfoBean.getProvinceName().equals(orderInfoBean.getProvince())) ? "selected" : ""%>><%=provinceInfoBean.getProvinceName()%></option>
					<%
						}
					%>
				</select>
				<select name="city" id="city" style="width: 120px" onchange="javascript:getRegionNameList('city','region','<%=orderInfoBean.getRegion()%>');" disabled="disabled">
					<option></option>
					<%
						for (CityInfoBean cityInfoBean : cityList) {
					%>
					<option value="<%=cityInfoBean.getCityName()%>" <%=(cityInfoBean.getCityName().equals(orderInfoBean.getCity())) ? "selected" : ""%>><%=cityInfoBean.getCityName()%></option>
					<%
						}
					%>
				</select>
				<select name="region" id="region" style="width: 120px" disabled="disabled">
					<option></option>
					<%
						for (RegionInfoBean regionInfoBean : regionList) {
					%>
					<option value="<%=regionInfoBean.getRegionName()%>" <%=(regionInfoBean.getRegionName().equals(orderInfoBean.getRegion())) ? "selected" : ""%>><%=regionInfoBean.getRegionName()%></option>
					<%
						}
					%>
				</select>
				<input type="text" name="address" id="address" size="50" maxlength="100" value="<%=orderInfoBean.getAddress()%>" checkStr="地址;txt;true;;100" readOnly/>
				<em><span>*</span></em>
			</p>
			<p>
				<em class="int_label">收货人姓名：</em>
				<input type="text" name="receiverName" id="receiverName" size="30" maxlength="100" value="<%=orderInfoBean.getReceiverName()%>" checkStr="收货人姓名;txt;true;;100" readOnly />
				<em><span>*</span></em>
			</p>
			<p>
				<em class="int_label">发票抬头：</em>
				<input type="text"  size="30" maxlength="100" value="<%=soInfoBean.getInvoiceTitle()%>" readOnly />
			</p>
			<p>
				<em class="int_label">发票金额：</em>
				<input type="text"  size="30" maxlength="100" value="<%=soInfoBean.getInvoiceAmount()%>" readOnly />
			</p>
			<p>
				<em class="int_label">电话：</em>
				<input type="text" name="telephone" id="telephone" size="20" maxlength="30" value="<%=orderInfoBean.getTelephone()%>" checkStr="电话;txt;false;;30" readOnly/>
			</p>
			<p>
				<em class="int_label">手机：</em>
				<input type="text" name="mobile" id="mobile" size="20" maxlength="30" value="<%=orderInfoBean.getMobile()%>" checkStr="手机;txt;true;;30" readOnly/>
				<em><span>*</span></em>
			</p>
			<p>
				<em class="int_label">邮编：</em>
				<input type="text" name="zipCode" id="zipCode" size="10" maxlength="10" value="<%=orderInfoBean.getZipCode()%>" checkStr="邮编;txt;false;;10" readOnly/>
			</p>
			<p>
				<em class="int_label">发货方式：</em>
				<select name="deliveryMethod" id="deliveryMethod">
					<%
						for (StatusBean<Integer, String> dmBean : DeliveryMethodConfig.DELIVERY_METHOD_LIST) {
					%>
					<option value="<%=dmBean.getStatus()%>" <%=(dmBean.getStatus() == orderInfoBean.getDeliveryMethod()) ? "selected" : ""%>><%=dmBean.getValue()%></option>
					<%
						}
					%>
				</select>
			</p>
			<p>
				<em class="int_label">发货方：</em>
				<input type="text"  size="30" maxlength="100" value="<%=MapKit.getValueFromMap(orderInfoBean.getConsignerId(), DeliveryMethodConfig.DELIVERY_WAREHOUSE_MAP) %>" disabled="disabled"/>
				<em><span>*</span></em>
			</p>
			<p>
				<em class="int_label">运输信息：</em>
				<select name="transporterType" id="transporterType" style="width: 60px" onchange="javascript:getTransporterNameList('transporterType','transporterId','<%=orderInfoBean.getTransporterId()%>');">
					<option></option>
					<%
						for (StatusBean<Integer, String> ttBean : TmsConfig.TRANSPORTER_TYPE_LIST) {
					%>
					<option value="<%=ttBean.getStatus()%>" <%=(ttBean.getStatus() == orderInfoBean.getTransporterType()) ? "selected" : ""%>><%=ttBean.getValue()%></option>
					<%
						}
					%>
				</select>
				<select name="transporterId" id="transporterId" style="width: 100px">
					<option></option>
					<%
						for (TransporterInfoBean transporterInfoBean : transporterInfoList) {
					%>
					<option value="<%=transporterInfoBean.getTransporterId()%>" <%=(transporterInfoBean.getTransporterId() == orderInfoBean.getTransporterId()) ? "selected" : ""%>><%=transporterInfoBean.getTransporterName()%></option>
					<%
						}
					%>
				</select>
				<input type="text" name="transportNumber" id="transportNumber" size="20" maxlength="20" value="<%=orderInfoBean.getTransportNumber()%>" checkStr="运单号;txt;false;;15" />
			</p>
			<p>
				<em class="int_label">发货时间：</em>
				<input type="text"  size="30" maxlength="100" value="<%=DateKit.formatTimestamp(orderInfoBean.getDeliveryStartTime(), DateKit.DEFAULT_DATE_TIME_FORMAT)%>" readOnly/>
				<em><span>*</span></em>
			</p>
			<p>
				<em class="int_label">币种：</em>
				<select name="currency" id="currency" disabled="disabled">
					<%
						for (StatusBean<String, String> currency : CurrencyConfig.CURRENCY_LIST) {
					%>
					<option value="<%=currency.getStatus()%>" <%=(currency.getStatus().equals(orderInfoBean.getCurrency())) ? "selected" : ""%>><%=currency.getValue()%></option>
					<%
						}
					%>
				</select>
			</p>
			<p>
				<em class="int_label">邮费：</em>
				<input type="text" name="freight" id="freight" size="30" maxlength="30" value="<%=orderInfoBean.getFreight()%>" checkStr="邮费;num;false;;30" readOnly/>
			</p>
			<p>
				<em class="int_label">折扣邮费：</em>
				<input type="text" name="discountFreight" id="discountFreight" size="30" maxlength="30" value="<%=orderInfoBean.getDiscountFreight()%>" checkStr="折扣邮费;num;false;;30" readOnly/>
			</p>
			<p>
				<em class="int_label">实际邮费：</em>
				<input type="text" name="factFreight" id="factFreight" size="30" maxlength="30" value="<%=orderInfoBean.getFactFreight()%>" checkStr="实际邮费;num;false;;30" readOnly/>
			</p>
			<p>
				<em class="int_label">订单总价：</em>
				<input type="text" name="soAmount" id="soAmount" size="30" maxlength="30" value="<%=orderInfoBean.getSoAmount()%>" checkStr="订单总价;num;false;;30" readOnly/>
			</p>
			<p>
				<em class="int_label">订单折扣金额：</em>
				<input type="text" name="soDiscountAmount" id="soDiscountAmount" size="30" maxlength="30" value="<%=orderInfoBean.getSoDiscountAmount()%>" checkStr="订单折扣金额;num;false;;30" readOnly/>
			</p>
			<p>
				<em class="int_label">订单实际金额：</em>
				<input type="text" name="soFactAmount" id="soFactAmount" size="30" maxlength="30" value="<%=orderInfoBean.getSoFactAmount()%>" checkStr="订单实际金额;num;false;;30" readOnly/>
			</p>
			<p>
				<em class="int_label">总件数：</em>
				<input type="text" name="totalQuantity" id="totalQuantity" size="30" maxlength="30" value="<%=orderInfoBean.getTotalQuantity()%>" checkStr="总件数;num;false;;30" readOnly/>
			</p>
			<p>
				<em class="int_label">发运订单状态：</em>
				<select id="doStatus" name="doStatus">
					<%
						for (StatusBean<String, String> statusBean : DispatchOrderStatusConfig.DISPATCH_ORDER_STATUS_LIST) {
					%>
					<option value="<%=statusBean.getStatus()%>" <%=statusBean.getStatus().equals(orderInfoBean.getDoStatus()) ? "selected" : ""%>><%=statusBean.getValue()%></option>
					<%
						}
					%>
				</select>
			</p>
			<p>
				<em class="int_label">同步状态：</em>
				<select id="syncStatus" name="syncStatus" disabled="disabled">
					<%
						for (StatusBean<String, String> statusBean : SyncStatusConfig.SYNC_STATUS_LIST) {
					%>
					<option value="<%=statusBean.getStatus()%>" <%=statusBean.getStatus().equals(orderInfoBean.getSyncStatus()) ? "selected" : ""%>><%=statusBean.getValue()%></option>
					<%
						}
					%>
				</select>
			</p>
			<p class="p_top_border">
				<em class="int_label">顾客留言：</em>
				<textarea name="userComment" id="userComment" cols="60" rows="3" checkStr="顾客留言;txt;false;;1000"><%=orderInfoBean.getUserComment()%></textarea>
			</p>
			<p class="p_top_border">
				<em class="int_label">备注：</em>
				<textarea name="memo" id="memo" cols="60" rows="3" checkStr="备注;txt;false;;1000"><%=orderInfoBean.getMemo()%></textarea>
			</p>
		</div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_style">
    <tr>
      <th><input type="checkbox" name="selectAllSoId" id="selectAllSoId" onclick="javascript:changeChiefCheckbox('selectAllSoId','selectSoId');"/></th>
      <th>发运单项ID</th>
      <th>商品项ID</th>
      <th>商品项编码</th>
      <th>商品条形码</th>
      <th>商品名称</th>
      <th>单价</th>
      <th>数量</th>
      <th>总价</th>
    </tr>
    <%
    for(DispatchOrderItemBean itemBean:orderItemList){
    %>
    <tr>
      <td>&nbsp;</td>
      <td><%=itemBean.getDoItemId() %></td>
      <td><%=itemBean.getProductItemId() %></td>
      <td><%=itemBean.getProductItemNumber() %></td>
      <td><%=itemBean.getProductItemBarcode() %></td>
      <td><%=itemBean.getProductName() %></td>
      <td><%=itemBean.getFactPrice() %></td>
      <td><%=itemBean.getQuantity() %></td>
      <td><%=itemBean.getItemFactAmount() %></td>
    </tr>
    <%} %>
  </table>
		<div class="div_center">
			<input type="submit" name="button" id="button" class="modifysubbtn" value="提交" />
		</div>
	</form>
</body>
<script>
$(document).ready(function() {
	$("#transporterType").trigger('change');
});
</script>
</html>