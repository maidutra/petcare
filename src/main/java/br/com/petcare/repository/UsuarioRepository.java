package br.com.petcare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petcare.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}