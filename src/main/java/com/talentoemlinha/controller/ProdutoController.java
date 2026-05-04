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
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.model.Produto;
import com.talentoemlinha.service.ProdutoService;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoService prodService;

    @GetMapping("/produto")
    public ResponseEntity<List<Produto>> produtosGet(){
        return ResponseEntity.status(HttpStatus.OK).body(prodService.buscarTodosOsProdutos());
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> produtosGet(@PathVariable long id){
        Produto produto = prodService.buscarProduto(id);
        if (produto != null) return ResponseEntity.status(HttpStatus.OK).body(produto);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/produto")
    public ResponseEntity<Produto> produtosPost(@RequestBody Produto produto){
        if (prodService.cadastrarNovoProduto(produto) == null)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @DeleteMapping("/produto/{id}")
    public void produtosDelete(@PathVariable long id){
        prodService.removerProduto(id);
    }

}
