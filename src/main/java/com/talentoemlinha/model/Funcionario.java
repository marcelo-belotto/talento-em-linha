package com.talentoemlinha.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Funcionario {
    @EqualsAndHashCode.Include
    private long np;
    private String nome;
    private String hash;
    private String cargo;
    private String setor;
    private String role;
    private Funcionario gestor;
    private DetalhesFuncionario detalhes;
    private static int npControleTemp = 10000001;

    public static List<Funcionario> getFuncionariosMocados() {
        List<Funcionario> temp = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Funcionario tempf = new Funcionario();
            tempf.setNome("Funcionario-" + i);
            tempf.setNp(npControleTemp);
            tempf.setHash("123");
            tempf.setDetalhes(new DetalhesFuncionario(LocalDateTime.now(),"funcionario"+i+"@empresa.com","(10) 98765-543"+i));
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
            tempf.setGestor(null);
            if (i > 2) tempf.setGestor(temp.stream().filter(x -> x.getNp()== 10000001).findFirst().orElse(null));
            temp.add(tempf);
            npControleTemp++;
        }
        return temp;
    }

}
