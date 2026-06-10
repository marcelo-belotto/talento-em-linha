package com.talentoemlinha.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// DetalhesFuncionario.java
@Entity
@Table(name = "detalhes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // protege contra lazy em equals
public class DetalhesFuncionario {

    @Id
    @EqualsAndHashCode.Include
    private Long id; // mesmo tipo da PK de Funcionario

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId           // compartilha o ID com Funcionario
    @JoinColumn(name = "funcionario")
    private Funcionario funcionario;

    private LocalDateTime dataAdmissao;
    private String email;
    private String telefone;
}
