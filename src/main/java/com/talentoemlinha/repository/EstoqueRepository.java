package com.talentoemlinha.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.talentoemlinha.model.Estoque;
import com.talentoemlinha.model.Produto;

@Repository
public class EstoqueRepository {
    private List<Estoque> listaEstoque = new ArrayList<Estoque>();

    public Estoque save(Estoque estoque){
        if (!listaEstoque.contains(estoque)){
            listaEstoque.add(estoque);
        }else{
            int indice = listaEstoque.indexOf(estoque);
            listaEstoque.remove(indice);
            listaEstoque.add(estoque);
        }
        return estoque;
    }

    public List<Estoque> findAll(){
        return listaEstoque;
    }

    public Optional<Estoque> findByProduto(Produto produto) {
        return listaEstoque.stream().filter(x -> x.getProduto().equals(produto)).findFirst();
    }


}
