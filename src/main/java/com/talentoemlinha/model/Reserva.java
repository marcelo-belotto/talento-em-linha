package com.talentoemlinha.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;
    @EqualsAndHashCode.Include
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Funcionario funcionario;
    private int quantidade;
    private LocalDateTime dataReserva;
    private LocalDateTime dataExpiracao;
    private String status; //Ativa, Retirada, Expirada (Futuramente);
}
