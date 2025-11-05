<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Otimize a coleta e a análise de dados da ETA</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/Estilos/style.css">
        <link rel="icon" type="image/svg" href="<%=request.getContextPath()%>/icones/icone-sinara.svg">
    </head>
    <body>

        <!-- Header -->

        <%@include file="padrao/header.jsp"%>

        <!--  Seção 1 - Principal -->

        <main>
            <section class="secao1">
                <div id="separacao">
                    <div class="container">
                        <h1 id="texto-inicial">Otimize a coleta e a <br> análise
                            de dados da ETA</h1>
                        <p id="descricao">Onde a produção <b>fala</b> e a <br>
                            gestão <b>escuta</b>.</p>

                        <a href="Pagina-Detalhes/index.jsp" id="saiba">
                            <button id="saiba-mais">Saiba mais</button>
                        </a>
                    </div>
                    <img id="imagem-notebook" src="imagens/notebook.svg"
                        alt="imagem do notebook">
                </div>
            </section>

            <!-- Segunda seção - O que é o Sinara -->

            <img id="primeira-divisao" src="divisoes/ondinha.svg"
                alt="transição de ondinha de uma seção para outra.">
            <section class="secao2">
                <div id="container2">
                    <img id="secao2-imagem"
                        src="imagens/imgsecao2.svg" alt="imagem-sinara">
                    <div id="secao2-texto-titulos">
                        <h1 class="titulos-secao"
                            id="oque-eh">O Que é o Sinara?</h1>
                        <p class="explicacao" id="primeira-explicacao">O SINARA
                            é um
                            aplicativo que visa descomplicar toda a burocracia,
                            agilizar os processos manuais, e facilitar o
                            controle nas Estações
                            de Tratamento de Água, provendo soluções intuitivas,
                            e
                            fáceis de implementar.
                        </p>
                    </div>
                </div>
            </section>

            <!-- Seção 3 - Colaboradores  -->

            <section class="secao3">
                <h1 class="titulos-secao" id="titulo-colaboradores">
                    Colaboradores
                </h1>
                <div class="carrossel">
                    <div id="colaborador" class="titulo-colaborador"></div>
                    <div class="controles">
                        <button class="seta-colaborador" onclick="anterior()">
                            <
                        </button>
                        <button class="seta-colaborador" onclick="proximo()">
                            >
                        </button>
                    </div>
                </div>
            </section>

            <!-- Seção 4 - Funcionamento  -->

            <img id="divisao-secao4" src="divisoes/onda-azul-secao4.svg"
                alt="divisão da seção com uma onda azul">
            <section class="secao4">
                <div class="bubble-container">
                    <span class="bubble"></span>
                    <span class="bubble"></span>
                    <span class="bubble"></span>
                    <span class="bubble"></span>
                    <span class="bubble"></span>
                    <span class="bubble"></span>
                    <span class="bubble"></span>
                    <span class="bubble"></span>
                    <span class="bubble"></span>
                    <span class="bubble"></span>
                    <span class="bubble"></span>
                    <span class="bubble"></span>
                    <span class="bubble"></span>
                    <span class="bubble"></span>
                    <span class="bubble"></span>
                    <span class="bubble"></span>
                    <span class="bubble"></span>
                    <span class="bubble"></span>
                    <span class="bubble"></span>
                    <span class="bubble"></span>
                </div>

                <h1 class="titulos-secao" id="titulo-funcionamento">Como
                    Funciona?</h1>
                <div id="container-secao4">
                    <div class="explicacao-secao4" id="explicacao1-secao4">
                        <h2 class="subtitulo">
                            1. Criação dos formulários
                        </h2>
                        <p class="explicacao" id="segunda-explicacao">A equipe
                            administrativa cria<br>
                            formulários personalizados <br> utilizando o
                            aplicativo.</p>
                    </div>
                    <img src="imagens/celular1-secao4.svg" class="imagem-secao4"
                        alt="Celular criação formulário">
                </div>

                <div id="container2-secao4">
                    <div id="explicacao2-secao4" class="explicacao-secao4">
                        <img src="imagens/preenchimento-secao4.svg"
                            id="imagem-formulario"
                            alt="imagem intuitiva de preenchimeno do formulário">
                        <div id="container-textos-secao4">
                            <h2 class="subtitulo" id="segundo-subtitulo">
                                2. Preenchimento dos formulários
                            </h2>
                            <p class="explicacao"
                                id="explicacao-preenchimento">Os operadores
                                preenchem os
                                formulários criados <br> e disponibilizados
                                em tempo real.
                            </p>
                        </div>
                    </div>
                </div>

                <div id="container3-secao4">
                    <div class="explicacao-secao4" id="explicacao3-secao4">
                        <h2 class="subtitulo" id="terceiro-subtitulo">
                            3. Análise
                        </h2>
                        <p class="explicacao" id="explicacao-criacao">
                            Automaticamente são
                            criados gráficos, <br> planilhas, dashboards e
                            alertas baseados<br> nos
                            dados coletados dos formulários.</p>
                    </div>
                    <img src="imagens/celular2-secao4.svg" class="imagem-secao4"
                        alt="Tela do dashboards ">
                </div>
            </section>

            <img id="divisao2-secao4" src="divisoes/onda-clara-secao4.svg"
                alt="divisão da seção 4 para a seção 5">

            <!-- Seção 5 - Benefícios -->

            <section class="secao5">
                <h1 class="titulos-secao" id="terceiro-titulo"> Principais
                    benefícios</h1>
                <div id="container-secao5">
                    <div class="blocos-explicativos">
                        <div class="fundo-secao5" id="fundo1-secao5">
                            <img class="icones-secao5" id="icone-cadeado"
                                src="icones/Cadeado.svg" alt="Ícone de cadeado">
                        </div>
                        <h2 class="beneficio">Dados digitalizados e seguros</h2>
                        <p class="detalhe">Registros organizados e acessíveis a
                            qualquer momento.</p>
                    </div>
                    <div class="blocos-explicativos">
                        <div class="fundo-secao5" id="fundo2-secao5">
                            <img class="icones-secao5" id="icone-papel"
                                src="icones/Papel.svg" alt="Ícone de papel">
                        </div>
                        <h2 class="beneficio">Menos erros em coletas de
                            dados</h2>
                        <p class="detalhe">Validações automáticas garantem
                            precisão nos registros.</p>
                    </div>
                    <div class="blocos-explicativos">
                        <div class="fundo-secao5" id="fundo3-secao5">
                            <img class="icones-secao5" id="icone-interface"
                                src="icones/Interface.svg"
                                alt="Ícone de interface">
                        </div>
                        <h2 class="beneficio">Interface intuitiva</h2>
                        <p class="detalhe">Fácil de usar por operadores e
                            gestores.</p>
                    </div>
                    <div class="blocos-explicativos">
                        <div class="fundo-secao5" id="fundo4-secao5">
                            <img class="icones-secao5" id="icone-prancheta"
                                src="icones/Prancheta.svg"
                                alt="Ícone de prancheta">
                        </div>
                        <h2 class="beneficio">Controle de estoque eficiente</h2>
                        <p class="detalhe">Gestão precisa de insumos, evitando
                            desperdícios.</p>
                    </div>
                </div>
            </section>

            <!-- Seção 6 -  Meios de contato -->

            <section class="secao6">
                <h1 class="titulos-secao" id="titulo-secao6">Alguma dúvida?</h1>
                <p id="subtitulo-secao6">Nossa equipe está aqui para ajudar!
                    Entre
                    em contato conosco para suporte.</p>
                <div class="quadro" id="quadro-ajuda">
                    <h2 id="titulo-quadro">MEIOS DE CONTATO</h2>
                    <div id="container-secao6">
                        <div id="container-contatos">
                            <div class="formas-contato">
                                <a href="https://wa.link/g33hrg">
                                    <img class="icones-secao6"
                                        src="icones/telefone.svg"
                                        alt="Icone de telefone">
                                </a>
                                <p class="meios-contato" id="numero-sinara">+55
                                    11
                                    2219-1030</p>
                            </div>
                            <div class="formas-contato">
                                <img class="icones-secao6"
                                    src="icones/email.svg" alt="ícone de email">

                                <p class="meios-contato"
                                    id="email-sinara">sinaraoficial.suporte@gmail.com</p>
                            </div>
                            <div class="formas-contato">
                                <img class="icones-secao6"
                                    id="icone-insta-secao6"
                                    src="icones/icone-instagram.svg"
                                    alt="ícone do instagram">
                                <p class="meios-contato"
                                    id="insta-sinara">@sinara.oficial</p>
                            </div>
                        </div>
                        <div id="container-campos">
                            <form
                                action="mailto:sinaraoficial.suporte@gmail.com">
                                <label for="text" class="label-campos">
                                    <input type="text" class="campos"
                                        id="nome-campo"
                                        placeholder="Digite o seu nome completo"
                                        name="subject" required>
                                    <img src="icones/pessoa.svg" alt="pessoa"
                                        class="icons"
                                        id="icone-pessoa">
                                </label>
                                <label for="text" class="label-campos">
                                    <textarea type="text" class="campos"
                                        id="duvida-campo"
                                        placeholder="Digite sua dúvida"
                                        name="body" required></textarea>
                                </label>
                                <a href>
                                    <button id="envio-secao6">Enviar</button>
                                </a>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        <%@include file="/padrao/footer.jsp"%>
        <script>
            const colaboradores = [
                {
                    nome: "Bruna Santos",
                    img: "imagens/bruna.svg",
                    descricao:"Sou a Bruna dos Santos Salla, trabalho em desenvolvimento no banco original, usando majoritariamente C#.",
                    insta: "https://instagram.com/salla05090",
                    github: "https://github.com/Salla0905"
                },
                {
                    nome: "Gabriel Peres",
                    img: "imagens/gabriel.svg",
                    descricao:"Oi! Meu nome é Gabriel Peres, atualmente estou na 1º série do Germinare Tech, e sou um dos responsáveis pelo banco de dados do projeto. Atualmente, tenho interesse em seguir na área de Back-end.",
                    insta: "https://instagram.com/gab.p22k",
                    github: "https://github.com/GabPGarcia"
                },
                {
                    nome: "Heloisa Jesus",
                    img: "imagens/heloisa.svg",
                    descricao: "Atuo na área de dados e inteligência artificial no projeto, que tem como foco transformar informações em conhecimento útil e desenvolver soluções inteligentes. Isso inclui o uso de análise de dados, modelos de IA e chatbots para apoiar a equipe, otimizar processos e trazer inovação para o projeto.",
                    insta: "https://www.instagram.com/helo_mdj/",
                    github: "https://github.com/heloisamdj"
                },
                {
                    nome: "Isabelly da Hora",
                    img: "imagens/isabelly.svg",
                    descricao: "Oiêe! Sou a Isabelly, estudante do primeiro ano do ensino médio na Germinare TECH. Atualmente, estou focada em desenvolvimento front-end no interdisciplinar. Tenho interesse em seguir na área de análise de dados.  ",
                    insta: "https://instagram.com/dahoraisa",
                    github: "https://github.com/IsaDaHxra"
                },
                {
                    nome: "Lorena Tavares",
                    img: "imagens/lorena.svg",
                    descricao: "Oie! Me chamo Lorena, atualmente curso Desenvolvimento de Sistemas na Germinare e sou desenvolvedora back-end na PicPay, na área de Conta Global. Tenho interesse tanto por design quanto por back-end.",
                    insta: "https://instagram.com/loloh_tavares",
                    github: "https://github.com/Lorenaatavares"
                },
                {
                    nome: "Mariana Luna",
                    img: "imagens/mariana.svg",
                    descricao: "Oi! Sou a Mari Luna, estudante de Desenvolvimento de Sistemas e desenvolvedora front-end na PicPay, na área de PJ. Tenho interesse em design, frontend e engenharia de software — e estou colocando tudo isso em prática no desenvolvimento do Sinara, junto com meu grupo.",
                    insta: "https://instagram.com/marilunasgallery",
                    github: "https://github.com/Mariluna09"
                },
                {
                    nome: "Olavo Ventura",
                    img: "imagens/olavo.svg",
                    descricao: " Meu nome é Olavo Ventura, tenho 16 anos, e sou analista de dados do Sinara.",
                    insta: "https://instagram.com/whoisventtura",
                    github: "https://github.com/OlavoVent"
                },
                {
                    nome: "Pedro Tarquiano",
                    img: "imagens/tarqui.svg",
                    descricao: "Olá! Sou o Pedro, mais conhecido como Tarqui. Sou estudante de programação, apaixonado por criar, aprender e transformar ideias em projetos. Tenho perfil criativo, gosto de explorar diferentes perspectivas e estou sempre em busca de novos desafios.",
                    insta: "https://instagram.com/Tarquiano_oficial",
                    github: "https://github.com/Tarquiano"
                },
                {
                    nome: "Rafael Queiroz",
                    img: "imagens/rafael.svg",
                    descricao: "Oi eu sou o Rafa, sou estudante da primeira série da Germinare TECH, tento sempre dar o meu máximo quando o que eu faço pode influenciar outra pessoa; sou esquecido, porém tento corrigir. Espero poder aprender e ensinar cada vez mais. ",
                    insta: "https://instagram.com/rafael.queirozxz",
                    github: "https://github.com/RafonesLenda"
                },
                {
                    nome: "Rafael Pither",
                    img: "imagens/pizza.svg",
                    descricao: "Sou o Rafael Pither, frontend do 2° ano dev, gosto muito de gatos e torço pro São Paulo.",
                    insta: "https://instagram.com/rafapizza14",
                    github: "https://github.com/RafaPitherPizza "
                },
                {
                    nome: "Rafaella Antunes",
                    img: "imagens/rafaella.svg",
                    descricao: "Oii, sou a Rafaella Antunes, tenho 16 anos e estou no segundo ano de Análise de dados e no aplicativo eu sou a responsável pelos dashboards, treinamento de modelos e pela análise exploratória do Sinara.",
                    insta: "https://instagram.com/ _antunsz",
                    github: "https://github.com/rafaellaantunes27"
                },
                {
                    nome: "Rogerio Buscariolo",
                    img: "imagens/rogerio.svg",
                    descricao: "Olá sou o Rogerio! Sou aluno da primeira série Da Germinare Tech no Instituto J&F. Sou uma pessoa muito carismática, inteligente, sincera e amigável. Sempre busco meus objetivos e coisas que despertam meu interesse.",
                    insta: "https://instagram.com/rogerx.bsx",
                    github: "https://github.com/buscariolo-rogerio"
                },
                {
                    nome: "Sophia Castro",
                    img: "imagens/sophia.svg",
                    descricao: "Oii! Sou a Sophia, atualmente estudante do Germinare Tech, onde auxiliei a desenvolver a ideia, e contribuo ativamente com a evolução do projeto Sinara para que o mesmo chegue a sua fase final. Meus interesses incluem a área de Dados e BackEnd.",
                    insta: "https://instagram.com/shhiac",
                    github: "https://github.com/Shhiaa"
                },
                {
                    nome: "Vinícius Vilas Boas",
                    img: "imagens/vinicius.svg",
                    descricao: "Olá! Sou o Vinícius, atualmente estudante do Germinare Tech, onde ajudei a desenvolver a ideia, e contribuo com a implementação do projeto Sinara ao longo de todo nosso grupo! Me interesso pelo Back-End, e por Desenvolvimento no geral.",
                    insta: "https://instagram.com/vinicius_v_boas",
                    github: "https://github.com/Vini-Boas"
                }
            ];

            let index = 0;

            function mostrarColaborador(i) {
                const colab = colaboradores[i];
                document.getElementById("colaborador").innerHTML = `
      <h2 id="colaborador-nome">${colab.nome}</h2>
      <div class= "container-colaboradores" >
          <img class="foto" src="${colab.img}" alt="${colab.nome}"
          style="user-select: none; -webkit-user-drag: none; pointer-events: none; position: relative; bottom: 70px;">

          <p id="descricao-colaborador">${colab.descricao}</p>
      </div>
      <div id="container-redes">
        <div id="icone-instagram">
          <a href="${colab.insta}" target="_blank">
            <img id="imagem-icone-insta" src="icones/icone-instagram.svg" alt="Instagram"
            style="user-select: none; -webkit-user-drag: none; pointer-events: none;">
          </a>
        </div>
        <div id="icone-github">
          <a href="${colab.github}" target="_blank">
            <img id="imagem-icone-github" src="icones/icone-github.svg" alt="GitHub"
            style="user-select: none; -webkit-user-drag: none; pointer-events: none;">
          </a>
          </div>
      </div>
    `;
            }

            function anterior() {
                index = (index - 1 + colaboradores.length) % colaboradores.length;
                mostrarColaborador(index);
            }

            function proximo() {
                index = (index + 1) % colaboradores.length;
                mostrarColaborador(index);
            }

            mostrarColaborador(index);
        </script>
    </body>
</html>