<%--
  Created by IntelliJ IDEA.
  User: yp
  Date: 12/12
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
</head>
<body>
<center>
    <h1>用户注册</h1>
    <form action="register" method="post">
        姓名:<input type="text" name="username"><br>
        密码:<input type="password" name="password"><br>
        昵称:<input type="text" name="nickname"><br>
        地址:<input type="text" name="address"><br>
        邮箱:<input type="text" name="email" /><br/>
        性别:<input type="radio" name="gender" value="female">女
        <input type="radio" name="gender" value="male">男<br>
        <input type="submit" value="注册">
    </form>
</center>
</body>
</html>
