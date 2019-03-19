<%--
  Created by IntelliJ IDEA.
  User: lars
  Date: 18/03/2019
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

%><%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'
%><c:if test="${pageContext.request.userPrincipal==null}">
        <c:redirect url="/home.jsp"/>
        <!-- this will redirect if user is already logged in -->
</c:if>

<html>
<head>
    <title>Secured</title>
</head>
<body>
<p>
    Secured!
</p>

<logout></logout>
</body>
</html>
