// MODAIS
const botaoAbrir = document.querySelector('#botao-adicionar');
const modalFunc = document.getElementById('modal-func');
const modalEditar = document.getElementById('modal-editar');

// ABRIR MODAL DE ADIÇÃO
botaoAbrir.addEventListener('click', () => {
  modalFunc.style.display = 'flex';
});

// CANCELAR MODAIS
document.querySelector('#cancelar-adicionar').addEventListener('click', () => modalFunc.style.display = 'none');
document.querySelector('#cancelar-editar').addEventListener('click', () => modalEditar.style.display = 'none');

// FECHAR AO CLICAR FORA
window.addEventListener('click', (e) => {
  if (e.target === modalFunc) modalFunc.style.display = 'none';
  if (e.target === modalEditar) modalEditar.style.display = 'none';
});

// MENU TRÊS PONTINHOS
document.querySelectorAll('.menu-btn').forEach(btn => {
  btn.addEventListener('click', e => {
    e.stopPropagation();
    const menu = btn.nextElementSibling;
    document.querySelectorAll('.menu-opcoes').forEach(m => {
      if (m !== menu) m.style.display = 'none';
    });
    menu.style.display = menu.style.display === 'flex' ? 'none' : 'flex';
  });
});

// FECHAR MENU AO CLICAR FORA
window.addEventListener('click', () => {
  document.querySelectorAll('.menu-opcoes').forEach(m => m.style.display = 'none');
});

// EDITAR ADMIN
document.querySelectorAll('.editar').forEach((btn, index) => {
  btn.addEventListener('click', () => {
    const admin = document.querySelectorAll('tbody tr')[index];
    document.getElementById('id-editar').value = admin.children[0].innerText;
    document.getElementById('nome-editar').value = admin.children[2].innerText;
    document.getElementById('email-editar').value = admin.children[1].innerText;
    modalEditar.style.display = 'flex';
  });
});

// FORMULÁRIO ADICIONAR COM VALIDAÇÃO CPF
const cpfInput = document.getElementById('cpf');
const formAdicionar = document.getElementById('modal-func');

cpfInput.addEventListener('input', () => {
  let value = cpfInput.value.replace(/\D/g, '');
  if (value.length > 11) value = value.slice(0, 11);
  value = value.replace(/(\d{3})(\d)/, '$1.$2');
  value = value.replace(/(\d{3})(\d)/, '$1.$2');
  value = value.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
  cpfInput.value = value;
});

formAdicionar.addEventListener('submit', e => {
  const cpfRegex = /^\d{3}\.\d{3}\.\d{3}-\d{2}$/;
  if (!cpfRegex.test(cpfInput.value)) {
    e.preventDefault();
    alert('CPF inválido! Use o formato xxx.xxx.xxx-xx');
    cpfInput.focus();
  }
});
