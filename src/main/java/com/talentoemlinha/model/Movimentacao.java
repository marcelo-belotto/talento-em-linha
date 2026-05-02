package com.talentoemlinha.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movimentacao {
    private long id;
    private Produto produto;
    private String tipoMovimentacao;
    private int quantidade;
    private LocalDateTime dataHora = LocalDateTime.now();
    
}
