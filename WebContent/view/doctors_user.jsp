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
					<c:if test="${activeCss=='yishi'}">
						<c:set var="yishiActive" value="active"></c:set>
					</c:if>
					<a href="${ctx}/UserHopsitalServlet.do"
						class="account_slide_title ${yishiActive }"><i
						class="iconfont icon-yisheng"></i>医师列表</a>
				</div>
			</div>
			<div class="account_main">
				<div class="account_main_block">
					<h3 class="account_main_title clearfix">
						<span class="fl">医师列表</span>
					</h3>
					<div class="account_main_pad">
						<div class="search_table">
							<form name="searchFrm" action="${ctx}/UserDoctorServlet.do?hosName=${pageDomain.hosName }"
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
										<td class="text_right">医师姓名：</td>
										<td><input type="text" name="docName"
											class="input_common input_middle" /></td>
										<td class="text_right">职 称：</td>
										<td><input type="text" name="docTitle"
											class="input_common input_middle" /></td>
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
								<col class="doctors_col01">
								<col class="doctors_col02">
								<col class="doctors_col03">
								<col class="doctors_col04">
								<col class="doctors_col05">
								<col class="doctors_col06">
								<col class="doctors_col07">
								<col class="doctors_col08">
							</colgroup>
							<thead>
								<tr>
									<th>医师姓名</th>
									<th>职称</th>
									<th>所属医院</th>
									<th>科室</th>
									<th>擅长</th>
									<th>预约费用</th>
									<th>备注</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pageDomain.pageDoctor}" var="doctor">
									<tr>
										<td>${doctor.docName }</td>
										<td>${doctor.docTitle }</td>
										<td>${doctor.hosName }</td>
										<td>${doctor.deptName}</td>
										<td>
											<p class="doctors_advance">${doctor.expertDesc }</p>
										</td>
										<td>￥${doctor.orderPrice }</td>
										<td>${doctor.memo }</td>
										<td><a href="${ctx}/UserOrderServlet.do?docId=${doctor.docId}" class="blue">预约</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="centen_page">
							<a class="disabled"
								href="${ctx}/UserDoctorServlet.do?hosName=${pageDomain.hosName }&currentPage=${pageDomain.currentPage-1}">上一页</a>
							<a href="${ctx}/UserDoctorServlet.do?hosName=${pageDomain.hosName }&currentPage=1"
								class="page_on">首页</a>
							<!-- <a href="">2</a>
										<a href="">3</a>
										<a href="">4</a>
										<a href="">5</a> -->
							<a
								href="${ctx}/UserDoctorServlet.do?hosName=${pageDomain.hosName }&currentPage=${pageDomain.currentPage+1}">下一页</a>
							<a
								href="${ctx}/UserDoctorServlet.do?hosName=${pageDomain.hosName }&currentPage=${pageDomain.totalPage}">最后一页</a>
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
	//搜索
	function search() {
		searchFrm.submit();
	}
</script>
</body>
</html>
