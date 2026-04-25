package com.talentoemlinha.model;

import java.util.ArrayList;
import java.util.List;

import com.talentoemlinha.dto.FuncionarioDto;

import lombok.Data;

@Data
public class Funcionario {
    private int np;
    private String nome;
    private String login;
    private String senha;
    private List<Categoria> pontos;

    public static List<Funcionario> getFuncionariosMocados() {
        List<Funcionario> temp = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Funcionario tempf = new Funcionario();
            tempf.setNome("Funcionario-" + i);
            tempf.setNp(10000000 + i);
            tempf.setLogin(tempf.getNome());
            tempf.setSenha(tempf.getNome() + tempf.getNp());
            tempf.pontos = new ArrayList<Categoria>();
            tempf.pontos.add(new Categoria("CATEGORIA_1", 5));
            tempf.pontos.add(new Categoria("CATEGORIA_2", 7));
            tempf.pontos.add(new Categoria("CATEGORIA_3", 6));
            tempf.pontos.add(new Categoria("CATEGORIA_4", 4));
            tempf.pontos.add(new Categoria("CATEGORIA_5", 3));
            temp.add(tempf);
        }
        return temp;
    }

    public FuncionarioDto toFuncionarioDto() {
        return new FuncionarioDto(this.np, this.nome, this.pontos.stream().map(x -> x.toCategoriaDto()).toList());
    }
}
