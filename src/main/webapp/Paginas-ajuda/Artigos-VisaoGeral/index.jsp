<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Central de Ajuda - SINARA</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Estilos/artigos.css">
        <link rel="icon" type="image/svg" href="<%=request.getContextPath()%>/icones/icone-sinara.svg">
    </head>
    <body>

        <!-- Header -->

        <%@include file="/padrao/header.jsp"%>

        <!-- Seção Principal -->

        <div id="container-superior">
            <p id="caminhos"><a class="links-caminhos" href>Central de ajuda -
                    Serviços Sinara</a> > <a class="links-caminhos"
                    href>Visão Geral</a></p>
            <div id="barra-pesquisa">
                <label for="text">
                    <img href="<%=request.getContextPath()%>/icones/lupa-pesquisa.svg"
                        alt>
                    <input type="text" placeholder="Pesquisar">
                </label>
            </div>
        </div>
        <p id="titulo-da-secao">Artigos - Visão Geral</p>
        <div class="container">
            <aside class="menu">
                <button class="item-menu ativo" id-artigo="artigo1">O que é o
                    Sinara e para quem foi desenvolvido?</button>
                <button class="item-menu" id-artigo="artigo2">O aplicativo será
                    usado por quem?</button>
                <button class="item-menu" id-artigo="artigo3">O Sinara pode ser
                    usado para gerenciar outras unidades (fábricas) ou filiais
                    da minha empresa?</button>
                <button class="item-menu" id-artigo="artigo4">Quanto tempo leva
                    para implementar o sistema em uma ETA?</button>
            </aside>

            <main class="conteudo">
                <section id="artigo1" class="artigo ativo">
                    <h2 class="perguntas">O que é o Sinara e para quem foi
                        desenvolvido?</h2>
                    <p class="respostas">O Sinara é uma plataforma digital (Web
                        e Mobile) de gestão de dados e otimização de processos,
                        voltada para indústrias e empresas com Estações de
                        Tratamento de Água (ETA) que buscam modernizar a coleta
                        de dados e promover a industrialização sustentável e a
                        inovação.</p>
                </section>

                <section id="artigo2" class="artigo">
                    <h2 class="perguntas">O aplicativo será usado por quem?</h2>
                    <p class="respostas">O Sinara foi projetado para atender
                        dois perfis principais na indústria. Os Operadores e
                        Técnicos de Campo utilizam o aplicativo móvel,
                        ferramenta essencial para a coleta de dados precisa e em
                        tempo real, facilitando as rotinas no local de trabalho.
                        Já os Administradores, Gerentes e Supervisores utilizam
                        a Plataforma Web, que serve como o centro de comando
                        para configurar todo o sistema, realizar a análise
                        aprofundada dos dados e gerenciar os alarmes de forma
                        eficiente.</p>
                </section>

                <section id="artigo3" class="artigo">
                    <h2 class="perguntas">O Sinara pode ser usado para gerenciar
                        outras unidades ou filiais da minha empresa?</h2>
                    <p class="respostas">Sim, é o ideal. Um sistema único
                        facilita a organização de dados, a comparação e o
                        aprendizado para melhorar a eficiência. A cobrança é
                        feita por indústria.</p>
                </section>

                <section id="artigo4" class="artigo">
                    <h2 class="perguntas">Quanto tempo leva para implementar o
                        sistema em uma ETA?</h2>
                    <p class="respostas">O tempo de implementação é extremamente
                        curto, pois é uma plataforma pensada em diminuir a perda
                        de desempenho, ela possui período de adequação e
                        implementação de 1 a 3 dias se for feita de forma
                        eficiente, podendo passar a ser usada menos de 24 após a
                        sua compra.</p>
                </section>
            </main>
        </div>

        <!-- Footer -->

        <%@include file="/padrao/footer.jsp"%>

        <script href="<%=request.getContextPath()%>/Scripts/script-artigos.js"></script>
    </body>
</html>