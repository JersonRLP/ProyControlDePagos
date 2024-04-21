package com.prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prestamos.model.Rol;

import java.util.List;


public interface RolRepository extends JpaRepository<Rol,Long> {

	
}
