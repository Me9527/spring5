<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/modules/commons/jsp/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body onload='document.f.username.focus();'>
	<h3> 用户登录信息 </h3>
	<form name='f' action='${ctx}/modules/user/login' method='POST'>
		<table>
			<tr>
				<td>用户名:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td><input type='checkbox' name='remember-me' /></td>
				<td>下次自动登录</td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit" value="登录" /></td>
			</tr>
			<input name="_csrf" type="hidden"value="${_csrf.token}" />
		</table>
	</form>
	
	<script type="text/javascript">
		//alert("${_csrf.token}");
	</script>
</body>
</html>

