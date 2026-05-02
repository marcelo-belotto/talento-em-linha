package com.talentoemlinha.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.model.Produto;

@RestController
public class ProdutoController {
    List<Produto> produtosMocados = Produto.produtosMocados();

    @GetMapping("/produto")
    public ResponseEntity<List<Produto>> produtosGet(){
        return ResponseEntity.status(HttpStatus.OK).body(produtosMocados);
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> produtosGet(@PathVariable int id){
        for (Produto produto : produtosMocados) {
            if (produto.getId() == id) return ResponseEntity.status(HttpStatus.OK).body(produto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/produto")
    public ResponseEntity<Produto> produtosPost(@RequestBody Produto produto){
        produtosMocados.add(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    // @DeleteMapping("/produto/{id}")
    // public void produtosDelete(@PathVariable int id){
    //     produtosMocados.remove(produtosGet(id));
    // }

}
