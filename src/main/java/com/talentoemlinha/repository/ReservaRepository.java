package com.talentoemlinha.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.talentoemlinha.model.Reserva;

@Repository
public class ReservaRepository {
    private List<Reserva> listaReservas = new ArrayList<>();

    public Reserva save(Reserva reserva){
        listaReservas.add(reserva);
        return reserva;
    }
    
}
