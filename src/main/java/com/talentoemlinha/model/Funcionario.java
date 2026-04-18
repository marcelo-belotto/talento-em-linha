package com.talentoemlinha.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lombok.Data;

@Data
public class Funcionario {
    private int np;
    private String nome;
    private String login;
    private String senha;
    private Map<Categoria,Integer> pontos; //Implementação inicial, não sei bem ainda como vou fazer isso

    public static List<Funcionario> getFuncionariosMocados(){
        List<Funcionario> temp = new ArrayList<>();
        for(int i = 1; i < 11; i++){
            Funcionario tempf = new Funcionario();
            tempf.setNome("Funcionario-"+ i);
            tempf.setNp(10000000+i);
            tempf.setLogin(tempf.getNome());
            tempf.setSenha(tempf.getNome()+tempf.getNp());
            tempf.pontos = new TreeMap<Categoria,Integer>();
            tempf.pontos.put(Categoria.CATEGORIA_1, 5);
            tempf.pontos.put(Categoria.CATEGORIA_2, 7);
            tempf.pontos.put(Categoria.CATEGORIA_3, 6);
            tempf.pontos.put(Categoria.CATEGORIA_4, 4);
            tempf.pontos.put(Categoria.CATEGORIA_5, 3);
            temp.add(tempf);
        }
        return temp;
    }
}
