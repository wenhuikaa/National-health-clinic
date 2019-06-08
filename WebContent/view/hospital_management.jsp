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
							<span class="fl">医馆管理</span> <a href="javascript:;"
								class="add_hospital"><i class="iconfont icon-jiahao"></i>添加医馆</a>
						</h3>
						<div class="account_main_pad">
							<div class="search_table">
								<form name="searchFrm" action="${ctx }/hospitalServlet.do"
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
									<col class="hospital_col06">
								</colgroup>
								<thead>
									<tr>
										<th>医馆名称</th>
										<th>医馆地址</th>
										<th>医师总数</th>
										<th>科室总数</th>
										<th>备注</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageDomain.pageData}" var="hospital">
										<tr>
											<td>${hospital.hosName}</td>
											<td>
												<p class="hospital_adress">${hospital.hosProvince}${hospital.hosCity}${hospital.hosArea}${hospital.hosAddr}</p>
											</td>
											<td>${hospital.doctorNum }</td>
											<td>${hospital.deptNum }</td>
											<td>${hospital.memo}</td>
											<td><a href="javascript:;"
												onclick="editorQuery(${hospital.hosId})" class="blue">编辑</a>&nbsp;&nbsp;
												<a href="javascript:;" class="blue"
												onclick="deleteHos(${hospital.hosId})">删除</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="centen_page">
								<a class="disabled"
									href="${ctx}/hospitalServlet.do?currentPage=${pageDomain.currentPage-1}">上一页</a>
								<a href="${ctx}/hospitalServlet.do?currentPage=1"
									class="page_on">首页</a>
								<!-- <a href="">2</a>
										<a href="">3</a>
										<a href="">4</a>
										<a href="">5</a> -->
								<a
									href="${ctx}/hospitalServlet.do?currentPage=${pageDomain.currentPage+1}">下一页</a>
								<a
									href="${ctx}/hospitalServlet.do?currentPage=${pageDomain.totalPage}">最后一页</a>
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
	<div id="addhospitalLayer" class="hidden">
		<div class="layerDiv">
			<form name="hospitalAddFrm"
				action="${ctx}/hospitalServlet.do?handlerType=add" method="post">
				<div class="input_group clearfix">
					<label class="input_text">医馆名称：</label>
					<div class="input_control">
						<input type="text" name="hosName" class="input_long error_border" />
						<p class="error_text">错误提示</p>
					</div>
				</div>
				<div class="input_group clearfix">
					<label class="input_text">所在区域：</label>
					<div class="input_control">
						<select class="input_short" name="hosProvince" id="hosProvince"
							onchange="getCity(this)">
							<c:forEach items="${provinces }" var="province">
								<option value="${province.provinceCode }">${province.provinceName }</option>
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
				<div class="input_group clearfix">
					<label class="input_text">详细地址：</label>
					<div class="input_control">
						<textarea class="input_long" name="hosAddr" placeholder="请填写详细信息"></textarea>
					</div>
				</div>
				<div class="input_group clearfix">
					<label class="input_text">科室选择：</label>
					<div class="input_control">
						<div class="department_box">
							<div class="department_seleted">
								<!-- 标签区域 -->
							</div>
							<input type="hidden" name="depts" value="">
						</div>
						<div class="department_div">
							<c:forEach items="${depts }" var="dept">
								<a href="javascript:;" onclick="selectDept(this)"
									id="un_${dept.deptId}">${dept.deptName}</a>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="input_group clearfix">
					<label class="input_text">备注：</label>
					<div class="input_control">
						<textarea class="input_long" name="memo" placeholder="请填写备注信息"></textarea>
					</div>
				</div>
				<div class="button_div">
					<button type="button" onclick="hospitalCommit()"
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

  	//选择科室
	function selectDept(obj){
		var unselecta = $(obj).text();
		$(".department_seleted").append("<a href=\"javascript:;\" id=\"select_" + obj.id + "\" onclick=\"removeDept(this)\">" + unselecta + "</a>")
		$(obj).addClass("selected")
	}

	//移除科室
	function removeDept(obj){
		$(obj).remove();
		var oid = obj.id;
		oid = oid.replace(/select_/ , "");
		$("#"+oid).removeClass("selected");
	}

	function search() {
		searchFrm.submit();
	}
	
	function hospitalCommit(){
		//由于选择科室是添加的超链接，无法上传到服务器，所以在上方标签区域下面设置一个隐藏域
		//提交之前将所有的超链接拿到
		var deptIdStr = "";
		$(".department_seleted").children().each(function(i,item){
			//取出select下面的所有子元素（被选择的科室带有属性id="select_un_1"...)
			var deptId = $(this).attr("id");
		   //将自己拼接的id属性换成数据库中的DEPT_ID，用到正则表达式
			deptId = deptId.replace(/select_un_/ , "");
			deptIdStr = deptIdStr + "," + deptId;
		});
		console.log(deptIdStr);
		$("input[name='depts']").val(deptIdStr);
		hospitalAddFrm.submit();
		
	}

	$(document).ready(function() {
		$('.add_hospital').on('click', function() {
			var index = layer.open({
				type : 1,
				skin : 'demo-class',
				title : '添加医馆',
				area : [ '600px', '620px' ],
				shadeClose : true, //点击遮罩关闭
				content : $('#addhospitalLayer')
			});
			$('.closeLayer').on('click', function() {
				layer.close(index);
			});
		});
		$('.department_add').on('click', function() {
			$(".new_department").slideToggle();
		})
		$('.department_cancel').on('click', function() {
			$(".new_department").slideUp();
		});
		
		//触发province下拉框的onChange事件
		//因为一开始北京的时候不能联动区
		$("#hosProvince").trigger("change");
	});
	
	function getCity(obj){
		//取出obj对象中的value值
		//obj对应this
		//所以要先把省先查出来
		var provinceCode = obj.value;
		//console.log(provinceCode);
		$.ajax({
    		type : "GET",
    		url: "${ctx}/bsCity.do?getType=city&provinceCode="+provinceCode,
    		async : false, //同步
    		dataType : "json",
    		success:function(data){
    			$("#hosCity").empty();
    			$(data.citys).each(function(i,item){
    				$("#hosCity").append("<option value='" + item.cityCode + "'>" + item.cityName + "</option>")
    			});
    			//console.log(data.citys);
    			//触发city下拉框的onChange事件
    			//比如上海只有一个区的时候不会自动联动和出区
    			$("#hosCity").trigger("change");
    		},
    		error:function(e){
    			console.log(e);
    		}
    	});
	}
	
	function getArea(obj){
		var cityCode=obj.value;
		$.ajax({
			type:"GET",
			url: "${ctx}/bsCity.do?getType=area&cityCode="+cityCode,
		    async:false,
		    dataTyoe:"json",
		    success:function(data){
		    	$("#hosArea").empty();
				$(data.areas).each(function(i,item){
					$("#hosArea").append("<option value='" + item.areaCode + "'>" + item.areaName + "</option>")
				});
				//console.log(data.areas);
		    },
		    error:function(e){
		    	console.log(e);
		    }
		});
	}
	
	//删除医馆
	function deleteHos(hosId){
		if(hosId==null||hosId==''){
			return false;
		}
		var msg = "您真的确定要删除吗？\n\n请确认！";
		if (confirm(msg)==true){
			window.location.href = '${ctx}/hospitalServlet.do?handlerType=del&hosId=' + hosId;
			return true;
		}else{
			return false;
		}
	}
	
	//编辑前先查询
	function editorQuery(hosId){
		window.location.href='${ctx}/hospitalServlet.do?handlerType=editorQuery&hosId=' + hosId;
	}
</script>
</body>
</html>
