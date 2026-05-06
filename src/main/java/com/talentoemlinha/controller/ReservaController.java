package com.talentoemlinha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.dto.ReservaDto;
import com.talentoemlinha.model.Reserva;
import com.talentoemlinha.repository.ReservaRepository;
import com.talentoemlinha.service.ReservaService;


@RestController
public class ReservaController {
    
    @Autowired
    private ReservaRepository reservaRepo;
    @Autowired
    private ReservaService reservaServ;

    @GetMapping("/reserva")
    public List<Reserva> reservaGet(){
        return reservaRepo.findAll();
    }

    @PostMapping("/reserva")
    public Reserva reservaPost(@RequestBody ReservaDto reservaDto){
        return reservaServ.reservar(reservaDto.getIdProduto(), reservaDto.getNpFuncionario(), reservaDto.getQuantidade());
    }

}
