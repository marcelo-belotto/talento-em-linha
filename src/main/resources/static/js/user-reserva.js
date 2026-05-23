// const carrinhoReservas = document.querySelector("#carrinhoReservas");
// const produtosDisponiveis = document.querySelector("#produtosDisponiveis");

// function reservarItem(item) {
//   let id = item.cells[4].children[0].id;
//   carrinhoReservas.innerHTML += `<tr id=${item.id}>
//         <td data-label="# ID">${id}</td>
//         <td data-label="Brinde">${item.cells[0].innerHTML}</td>
//         <td data-label="Pontos">${item.cells[2].innerHTML}</td>
//         <td data-label="Remover"><input type="button" value="Remover" 
//         onclick="removerItem(${item.id})"></td>
//     </tr>`;
//     console.log(item);
//     produtosDisponiveis.removeChild(item);
// }

// function removerItem(itemARemover) {
// //    carrinhoReservas.removeChild(itemARemover);
//    console.log(itemARemover);
// }
/**
 * user-reserva.js
 * Gerencia o catálogo de brindes e o carrinho de reservas.
 * - Move itens entre a tabela de produtos e o carrinho
 * - Valida saldo de pontos disponíveis
 * - Envia POST para a API ao confirmar reserva
 */

(() => {
  // ── Helpers ────────────────────────────────────────────────────────────────

  /** Lê o saldo disponível exibido na página (elemento <strong> dentro do <p> de saldo). */
  function getSaldoDisponivel() {
    const el = document.querySelector('.container-cabecalho p strong');
    return el ? parseInt(el.textContent.replace(/\D/g, ''), 10) || 0 : 0;
  }

  /** Lê o total de pontos já no carrinho. */
  function getTotalCarrinho() {
    return parseInt(document.getElementById('totalPontos').textContent, 10) || 0;
  }

  /** Atualiza os totalizadores no rodapé do carrinho. */
  function atualizarTotais() {
    let total = 0;
    document
      .querySelectorAll('#carrinhoReservas tr')
      .forEach(tr => {
        const pts = parseInt(tr.dataset.pontos, 10) || 0;
        const qty = parseInt(tr.dataset.quantidade, 10) || 1;
        total += pts * qty;
      });

    document.getElementById('totalPontos').textContent = total;
    document.getElementById('saldoFinal').textContent = getSaldoDisponivel() - total;
  }

  /** Exibe uma mensagem de erro temporária na tela. */
  function exibirErro(msg) {
    let toast = document.getElementById('reserva-toast');
    if (!toast) {
      toast = document.createElement('div');
      toast.id = 'reserva-toast';
      Object.assign(toast.style, {
        position: 'fixed',
        bottom: '24px',
        right: '24px',
        background: '#c0392b',
        color: '#fff',
        padding: '12px 20px',
        borderRadius: '6px',
        fontWeight: '600',
        zIndex: '9999',
        boxShadow: '0 4px 12px rgba(0,0,0,.3)',
        transition: 'opacity .3s',
      });
      document.body.appendChild(toast);
    }
    toast.textContent = msg;
    toast.style.opacity = '1';
    clearTimeout(toast._timeout);
    toast._timeout = setTimeout(() => { toast.style.opacity = '0'; }, 3500);
  }

  // ── Reservar item (produtos → carrinho) ────────────────────────────────────

  /**
   * Chamada pelo onclick inline: reservarItem(tr)
   * @param {HTMLTableRowElement} trProduto - linha da tabela de produtos
   */
  window.reservarItem = function (trProduto) {
    if (!trProduto) return;

    // Extrai dados da linha de produto
    const cells    = trProduto.querySelectorAll('td');
    const nome     = cells[0]?.textContent.trim() ?? '';
    const pontos   = parseInt(cells[2]?.textContent.trim(), 10) || 0;
    const estoque  = parseInt(cells[3]?.textContent.trim(), 10) || 0;
    const btn      = trProduto.querySelector('input[type="button"]');
    const id       = btn?.id ?? trProduto.id.replace('prod', '');

    // Valida estoque
    if (estoque <= 0) {
      exibirErro(`"${nome}" está sem estoque.`);
      return;
    }

    // Verifica se já existe no carrinho → incrementa quantidade
    const trExistente = document.querySelector(`#carrinhoReservas tr[data-id="${id}"]`);
    if (trExistente) {
      const qtdAtual     = parseInt(trExistente.dataset.quantidade, 10) || 1;
      const estoqueAtual = parseInt(trExistente.dataset.estoqueMax, 10) || estoque;

      if (qtdAtual >= estoqueAtual) {
        exibirErro(`Quantidade máxima de "${nome}" já adicionada (${estoqueAtual} un.).`);
        return;
      }

      // Valida saldo
      if (getTotalCarrinho() + pontos > getSaldoDisponivel()) {
        exibirErro('Saldo de pontos insuficiente para adicionar mais este brinde.');
        return;
      }

      const novaQtd = qtdAtual + 1;
      trExistente.dataset.quantidade = novaQtd;
      trExistente.querySelector('.carrinho-qtd').textContent = novaQtd;

      // Atualiza estoque visível na tabela de produtos
      cells[3].textContent = estoqueAtual - novaQtd;

      atualizarTotais();
      return;
    }

    // Valida saldo para novo item
    if (getTotalCarrinho() + pontos > getSaldoDisponivel()) {
      exibirErro('Saldo de pontos insuficiente para reservar este brinde.');
      return;
    }

    // Cria linha no carrinho
    const trCarrinho = document.createElement('tr');
    trCarrinho.dataset.id         = id;
    trCarrinho.dataset.pontos     = pontos;
    trCarrinho.dataset.quantidade = 1;
    trCarrinho.dataset.estoqueMax = estoque;

    trCarrinho.innerHTML = `
      <td data-label="# ID">${id}</td>
      <td data-label="Brinde">${nome}</td>
      <td data-label="Pontos">
        <span class="carrinho-qtd">1</span> × ${pontos} pts
      </td>
      <td>
        <input type="button" value="Remover"
          onclick="removerItem(this.closest('tr'))">
      </td>
    `;

    document.getElementById('carrinhoReservas').appendChild(trCarrinho);

    // Reduz estoque visível (1 unidade reservada)
    cells[3].textContent = estoque - 1;

    atualizarTotais();
  };

  // ── Remover item (carrinho → devolve ao estoque visual) ────────────────────

  /**
   * Chamada pelo botão "Remover" gerado dinamicamente.
   * @param {HTMLTableRowElement} trCarrinho - linha do carrinho
   */
  window.removerItem = function (trCarrinho) {
    if (!trCarrinho) return;

    const id       = trCarrinho.dataset.id;
    const pontos   = parseInt(trCarrinho.dataset.pontos, 10)     || 0;
    const quantidade = parseInt(trCarrinho.dataset.quantidade, 10) || 1;

    // Devolve estoque na tabela de produtos
    const trProduto = document.querySelector(`tr#prod${id}`);
    if (trProduto) {
      const tdEstoque = trProduto.querySelectorAll('td')[3];
      if (tdEstoque) {
        const estoqueAtual = parseInt(tdEstoque.textContent, 10) || 0;
        tdEstoque.textContent = estoqueAtual + quantidade;
      }
    }

    trCarrinho.remove();
    atualizarTotais();
  };

  // ── Confirmar Reserva (POST para API) ──────────────────────────────────────

  const form = document.querySelector('form.container-brindes');
  if (form) {
    form.addEventListener('submit', async (e) => {
      e.preventDefault();

      const linhas = document.querySelectorAll('#carrinhoReservas tr');
      if (linhas.length === 0) {
        exibirErro('Adicione ao menos um brinde ao carrinho antes de confirmar.');
        return;
      }

      // Monta payload
      const payload = Array.from(linhas).map(tr => ({
        idProduto: parseInt(tr.dataset.id, 10),
        quantidade: parseInt(tr.dataset.quantidade, 10) || 1,
      }));

      // Botão: feedback visual durante envio
      const btnConfirmar = form.querySelector('button[type="submit"]');
      const textoOriginal = btnConfirmar.textContent;
      btnConfirmar.disabled = true;
      btnConfirmar.textContent = 'Enviando…';

      try {
        const response = await fetch('/api/v1/reserva/10000001', {   // ← ajuste a URL conforme sua API
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(payload),
        });

        if (!response.ok) {
          const erro = await response.text();
          throw new Error(erro || `HTTP ${response.status}`);
        }

        // Sucesso: redireciona ou exibe confirmação
        // Descomente a linha abaixo para redirecionar após sucesso:
        // window.location.href = '/index';

        alert('Reserva confirmada com sucesso!');
        // Limpa carrinho após confirmação
        document.getElementById('carrinhoReservas').innerHTML = '';
        atualizarTotais();

      } catch (err) {
        exibirErro(`Erro ao confirmar reserva: ${err.message}`);
      } finally {
        btnConfirmar.disabled = false;
        btnConfirmar.textContent = textoOriginal;
      }
    });
  }

  // ── Init: calcula saldo inicial ────────────────────────────────────────────
  atualizarTotais();

})();