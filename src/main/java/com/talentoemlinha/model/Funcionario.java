package com.talentoemlinha.model;

import java.util.ArrayList;
import java.util.List;

// import com.talentoemlinha.dto.FuncionarioDto;

import lombok.Data;

@Data
public class Funcionario {
    private int np;
    private String nome;
    private String login;
    private String senha;
    private int totalDePontos;
    private String ROLE;
    private static int npControleTemp = 10000001;

    public static List<Funcionario> getFuncionariosMocados() {
        List<Funcionario> temp = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Funcionario tempf = new Funcionario();
            tempf.setNome("Funcionario-" + i);
            tempf.setNp(npControleTemp);
            tempf.setLogin(tempf.getNome());
            tempf.setSenha(tempf.getNome() + tempf.getNp());
            tempf.totalDePontos = 0;
            temp.add(tempf);
            npControleTemp++;
        }
        return temp;
    }

    // public FuncionarioDto toFuncionarioDto() {
    //     return new FuncionarioDto(this.np, this.nome, this.pontos.stream().map(x -> x.toCategoriaDto()).toList());
    // }
}
