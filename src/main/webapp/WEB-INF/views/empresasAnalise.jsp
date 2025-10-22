<%@ page import="java.util.List" %>
<%@ page import="com.sinara.model.Empresa" %><%--
  Created by IntelliJ IDEA.
  User: viniciusboas-ieg
  Date: 20/10/2025
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>asdasdasd</title>
</head>

<%
    String status;
    String mensagem = (String) request.getAttribute("mensagem");
    List<String> erros = (List<String>) request.getAttribute("erro");
    List<Empresa> empresas = (List<Empresa>) request.getAttribute("empresas");
%>

<body>
        <% if (erros!=null) for (String erro : erros) {
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
        <a href="empresas?action=add">
            Adicionar Empresa
        </a>
        <table border="1px">
        <tr>
            <th></th>
            <th>Nome</th>
            <th>Email</th>
            <th>Telefone</th>
            <th>Ramo</th>
            <th>Atividade</th>
            <th>Início do Plano</th>
            <th>Tipo de assinatura</th>
            <th>Editar</th>
            <th>Excluir</th>
        </tr>
        <%
            int cont = 1;
            for (Empresa empresa : empresas) {
                if (empresa.isStatus()) status = "Ativo";
                else status = "Inativo";
        %>
        <tr>
            <td><%=cont++%></td>
            <td><%=empresa.getNome()%></td>
            <td><%=empresa.getEmail()%></td>
            <td><%=empresa.getTelefone()%></td>
            <td><%=empresa.getRamo()%></td>
            <td><%=status%></td>
            <td><%=empresa.getInicioPlano()%></td>
            <td><%=empresa.getPlano()%></td>
            <td><a href="empresas?action=editar&id=<%=empresa.getId()%>" target="_blank">Editar</a></td>
            <td><a href="empresas?action=excluir&id=11">Excluir</a></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
