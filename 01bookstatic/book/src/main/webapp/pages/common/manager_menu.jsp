<%--
  Created by IntelliJ IDEA.
  User: hensen
  Date: 2021/11/6
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ include file="/pages/common/head.jsp"%>--%>
<div>
    <a href="manager/bookServlet?action=page">图书管理</a>
    <a href="manager/orderServlet?action=showMyOrder&userid=${sessionScope.user.id}">订单管理</a>
    <a href="index.jsp">返回商城</a>
</div>
