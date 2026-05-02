package com.talentoemlinha.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.dto.PontoDto;
import com.talentoemlinha.model.Funcionario;
import com.talentoemlinha.model.Ponto;
import com.talentoemlinha.service.FuncionarioService;
import com.talentoemlinha.service.PontoService;

@RestController
public class PontosController {

    @Autowired
    private PontoService pontoService;

    @Autowired
    private FuncionarioService funcService;

    @GetMapping("/pontos")
    public ResponseEntity<List<Ponto>> pontosGet() {
        return ResponseEntity.status(HttpStatus.OK).body(pontoService.retornarTodosPontos());
    }

    @GetMapping("/pontos/{id}")
    public ResponseEntity<List<Ponto>> pontosGet(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pontoService.retornarTodosPontos().stream().filter(x -> x.getNpFuncionario() == id).toList());
    }

    @PostMapping("bonificar/{id}")
    public ResponseEntity<Ponto> funcionarioPost(@PathVariable int id, @RequestBody PontoDto pontos) {
        Funcionario funcionario = funcService.retornarFuncionarioPeloId(id);
        if (funcionario != null) {
            int indicePonto = pontoService.retornarTodosPontos().size() + 1;
            Ponto novoPonto = new Ponto(indicePonto, id, pontos.getQuantidade(), pontos.getMotivo());
            return ResponseEntity.status(HttpStatus.CREATED).body(pontoService.adicionarPonto(novoPonto));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
