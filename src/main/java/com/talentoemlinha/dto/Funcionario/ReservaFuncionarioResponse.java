package com.talentoemlinha.dto.Funcionario;

import java.util.List;

import com.talentoemlinha.dto.Reserva.ListaReservaFuncionario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaFuncionarioResponse {
    private long np;
    private String nome;
    private String cargo;
    private String setor;
    private List<ListaReservaFuncionario> reservas;
}
