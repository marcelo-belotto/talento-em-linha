package com.talentoemlinha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// import com.talentoemlinha.dto.PontoDto;
import com.talentoemlinha.model.Funcionario;
// import com.talentoemlinha.model.Ponto;
// import com.talentoemlinha.service.PontoService;
import com.talentoemlinha.service.FuncionarioService;

@RestController
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcService;
    // private PontoService pontoService;

    @GetMapping("/funcionario")
    public ResponseEntity<List<Funcionario>> funcionarioGet() {
        return ResponseEntity.status(HttpStatus.OK).body(funcService.retornarTodosFuncionarios());
    }

    @GetMapping("/funcionario/{id}")
    public ResponseEntity<Funcionario> funcionarioGet(@PathVariable int id) {
        Funcionario responseFunc = funcService.retornarFuncionarioPeloId(id);
        if (responseFunc != null)
            return ResponseEntity.status(HttpStatus.OK).body(responseFunc);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    // @PostMapping("/funcionario/{id}/bonificar")
    // public ResponseEntity<Ponto> funcionarioPost(@PathVariable int id,
    // @RequestBody PontoDto pontos) {
    // for (Funcionario funcionario : funcionarios) {
    // if (funcionario.getNp() == id){
    // int indicePonto = pontoService.retornarTodosPontos().size()+1;
    // Ponto novoPonto = new
    // Ponto(indicePonto,id,pontos.getQuantidade(),pontos.getMotivo());
    // funcionario.setTotalDePontos(funcionario.getTotalDePontos() +
    // pontos.getQuantidade());
    // return
    // ResponseEntity.status(HttpStatus.CREATED).body(pontoService.adicionarPonto(novoPonto));
    // }
    // }
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    // }

    @PostMapping("/funcionario")
    public ResponseEntity<Funcionario> funcionarioPost(@RequestBody Funcionario func) {
        Funcionario responseFunc = funcService.adicionarFuncionario(func);
        if (responseFunc == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(func);
    }

    @PutMapping("/funcionario/{id}")
    public ResponseEntity<Funcionario> funcionarioPut(@PathVariable int id, @RequestBody Funcionario func) {
        Funcionario resposeFunc = funcService.alterarFuncionario(id, func);
        if (resposeFunc == null)
            return null;
        return ResponseEntity.status(HttpStatus.CREATED).body(func);
    }

    @DeleteMapping("/funcionario/{id}")
    public ResponseEntity<Funcionario> funcionarioDelete(@PathVariable int id) {
        if (funcService.deletarFuncionario(id) != null)
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
