package com.talentoemlinha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String Homepage(){
        return "index";
    }
    @GetMapping("/meus-dados")
    public String Dados(){
        return "meus-dados";
    }
    @GetMapping("/pontos-categoria")
    public String Pontos(){
        return "pontos-categoria";
    }
    @GetMapping("/reserva")
    public String Reserva(){
        return "reserva";
    }

}
