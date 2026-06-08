package com.talentoemlinha.dto.Funcionario;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDto {
    private long np;
    private String nome;
    private String setor;
    private String cargo;
    private String role;
    private LocalDate dataAdmissao;
    private String email;
    private String telefone;
}
