package com.talentoemlinha.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

/**
 * Configuração central do Spring Security.
 *
 * DECISÃO DE ARQUITETURA — Sessão HTTP vs JWT:
 * Este projeto usa Thymeleaf (server-side rendering). JWT é adequado para
 * APIs REST consumidas por SPAs desacopladas. Para aplicações MVC com
 * templates no servidor, a sessão HTTP gerenciada pelo Spring Security
 * é a abordagem correta: mais simples, segura por padrão (JSESSIONID via cookie
 * HttpOnly), e sem necessidade de gerenciar tokens no cliente.
 *
 * PROBLEMAS CORRIGIDOS:
 * 1. Registra DaoAuthenticationProvider com o CustomUserDetailsService e PasswordEncoder.
 *    Antes, o Spring usava o provider padrão (in-memory) e ignorava o nosso serviço.
 * 2. Adiciona tratamento de erro de login (/login?error) e logout.
 * 3. Mantém a sessão ativa até o logout explícito (comportamento padrão do Spring).
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final CustomSuccessHandler customSuccessHandler;

    /**
     * PasswordEncoder: NoOpPasswordEncoder mantém compatibilidade com o hash
     * em plain text que já existe no repositório/banco.
     *
     * IMPORTANTE: em produção, migre para BCryptPasswordEncoder e re-hash as senhas.
     * Troque para: return new BCryptPasswordEncoder();
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * Provider que conecta o UserDetailsService ao mecanismo de autenticação.
     * Sem isso, o Spring Security não sabe como carregar o usuário pelo NP.
     */
@Bean
public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
}

    /**
     * Expõe o AuthenticationManager como bean — útil se futuramente precisar
     * autenticar programaticamente (ex: em testes ou endpoints específicos).
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // CSRF: desabilitado. Habilite em produção se não houver razão para manter desabilitado.
            // Para formulários Thymeleaf, o CSRF pode ser habilitado facilmente com th:action.
            .csrf(csrf -> csrf.disable())

            // Registra o provider customizado
            .authenticationProvider(authenticationProvider())

            .authorizeHttpRequests(authorize -> authorize
                // Recursos públicos
                .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                // API interna (endpoints REST utilizados pelo frontend JS)
                .requestMatchers("/api/v1/**").permitAll()
                // A página de login é pública
                .requestMatchers("/").permitAll()
                // Tudo mais exige autenticação
                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginPage("/")                        // GET "/" exibe o formulário de login
                .loginProcessingUrl("/")               // POST "/" processa as credenciais
                .usernameParameter("usuario")          // campo "usuario" no HTML = NP/matrícula
                .passwordParameter("senha")            // campo "senha" no HTML
                .successHandler(customSuccessHandler)  // redireciona por role após login
                .failureUrl("/?erro=true")             // redireciona com parâmetro de erro
                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/?logout=true")
                .invalidateHttpSession(true)   // invalida a sessão no servidor
                .deleteCookies("JSESSIONID")   // remove o cookie da sessão no cliente
                .permitAll()
            )

            // Gerenciamento de sessão: uma sessão por usuário
            .sessionManagement(session -> session
                .maximumSessions(1)            // impede múltiplas sessões simultâneas
            );

        return http.build();
    }
}
