package com.talentoemlinha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.talentoemlinha.repository.ReservaRepository;
import com.talentoemlinha.service.EstoqueService;
import com.talentoemlinha.service.PontoService;
import com.talentoemlinha.service.ReservaService;

@Controller
public class HomeController {

    @Autowired
    private PontoService pontoServ;
    @Autowired
    private ReservaRepository reservaRepo;
    @Autowired
    private EstoqueService estoqueserv;
    
    private long NP = 10000001; //Temporario Apenas teste

    @GetMapping("/index")
    public String Homepage(Model model) {
        model.addAttribute("total", pontoServ.retornarTotalDePontos(NP));
        model.addAttribute("usados", pontoServ.retornarTotalDePontosUtilizados(NP));
        model.addAttribute("pontos", pontoServ.retornarPontosPeloNp(NP));
        model.addAttribute("brindes", reservaRepo.findByNpFuncionario(NP));
        System.out.println(model);
        return "index";
    }

    @GetMapping("/reserva")
    public String Reserva(Model model) {
        model.addAttribute("disponivel", pontoServ.retornarPontosDisponiveis(NP));
        model.addAttribute("listaEstoque", estoqueserv.consultarTodos());
        System.out.println(model);
        return "reserva";
    }

    @GetMapping("/pontos-categoria")
    public String Pontos() {
        return "pontos-categoria";
    }

    @GetMapping("/meus-dados")
    public String Dados(Model model) {
        return "meus-dados";
    }

}
