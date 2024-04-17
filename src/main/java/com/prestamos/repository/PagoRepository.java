package com.prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prestamos.model.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {

}
