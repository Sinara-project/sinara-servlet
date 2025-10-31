<%@ page import="java.util.List" %>
<%@ page import="com.sinara.servlet.ServletHome" %><%--
  Created by IntelliJ IDEA.
  User: viniciusboas-ieg
  Date: 24/10/2025
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div>
    <%
        List<String> erros = (List<String>) request.getAttribute("erros");
        if (erros!=null)  {
            for (String erro : erros) {
    %>
    <p style="color: red"><%=erro%></p>
    <%      }
        }
        request.setAttribute("erros", null);
    %>
</div>
<form action="<%=request.getContextPath()%>/home?action=login" method="post">
    <input type="email" name="email">
    <input type="password" name="senha">
    <input type="submit" value="Mandar">
</form>
<a href="<%=request.getContextPath()%>/home?action=cadastro">Cadastrar-se</a>
</body>
</html>
