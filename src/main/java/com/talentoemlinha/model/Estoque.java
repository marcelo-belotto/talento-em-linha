package com.talentoemlinha.model;


import lombok.Data;

@Data
public class Estoque {
    private int id;
    private Brinde brinde;
    private int quantidade;
}
