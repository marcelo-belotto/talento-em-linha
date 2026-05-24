const formulario = document.getElementById("formAdicionarEstoque");

formulario.addEventListener("submit", async (event) => {
  event.preventDefault(); // Impede o recarregamento da página

  const formData = new FormData(formulario); // Captura os dados do formulário
  const dadosObjeto = Object.fromEntries(formData.entries());

  try {
    const resposta = await fetch("http://localhost:8080/api/v1/estoque", {
      method: "PUT",
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
      console.log("Erro no envio:"+ resposta.status);
    }
  } catch (erro) {
    console.log("Erro de rede:"+ erro);
  }
});
