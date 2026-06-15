package com.talentoemlinha.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.dto.Funcionario.FuncionarioDto;
import com.talentoemlinha.model.DetalhesFuncionario;
import com.talentoemlinha.model.Funcionario;
import com.talentoemlinha.repository.DetalhesFuncionarioRepository;
import com.talentoemlinha.service.FuncionarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FuncionarioController {

    private final FuncionarioService funcService;
    private final DetalhesFuncionarioRepository detalhesRepo;

    // @GetMapping("/funcionario")
    // public ResponseEntity<List<Funcionario>> getFuncionario() {
    //     return ResponseEntity.status(HttpStatus.OK).body(funcService.retornarTodosFuncionarios());
    // }

    @GetMapping("/funcionario/{id}")
    public ResponseEntity<FuncionarioDto> getFuncionario(@PathVariable long id) {
        Funcionario responseFunc = funcService.retornarFuncionarioPeloId(id);
        if (responseFunc == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioParaDto(responseFunc));
    }

    @PostMapping("/funcionario")
    public ResponseEntity<FuncionarioDto> postFuncionario(@RequestBody FuncionarioDto funcDto, Authentication authentication) {
        Funcionario func = new Funcionario(
            funcDto.getNp(),
            funcDto.getNome(),
            "123",
            funcDto.getCargo(),
            funcDto.getSetor(),
            funcDto.getRole(),
            funcService.retornarFuncionarioPeloId(Long.parseLong(authentication.getName())),
            null
        );
        Funcionario responseFunc = funcService.adicionarFuncionario(func);
        if (responseFunc == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        var detalhes = detalhesRepo.save(new DetalhesFuncionario(null,func,funcDto.getDataAdmissao().atStartOfDay(),funcDto.getEmail(),funcDto.getTelefone()));
        if (detalhes == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        responseFunc.setDetalhes(detalhes);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioParaDto(responseFunc));
    }

    @PutMapping("/funcionario/{id}")
    public ResponseEntity<Funcionario> putFuncionario(@PathVariable long id, @RequestBody Funcionario func, Authentication authentication) {
        Funcionario resposeFunc = funcService.alterarFuncionario(id, func);
        if (resposeFunc == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(func);
    }

    @DeleteMapping("/funcionario/{id}")
    public ResponseEntity<Funcionario> deleteFuncionario(@PathVariable long id, Authentication authentication) {
        if (funcService.deletarFuncionario(id) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    private FuncionarioDto funcionarioParaDto(Funcionario func){
        FuncionarioDto dto = new FuncionarioDto();
        dto.setNp(func.getNp());
        dto.setNome(func.getNome());
        dto.setSetor(func.getSetor());
        dto.setCargo(func.getCargo());
        dto.setRole(func.getRole());
        dto.setDataAdmissao(func.getDetalhes().getDataAdmissao().toLocalDate());
        dto.setEmail(func.getDetalhes().getEmail());
        dto.setTelefone(func.getDetalhes().getTelefone());
        System.out.println(dto);
        return dto;
    }

}
