<%--
  Created by IntelliJ IDEA.
  User: lars
  Date: 18/03/2019
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'
%><c:if test="${pageContext.request.userPrincipal==null}">
        <c:redirect url="/session/index.jsp"/>
        <!-- this will redirect if user is already logged in -->
</c:if>

<html>
<body>
<head>
    <link href="/1/css/app.css" rel="stylesheet">
</head>

<div class="flex items-center justify-center w-full mt-12 p-8 bg-blue shadow">
    <h2 class="text-white">AUTHENTICATED!</h2>
</div>

<div class="flex items-center justify-center w-full mt-12" id="app">
    <logout></logout>
</div>

<script src="/1/js/session/app.js"></script>
</body>
</html>
