package com.prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prestamos.model.Usuario;

import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
	Usuario findByNombres(String nombres);
	Usuario findByIdUsuario(int idUsuario);
}
