package com.talentoemlinha.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.talentoemlinha.model.Ponto;

@Service
public class PontoService {
    private List<Ponto> pontos = new ArrayList<Ponto>();

    public List<Ponto> retornarTodosPontos(){
        return pontos;
    }

    public Ponto adicionarPonto(Ponto ponto){
        pontos.add(ponto);
        return ponto;
    }
}
