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
                <h1 id="titulo">Funcionários</h1>
                <div id="container-adicionar">
                    <button id="botao-adicionar"><p>Adicionar</p></button>
                </div>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>EMAIL</th>
                        <th>NOME</th>
                        <th>CARGO</th>
                        <th>EMPRESA</th>
                        <th>CPF</th>
                        <th>AÇÕES</th>
                    </tr>
                </thead>
                <tbody>
                    <tr data-id="1" data-cargo="Operador Geral"
                        data-empresa="EcoLen" data-cpf="22***4*1**">
                        <td>1</td>
                        <td>sophiaabiaki24@gmail.com</td>
                        <td>Sophia Castro</td>
                        <td>Operador Geral</td>
                        <td>EcoLen</td>
                        <td>22***4*1**</td>
                        <td>
                            <div class="menu-container">
                                <button class="opcoes-btn">
                                    <img src="<%=request.getContextPath()%>/img/tres_pontinhos.svg"
                                        alt="menu">
                                </button>
                                <div class="menu-opcoes">
                                    <button class="btn-editar">Editar</button>
                                    <button class="btn-excluir">Excluir</button>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr data-id="2" data-cargo="Operador Administrativo"
                        data-empresa="EcoLen" data-cpf="31***5*3**">
                        <td>2</td>
                        <td>gabrieldahora@gmail.com</td>
                        <td>Gabriel Hora</td>
                        <td>Operador Administrativo</td>
                        <td>EcoLen</td>
                        <td>31***5*3**</td>
                        <td>
                            <div class="menu-container">
                                <button class="opcoes-btn">
                                    <img src="<%=request.getContextPath()%>/img/tres_pontinhos.svg"
                                        alt="menu">
                                </button>
                                <div class="menu-opcoes">
                                    <button class="btn-editar">Editar</button>
                                    <button class="btn-excluir">Excluir</button>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr data-id="3" data-cargo="Operário" data-empresa="EcoLen"
                        data-cpf="98***3*9**">
                        <td>3</td>
                        <td>viniciuscastro@gmail.com</td>
                        <td>Vinícius Castro</td>
                        <td>Operário</td>
                        <td>EcoLen</td>
                        <td>98***3*9**</td>
                        <td>
                            <div class="menu-container">
                                <button class="opcoes-btn">
                                    <img src="<%=request.getContextPath()%>/img/tres_pontinhos.svg"
                                        alt="menu">
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
                    <input type="text" id="editNome"
                        placeholder="Nome completo">

                    <label>Email:</label>
                    <input type="email" id="editEmail" placeholder="Email">

                    <label>Cargo:</label>
                    <input type="text" id="editCargo" placeholder="Cargo">

                    <div class="modal-buttons">
                        <button id="salvarEdicao">Salvar</button>
                        <button id="cancelarEdicao">Cancelar</button>
                    </div>
                </div>
            </div>

            <!-- Modal de Adicionar Funcionário -->
            <div id="modal-func" class="modal" aria-hidden="true">
                <div class="modal-content">
                    <h2>Adicionar Funcionário</h2>

                    <div class="input-group">
                        <label>
                            <img src="<%=request.getContextPath()%>/img/user.svg" alt>
                            <input id="addNome" type="text"
                                placeholder="Digite o nome completo">
                        </label>
                    </div>

                    <div class="input-group">
                        <label>
                            <img src="<%=request.getContextPath()%>/img/email.svg" alt>
                            <input id="addEmail" type="email"
                                placeholder="Digite o Email">
                        </label>
                    </div>

                    <div class="input-group">
                        <label>
                            <img src="<%=request.getContextPath()%>/img/cargo.svg" alt>
                            <input id="addCargo" type="text"
                                placeholder="Digite o Cargo">
                        </label>
                    </div>

                    <div class="input-group">
                        <label>
                            <img src="<%=request.getContextPath()%>/img/empresa.svg" alt>
                            <input id="addEmpresa" type="text"
                                placeholder="Digite o nome da Empresa">
                        </label>
                    </div>

                    <div class="input-group">
                        <label>
                            <img src="<%=request.getContextPath()%>/img/cpf.svg" alt>
                            <input id="addCPF" type="text"
                                placeholder="Digite o CPF">
                        </label>
                    </div>

                    <button id="btnAdicionarEnviar"
                        class="btn-primary">Adicionar</button>
                </div>
            </div>

        </main>

        <script src="<%=request.getContextPath()%>/JavaScript/tabela2.js"></script>
    </body>
</html>
