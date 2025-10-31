<%@ page import="com.sinara.model.Empresa" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR" class="labubu">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Adicionar Operário</title>

  <script>
    document.addEventListener("DOMContentLoaded", function () {
      const cpfInput = document.getElementById("cpf");
      const form = document.querySelector("form");

      // Máscara automática de CPF
      cpfInput.addEventListener("input", function (e) {
        let value = e.target.value.replace(/\D/g, ""); // Remove tudo que não é número
        if (value.length > 11) value = value.slice(0, 11); // Limita a 11 dígitos

        // Adiciona os pontos e traço automaticamente
        if (value.length > 9) {
          value = value.replace(/(\d{3})(\d{3})(\d{3})(\d{0,2})/, "$1.$2.$3-$4");
        } else if (value.length > 6) {
          value = value.replace(/(\d{3})(\d{3})(\d{0,3})/, "$1.$2.$3");
        } else if (value.length > 3) {
          value = value.replace(/(\d{3})(\d{0,3})/, "$1.$2");
        }
        e.target.value = value;
      });

      // Validação antes do envio
      form.addEventListener("submit", function (e) {
        const cpfValue = cpfInput.value.trim();
        const cpfRegex = /^\d{3}\.\d{3}\.\d{3}-\d{2}$/;

        if (!cpfRegex.test(cpfValue)) {
          e.preventDefault(); // Impede o envio
          alert("Por favor, insira um CPF válido no formato 000.000.000-00.");
          cpfInput.focus();
        }
      });
    });
  </script>
</head>

<body>
<h1>Adicionar operário</h1>
<form method="post" action="<%= request.getContextPath()%>/operarios?action=inserir">
  <label for="cpf">CPF:</label>
  <input type="text" name="cpf" id="cpf" maxlength="14" required>
  <br><br>

  <label for="nome">Nome:</label>
  <input type="text" name="nome" id="nome" required>
  <br><br>

  <label for="email">Email:</label>
  <input type="email" name="email" id="email" required>
  <br><br>

  <label for="cargo">Cargo:</label>
  <input type="text" name="cargo" id="cargo">
  <br><br>

  <div class="datalist-demo">
    <label for="empresa-datalist" class="small">Empresa:</label>
    <input list="empresas-list" id="empresa-datalist" name="empresa" class="search-input"
           placeholder="Digite o nome...">
    <datalist id="empresas-list">
      <%
        List<Empresa> listaEmpresas = (List<Empresa>) request.getAttribute("empresas");
        for (Empresa emp : listaEmpresas) {
      %>
      <option value="<%= emp.getNome() %>"></option>
      <%
        }
      %>
    </datalist>
  </div>
  <br><br>

  <label for="senha">Senha:</label>
  <input type="password" name="senha" id="senha" required>
  <br><br>

  <label for="horarioTrabalho">Horário de Trabalho:</label>
  <input type="time" name="horarioTrabalho" id="horarioTrabalho" required>
  <br><br>

  <button type="submit">Enviar</button>
</form>
</body>

</html>
