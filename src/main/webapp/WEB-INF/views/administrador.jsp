<%@ page import="com.sinara.model.Administrador" %>
<%@ page import="com.sinara.model.Empresa" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: pedrovicente-ieg
  Date: 28/10/2025
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Administrador admin = (Administrador) request.getAttribute("administrador");
%>
<html>
<head>
    <title>Editar administrador</title>
</head>
<body>
<h1>editar Administrador</h1>
<form action="<%= request.getContextPath() %>/administracao?action=alterar" method="post">
    <label for="nome">Nome:</label>
    <input type="text" name="nome" id="nome" value="<%= admin.getNome() %>">
    <br><br>
    <label for="email">Email:</label>
    <input type="email" name="email" id="email" value="<%= admin.getEmail() %>">
    <br><br>
    <label for="cargo">Cargo:</label>
    <input type="text" name="cargo" id="cargo" value="<%= admin.getCargo() %>">
    <br><br>
    <input type="hidden" value="<%= admin.getId() %>" name="id">
    <button type="submit">Editar</button>
    </div>
</form>

</body>
</html>