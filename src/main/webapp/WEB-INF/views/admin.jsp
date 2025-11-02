<%@ page import="com.sinara.model.Administrador" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Administrador> administradores = (List<Administrador>) request.getAttribute("administradores");
    String message = (String) session.getAttribute("message");
    List<String> erros = (List<String>) session.getAttribute("erros");
%>

<html lang="pt-BR">
<head>
    <title>Área Restrita</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/Sinarinha.svg" type="image/x-icon">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/adm.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/aside.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/geral.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/lista-superior.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/tabela.css">
</head>
<body>
<%@include file="/padrao/aside.jsp"%>

<main>
    <%@include file="/padrao/lista-superior.jsp"%>

    <div id="container-titulo-botoes">
        <h1 id="titulo">Administradores Registrados</h1>
        <div id="container-adicionar">
            <button id="botao-adicionar"><p>Adicionar</p></button>
        </div>
    </div>

    <% if (message != null) { %>
    <p style="color: rgb(14,197,75)"> <%= message %> ✓</p>
    <% } %>

    <% if (erros != null && !erros.isEmpty()) {
        for (String erro : erros) { %>
    <p style="color: rgb(201,16,16); font-weight: 700;"><%= erro %> ⚠︎</p>
    <% }
        session.removeAttribute("erros");
    } %>

    <div id="container" class="tabula">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Email</th>
                <th>Nome</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Administrador admin : administradores) {
            %>
            <tr>
                <td><%= admin.getId() %></td>
                <td><%= admin.getEmail() %></td>
                <td><%= admin.getNome() %></td>
                <td class="acoes">
                    <button class="menu-btn">
                        <img src="<%=request.getContextPath()%>/img/tres_pontinhos.svg" alt="menu">
                    </button>
                    <div class="menu-opcoes">
                        <button class="editar">Editar</button>
                        <form action="<%= request.getContextPath() %>/administracao?action=excluir" method="post"
                              onsubmit="return confirmarExclusao(<%= admin.getId() %>);">
                            <input type="hidden" name="id" value="<%= admin.getId() %>">
                            <button class="excluirAdmin" type="submit">Excluir</button>
                        </form>

                        <script>
                            function confirmarExclusao(adminId) {
                                return confirm('Deseja mesmo excluir o administrador de ID ' + adminId + '?');
                            }
                        </script>

                    </div>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</main>

<!-- Modal Editar -->
<form id="modal-editar" class="modal" action="<%= request.getContextPath() %>/administracao?action=alterar" method="post">
    <div class="modal-content">
        <h3>Editar Administrador</h3>
        <input type="hidden" id="id-editar" name="id">
        <label>Nome:</label>
        <input type="text" id="nome-editar" name="nome" required><br><br>
        <label>Email:</label>
        <input type="email" id="email-editar" name="email" required><br><br>
        <div class="modal-btns">
            <button id="salvar-editar" type="submit" class="botao">Salvar</button>
            <button type="button" id="cancelar-editar" class="botao">Cancelar</button>
        </div>
    </div>
</form>

<!-- Modal Adicionar -->
<form id="modal-func" class="modal" action="<%= request.getContextPath() %>/administracao?action=adicionar" method="post">
    <div class="modal-content">
        <h2>Adicionar ADMIN</h2>
        <div class="input-group">
            <label><input type="text" id="cpf" placeholder="Digite o CPF" name="cpf" maxlength="14" required></label>
        </div>
        <div class="input-group">
            <label><input type="text" placeholder="Digite o nome completo" name="nome" required></label>
        </div>
        <div class="input-group">
            <label><input type="email" placeholder="Digite o Email" name="email" required></label>
        </div>
        <div class="input-group">
            <label><input type="text" placeholder="Digite o cargo" name="cargo" required></label>
        </div>
        <div class="input-group">
            <label><input type="password" placeholder="Digite a senha" name="senha" required></label>
        </div>
        <div class="modal-btns">
            <button id="btnAdicionarFunc" type="submit" class="botao">Adicionar</button>
            <button type="button" id="cancelar-adicionar" class="botao">Cancelar</button>
        </div>
    </div>
</form>

<script src="<%=request.getContextPath()%>/JavaScript/adm.js"></script>
<script src="<%=request.getContextPath()%>/JavaScript/tabela.js"></script>
<script src="<%=request.getContextPath()%>/JavaScript/pesquisa.js"></script>

</body>
</html>
