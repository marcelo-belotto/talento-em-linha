package com.talentoemlinha.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.talentoemlinha.model.Ponto;
import com.talentoemlinha.repository.ReservaRepository;
import com.talentoemlinha.service.EstoqueService;
import com.talentoemlinha.service.PontoService;

@Controller
public class HomeController {

    @Autowired
    private PontoService pontoServ;
    @Autowired
    private ReservaRepository reservaRepo;
    @Autowired
    private EstoqueService estoqueserv;

    private long NP = 10000001; // Temporario Apenas teste

    @GetMapping("/index")
    public String Homepage(Model model) {
        model.addAttribute("total", pontoServ.retornarTotalDePontos(NP));
        model.addAttribute("usados", pontoServ.retornarTotalDePontosUtilizados(NP));
        model.addAttribute("pontos", pontoServ.retornarPontosPeloNp(NP));
        model.addAttribute("brindes", reservaRepo.findByNpFuncionario(NP));
        model.addAttribute("activePage", "index");
        model.addAttribute("content", "index.html");
        model.addAttribute("pageProps", Map.of(
                "title", " Início",
                "pageCss", "../css/index.css"));
        return "layout/main";
    }

    @GetMapping("/reserva")
    public String Reserva(Model model) {
        model.addAttribute("disponivel", pontoServ.retornarPontosDisponiveis(NP));
        model.addAttribute("listaEstoque", estoqueserv.consultarTodos());
        model.addAttribute("activePage", "reserva");
        model.addAttribute("content", "reserva.html");
        model.addAttribute("pageProps", Map.of(
                "title", " Reserva",
                "pageCss", "../css/reserva.css"));
        return "layout/main";
    }

    @GetMapping("/pontos-categoria")
    public String Pontos(Model model) {
        var pontos = pontoServ.retornarPontosPeloNp(NP);
        // Agrupa por "MMMM yyyy" e dentro por motivo, somando quantidade
        Map<String, Map<String, Integer>> pontosPorPeriodo = new LinkedHashMap<>();

        for (Ponto ponto : pontos) {
            String periodo = ponto.getDataAtribuicao()
                    .format(DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("pt", "BR"))).toUpperCase();

            String motivo = ponto.getMotivo();

            pontosPorPeriodo
                    .computeIfAbsent(periodo, k -> new LinkedHashMap<>())
                    .merge(motivo, ponto.getQuantidade(), Integer::sum);
        }

        Map<String, Integer> totaisPorMotivo = new LinkedHashMap<>();
        List<String> motivos = List.of("Treinamento", "Desempenho", "Metas", "Engajamento", "Indicacao", "Projetos");

        for (String motivo : motivos) {
            int total = pontosPorPeriodo.values().stream()
                    .mapToInt(m -> m.getOrDefault(motivo, 0))
                    .sum();
            totaisPorMotivo.put(motivo, total);
        }

        model.addAttribute("pontosPorPeriodo", pontosPorPeriodo);
        model.addAttribute("totaisPorMotivo", totaisPorMotivo);
        model.addAttribute("activePage", "pontos-categoria");
        model.addAttribute("content", "pontos-categoria.html");
        model.addAttribute("pageProps", Map.of(
                "title", " Categoria",
                "pageCss", "../css/categoria.css"));
        return "layout/main";
    }

    @GetMapping("/meus-dados")
    public String Dados(Model model) {
        model.addAttribute("activePage", "meus-dados");
        model.addAttribute("content", "meus-dados.html");
        model.addAttribute("pageProps", Map.of(
                "title", " Perfil",
                "pageCss", "../css/meus-dados.css"));
        return "layout/main";
    }

}
