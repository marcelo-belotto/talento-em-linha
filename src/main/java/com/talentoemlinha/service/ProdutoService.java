package com.talentoemlinha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentoemlinha.dto.ProdutoDto;
import com.talentoemlinha.model.Produto;
import com.talentoemlinha.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository prodRepo;

    public List<Produto> buscarTodosOsProdutos() {
        return prodRepo.findAll();
    }

    public Produto buscarProduto(long produtoId) {
        Produto produto = prodRepo.findById(produtoId)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return produto;
    }

    public Produto cadastrarNovoProduto(Produto produto){
        return prodRepo.save(produto);
    }

    public Produto removerProduto(long produtoId){
        Produto produto = prodRepo.delete(produtoId);
        return produto;
    }

    public Produto produtoMapper(ProdutoDto produtoDto){
        return new Produto(0,produtoDto.getNome(),produtoDto.getDescricao(),produtoDto.getPontos());
    }
}
