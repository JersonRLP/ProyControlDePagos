package com.prestamos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prestamos.dto.UsuarioDTO;
import com.prestamos.model.Usuario;
import com.prestamos.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	 @Autowired
	    private UsuarioRepository usuarioRepository;

	 @Override
	    public UsuarioDTO obtenerUsuarioPorId(int id) {
	        Usuario usuario = usuarioRepository.findByIdUsuario(id);
	        if (usuario == null) {
	            return null;
	        }
	        UsuarioDTO usuarioDTO = new UsuarioDTO();
	        usuarioDTO.setUsername(usuario.getUsername());
	        usuarioDTO.setNombres(usuario.getNombres());
	        usuarioDTO.setApePaterno(usuario.getApePaterno());
	        usuarioDTO.setApeMaterno(usuario.getApeMaterno());
	        usuarioDTO.setEmail(usuario.getEmail());
	        usuarioDTO.setTelefono(usuario.getTelefono());
	        usuarioDTO.setDni(usuario.getDni());
	        return usuarioDTO;
	    }

	    @Override
	    public boolean actualizarUsuario(int id, UsuarioDTO usuarioDTO) {
	        Usuario usuario = usuarioRepository.findByIdUsuario(id);
	        if (usuario == null) {
	            return false;
	        }
	        usuario.setUsername(usuarioDTO.getUsername());
	        usuario.setNombres(usuarioDTO.getNombres());
	        usuario.setApePaterno(usuarioDTO.getApePaterno());
	        usuario.setApeMaterno(usuarioDTO.getApeMaterno());
	        usuario.setEmail(usuarioDTO.getEmail());
	        usuario.setTelefono(usuarioDTO.getTelefono());
	        usuario.setDni(usuarioDTO.getDni());
	        usuarioRepository.save(usuario);
	        return true;
	    }
}
