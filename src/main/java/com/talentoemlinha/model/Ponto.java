package com.talentoemlinha.model;

import java.time.LocalDateTime;

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
    private LocalDateTime dataAtribuicao;
}
