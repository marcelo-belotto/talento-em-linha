package com.talentoemlinha.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.talentoemlinha.model.Movimentacao;

@Repository
public class MovimentacaoRepository {

    private List<Movimentacao> listaMovimentacoes = new ArrayList<>();

    public Movimentacao save(Movimentacao movimentacao) {
        movimentacao.setId(listaMovimentacoes.size()+1);
        listaMovimentacoes.add(movimentacao);
        return movimentacao;
    }

}
