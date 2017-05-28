<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
+ request.getServerName() + ":" + request.getServerPort()
+ path + "/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
	<meta charset="UTF-8">
	<base href="<%=basePath%>"></base>
	<title>Register Page</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="format-detection" content="telephone=no">
	<meta name="renderer" content="webkit">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<link rel="alternate icon" type="image/png" href="assets/i/favicon.png">
	<link rel="stylesheet" href="assets/css/amazeui.min.css"/>
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
		<h1>小型贴吧系统-注册</h1>
	</div>
	<hr />
	</div>
	<div class="am-g">
	<div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
		<form id="registerForm" method="post" class="am-form" action="login/register">
		<label for="username">用户名:</label>
		<input type="text" name="username" id="username" value="">
		<br>
		<label for="password">密码:</label>
		<input type="password" name="password" id="password" value="">
		<br>
		<br />
		<div class="am-cf my-am-cf">
			<input type="button" onclick="registerSubmit()" value="注 册" class="am-btn am-btn-primary am-btn-sm am-fl">
		</div>
		</form>
		<hr>
	</div>
	</div>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/md5.js"></script> 
<script type="text/javascript">
	var message; 
	if(message != null && message != ""){
		//alert(message);
	}
	
	function registerSubmit(){
		$("#password").val(hex_md5($("#password").val()));
		$("#registerForm").submit();
	}

</script>
</body>
</html>