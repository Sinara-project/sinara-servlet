<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Análises</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/Sinarinha.svg" type="image/x-icon">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formularios.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/aside.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/geral.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/lista-superior.css">
</head>
<body>

    <!-- Barra Lateral -->

    <%@include file="/padrao/aside.jsp"%>

    <!-- Seção Principal -->

    <main>
        <%@include file="/padrao/lista-superior.jsp"%>
        <h1 id="titulo-analise">Relatórios e Formulários</h1>
        <div class="container">
            <iframe title="SinaraProjet" src="https://app.powerbi.com/view?r=eyJrIjoiM2I1ZmJhZTctN2ZkNy00NjkyLWE3ZDgtMjdlNjAwMjMyMTE1IiwidCI6ImIxNDhmMTRjLTIzOTctNDAyYy1hYjZhLTFiNDcxMTE3N2FjMCJ9" frameborder="0" allowFullScreen="true"></iframe>
        </div>
    </main>
</body>
</html>