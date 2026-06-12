package com.talentoemlinha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.dto.Funcionario.ReservaFuncionarioResponse;
import com.talentoemlinha.dto.Reserva.ReservaDto;
import com.talentoemlinha.dto.Reserva.ReservaRetiradaDto;
import com.talentoemlinha.model.Funcionario;
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

    private long getNpLogado(Authentication authentication) {
        return Long.parseLong(authentication.getName());
    }

    @GetMapping("/reserva")
    public List<Reserva> getReserva() {
        return reservaServ.retornarReservas();
    }

    @PostMapping("/reservas")
    public ReservaFuncionarioResponse getReservaById(Authentication authentication) {
        var funcionario = funcServ.retornarReservasPeloIdFuncionario(getNpLogado(authentication));
        funcionario.setReservas(reservaServ.retornarPorNp(funcionario.getNp()).stream()
                .filter(x -> x.getStatus().equalsIgnoreCase("reservado")).toList());
        return funcionario;
    }

    @PostMapping("/reserva")
    public List<Reserva> postReserva(@RequestBody List<ReservaDto> reservaDto,Authentication authentication) {
        long np = getNpLogado(authentication);
        return reservaServ.reservar(np, reservaDto);
    }

    @PostMapping("/reserva/retirar/")
    public List<Reserva> postRetirada(@RequestBody ReservaRetiradaDto reservaDto,Authentication authentication) {
        return reservaServ.retirar(reservaDto.getNpFuncionario(), reservaDto.getNpAlmoxarife());
    }
}
