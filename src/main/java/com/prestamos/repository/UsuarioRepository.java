package com.prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prestamos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
