<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="PT-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Conheça o Sinara: Nossa origem e missão</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Estilos/detalhes.css">
        <link rel="icon" type="image/svg" href="<%=request.getContextPath()%>/icones/icone-sinara.svg">
    </head>
    <body>
        <div class="fundo-azul"></div>

        <!-- Header -->

        <%@include file="/padrao/header.jsp"%>

        <!-- Primeira Seção -->

        <section id="primeira-secao">

            <div id="titulos-principais">
                <h1 id="titulo-principal">Conheça o Sinara:</h1>
                <h2 id="subtitulo-principal">Nossa origem e missão</h2>
            </div>
            <div id="memoria-de-olavo">
                <img id="imagem-pessoa"
                    src="<%=request.getContextPath()%>/imagens/olavo-detalhes.svg"
                    alt="foto legal">
            </div>
        </section>

        <div id="divisao-azul"></div>

        <!-- Segunda Seção -->

        <section id="segunda-secao">
            <img src="<%=request.getContextPath()%>/imagens/equipe-sinara.svg">
            <div>
                <h2>Quem somos?</h2>
                <p>Desenvolvido ao longo de 2025 por um grupo de estudantes do
                    Instituto J&F, o Sinara nasceu com uma missão clara:
                    desenvolver uma solução prática para o Objetivo de
                    Desenvolvimento Sustentável (ODS) 9 da ONU.</p>
                <p>A ODS 9 visa promover o desenvolvimento sustentável e
                    inclusivo, construindo infraestruturas resilientes,
                    fomentando a industrialização sustentável e impulsionando a
                    inovação em todos os países. O Sinara é a nossa resposta
                    digital para este desafio.</p>
            </div>
        </section>

        <!-- Terceira Seção -->

        <section id="terceira-secao">
            <div id="titulo-descricao-secao3">
                <h1 id="titulo-secao3">O que o Sinara faz</h1>
                <p id="descricao-secao3">O Sinara traduz o escopo da ODS 9 em
                    uma solução digital completa para indústrias.</p>
            </div>
            <div id="cards">
                <div class="cards-terceira-secao" id="primeiro-card">
                    <h2 class="titulo-cards">Plataforma Web</h2>
                    <p class="descricao-cards">Sistematiza, agiliza e moderniza
                        o preenchimento de tabelas e formulários. É a central de
                        administração de todos os dados registrados e
                        utilizados.</p>
                </div>

                <div class="cards-terceira-secao" id="segundo-card">
                    <h2 class="titulo-cards">Aplicativo Móvel</h2>
                    <p class="descricao-cards">Usado principalmente pelos
                        trabalhadores que atuam nas Estações de Tratamento de
                        Água (ETAs) Industriais, garantindo a coleta de dados de
                        forma ágil e em tempo real.</p>
                </div>
            </div>
        </section>

        <img id="onda-secao4"
            src="<%=request.getContextPath()%>/divisoes/onda3-detalhes.svg" alt>

        <!-- Quarta Seção -->

        <section id="quarta-secao">
            <div id="titulo-descricao-secao4">
                <h1 id="titulo-secao4">Por que escolher o Sinara?</h1>
                <p class="descricoes-secao4">Escolher o Sinara é dizer sim para
                    a inovação e para a modernidade, ao utilizar o Sinara, sua
                    indústria transforma a coleta de dados de um gargalo
                    burocrático em uma vantagem competitiva. Você não apenas
                    atende aos rigorosos padrões de industrialização
                    sustentável, mas também ganha eficiência operacional,
                    conformidade ambiental e inovação acessível.</p>
                <p class="descricoes-secao4">A próxima etapa da sua indústria
                    começa com o <b>Sinara</b>.</p>
            </div>
            <img id="celular" src="<%=request.getContextPath()%>/imagens/celular.png"
                alt>

        </section>
    </body>
</html>