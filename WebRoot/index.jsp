<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <form method="post" action="testA" >
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
  		UserName<input type="text" name="name" id="name"/><br/>
 		 Password<input type="password" name="password" id="password"/><br/>
  		<input type="submit" value="login" style="width: 94px; height: 35px">
  	<p>没有注册的学生请先注册<a href="RegistStudent.jsp">点击注册</a><br /></p>
	</form>
  </body>
</html>
