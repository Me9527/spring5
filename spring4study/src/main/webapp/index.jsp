<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
    xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.0">

  <jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />
  <jsp:output omit-xml-declaration="true" />
  <jsp:output doctype-root-element="HTML" doctype-system="about:legacy-compat" />
<html lang="en">
  <head>
    <title>Hello Security</title>
  </head>

  <body>
    <div class="container">
      <h1>This is secured!</h1>
      <p>
        Hello <b><c:out value="${pageContext.request.remoteUser}"/></b>
      </p>
      <c:url var="logoutUrl" value="/modules/user/logout"/>
      <form class="form-inline" action="${logoutUrl}" method="post">
          <input type="submit" value="注销" />
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      </form>
    </div>
    
    <SCRIPT type="text/javascript">
    	//alert("${logoutUrl}");
    	//alert("${_csrf.token}");
    </SCRIPT>    
  </body>
</html>
</jsp:root>
