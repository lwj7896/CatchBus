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
    <center>
    <h1>我是主页</h1>
    <form action="PublicAction!listLineNames">显示测试：<input type="submit" value="显示"></form>
    <form action="PublicAction!findbusline" method="post">
   	 <input type="text" name="star" ><br>
   	 <input type="text" name="end" ><br>
   	 <input type="submit" value="查询">
   	</form>
   	<form action="PublicAction!twebus" method="post">
   	 <input type="text" name="star" ><br>
   	 <input type="submit" value="查询站点所在线路的中转站">
   	</form>
    </center> 
  </body>
</html>
