package com.talentoemlinha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentoemlinha.model.Produto;
import com.talentoemlinha.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository prodRepo;

    public List<Produto> buscarTodosOsProdutos() {
        return prodRepo.findAll();
    }

    public Produto buscarProduto(Long produtoId) {
        Produto produto = prodRepo.findById(produtoId)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return produto;
    }

    public Produto cadastrarNovoProduto(Produto produto){
        return prodRepo.save(produto);
    }

    public Produto removerProduto(Long produtoId){
        Produto produto = prodRepo.delete(produtoId);
        return produto;
    }
}
