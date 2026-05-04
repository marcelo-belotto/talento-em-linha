package com.talentoemlinha.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.talentoemlinha.model.Funcionario;

@Service
public class FuncionarioService {
    private List<Funcionario> funcionarios = Funcionario.getFuncionariosMocados();

    public List<Funcionario> retornarTodosFuncionarios(){
        return funcionarios;
    }

    public Funcionario retornarFuncionarioPeloId(long id){
        return funcionarios.stream().filter(x -> x.getNp()==id).findFirst().orElse(null);
    }

    public Funcionario adicionarFuncionario(Funcionario func){
        if (funcionarios.stream().anyMatch(x -> x.equals(func)))
            return null;
        funcionarios.add(func);
        return func;
    }

    public Funcionario alterarFuncionario(long id, Funcionario novoFunc){
        Funcionario temp = retornarFuncionarioPeloId(id);
        if (temp == null)
            return null;
        funcionarios.remove(temp);
        funcionarios.add(novoFunc);
        return novoFunc;
    }

    public Funcionario deletarFuncionario(long id){
        Funcionario temp = retornarFuncionarioPeloId(id);
        if (temp == null)
            return null;
        return temp;
    }
}
