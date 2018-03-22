<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%
String ctx = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + ctx;
request.setAttribute("ctx", ctx);
request.setAttribute("basePath", basePath);
//System.out.println(ctx);
//System.out.println(basePath);
%>

<!-- ExtJS -->
<link rel="stylesheet" type="text/css" href="${ctx}/ext4.1/resources/css/ext-all.css" />
<script type="text/javascript" src="${ctx}/ext4.1/ext-all.js"></script>
<script type="text/javascript" >
	var ctx = "${ctx}";

</script>
