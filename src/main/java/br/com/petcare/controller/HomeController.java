package br.com.petcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.petcare.model.Pet;
import br.com.petcare.model.agendamento;
import br.com.petcare.repository.PetRepository;
import br.com.petcare.service.AgendamentoService;

@Controller
@RequestMapping("/cliente") 
public class HomeController {

    @Autowired
    private PetRepository petRepository;

    // CORRIGIDO: Agora a variável agendamentoService foi declarada corretamente
    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/meus-pets")
    public String listarMeusPets(Model model) {
        List<Pet> listaDoBanco = petRepository.findByNomeTutor("Maira Luiza");
        model.addAttribute("meusPets", listaDoBanco);
        return "meus-pets";
    }

    @GetMapping("/meus-agendamentos")
    public String meusAgendamentos(Model model) {
        // CORRIGIDO: Alterado de 'AgendamentoService' para 'agendamentoService' (minúsculo)
        List<agendamento> listaAgendamentos = agendamentoService.listarPorTutor("Maira Luiza");
        model.addAttribute("agendamentos", listaAgendamentos);
        return "meus-agendamentos";
    }

    @GetMapping("/agendamento")
    public String mostrarFormularioAgendamento(Model model) {
        model.addAttribute("agendamento", new agendamento()); 
        return "agendamento";
    }

    // APROVEITADO: Já adicionei o salvamento real aqui também para persistir o agendamento no banco!
    @PostMapping("/agendamento")
    public String processarAgendamento(@ModelAttribute("agendamento") agendamento agendamento) {
        agendamento.setNomeTutor("Maira Luiza");
        agendamentoService.salvar(agendamento);
        return "redirect:/cliente/meus-agendamentos?sucesso";
    }

    @GetMapping("/cadastrar-pet")
    public String exibirFormularioPet() {
        return "cadastrar-pet"; 
    }

    @PostMapping("/meus-pets/salvar")
    public String salvarNovoPet(Pet novoPet) {
        if (novoPet.getNomeTutor() == null || novoPet.getNomeTutor().isEmpty()) {
            novoPet.setNomeTutor("Maira Luiza"); 
        }
        petRepository.save(novoPet);
        return "redirect:/cliente/meus-pets";
    }

    @GetMapping("/meus-pets/excluir/{id}")
    public String excluirPet(@PathVariable("id") Long id) {
        petRepository.deleteById(id);
        return "redirect:/cliente/meus-pets";
    }
}