<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>教师登录</title>
    <link href="${pageContext.request.contextPath }/css/login.css" rel="stylesheet">
</head>
<body>
<div id="all">
    <h1>教师登录</h1>
    <form action="teacher/login.action" method="post">
        <div class="input">
            <label>用户名：</label>
            <input type="text" name="username" id="username">
        </div>
        <div class="input">
            <label>密&nbsp;&nbsp;&nbsp;码：</label>
            <input type="password" name="password" id="password">
        </div>
        <div class="input">
            <input type="submit" value="登录" id="btn">
        </div>
    </form>
</div>
</body>
</html>