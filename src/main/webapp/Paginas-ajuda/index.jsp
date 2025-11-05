<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Central de Ajuda - SINARA</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Estilos/ajuda.css">
        <link rel="icon" type="image/svg" href="<%=request.getContextPath()%>/icones/icone-sinara.svg">
    </head>
    <body>

        <%@include file="/padrao/header.jsp"%>

        <!-- Seção 1 -->

        <section class="primeira-secao">
            <div class="container-secao1">
                <div id="container-titulos">
                    <h1 id="titulo-principal">Central de ajuda do Sinara</h1>
                    <p id="subtitulo">Como podemos ajudar?</p>
                </div>
                <div id="barra-pesquisa">
                    <form action="#">
                        <label for="text">
                            <input type="text"
                                placeholder="Pesquisar artigos de ajuda">
                            <a href="//">
                                <button id="botao-pesquisar">Pesquisar</button>
                            </a>
                        </label>
                    </form>
                </div>
            </div>

        </section>

        <!-- Seção 2 -->

        <img src="<%=request.getContextPath()%>/divisoes/ajuda-onda.svg"
            id="divisão-ondas" alt>
        <section class="segunda-secao">
            <h2 class="titulos-secao">
                Categorias
            </h2>
            <div class="container-pai">
                <div class="container1">
                    <a href="<%=request.getContextPath()%>/Paginas-ajuda/Visao_Geral/index.jsp">
                        <div class="categorias" id="visao_geral">
                            <img
                                src="<%=request.getContextPath()%>/icones/categorias-visao.svg"
                                alt>
                            <p class="nomes-categorias">Visão geral</p>
                        </div>
                    </a>
                    <a href="<%=request.getContextPath()%>/Paginas-ajuda/Usabilidade/index.jsp">
                        <div class="categorias" id="usabilidade">
                            <img
                                src="<%=request.getContextPath()%>/icones/categorias-usabilidade.svg"
                                alt>
                            <p class="nomes-categorias">Usabilidade</p>
                        </div>
                    </a>
                </div>
                <div class="container2">
                    <a href="<%=request.getContextPath()%>/Paginas-ajuda/Seguranca/index.jsp">
                        <div class="categorias" id="seguranca">
                            <img
                                src="<%=request.getContextPath()%>/icones/categorias-seguranca.svg"
                                alt>
                            <p class="nomes-categorias">Segurança</p>
                        </div>
                    </a>
                    <a href="<%=request.getContextPath()%>/Paginas-ajuda/Politica-Uso/index.jsp">
                        <div class="categorias" id="politica_uso">
                            <img
                                src="<%=request.getContextPath()%>/icones/categorias-politica.svg"
                                alt>
                            <p class="nomes-categorias">Política de uso</p>
                        </div>
                    </a>
                </div>
            </div>
        </section>
        <section class="contatos">
            <h2 class="titulos-secao" id="centrais-ajuda">Centrais de ajuda</h2>
            <div class="container-contatos">
                <a href="https://www.instagram.com/sinara.oficial/"
                    target="_blank">
                    <div class="redes" id="instagram">
                        <p>Instagram</p>
                    </div>
                </a>
                <a href="mailto:sinaraoficial.suporte@gmail.com">
                    <div class="redes" id="email">
                        <p>Email</p>
                    </div>
                </a>
                <a href="https://wa.me/5511910036750" target="_blank">
                    <div class="redes" id="whatsapp">
                        <p>WhatsApp</p>
                    </div>
                </a>
            </div>
        </section>
        <%@include file="/padrao/footer.jsp"%>
    </body>
</html>