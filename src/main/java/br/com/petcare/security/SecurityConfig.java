package br.com.petcare.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth
                // Libera arquivos estáticos
                .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()

                // Libera páginas públicas
                .requestMatchers(
                        "/", 
                        "/contato", 
                        "/servicos", 
                        "/como-funciona", 
                        "/ajuda",
                        "/login", 
                        "/cadastro", 
                        "/recuperar-senha"
                ).permitAll()

                // PROTEGE a área do cliente
                .requestMatchers("/cliente/**").authenticated() 

                // PROTEGE a área administrativa do Petshop 🛠️
                .requestMatchers("/admin/**").authenticated()

                // Qualquer outra rota precisa de login
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                // Por enquanto, mantém o redirecionamento padrão. 
                // (Mais para frente podemos fazer um código que joga o admin para /admin/agenda e o cliente para o dashboard)
                .defaultSuccessUrl("/cliente/dashboard", true) 
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}