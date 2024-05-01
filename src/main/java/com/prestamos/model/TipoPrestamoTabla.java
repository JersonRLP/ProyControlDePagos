package com.prestamos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="tipoprestamotabla")
public class TipoPrestamoTabla {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int idTipoPrestamoTabla;
	private int dias;
	private double tasa;
	private double monto;
	private double montotasa;
	private int estado;
	
	
}
