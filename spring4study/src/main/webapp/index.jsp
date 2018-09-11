<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
String ctx = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + ctx;
request.setAttribute("ctx", ctx);
request.setAttribute("basePath", basePath);
//System.out.println(ctx);
//System.out.println(basePath);
%>

<html>
  <head>
    <title>欢迎浏览</title>
    <script LANGUAGE="JavaScript"> 
   		window.location.href="${ctx}/modules/module01/jsp/registration.jsp"; 
   	</script>   

  </head>

  <body>
    <h5> 欢迎浏览 </h5>
 
  </body>
</html>

