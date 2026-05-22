package com.talentoemlinha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.dto.ProdutoDto;
import com.talentoemlinha.model.Produto;
import com.talentoemlinha.service.EstoqueService;
import com.talentoemlinha.service.ProdutoService;

@RestController
@RequestMapping("/api/v1")
public class ProdutoController {

    @Autowired
    private ProdutoService prodService;
    @Autowired
    private EstoqueService estoqueServ;

    @GetMapping("/produto")
    public ResponseEntity<List<Produto>> getProduto() {
        return ResponseEntity.status(HttpStatus.OK).body(prodService.buscarTodosOsProdutos());
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> getProduto(@PathVariable long id) {
        Produto produto = prodService.buscarProduto(id);
        if (produto != null)
            return ResponseEntity.status(HttpStatus.OK).body(produto);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/produto")
    public ResponseEntity<Produto> postProduto(@RequestBody ProdutoDto produtoReq) {
        Produto produto = prodService.produtoMapper(produtoReq);
        if (prodService.cadastrarNovoProduto(produto) == null)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(produto);
        estoqueServ.entrada(produto.getId(), produtoReq.getEstoqueInicial(),produtoReq.getEstoqueMinimo());
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @DeleteMapping("/produto/{id}")
    public ResponseEntity<?> deleteProduto(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(prodService.removerProduto(id));
    }

}
