<%@ page import="com.sinara.model.Administrador" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: tarqu
  Date: 26/10/2025
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<Administrador> administradores = (List<Administrador>) request.getAttribute("administradores");
  String message = (String) session.getAttribute("message");
  List<String> erros = (List<String>) session.getAttribute("erros");
%>

<html>
<head>
  <title>Adminstradores</title>
</head>
<body>

<h1>Administrators:</h1>

<%
  if (message != null) {
    session.removeAttribute("message");
    if (message.contains("sucesso")) {
%>
<h1 style="background-color: rgb(8,252,0);" ><%= message %></h1>
<%
    }
  }
%>

<%
  if ( erros != null && !erros.isEmpty()) {
    for (String erro : erros) {
%>
<h1 style="background-color: red" ><%= erro %></h1>

<%
    }
    session.removeAttribute("erros");
  }
%>

<form action="<%= request.getContextPath()%>/administracao" method="get">
  <input type="hidden" name="action" value="adicionar">
  <button type="submit">adicionar admin</button>
</form>


<table border="1">
  <tr>
    <th>ID</th>
    <th>Email</th>
    <th>Nome</th>
    <th>Ações</th>
  </tr>
  <%
    for (Administrador admin : administradores) {

  %>
  <tr>
    <td><%= admin.getId() %></td>
    <td><%= admin.getEmail() %></td>
    <td><%= admin.getNome() %></td>
    <td>
      <form action="<%= request.getContextPath() %>/administracao" method="get">
        <input type="hidden" name="action" value="editar">
        <input type="hidden" name="id" value="<%= admin.getId() %>">
        <button type="submit">Editar</button>
      </form>

      <form action="<%= request.getContextPath() %>/administracao?action=excluir" method="post">
        <input type="hidden" name="id" value="<%= admin.getId() %>">
        <button type="submit" >Excluir</button>
      </form>
    </td>
  </tr>
  <%
    }
  %>
</table>


</body>
</html>