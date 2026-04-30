package com.talentoemlinha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ponto {
    private int id;
    private int npFuncionario;
    private int quantidade;
    private String motivo;
}
