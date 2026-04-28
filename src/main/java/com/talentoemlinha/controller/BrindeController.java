package com.talentoemlinha.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Brinde>> brindesGet(){
        return ResponseEntity.status(HttpStatus.OK).body(brindesMocados);
    }

    @GetMapping("/brinde/{id}")
    public ResponseEntity<Brinde> brindesGet(@PathVariable int id){
        for (Brinde brinde : brindesMocados) {
            if (brinde.getId() == id) return ResponseEntity.status(HttpStatus.OK).body(brinde);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/brinde")
    public ResponseEntity<Brinde> brindesPost(@RequestBody Brinde brinde){
        brindesMocados.add(brinde);
        return ResponseEntity.status(HttpStatus.CREATED).body(brinde);
    }

    @DeleteMapping("/brinde/{id}")
    public void brindesDelete(@PathVariable int id){
        brindesMocados.remove(brindesGet(id));
    }

}
