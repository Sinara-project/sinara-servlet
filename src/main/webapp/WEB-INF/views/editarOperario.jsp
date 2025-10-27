<%@ page import="com.sinara.model.Operario" %><%--
  Created by IntelliJ IDEA.
  User: pedrovicente-ieg
  Date: 21/10/2025
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar operário - Sinara</title>
</head>
<%
    String message = (String) session.getAttribute("message");
    if (message != null) {
        session.removeAttribute("message");
    }

%>

<%
    String nomeEmpresa = (String) request.getAttribute("nomeEmpresa");
    Operario operario = (Operario) request.getAttribute("operario");
%>


<body>
<H1>Editar operário: operário </H1>
<%
    if (message != null) {
        String classe = message.toLowerCase().contains("sucesso") ? "sucess" : "error";
%>
<div class="<%= classe %>"><%= message %></div>
<%
    }
%>
<br><br>



<form action="<%= request.getContextPath() %>/operarios?action=atualizar&id=<%= operario.getId() %>" method="post">
<label for="id">ID:</label>
<input type="number" name="ide" id="id" disabled value="<%= operario.getId() %>">
<br><br>
<label for="email">Email:</label>
<input type="email" name="email" id="email" placeholder="<%= operario.getEmail() %>">
<br><br>
<label for="nome">Nome:</label>
<input type="text" name="nome" id="nome" placeholder="<%= operario.getNome() %>">
<br><br>
<label for="cargo">Cargo:</label>
<input type="text" name="cargo" id="cargo" placeholder="<%= operario.getCargo() %>">
<br><br>
<label for="empresa">Empresa:</label>
<input type="text" name="empresa" id="empresa" disabled value="<%=nomeEmpresa%>">
<button type="submit">Enviar</button>
</form>
</body>

</html>