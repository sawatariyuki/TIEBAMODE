<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
+ request.getServerName() + ":" + request.getServerPort()
+ path + "/";
%>

<!DOCTYPE html>  
<html>  
<head>  
	<meta charset="UTF-8">  
	<base href="<%=basePath%>"></base>
	<title>Insert title here</title>  
</head>  
<body>  
   <h1>获得服务器更新</h1>  
   <div id="result"></div>  
<!--    <script type="text/javascript">  
        if(typeof(EventSource)!=="undefined"){//判断浏览器是否支持EventSource  
            var source = new EventSource("user/getMessageFrom");//创建一个新的 EventSource对象，  

            source.onmessage=function(event){//每接收到一次更新，就会发生 onmessage事件  
               document.getElementById("result").innerHTML=event.data;//把已接收的数据推入 id 为 "result" 的元素中  
            };  
        }else{  
          document.getElementById("result").innerHTML="sorry,your browser doesn't support event source";      
        }  
   </script>   -->
</body>  
</html>