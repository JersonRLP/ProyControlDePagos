package com.prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prestamos.model.Usuario;

import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
	Usuario findByNombres(String nombres);
	Usuario findByIdUsuario(int idUsuario);
	List<Usuario> findByIdRolDescripcionAndEstado(String descripcion, int estado);
	
	  @Query("SELECT u FROM Usuario u " +
	           "JOIN u.idRol r " +
	           "WHERE (LOWER(u.dni) LIKE LOWER(CONCAT('%', :search, '%')) " +
	           "       OR LOWER(u.nombres) LIKE LOWER(CONCAT('%', :search, '%')) " +
	           "       OR LOWER(u.apePaterno) LIKE LOWER(CONCAT('%', :search, '%'))) " +
	           "AND LOWER(r.descripcion) = LOWER(:rolDescripcion) " +
	           "AND u.estado = :estado")
	    List<Usuario> findBySearchAndIdRolDescripcionAndEstado(@Param("search") String search,
	                                                         @Param("rolDescripcion") String rolDescripcion,
	                                                         @Param("estado") int estado);
	
	/*List<Usuario> findByDniContainingOrNombresContainingOrApePaternoContainingAndIdRolDescripcionAndEstado
	(String dni, String nombres, String apePaterno, String descripcion, int estado);*/
}
