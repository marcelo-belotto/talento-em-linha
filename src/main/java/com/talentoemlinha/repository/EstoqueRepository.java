package com.talentoemlinha.repository;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Repository;

import com.talentoemlinha.model.Estoque;
import com.talentoemlinha.model.Produto;

@Repository
public class EstoqueRepository {
    private Set<Estoque> listaEstoque = new TreeSet<Estoque>();

    public Estoque save(Estoque estoque){
        listaEstoque.add(estoque);
        return estoque;
    }

    public Optional<Estoque> findByProduto(Produto produto) {
        return listaEstoque.stream().filter(x -> x.getProduto().equals(produto)).findFirst();
    }


}
