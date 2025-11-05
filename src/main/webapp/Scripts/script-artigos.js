const botoes = document.querySelectorAll('.item-menu');
const artigos = document.querySelectorAll('.artigo');

botoes.forEach(botao => {
  botao.addEventListener('click', () => {

    botoes.forEach(b => b.classList.remove('ativo'));
    artigos.forEach(a => a.classList.remove('ativo'));

    botao.classList.add('ativo');

    const idArtigo = botao.getAttribute('id-artigo');
    document.getElementById(idArtigo).classList.add('ativo');
  });
});