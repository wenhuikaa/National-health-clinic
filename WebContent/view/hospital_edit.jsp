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
							<span>修改医馆</span>
						</h3>
						<div class="account_main_pad">
							<form name="hospitalAddFrm" id="formId"
								action="${ctx}/hospitalServlet.do?handlerType=editorCommit"
								method="post">
								<input type="hidden" name="hosId" value="${hospital.hosId}">
								<div class="input_group">
									<label class="input_text">医馆名称：</label>
									<div class="input_control">
										<input type="text" name="hosName" value="${hospital.hosName }"
											class="input_long error_border" />
										<p class="error_text">错误提示</p>
									</div>
								</div>
								<div class="input_group">
									<label class="input_text">所在区域：</label>
									<div class="input_control">
										<select class="input_short" name="hosProvince"
											id="hosProvince" onchange="getCity(this)">
											<option>请选择省</option>
											<c:forEach items="${provinces}" var="province">
												<c:if
													test="${province.provinceCode == hospital.hosProvince}">
													<c:set var="proSelect" value="selected"></c:set>
												</c:if>
												<option value="${province.provinceCode}" ${proSelect}>${province.provinceName}</option>
												<c:set var="proSelect" value=""></c:set>
											</c:forEach>
										</select> &nbsp;&nbsp; <select class="input_short" name="hosCity"
											id="hosCity" onchange="getArea(this)">
											<option>请选择市</option>
										</select> &nbsp;&nbsp; <select class="input_short" name="hosArea"
											id="hosArea">
											<option>请选择区</option>
										</select>
									</div>
								</div>
								<div class="input_group">
									<label class="input_text">详细地址：</label>
									<div class="input_control">
										<textarea name="hosAddr" class="input_long"
											placeholder="请填写详细信息">${hospital.hosAddr }</textarea>
									</div>
								</div>
								<div class="input_group">
									<label class="input_text">科室选择：</label>
									<div class="input_control">
										<div class="department_box">
											<div class="department_seleted">
												<!-- 标签区域 -->
												<c:forEach items="${refs}" var="ref">
													<a href="javascript:;" id="select_un_${ref.deptId}"
														onclick="removeDept(this)">${ref.deptName }</a>
												</c:forEach>
											</div>
											<input type="hidden" name="depts" value="">
										</div>
										<div class="department_div">
											<c:forEach items="${depts}" var="dept">
												<c:if test="${dept.memo == 1 }">
													<c:set var="selected" value="class='selected'"></c:set>
												</c:if>
												<a href="javascript:;" onclick="selectDept(this)"
													${selected} id="un_${dept.deptId}">${dept.deptName}</a>
												<c:set var="selected" value=""></c:set>
											</c:forEach>
										</div>
									</div>
									<div class="input_group">
										<label class="input_text">备注：</label>
										<div class="input_control">
											<textarea name="memo" class="input_long"
												placeholder="请填写备注信息">${hospital.memo}</textarea>
										</div>
									</div>
									<div class="input_group">
										<label class="input_text"></label>
										<div class="input_control">
											<button type="button" onclick="editorCommit()"
												class="btn_block100 btn_default">提 交</button>
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
		$(document).ready(function() {
			//界面初始化后默认选中城市
			//通过id选中该对象，手动调用getCity(对象)触发联动事件
			var provinceSelect = document.getElementById("hosProvince");
			getCity(provinceSelect);

			$('.department_add').on('click', function() {
				$(".new_department").slideToggle();
			})
			$('.department_cancel').on('click', function() {
				$(".new_department").slideUp();
			})
		});

		function getCity(obj) {
			var hosCity = '${hospital.hosCity}';

			//取出obj对象中的value值
			//obj对应this
			//所以要先把省先查出来
			var provinceCode = obj.value;
			$.ajax({
				type : "GET",
				url : "${ctx}/bsCity.do?getType=city&provinceCode="
						+ provinceCode,
				async : false, //同步
				dataType : "json",
				success : function(data) {
					$("#hosCity").empty();
					$(data.citys).each(
							function(i, item) {
								var selectedStr = "";
								if (hosCity == item.cityCode) {
									selectedStr = "selected";
								}
								$("#hosCity").append(
										"<option value='" + item.cityCode + "' "+selectedStr+">"
												+ item.cityName + "</option>")
							});
					selectedStr = "";
					//console.log(data.citys);
					//触发city下拉框的onChange事件
					//比如上海只有一个区的时候不会自动联动和出区
					$("#hosCity").trigger("change");
				},
				error : function(e) {
					console.log(e);
				}
			});
		}

		function getArea(obj) {
			var hosArea = '${hospital.hosArea}';

			var cityCode = obj.value;
			$.ajax({
				type : "GET",
				url : "${ctx}/bsCity.do?getType=area&cityCode=" + cityCode,
				async : false,
				dataTyoe : "json",
				success : function(data) {
					$("#hosArea").empty();
					$(data.areas).each(
							function(i, item) {
								var selectedStr = "";
								if (hosArea == item.areaCode) {
									selectedStr = "selected";
								}
								$("#hosArea").append(
										"<option value='" + item.areaCode + "' "+selectedStr+">"
												+ item.areaName + "</option>")
							});
					selectedStr = "";
					//console.log(data.areas);
				},
				error : function(e) {
					console.log(e);
				}
			});
		}

		//选择科室
		function selectDept(obj) {
			var unselecta = $(obj).text();
			$(".department_seleted").append(
					"<a href=\"javascript:;\" id=\"select_" + obj.id
							+ "\" onclick=\"removeDept(this)\">" + unselecta
							+ "</a>")
			$(obj).addClass("selected")
		}

		//移除科室
		function removeDept(obj) {
			$(obj).remove();
			var oid = obj.id;
			oid = oid.replace(/select_/, "");
			$("#" + oid).removeClass("selected");
		}

		function editorCommit() {
			var deptIdStr = "";
			$(".department_seleted").children().each(function(i, item) {
				var deptId = $(this).attr("id");
				deptId = deptId.replace(/select_un_/, "");
				deptIdStr = deptIdStr + "," + deptId;
			});
			$("input[name='depts']").val(deptIdStr);
			hospitalAddFrm.submit();
		}
	</script>
</body>
</html>
