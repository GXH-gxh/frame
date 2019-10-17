<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>添加学生</title>
    <link href="${pageContext.request.contextPath }/css/add.css" rel="stylesheet">
</head>
<body>
<div id="all">
    <h1>添加学生信息</h1>
    <form action="${pageContext.request.contextPath }/student/stu_add.action" method="post">
        <div class="input">
            <label>姓名：</label>
            <input type="text" name="student.studentname" id="username">
        </div>
        <div class="input" style="margin-right:120px">
            <label>性别：</label>
            <input type="radio" name="student.sex" value="男" checked >男
            <input type="radio" name="student.sex" value="女">女
        </div>
        <div class="input">
            <label>电话：</label>
            <input type="text" name="student.phone" id="phone">
        </div>
        <div class="input">
            <label>年级：</label>
            
           <%--  <select name="gradeId" style="width: 160.8px" id="gradeId">
                <option value="">请选择</option>
                <s:iterator value="gradeList">
                	<option value="${id }">${gradeName }</option>
                </s:iterator>
            </select> --%>
            <s:select style="width: 160.8px" name="student.grade.id" list="gradeList" listKey="id" listValue="gradeName" headerKey="-1" headerValue="请选择年级"></s:select>
        </div>
        <div class="input">
            <label>生日：</label>
            <input type="text" name="student.birthday" id="birthday">
        </div>
        <div class="input">
            <input type="submit" value="保存" class="btn" id="btn">
            <input type="button" value="返回" class="btn">
        </div>
    </form>
</div>

</body>
</html>