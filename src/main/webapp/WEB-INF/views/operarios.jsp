<%@ page import="com.sinara.model.Operario" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sinara.model.Empresa" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<String> erros = (List<String>) session.getAttribute("erros");
    String message = (String) session.getAttribute("message");
    if (message != null) {
        session.removeAttribute("message");
    }
%>

<html>
<head>
    <title>Operários</title>
    <meta charset="UTF-8">
    <style>
        .success {
            background-color: #cfc;
        }
        .error {
            background-color: #fcc;
        }
    </style>
</head>
<body>
<h1>Operários</h1>

<% if (message != null) {
    %>
<div class=""><%= message %></div>
<% } %>

<%
    if (erros != null && !erros.isEmpty()) {
        for (String erro : erros) {
%>
<h1 style="background-color: red"><%= erro %></h1>
<%
        }
        session.removeAttribute("erros");
    }
%>

<button onclick="window.location='operarios?action=adicionar'">Adicionar Operário</button>

<br><br>

<label for="campoFiltro">Filtrar por:</label>
<select id="campoFiltro">
    <option value="0">ID</option>
    <option value="1">Email</option>
    <option value="2">Nome</option>
    <option value="3">Cargo</option>
    <option value="4">Empresa</option>
</select>

<input type="text" id="valorFiltro" placeholder="Digite o valor...">

<table border="1" id="tabelaOperarios">
    <tr>
        <th>ID</th>
        <th>Email</th>
        <th>Nome</th>
        <th>Cargo</th>
        <th>Empresa</th>
        <th>Ações</th>
    </tr>
    <%
        List<Operario> listaOperarios = (List<Operario>) request.getAttribute("listaOperarios");
        Map<Integer, String> idParaNome = (Map<Integer, String>) request.getAttribute("idParaNome");

        if (listaOperarios != null && !listaOperarios.isEmpty()) {
            for (Operario operario : listaOperarios) {
    %>
    <tr>
        <td><%= operario.getId() %></td>
        <td><%= operario.getEmail() %></td>
        <td><%= operario.getNome() %></td>
        <td><%= operario.getCargo() %></td>
        <td><%= idParaNome != null ? idParaNome.get(operario.getIdEmpresa()) : "" %></td>
        <td>
            <button onclick="window.location='operarios?action=editar&id=<%= operario.getId() %>'">Editar</button>

            <!-- Formulário direto de exclusão -->
            <form method="post" action="operarios?action=excluir" style="display:inline;">
                <input type="hidden" name="id" value="<%= operario.getId() %>">
                <button type="submit">Excluir</button>
            </form>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="6">Nenhum operário encontrado.</td>
    </tr>
    <% } %>
</table>

<script>
    const tabela = document.getElementById("tabelaOperarios");
    const selectCampo = document.getElementById("campoFiltro");
    const inputValor = document.getElementById("valorFiltro");

    function normalizar(str) {
        return String(str || "").normalize("NFD").replace(/[\u0300-\u036f]/g, "").toLowerCase();
    }

    function filtrar() {
        const colIndex = parseInt(selectCampo.value, 10);
        const filtro = normalizar(inputValor.value);
        const linhas = tabela.getElementsByTagName("tr");

        for (let i = 1; i < linhas.length; i++) {
            const col = linhas[i].getElementsByTagName("td")[colIndex];
            linhas[i].style.display = (col && normalizar(col.textContent).includes(filtro)) ? "" : "none";
        }
    }

    inputValor.addEventListener("keyup", filtrar);
    selectCampo.addEventListener("change", filtrar);
</script>
</body>
</html>
