<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%--		静态包含base标签，css样式，jQuery文件--%>
	<%@ include file="/pages/common/head.jsp"%>
<%--	<link type="text/css" rel="stylesheet" href="../../static/css/style.css" >--%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
<%--		静态包含manage页面--%>
		<%@ include file="/pages/common/manager_menu.jsp"%>
<%--			<div>--%>
<%--				<a href="book_manager.jsp">图书管理</a>--%>
<%--				<a href="order_manager.jsp">订单管理</a>--%>
<%--				<a href="../../index.jsp">返回商城</a>--%>
<%--			</div>--%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
				<td>发货</td>
			</tr>
			<c:forEach items="${requestScope.page.items}" var="order">
				<tr>
					<td>
						<fmt:formatDate value="${order.createTime}" pattern='yyyy-MM-dd'/>
					</td>
					<td>${order.price}</td>
					<td>
						<c:if test="${order.status == 0 }">
							未发货
						</c:if>

						<c:if test="${order.status == 1 }">
							已发货
						</c:if>

						<c:if test="${order.status == 2 }">
							已收货
						</c:if>
					</td>
					<td><a href="manager/orderServlet?action=orderDetails&orderId=${order.orderId}">查看详情</a></td>
					<td><a href="manager/orderServlet?action=sendOrder&orderId=${order.orderId}">点击发货</a></td>
				</tr>
			</c:forEach>
		</table>
		<%@include file="/pages/common/page_nav.jsp"%>
	</div>

	<%--	静态包含页脚--%>
	<%@ include file="/pages/common/foot.jsp"%>
</body>
</html>