<%--
  Created by IntelliJ IDEA.
  User: hensen
  Date: 2021/11/6
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ include file="/pages/common/head.jsp"%>--%>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
    <a href="manager/orderServlet?action=showMyOrder&userid=${sessionScope.user.id}">我的订单</a>
    <a href="user?action=logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>