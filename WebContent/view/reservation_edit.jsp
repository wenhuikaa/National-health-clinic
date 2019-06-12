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
					<c:if test="${activeCss=='yuyue'}">
						<c:set var="yuyueActive" value="active"></c:set>
					</c:if>
					<a href="${ctx}/UserHopsitalServlet.do"
						class="account_slide_title ${yuyueActive}"><i
						class="iconfont icon-icon77"></i>预约单</a>
				</div>
			</div>
			<div class="account_main">
				<div class="account_main_block">
					<h3 class="account_main_title clearfix">
						<span>填写预约单</span>
					</h3>
					<div class="account_main_pad">
						<form name="doctorAddFrm"
							action="${ctx}/UserOrderServlet.do?handlerType=add" method="post">
							<input type="hidden" name="docId" value="${doctor.docId}">
							<div class="input_group">
								<label class="input_text">医馆名称：</label>
								<div class="input_control">
									<input type="text" class="input_long" name="hosName"
										value="${doctor.hosName}" />
								</div>
							</div>
							<div class="input_group">
								<label class="input_text">科室名称：</label>
								<div class="input_control">
									<input type="text" class="input_long" name="deptName"
										value="${doctor.deptName}" />
								</div>
							</div>
							<div class="input_group">
								<label class="input_text">医生姓名：</label>
								<div class="input_control">
									<input type="text" class="input_long" name="docName"
										value="${doctor.docName}" />
								</div>
							</div>
							<div class="input_group">
								<label class="input_text">费用：</label>
								<div class="input_control">
									<input type="text" class="input_long" name="orderPrice"
										value="${doctor.orderPrice}" />
								</div>
							</div>
							<div class="input_group clearfix">
								<label class="input_text">类型：</label>
								<div class="input_control">
									<select class="input_long" name="orderType">
										<option>请选择</option>
										<option value="1">简易门诊</option>
										<option value="2">老专家门诊</option>
										<option value="3">其他门诊</option>
									</select>
								</div>
							</div>
							<div class="input_group clearfix">
								<label class="input_text">初复诊：</label>
								<div class="input_control">
									<select class="input_long" name="orderCount">
										<option>请选择</option>
										<option value="1">初诊</option>、
										<option value="2">复诊</option>
									</select>
								</div>
							</div>
							<div class="input_group">
								<label class="input_text">预约人姓名：</label>
								<div class="input_control">
									<input type="text" class="input_long" name="custName" />
								</div>
							</div>
							<div class="input_group">
								<label class="input_text">预约人电话：</label>
								<div class="input_control">
									<input type="text" class="input_long" name="phone" />
								</div>
							</div>
							<div class="input_group">
								<label class="input_text">预约人描述：</label>
								<div class="input_control">
									<textarea class="input_long" name="custDesc"></textarea>
								</div>
							</div>


							<div class="input_group">
								<label class="input_text"></label>
								<div class="input_control">
									<button type="button" class="btn_block100 btn_default"
										onclick="editorCommit()">提 交</button>
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
	function editorCommit() {
		doctorAddFrm.submit();
		var msg="<%=session.getAttribute("message")%>"
		alert(msg);
	}
</script>
</body>
</html>

