package com.talentoemlinha.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalhesFuncionario {
    private LocalDateTime dataAdmissao;
    private String email;
    private String telefone;

}
