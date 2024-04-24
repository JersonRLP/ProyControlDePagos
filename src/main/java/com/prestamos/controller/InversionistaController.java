package com.prestamos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.prestamos.model.Rol;
import com.prestamos.model.Usuario;
import com.prestamos.model.Zona;
import com.prestamos.repository.InversionistaRepository;
import com.prestamos.repository.UsuarioRepository;
import com.prestamos.repository.ZonaRepository;

import jakarta.servlet.http.HttpSession;


@Controller
public class InversionistaController {

	@Autowired
	private UsuarioRepository usurepo;
	
	@Autowired
	private ZonaRepository zonarepo;
	
	@Autowired
	private InversionistaRepository invrepo;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/nuevoJefe")
	public String cargarPagina(Model model, HttpSession session) {
	    // Obtener el nombre de usuario del contexto de seguridad
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String nombreUsuario = auth.getName();
	    
	    // Obtener el usuario actual por nombres
	    Usuario usuario = usurepo.findByNombres(nombreUsuario);
	    
	    // Obtener el ID del usuario actual
	    int idUsuario = usuario.getIdUsuario();
	    
	    // Poner el ID del usuario en el modelo
	    model.addAttribute("idUsuario", idUsuario);
	    
	    // Obtener el ID del rol del usuario actual
	    int idRol = usuario.getIdRol().getIdRol();
	    
	    // Poner el ID del rol en el modelo
	    model.addAttribute("idRol", idRol);
	    
	    // Poner la lista de Zonas en el modelo
	    model.addAttribute("listzonas", zonarepo.findAll());
	    		
		// Obtener el rol del usuario logueado (Inversionista)
	    Rol r = usuario.getIdRol();	    	
		String desrol = r.getDescripcion();		
		session.setAttribute("rol", desrol);		
		String rol = (String) session.getAttribute("rol");
		// Agregar el rol del usuario logueado (Inversionista) al modelo
		model.addAttribute("rol", rol);   
		//
		
	    return "nuevoJefe";
	}
	
	@GetMapping("/corregirJefe")
	public String corregirjefe(@ModelAttribute("jefe") Usuario jefe,
		    				 @ModelAttribute("lstZonas") List<Zona> lstZonas,
		    				 @ModelAttribute("errorField") String errorField,
		    				 Model model) {
	
	    model.addAttribute("jefe", jefe);
	    model.addAttribute("lstZonas", lstZonas);
	    model.addAttribute("errorField", errorField);
		return "nuevoJefe";
	}
		
    @PostMapping("/registrarJefe")
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
    	
    	// Declarar un objeto de tipo Usuario
    	Usuario jefe = new Usuario();
    	
    	// LLenar campos a traves de los parametros
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
    	
    	Usuario usuemail = invrepo.findByEmail(jefe.getEmail());
    	if(usuemail == null) {
    		Usuario usutel = invrepo.findByTelefono(jefe.getTelefono());
    		if(usutel == null) {
    			Usuario usudni = invrepo.findByDni(jefe.getDni());
    			if(usudni == null) {
    				usurepo.save(jefe);
    				return "redirect:/nuevoJefe?registroExitoso";
    			}else {
					attribute.addFlashAttribute("mensaje", "El Jefe con el Correo: "+jefe.getEmail()+" ya existe");
		            attribute.addFlashAttribute("jefe", jefe);
		            attribute.addFlashAttribute("lstZonas", zonarepo.findAll());
		            attribute.addFlashAttribute("errorField", "email");
				}
    		}else{
				attribute.addFlashAttribute("mensaje", "El Jefe con el Teléfono: "+jefe.getTelefono()+" ya existe");
	            attribute.addFlashAttribute("jefe", jefe);
	            attribute.addFlashAttribute("lstZonas", zonarepo.findAll());
	            attribute.addFlashAttribute("errorField", "telefono");
    		}
    	}else {
			attribute.addFlashAttribute("mensaje", "El Jefe con el DNI: "+jefe.getDni()+" ya existe");
            attribute.addFlashAttribute("jefe", jefe);
            attribute.addFlashAttribute("lstZonas", zonarepo.findAll());
            attribute.addFlashAttribute("errorField", "dniUsuario");
		}
    	
