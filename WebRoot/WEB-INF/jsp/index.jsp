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
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>全部帖子</title>

	<!-- Set render engine for 360 browser -->
	<meta name="renderer" content="webkit">

	<!-- No Baidu Siteapp-->
	<meta http-equiv="Cache-Control" content="no-siteapp" />

	<link rel="icon" type="image/png" href="assets/i/favicon.png">

	<!-- Add to homescreen for Chrome on Android -->
	<meta name="mobile-web-app-capable" content="yes">
	<link rel="icon" sizes="192x192" href="assets/i/favicon.png">

	<!-- Add to homescreen for Safari on iOS -->
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-title" content="Amaze UI" />
	<link rel="apple-touch-icon-precomposed"
	href="assets/i/app-icon72x72@2x.png">

	<!-- Tile icon for Win8 (144x144 + tile color) -->
	<meta name="msapplication-TileImage"
	content="assets/i/favicon.png">
	<meta name="msapplication-TileColor" content="#0e90d2">

	<link rel="stylesheet" href="assets/css/amazeui.min.css">
	<link rel="stylesheet" href="assets/css/app.css">

	<style type="text/css">
		.my-flex {
			display: flex;
			flex-direction: row;

		}
		.my-flex > .flex-item:nth-child(1){
			flex: 8%;
			text-align: center;
			/*padding-top: 20px; */
		}
		.my-flex > .flex-item:nth-child(1) > h1{
			margin-bottom: 0;
		}
		.my-flex > .flex-item:nth-child(1) > h3{
			margin-top: 0;
			margin-bottom: 0;
		}
		.my-flex > .flex-item:nth-child(2){
			flex: 62%;
		}
		.my-flex > .flex-item:nth-child(2):before{
			background-color: #606060;  
			bottom: 0;  
			content: "";  
			display: block;  
			left: 0px;  
			position: absolute;  
			top: 0;  
			width: 1px;
		}
		.my-flex > .flex-item:nth-child(3){
			flex: 15%;	
		}
		.my-flex > .flex-item:nth-child(3):before{
			background-color: #606060;  
			bottom: 0;  
			content: "";  
			display: block;  
			left: 0px;  
			position: absolute;  
			top: 0;  
			width: 1px;
			height: 100%
		}
		.my-flex > .flex-item:nth-child(3) > p:nth-child(1){
			font-size: 15px;
			margin-bottom: 0;
			font-weight: normal;
		}
		.my-flex > .flex-item:nth-child(3) > p:nth-child(2){
			font-size: 15px;
			margin-top: 10px;
			margin-bottom: 0;
			font-weight: normal;
		}
		.my-flex > .flex-item:nth-child(4){
			flex: 15%;	
		}
		.my-flex > .flex-item:nth-child(4):before{
			background-color: #606060;  
			bottom: 0;  
			content: "";  
			display: block;  
			left: 0px;  
			position: absolute;  
			top: 0;  
			width: 1px;
			height: 100%
		}
		.my-flex > .flex-item:nth-child(4) > p:nth-child(1){
			font-size: 15px;
			margin-bottom: 0;
			font-weight: normal;
		}
		.my-flex > .flex-item:nth-child(4) > p:nth-child(2){
			font-size: 15px;
			margin-top: 10px;
			margin-bottom: 0;
			font-weight: normal;
		}
		.my-textarea {
			margin-left: 10%;
			margin-right: 10%;
			width: 80%;
		}
		.my-btn {
			margin-left: 84%
		}
	</style>

</head>
<body>
	<include file="assets/view/header.html" />
	<div style="position:fixed;right:3%;bottom:3%;z-index:100">
		<a href="javascript:void(0)" onclick="window.scrollTo(0,0)"><img src="assets/img/gototop.png" style="width:60px;border:1px solid;margin-bottom:10px"></a></br>
		<a href="javascript:void(0)" onclick="window.scrollTo(0,10000)"><img src="assets/img/gotobottom.png" style="width:60px;border:1px solid;"></a>
	</div>
	<div class="am-list-news-bd">
		<ul class="am-list">
			<!--缩略图在标题左边-->
			<c:forEach items="${tieziList}" var="tiezi">
			<li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left">
				<div class="my-flex">
					<div class="flex-item">
						<h1>${tiezi.replyNum}</h1>
						<h3>${tiezi.viewNum}</h3>
					</div>
					<div class="am-u-sm-8 am-list-main flex-item">
						<h3 class="am-list-item-hd">
							<a href="tiezi/getTieziRepliesByTieziId?tiezi_id=${tiezi.tieziId}">${tiezi.title}</a>
						</h3>
						<div class="am-list-item-text">
							${tiezi.contentText}
						</div>
					</div>
					<div class="am-u-sm-8 am-list-main flex-item">
						<p>
							发帖者：
							<a href="user/getUserDetailByUserId?USERID=${tiezi.userid}" title="${tiezi.sign}">
								${tiezi.username}
							</a>
							&nbsp;&nbsp;&nbsp;&nbsp;Lv${tiezi.userLevel}
						</p>
						<p class="tieziTime">${tiezi.postTime}</p>
					</div>
					<div class="am-u-sm-8 am-list-main flex-item">
						<p>
							最新回复：
							<a href="user/getUserDetailByUserId?USERID=${tiezi.lastReplyUserid}" title="${tiezi.lastReplySign}">
								${tiezi.lastReplyUsername}
							</a>
						</p>
						<p class="tieziTime">${tiezi.latestReplyTime}</p>
					</div>
				</div>			
			</li>
			</c:forEach>
		<li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left">
			<form id="postTieziForm" action="tiezi/addTiezi" method="post">
				<input type="hidden" name="contentImg" value="">
				<input type="hidden" name="replyDevice" value="">
				<input type="hidden" name="replyLoc" value="">
				<div class="am-form-group">
					<label for="doc-ta-1"></label>
					<textarea class="my-textarea" name="title" rows="1" placeholder="请输入标题" required style="margin-bottom:10px;resize:none" ></textarea>
					<textarea id="textArea" class="my-textarea" name="contentText" rows="5" placeholder="请输入发帖内容" required style="resize:none" onkeydown="postTiezi()"></textarea>
				</div>
				 <p><button type="submit" class="my-btn am-btn am-btn-default">发帖</button></p>
			</form>
		</li>
	</ul>
</div>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<script type="text/javascript">
	window.onload = function() {
		$(".tieziTime").each(function(index,element){
			var timeStr = $(element).text();
			$(element).text( timeStr.substring(0,19) );
		});
	}

	function postTiezi(){
		var keynum = window.event ? event.keyCode : event.which;
		if(keynum==13){
			if(event.shiftKey==1){
				// document.getElementById("textArea").innerHTML = document.getElementById("textArea").innerHTML+"\r\n";
				document.getElementById("postTieziForm").submit();
				event.returnValue=false;
			}else{
				
			}
			return false;
		}
	}
</script>
</body>
</html>