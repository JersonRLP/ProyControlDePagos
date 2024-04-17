package com.prestamos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.prestamos.model.Rol;
import com.prestamos.model.Usuario;
import com.prestamos.model.Zona;
import com.prestamos.repository.RolRepository;
import com.prestamos.repository.UsuarioRepository;
import com.prestamos.repository.ZonaRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usurepo;
	
	@Autowired
	RolRepository rolrepo;
	
	@Autowired
	ZonaRepository zonarepo;
	
	public List<Usuario> obtenerUsuarios(){
		return  usurepo.findAll();
	}
	
	public String crearUsuario(@RequestBody Usuario usuario) {
		usurepo.save(usuario);
		return "Usuario creado";
	}
	
	public List<Rol> obtenerRoles(){
		return  rolrepo.findAll();
	}
		
	public List<Zona> obtenerZonas(){
		return zonarepo.findAll();
	}
}
