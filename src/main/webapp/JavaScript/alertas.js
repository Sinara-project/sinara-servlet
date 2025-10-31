const alertas = [
    {
      nome: "Burguer King",
      informacoes: "A empresa Burguer King está com um recall em alguns lotes de seus produtos devido à contaminação da água com fragmentos de plástico. Os consumidores que adquiriram produtos da marca entre os dias 1 e 15 de junho devem verificar os códigos dos lotes no site oficial da empresa e entrar em contato para troca ou reembolso.",
      infoAlertas:"O alerta aconteceu no dia 21/03 às 19:23 da noite de terça-feira. Recomenda-se que os consumidores verifiquem os lotes dos produtos adquiridos entre os dias 1 e 15 de junho para garantir a segurança alimentar.",
      alerta: "Alerta 21/03"
    },
    {
      nome: "MacDonalds",
      informacoes: "A rede de fast food MacDonalds anunciou um recall voluntário de seus sorvetes devido à possível contaminação por bactérias Listeria monocytogenes. Os consumidores que compraram sorvetes entre os dias 10 e 20 de maio devem descartar os produtos e entrar em contato com o serviço de atendimento ao cliente para mais informações.",
      infoAlertas:"O alerta foi emitido no dia 15/05 às 14:45. Recomenda-se que os consumidores que adquiriram sorvetes entre os dias 10 e 20 de maio descartem os produtos imediatamente para evitar riscos à saúde.",
      alerta: "Alerta 15/05"
    },
    {
      nome: "Sinara",
      informacoes: "A empresa Sinara está realizando um recall de seus carregadores portáteis modelo X100 devido ao risco de superaquecimento e incêndio. Os consumidores que adquiriram esse modelo entre os meses de março e abril devem parar de usar o produto imediatamente e entrar em contato com a empresa para obter um substituto gratuito.",
      infoAlertas:"O alerta foi divulgado no dia 30/04 às 09:00. Recomenda-se que os consumidores que possuem o modelo X100 parem de usar o carregador imediatamente e entrem em contato com a Sinara Tech para garantir sua segurança.",
      alerta: "Alerta 30/04"
    },
    {
      nome: "Kronos",
      informacoes: "A empresa Kronos está emitindo um recall para seu modelo de smartwatch K-Active devido a falhas na bateria que podem causar superaquecimento. Os consumidores que possuem esse modelo devem interromper o uso imediatamente e entrar em contato com o suporte ao cliente para obter instruções sobre como proceder com a substituição do produto.",
      infoAlertas:"O alerta foi emitido no dia 12/02 às 11:30. Recomenda-se que os consumidores que possuem o modelo K-Active interrompam o uso do smartwatch imediatamente para evitar riscos de segurança.",
      alerta: "Alerta 12/02"
    },
    {
      nome: "CodCoz",
      informacoes: "A empresa CodCoz está realizando um recall de seus fogões elétricos modelo C-200 devido a um defeito no sistema de controle de temperatura, que pode causar superaquecimento e risco de incêndio. Os consumidores que adquiriram esse modelo entre janeiro e março devem entrar em contato com o serviço de atendimento ao cliente para obter um reparo gratuito ou substituição do produto.",
      infoAlertas:"O alerta foi divulgado no dia 05/03 às 16:00. Recomenda-se que os consumidores que possuem o modelo C-200 entrem em contato com a CodCoz para garantir sua segurança e evitar possíveis riscos de incêndio.",
      alerta: "Alerta 05/03"
    },
    {
      nome: "ScaneIA",
      informacoes: "A empresa ScaneIA está emitindo um recall para seus scanners de documentos modelo S-Scan devido a falhas no software que podem comprometer a segurança dos dados digitalizados. Os consumidores que possuem esse modelo devem atualizar o software imediatamente através do site oficial da empresa e entrar em contato com o suporte ao cliente para mais informações.",
      infoAlertas:"O alerta foi emitido no dia 18/04 às 10:15. Recomenda-se que os consumidores que possuem o modelo S-Scan atualizem o software imediatamente para garantir a segurança dos dados digitalizados.",
      alerta: "Alerta 18/04"
    }
  ];

  let index = 0;
  
  function mostrarAlertas(i) {
    const alerts = alertas[i];
    document.getElementById("informacoes-alertas").innerHTML = `
      <div id="container-geral">
          <div class="container-alerta">
              <h2 id="alerta-nome">${alerts.alerta}</h2>
              <p id="descricao-alerta">${alerts.infoAlertas}</p>
          </div>
          <div class="container-alerta">
              <h2 id="industria-nome">${alerts.nome}</h2>
              <p id="descricao-industria">${alerts.informacoes}</p>
          </div>
      </div>
    `;
  }
  
  function anterior() {
    index = (index - 1 + alertas.length) % alertas.length;
    mostrarAlertas(index);
  }
  
  function proximo() {
    index = (index + 1) % alertas.length;
    mostrarAlertas(index);
  }
  
  mostrarAlertas(index);