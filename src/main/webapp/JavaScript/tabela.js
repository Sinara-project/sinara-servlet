document.querySelectorAll('.menu-btn').forEach(btn => {
  btn.addEventListener('click', e => {
    e.stopPropagation(); // evita fechar imediatamente
    const menu = btn.nextElementSibling;
    document.querySelectorAll('.menu-opcoes').forEach(m => {
      if (m !== menu) m.style.display = 'none';
    });
    menu.style.display = menu.style.display === 'block' ? 'none' : 'block';
  });
});

document.addEventListener('click', () => {
  document.querySelectorAll('.menu-opcoes').forEach(m => m.style.display = 'none');
});

const modal = document.getElementById('modal-editar');
const inputNome = document.getElementById('nome-editar');
const inputEmail = document.getElementById('email-editar');
let linhaSelecionada = null;

document.querySelectorAll('.editar').forEach(btn => {
  btn.addEventListener('click', e => {
    const linha = e.target.closest('tr');
    linhaSelecionada = linha;
    inputNome.value = linha.children[2].textContent;
    inputEmail.value = linha.children[1].textContent;
    modal.style.display = 'flex';
  });
});

document.getElementById('salvar-editar').addEventListener('click', () => {
  if (linhaSelecionada) {
    linhaSelecionada.children[2].textContent = inputNome.value;
    linhaSelecionada.children[1].textContent = inputEmail.value;
  }
  modal.style.display = 'none';
});

document.getElementById('cancelar-editar').addEventListener('click', () => {
  modal.style.display = 'none';
});

document.querySelectorAll('.excluir').forEach(btn => {
  btn.addEventListener('click', e => {
    if (confirm('Deseja realmente excluir este administrador?')) {
      e.target.closest('tr').remove();
    }
  });
});