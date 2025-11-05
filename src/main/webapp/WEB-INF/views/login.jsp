<%@ page import="java.util.List" %>
<%@ page import="com.sinara.servlet.ServletHome" %><%--
  Created by IntelliJ IDEA.
  User: viniciusboas-ieg
  Date: 24/10/2025
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sinara - Login</title>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400" rel="stylesheet">
    <link rel="icon" type="image/svg" href="<%=request.getContextPath()%>/icones/icone-sinara.svg">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Paginas-Login/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Paginas-Login/css/input.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Paginas-Login/css/index.css">
</head>
<body>
<main>
    <header>
        <a href="<%=request.getContextPath()%>"><img id="logo" width="300vw" src="<%=request.getContextPath()%>/Paginas-Login/img/sinaralogo.svg" alt="Sinara"></a>
        <p id="titulo">Fazer Login</p>
    </header>
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
    <form action="<%=request.getContextPath()%>/home?action=login" method="post">
        <input id="email" type="email" name="email" placeholder="Digite seu Email">
        <input id="password" type="password" name="senha" placeholder="Digite sua Senha">
        <input id="login" type="submit" name="button" value="Login">
    </form>
    <b>Não possui uma conta? <a href="<%=request.getContextPath()%>/home?action=cadastro">Cadastre-se</a></b>
</main>
</body>
</html>
