<%@ page import="com.sinara.model.Empresa" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: viniciusboas-ieg
  Date: 20/10/2025
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! // Método para verificar qual plano o usuário se encontra atualmente e selecionar de acordo no HTML
    public String selecionado(String s, Empresa emp) {
        if (s.equals(emp.getPlano())) return "selected=\"selected\"";
        else return "";
    }
%>
<% // Pegando empresa a ser editada, mesangens de sucesso, e mensagens de erro do request
    Empresa empresa = (Empresa) request.getAttribute("empresa");
    String mensagem = (String) request.getAttribute("mensagem");
    List<String> erros = (List<String>) request.getAttribute("erro");

    // Declarar checked no HTML caso status da empresa seja ativo
    String status;
    if (empresa.isStatus()) status = "checked";

    else status = "";
%>
<html>
<head>
    <title>Edição - <%=empresa.getNome()%></title>
</head>
<body>
    <div id="mensagens">
        <% // Apresentar mensagens de erro ou sucesso
            if (erros!=null) for (String erro : erros) {
        %>
        <p style="color: red">* <%=erro%></p>
        <%
                }
        %>
        <%
            if (mensagem!=null) {
        %>
        <p><%=mensagem%></p>
        <%
            }
            request.removeAttribute("mensagem");
            request.removeAttribute("erro");
            Empresa.nulo = false;
        %>
    </div>
    <form action="empresas?action=atualizar&id=<%=empresa.getId()%>" method="post">
        <label for="id">
            ID:
            <input id="id" name="id" type="text" disabled value="<%=empresa.getId()%>">
        </label>
        <br>
        <label for="nome">
            Nome:
            <input id="nome" name="nome" type="text" value="<%=empresa.getNome()%>">
        </label>
        <br>
        <label for="email">
            Email:
            <input id="email" name="email" type="email" value="<%=empresa.getEmail()%>">
        </label>
        <br>
        <label for="ramo">
            Ramo:
            <input id="ramo" name="ramo" type="text" value="<%=empresa.getRamo()%>">
        </label>
        <br>
        <label for="telefone">
            Telefone:
            <input id="telefone" name="telefone" type="text" value="<%=empresa.getTelefone()%>">
        </label>
        <br>
        <label for="status">
            Status:
            <input id="status" name="status" type="checkbox" <%=status%>>
        </label>
        <br>
        <input type="submit" value="Enviar">
    </form>
</body>
</html>
