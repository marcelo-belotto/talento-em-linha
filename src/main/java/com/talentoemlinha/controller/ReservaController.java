package com.talentoemlinha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.dto.Funcionario.ReservaFuncionarioResponse;
import com.talentoemlinha.dto.Reserva.ReservaDto;
import com.talentoemlinha.dto.Reserva.ReservaRetiradaDto;
import com.talentoemlinha.model.Reserva;
import com.talentoemlinha.service.FuncionarioService;
import com.talentoemlinha.service.ReservaService;

@RestController
@RequestMapping("/api/v1")
public class ReservaController {

    @Autowired
    private ReservaService reservaServ;
    @Autowired
    private FuncionarioService funcServ;

    @GetMapping("/reserva")
    public List<Reserva> getReserva() {
        return reservaServ.retornarReservas();
    }

    @PostMapping("/{np}/reservas")
    public ReservaFuncionarioResponse getReservaById(@PathVariable long np) {
        var funcionario = funcServ.retornarReservasPeloIdFuncionario(np);
        funcionario.setReservas(reservaServ.retornarPorNp(np).stream().filter(x -> x.getStatus().equalsIgnoreCase("reservado")).toList());
        return funcionario;
    }

    @PostMapping("/reserva/{np}")
    public List<Reserva> postReserva(@PathVariable long np,@RequestBody List<ReservaDto> reservaDto) {
        return reservaServ.reservar(np,reservaDto);
    }

    @PostMapping("/reserva/retirar/")
    public List<Reserva> postRetirada(@RequestBody ReservaRetiradaDto reservaDto) {
        return reservaServ.retirar(reservaDto.getNpFuncionario(),reservaDto.getNpAlmoxarife());
    }
}
