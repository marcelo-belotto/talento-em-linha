package com.talentoemlinha.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentoemlinha.dto.Reserva.ListaReservaFuncionario;
import com.talentoemlinha.dto.Reserva.ReservaDto;
import com.talentoemlinha.model.Produto;
import com.talentoemlinha.model.Reserva;
import com.talentoemlinha.repository.ReservaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservaService {
    @Autowired
    private EstoqueService estoqueService;
    @Autowired
    private ReservaRepository reservaRepo;
    @Autowired
    private FuncionarioService funcionarioServ;

    public List<Reserva> retornarReservas() {
        return reservaRepo.findAll();
    }

    public List<ListaReservaFuncionario> retornarPorNp(long np){
        var reservas =  reservaRepo.findByNpFuncionario(np);
        var novaLista = new ArrayList<ListaReservaFuncionario>();
        for (Reserva reserva : reservas) {
            var tempReserva = new ListaReservaFuncionario();
            tempReserva.setId(reserva.getId());
            tempReserva.setDataReserva(reserva.getDataReserva());
            tempReserva.setProduto(reserva.getProduto());
            tempReserva.setStatus(reserva.getStatus());
            novaLista.add(tempReserva);
        }
        return novaLista;
    }

    public List<Reserva> reservar(long np, List<ReservaDto> listaReservasDto) {
        List<Reserva> reservasConfirmadas = new ArrayList<>();
        for (ReservaDto reservaDto : listaReservasDto) {
            Produto produto = estoqueService.consultarSaldo(reservaDto.getIdProduto()).getProduto();

            if (funcionarioServ.descontarPontosReserva(np,produto.getPontos() * reservaDto.getQuantidade())){
                estoqueService.reservar(reservaDto.getIdProduto(), reservaDto.getQuantidade());
        
                Reserva reserva = new Reserva();
                reserva.setNpFuncionario(np);
                reserva.setQuantidade(reservaDto.getQuantidade());
                reserva.setStatus("RESERVADO");
                reserva.setProduto(produto);
                reserva.setDataReserva(LocalDateTime.now());
                reserva.setDataExpiracao(LocalDateTime.now().plusDays(8));
                reservasConfirmadas.add(reserva);
                reservaRepo.save(reserva);
            }
        }
        return reservasConfirmadas;
    }

    public List<Reserva> retirar(long npFuncionario, long npResponsavel) {
        List<Reserva> reservas = reservaRepo.findByNpFuncionario(npFuncionario).stream()
                .filter(x -> x.getStatus().equalsIgnoreCase("RESERVADO")).toList();
        if (reservas != null) {
            for (Reserva reserva : reservas) {
                estoqueService.retirada(reserva.getProduto().getId(), reserva.getQuantidade());
                reserva.setStatus("RETIRADO");
                reservaRepo.save(reserva);
            }

        }
        return reservas;

    }

    public void expirar(Reserva reserva) {
        // Futuramente
    }
}
