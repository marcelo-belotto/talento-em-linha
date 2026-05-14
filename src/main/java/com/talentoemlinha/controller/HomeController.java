package com.talentoemlinha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.talentoemlinha.service.PontoService;

@Controller
public class HomeController {

    @Autowired
    private PontoService pontoServ;
    

    @GetMapping("/index")
    public String Homepage(Model model){
        model.addAttribute("total",pontoServ.retornarTotalDePontos(10000001));
        model.addAttribute("usados",pontoServ.retornarTotalDePontosUtilizados(10000001));
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
