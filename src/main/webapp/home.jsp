<%@page contentType="text/html" pageEncoding="UTF-8"
%><%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'
%><c:if test="${pageContext.request.userPrincipal!=null}">
        <c:redirect url="/secured.jsp"/>
        <!-- this will redirect if user is already logged in -->
</c:if>

<html>
<body>
<h2>home!</h2>
<div id="app">
    <authentication></authentication>
</div>

<script src="./js/app.js"></script>
</body>
</html>
