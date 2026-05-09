package com.talentoemlinha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.dto.ReservaDto;
import com.talentoemlinha.model.Reserva;
import com.talentoemlinha.service.ReservaService;

@RestController
public class ReservaController {

    @Autowired
    private ReservaService reservaServ;

    @GetMapping("/reserva")
    public List<Reserva> getReserva() {
        return reservaServ.retornarReservas();
    }

    @PostMapping("/reserva")
    public Reserva postReserva(@RequestBody ReservaDto reservaDto) {
        return reservaServ.reservar(reservaDto.getIdProduto(), reservaDto.getNpFuncionario(),
                reservaDto.getQuantidade());
    }

    @PostMapping("/reserva/retirar/{np}")
    public List<Reserva> postRetirada(@PathVariable long np) {
        return reservaServ.retirar(np);
    }
}
