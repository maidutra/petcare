package br.com.petcare.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        
        // CORRIGIDO: Extrai os papéis (roles) mapeando a lista direto para Strings sem usar AuthorityUtils
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        boolean isCliente = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_CLIENTE"));

        // Redirecionamento inteligente baseado na validação booleana
        if (isAdmin) {
            response.sendRedirect("/admin/agenda");
        } else if (isCliente) {
            response.sendRedirect("/cliente/dashboard");
        } else {
            response.sendRedirect("/");
        }
    }
}