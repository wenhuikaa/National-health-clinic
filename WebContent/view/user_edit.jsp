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
						<span>个人信息</span>
					</h3>
					<div class="account_main_pad">
						<form  name="userAddFrm" action="${ctx}/userServlet.do?handlerType=editorCommit" method="post">
							<div class="input_group">
								<label class="input_text">用户名：</label>
								<div class="input_control">
									<input type="text" class="input_long"
										name="sysUserName" value=" ${ sessionScope.USER_NAME}" />
								</div>
							</div>
							<div class="input_group">
								<label class="input_text">真实姓名：</label>
								<div class="input_control">
									<input type="text" class="input_long"
										name="sysRealName" value=" ${ sessionScope.REAL_NAME}" />
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
 		userAddFrm.submit();
 	}
</script>
</body>
</html>



