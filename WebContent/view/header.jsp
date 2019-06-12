<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath()%>"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>健康国医馆-${title}</title>
<link type="text/css" rel="stylesheet"
	href="${ctx}/static/css/style.css" />
</head>
<body>
	<div class="admin_header">
		<div class="container clearfix">
			<h1 class="logo">
				<a href=""> <img src="${ctx}/static/images/logo.png" />
				</a>
			</h1>
			<span class="logo_text">健康国医在线平台</span>
			<div class="heder_right">
				<a href="javascript:;" class=""  onclick="editor()"><i
					class="iconfont icon-gerenzhongxin"></i> ${ sessionScope.USER_NAME}</a> <a href="javascript:;"
					class=""  onclick="loginOut()"><i class="iconfont icon-tuichu"></i>退出</a>
			</div>
		</div>
	</div>
	
	<script>
	//编辑前先查询
	function editor(){
		window.location.href='${ctx}/userServlet.do?handlerType=editorQuery';
	}
	
	function loginOut(){
		var msg = "继续执行您将退出后台管理系统！";
		if (confirm(msg)==true){
			window.location.href='${ctx}/userServlet.do?handlerType=loginOut';
			return true;
		}else{
			return false;
		}
	}
	</script>