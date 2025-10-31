<%@ page import="java.util.List" %>
<%@ page import="com.sinara.model.Empresa" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.sinara.model.VisaoGeral" %>
<%--
  Created by IntelliJ IDEA.
  User: viniciusboas-ieg
  Date: 20/10/2025
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Visão Geral</title>
</head>

<%
  String status;
  String mensagem = (String) request.getAttribute("mensagem");
  List<String> erros = (List<String>) request.getAttribute("erro");
  List<VisaoGeral> visoes = (List<VisaoGeral>) request.getAttribute("empresas");
%>

<body>
<div id="mensagens">
  <% if (erros!=null) for (String erro : erros) {
  %>
  <p style="color: red">* <%=erro%></p>
  <%
      }
  %>
  <% if (mensagem!=null) {
  %>
  <p><%=mensagem%></p>
  <%
    }
    request.removeAttribute("mensagem");
    request.removeAttribute("erro");
    Empresa.nulo = false;
  %>
</div>
<table border="1px">
  <tr>
    <th></th>
    <th>Nome</th>
    <th>Quantidade de administradores</th>
    <th>Quantidade de operários</th>
    <th>Quantidade de Alertas</th>
    <th>Quantidade de relatórios</th>
  </tr>
  <%
    int cont = 1;
    for (VisaoGeral visao : visoes) {
  %>
  <tr>
    <td><%=cont++%></td>
    <td><%=visao.getNome()%></td>
    <td><%=visao.getQuant_administradores()%></td>
    <td><%=visao.getQuant_operarios()%></td>
    <td><%=visao.getQuant_alertas()%></td>
    <td><%=visao.getQuant_relatorios()%></td>
  </tr>
  <%
      }
  %>
</table>
</body>
</html>
