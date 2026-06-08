package com.talentoemlinha.controller;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.talentoemlinha.service.FuncionarioService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final FuncionarioService funcServ;

    @GetMapping("/index")
    public String Homepage(Model model){
        model.addAttribute("activePage", "index");
        model.addAttribute("content", "/administrador/index.html");
        model.addAttribute("pageProps", Map.of(
                "title", " Início",
                "pageCss", "../css/pages-styles.css"));
        return "layout/administrador";
    }

    @GetMapping("/bonificacao")
    public String Bonificacao(Model model){
        model.addAttribute("activePage", "bonificacao");
        model.addAttribute("content", "/administrador/bonificacao.html");
        model.addAttribute("pageProps", Map.of(
                "title", " Bonificação",
                "pageCss", "../css/pages-styles.css"));
        return "layout/administrador";
    }

    @GetMapping("/cadastro-funcionario")
    public String Cadastro(Model model, Authentication authentication){
        model.addAttribute("activePage", "cadastro-funcionario");
        model.addAttribute("content", "/administrador/cadastro-funcionario.html");
        model.addAttribute("pageProps", Map.of(
                "title", " Cadastro de Funcionários",
                "pageCss", "../css/pages-styles.css"));
        model.addAttribute("listaFuncionarios", funcServ.retornarTodosFuncionarios()
        .stream().filter(x -> !x.getRole().equalsIgnoreCase("admin"))
        .filter(x -> x.getSetor().equalsIgnoreCase(
            funcServ.retornarFuncionarioPeloId(
                Long.parseLong(authentication.getName())
            ).getSetor())
        )
        .toList());
        return "layout/administrador";
    }

    @GetMapping("/relatorio-pontos")
    public String Relatorio(Model model, Authentication authentication){
        model.addAttribute("activePage", "relatorio-pontos");
        model.addAttribute("content", "/administrador/relatorio-pontos.html");
        model.addAttribute("pageProps", Map.of(
                "title", " Relatórios",
                "pageCss", "../css/pages-styles.css"));
        return "layout/administrador";
    }
}
