<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户登录</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/user/login.action" method="post">
	userName:<input type="text" name="userName" value="${user.userName }"/><br/>
	password:<input type="password" name="password" value="${user.password }"><br/>
	<input type="submit" value="登录"/><font color="red">${errorInfo }</font>
</form>
</body>
</html>