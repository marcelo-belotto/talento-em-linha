package com.talentoemlinha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentoemlinha.model.Movimentacao;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{

}
