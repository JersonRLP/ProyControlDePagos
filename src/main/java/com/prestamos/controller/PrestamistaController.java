package com.prestamos.controller;


import com.prestamos.model.Rol;
import com.prestamos.model.Usuario;
import com.prestamos.service.PrestamistaService;
import com.prestamos.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@Controller
public class PrestamistaController {
	
	 @Autowired
	    private PrestamistaService prestamistaService;

	    @Autowired
	    private RolService rolService;


	    /*@GetMapping("prestamista-list")
	    public String mostrarTodos(Model model) {
	        List<Rol> roles = rolService.obtenerTodos();
	        List<Usuario> prestamistas = prestamistaService.obtenerTodos();
	        model.addAttribute("roles", roles);
	        model.addAttribute("prestamistas", prestamistas);
	        return "prestamista-list";
	    }*/
	    @GetMapping("prestamista-list")
	    public String mostrarTodos(Model model) {
	        List<Rol> roles = rolService.obtenerTodos();
	        List<Usuario> prestamistas = prestamistaService.obtenerTodos();
	        List<Usuario> prestamistasConRol5 = new ArrayList<>();

	        // Filtrar los prestamistas que tienen el rol con ID 5
	        for (Usuario prestamista : prestamistas) {
	            if (prestamista.getIdRol().getIdRol() == 5) {
	                prestamistasConRol5.add(prestamista);
	            }
	        }

	        model.addAttribute("roles", roles);
	        model.addAttribute("prestamistas", prestamistasConRol5);
	        return "prestamista-list";
	    }

	    @GetMapping("/crear")
	    public String mostrarFormularioCrear(Model model) {
	        model.addAttribute("prestamista", new Usuario());
	        return "prestamista-crear";
	    }

	    @PostMapping("/crear")
	    public String crearPrestamista(@ModelAttribute Usuario usuario) {
	        prestamistaService.guardar(usuario);
	        return "redirect:/prestamistas";
	    }

	    @GetMapping("/editar/{id}")
	    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
	        Usuario usuario = prestamistaService.obtenerPorId(id);
	        model.addAttribute("usuario", usuario);
	        return "prestamista-actualizar";
	    }

	    @PostMapping("/editar/{id}")
	    public String actualizarPrestamista(@PathVariable Integer id, @ModelAttribute Usuario usuario) {
	        usuario.setIdUsuario(id); // Asegurarse de que el rol tenga el ID correcto
	        prestamistaService.actualizar(usuario);
	        return "redirect:/prestamistas";
	    }

	    @GetMapping("/eliminar/{id}")
	    public String eliminarPrestamista(@PathVariable Integer id) {
	        prestamistaService.eliminar(id);
	        return "redirect:/prestamistas";
	    }
	
	
	
	
	
	
}
