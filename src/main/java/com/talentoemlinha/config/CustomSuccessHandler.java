package com.talentoemlinha.config;

import java.io.IOException;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils
        .authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ADMIN")){
            response.sendRedirect("/admin/index");
        }else if (roles.contains("USER")){
            response.sendRedirect("/index");
        }
        else if (roles.contains("ALMOXARIFE")){
            response.sendRedirect("/almoxarifado/index");
        }else{
            response.sendRedirect("/login?error=true");
        }
    }
    
}
