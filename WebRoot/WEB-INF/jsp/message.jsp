<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
+ request.getServerName() + ":" + request.getServerPort()
+ path + "/";

%>

<!doctype html>
<html class="no-js">
<head>
	<meta charset="utf-8">
	<base href="<%=basePath%>"></base>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="description" content="">
	<meta name="keywords" content="">
	<meta name="viewport"
	content="width=device-width, initial-scale=1">
	<title>Message</title>

	<!-- Set render engine for 360 browser -->
	<meta name="renderer" content="webkit">

	<!-- No Baidu Siteapp-->
	<meta http-equiv="Cache-Control" content="no-siteapp"/>

	<link rel="icon" type="image/png" href="assets/i/favicon.png">

	<!-- Add to homescreen for Chrome on Android -->
	<meta name="mobile-web-app-capable" content="yes">
	<link rel="icon" sizes="192x192" href="assets/i/favicon.png">

	<!-- Add to homescreen for Safari on iOS -->
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-title" content="Message"/>
	<link rel="apple-touch-icon-precomposed" href="assets/i/favicon.png">

	<!-- Tile icon for Win8 (144x144 + tile color) -->
	<meta name="msapplication-TileImage" content="assets/i/favicon.png">
	<meta name="msapplication-TileColor" content="#0e90d2">

	<link rel="stylesheet" href="assets/css/amazeui.min.css">
	<link rel="stylesheet" href="assets/css/app.css">

	<style type="text/css">
		.td-div {
			word-wrap:break-word;
			word-break:break-all;
			height: 80px;
			overflow-y: auto;
			overflow-x:hidden
		}
		.td-div-time {
			height: 10px;
			text-align: right;
		}
		table
		{
			font-size:12px;
			color:#000000;
		}
	</style>

</head>
<body>
	<div id="app">
		<div class="am-u-sm-12 am-u-md-6">
			<div class="am-g">
				<div class="am-u-sm-12">收到的信息：</div>
				<div class="am-u-sm-12">
					<table class="am-table am-table-striped am-table-hover table-main">
						<thead>
							<tr>
								<th>用户名</th>
								<th>Message</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${receiveAts}" var="message" varStatus="status">
							<tr>
								<td style="width: 15%;font-size:20px">
									<a href="user/getUserDetailByUserId?USERID=${message.src_userid}" title="${message.src_sign}">
										${message.src_username}</td>
									</a>
									<td style="width: 80%;">
										<div class="td-div">
											${message.message}
										</div>
										<div class="td-div-time">
											${message.atTime}
										</div>
									</td>
									<td style="width: 5%;line-height: 80px;font-weight:bold;color:#ff9201">
										<span v-show="${message.isread} != 1">new!</span>
									</td> 
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="am-u-sm-12 am-u-md-6">
			<div class="am-g">
				<div class="am-u-sm-12">发出的信息：</div>
				<div class="am-u-sm-12">
					<table class="am-table am-table-striped am-table-hover table-main">
						<thead>
							<tr>					
								<th>Message</th>
								<th>用户名</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${sendAts}" var="message" varStatus="status">
							<tr>	
								<td style="width: 85%;">
									<div class="td-div">
										${message.message}
									</div>
									<div class="td-div-time">
										${message.atTime}
									</div>
								</td>
								<td style="width: 15%;font-size:20px">
									<a href="user/getUserDetailByUserId?USERID=${message.dest_userid}" title="${message.dest_sign}">
										${message.dest_username}
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
</div>

<script src="assets/js/jquery.min.js"></script>	
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/vue.js"></script>
<script type="text/javascript">
	var app = new Vue({
		el: '#app'
	})
</script>

</body>
</html>