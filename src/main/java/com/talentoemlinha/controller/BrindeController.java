package com.talentoemlinha.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.talentoemlinha.model.Brinde;

@RestController
public class BrindeController {

    @GetMapping("/api/brinde")
    public List<Brinde> brindesGet(){
        return null;
    }

    @GetMapping("/api/brinde/{id}")
    public Brinde brindesGet(@PathVariable int id){
        return null;
    }

    @PostMapping("/api/brinde")
    public List<Brinde> brindesPost(@RequestBody Brinde brinde){
        return null;
    }

}
