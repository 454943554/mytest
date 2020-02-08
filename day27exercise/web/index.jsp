<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

    <%--没有登录的情况--%>
    <c:if test="${empty user}">
        <a href="register.jsp">注册页面</a><a href="login.jsp">登录页面</a>
    </c:if>

    <%--登录情况--%>
    <c:if test="${not empty user}">
        <a href="#">Hi,${user.nickname}</a><br/>
        <a href="linkMan?method=findAll">查询所有的联系人</a><br/>
        <a href="linkMan?method=findPage&curPage=1">分页查询联系人</a><br/>
    </c:if>

</body>
</html>
