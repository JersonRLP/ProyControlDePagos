package com.prestamos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prestamos.dto.UsuarioDTO;
import com.prestamos.model.Rol;
import com.prestamos.model.Usuario;
import com.prestamos.model.Zona;
import com.prestamos.repository.JefeRepository;
import com.prestamos.repository.UsuarioRepository;
import com.prestamos.repository.ZonaRepository;
import com.prestamos.service.UsuarioService;

import jakarta.servlet.http.HttpSession;


@Controller
public class JefeController {

	
	@Autowired
    private UsuarioService usuarioService;
	@Autowired
	private UsuarioRepository usurepo;
	
	@Autowired
	private ZonaRepository zonarepo;
	
	@Autowired
	private JefeRepository jeferepo;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/nuevoJefe")
	public String cargarPagina(Model model, HttpSession session) {
		model.addAttribute("jefe", new Usuario());
	    // Obtener el nombre de usuario del contexto de seguridad
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
	    
	    // Obtener el usuario actual por nombres
	    Usuario usuario = usurepo.findByUsername(username);
	    
	    // Obtener el ID del usuario actual
	    int idUsuario = usuario.getIdUsuario();
	    
	    // Poner el ID del usuario en el modelo
	    model.addAttribute("idUsuario", idUsuario);
	    
	    // Obtener el ID del rol del usuario actual
	    int idRol = usuario.getIdRol().getIdRol();
	    
	    // Poner el ID del rol en el modelo
	    model.addAttribute("idRol", idRol);
	    
	    List<Usuario> jefes = jeferepo.findByIdRolDescripcionAndEstado("Jefe de Prestamista", 0);

		 // Crear una lista para almacenar las zonas de los jefes
		 List<Zona> zonasJefes = new ArrayList<>();
	
		 // Iterar sobre la lista de jefes para obtener y almacenar las zonas
		 for (Usuario jefe : jefes) {
		     Zona zonaJefe = jefe.getIdZona();
		     zonasJefes.add(zonaJefe);
		 }

		 List<Zona> listaZonas = zonarepo.findAll();
		 
		// Obtener la zona del jefe de prestamista
		Zona zonaJefe = listaZonas.get(0);
		    
		listaZonas.remove(zonaJefe);
		// Agregar la zona del jefe al modelo
		model.addAttribute("zonajefe", zonaJefe);
		 
		 // Eliminar las zonas de los jefes de la lista de zonas
	 	listaZonas.removeAll(zonasJefes);

	 	// Poner la lista de zonas restantes en el modelo
	 	model.addAttribute("listzonas", listaZonas);		
	    		
	    return "nuevoJefe";
	}
	
	@GetMapping("/corregirJefe")
	public String corregirjefe(@ModelAttribute("jefe") Usuario jefe,
		    				 @ModelAttribute("errorField") String errorField,
		    				 Model model, HttpSession session) {
	
		//Usuario usu = usurepo.findByIdUsuario(jefe.getIdUsuario());
		
	    model.addAttribute("jefe", jefe);

	    model.addAttribute("errorField", errorField);
	 
	    List<Usuario> jefes = jeferepo.findByIdRolDescripcionAndEstado("Jefe de Prestamista", 0);

		 // Crear una lista para almacenar las zonas de los jefes
		 List<Zona> zonasJefes = new ArrayList<>();
	
		 // Iterar sobre la lista de jefes para obtener y almacenar las zonas
		 for (Usuario jefee : jefes) {
		     Zona zonaJefe = jefee.getIdZona();
		     zonasJefes.add(zonaJefe);
		 }

		 List<Zona> listaZonas = zonarepo.findAll();
		 
		 // Eliminar las zonas de los jefes de la lista de zonas
	 	listaZonas.removeAll(zonasJefes);
	    
	    // Obtener la zona del jefe de prestamista
	    Zona zonaJefe = listaZonas.get(0);	    
	    
	    // Agregar lista de zonas al modelo
	    model.addAttribute("listzonas", listaZonas);
	    
		// Agregar la zona del jefe al modelo
		model.addAttribute("zonajefe", zonaJefe);
	    
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();		
	    Usuario usuario = usurepo.findByUsername(username);  
	    
	    // Obtener el ID del usuario actual
	    int idUsuario = usuario.getIdUsuario();
	    
	    // Poner el ID del usuario en el modelo
	    model.addAttribute("idUsuario", idUsuario);
	    
	    // Obtener el ID del rol del usuario actual
	    int idRol = usuario.getIdRol().getIdRol();
	    
	    // Poner el ID del rol en el modelo
	    model.addAttribute("idRol", idRol);
		
		return "nuevoJefe";
	}
		
