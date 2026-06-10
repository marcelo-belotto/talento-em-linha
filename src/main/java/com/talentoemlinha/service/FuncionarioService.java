package com.talentoemlinha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentoemlinha.dto.Funcionario.ReservaFuncionarioResponse;
import com.talentoemlinha.model.Funcionario;
import com.talentoemlinha.repository.DetalhesFuncionarioRepository;
import com.talentoemlinha.repository.FuncionarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepo;
    private final DetalhesFuncionarioRepository detalhesRepo;

    public List<Funcionario> retornarTodosFuncionarios() {
        return funcionarioRepo.findAll();
    }

    public Funcionario retornarFuncionarioPeloId(long id) {
        return funcionarioRepo.findById(id).orElse(null);
    }

    public ReservaFuncionarioResponse retornarReservasPeloIdFuncionario(long id) {
        var funcionario = retornarFuncionarioPeloId(id);
        var funcResponse = new ReservaFuncionarioResponse();
        funcResponse.setNp(funcionario.getNp());
        funcResponse.setNome(funcionario.getNome());
        funcResponse.setSetor(funcionario.getSetor());
        funcResponse.setCargo(funcionario.getCargo());
        funcResponse.setReservas(null);
        return funcResponse;
    }

    public Funcionario adicionarFuncionario(Funcionario func) {
        return funcionarioRepo.save(func);
    }

    public Funcionario alterarFuncionario(long id, Funcionario novoFunc) {
        return funcionarioRepo.save(novoFunc);
    }

    // public boolean bonificarFuncionario(long np, int quantidade) {
    //     Funcionario func = retornarFuncionarioPeloId(np);
    //     if (func == null)
    //         return false;
    //     func.setTotalDePontos(func.getTotalDePontos() + quantidade);
    //     funcionarioRepo.save(func);
    //     return true;
    // }

    // public boolean descontarPontosReserva(long np, int quantidade) {
    //     Funcionario func = retornarFuncionarioPeloId(np);
    //     if (func == null)
    //         return false;
    //     if (func.getTotalDePontos() < quantidade) return false;
    //     func.setTotalDePontos(func.getTotalDePontos() - quantidade);
    //     func.setPontosUtilizados(func.getPontosUtilizados() + quantidade);
    //     funcionarioRepo.save(func);
    //     return true;
    // }
    
    // public boolean estornarPontosReserva(long np, int quantidade) {
    //     Funcionario func = retornarFuncionarioPeloId(np);
    //     if (func == null)
    //         return false;
    //     func.setTotalDePontos(func.getTotalDePontos() + quantidade);
    //     func.setPontosUtilizados(func.getPontosUtilizados() - quantidade);
    //     funcionarioRepo.save(func);
    //     return true;
    // }

    public Funcionario deletarFuncionario(long id) {
        Funcionario temp = retornarFuncionarioPeloId(id);
        if (temp == null)
            return null;
        return temp;
    }
}
