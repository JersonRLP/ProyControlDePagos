package com.prestamos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.prestamos.model.Usuario;
import com.prestamos.repository.UsuarioRepository;


public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String nombres) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByNombres(nombres);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		return new MyUserDetails(usuario);
	}

}
