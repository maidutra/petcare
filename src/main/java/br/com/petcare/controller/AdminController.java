package br.com.petcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.petcare.model.agendamento;
import br.com.petcare.service.AgendamentoService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping("/dashboard")
    public String dashboardAdmin(Model model) {
        List<agendamento> hoje = agendamentoService.listarAgendamentosDeHoje();
        model.addAttribute("totalAtendimentos", hoje.size());
        return "admin/dashboard";
    }

    @GetMapping("/agenda")
    public String verAgenda(Model model) {
        // Mantido seu método original intacto!
        List<agendamento> todosAgendamentos = agendamentoService.listarAgendamentosDeHoje();
        model.addAttribute("todosAgendamentos", todosAgendamentos);
        return "admin/agenda";
    }

    @GetMapping("/clientes-pets")
    public String listarClientesEPets() {
        return "admin/clientes-pets"; 
    }

    // ADICIONADO: Rota para o botão "Confirmar" mudar o status no banco
    @GetMapping("/agenda/confirmar/{id}")
    public String confirmarAgendamento(@PathVariable("id") Long id) {
        agendamentoService.atualizarStatus(id, "CONFIRMADO");
        return "redirect:/admin/agenda";
    }

    // ADICIONADO: Rota para o botão "Finalizar" fechar o atendimento
    @GetMapping("/agenda/finalizar/{id}")
    public String finalizarAgendamento(@PathVariable("id") Long id) {
        agendamentoService.atualizarStatus(id, "FINALIZADO");
        return "redirect:/admin/agenda";
    }
}