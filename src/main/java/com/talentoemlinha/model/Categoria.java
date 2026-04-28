package com.talentoemlinha.model;

// import com.talentoemlinha.dto.CategoriaDto;

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
   
    // public CategoriaDto toCategoriaDto(){
    //     return new CategoriaDto(this.categoria, this.pontos);
    // }
}
