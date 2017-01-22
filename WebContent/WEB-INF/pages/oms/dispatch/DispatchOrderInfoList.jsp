<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lpmas.framework.bean.*"%>
<%@ page import="com.lpmas.framework.page.*"%>
<%@ page import="com.lpmas.framework.util.*"%>
<%@ page import="com.lpmas.framework.web.*"%>
<%@ page import="com.lpmas.oms.dispatch.bean.*"%>
<%@ page import="com.lpmas.oms.dispatch.config.*"%>
<%@ page import="com.lpmas.oms.config.*"%>
<%@ page import="com.lpmas.oms.order.config.*"%>
<%@ page import="com.lpmas.admin.business.*"%>
<%@ page import="com.lpmas.admin.config.*"%>
<%@ page import="com.lpmas.system.bean.*"%>
<%@ page import="com.lpmas.system.config.*"%>
<%@ page import="com.lpmas.tms.transporter.business.*"%>
<%
	AdminUserHelper adminHelper = (AdminUserHelper) request.getAttribute("AdminUserHelper");
	List<DispatchOrderInfoBean> list = (List<DispatchOrderInfoBean>) request.getAttribute("OrderList");

	PageBean PAGE_BEAN = (PageBean) request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>) request.getAttribute("CondList");

	List<StoreInfoBean> storeList = (List<StoreInfoBean>) request.getAttribute("StoreList");
	Map<Integer, String> storeNameMap = ListKit.list2Map(storeList, "storeId", "storeName");
	
	TransporterInfoMediator transporterInfoMediator = (TransporterInfoMediator) request
			.getAttribute("TransporterInfoMediator");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发运订单列表</title>
<%@ include file="../../include/header.jsp"%>
<link href="<%=STATIC_URL%>/js/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
<script type='text/javascript'src="<%=STATIC_URL%>/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body class="article_bg">
	<p class="article_tit">发运订单列表</p>
	<form name="formSearch" method="post" action="DispatchOrderInfoList.do" id="form">
		<div class="search_form">
			<em class="em1">发运订单ID：</em>
			<input type="text" name="doId" id="doId" value="<%=ParamKit.getParameter(request, "doId", "")%>" size="20" />
			<em class="em1">销售订单ID：</em>
			<input type="text" name="soId" id="soId" value="<%=ParamKit.getParameter(request, "soId", "")%>" size="20" />
			<em class="em1">商店：</em>
			<select id="storeId" name="storeId">
				<option value=""></option>
				<%
					int storeId = ParamKit.getIntParameter(request, "storeId", -1);
					for (StoreInfoBean store : storeList) {
				%>
				<option value="<%=store.getStoreId()%>" <%=storeId == store.getStoreId() ? "selected" : ""%>><%=store.getStoreName()%></option>
				<%
					}
				%>
			</select>
			<em class="em1">发运订单状态：</em>
			<select id="doStatus" name="doStatus">
				<option value=""></option>
				<%
					String doStatus = ParamKit.getParameter(request, "doStatus", "");
					for (StatusBean<String, String> statusBean : DispatchOrderStatusConfig.DISPATCH_ORDER_STATUS_LIST) {
				%>
				<option value="<%=statusBean.getStatus()%>" <%=statusBean.getStatus().equals(doStatus) ? "selected" : ""%>><%=statusBean.getValue()%></option>
				<%
					}
				%>
			</select>
			<em class="em1">发货方：</em>
			<select id="consignerId" name="consignerId">
				<option value=""></option>
				<%
					int consignerId = ParamKit.getIntParameter(request, "consignerId", 0);
					for (StatusBean<Integer, String> statusBean : DeliveryMethodConfig.DELIVERY_WAREHOUSE_LIST) {
				%>
				<option value="<%=statusBean.getStatus()%>" <%=statusBean.getStatus()==consignerId ? "selected" : ""%>><%=statusBean.getValue()%></option>
				<%
					}
				%>
			</select>
			<em class="em1">创建时间：</em>
			<input type="text" name="gtCreateTime" id="gtCreateTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" size="20" value="<%=ParamKit.getParameter(request, "gtCreateTime", "")%>" /> - 
			<input type="text" name=ltCreateTime id="ltCreateTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" size="20" value="<%=ParamKit.getParameter(request, "ltCreateTime", "")%>" />
			
			<input type="hidden" name="goAction" id="goAction"/>
			<input type="submit" name="" class="search_btn_sub" onclick="submitForm('display')" value="查询" />
			<input type="button" name="" class="search_btn_sub" onclick="submitForm('export')" value="导出">	
		</div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_style">
			<tr>
				<th>发运订单ID</th>
				<th>销售订单ID</th>
				<th>商店</th>
				<th>收货人</th>
				<th>收货地址</th>
				<th>发货方</th>
				<th>运输公司</th>
				<th>运单号</th>
				<th>创建时间</th>
				<th>发运订单状态</th>
				<th>操作</th>
			</tr>
			<%
				for (DispatchOrderInfoBean bean : list) {
			%>
			<tr>
				<td><%=bean.getDoId()%></td>
				<td><a href="/order/SalesOrderInfoManage.do?soId=<%=bean.getSoId()%>"><%=bean.getSoId()%></a></td>
				<td><%=MapKit.getValueFromMap(bean.getStoreId(), storeNameMap)%></td>
				<td><%=bean.getReceiverName()%></td>
				<td><%=bean.getProvince() + bean.getCity() + bean.getRegion() + bean.getAddress()%></td>
				<td><%=MapKit.getValueFromMap(bean.getConsignerId(), DeliveryMethodConfig.DELIVERY_WAREHOUSE_MAP) %></td>
				<td id="doTransporterName_<%=bean.getDoId()%>"><%=transporterInfoMediator.getTransporterNameByKey(bean.getTransporterType(), bean.getTransporterId())%></td>
				<td id="doTransportNumber_<%=bean.getDoId()%>"><%=bean.getTransportNumber()%></td>
				<td><%=DateKit.formatTimestamp(bean.getCreateTime(), DateKit.DEFAULT_DATE_TIME_FORMAT)%></td>
				<td id="doStatus_<%=bean.getDoId()%>"><%=MapKit.getValueFromMap(bean.getDoStatus(), DispatchOrderStatusConfig.DISPATCH_ORDER_STATUS_MAP)%></td>
				<td align="center">
				<%
					if (adminHelper.hasPermission(OmsResource.DISPATH_ORDER, OperationConfig.UPDATE)) {
				%> <a href="DispatchOrderInfoManage.do?doId=<%=bean.getDoId()%>">修改</a> <%
				 	}
				 %>
				</td>
			</tr>
			<%
				}
			%>
		</table>
	</form>
	<ul class="page_info">
		<!-- 
		<li class="page_left_btn">
			<%
				if (adminHelper.hasPermission(OmsResource.SALES_ORDER, OperationConfig.CREATE)) {
			%><input type="button" name="button" id="button" value="新建" onclick="javascript:location.href='DispatchOrderInfoManage.do'"> <%
			 	}
			 %>			 		
		</li>
		 -->
		<%@ include file="../../include/page.jsp"%>
	</ul>
</body>
<script>
function submitForm(goAction){
	var form = $('#form');
	$('#goAction').val(goAction);
	form.submit();
}
</script>
</html>