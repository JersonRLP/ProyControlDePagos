package com.prestamos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prestamos.model.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {

    List<Solicitud> findByIdPrestatarioIdUsuario(int idPrestatario);
    List<Solicitud> findByIdPrestamistaIdUsuario(int idPrestamista);

    @Query("SELECT s FROM Solicitud s " +
            "JOIN s.idPrestatario p " +
            "WHERE (LOWER(p.nombres) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "       OR (s.fecInicio BETWEEN :fecha1 AND :fecha2) " +
            "       OR (s.fecFin BETWEEN :fecha1 AND :fecha2)) " +
            "       AND s.idPrestamista.idUsuario = :idPrestamista")
    List<Solicitud> findBySearchAndIdPrestatarioNombresAndFecha1AndFecha2AndIdPrestamistaIdUsuario(@Param("search") String search,
                                                                                                   @Param("fecha1") Date fecha1,
                                                                                                   @Param("fecha2") Date fecha2,
                                                                                                   int idPrestamista);
}