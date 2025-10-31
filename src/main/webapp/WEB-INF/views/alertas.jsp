<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alertas</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/Sinarinha.svg" type="image/x-icon">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/alertas.css">
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
        
        <h1 id="titulo-alertas">Alertas Emitidos</h1>

        <div id="container-alertas">
            <div id="informacoes-alertas"></div>
            <div id="controles">
              <button class="setas" onclick="anterior()">&#60;</button>
              <button class="setas" onclick="proximo()">&#62;</button>
            </div>
        </div>
    </main>

    
    <script src="/JavaScript/alertas.js"></script>
</body>
</html>