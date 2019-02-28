<%@ page import="domain.User" %>
<%@ page import="java.util.List" %>
<html>
<body>
<h2>home!</h2>

<%
    List<User> users = (List<User>) request.getAttribute("users");
    for (User u : users) {
        out.print("<strong>" + u.getFirst_name() + "</strong><br/>");
    }
%>
</body>
</html>
