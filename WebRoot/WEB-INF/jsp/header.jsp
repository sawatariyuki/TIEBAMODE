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
	<title>Tieba</title>

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
	<meta name="apple-mobile-web-app-title" content="Amaze UI"/>
	<link rel="apple-touch-icon-precomposed" href="assets/i/favicon.png">

	<!-- Tile icon for Win8 (144x144 + tile color) -->
	<meta name="msapplication-TileImage" content="assets/i/favicon.png">
	<meta name="msapplication-TileColor" content="#0e90d2">

	<link rel="stylesheet" href="assets/css/amazeui.min.css">
	<link rel="stylesheet" href="assets/css/app.css">
	<link rel="stylesheet" href="assets/css/bootstrap.css"/>
</head>


<header id="msgsys" class="am-topbar am-topbar-inverse admin-header">
	
	<a href="tiezi/getAllTiezi" target="content_tiezi">
		<div class="am-topbar-brand">
			<strong>ΓΙΕΖΙ</strong> <small>小型贴吧系统</small>
		</div>
	</a>

	<a target="content_tiezi" data-toggle="modal" data-target="#myModal">
		<div class="am-topbar-brand">
			<small>Online: {{userNum}}</small>
		</div>
	</a>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">Modal title</h4>
				</div>
				<div class="modal-body">
					...
				</div>
			</div>
		</div>
	</div>


	<a href="tiezi/getAllTiezi" target="content_tiezi" v-if="message!=''">
		<div class="am-topbar-brand" style="margin-left: 15px; color:#ff9201">
			<small>{{message}}</small>
		</div>	
	</a>

	<div class="am-collapse am-topbar-collapse" id="topbar-collapse">
		<ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
			<li class="am-dropdown" data-am-dropdown>
				<a href="user/getUserDetailByUserId?USERID=${user.userid}" target="content_tiezi">
					${user.username}
				</a>
			</li>
			<li>
				<a href="user/getAllAt" target="content_tiezi">
					<span class="am-icon-envelope-o"></span>
					<sup style="font-weight:bold;color:#ff9201">{{atMsg}}</sup>
				</a>
			</li>
			<li><a href="user/logout" target="_parent"> 退出登录 </a></li>
		</ul>
	</div>
</header>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/vue.js"></script>
<script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
<script type="text/javascript">
	var MsssageSys = new Vue({
		el: "#msgsys",
		data: {
			message: "",
			atMsg: "",
			userNum: "",
		},
		mounted: function() {
			var vm = this
			if(typeof(EventSource)!=="undefined"){
				var source = new EventSource("user/getMessageFrom");
				source.onmessage = function(event){
					/***
					 *	"num|new@|1"	"num||1"	"|new@|1"	"||" 
					 **/
					 var data = event.data.split("|");
					 vm.message = data[0];
					 vm.atMsg = data[1];
					 vm.userNum = data[2];
					};
				}else{
					vm.message = "***";
					vm.atMsg = "***";
					vm.userNum = "***";
				}
			}
		});
	</script>  
	<html class="no-js">