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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sinara - Cadastro</title>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400" rel="stylesheet">
    <link rel="icon" type="image/svg" href="<%=request.getContextPath()%>/icones/icone-sinara.svg">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Paginas-Login/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Paginas-Login/css/input.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Paginas-Login/css/cadastro.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/geral.css">
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
<div class="cadastro">
    <header>
        <a href="<%=request.getContextPath()%>/index.jsp"><img width="300vw" src="<%=request.getContextPath()%>/img/sinara.svg" alt="Sinara"></a>
        <p id="titulo">Crie sua conta</p>
    </header>
    <form action="<%=request.getContextPath()%>/home?action=cadastro" method="post">
        <input id="user" type="text" name="nome" placeholder="Digite seu nome completo">
        <input id="email" type="email" name="senha" placeholder="Digite seu email">
        <input id="cpf" type="text" name="cpf" placeholder="Digite seu CPF">
        <input id="cargo" type="text" name="cargo" placeholder="Digite seu cargo na empresa">
        <input id="password" type="password" name="password" placeholder="Digite sua Senha">
        <select class="botao" name="cnpjEmpresa" id="cnpjEmpresa">
            <%
            for (Empresa empresa : empresas) {
                if (empresa.getId()!=11) {
            %>
                    <option value="<%=empresa.getId()%>"><%=empresa.getNome()%></option>
            <%
                }
            }
            %>
        </select>
        <input id="login" type="submit" name="cadastro" value="Criar conta">
    </form>
    <b>Já possui uma conta? <a href="home?action=login" target="_blank">Faça Login</a></b>
</div>
</body>
</html>
