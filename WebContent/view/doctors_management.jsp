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
						<span class="fl">医师管理</span> <a href="javascript:;"
							class="add_doctors"><i class="iconfont icon-jiahao"></i>添加医师</a>
					</h3>
					<div class="account_main_pad">
						<div class="search_table">
							<form name="searchFrm" action="${ctx }/doctorServlet.do"
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
										<td><a href="javascript:;" class="blue"
											onclick="editorQuery(${doctor.docId})">编辑</a>&nbsp;&nbsp; <a
											href="javascript:;" class="blue"
											onclick="deleteDoc(${doctor.docId})">删除</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="centen_page">
							<a class="disabled"
								href="${ctx}/doctorServlet.do?currentPage=${pageDomain.currentPage-1}">上一页</a>
							<a href="${ctx}/doctorServlet.do?currentPage=1" class="page_on">首页</a>
							<!-- <a href="">2</a>
										<a href="">3</a>
										<a href="">4</a>
										<a href="">5</a> -->
							<a
								href="${ctx}/doctorServlet.do?currentPage=${pageDomain.currentPage+1}">下一页</a>
							<a
								href="${ctx}/doctorServlet.do?currentPage=${pageDomain.totalPage}">最后一页</a>
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
<div id="adddoctorsLayer" class="hidden">
	<div class="layerDiv">
		<form name="doctorAddFrm"
			action="${ctx}/doctorServlet.do?handlerType=add" method="post">
			<div class="input_group clearfix">
				<label class="input_text">医生姓名：</label>
				<div class="input_control">
					<input type="text" name="docName" class="input_long error_border" />
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
							<option value="${docTitle.docTitle}">${docTitle.docTitle }</option>
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
							<option value="${hospital.hosName}">${hospital.hosName }</option>
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
							<option value="${dept.deptName}">${dept.deptName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="input_group clearfix">
				<label class="input_text">预约费用：</label>
				<div class="input_control">
					<input type="text" name="orderPrice" class="input_long" />&nbsp;
				</div>
			</div>
			<div class="input_group clearfix">
				<label class="input_text">擅长：</label>
				<div class="input_control">
					<textarea class="input_long" name="expertDesc"
						placeholder="请填写备注信息"></textarea>
				</div>
			</div>
			<div class="input_group clearfix">
				<label class="input_text">备注：</label>
				<div class="input_control">
					<textarea class="input_long" name="memo" placeholder="请填写备注信息"></textarea>
				</div>
			</div>
			<div class="button_div">
				<button type="button" onclick="doctorCommit()"
					class="btn_block100 btn_default closeLayer">提 交</button>
				<button type="button" class="btn_block100 btn_cancel">重 置</button>
			</div>
		</form>
	</div>
</div>
<!-- layer弹框 end-->

<!--引用js-->
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/layer/layer.js"></script>
<script>
	$(document).ready(function() {
		$('.add_doctors').on('click', function() {
			var index = layer.open({
				type : 1,
				skin : 'demo-class',
				title : '添加医师',
				area : [ '600px', '620px' ],
				shadeClose : true, //点击遮罩关闭
				content : $('#adddoctorsLayer')
			});
			$('.closeLayer').on('click', function() {
				layer.close(index);
			});
		});
	});

	//搜索
	function search() {
		searchFrm.submit();
	}
	
	//提交添加医师表单
	function doctorCommit(){
		doctorAddFrm.submit();
	}
	
	//删除医师
	function  deleteDoc(docId){
		if(docId==null||docId==''){
			return false;
		}
		var msg = "您真的确定要删除吗？\n\n请确认！";
		if (confirm(msg)==true){
			window.location.href = '${ctx}/doctorServlet.do?handlerType=del&docId=' +docId;
			return true;
		}else{
			return false;
		}

	}
	
	//编辑医师
	//编辑前先查询
	function editorQuery(docId){
		window.location.href='${ctx}/doctorServlet.do?handlerType=editorQuery&docId=' +docId;
	}
	
</script>
</body>
</html>
