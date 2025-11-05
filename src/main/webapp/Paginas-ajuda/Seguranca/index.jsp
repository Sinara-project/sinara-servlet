<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="PT-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Segurança - SINARA</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Estilos/seguranca.css">
        <link rel="icon" type="image/svg" href="<%=request.getContextPath()%>/icones/icone-sinara.svg">
    </head>
    <body>
        <!-- Header -->

        <%@include file="/padrao/header.jsp"%>

        <p id="caminhos"><a class="links-caminhos" href>Central de ajuda -
                Serviços Sinara</a> > <a class="links-caminhos"
                href>Segurança</a></p>
        <div id="container-superior">
            <h2 id="titulo">Segurança</h2>
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
                <a href="<%=request.getContextPath()%>/Paginas-ajuda/Artigos-Seguranca/index.jsp" class="artigo">Qual a política do Sinara em relação à
                    privacidade e propriedade dos dados da minha indústria?</a>
                <a href="<%=request.getContextPath()%>/Paginas-ajuda/Artigos-Seguranca/index.jsp" class="artigo">Como o Sinara garante a rastreabilidade
                    (quem, quando e onde) de cada dado inserido?</a>
                <a href="<%=request.getContextPath()%>/Paginas-ajuda/Artigos-Seguranca/index.jsp" class="artigo">Os dados coletados são armazenados de
                    forma segura?</a>
                <a href="<%=request.getContextPath()%>/Paginas-ajuda/Artigos-Seguranca/index.jsp" class="artigo">É possível definir níveis de acesso
                    diferentes para operadores e administradores?</a>
            </div>
            <div id="container2-artigos">
                <a href="<%=request.getContextPath()%>/Paginas-ajuda/Artigos-Seguranca/index.jsp" class="artigo">O sistema é compatível com normas
                    ambientais ou de auditoria?</a>
            </div>
        </div>
        <%@include file="/padrao/footer.jsp"%>
    </body>
</html>