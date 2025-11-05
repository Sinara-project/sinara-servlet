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
                    href>Política de uso</a></p>
            <div id="barra-pesquisa">
                <label for="text">
                    <img src="<%=request.getContextPath()%>/icones/lupa-pesquisa.svg"
                        alt>
                    <input type="text" placeholder="Pesquisar">
                </label>
            </div>
        </div>
        <p id="titulo-da-secao">Artigos - Política de uso</p>
        <div class="container">
            <aside class="menu">
                <button class="item-menu ativo" id-artigo="artigo1">As novas
                    funcionalidades do aplicativo são cobradas à parte?</button>
                <button class="item-menu" id-artigo="artigo2">É possível
                    exportar os relatórios e planilhas para Excel, PDF ou outros
                    formatos?</button>
                <button class="item-menu" id-artigo="artigo3">O que acontece com
                    os meus dados se eu decidir cancelar a assinatura do
                    Sinara?</button>
                <button class="item-menu" id-artigo="artigo4">Como funciona o
                    modelo de preços do aplicativo?</button>
            </aside>

            <main class="conteudo">
                <section id="artigo1" class="artigo ativo">
                    <h2 class="perguntas">As novas funcionalidades do aplicativo
                        são cobradas à parte?</h2>
                    <p class="respostas">Não. O Sinara opera como Software como
                        Serviço (SaaS). Novas funcionalidades e melhorias de
                        segurança já estão incluídas na taxa de assinatura
                        mensal/anual.</p>
                </section>

                <section id="artigo2" class="artigo">
                    <h2 class="perguntas">É possível exportar os relatórios e
                        planilhas para Excel, PDF ou outros formatos?</h2>
                    <p class="respostas">Sim. A plataforma web permite exportar
                        dados brutos para CSV (compatível com Excel) e gerar
                        relatórios consolidados em PDF.</p>
                </section>

                <section id="artigo3" class="artigo">
                    <h2 class="perguntas">O que acontece com os meus dados se eu
                        decidir cancelar a assinatura do Sinara?</h2>
                    <p class="respostas">Você tem 60 dias de prazo para realizar
                        a exportação completa de todos os seus dados em formatos
                        padronizados (CSV, PDF). Após o prazo, os dados da sua
                        conta são excluídos de forma segura.</p>
                </section>

                <section id="artigo4" class="artigo">
                    <h2 class="perguntas">Como funciona o modelo de preços do
                        aplicativo?</h2>
                    <p class="respostas">O custo mensal é R$2500, podendo ser
                        reduzido com o plano anual (R$25000). Também há uma
                        versão grátis que permite testar e avaliar o aplicativo
                        com funcionalidades essenciais.</p>
                </section>

            </main>
        </div>

        <!-- Footer -->

        <%@include file="/padrao/footer.jsp"%>

        <script href="<%=request.getContextPath()%>/Scripts/script-artigos.js"></script>
    </body>
</html>