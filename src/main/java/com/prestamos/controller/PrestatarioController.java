package com.prestamos.controller;

import java.util.ArrayList;
import java.util.List;

import com.prestamos.model.Zona;
import com.prestamos.repository.UsuarioRepository;
import com.prestamos.service.ZonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ZonaService zonaService;

    @Autowired
    private UsuarioRepository usuarioRepository;



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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nombrePrestamista = auth.getName();
        Usuario prestamista = usuarioRepository.findByNombres(nombrePrestamista);

        // Obtener la zona del prestamista
        Zona zonaPrestamista = prestamista.getIdZona();

        // Agregar la zona del prestamista al modelo
        model.addAttribute("zonaPrestamista", zonaPrestamista);

        //List<Zona> zonas = zonaService.obtenerTodos();

        // Filtrar los roles para obtener solo el de "prestatario"
        Rol rolPrestatario = rolService.obtenerRolPrestatario(6);

        model.addAttribute("nombrePrestamista", nombrePrestamista);
        //model.addAttribute("zonas", zonas);
        // Agregar el rol al modelo
        model.addAttribute("rolPrestatario", rolPrestatario);
        model.addAttribute("prestatario", new Usuario());
        return "prestatario-crear";
    }

    @PostMapping("/prestatario/crear")
    public String crearPrestamista(@ModelAttribute Usuario usuario, @RequestParam("idZona") int idZona, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nombrePrestamista = auth.getName();
        Usuario prestamista = usuarioRepository.findByNombres(nombrePrestamista);

        // Encriptar la contraseña
        String passwordEncriptado = encriptarPassword(usuario.getPassword());
        usuario.setPassword(passwordEncriptado);


        int idPrestamista = prestamista.getIdUsuario();
        usuario.setIdUsuarioLider(idPrestamista);

        // Obtener el rol del prestatario
        Rol rolPrestatario = rolService.obtenerRolPrestatario(6);
        // Asignar el rol al usuario
        usuario.setIdRol(rolPrestatario);

        // Asignar el ID de la zona seleccionada al usuario
        /*Zona zonaSeleccionada = new Zona();
        zonaSeleccionada.setIdZona(idZona);
        usuario.setIdZona(zonaSeleccionada);*/



        prestatarioService.guardar(usuario);
        return "redirect:/prestatario-list";
    }

    // Método para encriptar la contraseña
    private String encriptarPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
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
