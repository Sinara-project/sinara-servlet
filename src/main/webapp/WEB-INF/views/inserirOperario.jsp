<%@ page import="com.sinara.model.Empresa" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: pedrovicente-ieg
  Date: 23/10/2025
  Time: 07:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="labubu">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>

<%
  List<Empresa> listaEmpresas = (List<Empresa>) request.getAttribute("empresas");

%>

<body>
<h1>Adicionar operário</h1>
<form method="post" action="<%= request.getContextPath()%>/operarios?action=inserir">
  <label for="nome">Nome:</label>
  <input type="text" name="nome" id="nome" required>
  <br><br>
  <label for="email">Email:</label>
  <input type="email" name="email" id="email" required>
  <br><br>
  <label for="cargo">Cargo:</label>
  <input type="text" name="cargo" id="cargo" >
  <br><br>
  <div class="datalist-demo">
    <label for="empresa-datalist" class="small">Empresa:</label>
    <input list="empresas-list" id="empresa-datalist" name="empresa" class="search-input"
           placeholder="Digite o nome...">
    <datalist id="empresas-list">
      <%
        for (Empresa emp : listaEmpresas) {
      %>
      <option value="<%= emp.getNome() %>"></option>
    <%
      }
    %>
    </datalist>
  </div>
  <input type="time" name="horarioTrabalho">


  <button type="submit">Enviar</button>
  </form>
</body>

</html>