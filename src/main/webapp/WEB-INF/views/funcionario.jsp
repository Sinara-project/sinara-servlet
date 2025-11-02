<%@ page import="java.util.List" %>
<%@ page import="com.sinara.model.Operario" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.sinara.model.Empresa" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<String> erros = (List<String>) session.getAttribute("erros");
    String message = (String) session.getAttribute("message");
    List<Operario> listaOperarios = (List<Operario>) request.getAttribute("listaOperarios");
    Map<Integer, String> idParaNome = (Map<Integer, String>) request.getAttribute("idParaNome");

    if (message != null) {
        session.removeAttribute("message");
    }
%>

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

<%@include file="/padrao/aside.jsp"%>

<main>
    <%@include file="/padrao/lista-superior.jsp"%>

    <div id="container-titulo-botoes">
        <h1 id="titulo">Funcionários</h1>
        <% if (message != null) { %>
        <p style="color: rgb(14,197,75)"> <%= message %> ✓</p>
        <% } %>

        <% if (erros != null && !erros.isEmpty()) {
            for (String erro : erros) { %>
        <p style="color: rgb(201,16,16); font-weight: 700;"><%= erro %> ⚠︎</p>
        <% }
            session.removeAttribute("erros");
        } %>

        <div id="container-adicionar">
            <button id="botao-adicionar"><p>Adicionar</p></button>
        </div>
    </div>

    <div id="tabula">
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
            <% if (listaOperarios != null && !listaOperarios.isEmpty()) {
                for (Operario operario : listaOperarios) { %>
            <tr data-id="<%= operario.getId() %>"
                data-cargo="<%= operario.getCargo() %>"
                data-empresa="<%= idParaNome != null ? idParaNome.get(operario.getIdEmpresa()) : "" %>"
                data-cpf="<%= operario.getCpf() %>">
                <td><%= operario.getId() %></td>
                <td><%= operario.getEmail() %></td>
                <td><%= operario.getNome() %></td>
                <td><%= operario.getCargo() %></td>
                <td><%= idParaNome != null ? idParaNome.get(operario.getIdEmpresa()) : "" %></td>
                <td><%= operario.getCpf() %></td>
                <td>
                    <div class="menu-container">
                        <button class="opcoes-btn">
                            <img src="<%=request.getContextPath()%>/img/tres_pontinhos.svg" alt="menu">
                        </button>
                        <div class="menu-opcoes">
                            <button class="btn-editar">Editar</button>
                            <form action="<%= request.getContextPath() %>/operarios?action=excluir"
                                  method="post">
                                <input type="hidden" name="id" value="<%= operario.getId() %>">
                                <button class="btn-excluir" type="submit">Excluir</button>
                            </form>
                        </div>
                    </div>
                </td>
            </tr>
            <% } } %>
            </tbody>
        </table>
    </div>

    <!-- Modal de Adicionar Funcionário -->
    <form id="modal-func" class="modal" method="post" action="<%=request.getContextPath()%>/operarios?action=inserir">
        <div class="modal-content">
            <h2>Adicionar Funcionário</h2>

            <div class="input-group">
                <label>
                    <img src="/img/user.svg" alt>
                    <input id="addNome" type="text" name="nome" placeholder="Digite o nome completo" required>
                </label>
            </div>

            <div class="input-group">
                <label>
                    <img src="/img/email.svg" alt>
                    <input id="addEmail" type="email" name="email" placeholder="Digite o Email" required>
                </label>
            </div>

            <div class="input-group">
                <label>
                    <img src="/img/cargo.svg" alt>
                    <input id="addCargo" type="text" name="cargo" placeholder="Digite o Cargo">
                </label>
            </div>

            <div class="input-group">
                <label>
                    <img src="/img/empresa.svg" alt>
                    <input list="empresas-list" id="addEmpresa" name="empresa" placeholder="Digite o nome da Empresa">
                </label>
                <datalist id="empresas-list">
                    <%
                        List<Empresa> listaEmpresas = (List<Empresa>) request.getAttribute("listaEmpresas");
                        if (listaEmpresas != null) {
                            for (Empresa emp : listaEmpresas) {
                    %>
                    <option value="<%= emp.getNome() %>"></option>
                    <%
                            }
                        }
                    %>
                </datalist>
            </div>

            <div class="input-group">
                <label>
                    <img src="/img/password.svg" alt>
                    <input id="addSenha" type="password" name="senha" placeholder="Digite a Senha" required>
                </label>
            </div>

            <div class="input-group">
                <label>
                    <img src="/img/cpf.svg" alt>
                    <input id="addCPF" type="text" name="cpf" placeholder="Digite o CPF" maxlength="14" required>
                </label>
            </div>

            <div class="input-group">
                <label for="addHorario">Horário total de trabalho:</label>
                <input id="addHorario" type="time" name="horarioTrabalho" required>
            </div>

            <button class="btn-primary" type="submit">Adicionar</button>
        </div>
    </form>

    <!-- Modal de Edição -->
    <form id="editarModal" class="modal" action="<%= request.getContextPath() %>/operarios?action=atualizar" method="post">
        <div class="modal-content">
            <h2>Editar Funcionário</h2>

            <input type="hidden" id="editId" name="id">

            <div class="input-group">
                <label>Nome:
                    <input type="text" id="editNome" name="nome">
                </label>
            </div>
            <div class="input-group">
                <label>Email:
                    <input type="email" id="editEmail" name="email">
                </label>
            </div>
            <div class="input-group">
                <label>Cargo:
                    <input type="text" id="editCargo" name="cargo">
                </label>
            </div>

            <div class="modal-buttons">
                <button id="salvarEdicao" class="botao" type="submit">Salvar</button>
                <button id="cancelarEdicao" class="botao" type="button">Cancelar</button>
            </div>
        </div>
    </form>

</main>



<script src="<%= request.getContextPath() %>/JavaScript/tabela2.js"></script>
<script src="<%=request.getContextPath()%>/JavaScript/pesquisa.js"></script>


</body>
</html>
