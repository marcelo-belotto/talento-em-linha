package com.talentoemlinha.model;

import java.util.ArrayList;
import java.util.List;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Funcionario {
    @EqualsAndHashCode.Include
    private long np;
    private String nome;
    private String hash;
    private String cargo;
    private String setor;
    private String role;
    private static int npControleTemp = 10000001;

    public static List<Funcionario> getFuncionariosMocados() {
        List<Funcionario> temp = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Funcionario tempf = new Funcionario();
            tempf.setNome("Funcionario-" + i);
            tempf.setNp(npControleTemp);
            tempf.setHash("123");
            if (i % 3 == 0){
                tempf.setRole("USER");
                tempf.setCargo("Colaborador");
                tempf.setSetor("Produção");
            }else if (i % 4 == 0){
                tempf.setRole("ALMOXARIFE");

                tempf.setCargo("Almoxarife");
                tempf.setSetor("Almoxarifado");
            }else{
                tempf.setRole("ADMIN");
                tempf.setCargo("Coordenador");
                tempf.setSetor("Produção");
            }
            temp.add(tempf);
            npControleTemp++;
        }
        return temp;
    }

}
