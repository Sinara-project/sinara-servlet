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

        <div id="container-relatorios">
            <div class="relatorios-dashboards">
                <div class="bloco-formulario">
                    <h2 class="titulo-formulario">Formulário 24/09</h2>
                    <p class="descricao-formulario">Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsum a dolore laboriosam! Autem, nobis sequi quis nihil commodi incidunt impedit qui at cupiditate facere quod quam omnis, accusamus animi soluta!</p>
                </div>
                <img src="<%=request.getContextPath()%>/img/grafico-laranja.svg" alt="">
            </div>
            <div class="relatorios-dashboards">
                <div class="bloco-formulario">
                    <h2 class="titulo-formulario">Formulário 25/09</h2>
                    <p class="descricao-formulario">Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsum a dolore laboriosam! Autem, nobis sequi quis nihil commodi incidunt impedit qui at cupiditate facere quod quam omnis, accusamus animi soluta!</p>
                </div>
                <img src="<%=request.getContextPath()%>/img/grafico-azul.svg" alt="">
            </div>
        </div>
    </main>
</body>
</html>