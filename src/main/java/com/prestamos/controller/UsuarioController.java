package com.prestamos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.prestamos.model.Rol;
import com.prestamos.model.Usuario;
import com.prestamos.repository.RolRepository;
import com.prestamos.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {

	@Autowired
	UsuarioRepository usurepo;
	
	@Autowired
	RolRepository rolrepo;

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
	public String home(Model model, HttpSession session) {
	   
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String nombreUsuario = auth.getName();
		
	    Usuario usuario = usurepo.findByNombres(nombreUsuario);
	    
	    Rol r = usuario.getIdRol();
	    	
		String desrol = r.getDescripcion();
		
		session.setAttribute("rol", desrol);
			
		String rol = (String) session.getAttribute("rol");
		
		model.addAttribute("rol", rol);
		
		return "home";
	}
	
	
}
