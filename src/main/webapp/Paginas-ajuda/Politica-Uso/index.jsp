<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Política de Uso - SINARA</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Estilos/politica-uso.css">
        <link rel="icon" type="image/svg" href="<%=request.getContextPath()%>/icones/icone-sinara.svg">
    </head>
    <body>

        <!-- Header -->

        <%@include file="/padrao/header.jsp"%>

        <!-- Seção principal -->

        <p id="caminhos"><a class="links-caminhos" href>Central de ajuda -
                Serviços Sinara</a> > <a class="links-caminhos" href>Política de
                uso</a></p>
        <div id="container-superior">
            <h2 id="titulo">Política de uso</h2>
            <div id="barra-pesquisa">
                <label for="text">
                    <img href="<%=request.getContextPath()%>/icones/lupa-pesquisa.svg"
                        alt>
                    <input type="text" placeholder="Pesquisar">
                </label>
            </div>
        </div>
        <div id="container-artigos">
            <a href="<%=request.getContextPath()%>/Paginas-ajuda/Artigos-Politica/index.jsp" class="artigo">As novas funcionalidades do aplicativo são
                cobradas à parte?</a>
            <a href="<%=request.getContextPath()%>/Paginas-ajuda/Artigos-Politica/index.jsp" class="artigo">É possível exportar os relatórios e planilhas
                para Excel, PDF ou outros formatos?</a>
            <a href="<%=request.getContextPath()%>/Paginas-ajuda/Artigos-Politica/index.jsp" class="artigo">O que acontece com os meus dados se eu
                decidir cancelar a assinatura do Sinara?</a>
            <a href="<%=request.getContextPath()%>/Paginas-ajuda/Artigos-Politica/index.jsp" class="artigo">Como funciona o modelo de preços do
                aplicativo?</a>
        </div>
        <%@include file="/padrao/footer.jsp"%>
    </body>
</html>