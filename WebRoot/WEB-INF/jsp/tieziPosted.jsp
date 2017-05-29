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
	<title>全部发帖</title>
	<meta name="description" content="这是一个 全部发帖 页面">
	<meta name="keywords" content="table">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="renderer" content="webkit">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<link rel="icon" type="image/png" href="assets/i/favicon.png">
	<link rel="apple-touch-icon-precomposed" href="assets/i/favicon.png">
	<meta name="apple-mobile-web-app-title" content="Amaze UI" />
	<link rel="stylesheet" href="assets/css/amazeui.min.css"/>
	<link rel="stylesheet" href="assets/css/admin.css">
</head>
<body>
	<div class="am-cf admin-main" style="padding-top:0px;">
		<!-- content start -->
		<div class="admin-content">
			<div class="admin-content-body">
				<div class="am-cf am-padding am-padding-bottom-0">
					<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">发帖</strong> / <small>Tiezi Posted</small></div>
				</div>

				<hr>
				<div class="am-g">
					<div class="am-u-sm-12">
						<table class="am-table am-table-striped am-table-hover table-main">
							<thead>
								<tr>
								<th class="table-id"></th>
								<th class="table-date">标题</th>
								<th class="table-type">回复数</th>
								<th class="table-author am-hide-sm-only">浏览数</th>
								<th class="table-date am-hide-sm-only">发帖时间</th>
								<th class="table-type am-hide-sm-only">最新回帖者</th>
								<th class="table-date am-hide-sm-only">最新回帖时间</th>
								<th class="table-set">操作</th>
								</tr>
							</thead>
							<tbody id="tableBody">
								<c:forEach items="${tieziList}" var="tiezi" varStatus="status">
									<tr>
										<td>${status.count}</td>
										<td>
											<a href="tiezi/getTieziRepliesByTieziId?tiezi_id=${tiezi.tieziId}">
												${tiezi.title}
											</a>
										</td>
										<td>${tiezi.replyNum}</td>
										<td class="am-hide-sm-only">${tiezi.viewNum}</td>
										<td class="am-hide-sm-only tieziTime">${tiezi.postTime}</td>
										<td class="am-hide-sm-only">
										<a href="user/getUserDetailByUserId?USERID=${tiezi.lastReplyUserid}" title="${tiezi.lastReplySign}">
											${tiezi.lastReplyUsername}
										</a>
										</td>
										<td class="am-hide-sm-only tieziTime">${tiezi.latestReplyTime}</td>
										<td>
											<div class="am-btn-toolbar">
												<div class="am-btn-group am-btn-group-xs" v-if="isEditable">
													<a href="tiezi/deleteTiezi?tieziId=${tiezi.tieziId}">
														<span class="am-icon-trash-o"></span> 删除
													</a>
												</div>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<hr/>
					</div>
				</div>
			</div>
		</div>
		<!-- content end -->
	</div>
	<footer>
		<hr>
	</footer>
<script src="assets/js/jquery.min.js"></script>
<!--<![endif]-->
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/app.js"></script>
<script src="assets/js/vue.js"></script>
<script type="text/javascript">
	var tableBody = new Vue({
		el: "#tableBody",
		data: {
			isEditable: ${isEditable}
		}
	})

	window.onload = function() {
		$(".tieziTime").each(function(index,element){
			var timeStr = $(element).text().trim();
			$(element).text( timeStr.substring(0,19) );
		});
	}
</script>
</body>
</html>
