package com.talentoemlinha.dto.Reserva;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaRetiradaDto {
    private long npAlmoxarife;
    private long npFuncionario;
    private List<Long> idReservas;
}
