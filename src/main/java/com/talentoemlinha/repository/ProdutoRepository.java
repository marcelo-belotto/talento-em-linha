package com.talentoemlinha.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.talentoemlinha.model.Produto;

public class ProdutoRepository {

    private List<Produto> listaProdutos = new ArrayList<>();

    public Optional<Produto> findById(Long produtoId) {
        return listaProdutos.stream().filter(x -> x.getId()==produtoId).findFirst();
    }

}
