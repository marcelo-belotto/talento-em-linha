package com.talentoemlinha.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ponto {
    private long id;
    private long npFuncionario;
    private int quantidade;
    private String motivo;
    private String descricao;
    private LocalDate dataAtribuicao;
}
