package com.prestamos.controller;

import com.prestamos.model.Rol;
import com.prestamos.model.Usuario;
import com.prestamos.model.Zona;
import com.prestamos.repository.UsuarioRepository;
import com.prestamos.repository.ZonaRepository;
import com.prestamos.service.PrestamistaService;
import com.prestamos.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PrestamistaController {

	@Autowired
	private PrestamistaService prestamistaService;

	@Autowired
	private RolService rolService;

	@Autowired
	private ZonaRepository zonarepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioRepository usurepo;


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

		// Obtener el usuario autenticado
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String nombreUsuario = authentication.getName();
		Usuario usuarioAutenticado = usurepo.findByNombres(nombreUsuario);

		// Obtener el idUsuarioLider del usuario autenticado
		Integer idUsuarioLider = usuarioAutenticado.getIdUsuario();

		// Obtener todos los prestamistas
		List<Usuario> prestamistas = prestamistaService.obtenerTodos();
		List<Usuario> prestamistasFiltrados = new ArrayList<>();

		// Filtrar los prestamistas que tienen el rol con ID 5 y el idUsuarioLider correspondiente al usuario autenticado
		for (Usuario prestamista : prestamistas) {
			if (prestamista.getIdRol().getIdRol() == 5 && prestamista.getIdUsuarioLider() != null && prestamista.getIdUsuarioLider() == idUsuarioLider) {
				prestamistasFiltrados.add(prestamista);
			}
		}

		model.addAttribute("roles", roles);
		model.addAttribute("prestamistas", prestamistasFiltrados);
		return "prestamista-list";
	}

	@GetMapping("/prestamista-crear")
	public String mostrarFormularioCrear(Model model) {
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

		// Obtener la descripcion de la zona del usuario actual
		int idZona = usuario.getIdZona().getIdZona();

		// Poner la descripcion de la zona en el modelo
		model.addAttribute("idZona", idZona);

		// También puedes pasar otros datos necesarios al modelo
		model.addAttribute("listzonas", zonarepo.findAll());

		return "prestamista-crear";
	}

	@PostMapping("/crear")

		public String crearPrestamista (@RequestParam("nombres") String nombres,
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
				RedirectAttributes attribute){

			// Encriptar la contraseña antes de guardarla
			String passwordEncriptado = passwordEncoder.encode(password);

			Usuario prestamista = new Usuario();
			prestamista.setNombres(nombres);
			prestamista.setApePaterno(apePaterno);
			prestamista.setApeMaterno(apeMaterno);
			prestamista.setPassword(passwordEncriptado);
			prestamista.setEmail(email);
			prestamista.setTelefono(telefono);
			prestamista.setDni(dni);
			prestamista.setEstado(estado);
			prestamista.setIdRol(idRol);
			prestamista.setIdZona(idZona);
			prestamista.setIdUsuarioLider(idUsuarioLider);

			usurepo.save(prestamista);

			return "redirect:/prestamista-crear?registroExitoso";


		}

		@GetMapping("/editar/{id}")
		public String mostrarFormularioEditar (@PathVariable Integer id, Model model){
			Usuario usuario = prestamistaService.obtenerPorId(id);
			model.addAttribute("usuario", usuario);
			return "prestamista-actualizar";
		}

		@PostMapping("/editar/{id}")
		public String actualizarPrestamista (@PathVariable Integer id, @ModelAttribute Usuario usuario){
			usuario.setIdUsuario(id); // Asegurarse de que el rol tenga el ID correcto
			prestamistaService.actualizar(usuario);
			return "redirect:/prestamista-list";
		}

		@GetMapping("/eliminar/{id}")
		public String eliminarPrestamista (@PathVariable Integer id){
			prestamistaService.eliminar(id);
			return "redirect:/prestamistas";
		}
	}

