package br.com.petcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.petcare.model.agendamento;

@Controller
@RequestMapping("/cliente") 
public class HomeController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/meus-pets")
    public String meusPets() {
        return "meus-pets";
    }

    @GetMapping("/meus-agendamentos")
    public String meusAgendamentos() {
        return "meus-agendamentos";
    }

    @GetMapping("/agendamento")
    public String mostrarFormularioAgendamento(Model model) {
        model.addAttribute("agendamento", new agendamento()); 
        return "agendamento";
    }

    @PostMapping("/agendamento")
    public String processarAgendamento(@ModelAttribute("agendamento") agendamento agendamento) {
        
        System.out.println("Formulário de agendamento recebido com sucesso!");
        
        return "redirect:/cliente/meus-agendamentos?sucesso";
    }

    @GetMapping("/cadastrar-pet")
public String exibirFormularioPet() {
    // Isso vai carregar o arquivo cadastrar-pet.html da sua pasta templates
    return "cadastrar-pet"; 
}

@Autowired
private br.com.petcare.repository.PetRepository petRepository;

@GetMapping("/meus-pets")
public String listarMeusPets(Model model) {
    // 1. Busca no banco todos os pets cadastrados para a Maira
    List<br.com.petcare.model.Pet> listaDoBanco = petRepository.findByNomeTutor("Maira Luiza");
    
    // 2. Envia essa lista real para o HTML do Thymeleaf
    model.addAttribute("meusPets", listaDoBanco);
    
    // 3. Abre o arquivo meus-pets.html
    return "meus-pets";
}

}

