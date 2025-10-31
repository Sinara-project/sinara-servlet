<%--
  Created by IntelliJ IDEA.
  User: pedrovicente-ieg
  Date: 29/10/2025
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Adicionar administrador</h1>
<form id="adminForm" action="<%= request.getContextPath() %>/administracao?action=adicionar" method="post">
    <label for="cpf">Cpf:</label>
    <input type="text" name="cpf" id="cpf" placeholder="xxx.xxx.xxx-xx" maxlength="14">
    <br><br>
    <label for="nome">Nome:</label>
    <input type="text" name="nome" id="nome">
    <br><br>
    <label for="email">Email:</label>
    <input type="email" name="email" id="email">
    <br><br>
    <label for="cargo">Cargo:</label>
    <input type="text" name="cargo" id="cargo">
    <br><br>
    <label for="senha">Senha:</label>
    <input type="password" name="senha" id="senha">
    <br><br>
    <button type="submit">Enviar</button>
</form>

<script>
    const cpfInput = document.getElementById('cpf');
    const form = document.getElementById('adminForm');

    // Formatação automática do CPF
    cpfInput.addEventListener('input', function() {
        let value = cpfInput.value.replace(/\D/g, ''); // remove tudo que não é número

        if (value.length > 11) value = value.slice(0, 11); // limita a 11 dígitos

        // Formata: xxx.xxx.xxx-xx
        value = value.replace(/(\d{3})(\d)/, '$1.$2');
        value = value.replace(/(\d{3})(\d)/, '$1.$2');
        value = value.replace(/(\d{3})(\d{1,2})$/, '$1-$2');

        cpfInput.value = value;
    });

    // Validação ao enviar o formulário
    form.addEventListener('submit', function(e) {
        const cpfRegex = /^\d{3}\.\d{3}\.\d{3}-\d{2}$/;
        if (!cpfRegex.test(cpfInput.value)) {
            e.preventDefault();
            alert('CPF inválido! Use o formato xxx.xxx.xxx-xx');
            cpfInput.focus();
        }
    });
</script>
</body>
</html>
