package com.prestamos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_tipoprestamo")
public class TipoPrestamo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoPrestamo;
	
	@Column(name = "tasa")
	private double tasa;
	
	@Column(name = "estado")
	private int estado;

	public int getIdTipoPrestamo() {
		return idTipoPrestamo;
	}

	public void setIdTipoPrestamo(int idTipoPrestamo) {
		this.idTipoPrestamo = idTipoPrestamo;
	}

	public double getTasa() {
		return tasa;
	}

	public void setTasa(double tasa) {
		this.tasa = tasa;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
}
