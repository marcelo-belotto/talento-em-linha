package com.talentoemlinha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentoemlinha.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
    // private List<Funcionario> listaFuncionario = Funcionario.getFuncionariosMocados();

    // public List<Funcionario> findAll(){
    //     return listaFuncionario;
    // }

    // public Funcionario findById(long npFuncionario){
    //     return listaFuncionario.stream().filter(x -> x.getNp()==npFuncionario).findFirst()
    //         .orElse(null);
    // }

    // public Funcionario save(Funcionario funcionario){
    //     if (listaFuncionario.contains(funcionario)){
    //         int index = listaFuncionario.indexOf(funcionario);
    //         listaFuncionario.remove(index);
    //         listaFuncionario.add(funcionario);
    //     }else{
    //         listaFuncionario.add(funcionario);
    //     }
    //     return funcionario;
    // }
}
