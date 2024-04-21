package com.prestamos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.prestamos.model.Usuario;
import com.prestamos.repository.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	UsuarioRepository usurepo;

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	
	
    @GetMapping("/registrarUser")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registrarUser";
    }
    

	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	
}
