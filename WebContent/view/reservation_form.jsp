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
						<h3 class="account_main_title">
							<span>预约单</span>
						</h3>
						<div class="account_main_pad">
							<div class="search_table">
								<form name="searchFrm" action="${ctx }/orderInfoServlet.do?"
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
											<td class="text_right">预约单：</td>
											<td><input type="text" class="input_common input_middle"
												name="orderCode" /></td>
											<td colspan="4">
												<button type="button" class="btn btn_default"
													onclick="search()">搜 索</button>
											</td>
										</tr>
									</table>
								</form>
							</div>
							<div class="table_warp">
								<table class="alt" cellpadding="0" cellspacing="0">
									<thead>
										<tr>
											<th>预约单号</th>
											<th>预约医生信息</th>
											<th>预约联系人信息</th>
											<th>类型</th>
											<th>费用</th>
											<th>初/复诊</th>
											<th>预约时间</th>
											<th>就诊时间</th>
											<th>备注</th>
											<th>状态</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${pageDomain.pageOrder}" var="orderInfo">
											<tr>
												<td>
													<div class="col01 over_hidden">${orderInfo.orderCode }</div>
												</td>
												<td>
													<p class="table_list">
														<label class="table_list_title">医生：</label>
														<c:forEach items="${doctors }" var="doctor">
															<c:if test="${ orderInfo.docId==doctor.docId}">
																<span class="table_list_info">${doctor.docName }</span>
															</c:if>
														</c:forEach>
													</p>
													<p class="table_list">
														<label class="table_list_title">科室：</label>
														<c:forEach items="${doctors }" var="doctor">
															<c:if test="${ orderInfo.docId==doctor.docId}">
																<span class="table_list_info">${doctor.deptName }</span>
															</c:if>
														</c:forEach>
													</p>
													<p class="table_list">
														<label class="table_list_title">医馆：</label>
														<c:forEach items="${doctors }" var="doctor">
															<c:if test="${ orderInfo.docId==doctor.docId}">
																<span class="table_list_info">${doctor.hosName }</span>
															</c:if>
														</c:forEach>
													</p>
												</td>
												<td class="text_left">
													<p class="table_list">
														<label class="table_list_title">姓名：</label>
														<c:forEach items="${customers}" var="customer">
															<c:if test="${customer.custId==orderInfo.custId }">
																<span class="table_list_info">${customer.custName}</span>
															</c:if>
														</c:forEach>
													</p>
													<p class="table_list">
														<label class="table_list_title">手机号：</label>
														<c:forEach items="${customers}" var="customer">
															<c:if test="${customer.custId==orderInfo.custId }">
																<span class="table_list_info">${customer.phone }</span>
															</c:if>
														</c:forEach>
													</p>
													<p class="table_list">
														<label class="table_list_title">预约描述：</label>
														<c:forEach items="${customers}" var="customer">
															<c:if test="${customer.custId==orderInfo.custId }">
																<span class="table_list_info">${customer.custDesc }</span>
															</c:if>
														</c:forEach>
													</p>
												</td>
												<td><c:forEach items="${orderTypes}" var="orderType">
														<c:if
															test="${orderType.orderTypeId==orderInfo.orderType }">
															<div class="col04">${orderType.orderType }</div>
														</c:if>
													</c:forEach></td>
												<td>
													<div class="col05">￥${orderInfo.price }</div>
												</td>
												<td><c:forEach items="${orderCount}" var="orderCount">
														<c:if
															test="${orderCount.orderCountId==orderInfo.orderCount }">
															<div class="col06">${orderCount.orderCountName}</div>
														</c:if>
													</c:forEach></td>
												<td>
													<div class="col07">
														<script>document.write("${orderInfo.orderDate }".substring(0, 19));</script>
													</div>
												</td>
												<td>
													<div class="col08">
														<script>document.write("${orderInfo.treatDate }".substring(0, 19));</script>
													</div>
												</td>
												<td>
													<div class="col09">${orderInfo.memo }</div>
												</td>
												<td><c:forEach items="${treatStates}" var="treatState">
														<c:if
															test="${treatState.treatStateId==orderInfo.treatState }">
															<div class="col10">${treatState.treatState }</div>
														</c:if>
													</c:forEach></td>
												<td>
													<div class="col11">
														<a href="javascript:;" class="blue"
															onclick="treatFailed(${orderInfo.orderId})">就诊失败</a> <a
															href="javascript:;" class="blue"
															onclick="treatSucceed(${orderInfo.orderId})">就诊成功</a>
													</div>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="centen_page">
								<a class="disabled"
									href="${ctx}/orderInfoServlet.do?currentPage=${pageDomain.currentPage-1}">上一页</a>
								<a href="${ctx}/orderInfoServlet.do?currentPage=1"
									class="page_on">首页</a> <a
									href="${ctx}/orderInfoServlet.do?currentPage=${pageDomain.currentPage+1}">下一页</a>
								<a
									href="${ctx}/orderInfoServlet.do?currentPage=${pageDomain.totalPage}">最后一页</a>
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

	<!--  layer弹框-->
	<div id="visitingLayer" class="hidden">
		<div class="layerDiv">
			<form name="updateTreatStateFrm"
				action="${ctx}/orderInfoServlet.do?handlerType=updateTreatState"
				method="post">
				<!-- 隐藏域orderId -->
				<input type="hidden" name="orderId" value="${orderInfoList.orderId}">
				<div class="input_group clearfix">
					<label class="input_text">备注：</label>
					<div class="input_control">
						<textarea class="input_long" placeholder="请填写备注信息" name="memo">${orderInfoList.memo }</textarea>
					</div>
				</div>
				<div class="button_div">
					<button type="button" onclick="commit()"
						class="btn_block100 btn_default closeLayer">提 交</button>
					<button type="button" class="btn_block100 btn_cancel closeLayer">关
						闭</button>
				</div>
			</form>
		</div>
	</div>
	<!-- layer弹框 end-->

	<!--引用js-->
	<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/layer/layer.js"></script>
	<script>
	
	//搜索
	function search() {
		searchFrm.submit();
	}
	
	//就诊成功
	function treatSucceed(orderId){
		window.location.href='${ctx}/orderInfoServlet.do?handlerType=treatSucceed&orderId=' +orderId;
	}
	
	//就诊失败
	function treatFailed(orderId){
		window.location.href='${ctx}/orderInfoServlet.do?handlerType=treatFailed&orderId=' +orderId;
	}
	
</script>
</body>
</html>
