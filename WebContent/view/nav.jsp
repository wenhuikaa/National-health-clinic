<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>

<div class="account_slide">
	<div class="account_slide_list">
		<c:if test="${activeCss=='yuyue'}">
			<c:set var="yuyueActive" value="active"></c:set>
		</c:if>
		<c:if test="${activeCss=='yiguan'}">
			<c:set var="yiguanActive" value="active"></c:set>
		</c:if>
		<c:if test="${activeCss=='yishi'}">
			<c:set var="yishiActive" value="active"></c:set>
		</c:if>
		<a href="${ctx }/orderInfoServlet.do"
			class="account_slide_title ${yuyueActive}"><i
			class="iconfont icon-icon77"></i>预约单</a>
	</div>
	<div class="account_slide_list">
		<a href="${ctx}/hospitalServlet.do"
			class="account_slide_title ${yiguanActive }"><i
			class="iconfont icon-souyiyuan"></i>医馆管理</a>
	</div>
	<div class="account_slide_list">
		<a href="${ctx}/doctorServlet.do"
			class="account_slide_title ${yishiActive }"><i
			class="iconfont icon-yisheng"></i>医师管理</a>
	</div>
</div>