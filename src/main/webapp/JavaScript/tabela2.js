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
  const editCargo = document.getElementById('editCargo');

  const tabelaBody = document.querySelector('table tbody');

  // Abre/fecha modal adicionar
  btnAbrirAdicionar && btnAbrirAdicionar.addEventListener('click', () => {
    openModal(modalAdicionar);
  });

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

  // Evento para enviar/adicionar novo (cria linha na tabela)
  btnEnviarAdicionar && btnEnviarAdicionar.addEventListener('click', () => {
    // pegar valores dos inputs do modal adicionar
    const nome = document.getElementById('addNome').value.trim();
    const ramo = document.getElementById('addRamo').value.trim();
    const email = document.getElementById('addEmail').value.trim();
    const tel = document.getElementById('addTel').value.trim();
    const cnpj = document.getElementById('addCNPJ').value.trim();
    const ativo = document.getElementById('addAtivo').checked;
    const inicio = document.getElementById('addInicio').value;
    const plano = document.getElementById('addPlano').value;

    if (!nome || !email) {
      alert('Por favor preencha pelo menos Nome e Email.');
      return;
    }

    // gerar novo ID (pegando último ID da tabela)
    let novoId = 1;
    const linhas = tabelaBody.querySelectorAll('tr');
    if (linhas.length) {
      const ultimo = linhas[linhas.length - 1].querySelector('td');
      const ultimoId = parseInt(ultimo?.textContent || '0', 10);
      novoId = (isNaN(ultimoId) ? linhas.length + 1 : ultimoId + 1);
    }

    // montar nova linha
    const tr = document.createElement('tr');
    tr.setAttribute('data-id', novoId);
    tr.setAttribute('data-cargo', ramo || '');
    tr.setAttribute('data-empresa', nome);
    tr.setAttribute('data-cpf', cnpj || '');

    tr.innerHTML = `
      <td>${novoId}</td>
      <td>${sanitize(email)}</td>
      <td>${sanitize(nome)}</td>
      <td>${sanitize(ramo)}</td>
      <td>${sanitize(nome)}</td>
      <td>${maskCPF(cnpj)}</td>
      <td>
        <div class="menu-container">
          <button class="opcoes-btn">
            <img src="/img/tres_pontinhos.svg" alt="menu">
          </button>
          <div class="menu-opcoes">
            <button class="btn-editar">Editar</button>
            <button class="btn-excluir">Excluir</button>
          </div>
        </div>
      </td>
    `;

    tabelaBody.appendChild(tr);
    attachRowListeners(tr); // ligar eventos da linha nova
    closeModal(modalAdicionar);
    clearAddModal();
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
  function clearAddModal() {
    document.getElementById('addNome').value = '';
    document.getElementById('addRamo').value = '';
    document.getElementById('addEmail').value = '';
    document.getElementById('addTel').value = '';
    document.getElementById('addCNPJ').value = '';
    document.getElementById('addAtivo').checked = false;
    document.getElementById('addInicio').value = '';
    document.getElementById('addPlano').value = 'anual';
  }
  function sanitize(str) {
    return String(str).replace(/</g, '&lt;').replace(/>/g, '&gt;');
  }
  function maskCPF(cpf) {
    if (!cpf) return '';
    // se já tiver máscara simples, retorna
    const cleaned = cpf.replace(/\D/g, '');
    if (!cleaned) return '';
    // exibir parcialmente: primeiros 2 e últimos 2
    const start = cleaned.slice(0,2);
    const end = cleaned.slice(-2);
    return `${start}***${end}`;
  }

  // Ligar eventos já existentes nas linhas
  document.querySelectorAll('table tbody tr').forEach(tr => attachRowListeners(tr));

  function attachRowListeners(tr) {
    const btn = tr.querySelector('.opcoes-btn');
    const menu = tr.querySelector('.menu-opcoes');
    const btnEditar = tr.querySelector('.btn-editar');
    const btnExcluir = tr.querySelector('.btn-excluir');

    btn && btn.addEventListener('click', (e) => {
      e.stopPropagation();
      closeAllMenus();
      menu && menu.classList.toggle('show');
    });

    btnEditar && btnEditar.addEventListener('click', () => {
      // preencher modal editar com os dados da linha
      const id = tr.getAttribute('data-id');
      const nome = tr.children[2].textContent.trim();
      const email = tr.children[1].textContent.trim();
      const cargo = tr.children[3].textContent.trim();

      editId.value = id;
      editNome.value = nome;
      editEmail.value = email;
      editCargo.value = cargo;

      openModal(editarModal);
      closeAllMenus();
    });

    btnExcluir && btnExcluir.addEventListener('click', () => {
      if (confirm('Deseja realmente excluir este registro?')) {
        tr.remove();
      }
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
