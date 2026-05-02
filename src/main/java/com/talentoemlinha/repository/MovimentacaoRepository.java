package com.talentoemlinha.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.talentoemlinha.model.Movimentacao;

@Component
public class MovimentacaoRepository {

    private List<Movimentacao> listaMovimentacoes = new ArrayList<>();

    public Movimentacao save(Movimentacao movimentacao) {
        listaMovimentacoes.add(movimentacao);
        return movimentacao;
    }

}
