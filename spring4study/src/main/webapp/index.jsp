<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>
<html>
  <head>
    <title>欢迎浏览</title>
  </head>

  <body>
  <!--  
    <div sec:authorize="isAuthenticated()">
        <p>已有用户登录</p>
        <p>登录的用户为：<span sec:authentication="name"></span></p>
        <p>用户角色为：<span sec:authentication="principal.authorities"></span></p>

    </div>
   --> 
    <div class="container">
      <h1>欢迎浏览首页</h1>
      <p>

      </p>
      <c:url var="logoutUrl" value="/modules/user/logout"/>
      <form class="form-inline" action="${logoutUrl}" method="post">
          <input type="submit" value="注销" />
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      </form>
    </div>
    
    <SCRIPT type="text/javascript">
    	//alert("${logoutUrl}");
    	alert("${_csrf.token}");
    </SCRIPT>    
  </body>
</html>
