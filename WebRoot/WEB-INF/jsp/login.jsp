<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head lang="en">
<base href="<%=basePath%>"></base>
<meta charset="UTF-8">
<title>Login Page</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="alternate icon" type="image/png" href="assets/i/favicon.png">
<link rel="stylesheet" href="assets/css/amazeui.min.css" />
<style>
.header {
	text-align: center;
}

.header h1 {
	font-size: 200%;
	color: #333;
	margin-top: 30px;
}

.header p {
	font-size: 14px;
}
</style>
</head>
<body>
	<div class="header">
		<div class="am-g">
			<h1>小型贴吧系统-登录</h1>
		</div>
		<hr />
	</div>
	<div class="am-g">
		<div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
			<form method="post" id="loginForm" class="am-form" action="login/isvalid">
				<label for="username">用户名:</label> <input type="text"
					name="username" id="username" value=""> <br> <label
					for="password">密码:</label> <input type="password" name="password"
					id="password" value=""> <br> <label for="remember-me">
					<input id="remember-me" type="checkbox"> 记住密码
				</label> <br />
				<div class="am-cf">
					<input type="button"  value="登 录"
						class="am-btn am-btn-primary am-btn-sm am-fl" onclick="loginSubmit()"> 
						<a href="login/registerPage">
						<input
						type="button"  value="用户注册 "
						class="am-btn am-btn-default am-btn-sm am-fr">
						</a>

				</div>
			</form>
			<hr>
		</div>
	</div>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/amazeui.min.js"></script>
	<script src="assets/js/md5.js"></script>
	<script type="text/javascript">
		function loginSubmit() {
			$("#password").val(hex_md5($("#password").val()));
			$("#loginForm").submit();
		}

	</script>
</body>
</html>
