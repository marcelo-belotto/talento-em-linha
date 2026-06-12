package com.talentoemlinha.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private final EstoqueService estoqueService;
    private final ReservaRepository reservaRepo;
    private final FuncionarioService funcionarioServ;
    private final PontoService pontoServ;

    public List<Reserva> retornarReservas() {
        return reservaRepo.findAll();
    }

    public List<ListaReservaFuncionario> retornarPorNp(long np) {
        var reservas = reservaRepo.findByNpFuncionario(np);
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

        List<Produto> listaProdutos = new ArrayList<>();
        for (ReservaDto reserva : listaReservasDto) {
            listaProdutos.add(estoqueService.consultarSaldo(reserva.getIdProduto()).getProduto());
        }

        int totalNecessario = listaProdutos.stream()
                .mapToInt(x -> x.getPontos())
                .sum();

        if (totalNecessario == 0 || pontoServ.retornarPontosDisponiveis(np) < totalNecessario) return null;

        List<Reserva> reservasConfirmadas = new ArrayList<>();
        for (ReservaDto reservaDto : listaReservasDto) {
            Produto produto = estoqueService.consultarSaldo(reservaDto.getIdProduto()).getProduto();

            estoqueService.reservar(reservaDto.getIdProduto(), reservaDto.getQuantidade());

            Reserva reserva = new Reserva();
            reserva.setFuncionario(funcionarioServ.retornarFuncionarioPeloId(np));
            reserva.setQuantidade(reservaDto.getQuantidade());
            reserva.setStatus("RESERVADO");
            reserva.setProduto(produto);
            reserva.setDataReserva(LocalDateTime.now());
            reserva.setDataExpiracao(LocalDateTime.now().plusDays(8));
            reservasConfirmadas.add(reserva);
            reservaRepo.save(reserva);
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
