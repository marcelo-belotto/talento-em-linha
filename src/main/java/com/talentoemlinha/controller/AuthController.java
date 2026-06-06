package com.talentoemlinha.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller responsável apenas pela exibição da tela de login.
 *
 * IMPORTANTE: o POST de "/" NÃO passa por aqui.
 * O Spring Security intercepta o POST para "/login-processing"
 * (loginProcessingUrl)
 * antes de qualquer controller. O AuthController só precisa servir o GET da
 * tela.
 *
 * Se o usuário já estiver autenticado e tentar acessar "/", é redirecionado
 * para a página inicial correspondente ao seu role.
 */
@Controller
public class AuthController {

    @GetMapping("/")
    public String loginTela(Authentication authentication) {
        // Se já estiver logado, redireciona para evitar que veja a tela de login
        // novamente
        if (authentication != null && authentication.isAuthenticated()) {
            boolean isAlmoxarife = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ALMOXARIFE"));
            boolean isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

            if (isAdmin)
                return "redirect:/admin/index";
            if (isAlmoxarife)
                return "redirect:/almoxarifado/index";
            return "redirect:/index";
        }
        return "login";
    }

    @GetMapping("/acesso-negado")
    public String acessoNegado() {
        return "acesso-negado";
    }
}
