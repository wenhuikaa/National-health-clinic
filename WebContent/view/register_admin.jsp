<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath()%>"></c:set>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>登录</title>
<link type="text/css" rel="stylesheet"
	href="${ctx}/static/css/login.css" />

</head>

<body>
	<div class="login_admincontain">
		<div class="over_bg"></div>
		<div class="login_adminbox" style="width: 360px; height: 500px">
			<div class="login_title">
				<h2>
					<a href="index.html"><img src="${ctx}/static/images/logo.png"></a>
				</h2>
			</div>
			<div class="login_area">
				<form name="registerFrm" method="post"
					action="${ctx}/userServlet.do?handlerType=register">
					<label class="minput"> <input type="text" name="userName"
						placeholder="用户名" value="" /> <i class="login_icon user_icon"></i>
					</label> <label class="minput"> <input type="text" name="realName"
						placeholder="真实姓名" value="" /> <i class="login_icon user_icon"></i>
					</label> <label class="minput"> <input type="password"
						name="password" placeholder="密码" /> <i
						class="login_icon password_icon"></i>
					</label>
					<div style="text-align: center;">
						<input type="radio" name="userType"
							value="管理员" />管理员 <input type="radio" name="userType"
							value="非管理员" />非管理员
					</div>
					<div class="btn_div">
						<a href="javascript:void(0)" onclick="register()"
							class="btn_block">注册</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function register() {
		registerFrm.submit();
	}
</script>

</html>