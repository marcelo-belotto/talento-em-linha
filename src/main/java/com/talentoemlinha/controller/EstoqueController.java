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
    public List<Estoque> estoqueGet() {
        return estServ.consultarTodos();
    }

    @GetMapping("/estoque/{id}")
    public Estoque estoqueGet(@PathVariable long id) {
        return estServ.consultarSaldo(id);
    }

    @PostMapping("/estoque/entrada")
    public Movimentacao estoqueEntradaPost(@RequestBody EstoqueDto estoqueDto) {
        return estServ.entrada(estoqueDto.getIdProduto(), estoqueDto.getQuantidade());
    }

    @PostMapping("/estoque/saida")
    public Movimentacao estoqueSaidaPost(@RequestBody EstoqueDto estoqueDto) {
        return estServ.saida(estoqueDto.getIdProduto(), estoqueDto.getQuantidade());
    }
}