package com.talentoemlinha.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.model.Brinde;

@RestController
public class BrindeController {
    List<Brinde> brindesMocados = Brinde.brindesMocados();

    @GetMapping("/brinde")
    public List<Brinde> brindesGet(){
        return brindesMocados;
    }

    @GetMapping("/brinde/{id}")
    public Brinde brindesGet(@PathVariable int id){
        for (Brinde brinde : brindesMocados) {
            if (brinde.getId() == id) return brinde;
        }
        return null;
    }

    @PostMapping("/brinde")
    public Brinde brindesPost(@RequestBody Brinde brinde){
        brindesMocados.add(brinde);
        return brinde;
    }

    @DeleteMapping("/brinde/{id}")
    public void brindesDelete(@PathVariable int id){
        brindesMocados.remove(brindesGet(id));
    }

}
