<%@ page import="com.sinara.model.Empresa" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String status;
    List<Empresa> empresas = (List<Empresa>) request.getAttribute("empresas");
%>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Funcionários</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/Sinarinha.svg" type="image/x-icon">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/empresa.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/tabela.css">
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
        <h1 id="titulo">Empresa</h1>
    </div>
    <%@include file="/padrao/mensagens.jsp"%>
    <div id="tabula">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>NOME</th>
            <th>EMAIL</th>
            <th>TELEFONE</th>
            <th>RAMO</th>
            <th>ATIVIDADE</th>
            <th>INÍCIO DO PLANO</th>
            <th>TIPO DE ASSINATURA</th>
            <th>AÇÕES</th>
        </tr>
        </thead>
        <tbody>
        <%
            int cont = 1;
            for (Empresa empresa : empresas) {
                if (empresa.isStatus()) status = "Ativo";
                else status = "Inativo";
        %>
        <tr data-id="<%=empresa.getId()%>" data-ramo="<%=empresa.getRamo()%>" data-nome="<%=empresa.getNome()%>"
            data-cnpj="<%=empresa.getCnpj()%>" data-email="<%=empresa.getEmail()%>" data-telefone="<%=empresa.getTelefone()%>">
            <td><%=cont++%></td>
            <td><%=empresa.getNome()%></td>
            <td><%=empresa.getEmail()%></td>
            <td><%=empresa.getTelefone()%></td>
            <td><%=empresa.getRamo()%></td>
            <td><%=status%></td>
            <td><%=empresa.getInicioPlano()%></td>
            <td><%=empresa.getPlano()%></td>
            <td>
                <div class="menu-container">
                    <button class="opcoes-btn">
                        <img src="<%=request.getContextPath()%>/img/tres_pontinhos.svg" alt="menu">
                    </button>
                    <div class="menu-opcoes">
                        <button class="btn-editar">Editar</button>
                        <a href="empresas?action=excluir&id=<%=empresa.getId()%>"><button>Excluir</button></a>
                    </div>
                </div>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    </div>

    <!-- Modal de Edição -->

    <div id="editarModal" class="modal">
        <form class="modal-content" action="<%=request.getContextPath()%>/empresas?action=atualizar" method="post">
            <h2>Editar Funcionário</h2>

            <input type="hidden" id="editId" name="id">

            <label for="editNome">Nome:</label>
            <input type="text" id="editNome" name="nome" placeholder="Nome completo">

            <label for="editEmail">Email:</label>
            <input type="email" id="editEmail" name="email" placeholder="Email">

            <label for="editRamo">Ramo:</label>
            <input type="text" id="editRamo" name="ramo" placeholder="Ramo">

            <label for="editTelefone">Telefone:</label>
            <input type="text" id="editTelefone" name="telefone" placeholder="Telefone">

            <label for="editStatus">
                Status:
                <input type="checkbox" id="editStatus" name="status" style="width: initial" value="on">
            </label>

            <div class="modal-buttons">
                <button class="botao" style="width: fit-content" type="submit">Salvar Mudanças</button>
                <button class="botao" style="width: fit-content" id="cancelarEdicao">Cancelar</button>
            </div>
        </form>
    </div>
</main>

<script src="<%=request.getContextPath()%>/JavaScript/tabela2.js"></script>
<script src="<%=request.getContextPath()%>/JavaScript/tabela2.js"></script>
</body>
</html>