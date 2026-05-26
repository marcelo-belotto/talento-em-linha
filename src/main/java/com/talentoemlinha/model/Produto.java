package com.talentoemlinha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {
    @EqualsAndHashCode.Include
    private long id;
    @EqualsAndHashCode.Include
    private String nome;
    private String descricao;
    private String categoria;
    private int pontos;
}
