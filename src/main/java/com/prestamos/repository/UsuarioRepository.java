package com.prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prestamos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
	Usuario findByNombres(String nombres);
	Usuario findByIdUsuario(int idUsuario);
}
