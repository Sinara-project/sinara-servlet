<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul id="lista-superior">
    <li>
        <div id="perfil">
            <div id="foto"></div>
            <P>Bem-vindo,<br><b><%=request.getSession().getAttribute("user")%></b> </P>
        </div>
    </li>
    <li id="barra-pesquisa">
        <label>
            <img src="<%=request.getContextPath()%>/img/lupa.svg" alt="lupa">
            <input type="search" placeholder="Pesquisar" id="valorFiltro">
        </label>
    </li>
    <li class="icones-superior">
        <a href=""><img src="<%=request.getContextPath()%>/img/filtro.svg" alt="filtro"></a>
        <a href=""><img src="<%=request.getContextPath()%>/img/sino.svg" alt="notificação"></a>
    </li>
</ul>