<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
	<form action="tiezi/getAllTiezi">
    	测试获得所有帖子 按最新回复排序</br>
    	http://localhost:8080/TIEBAMODE/tiezi/getAllTiezi
    	<input type="submit">
    </form>
	</br>
	
    <form action="tiezi/getTieziRepliesByTieziId" method="get">
    	根据帖子ID测试获得一个帖子的所有回帖（GET请求） 按楼层从小排序</br>
    	http://localhost:8080/TIEBAMODE/tiezi/getTieziRepliesByTieziId</br>
    	tiezi_id:<input	name="tiezi_id" value="17">
    	<input type="submit">
    </form>
    </br>
    
    <form action="tiezi/addReplyToTiezi" method="post">
    	向数据库中插入一个回帖</br>
    	http://localhost:8080/TIEBAMODE/tiezi/addReplyToTiezi</br>
    	tiezi_id:<input	name="tiezi_id" value="17"></br>
    	contentText:<input name="contentText" ></br>
    	contentImg:<input name="contentImg" ></br>
    	replyDevice:<input name="replyDevice" ></br>
    	replyLoc:<input name="replyLoc" >
    	<input type="submit">
    </form>
    </br>
    
    <form action="tiezi/addTiezi" method="post">
    	发帖</br>
    	http://localhost:8080/TIEBAMODE/tiezi/addTiezi</br>
    	title:<input name="title"></br>
    	contentText:<input name="contentText" ></br>
    	contentImg:<input name="contentImg" ></br>
    	replyDevice:<input name="replyDevice" ></br>
    	replyLoc:<input name="replyLoc" >
    	<input type="submit">
    </form></br>
    
    <form action="tiezi/getAllTieziByUserId" method="get">
    	根据用户ID获得其所有发帖（GET请求）结果按时间最新排序</br>
    	http://localhost:8080/TIEBAMODE/tiezi/getAllTieziByUserId</br>
    	userId:<input name="userId" value="4">
    	<input type="submit">
    </form></br>
    
    <form action="tiezi/getTieziReplyByUserId" method="get">
    	根据用户ID获得他的全部回帖（包括对应帖子的ID、title） 按时间最新排序</br>
    	http://localhost:8080/TIEBAMODE/tiezi/getTieziReplyByUserId</br>
    	userId:<input name="userId" value="4">
    	<input type="submit">
    </form></br>
    
    <form action="tiezi/deleteTiezi" method="get">
    	根据帖子ID删除该贴（还需用户ID）（GET请求）</br>
    	http://localhost:8080/TIEBAMODE/tiezi/deleteTiezi</br>
    	tieziId:<input name="tieziId" value="">
    	<input type="submit">
    </form></br>
    
   	<form action="tiezi/deleteReply" method="get">
    	根据帖子ID、楼层ID删除该回帖（还需用户ID）（GET请求）</br>
    	http://localhost:8080/TIEBAMODE/tiezi/deleteReply</br>
    	tieziId:<input name="tieziId" value="">
    	floorId:<input name="floorId" value="">
    	<input type="submit">
    </form></br>
    ----------------------------------------------------------------------</br>
    <form action="user/getUserOperateRecord" method="get">
    	根据用户ID获得其最近20条操作记录</br>
    	http://localhost:8080/TIEBAMODE/user/getUserOperateRecord</br>
    	<input type="submit">
    </form></br>
    
    <form action="user/getUserDetailByUserId" method="get">
    	根据用户ID获得其详细信息（USERID为GET请求）</br>
    	http://localhost:8080/TIEBAMODE/user/getUserDetailByUserId</br>
    	USERID:<input name="USERID" value="29">
    	<input type="submit">
    </form></br>
    
    <form action="user/updateUserDetail" method="post">
    	根据用户ID修改其详细信息（POST请求）</br>
    	http://localhost:8080/TIEBAMODE/user/updateUserDetail</br>
    	注：gender必须为“男”、“女”、“未定”中的一个选择</br>
    	注：birthday必须为 yyyy-mm-dd hh:mm:ss[.f...] 这样的格式，中括号表示可选，否则报错，如1999-05-09 11:49:45</br>
    	更新完后重新请求下getUserDetailByUserId（获得详细信息）来更新年龄字段</br>
    	email:<input name="email"> icon:<input name="icon"> gender:<input name="gender"></br>
		birthday:<input name="birthday"> phoneNum:<input name="phoneNum"> sign:<input name="sign"></br>
		intro:<input name="intro"> bloodType:<input name="bloodType"> occupation:<input name="occupation"></br>
		company:<input name="company"> birthLand:<input name="birthLand"> liveLand:<input name="liveLand"></br>
    	<input type="submit">
    </form></br> 
    
    <form action="user/atSomeone" method="post">
    	@某个用户</br>
    	被@的用户会有“new!”提示</br>
    	http://localhost:8080/TIEBAMODE/user/atSomeone</br>
    	targetUser:<input name="targetUser" value="">
    	msg:<input name="msg" value="">
    	<input type="submit">
    </form></br>
    
     <form action="user/getAt" method="get">
    	查看用户发出的@</br>
    	http://localhost:8080/TIEBAMODE/user/getAt?flag=send</br>
    	flag:<input name="flag" value="send">
    	<input type="submit">
    </form></br>
    
     <form action="user/getAt" method="get">
    	查看用户收到的@</br>
    	并去除该用户的“new@”提示</br>
    	http://localhost:8080/TIEBAMODE/user/getAt?flag=receive</br>
    	flag:<input name="flag" value="receive">
    	<input type="submit">
    </form></br>
    
  </body>
</html>
