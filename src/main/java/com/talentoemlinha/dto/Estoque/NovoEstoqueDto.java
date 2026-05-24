package com.talentoemlinha.dto.Estoque;

import lombok.Data;

@Data
public class NovoEstoqueDto {
    private long idProduto;
    private int quantidade;
    private int estoqueMinimo;
}
