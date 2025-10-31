<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Funcionários</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/Sinarinha.svg" type="image/x-icon">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/funcionario.css">
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
            <h1 id="titulo">Quadro Geral</h1>
        </div>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>NOME</th>
                    <th>QUANTIDADE DE ADMINISTRADORES</th>
                    <th>QUANTIDADE DE OPERÁRIOS</th>
                    <th>QUANTIDADE DE ALERTAS</th>
                    <th>QUANTIDADE DE RELATÓRIOS</th>
                </tr>
            </thead>
            <tbody>
                <tr data-id="1" data-cargo="Operador Geral" data-empresa="EcoLen" data-cpf="22***4*1**">
                    <td>1</td>
                    <td>Ambar</td>
                    <td>1</td>
                    <td>2</td>
                    <td>3</td>
                    <td>5</td>
                </tr>
                
                <tr data-id="1" data-cargo="Operador Geral" data-empresa="EcoLen" data-cpf="22***4*1**">
                    <td>1</td>
                    <td>Ambar</td>
                    <td>1</td>
                    <td>2</td>
                    <td>3</td>
                    <td>5</td>
                </tr>
                <tr data-id="1" data-cargo="Operador Geral" data-empresa="EcoLen" data-cpf="22***4*1**">
                    <td>1</td>
                    <td>Ambar</td>
                    <td>1</td>
                    <td>2</td>
                    <td>3</td>
                    <td>5</td>
                </tr>
            </tbody>
        </table>

        <!-- Modal de Edição -->
        <div id="editarModal" class="modal">
            <div class="modal-content">
                <h2>Editar Funcionário</h2>

                <input type="hidden" id="editId">

                <label>Nome:</label>
                <input type="text" id="editNome" placeholder="Nome completo">

                <label>Email:</label>
                <input type="email" id="editEmail" placeholder="Email">

                <label>Cargo:</label>
                <input type="text" id="editCargo" placeholder="Cargo">

                <label>CPF:</label>
                <input type="text" id="editCpf" placeholder="CPF">

                <div class="modal-buttons">
                    <button id="salvarEdicao">Salvar</button>
                    <button id="cancelarEdicao">Cancelar</button>
                </div>
            </div>
        </div>

    </main>

    <script src="<%=request.getContextPath()%>/JavaScript/tabela2.js"></script>
</body>
</html>
