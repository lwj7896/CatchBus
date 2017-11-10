<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'listAllRelations.jsp' starting page</title>
    
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
  	<h1>测试</h1>
  	<table border="1" width="400" >
  	<tr>
  			<td align="center">Rid</td>
  			<td align="center">lineName</td>
  			<td align="center">siteName</td>
  			<td align="center">position</td>
  			<td align="center">transfer</td>
  			<td align="center">delete</td>
  		</tr>
  	<c:forEach items="${request.relations}" var="relations">
  		<tr>
  			<td align="center">${relations.rid}</td>
  			<td align="center">${relations.lineName}</td>
  			<td align="center">${relations.siteName}</td>
  			<td align="center">${relations.position}</td>
  			<td align="center">${relations.transfer}</td>
  			<td align="center"><a href="RelationAction!delelteRelation?rid=${relations.rid}">delete</a></td>
  		</tr>
  		</c:forEach>
  	</table>
  	<form action=""><input type="submit" value="AddRelation"></form>
  	
  </center>
  </body>
</html>
