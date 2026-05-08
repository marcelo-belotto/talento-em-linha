package com.talentoemlinha.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Reserva {
    @EqualsAndHashCode.Include
    private long id;
    @EqualsAndHashCode.Include
    private Produto produto;
    private long npFuncionario;
    private int quantidade;
    private LocalDateTime dataExpiracao;
    private String status; //Ativa, Retirada, Expirada (Futuramente);
}
