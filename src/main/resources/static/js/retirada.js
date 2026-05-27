const formulario = document.getElementById("formBuscaUsuario");

formulario.addEventListener("submit", async (event) => {
  event.preventDefault(); // Impede o recarregamento da página
  const npFuncionario = document.getElementById("id-func").value;
  const painel = document.getElementById("painelUsuario");

  try {
    const resposta = await fetch(
      `http://localhost:8080/api/v1/${npFuncionario}/reservas`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
      },
    );

    if (resposta.ok) {
      const dados = await resposta.json();
      //   window.location.reload();
      painel.style.border = "1px solid var(--border)";
      painel.innerHTML = `
      <div class="panel-header">
      <span class="panel-title">
      <span style="background:var(--accent2);color:#0a0a0a;font-family:'Space Mono',monospace;font-size:0.65rem;padding:3px 8px;border-radius:3px;margin-right:10px;">2</span>
      Reservas Pendentes — #${dados.np}
      </span>
      </div>
      <div style="padding:18px 24px;border-bottom:1px solid var(--border);display:grid;grid-template-columns:1fr 1fr 1fr;gap:20px;">
      <div>
      <div style="font-family:'Space Mono',monospace;font-size:0.62rem;letter-spacing:2px;text-transform:uppercase;color:var(--muted2);margin-bottom:5px;">Nome</div>
      <div style="color:var(--text);font-size:0.9rem;font-weight:500;">${dados.nome}</div>
      </div>
        <div>
        <div style="font-family:'Space Mono',monospace;font-size:0.62rem;letter-spacing:2px;text-transform:uppercase;color:var(--muted2);margin-bottom:5px;">Cargo</div>
        <div style="color:var(--text);font-size:0.9rem;">${dados.cargo}</div>
        </div>
        <div>
        <div style="font-family:'Space Mono',monospace;font-size:0.62rem;letter-spacing:2px;text-transform:uppercase;color:var(--muted2);margin-bottom:5px;">Setor</div>
        <div style="color:var(--text);font-size:0.9rem;">${dados.setor}</div>
        </div>
        </div>
        <div class="table-wrap">
        <table class="data-table">
        <thead>
        <tr>
        <th>Nº Reserva</th>
        <th>Produto</th>
        <th>Pontos Debitados</th>
        <th>Data Reserva</th>
        <th>Status</th>
        <th>Selecionar</th>
        </tr>
        </thead>
        <tbody id="tableProdutosFuncionario">
        </tbody>
        </table>
        </div>
        <div style="padding:16px 24px;border-top:1px solid var(--border);">
        <div class="alert alert-warning" style="margin-bottom:16px;">
        <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="12"/><line x1="12" y1="16" x2="12.01" y2="16"/></svg>
        Confirme a identidade do funcionário antes de efetuar a entrega. Peça o crachá ou documento com o número de registro.
        </div>
        </div>
        `;
      const tabelaProdutos = document.getElementById(
        "tableProdutosFuncionario",
      );
      
      if (dados.reservas.length !== 0) {
        dados.reservas.forEach((reserva) => {
          tabelaProdutos.innerHTML += `<tr>
          <td style="font-family:'Space Mono',monospace;font-size:0.75rem;color:var(--muted2);">${reserva.id.toString().padStart(5, "0")}</td>
          <td><strong>${reserva.produto.nome}</strong><br><span style="font-size:0.78rem;color:var(--muted2);">${reserva.produto.descricao}</span></td>
          <td style="font-family:'Space Mono',monospace;">${reserva.produto.pontos} pts</td>
          <td style="font-family:'Space Mono',monospace;font-size:0.8rem;">${reserva.dataReserva.toString().substring(0, 10)}</td>
          <td><span class="badge badge-pendente">${reserva.status}</span></td>
          <td>
          <input type="checkbox" id="sel-r031" name="reserva" value="R-2026-031" style="accent-color:var(--accent);width:16px;height:16px;cursor:pointer;">
          </td>
          </tr>`;
        });
        const painelConfirmacao = document.getElementById("painelConfirmacao");
        painelConfirmacao.style.border = "1px solid var(--border)";
        painelConfirmacao.innerHTML += `
          <div class="panel-header">
        <span class="panel-title">
          <span style="background:var(--muted2);color:#0a0a0a;font-family:'Space Mono',monospace;font-size:0.65rem;padding:3px 8px;border-radius:3px;margin-right:10px;">3</span>
          Confirmar Entrega
        </span>
      </div>
      <div class="panel-body">
        <form action="#" method="get">
          <div class="form-grid" style="gap:18px;">
            <div class="form-group">
              <label class="form-label" for="data-retirada">Data da Retirada *</label>
              <input type="date" class="form-input" id="data-retirada" name="data" required>
            </div>
            <div class="form-group">
              <label class="form-label" for="almoxarife">Almoxarife Responsável *</label>
              <input type="text" class="form-input" id="almoxarife" name="almoxarife" placeholder="Nome de quem está entregando" required>
            </div>
            <div class="form-group span-2">
              <label class="form-label" for="obs-retirada">Observações</label>
              <textarea class="form-textarea" id="obs-retirada" name="obs" placeholder="Alguma observação sobre a entrega..." style="min-height:70px;"></textarea>
            </div>
          </div>
          <div class="divider" style="margin-top:20px;"></div>
          <div class="form-actions" style="margin-top:20px;">
            <button type="submit" class="btn btn-primary">
              <svg width="13" height="13" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><polyline points="20 6 9 17 4 12"/></svg>
              Confirmar Retirada
            </button>
            <button type="button" class="btn btn-danger">
              <svg width="13" height="13" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><line x1="18" y1="6" x2="6" y2="18"/><line x1="6" y1="6" x2="18" y2="18"/></svg>
              Cancelar Reserva
            </button>
            <button type="reset" class="btn btn-ghost">Limpar</button>
          </div>
        </form>
      </div>
        `;
      } else {
        tabelaProdutos.innerHTML = `<tr>
        <td colspan="6" style="text-align:center;">Nenhum Item reservado!</td>
        </tr>`;
      }
    } else {
      console.log("Erro no envio:" + resposta.status);
    }
  } catch (erro) {
    console.log("Erro de rede:" + erro);
  }
});
