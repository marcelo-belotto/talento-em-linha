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

import com.talentoemlinha.model.Funcionario;
import com.talentoemlinha.service.FuncionarioService;

@RestController
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcService;

    @GetMapping("/funcionario")
    public ResponseEntity<List<Funcionario>> getFuncionario() {
        return ResponseEntity.status(HttpStatus.OK).body(funcService.retornarTodosFuncionarios());
    }

    @GetMapping("/funcionario/{id}")
    public ResponseEntity<Funcionario> getFuncionario(@PathVariable long id) {
        Funcionario responseFunc = funcService.retornarFuncionarioPeloId(id);
        if (responseFunc != null)
            return ResponseEntity.status(HttpStatus.OK).body(responseFunc);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @PostMapping("/funcionario")
    public ResponseEntity<Funcionario> postFuncionario(@RequestBody Funcionario func) {
        Funcionario responseFunc = funcService.adicionarFuncionario(func);
        if (responseFunc == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(func);
    }

    @PutMapping("/funcionario/{id}")
    public ResponseEntity<Funcionario> putFuncionario(@PathVariable long id, @RequestBody Funcionario func) {
        Funcionario resposeFunc = funcService.alterarFuncionario(id, func);
        if (resposeFunc == null)
            return null;
        return ResponseEntity.status(HttpStatus.CREATED).body(func);
    }

    @DeleteMapping("/funcionario/{id}")
    public ResponseEntity<Funcionario> deleteFuncionario(@PathVariable long id) {
        if (funcService.deletarFuncionario(id) != null)
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
