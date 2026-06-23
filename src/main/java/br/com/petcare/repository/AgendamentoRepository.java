package br.com.petcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.petcare.model.agendamento;

@Repository
public interface AgendamentoRepository extends JpaRepository<agendamento, Long> {
    
    // Busca os agendamentos pela data (como String) ordenando pelo horário (como String)
    List<agendamento> findByDataOrderByHorarioAsc(String data);
    
    // Busca todos os agendamentos de um tutor específico (útil para a tela de Meus Agendamentos do cliente)
    List<agendamento> findByNomeTutor(String nomeTutor);
}