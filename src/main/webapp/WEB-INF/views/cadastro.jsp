<%@ page import="com.sinara.dao.EmpresaDAO" %>
<%@ page import="com.sinara.model.Empresa" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: viniciusboas-ieg
  Date: 24/10/2025
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Empresa> empresas = (List<Empresa>) request.getAttribute("empresas");
%>
<html>
<head>
    <title>Cadastro</title>
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
    <form action="<%=request.getContextPath()%>/home?action=cadastro" method="post">
        <label for="nome">
            Nome:
            <input type="text" name="nome" id="nome">
        </label>
        <label for="email">
            Email:
            <input type="email" name="email" id="email">
        </label>
        <label for="senha">
            Senha:
            <input type="password" name="senha" id="senha">
        </label>
        <label for="cpf">
            CPF:
            <input type="text" name="cpf" id="cpf">
        </label>
        <label for="cargo">
            Cargo:
            <input type="text" name="cargo" id="cargo">
        </label>
        <label for="cnpjEmpresa">
            Empresa:
            <select name="cnpjEmpresa" id="cnpjEmpresa">
                <%for (Empresa empresa : empresas) {%>
                <option value="<%=empresa.getId()%>"><%=empresa.getNome()%></option>
                <%
                }
                %>
            </select>
        </label>
        <input type="submit">
    </form>
</body>
</html>
