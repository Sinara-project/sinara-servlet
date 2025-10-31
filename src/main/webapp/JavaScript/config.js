const empresas = [
    {
        empresa1: "Sinara",
        info1: "Empresa de cosméticos",
        empresa2: "Kronos",
        info2: "Empresa alimentícia",
        empresa3: "ScaneIA",
        info3: "Empresa de borracharia" ,
        empresa4: "CodCoz",
        info4: "Empresa de bebidas alcoólica"
    },
    {
      empresa1: "Marabraz",
      info1: "Empresa de móveis",
      empresa2: "Acre",
      info2: "Empresa de eletrônicos",
      empresa3: "Shein",
      info3: "Mercado virtual", 
      empresa4: "Shopee",
      info4: "Xin xon xin xin xin🏮🐉🐼🥢🥠"
    },

    {
      empresa1: "Cacau Show",
      info1: "Empresa alimentícia",
      empresa2: "EcoLen",
      info2: "Empresa alimentícia",
      empresa3: "Mano Brown",
      info3: "Nego drama",
      empresa4: "Ronaldo sedutor",
      info4: "Olha patrai patrai"
    }

  ];

  let index = 0;
  
  function mostrarEmpresas(i) {
    const empresa = empresas[i];
    document.getElementById("container-empresas").innerHTML = `
        <div class="container-empresa">
            <div class="container">
                <h2 id="alerta-nome">${empresa.empresa1}</h2>
                <p id="descricao-alerta">${empresa.info1}</p>
            </div>
            <div class="container">
                <h2 id="alerta-nome">${empresa.empresa2}</h2>
                <p id="descricao-alerta">${empresa.info2}</p>
            </div>
        </div>
        <div class="container-empresa">
            <div class="container">
                <h2 id="alerta-nome">${empresa.empresa3}</h2>
                <p id="descricao-alerta">${empresa.info3}</p>
            </div>
            <div class="container">
                <h2 id="alerta-nome">${empresa.empresa4}</h2>
                <p id="descricao-alerta">${empresa.info4}</p>
            </div>
        </div>
        
    `;
  }
  
  function anterior() {
    index = (index - 1 + empresas.length) % empresas.length;
    mostrarEmpresas(index);
  }
  
  function proximo() {
    index = (index + 1) % empresas.length;
    mostrarEmpresas(index);
  }
  
  mostrarEmpresas(index);

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
 