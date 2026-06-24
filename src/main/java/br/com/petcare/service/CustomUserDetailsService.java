package br.com.petcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.petcare.model.Usuario;
import br.com.petcare.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca o usuário no PostgreSQL pelo e-mail
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));

        // CORRIGIDO: Se a role no banco for null por segurança, assume CLIENTE por padrão
        String perfil = usuario.getRole() != null ? usuario.getRole() : "CLIENTE";

        // Retorna o objeto User contendo as credenciais e a Role correta do banco de dados (ex: ROLE_CLIENTE)
        return new User(
            usuario.getEmail(), 
            usuario.getSenha(), 
            AuthorityUtils.createAuthorityList("ROLE_" + perfil)
        );
    }
}