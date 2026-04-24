package com.talentoemlinha.model;

import lombok.Data;

@Data
public class Categoria {
    private int id;
    private String categoria;
    private int pontos;
    
    public Categoria(String categoria, int pontos) {
        this.categoria = categoria;
        this.pontos = pontos;
    }
    
}
