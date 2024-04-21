package com.prestamos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.prestamos.model.Rol;
import com.prestamos.model.Usuario;
import com.prestamos.model.Zona;
import com.prestamos.repository.UsuarioRepository;
import com.prestamos.repository.ZonaRepository;


@Controller
public class InversionistaController {

	@Autowired
	private UsuarioRepository usurepo;
	
	@Autowired
	private ZonaRepository zonarepo;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;

	
	
	@GetMapping("/client-new")
	public String cargarPagina(Model model) {
	    // Obtener el nombre de usuario del contexto de seguridad
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String nombreUsuario = auth.getName();
	    
	    
	    // Obtener el usuario actual
	    Usuario usuario = usurepo.findByNombres(nombreUsuario);
	    
	    // Obtener el ID del usuario actual
	    int idUsuario = usuario.getIdUsuario();
	    
	    // Poner el ID del usuario en el modelo
	    model.addAttribute("idUsuario", idUsuario);
	    
	    // Obtener el ID del rol del usuario actual
	    int idRol = usuario.getIdRol().getIdRol();
	    
	    // Poner el ID del rol en el modelo
	    model.addAttribute("idRol", idRol);
	    
	    // También puedes pasar otros datos necesarios al modelo
	    model.addAttribute("listzonas", zonarepo.findAll());
	    
	    return "client-new";
	}

	@GetMapping("/client-search")
	public String cargarPaginaBuscar(Model model) {
		return "client-search";
	}
	
	@GetMapping("/client-list")
	public String cargarPaginaListar(Model model) {
		return "client-list";
	}

    
    @PostMapping("/save")
    public String registrarUsuarioJefe(@RequestParam("nombres") String nombres,
    									@RequestParam("apePaterno") String apePaterno,
    									@RequestParam("apeMaterno") String apeMaterno,
    									@RequestParam("password") String password,
    									@RequestParam("email") String email,
    									@RequestParam("telefono") String telefono,
    									@RequestParam("dni") String dni,
    									@RequestParam("estado") int estado,
    									@RequestParam("idRol") Rol idRol,
    									@RequestParam("idZona") Zona idZona,
    									@RequestParam("idUsuarioLider") int idUsuarioLider,
    									RedirectAttributes attribute) {
        // Encriptar la contraseña antes de guardarla
    	String passwordEncriptado = passwordEncoder.encode(password);
    	
    	
    	Usuario jefe = new Usuario();
    	jefe.setNombres(nombres);
    	jefe.setApePaterno(apePaterno);
    	jefe.setApeMaterno(apeMaterno);
    	jefe.setPassword(passwordEncriptado);
    	jefe.setEmail(email);
    	jefe.setTelefono(telefono);
    	jefe.setDni(dni);
    	jefe.setEstado(estado);
    	jefe.setIdRol(idRol);
    	jefe.setIdZona(idZona);
    	jefe.setIdUsuarioLider(idUsuarioLider);
    	
    	
    	usurepo.save(jefe);
        

        return "redirect:/client-new?registroExitoso";
    }
}
