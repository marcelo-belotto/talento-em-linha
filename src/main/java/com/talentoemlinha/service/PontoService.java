package com.talentoemlinha.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentoemlinha.model.Ponto;
import com.talentoemlinha.model.Reserva;
import com.talentoemlinha.repository.ReservaRepository;

@Service
public class PontoService {

    private List<Ponto> pontos = new ArrayList<Ponto>();

    @Autowired
    private ReservaRepository reservaRepo;

    public List<Ponto> retornarTodosPontos(){
        return pontos;
    }

    public List<Ponto> retornarPontosPeloNp(long np){
        return pontos.stream().filter(x -> x.getNpFuncionario() == np).toList();
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
        ponto.setId(pontos.size()+1);
        pontos.add(ponto);
        return ponto;
    }
}
