package com.talentoemlinha.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Produto {
    private long id;
    private String nome;
    private String descricao;
    private int pontos;

    public static List<Produto> produtosMocados() {
        List<Produto> produtos = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Produto protudoTemp = new Produto();
            protudoTemp.setId(i);
            protudoTemp.setNome("Produto " + i);
            protudoTemp.setDescricao("Descrição do Produto " + i);
            protudoTemp.setPontos(i * 10);
            produtos.add(protudoTemp);
        }
        return produtos;
    }
}
