package com.prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prestamos.model.Usuario;

import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
	Usuario findByNombres(String nombres);
	Usuario findByIdUsuario(Long idUsuario);
}
