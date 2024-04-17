package com.prestamos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prestamos.model.Usuario;
import com.prestamos.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService ususervice;
	
	@GetMapping("/listausuarios")
	public List<Usuario> obtenerUsuarios(){
		return ususervice.obtenerUsuarios();
	}
	
	@PostMapping("/crearUsuario")
	public String crearUsuario(Usuario usu) {
		return ususervice.crearUsuario(usu);
	}

}
