package com.talentoemlinha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.dto.PontoDto;
import com.talentoemlinha.model.Funcionario;
import com.talentoemlinha.model.Ponto;
import com.talentoemlinha.service.PontoService;

@RestController
public class FuncionarioController {
    private List<Funcionario> funcionarios = Funcionario.getFuncionariosMocados();

    @Autowired
    private PontoService pontoService;

    @GetMapping("/funcionario")
    public ResponseEntity<List<Funcionario>> funcionarioGet() {
        return ResponseEntity.status(HttpStatus.OK).body(funcionarios);
    }

    @GetMapping("/funcionario/{id}")
    public ResponseEntity<Funcionario> funcionarioGet(@PathVariable int id) {
        for (Funcionario funcionario : funcionarios) {
            if (id == funcionario.getNp())
                return ResponseEntity.status(HttpStatus.OK).body(funcionario);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/funcionario/{id}/bonificar")
    public ResponseEntity<Ponto> funcionarioPost(@PathVariable int id, @RequestBody PontoDto pontos) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getNp() == id){
                Ponto novoPonto = new Ponto(1,id,pontos.getQuantidade(),pontos.getMotivo());
                funcionario.setTotalDePontos(funcionario.getTotalDePontos() + pontos.getQuantidade());
            return ResponseEntity.status(HttpStatus.CREATED).body(pontoService.adicionarPonto(novoPonto));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/funcionario")
    public ResponseEntity<Funcionario> funcionarioPost(@RequestBody Funcionario func) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.equals(func))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        funcionarios.add(func);
        return ResponseEntity.status(HttpStatus.CREATED).body(func);
    }

    @PutMapping("/funcionario/{id}")
    public ResponseEntity<Funcionario> funcionarioPut(@PathVariable int id, @RequestBody Funcionario func) {
        for (Funcionario funcionario : funcionarios) {
            if (id == funcionario.getNp() && func != null) {
                funcionarios.remove(funcionario);
                funcionarios.add(func);
                return ResponseEntity.status(HttpStatus.CREATED).body(func);
            }
        }
        return null;
    }

    @DeleteMapping("/funcionario/{id}")
    public ResponseEntity<Funcionario> funcionarioDelete(@PathVariable int id){
        if (funcionarios.remove(funcionarioGet(id))){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
