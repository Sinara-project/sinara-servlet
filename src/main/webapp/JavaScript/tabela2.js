document.addEventListener('DOMContentLoaded', () => {
  const btnAbrirAdicionar = document.getElementById('botao-adicionar');
  const modalAdicionar = document.getElementById('modal-func');
  const editarModal = document.getElementById('editarModal');
  const cancelarEdicao = document.getElementById('cancelarEdicao');
  const tabelaBody = document.querySelector('table tbody');

  // Abrir modal adicionar
  btnAbrirAdicionar?.addEventListener('click', () => { if(modalAdicionar) modalAdicionar.style.display='flex'; });

  // Fechar modal clicando fora
  [modalAdicionar, editarModal].forEach(modal => {
    modal?.addEventListener('click', e => { if(e.target === modal) modal.style.display='none'; });
  });

  // ESC fecha modais
  document.addEventListener('keydown', e => {
    if(e.key==='Escape'){
      if(modalAdicionar) modalAdicionar.style.display='none';
      if(editarModal) editarModal.style.display='none';
      closeAllMenus();
    }
  });

  // Mascara CPF
  const addCPF = document.getElementById('addCPF');
  addCPF?.addEventListener('input', e => {
    let v = e.target.value.replace(/\D/g,'').slice(0,11);
    if(v.length>9) v=v.replace(/(\d{3})(\d{3})(\d{3})(\d{0,2})/,'$1.$2.$3-$4');
    else if(v.length>6) v=v.replace(/(\d{3})(\d{3})(\d{0,3})/,'$1.$2.$3');
    else if(v.length>3) v=v.replace(/(\d{3})(\d{0,3})/,'$1.$2');
    e.target.value = v;
  });

  // Menus
  function closeAllMenus() {
    document.querySelectorAll('.menu-opcoes.show').forEach(m => m.classList.remove('show'));
  }

  function attachRowListeners(tr){
    const btnMenu = tr.querySelector('.opcoes-btn');
    const menu = tr.querySelector('.menu-opcoes');
    const btnEditar = tr.querySelector('.btn-editar');
    const formExcluir = tr.querySelector('form');

    btnMenu?.addEventListener('click', e => {
      e.stopPropagation();
      closeAllMenus();
      menu?.classList.toggle('show');
    });

    btnEditar?.addEventListener('click', () => {
      document.getElementById('editId').value = tr.dataset.id;
      document.getElementById('editNome').value = tr.children[2].textContent;
      document.getElementById('editEmail').value = tr.children[1].textContent;
      document.getElementById('editCargo').value = tr.children[3].textContent;
      editarModal.style.display='flex';
      closeAllMenus();
    });

    formExcluir?.addEventListener('submit', e => {
      if(!confirm(`Deseja realmente excluir o funcionário #${tr.dataset.id}?`)) e.preventDefault();
    });
  }

  // Aplica a cada TR
  document.querySelectorAll('table tbody tr').forEach(attachRowListeners);
  document.addEventListener('click', e => { if(!e.target.closest('.menu-opcoes') && !e.target.closest('.opcoes-btn')) closeAllMenus(); });

  cancelarEdicao?.addEventListener('click', () => { if(editarModal) editarModal.style.display='none'; });
});