package com.talentoemlinha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentoemlinha.model.Funcionario;
import com.talentoemlinha.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepo;

    public List<Funcionario> retornarTodosFuncionarios(){
        return funcionarioRepo.findAll();
    }

    public Funcionario retornarFuncionarioPeloId(long id){
        return funcionarioRepo.findById(id);
    }

    public Funcionario adicionarFuncionario(Funcionario func){
        return funcionarioRepo.save(func);
    }

    public Funcionario alterarFuncionario(long id, Funcionario novoFunc){
        return funcionarioRepo.save(novoFunc);
    }

    public Funcionario deletarFuncionario(long id){
        Funcionario temp = retornarFuncionarioPeloId(id);
        if (temp == null)
            return null;
        return temp;
    }
}
