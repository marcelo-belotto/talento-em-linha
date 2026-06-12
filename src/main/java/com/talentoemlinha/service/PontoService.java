package com.talentoemlinha.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.talentoemlinha.model.Ponto;
import com.talentoemlinha.model.Reserva;
import com.talentoemlinha.repository.PontoRepository;
import com.talentoemlinha.repository.ReservaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PontoService {

    private final PontoRepository pontoRepo;
    private final ReservaRepository reservaRepo;

    public List<Ponto> retornarTodosPontos(){
        return pontoRepo.findAll();
    }

    public List<Ponto> retornarPontosPeloNp(long np){
        return pontoRepo.findByNp(np);
    }

    public int retornarPontosDisponiveis(long np){
        return retornarTotalDePontos(np)-retornarTotalDePontosUtilizados(np);
    }

    public int retornarTotalDePontos(long np){
        var pontos = retornarPontosPeloNp(np);
        int totalDePontos = 0;
        for (Ponto ponto : pontos) {
            totalDePontos += ponto.getQuantidade();
        }
        return totalDePontos;
    }

    public int retornarTotalDePontosUtilizados(long np){
        var reservas = reservaRepo.findByNpFuncionario(np);
        int totalDePontosUsados = 0;
        for (Reserva reserva : reservas) {
            totalDePontosUsados += (reserva.getProduto().getPontos()*reserva.getQuantidade());
        }
        return totalDePontosUsados;
    }

    public Ponto adicionarPonto(Ponto ponto){
        return pontoRepo.save(ponto);
    }
}
