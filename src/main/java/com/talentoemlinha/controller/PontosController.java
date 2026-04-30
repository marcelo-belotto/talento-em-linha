package com.talentoemlinha.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.model.Ponto;


@RestController
public class PontosController {
    private List<Ponto> pontos = new ArrayList<Ponto>();

    @GetMapping("/pontos")
    public ResponseEntity<List<Ponto>> pontosGet() {
        return ResponseEntity.status(HttpStatus.OK).body(pontos);
    }

    @GetMapping("/pontos/{id}")
    public ResponseEntity<List<Ponto>> pontosGet(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pontos.stream().filter(x -> x.getNpFuncionario() == id).toList());
    }

    @PostMapping("/pontos")
    public ResponseEntity<Ponto> pontoPost(@RequestBody Ponto ponto) {
        System.out.println(ponto);
        if (ponto.getId() == 0 || ponto.getNpFuncionario() == 0 || ponto.getQuantidade() == 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        pontos.add(ponto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ponto);
    }

}
