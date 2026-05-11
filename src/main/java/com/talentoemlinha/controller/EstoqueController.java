package com.talentoemlinha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.service.EstoqueService;
import com.talentoemlinha.dto.EstoqueDto;
import com.talentoemlinha.model.Estoque;
import com.talentoemlinha.model.Movimentacao;

@RestController
@RequestMapping("/api/v1")
public class EstoqueController {

    @Autowired
    private EstoqueService estServ;

    @GetMapping("/estoque")
    public ResponseEntity<List<Estoque>> getEstoque() {
        return ResponseEntity.status(HttpStatus.OK).body(estServ.consultarTodos());
    }

    @GetMapping("/estoque/{id}")
    public ResponseEntity<Estoque> getEstoque(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(estServ.consultarSaldo(id));
    }

    @PostMapping("/estoque/entrada")
    public ResponseEntity<Movimentacao> postEstoqueEntrada(@RequestBody EstoqueDto estoqueDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estServ.entrada(estoqueDto.getIdProduto(), estoqueDto.getQuantidade()));
    }

    // @PostMapping("/estoque/retirada")
    // public RespoMovimentacao postEstoqueRetirada(@RequestBody EstoqueDto estoqueDto) {
    //     return estServ.retirada(estoqueDto.getIdProduto(), estoqueDto.getQuantidade());
    // }
}
