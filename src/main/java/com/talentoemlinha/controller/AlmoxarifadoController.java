package com.talentoemlinha.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.talentoemlinha.repository.MovimentacaoRepository;
import com.talentoemlinha.service.EstoqueService;
import com.talentoemlinha.service.FuncionarioService;
import com.talentoemlinha.service.ReservaService;

@Controller
@RequestMapping("/almoxarifado")
public class AlmoxarifadoController {

    @Autowired
    private EstoqueService estoqueSer;
    @Autowired
    private ReservaService reservaServ;
    @Autowired
    private FuncionarioService funcServ;
    @Autowired
    private MovimentacaoRepository movRepo;

    @GetMapping("/index")
    public String Homepage(Model model) {
        model.addAttribute("activePage", "index");
        model.addAttribute("content", "/almoxarifado/index.html");
        model.addAttribute("pageProps", Map.of(
                "title", " Início",
                "pageCss", "../css/almo-styles.css"));
        var listaEstoque = estoqueSer.consultarTodos();
        var listaReserva = reservaServ.retornarReservas();
        model.addAttribute("itensCadastrados",listaEstoque.size());
        model.addAttribute("unidadesDisponiveis",listaEstoque.stream()
                    .filter(x -> x.getQuantidadeDisponivel() > 0)
                    .mapToInt(x -> x.getQuantidadeDisponivel())
                    .sum());
        model.addAttribute("aguardandoRetirada",listaReserva.stream()
                    .filter(x -> x.getStatus().equalsIgnoreCase("reservado"))
                    .mapToInt(x -> x.getQuantidade())
                    .sum());
        model.addAttribute("estoqueBaixo",listaEstoque.stream()
                    .filter(x -> x.getQuantidadeDisponivel() < x.getEstoqueMinimo())
                    .toList().size());
        model.addAttribute("listaReservas",listaReserva);
        model.addAttribute("funcionarios",funcServ);
        return "layout/almoxarifado";
    }

    @GetMapping("/cadastro-produto")
    public String CadastroDeProdutos(Model model) {
        model.addAttribute("activePage", "cadastro-produto");
        model.addAttribute("content", "/almoxarifado/cadastro-produto.html");
        model.addAttribute("pageProps", Map.of(
                "title", " Cadastro de Produto",
                "pageCss", "../css/almo-styles.css"));
        model.addAttribute("listaEstoque", estoqueSer.consultarTodos());
        return "layout/almoxarifado";
    }

    @GetMapping("/estoque")
    public String Estoque(Model model) {
        model.addAttribute("activePage", "estoque");
        model.addAttribute("content", "/almoxarifado/estoque.html");
        model.addAttribute("pageProps", Map.of(
                "title", " Estoque",
                "pageCss", "../css/almo-styles.css"));
        model.addAttribute("listaEstoque",estoqueSer.consultarTodos());
        var listaDeMovimentacoes = movRepo.findAll().stream().filter(x -> x.getTipoMovimentacao().equalsIgnoreCase("ENTRADA")).toList();
        model.addAttribute("listaMovimentacao",listaDeMovimentacoes);
        model.addAttribute("listaDeMeses",listaDeMovimentacoes.stream().map(x -> x.getDataHora().getMonth()).toList());
        return "layout/almoxarifado";
    }

    @GetMapping("/disponibilidade")
    public String Disponibilidade(Model model) {
        model.addAttribute("activePage", "disponibilidade");
        model.addAttribute("content", "/almoxarifado/disponibilidade.html");
        model.addAttribute("pageProps", Map.of(
                "title", " Disponibilidade",
                "pageCss", "../css/almo-styles.css"));
        model.addAttribute("listaProdutos",estoqueSer.consultarTodos());
        return "layout/almoxarifado";
    }

    @GetMapping("/reservas")
    public String Reservas(Model model) {
        model.addAttribute("activePage", "reservas");
        model.addAttribute("content", "/almoxarifado/reservas.html");
        model.addAttribute("pageProps", Map.of(
                "title", " Reservas",
                "pageCss", "../css/almo-styles.css"));
        return "layout/almoxarifado";
    }

    @GetMapping("/retirada")
    public String Retirada(Model model) {
        model.addAttribute("activePage", "retirada");
        model.addAttribute("content", "/almoxarifado/retirada.html");
        model.addAttribute("pageProps", Map.of(
                "title", " Retiradas",
                "pageCss", "../css/almo-styles.css"));
        return "layout/almoxarifado";
    }

}
