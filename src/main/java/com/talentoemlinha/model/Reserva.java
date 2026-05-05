package com.talentoemlinha.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Reserva {
    private long id;
    private Produto produto;
    private long npFuncionario;
    private int quantidade;
    private LocalDateTime dataExpiracao;
    private String status; //Ativa, Retirada, Expirada (Futuramente);
}
