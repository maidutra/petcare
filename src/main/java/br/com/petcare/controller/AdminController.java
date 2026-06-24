package br.com.petcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.petcare.model.Pet;
import br.com.petcare.model.agendamento;
import br.com.petcare.repository.PetRepository;
import br.com.petcare.service.AgendamentoService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private PetRepository petRepository; 

    @GetMapping("/dashboard")
    public String dashboardAdmin(Model model) {
        // 1. Busca os agendamentos de hoje para as métricas e para a fila
        List<agendamento> hoje = agendamentoService.listarAgendamentosDeHoje();
        model.addAttribute("totalAtendimentos", hoje.size());
        
        // 2. Filtra quem está aguardando (PENDENTE) ou em execução (CONFIRMADO)
        List<agendamento> proximosFila = hoje.stream()
                .filter(a -> "PENDENTE".equals(a.getStatus()) || "CONFIRMADO".equals(a.getStatus()))
                .toList();
        model.addAttribute("proximosAtendimentos", proximosFila);

        // 3. Conta a quantidade total de registros na tabela de Pets
        long totalPetsClientes = petRepository.count();
        model.addAttribute("novosClientes", totalPetsClientes);

        // 4. TABELA DE PREÇOS ATUALIZADA: Mapeamento defensivo baseado no agendamento.html
        double faturamentoPrevisto = hoje.stream().mapToDouble(a -> {
            if (a.getServico() == null) return 50.0;
            
            String servico = a.getServico().toLowerCase();
            
            if (servico.contains("combo") || servico.contains("completo")) {
                return 110.0; // Combo Completo (Banho + Tosa) - R$ 110
            } else if (servico.contains("tosa") && servico.contains("completa")) {
                return 90.0;  // Tosa Completa - R$ 90
            } else if (servico.contains("tosa") || servico.contains("higienica") || servico.contains("higiênica")) {
                return 70.0;  // Tosa Higiênica - R$ 70
            }
            
            return 50.0; // Banho Padrão - R$ 50
        }).sum();
        model.addAttribute("faturamento", faturamentoPrevisto);

        return "admin/dashboard";
    }

    @GetMapping("/agenda")
    public String verAgenda(Model model) {
        List<agendamento> todosAgendamentos = agendamentoService.listarAgendamentosDeHoje();
        model.addAttribute("todosAgendamentos", todosAgendamentos);
        return "admin/agenda";
    }

    @GetMapping("/clientes-pets")
    public String listarClientesEPets(Model model) {
        List<Pet> todosOsPets = petRepository.findAll();
        model.addAttribute("listaPets", todosOsPets);
        return "admin/clientes-pets"; 
    }

    @GetMapping("/historico")
    public String verHistoricoGeral(Model model) {
        List<agendamento> todosDoBanco = agendamentoService.listarTodos(); 
        model.addAttribute("historicoAgendamentos", todosDoBanco);
        return "admin/historico";
    }

    @GetMapping("/agenda/confirmar/{id}")
    public String confirmarAgendamento(@PathVariable("id") Long id) {
        agendamentoService.atualizarStatus(id, "CONFIRMADO");
        return "redirect:/admin/agenda";
    }

    @GetMapping("/agenda/finalizar/{id}")
    public String finalizarAgendamento(@PathVariable("id") Long id) {
        agendamentoService.atualizarStatus(id, "FINALIZADO");
        return "redirect:/admin/agenda";
    }
}