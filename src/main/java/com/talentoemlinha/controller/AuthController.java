package com.talentoemlinha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.talentoemlinha.service.FuncionarioService;

@Controller
public class AuthController {

    @Autowired
    private FuncionarioService funcService;

    @GetMapping("/")
    public String loginTela(){
        return "login";
    }
    
    @PostMapping("/") //metodo ruim apenas para testar o metodo post e redirecionamento vou melhorar posteriormente com spring security
    public String login(@RequestParam("usuario") String usuario, @RequestParam("senha") String senha){
        var func = funcService.retornarFuncionarioPeloId(Long.parseLong(usuario));
        System.out.println(func);
        if (func != null)
        if (func.getHash().equals(senha))
            if (func.getROLE().equalsIgnoreCase("colaborador")) return "redirect:/index";
            else if (func.getROLE().equalsIgnoreCase("almoxarife")) return "redirect:/almoxarifado/index";
        return "redirect:";
    }
}
