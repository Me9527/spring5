<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> 回话超时退出！</h1>
	<form class="form-inline" action="${ctx}/modules/user/logout" method="post">
          <input type="submit" value="注销" />
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</body>
</html>