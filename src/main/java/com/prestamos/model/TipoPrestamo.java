package com.prestamos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_tipoprestamo")
public class TipoPrestamo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoPrestamo;

	@Column(name = "tasa")
	private double tasa;

	@Column(name = "estado")
	private int estado;

}
