<%@ page import="com.sinara.model.Empresa" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sinara.model.VisaoGeral" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<VisaoGeral> visoes = (List<VisaoGeral>) request.getAttribute("empresas");
%>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Funcionários</title>
  <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/Sinharinha.svg" type="image/x-icon">
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
    <h1 id="titulo">Quadro Geral</h1>
  </div>

  <div id="tabula">
  <table id="tabela">
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
    <%
      int cont = 1;
      for (VisaoGeral visao : visoes) {
    %>
    <tr data-id="1" data-cargo="Operador Geral" data-empresa="EcoLen" data-cpf="22***4*1**">
      <td><%=cont%></td>
      <td><%=visao.getNome()%></td>
      <td><%=visao.getQuant_administradores()%></td>
      <td><%=visao.getQuant_operarios()%></td>
      <td><%=visao.getQuant_alertas()%></td>
      <td><%=visao.getQuant_relatorios()%></td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>
  </div>

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
      <%@include file="/padrao/mensagens.jsp"%>
    </div>
  </div>
</main>

<script src="<%=request.getContextPath()%>/JavaScript/tabela2.js"></script>
</body>
</html>