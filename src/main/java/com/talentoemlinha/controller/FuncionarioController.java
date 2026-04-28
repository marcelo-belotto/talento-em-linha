package com.talentoemlinha.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.model.Categoria;
import com.talentoemlinha.model.Funcionario;

@RestController
public class FuncionarioController {
    public List<Funcionario> funcionarios = Funcionario.getFuncionariosMocados();

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
    public ResponseEntity<Funcionario> funcionarioPost(@PathVariable int id, @RequestBody Categoria cat) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getNp() == id)
                // funcionario.getPontos().add(cat);
            return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
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
    public ResponseEntity<Funcionario> funcionarioDelete(@PathVariable int id) {
        funcionarios.remove(funcionarioGet(id));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

}
