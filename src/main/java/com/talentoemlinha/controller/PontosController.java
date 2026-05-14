package com.talentoemlinha.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.dto.PontoDto;
import com.talentoemlinha.model.Ponto;
import com.talentoemlinha.service.FuncionarioService;
import com.talentoemlinha.service.PontoService;

@RestController
@RequestMapping("/api/v1")
public class PontosController {

    @Autowired
    private PontoService pontoService;

    @Autowired
    private FuncionarioService funcService;

    @GetMapping("/pontos")
    public ResponseEntity<List<Ponto>> getPonto() {
        return ResponseEntity.status(HttpStatus.OK).body(pontoService.retornarTodosPontos());
    }

    @GetMapping("/pontos/{np}")
    public ResponseEntity<List<Ponto>> getPonto(@PathVariable int np) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pontoService.retornarPontosPeloNp(np));
    }

    @PostMapping("/bonificar/{np}")
    public ResponseEntity<Ponto> postPonto(@PathVariable long np, @RequestBody PontoDto pontos) {
        if (funcService.bonificarFuncionario(np, pontos.getQuantidade())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(pontoService
                    .adicionarPonto(new Ponto(0, np, pontos.getQuantidade(), pontos.getMotivo(), LocalDateTime.now())));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
