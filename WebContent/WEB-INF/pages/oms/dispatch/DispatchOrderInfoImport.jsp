<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lpmas.framework.bean.*"%>
<%@ page import="com.lpmas.framework.page.*"%>
<%@ page import="com.lpmas.framework.util.*"%>
<%@ page import="com.lpmas.framework.web.*"%>
<%@ page import="com.lpmas.framework.config.*"%>
<%@ page import="com.lpmas.oms.dispatch.bean.*"%>
<%@ page import="com.lpmas.oms.dispatch.config.*"%>
<%@ page import="com.lpmas.oms.config.*"%>
<%@ page import="com.lpmas.oms.order.config.*"%>
<%@ page import="com.lpmas.admin.business.*"%>
<%@ page import="com.lpmas.admin.config.*"%>
<%@ page import="com.lpmas.system.bean.*"%>
<%@ page import="com.lpmas.system.config.*"%>
<%@ page import="com.lpmas.tms.express.bean.*"%>
<%@ page import="com.lpmas.framework.tools.common.bean.ImportResultBean"  %>
<%
ImportResultBean importResultBean = (ImportResultBean)request.getAttribute("ImportResult");
List<ExpressCompanyInfoBean> expressCompanyInfoList = (List<ExpressCompanyInfoBean>)request.getAttribute("ExpressCompanyInfoList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>同步发货信息</title>
<%@ include file="../../include/header.jsp"%>
</head>
<body class="article_bg">
	<p class="article_tit">同步发货信息</p>
	<form method="post" id="formData"  enctype="multipart/form-data" action="DispatchOrderInfoImport.do">		
		<div class="modify_form">
			<p>请严格按要求输入如下快递公司名称：
		    <%for(ExpressCompanyInfoBean expressCompanyInfoBean : expressCompanyInfoList) {
                 if(expressCompanyInfoBean.getStatus() == Constants.STATUS_VALID){%>
				<%=expressCompanyInfoBean.getCompanyName()%>,
			<%}}%></p> 
			<p>
				<em class="int_label">同步发货信息：</em>
				<input type="file" name="file" id="file" size="80" checkStr="文件;txt;true;;2000" /><a href="/examples/DispatchOrderExample.xls" target="_blank">模板</a>
			</p>
			<%if(importResultBean !=null){ %>
				<div class="article_subtit">处理结果</div>
				<div class="modify_form">
					<p>
						<%=importResultBean.getSuccessMsgList().get(0) %>
					</p>
				</div>
				<%if(!importResultBean.getErrorMsgList().isEmpty()) {%>
				<div class="article_subtit">错误信息</div>
				<div class="modify_form">
				  <%for(String msg : importResultBean.getErrorMsgList()) {%>
						<p><%=msg%></p>
					<%}%> 
				</div>
				<%}%>
			 <%} %>
		</div>
		<div class="div_center">
			<p>
				
				<input type="submit" name="button" id="button" class="modifysubbtn" value="提交" />
				
			</p>
		</div>
	</form>
</body>
</html>