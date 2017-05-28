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
	<title>全部回帖</title>
	<meta name="description" content="这是一个 全部回帖 页面">
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
					<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">回帖</strong> / <small>Reply Posted</small></div>
				</div>

				<hr>
				<div class="am-g">
					<div class="am-u-sm-12">
						<table class="am-table am-table-striped am-table-hover table-main">
							<thead>
								<tr>
								<th class="table-id"></th>
								<th class="table-date">标题</th>
								<th class="table-type">楼层数</th>
								<th class="table-date am-hide-sm-only">内容</th>
								<th class="table-date am-hide-sm-only">回帖时间</th>
								<th class="table-set">操作</th>
								</tr>
							</thead>
							<tbody id="tableBody">
								<c:forEach items="${replyList}" var="reply" varStatus="status">
									<tr>
										<td>${status.count}</td>
										<td>
											<a href="tiezi/getTieziRepliesByTieziId?tiezi_id=${reply.tieziId}">
												${reply.title}
											</a>
										</td>
										<td>${reply.floorId}</td>
										<td class="am-hide-sm-only">${reply.contentText}</td>
										<td class="am-hide-sm-only tieziTime">${reply.replyTime}</td>
										<td>
											<div class="am-btn-toolbar">
												<div class="am-btn-group am-btn-group-xs" v-if="isEditable">
													<a href="tiezi/deleteReply?tieziId=${reply.tieziId}&floorId=${reply.floorId}">
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
			var timeStr = $(element).text();
			$(element).text( timeStr.substring(0,19) );
		});
	}
</script>
</body>
</html>
