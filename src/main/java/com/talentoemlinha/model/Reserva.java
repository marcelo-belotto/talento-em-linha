package com.talentoemlinha.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reserva {
    @EqualsAndHashCode.Include
    private long id;
    @EqualsAndHashCode.Include
    private Produto produto;
    private Funcionario funcionario;
    private int quantidade;
    private LocalDateTime dataReserva;
    private LocalDateTime dataExpiracao;
    private String status; //Ativa, Retirada, Expirada (Futuramente);
}
