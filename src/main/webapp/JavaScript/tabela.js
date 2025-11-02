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

// Modal de edição
const modal = document.getElementById('modal-editar');
const inputId = document.getElementById('id-editar');       // campo hidden para ID
const inputNome = document.getElementById('nome-editar');
const inputEmail = document.getElementById('email-editar');
const inputCargo = document.getElementById('cargo-editar'); // novo campo para cargo
let linhaSelecionada = null;

// Abrir modal e preencher campos
document.querySelectorAll('.editar').forEach(btn => {
  btn.addEventListener('click', e => {
    const linha = e.target.closest('tr');
    linhaSelecionada = linha;

    inputId.value = linha.children[0].textContent;     // ID
    inputEmail.value = linha.children[1].textContent;  // Email
    inputNome.value = linha.children[2].textContent;   // Nome
    inputCargo.value = linha.children[3].textContent;  // Cargo

    modal.style.display = 'flex';
  });
});

// Salvar edição
document.getElementById('salvar-editar').addEventListener('click', () => {
  if (linhaSelecionada) {
    linhaSelecionada.children[1].textContent = inputEmail.value;
    linhaSelecionada.children[2].textContent = inputNome.value;
    linhaSelecionada.children[3].textContent = inputCargo.value;
  }
  modal.style.display = 'none';
});

// Cancelar edição
document.getElementById('cancelar-editar').addEventListener('click', () => {
  modal.style.display = 'none';
});

// Excluir administrador
document.querySelectorAll('.excluir').forEach(btn => {
  btn.addEventListener('click', e => {
    if (confirm('Deseja realmente excluir este administrador?')) {
      e.target.closest('tr').remove();
    }
  });
});
