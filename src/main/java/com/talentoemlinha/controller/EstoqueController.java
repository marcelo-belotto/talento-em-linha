package com.talentoemlinha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.service.EstoqueService;
import com.talentoemlinha.dto.EstoqueDto;
import com.talentoemlinha.model.Estoque;
import com.talentoemlinha.model.Movimentacao;

@RestController
public class EstoqueController {

    @Autowired
    private EstoqueService estServ;

    @GetMapping("/estoque")
    public List<Estoque> getEstoque() {
        return estServ.consultarTodos();
    }

    @GetMapping("/estoque/{id}")
    public Estoque getEstoque(@PathVariable long id) {
        return estServ.consultarSaldo(id);
    }

    @PostMapping("/estoque/entrada")
    public Movimentacao postEstoqueEntrada(@RequestBody EstoqueDto estoqueDto) {
        return estServ.entrada(estoqueDto.getIdProduto(), estoqueDto.getQuantidade());
    }

    @PostMapping("/estoque/retirada")
    public Movimentacao postEstoqueRetirada(@RequestBody EstoqueDto estoqueDto) {
        return estServ.retirada(estoqueDto.getIdProduto(), estoqueDto.getQuantidade());
    }
}