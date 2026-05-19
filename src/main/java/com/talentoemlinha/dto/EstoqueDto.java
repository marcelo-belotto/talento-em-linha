package com.talentoemlinha.dto;

import lombok.Data;

@Data
public class EstoqueDto {
    private long idProduto;
    private int quantidade;
    private int estoqueMinimo;
}
