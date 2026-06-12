package com.talentoemlinha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.talentoemlinha.model.Ponto;

@Repository
public interface PontoRepository extends JpaRepository<Ponto,Long> {
    
    @Query("SELECT u FROM Ponto u WHERE u.npFuncionario = :np")
    List<Ponto> findByNp(@Param("np") Long np);
}
