package com.talentoemlinha.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.talentoemlinha.model.Funcionario;
import com.talentoemlinha.service.FuncionarioService;

import lombok.RequiredArgsConstructor;

/**
 * Serviço responsável por carregar os dados do usuário para o Spring Security.
 *
 * PROBLEMA ANTERIOR: a classe não estava anotada com @Service,
 * por isso o Spring nunca a registrava no contexto e a autenticação
 * era tratada pelo provider padrão (in-memory), ignorando o banco/repositório.
 *
 * O campo "username" que chega do formulário é o NP (matrícula) do funcionário,
 * conforme configurado em SecurityConfig (.usernameParameter("usuario")).
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final FuncionarioService funcService;

    @Override
    public UserDetails loadUserByUsername(String npFuncionario) throws UsernameNotFoundException {
        long np;
        try {
            np = Long.parseLong(npFuncionario);
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("Matrícula inválida: " + npFuncionario);
        }

        Funcionario usuario = funcService.retornarFuncionarioPeloId(np);

        if (usuario == null) {
            throw new UsernameNotFoundException("Funcionário não encontrado: " + npFuncionario);
        }

        /*
         * O username armazenado na sessão do Spring Security será o NP (como String),
         * não o nome. Isso permite recuperar o funcionário a qualquer momento
         * via Authentication.getName() nos controllers.
         *
         * Senha: {noop} indica que não há encoding (plain text).
         * Em produção, substitua por BCrypt e armazene o hash no banco.
         */
        return User.withUsername(String.valueOf(usuario.getNp()))
                .password("{noop}" + usuario.getHash())
                .roles(usuario.getRole())
                .build();
    }
}
