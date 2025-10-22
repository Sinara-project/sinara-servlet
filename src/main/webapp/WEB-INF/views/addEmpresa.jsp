<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: viniciusboas-ieg
  Date: 22/10/2025
  Time: 06:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% // Pegando empresa a ser editada, mesangens de sucesso, e mensagens de erro do request
  String mensagem = (String) request.getAttribute("mensagem");
  List<String> erros = (List<String>) request.getAttribute("erro");
%>
<html>
<head>
    <title>Adicionar Empresa</title>
</head>
<body>
    <% // Apresentar mensagens de erro ou sucesso
      if (erros!=null) for (String erro : erros) {
    %>
    <p style="color: red"><%=erro%></p>
    <%
        }
    %>
    <% if (mensagem!=null) {
    %>
    <p><%=mensagem%></p>
    <%
      }
    %>
    <form action="empresas?action=adicionar" method="post">
      <label for="cnpj">
        CNPJ:
        <input id="cnpj" name="cnpj" type="text" placeholder="XX.XXX.XXX/0001-XX">
      </label>
      <br>
      <label for="nome">
        Nome:
        <input id="nome" name="nome" type="text" placeholder="Nome da sua empresa">
      </label>
      <br>
      <label for="email">
        Email:
        <input id="email" name="email" type="email" placeholder="sinara@email.com">
      </label>
      <br>
      <label for="ramo">
        Ramo:
        <input id="ramo" name="ramo" type="text" placeholder="Tratamento de Água">
      </label>
      <br>
      <label for="telefone">
        Telefone:
        <input id="telefone" name="telefone" type="text" placeholder="(XX) 9XXXX-XXXX">
      </label>
      <br>
      <label for="status">
        Status:
        <input id="status" name="status" type="checkbox" checked>
      </label>
      <br>
      <label for="inicioPlano">
        Início do plano:
        <input id="inicioPlano" name="inicioPlano" type="date">
      </label>
      <br>
      <label for="plano">
        Plano:
        <select name="plano" id="plano" required>
          <option value="ANUAL" selected="selected">Anual</option>
          <option value="MENSAL">Mensal</option>
          <option value="GRÁTIS">Grátis</option>
        </select>
      </label>
      <br>
      <input type="submit" value="Enviar">
    </form>
</body>
</html>
