package com.talentoemlinha.dto.Estoque;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarEstoqueDto {
    private long idProduto;
    private int quantidade;
    private LocalDate data;
    private String observacao;
}
