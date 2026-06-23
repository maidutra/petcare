package br.com.petcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.petcare.model.Usuario;
import br.com.petcare.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void salvarUsuario(Usuario usuario) {
        // Criptografa a senha usando BCrypt (o Bean que configuramos no SecurityConfig)
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        
        // Salva no PostgreSQL
        usuarioRepository.save(usuario);
    }
}