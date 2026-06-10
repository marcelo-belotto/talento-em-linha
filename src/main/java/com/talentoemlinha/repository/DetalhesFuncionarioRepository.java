package com.talentoemlinha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentoemlinha.model.DetalhesFuncionario;

@Repository
public interface DetalhesFuncionarioRepository extends JpaRepository<DetalhesFuncionario,Long>  {

}

