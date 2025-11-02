const tabela = document.getElementById("tabela");
const inputValor = document.getElementById("valorFiltro");

function normalizar(str) {
    return String(str || "").normalize("NFD").replace(/[\u0300-\u036f]/g, "").toLowerCase();
}

function filtrar() {
    const filtro = normalizar(inputValor.value);
    const linhas = tabela.getElementsByTagName("tr");

    for (let i = 1; i < linhas.length; i++) {
        const col = linhas[i].getElementsByTagName("td")[1]; // primeira coluna (nome)
        linhas[i].style.display = (col && normalizar(col.textContent).includes(filtro)) ? "" : "none";
    }
}

inputValor.addEventListener("keyup", filtrar);