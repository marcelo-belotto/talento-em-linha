const carrinhoReservas = document.querySelector("#carrinhoReservas");
const produtosDisponiveis = document.querySelector("#produtosDisponiveis");

function reservarItem(item){
    let id = item.cells[4].children[0].id;
    carrinhoReservas.innerHTML += 
    `<tr id=${item.id}>
        <td data-label="# ID">${id}</td>
        <td data-label="Brinde">${item.cells[0].innerHTML}</td>
        <td data-label="Pontos">${item.cells[2].innerHTML}</td>
        <td data-label="Remover"><input type="button" value="Remover" 
        onclick="removerItem(${item.id})"></td>
    </tr>`;
    produtosDisponiveis.remove(item);
}

function removerItem(item){
    carrinhoReservas.remove(item);
}

console.log(carrinhoReservas);
console.log(produtosDisponiveis);
