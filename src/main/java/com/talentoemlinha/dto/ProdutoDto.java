package com.talentoemlinha.dto;

import lombok.Data;

@Data
public class ProdutoDto {
    private String nome;
    private String descricao;
    private String categoria;
    private int pontos;
    private int estoqueInicial;
    private int estoqueMinimo;
}
