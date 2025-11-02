document.addEventListener('DOMContentLoaded', () => {
  // elementos principais
  const btnAbrirAdicionar = document.getElementById('botao-adicionar'); // botão no topo
  const modalAdicionar = document.getElementById('modal-func');
  const btnEnviarAdicionar = document.getElementById('btnAdicionarEnviar');

  const editarModal = document.getElementById('editarModal');
  const salvarEdicao = document.getElementById('salvarEdicao');
  const cancelarEdicao = document.getElementById('cancelarEdicao');

  const editId = document.getElementById('editId');
  const editNome = document.getElementById('editNome');
  const editEmail = document.getElementById('editEmail');
  const editRamo = document.getElementById('editRamo');
  const editTelefone = document.getElementById('editTelefone');
  const editStatus = document.getElementById('editStatus');

  const tabelaBody = document.querySelector('table tbody');

  // Fecha modal ao clicar fora do conteúdo ou ao apertar ESC
  [modalAdicionar, editarModal].forEach(modal => {
    if (!modal) return;
    modal.addEventListener('click', (e) => {
      if (e.target === modal) closeModal(modal);
    });
  });
  document.addEventListener('keydown', (e) => {
    if (e.key === 'Escape') {
      closeModal(modalAdicionar);
      closeModal(editarModal);
      closeAllMenus();
    }
  });

  // Funções utilitárias
  function openModal(modal) {
    if (!modal) return;
    modal.style.display = 'flex';
    modal.setAttribute('aria-hidden','false');
  }
  function closeModal(modal) {
    if (!modal) return;
    modal.style.display = 'none';
    modal.setAttribute('aria-hidden','true');
  }

  // Ligar eventos já existentes nas linhas
  document.querySelectorAll('table tbody tr').forEach(tr => attachRowListeners(tr));

  function attachRowListeners(tr) {
    const btn = tr.querySelector('.opcoes-btn');
    const menu = tr.querySelector('.menu-opcoes');
    const btnEditar = tr.querySelector('.btn-editar');

    btn && btn.addEventListener('click', (e) => {
      e.stopPropagation();
      closeAllMenus();
      menu && menu.classList.toggle('show');
    });

    btnEditar && btnEditar.addEventListener('click', () => {
      // preencher modal editar com os dados da linha
      const id = tr.getAttribute('data-id');
      const nome = tr.children[1].textContent.trim();
      const email = tr.children[2].textContent.trim();
      const ramo = tr.children[4].textContent.trim();
      const telefone = tr.children[3].textContent.trim();
      const status = tr.children[5].textContent.trim()==="true";

      editId.value = id;
      editNome.value = nome;
      editEmail.value = email;
      editRamo.value = ramo;
      editTelefone.value = telefone;
      editStatus.value = status;

      openModal(editarModal);
      closeAllMenus();
    });
  }

  function closeAllMenus() {
    document.querySelectorAll('.menu-opcoes.show').forEach(m => m.classList.remove('show'));
  }

  // fechar menus se clicar fora
  document.addEventListener('click', (e) => {
    if (!e.target.closest('.menu-opcoes') && !e.target.closest('.opcoes-btn')) {
      closeAllMenus();
    }
  });

  // salvar edição (pega editId e atualiza a linha correspondente)
  salvarEdicao && salvarEdicao.addEventListener('click', () => {
    const id = editId.value;
    if (!id) return;
    const tr = tabelaBody.querySelector(`tr[data-id="${id}"]`);
    if (!tr) return;

    tr.children[2].textContent = editNome.value.trim() || tr.children[2].textContent;
    tr.children[1].textContent = editEmail.value.trim() || tr.children[1].textContent;
    tr.children[3].textContent = editCargo.value.trim() || tr.children[3].textContent;

    // atualizar atributos data se quiser
    tr.setAttribute('data-cargo', editCargo.value.trim());
    closeModal(editarModal);
  });
  cancelarEdicao && cancelarEdicao.addEventListener('click', () => {
    closeModal(editarModal);
  });
});