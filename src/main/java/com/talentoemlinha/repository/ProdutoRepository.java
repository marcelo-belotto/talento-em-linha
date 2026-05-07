package com.talentoemlinha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.talentoemlinha.model.Produto;

@Repository
public class ProdutoRepository {

    private List<Produto> listaProdutos = Produto.produtosMocados();

    public List<Produto> findAll() {
        return listaProdutos;
    }

    public Optional<Produto> findById(long produtoId) {
        return listaProdutos.stream().filter(x -> x.getId() == produtoId).findFirst();
    }

    public Produto save(Produto produto){
        if (listaProdutos.stream().anyMatch(x -> x.equals(produto))) throw new RuntimeException("Produto já existe");
        produto.setId(listaProdutos.size()+1);
        listaProdutos.add(produto);
        return produto;
    }

    public Produto delete(long produtoId){
        Produto produto = listaProdutos.stream().filter(x -> x.getId() == produtoId).findFirst()
            .orElseThrow(() -> new RuntimeException("Produto Não encontrado"));
        listaProdutos.remove(produto);
        return produto;
    }

}
