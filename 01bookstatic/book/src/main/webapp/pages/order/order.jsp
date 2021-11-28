<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%--		静态包含base标签，css样式，jQuery文件--%>
	<%@ include file="/pages/common/head.jsp"%>
<%--<link type="text/css" rel="stylesheet" href="../../static/css/style.css" >--%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
		<%--静态包含登录成功之后的界面--%>
		<%@ include file="/pages/common/login_success_menu.jsp"%>
	</div>

	<div id="main">

		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${requestScope.orders}" var="order">
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
					<td><a href="manager/orderServlet?action=receiveOrder&orderId=${order.orderId}">收货</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<%--	静态包含页脚--%>
	<%@ include file="/pages/common/foot.jsp"%>
</body>
</html>