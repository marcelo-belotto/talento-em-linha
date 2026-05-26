const formulario = document.getElementById("formBuscaUsuario");

formulario.addEventListener("submit", async (event) => {
  event.preventDefault(); // Impede o recarregamento da página
  const npFuncionario = document.getElementById("id-func").value;
  const tabelaProdutos = document.getElementById("tableProdutosFuncionario");

  try {
    const resposta = await fetch(`http://localhost:8080/api/v1/${npFuncionario}/reservas`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (resposta.ok) {
      const dados = await resposta.json();
      console.log("Dados enviados com sucesso!", dados);
    //   window.location.reload();
    dados.forEach(reserva => {
        tabelaProdutos.innerHTML += `<tr>
              <td style="font-family:'Space Mono',monospace;font-size:0.75rem;color:var(--muted2);">${reserva.id.toString().padStart(5, '0')}</td>
              <td><strong>${reserva.produto.nome}</strong><br><span style="font-size:0.78rem;color:var(--muted2);">${reserva.produto.descricao}</span></td>
              <td style="font-family:'Space Mono',monospace;">${reserva.produto.pontos} pts</td>
              <td style="font-family:'Space Mono',monospace;font-size:0.8rem;">${reserva.dataReserva.toString().substring(0,10)}</td>
              <td><span class="badge badge-pendente">${reserva.status}</span></td>
              <td>
                <input type="checkbox" id="sel-r031" name="reserva" value="R-2026-031" style="accent-color:var(--accent);width:16px;height:16px;cursor:pointer;">
              </td>
            </tr>`;
    });

    } else {
      console.log("Erro no envio:"+ resposta.status);
    }
  } catch (erro) {
    console.log("Erro de rede:"+ erro);
  }
});
