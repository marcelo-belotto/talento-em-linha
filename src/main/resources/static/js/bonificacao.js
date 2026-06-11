const formulario = document.getElementById("formBonifIndividual");

formulario.addEventListener("submit", async (event) => {
    event.preventDefault(); // Impede o recarregamento da página
    
    const formData = new FormData(formulario); // Captura os dados do formulário
    const dadosObjeto = Object.fromEntries(formData.entries());
    const np = document.getElementById("npFuncionario").value;
    
  try {
    const resposta = await fetch(`http://localhost:8080/api/v1/bonificar/${np}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(dadosObjeto), // Envia os dados do formulário automaticamente formatados
    });

    if (resposta.ok) {
      const dados = await resposta.json();
      console.log("Dados enviados com sucesso!", dados);
      window.location.reload();
    } else {
      console.log("Erro no envio:" + resposta.status);
    }
  } catch (erro) {
    console.log("Erro de rede:" + erro);
  }
});

function switchTab(tab) {
  document.getElementById("painel-individual").style.display =
    tab === "individual" ? "block" : "none";
  document.getElementById("painel-lote").style.display =
    tab === "lote" ? "block" : "none";
  document
    .getElementById("tab-individual")
    .classList.toggle("active", tab === "individual");
  document
    .getElementById("tab-lote")
    .classList.toggle("active", tab === "lote");
}

function buscarFuncionario() {
  const np = document.getElementById("npFuncionario").value.trim();
  if (!np) {
    alert("Informe o NP do funcionário.");
    return;
  }
  fetch("/api/v1/funcionario/" + np)
    .then((r) => r.json())
    .then((data) => {
      if (data && data.nome) {
        document.getElementById("nome-funcionario-display").textContent =
          data.nome + " · " + data.setor;
        document.getElementById("nome-funcionario-display").style.color =
          "var(--text)";
        document.getElementById("funcionarioId").value = data.np;
      } else {
        document.getElementById("nome-funcionario-display").textContent =
          "Funcionário não encontrado.";
        document.getElementById("nome-funcionario-display").style.color =
          "var(--accent3)";
        document.getElementById("funcionarioId").value = "";
      }
    })
    .catch(() => {
      document.getElementById("nome-funcionario-display").textContent =
        "Erro ao buscar.";
      document.getElementById("funcionarioId").value = "";
    });
}

function toggleTodos(estado) {
  document.querySelectorAll(".check-func").forEach((c) => (c.checked = estado));
  document.getElementById("checkTodos").checked = estado;
  atualizarResumo();
}

document.addEventListener("change", function (e) {
  if (e.target.classList.contains("check-func")) atualizarResumo();
});

function atualizarResumo() {
  const selecionados = document.querySelectorAll(".check-func:checked").length;
  const pts = parseInt(document.getElementById("pontosLote").value) || 0;
  const resumo = document.getElementById("resumo-lote");
  if (selecionados > 0) {
    resumo.style.display = "flex";
    document.getElementById("texto-resumo-lote").textContent =
      selecionados +
      " funcionário(s) selecionado(s) · " +
      selecionados * pts +
      " pontos no total.";
  } else {
    resumo.style.display = "none";
  }
}

document
  .getElementById("pontosLote")
  .addEventListener("input", atualizarResumo);

function filtrarTabelaLote(busca) {
  const termo = busca.toLowerCase();
  document
    .querySelectorAll("#tabelaLote tbody tr.linha-funcionario")
    .forEach((tr) => {
      const np = tr.querySelector(".col-np")?.textContent.toLowerCase() || "";
      const nome =
        tr.querySelector(".col-nome")?.textContent.toLowerCase() || "";
      tr.style.display =
        np.includes(termo) || nome.includes(termo) ? "" : "none";
    });
}

function confirmarLote() {
  const selecionados = document.querySelectorAll(".check-func:checked").length;
  if (selecionados === 0) {
    alert("Selecione pelo menos um funcionário.");
    return false;
  }
  const pts = document.getElementById("pontosLote").value;
  return confirm(
    "Confirmar bonificação de " +
      pts +
      " pontos para " +
      selecionados +
      " funcionário(s)?",
  );
}
