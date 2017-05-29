<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
+ request.getServerName() + ":" + request.getServerPort()
+ path + "/";

%>
<!doctype html>
<html class="no-js fixed-layout">
<head>
  <base href="<%=basePath%>"></base>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>操作记录</title>
  <meta name="description" content="操作记录">
  <meta name="keywords" content="index">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/favicon.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="assets/css/admin.css">

  <style type="text/css">
    #recordBody > tr:nth-child(odd) td {
      background-color:  #f2f2f2;
    }
  </style>
</head>

<body>
  <!-- content start -->
  <div class="admin-content">
    <div class="admin-content-body">
      <div class="am-cf am-padding">
        <div class="am-fl am-cf">
          <strong class="am-text-primary am-text-lg">操作记录</strong> / <small>Operation Record</small>
        </div>   
      </div>
      <hr/>
      <div class="am-g" id="record-table">
        <div class="am-u-sm-12">
          <table class="am-table am-table-bd am-table-striped admin-content-table">
            <thead>
              <tr>
                <th></th><th>操作名称</th><th>操作时间</th><th>IP</th>
              </thead>
              <tbody id="recordBody">
                <c:forEach items="${records}" var="record" varStatus="status">
                <tr>
                  <td>${status.count}</td>
                  <td>${record.operateName}</td>
                  <td class="operateTime">${record.operateTime}</td>
                  <td>${record.ipAddress}</td>   
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>  
    </div>
  </div>

  <script src="assets/js/jquery.min.js"></script>
  <!--<![endif]-->
  <script src="assets/js/amazeui.min.js"></script>
  <script src="assets/js/app.js"></script>
  <script src="assets/js/vue.js"></script>
  <script type="text/javascript">
    window.onload = function() {
      $(".operateTime").each(function(index,element){
        var timeStr = $(element).text();
        $(element).text( timeStr.substring(0,19) );
      });
    }
    var recordTable = new Vue({
      ul: "#record-table",
      data: {

      }
    })
  </script>
</body>
</html>