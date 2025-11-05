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
                    href>Segurança</a></p>
            <div id="barra-pesquisa">
                <label for="text">
                    <img href="<%=request.getContextPath()%>/icones/lupa-pesquisa.svg"
                        alt>
                    <input type="text" placeholder="Pesquisar">
                </label>
            </div>
        </div>
        <p id="titulo-da-secao">Artigos - Segurança</p>
        <div class="container">
            <aside class="menu">
                <button class="item-menu ativo" id-artigo="artigo1">Qual a
                    política do Sinara em relação à privacidade e propriedade
                    dos dados da minha indústria?</button>
                <button class="item-menu" id-artigo="artigo2">Como o Sinara
                    garante a rastreabilidade (quem, quando e onde) de cada dado
                    inserido?</button>
                <button class="item-menu" id-artigo="artigo3">Os dados coletados
                    são armazenados de forma segura?</button>
                <button class="item-menu" id-artigo="artigo4">É possível definir
                    níveis de acesso diferentes para operadores e
                    administradores?</button>
                <button class="item-menu" id-artigo="artigo5">O sistema é
                    compatível com normas ambientais ou de auditoria?</button>

            </aside>

            <main class="conteudo">
                <section id="artigo1" class="artigo ativo">
                    <h2 class="perguntas">Qual a política do Sinara em relação à
                        privacidade e propriedade dos dados da minha
                        indústria?</h2>
                    <p class="respostas">Você é o único proprietário dos seus
                        dados. O Sinara é apenas o processador e custodiante,
                        garantindo que os dados não sejam vendidos,
                        compartilhados ou usados indevidamente, respeitando a
                        LGPD.</p>
                </section>

                <section id="artigo2" class="artigo">
                    <h2 class="perguntas">Como o Sinara garante a
                        rastreabilidade (quem, quando e onde) de cada dado
                        inserido?</h2>
                    <p class="respostas">O Sinara registra automaticamente um
                        carimbo de auditoria para cada entrada: quem (usuário
                        logado), quando (data e hora) e onde (geolocalização via
                        GPS), criando um registro imutável.</p>
                </section>

                <section id="artigo3" class="artigo">
                    <h2 class="perguntas">Os dados coletados são armazenados de
                        forma segura?</h2>
                    <p class="respostas">Absolutamente. O armazenamento é em
                        servidores na nuvem (Cloud) com criptografia de ponta a
                        ponta, backups regulares e protocolos de segurança
                        robustos, seguindo as melhores práticas de mercado (LGPD
                        e segurança de dados industriais).</p>
                </section>

                <section id="artigo4" class="artigo">
                    <h2 class="perguntas">É possível definir níveis de acesso
                        diferentes para operadores e administradores?</h2>
                    <p class="respostas">Sim (Controle de Acesso Baseado em
                        Função - RBAC). É possível definir perfis de acesso
                        detalhados, como Operador (apenas preenchimento),
                        Gerente (dashboards da área) e Administrador (acesso
                        total).</p>
                </section>

                <section id="artigo5" class="artigo">
                    <h2 class="perguntas">O sistema é compatível com normas
                        ambientais ou de auditoria?</h2>
                    <p class="respostas">Sim. O Sinara facilita a conformidade
                        ao centralizar dados com rastreabilidade completa,
                        gerando um registro de auditoria essencial para atender
                        a normas regulatórias e ambientais.</p>
                </section>

            </main>
        </div>

        <!-- Footer -->

        <%@include file="/padrao/footer.jsp"%>

        <script href="<%=request.getContextPath()%>/Scripts/script-artigos.js"></script>
    </body>
</html>