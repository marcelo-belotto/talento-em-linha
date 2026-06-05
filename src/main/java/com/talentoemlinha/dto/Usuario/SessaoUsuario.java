package com.talentoemlinha.dto.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessaoUsuario {
    private Long np;
    private String nome;
    private String token;
}
