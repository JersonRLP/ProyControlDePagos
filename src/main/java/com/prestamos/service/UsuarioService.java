package com.prestamos.service;

import com.prestamos.dto.UsuarioDTO;

public interface UsuarioService {
	UsuarioDTO obtenerUsuarioPorId(int id);
    boolean actualizarUsuario(int id, UsuarioDTO usuarioDTO);
}
