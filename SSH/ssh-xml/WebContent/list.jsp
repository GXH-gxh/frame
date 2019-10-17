<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>学生列表</title>
    <link href="${pageContext.request.contextPath }/css/list.css" rel="stylesheet">
</head>
<body>
<div id="all">
    <h1>学生信息列表</h1>
    <div class="title">
        <div id="searchBox">
            <label>学生姓名：</label>
            <input type="text" name="s_username" id="s_username" value="${stuQueryVO.studentname }" placeholder="请输入要查询的名称">
            <input type="button" onclick="search(1)" value="查询">
        </div>
        <button>添加学生</button>

    </div>

    <table border="1" cellspacing="0" align="center" width="800px">
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>电话</th>
            <th>年级</th>
            <th>生日</th>
            <th>操作</th>
        </tr>
        <!-- studentList：值栈中获取 -->
        <s:iterator value="studentList">
	        <tr>
	            <td>${id }</td>
	            <td>${studentname }</td>
	            <td>${sex }</td>
	            <td>${phone }</td>
	            <td>${grade.gradeName }</td>
	            <td>
	            	<s:date name="birthday" format="yyyy-MM-dd"/>
	            </td>
	            <td>
	                <a href="#">修改</a>
	                <a href="#">删除</a>
	            </td>
	        </tr>
        </s:iterator>
    </table>

    <div class="page">
        <input type="button" value="首页" onclick="search(1)">
        <input type="button" value="上一页" onclick="search(${page.prevPage})">
        <input type="button" value="下一页" onclick="search(${page.nextPage})">
        <input type="button" value="末页" onclick="search(${page.totalPage})">
    </div>

</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

	function search(pageNo){
		//获取学生姓名
		var studentname = $("#s_username").val().trim();
		//如果学生姓名为空，分页查询
		if(studentname=="" || studentname.length==0){
			//page.pageNo：其中page属性是BaseAction的表达式封装，pageNo是PageBean分页工具类中的属性
			location.href="${pageContext.request.contextPath }/student/stu_findStudentList.action?page.pageNo="+pageNo;
		}else{
			///如果学生姓名不为空，姓名查询
			location.href="${pageContext.request.contextPath }/student/stu_findStudentList.action?page.pageNo="+pageNo+"&stuQueryVO.studentname="+studentname;
		}
	}
</script>
</html>