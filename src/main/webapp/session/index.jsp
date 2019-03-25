<%@page contentType="text/html" pageEncoding="UTF-8"
%><%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'
%><c:if test="${pageContext.request.userPrincipal!=null}">
        <c:redirect url="/session/secured.jsp"/>
        <!-- this will redirect if user is already logged in -->
</c:if>

<html>
<body>
<head>
    <link href="/1/css/app.css" rel="stylesheet">
</head>

<div class="flex items-center justify-center w-full mt-12 p-8 bg-blue shadow">
    <h2 class="text-white">AUTHENTICATION</h2>
</div>

<div id="app">
    <authentication></authentication>
</div>

<script src="/1/js/session/app.js?version=1"></script>
</body>
</html>
