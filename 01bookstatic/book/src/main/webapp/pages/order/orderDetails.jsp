<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hensen
  Date: 2021/11/28
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单详情</title>
    <%--		静态包含base标签，css样式，jQuery文件--%>
    <%@ include file="/pages/common/head.jsp"%>
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
            <td>商品名称</td>
            <td>商品数量</td>
            <td>商品单价</td>
            <td>商品总价</td>
        </tr>
        <c:forEach items="${requestScope.orderItems}" var="orderItem">
            <tr>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>${orderItem.price}</td>
                <td>${orderItem.totalPrice}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<%--	静态包含页脚--%>
<%@ include file="/pages/common/foot.jsp"%>
</body>
</html>
