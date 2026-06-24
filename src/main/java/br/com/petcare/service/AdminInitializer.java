package br.com.petcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.petcare.model.Usuario;
import br.com.petcare.repository.UsuarioRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String emailAdmin = "admin2@petcare.com";

        // Verifica se o admin já existe para não duplicar toda vez que o servidor ligar
        if (usuarioRepository.findByEmail(emailAdmin).isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNome("Administrador PetCare");
            admin.setEmail(emailAdmin);
            
            // ATENÇÃO: Criptografa a senha antes de salvar (regra do Spring Security!)
            admin.setSenha(passwordEncoder.encode("petcare123")); 
            
            // Define o papel como ADMIN para liberar o painel do petshop
            admin.setRole("ADMIN");

            usuarioRepository.save(admin);
            System.out.println("🚀 Usuário Admin criado com sucesso! Login: admin2@petcare.com | Senha: petcare123");
        }
    }
}