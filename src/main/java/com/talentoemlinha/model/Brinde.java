package com.talentoemlinha.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Brinde {
    private int id;
    private String nome;
    private String descricao;
    private int pontos;

    public static List<Brinde> brindesMocados() {
        List<Brinde> brindes = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Brinde brindeTemp = new Brinde();
            brindeTemp.setId(i);
            brindeTemp.setNome("Brinde " + i);
            brindeTemp.setDescricao("Descrição do Brinde " + i);
            brindeTemp.setPontos(i * 10);
            brindes.add(brindeTemp);
        }
        return brindes;
    }
}
