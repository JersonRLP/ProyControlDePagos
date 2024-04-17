package com.prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prestamos.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

}
