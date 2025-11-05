<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sinara - Área Restrita</title>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400" rel="stylesheet">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/Sinarinha.svg" type="image/x-icon">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/input.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/geral.css">
</head>
<body>
    <main>
        <header>
            <a href="<%=request.getContextPath()%>/index.jsp"><img id="logo" width="300vw" src="<%=request.getContextPath()%>/img/sinara.svg" alt="Sinara"></a>
            <p id="titulo">Área Restrita</p>
        </header>
        <form action="arearestrita" method="post">
            <input id="email" type="email" name="email" placeholder="Digite seu Email">
            <input id="password" type="password" name="senha" placeholder="Digite sua Senha">
            <input type="submit" class="botao" name="button" value="Login">
            <!-- Se incorreta a senha: -->
            <%
                List<String> erros = (List<String>) request.getSession().getAttribute("erro");
                if (erros!=null)  {
                for (String erro : erros) {
            %>
               <p style="color: red"><%=erro%></p>
            <%      }
                }
                request.getSession().setAttribute("erros", null);
            %>
        </form>
    </main>
</body>
</html>
