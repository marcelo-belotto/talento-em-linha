package com.talentoemlinha.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentoemlinha.model.Estoque;
import com.talentoemlinha.model.Funcionario;
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

    public Reserva reservar(long idProduto, long npFuncionario, int quantidade) {
        Estoque estoque = estoqueService.consultarSaldo(idProduto);
        if (estoque == null) return null;
        Funcionario funcionario = funcionarioServ.retornarFuncionarioPeloId(npFuncionario);
        if (funcionario == null) return null;
        if (funcionario.getTotalDePontos() < (estoque.getProduto().getPontos()*quantidade)) throw new RuntimeException("Funcionário possui pontos insuficientes!");
        
        funcionario.setTotalDePontos(funcionario.getTotalDePontos()-estoque.getProduto().getPontos()*quantidade);
        funcionario.setPontosUtilizados(funcionario.getPontosUtilizados()+estoque.getProduto().getPontos()*quantidade);
        funcionarioServ.adicionarFuncionario(funcionario);

        estoqueService.reservar(idProduto, quantidade);

        Reserva reserva = new Reserva();
        reserva.setNpFuncionario(npFuncionario);
        reserva.setQuantidade(quantidade);
        reserva.setStatus("RESERVADO");
        reserva.setProduto(estoque.getProduto());
        reserva.setDataExpiracao(LocalDateTime.now().plusDays(8));

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
