package com.prestamos.repository;

import com.prestamos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestatarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.idUsuarioLider = :idUsuario")
    List<Usuario> obtenerPrestatariosDelPrestamista(Integer idUsuario);

    @Query("SELECT u FROM Usuario u JOIN u.idRol r WHERE u.nombres LIKE %:nombres% AND r.idRol = :idRol")
    List<Usuario> findByNombresAndRolId(String nombres, Integer idRol);

    @Query("SELECT u FROM Usuario u JOIN u.idRol r WHERE " +
            "(:nombres is null or u.nombres like %:nombres%) AND " +
            "(:apePaterno is null or u.apePaterno like %:apePaterno%) AND " +
            "(:apeMaterno is null or u.apeMaterno like %:apeMaterno%) AND " +
            "(:email is null or u.email like %:email%) AND " +
            "(:telefono is null or u.telefono like %:telefono%) AND " +
            "(:dni is null or u.dni like %:dni%) AND " +
            "(r.idRol = :idRol)")
    List<Usuario> findByAttributes(String nombres,
                                String apePaterno,
                                String apeMaterno,
                                String email,
                                String telefono, String dni,
                                Integer idRol);

}
