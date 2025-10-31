<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside>
    <img id="logo" src="<%=request.getContextPath()%>/img/sinara.svg" alt="">
    <div id="container-aside">
        <ul id="lista-aside">
            <li>
                <a href="<%=request.getContextPath()%>/arearestrita?page=funcionarios" class="container-botoes" id="funcionarios">
                    <img src="<%=request.getContextPath()%>/img/passoinhaBusca.svg" alt="">
                    <button class="botoes-aside">Funcionários</button>
                </a>
            </li>

            <li>
                <a href="<%=request.getContextPath()%>/arearestrita?page=analise" class="container-botoes" id="analise">
                    <img src="<%=request.getContextPath()%>/img/formulariozinho.svg" alt="">
                    <button class="botoes-aside">Análise</button>
                </a> 
            </li>

            <li>
                <a href="<%=request.getContextPath()%>/arearestrita?page=alertas" class="container-botoes" id="alertas">
                    <img src="<%=request.getContextPath()%>/img/alertazinho.svg" alt="">
                    <button class="botoes-aside">Alertas Emitidos</button>
                </a>
            </li>

            <li>
                <a href="<%=request.getContextPath()%>/arearestrita?page=empresas" class="container-botoes" id="configs">
                    <img src="<%=request.getContextPath()%>/img/configuracoes.svg" alt="">
                    <button class="botoes-aside">Configurações da<br>Indústria</button>
                </a>
            </li>

            <li>
                <a href="<%=request.getContextPath()%>/arearestrita?page=admin" class="container-botoes" id="admins">
                    <img src="<%=request.getContextPath()%>/img/admin.svg" alt="">
                    <button class="botoes-aside">Administradores</button>
                </a>
            </li>
        </ul>
    </div>
</aside>