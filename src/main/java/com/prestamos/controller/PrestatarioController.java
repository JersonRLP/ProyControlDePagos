package com.prestamos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.prestamos.model.Rol;
import com.prestamos.model.Usuario;
import com.prestamos.service.PrestatarioService;
import com.prestamos.service.RolService;


@Controller
public class PrestatarioController {

	@Autowired
    private PrestatarioService prestatarioService;

    @Autowired
    private RolService rolService;



    @GetMapping("prestatario-list")
    public String mostrarTodos(Model model) {
        List<Rol> roles = rolService.obtenerTodos();
        List<Usuario> prestatarios = prestatarioService.obtenerTodos();
        List<Usuario> prestatariosConRol6 = new ArrayList<>();

        // Filtrar los prestamistas que tienen el rol con ID 5
        for (Usuario prestatario : prestatarios) {
            if (prestatario.getIdRol().getIdRol() == 6) {
                prestatariosConRol6.add(prestatario);
            }
        }

        model.addAttribute("roles", roles);
        model.addAttribute("prestatarios", prestatariosConRol6);
        return "prestatario-list";
    }

    @GetMapping("/prestatario-crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("prestatario", new Usuario());
        return "prestatario-crear";
    }

    @PostMapping("/prestatario/crear")
    public String crearPrestamista(@ModelAttribute Usuario usuario) {
        prestatarioService.guardar(usuario);
        return "redirect:/prestatario-list";
    }

    @GetMapping("/prestatario/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Usuario usuario = prestatarioService.obtenerPorId(id);
        model.addAttribute("usuario", usuario);
        return "prestatario-actualizar";
    }

    @PostMapping("/prestatario/editar/{id}")
    public String actualizarPrestamista(@PathVariable Integer id, @ModelAttribute Usuario usuario) {
        usuario.setIdUsuario(id); // Asegurarse de que el rol tenga el ID correcto
        prestatarioService.actualizar(usuario);
        return "redirect:/prestatario-list";
    }

    @GetMapping("/prestatario/eliminar/{id}")
    public String eliminarPrestamista(@PathVariable Integer id) {
        prestatarioService.eliminar(id);
        return "redirect:/prestatario-list";
    }
	
	
	
}
