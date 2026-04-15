package com.talentoemlinha;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FuncionarioController {


    @GetMapping("/funcionario")
    public Funcionario funcionarioGet(){
        var func = new Funcionario();
        func.setNome("Marcelo");
        func.setNp("37024456");
        return func;
    }

}
