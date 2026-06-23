package br.com.petcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.petcare.model.Pet;
import br.com.petcare.model.agendamento;
import br.com.petcare.repository.PetRepository;

@Controller
@RequestMapping("/cliente") 
public class HomeController {

    @Autowired
    private PetRepository petRepository;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    // DINÂMICO: Método antigo duplicado removido! Esse agora busca direto do banco
    @GetMapping("/meus-pets")
    public String listarMeusPets(Model model) {
        List<Pet> listaDoBanco = petRepository.findByNomeTutor("Maira Luiza");
        model.addAttribute("meusPets", listaDoBanco);
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
        return "cadastrar-pet"; 
    }

    // ADICIONADO: Esse método faltava para receber os dados do formulário e salvar no banco
    @PostMapping("/meus-pets/salvar")
    public String salvarNovoPet(Pet novoPet) {
        if (novoPet.getNomeTutor() == null || novoPet.getNomeTutor().isEmpty()) {
            novoPet.setNomeTutor("Maira Luiza"); 
        }
        petRepository.save(novoPet);
        return "redirect:/cliente/meus-pets";
    }

    @GetMapping("/meus-pets/excluir/{id}")
public String excluirPet(@org.springframework.web.bind.annotation.PathVariable("id") Long id) {
    // Deleta o pet do PostgreSQL usando o ID que veio na URL
    petRepository.deleteById(id);
    
    // Redireciona de volta para a listagem atualizada
    return "redirect:/cliente/meus-pets";
}
}