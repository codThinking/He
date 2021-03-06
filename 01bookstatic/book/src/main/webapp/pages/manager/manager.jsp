<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>
<%--	<link type="text/css" rel="stylesheet" href="../../static/css/style.css" --%>
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
			<span class="wel_word">后台管理系统</span>
		<%--		静态包含manage页面--%>
		<%@ include file="/pages/common/manager_menu.jsp"%>
	</div>
	<c:if test="${not empty requestScope.msg}">
		<div class="msg_cont">
			<b></b>
			<span class="errorMsg">${requestScope.msg}</span>
		</div>
	</c:if>
	<div id="main">
		<h1>欢迎管理员进入后台管理系统</h1>
	</div>

	<%--	静态包含页脚--%>
	<%@ include file="/pages/common/foot.jsp"%>
</body>
</html>