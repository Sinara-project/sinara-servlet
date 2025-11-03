<%@ page import="java.util.List" %>
<%@ page import="com.sinara.model.Empresa" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="mensagens">
  <%
    String mensagem = (String) request.getAttribute("mensagem");
    List<String> erros = (List<String>) request.getAttribute("erro");
    // Apresentar mensagens de erro ou sucesso
    if (erros!=null) for (String erro : erros) {
  %>
  <p style="color: red">* <%=erro%></p>
  <%
      }
  %>
  <%  if (mensagem!=null) {
  %>
  <p style="color: green"><%=mensagem%> ✓</p>
  <%
    }
    request.removeAttribute("mensagem");
    request.removeAttribute("erro");
    Empresa.nulo = false;
  %>
</div>