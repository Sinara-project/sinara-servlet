<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Configurações</title>
        <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/Sinarinha.svg" type="image/x-icon">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/config-industrias.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/aside.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/geral.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/lista-superior.css">
    </head>
    <body>

        <!-- Barra Lateral -->

        <%@include file="/padrao/aside.jsp"%>

        <!-- Seção Principal -->

        <main>
            <%@include file="/padrao/lista-superior.jsp"%>

            <div id="container-titulo-botoes">
                <div id="container-adicionar">
                    <button id="botao-adicionar"><p>Adicionar</p></button>

                </div>
            </div>
            <!-- Modal de Adicionar Funcionário -->
            <div id="modal-func" class="modal">
                <div class="modal-content">
                    <h2>Adicionar empresa</h2>

                    <div class="input-group">
                        <label>
                            <img src="<%=request.getContextPath()%>/img/user.svg" alt>
                            <input type="text"
                                placeholder="Nome da empresa">
                        </label>
                    </div>

                    <div class="input-group">
                        <label>
                            <img src="<%=request.getContextPath()%>/img/email.svg" alt>
                            <input type="text"
                                placeholder="Ramo">
                        </label>
                    </div>

                    <div class="input-group">
                        <label>
                            <img src="<%=request.getContextPath()%>/img/cargo.svg" alt>
                            <input type="email" placeholder="Digite o Email">
                        </label>
                    </div>
                    <div class="input-group">
                        <label>
                            <img src="<%=request.getContextPath()%>/img/cargo.svg" alt>
                            <input type="tel" placeholder="Digite o Telefone">
                        </label>
                    </div>
                    <div class="input-group">
                        <label>
                            <input type="" placeholder="Digite o CNPJ">
                        </label>
                    </div>
                    <div class="input-group">
                        <label>
                             <p>ativo? <input placeholder="Status" type="checkbox">
                        </label>
                    </div>
                    <div class="input-group">
                        <label>
                             <p>Início do Plano: <input placeholder="Status" type="date">
                        </label>
                    </div>
                    <div class="input-group">
                        <label>
                             <p>Plano: <select name="idEmpresa" id="idEmpresa" ><option value="pagamento">Anual</option> <option value="pagamentoMensal">Mensal</option></select>
                        </label>
                    </div>



                    <button id="btnAdicionarFunc">Adicionar</button>
                </div>
            </div>

            <h1 id="titulo-config">Configurações das Indústrias</h1>

            <div id="container-industrias">
                <div id="categorias">
                    <a href="<%=request.getContextPath()%>/empresas?action=quadrogeral"><button class="categoria" id="botao-adicionar">Quadro Geral</button></a>
                    <div id="container-titulo-botoes">
                </div>
                <div id="container-adicionar">
                    <a href="<%=request.getContextPath()%>/empresas"><button class="categoria" id="botao-adicionar">Empresa</button></a>
                </div>
            </div>
                <div id="container-empresas"></div>
                <div id="controles">
                    <button class="setas" onclick="anterior()">&#60;</button>
                    <button class="setas" onclick="proximo()">&#62;</button>
                </div>
            </div>
        </main>
        <script src="<%=request.getContextPath()%>/JavaScript/config.js"></script>
    </body>
</html>