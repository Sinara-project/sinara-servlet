<%--
  Created by IntelliJ IDEA.
  User: viniciusboas-ieg
  Date: 24/10/2025
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h1>Home do aplicativo | Administração</h1>
    <p>Bem vindo, <%=request.getSession().getAttribute("user")%></p>
    <form action="home?action=logout" method="post">
        <input type="submit" value="LogOut">
    </form>
</body>
</html>
