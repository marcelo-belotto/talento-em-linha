package com.talentoemlinha.dto.Reserva;

import java.time.LocalDateTime;

import com.talentoemlinha.model.Produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaReservaFuncionario {
    private long id;
    private Produto produto;
    private LocalDateTime dataReserva;
    private String status;

}
