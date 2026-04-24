package com.talentoemlinha.controller;

import java.util.ArrayList;
import java.util.List;

// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.dto.EstoqueDto;
import com.talentoemlinha.model.Brinde;
import com.talentoemlinha.model.Estoque;

@RestController
public class EstoqueController {
    List<Estoque> estoqueMock = new ArrayList<>();

    @GetMapping("/estoque")
    public List<Estoque> estoqueGet() {
        return estoqueMock;
    }

    @GetMapping("/estoque/{id}")
    public Estoque estoqueGet(@PathVariable int id) {
        for (Estoque estoque : estoqueMock) {
            if (id == estoque.getId())
                return estoque;
        }
        return null;
    }

    @PostMapping("/estoque")
    public Estoque estoquePost(@RequestBody EstoqueDto estoqueDto) {
        Estoque temp = new Estoque();
        temp.setId(estoqueMock.size() + 1); // Não vou precisar depois de mapear, ja que a entidade se encarrega disso
        Brinde brindeTemp = Brinde.brindesMocados().get(estoqueDto.getIdBrinde()-1); //TEMPORÀRIO VAI DAR ERRO COM BRINDES NOVOS CADASTRADOS DEVIDO AO METODO STATIC
        if (brindeTemp == null) return null; // Melhorar a resposta http
        temp.setBrinde(brindeTemp);
        temp.setQuantidade(estoqueDto.getQuantidade());
        estoqueMock.add(temp);
        return temp;
    }
}