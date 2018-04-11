<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/modules/commons/common.jsp" %>

<html>
<head>
    <title>Registration Form</title>

    <!-- Shared -->
    <link rel="stylesheet" type="text/css" href="../shared/example.css" />
    <script type="text/javascript">
    	var url = ctx + "/modules/module02/ActionTwo/aabbcc.do?${_csrf.parameterName}=${_csrf.token}";
    </script>
    <script type="text/javascript" src="registration2.js"></script>
    
    <!-- GC -->
    <!-- Example -->
    <style type="text/css">
        /* Styling of global error indicator */
        .form-error-state {
            font-size: 11px;
            padding-left: 20px;
            height: 16px;
            line-height: 18px;
            background: no-repeat 0 0;
            cursor: default;
        }
        .form-error-state-invalid {
            color: #C30;
            background-image: url(${ctx}/ext4.1/resources/themes/images/default/form/exclamation.gif);
        }
        .form-error-state-valid {
            color: #090;
            background-image: url(${ctx}/ext4.1/resources/themes/images/default/dd/drop-yes.gif);
        }

        /* Error details tooltip */
        .errors-tip .error {
            font-style: italic;
        }
    </style>
</head>
<body>
    <script type="text/javascript">
    	//alert(url);
    </script>
    <form class="form-inline" action="${ctx}/modules/user/logout" method="post">
          <input type="submit" value="注销" />
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</body>
</html>
