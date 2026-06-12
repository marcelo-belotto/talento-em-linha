const formulario = document.getElementById("formCadastroFuncionario");

formulario.addEventListener("submit", async (event) => {
  event.preventDefault(); // Impede o recarregamento da página

  const formData = new FormData(formulario); // Captura os dados do formulário
  const dadosObjeto = Object.fromEntries(formData.entries());
  
  try {
    const resposta = await fetch("http://localhost:8080/api/v1/funcionario", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(dadosObjeto), // Envia os dados do formulário automaticamente formatados
    });
    const dados = await resposta.json();
    
    if (resposta.ok) {
      console.log("Dados enviados com sucesso!", dados);
      alert("Funcionário cadastrado com sucesso!");
      window.location.reload();
    } else {
      console.log("Erro no envio:"+ resposta.status);
    }
  } catch (erro) {
    console.log("Erro de rede:"+ erro);
  }
});