    	return "redirect:/corregirJefe";
    }
    
	@GetMapping("/listaJefe")
	public String cargarPaginaListar(Model model, HttpSession session) {
		// Buscar Jefes de prestamistas con estado 0 = Activo
		List<Usuario> listaJefes = invrepo.findByIdRolDescripcionAndEstado("Jefe de Prestamista", 0);
		// Agregar lista de Jefes de prestamistas al modelo
		model.addAttribute("lstJefes", listaJefes);
		
		// Dar permiso por rol a las acciones de Inversionista
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String nombreUsuario = auth.getName();		
	    Usuario usuario = usurepo.findByNombres(nombreUsuario);   
	    Rol r = usuario.getIdRol();
	    String desrol = r.getDescripcion();	
		session.setAttribute("rol", desrol);		
		String rol = (String) session.getAttribute("rol");	
		model.addAttribute("rol", rol);
		//
		
		return "listaJefe";
	}
    
	@GetMapping("/obtenerJefe")
	public String cargarDatosActualizar(@RequestParam("id") int idUsuario, Model model, HttpSession session) {
		// Buscar usuario por id
		Usuario usu = usurepo.findByIdUsuario(idUsuario);
		
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String nombreUsuario = auth.getName();
	    
	    Usuario usuario = usurepo.findByNombres(nombreUsuario);  
	    int inversionista = usuario.getIdUsuario();
	    model.addAttribute("idUsuario", inversionista);	    
	    int idRol = usuario.getIdRol().getIdRol();	    
	    model.addAttribute("idRol", idRol);
	    
	    // Obtener la zona del jefe de prestamista
	    Zona zonaJefe = usu.getIdZona();	    
	    List<Zona> listaZonas = zonarepo.findAll();
	    // Eliminar la zona del jefe del la lista para evitar duplicidad
	    listaZonas.remove(zonaJefe);
	    
	    // Agregar lista de zonas al modelo
	    model.addAttribute("listzonas", listaZonas);
	    // Agregar jefe al modelo
		model.addAttribute("jefe", usu);
		// Agregar la zona del jefe al modelo
		model.addAttribute("zonajefe", zonaJefe);
		
		
		//
	    Rol r = usuario.getIdRol();
	    String desrol = r.getDescripcion();	
		session.setAttribute("rol", desrol);		
		String rol = (String) session.getAttribute("rol");	
		model.addAttribute("rol", rol);
		//
		
		return "actualizarJefe";
	}
	
	@PostMapping("/actualizarJefe")
	public String actualizarJefe(@ModelAttribute Usuario jefe, @RequestParam("nombres") String nombres,
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
		
    	String passwordEncriptado = passwordEncoder.encode(password);
    	 	
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

    	return "redirect:/listaJefe?actualizacionExitosa";
	}
    
	@GetMapping("/buscarJefe")
	public String cargarPaginaBuscar(@RequestParam(name = "search", required = false) String search, Model model, HttpSession session) {
		List<Usuario> listaJefes = null;
		
	    if (search != null && !search.isEmpty()) {
	    	listaJefes = invrepo.findBySearchAndIdRolDescripcionAndEstado(search, "Jefe de Prestamista", 0);
	    } else {
	    	listaJefes = invrepo.findByIdRolDescripcionAndEstado("Jefe de Prestamista", 0);
	    }
	    
	    model.addAttribute("lstJefes", listaJefes);
	    
		//
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String nombreUsuario = auth.getName();		
	    Usuario usuario = usurepo.findByNombres(nombreUsuario);   
	    Rol r = usuario.getIdRol();
	    String desrol = r.getDescripcion();		
		session.setAttribute("rol", desrol);		
		String rol = (String) session.getAttribute("rol");	
		model.addAttribute("rol", rol);
		//
		
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
