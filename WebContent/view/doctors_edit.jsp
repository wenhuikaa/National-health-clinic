<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath()%>"></c:set>
<jsp:include page="header.jsp"></jsp:include>
<div class="main">
	<div class="main_pad">
		<div class="account_box clearfix">
			<jsp:include page="nav.jsp"></jsp:include>
			<div class="account_main">
				<div class="account_main_block">
					<h3 class="account_main_title clearfix">
						<span>修改医师</span>
					</h3>
					<div class="account_main_pad">
						<form  name="doctorAddFrm" action="${ctx}/doctorServlet.do?handlerType=editorCommit" method="post">
						<input type="hidden" name="docId" value="${doctor.docId}">
							<div class="input_group">
								<label class="input_text">医生姓名：</label>
								<div class="input_control">
									<input type="text" class="input_long error_border"
										name="docName" value="${doctor.docName}" />
									<p class="error_text">错误提示</p>
								</div>
							</div>
							<div class="input_group clearfix">
								<label class="input_text">职称：</label>
								<div class="input_control">
									<select class="input_long" name="docTitle" id="docTitle"
										onclick="getDocTitle(this)">
										<option>请选择</option>
										<c:forEach items="${docTitles }" var="docTitle">
											<c:if test="${docTitle.docTitle==doctor.docTitle }">
												<c:set var="docTitleSelect" value="selected"></c:set>
											</c:if>
											<option value="${docTitle.docTitle}" ${docTitleSelect }>${docTitle.docTitle }</option>
											<c:set var="docTitleSelect" value=""></c:set>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="input_group clearfix">
								<label class="input_text">所属医院：</label>
								<div class="input_control">
									<select class="input_long" name="hosName" id="hosName">
										<option>请选择</option>
										<c:forEach items="${hospitals }" var="hospital">
											<c:if test="${hospital.hosName==doctor.hosName }">
												<c:set var="hosSelect" value="selected"></c:set>
											</c:if>
											<option value="${hospital.hosName}" ${hosSelect }>${hospital.hosName }</option>
											<c:set var="hosSelect" value=""></c:set>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="input_group clearfix">
								<label class="input_text">所在科室：</label>
								<div class="input_control">
									<select class="input_long" name="deptName" id="deptName">
										<option>请选择</option>
										<c:forEach items="${depts }" var="dept">
											<c:if test="${dept.deptName==doctor.deptName }">
												<c:set var="deptSelect" value="selected"></c:set>
											</c:if>
											<option value="${dept.deptName}" ${deptSelect }>${dept.deptName }</option>
											<c:set var="deptSelect" value=""></c:set>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="input_group">
								<label class="input_text">预约费用：</label>
								<div class="input_control">
									<input type="text" class="input_long" name="orderPrice"
										value="${doctor.orderPrice }" />&nbsp;
								</div>
							</div>
							<div class="input_group">
								<label class="input_text">擅长：</label>
								<div class="input_control">
									<textarea class="input_long" placeholder="请填写备注信息"
										name="expertDesc">${doctor.expertDesc }</textarea>
								</div>
							</div>
							<div class="input_group">
								<label class="input_text">备注：</label>
								<div class="input_control">
									<textarea class="input_long" placeholder="请填写备注信息" name="memo">${doctor.memo }</textarea>
								</div>
							</div>
							<div class="input_group">
								<label class="input_text"></label>
								<div class="input_control">
									<button type="button" class="btn_block100 btn_default" onclick="editorCommit()">提
										交</button>
									<button type="button" class="btn_block100 btn_cancel">重
										置</button>
								</div>
							</div>
						</form>
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

	
	function editorCommit(){
		doctorAddFrm.submit();
	}
</script>
</body>
</html>

