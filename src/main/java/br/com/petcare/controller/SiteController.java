package br.com.petcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.petcare.model.Usuario;
import br.com.petcare.service.UsuarioService;

@Controller
public class SiteController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/servicos")
    public String servicos() {
        return "servicos";
    }

    @GetMapping("/contato")
    public String contato() {
        return "contato";
    }

    @GetMapping("/como-funciona")
    public String ajuda() {
        return "ajuda";
    }

    // --- LOGIN E CADASTRO ---

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario, String petNome) {
        usuarioService.salvarUsuario(usuario);
        System.out.println("Usuário cadastrado: " + usuario.getEmail());
        return "redirect:/login?sucesso";
    }

@GetMapping("/recuperar-senha")
public String mostrarPaginaRecuperarSenha() {
    return "recuperar-senha";
}

@PostMapping("/recuperar-senha")
public String processarRecuperacao(String email) {
    // No futuro, aqui você chamará um EmailService para enviar o token
    System.out.println("Solicitada recuperação para o e-mail: " + email);
    
    // Redireciona de volta com uma mensagem de sucesso
    return "redirect:/recuperar-senha?sucesso";
}
}