    @PostMapping("/registrarJefe")
    public String registrarUsuarioJefe(@ModelAttribute Usuario jefe, RedirectAttributes attribute) {
    	
        // Encriptar la contraseña antes de guardarla    	
    	String passwordEncriptado = passwordEncoder.encode(jefe.getPassword());
    	  	
    	jefe.setPassword(passwordEncriptado);
    	Usuario jefeuser = jeferepo.findByUsername(jefe.getUsername());
    	if(jefeuser == null) {
	    	Usuario jefeemail = jeferepo.findByEmail(jefe.getEmail());
	    	if(jefeemail == null) {
	    		Usuario jefetel = jeferepo.findByTelefono(jefe.getTelefono());
	    		if(jefetel == null) {
	    			Usuario jefedni = jeferepo.findByDni(jefe.getDni());
	    			if(jefedni == null) {
	    				usurepo.save(jefe);
	    				return "redirect:/nuevoJefe?registroExitoso";
	    			}else {
						attribute.addFlashAttribute("mensaje", "El Jefe con el DNI: "+jefe.getDni()+" ya existe");
			            attribute.addFlashAttribute("jefe", jefe);
			            attribute.addFlashAttribute("listzonas", zonarepo.findAll());
			            attribute.addFlashAttribute("errorField", "dni");
					}
	    		}else{
					attribute.addFlashAttribute("mensaje", "El Jefe con el Teléfono: "+jefe.getTelefono()+" ya existe");
		            attribute.addFlashAttribute("jefe", jefe);
		            attribute.addFlashAttribute("listzonas", zonarepo.findAll());
		            attribute.addFlashAttribute("errorField", "telefono");
	    		}
	    	}else {
				attribute.addFlashAttribute("mensaje", "El Jefe con el Correo: "+jefe.getEmail()+" ya existe");
	            attribute.addFlashAttribute("jefe", jefe);
	            attribute.addFlashAttribute("listzonas", zonarepo.findAll());
	            attribute.addFlashAttribute("errorField", "email");
	    	}
    	}else {
			attribute.addFlashAttribute("mensaje", "El Jefe con el usuario: "+jefe.getUsername()+" ya existe");
            attribute.addFlashAttribute("jefe", jefe);
            attribute.addFlashAttribute("listzonas", zonarepo.findAll());
            attribute.addFlashAttribute("errorField", "username");
		}
	    	
    	
    	return "redirect:/corregirJefe";
    }
    
	@GetMapping("/listaJefe")
	public String cargarPaginaListar(Model model, HttpSession session) {
		// Buscar Jefes de prestamistas con estado 0 = Activo
		List<Usuario> listaJefes = jeferepo.findByIdRolDescripcionAndEstado("Jefe de Prestamista", 0);
		// Agregar lista de Jefes de prestamistas al modelo
		model.addAttribute("lstJefes", listaJefes);
				
		return "listaJefe";
	}
    
	
    
	
	@GetMapping("/{id}/editar")
    public String editarUsuario(@PathVariable int id, Model model) {
        UsuarioDTO usuarioDTO = usuarioService.obtenerUsuarioPorId(id);
        if (usuarioDTO == null) {
            return "redirect:/listaJefe?errorUsuarioNoEncontrado";
        }
        model.addAttribute("usuarioDTO", usuarioDTO);
        model.addAttribute("usuarioId", id);
        return "actualizarJefe";
    }

	@PostMapping("/{id}/actualizar")
	public String actualizarUsuario(@PathVariable int id, @ModelAttribute UsuarioDTO usuarioDTO, BindingResult result, RedirectAttributes redirectAttributes) {
	    if (result.hasErrors()) {
	        return "actualizarJefe";
	    }
	    boolean actualizado = usuarioService.actualizarUsuario(id, usuarioDTO);
	    if (!actualizado) {
	        return "redirect:/listaJefe?errorUsuarioNoEncontrado";
	    }
	    redirectAttributes.addFlashAttribute("actualizacionExitosa", true);
	    return "redirect:/listaJefe";
	}

	@GetMapping("/buscarJefe")
	public String cargarPaginaBuscar(@RequestParam(name = "search", required = false) String search, Model model, HttpSession session) {
		List<Usuario> listaJefes = null;
		
	    if (search != null && !search.isEmpty()) {
	    	listaJefes = jeferepo.findBySearchAndIdRolDescripcionAndEstado(search, "Jefe de Prestamista", 0);
	    } else {
	    	listaJefes = jeferepo.findByIdRolDescripcionAndEstado("Jefe de Prestamista", 0);
	    }
	    
	    model.addAttribute("lstJefes", listaJefes);
	    model.addAttribute("search", search);
		
		return "buscarJefe";
	}
    
    @PostMapping("/eliminarJefeLogico")
	public String eliminarJefeLogico(@ModelAttribute Usuario usuario, Model model) {
		Usuario usu = usurepo.findByIdUsuario(usuario.getIdUsuario());
		usu.setEstado(1);
		usurepo.save(usu);
		return "redirect:/listaJefe?eliminacionExitosa";
	}
    
    @PostMapping("/eliminarJefeFisico")
	public String eliminarJefeFisico(@ModelAttribute Usuario usuario, Model model) {
		Usuario usu = usurepo.findByIdUsuario(usuario.getIdUsuario());
		usurepo.delete(usu);
		return "redirect:/listaJefe?eliminacionExitosa";
	}
}
