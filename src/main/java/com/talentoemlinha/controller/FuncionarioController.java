package com.talentoemlinha.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.model.Funcionario;

@RestController
public class FuncionarioController {
    List<Funcionario> funcionarios = Funcionario.getFuncionariosMocados();

    @GetMapping("/api/funcionario")
    public List<Funcionario> funcionarioGet(){
        return funcionarios;
    }

    @GetMapping("/api/funcionario/{id}")
    public Funcionario funcionarioGet(@PathVariable int id){
        for (Funcionario funcionario : funcionarios) {
            if (id == funcionario.getNp()) return funcionario;
        }
        return null;
    }

    @PostMapping("/api/funcionario")
    public Funcionario funcionarioPost(@RequestBody Funcionario func){
        funcionarios.add(func);
        return func;
    }

    @PutMapping("/api/funcionario/{id}")
    public Funcionario funcionarioPut(@PathVariable int id ,@RequestBody Funcionario func){
        for (Funcionario funcionario : funcionarios) {
            if (id == funcionario.getNp()) {
                funcionario.setNome(func.getNome());
                funcionario.setNp(func.getNp());
                return funcionario;
            }
        }
        return null;
    }

    @DeleteMapping("/api/funcionario/{id}")
    public void funcionarioDelete(@PathVariable int id){
        funcionarios.remove(funcionarioGet(id));
    }

}
