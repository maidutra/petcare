package br.com.petcare.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petcare.model.agendamento;
import br.com.petcare.repository.AgendamentoRepository;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    // Salva um novo agendamento vindo do formulário
    public agendamento salvar(agendamento novoAgendamento) {
        // Garante que todo agendamento comece com o status PENDENTE
        novoAgendamento.setStatus("PENDENTE");
        return agendamentoRepository.save(novoAgendamento);
    }

    // Busca os agendamentos de um cliente específico pelo nome do tutor
    public List<agendamento> listarPorTutor(String nomeTutor) {
        return agendamentoRepository.findByNomeTutor(nomeTutor);
    }

    // Busca os agendamentos do dia atual para a agenda do petshop
public List<agendamento> listarAgendamentosDeHoje() {
    // CORRIGIDO: Nome da variável tudo junto (hojeFormatada)
    String hojeFormatada = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    return agendamentoRepository.findByDataOrderByHorarioAsc(hojeFormatada);
}
    // Altera o status do agendamento (usado pelo petshop na tela da agenda)
    public void atualizarStatus(Long id, String novoStatus) {
        agendamentoRepository.findById(id).ifPresent(agenda -> {
            agenda.setStatus(novoStatus);
            agendamentoRepository.save(agenda);
        });
    }
}