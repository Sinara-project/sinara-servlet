<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pt-BR">
<head>
    <title>Área Restrita</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/Sinarinha.svg" type="image/x-icon">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/adm.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/aside.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/geral.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/lista-superior.css">
</head>
<body>
    <%@include file="/padrao/aside.jsp"%>
 
    <!-- Seção Principal -->
 
    <main>
        <%@include file="/padrao/lista-superior.jsp"%>
       
        <div id="container-titulo-botoes">
            <h1 id="titulo">Administradores Registrados</h1>
            <div id="container-adicionar">
                <button id="botao-adicionar"><p>Adicionar</p></button>
            </div>
        </div>
        <div id="container">
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
      <tr>
        <td>1</td>
        <td>peresgames@gmail.com</td>
        <td>Pedreiro</td>
        <td class="acoes">
          <button class="menu-btn">
            <img src="<%=request.getContextPath()%>/img/tres_pontinhos.svg" alt="menu">
          </button>
          <div class="menu-opcoes">
            <button class="editar">Editar</button>
            <button class="excluir">Excluir</button>
          </div>
        </td>
      </tr>
    </tbody>
  </table>
</div>
</main>


<div id="modal-editar" class="modal">
  <div class="modal-content">
    <h3>Editar Administrador</h3>
    <label>Nome:</label>
    <input type="text" id="nome-editar">
    <label>Email:</label>
    <input type="email" id="email-editar">
    <div class="modal-btns">
      <button id="salvar-editar">Salvar</button>
      <button id="cancelar-editar">Cancelar</button>
    </div>
  </div>
</div>
    <div id="modal-func" class="modal">
        <div class="modal-content">
          <h2>Adicionar ADMIN</h2>
     
          <div class="input-group">
            <label>
              <img src="<%=request.getContextPath()%>/img/user.svg" alt="">
              <input type="text" placeholder="Digite o nome completo">
            </label>
          </div>
     
          <div class="input-group">
            <label>
              <img src="<%=request.getContextPath()%>/img/email.svg" alt="">
              <input type="email" placeholder="Digite o Email">
            </label>
          </div>
          <button id="btnAdicionarFunc">Adicionar</button>
        </div>
      </div>
     
    <script src="<%=request.getContextPath()%>/JavaScript/adm.js"></script>
    <script src="<%=request.getContextPath()%>/JavaScript/tabela.js"></script>
</body>
</html>
 
