<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="PT-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Usabilidade - SINARA</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Estilos/usabilidade.css">
        <link rel="icon" type="image/svg" href="<%=request.getContextPath()%>/icones/icone-sinara.svg">
    </head>
    <body>
        <!-- Header -->

        <%@include file="/padrao/header.jsp"%>

        <p id="caminhos"><a class="links-caminhos" href>Central de ajuda -
                Serviços Sinara</a> > <a class="links-caminhos"
                href>Usabilidade</a></p>
        <div id="container-superior">
            <h2 id="titulo">Usabilidade</h2>
            <div id="barra-pesquisa">
                <label for="text">
                    <img href="<%=request.getContextPath()%>/icones/lupa-pesquisa.svg"
                        alt>
                    <input type="text" placeholder="Pesquisar">
                </label>
            </div>
        </div>
        <div id="containers-artigos">
            <div id="container-artigos">
                <a href="<%=request.getContextPath()%>/Paginas-ajuda/Artigos-Usabilidade/index.jsp" class="artigo">Preciso de conhecimentos técnicos
                    avançados para usar o aplicativo?</a>
                <a href="<%=request.getContextPath()%>/Paginas-ajuda/Artigos-Usabilidade/index.jsp" class="artigo">Como funciona a criação de formulários
                    personalizados dentro do aplicativo?</a>
                <a href="<%=request.getContextPath()%>/Paginas-ajuda/Artigos-Usabilidade/index.jsp" class="artigo">Os operadores conseguem preencher os
                    formulários mesmo sem internet?</a>
                <a href="<%=request.getContextPath()%>/Paginas-ajuda/Artigos-Usabilidade/index.jsp" class="artigo">Como funciona a criação automática de
                    dashboards e gráficos?</a>
            </div>
            <div id="container2-artigos">
                <a href="<%=request.getContextPath()%>/Paginas-ajuda/Artigos-Usabilidade/index.jsp" class="artigo">Como funcionam os alarmes dentro do
                    aplicativo e quando eles são ativados?</a>
                <a href="<%=request.getContextPath()%>/Paginas-ajuda/Artigos-Usabilidade/index.jsp" class="artigo">Como os funcionários não qualificados
                    usarão o aplicativo?</a>
                <a href="<%=request.getContextPath()%>/Paginas-ajuda/Artigos-Usabilidade/index.jsp" class="artigo">Como funciona a inteligência artificial
                    dentro do sistema?</a>
            </div>
        </div>
        <%@include file="/padrao/footer.jsp"%>
    </body>
</html>