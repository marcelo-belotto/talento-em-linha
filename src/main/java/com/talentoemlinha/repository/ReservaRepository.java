package com.talentoemlinha.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.talentoemlinha.model.Reserva;

@Repository
public class ReservaRepository {
    private List<Reserva> listaReservas = new ArrayList<>();

    public Reserva save(Reserva reserva) {
        if (listaReservas.contains(reserva)) {
            listaReservas.remove(reserva);
        } else {
            reserva.setId(listaReservas.size() + 1);
        }
        listaReservas.add(reserva);
        return reserva;
    }

    public List<Reserva> findAll() {
        return listaReservas;
    }

    public Reserva findById(long id) {
        return listaReservas.stream().filter(x -> x.getId() == id).findFirst()
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
    }

    public List<Reserva> findByNpFuncionario(long np) {
        return listaReservas.stream().filter(x -> x.getNpFuncionario() == np).toList();
    }
}
