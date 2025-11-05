<%--
  Created by IntelliJ IDEA.
  User: viniciusboas-ieg
  Date: 05/11/2025
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
  <nav>
    <ul class="cabecalho">
      <a href="<%=request.getContextPath()%>/index.jsp"><img height="100" src="<%=request.getContextPath()%>/imagens/logo-sinara.svg" alt></a>
      <div class="botoes-nav">
        <li>
          <a href="<%=request.getContextPath()%>/Paginas-ajuda/index.jsp" id="link-ajuda">
            <button class="botao-header"
                    id="ajuda">Ajuda</button>
          </a>
        </li>
        <li>
          <a href="<%=request.getContextPath()%>/index.jsp#titulo-secao6" id="a_contato"> <button
                  class="botao-header"
                  id="contato">Contato</button></a>
        </li>
        <li>
          <a href="home"
             id="comece_ja" target="_blank">
            <button class="botao-header"
                    id="comece">Entrar</button>
          </a>
        </li>
      </div>
    </ul>
  </nav>
</header>