<%@ page import="java.util.List" %>
<%@ page import="com.sinara.model.Empresa" %>
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
    <%@include file="/padrao/mensagens.jsp"%>
    <!-- Modal de Adicionar Empresa -->
    <div id="modal-func" class="modal">
        <form class="modal-content" action="<%=request.getContextPath()%>/empresas?action=adicionar" method="post">
            <h2>Adicionar empresa</h2>

            <div class="input-group">
                <label>
                    <img src="<%=request.getContextPath()%>/img/user.svg" alt>
                    <input type="text" name="nome"
                           placeholder="Nome da empresa">
                </label>
            </div>

            <div class="input-group">
                <label>
                    <img src="<%=request.getContextPath()%>/img/email.svg" alt>
                    <input type="text" name="ramo"
                           placeholder="Ramo">
                </label>
            </div>

            <div class="input-group">
                <label>
                    <img src="<%=request.getContextPath()%>/img/cargo.svg" alt>
                    <input type="email" name="email" placeholder="Digite o Email">
                </label>
            </div>
            <div class="input-group">
                <label>
                    <img src="<%=request.getContextPath()%>/img/cargo.svg" alt>
                    <input type="tel" name="telefone" placeholder="Digite o Telefone">
                </label>
            </div>
            <div class="input-group">
                <label>
                    <input type="text" name="cnpj" placeholder="Digite o CNPJ">
                </label>
            </div>
            <div class="input-group">
                <label>
                    Ativo?
                    <input placeholder="Status" name="status" type="checkbox">
                </label>
            </div>
            <div class="input-group">
                <label>
                    Início do Plano:
                    <input placeholder="Status" name="inicioPlano" type="date">
                </label>
            </div>
            <div class="input-group">
                <label>
                        Plano:
                    <select name="plano" id="idEmpresa">
                        <option value="ANUAL">Anual</option>
                        <option value="MENSAL">Mensal</option>
                        <option value="GRÁTIS">Grátis</option>
                    </select>
                </label>
            </div>
            <button class="botao" type="submit">Adicionar</button>
        </form>
    </div>

    <h1 id="titulo-config">Configurações das Indústrias</h1>

    <div id="container-industrias">
        <div id="categorias">
            <a href="<%=request.getContextPath()%>/empresas?action=visaoGeral"><button style="width: fit-content" class="categoria botao">Quadro Geral</button></a>
            <a href="<%=request.getContextPath()%>/empresas?action=listar"><button style="width: fit-content" class="categoria botao">Empresa</button></a>
            <div id="container-titulo-botoes" >
                <div id="container-adicionar">
                    <button class="categoria botao" style="width: fit-content" id="botao-adicionar">Adicionar Empresa</button>
                </div>
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