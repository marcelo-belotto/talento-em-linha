package com.talentoemlinha.dto;

import java.util.List;

import com.talentoemlinha.model.Categoria;

import lombok.Data;

@Data
public class FuncionarioDto {
    private int np;
    private String nome;
    private List<CategoriaDto> pontos;

    public FuncionarioDto() {
    }

    public FuncionarioDto(int np, String nome, List<CategoriaDto> pontos){
        this.np = np;
        this.nome = nome;
        this.pontos = pontos;
    }
}
