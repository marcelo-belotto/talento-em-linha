package com.talentoemlinha.dto;

import lombok.Data;

@Data
public class CategoriaDto {
    private String categoria;
    private int pontos;
    
    public CategoriaDto(String categoria, int pontos) {
        this.categoria = categoria;
        this.pontos = pontos;
    }
}
