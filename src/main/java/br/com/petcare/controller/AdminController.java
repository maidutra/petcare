package br.com.petcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        // Puxa a lista de hoje para sabermos quantos atendimentos temos no total
        List<agendamento> hoje = agendamentoService.listarAgendamentosDeHoje();
        
        // Passamos o tamanho da lista (quantidade de pets) para os cards do dashboard
        model.addAttribute("totalAtendimentos", hoje.size());
        
        return "admin/dashboard";
    }

    @GetMapping("/agenda")
    public String verAgenda(Model model) {
        // BUSCA REAL: Puxa todos os agendamentos do dia atual direto do banco de dados
        List<agendamento> todosAgendamentos = agendamentoService.listarAgendamentosDeHoje();
        
        // Envia a lista para o th:each do HTML
        model.addAttribute("todosAgendamentos", todosAgendamentos);
        
        return "admin/agenda";
    }

    @GetMapping("/clientes-pets")
    public String listarClientesEPets() {
        return "admin/clientes-pets"; 
    }
}