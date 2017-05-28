<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>"></base>
<title>ΓΙΕΒΑ</title>
<link rel="alternate icon" type="image/png" href="assets/i/favicon.png">
<style type="text/css">
frameset {
	position: relative;
}
</style>
</head>

<frameset rows="9%,*" border="0">
	<frame src="user/getHeader" name="top" scrolling="NO" noresize>
	<frame src="tiezi/getAllTiezi" name="content_tiezi">
</frameset>
</html>