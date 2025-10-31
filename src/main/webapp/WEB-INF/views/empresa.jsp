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
            <h1 id="titulo">Empresa</h1>
        </div>

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
                <tr data-id="1" data-cargo="Operador Geral" data-empresa="EcoLen" data-cpf="22***4*1**">
                    <td>1</td>
                    <td>Ambar</td>
                    <td>Ambar.suporte@gmail.com</td>
                    <td>11995911646</td>
                    <td>Energia</td>
                    <td>Ativo</td>
                    <td>10-03-2010</td>
                    <td>Mensal</td>
                    <td>
                        <div class="menu-container">
                            <button class="opcoes-btn">
                                <img src="<%=request.getContextPath()%>/img/tres_pontinhos.svg" alt="menu">
                            </button>
                            <div class="menu-opcoes">
                                <button class="btn-editar">Editar</button>
                                <button class="btn-excluir">Excluir</button>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr data-id="2" data-cargo="Operador Administrativo" data-empresa="EcoLen" data-cpf="31***5*3**">
                    <td>1</td>
                    <td>FriBoi</td>
                    <td>Friboi.suporte@gmail.com</td>
                    <td>987453627</td>
                    <td>Energia</td>
                    <td>Ativo</td>
                    <td>09-11-2010</td>
                    <td>Mensal</td>
                    <td>
                        <div class="menu-container">
                            <button class="opcoes-btn">
                                <img src="<%=request.getContextPath()%>/img/tres_pontinhos.svg" alt="menu">
                            </button>
                            <div class="menu-opcoes">
                                <button class="btn-editar">Editar</button>
                                <button class="btn-excluir">Excluir</button>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr data-id="3" data-cargo="Operário" data-empresa="EcoLen" data-cpf="98***3*9**">
                    <td>1</td>
                    <td>JBS</td>
                    <td>JBS.suporte@gmail.com</td>
                    <td>11995911646</td>
                    <td>Alimentos</td>
                    <td>Inativo</td>
                    <td>09-01-1990</td>
                    <td>Anual</td>
                    <td>
                        <div class="menu-container">
                            <button class="opcoes-btn">
                                <img src="<%=request.getContextPath()%>/img/tres_pontinhos.svg" alt="menu">
                            </button>
                            <div class="menu-opcoes">
                                <button class="btn-editar">Editar</button>
                                <button class="btn-excluir">Excluir</button>
                            </div>
                        </div>
                    </td>
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
