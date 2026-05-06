package com.talentoemlinha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ProdutoService produtoService;
    @Autowired
    private ReservaRepository reservaRepo;

    public Reserva reservar(long idProduto, long npFuncionario, int quantidade) {
        Produto produto = produtoService.buscarProduto(idProduto);

        if (produto == null) return null;
        
        estoqueService.reservar(idProduto, quantidade);

        Reserva reserva = new Reserva();
        reserva.setNpFuncionario(npFuncionario);
        reserva.setQuantidade(quantidade);
        reserva.setStatus("RESERVADO");
        reserva.setProduto(produto);

        reservaRepo.save(reserva);
        /*
         * TO-DO: Lógica ao reservar um produto:
         * Após encontrar o produto, mover o saldo para quantidadeReservada através de
         * um novo método em estoqueService
         * Após mover o saldo, criar uma nova Reserva e salvar no repositório de
         * reservas.
         */

        return reserva;

    }

    public Reserva retirar(long idReserva) {

        /*
         * TO-DO:
         * Buscar uma reserva ativa e confirmar retirada (novo metodo em estoqueService)
         * Salvar no repositorio de reservas que foi retirado
         */

        return null;

    }

    public void expirar(Reserva reserva) {
        // Futuramente
    }
}
