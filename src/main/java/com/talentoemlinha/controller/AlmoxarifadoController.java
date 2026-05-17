package com.talentoemlinha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/almoxarifado")
public class AlmoxarifadoController {

    @GetMapping("/index")
    public String Homepage(Model model) {
        return "/almoxarifado/index";
    }

    @GetMapping("/cadastro-produto")
    public String CadastroDeProdutos(Model model) {
        return "/almoxarifado/cadastro-produto";
    }

    @GetMapping("/estoque")
    public String Estoque(Model model) {
        return "/almoxarifado/estoque";
    }

    @GetMapping("/disponibilidade")
    public String Disponibilidade(Model model) {
        return "/almoxarifado/disponibilidade";
    }

    @GetMapping("/reservas")
    public String Reservas(Model model) {
        return "/almoxarifado/reservas";
    }

    @GetMapping("/retirada")
    public String Retirada(Model model) {
        return "/almoxarifado/retirada";
    }

}
