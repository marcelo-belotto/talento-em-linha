package com.talentoemlinha.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ponto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ponto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long npFuncionario;
    private int quantidade;
    private String motivo;
    private String descricao;
    private LocalDate dataAtribuicao;
}
