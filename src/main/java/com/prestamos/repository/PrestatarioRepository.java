package com.prestamos.repository;

import com.prestamos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestatarioRepository extends JpaRepository<Usuario, Integer> {
}
