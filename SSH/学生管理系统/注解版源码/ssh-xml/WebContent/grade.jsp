<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>添加年级</title>
    <link href="${pageContext.request.contextPath }/css/add.css" rel="stylesheet">
</head>
<body>
<div id="all">
    <h1>添加年级信息</h1>
    <form action="#" method="post">
        <div class="input">
            <label>年级名称：</label>
            <input type="text" name="gradeName" id="gradeName" onblur="check()">
        </div>
        <div class="input">
            <span id="msg"></span>
        </div>
    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	function check(){
		var gradeName = $("#gradeName").val().trim();
		if(gradeName=="" || gradeName.length==0){
			$("#msg").html("<font color=red>年级名称不能为空</font>");
			return;
		}
		/*
		$.post("${pageContext.request.contextPath }/grade/check.action",{"gradeName":gradeName},function(result){
			if(result=="true"){
				$("#msg").html("<font color=red>年级名称已存在</font>");
			}else{
				$("#msg").html("<font color=green>年级名称可以使用</font>");
			}
		},"text");
		*/
		$.post("${pageContext.request.contextPath }/grade/checkJson.action",{"gradeName":gradeName},function(result){
			if(result.state){
				$("#msg").html("<font color=red>年级名称已存在1</font>");
			}else{
				$("#msg").html("<font color=green>年级名称可以使用1</font>");
			}
		},"json");
	}
</script>

</body>
</html>