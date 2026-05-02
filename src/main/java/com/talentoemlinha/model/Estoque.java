package com.talentoemlinha.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estoque {
    private int id;
    private Produto produto;
    private int quantidade;
}
