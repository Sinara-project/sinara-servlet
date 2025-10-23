<%@ page import="java.util.List" %>
<%@ page import="com.sinara.model.Empresa" %><%--
  Created by IntelliJ IDEA.
  User: viniciusboas-ieg
  Date: 22/10/2025
  Time: 06:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    // Métodos para definir valor caso usuário já o tenha digitado
    private String setPlaceholder(String placeholder) {
        if (placeholder!=null && !placeholder.isBlank()) {
            return "value="+placeholder;
        } else return "";
    }
    public String setEditado(Empresa empresa, int campo) {
        if (empresa!=null) { // Se empresa não for nula, fazer a substituição do texto já adicionado
            switch (campo) {
                case 0: return setPlaceholder(empresa.getCnpj());
                case 1: return setPlaceholder(empresa.getNome());
                case 2: return setPlaceholder(empresa.getEmail());
                case 3: return setPlaceholder(empresa.getRamo());
                case 4: return setPlaceholder(empresa.getTelefone());
            }
        } return "";
    }
%>
<% // Pegando empresa a ser editada, mesangens de sucesso, e mensagens de erro do request
  String mensagem = (String) request.getAttribute("mensagem");
  List<String> erros = (List<String>) request.getAttribute("erro");
  Empresa empresa = (Empresa) request.getAttribute("empresa");
%>
<html>
<head>
    <title>Adicionar Empresa</title>
</head>
<body>
    <div id="Mensagens">
        <% // Apresentar mensagens de erro ou sucesso
            if (erros!=null) for (String erro : erros) {
        %>
        <p style="color: red">* <%=erro%></p>
        <%
                }
        %>
        <%  if (mensagem!=null) {
        %>
        <p><%=mensagem%></p>
        <%
            }
            request.removeAttribute("mensagem");
            request.removeAttribute("erro");
            Empresa.nulo = false;
        %>
    </div>
    <form action="empresas?action=adicionar" method="post">
      <label for="cnpj">
        CNPJ:
        <input id="cnpj" name="cnpj" type="text" required placeholder="XX.XXX.XXX/0001-XX" <%=setEditado(empresa, 0)%>>
      </label>
      <br>
      <label for="nome">
        Nome:
        <input id="nome" name="nome" type="text" required placeholder="Sinara" <%=setEditado(empresa, 1)%>>
      </label>
      <br>
      <label for="email">
        Email:
        <input id="email" name="email" type="email" required placeholder="sinara@email.com" <%=setEditado(empresa, 2)%>>
      </label>
      <br>
      <label for="ramo">
        Ramo:
        <input id="ramo" name="ramo" type="text" required placeholder="Tratamento de Água" <%=setEditado(empresa, 3)%>>
      </label>
      <br>
      <label for="telefone">
        Telefone:
        <input id="telefone" name="telefone" type="text" required placeholder="(XX) 9XXXX-XXXX" <%=setEditado(empresa, 4)%>>
      </label>
      <br>
      <label for="status">
        Status:
        <input id="status" name="status" type="checkbox" checked>
      </label>
      <br>
      <label for="inicioPlano">
        Início do plano:
        <input id="inicioPlano" name="inicioPlano" type="date" required>
      </label>
      <br>
      <label for="plano">
        Plano:
        <select name="plano" id="plano" required>
          <option value="ANUAL" selected="selected">Anual</option>
          <option value="MENSAL">Mensal</option>
          <option value="GRÁTIS">Grátis</option>
        </select>
      </label>
      <br>
      <input type="submit" value="Enviar">
    </form>
</body>
</html>
