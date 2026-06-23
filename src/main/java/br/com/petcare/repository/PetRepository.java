package br.com.petcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.petcare.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    // Busca os pets específicos de um dono
    List<Pet> findByNomeTutor(String nomeTutor);
}