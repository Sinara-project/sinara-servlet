<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="PT-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Visão Geral - SINARA</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Estilos/visao-geral.css">
        <link rel="icon" type="image/svg" href="<%=request.getContextPath()%>/icones/icone-sinara.svg">
    </head>
    <body>
r
        <!-- Header -->

        <%@include file="/padrao/header.jsp"%>

        <!-- Seção principal -->

        <p id="caminhos"><a class="links-caminhos" href>Central de ajuda -
                Serviços Sinara</a> > <a class="links-caminhos" href>Visão
                Geral</a></p>
        <div id="container-superior">
            <h2 id="titulo">Visão Geral</h2>
            <div id="barra-pesquisa">
                <label for="text">
                    <img href="<%=request.getContextPath()%>/icones/lupa-pesquisa.svg"
                        alt>
                    <input type="text" placeholder="Pesquisar">
                </label>
            </div>
        </div>
        <div id="container-artigos">
            <a href="<%=request.getContextPath()%>/Paginas-ajuda/Artigos-VisaoGeral/index.jsp" class="artigo">O que é o Sinara e para quem ele foi
                desenvolvido?</a>
            <a href="<%=request.getContextPath()%>/Paginas-ajuda/Artigos-VisaoGeral/index.jsp" class="artigo"> O aplicativo será usado por quem?</a>
            <a href="<%=request.getContextPath()%>/Paginas-ajuda/Artigos-VisaoGeral/index.jsp" class="artigo">O Sinara pode ser usado para gerenciar outras
                unidades (fábricas) ou filiais da minha empresa?</a>
            <a href="<%=request.getContextPath()%>/Paginas-ajuda/Artigos-VisaoGeral/index.jsp" class="artigo">Quanto tempo leva para implementar o sistema
                em uma ETA?</a>
        </div>
        <%@include file="/padrao/footer.jsp"%>
    </body>
</html>