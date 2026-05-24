package com.talentoemlinha.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentoemlinha.model.Estoque;
import com.talentoemlinha.model.Movimentacao;
import com.talentoemlinha.model.Produto;
import com.talentoemlinha.repository.EstoqueRepository;
import com.talentoemlinha.repository.MovimentacaoRepository;
import com.talentoemlinha.repository.ProdutoRepository;

@Service
public class EstoqueService {
    @Autowired
    private  EstoqueRepository estoqueRepo;
    @Autowired
    private ProdutoRepository produtoRepo;
    @Autowired
    private MovimentacaoRepository movRepo;

    public Movimentacao entrada(long produtoId, int quantidade, int estoqueMinimo) {
        Estoque estoque = buscarOuCriarEstoque(produtoId);

        estoque.setQuantidadeDisponivel(estoque.getQuantidadeDisponivel() + quantidade);
        estoque.setEstoqueMinimo(estoqueMinimo);
        estoqueRepo.save(estoque);

        return registrarMovimentacao(estoque.getProduto(), "ENTRADA"," - ", quantidade, LocalDateTime.now());
    }
    public Movimentacao adicionarAoEstoqueExistente(long produtoId, int quantidade, String observacao, LocalDateTime data) {
        Estoque estoque = buscarOuCriarEstoque(produtoId);

        estoque.setQuantidadeDisponivel(estoque.getQuantidadeDisponivel() + quantidade);
        estoqueRepo.save(estoque);

        return registrarMovimentacao(estoque.getProduto(),"ENTRADA", observacao, quantidade, data);
    }

    public Movimentacao reservar(long idProduto,int quantidade){
        Estoque estoque = buscarOuCriarEstoque(idProduto);
        if (estoque.getQuantidadeDisponivel() < quantidade) throw new RuntimeException("Estoque Insuficiente");
        estoque.setQuantidadeDisponivel(estoque.getQuantidadeDisponivel() - quantidade);
        estoque.setQuantidadeReservada(estoque.getQuantidadeReservada() + quantidade);
        estoqueRepo.save(estoque);
        return registrarMovimentacao(estoque.getProduto(), "RESERVADO"," - ", quantidade, LocalDateTime.now());
    }

    public Movimentacao retirada(long produtoId, int quantidade) {
        Estoque estoque = buscarOuCriarEstoque(produtoId);

        estoque.setQuantidadeReservada(estoque.getQuantidadeReservada() - quantidade);
        estoqueRepo.save(estoque);

        return registrarMovimentacao(estoque.getProduto(), "RETIRADO"," - ", quantidade, LocalDateTime.now());
    }

    public List<Estoque> consultarTodos(){
        return estoqueRepo.findAll();
    }

    public Estoque consultarSaldo(long produtoId) {
        return buscarOuCriarEstoque(produtoId);
    }

    private Estoque buscarOuCriarEstoque(long produtoId) {
        Produto produto = produtoRepo.findById(produtoId)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return estoqueRepo.findByProduto(produto)
            .orElseGet(() -> estoqueRepo.save(new Estoque(0,produto, 0,0,0)));
    }

    private Movimentacao registrarMovimentacao(Produto produto, String tipo, String observacao, int quantidade, LocalDateTime hora) {
        return movRepo.save(new Movimentacao(0, produto, tipo,observacao, quantidade, hora));
    }
}