<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>修改学生</title>
    <link href="${pageContext.request.contextPath }/css/add.css" rel="stylesheet">
</head>
<body>
<div id="all">
    <h1>修改学生信息</h1>
    <form action="${pageContext.request.contextPath }/student/stu_update.action" method="post">
    	<!-- 隐藏域 -->
    	<s:hidden name="student.id"></s:hidden>
        <div class="input">
            <label>姓名：</label>
            <%-- <input type="text" name="username" id="username" value="${student.studentname }"> --%>
            <s:textfield name="student.studentname"></s:textfield>
        </div>
        <div class="input" style="margin-right:120px">
            <label>性别：</label>
			<s:radio list="#{ '男' :'男','女':'女'}" name="student.sex"></s:radio>
        </div>
        <div class="input">
            <label>电话：</label>
            <!-- <input type="text" name="phone" id="phone"> -->
            <s:textfield name="student.phone"></s:textfield>
        </div>
        <div class="input">
            <label>年级：</label>
<%--             <select name="gradeId" style="width: 160.8px" id="gradeId">
                <option value="">请选择</option>
                <option value="1">S1</option>
                <option value="2">S2</option>
                <option value="3">Y2</option>
            </select> --%>
             <s:select style="width: 160.8px" name="student.grade.id" list="gradeList" listKey="id" listValue="gradeName" headerKey="-1" headerValue="请选择年级"></s:select>
        </div>
        <div class="input">
            <label>生日：</label>
            <!-- <input type="text" name="birthday" id="birthday"> -->
             <s:textfield name="student.birthday">
             	<s:param name="value"><s:date name="student.birthday" format="yyyy-MM-dd"/></s:param>
             </s:textfield>
        </div>
        <div class="input">
            <input type="submit" value="保存" class="btn" id="btn">
            <input type="button" value="返回" class="btn">
        </div>
    </form>
</div>

</body>
</html>