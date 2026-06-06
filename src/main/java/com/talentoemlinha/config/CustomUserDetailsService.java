package com.talentoemlinha.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.talentoemlinha.model.Funcionario;
import com.talentoemlinha.service.FuncionarioService;

import lombok.RequiredArgsConstructor;

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

        return User.withUsername(String.valueOf(usuario.getNp()))
                .password(usuario.getHash())
                .roles(usuario.getRole())
                .build();
    }
}
