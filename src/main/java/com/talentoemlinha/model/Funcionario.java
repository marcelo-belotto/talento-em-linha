package com.talentoemlinha.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "funcionario")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Funcionario {

    @Id
    @EqualsAndHashCode.Include
    private Long np; // Long, não long

    private String nome;
    private String hash;
    private String cargo;
    private String setor;
    private String role;

    @ManyToOne(fetch = FetchType.LAZY) // gestor tem N subordinados
    @JoinColumn(name = "gestor_np") // coluna FK separada, não "np"
    private Funcionario gestor;

    @OneToOne(mappedBy = "funcionario", fetch = FetchType.LAZY)
    private DetalhesFuncionario detalhes;

}