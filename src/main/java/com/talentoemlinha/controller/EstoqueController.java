package com.talentoemlinha.controller;

import java.util.List;

// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.model.Estoque;


@RestController
public class EstoqueController {
    List<Estoque> estoqueMock = Estoque.getBrindesMockados();

    @GetMapping("/api/estoque")
    public List<Estoque> estoqueGet(){
        return estoqueMock;
    }

    @GetMapping("/api/estoque/{id}")
    public Estoque estoqueGet(@PathVariable int id){
        for (Estoque estoque : estoqueMock) {
            if (id == estoque.getId()) return estoque;
        }
        return null;
    }

    @PostMapping("/api/estoque")
    public Estoque estoquePost(@RequestBody Estoque estoque){
        estoqueMock.add(estoque);
        return estoque;
    }

}
