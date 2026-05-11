package com.talentoemlinha.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estoque {
    @EqualsAndHashCode.Include
    private long id;
    @EqualsAndHashCode.Include
    private Produto produto;
    private int quantidadeDisponivel;
    private int quantidadeReservada;  
}
