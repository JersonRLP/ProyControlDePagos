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
	
	@Query("SELECT u FROM Usuario u WHERE u.idUsuarioLider = :idUsuario AND u.estado = :estado")
	List<Usuario> findByIdUsuarioLiderAndEstado(Integer idUsuario, int estado);
}
