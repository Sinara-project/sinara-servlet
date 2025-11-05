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
                    href>Usabilidade</a></p>
            <div id="barra-pesquisa">
                <label for="text">
                    <img href="<%=request.getContextPath()%>/icones/lupa-pesquisa.svg"
                        alt>
                    <input type="text" placeholder="Pesquisar">
                </label>
            </div>
        </div>
        <p id="titulo-da-secao">Artigos - Usabilidade</p>
        <div class="container">
            <aside class="menu">
                <button class="item-menu ativo" id-artigo="artigo1">Preciso de
                    conhecimentos técnicos avançados para usar o
                    aplicativo?</button>
                <button class="item-menu" id-artigo="artigo2">Como funciona a
                    criação de formulários personalizados dentro do
                    aplicativo?</button>
                <button class="item-menu" id-artigo="artigo3">Os operadores
                    conseguem preencher os formulários mesmo sem
                    internet?</button>
                <button class="item-menu" id-artigo="artigo4">Como funciona a
                    criação automática de dashboards e gráficos?</button>
                <button class="item-menu" id-artigo="artigo5">Como funcionam os
                    alarmes dentro do aplicativo, e quando eles são
                    ativados?</button>
                <button class="item-menu" id-artigo="artigo6">Como os
                    funcionários não qualificados usarão o aplicativo?</button>
                <button class="item-menu" id-artigo="artigo7">Como funciona a
                    inteligência artificial dentro do sistema?</button>
            </aside>

            <main class="conteudo">
                <section id="artigo1" class="artigo ativo">
                    <h2 class="perguntas">Preciso de conhecimentos técnicos
                        avançados para usar o aplicativo?</h2>
                    <p class="respostas">Não. O aplicativo mobile para
                        operadores tem uma interface intuitiva e simplificada. O
                        painel web para administradores também tem uma curva de
                        aprendizado rápida e é intuitivo.</p>
                </section>

                <section id="artigo2" class="artigo">
                    <h2 class="perguntas">Como funciona a criação de formulários
                        personalizados dentro do aplicativo?</h2>
                    <p class="respostas">É feita na Plataforma Web por
                        administradores, usando um editor simples e prático que
                        permite criar campos, definir tipos de dados e regras de
                        validação sem necessidade de código. Os formulários são
                        sincronizados automaticamente com os dispositivos
                        móveis.</p>
                </section>

                <section id="artigo3" class="artigo">
                    <h2 class="perguntas">Os operadores conseguem preencher os
                        formulários mesmo sem internet?</h2>
                    <p class="respostas">Sim (Modo Offline). O aplicativo mobile
                        armazena os dados localmente e sincroniza
                        automaticamente com o servidor na nuvem assim que o
                        dispositivo se conecta à internet.</p>
                </section>

                <section id="artigo4" class="artigo">
                    <h2 class="perguntas">Como funciona a criação automática de
                        dashboards e gráficos?</h2>
                    <p class="respostas">Os dados coletados são processados e
                        exibidos instantaneamente em dashboards
                        pré-configurados. Administradores podem personalizar
                        novos gráficos e widgets.</p>
                </section>

                <section id="artigo5" class="artigo">
                    <h2 class="perguntas">Como funcionam os alarmes dentro do
                        aplicativo, e quando eles são ativados?</h2>
                    <p class="respostas">Os alarmes são usados para gestão de
                        estoque de materiais e, com base no uso da ETA, medem a
                        necessidade de reposição. Eles são ativados
                        automaticamente quando a reposição é necessária.</p>
                </section>

                <section id="artigo6" class="artigo">
                    <h2 class="perguntas">Como os funcionários não qualificados
                        usarão o aplicativo?</h2>
                    <p class="respostas">O design foca na simplicidade visual,
                        usando interface iconográfica, fluxos guiados passo a
                        passo, validações imediatas (alertas) e priorizando a
                        inserção de dados numéricos ou seleções em listas.</p>
                </section>

                <section id="artigo7" class="artigo">
                    <h2 class="perguntas">Como funciona a inteligência
                        artificial dentro do sistema?</h2>
                    <p class="respostas">A IA está inserida através de um
                        chatbot especializado e treinado na plataforma web e nos
                        dispositivos móveis, que responde a dúvidas sobre o
                        funcionamento das ETAs ou do próprio aplicativo.</p>
                </section>

            </main>
        </div>

        <!-- Footer -->

        <%@include file="/padrao/footer.jsp"%>

        <script href="<%=request.getContextPath()%>/Scripts/script-artigos.js"></script>
    </body>
</html>