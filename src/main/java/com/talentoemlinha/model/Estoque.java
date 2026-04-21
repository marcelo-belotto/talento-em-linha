package com.talentoemlinha.model;


import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Estoque {
    private int id;
    private Brinde brinde;
    private int quantidade;

    public static List<Estoque> getBrindesMockados(){
        List<Estoque> estoque = new ArrayList<>();
        for( int i = 1; i < 11; i++){
            Estoque estoqueTemp = new Estoque();
            estoqueTemp.setId(i);
            Brinde brindeTemp = new Brinde();
            brindeTemp.setId(i);
            brindeTemp.setNome("Brinde "+i);
            brindeTemp.setDescricao("Descrição do Brinde "+i);
            brindeTemp.setPontos(i*10);
            estoqueTemp.setBrinde(brindeTemp);
            estoqueTemp.setQuantidade(i*20);
            estoque.add(estoqueTemp);
        }
        return estoque;
    }
}
