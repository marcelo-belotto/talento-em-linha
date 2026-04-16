package com.talentoemlinha.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.model.Funcionario;

@RestController
public class FuncionarioController {
    List<Funcionario> funcionarios = new ArrayList<Funcionario>();

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

}
