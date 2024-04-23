package com.prestamos.repository;

import com.prestamos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamistaRepository extends JpaRepository<Usuario, Integer> {

    //@Query("SELECT u FROM Usuario u WHERE u.idRol = :idRol")
   //List<Usuario> findByRolId(Integer idRol);

}
