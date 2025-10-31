const botaoAbrir = document.querySelector('#botao-adicionar');
const modalFunc = document.getElementById('modal-func');
 
function abrirModal() {
  modalFunc.style.display = 'flex';
  localStorage.setItem('modalAberto', 'true');
  preencherCampos();
}
 
function fecharModal() {
  modalFunc.style.display = 'none';
  localStorage.removeItem('modalAberto');
}
 
if (botaoAbrir) {
  botaoAbrir.addEventListener('click', abrirModal);
}
 
window.addEventListener('click', (e) => {
  if (e.target === modalFunc) {
    fecharModal();
  }
});
 
function preencherCampos() {
  const inputs = modalFunc.querySelectorAll('input');
  const dados = {
    nome: '',
    email: '',
  };
 
  if (inputs.length >= 4) {
    inputs[0].value = dados.nome;
    inputs[1].value = dados.email;
  }
 
  const botaoAdicionar = modalFunc.querySelector('#btnAdicionarFunc');
  if (botaoAdicionar) {
    botaoAdicionar.addEventListener('click', () => {
 
      alert('Administrador adicionado com sucesso!');
 
      fecharModal();
    });
  }
}
 
window.addEventListener('DOMContentLoaded', () => {
  const modalAberto = localStorage.getItem('modalAberto');
  if (modalAberto === 'true') {
    abrirModal();
  }
});
 