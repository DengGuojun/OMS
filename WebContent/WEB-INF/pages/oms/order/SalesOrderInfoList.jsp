<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lpmas.framework.bean.*"%>
<%@ page import="com.lpmas.framework.page.*"%>
<%@ page import="com.lpmas.framework.util.*"%>
<%@ page import="com.lpmas.framework.web.*"%>
<%@ page import="com.lpmas.oms.order.bean.*"%>
<%@ page import="com.lpmas.oms.order.config.*"%>
<%@ page import="com.lpmas.oms.config.*"%>
<%@ page import="com.lpmas.admin.business.*"%>
<%@ page import="com.lpmas.admin.config.*"%>
<%@ page import="com.lpmas.system.bean.*"%>
<%
	AdminUserHelper adminHelper = (AdminUserHelper) request.getAttribute("AdminUserHelper");
	List<SalesOrderInfoBean> list = (List<SalesOrderInfoBean>) request.getAttribute("OrderList");

	PageBean PAGE_BEAN = (PageBean) request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>) request.getAttribute("CondList");

	List<StoreInfoBean> storeList = (List<StoreInfoBean>) request.getAttribute("StoreList");
	Map<Integer, String> storeNameMap = ListKit.list2Map(storeList, "storeId", "storeName");
	
	HashMap<Integer,String> traceSourceInfoMap = (HashMap<Integer,String>) request.getAttribute("TraceSourceInfoMap");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>销售订单列表</title>
<%@ include file="../../include/header.jsp"%>
<link href="<%=STATIC_URL%>/js/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
<script type='text/javascript'src="<%=STATIC_URL%>/js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">
	function approveSaleOrder() {
		var soIds = getCheckboxValue('selectSoId', ',');
		if (strLength(soIds) == 0) {
			alert("请选择需要审批的订单");
			return;
		}
		jQuery.getJSON("SalesOrderApprove.do", {
			soIds : soIds
		}, function(data) {
			var obj = jQuery.parseJSON(data);
			var failResult = "";
			for(var j=0;j<obj.failList.length;j++){
				var soId = obj.failList[j];
				failResult += "\n ["+soId+"]审核失败"
			}
			if(obj.failList.length >0){
				alert("订单审批完成,以下订单审核失败"+failResult);
			}else{
				alert("订单审批完成")
			}
			
			for(var i=0;i < obj.successList.length;i++){
				var soId = obj.successList[i];
				jQuery("#soStatus_"+soId).html("<%=MapKit.getValueFromMap(SalesOrderStatusConfig.ORDS_APPROVED, SalesOrderStatusConfig.ORDER_STATUS_MAP)%>");
			}
			
			
		});
	}
</script>
</head>
<body class="article_bg">
	<p class="article_tit">销售订单列表</p>
	<form name="formSearch" method="post" action="SalesOrderInfoList.do">
		<div class="search_form">
			<em class="em1">订单ID：</em>
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
			<em class="em1">外部订单号：</em>
			<input type="text" name="outerOrderId" id="outerOrderId" value="<%=ParamKit.getParameter(request, "outerOrderId", "")%>" size="20" />
			<em class="em1">创建时间：</em>
			<input type="text" name="gtCreateTime" id="gtCreateTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" size="20" value="<%=ParamKit.getParameter(request, "gtCreateTime", "")%>" /> - 
			<input type="text" name="ltCreateTime" id="ltCreateTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" size="20" value="<%=ParamKit.getParameter(request, "ltCreateTime", "")%>" />
			<em class="em1">订单状态：</em>
			<select id="soStatus" name="soStatus">
				<option value=""></option>
				<%
					String soStatus = ParamKit.getParameter(request, "soStatus", "");
					for (StatusBean<String, String> statusBean : SalesOrderStatusConfig.ORDER_STATUS_LIST) {
				%>
				<option value="<%=statusBean.getStatus()%>" <%=statusBean.getStatus().equals(soStatus) ? "selected" : ""%>><%=statusBean.getValue()%></option>
				<%
					}
				%>
			</select>
			<input name="" type="submit" class="search_btn_sub" value="查询" />
		</div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_style">
			<tr>
				<th><input type="checkbox" name="selectAllSoId" id="selectAllSoId" onclick="javascript:changeChiefCheckbox('selectAllSoId','selectSoId');" /></th>
				<th>订单ID</th>
				<th>商店</th>
				<th>来源</th>
				<th>外部订单号</th>
				<th>收货人</th>
				<th>收货地址</th>
				<th>创建时间</th>
				<th>订单状态</th>
				<th>操作</th>
			</tr>
			<%
				for (SalesOrderInfoBean bean : list) {
			%>
			<tr>
				<td><input type="checkbox" name="selectSoId" id="selectSoId" value="<%=bean.getSoId()%>" /></td>
				<td><%=bean.getSoId()%></td>
				<td><%=MapKit.getValueFromMap(bean.getStoreId(), storeNameMap)%></td>
				<td><%=MapKit.getValueFromMap(bean.getSoId(), traceSourceInfoMap)%></td>
				<td><%=bean.getOuterOrderId()%></td>
				<td><%=bean.getReceiverName()%></td>
				<td><%=bean.getProvince() + bean.getCity() + bean.getRegion() + bean.getAddress()%></td>
				<td><%=DateKit.formatTimestamp(bean.getCreateTime(), DateKit.DEFAULT_DATE_TIME_FORMAT)%></td>
				<td id="soStatus_<%=bean.getSoId()%>"><%=MapKit.getValueFromMap(bean.getSoStatus(), SalesOrderStatusConfig.ORDER_STATUS_MAP)%></td>
				<td align="center">
					<%
						if (adminHelper.hasPermission(OmsResource.SALES_ORDER, OperationConfig.UPDATE)) {
					%><a href="SalesOrderInfoManage.do?soId=<%=bean.getSoId()%>">修改</a> <%
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
		<li class="page_left_btn">
			<!-- 
			<%
				if (adminHelper.hasPermission(OmsResource.SALES_ORDER, OperationConfig.CREATE)) {
			%><input type="button" name="button" id="button" value="新建" onclick="javascript:location.href='SalesOrderInfoManage.do'"> <%
 			}
 			%> 
 			 -->
 			<%
			 	if (adminHelper.hasPermission(OmsResource.SALES_ORDER, OperationConfig.UPDATE)) {
			 %><input type="button" name="btnApprove" id="btnApprove" value="审批" onclick="javascript:approveSaleOrder();"> <%
			 	}
			 %>
		</li>
		<%@ include file="../../include/page.jsp"%>
	</ul>
</body>
</html>