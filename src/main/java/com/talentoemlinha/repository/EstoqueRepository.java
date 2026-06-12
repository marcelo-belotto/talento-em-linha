package com.talentoemlinha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.talentoemlinha.model.Estoque;
import com.talentoemlinha.model.Produto;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    @Query("SELECT u FROM Estoque u WHERE u.produto = :produto")
    Optional<Estoque> findByProduto(@Param("produto") Produto produto);
    
}
