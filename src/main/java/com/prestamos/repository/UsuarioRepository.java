package com.prestamos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prestamos.model.Rol;
import com.prestamos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
	Usuario findByIdUsuario(Integer selectPrestamista);
	Usuario findByNombres(String nombres);
	Usuario findByIdUsuario(int idUsuario);
	List<Usuario> findByIdRol(Rol idRol);
	List<Usuario> findByIdUsuarioLiderAndEstado(int id, int estado);
}
