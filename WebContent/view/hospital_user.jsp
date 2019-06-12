<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath()%>"></c:set>
<jsp:include page="header.jsp"></jsp:include>
<div class="main">
	<div class="main_pad">
		<div class="account_box clearfix">

			<div class="account_slide">
				<div class="account_slide_list">
					<c:if test="${activeCss=='yiguan'}">
						<c:set var="yiguanActive" value="active"></c:set>
					</c:if>
					<a href="${ctx}/UserHopsitalServlet.do"
						class="account_slide_title ${yiguanActive }"><i
						class="iconfont icon-souyiyuan"></i>医馆列表</a>
				</div>
			</div>
			<div class="account_main">
				<div class="account_main_block">
					<h3 class="account_main_title clearfix">
						<span class="fl">医馆列表</span>
					</h3>
					<div class="account_main_pad">
						<div class="search_table">
							<form name="searchFrm" action="${ctx }/UserHopsitalServlet.do"
								method="post">
								<table width="100%">
									<colgroup>
										<col class="search_col01">
										<col class="search_col02">
										<col class="search_col03">
										<col class="search_col04">
										<col class="search_col05">
										<col class="search_col06">
									</colgroup>
									<tr>
										<td class="text_right">医馆名称：</td>
										<td><input type="text" class="input_common input_middle"
											name="hosName" /></td>
										<td class="text_right">医馆地址：</td>
										<td><input type="text" class="input_common input_middle"
											name="hosAddr" /></td>
										<td colspan="2">
											<button type="button" class="btn btn_default"
												onclick="search()">搜 索</button>
										</td>
									</tr>
								</table>
							</form>
						</div>
						<table class="alt" cellpadding="0" cellspacing="0">
							<colgroup>
								<col class="hospital_col01">
								<col class="hospital_col02">
								<col class="hospital_col03">
								<col class="hospital_col04">
								<col class="hospital_col05">
							</colgroup>
							<thead>
								<tr>
									<th>医馆名称</th>
									<th>医馆地址</th>
									<th>医师总数</th>
									<th>科室总数</th>
									<th>备注</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pageDomain.pageData}" var="hospital">
									<tr>
										<td><a href="${ctx}/UserDoctorServlet.do?hosName=${hospital.hosName}" class="blue">${hospital.hosName}</a></td>
										<td>
											<p class="hospital_adress">${hospital.hosProvince}${hospital.hosCity}${hospital.hosArea}${hospital.hosAddr}</p>
										</td>
										<td>${hospital.doctorNum }</td>
										<td>${hospital.deptNum }</td>
										<td>${hospital.memo}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="centen_page">
							<a class="disabled"
								href="${ctx}/UserHopsitalServlet.do?currentPage=${pageDomain.currentPage-1}">上一页</a>
							<a href="${ctx}/UserHopsitalServlet.do?currentPage=1"
								class="page_on">首页</a>
							<!-- <a href="">2</a>
										<a href="">3</a>
										<a href="">4</a>
										<a href="">5</a> -->
							<a
								href="${ctx}/UserHopsitalServlet.do?currentPage=${pageDomain.currentPage+1}">下一页</a>
							<a
								href="${ctx}/UserHopsitalServlet.do?currentPage=${pageDomain.totalPage}">最后一页</a>
							<span>共<strong>${pageDomain.totalCount}</strong>条记录&nbsp;&nbsp;<strong>${pageDomain.currentPage}</strong>/${pageDomain.totalPage}页
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="footer">
	<div class="container">
		<div class="footer_link">
			<p>2007-2019 All Rights Reserved. xxxxxxx有限公司 版权所有 沪ICP备xxxxxx号</p>
		</div>
	</div>
</div>


<!--引用js-->
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/layer/layer.js"></script>
<script>
	function search() {
		searchFrm.submit();
	}

	
</script>
</body>
</html>
