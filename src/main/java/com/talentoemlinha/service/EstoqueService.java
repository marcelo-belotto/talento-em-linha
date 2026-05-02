package com.talentoemlinha.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.talentoemlinha.model.Estoque;
import com.talentoemlinha.model.Movimentacao;
import com.talentoemlinha.model.Produto;
import com.talentoemlinha.repository.EstoqueRepository;
import com.talentoemlinha.repository.MovimentacaoRepository;
import com.talentoemlinha.repository.ProdutoRepository;

@Service
public class EstoqueService {

    private EstoqueRepository estoqueRepo;
    private ProdutoRepository produtoRepo;
    private MovimentacaoRepository movRepo;

    public Movimentacao entrada(Long produtoId, int quantidade) {
        Estoque estoque = buscarOuCriarEstoque(produtoId);

        estoque.setQuantidade(estoque.getQuantidade() + quantidade);
        estoqueRepo.save(estoque);

        return registrarMovimentacao(estoque.getProduto(), "ENTRADA", quantidade);
    }

    public Movimentacao saida(Long produtoId, int quantidade) {
        Estoque estoque = buscarOuCriarEstoque(produtoId);

        if (estoque.getQuantidade() < quantidade) {
            throw new RuntimeException("Estoque insuficiente");
        }

        estoque.setQuantidade(estoque.getQuantidade() - quantidade);
        estoqueRepo.save(estoque);

        return registrarMovimentacao(estoque.getProduto(), "SAIDA", quantidade);
    }

    public Estoque consultarSaldo(Long produtoId) {
        return buscarOuCriarEstoque(produtoId);
    }

    private Estoque buscarOuCriarEstoque(Long produtoId) {
        Produto produto = produtoRepo.findById(produtoId)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return estoqueRepo.findByProduto(produto)
            .orElseGet(() -> estoqueRepo.save(new Estoque(0, produto, 0)));
    }

    private Movimentacao registrarMovimentacao(Produto produto, String tipo, int quantidade) {
        return movRepo.save(new Movimentacao(0, produto, tipo, quantidade, LocalDateTime.now()));
    }
}