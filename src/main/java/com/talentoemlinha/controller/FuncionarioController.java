package com.talentoemlinha.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.dto.FuncionarioDto;
import com.talentoemlinha.model.Funcionario;

@RestController
public class FuncionarioController {
    List<FuncionarioDto> funcionarios = Funcionario.getFuncionariosMocados().stream().map(x -> x.toFuncionarioDto()).toList();

    @GetMapping("/funcionario")
    public List<FuncionarioDto> funcionarioGet(){
        return funcionarios;
    }

    @GetMapping("/funcionario/{id}")
    public FuncionarioDto funcionarioGet(@PathVariable int id){
        for (FuncionarioDto funcionario : funcionarios) {
            if (id == funcionario.getNp()) return funcionario;
        }
        return null;
    }

    // @PostMapping("/funcionario")
    // public FuncionarioDto funcionarioPost(@RequestBody FuncionarioDto func) {
    //     for (FuncionarioDto funcionarioDto : funcionarios) {
    //         System.out.println(funcionarioDto);
    //         if (funcionarioDto.equals(func))
    //             return null;
    //     }
    //     funcionarios.add(func);
    //     return func;
    // }

    @PutMapping("/funcionario/{id}")
    public FuncionarioDto funcionarioPut(@PathVariable int id ,@RequestBody Funcionario func){
        for (FuncionarioDto funcionario : funcionarios) {
            if (id == funcionario.getNp()) {
                funcionario.setNome(func.getNome());
                funcionario.setNp(func.getNp());
                return funcionario;
            }
        }
        return null;
    }

    @DeleteMapping("/funcionario/{id}")
    public void funcionarioDelete(@PathVariable int id){
        funcionarios.remove(funcionarioGet(id));
    }

}
